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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"rewardId","allotId","acctId","resourceId","rewardAmount","billingType","productOfferingId","creditItemId","validDate","expireDate","assetInfoList"})
@Sdl(module="MImsSyncDef")
public class SCaRewardInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_REWARD_ID = "REWARD_ID";
	public final static String COL_ALLOT_ID = "ALLOT_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_REWARD_AMOUNT = "REWARD_AMOUNT";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_PRODUCT_OFFERING_ID = "PRODUCT_OFFERING_ID";
	public final static String COL_CREDIT_ITEM_ID = "CREDIT_ITEM_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_ASSET_INFO_LIST = "ASSET_INFO_LIST";
	public final static int IDX_REWARD_ID = 0;
	public final static int IDX_ALLOT_ID = 1;
	public final static int IDX_ACCT_ID = 2;
	public final static int IDX_RESOURCE_ID = 3;
	public final static int IDX_REWARD_AMOUNT = 4;
	public final static int IDX_BILLING_TYPE = 5;
	public final static int IDX_PRODUCT_OFFERING_ID = 6;
	public final static int IDX_CREDIT_ITEM_ID = 7;
	public final static int IDX_VALID_DATE = 8;
	public final static int IDX_EXPIRE_DATE = 9;
	public final static int IDX_ASSET_INFO_LIST = 10;

	/**
	 * 
	 */
	@XmlElement(name="assetInfoList")
	@Sdl
	private List<SAssetInfo> assetInfoList;

	/**
	 * 
	 */
	@XmlElement(name="rewardId")
	@Sdl
	private int rewardId;

	/**
	 * 
	 */
	@XmlElement(name="allotId")
	@Sdl
	private int allotId;

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
	@XmlElement(name="rewardAmount")
	@Sdl
	private long rewardAmount;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	/**
	 * 
	 */
	@XmlElement(name="productOfferingId")
	@Sdl
	private int productOfferingId;

	/**
	 * 
	 */
	@XmlElement(name="creditItemId")
	@Sdl
	private long creditItemId;

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

	public void setAssetInfoList(List<SAssetInfo> obj){
		this.assetInfoList = obj;
		onFieldSet(10, obj);
	}

	public List<SAssetInfo> getAssetInfoList(){
		return assetInfoList;
	}

	public void setRewardId(int obj){
		this.rewardId = obj;
		onFieldSet(0, obj);
	}

	public int getRewardId(){
		return rewardId;
	}

	public void setAllotId(int obj){
		this.allotId = obj;
		onFieldSet(1, obj);
	}

	public int getAllotId(){
		return allotId;
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

	public void setRewardAmount(long obj){
		this.rewardAmount = obj;
		onFieldSet(4, obj);
	}

	public long getRewardAmount(){
		return rewardAmount;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(5, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public void setProductOfferingId(int obj){
		this.productOfferingId = obj;
		onFieldSet(6, obj);
	}

	public int getProductOfferingId(){
		return productOfferingId;
	}

	public void setCreditItemId(long obj){
		this.creditItemId = obj;
		onFieldSet(7, obj);
	}

	public long getCreditItemId(){
		return creditItemId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(8, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(9, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCaRewardInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCaRewardInfo(SCaRewardInfo arg0){
		copy(arg0);
	}

	public void copy(final SCaRewardInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		rewardId = rhs.rewardId;
		allotId = rhs.allotId;
		acctId = rhs.acctId;
		resourceId = rhs.resourceId;
		rewardAmount = rhs.rewardAmount;
		billingType = rhs.billingType;
		productOfferingId = rhs.productOfferingId;
		creditItemId = rhs.creditItemId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		assetInfoList = rhs.assetInfoList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCaRewardInfo rhs=(SCaRewardInfo)rhs0;
		if(!ObjectUtils.equals(rewardId, rhs.rewardId)) return false;
		if(!ObjectUtils.equals(allotId, rhs.allotId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(rewardAmount, rhs.rewardAmount)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(productOfferingId, rhs.productOfferingId)) return false;
		if(!ObjectUtils.equals(creditItemId, rhs.creditItemId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(assetInfoList, rhs.assetInfoList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(rewardId)
		.append(allotId)
		.append(acctId)
		.append(resourceId)
		.append(rewardAmount)
		.append(billingType)
		.append(productOfferingId)
		.append(creditItemId)
		.append(validDate)
		.append(expireDate)
		.append(assetInfoList)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SCaRewardInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "REWARD_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "ALLOT_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "ACCT_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "RESOURCE_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "REWARD_AMOUNT", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "BILLING_TYPE", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "PRODUCT_OFFERING_ID", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "CREDIT_ITEM_ID", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "VALID_DATE", 8, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "EXPIRE_DATE", 9, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCaRewardInfo.class, "ASSET_INFO_LIST", 10, List.class));
}

}