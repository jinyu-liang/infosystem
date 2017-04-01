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
@XmlType(propOrder={"userId","phoneId","activationTime","deviceType","smsLang","ivrLang","ussdLang","notifyFlag","location","balance"})
@Sdl(module="MXdr")
public class SFirstAct extends CsdlStructObject implements IComplexEntity{

	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_ACTIVATION_TIME = "ACTIVATION_TIME";
	public final static String COL_DEVICE_TYPE = "DEVICE_TYPE";
	public final static String COL_SMS_LANG = "SMS_LANG";
	public final static String COL_IVR_LANG = "IVR_LANG";
	public final static String COL_USSD_LANG = "USSD_LANG";
	public final static String COL_NOTIFY_FLAG = "NOTIFY_FLAG";
	public final static String COL_LOCATION = "LOCATION";
	public final static String COL_BALANCE = "BALANCE";
	public final static int IDX_USER_ID = 0;
	public final static int IDX_PHONE_ID = 1;
	public final static int IDX_ACTIVATION_TIME = 2;
	public final static int IDX_DEVICE_TYPE = 3;
	public final static int IDX_SMS_LANG = 4;
	public final static int IDX_IVR_LANG = 5;
	public final static int IDX_USSD_LANG = 6;
	public final static int IDX_NOTIFY_FLAG = 7;
	public final static int IDX_LOCATION = 8;
	public final static int IDX_BALANCE = 9;

	/**
	 * 
	 */
	@XmlElement(name="balance")
	@Sdl
	private SBalance balance;

	/**
	 * 
	 */
	@XmlElement(name="userId")
	@Sdl
	private long userId;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="activationTime")
	@Sdl
	private long activationTime;

	/**
	 * 
	 */
	@XmlElement(name="deviceType")
	@Sdl
	private short deviceType;

	/**
	 * 
	 */
	@XmlElement(name="smsLang")
	@Sdl
	private short smsLang;

	/**
	 * 
	 */
	@XmlElement(name="ivrLang")
	@Sdl
	private short ivrLang;

	/**
	 * 
	 */
	@XmlElement(name="ussdLang")
	@Sdl
	private short ussdLang;

	/**
	 * 
	 */
	@XmlElement(name="notifyFlag")
	@Sdl
	private short notifyFlag;

	/**
	 * 
	 */
	@XmlElement(name="location")
	@Sdl
	private String location;

	public void setBalance(SBalance obj){
		this.balance = obj;
		onFieldSet(9, obj);
	}

	public SBalance getBalance(){
		return balance;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(0, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(1, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setActivationTime(long obj){
		this.activationTime = obj;
		onFieldSet(2, obj);
	}

	public long getActivationTime(){
		return activationTime;
	}

	public void setDeviceType(short obj){
		this.deviceType = obj;
		onFieldSet(3, obj);
	}

	public short getDeviceType(){
		return deviceType;
	}

	public void setSmsLang(short obj){
		this.smsLang = obj;
		onFieldSet(4, obj);
	}

	public short getSmsLang(){
		return smsLang;
	}

	public void setIvrLang(short obj){
		this.ivrLang = obj;
		onFieldSet(5, obj);
	}

	public short getIvrLang(){
		return ivrLang;
	}

	public void setUssdLang(short obj){
		this.ussdLang = obj;
		onFieldSet(6, obj);
	}

	public short getUssdLang(){
		return ussdLang;
	}

	public void setNotifyFlag(short obj){
		this.notifyFlag = obj;
		onFieldSet(7, obj);
	}

	public short getNotifyFlag(){
		return notifyFlag;
	}

	public void setLocation(String obj){
		this.location = obj;
		onFieldSet(8, obj);
	}

	public String getLocation(){
		return location;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFirstAct(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 10; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFirstAct(SFirstAct arg0){
		copy(arg0);
	}

	public void copy(final SFirstAct rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		activationTime = rhs.activationTime;
		deviceType = rhs.deviceType;
		smsLang = rhs.smsLang;
		ivrLang = rhs.ivrLang;
		ussdLang = rhs.ussdLang;
		notifyFlag = rhs.notifyFlag;
		location = rhs.location;
		balance = rhs.balance;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFirstAct rhs=(SFirstAct)rhs0;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(activationTime, rhs.activationTime)) return false;
		if(!ObjectUtils.equals(deviceType, rhs.deviceType)) return false;
		if(!ObjectUtils.equals(smsLang, rhs.smsLang)) return false;
		if(!ObjectUtils.equals(ivrLang, rhs.ivrLang)) return false;
		if(!ObjectUtils.equals(ussdLang, rhs.ussdLang)) return false;
		if(!ObjectUtils.equals(notifyFlag, rhs.notifyFlag)) return false;
		if(!ObjectUtils.equals(location, rhs.location)) return false;
		if(!ObjectUtils.equals(balance, rhs.balance)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userId)
		.append(phoneId)
		.append(activationTime)
		.append(deviceType)
		.append(smsLang)
		.append(ivrLang)
		.append(ussdLang)
		.append(notifyFlag)
		.append(location)
		.append(balance)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(10);
public static final long BITS_ALL_MARKER = 0x200L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFirstAct";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "USER_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "PHONE_ID", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "ACTIVATION_TIME", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "DEVICE_TYPE", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "SMS_LANG", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "IVR_LANG", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "USSD_LANG", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "NOTIFY_FLAG", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "LOCATION", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstAct.class, "BALANCE", 9, SBalance.class));
}

}