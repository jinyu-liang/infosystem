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
@XmlType(propOrder={"checksms","listchecksmsdetail"})
@Sdl(module="MImsSyncDef")
public class SBatchSmsInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CHECKSMS = "CHECKSMS";
	public final static String COL_LISTCHECKSMSDETAIL = "LISTCHECKSMSDETAIL";
	public final static int IDX_CHECKSMS = 0;
	public final static int IDX_LISTCHECKSMSDETAIL = 1;

	/**
	 * 
	 */
	@XmlElement(name="checksms")
	@Sdl
	private SCheckSms checksms;

	/**
	 * 
	 */
	@XmlElement(name="listchecksmsdetail")
	@Sdl
	private List<SCheckSmsDetail> listchecksmsdetail;

	public void setChecksms(SCheckSms obj){
		this.checksms = obj;
		onFieldSet(0, obj);
	}

	public SCheckSms getChecksms(){
		return checksms;
	}

	public void setListchecksmsdetail(List<SCheckSmsDetail> obj){
		this.listchecksmsdetail = obj;
		onFieldSet(1, obj);
	}

	public List<SCheckSmsDetail> getListchecksmsdetail(){
		return listchecksmsdetail;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBatchSmsInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 2; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBatchSmsInfo(SBatchSmsInfo arg0){
		copy(arg0);
	}

	public void copy(final SBatchSmsInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		checksms = rhs.checksms;
		listchecksmsdetail = rhs.listchecksmsdetail;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBatchSmsInfo rhs=(SBatchSmsInfo)rhs0;
		if(!ObjectUtils.equals(checksms, rhs.checksms)) return false;
		if(!ObjectUtils.equals(listchecksmsdetail, rhs.listchecksmsdetail)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(checksms)
		.append(listchecksmsdetail)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(2);
public static final long BITS_ALL_MARKER = 0x2L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SBatchSmsInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBatchSmsInfo.class, "CHECKSMS", 0, SCheckSms.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBatchSmsInfo.class, "LISTCHECKSMSDETAIL", 1, List.class));
}

}