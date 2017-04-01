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
@XmlType(propOrder={"tariff","tariffSeqId","qosReqReliability","qosReqDelay","qosReqPrecedence","qosReqPeak","qosReqMean","qosNegReliability","qosNegDelay","qosNegPrecedence","qosNegPeak","qosNegMean","dataFlowUp","dataFlowDown","beginTime","tariffDuration"})
@Sdl(module="MXdr")
public class STarffQcs extends CsdlStructObject implements IComplexEntity{

	public final static String COL_TARIFF = "TARIFF";
	public final static String COL_TARIFF_SEQ_ID = "TARIFF_SEQ_ID";
	public final static String COL_QOS_REQ_RELIABILITY = "QOS_REQ_RELIABILITY";
	public final static String COL_QOS_REQ_DELAY = "QOS_REQ_DELAY";
	public final static String COL_QOS_REQ_PRECEDENCE = "QOS_REQ_PRECEDENCE";
	public final static String COL_QOS_REQ_PEAK = "QOS_REQ_PEAK";
	public final static String COL_QOS_REQ_MEAN = "QOS_REQ_MEAN";
	public final static String COL_QOS_NEG_RELIABILITY = "QOS_NEG_RELIABILITY";
	public final static String COL_QOS_NEG_DELAY = "QOS_NEG_DELAY";
	public final static String COL_QOS_NEG_PRECEDENCE = "QOS_NEG_PRECEDENCE";
	public final static String COL_QOS_NEG_PEAK = "QOS_NEG_PEAK";
	public final static String COL_QOS_NEG_MEAN = "QOS_NEG_MEAN";
	public final static String COL_DATA_FLOW_UP = "DATA_FLOW_UP";
	public final static String COL_DATA_FLOW_DOWN = "DATA_FLOW_DOWN";
	public final static String COL_BEGIN_TIME = "BEGIN_TIME";
	public final static String COL_TARIFF_DURATION = "TARIFF_DURATION";
	public final static int IDX_TARIFF = 0;
	public final static int IDX_TARIFF_SEQ_ID = 1;
	public final static int IDX_QOS_REQ_RELIABILITY = 2;
	public final static int IDX_QOS_REQ_DELAY = 3;
	public final static int IDX_QOS_REQ_PRECEDENCE = 4;
	public final static int IDX_QOS_REQ_PEAK = 5;
	public final static int IDX_QOS_REQ_MEAN = 6;
	public final static int IDX_QOS_NEG_RELIABILITY = 7;
	public final static int IDX_QOS_NEG_DELAY = 8;
	public final static int IDX_QOS_NEG_PRECEDENCE = 9;
	public final static int IDX_QOS_NEG_PEAK = 10;
	public final static int IDX_QOS_NEG_MEAN = 11;
	public final static int IDX_DATA_FLOW_UP = 12;
	public final static int IDX_DATA_FLOW_DOWN = 13;
	public final static int IDX_BEGIN_TIME = 14;
	public final static int IDX_TARIFF_DURATION = 15;

	/**
	 * 
	 */
	@XmlElement(name="tariff")
	@Sdl
	private String tariff;

	/**
	 * 
	 */
	@XmlElement(name="tariffSeqId")
	@Sdl
	private String tariffSeqId;

	/**
	 * 
	 */
	@XmlElement(name="qosReqReliability")
	@Sdl
	private short qosReqReliability;

	/**
	 * 
	 */
	@XmlElement(name="qosReqDelay")
	@Sdl
	private short qosReqDelay;

	/**
	 * 
	 */
	@XmlElement(name="qosReqPrecedence")
	@Sdl
	private short qosReqPrecedence;

	/**
	 * 
	 */
	@XmlElement(name="qosReqPeak")
	@Sdl
	private short qosReqPeak;

	/**
	 * 
	 */
	@XmlElement(name="qosReqMean")
	@Sdl
	private short qosReqMean;

	/**
	 * 
	 */
	@XmlElement(name="qosNegReliability")
	@Sdl
	private short qosNegReliability;

