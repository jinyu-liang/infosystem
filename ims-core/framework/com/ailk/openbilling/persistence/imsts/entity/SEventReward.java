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
@XmlType(propOrder={"id","custId","acctId","resourceId","specId","billingType","createDate","soNbr","sts"})
@Sdl(module="MImsTsDef")
public class SEventReward extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ID = "ID";
	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_SPEC_ID = "SPEC_ID";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_STS = "STS";
	public final static int IDX_ID = 0;
	public final static int IDX_CUST_ID = 1;
	public final static int IDX_ACCT_ID = 2;
	public final static int IDX_RESOURCE_ID = 3;
	public final static int IDX_SPEC_ID = 4;
	public final static int IDX_BILLING_TYPE = 5;
	public final static int IDX_CREATE_DATE = 6;
	public final static int IDX_SO_NBR = 7;
	public final static int IDX_STS = 8;

	/**
	 * 
	 */
	@XmlElement(name="id")
	@Sdl
	private long id;

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
	@XmlElement(name="specId")
	@Sdl
	private long specId;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	/**
	 * 
	 */
	@XmlElement(name="createDate")
	@Sdl
	private Date createDate;

	/**
	 * 
	 */
	@XmlElement(name="soNbr")
	@Sdl
	private long soNbr;

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

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(1, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(2, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(3, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setSpecId(long obj){
		this.specId = obj;
		onFieldSet(4, obj);
	}

	public long getSpecId(){
		return specId;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(5, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(6, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSoNbr(long obj){
		this.soNbr = obj;
		onFieldSet(7, obj);
	}

	public long getSoNbr(){
		return soNbr;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(8, obj);
	}

	public int getSts(){
		return sts;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SEventReward(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SEventReward(SEventReward arg0){
		copy(arg0);
	}

	public void copy(final SEventReward rhs){
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
		custId = rhs.custId;
		acctId = rhs.acctId;
		resourceId = rhs.resourceId;
		specId = rhs.specId;
		billingType = rhs.billingType;
		createDate = rhs.createDate;
		soNbr = rhs.soNbr;
		sts = rhs.sts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SEventReward rhs=(SEventReward)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(specId, rhs.specId)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(custId)
		.append(acctId)
		.append(resourceId)
		.append(specId)
		.append(billingType)
		.append(createDate)
		.append(soNbr)
		.append(sts)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SEventReward";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "CUST_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "ACCT_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "RESOURCE_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "SPEC_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "BILLING_TYPE", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "CREATE_DATE", 6, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "SO_NBR", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventReward.class, "STS", 8, int.class));
}

}