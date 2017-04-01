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
@XmlType(propOrder={"resourceId","serviceSpecId","firstUsedDate"})
@Sdl(module="MImsSyncDef")
public class SResSevCycle extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_SERVICE_SPEC_ID = "SERVICE_SPEC_ID";
	public final static String COL_FIRST_USED_DATE = "FIRST_USED_DATE";
	public final static int IDX_RESOURCE_ID = 0;
	public final static int IDX_SERVICE_SPEC_ID = 1;
	public final static int IDX_FIRST_USED_DATE = 2;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="serviceSpecId")
	@Sdl
	private int serviceSpecId;

	/**
	 * 
	 */
	@XmlElement(name="firstUsedDate")
	@Sdl
	private long firstUsedDate;

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(0, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setServiceSpecId(int obj){
		this.serviceSpecId = obj;
		onFieldSet(1, obj);
	}

	public int getServiceSpecId(){
		return serviceSpecId;
	}

	public void setFirstUsedDate(long obj){
		this.firstUsedDate = obj;
		onFieldSet(2, obj);
	}

	public long getFirstUsedDate(){
		return firstUsedDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SResSevCycle(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SResSevCycle(SResSevCycle arg0){
		copy(arg0);
	}

	public void copy(final SResSevCycle rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		resourceId = rhs.resourceId;
		serviceSpecId = rhs.serviceSpecId;
		firstUsedDate = rhs.firstUsedDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SResSevCycle rhs=(SResSevCycle)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(serviceSpecId, rhs.serviceSpecId)) return false;
		if(!ObjectUtils.equals(firstUsedDate, rhs.firstUsedDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(serviceSpecId)
		.append(firstUsedDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SResSevCycle";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SResSevCycle.class, "RESOURCE_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResSevCycle.class, "SERVICE_SPEC_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SResSevCycle.class, "FIRST_USED_DATE", 2, long.class));
}

}