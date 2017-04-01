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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"alarmIdMap"})
@Sdl(module="MXdr")
public class SAnalyseStateInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ALARM_ID_MAP = "ALARM_ID_MAP";
	public final static int IDX_ALARM_ID_MAP = 0;

	/**
	 * 
	 */
	@XmlElement(name="alarmIdMap")
	@Sdl
	private Map<Integer,Integer> alarmIdMap;

	public void setAlarmIdMap(Map<Integer,Integer> obj){
		this.alarmIdMap = obj;
		onFieldSet(0, obj);
	}

	public Map<Integer,Integer> getAlarmIdMap(){
		return alarmIdMap;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAnalyseStateInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 1; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAnalyseStateInfo(SAnalyseStateInfo arg0){
		copy(arg0);
	}

	public void copy(final SAnalyseStateInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		alarmIdMap = rhs.alarmIdMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAnalyseStateInfo rhs=(SAnalyseStateInfo)rhs0;
		if(!ObjectUtils.equals(alarmIdMap, rhs.alarmIdMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(alarmIdMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(1);
public static final long BITS_ALL_MARKER = 0x1L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAnalyseStateInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAnalyseStateInfo.class, "ALARM_ID_MAP", 0, Map.class));
}

}