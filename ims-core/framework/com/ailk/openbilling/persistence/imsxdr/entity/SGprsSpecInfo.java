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
@XmlType(propOrder={"cgAddr","url","callerIndicate","sgsnAddress","triggerType","msnc","rac","chargingId","ggsnAddress","apnNi","apnOi","pdpType","pdpAddress","causeClose","combineResult","chargingCharacteristics","ccSelectionMode","sgsnPlmn","partialTypeIndicator","recSeqNo","localRecSeqNo","systemType","ggsnId","sgsnId","serviceUsage","partialNum","rggInfoMap","multiSimSeq","currentRgMap","tariffQcsList","gprsWlanInfo"})
@Sdl(module="MXdr")
public class SGprsSpecInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CG_ADDR = "CG_ADDR";
	public final static String COL_URL = "URL";
	public final static String COL_CALLER_INDICATE = "CALLER_INDICATE";
	public final static String COL_SGSN_ADDRESS = "SGSN_ADDRESS";
	public final static String COL_TRIGGER_TYPE = "TRIGGER_TYPE";
	public final static String COL_MSNC = "MSNC";
	public final static String COL_RAC = "RAC";
	public final static String COL_CHARGING_ID = "CHARGING_ID";
	public final static String COL_GGSN_ADDRESS = "GGSN_ADDRESS";
	public final static String COL_APN_NI = "APN_NI";
	public final static String COL_APN_OI = "APN_OI";
	public final static String COL_PDP_TYPE = "PDP_TYPE";
	public final static String COL_PDP_ADDRESS = "PDP_ADDRESS";
	public final static String COL_CAUSE_CLOSE = "CAUSE_CLOSE";
	public final static String COL_COMBINE_RESULT = "COMBINE_RESULT";
	public final static String COL_CHARGING_CHARACTERISTICS = "CHARGING_CHARACTERISTICS";
	public final static String COL_CC_SELECTION_MODE = "CC_SELECTION_MODE";
	public final static String COL_SGSN_PLMN = "SGSN_PLMN";
	public final static String COL_PARTIAL_TYPE_INDICATOR = "PARTIAL_TYPE_INDICATOR";
	public final static String COL_REC_SEQ_NO = "REC_SEQ_NO";
	public final static String COL_LOCAL_REC_SEQ_NO = "LOCAL_REC_SEQ_NO";
	public final static String COL_SYSTEM_TYPE = "SYSTEM_TYPE";
	public final static String COL_GGSN_ID = "GGSN_ID";
	public final static String COL_SGSN_ID = "SGSN_ID";
	public final static String COL_SERVICE_USAGE = "SERVICE_USAGE";
	public final static String COL_PARTIAL_NUM = "PARTIAL_NUM";
	public final static String COL_RGG_INFO_MAP = "RGG_INFO_MAP";
	public final static String COL_MULTI_SIM_SEQ = "MULTI_SIM_SEQ";
	public final static String COL_CURRENT_RG_MAP = "CURRENT_RG_MAP";
	public final static String COL_TARIFF_QCS_LIST = "TARIFF_QCS_LIST";
	public final static String COL_GPRS_WLAN_INFO = "GPRS_WLAN_INFO";
	public final static int IDX_CG_ADDR = 0;
	public final static int IDX_URL = 1;
	public final static int IDX_CALLER_INDICATE = 2;
	public final static int IDX_SGSN_ADDRESS = 3;
	public final static int IDX_TRIGGER_TYPE = 4;
	public final static int IDX_MSNC = 5;
	public final static int IDX_RAC = 6;
	public final static int IDX_CHARGING_ID = 7;
	public final static int IDX_GGSN_ADDRESS = 8;
	public final static int IDX_APN_NI = 9;
	public final static int IDX_APN_OI = 10;
	public final static int IDX_PDP_TYPE = 11;
	public final static int IDX_PDP_ADDRESS = 12;
	public final static int IDX_CAUSE_CLOSE = 13;
	public final static int IDX_COMBINE_RESULT = 14;
	public final static int IDX_CHARGING_CHARACTERISTICS = 15;
	public final static int IDX_CC_SELECTION_MODE = 16;
	public final static int IDX_SGSN_PLMN = 17;
	public final static int IDX_PARTIAL_TYPE_INDICATOR = 18;
	public final static int IDX_REC_SEQ_NO = 19;
	public final static int IDX_LOCAL_REC_SEQ_NO = 20;
	public final static int IDX_SYSTEM_TYPE = 21;
	public final static int IDX_GGSN_ID = 22;
	public final static int IDX_SGSN_ID = 23;
	public final static int IDX_SERVICE_USAGE = 24;
	public final static int IDX_PARTIAL_NUM = 25;
	public final static int IDX_RGG_INFO_MAP = 26;
	public final static int IDX_MULTI_SIM_SEQ = 27;
	public final static int IDX_CURRENT_RG_MAP = 28;
	public final static int IDX_TARIFF_QCS_LIST = 29;
	public final static int IDX_GPRS_WLAN_INFO = 30;

	/**
	 * 
	 */
	@XmlElement(name="rggInfoMap")
	@Sdl
	private Map<Integer,SRggInfo> rggInfoMap;

	/**
	 * 
	 */
	@XmlElement(name="currentRgMap")
	@Sdl
	private Map<Integer,Integer> currentRgMap;

	/**
	 * 
	 */
	@XmlElement(name="tariffQcsList")
	@Sdl
	private List<STarffQcs> tariffQcsList;

	/**
	 * 
	 */
	@XmlElement(name="gprsWlanInfo")
	@Sdl
	private SWlanInfo gprsWlanInfo;

	/**
	 * 
	 */
	@XmlElement(name="cgAddr")
	@Sdl
	private String cgAddr;

	/**
	 * 
	 */
	@XmlElement(name="url")
	@Sdl
	private String url;

	/**
	 * 
	 */
	@XmlElement(name="callerIndicate")
	@Sdl
	private int callerIndicate;

	/**
	 * 
	 */
	@XmlElement(name="sgsnAddress")
	@Sdl
	private String sgsnAddress;

	/**
	 * 
	 */
	@XmlElement(name="triggerType")
	@Sdl
	private int triggerType;

	/**
	 * 
	 */
	@XmlElement(name="msnc")
	@Sdl
	private int msnc;

	/**
	 * 
	 */
	@XmlElement(name="rac")
	@Sdl
	private String rac;

	/**
	 * 
	 */
	@XmlElement(name="chargingId")
	@Sdl
	private String chargingId;

	/**
	 * 
	 */
	@XmlElement(name="ggsnAddress")
	@Sdl
	private String ggsnAddress;

	/**
	 * 
	 */
	@XmlElement(name="apnNi")
	@Sdl
	private String apnNi;

	/**
	 * 
	 */
	@XmlElement(name="apnOi")
	@Sdl
	private String apnOi;

	/**
	 * 
	 */
	@XmlElement(name="pdpType")
	@Sdl
	private int pdpType;

	/**
	 * 
	 */
	@XmlElement(name="pdpAddress")
	@Sdl
	private String pdpAddress;

	/**
	 * 
	 */
	@XmlElement(name="causeClose")
	@Sdl
	private int causeClose;

	/**
	 * 
	 */
	@XmlElement(name="combineResult")
	@Sdl
	private String combineResult;

	/**
	 * 
	 */
	@XmlElement(name="chargingCharacteristics")
	@Sdl
	private String chargingCharacteristics;

	/**
	 * 
	 */
	@XmlElement(name="ccSelectionMode")
	@Sdl
	private String ccSelectionMode;

	/**
	 * 
	 */
	@XmlElement(name="sgsnPlmn")
	@Sdl
	private String sgsnPlmn;

	/**
	 * 
	 */
	@XmlElement(name="partialTypeIndicator")
	@Sdl
	private String partialTypeIndicator;

	/**
	 * 
	 */
	@XmlElement(name="recSeqNo")
	@Sdl
	private String recSeqNo;

	/**
	 * 
	 */
	@XmlElement(name="localRecSeqNo")
	@Sdl
	private String localRecSeqNo;

	/**
	 * 
	 */
	@XmlElement(name="systemType")
	@Sdl
	private String systemType;

	/**
	 * 
	 */
	@XmlElement(name="ggsnId")
	@Sdl
	private String ggsnId;

	/**
	 * 
	 */
	@XmlElement(name="sgsnId")
	@Sdl
	private String sgsnId;

	/**
	 * 
	 */
	@XmlElement(name="serviceUsage")
	@Sdl
	private String serviceUsage;

	/**
	 * 
	 */
	@XmlElement(name="partialNum")
	@Sdl
	private int partialNum;

	/**
	 * 
	 */
	@XmlElement(name="multiSimSeq")
	@Sdl
	private String multiSimSeq;

	public void setRggInfoMap(Map<Integer,SRggInfo> obj){
		this.rggInfoMap = obj;
		onFieldSet(26, obj);
	}

	public Map<Integer,SRggInfo> getRggInfoMap(){
		return rggInfoMap;
	}

	public void setCurrentRgMap(Map<Integer,Integer> obj){
		this.currentRgMap = obj;
		onFieldSet(28, obj);
	}

	public Map<Integer,Integer> getCurrentRgMap(){
		return currentRgMap;
	}

	public void setTariffQcsList(List<STarffQcs> obj){
		this.tariffQcsList = obj;
		onFieldSet(29, obj);
	}

	public List<STarffQcs> getTariffQcsList(){
		return tariffQcsList;
	}

	public void setGprsWlanInfo(SWlanInfo obj){
		this.gprsWlanInfo = obj;
		onFieldSet(30, obj);
	}

	public SWlanInfo getGprsWlanInfo(){
		return gprsWlanInfo;
	}

	public void setCgAddr(String obj){
		this.cgAddr = obj;
		onFieldSet(0, obj);
	}

	public String getCgAddr(){
		return cgAddr;
	}

	public void setUrl(String obj){
		this.url = obj;
		onFieldSet(1, obj);
	}

	public String getUrl(){
		return url;
	}

	public void setCallerIndicate(int obj){
		this.callerIndicate = obj;
		onFieldSet(2, obj);
	}

	public int getCallerIndicate(){
		return callerIndicate;
	}

	public void setSgsnAddress(String obj){
		this.sgsnAddress = obj;
		onFieldSet(3, obj);
	}

	public String getSgsnAddress(){
		return sgsnAddress;
	}

	public void setTriggerType(int obj){
		this.triggerType = obj;
		onFieldSet(4, obj);
	}

	public int getTriggerType(){
		return triggerType;
	}

	public void setMsnc(int obj){
		this.msnc = obj;
		onFieldSet(5, obj);
	}

	public int getMsnc(){
		return msnc;
	}

	public void setRac(String obj){
		this.rac = obj;
		onFieldSet(6, obj);
	}

	public String getRac(){
		return rac;
	}

	public void setChargingId(String obj){
		this.chargingId = obj;
		onFieldSet(7, obj);
	}

	public String getChargingId(){
		return chargingId;
	}

	public void setGgsnAddress(String obj){
		this.ggsnAddress = obj;
		onFieldSet(8, obj);
	}

	public String getGgsnAddress(){
		return ggsnAddress;
	}

	public void setApnNi(String obj){
		this.apnNi = obj;
		onFieldSet(9, obj);
	}

	public String getApnNi(){
		return apnNi;
	}

	public void setApnOi(String obj){
		this.apnOi = obj;
		onFieldSet(10, obj);
	}

	public String getApnOi(){
		return apnOi;
	}

	public void setPdpType(int obj){
		this.pdpType = obj;
		onFieldSet(11, obj);
	}

	public int getPdpType(){
		return pdpType;
	}

	public void setPdpAddress(String obj){
		this.pdpAddress = obj;
		onFieldSet(12, obj);
	}

	public String getPdpAddress(){
		return pdpAddress;
	}

	public void setCauseClose(int obj){
		this.causeClose = obj;
		onFieldSet(13, obj);
	}

	public int getCauseClose(){
		return causeClose;
	}

	public void setCombineResult(String obj){
		this.combineResult = obj;
		onFieldSet(14, obj);
	}

	public String getCombineResult(){
		return combineResult;
	}

	public void setChargingCharacteristics(String obj){
		this.chargingCharacteristics = obj;
		onFieldSet(15, obj);
	}

	public String getChargingCharacteristics(){
		return chargingCharacteristics;
	}

	public void setCcSelectionMode(String obj){
		this.ccSelectionMode = obj;
		onFieldSet(16, obj);
	}

	public String getCcSelectionMode(){
		return ccSelectionMode;
	}

	public void setSgsnPlmn(String obj){
		this.sgsnPlmn = obj;
		onFieldSet(17, obj);
	}

	public String getSgsnPlmn(){
		return sgsnPlmn;
	}

	public void setPartialTypeIndicator(String obj){
		this.partialTypeIndicator = obj;
		onFieldSet(18, obj);
	}

	public String getPartialTypeIndicator(){
		return partialTypeIndicator;
	}

	public void setRecSeqNo(String obj){
		this.recSeqNo = obj;
		onFieldSet(19, obj);
	}

	public String getRecSeqNo(){
		return recSeqNo;
	}

	public void setLocalRecSeqNo(String obj){
		this.localRecSeqNo = obj;
		onFieldSet(20, obj);
	}

	public String getLocalRecSeqNo(){
		return localRecSeqNo;
	}

	public void setSystemType(String obj){
		this.systemType = obj;
		onFieldSet(21, obj);
	}

	public String getSystemType(){
		return systemType;
	}

	public void setGgsnId(String obj){
		this.ggsnId = obj;
		onFieldSet(22, obj);
	}

	public String getGgsnId(){
		return ggsnId;
	}

	public void setSgsnId(String obj){
		this.sgsnId = obj;
		onFieldSet(23, obj);
	}

	public String getSgsnId(){
		return sgsnId;
	}

	public void setServiceUsage(String obj){
		this.serviceUsage = obj;
		onFieldSet(24, obj);
	}

	public String getServiceUsage(){
		return serviceUsage;
	}

	public void setPartialNum(int obj){
		this.partialNum = obj;
		onFieldSet(25, obj);
	}

	public int getPartialNum(){
		return partialNum;
	}

	public void setMultiSimSeq(String obj){
		this.multiSimSeq = obj;
		onFieldSet(27, obj);
	}

	public String getMultiSimSeq(){
		return multiSimSeq;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SGprsSpecInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 31; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SGprsSpecInfo(SGprsSpecInfo arg0){
		copy(arg0);
	}

	public void copy(final SGprsSpecInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		cgAddr = rhs.cgAddr;
		url = rhs.url;
		callerIndicate = rhs.callerIndicate;
		sgsnAddress = rhs.sgsnAddress;
		triggerType = rhs.triggerType;
		msnc = rhs.msnc;
		rac = rhs.rac;
		chargingId = rhs.chargingId;
		ggsnAddress = rhs.ggsnAddress;
		apnNi = rhs.apnNi;
		apnOi = rhs.apnOi;
		pdpType = rhs.pdpType;
		pdpAddress = rhs.pdpAddress;
		causeClose = rhs.causeClose;
		combineResult = rhs.combineResult;
		chargingCharacteristics = rhs.chargingCharacteristics;
		ccSelectionMode = rhs.ccSelectionMode;
		sgsnPlmn = rhs.sgsnPlmn;
		partialTypeIndicator = rhs.partialTypeIndicator;
		recSeqNo = rhs.recSeqNo;
		localRecSeqNo = rhs.localRecSeqNo;
		systemType = rhs.systemType;
		ggsnId = rhs.ggsnId;
		sgsnId = rhs.sgsnId;
		serviceUsage = rhs.serviceUsage;
		partialNum = rhs.partialNum;
		rggInfoMap = rhs.rggInfoMap;
		multiSimSeq = rhs.multiSimSeq;
		currentRgMap = rhs.currentRgMap;
		tariffQcsList = rhs.tariffQcsList;
		gprsWlanInfo = rhs.gprsWlanInfo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGprsSpecInfo rhs=(SGprsSpecInfo)rhs0;
		if(!ObjectUtils.equals(cgAddr, rhs.cgAddr)) return false;
		if(!ObjectUtils.equals(url, rhs.url)) return false;
		if(!ObjectUtils.equals(callerIndicate, rhs.callerIndicate)) return false;
		if(!ObjectUtils.equals(sgsnAddress, rhs.sgsnAddress)) return false;
		if(!ObjectUtils.equals(triggerType, rhs.triggerType)) return false;
		if(!ObjectUtils.equals(msnc, rhs.msnc)) return false;
		if(!ObjectUtils.equals(rac, rhs.rac)) return false;
		if(!ObjectUtils.equals(chargingId, rhs.chargingId)) return false;
		if(!ObjectUtils.equals(ggsnAddress, rhs.ggsnAddress)) return false;
		if(!ObjectUtils.equals(apnNi, rhs.apnNi)) return false;
		if(!ObjectUtils.equals(apnOi, rhs.apnOi)) return false;
		if(!ObjectUtils.equals(pdpType, rhs.pdpType)) return false;
		if(!ObjectUtils.equals(pdpAddress, rhs.pdpAddress)) return false;
		if(!ObjectUtils.equals(causeClose, rhs.causeClose)) return false;
		if(!ObjectUtils.equals(combineResult, rhs.combineResult)) return false;
		if(!ObjectUtils.equals(chargingCharacteristics, rhs.chargingCharacteristics)) return false;
		if(!ObjectUtils.equals(ccSelectionMode, rhs.ccSelectionMode)) return false;
		if(!ObjectUtils.equals(sgsnPlmn, rhs.sgsnPlmn)) return false;
		if(!ObjectUtils.equals(partialTypeIndicator, rhs.partialTypeIndicator)) return false;
		if(!ObjectUtils.equals(recSeqNo, rhs.recSeqNo)) return false;
		if(!ObjectUtils.equals(localRecSeqNo, rhs.localRecSeqNo)) return false;
		if(!ObjectUtils.equals(systemType, rhs.systemType)) return false;
		if(!ObjectUtils.equals(ggsnId, rhs.ggsnId)) return false;
		if(!ObjectUtils.equals(sgsnId, rhs.sgsnId)) return false;
		if(!ObjectUtils.equals(serviceUsage, rhs.serviceUsage)) return false;
		if(!ObjectUtils.equals(partialNum, rhs.partialNum)) return false;
		if(!ObjectUtils.equals(rggInfoMap, rhs.rggInfoMap)) return false;
		if(!ObjectUtils.equals(multiSimSeq, rhs.multiSimSeq)) return false;
		if(!ObjectUtils.equals(currentRgMap, rhs.currentRgMap)) return false;
		if(!ObjectUtils.equals(tariffQcsList, rhs.tariffQcsList)) return false;
		if(!ObjectUtils.equals(gprsWlanInfo, rhs.gprsWlanInfo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cgAddr)
		.append(url)
		.append(callerIndicate)
		.append(sgsnAddress)
		.append(triggerType)
		.append(msnc)
		.append(rac)
		.append(chargingId)
		.append(ggsnAddress)
		.append(apnNi)
		.append(apnOi)
		.append(pdpType)
		.append(pdpAddress)
		.append(causeClose)
		.append(combineResult)
		.append(chargingCharacteristics)
		.append(ccSelectionMode)
		.append(sgsnPlmn)
		.append(partialTypeIndicator)
		.append(recSeqNo)
		.append(localRecSeqNo)
		.append(systemType)
		.append(ggsnId)
		.append(sgsnId)
		.append(serviceUsage)
		.append(partialNum)
		.append(rggInfoMap)
		.append(multiSimSeq)
		.append(currentRgMap)
		.append(tariffQcsList)
		.append(gprsWlanInfo)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(31);
public static final long BITS_ALL_MARKER = 0x40000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SGprsSpecInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CG_ADDR", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "URL", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CALLER_INDICATE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "SGSN_ADDRESS", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "TRIGGER_TYPE", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "MSNC", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "RAC", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CHARGING_ID", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "GGSN_ADDRESS", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "APN_NI", 9, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "APN_OI", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "PDP_TYPE", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "PDP_ADDRESS", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CAUSE_CLOSE", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "COMBINE_RESULT", 14, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CHARGING_CHARACTERISTICS", 15, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CC_SELECTION_MODE", 16, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "SGSN_PLMN", 17, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "PARTIAL_TYPE_INDICATOR", 18, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "REC_SEQ_NO", 19, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "LOCAL_REC_SEQ_NO", 20, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "SYSTEM_TYPE", 21, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "GGSN_ID", 22, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "SGSN_ID", 23, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "SERVICE_USAGE", 24, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "PARTIAL_NUM", 25, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "RGG_INFO_MAP", 26, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "MULTI_SIM_SEQ", 27, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "CURRENT_RG_MAP", 28, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "TARIFF_QCS_LIST", 29, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SGprsSpecInfo.class, "GPRS_WLAN_INFO", 30, SWlanInfo.class));
}

}