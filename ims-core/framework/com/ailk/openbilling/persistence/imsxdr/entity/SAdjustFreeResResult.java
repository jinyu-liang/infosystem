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
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"lifeCycleStatus","assetInfoList","extraMap"})
@Sdl(module="MXdr")
public class SAdjustFreeResResult extends CsdlStructObject implements IComplexEntity{

	public final static String COL_LIFE_CYCLE_STATUS = "LIFE_CYCLE_STATUS";
	public final static String COL_ASSET_INFO_LIST = "ASSET_INFO_LIST";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_LIFE_CYCLE_STATUS = 0;
	public final static int IDX_ASSET_INFO_LIST = 1;
	public final static int IDX_EXTRA_MAP = 2;

	/**
	 * 
	 */
	@XmlElement(name="assetInfoList")
	@Sdl
	private List<SAssetInfo> assetInfoList;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="lifeCycleStatus")
	@Sdl
	private String lifeCycleStatus;

	public void setAssetInfoList(List<SAssetInfo> obj){
		this.assetInfoList = obj;
		onFieldSet(1, obj);
	}

	public List<SAssetInfo> getAssetInfoList(){
		return assetInfoList;
	}

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(2, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setLifeCycleStatus(String obj){
		this.lifeCycleStatus = obj;
		onFieldSet(0, obj);
	}

	public String getLifeCycleStatus(){
		return lifeCycleStatus;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAdjustFreeResResult(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAdjustFreeResResult(SAdjustFreeResResult arg0){
		copy(arg0);
	}

	public void copy(final SAdjustFreeResResult rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		lifeCycleStatus = rhs.lifeCycleStatus;
		assetInfoList = rhs.assetInfoList;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAdjustFreeResResult rhs=(SAdjustFreeResResult)rhs0;
		if(!ObjectUtils.equals(lifeCycleStatus, rhs.lifeCycleStatus)) return false;
		if(!ObjectUtils.equals(assetInfoList, rhs.assetInfoList)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(lifeCycleStatus)
		.append(assetInfoList)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAdjustFreeResResult";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustFreeResResult.class, "LIFE_CYCLE_STATUS", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustFreeResResult.class, "ASSET_INFO_LIST", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAdjustFreeResResult.class, "EXTRA_MAP", 2, Map.class));
}

}