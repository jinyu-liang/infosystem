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
@XmlType(propOrder={"threshold","notifyType","notifyAddr","notifyPhoneId"})
@Sdl(module="MImsSyncDef")
public class SThreshold extends CsdlStructObject implements IComplexEntity{

	public final static String COL_THRESHOLD = "THRESHOLD";
	public final static String COL_NOTIFY_TYPE = "NOTIFY_TYPE";
	public final static String COL_NOTIFY_ADDR = "NOTIFY_ADDR";
	public final static String COL_NOTIFY_PHONE_ID = "NOTIFY_PHONE_ID";
	public final static int IDX_THRESHOLD = 0;
	public final static int IDX_NOTIFY_TYPE = 1;
	public final static int IDX_NOTIFY_ADDR = 2;
	public final static int IDX_NOTIFY_PHONE_ID = 3;

	/**
	 * 
	 */
	@XmlElement(name="threshold")
	@Sdl
	private long threshold;

	/**
	 * 
	 */
	@XmlElement(name="notifyType")
	@Sdl
	private short notifyType;

	/**
	 * 
	 */
	@XmlElement(name="notifyAddr")
	@Sdl
	private String notifyAddr;

	/**
	 * 
	 */
	@XmlElement(name="notifyPhoneId")
	@Sdl
	private String notifyPhoneId;

	public void setThreshold(long obj){
		this.threshold = obj;
		onFieldSet(0, obj);
	}

	public long getThreshold(){
		return threshold;
	}

	public void setNotifyType(short obj){
		this.notifyType = obj;
		onFieldSet(1, obj);
	}

	public short getNotifyType(){
		return notifyType;
	}

	public void setNotifyAddr(String obj){
		this.notifyAddr = obj;
		onFieldSet(2, obj);
	}

	public String getNotifyAddr(){
		return notifyAddr;
	}

	public void setNotifyPhoneId(String obj){
		this.notifyPhoneId = obj;
		onFieldSet(3, obj);
	}

	public String getNotifyPhoneId(){
		return notifyPhoneId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SThreshold(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SThreshold(SThreshold arg0){
		copy(arg0);
	}

	public void copy(final SThreshold rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		threshold = rhs.threshold;
		notifyType = rhs.notifyType;
		notifyAddr = rhs.notifyAddr;
		notifyPhoneId = rhs.notifyPhoneId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SThreshold rhs=(SThreshold)rhs0;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(notifyType, rhs.notifyType)) return false;
		if(!ObjectUtils.equals(notifyAddr, rhs.notifyAddr)) return false;
		if(!ObjectUtils.equals(notifyPhoneId, rhs.notifyPhoneId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(threshold)
		.append(notifyType)
		.append(notifyAddr)
		.append(notifyPhoneId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SThreshold";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SThreshold.class, "THRESHOLD", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SThreshold.class, "NOTIFY_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SThreshold.class, "NOTIFY_ADDR", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SThreshold.class, "NOTIFY_PHONE_ID", 3, String.class));
}

}