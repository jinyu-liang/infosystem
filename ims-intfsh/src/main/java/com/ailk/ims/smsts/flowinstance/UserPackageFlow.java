package com.ailk.ims.smsts.flowinstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 用户套餐免费资源上网时长提醒, 业务逻辑:只要订购了销售品Id为 10000016的有效用户,如果有免费资源则发送短信提醒
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * bus_num=7503
 * @Author zenglu
 * @Date 2012-07-31
 */
public class UserPackageFlow extends BaseFlow
{
    private static final long templateId = EnumSmsDefine.IM_SH_USER_PACKAGE_CODE;
    private CaFreeresDayNotify dayNotify;

    /**
     * 对应扫描表的数据库实体对象类 比如：扫描CmResource表，则返回CmResource.class<BR>
     * 默认扫描用户表,特殊业务可以覆盖
     */
    public Class<? extends DataObject> getScanTableClass()
    {
        return CoProd.class;
    }
    /**
     * 指定排序字段 <BR>
     * 用户表统一按照用户编号排序,特殊业务可以覆盖
     */
    public OrderCondition[] getOrderCond()
    {
        OrderCondition orderCondition = new OrderCondition(true, CoProd.Field.objectId);
        return new OrderCondition[] { orderCondition };
    }
    /**
     * 要扫描的表中的扫描字段<BR>
     * 默认为用户ID,特殊业务可以覆盖
     */
    public Field getKeyField()
    {
        return CoProd.Field.objectId;
    }
    public DBCondition[] getQueryConds()
    {
        return new DBCondition[] { new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
                new DBCondition(CoProd.Field.productOfferingId, EnumSmsDefine.SMS_NOTIFY_USER_PACKAGE_OFFERID),
                new DBCondition(CoProd.Field.validDate, getCurrentDate(), Operator.LESS),
                new DBCondition(CoProd.Field.expireDate, getCurrentDate(), Operator.GREAT) };
    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        CoProd prod = (CoProd) obj;
        Long userId = prod.getObjectId();
        IMSUtil.setUserRouterParam(userId);
        // 判断是否是无效用户
        if (isInvalidateUser(userId))
        {
            imsLogger.info("[" + userId + "]是无效用户");
            return null;
        }

        // 判断用户套餐的主产品
        Long acctId = getSmsCmp().loadAcctIdByUserId(userId);
        String phoneId = getSmsCmp().loadPhoneIdByUserId(userId);
        if (null == acctId || null == phoneId)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_PHONEID_ERROR, "acctId", "phoneId");
        }

          List<CaFreeres>list= getCaFreeres(userId);
          if(CommonUtil.isEmpty(list)){
              imsLogger.info("用户[",userId,"],账号[",acctId,"]没没费资源,不提醒");
              return null;
          }
 

        // 获取短信
        String template = getMessage(list);

        // 要插的短信表
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
        if (CommonUtil.isEmpty(template))
        {

            imsLogger.info("************************* 短信模板内容为空,不发送信息!");
        }
        else
        {
            // 返回短信审核明细表
            smsList.add(createSmsSendInterfaceCheck(template, phoneId, templateId, blockId));
        }
        return smsList;
    }

    private boolean isInvalidateUser(Long userId)
    {
        return DBUtil.queryCount(CmResource.class, getQueryConds(userId)) == 0;
    }

    private DBCondition[] getQueryConds(Long userId)
    {
        return new DBCondition[] { new DBCondition(CmResource.Field.activeDate, getCurrentDate(), Operator.LESS_EQUALS),
                new DBCondition(CmResource.Field.billingType, 1),
                new DBCondition(CmResource.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS),
                new DBCondition(CmResource.Field.expireDate, getCurrentDate(), Operator.GREAT),
                new DBCondition(CmResource.Field.resourceId, userId) };

    }

    private SmsSendInterfaceCheck createSmsSendInterfaceCheck(String template, String phoneId, Long templateId, Long blockId)
    {
        // 返回短信审核明细表
        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setMessage(template);// 短信模板
        smsSendInterfaceCheck.setBillId(phoneId);
        smsSendInterfaceCheck.setSmsCode(templateId);// 短信模板Id
        smsSendInterfaceCheck.setBlockId(blockId);// 批次号
        smsSendInterfaceCheck.setDoneCode(SmsSeqConfig.newDoneCode());// 新的doneCode
        smsSendInterfaceCheck.setSendDate(DateUtil.currentDate());// 发送时间
        smsSendInterfaceCheck.setPriorityLevel(0);// 优先级
        smsSendInterfaceCheck.setRequestReport(SmsHelper.getSendTpl(templateId));// 是否回执，0：不需要，1：需要
        smsSendInterfaceCheck.setSrcAddr("10086");// 服务号码
        smsSendInterfaceCheck.setDealDate(getCurrentDate());
        smsSendInterfaceCheck.setDoneDate(getCurrentDate());
        return smsSendInterfaceCheck;
    }

    private List<CaFreeres> getCaFreeres(Long userId)
    {
        
        CaFreeres res=new CaFreeres();
        res.getQuery().addCondition(CaFreeres.Field.resourceId, userId);
        List<CaFreeres> list=null;
        try
        {
            list= getSmsCmp().queryFreeResListForSms(FreeresDayNotifyHelper.getFreeresTableName(dayNotify),null,userId);
        }
        catch (Exception e)
        {
           imsLogger.error(e,e);
            e.printStackTrace();
        }
       return list;
        
//        String freeresTableName = FreeresDayNotifyHelper.getFreeresTableName(dayNotify);
//        FreeresBaseInfo baseInfo = new FreeresBaseInfo();
//        try
//        {
//            List<FreeresBaseInfo> freeResourceList = DBUtil.getDBClient()
//                    .createNativeQuery(buildSql(userId, acctId, freeresTableName), FreeresBaseInfo.class).getResultList();
//            if (freeResourceList.isEmpty())
//            {
//
//                baseInfo.setUnit(0L);
//                baseInfo.setRealUnit(0L);
//                return baseInfo;
//            }
//            if (freeResourceList.size() > 1)
//            {
//                imsLogger.info("返回的数据有多条，选择第一条!");
//            }
//            baseInfo = freeResourceList.get(0);
//        }
//        catch (Exception e)
//        {
//            imsLogger.error(e,e);
//        }
//        return baseInfo;
    }
