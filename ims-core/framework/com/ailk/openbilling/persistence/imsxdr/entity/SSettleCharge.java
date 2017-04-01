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
@XmlType(propOrder={"settleItemId","fee","measureId"})
@Sdl(module="MXdr")
public class SSettleCharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SETTLE_ITEM_ID = "SETTLE_ITEM_ID";
	public final static String COL_FEE = "FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_SETTLE_ITEM_ID = 0;
	public final static int IDX_FEE = 1;
	public final static int IDX_MEASURE_ID = 2;

	/**
	 * 
	 */
	@XmlElement(name="settleItemId")
	@Sdl
	private int settleItemId;

	/**
	 * 
	 */
	@XmlElement(name="fee")
	@Sdl
	private long fee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setSettleItemId(int obj){
		this.settleItemId = obj;
		onFieldSet(0, obj);
	}

	public int getSettleItemId(){
		return settleItemId;
	}

	public void setFee(long obj){
		this.fee = obj;
		onFieldSet(1, obj);
	}

	public long getFee(){
		return fee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(2, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSettleCharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSettleCharge(SSettleCharge arg0){
		copy(arg0);
	}

	public void copy(final SSettleCharge rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		settleItemId = rhs.settleItemId;
		fee = rhs.fee;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSettleCharge rhs=(SSettleCharge)rhs0;
		if(!ObjectUtils.equals(settleItemId, rhs.settleItemId)) return false;
		if(!ObjectUtils.equals(fee, rhs.fee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(settleItemId)
		.append(fee)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSettleCharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSettleCharge.class, "SETTLE_ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSettleCharge.class, "FEE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSettleCharge.class, "MEASURE_ID", 2, int.class));
}

}