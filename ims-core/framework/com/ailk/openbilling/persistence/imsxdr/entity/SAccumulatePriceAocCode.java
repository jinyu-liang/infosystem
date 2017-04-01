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
@XmlType(propOrder={"aoc","alarmType","priceId","doneCode","validDate","expireDate","salarmAddup"})
@Sdl(module="MXdr")
public class SAccumulatePriceAocCode extends CsdlStructObject implements IComplexEntity{

	public final static String COL_AOC = "AOC";
	public final static String COL_ALARM_TYPE = "ALARM_TYPE";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_SALARM_ADDUP = "SALARM_ADDUP";
	public final static int IDX_AOC = 0;
	public final static int IDX_ALARM_TYPE = 1;
	public final static int IDX_PRICE_ID = 2;
	public final static int IDX_DONE_CODE = 3;
	public final static int IDX_VALID_DATE = 4;
	public final static int IDX_EXPIRE_DATE = 5;
	public final static int IDX_SALARM_ADDUP = 6;

	/**
	 * 
	 */
	@XmlElement(name="aoc")
	@Sdl
	private SAocCode aoc;

	/**
	 * 
	 */
	@XmlElement(name="salarmAddup")
	@Sdl
	private List<SAlarmAddup> salarmAddup;

	/**
	 * 
	 */
	@XmlElement(name="alarmType")
	@Sdl
	private int alarmType;

	/**
	 * 
	 */
	@XmlElement(name="priceId")
	@Sdl
	private int priceId;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private long validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private long expireDate;

	public void setAoc(SAocCode obj){
		this.aoc = obj;
		onFieldSet(0, obj);
	}

	public SAocCode getAoc(){
		return aoc;
	}

	public void setSalarmAddup(List<SAlarmAddup> obj){
		this.salarmAddup = obj;
		onFieldSet(6, obj);
	}

	public List<SAlarmAddup> getSalarmAddup(){
		return salarmAddup;
	}

	public void setAlarmType(int obj){
		this.alarmType = obj;
		onFieldSet(1, obj);
	}

	public int getAlarmType(){
		return alarmType;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(2, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(3, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(4, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(5, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAccumulatePriceAocCode(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAccumulatePriceAocCode(SAccumulatePriceAocCode arg0){
		copy(arg0);
	}

	public void copy(final SAccumulatePriceAocCode rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		aoc = rhs.aoc;
		alarmType = rhs.alarmType;
		priceId = rhs.priceId;
		doneCode = rhs.doneCode;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		salarmAddup = rhs.salarmAddup;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAccumulatePriceAocCode rhs=(SAccumulatePriceAocCode)rhs0;
		if(!ObjectUtils.equals(aoc, rhs.aoc)) return false;
		if(!ObjectUtils.equals(alarmType, rhs.alarmType)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(salarmAddup, rhs.salarmAddup)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(aoc)
		.append(alarmType)
		.append(priceId)
		.append(doneCode)
		.append(validDate)
		.append(expireDate)
		.append(salarmAddup)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAccumulatePriceAocCode";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "AOC", 0, SAocCode.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "ALARM_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "PRICE_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "DONE_CODE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "VALID_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "EXPIRE_DATE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAccumulatePriceAocCode.class, "SALARM_ADDUP", 6, List.class));
}

}