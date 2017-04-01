package com.ailk.openbilling.persistence.imsxdr.entity;

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
@XmlType(propOrder={"attendedNumber","bookedNumber","scaling","contentType","eventType","dateStamp","channelType","sharingType","sharingName","oriDlNumber","sourceType","applicationType"})
@Sdl(module="MXdr")
public class SIsmpInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ATTENDED_NUMBER = "ATTENDED_NUMBER";
	public final static String COL_BOOKED_NUMBER = "BOOKED_NUMBER";
	public final static String COL_SCALING = "SCALING";
	public final static String COL_CONTENT_TYPE = "CONTENT_TYPE";
	public final static String COL_EVENT_TYPE = "EVENT_TYPE";
	public final static String COL_DATE_STAMP = "DATE_STAMP";
	public final static String COL_CHANNEL_TYPE = "CHANNEL_TYPE";
	public final static String COL_SHARING_TYPE = "SHARING_TYPE";
	public final static String COL_SHARING_NAME = "SHARING_NAME";
	public final static String COL_ORI_DL_NUMBER = "ORI_DL_NUMBER";
	public final static String COL_SOURCE_TYPE = "SOURCE_TYPE";
	public final static String COL_APPLICATION_TYPE = "APPLICATION_TYPE";
	public final static int IDX_ATTENDED_NUMBER = 0;
	public final static int IDX_BOOKED_NUMBER = 1;
	public final static int IDX_SCALING = 2;
	public final static int IDX_CONTENT_TYPE = 3;
	public final static int IDX_EVENT_TYPE = 4;
	public final static int IDX_DATE_STAMP = 5;
	public final static int IDX_CHANNEL_TYPE = 6;
	public final static int IDX_SHARING_TYPE = 7;
	public final static int IDX_SHARING_NAME = 8;
	public final static int IDX_ORI_DL_NUMBER = 9;
	public final static int IDX_SOURCE_TYPE = 10;
	public final static int IDX_APPLICATION_TYPE = 11;

	/**
	 * 
	 */
	@XmlElement(name="attendedNumber")
	@Sdl
	private int attendedNumber;

	/**
	 * 
	 */
	@XmlElement(name="bookedNumber")
	@Sdl
	private int bookedNumber;

	/**
	 * 
	 */
	@XmlElement(name="scaling")
	@Sdl
	private int scaling;

	/**
	 * 
	 */
	@XmlElement(name="contentType")
	@Sdl
	private int contentType;

	/**
	 * 
	 */
	@XmlElement(name="eventType")
	@Sdl
	private String eventType;

	/**
	 * 
	 */
	@XmlElement(name="dateStamp")
	@Sdl
	private long dateStamp;

	/**
	 * 
	 */
	@XmlElement(name="channelType")
	@Sdl
	private int channelType;

	/**
	 * 
	 */
	@XmlElement(name="sharingType")
	@Sdl
	private int sharingType;

	/**
	 * 
	 */
	@XmlElement(name="sharingName")
	@Sdl
	private String sharingName;

	/**
	 * 
	 */
	@XmlElement(name="oriDlNumber")
	@Sdl
	private String oriDlNumber;

	/**
	 * 
	 */
	@XmlElement(name="sourceType")
	@Sdl
	private int sourceType;

	/**
	 * 
	 */
	@XmlElement(name="applicationType")
	@Sdl
	private int applicationType;

	public void setAttendedNumber(int obj){
		this.attendedNumber = obj;
		onFieldSet(0, obj);
	}

	public int getAttendedNumber(){
		return attendedNumber;
	}

	public void setBookedNumber(int obj){
		this.bookedNumber = obj;
		onFieldSet(1, obj);
	}

	public int getBookedNumber(){
		return bookedNumber;
	}

	public void setScaling(int obj){
		this.scaling = obj;
		onFieldSet(2, obj);
	}

	public int getScaling(){
		return scaling;
	}

	public void setContentType(int obj){
		this.contentType = obj;
		onFieldSet(3, obj);
	}

	public int getContentType(){
		return contentType;
	}

	public void setEventType(String obj){
		this.eventType = obj;
		onFieldSet(4, obj);
	}

	public String getEventType(){
		return eventType;
	}

	public void setDateStamp(long obj){
		this.dateStamp = obj;
		onFieldSet(5, obj);
	}

	public long getDateStamp(){
		return dateStamp;
	}

	public void setChannelType(int obj){
		this.channelType = obj;
		onFieldSet(6, obj);
	}

	public int getChannelType(){
		return channelType;
	}

	public void setSharingType(int obj){
		this.sharingType = obj;
		onFieldSet(7, obj);
	}

	public int getSharingType(){
		return sharingType;
	}

	public void setSharingName(String obj){
		this.sharingName = obj;
		onFieldSet(8, obj);
	}

	public String getSharingName(){
		return sharingName;
	}

	public void setOriDlNumber(String obj){
		this.oriDlNumber = obj;
		onFieldSet(9, obj);
	}

	public String getOriDlNumber(){
		return oriDlNumber;
	}

	public void setSourceType(int obj){
		this.sourceType = obj;
		onFieldSet(10, obj);
	}

	public int getSourceType(){
		return sourceType;
	}

	public void setApplicationType(int obj){
		this.applicationType = obj;
		onFieldSet(11, obj);
	}

	public int getApplicationType(){
		return applicationType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SIsmpInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 12; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SIsmpInfo(SIsmpInfo arg0){
		copy(arg0);
	}

	public void copy(final SIsmpInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		attendedNumber = rhs.attendedNumber;
		bookedNumber = rhs.bookedNumber;
		scaling = rhs.scaling;
		contentType = rhs.contentType;
		eventType = rhs.eventType;
		dateStamp = rhs.dateStamp;
		channelType = rhs.channelType;
		sharingType = rhs.sharingType;
		sharingName = rhs.sharingName;
		oriDlNumber = rhs.oriDlNumber;
		sourceType = rhs.sourceType;
		applicationType = rhs.applicationType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SIsmpInfo rhs=(SIsmpInfo)rhs0;
		if(!ObjectUtils.equals(attendedNumber, rhs.attendedNumber)) return false;
		if(!ObjectUtils.equals(bookedNumber, rhs.bookedNumber)) return false;
		if(!ObjectUtils.equals(scaling, rhs.scaling)) return false;
		if(!ObjectUtils.equals(contentType, rhs.contentType)) return false;
		if(!ObjectUtils.equals(eventType, rhs.eventType)) return false;
		if(!ObjectUtils.equals(dateStamp, rhs.dateStamp)) return false;
		if(!ObjectUtils.equals(channelType, rhs.channelType)) return false;
		if(!ObjectUtils.equals(sharingType, rhs.sharingType)) return false;
		if(!ObjectUtils.equals(sharingName, rhs.sharingName)) return false;
		if(!ObjectUtils.equals(oriDlNumber, rhs.oriDlNumber)) return false;
		if(!ObjectUtils.equals(sourceType, rhs.sourceType)) return false;
		if(!ObjectUtils.equals(applicationType, rhs.applicationType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(attendedNumber)
		.append(bookedNumber)
		.append(scaling)
		.append(contentType)
		.append(eventType)
		.append(dateStamp)
		.append(channelType)
		.append(sharingType)
		.append(sharingName)
		.append(oriDlNumber)
		.append(sourceType)
		.append(applicationType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(12);
public static final long BITS_ALL_MARKER = 0x800L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SIsmpInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "ATTENDED_NUMBER", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "BOOKED_NUMBER", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "SCALING", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "CONTENT_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "EVENT_TYPE", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "DATE_STAMP", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "CHANNEL_TYPE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "SHARING_TYPE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "SHARING_NAME", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "ORI_DL_NUMBER", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "SOURCE_TYPE", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpInfo.class, "APPLICATION_TYPE", 11, int.class));
}

}