	/**
	 * 
	 */
	@XmlElement(name="qosNegDelay")
	@Sdl
	private short qosNegDelay;

	/**
	 * 
	 */
	@XmlElement(name="qosNegPrecedence")
	@Sdl
	private short qosNegPrecedence;

	/**
	 * 
	 */
	@XmlElement(name="qosNegPeak")
	@Sdl
	private short qosNegPeak;

	/**
	 * 
	 */
	@XmlElement(name="qosNegMean")
	@Sdl
	private short qosNegMean;

	/**
	 * 
	 */
	@XmlElement(name="dataFlowUp")
	@Sdl
	private long dataFlowUp;

	/**
	 * 
	 */
	@XmlElement(name="dataFlowDown")
	@Sdl
	private long dataFlowDown;

	/**
	 * 
	 */
	@XmlElement(name="beginTime")
	@Sdl
	private long beginTime;

	/**
	 * 
	 */
	@XmlElement(name="tariffDuration")
	@Sdl
	private long tariffDuration;

	public void setTariff(String obj){
		this.tariff = obj;
		onFieldSet(0, obj);
	}

	public String getTariff(){
		return tariff;
	}

	public void setTariffSeqId(String obj){
		this.tariffSeqId = obj;
		onFieldSet(1, obj);
	}

	public String getTariffSeqId(){
		return tariffSeqId;
	}

	public void setQosReqReliability(short obj){
		this.qosReqReliability = obj;
		onFieldSet(2, obj);
	}

	public short getQosReqReliability(){
		return qosReqReliability;
	}

	public void setQosReqDelay(short obj){
		this.qosReqDelay = obj;
		onFieldSet(3, obj);
	}

	public short getQosReqDelay(){
		return qosReqDelay;
	}

	public void setQosReqPrecedence(short obj){
		this.qosReqPrecedence = obj;
		onFieldSet(4, obj);
	}

	public short getQosReqPrecedence(){
		return qosReqPrecedence;
	}

	public void setQosReqPeak(short obj){
		this.qosReqPeak = obj;
		onFieldSet(5, obj);
	}

	public short getQosReqPeak(){
		return qosReqPeak;
	}

	public void setQosReqMean(short obj){
		this.qosReqMean = obj;
		onFieldSet(6, obj);
	}

	public short getQosReqMean(){
		return qosReqMean;
	}

	public void setQosNegReliability(short obj){
		this.qosNegReliability = obj;
		onFieldSet(7, obj);
	}

	public short getQosNegReliability(){
		return qosNegReliability;
	}

	public void setQosNegDelay(short obj){
		this.qosNegDelay = obj;
		onFieldSet(8, obj);
	}

	public short getQosNegDelay(){
		return qosNegDelay;
	}

	public void setQosNegPrecedence(short obj){
		this.qosNegPrecedence = obj;
		onFieldSet(9, obj);
	}

	public short getQosNegPrecedence(){
		return qosNegPrecedence;
	}

	public void setQosNegPeak(short obj){
		this.qosNegPeak = obj;
		onFieldSet(10, obj);
	}

	public short getQosNegPeak(){
		return qosNegPeak;
	}

	public void setQosNegMean(short obj){
		this.qosNegMean = obj;
		onFieldSet(11, obj);
	}

	public short getQosNegMean(){
		return qosNegMean;
	}

	public void setDataFlowUp(long obj){
		this.dataFlowUp = obj;
		onFieldSet(12, obj);
	}

	public long getDataFlowUp(){
		return dataFlowUp;
	}

	public void setDataFlowDown(long obj){
		this.dataFlowDown = obj;
		onFieldSet(13, obj);
	}

	public long getDataFlowDown(){
		return dataFlowDown;
	}

	public void setBeginTime(long obj){
		this.beginTime = obj;
		onFieldSet(14, obj);
	}

	public long getBeginTime(){
		return beginTime;
	}

