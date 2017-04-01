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
@XmlType(propOrder={"rgRuleName","rgBaseName"})
@Sdl(module="MXdr")
public class SRgName extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RG_RULE_NAME = "RG_RULE_NAME";
	public final static String COL_RG_BASE_NAME = "RG_BASE_NAME";
	public final static int IDX_RG_RULE_NAME = 0;
	public final static int IDX_RG_BASE_NAME = 1;

	/**
	 * 
	 */
	@XmlElement(name="rgRuleName")
	@Sdl
	private String rgRuleName;

	/**
	 * 
	 */
	@XmlElement(name="rgBaseName")
	@Sdl
	private String rgBaseName;

	public void setRgRuleName(String obj){
		this.rgRuleName = obj;
		onFieldSet(0, obj);
	}

	public String getRgRuleName(){
		return rgRuleName;
	}

	public void setRgBaseName(String obj){
		this.rgBaseName = obj;
		onFieldSet(1, obj);
	}

	public String getRgBaseName(){
		return rgBaseName;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRgName(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRgName(SRgName arg0){
		copy(arg0);
	}

	public void copy(final SRgName rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		rgRuleName = rhs.rgRuleName;
		rgBaseName = rhs.rgBaseName;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRgName rhs=(SRgName)rhs0;
		if(!ObjectUtils.equals(rgRuleName, rhs.rgRuleName)) return false;
		if(!ObjectUtils.equals(rgBaseName, rhs.rgBaseName)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(rgRuleName)
		.append(rgBaseName)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRgName";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRgName.class, "RG_RULE_NAME", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRgName.class, "RG_BASE_NAME", 1, String.class));
}

}