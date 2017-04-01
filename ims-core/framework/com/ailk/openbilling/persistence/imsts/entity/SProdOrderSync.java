package com.ailk.openbilling.persistence.imsts.entity;

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
@XmlType(propOrder={"id","associateId","custId","acctId","userId","phoneId","reasonCode","soNbr","createDate","sts"})
@Sdl(module="MImsTsDef")
public class SProdOrderSync extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ID = "ID";
	public final static String COL_ASSOCIATE_ID = "ASSOCIATE_ID";
	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_REASON_CODE = "REASON_CODE";
	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_STS = "STS";
	public final static int IDX_ID = 0;
	public final static int IDX_ASSOCIATE_ID = 1;
	public final static int IDX_CUST_ID = 2;
	public final static int IDX_ACCT_ID = 3;
	public final static int IDX_USER_ID = 4;
	public final static int IDX_PHONE_ID = 5;
	public final static int IDX_REASON_CODE = 6;
	public final static int IDX_SO_NBR = 7;
	public final static int IDX_CREATE_DATE = 8;
	public final static int IDX_STS = 9;

	/**
	 * 
	 */
	@XmlElement(name="id")
	@Sdl
	private long id;

	/**
	 * 
	 */
	@XmlElement(name="associateId")
	@Sdl
	private long associateId;

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
	@XmlElement(name="reasonCode")
	@Sdl
	private int reasonCode;

	/**
	 * 
	 */
	@XmlElement(name="soNbr")
	@Sdl
	private long soNbr;

	/**
	 * 
	 */
	@XmlElement(name="createDate")
	@Sdl
	private Date createDate;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	public void setId(long obj){
		this.id = obj;
		onFieldSet(0, obj);
	}

	public long getId(){
		return id;
	}

	public void setAssociateId(long obj){
		this.associateId = obj;
		onFieldSet(1, obj);
	}

	public long getAssociateId(){
		return associateId;
	}

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(2, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(3, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(4, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(5, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setReasonCode(int obj){
		this.reasonCode = obj;
		onFieldSet(6, obj);
	}

	public int getReasonCode(){
		return reasonCode;
	}

	public void setSoNbr(long obj){
		this.soNbr = obj;
		onFieldSet(7, obj);
	}

	public long getSoNbr(){
		return soNbr;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(8, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(9, obj);
	}

	public int getSts(){
		return sts;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SProdOrderSync(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 10; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SProdOrderSync(SProdOrderSync arg0){
		copy(arg0);
	}

	public void copy(final SProdOrderSync rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		id = rhs.id;
		associateId = rhs.associateId;
		custId = rhs.custId;
		acctId = rhs.acctId;
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		reasonCode = rhs.reasonCode;
		soNbr = rhs.soNbr;
		createDate = rhs.createDate;
		sts = rhs.sts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProdOrderSync rhs=(SProdOrderSync)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(associateId, rhs.associateId)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(reasonCode, rhs.reasonCode)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(associateId)
		.append(custId)
		.append(acctId)
		.append(userId)
		.append(phoneId)
		.append(reasonCode)
		.append(soNbr)
		.append(createDate)
		.append(sts)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(10);
public static final long BITS_ALL_MARKER = 0x200L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SProdOrderSync";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "ASSOCIATE_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "CUST_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "ACCT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "USER_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "PHONE_ID", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "REASON_CODE", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "SO_NBR", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "CREATE_DATE", 8, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdOrderSync.class, "STS", 9, int.class));
}

}