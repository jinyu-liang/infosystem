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
import java.util.Date;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"eventId","eventClass","triggerDate","createDate"})
@Sdl(module="MImsTsDef")
public class SEventRecord extends CsdlStructObject implements IComplexEntity{

	public final static String COL_EVENT_ID = "EVENT_ID";
	public final static String COL_EVENT_CLASS = "EVENT_CLASS";
	public final static String COL_TRIGGER_DATE = "TRIGGER_DATE";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static int IDX_EVENT_ID = 0;
	public final static int IDX_EVENT_CLASS = 1;
	public final static int IDX_TRIGGER_DATE = 2;
	public final static int IDX_CREATE_DATE = 3;

	/**
	 * 
	 */
	@XmlElement(name="eventId")
	@Sdl
	private long eventId;

	/**
	 * 
	 */
	@XmlElement(name="eventClass")
	@Sdl
	private int eventClass;

	/**
	 * 
	 */
	@XmlElement(name="triggerDate")
	@Sdl
	private Date triggerDate;

	/**
	 * 
	 */
	@XmlElement(name="createDate")
	@Sdl
	private Date createDate;

	public void setEventId(long obj){
		this.eventId = obj;
		onFieldSet(0, obj);
	}

	public long getEventId(){
		return eventId;
	}

	public void setEventClass(int obj){
		this.eventClass = obj;
		onFieldSet(1, obj);
	}

	public int getEventClass(){
		return eventClass;
	}

	public void setTriggerDate(Date obj){
		this.triggerDate = obj;
		onFieldSet(2, obj);
	}

	public Date getTriggerDate(){
		return triggerDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(3, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SEventRecord(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SEventRecord(SEventRecord arg0){
		copy(arg0);
	}

	public void copy(final SEventRecord rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		eventId = rhs.eventId;
		eventClass = rhs.eventClass;
		triggerDate = rhs.triggerDate;
		createDate = rhs.createDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SEventRecord rhs=(SEventRecord)rhs0;
		if(!ObjectUtils.equals(eventId, rhs.eventId)) return false;
		if(!ObjectUtils.equals(eventClass, rhs.eventClass)) return false;
		if(!ObjectUtils.equals(triggerDate, rhs.triggerDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(eventId)
		.append(eventClass)
		.append(triggerDate)
		.append(createDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SEventRecord";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SEventRecord.class, "EVENT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventRecord.class, "EVENT_CLASS", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventRecord.class, "TRIGGER_DATE", 2, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SEventRecord.class, "CREATE_DATE", 3, Date.class));
}

}