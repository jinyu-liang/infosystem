package com.ailk.openbilling.persistence.imssdl.entity;

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
@XmlType(propOrder={"organizeId","organizeName","countyId","districtId"})
@Sdl(module="MImsSyncDef")
public class SOrgInfoResp extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ORGANIZE_ID = "ORGANIZE_ID";
	public final static String COL_ORGANIZE_NAME = "ORGANIZE_NAME";
	public final static String COL_COUNTY_ID = "COUNTY_ID";
	public final static String COL_DISTRICT_ID = "DISTRICT_ID";
	public final static int IDX_ORGANIZE_ID = 0;
	public final static int IDX_ORGANIZE_NAME = 1;
	public final static int IDX_COUNTY_ID = 2;
	public final static int IDX_DISTRICT_ID = 3;

	/**
	 * 
	 */
	@XmlElement(name="organizeId")
	@Sdl
	private long organizeId;

	/**
	 * 
	 */
	@XmlElement(name="organizeName")
	@Sdl
	private String organizeName;

	/**
	 * 
	 */
	@XmlElement(name="countyId")
	@Sdl
	private long countyId;

	/**
	 * 
	 */
	@XmlElement(name="districtId")
	@Sdl
	private String districtId;

	public void setOrganizeId(long obj){
		this.organizeId = obj;
		onFieldSet(0, obj);
	}

	public long getOrganizeId(){
		return organizeId;
	}

	public void setOrganizeName(String obj){
		this.organizeName = obj;
		onFieldSet(1, obj);
	}

	public String getOrganizeName(){
		return organizeName;
	}

	public void setCountyId(long obj){
		this.countyId = obj;
		onFieldSet(2, obj);
	}

	public long getCountyId(){
		return countyId;
	}

	public void setDistrictId(String obj){
		this.districtId = obj;
		onFieldSet(3, obj);
	}

	public String getDistrictId(){
		return districtId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOrgInfoResp(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOrgInfoResp(SOrgInfoResp arg0){
		copy(arg0);
	}

	public void copy(final SOrgInfoResp rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		organizeId = rhs.organizeId;
		organizeName = rhs.organizeName;
		countyId = rhs.countyId;
		districtId = rhs.districtId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOrgInfoResp rhs=(SOrgInfoResp)rhs0;
		if(!ObjectUtils.equals(organizeId, rhs.organizeId)) return false;
		if(!ObjectUtils.equals(organizeName, rhs.organizeName)) return false;
		if(!ObjectUtils.equals(countyId, rhs.countyId)) return false;
		if(!ObjectUtils.equals(districtId, rhs.districtId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(organizeId)
		.append(organizeName)
		.append(countyId)
		.append(districtId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SOrgInfoResp";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOrgInfoResp.class, "ORGANIZE_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOrgInfoResp.class, "ORGANIZE_NAME", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOrgInfoResp.class, "COUNTY_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOrgInfoResp.class, "DISTRICT_ID", 3, String.class));
}

}