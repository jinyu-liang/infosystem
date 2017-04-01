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
@XmlType(propOrder={"doneCode","freeresItemId","freeResType","validDateTime","expireDateTime","thisTimeUsed","standardProductId","changeItemId","measureId","reduceFee"})
@Sdl(module="MXdr")
public class SFreeResDiscountFee extends CsdlStructObject implements IComplexEntity{

	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static String COL_FREERES_ITEM_ID = "FREERES_ITEM_ID";
	public final static String COL_FREE_RES_TYPE = "FREE_RES_TYPE";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static String COL_THIS_TIME_USED = "THIS_TIME_USED";
	public final static String COL_STANDARD_PRODUCT_ID = "STANDARD_PRODUCT_ID";
	public final static String COL_CHANGE_ITEM_ID = "CHANGE_ITEM_ID";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_REDUCE_FEE = "REDUCE_FEE";
	public final static int IDX_DONE_CODE = 0;
	public final static int IDX_FREERES_ITEM_ID = 1;
	public final static int IDX_FREE_RES_TYPE = 2;
	public final static int IDX_VALID_DATE_TIME = 3;
	public final static int IDX_EXPIRE_DATE_TIME = 4;
	public final static int IDX_THIS_TIME_USED = 5;
	public final static int IDX_STANDARD_PRODUCT_ID = 6;
	public final static int IDX_CHANGE_ITEM_ID = 7;
	public final static int IDX_MEASURE_ID = 8;
	public final static int IDX_REDUCE_FEE = 9;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	/**
	 * 
	 */
	@XmlElement(name="freeresItemId")
	@Sdl
	private int freeresItemId;

	/**
	 * 
	 */
	@XmlElement(name="freeResType")
	@Sdl
	private int freeResType;

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
	@XmlElement(name="thisTimeUsed")
	@Sdl
	private long thisTimeUsed;

	/**
	 * 
	 */
	@XmlElement(name="standardProductId")
	@Sdl
	private long standardProductId;

	/**
	 * 
	 */
	@XmlElement(name="changeItemId")
	@Sdl
	private int changeItemId;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="reduceFee")
	@Sdl
	private long reduceFee;

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(0, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public void setFreeresItemId(int obj){
		this.freeresItemId = obj;
		onFieldSet(1, obj);
	}

	public int getFreeresItemId(){
		return freeresItemId;
	}

	public void setFreeResType(int obj){
		this.freeResType = obj;
		onFieldSet(2, obj);
	}

	public int getFreeResType(){
		return freeResType;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(3, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(4, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public void setThisTimeUsed(long obj){
		this.thisTimeUsed = obj;
		onFieldSet(5, obj);
	}

	public long getThisTimeUsed(){
		return thisTimeUsed;
	}

	public void setStandardProductId(long obj){
		this.standardProductId = obj;
		onFieldSet(6, obj);
	}

	public long getStandardProductId(){
		return standardProductId;
	}

	public void setChangeItemId(int obj){
		this.changeItemId = obj;
		onFieldSet(7, obj);
	}

	public int getChangeItemId(){
		return changeItemId;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(8, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setReduceFee(long obj){
		this.reduceFee = obj;
		onFieldSet(9, obj);
	}

	public long getReduceFee(){
		return reduceFee;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFreeResDiscountFee(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 10; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFreeResDiscountFee(SFreeResDiscountFee arg0){
		copy(arg0);
	}

	public void copy(final SFreeResDiscountFee rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		doneCode = rhs.doneCode;
		freeresItemId = rhs.freeresItemId;
		freeResType = rhs.freeResType;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
		thisTimeUsed = rhs.thisTimeUsed;
		standardProductId = rhs.standardProductId;
		changeItemId = rhs.changeItemId;
		measureId = rhs.measureId;
		reduceFee = rhs.reduceFee;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFreeResDiscountFee rhs=(SFreeResDiscountFee)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(freeresItemId, rhs.freeresItemId)) return false;
		if(!ObjectUtils.equals(freeResType, rhs.freeResType)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		if(!ObjectUtils.equals(thisTimeUsed, rhs.thisTimeUsed)) return false;
		if(!ObjectUtils.equals(standardProductId, rhs.standardProductId)) return false;
		if(!ObjectUtils.equals(changeItemId, rhs.changeItemId)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(reduceFee, rhs.reduceFee)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(freeresItemId)
		.append(freeResType)
		.append(validDateTime)
		.append(expireDateTime)
		.append(thisTimeUsed)
		.append(standardProductId)
		.append(changeItemId)
		.append(measureId)
		.append(reduceFee)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(10);
public static final long BITS_ALL_MARKER = 0x200L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SFreeResDiscountFee";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "DONE_CODE", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "FREERES_ITEM_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "FREE_RES_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "VALID_DATE_TIME", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "EXPIRE_DATE_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "THIS_TIME_USED", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "STANDARD_PRODUCT_ID", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "CHANGE_ITEM_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "MEASURE_ID", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFreeResDiscountFee.class, "REDUCE_FEE", 9, long.class));
}

}