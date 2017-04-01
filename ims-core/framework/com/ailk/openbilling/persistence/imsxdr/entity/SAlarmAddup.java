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
@XmlType(propOrder={"accumulateThreshold","accumulateValue","itemCode","measureId"})
@Sdl(module="MXdr")
public class SAlarmAddup extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCUMULATE_THRESHOLD = "ACCUMULATE_THRESHOLD";
	public final static String COL_ACCUMULATE_VALUE = "ACCUMULATE_VALUE";
	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_ACCUMULATE_THRESHOLD = 0;
	public final static int IDX_ACCUMULATE_VALUE = 1;
	public final static int IDX_ITEM_CODE = 2;
	public final static int IDX_MEASURE_ID = 3;

	/**
	 * 
	 */
	@XmlElement(name="accumulateThreshold")
	@Sdl
	private long accumulateThreshold;

	/**
	 * 
	 */
	@XmlElement(name="accumulateValue")
	@Sdl
	private long accumulateValue;

	/**
	 * 
	 */
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setAccumulateThreshold(long obj){
		this.accumulateThreshold = obj;
		onFieldSet(0, obj);
	}

	public long getAccumulateThreshold(){
		return accumulateThreshold;
	}

	public void setAccumulateValue(long obj){
		this.accumulateValue = obj;
		onFieldSet(1, obj);
	}

	public long getAccumulateValue(){
		return accumulateValue;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(2, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(3, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAlarmAddup(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAlarmAddup(SAlarmAddup arg0){
		copy(arg0);
	}

	public void copy(final SAlarmAddup rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		accumulateThreshold = rhs.accumulateThreshold;
		accumulateValue = rhs.accumulateValue;
		itemCode = rhs.itemCode;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAlarmAddup rhs=(SAlarmAddup)rhs0;
		if(!ObjectUtils.equals(accumulateThreshold, rhs.accumulateThreshold)) return false;
		if(!ObjectUtils.equals(accumulateValue, rhs.accumulateValue)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accumulateThreshold)
		.append(accumulateValue)
		.append(itemCode)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAlarmAddup";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAlarmAddup.class, "ACCUMULATE_THRESHOLD", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAlarmAddup.class, "ACCUMULATE_VALUE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAlarmAddup.class, "ITEM_CODE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAlarmAddup.class, "MEASURE_ID", 3, int.class));
}

}