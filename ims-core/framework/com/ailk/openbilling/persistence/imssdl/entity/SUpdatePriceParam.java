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
@XmlType(propOrder={"prodId","paramId","paramValue","validDate","expireDate"})
@Sdl(module="MImsSyncDef")
public class SUpdatePriceParam extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_PARAM_ID = "PARAM_ID";
	public final static String COL_PARAM_VALUE = "PARAM_VALUE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static int IDX_PROD_ID = 0;
	public final static int IDX_PARAM_ID = 1;
	public final static int IDX_PARAM_VALUE = 2;
	public final static int IDX_VALID_DATE = 3;
	public final static int IDX_EXPIRE_DATE = 4;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="paramId")
	@Sdl
	private int paramId;

	/**
	 * 
	 */
	@XmlElement(name="paramValue")
	@Sdl
	private String paramValue;

	/**
	 * 
	 */
	@XmlElement(name="validDate")
	@Sdl
	private long validDate;

	/**
	 * 
	 */
	@XmlElement(name="expireDate")
	@Sdl
	private long expireDate;

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(0, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setParamId(int obj){
		this.paramId = obj;
		onFieldSet(1, obj);
	}

	public int getParamId(){
		return paramId;
	}

	public void setParamValue(String obj){
		this.paramValue = obj;
		onFieldSet(2, obj);
	}

	public String getParamValue(){
		return paramValue;
	}

	public void setValidDate(long obj){
		this.validDate = obj;
		onFieldSet(3, obj);
	}

	public long getValidDate(){
		return validDate;
	}

	public void setExpireDate(long obj){
		this.expireDate = obj;
		onFieldSet(4, obj);
	}

	public long getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SUpdatePriceParam(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 5; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SUpdatePriceParam(SUpdatePriceParam arg0){
		copy(arg0);
	}

	public void copy(final SUpdatePriceParam rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		prodId = rhs.prodId;
		paramId = rhs.paramId;
		paramValue = rhs.paramValue;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUpdatePriceParam rhs=(SUpdatePriceParam)rhs0;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(paramId, rhs.paramId)) return false;
		if(!ObjectUtils.equals(paramValue, rhs.paramValue)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodId)
		.append(paramId)
		.append(paramValue)
		.append(validDate)
		.append(expireDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(5);
public static final long BITS_ALL_MARKER = 0x10L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SUpdatePriceParam";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SUpdatePriceParam.class, "PROD_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpdatePriceParam.class, "PARAM_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpdatePriceParam.class, "PARAM_VALUE", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpdatePriceParam.class, "VALID_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SUpdatePriceParam.class, "EXPIRE_DATE", 4, long.class));
}

}