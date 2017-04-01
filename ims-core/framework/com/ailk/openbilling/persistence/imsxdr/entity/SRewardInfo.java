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
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"rewardId","allotId","acctId","resourceId","rewardAmount","measureId","billingType","productOfferingId","creditItemId","validDate","expireDate","rewardType","assetInfoList","extraMap"})
@Sdl(module="MXdr")
public class SRewardInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_REWARD_ID = "REWARD_ID";
	public final static String COL_ALLOT_ID = "ALLOT_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_REWARD_AMOUNT = "REWARD_AMOUNT";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_PRODUCT_OFFERING_ID = "PRODUCT_OFFERING_ID";
	public final static String COL_CREDIT_ITEM_ID = "CREDIT_ITEM_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_REWARD_TYPE = "REWARD_TYPE";
	public final static String COL_ASSET_INFO_LIST = "ASSET_INFO_LIST";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_REWARD_ID = 0;
	public final static int IDX_ALLOT_ID = 1;
	public final static int IDX_ACCT_ID = 2;
	public final static int IDX_RESOURCE_ID = 3;
	public final static int IDX_REWARD_AMOUNT = 4;
	public final static int IDX_MEASURE_ID = 5;
	public final static int IDX_BILLING_TYPE = 6;
	public final static int IDX_PRODUCT_OFFERING_ID = 7;
	public final static int IDX_CREDIT_ITEM_ID = 8;
	public final static int IDX_VALID_DATE = 9;
	public final static int IDX_EXPIRE_DATE = 10;
	public final static int IDX_REWARD_TYPE = 11;
	public final static int IDX_ASSET_INFO_LIST = 12;
	public final static int IDX_EXTRA_MAP = 13;

	/**
	 * 
	 */
	@XmlElement(name="assetInfoList")
	@Sdl
	private List<SAssetInfo> assetInfoList;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

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
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private short billingType;

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
	private int creditItemId;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private long validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private long expireDate;

	/**
	 * 
	 */
	@XmlElement(name="rewardType")
	@Sdl
	private short rewardType;

	public void setAssetInfoList(List<SAssetInfo> obj){
		this.assetInfoList = obj;
		onFieldSet(12, obj);
	}

	public List<SAssetInfo> getAssetInfoList(){
		return assetInfoList;
	}

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(13, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
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

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setBillingType(short obj){
		this.billingType = obj;
		onFieldSet(6, obj);
	}

	public short getBillingType(){
		return billingType;
	}

	public void setProductOfferingId(int obj){
		this.productOfferingId = obj;
		onFieldSet(7, obj);
	}

	public int getProductOfferingId(){
		return productOfferingId;
	}

	public void setCreditItemId(int obj){
		this.creditItemId = obj;
		onFieldSet(8, obj);
	}

	public int getCreditItemId(){
		return creditItemId;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(9, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(10, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setRewardType(short obj){
		this.rewardType = obj;
		onFieldSet(11, obj);
	}

	public short getRewardType(){
		return rewardType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRewardInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 14; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRewardInfo(SRewardInfo arg0){
		copy(arg0);
	}

	public void copy(final SRewardInfo rhs){
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
		measureId = rhs.measureId;
		billingType = rhs.billingType;
		productOfferingId = rhs.productOfferingId;
		creditItemId = rhs.creditItemId;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		rewardType = rhs.rewardType;
		assetInfoList = rhs.assetInfoList;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRewardInfo rhs=(SRewardInfo)rhs0;
		if(!ObjectUtils.equals(rewardId, rhs.rewardId)) return false;
		if(!ObjectUtils.equals(allotId, rhs.allotId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(rewardAmount, rhs.rewardAmount)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(productOfferingId, rhs.productOfferingId)) return false;
		if(!ObjectUtils.equals(creditItemId, rhs.creditItemId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(rewardType, rhs.rewardType)) return false;
		if(!ObjectUtils.equals(assetInfoList, rhs.assetInfoList)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(rewardId)
		.append(allotId)
		.append(acctId)
		.append(resourceId)
		.append(rewardAmount)
		.append(measureId)
		.append(billingType)
		.append(productOfferingId)
		.append(creditItemId)
		.append(validDate)
		.append(expireDate)
		.append(rewardType)
		.append(assetInfoList)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(14);
public static final long BITS_ALL_MARKER = 0x2000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRewardInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "REWARD_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "ALLOT_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "ACCT_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "RESOURCE_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "REWARD_AMOUNT", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "MEASURE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "BILLING_TYPE", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "PRODUCT_OFFERING_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "CREDIT_ITEM_ID", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "VALID_DATE", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "EXPIRE_DATE", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "REWARD_TYPE", 11, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "ASSET_INFO_LIST", 12, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRewardInfo.class, "EXTRA_MAP", 13, Map.class));
}

}