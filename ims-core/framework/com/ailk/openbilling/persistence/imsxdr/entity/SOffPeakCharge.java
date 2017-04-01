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
@XmlType(propOrder={"timeSegment","timeAddup","volumeAddup","charge"})
@Sdl(module="MXdr")
public class SOffPeakCharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_TIME_SEGMENT = "TIME_SEGMENT";
	public final static String COL_TIME_ADDUP = "TIME_ADDUP";
	public final static String COL_VOLUME_ADDUP = "VOLUME_ADDUP";
	public final static String COL_CHARGE = "CHARGE";
	public final static int IDX_TIME_SEGMENT = 0;
	public final static int IDX_TIME_ADDUP = 1;
	public final static int IDX_VOLUME_ADDUP = 2;
	public final static int IDX_CHARGE = 3;

	/**
	 * 
	 */
	@XmlElement(name="timeSegment")
	@Sdl
	private SOffPeak timeSegment;

	/**
	 * 
	 */
	@XmlElement(name="charge")
	@Sdl
	private List<SCharge> charge;

	/**
	 * 
	 */
	@XmlElement(name="timeAddup")
	@Sdl
	private long timeAddup;

	/**
	 * 
	 */
	@XmlElement(name="volumeAddup")
	@Sdl
	private long volumeAddup;

	public void setTimeSegment(SOffPeak obj){
		this.timeSegment = obj;
		onFieldSet(0, obj);
	}

	public SOffPeak getTimeSegment(){
		return timeSegment;
	}

	public void setCharge(List<SCharge> obj){
		this.charge = obj;
		onFieldSet(3, obj);
	}

	public List<SCharge> getCharge(){
		return charge;
	}

	public void setTimeAddup(long obj){
		this.timeAddup = obj;
		onFieldSet(1, obj);
	}

	public long getTimeAddup(){
		return timeAddup;
	}

	public void setVolumeAddup(long obj){
		this.volumeAddup = obj;
		onFieldSet(2, obj);
	}

	public long getVolumeAddup(){
		return volumeAddup;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOffPeakCharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOffPeakCharge(SOffPeakCharge arg0){
		copy(arg0);
	}

	public void copy(final SOffPeakCharge rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		timeSegment = rhs.timeSegment;
		timeAddup = rhs.timeAddup;
		volumeAddup = rhs.volumeAddup;
		charge = rhs.charge;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOffPeakCharge rhs=(SOffPeakCharge)rhs0;
		if(!ObjectUtils.equals(timeSegment, rhs.timeSegment)) return false;
		if(!ObjectUtils.equals(timeAddup, rhs.timeAddup)) return false;
		if(!ObjectUtils.equals(volumeAddup, rhs.volumeAddup)) return false;
		if(!ObjectUtils.equals(charge, rhs.charge)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(timeSegment)
		.append(timeAddup)
		.append(volumeAddup)
		.append(charge)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOffPeakCharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOffPeakCharge.class, "TIME_SEGMENT", 0, SOffPeak.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOffPeakCharge.class, "TIME_ADDUP", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOffPeakCharge.class, "VOLUME_ADDUP", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOffPeakCharge.class, "CHARGE", 3, List.class));
}

}