package com.ailk.ims.smsts.flowinstance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.common.wrapper.IntRange;
import jef.database.AutoCloseIterator;
import jef.database.query.Query;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.exception.IMSException;
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
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * wlan时长提醒,对于没有订购免费资源科目为:6000051,6000401 ,6000411 ,6000541 的免费资源,但是有科目为: 4508096,4508321,4528941,4527081,4528501的累积量,发送短信提醒
 * 
 * @author gaoqc
 */
public class WlanTimeFlow extends BaseFlow
{
    private long templateId = EnumCodeShDefine.IM_SH_WLAN_TIME_FLOW_CODE;
    private CaFreeresDayNotify dayNotify;
    private SmsParam param;
    private static final String tempTableName = "JD.IMS_WLAN_NOTICE";//需要发送短信的表名
    public DBCondition[] getQueryConds()
    {
        return new DBCondition[0];
    }

    @Override
    protected void beforeDo(SmsParam param)
    {
        this.dayNotify = param.getDayNotify();
       this.param=param;

    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        CaFreeres res = (CaFreeres) obj;
        String message = getMessage(res.getRealUnit());
        IMSUtil.setAcctRouterParam(res.getAcctId());
        String phoneId =getSmsCmp().loadPhoneIdByUserId(res.getResourceId());
        if(CommonUtil.isEmpty(phoneId)){
            imsLogger.info("用户[",res.getResourceId(),"]没对应的手机号码，不发送短信");
            return null;
        }
        List<SmsSendInterfaceCheck> list = new ArrayList<SmsSendInterfaceCheck>();
        list.add(createSmsSendInterfaceCheck(message, phoneId, templateId, blockId));
        return list;
    }
    protected AutoCloseIterator<?> getIterator(Query<?> query, IntRange range, String subTableName) throws IMSException,
            SQLException
    {
        // String freeresTableName = FreeresDayNotifyHelper.getFreeresTableName(dayNotify);
        return DBUtil.getDBClient().createNativeQuery(buildSql(), CaFreeres.class).getResultIterator();
    }

    /**
     * @param items
     * @param freeresTableName
     * @return
     */
    private String buildSql()
    {
        String  modVal=String.valueOf(dayNotify.getSubTableIndex());
        return new StringBuffer().append(" select t.user_id  as RESOURCE_ID,  ").append(" t.acct_id as ACCT_ID, ")
                .append(" t.acct_id as ACCT_ID, ").append(" t.accumulate as REAL_UNIT  ")
                .append("  from " + tempTableName + " t ")
                .append("where MOD(t.acct_id," +getThreadCount())
                .append(")=")
                .append(modVal)
                .append(buildKeyWordCondition())
                .append(" order by t.user_id ")
                .toString();

    }
    /**
     * 根据关键值判断上次是否有中断
     * @return
     */
    private String buildKeyWordCondition(){
        if(param.getKeywordVal()>0){
            return CommonUtil.concat(" and t.user_id >=",param.getKeywordVal()," ");
        }
        return "";
    }
    private int getThreadCount()
    {
        try
        {
            return SysUtil.getInt(EnumCodeShDefine.IMS_SH_WLAN_THREAD_COUNT);
        }
        catch (Exception e)
        {
            imsLogger.error("取线程个数异常,默认启动20个线程");
        }
        return 20;
    }

    /**
     * 尊敬的客户，截至<6001>日<6002>时<6003>分，您当月已使用的WLAN（CMCC）上网时长为<6004>小时<6005>分。 为节约您的上网费用，建议您发送KTWLANTC到10086选择合适的WLAN时长套餐。
     * 
     * @return
     */
    private String getMessage(long totalSecod)
    {
        int day = FreeresDayNotifyHelper.getDay(dayNotify);
        int hour = FreeresDayNotifyHelper.getHour(dayNotify);
        int minute = FreeresDayNotifyHelper.getMinute(dayNotify);
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", CommonUtil.int2String(day));
        map.put("6002", CommonUtil.int2String(hour));// CommonUtil.long2String(amount));
        map.put("6003", CommonUtil.int2String(minute));// CommonUtil.long2String(amount));
        map.put("6004", CommonUtil.long2String(totalSecod / 3600));// CommonUtil.long2String(amount));
        map.put("6005", CommonUtil.long2String((totalSecod % 3600) / 60));// CommonUtil.long2String(amount));
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
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

    @Override
    protected Long getTemplateId()
    {
        return templateId;
    }

    @Override
    public void commitOther()
    {

    }

}
