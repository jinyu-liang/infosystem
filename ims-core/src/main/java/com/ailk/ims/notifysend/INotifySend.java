package com.ailk.ims.notifysend;

import com.ailk.ims.common.IMSContext;


/**
 * @Description: 告警发送业务处理类，发送实现类继承这个接口
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-19
 */
public interface INotifySend {
	public void sendSms(NotifySendInfo sendInfo,IMSContext context);
	public void sendEmail(NotifySendInfo sendInfo,IMSContext context);
}

