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
import com.ailk.easyframe.sdl.sdlbuffer.CsdlXdrObject;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"chargeMode","dccHead","sockInfo","featureCode","splitInfo","common","totalXdr","subXdrMap","managerInfo","rggXdrMap","functionFlag","gxFlag","gxXdrMap","inputFileName","tXdr"})
@Sdl(module="MXdr")
public class SXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CHARGE_MODE = "CHARGE_MODE";
	public final static String COL_DCC_HEAD = "DCC_HEAD";
	public final static String COL_SOCK_INFO = "SOCK_INFO";
	public final static String COL_FEATURE_CODE = "FEATURE_CODE";
	public final static String COL_SPLIT_INFO = "SPLIT_INFO";
	public final static String COL_COMMON = "COMMON";
	public final static String COL_TOTAL_XDR = "TOTAL_XDR";
	public final static String COL_SUB_XDR_MAP = "SUB_XDR_MAP";
	public final static String COL_MANAGER_INFO = "MANAGER_INFO";
	public final static String COL_RGG_XDR_MAP = "RGG_XDR_MAP";
	public final static String COL_FUNCTION_FLAG = "FUNCTION_FLAG";
	public final static String COL_GX_FLAG = "GX_FLAG";
	public final static String COL_GX_XDR_MAP = "GX_XDR_MAP";
	public final static String COL_INPUT_FILE_NAME = "INPUT_FILE_NAME";
	public final static String COL_T_XDR = "T_XDR";
	public final static int IDX_CHARGE_MODE = 0;
	public final static int IDX_DCC_HEAD = 1;
	public final static int IDX_SOCK_INFO = 2;
	public final static int IDX_FEATURE_CODE = 3;
	public final static int IDX_SPLIT_INFO = 4;
	public final static int IDX_COMMON = 5;
	public final static int IDX_TOTAL_XDR = 6;
	public final static int IDX_SUB_XDR_MAP = 7;
	public final static int IDX_MANAGER_INFO = 8;
	public final static int IDX_RGG_XDR_MAP = 9;
	public final static int IDX_FUNCTION_FLAG = 10;
	public final static int IDX_GX_FLAG = 11;
	public final static int IDX_GX_XDR_MAP = 12;
	public final static int IDX_INPUT_FILE_NAME = 13;
	public final static int IDX_T_XDR = 14;

	/**
	 * 
	 */
	@XmlElement(name="dccHead")
	@Sdl
	private SDccHead dccHead;

	/**
	 * 
	 */
	@XmlElement(name="splitInfo")
	@Sdl
	private SSplitInfo splitInfo;

	/**
	 * 
	 */
	@XmlElement(name="common")
	@Sdl
	private SCommon common;

	/**
	 * 
	 */
	@XmlElement(name="totalXdr")
	@Sdl
	private SSubXdr totalXdr;

	/**
	 * 
	 */
	@XmlElement(name="subXdrMap")
	@Sdl
	private Map<String,SSubXdr> subXdrMap;

	/**
	 * 
	 */
	@XmlElement(name="managerInfo")
	@Sdl
	private SManagerInfo managerInfo;

	/**
	 * 
	 */
	@XmlElement(name="rggXdrMap")
	@Sdl
	private Map<String,SSubXdr> rggXdrMap;

	/**
	 * 
	 */
	@XmlElement(name="gxXdrMap")
	@Sdl
	private Map<String,SSubXdr> gxXdrMap;

	/**
	 * 
	 */
	@XmlElement(name="chargeMode")
	@Sdl
	private int chargeMode;

	/**
	 * 
	 */
	@XmlElement(name="sockInfo")
	@Sdl
	private String sockInfo;

	/**
	 * 
	 */
	@XmlElement(name="featureCode")
	@Sdl
	private String featureCode;

	/**
	 * 
	 */
	@XmlElement(name="functionFlag")
	@Sdl
	private int functionFlag;

	/**
	 * 
	 */
	@XmlElement(name="gxFlag")
	@Sdl
	private int gxFlag;

	/**
	 * 
	 */
	@XmlElement(name="inputFileName")
	@Sdl
	private String inputFileName;

	/**
	 * 
	 */
	@XmlElement(name="tXdr")
	@Sdl
	private CsdlXdrObject tXdr;

	public void setDccHead(SDccHead obj){
		this.dccHead = obj;
		onFieldSet(1, obj);
	}

	public SDccHead getDccHead(){
		return dccHead;
	}

	public void setSplitInfo(SSplitInfo obj){
		this.splitInfo = obj;
		onFieldSet(4, obj);
	}

	public SSplitInfo getSplitInfo(){
		return splitInfo;
	}

	public void setCommon(SCommon obj){
		this.common = obj;
		onFieldSet(5, obj);
	}

	public SCommon getCommon(){
		return common;
	}

	public void setTotalXdr(SSubXdr obj){
		this.totalXdr = obj;
		onFieldSet(6, obj);
	}

	public SSubXdr getTotalXdr(){
		return totalXdr;
	}

	public void setSubXdrMap(Map<String,SSubXdr> obj){
		this.subXdrMap = obj;
		onFieldSet(7, obj);
	}

	public Map<String,SSubXdr> getSubXdrMap(){
		return subXdrMap;
	}

	public void setManagerInfo(SManagerInfo obj){
		this.managerInfo = obj;
		onFieldSet(8, obj);
	}

	public SManagerInfo getManagerInfo(){
		return managerInfo;
	}

	public void setRggXdrMap(Map<String,SSubXdr> obj){
		this.rggXdrMap = obj;
		onFieldSet(9, obj);
	}

	public Map<String,SSubXdr> getRggXdrMap(){
		return rggXdrMap;
	}

	public void setGxXdrMap(Map<String,SSubXdr> obj){
		this.gxXdrMap = obj;
		onFieldSet(12, obj);
	}

	public Map<String,SSubXdr> getGxXdrMap(){
		return gxXdrMap;
	}

	public void setChargeMode(int obj){
		this.chargeMode = obj;
		onFieldSet(0, obj);
	}

	public int getChargeMode(){
		return chargeMode;
	}

	public void setSockInfo(String obj){
		this.sockInfo = obj;
		onFieldSet(2, obj);
	}

	public String getSockInfo(){
		return sockInfo;
	}

	public void setFeatureCode(String obj){
		this.featureCode = obj;
		onFieldSet(3, obj);
	}

	public String getFeatureCode(){
		return featureCode;
	}

	public void setFunctionFlag(int obj){
		this.functionFlag = obj;
		onFieldSet(10, obj);
	}

	public int getFunctionFlag(){
		return functionFlag;
	}

	public void setGxFlag(int obj){
		this.gxFlag = obj;
		onFieldSet(11, obj);
	}

	public int getGxFlag(){
		return gxFlag;
	}

	public void setInputFileName(String obj){
		this.inputFileName = obj;
		onFieldSet(13, obj);
	}

	public String getInputFileName(){
		return inputFileName;
	}

	public void setTXdr(CsdlXdrObject obj){
		this.tXdr = obj;
		onFieldSet(14, obj);
	}

	public CsdlXdrObject getTXdr(){
		return tXdr;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 15; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SXdr(SXdr arg0){
		copy(arg0);
	}

	public void copy(final SXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		chargeMode = rhs.chargeMode;
		dccHead = rhs.dccHead;
		sockInfo = rhs.sockInfo;
		featureCode = rhs.featureCode;
		splitInfo = rhs.splitInfo;
		common = rhs.common;
		totalXdr = rhs.totalXdr;
		subXdrMap = rhs.subXdrMap;
		managerInfo = rhs.managerInfo;
		rggXdrMap = rhs.rggXdrMap;
		functionFlag = rhs.functionFlag;
		gxFlag = rhs.gxFlag;
		gxXdrMap = rhs.gxXdrMap;
		inputFileName = rhs.inputFileName;
		tXdr = rhs.tXdr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SXdr rhs=(SXdr)rhs0;
		if(!ObjectUtils.equals(chargeMode, rhs.chargeMode)) return false;
		if(!ObjectUtils.equals(dccHead, rhs.dccHead)) return false;
		if(!ObjectUtils.equals(sockInfo, rhs.sockInfo)) return false;
		if(!ObjectUtils.equals(featureCode, rhs.featureCode)) return false;
		if(!ObjectUtils.equals(splitInfo, rhs.splitInfo)) return false;
		if(!ObjectUtils.equals(common, rhs.common)) return false;
		if(!ObjectUtils.equals(totalXdr, rhs.totalXdr)) return false;
		if(!ObjectUtils.equals(subXdrMap, rhs.subXdrMap)) return false;
		if(!ObjectUtils.equals(managerInfo, rhs.managerInfo)) return false;
		if(!ObjectUtils.equals(rggXdrMap, rhs.rggXdrMap)) return false;
		if(!ObjectUtils.equals(functionFlag, rhs.functionFlag)) return false;
		if(!ObjectUtils.equals(gxFlag, rhs.gxFlag)) return false;
		if(!ObjectUtils.equals(gxXdrMap, rhs.gxXdrMap)) return false;
		if(!ObjectUtils.equals(inputFileName, rhs.inputFileName)) return false;
		if(!ObjectUtils.equals(tXdr, rhs.tXdr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(chargeMode)
		.append(dccHead)
		.append(sockInfo)
		.append(featureCode)
		.append(splitInfo)
		.append(common)
		.append(totalXdr)
		.append(subXdrMap)
		.append(managerInfo)
		.append(rggXdrMap)
		.append(functionFlag)
		.append(gxFlag)
		.append(gxXdrMap)
		.append(inputFileName)
		.append(tXdr)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(15);
public static final long BITS_ALL_MARKER = 0x4000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "CHARGE_MODE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "DCC_HEAD", 1, SDccHead.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "SOCK_INFO", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "FEATURE_CODE", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "SPLIT_INFO", 4, SSplitInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "COMMON", 5, SCommon.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "TOTAL_XDR", 6, SSubXdr.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "SUB_XDR_MAP", 7, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "MANAGER_INFO", 8, SManagerInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "RGG_XDR_MAP", 9, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "FUNCTION_FLAG", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "GX_FLAG", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "GX_XDR_MAP", 12, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "INPUT_FILE_NAME", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SXdr.class, "T_XDR", 14, CsdlXdrObject.class));
}

}