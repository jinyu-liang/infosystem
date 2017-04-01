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
@XmlType(propOrder={"requestType","callNumber","security","qosType","correction","lspIdInput","lspIdLocal","resultX","resultY","coordinationType"})
@Sdl(module="MXdr")
public class SLbsInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_REQUEST_TYPE = "REQUEST_TYPE";
	public final static String COL_CALL_NUMBER = "CALL_NUMBER";
	public final static String COL_SECURITY = "SECURITY";
	public final static String COL_QOS_TYPE = "QOS_TYPE";
	public final static String COL_CORRECTION = "CORRECTION";
	public final static String COL_LSP_ID_INPUT = "LSP_ID_INPUT";
	public final static String COL_LSP_ID_LOCAL = "LSP_ID_LOCAL";
	public final static String COL_RESULT_X = "RESULT_X";
	public final static String COL_RESULT_Y = "RESULT_Y";
	public final static String COL_COORDINATION_TYPE = "COORDINATION_TYPE";
	public final static int IDX_REQUEST_TYPE = 0;
	public final static int IDX_CALL_NUMBER = 1;
	public final static int IDX_SECURITY = 2;
	public final static int IDX_QOS_TYPE = 3;
	public final static int IDX_CORRECTION = 4;
	public final static int IDX_LSP_ID_INPUT = 5;
	public final static int IDX_LSP_ID_LOCAL = 6;
	public final static int IDX_RESULT_X = 7;
	public final static int IDX_RESULT_Y = 8;
	public final static int IDX_COORDINATION_TYPE = 9;

	/**
	 * 
	 */
	@XmlElement(name="requestType")
	@Sdl
	private short requestType;

	/**
	 * 
	 */
	@XmlElement(name="callNumber")
	@Sdl
	private short callNumber;

	/**
	 * 
	 */
	@XmlElement(name="security")
	@Sdl
	private int security;

	/**
	 * 
	 */
	@XmlElement(name="qosType")
	@Sdl
	private short qosType;

	/**
	 * 
	 */
	@XmlElement(name="correction")
	@Sdl
	private short correction;

	/**
	 * 
	 */
	@XmlElement(name="lspIdInput")
	@Sdl
	private String lspIdInput;

	/**
	 * 
	 */
	@XmlElement(name="lspIdLocal")
	@Sdl
	private String lspIdLocal;

	/**
	 * 
	 */
	@XmlElement(name="resultX")
	@Sdl
	private String resultX;

	/**
	 * 
	 */
	@XmlElement(name="resultY")
	@Sdl
	private String resultY;

	/**
	 * 
	 */
	@XmlElement(name="coordinationType")
	@Sdl
	private String coordinationType;

	public void setRequestType(short obj){
		this.requestType = obj;
		onFieldSet(0, obj);
	}

	public short getRequestType(){
		return requestType;
	}

	public void setCallNumber(short obj){
		this.callNumber = obj;
		onFieldSet(1, obj);
	}

	public short getCallNumber(){
		return callNumber;
	}

	public void setSecurity(int obj){
		this.security = obj;
		onFieldSet(2, obj);
	}

	public int getSecurity(){
		return security;
	}

	public void setQosType(short obj){
		this.qosType = obj;
		onFieldSet(3, obj);
	}

	public short getQosType(){
		return qosType;
	}

	public void setCorrection(short obj){
		this.correction = obj;
		onFieldSet(4, obj);
	}

	public short getCorrection(){
		return correction;
	}

	public void setLspIdInput(String obj){
		this.lspIdInput = obj;
		onFieldSet(5, obj);
	}

	public String getLspIdInput(){
		return lspIdInput;
	}

	public void setLspIdLocal(String obj){
		this.lspIdLocal = obj;
		onFieldSet(6, obj);
	}

	public String getLspIdLocal(){
		return lspIdLocal;
	}

	public void setResultX(String obj){
		this.resultX = obj;
		onFieldSet(7, obj);
	}

	public String getResultX(){
		return resultX;
	}

	public void setResultY(String obj){
		this.resultY = obj;
		onFieldSet(8, obj);
	}

	public String getResultY(){
		return resultY;
	}

	public void setCoordinationType(String obj){
		this.coordinationType = obj;
		onFieldSet(9, obj);
	}

	public String getCoordinationType(){
		return coordinationType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SLbsInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 10; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SLbsInfo(SLbsInfo arg0){
		copy(arg0);
	}

	public void copy(final SLbsInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		requestType = rhs.requestType;
		callNumber = rhs.callNumber;
		security = rhs.security;
		qosType = rhs.qosType;
		correction = rhs.correction;
		lspIdInput = rhs.lspIdInput;
		lspIdLocal = rhs.lspIdLocal;
		resultX = rhs.resultX;
		resultY = rhs.resultY;
		coordinationType = rhs.coordinationType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SLbsInfo rhs=(SLbsInfo)rhs0;
		if(!ObjectUtils.equals(requestType, rhs.requestType)) return false;
		if(!ObjectUtils.equals(callNumber, rhs.callNumber)) return false;
		if(!ObjectUtils.equals(security, rhs.security)) return false;
		if(!ObjectUtils.equals(qosType, rhs.qosType)) return false;
		if(!ObjectUtils.equals(correction, rhs.correction)) return false;
		if(!ObjectUtils.equals(lspIdInput, rhs.lspIdInput)) return false;
		if(!ObjectUtils.equals(lspIdLocal, rhs.lspIdLocal)) return false;
		if(!ObjectUtils.equals(resultX, rhs.resultX)) return false;
		if(!ObjectUtils.equals(resultY, rhs.resultY)) return false;
		if(!ObjectUtils.equals(coordinationType, rhs.coordinationType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(requestType)
		.append(callNumber)
		.append(security)
		.append(qosType)
		.append(correction)
		.append(lspIdInput)
		.append(lspIdLocal)
		.append(resultX)
		.append(resultY)
		.append(coordinationType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(10);
public static final long BITS_ALL_MARKER = 0x200L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SLbsInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "REQUEST_TYPE", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "CALL_NUMBER", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "SECURITY", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "QOS_TYPE", 3, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "CORRECTION", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "LSP_ID_INPUT", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "LSP_ID_LOCAL", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "RESULT_X", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "RESULT_Y", 8, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SLbsInfo.class, "COORDINATION_TYPE", 9, String.class));
}

}