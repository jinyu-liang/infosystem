package com.ailk.openbilling.persistence.imsts.entity;

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
@XmlType(propOrder={"id","resourceId","busiType","identity","servType","spCode","operatorCode","billFlag","thirdMsisdn","billProp","regionCode","confirmResult","confirmTime","alarmTime","alarmDoneCode","sequenceNo","srcType","extendFlag","validDate","expireDate","soDate","soNbr","sts","serviceCode","remark","createDate","dealSts","dealDate","dealCount","errorCode","errorMsg"})
@Sdl(module="MImsTsDef")
public class SUserConfirm extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ID = "ID";
	public final static String COL_RESOURCE_ID = "RESOURCE_ID";
	public final static String COL_BUSI_TYPE = "BUSI_TYPE";
	public final static String COL_IDENTITY = "IDENTITY";
	public final static String COL_SERV_TYPE = "SERV_TYPE";
	public final static String COL_SP_CODE = "SP_CODE";
	public final static String COL_OPERATOR_CODE = "OPERATOR_CODE";
	public final static String COL_BILL_FLAG = "BILL_FLAG";
	public final static String COL_THIRD_MSISDN = "THIRD_MSISDN";
	public final static String COL_BILL_PROP = "BILL_PROP";
	public final static String COL_REGION_CODE = "REGION_CODE";
	public final static String COL_CONFIRM_RESULT = "CONFIRM_RESULT";
	public final static String COL_CONFIRM_TIME = "CONFIRM_TIME";
	public final static String COL_ALARM_TIME = "ALARM_TIME";
	public final static String COL_ALARM_DONE_CODE = "ALARM_DONE_CODE";
	public final static String COL_SEQUENCE_NO = "SEQUENCE_NO";
	public final static String COL_SRC_TYPE = "SRC_TYPE";
	public final static String COL_EXTEND_FLAG = "EXTEND_FLAG";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static String COL_SO_DATE = "SO_DATE";
	public final static String COL_SO_NBR = "SO_NBR";
	public final static String COL_STS = "STS";
	public final static String COL_SERVICE_CODE = "SERVICE_CODE";
	public final static String COL_REMARK = "REMARK";
	public final static String COL_CREATE_DATE = "CREATE_DATE";
	public final static String COL_DEAL_STS = "DEAL_STS";
	public final static String COL_DEAL_DATE = "DEAL_DATE";
	public final static String COL_DEAL_COUNT = "DEAL_COUNT";
	public final static String COL_ERROR_CODE = "ERROR_CODE";
	public final static String COL_ERROR_MSG = "ERROR_MSG";
	public final static int IDX_ID = 0;
	public final static int IDX_RESOURCE_ID = 1;
	public final static int IDX_BUSI_TYPE = 2;
	public final static int IDX_IDENTITY = 3;
	public final static int IDX_SERV_TYPE = 4;
	public final static int IDX_SP_CODE = 5;
	public final static int IDX_OPERATOR_CODE = 6;
	public final static int IDX_BILL_FLAG = 7;
	public final static int IDX_THIRD_MSISDN = 8;
	public final static int IDX_BILL_PROP = 9;
	public final static int IDX_REGION_CODE = 10;
	public final static int IDX_CONFIRM_RESULT = 11;
	public final static int IDX_CONFIRM_TIME = 12;
	public final static int IDX_ALARM_TIME = 13;
	public final static int IDX_ALARM_DONE_CODE = 14;
	public final static int IDX_SEQUENCE_NO = 15;
	public final static int IDX_SRC_TYPE = 16;
	public final static int IDX_EXTEND_FLAG = 17;
	public final static int IDX_VALID_DATE = 18;
	public final static int IDX_EXPIRE_DATE = 19;
	public final static int IDX_SO_DATE = 20;
	public final static int IDX_SO_NBR = 21;
	public final static int IDX_STS = 22;
	public final static int IDX_SERVICE_CODE = 23;
	public final static int IDX_REMARK = 24;
	public final static int IDX_CREATE_DATE = 25;
	public final static int IDX_DEAL_STS = 26;
	public final static int IDX_DEAL_DATE = 27;
	public final static int IDX_DEAL_COUNT = 28;
	public final static int IDX_ERROR_CODE = 29;
	public final static int IDX_ERROR_MSG = 30;

	/**
	 * 
	 */
	@XmlElement(name="id")
	@Sdl
	private long id;

	/**
	 * 
	 */
	@XmlElement(name="resourceId")
	@Sdl
	private long resourceId;

	/**
	 * 
	 */
	@XmlElement(name="busiType")
	@Sdl
	private int busiType;

	/**
	 * 
	 */
	@XmlElement(name="identity")
	@Sdl
	private String identity;

	/**
	 * 
	 */
	@XmlElement(name="servType")
	@Sdl
	private int servType;

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
	@XmlElement(name="regionCode")
	@Sdl
	private int regionCode;

	/**
	 * 
	 */
	@XmlElement(name="confirmResult")
	@Sdl
	private int confirmResult;

	/**
	 * 
	 */
	@XmlElement(name="confirmTime")
	@Sdl
	private Date confirmTime;

	/**
	 * 
	 */
	@XmlElement(name="alarmTime")
	@Sdl
	private Date alarmTime;

	/**
	 * 
	 */
	@XmlElement(name="alarmDoneCode")
	@Sdl
	private long alarmDoneCode;

	/**
	 * 
	 */
	@XmlElement(name="sequenceNo")
	@Sdl
	private String sequenceNo;

	/**
	 * 
	 */
	@XmlElement(name="srcType")
	@Sdl
	private String srcType;

	/**
	 * 
	 */
	@XmlElement(name="extendFlag")
	@Sdl
	private int extendFlag;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private Date validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private Date expireDate;

	/**
	 * 
	 */
	@XmlElement(name="soDate")
	@Sdl
	private Date soDate;

	/**
	 * 
	 */
	@XmlElement(name="soNbr")
	@Sdl
	private long soNbr;

	/**
	 * 
	 */
	@XmlElement(name="sts")
	@Sdl
	private int sts;

	/**
	 * 
	 */
	@XmlElement(name="serviceCode")
	@Sdl
	private String serviceCode;

	/**
	 * 
	 */
	@XmlElement(name="remark")
	@Sdl
	private String remark;

	/**
	 * 
	 */
	@XmlElement(name="createDate")
	@Sdl
	private Date createDate;

	/**
	 * 
	 */
	@XmlElement(name="dealSts")
	@Sdl
	private int dealSts;

	/**
	 * 
	 */
	@XmlElement(name="dealDate")
	@Sdl
	private Date dealDate;

	/**
	 * 
	 */
	@XmlElement(name="dealCount")
	@Sdl
	private int dealCount;

	/**
	 * 
	 */
	@XmlElement(name="errorCode")
	@Sdl
	private int errorCode;

	/**
	 * 
	 */
	@XmlElement(name="errorMsg")
	@Sdl
	private String errorMsg;

	public void setId(long obj){
		this.id = obj;
		onFieldSet(0, obj);
	}

	public long getId(){
		return id;
	}

	public void setResourceId(long obj){
		this.resourceId = obj;
		onFieldSet(1, obj);
	}

	public long getResourceId(){
		return resourceId;
	}

	public void setBusiType(int obj){
		this.busiType = obj;
		onFieldSet(2, obj);
	}

	public int getBusiType(){
		return busiType;
	}

	public void setIdentity(String obj){
		this.identity = obj;
		onFieldSet(3, obj);
	}

	public String getIdentity(){
		return identity;
	}

	public void setServType(int obj){
		this.servType = obj;
		onFieldSet(4, obj);
	}

	public int getServType(){
		return servType;
	}

	public void setSpCode(String obj){
		this.spCode = obj;
		onFieldSet(5, obj);
	}

	public String getSpCode(){
		return spCode;
	}

	public void setOperatorCode(String obj){
		this.operatorCode = obj;
		onFieldSet(6, obj);
	}

	public String getOperatorCode(){
		return operatorCode;
	}

	public void setBillFlag(int obj){
		this.billFlag = obj;
		onFieldSet(7, obj);
	}

	public int getBillFlag(){
		return billFlag;
	}

	public void setThirdMsisdn(String obj){
		this.thirdMsisdn = obj;
		onFieldSet(8, obj);
	}

	public String getThirdMsisdn(){
		return thirdMsisdn;
	}

	public void setBillProp(int obj){
		this.billProp = obj;
		onFieldSet(9, obj);
	}

	public int getBillProp(){
		return billProp;
	}

	public void setRegionCode(int obj){
		this.regionCode = obj;
		onFieldSet(10, obj);
	}

	public int getRegionCode(){
		return regionCode;
	}

	public void setConfirmResult(int obj){
		this.confirmResult = obj;
		onFieldSet(11, obj);
	}

	public int getConfirmResult(){
		return confirmResult;
	}

	public void setConfirmTime(Date obj){
		this.confirmTime = obj;
		onFieldSet(12, obj);
	}

	public Date getConfirmTime(){
		return confirmTime;
	}

	public void setAlarmTime(Date obj){
		this.alarmTime = obj;
		onFieldSet(13, obj);
	}

	public Date getAlarmTime(){
		return alarmTime;
	}

	public void setAlarmDoneCode(long obj){
		this.alarmDoneCode = obj;
		onFieldSet(14, obj);
	}

	public long getAlarmDoneCode(){
		return alarmDoneCode;
	}

	public void setSequenceNo(String obj){
		this.sequenceNo = obj;
		onFieldSet(15, obj);
	}

	public String getSequenceNo(){
		return sequenceNo;
	}

	public void setSrcType(String obj){
		this.srcType = obj;
		onFieldSet(16, obj);
	}

	public String getSrcType(){
		return srcType;
	}

	public void setExtendFlag(int obj){
		this.extendFlag = obj;
		onFieldSet(17, obj);
	}

	public int getExtendFlag(){
		return extendFlag;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(18, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(19, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
		onFieldSet(20, obj);
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setSoNbr(long obj){
		this.soNbr = obj;
		onFieldSet(21, obj);
	}

	public long getSoNbr(){
		return soNbr;
	}

	public void setSts(int obj){
		this.sts = obj;
		onFieldSet(22, obj);
	}

	public int getSts(){
		return sts;
	}

	public void setServiceCode(String obj){
		this.serviceCode = obj;
		onFieldSet(23, obj);
	}

	public String getServiceCode(){
		return serviceCode;
	}

	public void setRemark(String obj){
		this.remark = obj;
		onFieldSet(24, obj);
	}

	public String getRemark(){
		return remark;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
		onFieldSet(25, obj);
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setDealSts(int obj){
		this.dealSts = obj;
		onFieldSet(26, obj);
	}

	public int getDealSts(){
		return dealSts;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
		onFieldSet(27, obj);
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setDealCount(int obj){
		this.dealCount = obj;
		onFieldSet(28, obj);
	}

	public int getDealCount(){
		return dealCount;
	}

	public void setErrorCode(int obj){
		this.errorCode = obj;
		onFieldSet(29, obj);
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setErrorMsg(String obj){
		this.errorMsg = obj;
		onFieldSet(30, obj);
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUserConfirm(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 31; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUserConfirm(SUserConfirm arg0){
		copy(arg0);
	}

	public void copy(final SUserConfirm rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		id = rhs.id;
		resourceId = rhs.resourceId;
		busiType = rhs.busiType;
		identity = rhs.identity;
		servType = rhs.servType;
		spCode = rhs.spCode;
		operatorCode = rhs.operatorCode;
		billFlag = rhs.billFlag;
		thirdMsisdn = rhs.thirdMsisdn;
		billProp = rhs.billProp;
		regionCode = rhs.regionCode;
		confirmResult = rhs.confirmResult;
		confirmTime = rhs.confirmTime;
		alarmTime = rhs.alarmTime;
		alarmDoneCode = rhs.alarmDoneCode;
		sequenceNo = rhs.sequenceNo;
		srcType = rhs.srcType;
		extendFlag = rhs.extendFlag;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
		soDate = rhs.soDate;
		soNbr = rhs.soNbr;
		sts = rhs.sts;
		serviceCode = rhs.serviceCode;
		remark = rhs.remark;
		createDate = rhs.createDate;
		dealSts = rhs.dealSts;
		dealDate = rhs.dealDate;
		dealCount = rhs.dealCount;
		errorCode = rhs.errorCode;
		errorMsg = rhs.errorMsg;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserConfirm rhs=(SUserConfirm)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(busiType, rhs.busiType)) return false;
		if(!ObjectUtils.equals(identity, rhs.identity)) return false;
		if(!ObjectUtils.equals(servType, rhs.servType)) return false;
		if(!ObjectUtils.equals(spCode, rhs.spCode)) return false;
		if(!ObjectUtils.equals(operatorCode, rhs.operatorCode)) return false;
		if(!ObjectUtils.equals(billFlag, rhs.billFlag)) return false;
		if(!ObjectUtils.equals(thirdMsisdn, rhs.thirdMsisdn)) return false;
		if(!ObjectUtils.equals(billProp, rhs.billProp)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(confirmResult, rhs.confirmResult)) return false;
		if(!ObjectUtils.equals(confirmTime, rhs.confirmTime)) return false;
		if(!ObjectUtils.equals(alarmTime, rhs.alarmTime)) return false;
		if(!ObjectUtils.equals(alarmDoneCode, rhs.alarmDoneCode)) return false;
		if(!ObjectUtils.equals(sequenceNo, rhs.sequenceNo)) return false;
		if(!ObjectUtils.equals(srcType, rhs.srcType)) return false;
		if(!ObjectUtils.equals(extendFlag, rhs.extendFlag)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(serviceCode, rhs.serviceCode)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(dealSts, rhs.dealSts)) return false;
		if(!ObjectUtils.equals(dealDate, rhs.dealDate)) return false;
		if(!ObjectUtils.equals(dealCount, rhs.dealCount)) return false;
		if(!ObjectUtils.equals(errorCode, rhs.errorCode)) return false;
		if(!ObjectUtils.equals(errorMsg, rhs.errorMsg)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(resourceId)
		.append(busiType)
		.append(identity)
		.append(servType)
		.append(spCode)
		.append(operatorCode)
		.append(billFlag)
		.append(thirdMsisdn)
		.append(billProp)
		.append(regionCode)
		.append(confirmResult)
		.append(confirmTime)
		.append(alarmTime)
		.append(alarmDoneCode)
		.append(sequenceNo)
		.append(srcType)
		.append(extendFlag)
		.append(validDate)
		.append(expireDate)
		.append(soDate)
		.append(soNbr)
		.append(sts)
		.append(serviceCode)
		.append(remark)
		.append(createDate)
		.append(dealSts)
		.append(dealDate)
		.append(dealCount)
		.append(errorCode)
		.append(errorMsg)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(31);
public static final long BITS_ALL_MARKER = 0x40000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsts.entity.SUserConfirm";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "RESOURCE_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "BUSI_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "IDENTITY", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SERV_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SP_CODE", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "OPERATOR_CODE", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "BILL_FLAG", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "THIRD_MSISDN", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "BILL_PROP", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "REGION_CODE", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "CONFIRM_RESULT", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "CONFIRM_TIME", 12, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "ALARM_TIME", 13, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "ALARM_DONE_CODE", 14, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SEQUENCE_NO", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SRC_TYPE", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "EXTEND_FLAG", 17, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "VALID_DATE", 18, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "EXPIRE_DATE", 19, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SO_DATE", 20, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SO_NBR", 21, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "STS", 22, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "SERVICE_CODE", 23, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "REMARK", 24, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "CREATE_DATE", 25, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "DEAL_STS", 26, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "DEAL_DATE", 27, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "DEAL_COUNT", 28, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "ERROR_CODE", 29, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUserConfirm.class, "ERROR_MSG", 30, String.class));
}

}