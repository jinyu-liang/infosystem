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
@XmlType(propOrder={"standardProductId","chargeItem","taxId","fee","reduceFee","measureId"})
@Sdl(module="MXdr")
public class STaxCharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_STANDARD_PRODUCT_ID = "STANDARD_PRODUCT_ID";
	public final static String COL_CHARGE_ITEM = "CHARGE_ITEM";
	public final static String COL_TAX_ID = "TAX_ID";
	public final static String COL_FEE = "FEE";
	public final static String COL_REDUCE_FEE = "REDUCE_FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_STANDARD_PRODUCT_ID = 0;
	public final static int IDX_CHARGE_ITEM = 1;
	public final static int IDX_TAX_ID = 2;
	public final static int IDX_FEE = 3;
	public final static int IDX_REDUCE_FEE = 4;
	public final static int IDX_MEASURE_ID = 5;

	/**
	 * 
	 */
	@XmlElement(name="standardProductId")
	@Sdl
	private long standardProductId;

	/**
	 * 
	 */
	@XmlElement(name="chargeItem")
	@Sdl
	private int chargeItem;

	/**
	 * 
	 */
	@XmlElement(name="taxId")
	@Sdl
	private int taxId;

	/**
	 * 
	 */
	@XmlElement(name="fee")
	@Sdl
	private long fee;

	/**
	 * 
	 */
	@XmlElement(name="reduceFee")
	@Sdl
	private long reduceFee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setStandardProductId(long obj){
		this.standardProductId = obj;
		onFieldSet(0, obj);
	}

	public long getStandardProductId(){
		return standardProductId;
	}

	public void setChargeItem(int obj){
		this.chargeItem = obj;
		onFieldSet(1, obj);
	}

	public int getChargeItem(){
		return chargeItem;
	}

	public void setTaxId(int obj){
		this.taxId = obj;
		onFieldSet(2, obj);
	}

	public int getTaxId(){
		return taxId;
	}

	public void setFee(long obj){
		this.fee = obj;
		onFieldSet(3, obj);
	}

	public long getFee(){
		return fee;
	}

	public void setReduceFee(long obj){
		this.reduceFee = obj;
		onFieldSet(4, obj);
	}

	public long getReduceFee(){
		return reduceFee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public STaxCharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public STaxCharge(STaxCharge arg0){
		copy(arg0);
	}

	public void copy(final STaxCharge rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		standardProductId = rhs.standardProductId;
		chargeItem = rhs.chargeItem;
		taxId = rhs.taxId;
		fee = rhs.fee;
		reduceFee = rhs.reduceFee;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STaxCharge rhs=(STaxCharge)rhs0;
		if(!ObjectUtils.equals(standardProductId, rhs.standardProductId)) return false;
		if(!ObjectUtils.equals(chargeItem, rhs.chargeItem)) return false;
		if(!ObjectUtils.equals(taxId, rhs.taxId)) return false;
		if(!ObjectUtils.equals(fee, rhs.fee)) return false;
		if(!ObjectUtils.equals(reduceFee, rhs.reduceFee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(standardProductId)
		.append(chargeItem)
		.append(taxId)
		.append(fee)
		.append(reduceFee)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.STaxCharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(STaxCharge.class, "STANDARD_PRODUCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STaxCharge.class, "CHARGE_ITEM", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STaxCharge.class, "TAX_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STaxCharge.class, "FEE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STaxCharge.class, "REDUCE_FEE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STaxCharge.class, "MEASURE_ID", 5, int.class));
}

}