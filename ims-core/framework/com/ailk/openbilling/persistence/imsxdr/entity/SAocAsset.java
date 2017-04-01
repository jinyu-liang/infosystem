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
@XmlType(propOrder={"aoc","alarmType","assetDtl"})
@Sdl(module="MXdr")
public class SAocAsset extends CsdlStructObject implements IComplexEntity{

	public final static String COL_AOC = "AOC";
	public final static String COL_ALARM_TYPE = "ALARM_TYPE";
	public final static String COL_ASSET_DTL = "ASSET_DTL";
	public final static int IDX_AOC = 0;
	public final static int IDX_ALARM_TYPE = 1;
	public final static int IDX_ASSET_DTL = 2;

	/**
	 * 
	 */
	@XmlElement(name="aoc")
	@Sdl
	private SAocCode aoc;

	/**
	 * 
	 */
	@XmlElement(name="assetDtl")
	@Sdl
	private SAocAssetDtl assetDtl;

	/**
	 * 
	 */
	@XmlElement(name="alarmType")
	@Sdl
	private int alarmType;

	public void setAoc(SAocCode obj){
		this.aoc = obj;
		onFieldSet(0, obj);
	}

	public SAocCode getAoc(){
		return aoc;
	}

	public void setAssetDtl(SAocAssetDtl obj){
		this.assetDtl = obj;
		onFieldSet(2, obj);
	}

	public SAocAssetDtl getAssetDtl(){
		return assetDtl;
	}

	public void setAlarmType(int obj){
		this.alarmType = obj;
		onFieldSet(1, obj);
	}

	public int getAlarmType(){
		return alarmType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAocAsset(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAocAsset(SAocAsset arg0){
		copy(arg0);
	}

	public void copy(final SAocAsset rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		aoc = rhs.aoc;
		alarmType = rhs.alarmType;
		assetDtl = rhs.assetDtl;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAocAsset rhs=(SAocAsset)rhs0;
		if(!ObjectUtils.equals(aoc, rhs.aoc)) return false;
		if(!ObjectUtils.equals(alarmType, rhs.alarmType)) return false;
		if(!ObjectUtils.equals(assetDtl, rhs.assetDtl)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(aoc)
		.append(alarmType)
		.append(assetDtl)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAocAsset";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAsset.class, "AOC", 0, SAocCode.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAsset.class, "ALARM_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAsset.class, "ASSET_DTL", 2, SAocAssetDtl.class));
}

}