//
//    private String buildSql(Long userId, Long acctId, String freeresTableName)
//    {
//        return new StringBuffer("select  t.RESOURCE_ID as resourceId,")
//                .append(" SUM(CASE measure_id     WHEN 101 THEN unit/1000   WHEN 106 THEN unit*60 WHEN 107 THEN unit*3600  else unit END) as unit,")
//                .append(" SUM(CASE measure_id     WHEN 101 THEN real_unit/1000   WHEN 106 THEN real_unit*60 WHEN 107 THEN real_unit*3600  else real_unit END) as realUnit ")
//                .append(" from " + freeresTableName + " t"). // 表名
//                append(CommonUtil.concat(" where t.RESOURCE_ID= '", userId, "' and t.acct_id='", acctId, "'"))// 条件
//                .append("  and measure_id in(101,102,106,107)").append(" GROUP BY t.RESOURCE_ID ")// 分组
//                .append(" order by t.RESOURCE_ID")// 排序
//                .toString();
//
//    }

    /**
     *
      尊敬的客户，截止至<6001>年<6002>月<6003>日<6004>时<6005>分，本月您<6006>.显示的已用免费套餐使用情况可能会小于您的实际套餐使用情况，请您谅解。中国移动 
     *例子:
     *尊敬的客户，截止至2012年12月25日15时14分，本月您已使用的移动数据流量为11.36M,还剩余的移动数据流量为488.64M;
     *已使用的智能网VPMN组内为6.00分钟,还剩余的智能网VPMN组内为1494.00分钟;
     *已使用的校园内指定区域的WLAN时长为632.00分钟,还剩余的校园内指定区域的WLAN时长为5368.00分钟;
     *已使用的短信条数为13条,还剩余的短信条数为47条;已使用的WLAN自动认证流量为94.71M,还剩余的WLAN自动认证流量为929.29M;
     *已使用的帐务VPMN群内为2.00分钟,还剩余的帐务VPMN群内为298.00分钟;已使用的WLAN为0分钟,还剩余的WLAN为9600.00分钟;
     *已使用的本地时长为35.00分钟,还剩余的本地时长为285.00分钟。
     *显示的已用免费套餐使用情况可能会小于您的实际套餐使用情况，请您谅解。中国移动 
     * @return
     */
    private String getMessage(List<CaFreeres>list)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", CommonUtil.int2String(FreeresDayNotifyHelper.getYear(dayNotify)));
        map.put("6002", CommonUtil.int2String(FreeresDayNotifyHelper.getMonth(dayNotify)));
        map.put("6003", CommonUtil.int2String(FreeresDayNotifyHelper.getDay(dayNotify)));
        map.put("6004", CommonUtil.int2String(FreeresDayNotifyHelper.getHour(dayNotify)));
        map.put("6005", CommonUtil.int2String(FreeresDayNotifyHelper.getMinute(dayNotify)));
        map.put("6006", getParamMessage(list));
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

    private static String getParamMessage(List<CaFreeres> list)
    {
        StringBuffer sb = new StringBuffer();
        String timeMsg = "已使用的<1001>为<1002>分钟,还剩余的<1001>为<1003>分钟;";
        String flowMsg = "已使用的<1001>为<1002>M,还剩余的<1001>为<1003>M;";
        String smsMsg = "已使用的<1001>为<1002>条,还剩余的<1001>为<1003>条;";
        for (CaFreeres res : list)
        {
            Map<String, String> map = new HashMap<String, String>();
            map.put("1001",removeMeasure( res.getItemName()));
            String currentMsg=smsMsg;
            if (SmsUtil.isTimeMeasure(res.getMeasureId()))
            {
                map.put("1002", SmsUtil.measureChangeToMinute(res.getMeasureId(), res.getUnit()));
                map.put("1003", SmsUtil.measureChangeToMinute(res.getMeasureId(), res.getUnit() - res.getRealUnit()));
                currentMsg=timeMsg;
            }
            else if (SmsUtil.isFlowMeasure(res.getMeasureId()))
            {
                map.put("1002", SmsUtil.measureChangeToM(res.getMeasureId(), res.getUnit()));
                map.put("1003", SmsUtil.measureChangeToM(res.getMeasureId(), res.getUnit() - res.getRealUnit()));
                currentMsg=flowMsg;
            }
            else
            {
                map.put("1002", String.valueOf(res.getUnit()));
                map.put("1003", String.valueOf(res.getUnit() - res.getRealUnit()));
            }
            sb.append(SmsUtil.instanceMessage(currentMsg, map));
        }
        return CommonUtilSh.removeLastFlag(sb.toString(),';');
    }
    /**
     * 去掉单位
     * 如 短信条数（次）--->短信条数
     * @param itemName
     * @return
     */
    private static String removeMeasure(String itemName){
        if(CommonUtil.isEmpty(itemName)){
            return itemName;
        }
        if(itemName.endsWith("）")){
            int index=itemName.lastIndexOf("（");
            itemName=itemName.substring(0,index==-1?itemName.length():index);
        }
        if(itemName.endsWith(")")){
            int index=itemName.lastIndexOf("(");
            itemName=itemName.substring(0,index==-1?itemName.length():index);
        }
        return itemName;
    }

    @Override
    protected void beforeDo(SmsParam param)
    {
        this.dayNotify = param.getDayNotify();
    }
/*  protected Date getMessageSendDate()
    {
        return getCurrentDate();
    }*/

    @Override
    protected Long getTemplateId()
    {
        return templateId;
    }

    @Override
    public void commitOther()
    {

    }
//    public static void main(String[] args)
//    {
//        List<CaFreeres> list=new ArrayList<CaFreeres>();
//        list.add(new CaFreeres(1L, 102, 60*60 , 60*30,"本地时长（秒）"));//时间
//        list.add(new CaFreeres(2L, 103, 1024*1024*100 , 1024*1024*10,"移动数据流量（byte）"));//流量
//        list.add(new CaFreeres(3L, 109, 10 , 5,"彩信条数（条）"));//条数
//        System.out.println(getParamMessage(list));
//    }

  
}
