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
@XmlType(propOrder={"ruleId","numerator","denominator","threshold","payforThreshold","alarmId","bePayforThreshold","beAlarmId","doneCode","measureId"})
@Sdl(module="MXdr")
public class SSubPayforRule extends CsdlStructObject implements IComplexEntity{

	public final static String COL_RULE_ID = "RULE_ID";
	public final static String COL_NUMERATOR = "NUMERATOR";
	public final static String COL_DENOMINATOR = "DENOMINATOR";
	public final static String COL_THRESHOLD = "THRESHOLD";
	public final static String COL_PAYFOR_THRESHOLD = "PAYFOR_THRESHOLD";
	public final static String COL_ALARM_ID = "ALARM_ID";
	public final static String COL_BE_PAYFOR_THRESHOLD = "BE_PAYFOR_THRESHOLD";
	public final static String COL_BE_ALARM_ID = "BE_ALARM_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_RULE_ID = 0;
	public final static int IDX_NUMERATOR = 1;
	public final static int IDX_DENOMINATOR = 2;
	public final static int IDX_THRESHOLD = 3;
	public final static int IDX_PAYFOR_THRESHOLD = 4;
	public final static int IDX_ALARM_ID = 5;
	public final static int IDX_BE_PAYFOR_THRESHOLD = 6;
	public final static int IDX_BE_ALARM_ID = 7;
	public final static int IDX_DONE_CODE = 8;
	public final static int IDX_MEASURE_ID = 9;

	/**
	 * 
	 */
	@XmlElement(name="ruleId")
	@Sdl
	private int ruleId;

	/**
	 * 
	 */
	@XmlElement(name="numerator")
	@Sdl
	private int numerator;

	/**
	 * 
	 */
	@XmlElement(name="denominator")
	@Sdl
	private int denominator;

	/**
	 * 
	 */
	@XmlElement(name="threshold")
	@Sdl
	private long threshold;

	/**
	 * 
	 */
	@XmlElement(name="payforThreshold")
	@Sdl
	private long payforThreshold;

	/**
	 * 
	 */
	@XmlElement(name="alarmId")
	@Sdl
	private int alarmId;

	/**
	 * 
	 */
	@XmlElement(name="bePayforThreshold")
	@Sdl
	private long bePayforThreshold;

	/**
	 * 
	 */
	@XmlElement(name="beAlarmId")
	@Sdl
	private int beAlarmId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setRuleId(int obj){
		this.ruleId = obj;
		onFieldSet(0, obj);
	}

	public int getRuleId(){
		return ruleId;
	}

	public void setNumerator(int obj){
		this.numerator = obj;
		onFieldSet(1, obj);
	}

	public int getNumerator(){
		return numerator;
	}

	public void setDenominator(int obj){
		this.denominator = obj;
		onFieldSet(2, obj);
	}

	public int getDenominator(){
		return denominator;
	}

	public void setThreshold(long obj){
		this.threshold = obj;
		onFieldSet(3, obj);
	}

	public long getThreshold(){
		return threshold;
	}

	public void setPayforThreshold(long obj){
		this.payforThreshold = obj;
		onFieldSet(4, obj);
	}

	public long getPayforThreshold(){
		return payforThreshold;
	}

	public void setAlarmId(int obj){
		this.alarmId = obj;
		onFieldSet(5, obj);
	}

	public int getAlarmId(){
		return alarmId;
	}

	public void setBePayforThreshold(long obj){
		this.bePayforThreshold = obj;
		onFieldSet(6, obj);
	}

	public long getBePayforThreshold(){
		return bePayforThreshold;
	}

	public void setBeAlarmId(int obj){
		this.beAlarmId = obj;
		onFieldSet(7, obj);
	}

	public int getBeAlarmId(){
		return beAlarmId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(8, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(9, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSubPayforRule(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 10; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSubPayforRule(SSubPayforRule arg0){
		copy(arg0);
	}

	public void copy(final SSubPayforRule rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		ruleId = rhs.ruleId;
		numerator = rhs.numerator;
		denominator = rhs.denominator;
		threshold = rhs.threshold;
		payforThreshold = rhs.payforThreshold;
		alarmId = rhs.alarmId;
		bePayforThreshold = rhs.bePayforThreshold;
		beAlarmId = rhs.beAlarmId;
		doneCode = rhs.doneCode;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSubPayforRule rhs=(SSubPayforRule)rhs0;
		if(!ObjectUtils.equals(ruleId, rhs.ruleId)) return false;
		if(!ObjectUtils.equals(numerator, rhs.numerator)) return false;
		if(!ObjectUtils.equals(denominator, rhs.denominator)) return false;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(payforThreshold, rhs.payforThreshold)) return false;
		if(!ObjectUtils.equals(alarmId, rhs.alarmId)) return false;
		if(!ObjectUtils.equals(bePayforThreshold, rhs.bePayforThreshold)) return false;
		if(!ObjectUtils.equals(beAlarmId, rhs.beAlarmId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(ruleId)
		.append(numerator)
		.append(denominator)
		.append(threshold)
		.append(payforThreshold)
		.append(alarmId)
		.append(bePayforThreshold)
		.append(beAlarmId)
		.append(doneCode)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(10);
public static final long BITS_ALL_MARKER = 0x200L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSubPayforRule";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "RULE_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "NUMERATOR", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "DENOMINATOR", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "THRESHOLD", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "PAYFOR_THRESHOLD", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "ALARM_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "BE_PAYFOR_THRESHOLD", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "BE_ALARM_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "DONE_CODE", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubPayforRule.class, "MEASURE_ID", 9, int.class));
}

}