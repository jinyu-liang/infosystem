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
@XmlType(propOrder={"chargeItemId","chargeUnit","resMeasureId","rateValue","currencyMeasureId"})
@Sdl(module="MXdr")
public class SRateValue extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CHARGE_ITEM_ID = "CHARGE_ITEM_ID";
	public final static String COL_CHARGE_UNIT = "CHARGE_UNIT";
	public final static String COL_RES_MEASURE_ID = "RES_MEASURE_ID";
	public final static String COL_RATE_VALUE = "RATE_VALUE";
	public final static String COL_CURRENCY_MEASURE_ID = "CURRENCY_MEASURE_ID";
	public final static int IDX_CHARGE_ITEM_ID = 0;
	public final static int IDX_CHARGE_UNIT = 1;
	public final static int IDX_RES_MEASURE_ID = 2;
	public final static int IDX_RATE_VALUE = 3;
	public final static int IDX_CURRENCY_MEASURE_ID = 4;

	/**
	 * 
	 */
	@XmlElement(name="chargeItemId")
	@Sdl
	private int chargeItemId;

	/**
	 * 
	 */
	@XmlElement(name="chargeUnit")
	@Sdl
	private int chargeUnit;

	/**
	 * 
	 */
	@XmlElement(name="resMeasureId")
	@Sdl
	private int resMeasureId;

	/**
	 * 
	 */
	@XmlElement(name="rateValue")
	@Sdl
	private int rateValue;

	/**
	 * 
	 */
	@XmlElement(name="currencyMeasureId")
	@Sdl
	private int currencyMeasureId;

	public void setChargeItemId(int obj){
		this.chargeItemId = obj;
		onFieldSet(0, obj);
	}

	public int getChargeItemId(){
		return chargeItemId;
	}

	public void setChargeUnit(int obj){
		this.chargeUnit = obj;
		onFieldSet(1, obj);
	}

	public int getChargeUnit(){
		return chargeUnit;
	}

	public void setResMeasureId(int obj){
		this.resMeasureId = obj;
		onFieldSet(2, obj);
	}

	public int getResMeasureId(){
		return resMeasureId;
	}

	public void setRateValue(int obj){
		this.rateValue = obj;
		onFieldSet(3, obj);
	}

	public int getRateValue(){
		return rateValue;
	}

	public void setCurrencyMeasureId(int obj){
		this.currencyMeasureId = obj;
		onFieldSet(4, obj);
	}

	public int getCurrencyMeasureId(){
		return currencyMeasureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRateValue(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRateValue(SRateValue arg0){
		copy(arg0);
	}

	public void copy(final SRateValue rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		chargeItemId = rhs.chargeItemId;
		chargeUnit = rhs.chargeUnit;
		resMeasureId = rhs.resMeasureId;
		rateValue = rhs.rateValue;
		currencyMeasureId = rhs.currencyMeasureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRateValue rhs=(SRateValue)rhs0;
		if(!ObjectUtils.equals(chargeItemId, rhs.chargeItemId)) return false;
		if(!ObjectUtils.equals(chargeUnit, rhs.chargeUnit)) return false;
		if(!ObjectUtils.equals(resMeasureId, rhs.resMeasureId)) return false;
		if(!ObjectUtils.equals(rateValue, rhs.rateValue)) return false;
		if(!ObjectUtils.equals(currencyMeasureId, rhs.currencyMeasureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(chargeItemId)
		.append(chargeUnit)
		.append(resMeasureId)
		.append(rateValue)
		.append(currencyMeasureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRateValue";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRateValue.class, "CHARGE_ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateValue.class, "CHARGE_UNIT", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateValue.class, "RES_MEASURE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateValue.class, "RATE_VALUE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateValue.class, "CURRENCY_MEASURE_ID", 4, int.class));
}

}