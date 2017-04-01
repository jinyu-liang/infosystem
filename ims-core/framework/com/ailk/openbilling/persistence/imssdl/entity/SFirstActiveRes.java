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
@XmlType(propOrder={"userId","phoneId","acctId","rewardId","activationTime","orgLocation","location","pricePlanId","smsLanguage","ivrLanguage","ussdLanguage","soNbr","balance","delProdList","modProdList","addProdList"})
@Sdl(module="MImsSyncDef")
public class SFirstActiveRes extends CsdlStructObject implements IComplexEntity{

	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_REWARD_ID = "REWARD_ID";
	public final static String COL_ACTIVATION_TIME = "ACTIVATION_TIME";
	public final static String COL_ORG_LOCATION = "ORG_LOCATION";
	public final static String COL_LOCATION = "LOCATION";
	public final static String COL_PRICE_PLAN_ID = "PRICE_PLAN_ID";
	public final static String COL_SMS_LANGUAGE = "SMS_LANGUAGE";
	public final static String COL_IVR_LANGUAGE = "IVR_LANGUAGE";
	public final static String COL_USSD_LANGUAGE = "USSD_LANGUAGE";
	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_BALANCE = "BALANCE";
	public final static String COL_DEL_PROD_LIST = "DEL_PROD_LIST";
	public final static String COL_MOD_PROD_LIST = "MOD_PROD_LIST";
	public final static String COL_ADD_PROD_LIST = "ADD_PROD_LIST";
	public final static int IDX_USER_ID = 0;
	public final static int IDX_PHONE_ID = 1;
	public final static int IDX_ACCT_ID = 2;
	public final static int IDX_REWARD_ID = 3;
	public final static int IDX_ACTIVATION_TIME = 4;
	public final static int IDX_ORG_LOCATION = 5;
	public final static int IDX_LOCATION = 6;
	public final static int IDX_PRICE_PLAN_ID = 7;
	public final static int IDX_SMS_LANGUAGE = 8;
	public final static int IDX_IVR_LANGUAGE = 9;
	public final static int IDX_USSD_LANGUAGE = 10;
	public final static int IDX_SO_NBR = 11;
	public final static int IDX_BALANCE = 12;
	public final static int IDX_DEL_PROD_LIST = 13;
	public final static int IDX_MOD_PROD_LIST = 14;
	public final static int IDX_ADD_PROD_LIST = 15;

	/**
	 * 
	 */
	@XmlElement(name="balance")
	@Sdl
	private SBalance balance;

	/**
	 * 
	 */
	@XmlElement(name="delProdList")
	@Sdl
	private List<SProdInfo> delProdList;

	/**
	 * 
	 */
	@XmlElement(name="modProdList")
	@Sdl
	private List<SProdInfo> modProdList;

	/**
	 * 
	 */
	@XmlElement(name="addProdList")
	@Sdl
	private List<SProdInfo> addProdList;

	/**
	 * 
	 */
	@XmlElement(name="userId")
	@Sdl
	private long userId;

