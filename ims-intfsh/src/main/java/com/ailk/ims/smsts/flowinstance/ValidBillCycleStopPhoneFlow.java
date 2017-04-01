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
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.FlowInstanceHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.jd.entity.ImsStsSyncStore;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * 有效期停机,即日保号停 短信提醒
 * 
 * @date 2012-09-20
 * @author gaoqc5 busi_code=7509
 */
public class ValidBillCycleStopPhoneFlow extends BaseFlow
{
    private static final Long templateId = EnumSmsDefine.IM_SH_VALID_BILL_CYCLE_STOP_PHONE_CODE;

    private List<ImsStsSyncStore> stores=new ArrayList<ImsStsSyncStore>();
    @Override
    public OrderCondition[] getOrderCond()
    {
        OrderCondition orderCondition = new OrderCondition(true, CmResValidity.Field.resourceId);
        return new OrderCondition[] { orderCondition };
    }

    @Override
    public Field getKeyField()
    {
        return CmResValidity.Field.resourceId;
    }

    @Override
    public Class<? extends DataObject> getScanTableClass()
    {
        return CmResValidity.class;
    }

    public DBCondition[] getQueryConds()
    {
        // Date[] dates=getValidateRange();
        return new DBCondition[] { new DBCondition(CmResValidity.Field.effectFlag, 1),// 取消了用户有效期的用户
                new DBCondition(CmResValidity.Field.expireDate, getCurrentDate(), Operator.GREAT),
                new DBCondition(CmResValidity.Field.validDate, getBeforeMonthDate(), Operator.LESS) };
        // new DBCondition(CmResValidity.Field.validDate, dates[1], Operator.LESS_EQUALS) };
    }

