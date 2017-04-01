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
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"etopupSessionId","rechargeNumber","tradeDate","rechargePartnerId","errorCode","cardBatch","cardSerial","cardType","cardValidity","cardValue","cardMerchantId","cardPin","rechargeMethod","serviceId","rewardList","rechargeAmount","activeDay","accountType","activePeriod","oldActivePeriod","validityAdd","transparentData1","transparentData2","transparentData3","extraMap"})
@Sdl(module="MXdr")
public class SRechargeLog extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ETOPUP_SESSION_ID = "ETOPUP_SESSION_ID";
	public final static String COL_RECHARGE_NUMBER = "RECHARGE_NUMBER";
	public final static String COL_TRADE_DATE = "TRADE_DATE";
	public final static String COL_RECHARGE_PARTNER_ID = "RECHARGE_PARTNER_ID";
	public final static String COL_ERROR_CODE = "ERROR_CODE";
	public final static String COL_CARD_BATCH = "CARD_BATCH";
	public final static String COL_CARD_SERIAL = "CARD_SERIAL";
	public final static String COL_CARD_TYPE = "CARD_TYPE";
	public final static String COL_CARD_VALIDITY = "CARD_VALIDITY";
	public final static String COL_CARD_VALUE = "CARD_VALUE";
	public final static String COL_CARD_MERCHANT_ID = "CARD_MERCHANT_ID";
	public final static String COL_CARD_PIN = "CARD_PIN";
	public final static String COL_RECHARGE_METHOD = "RECHARGE_METHOD";
	public final static String COL_SERVICE_ID = "SERVICE_ID";
	public final static String COL_REWARD_LIST = "REWARD_LIST";
	public final static String COL_RECHARGE_AMOUNT = "RECHARGE_AMOUNT";
	public final static String COL_ACTIVE_DAY = "ACTIVE_DAY";
	public final static String COL_ACCOUNT_TYPE = "ACCOUNT_TYPE";
	public final static String COL_ACTIVE_PERIOD = "ACTIVE_PERIOD";
	public final static String COL_OLD_ACTIVE_PERIOD = "OLD_ACTIVE_PERIOD";
	public final static String COL_VALIDITY_ADD = "VALIDITY_ADD";
	public final static String COL_TRANSPARENT_DATA1 = "TRANSPARENT_DATA1";
	public final static String COL_TRANSPARENT_DATA2 = "TRANSPARENT_DATA2";
	public final static String COL_TRANSPARENT_DATA3 = "TRANSPARENT_DATA3";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_ETOPUP_SESSION_ID = 0;
	public final static int IDX_RECHARGE_NUMBER = 1;
	public final static int IDX_TRADE_DATE = 2;
	public final static int IDX_RECHARGE_PARTNER_ID = 3;
	public final static int IDX_ERROR_CODE = 4;
	public final static int IDX_CARD_BATCH = 5;
	public final static int IDX_CARD_SERIAL = 6;
	public final static int IDX_CARD_TYPE = 7;
	public final static int IDX_CARD_VALIDITY = 8;
	public final static int IDX_CARD_VALUE = 9;
	public final static int IDX_CARD_MERCHANT_ID = 10;
	public final static int IDX_CARD_PIN = 11;
	public final static int IDX_RECHARGE_METHOD = 12;
	public final static int IDX_SERVICE_ID = 13;
	public final static int IDX_REWARD_LIST = 14;
	public final static int IDX_RECHARGE_AMOUNT = 15;
	public final static int IDX_ACTIVE_DAY = 16;
	public final static int IDX_ACCOUNT_TYPE = 17;
	public final static int IDX_ACTIVE_PERIOD = 18;
	public final static int IDX_OLD_ACTIVE_PERIOD = 19;
	public final static int IDX_VALIDITY_ADD = 20;
	public final static int IDX_TRANSPARENT_DATA1 = 21;
	public final static int IDX_TRANSPARENT_DATA2 = 22;
	public final static int IDX_TRANSPARENT_DATA3 = 23;
	public final static int IDX_EXTRA_MAP = 24;

	/**
	 * 
	 */
	@XmlElement(name="rewardList")
	@Sdl
	private List<SRewardInfo> rewardList;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="etopupSessionId")
	@Sdl
	private String etopupSessionId;

	/**
	 * 
	 */
	@XmlElement(name="rechargeNumber")
	@Sdl
	private String rechargeNumber;

	/**
	 * 
	 */
	@XmlElement(name="tradeDate")
	@Sdl
	private long tradeDate;

	/**
	 * 
	 */
	@XmlElement(name="rechargePartnerId")
	@Sdl
	private int rechargePartnerId;

	/**
	 * 
	 */
	@XmlElement(name="errorCode")
	@Sdl
	private int errorCode;

	/**
	 * 
	 */
	@XmlElement(name="cardBatch")
	@Sdl
	private String cardBatch;

	/**
	 * 
	 */
	@XmlElement(name="cardSerial")
	@Sdl
	private String cardSerial;

	/**
	 * 
	 */
	@XmlElement(name="cardType")
	@Sdl
	private int cardType;

	/**
	 * 
	 */
	@XmlElement(name="cardValidity")
	@Sdl
	private long cardValidity;

	/**
	 * 
	 */
	@XmlElement(name="cardValue")
	@Sdl
	private int cardValue;

	/**
	 * 
	 */
	@XmlElement(name="cardMerchantId")
	@Sdl
	private int cardMerchantId;

	/**
	 * 
	 */
	@XmlElement(name="cardPin")
	@Sdl
	private String cardPin;

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
	@XmlElement(name="rechargeAmount")
	@Sdl
	private long rechargeAmount;

	/**
	 * 
	 */
	@XmlElement(name="activeDay")
	@Sdl
	private int activeDay;

	/**
	 * 
	 */
	@XmlElement(name="accountType")
	@Sdl
	private short accountType;

	/**
	 * 
	 */
	@XmlElement(name="activePeriod")
	@Sdl
	private long activePeriod;

	/**
	 * 
	 */
	@XmlElement(name="oldActivePeriod")
	@Sdl
	private long oldActivePeriod;

	/**
	 * 
	 */
	@XmlElement(name="validityAdd")
	@Sdl
	private int validityAdd;

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

	public void setRewardList(List<SRewardInfo> obj){
		this.rewardList = obj;
		onFieldSet(14, obj);
	}

	public List<SRewardInfo> getRewardList(){
		return rewardList;
	}

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(24, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setEtopupSessionId(String obj){
		this.etopupSessionId = obj;
		onFieldSet(0, obj);
	}

	public String getEtopupSessionId(){
		return etopupSessionId;
	}

	public void setRechargeNumber(String obj){
		this.rechargeNumber = obj;
		onFieldSet(1, obj);
	}

	public String getRechargeNumber(){
		return rechargeNumber;
	}

	public void setTradeDate(long obj){
		this.tradeDate = obj;
		onFieldSet(2, obj);
	}

	public long getTradeDate(){
		return tradeDate;
	}

	public void setRechargePartnerId(int obj){
		this.rechargePartnerId = obj;
		onFieldSet(3, obj);
	}

	public int getRechargePartnerId(){
		return rechargePartnerId;
	}

	public void setErrorCode(int obj){
		this.errorCode = obj;
		onFieldSet(4, obj);
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setCardBatch(String obj){
		this.cardBatch = obj;
		onFieldSet(5, obj);
	}

	public String getCardBatch(){
		return cardBatch;
	}

	public void setCardSerial(String obj){
		this.cardSerial = obj;
		onFieldSet(6, obj);
	}

	public String getCardSerial(){
		return cardSerial;
	}

	public void setCardType(int obj){
		this.cardType = obj;
		onFieldSet(7, obj);
	}

	public int getCardType(){
		return cardType;
	}

	public void setCardValidity(long obj){
		this.cardValidity = obj;
		onFieldSet(8, obj);
	}

	public long getCardValidity(){
		return cardValidity;
	}

	public void setCardValue(int obj){
		this.cardValue = obj;
		onFieldSet(9, obj);
	}

	public int getCardValue(){
		return cardValue;
	}

	public void setCardMerchantId(int obj){
		this.cardMerchantId = obj;
		onFieldSet(10, obj);
	}

	public int getCardMerchantId(){
		return cardMerchantId;
	}

	public void setCardPin(String obj){
		this.cardPin = obj;
		onFieldSet(11, obj);
	}

	public String getCardPin(){
		return cardPin;
	}

	public void setRechargeMethod(int obj){
		this.rechargeMethod = obj;
		onFieldSet(12, obj);
	}

	public int getRechargeMethod(){
		return rechargeMethod;
	}

	public void setServiceId(int obj){
		this.serviceId = obj;
		onFieldSet(13, obj);
	}

	public int getServiceId(){
		return serviceId;
	}

	public void setRechargeAmount(long obj){
		this.rechargeAmount = obj;
		onFieldSet(15, obj);
	}

	public long getRechargeAmount(){
		return rechargeAmount;
	}

	public void setActiveDay(int obj){
		this.activeDay = obj;
		onFieldSet(16, obj);
	}

	public int getActiveDay(){
		return activeDay;
	}

	public void setAccountType(short obj){
		this.accountType = obj;
		onFieldSet(17, obj);
	}

	public short getAccountType(){
		return accountType;
	}

	public void setActivePeriod(long obj){
		this.activePeriod = obj;
		onFieldSet(18, obj);
	}

	public long getActivePeriod(){
		return activePeriod;
	}

	public void setOldActivePeriod(long obj){
		this.oldActivePeriod = obj;
		onFieldSet(19, obj);
	}

	public long getOldActivePeriod(){
		return oldActivePeriod;
	}

	public void setValidityAdd(int obj){
		this.validityAdd = obj;
		onFieldSet(20, obj);
	}

	public int getValidityAdd(){
		return validityAdd;
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

	public SRechargeLog(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 25; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SRechargeLog(SRechargeLog arg0){
		copy(arg0);
	}

	public void copy(final SRechargeLog rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		etopupSessionId = rhs.etopupSessionId;
		rechargeNumber = rhs.rechargeNumber;
		tradeDate = rhs.tradeDate;
		rechargePartnerId = rhs.rechargePartnerId;
		errorCode = rhs.errorCode;
		cardBatch = rhs.cardBatch;
		cardSerial = rhs.cardSerial;
		cardType = rhs.cardType;
		cardValidity = rhs.cardValidity;
		cardValue = rhs.cardValue;
		cardMerchantId = rhs.cardMerchantId;
		cardPin = rhs.cardPin;
		rechargeMethod = rhs.rechargeMethod;
		serviceId = rhs.serviceId;
		rewardList = rhs.rewardList;
		rechargeAmount = rhs.rechargeAmount;
		activeDay = rhs.activeDay;
		accountType = rhs.accountType;
		activePeriod = rhs.activePeriod;
		oldActivePeriod = rhs.oldActivePeriod;
		validityAdd = rhs.validityAdd;
		transparentData1 = rhs.transparentData1;
		transparentData2 = rhs.transparentData2;
		transparentData3 = rhs.transparentData3;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SRechargeLog rhs=(SRechargeLog)rhs0;
		if(!ObjectUtils.equals(etopupSessionId, rhs.etopupSessionId)) return false;
		if(!ObjectUtils.equals(rechargeNumber, rhs.rechargeNumber)) return false;
		if(!ObjectUtils.equals(tradeDate, rhs.tradeDate)) return false;
		if(!ObjectUtils.equals(rechargePartnerId, rhs.rechargePartnerId)) return false;
		if(!ObjectUtils.equals(errorCode, rhs.errorCode)) return false;
		if(!ObjectUtils.equals(cardBatch, rhs.cardBatch)) return false;
		if(!ObjectUtils.equals(cardSerial, rhs.cardSerial)) return false;
		if(!ObjectUtils.equals(cardType, rhs.cardType)) return false;
		if(!ObjectUtils.equals(cardValidity, rhs.cardValidity)) return false;
		if(!ObjectUtils.equals(cardValue, rhs.cardValue)) return false;
		if(!ObjectUtils.equals(cardMerchantId, rhs.cardMerchantId)) return false;
		if(!ObjectUtils.equals(cardPin, rhs.cardPin)) return false;
		if(!ObjectUtils.equals(rechargeMethod, rhs.rechargeMethod)) return false;
		if(!ObjectUtils.equals(serviceId, rhs.serviceId)) return false;
		if(!ObjectUtils.equals(rewardList, rhs.rewardList)) return false;
		if(!ObjectUtils.equals(rechargeAmount, rhs.rechargeAmount)) return false;
		if(!ObjectUtils.equals(activeDay, rhs.activeDay)) return false;
		if(!ObjectUtils.equals(accountType, rhs.accountType)) return false;
		if(!ObjectUtils.equals(activePeriod, rhs.activePeriod)) return false;
		if(!ObjectUtils.equals(oldActivePeriod, rhs.oldActivePeriod)) return false;
		if(!ObjectUtils.equals(validityAdd, rhs.validityAdd)) return false;
		if(!ObjectUtils.equals(transparentData1, rhs.transparentData1)) return false;
		if(!ObjectUtils.equals(transparentData2, rhs.transparentData2)) return false;
		if(!ObjectUtils.equals(transparentData3, rhs.transparentData3)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(etopupSessionId)
		.append(rechargeNumber)
		.append(tradeDate)
		.append(rechargePartnerId)
		.append(errorCode)
		.append(cardBatch)
		.append(cardSerial)
		.append(cardType)
		.append(cardValidity)
		.append(cardValue)
		.append(cardMerchantId)
		.append(cardPin)
		.append(rechargeMethod)
		.append(serviceId)
		.append(rewardList)
		.append(rechargeAmount)
		.append(activeDay)
		.append(accountType)
		.append(activePeriod)
		.append(oldActivePeriod)
		.append(validityAdd)
		.append(transparentData1)
		.append(transparentData2)
		.append(transparentData3)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(25);
public static final long BITS_ALL_MARKER = 0x1000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SRechargeLog";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "ETOPUP_SESSION_ID", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "RECHARGE_NUMBER", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "TRADE_DATE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "RECHARGE_PARTNER_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "ERROR_CODE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_BATCH", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_SERIAL", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_TYPE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_VALIDITY", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_VALUE", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_MERCHANT_ID", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "CARD_PIN", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "RECHARGE_METHOD", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "SERVICE_ID", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "REWARD_LIST", 14, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "RECHARGE_AMOUNT", 15, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "ACTIVE_DAY", 16, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "ACCOUNT_TYPE", 17, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "ACTIVE_PERIOD", 18, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "OLD_ACTIVE_PERIOD", 19, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "VALIDITY_ADD", 20, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "TRANSPARENT_DATA1", 21, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "TRANSPARENT_DATA2", 22, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "TRANSPARENT_DATA3", 23, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SRechargeLog.class, "EXTRA_MAP", 24, Map.class));
}

}