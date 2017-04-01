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
@XmlType(propOrder={"subCommon","gsmInfo","gprsInfo","mmsInfo","smsInfo","ismpInfo","gsInfo","extension","subXdrType"})
@Sdl(module="MXdr")
public class SSubXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SUB_COMMON = "SUB_COMMON";
	public final static String COL_GSM_INFO = "GSM_INFO";
	public final static String COL_GPRS_INFO = "GPRS_INFO";
	public final static String COL_MMS_INFO = "MMS_INFO";
	public final static String COL_SMS_INFO = "SMS_INFO";
	public final static String COL_ISMP_INFO = "ISMP_INFO";
	public final static String COL_GS_INFO = "GS_INFO";
	public final static String COL_EXTENSION = "EXTENSION";
	public final static String COL_SUB_XDR_TYPE = "SUB_XDR_TYPE";
	public final static int IDX_SUB_COMMON = 0;
	public final static int IDX_GSM_INFO = 1;
	public final static int IDX_GPRS_INFO = 2;
	public final static int IDX_MMS_INFO = 3;
	public final static int IDX_SMS_INFO = 4;
	public final static int IDX_ISMP_INFO = 5;
	public final static int IDX_GS_INFO = 6;
	public final static int IDX_EXTENSION = 7;
	public final static int IDX_SUB_XDR_TYPE = 8;

	/**
	 * 
	 */
	@XmlElement(name="subCommon")
	@Sdl
	private SSubCommon subCommon;

	/**
	 * 
	 */
	@XmlElement(name="gsmInfo")
	@Sdl
	private SGsmInfo gsmInfo;

	/**
	 * 
	 */
	@XmlElement(name="gprsInfo")
	@Sdl
	private SGprsInfo gprsInfo;

	/**
	 * 
	 */
	@XmlElement(name="mmsInfo")
	@Sdl
	private SMmsInfo mmsInfo;

	/**
	 * 
	 */
	@XmlElement(name="smsInfo")
	@Sdl
	private SSmsInfo smsInfo;

	/**
	 * 
	 */
	@XmlElement(name="ismpInfo")
	@Sdl
	private SIsmpInfo ismpInfo;

	/**
	 * 
	 */
	@XmlElement(name="gsInfo")
	@Sdl
	private SGsInfo gsInfo;

	/**
	 * 
	 */
	@XmlElement(name="extension")
	@Sdl
	private Map<String,String> extension;

	/**
	 * 
	 */
	@XmlElement(name="subXdrType")
	@Sdl
	private short subXdrType;

	public void setSubCommon(SSubCommon obj){
		this.subCommon = obj;
		onFieldSet(0, obj);
	}

	public SSubCommon getSubCommon(){
		return subCommon;
	}

	public void setGsmInfo(SGsmInfo obj){
		this.gsmInfo = obj;
		onFieldSet(1, obj);
	}

	public SGsmInfo getGsmInfo(){
		return gsmInfo;
	}

	public void setGprsInfo(SGprsInfo obj){
		this.gprsInfo = obj;
		onFieldSet(2, obj);
	}

	public SGprsInfo getGprsInfo(){
		return gprsInfo;
	}

	public void setMmsInfo(SMmsInfo obj){
		this.mmsInfo = obj;
		onFieldSet(3, obj);
	}

	public SMmsInfo getMmsInfo(){
		return mmsInfo;
	}

	public void setSmsInfo(SSmsInfo obj){
		this.smsInfo = obj;
		onFieldSet(4, obj);
	}

	public SSmsInfo getSmsInfo(){
		return smsInfo;
	}

	public void setIsmpInfo(SIsmpInfo obj){
		this.ismpInfo = obj;
		onFieldSet(5, obj);
	}

	public SIsmpInfo getIsmpInfo(){
		return ismpInfo;
	}

	public void setGsInfo(SGsInfo obj){
		this.gsInfo = obj;
		onFieldSet(6, obj);
	}

	public SGsInfo getGsInfo(){
		return gsInfo;
	}

	public void setExtension(Map<String,String> obj){
		this.extension = obj;
		onFieldSet(7, obj);
	}

	public Map<String,String> getExtension(){
		return extension;
	}

	public void setSubXdrType(short obj){
		this.subXdrType = obj;
		onFieldSet(8, obj);
	}

	public short getSubXdrType(){
		return subXdrType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSubXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSubXdr(SSubXdr arg0){
		copy(arg0);
	}

	public void copy(final SSubXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		subCommon = rhs.subCommon;
		gsmInfo = rhs.gsmInfo;
		gprsInfo = rhs.gprsInfo;
		mmsInfo = rhs.mmsInfo;
		smsInfo = rhs.smsInfo;
		ismpInfo = rhs.ismpInfo;
		gsInfo = rhs.gsInfo;
		extension = rhs.extension;
		subXdrType = rhs.subXdrType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSubXdr rhs=(SSubXdr)rhs0;
		if(!ObjectUtils.equals(subCommon, rhs.subCommon)) return false;
		if(!ObjectUtils.equals(gsmInfo, rhs.gsmInfo)) return false;
		if(!ObjectUtils.equals(gprsInfo, rhs.gprsInfo)) return false;
		if(!ObjectUtils.equals(mmsInfo, rhs.mmsInfo)) return false;
		if(!ObjectUtils.equals(smsInfo, rhs.smsInfo)) return false;
		if(!ObjectUtils.equals(ismpInfo, rhs.ismpInfo)) return false;
		if(!ObjectUtils.equals(gsInfo, rhs.gsInfo)) return false;
		if(!ObjectUtils.equals(extension, rhs.extension)) return false;
		if(!ObjectUtils.equals(subXdrType, rhs.subXdrType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(subCommon)
		.append(gsmInfo)
		.append(gprsInfo)
		.append(mmsInfo)
		.append(smsInfo)
		.append(ismpInfo)
		.append(gsInfo)
		.append(extension)
		.append(subXdrType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSubXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "SUB_COMMON", 0, SSubCommon.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "GSM_INFO", 1, SGsmInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "GPRS_INFO", 2, SGprsInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "MMS_INFO", 3, SMmsInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "SMS_INFO", 4, SSmsInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "ISMP_INFO", 5, SIsmpInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "GS_INFO", 6, SGsInfo.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "EXTENSION", 7, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSubXdr.class, "SUB_XDR_TYPE", 8, short.class));
}

}