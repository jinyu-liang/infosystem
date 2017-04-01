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
@XmlType(propOrder={"paramList"})
@Sdl(module="MImsTsDef")
public class STsRecord extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PARAM_LIST = "PARAM_LIST";
	public final static int IDX_PARAM_LIST = 0;

	/**
	 * 
	 */
	@XmlElement(name="paramList")
	@Sdl
	private List<STsParam> paramList;

	public void setParamList(List<STsParam> obj){
		this.paramList = obj;
		onFieldSet(0, obj);
	}

	public List<STsParam> getParamList(){
		return paramList;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public STsRecord(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 1; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public STsRecord(STsRecord arg0){
		copy(arg0);
	}

	public void copy(final STsRecord rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		paramList = rhs.paramList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STsRecord rhs=(STsRecord)rhs0;
		if(!ObjectUtils.equals(paramList, rhs.paramList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paramList)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(1);
public static final long BITS_ALL_MARKER = 0x1L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.STsRecord";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(STsRecord.class, "PARAM_LIST", 0, List.class));
}

}