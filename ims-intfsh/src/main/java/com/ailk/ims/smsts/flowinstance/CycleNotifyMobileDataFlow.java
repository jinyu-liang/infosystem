package com.ailk.ims.smsts.flowinstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 移动数据流量免费资源提醒(按日、周、或者月周期提醒)
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author zenglu
 * @Date 2012-07-31 busi_code=7508
 */
public class CycleNotifyMobileDataFlow extends BaseFlow
{
    private String freeResItem = EnumCodeShDefine.IM_SH_MOBLIE_DATA_FLOW;
    private String cumulateItem= EnumCodeShDefine.IM_SH_GPRS_CUMULANT_FLOW;
    private Long relTemplateId=EnumSmsDefine.IM_SH_CYCLE_NOTIFY_SELECT_AUTO_ADD_PACKAGE_CODD;
    private CaFreeresDayNotify dayNotify;
    private SmsParam smsParam;
    /**
     * 对应扫描表的数据库实体对象类 比如：扫描CmResource表，则返回CmResource.class<BR>
     * 默认扫描用户表,特殊业务可以覆盖
     */
    public Class<? extends DataObject> getScanTableClass()
    {
        return CoProd.class;
    }

    /**
     * 要扫描的表中的扫描字段<BR>
     * 默认为用户ID,特殊业务可以覆盖
     */
    public Field getKeyField()
    {
        return CoProd.Field.objectId;
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

    public DBCondition[] getQueryConds()
    {
        return new DBCondition[] {
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
                new DBCondition(CoProd.Field.productOfferingId, new Integer[] { EnumSmsDefine.SMS_NOTIFY_CYCLE_DATE_OFFERID,
                        EnumSmsDefine.SMS_NOTIFY_CYCLE_WEEK_OFFERID, EnumSmsDefine.SMS_NOTIFY_CYCLE_MONTH_OFFERID }, Operator.IN),
                new DBCondition(CoProd.Field.validDate, getCurrentDate(), Operator.LESS),
                new DBCondition(CoProd.Field.expireDate, getCurrentDate(), Operator.GREAT) };
    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        CoProd prod = (CoProd) obj;
        Long userId = prod.getObjectId();
        //2012-12-13 gaoqc5  如果是无效用户，直接返回
        if(isInValidateUser(userId)){
            return null;
        }
        imsLogger.info("用户[" + prod.getObjectId() + "],销售品ID:" + prod.getProductOfferingId());
        if (prod.getProductOfferingId() == EnumSmsDefine.SMS_NOTIFY_CYCLE_DATE_OFFERID)
        {
            return doSms(userId, prod, EnumCodeShDefine.ENUM_MOBILEFLOW_CYCLE_DATE, blockId, prod.getProductOfferingId());
        }
        else if (prod.getProductOfferingId() == EnumSmsDefine.SMS_NOTIFY_CYCLE_WEEK_OFFERID)
        {
            return doSms(userId, prod, EnumCodeShDefine.ENUM_MOBILEFLOW_CYCLE_WEEK, blockId, prod.getProductOfferingId());
        }
        else if (prod.getProductOfferingId() == EnumSmsDefine.SMS_NOTIFY_CYCLE_MONTH_OFFERID)
        {
            return doSms(userId, prod, EnumCodeShDefine.ENUM_MOBILEFLOW_CYCLE_MONTH, blockId, prod.getProductOfferingId());
        }
        else
        {
            imsLogger.error("未知的 销售品ID:" + prod.getProductOfferingId());
        }

        return null;

    }
    /**
     * 判断是否是无效用户
     * @param userId
     * @return
     */
    private boolean isInValidateUser(Long userId){
        return DBUtil.queryCount(CmResource.class, getUserQueryConds(userId)) ==0;
    }
    
    private DBCondition[] getUserQueryConds(Long userId)
    {
        DBCondition[] conds = new DBCondition[5];
        conds[0] = new DBCondition(CmResource.Field.activeDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[1] = new DBCondition(CmResource.Field.billingType, 1);
        conds[2] = new DBCondition(CmResource.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[3] = new DBCondition(CmResource.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[4] = new DBCondition(CmResource.Field.resourceId, userId);
        return conds;
    }
    

    private List<SmsSendInterfaceCheck> doSms(Long userId, CoProd prod, int cycleType, Long blockId, int prodOfferingId)
    {
        int acctFisrtDay = getAcctFirstBillDay(userId);
        Date orderProdFisrtDate = prod.getProdValidDate();
        int nowDay = DateUtil.getDateField(getCurrentDate(), Calendar.DAY_OF_MONTH);

        if (!needSendMessage(cycleType, acctFisrtDay, orderProdFisrtDate, nowDay))
        {
            return null;
        }
        IMSUtil.setUserRouterParam(userId);
        Long acctId = getSmsCmp().loadAcctIdByUserId(userId);
        String phoneId = getSmsCmp().loadPhoneIdByUserId(userId);
        if (null == acctId || null == phoneId)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_PHONEID_ERROR, "acctId", "phoneId");
        }
        imsLogger.info(CommonUtil.concat("用户[", userId, "],账户[", acctId, "],手机号码[", phoneId, "]"));
//        if (acctFisrtDay == nowDay || isOrderProdFisrtDate(orderProdFisrtDate))
//        {
//            imsLogger.info("今天是首帐日，查询历史免资源或者历史累积量");
//            return queryResources(acctFisrtDay, true, phoneId, userId, acctId, blockId);
//        }
        return queryResources(acctFisrtDay, false, phoneId, userId, acctId, blockId);
    }
    
//    private boolean isOrderProdFisrtDate(Date orderProdFisrtDate){
//        return DateUtil.dayBegin(getCurrentDate()).equals(DateUtil.dayBegin(orderProdFisrtDate));
//    }

    /**
     * 如果是按周期提醒，判断今天是否是周末 如果是 gao 2012-11-15
     * 
     * @param cyecleType
     * @param acctFisrtDay
     * @param orderProdFisrtDay
     * @param nowDay
     * @return
     */
    private boolean needSendMessage(int cycleType, int acctFisrtDay, Date orderProdFisrtDate, int nowDay)
    {
        imsLogger.info("首帐日:" + acctFisrtDay + ",产品首帐日:" + DateUtil.formatDate(orderProdFisrtDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD));
        boolean needMessage = true;
        switch (cycleType)
        {
        case EnumCodeShDefine.ENUM_MOBILEFLOW_CYCLE_WEEK:
            if ((nowDay - acctFisrtDay) % 7 != 0)
            {
                imsLogger.info("周提醒，但今天不是 帐期首日偏移7 天，不提醒");
                needMessage = false;
            }
            break;
        case EnumCodeShDefine.ENUM_MOBILEFLOW_CYCLE_MONTH:
            if (!isLastDayOfMonth())
            {
                imsLogger.info("月提醒,但今天不是月末最后一天，不提醒");
                needMessage = false;
            }
            break;
        default:
            break;
        }

        return needMessage;
    }

    /**
     * 查询免费资源或累积量 gao 2012-11-15
     * 
     * @return
     */
    private List<SmsSendInterfaceCheck> queryResources(int acctFisrtDay, boolean queryHis, String phoneId, Long userId,
            Long acctId, Long blockId )
    {
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();

        return smsList;

    }

    /**
     * 判断是否用完 gao 2012-11-19
     * 
     * @param total
     * @param used
     * @return
     */
    private boolean isFullUse(long total, long used)
    {
        return total > 0 && total == used;
    }
    /**
     * 周期提醒_开通自动叠加包:上海移动提醒服务：尊敬的客户，截至%日%时%分，您本账期移动数据套餐流量总共累计使用%，剩余%。本提醒免费，退订请回复QXLLTX。建议您回复305选择合适的移动数据流量套餐。
     * 周期提醒_未开通自动叠加包:上海移动提醒服务：尊敬的客户，截至%日%时%分，您本账期移动数据套餐流量总共累计使用%，剩余%。本提醒免费，退订请回复QXLLTX。可回复6611开通2元/5M无限次自动追加的流量叠加包 gao 2012-11-19
     * 判断用户是否有自动叠加包产品
     * @param prodOfferingId
     * @return
     */
    private Long getTemplateId(Long userId)
    {
        
        IMSUtil.setUserRouterParam(userId);
         int isAutoAddPackage=DBUtil.queryCount(CoProd.class, new DBCondition(CoProd.Field.objectId, userId),
                 new DBCondition(CoProd.Field.productOfferingId,EnumSmsDefine.SMS_NOTIFY_AUTO_ADD_OFFERID));
         if(isAutoAddPackage>0){
             relTemplateId= EnumSmsDefine.IM_SH_CYCLE_NOTIFY_SELECT_AUTO_ADD_PACKAGE_CODD;
         }else{
             relTemplateId= EnumSmsDefine.IM_SH_CYCLE_NOTIFY_N0_SELECT_AUTO_ADD_PACKAGE_CODD;
         }
         return relTemplateId;
    }

    
    /**
     * 实时账期 gao 2012-11-15
     * 
     * @return
     */
    private Date[] getCurrentBillDay(int accFistBillDay)
    {
        Date[] dates = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, accFistBillDay);
        dates[0] = DateUtil.dayBegin(cal.getTime());

        // cal.add(Calendar.MONTH, 1);
        dates[1] = getCurrentDate();// DateUtil.dayBegin(cal.getTime());

        return dates;

    }

//    /**
//     * 上个账期 gao 2012-11-15
//     * date[0],上个帐期第一天:    02-01
//     * date[1],上个帐期最后一天:  02-28
//     * @return
//     */
//    private  static  Date[] getLastBillDay()
//    {
//        Date[] dates = new Date[2];
//        Calendar calBegin =Calendar.getInstance();
//        Calendar calEnd =Calendar.getInstance();
//        
//        calBegin.set(Calendar.DAY_OF_MONTH, 1);
//        calBegin.add(Calendar.MONTH, -1);
//        dates[0] = DateUtil.dayBegin(calBegin.getTime());
//        
//        calEnd.set(Calendar.DAY_OF_MONTH, 1);
//        calEnd.add(Calendar.DAY_OF_MONTH, -1);
//        dates[1] = DateUtil.dayEnd(calEnd.getTime());
//        
//        return dates;
//    }
    
//    public static void main(String[] args)
//    {
//    	Date date = new Date();
//    	Calendar cal = Calendar.getInstance();
//    	cal.set(Calendar.DAY_OF_MONTH, 1);
//    	cal.add(Calendar.MONTH, -1);
//    	date = DateUtil.dayBegin(cal.getTime());
//    	
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        formatter.format(date);
//        System.out.println(formatter.format(date));
//    }
 

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

//    private Date getOrderProdFisrtDay(Date prodValidDate)
//    {
//        // 获取订购首日
//        Calendar calOrder = Calendar.getInstance();
//        // Date orderDate = getSmsCmp().queryProd(prodId).getProdValidDate();
//        calOrder.setTime(prodValidDate);
//        return calOrder.get(Calendar.DAY_OF_MONTH);
//    }

    // 获取账户的首帐日
    private int getAcctFirstBillDay(Long resourceId)
    {
        Long acctId = getSmsCmp().loadAcctIdByUserId(resourceId);
        imsLogger.info("getAcct[" + acctId + "]'s  FirstBillDate..... ");
        List<BillCycleComplex> complex = this.getSmsCmp().queryBillCycle(acctId);
        if (CommonUtil.isEmpty(complex))
        {
            imsLogger.info("************FirstBillDate is null");
            return 1;
        }
        BillCycleComplex billCycleComplex = complex.get(0);
        return billCycleComplex.getFirstBillDate().intValue();// 首账日期
    }

    /**
     * @description:判断是否月末最后一天
     * @author zenglu
     * @date 2012-09-04
     * @param
     */
    private boolean isLastDayOfMonth()
    {
        boolean flag = false;

        Calendar cal = Calendar.getInstance();
        int nowMonth = cal.get(Calendar.MONTH);
        cal.add(Calendar.DATE, 1);
        if (nowMonth + 1 == cal.get(Calendar.MONTH))
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 模板1：上海移动提醒服务：尊敬的客户，截至<6001>日<6002>时<6003>分，
     * 您本账期移动数据套餐流量总共累计使用<6004>M，剩余<6005>M。本提醒免费，
     * 退订请回复QXZQTX。建议您回复305选择合适的移动数据流量套餐。
     * ----------------------------------------
     * 模板2：上海移动提醒服务：尊敬的客户，截至<6001>日<6002>时<6003>分，
     * 您本账期移动数据套餐流量总共累计使用<6004>M，剩余<6005>M。本提醒免费，退订请回复QXZQTX。
     * 可回复6611开通2元/5M无限次自动追加的流量叠加包。
     * 
     * @return
     */
    private String getMessage(Long total, Long used, long addAmount, Long templateId)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", CommonUtil.int2String(FreeresDayNotifyHelper.getDay(dayNotify)));
        map.put("6002", CommonUtil.int2String(FreeresDayNotifyHelper.getHour(dayNotify)));
        map.put("6003", CommonUtil.int2String(FreeresDayNotifyHelper.getMinute(dayNotify)));
        
        //2013-07-19 liming15 如果免费资源已经用完，那么提醒的流量就用aps查询出来的量
    	if((0 == total) || (total <= used))
    	{
    		map.put("6004", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, addAmount));//使用多少
     		map.put("6005", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, 0));//剩余多少
    	}
    	else
    	{
    		map.put("6004", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, used));//使用多少
    		map.put("6005", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, total-used));//剩余多少
    	}
		
    	//end
    	
        // map.put("6004", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, used + addAmount));//使用多少
        // map.put("6005", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, total-used));//剩余多少
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

  @Override
protected void beforeDo(SmsParam param)
{
      this.dayNotify=param.getDayNotify();
      this.smsParam=param;
}
//    @Override
//    protected Date getMessageSendDate()
//    {
//
//        return getCurrentDate();
//    }

    @Override
    protected Long getTemplateId()
    {

        return relTemplateId;
    }

    @Override
    public void commitOther()
    {
        
    }
    
    @Override
    public Map<Long, Long> getTemplateAndBlockIdMap()
    {
        Map<Long ,Long> map=new HashMap<Long, Long>();
        map.put(EnumSmsDefine.IM_SH_CYCLE_NOTIFY_SELECT_AUTO_ADD_PACKAGE_CODD,  SmsSeqConfig.newBlockId());
        map.put( EnumSmsDefine.IM_SH_CYCLE_NOTIFY_N0_SELECT_AUTO_ADD_PACKAGE_CODD,  SmsSeqConfig.newBlockId());
        return map;
    }
}
