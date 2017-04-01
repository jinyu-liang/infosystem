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
@XmlType(propOrder={"custId","acctId","userId","phoneId","leavelType","productSequenceId","usedUpTime"})
@Sdl(module="MImsSyncDef")
public class SOneTimeProm extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_LEAVEL_TYPE = "LEAVEL_TYPE";
	public final static String COL_PRODUCT_SEQUENCE_ID = "PRODUCT_SEQUENCE_ID";
	public final static String COL_USED_UP_TIME = "USED_UP_TIME";
	public final static int IDX_CUST_ID = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_USER_ID = 2;
	public final static int IDX_PHONE_ID = 3;
	public final static int IDX_LEAVEL_TYPE = 4;
	public final static int IDX_PRODUCT_SEQUENCE_ID = 5;
	public final static int IDX_USED_UP_TIME = 6;

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
	@XmlElement(name="leavelType")
	@Sdl
	private short leavelType;

	/**
	 * 
	 */
	@XmlElement(name="productSequenceId")
	@Sdl
	private long productSequenceId;

	/**
	 * 
	 */
	@XmlElement(name="usedUpTime")
	@Sdl
	private long usedUpTime;

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

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(2, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(3, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setLeavelType(short obj){
		this.leavelType = obj;
		onFieldSet(4, obj);
	}

	public short getLeavelType(){
		return leavelType;
	}

	public void setProductSequenceId(long obj){
		this.productSequenceId = obj;
		onFieldSet(5, obj);
	}

	public long getProductSequenceId(){
		return productSequenceId;
	}

	public void setUsedUpTime(long obj){
		this.usedUpTime = obj;
		onFieldSet(6, obj);
	}

	public long getUsedUpTime(){
		return usedUpTime;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOneTimeProm(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOneTimeProm(SOneTimeProm arg0){
		copy(arg0);
	}

	public void copy(final SOneTimeProm rhs){
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
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		leavelType = rhs.leavelType;
		productSequenceId = rhs.productSequenceId;
		usedUpTime = rhs.usedUpTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOneTimeProm rhs=(SOneTimeProm)rhs0;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(leavelType, rhs.leavelType)) return false;
		if(!ObjectUtils.equals(productSequenceId, rhs.productSequenceId)) return false;
		if(!ObjectUtils.equals(usedUpTime, rhs.usedUpTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custId)
		.append(acctId)
		.append(userId)
		.append(phoneId)
		.append(leavelType)
		.append(productSequenceId)
		.append(usedUpTime)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SOneTimeProm";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "CUST_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "USER_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "PHONE_ID", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "LEAVEL_TYPE", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "PRODUCT_SEQUENCE_ID", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOneTimeProm.class, "USED_UP_TIME", 6, long.class));
}

}