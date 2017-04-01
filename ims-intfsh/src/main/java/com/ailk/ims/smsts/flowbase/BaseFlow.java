package com.ailk.ims.smsts.flowbase;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jef.common.wrapper.IntRange;
import jef.database.AutoCloseIterator;
import jef.database.DataObject;
import jef.database.Field;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.smsts.bean.ScanStartInfo;
import com.ailk.ims.smsts.bean.ScanStopInfo;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.cmp.SmsCmp;
import com.ailk.ims.smsts.config.SmsConfig;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.helper.BuildHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.smsts.service.ISmsService;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.jd.entity.SmsSendLog;

/**
 * @Description: 游标类 定时扫描上发流程基础类
 * @author wukl
 * @Date 2012-7-16
 */
public abstract class BaseFlow extends CacheFlow implements IBaseFlow
{
    protected ImsLogger imsLogger = new ImsLogger(this.getClass()); // 子类可以直接使用
    private Integer busiNum;// 根据method获取，子类可以直接使用
    private SmsCmp smsCmp;
    private Date currentDate = DateUtil.currentDate();
    //批次提交失败的最大次数(超过这个阀值,则退出)
    private static final int BLOCKERRORMAXCOUNT=100;
    /**
     * 根据method获取，子类可以直接使用而不需要重写
     */
    public Integer getBusiNum()
    {
        return busiNum;
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
        return    SysUtil.getInt("SMS_SEND_BATCH_NUM");
    }

    /**
     * 扫描流程的实现：先查询、然后针对查询的数据做业务处理、最后插入批量短信通知表
     * 
     * @param subTableName: 分表名：如cm_resource_01
     */
    public void dealProcess(SmsParam param) throws Exception
    {
        String subTableName=param.getSubTableName();
         Integer busiNum =param.getBusiNum();
        ScanStartInfo startInfo = SmsHelper.initStartInfo(subTableName, busiNum, getQueryConds(), getKeyField(), currentDate,param);
        if (startInfo == null)
        {
            imsLogger.info(subTableName, " need not to be executed, finish");
            return;// 本次任务无需执行
        }

        this.busiNum = busiNum;
        // 实例化cmp类
        this.smsCmp = new SmsCmp(this.initContext());

        String taskDate = startInfo.getTaskDate();
        Long blockId = startInfo.getBlockId();// 明细表和总表的关联字段值

        DBCondition[] queryCondArr = startInfo.getQueryCondArr();

        imsLogger.info("+++busiNum:", busiNum, ",subTableName:", subTableName, ",taskDate:", taskDate, " start execute....");
        AutoCloseIterator<?> iter = null;
        try
        {
            beforeDo(param);
            // 根据传入的信息封装成查询条件
            Query<?> query = DBUtil.getQueryCondition(this.getScanTableClass(), queryCondArr, getOrderCond());

            IntRange range = null;
            // range = new IntRange(0, SmsTestHelper.getScanUserCount());

            imsLogger.debug("###open iterate of subtable: ", subTableName);
            // 调用游标查询方法进行查询
            iter = getIterator(query, range, subTableName);
            int size=getBatchSize();
            imsLogger.info("以:",size,"条数据为一次事务提交");
            ScanStopInfo scanStopInfo = new ScanStopInfo(size + 8);
            scanStopInfo.setStopCount(startInfo.getStartCount());// 设置上次任务意外终止的记录数

            ISmsService smsService = SmsConfig.getSmsService();
         

            // while(notToEnd)
            //2012-12-24 gaoqc5 上一次批量提交发生的异常不能影响下一批次;但是如果异常次数多于某个阀值,认为是程序或者数据是存在问题的,需要跳出否则,可能出现死循环.
           int currentErrorCount=0;
           int counter=0;
            while (currentErrorCount<BLOCKERRORMAXCOUNT)
            {
                try
                {
                    while (smsService.commitDetalBatch(iter, this, subTableName, blockId, taskDate, scanStopInfo,++counter,param))
                        ;
                    //正常跑完
                    break;
                }
                catch (Exception e)
                {
                    imsLogger.error(e,e);
                    currentErrorCount++;
                }

            }
            imsLogger.info("提交失败次数:", currentErrorCount);
            if(currentErrorCount>=BLOCKERRORMAXCOUNT){
            imsLogger.info("因批量提交失败次数过多(",currentErrorCount,"程序退出,请检查原因!");
            }
            int allCount = scanStopInfo.getStopCount(); // 记录此次扫描需要发送的短信条数

            imsLogger.info("***** finsh to insert SmsSendInterfaceCheck, insert rows:", allCount);
            // 2012-12-31 gaoqc5 审核总表和日志表的时间使用完成扫描的时间,即插入数据时的
            Date nowDate =new Date();
     
            // 2012-11-10 gaoqc5 对于不需要发短信的流程，返回值可为null
//            Long modCode =getTemplateId();
//              if(null==modCode){
//                  modCode=EnumSmsDefine.IM_SH_FREERES_MODE_CODE; 
//              }
              //短信总表的插入动作已经移到SmsTsInterceptor 完成
//            CheckSmsSend checkSmsSend = BuildHelper.buildCheckSmsSend(blockId, modCode, nowDate, allCount);// 构建总表信息
            SmsSendLog smsSendLog = BuildHelper.buildSmsSendLog(subTableName, busiNum, taskDate, nowDate);

            smsService.commitTotal(null, smsSendLog);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            imsLogger.error(e.getMessage());
            throw new Exception(e);
        }
        finally
        {
            if (iter != null)
            {
                iter.close();
            }
        }

        imsLogger.info("+++busiNum:", busiNum, ",subTableName:", subTableName, ",taskDate:", taskDate, " end execute....");
    }
    protected  AutoCloseIterator<?>  getIterator(Query<?> query,  IntRange range, String subTableName ) throws IMSException, SQLException{
        return  DBUtil.getDBClient().iteratedSelect(query, range, new MyTableName(subTableName));
    }
    
    
    /**
     * 在轮询数据前的钩子
     * @param dayNotify
     */
    protected  void  beforeDo(SmsParam param){}
    /**
     * 轮询数据后的钩子
     * @param dayNotify
     */
    protected  void  afterDo(){}
    
    /**
     * 获取短信发送时间
     */
//    protected abstract  Date getMessageSendDate();
    /**
     * 获取短信模板
     */
    protected abstract  Long getTemplateId();
    private IMSContext initContext()
    {
        IMSContext context = new IMSContext();
        SOperInfo oper = new SOperInfo();
        context.setOper(oper);
        context.setRequestDate(currentDate);
        return context;
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
        }else{  //如果为空，则填入默认的
            map.put(0L  , SmsSeqConfig.newBlockId());
        }
        return map;
    }
    
    
}
