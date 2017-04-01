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
@XmlType(propOrder={"url","alarmType","assetDtl"})
@Sdl(module="MXdr")
public class SUrlAsset extends CsdlStructObject implements IComplexEntity{

	public final static String COL_URL = "URL";
	public final static String COL_ALARM_TYPE = "ALARM_TYPE";
	public final static String COL_ASSET_DTL = "ASSET_DTL";
	public final static int IDX_URL = 0;
	public final static int IDX_ALARM_TYPE = 1;
	public final static int IDX_ASSET_DTL = 2;

	/**
	 * 
	 */
	@XmlElement(name="url")
	@Sdl
	private SUrlAddr url;

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

	public void setUrl(SUrlAddr obj){
		this.url = obj;
		onFieldSet(0, obj);
	}

	public SUrlAddr getUrl(){
		return url;
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

	public SUrlAsset(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUrlAsset(SUrlAsset arg0){
		copy(arg0);
	}

	public void copy(final SUrlAsset rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		url = rhs.url;
		alarmType = rhs.alarmType;
		assetDtl = rhs.assetDtl;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUrlAsset rhs=(SUrlAsset)rhs0;
		if(!ObjectUtils.equals(url, rhs.url)) return false;
		if(!ObjectUtils.equals(alarmType, rhs.alarmType)) return false;
		if(!ObjectUtils.equals(assetDtl, rhs.assetDtl)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(url)
		.append(alarmType)
		.append(assetDtl)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SUrlAsset";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUrlAsset.class, "URL", 0, SUrlAddr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUrlAsset.class, "ALARM_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUrlAsset.class, "ASSET_DTL", 2, SAocAssetDtl.class));
}

}