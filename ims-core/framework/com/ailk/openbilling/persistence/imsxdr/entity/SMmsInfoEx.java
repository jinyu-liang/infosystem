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
@XmlType(propOrder={"receiveTime","receiveAddress","sendAddress","setTime","contentType","mmClass","infoLevel","reportType","addressHide","safeLevel","contentSw","statType","transmitDn","transmitType","storeTime","receiveMmscId","mobileAddress"})
@Sdl(module="MXdr")
public class SMmsInfoEx extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RECEIVE_TIME = "RECEIVE_TIME";
	public final static String COL_RECEIVE_ADDRESS = "RECEIVE_ADDRESS";
	public final static String COL_SEND_ADDRESS = "SEND_ADDRESS";
	public final static String COL_SET_TIME = "SET_TIME";
	public final static String COL_CONTENT_TYPE = "CONTENT_TYPE";
	public final static String COL_MM_CLASS = "MM_CLASS";
	public final static String COL_INFO_LEVEL = "INFO_LEVEL";
	public final static String COL_REPORT_TYPE = "REPORT_TYPE";
	public final static String COL_ADDRESS_HIDE = "ADDRESS_HIDE";
	public final static String COL_SAFE_LEVEL = "SAFE_LEVEL";
	public final static String COL_CONTENT_SW = "CONTENT_SW";
	public final static String COL_STAT_TYPE = "STAT_TYPE";
	public final static String COL_TRANSMIT_DN = "TRANSMIT_DN";
	public final static String COL_TRANSMIT_TYPE = "TRANSMIT_TYPE";
	public final static String COL_STORE_TIME = "STORE_TIME";
	public final static String COL_RECEIVE_MMSC_ID = "RECEIVE_MMSC_ID";
	public final static String COL_MOBILE_ADDRESS = "MOBILE_ADDRESS";
	public final static int IDX_RECEIVE_TIME = 0;
	public final static int IDX_RECEIVE_ADDRESS = 1;
	public final static int IDX_SEND_ADDRESS = 2;
	public final static int IDX_SET_TIME = 3;
	public final static int IDX_CONTENT_TYPE = 4;
	public final static int IDX_MM_CLASS = 5;
	public final static int IDX_INFO_LEVEL = 6;
	public final static int IDX_REPORT_TYPE = 7;
	public final static int IDX_ADDRESS_HIDE = 8;
	public final static int IDX_SAFE_LEVEL = 9;
	public final static int IDX_CONTENT_SW = 10;
	public final static int IDX_STAT_TYPE = 11;
	public final static int IDX_TRANSMIT_DN = 12;
	public final static int IDX_TRANSMIT_TYPE = 13;
	public final static int IDX_STORE_TIME = 14;
	public final static int IDX_RECEIVE_MMSC_ID = 15;
	public final static int IDX_MOBILE_ADDRESS = 16;

	/**
	 * 
	 */
	@XmlElement(name="receiveTime")
	@Sdl
	private long receiveTime;

	/**
	 * 
	 */
	@XmlElement(name="receiveAddress")
	@Sdl
	private String receiveAddress;

	/**
	 * 
	 */
	@XmlElement(name="sendAddress")
	@Sdl
	private String sendAddress;

	/**
	 * 
	 */
	@XmlElement(name="setTime")
	@Sdl
	private long setTime;

	/**
	 * 
	 */
	@XmlElement(name="contentType")
	@Sdl
	private String contentType;

	/**
	 * 
	 */
	@XmlElement(name="mmClass")
	@Sdl
	private short mmClass;

	/**
	 * 
	 */
	@XmlElement(name="infoLevel")
	@Sdl
	private short infoLevel;

	/**
	 * 
	 */
	@XmlElement(name="reportType")
	@Sdl
	private short reportType;

	/**
	 * 
	 */
	@XmlElement(name="addressHide")
	@Sdl
	private short addressHide;

	/**
	 * 
	 */
	@XmlElement(name="safeLevel")
	@Sdl
	private short safeLevel;

	/**
	 * 
	 */
	@XmlElement(name="contentSw")
	@Sdl
	private short contentSw;

	/**
	 * 
	 */
	@XmlElement(name="statType")
	@Sdl
	private short statType;

	/**
	 * 
	 */
	@XmlElement(name="transmitDn")
	@Sdl
	private String transmitDn;

	/**
	 * 
	 */
	@XmlElement(name="transmitType")
	@Sdl
	private short transmitType;

	/**
	 * 
	 */
	@XmlElement(name="storeTime")
	@Sdl
	private long storeTime;

	/**
	 * 
	 */
	@XmlElement(name="receiveMmscId")
	@Sdl
	private String receiveMmscId;

	/**
	 * 
	 */
	@XmlElement(name="mobileAddress")
	@Sdl
	private String mobileAddress;

	public void setReceiveTime(long obj){
		this.receiveTime = obj;
		onFieldSet(0, obj);
	}

	public long getReceiveTime(){
		return receiveTime;
	}

	public void setReceiveAddress(String obj){
		this.receiveAddress = obj;
		onFieldSet(1, obj);
	}

	public String getReceiveAddress(){
		return receiveAddress;
	}

	public void setSendAddress(String obj){
		this.sendAddress = obj;
		onFieldSet(2, obj);
	}

	public String getSendAddress(){
		return sendAddress;
	}

	public void setSetTime(long obj){
		this.setTime = obj;
		onFieldSet(3, obj);
	}

	public long getSetTime(){
		return setTime;
	}

	public void setContentType(String obj){
		this.contentType = obj;
		onFieldSet(4, obj);
	}

	public String getContentType(){
		return contentType;
	}

	public void setMmClass(short obj){
		this.mmClass = obj;
		onFieldSet(5, obj);
	}

	public short getMmClass(){
		return mmClass;
	}

	public void setInfoLevel(short obj){
		this.infoLevel = obj;
		onFieldSet(6, obj);
	}

	public short getInfoLevel(){
		return infoLevel;
	}

	public void setReportType(short obj){
		this.reportType = obj;
		onFieldSet(7, obj);
	}

	public short getReportType(){
		return reportType;
	}

	public void setAddressHide(short obj){
		this.addressHide = obj;
		onFieldSet(8, obj);
	}

	public short getAddressHide(){
		return addressHide;
	}

	public void setSafeLevel(short obj){
		this.safeLevel = obj;
		onFieldSet(9, obj);
	}

	public short getSafeLevel(){
		return safeLevel;
	}

	public void setContentSw(short obj){
		this.contentSw = obj;
		onFieldSet(10, obj);
	}

	public short getContentSw(){
		return contentSw;
	}

	public void setStatType(short obj){
		this.statType = obj;
		onFieldSet(11, obj);
	}

	public short getStatType(){
		return statType;
	}

	public void setTransmitDn(String obj){
		this.transmitDn = obj;
		onFieldSet(12, obj);
	}

	public String getTransmitDn(){
		return transmitDn;
	}

	public void setTransmitType(short obj){
		this.transmitType = obj;
		onFieldSet(13, obj);
	}

	public short getTransmitType(){
		return transmitType;
	}

	public void setStoreTime(long obj){
		this.storeTime = obj;
		onFieldSet(14, obj);
	}

	public long getStoreTime(){
		return storeTime;
	}

	public void setReceiveMmscId(String obj){
		this.receiveMmscId = obj;
		onFieldSet(15, obj);
	}

	public String getReceiveMmscId(){
		return receiveMmscId;
	}

	public void setMobileAddress(String obj){
		this.mobileAddress = obj;
		onFieldSet(16, obj);
	}

	public String getMobileAddress(){
		return mobileAddress;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SMmsInfoEx(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 17; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SMmsInfoEx(SMmsInfoEx arg0){
		copy(arg0);
	}

	public void copy(final SMmsInfoEx rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		receiveTime = rhs.receiveTime;
		receiveAddress = rhs.receiveAddress;
		sendAddress = rhs.sendAddress;
		setTime = rhs.setTime;
		contentType = rhs.contentType;
		mmClass = rhs.mmClass;
		infoLevel = rhs.infoLevel;
		reportType = rhs.reportType;
		addressHide = rhs.addressHide;
		safeLevel = rhs.safeLevel;
		contentSw = rhs.contentSw;
		statType = rhs.statType;
		transmitDn = rhs.transmitDn;
		transmitType = rhs.transmitType;
		storeTime = rhs.storeTime;
		receiveMmscId = rhs.receiveMmscId;
		mobileAddress = rhs.mobileAddress;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMmsInfoEx rhs=(SMmsInfoEx)rhs0;
		if(!ObjectUtils.equals(receiveTime, rhs.receiveTime)) return false;
		if(!ObjectUtils.equals(receiveAddress, rhs.receiveAddress)) return false;
		if(!ObjectUtils.equals(sendAddress, rhs.sendAddress)) return false;
		if(!ObjectUtils.equals(setTime, rhs.setTime)) return false;
		if(!ObjectUtils.equals(contentType, rhs.contentType)) return false;
		if(!ObjectUtils.equals(mmClass, rhs.mmClass)) return false;
		if(!ObjectUtils.equals(infoLevel, rhs.infoLevel)) return false;
		if(!ObjectUtils.equals(reportType, rhs.reportType)) return false;
		if(!ObjectUtils.equals(addressHide, rhs.addressHide)) return false;
		if(!ObjectUtils.equals(safeLevel, rhs.safeLevel)) return false;
		if(!ObjectUtils.equals(contentSw, rhs.contentSw)) return false;
		if(!ObjectUtils.equals(statType, rhs.statType)) return false;
		if(!ObjectUtils.equals(transmitDn, rhs.transmitDn)) return false;
		if(!ObjectUtils.equals(transmitType, rhs.transmitType)) return false;
		if(!ObjectUtils.equals(storeTime, rhs.storeTime)) return false;
		if(!ObjectUtils.equals(receiveMmscId, rhs.receiveMmscId)) return false;
		if(!ObjectUtils.equals(mobileAddress, rhs.mobileAddress)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(receiveTime)
		.append(receiveAddress)
		.append(sendAddress)
		.append(setTime)
		.append(contentType)
		.append(mmClass)
		.append(infoLevel)
		.append(reportType)
		.append(addressHide)
		.append(safeLevel)
		.append(contentSw)
		.append(statType)
		.append(transmitDn)
		.append(transmitType)
		.append(storeTime)
		.append(receiveMmscId)
		.append(mobileAddress)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(17);
public static final long BITS_ALL_MARKER = 0x10000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SMmsInfoEx";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "RECEIVE_TIME", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "RECEIVE_ADDRESS", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "SEND_ADDRESS", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "SET_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "CONTENT_TYPE", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "MM_CLASS", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "INFO_LEVEL", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "REPORT_TYPE", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "ADDRESS_HIDE", 8, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "SAFE_LEVEL", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "CONTENT_SW", 10, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "STAT_TYPE", 11, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "TRANSMIT_DN", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "TRANSMIT_TYPE", 13, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "STORE_TIME", 14, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "RECEIVE_MMSC_ID", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfoEx.class, "MOBILE_ADDRESS", 16, String.class));
}

}