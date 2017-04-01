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
import java.util.Map;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acctId","resourceId","phoneId","paidValue","measureId","rechargeType","rechargeFlag","rechargeMethod","serviceId","bankAcctId","bankId","bankAcctName","extendDays","voucherCardNo","cardType","cardBatchNo","cardSerialNo","cardValue","cardPin","cardMerchantId","cardValidity","transparentData1","transparentData2","transparentData3","extraMap"})
@Sdl(module="MXdr")
public class SRecharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_PAID_VALUE = "PAID_VALUE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_RECHARGE_TYPE = "RECHARGE_TYPE";
	public final static String COL_RECHARGE_FLAG = "RECHARGE_FLAG";
	public final static String COL_RECHARGE_METHOD = "RECHARGE_METHOD";
	public final static String COL_SERVICE_ID = "SERVICE_ID";
	public final static String COL_BANK_ACCT_ID = "BANK_ACCT_ID";
	public final static String COL_BANK_ID = "BANK_ID";
	public final static String COL_BANK_ACCT_NAME = "BANK_ACCT_NAME";
	public final static String COL_EXTEND_DAYS = "EXTEND_DAYS";
	public final static String COL_VOUCHER_CARD_NO = "VOUCHER_CARD_NO";
	public final static String COL_CARD_TYPE = "CARD_TYPE";
	public final static String COL_CARD_BATCH_NO = "CARD_BATCH_NO";
	public final static String COL_CARD_SERIAL_NO = "CARD_SERIAL_NO";
	public final static String COL_CARD_VALUE = "CARD_VALUE";
	public final static String COL_CARD_PIN = "CARD_PIN";
	public final static String COL_CARD_MERCHANT_ID = "CARD_MERCHANT_ID";
	public final static String COL_CARD_VALIDITY = "CARD_VALIDITY";
	public final static String COL_TRANSPARENT_DATA1 = "TRANSPARENT_DATA1";
	public final static String COL_TRANSPARENT_DATA2 = "TRANSPARENT_DATA2";
	public final static String COL_TRANSPARENT_DATA3 = "TRANSPARENT_DATA3";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_RESOURCE_ID = 1;
	public final static int IDX_PHONE_ID = 2;
	public final static int IDX_PAID_VALUE = 3;
	public final static int IDX_MEASURE_ID = 4;
	public final static int IDX_RECHARGE_TYPE = 5;
	public final static int IDX_RECHARGE_FLAG = 6;
	public final static int IDX_RECHARGE_METHOD = 7;
	public final static int IDX_SERVICE_ID = 8;
	public final static int IDX_BANK_ACCT_ID = 9;
	public final static int IDX_BANK_ID = 10;
	public final static int IDX_BANK_ACCT_NAME = 11;
	public final static int IDX_EXTEND_DAYS = 12;
	public final static int IDX_VOUCHER_CARD_NO = 13;
	public final static int IDX_CARD_TYPE = 14;
	public final static int IDX_CARD_BATCH_NO = 15;
	public final static int IDX_CARD_SERIAL_NO = 16;
	public final static int IDX_CARD_VALUE = 17;
	public final static int IDX_CARD_PIN = 18;
	public final static int IDX_CARD_MERCHANT_ID = 19;
	public final static int IDX_CARD_VALIDITY = 20;
	public final static int IDX_TRANSPARENT_DATA1 = 21;
	public final static int IDX_TRANSPARENT_DATA2 = 22;
	public final static int IDX_TRANSPARENT_DATA3 = 23;
	public final static int IDX_EXTRA_MAP = 24;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="paidValue")
	@Sdl
	private long paidValue;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="rechargeType")
	@Sdl
	private int rechargeType;

	/**
	 * 
	 */
	@XmlElement(name="rechargeFlag")
	@Sdl
	private short rechargeFlag;

	/**
	 * 
	 */
	@XmlElement(name="rechargeMethod")
	@Sdl
	private int rechargeMethod;

	/**
	 * 
	 */
	@XmlElement(name="serviceId")
	@Sdl
	private int serviceId;

	/**
	 * 
	 */
	@XmlElement(name="bankAcctId")
	@Sdl
	private String bankAcctId;

	/**
	 * 
	 */
	@XmlElement(name="bankId")
	@Sdl
	private long bankId;

	/**
	 * 
	 */
	@XmlElement(name="bankAcctName")
	@Sdl
	private String bankAcctName;

	/**
	 * 
	 */
	@XmlElement(name="extendDays")
	@Sdl
	private int extendDays;

	/**
	 * 
	 */
	@XmlElement(name="voucherCardNo")
	@Sdl
	private String voucherCardNo;

	/**
	 * 
	 */
	@XmlElement(name="cardType")
	@Sdl
	private short cardType;

	/**
	 * 
	 */
	@XmlElement(name="cardBatchNo")
	@Sdl
	private String cardBatchNo;

	/**
	 * 
	 */
	@XmlElement(name="cardSerialNo")
	@Sdl
	private String cardSerialNo;

	/**
	 * 
	 */
	@XmlElement(name="cardValue")
	@Sdl
	private int cardValue;

	/**
	 * 
	 */
	@XmlElement(name="cardPin")
	@Sdl
	private String cardPin;

	/**
	 * 
	 */
	@XmlElement(name="cardMerchantId")
	@Sdl
	private int cardMerchantId;

	/**
	 * 
	 */
	@XmlElement(name="cardValidity")
	@Sdl
	private long cardValidity;

	/**
	 * 
	 */
	@XmlElement(name="transparentData1")
	@Sdl
	private String transparentData1;

	/**
	 * 
	 */
	@XmlElement(name="transparentData2")
	@Sdl
	private String transparentData2;

	/**
	 * 
	 */
	@XmlElement(name="transparentData3")
	@Sdl
	private String transparentData3;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(24, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(1, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(2, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setPaidValue(long obj){
		this.paidValue = obj;
		onFieldSet(3, obj);
	}

	public long getPaidValue(){
		return paidValue;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(4, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setRechargeType(int obj){
		this.rechargeType = obj;
		onFieldSet(5, obj);
	}

	public int getRechargeType(){
		return rechargeType;
	}

	public void setRechargeFlag(short obj){
		this.rechargeFlag = obj;
		onFieldSet(6, obj);
	}

	public short getRechargeFlag(){
		return rechargeFlag;
	}

	public void setRechargeMethod(int obj){
		this.rechargeMethod = obj;
		onFieldSet(7, obj);
	}

	public int getRechargeMethod(){
		return rechargeMethod;
	}

	public void setServiceId(int obj){
		this.serviceId = obj;
		onFieldSet(8, obj);
	}

	public int getServiceId(){
		return serviceId;
	}

	public void setBankAcctId(String obj){
		this.bankAcctId = obj;
		onFieldSet(9, obj);
	}

	public String getBankAcctId(){
		return bankAcctId;
	}

	public void setBankId(long obj){
		this.bankId = obj;
		onFieldSet(10, obj);
	}

	public long getBankId(){
		return bankId;
	}

	public void setBankAcctName(String obj){
		this.bankAcctName = obj;
		onFieldSet(11, obj);
	}

	public String getBankAcctName(){
		return bankAcctName;
	}

	public void setExtendDays(int obj){
		this.extendDays = obj;
		onFieldSet(12, obj);
	}

	public int getExtendDays(){
		return extendDays;
	}

	public void setVoucherCardNo(String obj){
		this.voucherCardNo = obj;
		onFieldSet(13, obj);
	}

	public String getVoucherCardNo(){
		return voucherCardNo;
	}

	public void setCardType(short obj){
		this.cardType = obj;
		onFieldSet(14, obj);
	}

	public short getCardType(){
		return cardType;
	}

	public void setCardBatchNo(String obj){
		this.cardBatchNo = obj;
		onFieldSet(15, obj);
	}

	public String getCardBatchNo(){
		return cardBatchNo;
	}

	public void setCardSerialNo(String obj){
		this.cardSerialNo = obj;
		onFieldSet(16, obj);
	}

	public String getCardSerialNo(){
		return cardSerialNo;
	}

	public void setCardValue(int obj){
		this.cardValue = obj;
		onFieldSet(17, obj);
	}

	public int getCardValue(){
		return cardValue;
	}

	public void setCardPin(String obj){
		this.cardPin = obj;
		onFieldSet(18, obj);
	}

	public String getCardPin(){
		return cardPin;
	}

	public void setCardMerchantId(int obj){
		this.cardMerchantId = obj;
		onFieldSet(19, obj);
	}

	public int getCardMerchantId(){
		return cardMerchantId;
	}

	public void setCardValidity(long obj){
		this.cardValidity = obj;
		onFieldSet(20, obj);
	}

	public long getCardValidity(){
		return cardValidity;
	}

	public void setTransparentData1(String obj){
		this.transparentData1 = obj;
		onFieldSet(21, obj);
	}

	public String getTransparentData1(){
		return transparentData1;
	}

	public void setTransparentData2(String obj){
		this.transparentData2 = obj;
		onFieldSet(22, obj);
	}

	public String getTransparentData2(){
		return transparentData2;
	}

	public void setTransparentData3(String obj){
		this.transparentData3 = obj;
		onFieldSet(23, obj);
	}

	public String getTransparentData3(){
		return transparentData3;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SRecharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 25; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRecharge(SRecharge arg0){
		copy(arg0);
	}

	public void copy(final SRecharge rhs){
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
		resourceId = rhs.resourceId;
		phoneId = rhs.phoneId;
		paidValue = rhs.paidValue;
		measureId = rhs.measureId;
		rechargeType = rhs.rechargeType;
		rechargeFlag = rhs.rechargeFlag;
		rechargeMethod = rhs.rechargeMethod;
		serviceId = rhs.serviceId;
		bankAcctId = rhs.bankAcctId;
		bankId = rhs.bankId;
		bankAcctName = rhs.bankAcctName;
		extendDays = rhs.extendDays;
		voucherCardNo = rhs.voucherCardNo;
		cardType = rhs.cardType;
		cardBatchNo = rhs.cardBatchNo;
		cardSerialNo = rhs.cardSerialNo;
		cardValue = rhs.cardValue;
		cardPin = rhs.cardPin;
		cardMerchantId = rhs.cardMerchantId;
		cardValidity = rhs.cardValidity;
		transparentData1 = rhs.transparentData1;
		transparentData2 = rhs.transparentData2;
		transparentData3 = rhs.transparentData3;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRecharge rhs=(SRecharge)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(paidValue, rhs.paidValue)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(rechargeType, rhs.rechargeType)) return false;
		if(!ObjectUtils.equals(rechargeFlag, rhs.rechargeFlag)) return false;
		if(!ObjectUtils.equals(rechargeMethod, rhs.rechargeMethod)) return false;
		if(!ObjectUtils.equals(serviceId, rhs.serviceId)) return false;
		if(!ObjectUtils.equals(bankAcctId, rhs.bankAcctId)) return false;
		if(!ObjectUtils.equals(bankId, rhs.bankId)) return false;
		if(!ObjectUtils.equals(bankAcctName, rhs.bankAcctName)) return false;
		if(!ObjectUtils.equals(extendDays, rhs.extendDays)) return false;
		if(!ObjectUtils.equals(voucherCardNo, rhs.voucherCardNo)) return false;
		if(!ObjectUtils.equals(cardType, rhs.cardType)) return false;
		if(!ObjectUtils.equals(cardBatchNo, rhs.cardBatchNo)) return false;
		if(!ObjectUtils.equals(cardSerialNo, rhs.cardSerialNo)) return false;
		if(!ObjectUtils.equals(cardValue, rhs.cardValue)) return false;
		if(!ObjectUtils.equals(cardPin, rhs.cardPin)) return false;
		if(!ObjectUtils.equals(cardMerchantId, rhs.cardMerchantId)) return false;
		if(!ObjectUtils.equals(cardValidity, rhs.cardValidity)) return false;
		if(!ObjectUtils.equals(transparentData1, rhs.transparentData1)) return false;
		if(!ObjectUtils.equals(transparentData2, rhs.transparentData2)) return false;
		if(!ObjectUtils.equals(transparentData3, rhs.transparentData3)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(resourceId)
		.append(phoneId)
		.append(paidValue)
		.append(measureId)
		.append(rechargeType)
		.append(rechargeFlag)
		.append(rechargeMethod)
		.append(serviceId)
		.append(bankAcctId)
		.append(bankId)
		.append(bankAcctName)
		.append(extendDays)
		.append(voucherCardNo)
		.append(cardType)
		.append(cardBatchNo)
		.append(cardSerialNo)
		.append(cardValue)
		.append(cardPin)
		.append(cardMerchantId)
		.append(cardValidity)
		.append(transparentData1)
		.append(transparentData2)
		.append(transparentData3)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(25);
public static final long BITS_ALL_MARKER = 0x1000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRecharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "RESOURCE_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "PHONE_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "PAID_VALUE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "MEASURE_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "RECHARGE_TYPE", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "RECHARGE_FLAG", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "RECHARGE_METHOD", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "SERVICE_ID", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "BANK_ACCT_ID", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "BANK_ID", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "BANK_ACCT_NAME", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "EXTEND_DAYS", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "VOUCHER_CARD_NO", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_TYPE", 14, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_BATCH_NO", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_SERIAL_NO", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_VALUE", 17, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_PIN", 18, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_MERCHANT_ID", 19, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "CARD_VALIDITY", 20, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "TRANSPARENT_DATA1", 21, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "TRANSPARENT_DATA2", 22, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "TRANSPARENT_DATA3", 23, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRecharge.class, "EXTRA_MAP", 24, Map.class));
}

}