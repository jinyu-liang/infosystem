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
@XmlType(propOrder={"monitorCdr","billingType","payforBillingType"})
@Sdl(module="MXdr")
public class SPayOffMode extends CsdlStructObject implements IComplexEntity{

	public final static String COL_MONITOR_CDR = "MONITOR_CDR";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_PAYFOR_BILLING_TYPE = "PAYFOR_BILLING_TYPE";
	public final static int IDX_MONITOR_CDR = 0;
	public final static int IDX_BILLING_TYPE = 1;
	public final static int IDX_PAYFOR_BILLING_TYPE = 2;

	/**
	 * 
	 */
	@XmlElement(name="monitorCdr")
	@Sdl
	private int monitorCdr;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private int billingType;

	/**
	 * 
	 */
	@XmlElement(name="payforBillingType")
	@Sdl
	private int payforBillingType;

	public void setMonitorCdr(int obj){
		this.monitorCdr = obj;
		onFieldSet(0, obj);
	}

	public int getMonitorCdr(){
		return monitorCdr;
	}

	public void setBillingType(int obj){
		this.billingType = obj;
		onFieldSet(1, obj);
	}

	public int getBillingType(){
		return billingType;
	}

	public void setPayforBillingType(int obj){
		this.payforBillingType = obj;
		onFieldSet(2, obj);
	}

	public int getPayforBillingType(){
		return payforBillingType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPayOffMode(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 3; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPayOffMode(SPayOffMode arg0){
		copy(arg0);
	}

	public void copy(final SPayOffMode rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		monitorCdr = rhs.monitorCdr;
		billingType = rhs.billingType;
		payforBillingType = rhs.payforBillingType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPayOffMode rhs=(SPayOffMode)rhs0;
		if(!ObjectUtils.equals(monitorCdr, rhs.monitorCdr)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(payforBillingType, rhs.payforBillingType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(monitorCdr)
		.append(billingType)
		.append(payforBillingType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(3);
public static final long BITS_ALL_MARKER = 0x4L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPayOffMode";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPayOffMode.class, "MONITOR_CDR", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayOffMode.class, "BILLING_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPayOffMode.class, "PAYFOR_BILLING_TYPE", 2, int.class));
}

}