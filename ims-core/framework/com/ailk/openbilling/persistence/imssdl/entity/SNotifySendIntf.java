package com.ailk.openbilling.persistence.imssdl.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import com.ailk.easyframe.web.common.annotation.Sdl;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
import com.ailk.easyframe.sdl.sdlbuffer.MemberTypeInfo;
import javax.xml.bind.annotation.XmlTransient;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"custId","acctId","resourceId","queueId","notificationId","actionId","notifyLang","notifyContent","channelId","channelType","sendTime","sender","emailSubject","sequenceId"})
@Sdl(module="MImsSyncDef")
public class SNotifySendIntf extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_QUEUE_ID = "QUEUE_ID";
	public final static String COL_NOTIFICATION_ID = "NOTIFICATION_ID";
	public final static String COL_ACTION_ID = "ACTION_ID";
	public final static String COL_NOTIFY_LANG = "NOTIFY_LANG";
	public final static String COL_NOTIFY_CONTENT = "NOTIFY_CONTENT";
	public final static String COL_CHANNEL_ID = "CHANNEL_ID";
	public final static String COL_CHANNEL_TYPE = "CHANNEL_TYPE";
	public final static String COL_SEND_TIME = "SEND_TIME";
	public final static String COL_SENDER = "SENDER";
	public final static String COL_EMAIL_SUBJECT = "EMAIL_SUBJECT";
	public final static String COL_SEQUENCE_ID = "SEQUENCE_ID";
	public final static int IDX_CUST_ID = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_RESOURCE_ID = 2;
	public final static int IDX_QUEUE_ID = 3;
	public final static int IDX_NOTIFICATION_ID = 4;
	public final static int IDX_ACTION_ID = 5;
	public final static int IDX_NOTIFY_LANG = 6;
	public final static int IDX_NOTIFY_CONTENT = 7;
	public final static int IDX_CHANNEL_ID = 8;
	public final static int IDX_CHANNEL_TYPE = 9;
	public final static int IDX_SEND_TIME = 10;
	public final static int IDX_SENDER = 11;
	public final static int IDX_EMAIL_SUBJECT = 12;
	public final static int IDX_SEQUENCE_ID = 13;

	/**
	 * 
	 */
	@XmlElement(name="custId")
	@Sdl
	private long custId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="queueId")
	@Sdl
	private long queueId;

	/**
	 * 
	 */
	@XmlElement(name="notificationId")
	@Sdl
	private int notificationId;

	/**
	 * 
	 */
	@XmlElement(name="actionId")
	@Sdl
	private int actionId;

	/**
	 * 
	 */
	@XmlElement(name="notifyLang")
	@Sdl
	private int notifyLang;

	/**
	 * 
	 */
	@XmlElement(name="notifyContent")
	@Sdl
	private String notifyContent;

	/**
	 * 
	 */
	@XmlElement(name="channelId")
	@Sdl
	private String channelId;

	/**
	 * 
	 */
	@XmlElement(name="channelType")
	@Sdl
	private short channelType;

	/**
	 * 
	 */
	@XmlElement(name="sendTime")
	@Sdl
	private long sendTime;

	/**
	 * 
	 */
	@XmlElement(name="sender")
	@Sdl
	private String sender;

	/**
	 * 
	 */
	@XmlElement(name="emailSubject")
	@Sdl
	private String emailSubject;

	/**
	 * 
	 */
	@XmlElement(name="sequenceId")
	@Sdl
	private long sequenceId;

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(0, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(1, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(2, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setQueueId(long obj){
		this.queueId = obj;
		onFieldSet(3, obj);
	}

	public long getQueueId(){
		return queueId;
	}

	public void setNotificationId(int obj){
		this.notificationId = obj;
		onFieldSet(4, obj);
	}

	public int getNotificationId(){
		return notificationId;
	}

	public void setActionId(int obj){
		this.actionId = obj;
		onFieldSet(5, obj);
	}

	public int getActionId(){
		return actionId;
	}

	public void setNotifyLang(int obj){
		this.notifyLang = obj;
		onFieldSet(6, obj);
	}

	public int getNotifyLang(){
		return notifyLang;
	}

	public void setNotifyContent(String obj){
		this.notifyContent = obj;
		onFieldSet(7, obj);
	}

	public String getNotifyContent(){
		return notifyContent;
	}

	public void setChannelId(String obj){
		this.channelId = obj;
		onFieldSet(8, obj);
	}

	public String getChannelId(){
		return channelId;
	}

	public void setChannelType(short obj){
		this.channelType = obj;
		onFieldSet(9, obj);
	}

	public short getChannelType(){
		return channelType;
	}

	public void setSendTime(long obj){
		this.sendTime = obj;
		onFieldSet(10, obj);
	}

	public long getSendTime(){
		return sendTime;
	}

	public void setSender(String obj){
		this.sender = obj;
		onFieldSet(11, obj);
	}

	public String getSender(){
		return sender;
	}

	public void setEmailSubject(String obj){
		this.emailSubject = obj;
		onFieldSet(12, obj);
	}

	public String getEmailSubject(){
		return emailSubject;
	}

	public void setSequenceId(long obj){
		this.sequenceId = obj;
		onFieldSet(13, obj);
	}

	public long getSequenceId(){
		return sequenceId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SNotifySendIntf(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 14; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SNotifySendIntf(SNotifySendIntf arg0){
		copy(arg0);
	}

	public void copy(final SNotifySendIntf rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		custId = rhs.custId;
		acctId = rhs.acctId;
		resourceId = rhs.resourceId;
		queueId = rhs.queueId;
		notificationId = rhs.notificationId;
		actionId = rhs.actionId;
		notifyLang = rhs.notifyLang;
		notifyContent = rhs.notifyContent;
		channelId = rhs.channelId;
		channelType = rhs.channelType;
		sendTime = rhs.sendTime;
		sender = rhs.sender;
		emailSubject = rhs.emailSubject;
		sequenceId = rhs.sequenceId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SNotifySendIntf rhs=(SNotifySendIntf)rhs0;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(queueId, rhs.queueId)) return false;
		if(!ObjectUtils.equals(notificationId, rhs.notificationId)) return false;
		if(!ObjectUtils.equals(actionId, rhs.actionId)) return false;
		if(!ObjectUtils.equals(notifyLang, rhs.notifyLang)) return false;
		if(!ObjectUtils.equals(notifyContent, rhs.notifyContent)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(channelType, rhs.channelType)) return false;
		if(!ObjectUtils.equals(sendTime, rhs.sendTime)) return false;
		if(!ObjectUtils.equals(sender, rhs.sender)) return false;
		if(!ObjectUtils.equals(emailSubject, rhs.emailSubject)) return false;
		if(!ObjectUtils.equals(sequenceId, rhs.sequenceId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custId)
		.append(acctId)
		.append(resourceId)
		.append(queueId)
		.append(notificationId)
		.append(actionId)
		.append(notifyLang)
		.append(notifyContent)
		.append(channelId)
		.append(channelType)
		.append(sendTime)
		.append(sender)
		.append(emailSubject)
		.append(sequenceId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(14);
public static final long BITS_ALL_MARKER = 0x2000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SNotifySendIntf";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "CUST_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "RESOURCE_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "QUEUE_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "NOTIFICATION_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "ACTION_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "NOTIFY_LANG", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "NOTIFY_CONTENT", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "CHANNEL_ID", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "CHANNEL_TYPE", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "SEND_TIME", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "SENDER", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "EMAIL_SUBJECT", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SNotifySendIntf.class, "SEQUENCE_ID", 13, long.class));
}

}