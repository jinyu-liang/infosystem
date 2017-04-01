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
@XmlType(propOrder={"productId","priceId","ratingItemId","ratingValue","validDateTime","expireDateTime"})
@Sdl(module="MXdr")
public class SAddupResUsingVal extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_RATING_ITEM_ID = "RATING_ITEM_ID";
	public final static String COL_RATING_VALUE = "RATING_VALUE";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_PRICE_ID = 1;
	public final static int IDX_RATING_ITEM_ID = 2;
	public final static int IDX_RATING_VALUE = 3;
	public final static int IDX_VALID_DATE_TIME = 4;
	public final static int IDX_EXPIRE_DATE_TIME = 5;

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
	@XmlElement(name="ratingItemId")
	@Sdl
	private long ratingItemId;

	/**
	 * 
	 */
	@XmlElement(name="ratingValue")
	@Sdl
	private long ratingValue;

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

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(0, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(1, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setRatingItemId(long obj){
		this.ratingItemId = obj;
		onFieldSet(2, obj);
	}

	public long getRatingItemId(){
		return ratingItemId;
	}

	public void setRatingValue(long obj){
		this.ratingValue = obj;
		onFieldSet(3, obj);
	}

	public long getRatingValue(){
		return ratingValue;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(4, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(5, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAddupResUsingVal(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAddupResUsingVal(SAddupResUsingVal arg0){
		copy(arg0);
	}

	public void copy(final SAddupResUsingVal rhs){
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
		priceId = rhs.priceId;
		ratingItemId = rhs.ratingItemId;
		ratingValue = rhs.ratingValue;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAddupResUsingVal rhs=(SAddupResUsingVal)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(ratingItemId, rhs.ratingItemId)) return false;
		if(!ObjectUtils.equals(ratingValue, rhs.ratingValue)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(priceId)
		.append(ratingItemId)
		.append(ratingValue)
		.append(validDateTime)
		.append(expireDateTime)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAddupResUsingVal";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResUsingVal.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResUsingVal.class, "PRICE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResUsingVal.class, "RATING_ITEM_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResUsingVal.class, "RATING_VALUE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResUsingVal.class, "VALID_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAddupResUsingVal.class, "EXPIRE_DATE_TIME", 5, long.class));
}

}