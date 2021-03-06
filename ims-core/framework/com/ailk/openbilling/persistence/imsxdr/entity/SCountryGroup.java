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
@XmlType(propOrder={"countryGroupId"})
@Sdl(module="MXdr")
public class SCountryGroup extends CsdlStructObject implements IComplexEntity{

	public final static String COL_COUNTRY_GROUP_ID = "COUNTRY_GROUP_ID";
	public final static int IDX_COUNTRY_GROUP_ID = 0;

	/**
	 * 
	 */
	@XmlElement(name="countryGroupId")
	@Sdl
	private int countryGroupId;

	public void setCountryGroupId(int obj){
		this.countryGroupId = obj;
		onFieldSet(0, obj);
	}

	public int getCountryGroupId(){
		return countryGroupId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCountryGroup(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 1; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCountryGroup(SCountryGroup arg0){
		copy(arg0);
	}

	public void copy(final SCountryGroup rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		countryGroupId = rhs.countryGroupId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCountryGroup rhs=(SCountryGroup)rhs0;
		if(!ObjectUtils.equals(countryGroupId, rhs.countryGroupId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(countryGroupId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(1);
public static final long BITS_ALL_MARKER = 0x1L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SCountryGroup";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCountryGroup.class, "COUNTRY_GROUP_ID", 0, int.class));
}

}