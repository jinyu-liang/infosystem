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
@XmlType(propOrder={"redirectAddress","dealDatetime","apnniId","rgType","rggGroupId","rggFinishedFlag","bearerId","upRating","dlRating","ruleName","ruleBaseName","reportReason","redirectFlag","bearerCode","gprsProcessId","noRedirect"})
@Sdl(module="MXdr")
public class SGprsInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_REDIRECT_ADDRESS = "REDIRECT_ADDRESS";
	public final static String COL_DEAL_DATETIME = "DEAL_DATETIME";
	public final static String COL_APNNI_ID = "APNNI_ID";
	public final static String COL_RG_TYPE = "RG_TYPE";
	public final static String COL_RGG_GROUP_ID = "RGG_GROUP_ID";
	public final static String COL_RGG_FINISHED_FLAG = "RGG_FINISHED_FLAG";
	public final static String COL_BEARER_ID = "BEARER_ID";
	public final static String COL_UP_RATING = "UP_RATING";
	public final static String COL_DL_RATING = "DL_RATING";
	public final static String COL_RULE_NAME = "RULE_NAME";
	public final static String COL_RULE_BASE_NAME = "RULE_BASE_NAME";
	public final static String COL_REPORT_REASON = "REPORT_REASON";
	public final static String COL_REDIRECT_FLAG = "REDIRECT_FLAG";
	public final static String COL_BEARER_CODE = "BEARER_CODE";
	public final static String COL_GPRS_PROCESS_ID = "GPRS_PROCESS_ID";
	public final static String COL_NO_REDIRECT = "NO_REDIRECT";
	public final static int IDX_REDIRECT_ADDRESS = 0;
	public final static int IDX_DEAL_DATETIME = 1;
	public final static int IDX_APNNI_ID = 2;
	public final static int IDX_RG_TYPE = 3;
	public final static int IDX_RGG_GROUP_ID = 4;
	public final static int IDX_RGG_FINISHED_FLAG = 5;
	public final static int IDX_BEARER_ID = 6;
	public final static int IDX_UP_RATING = 7;
	public final static int IDX_DL_RATING = 8;
	public final static int IDX_RULE_NAME = 9;
	public final static int IDX_RULE_BASE_NAME = 10;
	public final static int IDX_REPORT_REASON = 11;
	public final static int IDX_REDIRECT_FLAG = 12;
	public final static int IDX_BEARER_CODE = 13;
	public final static int IDX_GPRS_PROCESS_ID = 14;
	public final static int IDX_NO_REDIRECT = 15;

	/**
	 * 
	 */
	@XmlElement(name="redirectAddress")
	@Sdl
	private String redirectAddress;

	/**
	 * 
	 */
	@XmlElement(name="dealDatetime")
	@Sdl
	private long dealDatetime;

	/**
	 * 
	 */
	@XmlElement(name="apnniId")
	@Sdl
	private int apnniId;

	/**
	 * 
	 */
	@XmlElement(name="rgType")
	@Sdl
	private int rgType;

	/**
	 * 
	 */
	@XmlElement(name="rggGroupId")
	@Sdl
	private int rggGroupId;

	/**
	 * 
	 */
	@XmlElement(name="rggFinishedFlag")
	@Sdl
	private int rggFinishedFlag;

	/**
	 * 
	 */
	@XmlElement(name="bearerId")
	@Sdl
	private int bearerId;

	/**
	 * 
	 */
	@XmlElement(name="upRating")
	@Sdl
	private int upRating;

	/**
	 * 
	 */
	@XmlElement(name="dlRating")
	@Sdl
	private int dlRating;

	/**
	 * 
	 */
	@XmlElement(name="ruleName")
	@Sdl
	private String ruleName;

	/**
	 * 
	 */
	@XmlElement(name="ruleBaseName")
	@Sdl
	private String ruleBaseName;

	/**
	 * 
	 */
	@XmlElement(name="reportReason")
	@Sdl
	private int reportReason;

	/**
	 * 
	 */
	@XmlElement(name="redirectFlag")
	@Sdl
	private int redirectFlag;

	/**
	 * 
	 */
	@XmlElement(name="bearerCode")
	@Sdl
	private String bearerCode;

	/**
	 * 
	 */
	@XmlElement(name="gprsProcessId")
	@Sdl
	private int gprsProcessId;

	/**
	 * 
	 */
	@XmlElement(name="noRedirect")
	@Sdl
	private short noRedirect;

	public void setRedirectAddress(String obj){
		this.redirectAddress = obj;
		onFieldSet(0, obj);
	}

	public String getRedirectAddress(){
		return redirectAddress;
	}

	public void setDealDatetime(long obj){
		this.dealDatetime = obj;
		onFieldSet(1, obj);
	}

	public long getDealDatetime(){
		return dealDatetime;
	}

	public void setApnniId(int obj){
		this.apnniId = obj;
		onFieldSet(2, obj);
	}

	public int getApnniId(){
		return apnniId;
	}

	public void setRgType(int obj){
		this.rgType = obj;
		onFieldSet(3, obj);
	}

	public int getRgType(){
		return rgType;
	}

	public void setRggGroupId(int obj){
		this.rggGroupId = obj;
		onFieldSet(4, obj);
	}

	public int getRggGroupId(){
		return rggGroupId;
	}

	public void setRggFinishedFlag(int obj){
		this.rggFinishedFlag = obj;
		onFieldSet(5, obj);
	}

	public int getRggFinishedFlag(){
		return rggFinishedFlag;
	}

	public void setBearerId(int obj){
		this.bearerId = obj;
		onFieldSet(6, obj);
	}

	public int getBearerId(){
		return bearerId;
	}

	public void setUpRating(int obj){
		this.upRating = obj;
		onFieldSet(7, obj);
	}

	public int getUpRating(){
		return upRating;
	}

	public void setDlRating(int obj){
		this.dlRating = obj;
		onFieldSet(8, obj);
	}

	public int getDlRating(){
		return dlRating;
	}

	public void setRuleName(String obj){
		this.ruleName = obj;
		onFieldSet(9, obj);
	}

	public String getRuleName(){
		return ruleName;
	}

	public void setRuleBaseName(String obj){
		this.ruleBaseName = obj;
		onFieldSet(10, obj);
	}

	public String getRuleBaseName(){
		return ruleBaseName;
	}

	public void setReportReason(int obj){
		this.reportReason = obj;
		onFieldSet(11, obj);
	}

	public int getReportReason(){
		return reportReason;
	}

	public void setRedirectFlag(int obj){
		this.redirectFlag = obj;
		onFieldSet(12, obj);
	}

	public int getRedirectFlag(){
		return redirectFlag;
	}

	public void setBearerCode(String obj){
		this.bearerCode = obj;
		onFieldSet(13, obj);
	}

	public String getBearerCode(){
		return bearerCode;
	}

	public void setGprsProcessId(int obj){
		this.gprsProcessId = obj;
		onFieldSet(14, obj);
	}

	public int getGprsProcessId(){
		return gprsProcessId;
	}

	public void setNoRedirect(short obj){
		this.noRedirect = obj;
		onFieldSet(15, obj);
	}

	public short getNoRedirect(){
		return noRedirect;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGprsInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 16; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGprsInfo(SGprsInfo arg0){
		copy(arg0);
	}

	public void copy(final SGprsInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		redirectAddress = rhs.redirectAddress;
		dealDatetime = rhs.dealDatetime;
		apnniId = rhs.apnniId;
		rgType = rhs.rgType;
		rggGroupId = rhs.rggGroupId;
		rggFinishedFlag = rhs.rggFinishedFlag;
		bearerId = rhs.bearerId;
		upRating = rhs.upRating;
		dlRating = rhs.dlRating;
		ruleName = rhs.ruleName;
		ruleBaseName = rhs.ruleBaseName;
		reportReason = rhs.reportReason;
		redirectFlag = rhs.redirectFlag;
		bearerCode = rhs.bearerCode;
		gprsProcessId = rhs.gprsProcessId;
		noRedirect = rhs.noRedirect;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGprsInfo rhs=(SGprsInfo)rhs0;
		if(!ObjectUtils.equals(redirectAddress, rhs.redirectAddress)) return false;
		if(!ObjectUtils.equals(dealDatetime, rhs.dealDatetime)) return false;
		if(!ObjectUtils.equals(apnniId, rhs.apnniId)) return false;
		if(!ObjectUtils.equals(rgType, rhs.rgType)) return false;
		if(!ObjectUtils.equals(rggGroupId, rhs.rggGroupId)) return false;
		if(!ObjectUtils.equals(rggFinishedFlag, rhs.rggFinishedFlag)) return false;
		if(!ObjectUtils.equals(bearerId, rhs.bearerId)) return false;
		if(!ObjectUtils.equals(upRating, rhs.upRating)) return false;
		if(!ObjectUtils.equals(dlRating, rhs.dlRating)) return false;
		if(!ObjectUtils.equals(ruleName, rhs.ruleName)) return false;
		if(!ObjectUtils.equals(ruleBaseName, rhs.ruleBaseName)) return false;
		if(!ObjectUtils.equals(reportReason, rhs.reportReason)) return false;
		if(!ObjectUtils.equals(redirectFlag, rhs.redirectFlag)) return false;
		if(!ObjectUtils.equals(bearerCode, rhs.bearerCode)) return false;
		if(!ObjectUtils.equals(gprsProcessId, rhs.gprsProcessId)) return false;
		if(!ObjectUtils.equals(noRedirect, rhs.noRedirect)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(redirectAddress)
		.append(dealDatetime)
		.append(apnniId)
		.append(rgType)
		.append(rggGroupId)
		.append(rggFinishedFlag)
		.append(bearerId)
		.append(upRating)
		.append(dlRating)
		.append(ruleName)
		.append(ruleBaseName)
		.append(reportReason)
		.append(redirectFlag)
		.append(bearerCode)
		.append(gprsProcessId)
		.append(noRedirect)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(16);
public static final long BITS_ALL_MARKER = 0x8000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGprsInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "REDIRECT_ADDRESS", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "DEAL_DATETIME", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "APNNI_ID", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "RG_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "RGG_GROUP_ID", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "RGG_FINISHED_FLAG", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "BEARER_ID", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "UP_RATING", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "DL_RATING", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "RULE_NAME", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "RULE_BASE_NAME", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "REPORT_REASON", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "REDIRECT_FLAG", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "BEARER_CODE", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "GPRS_PROCESS_ID", 14, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsInfo.class, "NO_REDIRECT", 15, short.class));
}

}