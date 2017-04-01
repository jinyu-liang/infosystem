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
@XmlType(propOrder={"changeItemId","itemType","fee","reduceFee","measureId","standardProductId"})
@Sdl(module="MXdr")
public class SCharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CHANGE_ITEM_ID = "CHANGE_ITEM_ID";
	public final static String COL_ITEM_TYPE = "ITEM_TYPE";
	public final static String COL_FEE = "FEE";
	public final static String COL_REDUCE_FEE = "REDUCE_FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_STANDARD_PRODUCT_ID = "STANDARD_PRODUCT_ID";
	public final static int IDX_CHANGE_ITEM_ID = 0;
	public final static int IDX_ITEM_TYPE = 1;
	public final static int IDX_FEE = 2;
	public final static int IDX_REDUCE_FEE = 3;
	public final static int IDX_MEASURE_ID = 4;
	public final static int IDX_STANDARD_PRODUCT_ID = 5;

	/**
	 * 
	 */
	@XmlElement(name="changeItemId")
	@Sdl
	private int changeItemId;

	/**
	 * 
	 */
	@XmlElement(name="itemType")
	@Sdl
	private int itemType;

	/**
	 * 
	 */
	@XmlElement(name="fee")
	@Sdl
	private long fee;

	/**
	 * 
	 */
	@XmlElement(name="reduceFee")
	@Sdl
	private long reduceFee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="standardProductId")
	@Sdl
	private long standardProductId;

	public void setChangeItemId(int obj){
		this.changeItemId = obj;
		onFieldSet(0, obj);
	}

	public int getChangeItemId(){
		return changeItemId;
	}

	public void setItemType(int obj){
		this.itemType = obj;
		onFieldSet(1, obj);
	}

	public int getItemType(){
		return itemType;
	}

	public void setFee(long obj){
		this.fee = obj;
		onFieldSet(2, obj);
	}

	public long getFee(){
		return fee;
	}

	public void setReduceFee(long obj){
		this.reduceFee = obj;
		onFieldSet(3, obj);
	}

	public long getReduceFee(){
		return reduceFee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(4, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setStandardProductId(long obj){
		this.standardProductId = obj;
		onFieldSet(5, obj);
	}

	public long getStandardProductId(){
		return standardProductId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCharge(SCharge arg0){
		copy(arg0);
	}

	public void copy(final SCharge rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		changeItemId = rhs.changeItemId;
		itemType = rhs.itemType;
		fee = rhs.fee;
		reduceFee = rhs.reduceFee;
		measureId = rhs.measureId;
		standardProductId = rhs.standardProductId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCharge rhs=(SCharge)rhs0;
		if(!ObjectUtils.equals(changeItemId, rhs.changeItemId)) return false;
		if(!ObjectUtils.equals(itemType, rhs.itemType)) return false;
		if(!ObjectUtils.equals(fee, rhs.fee)) return false;
		if(!ObjectUtils.equals(reduceFee, rhs.reduceFee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(standardProductId, rhs.standardProductId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(changeItemId)
		.append(itemType)
		.append(fee)
		.append(reduceFee)
		.append(measureId)
		.append(standardProductId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SCharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCharge.class, "CHANGE_ITEM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCharge.class, "ITEM_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCharge.class, "FEE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCharge.class, "REDUCE_FEE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCharge.class, "MEASURE_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCharge.class, "STANDARD_PRODUCT_ID", 5, long.class));
}

}