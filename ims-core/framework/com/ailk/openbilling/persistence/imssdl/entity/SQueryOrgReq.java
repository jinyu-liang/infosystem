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
@XmlType(propOrder={"organizeId"})
@Sdl(module="MImsSyncDef")
public class SQueryOrgReq extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ORGANIZE_ID = "ORGANIZE_ID";
	public final static int IDX_ORGANIZE_ID = 0;

	/**
	 * 
	 */
	@XmlElement(name="organizeId")
	@Sdl
	private long organizeId;

	public void setOrganizeId(long obj){
		this.organizeId = obj;
		onFieldSet(0, obj);
	}

	public long getOrganizeId(){
		return organizeId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SQueryOrgReq(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 1; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SQueryOrgReq(SQueryOrgReq arg0){
		copy(arg0);
	}

	public void copy(final SQueryOrgReq rhs){
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
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryOrgReq rhs=(SQueryOrgReq)rhs0;
		if(!ObjectUtils.equals(organizeId, rhs.organizeId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(organizeId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(1);
public static final long BITS_ALL_MARKER = 0x1L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SQueryOrgReq";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryOrgReq.class, "ORGANIZE_ID", 0, long.class));
}

}