	public void setTariffDuration(long obj){
		this.tariffDuration = obj;
		onFieldSet(15, obj);
	}

	public long getTariffDuration(){
		return tariffDuration;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public STarffQcs(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 16; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public STarffQcs(STarffQcs arg0){
		copy(arg0);
	}

	public void copy(final STarffQcs rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		tariff = rhs.tariff;
		tariffSeqId = rhs.tariffSeqId;
		qosReqReliability = rhs.qosReqReliability;
		qosReqDelay = rhs.qosReqDelay;
		qosReqPrecedence = rhs.qosReqPrecedence;
		qosReqPeak = rhs.qosReqPeak;
		qosReqMean = rhs.qosReqMean;
		qosNegReliability = rhs.qosNegReliability;
		qosNegDelay = rhs.qosNegDelay;
		qosNegPrecedence = rhs.qosNegPrecedence;
		qosNegPeak = rhs.qosNegPeak;
		qosNegMean = rhs.qosNegMean;
		dataFlowUp = rhs.dataFlowUp;
		dataFlowDown = rhs.dataFlowDown;
		beginTime = rhs.beginTime;
		tariffDuration = rhs.tariffDuration;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STarffQcs rhs=(STarffQcs)rhs0;
		if(!ObjectUtils.equals(tariff, rhs.tariff)) return false;
		if(!ObjectUtils.equals(tariffSeqId, rhs.tariffSeqId)) return false;
		if(!ObjectUtils.equals(qosReqReliability, rhs.qosReqReliability)) return false;
		if(!ObjectUtils.equals(qosReqDelay, rhs.qosReqDelay)) return false;
		if(!ObjectUtils.equals(qosReqPrecedence, rhs.qosReqPrecedence)) return false;
		if(!ObjectUtils.equals(qosReqPeak, rhs.qosReqPeak)) return false;
		if(!ObjectUtils.equals(qosReqMean, rhs.qosReqMean)) return false;
		if(!ObjectUtils.equals(qosNegReliability, rhs.qosNegReliability)) return false;
		if(!ObjectUtils.equals(qosNegDelay, rhs.qosNegDelay)) return false;
		if(!ObjectUtils.equals(qosNegPrecedence, rhs.qosNegPrecedence)) return false;
		if(!ObjectUtils.equals(qosNegPeak, rhs.qosNegPeak)) return false;
		if(!ObjectUtils.equals(qosNegMean, rhs.qosNegMean)) return false;
		if(!ObjectUtils.equals(dataFlowUp, rhs.dataFlowUp)) return false;
		if(!ObjectUtils.equals(dataFlowDown, rhs.dataFlowDown)) return false;
		if(!ObjectUtils.equals(beginTime, rhs.beginTime)) return false;
		if(!ObjectUtils.equals(tariffDuration, rhs.tariffDuration)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(tariff)
		.append(tariffSeqId)
		.append(qosReqReliability)
		.append(qosReqDelay)
		.append(qosReqPrecedence)
		.append(qosReqPeak)
		.append(qosReqMean)
		.append(qosNegReliability)
		.append(qosNegDelay)
		.append(qosNegPrecedence)
		.append(qosNegPeak)
		.append(qosNegMean)
		.append(dataFlowUp)
		.append(dataFlowDown)
		.append(beginTime)
		.append(tariffDuration)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(16);
public static final long BITS_ALL_MARKER = 0x8000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.STarffQcs";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "TARIFF", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "TARIFF_SEQ_ID", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_REQ_RELIABILITY", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_REQ_DELAY", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_REQ_PRECEDENCE", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_REQ_PEAK", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_REQ_MEAN", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_NEG_RELIABILITY", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_NEG_DELAY", 8, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_NEG_PRECEDENCE", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_NEG_PEAK", 10, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "QOS_NEG_MEAN", 11, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "DATA_FLOW_UP", 12, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "DATA_FLOW_DOWN", 13, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "BEGIN_TIME", 14, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(STarffQcs.class, "TARIFF_DURATION", 15, long.class));
}

}