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
@XmlType(propOrder={"offerProdId","doneCode","measureType"})
@Sdl(module="MXdr")
public class SRateProdRatingRes extends CsdlStructObject implements IComplexEntity{

	public final static String COL_OFFER_PROD_ID = "OFFER_PROD_ID";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_MEASURE_TYPE = "MEASURE_TYPE";
	public final static int IDX_OFFER_PROD_ID = 0;
	public final static int IDX_DONE_CODE = 1;
	public final static int IDX_MEASURE_TYPE = 2;

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
	@XmlElement(name="measureType")
	@Sdl
	private int measureType;

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

	public void setMeasureType(int obj){
		this.measureType = obj;
		onFieldSet(2, obj);
	}

	public int getMeasureType(){
		return measureType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRateProdRatingRes(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRateProdRatingRes(SRateProdRatingRes arg0){
		copy(arg0);
	}

	public void copy(final SRateProdRatingRes rhs){
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
		measureType = rhs.measureType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRateProdRatingRes rhs=(SRateProdRatingRes)rhs0;
		if(!ObjectUtils.equals(offerProdId, rhs.offerProdId)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(measureType, rhs.measureType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offerProdId)
		.append(doneCode)
		.append(measureType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRateProdRatingRes";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdRatingRes.class, "OFFER_PROD_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdRatingRes.class, "DONE_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRateProdRatingRes.class, "MEASURE_TYPE", 2, int.class));
}

}