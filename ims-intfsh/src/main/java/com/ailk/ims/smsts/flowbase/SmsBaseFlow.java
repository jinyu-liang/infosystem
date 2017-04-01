package com.ailk.ims.smsts.flowbase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jef.database.AutoCloseIterator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.smsts.bean.FreeresBaseInfo;
import com.ailk.ims.smsts.bean.ScanStartInfo;
import com.ailk.ims.smsts.bean.ScanStopInfo;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.cmp.SmsCmp;
import com.ailk.ims.smsts.config.SmsConfig;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.group.IGrouper;
import com.ailk.ims.smsts.flowbase.group.ResourceIdResGrouper;
import com.ailk.ims.smsts.helper.BuildHelper;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.smsts.service.ISmsService;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.jd.entity.SmsSendLog;

/**
 * 2012-11-09 短信
 * 
 * @author gaoqc5
 */
public abstract class SmsBaseFlow extends CacheFlow implements IBaseFlow
{
    protected ImsLogger imsLogger = new ImsLogger(this.getClass()); // 子类可以直接使用
    private Integer busiNum;// 根据method获取，子类可以直接使用
    private SmsCmp smsCmp;
    private Date currentDate = DateUtil.currentDate();

    // 批次提交失败的最大次数(超过这个阀值,则退出)
    private static final int BLOCKERRORMAXCOUNT = 100;

    /**
     * 根据method获取，子类可以直接使用而不需要重写
     */
    public Integer getBusiNum()
    {
        return busiNum;
    }

    public DBCondition[] getQueryConds()
    {

        return new DBCondition[] {};
    }

    /**
     * 对应扫描表的数据库实体对象类 比如：扫描CmResource表，则返回CmResource.class<BR>
     * 默认扫描用户表,特殊业务可以覆盖
     */
    public Class<? extends DataObject> getScanTableClass()
    {
        return CmResource.class;
    }

    /**
     * 要扫描的表中的扫描字段<BR>
     * 默认为用户ID,特殊业务可以覆盖
     */
    public Field getKeyField()
    {
        return CmResource.Field.resourceId;
    }

    /**
     * 指定排序字段 <BR>
     * 用户表统一按照用户编号排序,特殊业务可以覆盖
     */
    public OrderCondition[] getOrderCond()
    {
        OrderCondition orderCondition = new OrderCondition(true, CmResource.Field.resourceId);
        return new OrderCondition[] { orderCondition };
    }

    /**
     * 当需要发短信的用户数到达batchSize时，短信明细表提交一次<BR>
     * 特殊业务可以覆盖
     */
    public int getBatchSize()
    {
        return SysUtil.getInt("SMS_SEND_BATCH_NUM");
    }

    /**
     * 扫描流程的实现：先查询、然后针对查询的数据做业务处理、最后插入批量短信通知表
     * 
     * @param subTableName: 分表名：如cm_resource_01
     */
    public void dealProcess(SmsParam param) throws Exception
    {
        String subTableName = param.getSubTableName();
        busiNum = param.getBusiNum();

        CaFreeresDayNotify dayNotify = param.getDayNotify();
        // 先判断该流程是否已经执行过
        ScanStartInfo startInfo = SmsHelper.initStartInfo(subTableName, busiNum, getQueryConds(), getKeyField(), currentDate,param);

        if (startInfo == null)
        {
            imsLogger.info(subTableName, " need not to be executed, finish");
            return;// 本次任务无需执行
        }
        beforeDo(param);

        // 从 AD.CA_FREERES分表中找出该科目的用户
        this.smsCmp = new SmsCmp(this.initContext());
        long beginTime = System.currentTimeMillis();
        Long blockId = startInfo.getBlockId();// 明细表和总表的关联字段值
        int size = getBatchSize();
        imsLogger.info("以:", size, "条数据为一次事务提交");
        ScanStopInfo scanStopInfo = new ScanStopInfo(size + 8);
        String taskDate = startInfo.getTaskDate();
        ISmsService smsService = SmsConfig.getSmsService();
        AutoCloseIterator<?> iterator = getDataIterator(FreeresDayNotifyHelper.getFreeresTableName(dayNotify));
        if (null == iterator)
        {
            return;
        }
        // 2012-12-24 gaoqc5 上一次批量提交发生的异常不能影响下一批次;但是如果异常次数多于某个阀值,认为是程序或者数据是存在问题的,需要跳出,否则可能出现死循环..
        int currentErrorCount = 0;
        int counter = 0;
        while (currentErrorCount < BLOCKERRORMAXCOUNT)
        {
            try
            {
                while (smsService.commitGroupDetalBatch(iterator, this, subTableName, blockId, taskDate, scanStopInfo,
                        getGrouper(), ++counter,param))
                    ;
                // 正常跑完
                break;
            }
            catch (Exception e)
            {
                currentErrorCount++;
                imsLogger.error(e, e);
            }

        }
        imsLogger.info("提交失败次数:", currentErrorCount);
        if (currentErrorCount >= BLOCKERRORMAXCOUNT)
        {
            imsLogger.info("因批量提交失败次数过多(",currentErrorCount,"程序退出,请检查原因!");
        }
//        int allCount = scanStopInfo.getStopCount(); // 记录此次扫描需要发送的短信条数

        // 2012-12-31 gaoqc5 审核总表和日志表的时间使用完成扫描的时间,即插入数据时的
        Date nowDate = new Date();

        // DBUtil.insertBatch(smsDetails);
//        Long modCode = getTemplateId();// EnumSmsDefine.IM_SH_FREERES_MODE_CODE;
//        if (null == modCode)
//        {
//            modCode = EnumSmsDefine.IM_SH_FREERES_MODE_CODE;
//        }
          //短信总表的插入动作已经移到SmsTsInterceptor 完成
//        CheckSmsSend checkSmsSend = BuildHelper.buildCheckSmsSend(blockId, modCode, nowDate, allCount);// 构建总表信息
        SmsSendLog smsSendLog = BuildHelper.buildSmsSendLog(subTableName, busiNum, taskDate, nowDate);

        smsService.commitTotal(null, smsSendLog);
        // 钩子
        afterDo();
        imsLogger.info(beginTime, "短信扫描完成!");
        iterator.close();

    }

