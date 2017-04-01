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
import java.util.Date;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"soNbr","busiCode","soMode","stepId","soDate","chargeFlag","isnormal","custId","acctId","userId","phoneId","opId","provCode","regionCode","countyCode","orgId","rsoNbr","isMonitor","remark","sourceSystem","notifyFlag"})
@Sdl(module="MImsSyncDef")
public class SOperInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_BUSI_CODE = "BUSI_CODE";
	public final static String COL_SO_MODE = "SO_MODE";
	public final static String COL_STEP_ID = "STEP_ID";
	public final static String COL_SO_DATE = "SO_DATE";
	public final static String COL_CHARGE_FLAG = "CHARGE_FLAG";
	public final static String COL_ISNORMAL = "ISNORMAL";
	public final static String COL_CUST_ID = "CUST_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_USER_ID = "USER_ID";
	public final static String COL_PHONE_ID = "PHONE_ID";
	public final static String COL_OP_ID = "OP_ID";
	public final static String COL_PROV_CODE = "PROV_CODE";
	public final static String COL_REGION_CODE = "REGION_CODE";
	public final static String COL_COUNTY_CODE = "COUNTY_CODE";
	public final static String COL_ORG_ID = "ORG_ID";
	public final static String COL_RSO_NBR = "RSO_NBR";
	public final static String COL_IS_MONITOR = "IS_MONITOR";
	public final static String COL_REMARK = "REMARK";
	public final static String COL_SOURCE_SYSTEM = "SOURCE_SYSTEM";
	public final static String COL_NOTIFY_FLAG = "NOTIFY_FLAG";
	public final static int IDX_SO_NBR = 0;
	public final static int IDX_BUSI_CODE = 1;
	public final static int IDX_SO_MODE = 2;
	public final static int IDX_STEP_ID = 3;
	public final static int IDX_SO_DATE = 4;
	public final static int IDX_CHARGE_FLAG = 5;
	public final static int IDX_ISNORMAL = 6;
	public final static int IDX_CUST_ID = 7;
	public final static int IDX_ACCT_ID = 8;
	public final static int IDX_USER_ID = 9;
	public final static int IDX_PHONE_ID = 10;
	public final static int IDX_OP_ID = 11;
	public final static int IDX_PROV_CODE = 12;
	public final static int IDX_REGION_CODE = 13;
	public final static int IDX_COUNTY_CODE = 14;
	public final static int IDX_ORG_ID = 15;
	public final static int IDX_RSO_NBR = 16;
	public final static int IDX_IS_MONITOR = 17;
	public final static int IDX_REMARK = 18;
	public final static int IDX_SOURCE_SYSTEM = 19;
	public final static int IDX_NOTIFY_FLAG = 20;

	/**
	 * 
	 */
	@XmlElement(name="soNbr")
	@Sdl
	private String soNbr;

	/**
	 * 
	 */
	@XmlElement(name="busiCode")
	@Sdl
	private int busiCode;

	/**
	 * 
	 */
	@XmlElement(name="soMode")
	@Sdl
	private short soMode;

	/**
	 * 
	 */
	@XmlElement(name="stepId")
	@Sdl
	private short stepId;

	/**
	 * 
	 */
	@XmlElement(name="soDate")
	@Sdl
	private Date soDate;

	/**
	 * 
	 */
	@XmlElement(name="chargeFlag")
	@Sdl
	private short chargeFlag;

	/**
	 * 
	 */
	@XmlElement(name="isnormal")
	@Sdl
	private short isnormal;

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
	@XmlElement(name="opId")
	@Sdl
	private int opId;

	/**
	 * 
	 */
	@XmlElement(name="provCode")
	@Sdl
	private short provCode;

	/**
	 * 
	 */
	@XmlElement(name="regionCode")
	@Sdl
	private short regionCode;

	/**
	 * 
	 */
	@XmlElement(name="countyCode")
	@Sdl
	private short countyCode;

	/**
	 * 
	 */
	@XmlElement(name="orgId")
	@Sdl
	private int orgId;

	/**
	 * 
	 */
	@XmlElement(name="rsoNbr")
	@Sdl
	private String rsoNbr;

	/**
	 * 
	 */
	@XmlElement(name="isMonitor")
	@Sdl
	private short isMonitor;

	/**
	 * 
	 */
	@XmlElement(name="remark")
	@Sdl
	private String remark;

	/**
	 * 
	 */
	@XmlElement(name="sourceSystem")
	@Sdl
	private String sourceSystem;

	/**
	 * 
	 */
	@XmlElement(name="notifyFlag")
	@Sdl
	private short notifyFlag;

	public void setSoNbr(String obj){
		this.soNbr = obj;
		onFieldSet(0, obj);
	}

	public String getSoNbr(){
		return soNbr;
	}

	public void setBusiCode(int obj){
		this.busiCode = obj;
		onFieldSet(1, obj);
	}

	public int getBusiCode(){
		return busiCode;
	}

	public void setSoMode(short obj){
		this.soMode = obj;
		onFieldSet(2, obj);
	}

	public short getSoMode(){
		return soMode;
	}

	public void setStepId(short obj){
		this.stepId = obj;
		onFieldSet(3, obj);
	}

	public short getStepId(){
		return stepId;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
		onFieldSet(4, obj);
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setChargeFlag(short obj){
		this.chargeFlag = obj;
		onFieldSet(5, obj);
	}

	public short getChargeFlag(){
		return chargeFlag;
	}

	public void setIsnormal(short obj){
		this.isnormal = obj;
		onFieldSet(6, obj);
	}

	public short getIsnormal(){
		return isnormal;
	}

	public void setCustId(long obj){
		this.custId = obj;
		onFieldSet(7, obj);
	}

	public long getCustId(){
		return custId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(8, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setUserId(long obj){
		this.userId = obj;
		onFieldSet(9, obj);
	}

	public long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
		onFieldSet(10, obj);
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setOpId(int obj){
		this.opId = obj;
		onFieldSet(11, obj);
	}

	public int getOpId(){
		return opId;
	}

	public void setProvCode(short obj){
		this.provCode = obj;
		onFieldSet(12, obj);
	}

	public short getProvCode(){
		return provCode;
	}

	public void setRegionCode(short obj){
		this.regionCode = obj;
		onFieldSet(13, obj);
	}

	public short getRegionCode(){
		return regionCode;
	}

	public void setCountyCode(short obj){
		this.countyCode = obj;
		onFieldSet(14, obj);
	}

	public short getCountyCode(){
		return countyCode;
	}

	public void setOrgId(int obj){
		this.orgId = obj;
		onFieldSet(15, obj);
	}

	public int getOrgId(){
		return orgId;
	}

	public void setRsoNbr(String obj){
		this.rsoNbr = obj;
		onFieldSet(16, obj);
	}

	public String getRsoNbr(){
		return rsoNbr;
	}

	public void setIsMonitor(short obj){
		this.isMonitor = obj;
		onFieldSet(17, obj);
	}

	public short getIsMonitor(){
		return isMonitor;
	}

	public void setRemark(String obj){
		this.remark = obj;
		onFieldSet(18, obj);
	}

	public String getRemark(){
		return remark;
	}

	public void setSourceSystem(String obj){
		this.sourceSystem = obj;
		onFieldSet(19, obj);
	}

	public String getSourceSystem(){
		return sourceSystem;
	}

	public void setNotifyFlag(short obj){
		this.notifyFlag = obj;
		onFieldSet(20, obj);
	}

	public short getNotifyFlag(){
		return notifyFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOperInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 21; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOperInfo(SOperInfo arg0){
		copy(arg0);
	}

	public void copy(final SOperInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		soNbr = rhs.soNbr;
		busiCode = rhs.busiCode;
		soMode = rhs.soMode;
		stepId = rhs.stepId;
		soDate = rhs.soDate;
		chargeFlag = rhs.chargeFlag;
		isnormal = rhs.isnormal;
		custId = rhs.custId;
		acctId = rhs.acctId;
		userId = rhs.userId;
		phoneId = rhs.phoneId;
		opId = rhs.opId;
		provCode = rhs.provCode;
		regionCode = rhs.regionCode;
		countyCode = rhs.countyCode;
		orgId = rhs.orgId;
		rsoNbr = rhs.rsoNbr;
		isMonitor = rhs.isMonitor;
		remark = rhs.remark;
		sourceSystem = rhs.sourceSystem;
		notifyFlag = rhs.notifyFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOperInfo rhs=(SOperInfo)rhs0;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		if(!ObjectUtils.equals(soMode, rhs.soMode)) return false;
		if(!ObjectUtils.equals(stepId, rhs.stepId)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(chargeFlag, rhs.chargeFlag)) return false;
		if(!ObjectUtils.equals(isnormal, rhs.isnormal)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(provCode, rhs.provCode)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(countyCode, rhs.countyCode)) return false;
		if(!ObjectUtils.equals(orgId, rhs.orgId)) return false;
		if(!ObjectUtils.equals(rsoNbr, rhs.rsoNbr)) return false;
		if(!ObjectUtils.equals(isMonitor, rhs.isMonitor)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(sourceSystem, rhs.sourceSystem)) return false;
		if(!ObjectUtils.equals(notifyFlag, rhs.notifyFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(soNbr)
		.append(busiCode)
		.append(soMode)
		.append(stepId)
		.append(soDate)
		.append(chargeFlag)
		.append(isnormal)
		.append(custId)
		.append(acctId)
		.append(userId)
		.append(phoneId)
		.append(opId)
		.append(provCode)
		.append(regionCode)
		.append(countyCode)
		.append(orgId)
		.append(rsoNbr)
		.append(isMonitor)
		.append(remark)
		.append(sourceSystem)
		.append(notifyFlag)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(21);
public static final long BITS_ALL_MARKER = 0x100000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SOperInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "SO_NBR", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "BUSI_CODE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "SO_MODE", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "STEP_ID", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "SO_DATE", 4, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "CHARGE_FLAG", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "ISNORMAL", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "CUST_ID", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "ACCT_ID", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "USER_ID", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "PHONE_ID", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "OP_ID", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "PROV_CODE", 12, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "REGION_CODE", 13, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "COUNTY_CODE", 14, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "ORG_ID", 15, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "RSO_NBR", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "IS_MONITOR", 17, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "REMARK", 18, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "SOURCE_SYSTEM", 19, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOperInfo.class, "NOTIFY_FLAG", 20, short.class));
}

}