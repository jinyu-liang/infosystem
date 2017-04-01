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
@XmlType(propOrder={"alarmId","alarmCode","alarmInfo","urlAddress"})
@Sdl(module="MXdr")
public class SAnalyseAlarm extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ALARM_ID = "ALARM_ID";
	public final static String COL_ALARM_CODE = "ALARM_CODE";
	public final static String COL_ALARM_INFO = "ALARM_INFO";
	public final static String COL_URL_ADDRESS = "URL_ADDRESS";
	public final static int IDX_ALARM_ID = 0;
	public final static int IDX_ALARM_CODE = 1;
	public final static int IDX_ALARM_INFO = 2;
	public final static int IDX_URL_ADDRESS = 3;

	/**
	 * 
	 */
	@XmlElement(name="alarmId")
	@Sdl
	private int alarmId;

	/**
	 * 
	 */
	@XmlElement(name="alarmCode")
	@Sdl
	private int alarmCode;

	/**
	 * 
	 */
	@XmlElement(name="alarmInfo")
	@Sdl
	private String alarmInfo;

	/**
	 * 
	 */
	@XmlElement(name="urlAddress")
	@Sdl
	private String urlAddress;

	public void setAlarmId(int obj){
		this.alarmId = obj;
		onFieldSet(0, obj);
	}

	public int getAlarmId(){
		return alarmId;
	}

	public void setAlarmCode(int obj){
		this.alarmCode = obj;
		onFieldSet(1, obj);
	}

	public int getAlarmCode(){
		return alarmCode;
	}

	public void setAlarmInfo(String obj){
		this.alarmInfo = obj;
		onFieldSet(2, obj);
	}

	public String getAlarmInfo(){
		return alarmInfo;
	}

	public void setUrlAddress(String obj){
		this.urlAddress = obj;
		onFieldSet(3, obj);
	}

	public String getUrlAddress(){
		return urlAddress;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAnalyseAlarm(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAnalyseAlarm(SAnalyseAlarm arg0){
		copy(arg0);
	}

	public void copy(final SAnalyseAlarm rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		alarmId = rhs.alarmId;
		alarmCode = rhs.alarmCode;
		alarmInfo = rhs.alarmInfo;
		urlAddress = rhs.urlAddress;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAnalyseAlarm rhs=(SAnalyseAlarm)rhs0;
		if(!ObjectUtils.equals(alarmId, rhs.alarmId)) return false;
		if(!ObjectUtils.equals(alarmCode, rhs.alarmCode)) return false;
		if(!ObjectUtils.equals(alarmInfo, rhs.alarmInfo)) return false;
		if(!ObjectUtils.equals(urlAddress, rhs.urlAddress)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(alarmId)
		.append(alarmCode)
		.append(alarmInfo)
		.append(urlAddress)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAnalyseAlarm";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAnalyseAlarm.class, "ALARM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAnalyseAlarm.class, "ALARM_CODE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAnalyseAlarm.class, "ALARM_INFO", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAnalyseAlarm.class, "URL_ADDRESS", 3, String.class));
}

}