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
@XmlType(propOrder={"acctId","revenueCode","values","billingType","measureId"})
@Sdl(module="MXdr")
public class SRevenueCode extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_REVENUE_CODE = "REVENUE_CODE";
	public final static String COL_VALUES = "VALUES";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_REVENUE_CODE = 1;
	public final static int IDX_VALUES = 2;
	public final static int IDX_BILLING_TYPE = 3;
	public final static int IDX_MEASURE_ID = 4;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="revenueCode")
	@Sdl
	private int revenueCode;

	/**
	 * 
	 */
	@XmlElement(name="values")
	@Sdl
	private long values;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private short billingType;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setRevenueCode(int obj){
		this.revenueCode = obj;
		onFieldSet(1, obj);
	}

	public int getRevenueCode(){
		return revenueCode;
	}

	public void setValues(long obj){
		this.values = obj;
		onFieldSet(2, obj);
	}

	public long getValues(){
		return values;
	}

	public void setBillingType(short obj){
		this.billingType = obj;
		onFieldSet(3, obj);
	}

	public short getBillingType(){
		return billingType;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(4, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRevenueCode(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRevenueCode(SRevenueCode arg0){
		copy(arg0);
	}

	public void copy(final SRevenueCode rhs){
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
		revenueCode = rhs.revenueCode;
		values = rhs.values;
		billingType = rhs.billingType;
		measureId = rhs.measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRevenueCode rhs=(SRevenueCode)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(revenueCode, rhs.revenueCode)) return false;
		if(!ObjectUtils.equals(values, rhs.values)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(revenueCode)
		.append(values)
		.append(billingType)
		.append(measureId)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRevenueCode";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRevenueCode.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRevenueCode.class, "REVENUE_CODE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRevenueCode.class, "VALUES", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRevenueCode.class, "BILLING_TYPE", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRevenueCode.class, "MEASURE_ID", 4, int.class));
}

}