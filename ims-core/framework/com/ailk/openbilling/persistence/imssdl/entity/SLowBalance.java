package com.ailk.openbilling.persistence.imssdl.entity;

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
@XmlType(propOrder={"custId","acctId","phoneId","autoType","payChannel","lowBalanceAmount","currentBalanceAmount"})
@Sdl(module="MImsSyncDef")
public class SLowBalance extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_AUTO_TYPE = "AUTO_TYPE";
	public final static String COL_PAY_CHANNEL = "PAY_CHANNEL";
	public final static String COL_LOW_BALANCE_AMOUNT = "LOW_BALANCE_AMOUNT";
	public final static String COL_CURRENT_BALANCE_AMOUNT = "CURRENT_BALANCE_AMOUNT";
	public final static int IDX_CUST_ID = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_PHONE_ID = 2;
	public final static int IDX_AUTO_TYPE = 3;
	public final static int IDX_PAY_CHANNEL = 4;
	public final static int IDX_LOW_BALANCE_AMOUNT = 5;
	public final static int IDX_CURRENT_BALANCE_AMOUNT = 6;

	/**
	 * 
	 */
	@XmlElement(name="custId")
	@Sdl
	private long custId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="autoType")
	@Sdl
	private int autoType;

	/**
	 * 
	 */
	@XmlElement(name="payChannel")
	@Sdl
	private int payChannel;

	/**
	 * 
	 */
	@XmlElement(name="lowBalanceAmount")
	@Sdl
	private long lowBalanceAmount;

	/**
	 * 
	 */
	@XmlElement(name="currentBalanceAmount")
	@Sdl
	private long currentBalanceAmount;

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(0, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(1, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(2, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setAutoType(int obj){
		this.autoType = obj;
		onFieldSet(3, obj);
	}

	public int getAutoType(){
		return autoType;
	}

	public void setPayChannel(int obj){
		this.payChannel = obj;
		onFieldSet(4, obj);
	}

	public int getPayChannel(){
		return payChannel;
	}

	public void setLowBalanceAmount(long obj){
		this.lowBalanceAmount = obj;
		onFieldSet(5, obj);
	}

	public long getLowBalanceAmount(){
		return lowBalanceAmount;
	}

	public void setCurrentBalanceAmount(long obj){
		this.currentBalanceAmount = obj;
		onFieldSet(6, obj);
	}

	public long getCurrentBalanceAmount(){
		return currentBalanceAmount;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SLowBalance(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SLowBalance(SLowBalance arg0){
		copy(arg0);
	}

	public void copy(final SLowBalance rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		custId = rhs.custId;
		acctId = rhs.acctId;
		phoneId = rhs.phoneId;
		autoType = rhs.autoType;
		payChannel = rhs.payChannel;
		lowBalanceAmount = rhs.lowBalanceAmount;
		currentBalanceAmount = rhs.currentBalanceAmount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLowBalance rhs=(SLowBalance)rhs0;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(autoType, rhs.autoType)) return false;
		if(!ObjectUtils.equals(payChannel, rhs.payChannel)) return false;
		if(!ObjectUtils.equals(lowBalanceAmount, rhs.lowBalanceAmount)) return false;
		if(!ObjectUtils.equals(currentBalanceAmount, rhs.currentBalanceAmount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custId)
		.append(acctId)
		.append(phoneId)
		.append(autoType)
		.append(payChannel)
		.append(lowBalanceAmount)
		.append(currentBalanceAmount)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SLowBalance";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "CUST_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "PHONE_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "AUTO_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "PAY_CHANNEL", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "LOW_BALANCE_AMOUNT", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLowBalance.class, "CURRENT_BALANCE_AMOUNT", 6, long.class));
}

}