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
@XmlType(propOrder={"ctxCallerGroup","carryService","serviceScope","termService","callerDevType","calleeDevType","callScope"})
@Sdl(module="MXdr")
public class SPbxInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CTX_CALLER_GROUP = "CTX_CALLER_GROUP";
	public final static String COL_CARRY_SERVICE = "CARRY_SERVICE";
	public final static String COL_SERVICE_SCOPE = "SERVICE_SCOPE";
	public final static String COL_TERM_SERVICE = "TERM_SERVICE";
	public final static String COL_CALLER_DEV_TYPE = "CALLER_DEV_TYPE";
	public final static String COL_CALLEE_DEV_TYPE = "CALLEE_DEV_TYPE";
	public final static String COL_CALL_SCOPE = "CALL_SCOPE";
	public final static int IDX_CTX_CALLER_GROUP = 0;
	public final static int IDX_CARRY_SERVICE = 1;
	public final static int IDX_SERVICE_SCOPE = 2;
	public final static int IDX_TERM_SERVICE = 3;
	public final static int IDX_CALLER_DEV_TYPE = 4;
	public final static int IDX_CALLEE_DEV_TYPE = 5;
	public final static int IDX_CALL_SCOPE = 6;

	/**
	 * 
	 */
	@XmlElement(name="ctxCallerGroup")
	@Sdl
	private String ctxCallerGroup;

	/**
	 * 
	 */
	@XmlElement(name="carryService")
	@Sdl
	private long carryService;

	/**
	 * 
	 */
	@XmlElement(name="serviceScope")
	@Sdl
	private int serviceScope;

	/**
	 * 
	 */
	@XmlElement(name="termService")
	@Sdl
	private long termService;

	/**
	 * 
	 */
	@XmlElement(name="callerDevType")
	@Sdl
	private long callerDevType;

	/**
	 * 
	 */
	@XmlElement(name="calleeDevType")
	@Sdl
	private long calleeDevType;

	/**
	 * 
	 */
	@XmlElement(name="callScope")
	@Sdl
	private short callScope;

	public void setCtxCallerGroup(String obj){
		this.ctxCallerGroup = obj;
		onFieldSet(0, obj);
	}

	public String getCtxCallerGroup(){
		return ctxCallerGroup;
	}

	public void setCarryService(long obj){
		this.carryService = obj;
		onFieldSet(1, obj);
	}

	public long getCarryService(){
		return carryService;
	}

	public void setServiceScope(int obj){
		this.serviceScope = obj;
		onFieldSet(2, obj);
	}

	public int getServiceScope(){
		return serviceScope;
	}

	public void setTermService(long obj){
		this.termService = obj;
		onFieldSet(3, obj);
	}

	public long getTermService(){
		return termService;
	}

	public void setCallerDevType(long obj){
		this.callerDevType = obj;
		onFieldSet(4, obj);
	}

	public long getCallerDevType(){
		return callerDevType;
	}

	public void setCalleeDevType(long obj){
		this.calleeDevType = obj;
		onFieldSet(5, obj);
	}

	public long getCalleeDevType(){
		return calleeDevType;
	}

	public void setCallScope(short obj){
		this.callScope = obj;
		onFieldSet(6, obj);
	}

	public short getCallScope(){
		return callScope;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SPbxInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SPbxInfo(SPbxInfo arg0){
		copy(arg0);
	}

	public void copy(final SPbxInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		ctxCallerGroup = rhs.ctxCallerGroup;
		carryService = rhs.carryService;
		serviceScope = rhs.serviceScope;
		termService = rhs.termService;
		callerDevType = rhs.callerDevType;
		calleeDevType = rhs.calleeDevType;
		callScope = rhs.callScope;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPbxInfo rhs=(SPbxInfo)rhs0;
		if(!ObjectUtils.equals(ctxCallerGroup, rhs.ctxCallerGroup)) return false;
		if(!ObjectUtils.equals(carryService, rhs.carryService)) return false;
		if(!ObjectUtils.equals(serviceScope, rhs.serviceScope)) return false;
		if(!ObjectUtils.equals(termService, rhs.termService)) return false;
		if(!ObjectUtils.equals(callerDevType, rhs.callerDevType)) return false;
		if(!ObjectUtils.equals(calleeDevType, rhs.calleeDevType)) return false;
		if(!ObjectUtils.equals(callScope, rhs.callScope)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(ctxCallerGroup)
		.append(carryService)
		.append(serviceScope)
		.append(termService)
		.append(callerDevType)
		.append(calleeDevType)
		.append(callScope)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SPbxInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "CTX_CALLER_GROUP", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "CARRY_SERVICE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "SERVICE_SCOPE", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "TERM_SERVICE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "CALLER_DEV_TYPE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "CALLEE_DEV_TYPE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SPbxInfo.class, "CALL_SCOPE", 6, short.class));
}

}