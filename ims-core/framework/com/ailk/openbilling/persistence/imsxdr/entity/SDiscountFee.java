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
@XmlType(propOrder={"itemId","measureId","productId","priceId","priceType","reduceFee","standardProductId"})
@Sdl(module="MXdr")
public class SDiscountFee extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ITEM_ID = "ITEM_ID";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_PRICE_TYPE = "PRICE_TYPE";
	public final static String COL_REDUCE_FEE = "REDUCE_FEE";
	public final static String COL_STANDARD_PRODUCT_ID = "STANDARD_PRODUCT_ID";
	public final static int IDX_ITEM_ID = 0;
	public final static int IDX_MEASURE_ID = 1;
	public final static int IDX_PRODUCT_ID = 2;
	public final static int IDX_PRICE_ID = 3;
	public final static int IDX_PRICE_TYPE = 4;
	public final static int IDX_REDUCE_FEE = 5;
	public final static int IDX_STANDARD_PRODUCT_ID = 6;

	/**
	 * 
	 */
	@XmlElement(name="itemId")
	@Sdl
	private int itemId;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

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
	@XmlElement(name="priceType")
	@Sdl
	private int priceType;

	/**
	 * 
	 */
	@XmlElement(name="reduceFee")
	@Sdl
	private long reduceFee;

	/**
	 * 
	 */
	@XmlElement(name="standardProductId")
	@Sdl
	private long standardProductId;

	public void setItemId(int obj){
		this.itemId = obj;
		onFieldSet(0, obj);
	}

	public int getItemId(){
		return itemId;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(1, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(2, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(3, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setPriceType(int obj){
		this.priceType = obj;
		onFieldSet(4, obj);
	}

	public int getPriceType(){
		return priceType;
	}

	public void setReduceFee(long obj){
		this.reduceFee = obj;
		onFieldSet(5, obj);
	}

	public long getReduceFee(){
		return reduceFee;
	}

	public void setStandardProductId(long obj){
		this.standardProductId = obj;
		onFieldSet(6, obj);
	}

	public long getStandardProductId(){
		return standardProductId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SDiscountFee(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SDiscountFee(SDiscountFee arg0){
		copy(arg0);
	}

	public void copy(final SDiscountFee rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		itemId = rhs.itemId;
		measureId = rhs.measureId;
		productId = rhs.productId;
		priceId = rhs.priceId;
		priceType = rhs.priceType;
		reduceFee = rhs.reduceFee;
		standardProductId = rhs.standardProductId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDiscountFee rhs=(SDiscountFee)rhs0;
		if(!ObjectUtils.equals(itemId, rhs.itemId)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(priceType, rhs.priceType)) return false;
		if(!ObjectUtils.equals(reduceFee, rhs.reduceFee)) return false;
		if(!ObjectUtils.equals(standardProductId, rhs.standardProductId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(itemId)
		.append(measureId)
		.append(productId)
		.append(priceId)
		.append(priceType)
		.append(reduceFee)
		.append(standardProductId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SDiscountFee";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "MEASURE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "PRODUCT_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "PRICE_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "PRICE_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "REDUCE_FEE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDiscountFee.class, "STANDARD_PRODUCT_ID", 6, long.class));
}

}