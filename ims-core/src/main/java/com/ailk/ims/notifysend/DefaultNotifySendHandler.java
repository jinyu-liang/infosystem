package com.ailk.ims.notifysend;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.PsMail;
import com.ailk.openbilling.persistence.cust.entity.SmsInfo;

/**
 * @Description: 提供一个默认的实现,在bss broker提供具体实现之前可以避免抛异常
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-12-5 2012-02-16 yangjh 将信息存储到开通提供的短信发送表里
 * @Date 2012-06-12 tangkun 增加sender
 * @Date 2012-07-19 yangjh : story：50270 增加email的告警发送
 * @Date 2012-08-14 yangjh : sms_info 和ps_mail增加notifyLang字段
 * @Date 2012-08-16 yangjh : 增加提醒唯一标识
 */
public class DefaultNotifySendHandler implements INotifySend
{
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public void sendSms(NotifySendInfo sendInfo, IMSContext context)
    {

        UserQuery cmp = context.getComponent(UserQuery.class);
        SmsInfo sms = new SmsInfo();
        sms.setId(DBUtil.getSequence(SmsInfo.class));
        //@Date 2012-08-16 yangjh : 增加提醒唯一标识
        sms.setSequenceId(sendInfo.getSequenceId());
        // 上游系统流水，用于查询结果
        sms.setDoneCode(context.getDoneCode());
        // 短信标识，比如计费短信（JFDX）       2012-06-12 tangkun 增加sender
        sms.setFlag(sendInfo.getSender());
//        CmResIdentity identity = cmp.queryResIdentityByUserId(sendInfo.getResourceId());
//      if (identity != null)
//      {
//          sms.setMsisdn(identity.getIdentity());
//      }      
        try
        {
        	// 手机号码
        	sms.setMsisdn(context.get3hTree().loadUser3hBean(sendInfo.getResourceId()).getPhoneId());
        }
        catch(IMS3hNotFoundException e)
        {
        	imsLogger.error(e,e);
        }
        if(CommonUtil.isValid(sendInfo.getNotifyLang())){
            sms.setNotifyLang(sendInfo.getNotifyLang());
        }
        // 消息内容
        sms.setMsg(sendInfo.getNotifyContent());
        cmp.insert(sms);

        imsLogger.info("****** save success******");

    }

    public void sendEmail(NotifySendInfo sendInfo, IMSContext context)
    {
        // CmResource cmResource = context.getComponent(UserQuery.class)
        // .queryUserByUserID(sendInfo.getResourceId());
        // String email = cmResource.getEmail();
    	//@Date 2012-07-19 yangjh : story：50270 增加email的告警发送
    	PsMail mail = new PsMail();
    	mail.setId(CommonUtil.long2Int(DBUtil.getSequence(PsMail.class)));
    	//@Date 2012-08-16 yangjh : 增加提醒唯一标识
    	mail.setSequenceId(sendInfo.getSequenceId());
    	mail.setReceiveAccounts(CommonUtil.long2String(sendInfo.getAcctId()));
    	if(CommonUtil.isValid(sendInfo.getNotifyContent())){
    	    mail.setContent(sendInfo.getNotifyContent());
    	}
    	if(CommonUtil.isValid(sendInfo.getNotifyLang())){
    	    mail.setNotifyLang(sendInfo.getNotifyLang());
        }
    	if(CommonUtil.isValid(sendInfo.getEmailSubject())){
    	    mail.setSubject(sendInfo.getEmailSubject());
    	}else{
            imsLogger.info("*******no default configuration******");
            mail.setSubject("notification");
    	}
    	
        context.getComponent(BaseComponent.class).insert(mail);
        imsLogger.info("****** save eamil success******");
    }

}
