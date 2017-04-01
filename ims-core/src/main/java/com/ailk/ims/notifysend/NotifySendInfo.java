package com.ailk.ims.notifysend;


/**
 * @Description: 告警发送业务处理类，帐处通过调用告警发送sdl对象对应的java对象
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-19
  * @Date 2012-07-23 yangjh : 增加String emailSubject;
 */
public class NotifySendInfo {
	private long custId;
	private long acctId;
	private long resourceId;
	private long queueId;
	private int notificationId;
	private int actionId;
	private int notifyLang;
	private String notifyContent = new String();
	private String channelId = new String();
	private short channelType;
	private long sendTime;
	private String sender;
	private String emailSubject;
	private long sequenceId;
	
	
    public long getSequenceId()
    {
        return sequenceId;
    }
    public void setSequenceId(long sequenceId)
    {
        this.sequenceId = sequenceId;
    }
    public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
	}
	public long getAcctId() {
		return acctId;
	}
	public void setAcctId(long acctId) {
		this.acctId = acctId;
	}
	public long getResourceId() {
		return resourceId;
	}
	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}
	public long getQueueId() {
		return queueId;
	}
	public void setQueueId(long queueId) {
		this.queueId = queueId;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public int getNotifyLang() {
		return notifyLang;
	}
	public void setNotifyLang(int notifyLang) {
		this.notifyLang = notifyLang;
	}
	public String getNotifyContent() {
		return notifyContent;
	}
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public short getChannelType() {
		return channelType;
	}
	public void setChannelType(short channelType) {
		this.channelType = channelType;
	}
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
}
