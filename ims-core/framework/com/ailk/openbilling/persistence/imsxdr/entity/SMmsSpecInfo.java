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
@XmlType(propOrder={"mmType","mmscId","forwardIsmgId","carryType","domainName","gateway","cfwGateway","oppGateway","mmsSpecInfoEx"})
@Sdl(module="MXdr")
public class SMmsSpecInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_MM_TYPE = "MM_TYPE";
	public final static String COL_MMSC_ID = "MMSC_ID";
	public final static String COL_FORWARD_ISMG_ID = "FORWARD_ISMG_ID";
	public final static String COL_CARRY_TYPE = "CARRY_TYPE";
	public final static String COL_DOMAIN_NAME = "DOMAIN_NAME";
	public final static String COL_GATEWAY = "GATEWAY";
	public final static String COL_CFW_GATEWAY = "CFW_GATEWAY";
	public final static String COL_OPP_GATEWAY = "OPP_GATEWAY";
	public final static String COL_MMS_SPEC_INFO_EX = "MMS_SPEC_INFO_EX";
	public final static int IDX_MM_TYPE = 0;
	public final static int IDX_MMSC_ID = 1;
	public final static int IDX_FORWARD_ISMG_ID = 2;
	public final static int IDX_CARRY_TYPE = 3;
	public final static int IDX_DOMAIN_NAME = 4;
	public final static int IDX_GATEWAY = 5;
	public final static int IDX_CFW_GATEWAY = 6;
	public final static int IDX_OPP_GATEWAY = 7;
	public final static int IDX_MMS_SPEC_INFO_EX = 8;

	/**
	 * 
	 */
	@XmlElement(name="mmsSpecInfoEx")
	@Sdl
	private SMmsInfoEx mmsSpecInfoEx;

	/**
	 * 
	 */
	@XmlElement(name="mmType")
	@Sdl
	private int mmType;

	/**
	 * 
	 */
	@XmlElement(name="mmscId")
	@Sdl
	private String mmscId;

	/**
	 * 
	 */
	@XmlElement(name="forwardIsmgId")
	@Sdl
	private String forwardIsmgId;

	/**
	 * 
	 */
	@XmlElement(name="carryType")
	@Sdl
	private int carryType;

	/**
	 * 
	 */
	@XmlElement(name="domainName")
	@Sdl
	private String domainName;

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

	public void setMmsSpecInfoEx(SMmsInfoEx obj){
		this.mmsSpecInfoEx = obj;
		onFieldSet(8, obj);
	}

	public SMmsInfoEx getMmsSpecInfoEx(){
		return mmsSpecInfoEx;
	}

	public void setMmType(int obj){
		this.mmType = obj;
		onFieldSet(0, obj);
	}

	public int getMmType(){
		return mmType;
	}

	public void setMmscId(String obj){
		this.mmscId = obj;
		onFieldSet(1, obj);
	}

	public String getMmscId(){
		return mmscId;
	}

	public void setForwardIsmgId(String obj){
		this.forwardIsmgId = obj;
		onFieldSet(2, obj);
	}

	public String getForwardIsmgId(){
		return forwardIsmgId;
	}

	public void setCarryType(int obj){
		this.carryType = obj;
		onFieldSet(3, obj);
	}

	public int getCarryType(){
		return carryType;
	}

	public void setDomainName(String obj){
		this.domainName = obj;
		onFieldSet(4, obj);
	}

	public String getDomainName(){
		return domainName;
	}

	public void setGateway(String obj){
		this.gateway = obj;
		onFieldSet(5, obj);
	}

	public String getGateway(){
		return gateway;
	}

	public void setCfwGateway(String obj){
		this.cfwGateway = obj;
		onFieldSet(6, obj);
	}

	public String getCfwGateway(){
		return cfwGateway;
	}

	public void setOppGateway(String obj){
		this.oppGateway = obj;
		onFieldSet(7, obj);
	}

	public String getOppGateway(){
		return oppGateway;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SMmsSpecInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SMmsSpecInfo(SMmsSpecInfo arg0){
		copy(arg0);
	}

	public void copy(final SMmsSpecInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		mmType = rhs.mmType;
		mmscId = rhs.mmscId;
		forwardIsmgId = rhs.forwardIsmgId;
		carryType = rhs.carryType;
		domainName = rhs.domainName;
		gateway = rhs.gateway;
		cfwGateway = rhs.cfwGateway;
		oppGateway = rhs.oppGateway;
		mmsSpecInfoEx = rhs.mmsSpecInfoEx;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMmsSpecInfo rhs=(SMmsSpecInfo)rhs0;
		if(!ObjectUtils.equals(mmType, rhs.mmType)) return false;
		if(!ObjectUtils.equals(mmscId, rhs.mmscId)) return false;
		if(!ObjectUtils.equals(forwardIsmgId, rhs.forwardIsmgId)) return false;
		if(!ObjectUtils.equals(carryType, rhs.carryType)) return false;
		if(!ObjectUtils.equals(domainName, rhs.domainName)) return false;
		if(!ObjectUtils.equals(gateway, rhs.gateway)) return false;
		if(!ObjectUtils.equals(cfwGateway, rhs.cfwGateway)) return false;
		if(!ObjectUtils.equals(oppGateway, rhs.oppGateway)) return false;
		if(!ObjectUtils.equals(mmsSpecInfoEx, rhs.mmsSpecInfoEx)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(mmType)
		.append(mmscId)
		.append(forwardIsmgId)
		.append(carryType)
		.append(domainName)
		.append(gateway)
		.append(cfwGateway)
		.append(oppGateway)
		.append(mmsSpecInfoEx)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SMmsSpecInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "MM_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "MMSC_ID", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "FORWARD_ISMG_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "CARRY_TYPE", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "DOMAIN_NAME", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "GATEWAY", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "CFW_GATEWAY", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "OPP_GATEWAY", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SMmsSpecInfo.class, "MMS_SPEC_INFO_EX", 8, SMmsInfoEx.class));
}

}