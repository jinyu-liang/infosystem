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
@XmlType(propOrder={"oriStartRes","interStartRes","chargingUnit","measureId","standardProductId"})
@Sdl(module="MXdr")
public class SPreStdChargingInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ORI_START_RES = "ORI_START_RES";
	public final static String COL_INTER_START_RES = "INTER_START_RES";
	public final static String COL_CHARGING_UNIT = "CHARGING_UNIT";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_STANDARD_PRODUCT_ID = "STANDARD_PRODUCT_ID";
	public final static int IDX_ORI_START_RES = 0;
	public final static int IDX_INTER_START_RES = 1;
	public final static int IDX_CHARGING_UNIT = 2;
	public final static int IDX_MEASURE_ID = 3;
	public final static int IDX_STANDARD_PRODUCT_ID = 4;

	/**
	 * 
	 */
	@XmlElement(name="oriStartRes")
	@Sdl
	private long oriStartRes;

	/**
	 * 
	 */
	@XmlElement(name="interStartRes")
	@Sdl
	private long interStartRes;

	/**
	 * 
	 */
	@XmlElement(name="chargingUnit")
	@Sdl
	private int chargingUnit;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="standardProductId")
	@Sdl
	private long standardProductId;

	public void setOriStartRes(long obj){
		this.oriStartRes = obj;
		onFieldSet(0, obj);
	}

	public long getOriStartRes(){
		return oriStartRes;
	}

	public void setInterStartRes(long obj){
		this.interStartRes = obj;
		onFieldSet(1, obj);
	}

	public long getInterStartRes(){
		return interStartRes;
	}

	public void setChargingUnit(int obj){
		this.chargingUnit = obj;
		onFieldSet(2, obj);
	}

	public int getChargingUnit(){
		return chargingUnit;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(3, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setStandardProductId(long obj){
		this.standardProductId = obj;
		onFieldSet(4, obj);
	}

	public long getStandardProductId(){
		return standardProductId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPreStdChargingInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPreStdChargingInfo(SPreStdChargingInfo arg0){
		copy(arg0);
	}

	public void copy(final SPreStdChargingInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		oriStartRes = rhs.oriStartRes;
		interStartRes = rhs.interStartRes;
		chargingUnit = rhs.chargingUnit;
		measureId = rhs.measureId;
		standardProductId = rhs.standardProductId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPreStdChargingInfo rhs=(SPreStdChargingInfo)rhs0;
		if(!ObjectUtils.equals(oriStartRes, rhs.oriStartRes)) return false;
		if(!ObjectUtils.equals(interStartRes, rhs.interStartRes)) return false;
		if(!ObjectUtils.equals(chargingUnit, rhs.chargingUnit)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(standardProductId, rhs.standardProductId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oriStartRes)
		.append(interStartRes)
		.append(chargingUnit)
		.append(measureId)
		.append(standardProductId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPreStdChargingInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPreStdChargingInfo.class, "ORI_START_RES", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPreStdChargingInfo.class, "INTER_START_RES", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPreStdChargingInfo.class, "CHARGING_UNIT", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPreStdChargingInfo.class, "MEASURE_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPreStdChargingInfo.class, "STANDARD_PRODUCT_ID", 4, long.class));
}

}