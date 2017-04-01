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
@XmlType(propOrder={"etopupSessionId","rechargeNumber","startDate","endDate","rechargePartnerId","accountType","cardBatch","cardSerial","rechargeMethod","serviceId","extraMap"})
@Sdl(module="MXdr")
public class SQueryRechargeLog extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ETOPUP_SESSION_ID = "ETOPUP_SESSION_ID";
	public final static String COL_RECHARGE_NUMBER = "RECHARGE_NUMBER";
	public final static String COL_START_DATE = "START_DATE";
	public final static String COL_END_DATE = "END_DATE";
	public final static String COL_RECHARGE_PARTNER_ID = "RECHARGE_PARTNER_ID";
	public final static String COL_ACCOUNT_TYPE = "ACCOUNT_TYPE";
	public final static String COL_CARD_BATCH = "CARD_BATCH";
	public final static String COL_CARD_SERIAL = "CARD_SERIAL";
	public final static String COL_RECHARGE_METHOD = "RECHARGE_METHOD";
	public final static String COL_SERVICE_ID = "SERVICE_ID";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_ETOPUP_SESSION_ID = 0;
	public final static int IDX_RECHARGE_NUMBER = 1;
	public final static int IDX_START_DATE = 2;
	public final static int IDX_END_DATE = 3;
	public final static int IDX_RECHARGE_PARTNER_ID = 4;
	public final static int IDX_ACCOUNT_TYPE = 5;
	public final static int IDX_CARD_BATCH = 6;
	public final static int IDX_CARD_SERIAL = 7;
	public final static int IDX_RECHARGE_METHOD = 8;
	public final static int IDX_SERVICE_ID = 9;
	public final static int IDX_EXTRA_MAP = 10;

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
	@XmlElement(name="startDate")
	@Sdl
	private long startDate;

	/**
	 * 
	 */
	@XmlElement(name="endDate")
	@Sdl
	private long endDate;

	/**
	 * 
	 */
	@XmlElement(name="rechargePartnerId")
	@Sdl
	private int rechargePartnerId;

	/**
	 * 
	 */
	@XmlElement(name="accountType")
	@Sdl
	private short accountType;

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
	@XmlElement(name="rechargeMethod")
	@Sdl
	private int rechargeMethod;

	/**
	 * 
	 */
	@XmlElement(name="serviceId")
	@Sdl
	private int serviceId;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(10, obj);
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

	public void setStartDate(long obj){
		this.startDate = obj;
		onFieldSet(2, obj);
	}

	public long getStartDate(){
		return startDate;
	}

	public void setEndDate(long obj){
		this.endDate = obj;
		onFieldSet(3, obj);
	}

	public long getEndDate(){
		return endDate;
	}

	public void setRechargePartnerId(int obj){
		this.rechargePartnerId = obj;
		onFieldSet(4, obj);
	}

	public int getRechargePartnerId(){
		return rechargePartnerId;
	}

	public void setAccountType(short obj){
		this.accountType = obj;
		onFieldSet(5, obj);
	}

	public short getAccountType(){
		return accountType;
	}

	public void setCardBatch(String obj){
		this.cardBatch = obj;
		onFieldSet(6, obj);
	}

	public String getCardBatch(){
		return cardBatch;
	}

	public void setCardSerial(String obj){
		this.cardSerial = obj;
		onFieldSet(7, obj);
	}

	public String getCardSerial(){
		return cardSerial;
	}

	public void setRechargeMethod(int obj){
		this.rechargeMethod = obj;
		onFieldSet(8, obj);
	}

	public int getRechargeMethod(){
		return rechargeMethod;
	}

	public void setServiceId(int obj){
		this.serviceId = obj;
		onFieldSet(9, obj);
	}

	public int getServiceId(){
		return serviceId;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SQueryRechargeLog(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SQueryRechargeLog(SQueryRechargeLog arg0){
		copy(arg0);
	}

	public void copy(final SQueryRechargeLog rhs){
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
		startDate = rhs.startDate;
		endDate = rhs.endDate;
		rechargePartnerId = rhs.rechargePartnerId;
		accountType = rhs.accountType;
		cardBatch = rhs.cardBatch;
		cardSerial = rhs.cardSerial;
		rechargeMethod = rhs.rechargeMethod;
		serviceId = rhs.serviceId;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryRechargeLog rhs=(SQueryRechargeLog)rhs0;
		if(!ObjectUtils.equals(etopupSessionId, rhs.etopupSessionId)) return false;
		if(!ObjectUtils.equals(rechargeNumber, rhs.rechargeNumber)) return false;
		if(!ObjectUtils.equals(startDate, rhs.startDate)) return false;
		if(!ObjectUtils.equals(endDate, rhs.endDate)) return false;
		if(!ObjectUtils.equals(rechargePartnerId, rhs.rechargePartnerId)) return false;
		if(!ObjectUtils.equals(accountType, rhs.accountType)) return false;
		if(!ObjectUtils.equals(cardBatch, rhs.cardBatch)) return false;
		if(!ObjectUtils.equals(cardSerial, rhs.cardSerial)) return false;
		if(!ObjectUtils.equals(rechargeMethod, rhs.rechargeMethod)) return false;
		if(!ObjectUtils.equals(serviceId, rhs.serviceId)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(etopupSessionId)
		.append(rechargeNumber)
		.append(startDate)
		.append(endDate)
		.append(rechargePartnerId)
		.append(accountType)
		.append(cardBatch)
		.append(cardSerial)
		.append(rechargeMethod)
		.append(serviceId)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SQueryRechargeLog";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "ETOPUP_SESSION_ID", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "RECHARGE_NUMBER", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "START_DATE", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "END_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "RECHARGE_PARTNER_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "ACCOUNT_TYPE", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "CARD_BATCH", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "CARD_SERIAL", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "RECHARGE_METHOD", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "SERVICE_ID", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SQueryRechargeLog.class, "EXTRA_MAP", 10, Map.class));
}

}