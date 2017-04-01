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
@XmlType(propOrder={"soBbr","soDate","errorCode","errorDesc","redirectUrl","extraMap"})
@Sdl(module="MXdr")
public class SBusinessInteraction extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SO_BBR = "SO_BBR";
	public final static String COL_SO_DATE = "SO_DATE";
	public final static String COL_ERROR_CODE = "ERROR_CODE";
	public final static String COL_ERROR_DESC = "ERROR_DESC";
	public final static String COL_REDIRECT_URL = "REDIRECT_URL";
	public final static String COL_EXTRA_MAP = "EXTRA_MAP";
	public final static int IDX_SO_BBR = 0;
	public final static int IDX_SO_DATE = 1;
	public final static int IDX_ERROR_CODE = 2;
	public final static int IDX_ERROR_DESC = 3;
	public final static int IDX_REDIRECT_URL = 4;
	public final static int IDX_EXTRA_MAP = 5;

	/**
	 * 
	 */
	@XmlElement(name="extraMap")
	@Sdl
	private Map<String,String> extraMap;

	/**
	 * 
	 */
	@XmlElement(name="soBbr")
	@Sdl
	private long soBbr;

	/**
	 * 
	 */
	@XmlElement(name="soDate")
	@Sdl
	private long soDate;

	/**
	 * 
	 */
	@XmlElement(name="errorCode")
	@Sdl
	private int errorCode;

	/**
	 * 
	 */
	@XmlElement(name="errorDesc")
	@Sdl
	private String errorDesc;

	/**
	 * 
	 */
	@XmlElement(name="redirectUrl")
	@Sdl
	private String redirectUrl;

	public void setExtraMap(Map<String,String> obj){
		this.extraMap = obj;
		onFieldSet(5, obj);
	}

	public Map<String,String> getExtraMap(){
		return extraMap;
	}

	public void setSoBbr(long obj){
		this.soBbr = obj;
		onFieldSet(0, obj);
	}

	public long getSoBbr(){
		return soBbr;
	}

	public void setSoDate(long obj){
		this.soDate = obj;
		onFieldSet(1, obj);
	}

	public long getSoDate(){
		return soDate;
	}

	public void setErrorCode(int obj){
		this.errorCode = obj;
		onFieldSet(2, obj);
	}

	public int getErrorCode(){
		return errorCode;
	}

	public void setErrorDesc(String obj){
		this.errorDesc = obj;
		onFieldSet(3, obj);
	}

	public String getErrorDesc(){
		return errorDesc;
	}

	public void setRedirectUrl(String obj){
		this.redirectUrl = obj;
		onFieldSet(4, obj);
	}

	public String getRedirectUrl(){
		return redirectUrl;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBusinessInteraction(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 6; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBusinessInteraction(SBusinessInteraction arg0){
		copy(arg0);
	}

	public void copy(final SBusinessInteraction rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		soBbr = rhs.soBbr;
		soDate = rhs.soDate;
		errorCode = rhs.errorCode;
		errorDesc = rhs.errorDesc;
		redirectUrl = rhs.redirectUrl;
		extraMap = rhs.extraMap;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBusinessInteraction rhs=(SBusinessInteraction)rhs0;
		if(!ObjectUtils.equals(soBbr, rhs.soBbr)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(errorCode, rhs.errorCode)) return false;
		if(!ObjectUtils.equals(errorDesc, rhs.errorDesc)) return false;
		if(!ObjectUtils.equals(redirectUrl, rhs.redirectUrl)) return false;
		if(!ObjectUtils.equals(extraMap, rhs.extraMap)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(soBbr)
		.append(soDate)
		.append(errorCode)
		.append(errorDesc)
		.append(redirectUrl)
		.append(extraMap)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(6);
public static final long BITS_ALL_MARKER = 0x20L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBusinessInteraction";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInteraction.class, "SO_BBR", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInteraction.class, "SO_DATE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInteraction.class, "ERROR_CODE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInteraction.class, "ERROR_DESC", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInteraction.class, "REDIRECT_URL", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBusinessInteraction.class, "EXTRA_MAP", 5, Map.class));
}

}