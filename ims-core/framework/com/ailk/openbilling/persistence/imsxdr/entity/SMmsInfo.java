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
@XmlType(propOrder={"test","infoType","chargeType","applicationType"})
@Sdl(module="MXdr")
public class SMmsInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_TEST = "TEST";
	public final static String COL_INFO_TYPE = "INFO_TYPE";
	public final static String COL_CHARGE_TYPE = "CHARGE_TYPE";
	public final static String COL_APPLICATION_TYPE = "APPLICATION_TYPE";
	public final static int IDX_TEST = 0;
	public final static int IDX_INFO_TYPE = 1;
	public final static int IDX_CHARGE_TYPE = 2;
	public final static int IDX_APPLICATION_TYPE = 3;

	/**
	 * 
	 */
	@XmlElement(name="test")
	@Sdl
	private int test;

	/**
	 * 
	 */
	@XmlElement(name="infoType")
	@Sdl
	private int infoType;

	/**
	 * 
	 */
	@XmlElement(name="chargeType")
	@Sdl
	private int chargeType;

	/**
	 * 
	 */
	@XmlElement(name="applicationType")
	@Sdl
	private int applicationType;

	public void setTest(int obj){
		this.test = obj;
		onFieldSet(0, obj);
	}

	public int getTest(){
		return test;
	}

	public void setInfoType(int obj){
		this.infoType = obj;
		onFieldSet(1, obj);
	}

	public int getInfoType(){
		return infoType;
	}

	public void setChargeType(int obj){
		this.chargeType = obj;
		onFieldSet(2, obj);
	}

	public int getChargeType(){
		return chargeType;
	}

	public void setApplicationType(int obj){
		this.applicationType = obj;
		onFieldSet(3, obj);
	}

	public int getApplicationType(){
		return applicationType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SMmsInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 4; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SMmsInfo(SMmsInfo arg0){
		copy(arg0);
	}

	public void copy(final SMmsInfo rhs){
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
		infoType = rhs.infoType;
		chargeType = rhs.chargeType;
		applicationType = rhs.applicationType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMmsInfo rhs=(SMmsInfo)rhs0;
		if(!ObjectUtils.equals(test, rhs.test)) return false;
		if(!ObjectUtils.equals(infoType, rhs.infoType)) return false;
		if(!ObjectUtils.equals(chargeType, rhs.chargeType)) return false;
		if(!ObjectUtils.equals(applicationType, rhs.applicationType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(test)
		.append(infoType)
		.append(chargeType)
		.append(applicationType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(4);
public static final long BITS_ALL_MARKER = 0x8L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SMmsInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfo.class, "TEST", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfo.class, "INFO_TYPE", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfo.class, "CHARGE_TYPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsInfo.class, "APPLICATION_TYPE", 3, int.class));
}

}