    /**
     * 在轮询数据前的钩子
     * 
     * @param dayNotify
     */
    protected void beforeDo(SmsParam param)
    {
    }

    /**
     * 轮询数据后的钩子
     * 
     * @param dayNotify
     */
    protected void afterDo()
    {
    }

    /**
     * 可继承
     * 
     * @param iterator
     * @return
     * @throws
     * @throws IMSException
     */
    protected AutoCloseIterator<?> getDataIterator(String freeresTableName) throws Exception
    {

        String[] itemsKeyWords = getItemsKeyWords();
        String sql = null;
        if (null == itemsKeyWords || itemsKeyWords.length == 0)
        {// 不带条件查询
            sql = buildSql(null, freeresTableName);
        }
        else
        {
            StringBuffer items = new StringBuffer();
            for (String itemKeyWord : itemsKeyWords)
            {// 一个流程可能有多个科目
                items.append(getItemsCodeByItemKeyWord(itemKeyWord) + ",");

            }
            sql = buildSql(CommonUtilSh.removeLastFlag(items.toString(), ','), freeresTableName);

        }
        imsLogger.debug("编译前sql:" + sql);
        return DBUtil.getDBClient().createNativeQuery(sql, FreeresBaseInfo.class).getResultIterator();

    }

    /**
     * 获取短信模板
     */
    protected abstract Long getTemplateId();

    protected abstract String[] getItemsKeyWords();

    private IMSContext initContext()
    {
        IMSContext context = new IMSContext();
        SOperInfo oper = new SOperInfo();
        context.setOper(oper);
        context.setRequestDate(currentDate);
        return context;
    }

    protected IGrouper getGrouper()
    {
        return new ResourceIdResGrouper();
    }

    /**
     * 允许被重写
     * 
     * @param items
     * @param freeresTableName
     * @return
     */
    protected String buildSql(String items, String freeresTableName)
    {
        return new StringBuffer("select  t.RESOURCE_ID as resourceId,")
                .append(" SUM(CASE measure_id    WHEN 104 THEN 1024*unit   WHEN 105 THEN 1024*1024*unit  WHEN 101 THEN unit/1000   WHEN 106 THEN unit*60 WHEN 107 THEN unit*3600  else unit END) as unit,")
                .append(" SUM(CASE measure_id    WHEN 104 THEN 1024*real_unit   WHEN 105 THEN 1024*1024*real_unit  WHEN 101 THEN real_unit/1000   WHEN 106 THEN real_unit*60 WHEN 107 THEN real_unit*3600  else real_unit END) as realUnit ")
                .append(" from " + freeresTableName + " t"). // 表名
                append(buildCondition(items))// 条件
                .append(" GROUP BY t.RESOURCE_ID ")// 分组
                .append(buildHavingCondition()).append(" order by t.RESOURCE_ID")// 排序
                .toString();

    }

    /**
     * 设计成可继承的
     * 
     * @return
     */
    protected String buildHavingCondition()
    {
        return "";
    }

    /**
     * 设计成可继承的 gao 2012-11-15
     * 
     * @param items
     * @return
     */
    protected String buildCondition(String items)
    {
        if (null == items || items.trim().length() == 0)
        {
            return " where 1=1 ";
        }
        else
        {
            return CommonUtil.concat(" where item_code in(", items, " ) ");
        }
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    /**
     * 供子类使用
     */
    public SmsCmp getSmsCmp()
    {
        return smsCmp;
    }
    /**
     * 提供默认的方法，适合于一个流程只有一个短信模板的流程
     *如果一个流程有多个短信模板，则需要覆盖，返回短信模板与批次号的关系
     */
    @Override
    public Map<Long, Long> getTemplateAndBlockIdMap()
    {
        
        Map<Long, Long> map = new HashMap<Long, Long>();
        Long tpl = getTemplateId();
        if (null != tpl)
        {
            map.put(tpl, SmsSeqConfig.newBlockId());
        }else{  //如果为空，则填入默认的，否则没blockId
            map.put(0L  , SmsSeqConfig.newBlockId());
        }
        return map;
    }
    

}
