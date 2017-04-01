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
@XmlType(propOrder={"alarmCode","url"})
@Sdl(module="MXdr")
public class SSlaAlert extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ALARM_CODE = "ALARM_CODE";
	public final static String COL_URL = "URL";
	public final static int IDX_ALARM_CODE = 0;
	public final static int IDX_URL = 1;

	/**
	 * 
	 */
	@XmlElement(name="alarmCode")
	@Sdl
	private SAocCode alarmCode;

	/**
	 * 
	 */
	@XmlElement(name="url")
	@Sdl
	private SUrlAddr url;

	public void setAlarmCode(SAocCode obj){
		this.alarmCode = obj;
		onFieldSet(0, obj);
	}

	public SAocCode getAlarmCode(){
		return alarmCode;
	}

	public void setUrl(SUrlAddr obj){
		this.url = obj;
		onFieldSet(1, obj);
	}

	public SUrlAddr getUrl(){
		return url;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSlaAlert(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSlaAlert(SSlaAlert arg0){
		copy(arg0);
	}

	public void copy(final SSlaAlert rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		alarmCode = rhs.alarmCode;
		url = rhs.url;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSlaAlert rhs=(SSlaAlert)rhs0;
		if(!ObjectUtils.equals(alarmCode, rhs.alarmCode)) return false;
		if(!ObjectUtils.equals(url, rhs.url)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(alarmCode)
		.append(url)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSlaAlert";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSlaAlert.class, "ALARM_CODE", 0, SAocCode.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSlaAlert.class, "URL", 1, SUrlAddr.class));
}

}