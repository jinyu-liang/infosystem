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
@XmlType(propOrder={"test","sourceType","miscId","discount","serviceAttr","ismpLbsInfo","chCode","devCode","dnloadDuration","validTimes","onlineDuration","tranId","chargeupNum","ipAddress","oppIpAddress","cpCode","delvServerCode","contentSize","termType","videoCodeType","audioCodeType","authenticateNo","serviceBillingId","pkgBusinessCode","gateway","cfwGateway","oppGateway","carryType","serverProv"})
@Sdl(module="MXdr")
public class SIsmpSpecInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_TEST = "TEST";
	public final static String COL_SOURCE_TYPE = "SOURCE_TYPE";
	public final static String COL_MISC_ID = "MISC_ID";
	public final static String COL_DISCOUNT = "DISCOUNT";
	public final static String COL_SERVICE_ATTR = "SERVICE_ATTR";
	public final static String COL_ISMP_LBS_INFO = "ISMP_LBS_INFO";
	public final static String COL_CH_CODE = "CH_CODE";
	public final static String COL_DEV_CODE = "DEV_CODE";
	public final static String COL_DNLOAD_DURATION = "DNLOAD_DURATION";
	public final static String COL_VALID_TIMES = "VALID_TIMES";
	public final static String COL_ONLINE_DURATION = "ONLINE_DURATION";
	public final static String COL_TRAN_ID = "TRAN_ID";
	public final static String COL_CHARGEUP_NUM = "CHARGEUP_NUM";
	public final static String COL_IP_ADDRESS = "IP_ADDRESS";
	public final static String COL_OPP_IP_ADDRESS = "OPP_IP_ADDRESS";
	public final static String COL_CP_CODE = "CP_CODE";
	public final static String COL_DELV_SERVER_CODE = "DELV_SERVER_CODE";
	public final static String COL_CONTENT_SIZE = "CONTENT_SIZE";
	public final static String COL_TERM_TYPE = "TERM_TYPE";
	public final static String COL_VIDEO_CODE_TYPE = "VIDEO_CODE_TYPE";
	public final static String COL_AUDIO_CODE_TYPE = "AUDIO_CODE_TYPE";
	public final static String COL_AUTHENTICATE_NO = "AUTHENTICATE_NO";
	public final static String COL_SERVICE_BILLING_ID = "SERVICE_BILLING_ID";
	public final static String COL_PKG_BUSINESS_CODE = "PKG_BUSINESS_CODE";
	public final static String COL_GATEWAY = "GATEWAY";
	public final static String COL_CFW_GATEWAY = "CFW_GATEWAY";
	public final static String COL_OPP_GATEWAY = "OPP_GATEWAY";
	public final static String COL_CARRY_TYPE = "CARRY_TYPE";
	public final static String COL_SERVER_PROV = "SERVER_PROV";
	public final static int IDX_TEST = 0;
	public final static int IDX_SOURCE_TYPE = 1;
	public final static int IDX_MISC_ID = 2;
	public final static int IDX_DISCOUNT = 3;
	public final static int IDX_SERVICE_ATTR = 4;
	public final static int IDX_ISMP_LBS_INFO = 5;
	public final static int IDX_CH_CODE = 6;
	public final static int IDX_DEV_CODE = 7;
	public final static int IDX_DNLOAD_DURATION = 8;
	public final static int IDX_VALID_TIMES = 9;
	public final static int IDX_ONLINE_DURATION = 10;
	public final static int IDX_TRAN_ID = 11;
	public final static int IDX_CHARGEUP_NUM = 12;
	public final static int IDX_IP_ADDRESS = 13;
	public final static int IDX_OPP_IP_ADDRESS = 14;
	public final static int IDX_CP_CODE = 15;
	public final static int IDX_DELV_SERVER_CODE = 16;
	public final static int IDX_CONTENT_SIZE = 17;
	public final static int IDX_TERM_TYPE = 18;
	public final static int IDX_VIDEO_CODE_TYPE = 19;
	public final static int IDX_AUDIO_CODE_TYPE = 20;
	public final static int IDX_AUTHENTICATE_NO = 21;
	public final static int IDX_SERVICE_BILLING_ID = 22;
	public final static int IDX_PKG_BUSINESS_CODE = 23;
	public final static int IDX_GATEWAY = 24;
	public final static int IDX_CFW_GATEWAY = 25;
	public final static int IDX_OPP_GATEWAY = 26;
	public final static int IDX_CARRY_TYPE = 27;
	public final static int IDX_SERVER_PROV = 28;

	/**
	 * 
	 */
	@XmlElement(name="ismpLbsInfo")
	@Sdl
	private SLbsInfo ismpLbsInfo;

	/**
	 * 
	 */
	@XmlElement(name="test")
	@Sdl
	private int test;

	/**
	 * 
	 */
	@XmlElement(name="sourceType")
	@Sdl
	private short sourceType;

	/**
	 * 
	 */
	@XmlElement(name="miscId")
	@Sdl
	private String miscId;

	/**
	 * 
	 */
	@XmlElement(name="discount")
	@Sdl
	private short discount;

	/**
	 * 
	 */
	@XmlElement(name="serviceAttr")
	@Sdl
	private int serviceAttr;

	/**
	 * 
	 */
	@XmlElement(name="chCode")
	@Sdl
	private String chCode;

	/**
	 * 
	 */
	@XmlElement(name="devCode")
	@Sdl
	private String devCode;

	/**
	 * 
	 */
	@XmlElement(name="dnloadDuration")
	@Sdl
	private long dnloadDuration;

	/**
	 * 
	 */
	@XmlElement(name="validTimes")
	@Sdl
	private long validTimes;

	/**
	 * 
	 */
	@XmlElement(name="onlineDuration")
	@Sdl
	private long onlineDuration;

	/**
	 * 
	 */
	@XmlElement(name="tranId")
	@Sdl
	private String tranId;

	/**
	 * 
	 */
	@XmlElement(name="chargeupNum")
	@Sdl
	private String chargeupNum;

	/**
	 * 
	 */
	@XmlElement(name="ipAddress")
	@Sdl
	private String ipAddress;

	/**
	 * 
	 */
	@XmlElement(name="oppIpAddress")
	@Sdl
	private String oppIpAddress;

	/**
	 * 
	 */
	@XmlElement(name="cpCode")
	@Sdl
	private String cpCode;

	/**
	 * 
	 */
	@XmlElement(name="delvServerCode")
	@Sdl
	private String delvServerCode;

	/**
	 * 
	 */
	@XmlElement(name="contentSize")
	@Sdl
	private long contentSize;

	/**
	 * 
	 */
	@XmlElement(name="termType")
	@Sdl
	private String termType;

	/**
	 * 
	 */
	@XmlElement(name="videoCodeType")
	@Sdl
	private String videoCodeType;

	/**
	 * 
	 */
	@XmlElement(name="audioCodeType")
	@Sdl
	private String audioCodeType;

	/**
	 * 
	 */
	@XmlElement(name="authenticateNo")
	@Sdl
	private String authenticateNo;

	/**
	 * 
	 */
	@XmlElement(name="serviceBillingId")
	@Sdl
	private long serviceBillingId;

	/**
	 * 
	 */
	@XmlElement(name="pkgBusinessCode")
	@Sdl
	private String pkgBusinessCode;

	/**
	 * 
	 */
	@XmlElement(name="gateway")
	@Sdl
	private String gateway;

	/**
	 * 
	 */
	@XmlElement(name="cfwGateway")
	@Sdl
	private String cfwGateway;

	/**
	 * 
	 */
	@XmlElement(name="oppGateway")
	@Sdl
	private String oppGateway;

	/**
	 * 
	 */
	@XmlElement(name="carryType")
	@Sdl
	private int carryType;

	/**
	 * 
	 */
	@XmlElement(name="serverProv")
	@Sdl
	private String serverProv;

	public void setIsmpLbsInfo(SLbsInfo obj){
		this.ismpLbsInfo = obj;
		onFieldSet(5, obj);
	}

	public SLbsInfo getIsmpLbsInfo(){
		return ismpLbsInfo;
	}

	public void setTest(int obj){
		this.test = obj;
		onFieldSet(0, obj);
	}

	public int getTest(){
		return test;
	}

	public void setSourceType(short obj){
		this.sourceType = obj;
		onFieldSet(1, obj);
	}

	public short getSourceType(){
		return sourceType;
	}

	public void setMiscId(String obj){
		this.miscId = obj;
		onFieldSet(2, obj);
	}

	public String getMiscId(){
		return miscId;
	}

	public void setDiscount(short obj){
		this.discount = obj;
		onFieldSet(3, obj);
	}

	public short getDiscount(){
		return discount;
	}

	public void setServiceAttr(int obj){
		this.serviceAttr = obj;
		onFieldSet(4, obj);
	}

	public int getServiceAttr(){
		return serviceAttr;
	}

	public void setChCode(String obj){
		this.chCode = obj;
		onFieldSet(6, obj);
	}

	public String getChCode(){
		return chCode;
	}

	public void setDevCode(String obj){
		this.devCode = obj;
		onFieldSet(7, obj);
	}

	public String getDevCode(){
		return devCode;
	}

	public void setDnloadDuration(long obj){
		this.dnloadDuration = obj;
		onFieldSet(8, obj);
	}

	public long getDnloadDuration(){
		return dnloadDuration;
	}

	public void setValidTimes(long obj){
		this.validTimes = obj;
		onFieldSet(9, obj);
	}

	public long getValidTimes(){
		return validTimes;
	}

	public void setOnlineDuration(long obj){
		this.onlineDuration = obj;
		onFieldSet(10, obj);
	}

	public long getOnlineDuration(){
		return onlineDuration;
	}

	public void setTranId(String obj){
		this.tranId = obj;
		onFieldSet(11, obj);
	}

	public String getTranId(){
		return tranId;
	}

	public void setChargeupNum(String obj){
		this.chargeupNum = obj;
		onFieldSet(12, obj);
	}

	public String getChargeupNum(){
		return chargeupNum;
	}

	public void setIpAddress(String obj){
		this.ipAddress = obj;
		onFieldSet(13, obj);
	}

	public String getIpAddress(){
		return ipAddress;
	}

	public void setOppIpAddress(String obj){
		this.oppIpAddress = obj;
		onFieldSet(14, obj);
	}

	public String getOppIpAddress(){
		return oppIpAddress;
	}

	public void setCpCode(String obj){
		this.cpCode = obj;
		onFieldSet(15, obj);
	}

	public String getCpCode(){
		return cpCode;
	}

	public void setDelvServerCode(String obj){
		this.delvServerCode = obj;
		onFieldSet(16, obj);
	}

	public String getDelvServerCode(){
		return delvServerCode;
	}

	public void setContentSize(long obj){
		this.contentSize = obj;
		onFieldSet(17, obj);
	}

	public long getContentSize(){
		return contentSize;
	}

	public void setTermType(String obj){
		this.termType = obj;
		onFieldSet(18, obj);
	}

	public String getTermType(){
		return termType;
	}

	public void setVideoCodeType(String obj){
		this.videoCodeType = obj;
		onFieldSet(19, obj);
	}

	public String getVideoCodeType(){
		return videoCodeType;
	}

	public void setAudioCodeType(String obj){
		this.audioCodeType = obj;
		onFieldSet(20, obj);
	}

	public String getAudioCodeType(){
		return audioCodeType;
	}

	public void setAuthenticateNo(String obj){
		this.authenticateNo = obj;
		onFieldSet(21, obj);
	}

	public String getAuthenticateNo(){
		return authenticateNo;
	}

	public void setServiceBillingId(long obj){
		this.serviceBillingId = obj;
		onFieldSet(22, obj);
	}

	public long getServiceBillingId(){
		return serviceBillingId;
	}

	public void setPkgBusinessCode(String obj){
		this.pkgBusinessCode = obj;
		onFieldSet(23, obj);
	}

	public String getPkgBusinessCode(){
		return pkgBusinessCode;
	}

	public void setGateway(String obj){
		this.gateway = obj;
		onFieldSet(24, obj);
	}

	public String getGateway(){
		return gateway;
	}

	public void setCfwGateway(String obj){
		this.cfwGateway = obj;
		onFieldSet(25, obj);
	}

	public String getCfwGateway(){
		return cfwGateway;
	}

	public void setOppGateway(String obj){
		this.oppGateway = obj;
		onFieldSet(26, obj);
	}

	public String getOppGateway(){
		return oppGateway;
	}

	public void setCarryType(int obj){
		this.carryType = obj;
		onFieldSet(27, obj);
	}

	public int getCarryType(){
		return carryType;
	}

	public void setServerProv(String obj){
		this.serverProv = obj;
		onFieldSet(28, obj);
	}

	public String getServerProv(){
		return serverProv;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SIsmpSpecInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 29; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SIsmpSpecInfo(SIsmpSpecInfo arg0){
		copy(arg0);
	}

	public void copy(final SIsmpSpecInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		test = rhs.test;
		sourceType = rhs.sourceType;
		miscId = rhs.miscId;
		discount = rhs.discount;
		serviceAttr = rhs.serviceAttr;
		ismpLbsInfo = rhs.ismpLbsInfo;
		chCode = rhs.chCode;
		devCode = rhs.devCode;
		dnloadDuration = rhs.dnloadDuration;
		validTimes = rhs.validTimes;
		onlineDuration = rhs.onlineDuration;
		tranId = rhs.tranId;
		chargeupNum = rhs.chargeupNum;
		ipAddress = rhs.ipAddress;
		oppIpAddress = rhs.oppIpAddress;
		cpCode = rhs.cpCode;
		delvServerCode = rhs.delvServerCode;
		contentSize = rhs.contentSize;
		termType = rhs.termType;
		videoCodeType = rhs.videoCodeType;
		audioCodeType = rhs.audioCodeType;
		authenticateNo = rhs.authenticateNo;
		serviceBillingId = rhs.serviceBillingId;
		pkgBusinessCode = rhs.pkgBusinessCode;
		gateway = rhs.gateway;
		cfwGateway = rhs.cfwGateway;
		oppGateway = rhs.oppGateway;
		carryType = rhs.carryType;
		serverProv = rhs.serverProv;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SIsmpSpecInfo rhs=(SIsmpSpecInfo)rhs0;
		if(!ObjectUtils.equals(test, rhs.test)) return false;
		if(!ObjectUtils.equals(sourceType, rhs.sourceType)) return false;
		if(!ObjectUtils.equals(miscId, rhs.miscId)) return false;
		if(!ObjectUtils.equals(discount, rhs.discount)) return false;
		if(!ObjectUtils.equals(serviceAttr, rhs.serviceAttr)) return false;
		if(!ObjectUtils.equals(ismpLbsInfo, rhs.ismpLbsInfo)) return false;
		if(!ObjectUtils.equals(chCode, rhs.chCode)) return false;
		if(!ObjectUtils.equals(devCode, rhs.devCode)) return false;
		if(!ObjectUtils.equals(dnloadDuration, rhs.dnloadDuration)) return false;
		if(!ObjectUtils.equals(validTimes, rhs.validTimes)) return false;
		if(!ObjectUtils.equals(onlineDuration, rhs.onlineDuration)) return false;
		if(!ObjectUtils.equals(tranId, rhs.tranId)) return false;
		if(!ObjectUtils.equals(chargeupNum, rhs.chargeupNum)) return false;
		if(!ObjectUtils.equals(ipAddress, rhs.ipAddress)) return false;
		if(!ObjectUtils.equals(oppIpAddress, rhs.oppIpAddress)) return false;
		if(!ObjectUtils.equals(cpCode, rhs.cpCode)) return false;
		if(!ObjectUtils.equals(delvServerCode, rhs.delvServerCode)) return false;
		if(!ObjectUtils.equals(contentSize, rhs.contentSize)) return false;
		if(!ObjectUtils.equals(termType, rhs.termType)) return false;
		if(!ObjectUtils.equals(videoCodeType, rhs.videoCodeType)) return false;
		if(!ObjectUtils.equals(audioCodeType, rhs.audioCodeType)) return false;
		if(!ObjectUtils.equals(authenticateNo, rhs.authenticateNo)) return false;
		if(!ObjectUtils.equals(serviceBillingId, rhs.serviceBillingId)) return false;
		if(!ObjectUtils.equals(pkgBusinessCode, rhs.pkgBusinessCode)) return false;
		if(!ObjectUtils.equals(gateway, rhs.gateway)) return false;
		if(!ObjectUtils.equals(cfwGateway, rhs.cfwGateway)) return false;
		if(!ObjectUtils.equals(oppGateway, rhs.oppGateway)) return false;
		if(!ObjectUtils.equals(carryType, rhs.carryType)) return false;
		if(!ObjectUtils.equals(serverProv, rhs.serverProv)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(test)
		.append(sourceType)
		.append(miscId)
		.append(discount)
		.append(serviceAttr)
		.append(ismpLbsInfo)
		.append(chCode)
		.append(devCode)
		.append(dnloadDuration)
		.append(validTimes)
		.append(onlineDuration)
		.append(tranId)
		.append(chargeupNum)
		.append(ipAddress)
		.append(oppIpAddress)
		.append(cpCode)
		.append(delvServerCode)
		.append(contentSize)
		.append(termType)
		.append(videoCodeType)
		.append(audioCodeType)
		.append(authenticateNo)
		.append(serviceBillingId)
		.append(pkgBusinessCode)
		.append(gateway)
		.append(cfwGateway)
		.append(oppGateway)
		.append(carryType)
		.append(serverProv)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(29);
public static final long BITS_ALL_MARKER = 0x10000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SIsmpSpecInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "TEST", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "SOURCE_TYPE", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "MISC_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "DISCOUNT", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "SERVICE_ATTR", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "ISMP_LBS_INFO", 5, SLbsInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "CH_CODE", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "DEV_CODE", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "DNLOAD_DURATION", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "VALID_TIMES", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "ONLINE_DURATION", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "TRAN_ID", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "CHARGEUP_NUM", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "IP_ADDRESS", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "OPP_IP_ADDRESS", 14, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "CP_CODE", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "DELV_SERVER_CODE", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "CONTENT_SIZE", 17, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "TERM_TYPE", 18, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "VIDEO_CODE_TYPE", 19, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "AUDIO_CODE_TYPE", 20, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "AUTHENTICATE_NO", 21, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "SERVICE_BILLING_ID", 22, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "PKG_BUSINESS_CODE", 23, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "GATEWAY", 24, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "CFW_GATEWAY", 25, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "OPP_GATEWAY", 26, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "CARRY_TYPE", 27, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SIsmpSpecInfo.class, "SERVER_PROV", 28, String.class));
}

}