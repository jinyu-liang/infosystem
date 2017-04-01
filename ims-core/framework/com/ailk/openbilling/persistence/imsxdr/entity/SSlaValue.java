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
@XmlType(propOrder={"slaType","sla","measureId"})
@Sdl(module="MXdr")
public class SSlaValue extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SLA_TYPE = "SLA_TYPE";
	public final static String COL_SLA = "SLA";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_SLA_TYPE = 0;
	public final static int IDX_SLA = 1;
	public final static int IDX_MEASURE_ID = 2;

	/**
	 * 
	 */
	@XmlElement(name="slaType")
	@Sdl
	private int slaType;

	/**
	 * 
	 */
	@XmlElement(name="sla")
	@Sdl
	private long sla;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setSlaType(int obj){
		this.slaType = obj;
		onFieldSet(0, obj);
	}

	public int getSlaType(){
		return slaType;
	}

	public void setSla(long obj){
		this.sla = obj;
		onFieldSet(1, obj);
	}

	public long getSla(){
		return sla;
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

	public SSlaValue(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSlaValue(SSlaValue arg0){
		copy(arg0);
	}

	public void copy(final SSlaValue rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		slaType = rhs.slaType;
		sla = rhs.sla;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSlaValue rhs=(SSlaValue)rhs0;
		if(!ObjectUtils.equals(slaType, rhs.slaType)) return false;
		if(!ObjectUtils.equals(sla, rhs.sla)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(slaType)
		.append(sla)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSlaValue";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSlaValue.class, "SLA_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSlaValue.class, "SLA", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSlaValue.class, "MEASURE_ID", 2, int.class));
}

}