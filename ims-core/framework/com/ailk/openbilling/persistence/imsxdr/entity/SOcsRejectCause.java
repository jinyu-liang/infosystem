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
@XmlType(propOrder={"errTypeEnum","errCause"})
@Sdl(module="MXdr")
public class SOcsRejectCause extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ERR_TYPE_ENUM = "ERR_TYPE_ENUM";
	public final static String COL_ERR_CAUSE = "ERR_CAUSE";
	public final static int IDX_ERR_TYPE_ENUM = 0;
	public final static int IDX_ERR_CAUSE = 1;

	/**
	 * 
	 */
	@XmlElement(name="errTypeEnum")
	@Sdl
	private int errTypeEnum;

	/**
	 * 
	 */
	@XmlElement(name="errCause")
	@Sdl
	private long errCause;

	public void setErrTypeEnum(int obj){
		this.errTypeEnum = obj;
		onFieldSet(0, obj);
	}

	public int getErrTypeEnum(){
		return errTypeEnum;
	}

	public void setErrCause(long obj){
		this.errCause = obj;
		onFieldSet(1, obj);
	}

	public long getErrCause(){
		return errCause;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOcsRejectCause(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOcsRejectCause(SOcsRejectCause arg0){
		copy(arg0);
	}

	public void copy(final SOcsRejectCause rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		errTypeEnum = rhs.errTypeEnum;
		errCause = rhs.errCause;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOcsRejectCause rhs=(SOcsRejectCause)rhs0;
		if(!ObjectUtils.equals(errTypeEnum, rhs.errTypeEnum)) return false;
		if(!ObjectUtils.equals(errCause, rhs.errCause)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(errTypeEnum)
		.append(errCause)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOcsRejectCause";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOcsRejectCause.class, "ERR_TYPE_ENUM", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOcsRejectCause.class, "ERR_CAUSE", 1, long.class));
}

}