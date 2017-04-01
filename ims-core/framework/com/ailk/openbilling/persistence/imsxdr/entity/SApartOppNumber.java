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
@XmlType(propOrder={"accessNumber","iddNumber","countryCode","mnpPrefix","fphCode","npCode","pCode"})
@Sdl(module="MXdr")
public class SApartOppNumber extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCESS_NUMBER = "ACCESS_NUMBER";
	public final static String COL_IDD_NUMBER = "IDD_NUMBER";
	public final static String COL_COUNTRY_CODE = "COUNTRY_CODE";
	public final static String COL_MNP_PREFIX = "MNP_PREFIX";
	public final static String COL_FPH_CODE = "FPH_CODE";
	public final static String COL_NP_CODE = "NP_CODE";
	public final static String COL_P_CODE = "P_CODE";
	public final static int IDX_ACCESS_NUMBER = 0;
	public final static int IDX_IDD_NUMBER = 1;
	public final static int IDX_COUNTRY_CODE = 2;
	public final static int IDX_MNP_PREFIX = 3;
	public final static int IDX_FPH_CODE = 4;
	public final static int IDX_NP_CODE = 5;
	public final static int IDX_P_CODE = 6;

	/**
	 * 
	 */
	@XmlElement(name="accessNumber")
	@Sdl
	private String accessNumber;

	/**
	 * 
	 */
	@XmlElement(name="iddNumber")
	@Sdl
	private String iddNumber;

	/**
	 * 
	 */
	@XmlElement(name="countryCode")
	@Sdl
	private String countryCode;

	/**
	 * 
	 */
	@XmlElement(name="mnpPrefix")
	@Sdl
	private String mnpPrefix;

	/**
	 * 
	 */
	@XmlElement(name="fphCode")
	@Sdl
	private String fphCode;

	/**
	 * 
	 */
	@XmlElement(name="npCode")
	@Sdl
	private String npCode;

	/**
	 * 
	 */
	@XmlElement(name="pCode")
	@Sdl
	private String pCode;

	public void setAccessNumber(String obj){
		this.accessNumber = obj;
		onFieldSet(0, obj);
	}

	public String getAccessNumber(){
		return accessNumber;
	}

	public void setIddNumber(String obj){
		this.iddNumber = obj;
		onFieldSet(1, obj);
	}

	public String getIddNumber(){
		return iddNumber;
	}

	public void setCountryCode(String obj){
		this.countryCode = obj;
		onFieldSet(2, obj);
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setMnpPrefix(String obj){
		this.mnpPrefix = obj;
		onFieldSet(3, obj);
	}

	public String getMnpPrefix(){
		return mnpPrefix;
	}

	public void setFphCode(String obj){
		this.fphCode = obj;
		onFieldSet(4, obj);
	}

	public String getFphCode(){
		return fphCode;
	}

	public void setNpCode(String obj){
		this.npCode = obj;
		onFieldSet(5, obj);
	}

	public String getNpCode(){
		return npCode;
	}

	public void setPCode(String obj){
		this.pCode = obj;
		onFieldSet(6, obj);
	}

	public String getPCode(){
		return pCode;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SApartOppNumber(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SApartOppNumber(SApartOppNumber arg0){
		copy(arg0);
	}

	public void copy(final SApartOppNumber rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		accessNumber = rhs.accessNumber;
		iddNumber = rhs.iddNumber;
		countryCode = rhs.countryCode;
		mnpPrefix = rhs.mnpPrefix;
		fphCode = rhs.fphCode;
		npCode = rhs.npCode;
		pCode = rhs.pCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SApartOppNumber rhs=(SApartOppNumber)rhs0;
		if(!ObjectUtils.equals(accessNumber, rhs.accessNumber)) return false;
		if(!ObjectUtils.equals(iddNumber, rhs.iddNumber)) return false;
		if(!ObjectUtils.equals(countryCode, rhs.countryCode)) return false;
		if(!ObjectUtils.equals(mnpPrefix, rhs.mnpPrefix)) return false;
		if(!ObjectUtils.equals(fphCode, rhs.fphCode)) return false;
		if(!ObjectUtils.equals(npCode, rhs.npCode)) return false;
		if(!ObjectUtils.equals(pCode, rhs.pCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accessNumber)
		.append(iddNumber)
		.append(countryCode)
		.append(mnpPrefix)
		.append(fphCode)
		.append(npCode)
		.append(pCode)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SApartOppNumber";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "ACCESS_NUMBER", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "IDD_NUMBER", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "COUNTRY_CODE", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "MNP_PREFIX", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "FPH_CODE", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "NP_CODE", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SApartOppNumber.class, "P_CODE", 6, String.class));
}

}