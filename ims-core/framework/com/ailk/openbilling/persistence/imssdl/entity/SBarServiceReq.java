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
@XmlType(propOrder={"action","reasonCode","barserviceinfo"})
@Sdl(module="MImsSyncDef")
public class SBarServiceReq extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACTION = "ACTION";
	public final static String COL_REASON_CODE = "REASON_CODE";
	public final static String COL_BARSERVICEINFO = "BARSERVICEINFO";
	public final static int IDX_ACTION = 0;
	public final static int IDX_REASON_CODE = 1;
	public final static int IDX_BARSERVICEINFO = 2;

	/**
	 * 
	 */
	@XmlElement(name="barserviceinfo")
	@Sdl
	private SBarServiceInfo barserviceinfo;

	/**
	 * 
	 */
	@XmlElement(name="action")
	@Sdl
	private short action;

	/**
	 * 
	 */
	@XmlElement(name="reasonCode")
	@Sdl
	private short reasonCode;

	public void setBarserviceinfo(SBarServiceInfo obj){
		this.barserviceinfo = obj;
		onFieldSet(2, obj);
	}

	public SBarServiceInfo getBarserviceinfo(){
		return barserviceinfo;
	}

	public void setAction(short obj){
		this.action = obj;
		onFieldSet(0, obj);
	}

	public short getAction(){
		return action;
	}

	public void setReasonCode(short obj){
		this.reasonCode = obj;
		onFieldSet(1, obj);
	}

	public short getReasonCode(){
		return reasonCode;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBarServiceReq(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBarServiceReq(SBarServiceReq arg0){
		copy(arg0);
	}

	public void copy(final SBarServiceReq rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		action = rhs.action;
		reasonCode = rhs.reasonCode;
		barserviceinfo = rhs.barserviceinfo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBarServiceReq rhs=(SBarServiceReq)rhs0;
		if(!ObjectUtils.equals(action, rhs.action)) return false;
		if(!ObjectUtils.equals(reasonCode, rhs.reasonCode)) return false;
		if(!ObjectUtils.equals(barserviceinfo, rhs.barserviceinfo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(action)
		.append(reasonCode)
		.append(barserviceinfo)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SBarServiceReq";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBarServiceReq.class, "ACTION", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarServiceReq.class, "REASON_CODE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBarServiceReq.class, "BARSERVICEINFO", 2, SBarServiceInfo.class));
}

}