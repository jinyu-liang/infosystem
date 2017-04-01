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
@XmlType(propOrder={"itemSubType","fee","measureId","includeTaxFlag","hasOriCharge"})
@Sdl(module="MXdr")
public class SOriCharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ITEM_SUB_TYPE = "ITEM_SUB_TYPE";
	public final static String COL_FEE = "FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_INCLUDE_TAX_FLAG = "INCLUDE_TAX_FLAG";
	public final static String COL_HAS_ORI_CHARGE = "HAS_ORI_CHARGE";
	public final static int IDX_ITEM_SUB_TYPE = 0;
	public final static int IDX_FEE = 1;
	public final static int IDX_MEASURE_ID = 2;
	public final static int IDX_INCLUDE_TAX_FLAG = 3;
	public final static int IDX_HAS_ORI_CHARGE = 4;

	/**
	 * 
	 */
	@XmlElement(name="itemSubType")
	@Sdl
	private int itemSubType;

	/**
	 * 
	 */
	@XmlElement(name="fee")
	@Sdl
	private long fee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="includeTaxFlag")
	@Sdl
	private int includeTaxFlag;

	/**
	 * 
	 */
	@XmlElement(name="hasOriCharge")
	@Sdl
	private short hasOriCharge;

	public void setItemSubType(int obj){
		this.itemSubType = obj;
		onFieldSet(0, obj);
	}

	public int getItemSubType(){
		return itemSubType;
	}

	public void setFee(long obj){
		this.fee = obj;
		onFieldSet(1, obj);
	}

	public long getFee(){
		return fee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(2, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setIncludeTaxFlag(int obj){
		this.includeTaxFlag = obj;
		onFieldSet(3, obj);
	}

	public int getIncludeTaxFlag(){
		return includeTaxFlag;
	}

	public void setHasOriCharge(short obj){
		this.hasOriCharge = obj;
		onFieldSet(4, obj);
	}

	public short getHasOriCharge(){
		return hasOriCharge;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOriCharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOriCharge(SOriCharge arg0){
		copy(arg0);
	}

	public void copy(final SOriCharge rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		itemSubType = rhs.itemSubType;
		fee = rhs.fee;
		measureId = rhs.measureId;
		includeTaxFlag = rhs.includeTaxFlag;
		hasOriCharge = rhs.hasOriCharge;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOriCharge rhs=(SOriCharge)rhs0;
		if(!ObjectUtils.equals(itemSubType, rhs.itemSubType)) return false;
		if(!ObjectUtils.equals(fee, rhs.fee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(includeTaxFlag, rhs.includeTaxFlag)) return false;
		if(!ObjectUtils.equals(hasOriCharge, rhs.hasOriCharge)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(itemSubType)
		.append(fee)
		.append(measureId)
		.append(includeTaxFlag)
		.append(hasOriCharge)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOriCharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOriCharge.class, "ITEM_SUB_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOriCharge.class, "FEE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOriCharge.class, "MEASURE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOriCharge.class, "INCLUDE_TAX_FLAG", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOriCharge.class, "HAS_ORI_CHARGE", 4, short.class));
}

}