    private static Date getBeforeMonthDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -1);
        return DateUtil.dayBegin(calendar.getTime());
    }

    // private Date[] getValidateRange(){
    // Date[] date=new Date[2];
    // Calendar calendar=Calendar.getInstance();
    // calendar.set(Calendar.DAY_OF_MONTH, 1);
    // calendar.add(Calendar.MONTH, -2);
    // date[0]=DateUtil.formatHms2Zero(calendar.getTime());
    // calendar.add(Calendar.MONTH, 2);
    // date[1]=DateUtil.formatHms2Zero(calendar.getTime());
    // return date;
    // }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        CmResValidity cmResValidity = (CmResValidity) obj;

        Long resourceId = cmResValidity.getResourceId();
        IMSUtil.setUserRouterParam(resourceId);
        Long acctId = getSmsCmp().loadAcctIdByUserId(resourceId);
        //2012-12-21 gaoqc5 如果用户找不到对应的账户,说明这个用户已经失效,不用发送短信.
        if(null==acctId){
            imsLogger.info("用户[",resourceId,"无法找到所属账号");
            return null;
        }
        //2012-11-17 gaoqc5 增加免停判断:用户有免停标记的不生成短信
        if(FlowInstanceHelper.isExemption(acctId)){
            imsLogger.info("账户["+acctId+"]是免停,不做日保号停");
            return null;
        }
        //2012-11-19 gaoqc5  用户如果设置了“随心聊”功能的，不发短信
        if(FlowInstanceHelper.isSXL(resourceId)){
            imsLogger.info("用户["+resourceId+"]属于随心聊群,不做日保号停");
            return null;
        }
        Integer billDay = getFisrtBillDay(acctId);
        Integer today = DateUtil.getDateField(getCurrentDate(), Calendar.DAY_OF_MONTH);// 当前日期
        imsLogger.info("**********首帐日期  :", billDay);
        boolean todayBeforeBillday = true;
        int lastMont = -2;
        if (today > billDay)
        {
            todayBeforeBillday = false;
            lastMont = -1;
        }

        Calendar lastCal = FlowInstanceHelper.getFormatHms2ZeroCalendar(0, billDay);
        lastCal.set(Calendar.MONTH, lastCal.get(Calendar.MONTH) + lastMont);
        if (FlowInstanceHelper.isNotDayKeepNumUser(resourceId)// 非日保号停
                && FlowInstanceHelper.isNotActiveRecInLatelyDate(resourceId, acctId,billDay, todayBeforeBillday) // 前两个账期内无激活记录
                && FlowInstanceHelper.isCostZreoInRangeDate(resourceId, acctId, lastCal.getTime(), getCurrentDate()// 前两个账期无消费记录
                        ))
        {
            Calendar stopCal = getMessageSendDate(billDay, todayBeforeBillday);// 当天发短信
            // Calendar sendCal=getSendMessageDate(stopCal.getTime());
            Calendar sendCal = getSendMessageDate(new Date());

            stores.add(addDateToImsStsSyncStore(cmResValidity, acctId, stopCal.getTime()));
            // 日保号停
            // DBUtil.insert(createImsResStsSync(phoneId, acctId, resourceId,
            // cmResValidity.getSoNbr()));

            // 短信明细表
            List<SmsSendInterfaceCheck> list = new ArrayList<SmsSendInterfaceCheck>();
            String phoneId = getSmsCmp().loadPhoneIdByUserId(resourceId);
            if(null==phoneId){
                imsLogger.debug("用户[",resourceId,"]没有手机号码，不发送短信");
            }
            String message = getMessage(stopCal);
            if (CommonUtil.isEmpty(message))
            {

                imsLogger.info("************************* 短信模板内容为空,不发送信息!");
            }
            else
            {
                list.add(createSmsSendInterfaceCheck(message, phoneId, getSmsCode(), blockId, sendCal));
            }
            return list;
        }

        return null;
    }
    

    


    /**
     * 获取首帐日
     * 
     * @param acctId
     * @return
     */
    private int getFisrtBillDay(Long acctId)
    {
        List<BillCycleComplex> complex = this.getSmsCmp().queryBillCycle(acctId);
        if (CommonUtil.isEmpty(complex))
        {
            return 1;
        }
        BillCycleComplex billCycleComplex = complex.get(0);
        return billCycleComplex.getFirstBillDate();// 首账日期
    }

    /**
     * 获取短信发送的时间
     * 
     * @param date
     * @return
     */
    private Calendar getSendMessageDate(Date date)
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(getMessageSendDate());
        // cal.add(Calendar.DAY_OF_MONTH, -7);
        return cal;
    }

    /**
     * 返回信息内容 尊敬的客户：您的号码在<5004>月<5005>日前若仍未发生任何通信消费行为，则将于<5006>月<5007>日被日保号停，
     * 日保号停期间号码将被锁定无法使用，并扣除0.16元/天的日保号费。日保号停期间，您可通过10086、营业厅、网上营业厅申请激活号码。
     * 
     * @return
     */
    private String getMessage(Calendar stopCal)
    {
        // Calendar cureetCal=Calendar.getInstance();
        // cureetCal.setTime(getCurrentDate());
        Map<String, String> map = new HashMap<String, String>();
        map.put("5004", String.valueOf(getMonth(stopCal) + 1));
        map.put("5005", String.valueOf(getDay(stopCal)));
        map.put("5006", String.valueOf(getMonth(stopCal) + 1));
        map.put("5007", String.valueOf(getDay(stopCal)));

//        return SmsUtil.instanceSmsMessage(templateId, map);
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);

    }

    private int getMonth(Calendar cal)
    {
        return cal.get(Calendar.MONTH);
    }

    private int getDay(Calendar cal)
    {
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 短信编号
     */
    private Long getSmsCode()
    {
        // return Long.valueOf(SysUtil.getString(templateId));
        return templateId;
    }

    /**
     * 创建一个信息明细对象
     */
    private SmsSendInterfaceCheck createSmsSendInterfaceCheck(String message, String phoneId, Long smsCode, Long blockId,
            Calendar sendCal)
    {
        // 返回短信审核明细表
        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setMessage(message);
        smsSendInterfaceCheck.setBillId(phoneId);
        smsSendInterfaceCheck.setSmsCode(smsCode);
        smsSendInterfaceCheck.setDealDate(getCurrentDate());
        smsSendInterfaceCheck.setDoneDate(getCurrentDate());
        smsSendInterfaceCheck.setBlockId(blockId);// 批次号
        smsSendInterfaceCheck.setDoneCode(SmsSeqConfig.newDoneCode());// 新的doneCode
        smsSendInterfaceCheck.setSendDate(sendCal.getTime());// 下一个账期开始日- 7天
        smsSendInterfaceCheck.setRequestReport(SmsHelper.getSendTpl(smsCode));// 是否回执，0：不需要，1：需要
        
        return smsSendInterfaceCheck;
    }

    /**
     * 短信发送时间：下一个账期开始日- 7天，即：如果当前日期(如 9.20)大于账期日期(如 10)，发送时间是10.(20-7)=10.13; 如果当前日期(如9.20)小于账期日期(如25)，则发送时间是
     * 9.(25-7)=9.18,但是比当前日期还小，所以应取 9.20发送
     */
    private Calendar getMessageSendDate(int billDay, boolean todayBeforeBillday)
    {
        int month = DateUtil.getDateField(getCurrentDate(), Calendar.MONTH);
        if (todayBeforeBillday)// 如果当前日期小于账期日期
        {
            int today = DateUtil.getDateField(getCurrentDate(), Calendar.DAY_OF_MONTH);
            billDay -= 7;
            if (billDay - today <= 7)
            {
                billDay = today;
            }
        }
        else
        {
            month += 1;
        }
        return FlowInstanceHelper.getFormatHms2ZeroCalendar(month, billDay);

    }

    /**
     * 日保号停数据通过反向表通知crm-新建中间表
     * 
     * @param cmResValidity
     * @param createDate
     */
    private ImsStsSyncStore addDateToImsStsSyncStore(CmResValidity cmResValidity, Long acctId, Date createDate)
    {
        imsLogger.debug("begin to insert IMS_STS_SYNC_STORE");
        int nofityFlag = 8;// 日保号停状态变更通知
        ImsStsSyncStore store = new ImsStsSyncStore();
        String phoneId = getSmsCmp().loadPhoneIdByUserId(cmResValidity.getResourceId());
        store.setPhoneId(phoneId);
        store.setAcctId(acctId);
        store.setNotifyFlag(nofityFlag);
        store.setCreateDate(createDate);
        store.setDealDate(getCurrentDate());
        store.setResourceId(cmResValidity.getResourceId());
        store.setSts(1);
        store.setOpId(9l);
        store.setOrgId(0);
        store.setSoNbr(BusiRecordUtil.getReceiveDoneCode(createDate));
        store.setNsubType(0);
        store.setPsubType(0);
        return store;
    }

//    @Override
    protected Date getMessageSendDate()
    {
        return getCurrentDate();
    }

    @Override
    protected Long getTemplateId()
    {
        return templateId;
    }

    @Override
    public void commitOther()
    {
        if (CommonUtil.isNotEmpty(stores))
        {
            DBUtil.insertBatch(stores);
            stores.clear();
        }
    }

}
