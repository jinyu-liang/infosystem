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
@XmlType(propOrder={"servId","acctId","userNumber","spCode","operatorCode","billFlag","thirdMsisdn","billProp","startTime","alarmType","alarmPara","alarmSource","regionCode","totalFee","updateTime","seqNo","serviceCode","reservice1"})
@Sdl(module="MImsSyncDef")
public class SDeductAlarm extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SERV_ID = "SERV_ID";
	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_USER_NUMBER = "USER_NUMBER";
	public final static String COL_SP_CODE = "SP_CODE";
	public final static String COL_OPERATOR_CODE = "OPERATOR_CODE";
	public final static String COL_BILL_FLAG = "BILL_FLAG";
	public final static String COL_THIRD_MSISDN = "THIRD_MSISDN";
	public final static String COL_BILL_PROP = "BILL_PROP";
	public final static String COL_START_TIME = "START_TIME";
	public final static String COL_ALARM_TYPE = "ALARM_TYPE";
	public final static String COL_ALARM_PARA = "ALARM_PARA";
	public final static String COL_ALARM_SOURCE = "ALARM_SOURCE";
	public final static String COL_REGION_CODE = "REGION_CODE";
	public final static String COL_TOTAL_FEE = "TOTAL_FEE";
	public final static String COL_UPDATE_TIME = "UPDATE_TIME";
	public final static String COL_SEQ_NO = "SEQ_NO";
	public final static String COL_SERVICE_CODE = "SERVICE_CODE";
	public final static String COL_RESERVICE1 = "RESERVICE1";
	public final static int IDX_SERV_ID = 0;
	public final static int IDX_ACCT_ID = 1;
	public final static int IDX_USER_NUMBER = 2;
	public final static int IDX_SP_CODE = 3;
	public final static int IDX_OPERATOR_CODE = 4;
	public final static int IDX_BILL_FLAG = 5;
	public final static int IDX_THIRD_MSISDN = 6;
	public final static int IDX_BILL_PROP = 7;
	public final static int IDX_START_TIME = 8;
	public final static int IDX_ALARM_TYPE = 9;
	public final static int IDX_ALARM_PARA = 10;
	public final static int IDX_ALARM_SOURCE = 11;
	public final static int IDX_REGION_CODE = 12;
	public final static int IDX_TOTAL_FEE = 13;
	public final static int IDX_UPDATE_TIME = 14;
	public final static int IDX_SEQ_NO = 15;
	public final static int IDX_SERVICE_CODE = 16;
	public final static int IDX_RESERVICE1 = 17;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="userNumber")
	@Sdl
	private String userNumber;

	/**
	 * 
	 */
	@XmlElement(name="spCode")
	@Sdl
	private String spCode;

	/**
	 * 
	 */
	@XmlElement(name="operatorCode")
	@Sdl
	private String operatorCode;

	/**
	 * 
	 */
	@XmlElement(name="billFlag")
	@Sdl
	private int billFlag;

	/**
	 * 
	 */
	@XmlElement(name="thirdMsisdn")
	@Sdl
	private String thirdMsisdn;

	/**
	 * 
	 */
	@XmlElement(name="billProp")
	@Sdl
	private int billProp;

	/**
	 * 
	 */
	@XmlElement(name="startTime")
	@Sdl
	private long startTime;

	/**
	 * 
	 */
	@XmlElement(name="alarmType")
	@Sdl
	private int alarmType;

	/**
	 * 
	 */
	@XmlElement(name="alarmPara")
	@Sdl
	private String alarmPara;

	/**
	 * 
	 */
	@XmlElement(name="alarmSource")
	@Sdl
	private int alarmSource;

	/**
	 * 
	 */
	@XmlElement(name="regionCode")
	@Sdl
	private int regionCode;

	/**
	 * 
	 */
	@XmlElement(name="totalFee")
	@Sdl
	private long totalFee;

	/**
	 * 
	 */
	@XmlElement(name="updateTime")
	@Sdl
	private long updateTime;

	/**
	 * 
	 */
	@XmlElement(name="seqNo")
	@Sdl
	private String seqNo;

	/**
	 * 
	 */
	@XmlElement(name="serviceCode")
	@Sdl
	private String serviceCode;

	/**
	 * 
	 */
	@XmlElement(name="reservice1")
	@Sdl
	private String reservice1;

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(0, obj);
	}

	public long getServId(){
		return servId;
	}

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(1, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setUserNumber(String obj){
		this.userNumber = obj;
		onFieldSet(2, obj);
	}

	public String getUserNumber(){
		return userNumber;
	}

	public void setSpCode(String obj){
		this.spCode = obj;
		onFieldSet(3, obj);
	}

	public String getSpCode(){
		return spCode;
	}

	public void setOperatorCode(String obj){
		this.operatorCode = obj;
		onFieldSet(4, obj);
	}

	public String getOperatorCode(){
		return operatorCode;
	}

	public void setBillFlag(int obj){
		this.billFlag = obj;
		onFieldSet(5, obj);
	}

	public int getBillFlag(){
		return billFlag;
	}

	public void setThirdMsisdn(String obj){
		this.thirdMsisdn = obj;
		onFieldSet(6, obj);
	}

	public String getThirdMsisdn(){
		return thirdMsisdn;
	}

	public void setBillProp(int obj){
		this.billProp = obj;
		onFieldSet(7, obj);
	}

	public int getBillProp(){
		return billProp;
	}

	public void setStartTime(long obj){
		this.startTime = obj;
		onFieldSet(8, obj);
	}

	public long getStartTime(){
		return startTime;
	}

	public void setAlarmType(int obj){
		this.alarmType = obj;
		onFieldSet(9, obj);
	}

	public int getAlarmType(){
		return alarmType;
	}

	public void setAlarmPara(String obj){
		this.alarmPara = obj;
		onFieldSet(10, obj);
	}

	public String getAlarmPara(){
		return alarmPara;
	}

	public void setAlarmSource(int obj){
		this.alarmSource = obj;
		onFieldSet(11, obj);
	}

	public int getAlarmSource(){
		return alarmSource;
	}

	public void setRegionCode(int obj){
		this.regionCode = obj;
		onFieldSet(12, obj);
	}

	public int getRegionCode(){
		return regionCode;
	}

	public void setTotalFee(long obj){
		this.totalFee = obj;
		onFieldSet(13, obj);
	}

	public long getTotalFee(){
		return totalFee;
	}

	public void setUpdateTime(long obj){
		this.updateTime = obj;
		onFieldSet(14, obj);
	}

	public long getUpdateTime(){
		return updateTime;
	}

	public void setSeqNo(String obj){
		this.seqNo = obj;
		onFieldSet(15, obj);
	}

	public String getSeqNo(){
		return seqNo;
	}

	public void setServiceCode(String obj){
		this.serviceCode = obj;
		onFieldSet(16, obj);
	}

	public String getServiceCode(){
		return serviceCode;
	}

	public void setReservice1(String obj){
		this.reservice1 = obj;
		onFieldSet(17, obj);
	}

	public String getReservice1(){
		return reservice1;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SDeductAlarm(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 18; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SDeductAlarm(SDeductAlarm arg0){
		copy(arg0);
	}

	public void copy(final SDeductAlarm rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		servId = rhs.servId;
		acctId = rhs.acctId;
		userNumber = rhs.userNumber;
		spCode = rhs.spCode;
		operatorCode = rhs.operatorCode;
		billFlag = rhs.billFlag;
		thirdMsisdn = rhs.thirdMsisdn;
		billProp = rhs.billProp;
		startTime = rhs.startTime;
		alarmType = rhs.alarmType;
		alarmPara = rhs.alarmPara;
		alarmSource = rhs.alarmSource;
		regionCode = rhs.regionCode;
		totalFee = rhs.totalFee;
		updateTime = rhs.updateTime;
		seqNo = rhs.seqNo;
		serviceCode = rhs.serviceCode;
		reservice1 = rhs.reservice1;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDeductAlarm rhs=(SDeductAlarm)rhs0;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userNumber, rhs.userNumber)) return false;
		if(!ObjectUtils.equals(spCode, rhs.spCode)) return false;
		if(!ObjectUtils.equals(operatorCode, rhs.operatorCode)) return false;
		if(!ObjectUtils.equals(billFlag, rhs.billFlag)) return false;
		if(!ObjectUtils.equals(thirdMsisdn, rhs.thirdMsisdn)) return false;
		if(!ObjectUtils.equals(billProp, rhs.billProp)) return false;
		if(!ObjectUtils.equals(startTime, rhs.startTime)) return false;
		if(!ObjectUtils.equals(alarmType, rhs.alarmType)) return false;
		if(!ObjectUtils.equals(alarmPara, rhs.alarmPara)) return false;
		if(!ObjectUtils.equals(alarmSource, rhs.alarmSource)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(totalFee, rhs.totalFee)) return false;
		if(!ObjectUtils.equals(updateTime, rhs.updateTime)) return false;
		if(!ObjectUtils.equals(seqNo, rhs.seqNo)) return false;
		if(!ObjectUtils.equals(serviceCode, rhs.serviceCode)) return false;
		if(!ObjectUtils.equals(reservice1, rhs.reservice1)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(servId)
		.append(acctId)
		.append(userNumber)
		.append(spCode)
		.append(operatorCode)
		.append(billFlag)
		.append(thirdMsisdn)
		.append(billProp)
		.append(startTime)
		.append(alarmType)
		.append(alarmPara)
		.append(alarmSource)
		.append(regionCode)
		.append(totalFee)
		.append(updateTime)
		.append(seqNo)
		.append(serviceCode)
		.append(reservice1)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(18);
public static final long BITS_ALL_MARKER = 0x20000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SDeductAlarm";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "SERV_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "ACCT_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "USER_NUMBER", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "SP_CODE", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "OPERATOR_CODE", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "BILL_FLAG", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "THIRD_MSISDN", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "BILL_PROP", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "START_TIME", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "ALARM_TYPE", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "ALARM_PARA", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "ALARM_SOURCE", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "REGION_CODE", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "TOTAL_FEE", 13, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "UPDATE_TIME", 14, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "SEQ_NO", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "SERVICE_CODE", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SDeductAlarm.class, "RESERVICE1", 17, String.class));
}

}