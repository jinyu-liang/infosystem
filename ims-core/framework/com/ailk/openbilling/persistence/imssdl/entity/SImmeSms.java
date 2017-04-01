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
import java.util.Date;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"blockId","smsNoticeId","doneCode","smsCode","inModeCode","smsNetTag","chanId","recvObjectType","recvObject","recvId","noticeContent","smsTypeCode","smsKindCode","forceObject","forceStartTime","forceEndTime","smsPriority","referTime","remark","month","day"})
@Sdl(module="MImsSyncDef")
public class SImmeSms extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BLOCK_ID = "BLOCK_ID";
	public final static String COL_SMS_NOTICE_ID = "SMS_NOTICE_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_SMS_CODE = "SMS_CODE";
	public final static String COL_IN_MODE_CODE = "IN_MODE_CODE";
	public final static String COL_SMS_NET_TAG = "SMS_NET_TAG";
	public final static String COL_CHAN_ID = "CHAN_ID";
	public final static String COL_RECV_OBJECT_TYPE = "RECV_OBJECT_TYPE";
	public final static String COL_RECV_OBJECT = "RECV_OBJECT";
	public final static String COL_RECV_ID = "RECV_ID";
	public final static String COL_NOTICE_CONTENT = "NOTICE_CONTENT";
	public final static String COL_SMS_TYPE_CODE = "SMS_TYPE_CODE";
	public final static String COL_SMS_KIND_CODE = "SMS_KIND_CODE";
	public final static String COL_FORCE_OBJECT = "FORCE_OBJECT";
	public final static String COL_FORCE_START_TIME = "FORCE_START_TIME";
	public final static String COL_FORCE_END_TIME = "FORCE_END_TIME";
	public final static String COL_SMS_PRIORITY = "SMS_PRIORITY";
	public final static String COL_REFER_TIME = "REFER_TIME";
	public final static String COL_REMARK = "REMARK";
	public final static String COL_MONTH = "MONTH";
	public final static String COL_DAY = "DAY";
	public final static int IDX_BLOCK_ID = 0;
	public final static int IDX_SMS_NOTICE_ID = 1;
	public final static int IDX_DONE_CODE = 2;
	public final static int IDX_SMS_CODE = 3;
	public final static int IDX_IN_MODE_CODE = 4;
	public final static int IDX_SMS_NET_TAG = 5;
	public final static int IDX_CHAN_ID = 6;
	public final static int IDX_RECV_OBJECT_TYPE = 7;
	public final static int IDX_RECV_OBJECT = 8;
	public final static int IDX_RECV_ID = 9;
	public final static int IDX_NOTICE_CONTENT = 10;
	public final static int IDX_SMS_TYPE_CODE = 11;
	public final static int IDX_SMS_KIND_CODE = 12;
	public final static int IDX_FORCE_OBJECT = 13;
	public final static int IDX_FORCE_START_TIME = 14;
	public final static int IDX_FORCE_END_TIME = 15;
	public final static int IDX_SMS_PRIORITY = 16;
	public final static int IDX_REFER_TIME = 17;
	public final static int IDX_REMARK = 18;
	public final static int IDX_MONTH = 19;
	public final static int IDX_DAY = 20;

	/**
	 * 
	 */
	@XmlElement(name="blockId")
	@Sdl
	private long blockId;

	/**
	 * 
	 */
	@XmlElement(name="smsNoticeId")
	@Sdl
	private long smsNoticeId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="smsCode")
	@Sdl
	private long smsCode;

	/**
	 * 
	 */
	@XmlElement(name="inModeCode")
	@Sdl
	private String inModeCode;

	/**
	 * 
	 */
	@XmlElement(name="smsNetTag")
	@Sdl
	private String smsNetTag;

	/**
	 * 
	 */
	@XmlElement(name="chanId")
	@Sdl
	private String chanId;

	/**
	 * 
	 */
	@XmlElement(name="recvObjectType")
	@Sdl
	private String recvObjectType;

	/**
	 * 
	 */
	@XmlElement(name="recvObject")
	@Sdl
	private String recvObject;

	/**
	 * 
	 */
	@XmlElement(name="recvId")
	@Sdl
	private long recvId;

	/**
	 * 
	 */
	@XmlElement(name="noticeContent")
	@Sdl
	private String noticeContent;

	/**
	 * 
	 */
	@XmlElement(name="smsTypeCode")
	@Sdl
	private String smsTypeCode;

	/**
	 * 
	 */
	@XmlElement(name="smsKindCode")
	@Sdl
	private String smsKindCode;

	/**
	 * 
	 */
	@XmlElement(name="forceObject")
	@Sdl
	private String forceObject;

	/**
	 * 
	 */
	@XmlElement(name="forceStartTime")
	@Sdl
	private Date forceStartTime;

	/**
	 * 
	 */
	@XmlElement(name="forceEndTime")
	@Sdl
	private Date forceEndTime;

	/**
	 * 
	 */
	@XmlElement(name="smsPriority")
	@Sdl
	private int smsPriority;

	/**
	 * 
	 */
	@XmlElement(name="referTime")
	@Sdl
	private Date referTime;

	/**
	 * 
	 */
	@XmlElement(name="remark")
	@Sdl
	private String remark;

	/**
	 * 
	 */
	@XmlElement(name="month")
	@Sdl
	private short month;

	/**
	 * 
	 */
	@XmlElement(name="day")
	@Sdl
	private short day;

	public void setBlockId(long obj){
		this.blockId = obj;
		onFieldSet(0, obj);
	}

	public long getBlockId(){
		return blockId;
	}

	public void setSmsNoticeId(long obj){
		this.smsNoticeId = obj;
		onFieldSet(1, obj);
	}

	public long getSmsNoticeId(){
		return smsNoticeId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(2, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setSmsCode(long obj){
		this.smsCode = obj;
		onFieldSet(3, obj);
	}

	public long getSmsCode(){
		return smsCode;
	}

	public void setInModeCode(String obj){
		this.inModeCode = obj;
		onFieldSet(4, obj);
	}

	public String getInModeCode(){
		return inModeCode;
	}

	public void setSmsNetTag(String obj){
		this.smsNetTag = obj;
		onFieldSet(5, obj);
	}

	public String getSmsNetTag(){
		return smsNetTag;
	}

	public void setChanId(String obj){
		this.chanId = obj;
		onFieldSet(6, obj);
	}

	public String getChanId(){
		return chanId;
	}

	public void setRecvObjectType(String obj){
		this.recvObjectType = obj;
		onFieldSet(7, obj);
	}

	public String getRecvObjectType(){
		return recvObjectType;
	}

	public void setRecvObject(String obj){
		this.recvObject = obj;
		onFieldSet(8, obj);
	}

	public String getRecvObject(){
		return recvObject;
	}

	public void setRecvId(long obj){
		this.recvId = obj;
		onFieldSet(9, obj);
	}

	public long getRecvId(){
		return recvId;
	}

	public void setNoticeContent(String obj){
		this.noticeContent = obj;
		onFieldSet(10, obj);
	}

	public String getNoticeContent(){
		return noticeContent;
	}

	public void setSmsTypeCode(String obj){
		this.smsTypeCode = obj;
		onFieldSet(11, obj);
	}

	public String getSmsTypeCode(){
		return smsTypeCode;
	}

	public void setSmsKindCode(String obj){
		this.smsKindCode = obj;
		onFieldSet(12, obj);
	}

	public String getSmsKindCode(){
		return smsKindCode;
	}

	public void setForceObject(String obj){
		this.forceObject = obj;
		onFieldSet(13, obj);
	}

	public String getForceObject(){
		return forceObject;
	}

	public void setForceStartTime(Date obj){
		this.forceStartTime = obj;
		onFieldSet(14, obj);
	}

	public Date getForceStartTime(){
		return forceStartTime;
	}

	public void setForceEndTime(Date obj){
		this.forceEndTime = obj;
		onFieldSet(15, obj);
	}

	public Date getForceEndTime(){
		return forceEndTime;
	}

	public void setSmsPriority(int obj){
		this.smsPriority = obj;
		onFieldSet(16, obj);
	}

	public int getSmsPriority(){
		return smsPriority;
	}

	public void setReferTime(Date obj){
		this.referTime = obj;
		onFieldSet(17, obj);
	}

	public Date getReferTime(){
		return referTime;
	}

	public void setRemark(String obj){
		this.remark = obj;
		onFieldSet(18, obj);
	}

	public String getRemark(){
		return remark;
	}

	public void setMonth(short obj){
		this.month = obj;
		onFieldSet(19, obj);
	}

	public short getMonth(){
		return month;
	}

	public void setDay(short obj){
		this.day = obj;
		onFieldSet(20, obj);
	}

	public short getDay(){
		return day;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SImmeSms(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 21; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SImmeSms(SImmeSms arg0){
		copy(arg0);
	}

	public void copy(final SImmeSms rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		blockId = rhs.blockId;
		smsNoticeId = rhs.smsNoticeId;
		doneCode = rhs.doneCode;
		smsCode = rhs.smsCode;
		inModeCode = rhs.inModeCode;
		smsNetTag = rhs.smsNetTag;
		chanId = rhs.chanId;
		recvObjectType = rhs.recvObjectType;
		recvObject = rhs.recvObject;
		recvId = rhs.recvId;
		noticeContent = rhs.noticeContent;
		smsTypeCode = rhs.smsTypeCode;
		smsKindCode = rhs.smsKindCode;
		forceObject = rhs.forceObject;
		forceStartTime = rhs.forceStartTime;
		forceEndTime = rhs.forceEndTime;
		smsPriority = rhs.smsPriority;
		referTime = rhs.referTime;
		remark = rhs.remark;
		month = rhs.month;
		day = rhs.day;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SImmeSms rhs=(SImmeSms)rhs0;
		if(!ObjectUtils.equals(blockId, rhs.blockId)) return false;
		if(!ObjectUtils.equals(smsNoticeId, rhs.smsNoticeId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(smsCode, rhs.smsCode)) return false;
		if(!ObjectUtils.equals(inModeCode, rhs.inModeCode)) return false;
		if(!ObjectUtils.equals(smsNetTag, rhs.smsNetTag)) return false;
		if(!ObjectUtils.equals(chanId, rhs.chanId)) return false;
		if(!ObjectUtils.equals(recvObjectType, rhs.recvObjectType)) return false;
		if(!ObjectUtils.equals(recvObject, rhs.recvObject)) return false;
		if(!ObjectUtils.equals(recvId, rhs.recvId)) return false;
		if(!ObjectUtils.equals(noticeContent, rhs.noticeContent)) return false;
		if(!ObjectUtils.equals(smsTypeCode, rhs.smsTypeCode)) return false;
		if(!ObjectUtils.equals(smsKindCode, rhs.smsKindCode)) return false;
		if(!ObjectUtils.equals(forceObject, rhs.forceObject)) return false;
		if(!ObjectUtils.equals(forceStartTime, rhs.forceStartTime)) return false;
		if(!ObjectUtils.equals(forceEndTime, rhs.forceEndTime)) return false;
		if(!ObjectUtils.equals(smsPriority, rhs.smsPriority)) return false;
		if(!ObjectUtils.equals(referTime, rhs.referTime)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(month, rhs.month)) return false;
		if(!ObjectUtils.equals(day, rhs.day)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(blockId)
		.append(smsNoticeId)
		.append(doneCode)
		.append(smsCode)
		.append(inModeCode)
		.append(smsNetTag)
		.append(chanId)
		.append(recvObjectType)
		.append(recvObject)
		.append(recvId)
		.append(noticeContent)
		.append(smsTypeCode)
		.append(smsKindCode)
		.append(forceObject)
		.append(forceStartTime)
		.append(forceEndTime)
		.append(smsPriority)
		.append(referTime)
		.append(remark)
		.append(month)
		.append(day)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(21);
public static final long BITS_ALL_MARKER = 0x100000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SImmeSms";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "BLOCK_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "SMS_NOTICE_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "DONE_CODE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "SMS_CODE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "IN_MODE_CODE", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "SMS_NET_TAG", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "CHAN_ID", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "RECV_OBJECT_TYPE", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "RECV_OBJECT", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "RECV_ID", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "NOTICE_CONTENT", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "SMS_TYPE_CODE", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "SMS_KIND_CODE", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "FORCE_OBJECT", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "FORCE_START_TIME", 14, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "FORCE_END_TIME", 15, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "SMS_PRIORITY", 16, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "REFER_TIME", 17, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "REMARK", 18, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "MONTH", 19, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SImmeSms.class, "DAY", 20, short.class));
}

}