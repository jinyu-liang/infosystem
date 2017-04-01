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
@XmlType(propOrder={"key","value"})
@Sdl(module="MXdr")
public class SPromParam extends CsdlStructObject implements IComplexEntity{

	public final static String COL_KEY = "KEY";
	public final static String COL_VALUE = "VALUE";
	public final static int IDX_KEY = 0;
	public final static int IDX_VALUE = 1;

	/**
	 * 
	 */
	@XmlElement(name="key")
	@Sdl
	private int key;

	/**
	 * 
	 */
	@XmlElement(name="value")
	@Sdl
	private String value;

	public void setKey(int obj){
		this.key = obj;
		onFieldSet(0, obj);
	}

	public int getKey(){
		return key;
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

	public SPromParam(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPromParam(SPromParam arg0){
		copy(arg0);
	}

	public void copy(final SPromParam rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		key = rhs.key;
		value = rhs.value;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPromParam rhs=(SPromParam)rhs0;
		if(!ObjectUtils.equals(key, rhs.key)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(key)
		.append(value)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPromParam";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPromParam.class, "KEY", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPromParam.class, "VALUE", 1, String.class));
}

}