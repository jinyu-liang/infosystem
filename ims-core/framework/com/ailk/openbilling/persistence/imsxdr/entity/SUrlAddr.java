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
@XmlType(propOrder={"redirectAddr","doneCode"})
@Sdl(module="MXdr")
public class SUrlAddr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_REDIRECT_ADDR = "REDIRECT_ADDR";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static int IDX_REDIRECT_ADDR = 0;
	public final static int IDX_DONE_CODE = 1;

	/**
	 * 
	 */
	@XmlElement(name="redirectAddr")
	@Sdl
	private String redirectAddr;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	public void setRedirectAddr(String obj){
		this.redirectAddr = obj;
		onFieldSet(0, obj);
	}

	public String getRedirectAddr(){
		return redirectAddr;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(1, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUrlAddr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUrlAddr(SUrlAddr arg0){
		copy(arg0);
	}

	public void copy(final SUrlAddr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		redirectAddr = rhs.redirectAddr;
		doneCode = rhs.doneCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUrlAddr rhs=(SUrlAddr)rhs0;
		if(!ObjectUtils.equals(redirectAddr, rhs.redirectAddr)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(redirectAddr)
		.append(doneCode)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SUrlAddr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUrlAddr.class, "REDIRECT_ADDR", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUrlAddr.class, "DONE_CODE", 1, long.class));
}

}