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
@XmlType(propOrder={"productId","priceId","leftValue","measureId"})
@Sdl(module="MXdr")
public class SAccumulateAdjustMiddleInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_LEFT_VALUE = "LEFT_VALUE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_PRODUCT_ID = 0;
	public final static int IDX_PRICE_ID = 1;
	public final static int IDX_LEFT_VALUE = 2;
	public final static int IDX_MEASURE_ID = 3;

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
	@XmlElement(name="leftValue")
	@Sdl
	private long leftValue;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

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

	public void setLeftValue(long obj){
		this.leftValue = obj;
		onFieldSet(2, obj);
	}

	public long getLeftValue(){
		return leftValue;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(3, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAccumulateAdjustMiddleInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAccumulateAdjustMiddleInfo(SAccumulateAdjustMiddleInfo arg0){
		copy(arg0);
	}

	public void copy(final SAccumulateAdjustMiddleInfo rhs){
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
		leftValue = rhs.leftValue;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAccumulateAdjustMiddleInfo rhs=(SAccumulateAdjustMiddleInfo)rhs0;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(leftValue, rhs.leftValue)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productId)
		.append(priceId)
		.append(leftValue)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAccumulateAdjustMiddleInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateAdjustMiddleInfo.class, "PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateAdjustMiddleInfo.class, "PRICE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateAdjustMiddleInfo.class, "LEFT_VALUE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulateAdjustMiddleInfo.class, "MEASURE_ID", 3, int.class));
}

}