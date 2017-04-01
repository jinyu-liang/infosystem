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
@XmlType(propOrder={"productId","productOfferingId","pricePlanId","billingType","lifecycleStatusId","payAcctId","objectId","objectType","expireDate","validDate","syncFlag"})
@Sdl(module="MImsSyncDef")
public class SProdInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_PRODUCT_OFFERING_ID = "PRODUCT_OFFERING_ID";
	public final static String COL_PRICE_PLAN_ID = "PRICE_PLAN_ID";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_LIFECYCLE_STATUS_ID = "LIFECYCLE_STATUS_ID";
	public final static String COL_PAY_ACCT_ID = "PAY_ACCT_ID";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_SYNC_FLAG = "SYNC_FLAG";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_PRODUCT_OFFERING_ID = 1;
	public final static int IDX_PRICE_PLAN_ID = 2;
	public final static int IDX_BILLING_TYPE = 3;
	public final static int IDX_LIFECYCLE_STATUS_ID = 4;
	public final static int IDX_PAY_ACCT_ID = 5;
	public final static int IDX_OBJECT_ID = 6;
	public final static int IDX_OBJECT_TYPE = 7;
	public final static int IDX_EXPIRE_DATE = 8;
	public final static int IDX_VALID_DATE = 9;
	public final static int IDX_SYNC_FLAG = 10;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="productOfferingId")
	@Sdl
	private int productOfferingId;

	/**
	 * 
	 */
	@XmlElement(name="pricePlanId")
	@Sdl
	private int pricePlanId;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	/**
	 * 
	 */
	@XmlElement(name="lifecycleStatusId")
	@Sdl
	private short lifecycleStatusId;

	/**
	 * 
	 */
	@XmlElement(name="payAcctId")
	@Sdl
	private long payAcctId;

	/**
	 * 
	 */
	@XmlElement(name="objectId")
	@Sdl
	private long objectId;

	/**
	 * 
	 */
	@XmlElement(name="objectType")
	@Sdl
	private short objectType;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private int expireDate;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private int validDate;

	/**
	 * 
	 */
	@XmlElement(name="syncFlag")
	@Sdl
	private int syncFlag;

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setProductOfferingId(int obj){
		this.productOfferingId = obj;
		onFieldSet(1, obj);
	}

	public int getProductOfferingId(){
		return productOfferingId;
	}

	public void setPricePlanId(int obj){
		this.pricePlanId = obj;
		onFieldSet(2, obj);
	}

	public int getPricePlanId(){
		return pricePlanId;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(3, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public void setLifecycleStatusId(short obj){
		this.lifecycleStatusId = obj;
		onFieldSet(4, obj);
	}

	public short getLifecycleStatusId(){
		return lifecycleStatusId;
	}

	public void setPayAcctId(long obj){
		this.payAcctId = obj;
		onFieldSet(5, obj);
	}

	public long getPayAcctId(){
		return payAcctId;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(6, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setObjectType(short obj){
		this.objectType = obj;
		onFieldSet(7, obj);
	}

	public short getObjectType(){
		return objectType;
	}

	public void setExpireDate(int obj){
		this.expireDate = obj;
		onFieldSet(8, obj);
	}

	public int getExpireDate(){
		return expireDate;
	}

	public void setValidDate(int obj){
		this.validDate = obj;
		onFieldSet(9, obj);
	}

	public int getValidDate(){
		return validDate;
	}

	public void setSyncFlag(int obj){
		this.syncFlag = obj;
		onFieldSet(10, obj);
	}

	public int getSyncFlag(){
		return syncFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SProdInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SProdInfo(SProdInfo arg0){
		copy(arg0);
	}

	public void copy(final SProdInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		productId = rhs.productId;
		productOfferingId = rhs.productOfferingId;
		pricePlanId = rhs.pricePlanId;
		billingType = rhs.billingType;
		lifecycleStatusId = rhs.lifecycleStatusId;
		payAcctId = rhs.payAcctId;
		objectId = rhs.objectId;
		objectType = rhs.objectType;
		expireDate = rhs.expireDate;
		validDate = rhs.validDate;
		syncFlag = rhs.syncFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProdInfo rhs=(SProdInfo)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(productOfferingId, rhs.productOfferingId)) return false;
		if(!ObjectUtils.equals(pricePlanId, rhs.pricePlanId)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(lifecycleStatusId, rhs.lifecycleStatusId)) return false;
		if(!ObjectUtils.equals(payAcctId, rhs.payAcctId)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(syncFlag, rhs.syncFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(productOfferingId)
		.append(pricePlanId)
		.append(billingType)
		.append(lifecycleStatusId)
		.append(payAcctId)
		.append(objectId)
		.append(objectType)
		.append(expireDate)
		.append(validDate)
		.append(syncFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SProdInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "PRODUCT_OFFERING_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "PRICE_PLAN_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "BILLING_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "LIFECYCLE_STATUS_ID", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "PAY_ACCT_ID", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "OBJECT_ID", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "OBJECT_TYPE", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "EXPIRE_DATE", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "VALID_DATE", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdInfo.class, "SYNC_FLAG", 10, int.class));
}

}