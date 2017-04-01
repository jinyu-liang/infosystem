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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acctId","ownerId","ownerType","productId","priceId","measureId","ratingItemId","validDateTime","expireDateTime","ratingValue"})
@Sdl(module="MXdr")
public class SAddupResQueryVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_RATING_ITEM_ID = "RATING_ITEM_ID";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_RATING_VALUE = "RATING_VALUE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_OWNER_ID = 1;
	public final static int IDX_OWNER_TYPE = 2;
	public final static int IDX_PRODUCT_ID = 3;
	public final static int IDX_PRICE_ID = 4;
	public final static int IDX_MEASURE_ID = 5;
	public final static int IDX_RATING_ITEM_ID = 6;
	public final static int IDX_VALID_DATE_TIME = 7;
	public final static int IDX_EXPIRE_DATE_TIME = 8;
	public final static int IDX_RATING_VALUE = 9;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="ownerId")
	@Sdl
	private long ownerId;

	/**
	 * 
	 */
	@XmlElement(name="ownerType")
	@Sdl
	private int ownerType;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="priceId")
	@Sdl
	private int priceId;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="ratingItemId")
	@Sdl
	private long ratingItemId;

	/**
	 * 
	 */
	@XmlElement(name="validDateTime")
	@Sdl
	private long validDateTime;

	/**
	 * 
	 */
	@XmlElement(name="expireDateTime")
	@Sdl
	private long expireDateTime;

	/**
	 * 
	 */
	@XmlElement(name="ratingValue")
	@Sdl
	private long ratingValue;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(1, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setOwnerType(int obj){
		this.ownerType = obj;
		onFieldSet(2, obj);
	}

	public int getOwnerType(){
		return ownerType;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(3, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(4, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setRatingItemId(long obj){
		this.ratingItemId = obj;
		onFieldSet(6, obj);
	}

	public long getRatingItemId(){
		return ratingItemId;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(7, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(8, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setRatingValue(long obj){
		this.ratingValue = obj;
		onFieldSet(9, obj);
	}

	public long getRatingValue(){
		return ratingValue;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAddupResQueryVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 10; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAddupResQueryVal(SAddupResQueryVal arg0){
		copy(arg0);
	}

	public void copy(final SAddupResQueryVal rhs){
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
		ownerId = rhs.ownerId;
		ownerType = rhs.ownerType;
		productId = rhs.productId;
		priceId = rhs.priceId;
		measureId = rhs.measureId;
		ratingItemId = rhs.ratingItemId;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		ratingValue = rhs.ratingValue;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAddupResQueryVal rhs=(SAddupResQueryVal)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(ratingItemId, rhs.ratingItemId)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(ratingValue, rhs.ratingValue)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(ownerId)
		.append(ownerType)
		.append(productId)
		.append(priceId)
		.append(measureId)
		.append(ratingItemId)
		.append(validDateTime)
		.append(expireDateTime)
		.append(ratingValue)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(10);
public static final long BITS_ALL_MARKER = 0x200L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAddupResQueryVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "OWNER_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "OWNER_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "PRODUCT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "PRICE_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "MEASURE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "RATING_ITEM_ID", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "VALID_DATE_TIME", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "EXPIRE_DATE_TIME", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResQueryVal.class, "RATING_VALUE", 9, long.class));
}

}