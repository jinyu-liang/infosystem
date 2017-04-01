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
@XmlType(propOrder={"acctId","amount","deduceValue","thresholdValue","vitemCode","measureId","validDateTime","expireDateTime"})
@Sdl(module="MXdr")
public class SAocAssetDtl extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_AMOUNT = "AMOUNT";
	public final static String COL_DEDUCE_VALUE = "DEDUCE_VALUE";
	public final static String COL_THRESHOLD_VALUE = "THRESHOLD_VALUE";
	public final static String COL_VITEM_CODE = "VITEM_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_VALID_DATE_TIME = "VALID_DATE_TIME";
	public final static String COL_EXPIRE_DATE_TIME = "EXPIRE_DATE_TIME";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_AMOUNT = 1;
	public final static int IDX_DEDUCE_VALUE = 2;
	public final static int IDX_THRESHOLD_VALUE = 3;
	public final static int IDX_VITEM_CODE = 4;
	public final static int IDX_MEASURE_ID = 5;
	public final static int IDX_VALID_DATE_TIME = 6;
	public final static int IDX_EXPIRE_DATE_TIME = 7;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="amount")
	@Sdl
	private long amount;

	/**
	 * 
	 */
	@XmlElement(name="deduceValue")
	@Sdl
	private long deduceValue;

	/**
	 * 
	 */
	@XmlElement(name="thresholdValue")
	@Sdl
	private long thresholdValue;

	/**
	 * 
	 */
	@XmlElement(name="vitemCode")
	@Sdl
	private int vitemCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

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

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setAmount(long obj){
		this.amount = obj;
		onFieldSet(1, obj);
	}

	public long getAmount(){
		return amount;
	}

	public void setDeduceValue(long obj){
		this.deduceValue = obj;
		onFieldSet(2, obj);
	}

	public long getDeduceValue(){
		return deduceValue;
	}

	public void setThresholdValue(long obj){
		this.thresholdValue = obj;
		onFieldSet(3, obj);
	}

	public long getThresholdValue(){
		return thresholdValue;
	}

	public void setVitemCode(int obj){
		this.vitemCode = obj;
		onFieldSet(4, obj);
	}

	public int getVitemCode(){
		return vitemCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(5, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setValidDateTime(long obj){
		this.validDateTime = obj;
		onFieldSet(6, obj);
	}

	public long getValidDateTime(){
		return validDateTime;
	}

	public void setExpireDateTime(long obj){
		this.expireDateTime = obj;
		onFieldSet(7, obj);
	}

	public long getExpireDateTime(){
		return expireDateTime;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SAocAssetDtl(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 8; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SAocAssetDtl(SAocAssetDtl arg0){
		copy(arg0);
	}

	public void copy(final SAocAssetDtl rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		acctId = rhs.acctId;
		amount = rhs.amount;
		deduceValue = rhs.deduceValue;
		thresholdValue = rhs.thresholdValue;
		vitemCode = rhs.vitemCode;
		measureId = rhs.measureId;
		validDateTime = rhs.validDateTime;
		expireDateTime = rhs.expireDateTime;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAocAssetDtl rhs=(SAocAssetDtl)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(deduceValue, rhs.deduceValue)) return false;
		if(!ObjectUtils.equals(thresholdValue, rhs.thresholdValue)) return false;
		if(!ObjectUtils.equals(vitemCode, rhs.vitemCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(validDateTime, rhs.validDateTime)) return false;
		if(!ObjectUtils.equals(expireDateTime, rhs.expireDateTime)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(amount)
		.append(deduceValue)
		.append(thresholdValue)
		.append(vitemCode)
		.append(measureId)
		.append(validDateTime)
		.append(expireDateTime)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(8);
public static final long BITS_ALL_MARKER = 0x80L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SAocAssetDtl";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "AMOUNT", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "DEDUCE_VALUE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "THRESHOLD_VALUE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "VITEM_CODE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "MEASURE_ID", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "VALID_DATE_TIME", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SAocAssetDtl.class, "EXPIRE_DATE_TIME", 7, long.class));
}

}