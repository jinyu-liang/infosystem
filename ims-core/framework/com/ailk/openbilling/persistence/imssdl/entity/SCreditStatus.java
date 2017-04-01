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
@XmlType(propOrder={"workOrderType","phoneId","flag","oldStatus","oldSubType","newStatus","newSubType","creditLimit","tempCreditLimit","overUsage","triggerDate"})
@Sdl(module="MImsSyncDef")
public class SCreditStatus extends CsdlStructObject implements IComplexEntity{

	public final static String COL_WORK_ORDER_TYPE = "WORK_ORDER_TYPE";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_FLAG = "FLAG";
	public final static String COL_OLD_STATUS = "OLD_STATUS";
	public final static String COL_OLD_SUB_TYPE = "OLD_SUB_TYPE";
	public final static String COL_NEW_STATUS = "NEW_STATUS";
	public final static String COL_NEW_SUB_TYPE = "NEW_SUB_TYPE";
	public final static String COL_CREDIT_LIMIT = "CREDIT_LIMIT";
	public final static String COL_TEMP_CREDIT_LIMIT = "TEMP_CREDIT_LIMIT";
	public final static String COL_OVER_USAGE = "OVER_USAGE";
	public final static String COL_TRIGGER_DATE = "TRIGGER_DATE";
	public final static int IDX_WORK_ORDER_TYPE = 0;
	public final static int IDX_PHONE_ID = 1;
	public final static int IDX_FLAG = 2;
	public final static int IDX_OLD_STATUS = 3;
	public final static int IDX_OLD_SUB_TYPE = 4;
	public final static int IDX_NEW_STATUS = 5;
	public final static int IDX_NEW_SUB_TYPE = 6;
	public final static int IDX_CREDIT_LIMIT = 7;
	public final static int IDX_TEMP_CREDIT_LIMIT = 8;
	public final static int IDX_OVER_USAGE = 9;
	public final static int IDX_TRIGGER_DATE = 10;

	/**
	 * 
	 */
	@XmlElement(name="workOrderType")
	@Sdl
	private int workOrderType;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="flag")
	@Sdl
	private int flag;

	/**
	 * 
	 */
	@XmlElement(name="oldStatus")
	@Sdl
	private int oldStatus;

	/**
	 * 
	 */
	@XmlElement(name="oldSubType")
	@Sdl
	private int oldSubType;

	/**
	 * 
	 */
	@XmlElement(name="newStatus")
	@Sdl
	private int newStatus;

	/**
	 * 
	 */
	@XmlElement(name="newSubType")
	@Sdl
	private int newSubType;

	/**
	 * 
	 */
	@XmlElement(name="creditLimit")
	@Sdl
	private long creditLimit;

	/**
	 * 
	 */
	@XmlElement(name="tempCreditLimit")
	@Sdl
	private long tempCreditLimit;

	/**
	 * 
	 */
	@XmlElement(name="overUsage")
	@Sdl
	private long overUsage;

	/**
	 * 
	 */
	@XmlElement(name="triggerDate")
	@Sdl
	private Date triggerDate;

	public void setWorkOrderType(int obj){
		this.workOrderType = obj;
		onFieldSet(0, obj);
	}

	public int getWorkOrderType(){
		return workOrderType;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(1, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setFlag(int obj){
		this.flag = obj;
		onFieldSet(2, obj);
	}

	public int getFlag(){
		return flag;
	}

	public void setOldStatus(int obj){
		this.oldStatus = obj;
		onFieldSet(3, obj);
	}

	public int getOldStatus(){
		return oldStatus;
	}

	public void setOldSubType(int obj){
		this.oldSubType = obj;
		onFieldSet(4, obj);
	}

	public int getOldSubType(){
		return oldSubType;
	}

	public void setNewStatus(int obj){
		this.newStatus = obj;
		onFieldSet(5, obj);
	}

	public int getNewStatus(){
		return newStatus;
	}

	public void setNewSubType(int obj){
		this.newSubType = obj;
		onFieldSet(6, obj);
	}

	public int getNewSubType(){
		return newSubType;
	}

	public void setCreditLimit(long obj){
		this.creditLimit = obj;
		onFieldSet(7, obj);
	}

	public long getCreditLimit(){
		return creditLimit;
	}

	public void setTempCreditLimit(long obj){
		this.tempCreditLimit = obj;
		onFieldSet(8, obj);
	}

	public long getTempCreditLimit(){
		return tempCreditLimit;
	}

	public void setOverUsage(long obj){
		this.overUsage = obj;
		onFieldSet(9, obj);
	}

	public long getOverUsage(){
		return overUsage;
	}

	public void setTriggerDate(Date obj){
		this.triggerDate = obj;
		onFieldSet(10, obj);
	}

	public Date getTriggerDate(){
		return triggerDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCreditStatus(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCreditStatus(SCreditStatus arg0){
		copy(arg0);
	}

	public void copy(final SCreditStatus rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		workOrderType = rhs.workOrderType;
		phoneId = rhs.phoneId;
		flag = rhs.flag;
		oldStatus = rhs.oldStatus;
		oldSubType = rhs.oldSubType;
		newStatus = rhs.newStatus;
		newSubType = rhs.newSubType;
		creditLimit = rhs.creditLimit;
		tempCreditLimit = rhs.tempCreditLimit;
		overUsage = rhs.overUsage;
		triggerDate = rhs.triggerDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCreditStatus rhs=(SCreditStatus)rhs0;
		if(!ObjectUtils.equals(workOrderType, rhs.workOrderType)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(oldStatus, rhs.oldStatus)) return false;
		if(!ObjectUtils.equals(oldSubType, rhs.oldSubType)) return false;
		if(!ObjectUtils.equals(newStatus, rhs.newStatus)) return false;
		if(!ObjectUtils.equals(newSubType, rhs.newSubType)) return false;
		if(!ObjectUtils.equals(creditLimit, rhs.creditLimit)) return false;
		if(!ObjectUtils.equals(tempCreditLimit, rhs.tempCreditLimit)) return false;
		if(!ObjectUtils.equals(overUsage, rhs.overUsage)) return false;
		if(!ObjectUtils.equals(triggerDate, rhs.triggerDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(workOrderType)
		.append(phoneId)
		.append(flag)
		.append(oldStatus)
		.append(oldSubType)
		.append(newStatus)
		.append(newSubType)
		.append(creditLimit)
		.append(tempCreditLimit)
		.append(overUsage)
		.append(triggerDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SCreditStatus";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "WORK_ORDER_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "PHONE_ID", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "FLAG", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "OLD_STATUS", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "OLD_SUB_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "NEW_STATUS", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "NEW_SUB_TYPE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "CREDIT_LIMIT", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "TEMP_CREDIT_LIMIT", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "OVER_USAGE", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCreditStatus.class, "TRIGGER_DATE", 10, Date.class));
}

}