	/**
	 * 
	 */
	@XmlElement(name="phoneId")
	@Sdl
	private String phoneId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="rewardId")
	@Sdl
	private int rewardId;

	/**
	 * 
	 */
	@XmlElement(name="activationTime")
	@Sdl
	private long activationTime;

	/**
	 * 
	 */
	@XmlElement(name="orgLocation")
	@Sdl
	private String orgLocation;

	/**
	 * 
	 */
	@XmlElement(name="location")
	@Sdl
	private String location;

	/**
	 * 
	 */
	@XmlElement(name="pricePlanId")
	@Sdl
	private int pricePlanId;

	/**
	 * 
	 */
	@XmlElement(name="smsLanguage")
	@Sdl
	private short smsLanguage;

	/**
	 * 
	 */
	@XmlElement(name="ivrLanguage")
	@Sdl
	private short ivrLanguage;

	/**
	 * 
	 */
	@XmlElement(name="ussdLanguage")
	@Sdl
	private short ussdLanguage;

	/**
	 * 
	 */
	@XmlElement(name="soNbr")
	@Sdl
	private long soNbr;

	public void setBalance(SBalance obj){
		this.balance = obj;
		onFieldSet(12, obj);
	}

	public SBalance getBalance(){
		return balance;
	}

	public void setDelProdList(List<SProdInfo> obj){
		this.delProdList = obj;
		onFieldSet(13, obj);
	}

	public List<SProdInfo> getDelProdList(){
		return delProdList;
	}

	public void setModProdList(List<SProdInfo> obj){
		this.modProdList = obj;
		onFieldSet(14, obj);
	}

	public List<SProdInfo> getModProdList(){
		return modProdList;
	}

	public void setAddProdList(List<SProdInfo> obj){
		this.addProdList = obj;
		onFieldSet(15, obj);
	}

	public List<SProdInfo> getAddProdList(){
		return addProdList;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(0, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(1, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(2, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setRewardId(int obj){
		this.rewardId = obj;
		onFieldSet(3, obj);
	}

	public int getRewardId(){
		return rewardId;
	}

	public void setActivationTime(long obj){
		this.activationTime = obj;
		onFieldSet(4, obj);
	}

	public long getActivationTime(){
		return activationTime;
	}

	public void setOrgLocation(String obj){
		this.orgLocation = obj;
		onFieldSet(5, obj);
	}

	public String getOrgLocation(){
		return orgLocation;
	}

	public void setLocation(String obj){
		this.location = obj;
		onFieldSet(6, obj);
	}

	public String getLocation(){
		return location;
	}

	public void setPricePlanId(int obj){
		this.pricePlanId = obj;
		onFieldSet(7, obj);
	}

	public int getPricePlanId(){
		return pricePlanId;
	}

	public void setSmsLanguage(short obj){
		this.smsLanguage = obj;
		onFieldSet(8, obj);
	}

	public short getSmsLanguage(){
		return smsLanguage;
	}

	public void setIvrLanguage(short obj){
		this.ivrLanguage = obj;
		onFieldSet(9, obj);
	}

	public short getIvrLanguage(){
		return ivrLanguage;
	}

	public void setUssdLanguage(short obj){
		this.ussdLanguage = obj;
		onFieldSet(10, obj);
	}

	public short getUssdLanguage(){
		return ussdLanguage;
	}

	public void setSoNbr(long obj){
		this.soNbr = obj;
		onFieldSet(11, obj);
	}

	public long getSoNbr(){
		return soNbr;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SFirstActiveRes(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 16; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SFirstActiveRes(SFirstActiveRes arg0){
		copy(arg0);
	}

	public void copy(final SFirstActiveRes rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		acctId = rhs.acctId;
		rewardId = rhs.rewardId;
		activationTime = rhs.activationTime;
		orgLocation = rhs.orgLocation;
		location = rhs.location;
		pricePlanId = rhs.pricePlanId;
		smsLanguage = rhs.smsLanguage;
		ivrLanguage = rhs.ivrLanguage;
		ussdLanguage = rhs.ussdLanguage;
		soNbr = rhs.soNbr;
		balance = rhs.balance;
		delProdList = rhs.delProdList;
		modProdList = rhs.modProdList;
		addProdList = rhs.addProdList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFirstActiveRes rhs=(SFirstActiveRes)rhs0;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(rewardId, rhs.rewardId)) return false;
		if(!ObjectUtils.equals(activationTime, rhs.activationTime)) return false;
		if(!ObjectUtils.equals(orgLocation, rhs.orgLocation)) return false;
		if(!ObjectUtils.equals(location, rhs.location)) return false;
		if(!ObjectUtils.equals(pricePlanId, rhs.pricePlanId)) return false;
		if(!ObjectUtils.equals(smsLanguage, rhs.smsLanguage)) return false;
		if(!ObjectUtils.equals(ivrLanguage, rhs.ivrLanguage)) return false;
		if(!ObjectUtils.equals(ussdLanguage, rhs.ussdLanguage)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(balance, rhs.balance)) return false;
		if(!ObjectUtils.equals(delProdList, rhs.delProdList)) return false;
		if(!ObjectUtils.equals(modProdList, rhs.modProdList)) return false;
		if(!ObjectUtils.equals(addProdList, rhs.addProdList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userId)
		.append(phoneId)
		.append(acctId)
		.append(rewardId)
		.append(activationTime)
		.append(orgLocation)
		.append(location)
		.append(pricePlanId)
		.append(smsLanguage)
		.append(ivrLanguage)
		.append(ussdLanguage)
		.append(soNbr)
		.append(balance)
		.append(delProdList)
		.append(modProdList)
		.append(addProdList)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(16);
public static final long BITS_ALL_MARKER = 0x8000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SFirstActiveRes";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "USER_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "PHONE_ID", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "ACCT_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "REWARD_ID", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "ACTIVATION_TIME", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "ORG_LOCATION", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "LOCATION", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "PRICE_PLAN_ID", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "SMS_LANGUAGE", 8, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "IVR_LANGUAGE", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "USSD_LANGUAGE", 10, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "SO_NBR", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "BALANCE", 12, SBalance.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "DEL_PROD_LIST", 13, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "MOD_PROD_LIST", 14, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SFirstActiveRes.class, "ADD_PROD_LIST", 15, List.class));
}

}