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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acctId","resourceId","assetType","assetId","assetItemId","validDate","expireDate","amount","used","remainAmount","status","measureId","isCommonPocket","mainBalanceFlag","billingType","orgAmount","orgValidDate","orgExpireDate","groupId","productId","offerId","freeresFlag","objectType","sharedFreeres","extraMap"})
@Sdl(module="MXdr")
public class SAssetInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_ASSET_TYPE = "ASSET_TYPE";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_ASSET_ITEM_ID = "ASSET_ITEM_ID";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_USED = "USED";
	public final static String COL_REMAIN_AMOUNT = "REMAIN_AMOUNT";
	public final static String COL_STATUS = "STATUS";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_IS_COMMON_POCKET = "IS_COMMON_POCKET";
	public final static String COL_MAIN_BALANCE_FLAG = "MAIN_BALANCE_FLAG";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_ORG_AMOUNT = "ORG_AMOUNT";
	public final static String COL_ORG_VALID_DATE = "ORG_VALID_DATE";
	public final static String COL_ORG_EXPIRE_DATE = "ORG_EXPIRE_DATE";
	public final static String COL_GROUP_ID = "GROUP_ID";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_OFFER_ID = "OFFER_ID";
	public final static String COL_FREERES_FLAG = "FREERES_FLAG";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_SHARED_FREERES = "SHARED_FREERES";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_RESOURCE_ID = 1;
	public final static int IDX_ASSET_TYPE = 2;
	public final static int IDX_ASSET_ID = 3;
	public final static int IDX_ASSET_ITEM_ID = 4;
	public final static int IDX_VALID_DATE = 5;
	public final static int IDX_EXPIRE_DATE = 6;
	public final static int IDX_AMOUNT = 7;
	public final static int IDX_USED = 8;
	public final static int IDX_REMAIN_AMOUNT = 9;
	public final static int IDX_STATUS = 10;
	public final static int IDX_MEASURE_ID = 11;
	public final static int IDX_IS_COMMON_POCKET = 12;
	public final static int IDX_MAIN_BALANCE_FLAG = 13;
	public final static int IDX_BILLING_TYPE = 14;
	public final static int IDX_ORG_AMOUNT = 15;
	public final static int IDX_ORG_VALID_DATE = 16;
	public final static int IDX_ORG_EXPIRE_DATE = 17;
	public final static int IDX_GROUP_ID = 18;
	public final static int IDX_PRODUCT_ID = 19;
	public final static int IDX_OFFER_ID = 20;
	public final static int IDX_FREERES_FLAG = 21;
	public final static int IDX_OBJECT_TYPE = 22;
	public final static int IDX_SHARED_FREERES = 23;
	public final static int IDX_EXTRA_MAP = 24;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

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
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="used")
	@Sdl
	private long used;

	/**
	 * 
	 */
	@XmlElement(name="remainAmount")
	@Sdl
	private long remainAmount;

	/**
	 * 
	 */
	@XmlElement(name="status")
	@Sdl
	private short status;

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
	private short isCommonPocket;

	/**
	 * 
	 */
	@XmlElement(name="mainBalanceFlag")
	@Sdl
	private short mainBalanceFlag;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private short billingType;

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
	private long orgValidDate;

	/**
	 * 
	 */
	@XmlElement(name="orgExpireDate")
	@Sdl
	private long orgExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="groupId")
	@Sdl
	private int groupId;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="offerId")
	@Sdl
	private long offerId;

	/**
	 * 
	 */
	@XmlElement(name="freeresFlag")
	@Sdl
	private short freeresFlag;

	/**
	 * 
	 */
	@XmlElement(name="objectType")
	@Sdl
	private short objectType;

	/**
	 * 
	 */
	@XmlElement(name="sharedFreeres")
	@Sdl
	private short sharedFreeres;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(24, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

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

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(5, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(6, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(7, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setUsed(long obj){
		this.used = obj;
		onFieldSet(8, obj);
	}

	public long getUsed(){
		return used;
	}

	public void setRemainAmount(long obj){
		this.remainAmount = obj;
		onFieldSet(9, obj);
	}

	public long getRemainAmount(){
		return remainAmount;
	}

	public void setStatus(short obj){
		this.status = obj;
		onFieldSet(10, obj);
	}

	public short getStatus(){
		return status;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(11, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setIsCommonPocket(short obj){
		this.isCommonPocket = obj;
		onFieldSet(12, obj);
	}

	public short getIsCommonPocket(){
		return isCommonPocket;
	}

	public void setMainBalanceFlag(short obj){
		this.mainBalanceFlag = obj;
		onFieldSet(13, obj);
	}

	public short getMainBalanceFlag(){
		return mainBalanceFlag;
	}

	public void setBillingType(short obj){
		this.billingType = obj;
		onFieldSet(14, obj);
	}

	public short getBillingType(){
		return billingType;
	}

	public void setOrgAmount(long obj){
		this.orgAmount = obj;
		onFieldSet(15, obj);
	}

	public long getOrgAmount(){
		return orgAmount;
	}

	public void setOrgValidDate(long obj){
		this.orgValidDate = obj;
		onFieldSet(16, obj);
	}

	public long getOrgValidDate(){
		return orgValidDate;
	}

	public void setOrgExpireDate(long obj){
		this.orgExpireDate = obj;
		onFieldSet(17, obj);
	}

	public long getOrgExpireDate(){
		return orgExpireDate;
	}

	public void setGroupId(int obj){
		this.groupId = obj;
		onFieldSet(18, obj);
	}

	public int getGroupId(){
		return groupId;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(19, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setOfferId(long obj){
		this.offerId = obj;
		onFieldSet(20, obj);
	}

	public long getOfferId(){
		return offerId;
	}

	public void setFreeresFlag(short obj){
		this.freeresFlag = obj;
		onFieldSet(21, obj);
	}

	public short getFreeresFlag(){
		return freeresFlag;
	}

	public void setObjectType(short obj){
		this.objectType = obj;
		onFieldSet(22, obj);
	}

	public short getObjectType(){
		return objectType;
	}

	public void setSharedFreeres(short obj){
		this.sharedFreeres = obj;
		onFieldSet(23, obj);
	}

	public short getSharedFreeres(){
		return sharedFreeres;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAssetInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 25; 
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
		used = rhs.used;
		remainAmount = rhs.remainAmount;
		status = rhs.status;
		measureId = rhs.measureId;
		isCommonPocket = rhs.isCommonPocket;
		mainBalanceFlag = rhs.mainBalanceFlag;
		billingType = rhs.billingType;
		orgAmount = rhs.orgAmount;
		orgValidDate = rhs.orgValidDate;
		orgExpireDate = rhs.orgExpireDate;
		groupId = rhs.groupId;
		productId = rhs.productId;
		offerId = rhs.offerId;
		freeresFlag = rhs.freeresFlag;
		objectType = rhs.objectType;
		sharedFreeres = rhs.sharedFreeres;
		extraMap = rhs.extraMap;
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
		if(!ObjectUtils.equals(used, rhs.used)) return false;
		if(!ObjectUtils.equals(remainAmount, rhs.remainAmount)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(isCommonPocket, rhs.isCommonPocket)) return false;
		if(!ObjectUtils.equals(mainBalanceFlag, rhs.mainBalanceFlag)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(orgAmount, rhs.orgAmount)) return false;
		if(!ObjectUtils.equals(orgValidDate, rhs.orgValidDate)) return false;
		if(!ObjectUtils.equals(orgExpireDate, rhs.orgExpireDate)) return false;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(freeresFlag, rhs.freeresFlag)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(sharedFreeres, rhs.sharedFreeres)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
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
		.append(used)
		.append(remainAmount)
		.append(status)
		.append(measureId)
		.append(isCommonPocket)
		.append(mainBalanceFlag)
		.append(billingType)
		.append(orgAmount)
		.append(orgValidDate)
		.append(orgExpireDate)
		.append(groupId)
		.append(productId)
		.append(offerId)
		.append(freeresFlag)
		.append(objectType)
		.append(sharedFreeres)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(25);
public static final long BITS_ALL_MARKER = 0x1000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAssetInfo";
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
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "VALID_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "EXPIRE_DATE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "AMOUNT", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "USED", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "REMAIN_AMOUNT", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "STATUS", 10, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "MEASURE_ID", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "IS_COMMON_POCKET", 12, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "MAIN_BALANCE_FLAG", 13, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "BILLING_TYPE", 14, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ORG_AMOUNT", 15, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ORG_VALID_DATE", 16, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "ORG_EXPIRE_DATE", 17, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "GROUP_ID", 18, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "PRODUCT_ID", 19, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "OFFER_ID", 20, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "FREERES_FLAG", 21, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "OBJECT_TYPE", 22, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "SHARED_FREERES", 23, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAssetInfo.class, "EXTRA_MAP", 24, Map.class));
}

}