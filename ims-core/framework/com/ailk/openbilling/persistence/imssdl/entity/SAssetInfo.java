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
@XmlType(propOrder={"acctId","resourceId","assetType","assetId","assetItemId","validDate","expireDate","amount","status","measureId","isCommonPocket","billingType","orgAmount","orgValidDate","orgExpireDate"})
@Sdl(module="MImsSyncDef")
public class SAssetInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_ASSET_TYPE = "ASSET_TYPE";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_ASSET_ITEM_ID = "ASSET_ITEM_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_STATUS = "STATUS";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_IS_COMMON_POCKET = "IS_COMMON_POCKET";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_ORG_AMOUNT = "ORG_AMOUNT";
	public final static String COL_ORG_VALID_DATE = "ORG_VALID_DATE";
	public final static String COL_ORG_EXPIRE_DATE = "ORG_EXPIRE_DATE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_RESOURCE_ID = 1;
	public final static int IDX_ASSET_TYPE = 2;
	public final static int IDX_ASSET_ID = 3;
	public final static int IDX_ASSET_ITEM_ID = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_AMOUNT = 7;
	public final static int IDX_STATUS = 8;
	public final static int IDX_MEASURE_ID = 9;
	public final static int IDX_IS_COMMON_POCKET = 10;
	public final static int IDX_BILLING_TYPE = 11;
	public final static int IDX_ORG_AMOUNT = 12;
	public final static int IDX_ORG_VALID_DATE = 13;
	public final static int IDX_ORG_EXPIRE_DATE = 14;

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
	@XmlElement(name="assetType")
	@Sdl
	private int assetType;

	/**
	 * 
	 */
	@XmlElement(name="assetId")
	@Sdl
	private long assetId;

	/**
	 * 
	 */
	@XmlElement(name="assetItemId")
	@Sdl
	private int assetItemId;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private Date validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private Date expireDate;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="status")
	@Sdl
	private int status;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="isCommonPocket")
	@Sdl
	private int isCommonPocket;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	/**
	 * 
	 */
	@XmlElement(name="orgAmount")
	@Sdl
	private long orgAmount;

	/**
	 * 
	 */
	@XmlElement(name="orgValidDate")
	@Sdl
	private Date orgValidDate;

	/**
	 * 
	 */
	@XmlElement(name="orgExpireDate")
	@Sdl
	private Date orgExpireDate;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(1, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setAssetType(int obj){
		this.assetType = obj;
		onFieldSet(2, obj);
	}

	public int getAssetType(){
		return assetType;
	}

	public void setAssetId(long obj){
		this.assetId = obj;
		onFieldSet(3, obj);
	}

	public long getAssetId(){
		return assetId;
	}

	public void setAssetItemId(int obj){
		this.assetItemId = obj;
		onFieldSet(4, obj);
	}

	public int getAssetItemId(){
		return assetItemId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(7, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setStatus(int obj){
		this.status = obj;
		onFieldSet(8, obj);
	}

	public int getStatus(){
		return status;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(9, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setIsCommonPocket(int obj){
		this.isCommonPocket = obj;
		onFieldSet(10, obj);
	}

	public int getIsCommonPocket(){
		return isCommonPocket;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(11, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public void setOrgAmount(long obj){
		this.orgAmount = obj;
		onFieldSet(12, obj);
	}

	public long getOrgAmount(){
		return orgAmount;
	}

	public void setOrgValidDate(Date obj){
		this.orgValidDate = obj;
		onFieldSet(13, obj);
	}

	public Date getOrgValidDate(){
		return orgValidDate;
	}

	public void setOrgExpireDate(Date obj){
		this.orgExpireDate = obj;
		onFieldSet(14, obj);
	}

	public Date getOrgExpireDate(){
		return orgExpireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAssetInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 15; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAssetInfo(SAssetInfo arg0){
		copy(arg0);
	}

	public void copy(final SAssetInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		acctId = rhs.acctId;
		resourceId = rhs.resourceId;
		assetType = rhs.assetType;
		assetId = rhs.assetId;
		assetItemId = rhs.assetItemId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		amount = rhs.amount;
		status = rhs.status;
		measureId = rhs.measureId;
		isCommonPocket = rhs.isCommonPocket;
		billingType = rhs.billingType;
		orgAmount = rhs.orgAmount;
		orgValidDate = rhs.orgValidDate;
		orgExpireDate = rhs.orgExpireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAssetInfo rhs=(SAssetInfo)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(assetType, rhs.assetType)) return false;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(assetItemId, rhs.assetItemId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(isCommonPocket, rhs.isCommonPocket)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(orgAmount, rhs.orgAmount)) return false;
		if(!ObjectUtils.equals(orgValidDate, rhs.orgValidDate)) return false;
		if(!ObjectUtils.equals(orgExpireDate, rhs.orgExpireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(resourceId)
		.append(assetType)
		.append(assetId)
		.append(assetItemId)
		.append(validDate)
		.append(expireDate)
		.append(amount)
		.append(status)
		.append(measureId)
		.append(isCommonPocket)
		.append(billingType)
		.append(orgAmount)
		.append(orgValidDate)
		.append(orgExpireDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(15);
public static final long BITS_ALL_MARKER = 0x4000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SAssetInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "RESOURCE_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ASSET_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ASSET_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ASSET_ITEM_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "VALID_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "EXPIRE_DATE", 6, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "AMOUNT", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "STATUS", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "MEASURE_ID", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "IS_COMMON_POCKET", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "BILLING_TYPE", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ORG_AMOUNT", 12, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ORG_VALID_DATE", 13, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ORG_EXPIRE_DATE", 14, Date.class));
}

}