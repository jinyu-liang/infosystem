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
@XmlType(propOrder={"offerProdId","doneCode","firstCycleDateTime","cycleUnit","cycleType","validDateTime","expireDateTime","cycleCount"})
@Sdl(module="MXdr")
public class SRateProdBillCycle extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OFFER_PROD_ID = "OFFER_PROD_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_FIRST_CYCLE_DATE_TIME = "FIRST_CYCLE_DATE_TIME";
	public final static String COL_CYCLE_UNIT = "CYCLE_UNIT";
	public final static String COL_CYCLE_TYPE = "CYCLE_TYPE";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_CYCLE_COUNT = "CYCLE_COUNT";
	public final static int IDX_OFFER_PROD_ID = 0;
	public final static int IDX_DONE_CODE = 1;
	public final static int IDX_FIRST_CYCLE_DATE_TIME = 2;
	public final static int IDX_CYCLE_UNIT = 3;
	public final static int IDX_CYCLE_TYPE = 4;
	public final static int IDX_VALID_DATE_TIME = 5;
	public final static int IDX_EXPIRE_DATE_TIME = 6;
	public final static int IDX_CYCLE_COUNT = 7;

	/**
	 * 
	 */
	@XmlElement(name="offerProdId")
	@Sdl
	private int offerProdId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="firstCycleDateTime")
	@Sdl
	private long firstCycleDateTime;

	/**
	 * 
	 */
	@XmlElement(name="cycleUnit")
	@Sdl
	private short cycleUnit;

	/**
	 * 
	 */
	@XmlElement(name="cycleType")
	@Sdl
	private short cycleType;

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
	@XmlElement(name="cycleCount")
	@Sdl
	private int cycleCount;

	public void setOfferProdId(int obj){
		this.offerProdId = obj;
		onFieldSet(0, obj);
	}

	public int getOfferProdId(){
		return offerProdId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(1, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setFirstCycleDateTime(long obj){
		this.firstCycleDateTime = obj;
		onFieldSet(2, obj);
	}

	public long getFirstCycleDateTime(){
		return firstCycleDateTime;
	}

	public void setCycleUnit(short obj){
		this.cycleUnit = obj;
		onFieldSet(3, obj);
	}

	public short getCycleUnit(){
		return cycleUnit;
	}

	public void setCycleType(short obj){
		this.cycleType = obj;
		onFieldSet(4, obj);
	}

	public short getCycleType(){
		return cycleType;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(5, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(6, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setCycleCount(int obj){
		this.cycleCount = obj;
		onFieldSet(7, obj);
	}

	public int getCycleCount(){
		return cycleCount;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRateProdBillCycle(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRateProdBillCycle(SRateProdBillCycle arg0){
		copy(arg0);
	}

	public void copy(final SRateProdBillCycle rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		offerProdId = rhs.offerProdId;
		doneCode = rhs.doneCode;
		firstCycleDateTime = rhs.firstCycleDateTime;
		cycleUnit = rhs.cycleUnit;
		cycleType = rhs.cycleType;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		cycleCount = rhs.cycleCount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRateProdBillCycle rhs=(SRateProdBillCycle)rhs0;
		if(!ObjectUtils.equals(offerProdId, rhs.offerProdId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(firstCycleDateTime, rhs.firstCycleDateTime)) return false;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		if(!ObjectUtils.equals(cycleType, rhs.cycleType)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(cycleCount, rhs.cycleCount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offerProdId)
		.append(doneCode)
		.append(firstCycleDateTime)
		.append(cycleUnit)
		.append(cycleType)
		.append(validDateTime)
		.append(expireDateTime)
		.append(cycleCount)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRateProdBillCycle";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "OFFER_PROD_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "DONE_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "FIRST_CYCLE_DATE_TIME", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "CYCLE_UNIT", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "CYCLE_TYPE", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "VALID_DATE_TIME", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "EXPIRE_DATE_TIME", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdBillCycle.class, "CYCLE_COUNT", 7, int.class));
}

}