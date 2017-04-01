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
import java.sql.Timestamp;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","offerId","busiFlag","objectId","objectType","operType","validDate","expireDate","irValidDate","irExpireDate","irCountryName","isMain","oldMainOfferId","billingType","pricePlanId"})
@Sdl(module="MImsSyncDef")
public class SProdNotify extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_OFFER_ID = "OFFER_ID";
	public final static String COL_BUSI_FLAG = "BUSI_FLAG";
	public final static String COL_OBJECT_ID = "OBJECT_ID";
	public final static String COL_OBJECT_TYPE = "OBJECT_TYPE";
	public final static String COL_OPER_TYPE = "OPER_TYPE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_IR_VALID_DATE = "IR_VALID_DATE";
	public final static String COL_IR_EXPIRE_DATE = "IR_EXPIRE_DATE";
	public final static String COL_IR_COUNTRY_NAME = "IR_COUNTRY_NAME";
	public final static String COL_IS_MAIN = "IS_MAIN";
	public final static String COL_OLD_MAIN_OFFER_ID = "OLD_MAIN_OFFER_ID";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_PRICE_PLAN_ID = "PRICE_PLAN_ID";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_OFFER_ID = 1;
	public final static int IDX_BUSI_FLAG = 2;
	public final static int IDX_OBJECT_ID = 3;
	public final static int IDX_OBJECT_TYPE = 4;
	public final static int IDX_OPER_TYPE = 5;
	public final static int IDX_VALID_DATE = 6;
	public final static int IDX_EXPIRE_DATE = 7;
	public final static int IDX_IR_VALID_DATE = 8;
	public final static int IDX_IR_EXPIRE_DATE = 9;
	public final static int IDX_IR_COUNTRY_NAME = 10;
	public final static int IDX_IS_MAIN = 11;
	public final static int IDX_OLD_MAIN_OFFER_ID = 12;
	public final static int IDX_BILLING_TYPE = 13;
	public final static int IDX_PRICE_PLAN_ID = 14;

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
	private int offerId;

	/**
	 * 
	 */
	@XmlElement(name="busiFlag")
	@Sdl
	private int busiFlag;

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
	private int objectType;

	/**
	 * 
	 */
	@XmlElement(name="operType")
	@Sdl
	private int operType;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private Timestamp validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private Timestamp expireDate;

	/**
	 * 
	 */
	@XmlElement(name="irValidDate")
	@Sdl
	private Timestamp irValidDate;

	/**
	 * 
	 */
	@XmlElement(name="irExpireDate")
	@Sdl
	private Timestamp irExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="irCountryName")
	@Sdl
	private String irCountryName;

	/**
	 * 
	 */
	@XmlElement(name="isMain")
	@Sdl
	private int isMain;

	/**
	 * 
	 */
	@XmlElement(name="oldMainOfferId")
	@Sdl
	private int oldMainOfferId;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	/**
	 * 
	 */
	@XmlElement(name="pricePlanId")
	@Sdl
	private int pricePlanId;

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setOfferId(int obj){
		this.offerId = obj;
		onFieldSet(1, obj);
	}

	public int getOfferId(){
		return offerId;
	}

	public void setBusiFlag(int obj){
		this.busiFlag = obj;
		onFieldSet(2, obj);
	}

	public int getBusiFlag(){
		return busiFlag;
	}

	public void setObjectId(long obj){
		this.objectId = obj;
		onFieldSet(3, obj);
	}

	public long getObjectId(){
		return objectId;
	}

	public void setObjectType(int obj){
		this.objectType = obj;
		onFieldSet(4, obj);
	}

	public int getObjectType(){
		return objectType;
	}

	public void setOperType(int obj){
		this.operType = obj;
		onFieldSet(5, obj);
	}

	public int getOperType(){
		return operType;
	}

	public void setValidDate(Timestamp obj){
		this.validDate = obj;
		onFieldSet(6, obj);
	}

	public Timestamp getValidDate(){
		return validDate;
	}

	public void setExpireDate(Timestamp obj){
		this.expireDate = obj;
		onFieldSet(7, obj);
	}

	public Timestamp getExpireDate(){
		return expireDate;
	}

	public void setIrValidDate(Timestamp obj){
		this.irValidDate = obj;
		onFieldSet(8, obj);
	}

	public Timestamp getIrValidDate(){
		return irValidDate;
	}

	public void setIrExpireDate(Timestamp obj){
		this.irExpireDate = obj;
		onFieldSet(9, obj);
	}

	public Timestamp getIrExpireDate(){
		return irExpireDate;
	}

	public void setIrCountryName(String obj){
		this.irCountryName = obj;
		onFieldSet(10, obj);
	}

	public String getIrCountryName(){
		return irCountryName;
	}

	public void setIsMain(int obj){
		this.isMain = obj;
		onFieldSet(11, obj);
	}

	public int getIsMain(){
		return isMain;
	}

	public void setOldMainOfferId(int obj){
		this.oldMainOfferId = obj;
		onFieldSet(12, obj);
	}

	public int getOldMainOfferId(){
		return oldMainOfferId;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(13, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public void setPricePlanId(int obj){
		this.pricePlanId = obj;
		onFieldSet(14, obj);
	}

	public int getPricePlanId(){
		return pricePlanId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SProdNotify(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 15; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SProdNotify(SProdNotify arg0){
		copy(arg0);
	}

	public void copy(final SProdNotify rhs){
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
		offerId = rhs.offerId;
		busiFlag = rhs.busiFlag;
		objectId = rhs.objectId;
		objectType = rhs.objectType;
		operType = rhs.operType;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		irValidDate = rhs.irValidDate;
		irExpireDate = rhs.irExpireDate;
		irCountryName = rhs.irCountryName;
		isMain = rhs.isMain;
		oldMainOfferId = rhs.oldMainOfferId;
		billingType = rhs.billingType;
		pricePlanId = rhs.pricePlanId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProdNotify rhs=(SProdNotify)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(offerId, rhs.offerId)) return false;
		if(!ObjectUtils.equals(busiFlag, rhs.busiFlag)) return false;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(operType, rhs.operType)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(irValidDate, rhs.irValidDate)) return false;
		if(!ObjectUtils.equals(irExpireDate, rhs.irExpireDate)) return false;
		if(!ObjectUtils.equals(irCountryName, rhs.irCountryName)) return false;
		if(!ObjectUtils.equals(isMain, rhs.isMain)) return false;
		if(!ObjectUtils.equals(oldMainOfferId, rhs.oldMainOfferId)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(pricePlanId, rhs.pricePlanId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(offerId)
		.append(busiFlag)
		.append(objectId)
		.append(objectType)
		.append(operType)
		.append(validDate)
		.append(expireDate)
		.append(irValidDate)
		.append(irExpireDate)
		.append(irCountryName)
		.append(isMain)
		.append(oldMainOfferId)
		.append(billingType)
		.append(pricePlanId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(15);
public static final long BITS_ALL_MARKER = 0x4000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SProdNotify";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "OFFER_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "BUSI_FLAG", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "OBJECT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "OBJECT_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "OPER_TYPE", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "VALID_DATE", 6, Timestamp.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "EXPIRE_DATE", 7, Timestamp.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "IR_VALID_DATE", 8, Timestamp.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "IR_EXPIRE_DATE", 9, Timestamp.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "IR_COUNTRY_NAME", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "IS_MAIN", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "OLD_MAIN_OFFER_ID", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "BILLING_TYPE", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProdNotify.class, "PRICE_PLAN_ID", 14, int.class));
}

}