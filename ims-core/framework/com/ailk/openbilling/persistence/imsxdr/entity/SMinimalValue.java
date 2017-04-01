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
@XmlType(propOrder={"measureId","amountRes"})
@Sdl(module="MXdr")
public class SMinimalValue extends CsdlStructObject implements IComplexEntity{

	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_AMOUNT_RES = "AMOUNT_RES";
	public final static int IDX_MEASURE_ID = 0;
	public final static int IDX_AMOUNT_RES = 1;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="amountRes")
	@Sdl
	private long amountRes;

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(0, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setAmountRes(long obj){
		this.amountRes = obj;
		onFieldSet(1, obj);
	}

	public long getAmountRes(){
		return amountRes;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SMinimalValue(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SMinimalValue(SMinimalValue arg0){
		copy(arg0);
	}

	public void copy(final SMinimalValue rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		measureId = rhs.measureId;
		amountRes = rhs.amountRes;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMinimalValue rhs=(SMinimalValue)rhs0;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(amountRes, rhs.amountRes)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(measureId)
		.append(amountRes)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SMinimalValue";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SMinimalValue.class, "MEASURE_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMinimalValue.class, "AMOUNT_RES", 1, long.class));
}

}