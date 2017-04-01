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
@XmlType(propOrder={"feeItemId","amount","measureId","extraMap"})
@Sdl(module="MXdr")
public class SOtcFee extends CsdlStructObject implements IComplexEntity{

	public final static String COL_FEE_ITEM_ID = "FEE_ITEM_ID";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_FEE_ITEM_ID = 0;
	public final static int IDX_AMOUNT = 1;
	public final static int IDX_MEASURE_ID = 2;
	public final static int IDX_EXTRA_MAP = 3;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="feeItemId")
	@Sdl
	private int feeItemId;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private int amount;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(3, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setFeeItemId(int obj){
		this.feeItemId = obj;
		onFieldSet(0, obj);
	}

	public int getFeeItemId(){
		return feeItemId;
	}

	public void setAmount(int obj){
		this.amount = obj;
		onFieldSet(1, obj);
	}

	public int getAmount(){
		return amount;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(2, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOtcFee(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOtcFee(SOtcFee arg0){
		copy(arg0);
	}

	public void copy(final SOtcFee rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		feeItemId = rhs.feeItemId;
		amount = rhs.amount;
		measureId = rhs.measureId;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOtcFee rhs=(SOtcFee)rhs0;
		if(!ObjectUtils.equals(feeItemId, rhs.feeItemId)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(feeItemId)
		.append(amount)
		.append(measureId)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOtcFee";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOtcFee.class, "FEE_ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOtcFee.class, "AMOUNT", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOtcFee.class, "MEASURE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOtcFee.class, "EXTRA_MAP", 3, Map.class));
}

}