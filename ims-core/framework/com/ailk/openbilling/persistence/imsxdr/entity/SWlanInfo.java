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
@XmlType(propOrder={"hostspotId","acAddress","asAddress","homeCarrier","roamCarrier"})
@Sdl(module="MXdr")
public class SWlanInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_HOSTSPOT_ID = "HOSTSPOT_ID";
	public final static String COL_AC_ADDRESS = "AC_ADDRESS";
	public final static String COL_AS_ADDRESS = "AS_ADDRESS";
	public final static String COL_HOME_CARRIER = "HOME_CARRIER";
	public final static String COL_ROAM_CARRIER = "ROAM_CARRIER";
	public final static int IDX_HOSTSPOT_ID = 0;
	public final static int IDX_AC_ADDRESS = 1;
	public final static int IDX_AS_ADDRESS = 2;
	public final static int IDX_HOME_CARRIER = 3;
	public final static int IDX_ROAM_CARRIER = 4;

	/**
	 * 
	 */
	@XmlElement(name="hostspotId")
	@Sdl
	private String hostspotId;

	/**
	 * 
	 */
	@XmlElement(name="acAddress")
	@Sdl
	private String acAddress;

	/**
	 * 
	 */
	@XmlElement(name="asAddress")
	@Sdl
	private String asAddress;

	/**
	 * 
	 */
	@XmlElement(name="homeCarrier")
	@Sdl
	private long homeCarrier;

	/**
	 * 
	 */
	@XmlElement(name="roamCarrier")
	@Sdl
	private long roamCarrier;

	public void setHostspotId(String obj){
		this.hostspotId = obj;
		onFieldSet(0, obj);
	}

	public String getHostspotId(){
		return hostspotId;
	}

	public void setAcAddress(String obj){
		this.acAddress = obj;
		onFieldSet(1, obj);
	}

	public String getAcAddress(){
		return acAddress;
	}

	public void setAsAddress(String obj){
		this.asAddress = obj;
		onFieldSet(2, obj);
	}

	public String getAsAddress(){
		return asAddress;
	}

	public void setHomeCarrier(long obj){
		this.homeCarrier = obj;
		onFieldSet(3, obj);
	}

	public long getHomeCarrier(){
		return homeCarrier;
	}

	public void setRoamCarrier(long obj){
		this.roamCarrier = obj;
		onFieldSet(4, obj);
	}

	public long getRoamCarrier(){
		return roamCarrier;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SWlanInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SWlanInfo(SWlanInfo arg0){
		copy(arg0);
	}

	public void copy(final SWlanInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		hostspotId = rhs.hostspotId;
		acAddress = rhs.acAddress;
		asAddress = rhs.asAddress;
		homeCarrier = rhs.homeCarrier;
		roamCarrier = rhs.roamCarrier;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SWlanInfo rhs=(SWlanInfo)rhs0;
		if(!ObjectUtils.equals(hostspotId, rhs.hostspotId)) return false;
		if(!ObjectUtils.equals(acAddress, rhs.acAddress)) return false;
		if(!ObjectUtils.equals(asAddress, rhs.asAddress)) return false;
		if(!ObjectUtils.equals(homeCarrier, rhs.homeCarrier)) return false;
		if(!ObjectUtils.equals(roamCarrier, rhs.roamCarrier)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(hostspotId)
		.append(acAddress)
		.append(asAddress)
		.append(homeCarrier)
		.append(roamCarrier)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SWlanInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SWlanInfo.class, "HOSTSPOT_ID", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SWlanInfo.class, "AC_ADDRESS", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SWlanInfo.class, "AS_ADDRESS", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SWlanInfo.class, "HOME_CARRIER", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SWlanInfo.class, "ROAM_CARRIER", 4, long.class));
}

}