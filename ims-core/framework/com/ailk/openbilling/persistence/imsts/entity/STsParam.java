package com.ailk.openbilling.persistence.imsts.entity;

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
@XmlType(propOrder={"name","value"})
@Sdl(module="MImsTsDef")
public class STsParam extends CsdlStructObject implements IComplexEntity{

	public final static String COL_NAME = "NAME";
	public final static String COL_VALUE = "VALUE";
	public final static int IDX_NAME = 0;
	public final static int IDX_VALUE = 1;

	/**
	 * 
	 */
	@XmlElement(name="name")
	@Sdl
	private String name;

	/**
	 * 
	 */
	@XmlElement(name="value")
	@Sdl
	private String value;

	public void setName(String obj){
		this.name = obj;
		onFieldSet(0, obj);
	}

	public String getName(){
		return name;
	}

	public void setValue(String obj){
		this.value = obj;
		onFieldSet(1, obj);
	}

	public String getValue(){
		return value;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public STsParam(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public STsParam(STsParam arg0){
		copy(arg0);
	}

	public void copy(final STsParam rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		name = rhs.name;
		value = rhs.value;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STsParam rhs=(STsParam)rhs0;
		if(!ObjectUtils.equals(name, rhs.name)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(name)
		.append(value)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.STsParam";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(STsParam.class, "NAME", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STsParam.class, "VALUE", 1, String.class));
}

}