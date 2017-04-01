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
@XmlType(propOrder={"alarmRuleId","priority","validDateTime","expireDateTime","doneCode"})
@Sdl(module="MXdr")
public class SPropertyValMin extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ALARM_RULE_ID = "ALARM_RULE_ID";
	public final static String COL_PRIORITY = "PRIORITY";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static int IDX_ALARM_RULE_ID = 0;
	public final static int IDX_PRIORITY = 1;
	public final static int IDX_VALID_DATE_TIME = 2;
	public final static int IDX_EXPIRE_DATE_TIME = 3;
	public final static int IDX_DONE_CODE = 4;

	/**
	 * 
	 */
	@XmlElement(name="alarmRuleId")
	@Sdl
	private int alarmRuleId;

	/**
	 * 
	 */
	@XmlElement(name="priority")
	@Sdl
	private int priority;

	/**
	 * 
	 */
	@XmlElement(name="validDateTime")
	@Sdl
	private long validDateTime;

	/**
	 * 
	 */
	@XmlElement(name="expireDateTime")
	@Sdl
	private long expireDateTime;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	public void setAlarmRuleId(int obj){
		this.alarmRuleId = obj;
		onFieldSet(0, obj);
	}

	public int getAlarmRuleId(){
		return alarmRuleId;
	}

	public void setPriority(int obj){
		this.priority = obj;
		onFieldSet(1, obj);
	}

	public int getPriority(){
		return priority;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(2, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(3, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(4, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPropertyValMin(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPropertyValMin(SPropertyValMin arg0){
		copy(arg0);
	}

	public void copy(final SPropertyValMin rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		alarmRuleId = rhs.alarmRuleId;
		priority = rhs.priority;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		doneCode = rhs.doneCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPropertyValMin rhs=(SPropertyValMin)rhs0;
		if(!ObjectUtils.equals(alarmRuleId, rhs.alarmRuleId)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(alarmRuleId)
		.append(priority)
		.append(validDateTime)
		.append(expireDateTime)
		.append(doneCode)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPropertyValMin";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPropertyValMin.class, "ALARM_RULE_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPropertyValMin.class, "PRIORITY", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPropertyValMin.class, "VALID_DATE_TIME", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPropertyValMin.class, "EXPIRE_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPropertyValMin.class, "DONE_CODE", 4, long.class));
}

}