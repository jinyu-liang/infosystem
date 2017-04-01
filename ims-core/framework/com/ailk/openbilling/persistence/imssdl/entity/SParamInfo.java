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
@XmlType(propOrder={"paramId","value","validDate","expireDate"})
@Sdl(module="MImsSyncDef")
public class SParamInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PARAM_ID = "PARAM_ID";
	public final static String COL_VALUE = "VALUE";
	public final static String COL_VALID_DATE = "VALID_DATE";
	public final static String COL_EXPIRE_DATE = "EXPIRE_DATE";
	public final static int IDX_PARAM_ID = 0;
	public final static int IDX_VALUE = 1;
	public final static int IDX_VALID_DATE = 2;
	public final static int IDX_EXPIRE_DATE = 3;

	/**
	 * 
	 */
	@XmlElement(name="paramId")
	@Sdl
	private int paramId;

	/**
	 * 
	 */
	@XmlElement(name="value")
	@Sdl
	private String value;

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

	public void setParamId(int obj){
		this.paramId = obj;
		onFieldSet(0, obj);
	}

	public int getParamId(){
		return paramId;
	}

	public void setValue(String obj){
		this.value = obj;
		onFieldSet(1, obj);
	}

	public String getValue(){
		return value;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
		onFieldSet(2, obj);
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
		onFieldSet(3, obj);
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SParamInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SParamInfo(SParamInfo arg0){
		copy(arg0);
	}

	public void copy(final SParamInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		paramId = rhs.paramId;
		value = rhs.value;
		validDate = rhs.validDate;
		expireDate = rhs.expireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SParamInfo rhs=(SParamInfo)rhs0;
		if(!ObjectUtils.equals(paramId, rhs.paramId)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paramId)
		.append(value)
		.append(validDate)
		.append(expireDate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SParamInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SParamInfo.class, "PARAM_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SParamInfo.class, "VALUE", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SParamInfo.class, "VALID_DATE", 2, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SParamInfo.class, "EXPIRE_DATE", 3, Date.class));
}

}