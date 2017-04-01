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
@XmlType(propOrder={"measureId","beginRes","amountRes"})
@Sdl(module="MXdr")
public class SRatingRes extends CsdlStructObject implements IComplexEntity{

	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_BEGIN_RES = "BEGIN_RES";
	public final static String COL_AMOUNT_RES = "AMOUNT_RES";
	public final static int IDX_MEASURE_ID = 0;
	public final static int IDX_BEGIN_RES = 1;
	public final static int IDX_AMOUNT_RES = 2;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="beginRes")
	@Sdl
	private long beginRes;

	/**
	 * 
	 */
	@XmlElement(name="amountRes")
	@Sdl
	private long amountRes;

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(0, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setBeginRes(long obj){
		this.beginRes = obj;
		onFieldSet(1, obj);
	}

	public long getBeginRes(){
		return beginRes;
	}

	public void setAmountRes(long obj){
		this.amountRes = obj;
		onFieldSet(2, obj);
	}

	public long getAmountRes(){
		return amountRes;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRatingRes(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRatingRes(SRatingRes arg0){
		copy(arg0);
	}

	public void copy(final SRatingRes rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		measureId = rhs.measureId;
		beginRes = rhs.beginRes;
		amountRes = rhs.amountRes;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRatingRes rhs=(SRatingRes)rhs0;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(beginRes, rhs.beginRes)) return false;
		if(!ObjectUtils.equals(amountRes, rhs.amountRes)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(measureId)
		.append(beginRes)
		.append(amountRes)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRatingRes";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRatingRes.class, "MEASURE_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRatingRes.class, "BEGIN_RES", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRatingRes.class, "AMOUNT_RES", 2, long.class));
}

}