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
@XmlType(propOrder={"smsId","sccpAddress","gateway","cfwGateway","oppGateway","ssr","pid","dcs","retryTimes","priorLevel","ismgId","mocBillType","mtcBillType","udHi","udHid","udPnum","udCurPno","smsType","oppUserType"})
@Sdl(module="MXdr")
public class SSmsSpecInfo extends CsdlStructObject implements IComplexEntity{

	public final static String COL_SMS_ID = "SMS_ID";
	public final static String COL_SCCP_ADDRESS = "SCCP_ADDRESS";
	public final static String COL_GATEWAY = "GATEWAY";
	public final static String COL_CFW_GATEWAY = "CFW_GATEWAY";
	public final static String COL_OPP_GATEWAY = "OPP_GATEWAY";
	public final static String COL_SSR = "SSR";
	public final static String COL_PID = "PID";
	public final static String COL_DCS = "DCS";
	public final static String COL_RETRY_TIMES = "RETRY_TIMES";
	public final static String COL_PRIOR_LEVEL = "PRIOR_LEVEL";
	public final static String COL_ISMG_ID = "ISMG_ID";
	public final static String COL_MOC_BILL_TYPE = "MOC_BILL_TYPE";
	public final static String COL_MTC_BILL_TYPE = "MTC_BILL_TYPE";
	public final static String COL_UD_HI = "UD_HI";
	public final static String COL_UD_HID = "UD_HID";
	public final static String COL_UD_PNUM = "UD_PNUM";
	public final static String COL_UD_CUR_PNO = "UD_CUR_PNO";
	public final static String COL_SMS_TYPE = "SMS_TYPE";
	public final static String COL_OPP_USER_TYPE = "OPP_USER_TYPE";
	public final static int IDX_SMS_ID = 0;
	public final static int IDX_SCCP_ADDRESS = 1;
	public final static int IDX_GATEWAY = 2;
	public final static int IDX_CFW_GATEWAY = 3;
	public final static int IDX_OPP_GATEWAY = 4;
	public final static int IDX_SSR = 5;
	public final static int IDX_PID = 6;
	public final static int IDX_DCS = 7;
	public final static int IDX_RETRY_TIMES = 8;
	public final static int IDX_PRIOR_LEVEL = 9;
	public final static int IDX_ISMG_ID = 10;
	public final static int IDX_MOC_BILL_TYPE = 11;
	public final static int IDX_MTC_BILL_TYPE = 12;
	public final static int IDX_UD_HI = 13;
	public final static int IDX_UD_HID = 14;
	public final static int IDX_UD_PNUM = 15;
	public final static int IDX_UD_CUR_PNO = 16;
	public final static int IDX_SMS_TYPE = 17;
	public final static int IDX_OPP_USER_TYPE = 18;

	/**
	 * 
	 */
	@XmlElement(name="smsId")
	@Sdl
	private String smsId;

	/**
	 * 
	 */
	@XmlElement(name="sccpAddress")
	@Sdl
	private String sccpAddress;

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

	/**
	 * 
	 */
	@XmlElement(name="ssr")
	@Sdl
	private String ssr;

	/**
	 * 
	 */
	@XmlElement(name="pid")
	@Sdl
	private String pid;

	/**
	 * 
	 */
	@XmlElement(name="dcs")
	@Sdl
	private String dcs;

	/**
	 * 
	 */
	@XmlElement(name="retryTimes")
	@Sdl
	private int retryTimes;

	/**
	 * 
	 */
	@XmlElement(name="priorLevel")
	@Sdl
	private int priorLevel;

	/**
	 * 
	 */
	@XmlElement(name="ismgId")
	@Sdl
	private String ismgId;

	/**
	 * 
	 */
	@XmlElement(name="mocBillType")
	@Sdl
	private String mocBillType;

	/**
	 * 
	 */
	@XmlElement(name="mtcBillType")
	@Sdl
	private String mtcBillType;

	/**
	 * 
	 */
	@XmlElement(name="udHi")
	@Sdl
	private short udHi;

	/**
	 * 
	 */
	@XmlElement(name="udHid")
	@Sdl
	private long udHid;

	/**
	 * 
	 */
	@XmlElement(name="udPnum")
	@Sdl
	private long udPnum;

	/**
	 * 
	 */
	@XmlElement(name="udCurPno")
	@Sdl
	private long udCurPno;

	/**
	 * 
	 */
	@XmlElement(name="smsType")
	@Sdl
	private int smsType;

	/**
	 * 
	 */
	@XmlElement(name="oppUserType")
	@Sdl
	private int oppUserType;

	public void setSmsId(String obj){
		this.smsId = obj;
		onFieldSet(0, obj);
	}

	public String getSmsId(){
		return smsId;
	}

	public void setSccpAddress(String obj){
		this.sccpAddress = obj;
		onFieldSet(1, obj);
	}

	public String getSccpAddress(){
		return sccpAddress;
	}

	public void setGateway(String obj){
		this.gateway = obj;
		onFieldSet(2, obj);
	}

	public String getGateway(){
		return gateway;
	}

	public void setCfwGateway(String obj){
		this.cfwGateway = obj;
		onFieldSet(3, obj);
	}

	public String getCfwGateway(){
		return cfwGateway;
	}

	public void setOppGateway(String obj){
		this.oppGateway = obj;
		onFieldSet(4, obj);
	}

	public String getOppGateway(){
		return oppGateway;
	}

	public void setSsr(String obj){
		this.ssr = obj;
		onFieldSet(5, obj);
	}

	public String getSsr(){
		return ssr;
	}

	public void setPid(String obj){
		this.pid = obj;
		onFieldSet(6, obj);
	}

	public String getPid(){
		return pid;
	}

	public void setDcs(String obj){
		this.dcs = obj;
		onFieldSet(7, obj);
	}

	public String getDcs(){
		return dcs;
	}

	public void setRetryTimes(int obj){
		this.retryTimes = obj;
		onFieldSet(8, obj);
	}

	public int getRetryTimes(){
		return retryTimes;
	}

	public void setPriorLevel(int obj){
		this.priorLevel = obj;
		onFieldSet(9, obj);
	}

	public int getPriorLevel(){
		return priorLevel;
	}

	public void setIsmgId(String obj){
		this.ismgId = obj;
		onFieldSet(10, obj);
	}

	public String getIsmgId(){
		return ismgId;
	}

	public void setMocBillType(String obj){
		this.mocBillType = obj;
		onFieldSet(11, obj);
	}

	public String getMocBillType(){
		return mocBillType;
	}

	public void setMtcBillType(String obj){
		this.mtcBillType = obj;
		onFieldSet(12, obj);
	}

	public String getMtcBillType(){
		return mtcBillType;
	}

	public void setUdHi(short obj){
		this.udHi = obj;
		onFieldSet(13, obj);
	}

	public short getUdHi(){
		return udHi;
	}

	public void setUdHid(long obj){
		this.udHid = obj;
		onFieldSet(14, obj);
	}

	public long getUdHid(){
		return udHid;
	}

	public void setUdPnum(long obj){
		this.udPnum = obj;
		onFieldSet(15, obj);
	}

	public long getUdPnum(){
		return udPnum;
	}

	public void setUdCurPno(long obj){
		this.udCurPno = obj;
		onFieldSet(16, obj);
	}

	public long getUdCurPno(){
		return udCurPno;
	}

	public void setSmsType(int obj){
		this.smsType = obj;
		onFieldSet(17, obj);
	}

	public int getSmsType(){
		return smsType;
	}

	public void setOppUserType(int obj){
		this.oppUserType = obj;
		onFieldSet(18, obj);
	}

	public int getOppUserType(){
		return oppUserType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SSmsSpecInfo(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 19; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SSmsSpecInfo(SSmsSpecInfo arg0){
		copy(arg0);
	}

	public void copy(final SSmsSpecInfo rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		smsId = rhs.smsId;
		sccpAddress = rhs.sccpAddress;
		gateway = rhs.gateway;
		cfwGateway = rhs.cfwGateway;
		oppGateway = rhs.oppGateway;
		ssr = rhs.ssr;
		pid = rhs.pid;
		dcs = rhs.dcs;
		retryTimes = rhs.retryTimes;
		priorLevel = rhs.priorLevel;
		ismgId = rhs.ismgId;
		mocBillType = rhs.mocBillType;
		mtcBillType = rhs.mtcBillType;
		udHi = rhs.udHi;
		udHid = rhs.udHid;
		udPnum = rhs.udPnum;
		udCurPno = rhs.udCurPno;
		smsType = rhs.smsType;
		oppUserType = rhs.oppUserType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSmsSpecInfo rhs=(SSmsSpecInfo)rhs0;
		if(!ObjectUtils.equals(smsId, rhs.smsId)) return false;
		if(!ObjectUtils.equals(sccpAddress, rhs.sccpAddress)) return false;
		if(!ObjectUtils.equals(gateway, rhs.gateway)) return false;
		if(!ObjectUtils.equals(cfwGateway, rhs.cfwGateway)) return false;
		if(!ObjectUtils.equals(oppGateway, rhs.oppGateway)) return false;
		if(!ObjectUtils.equals(ssr, rhs.ssr)) return false;
		if(!ObjectUtils.equals(pid, rhs.pid)) return false;
		if(!ObjectUtils.equals(dcs, rhs.dcs)) return false;
		if(!ObjectUtils.equals(retryTimes, rhs.retryTimes)) return false;
		if(!ObjectUtils.equals(priorLevel, rhs.priorLevel)) return false;
		if(!ObjectUtils.equals(ismgId, rhs.ismgId)) return false;
		if(!ObjectUtils.equals(mocBillType, rhs.mocBillType)) return false;
		if(!ObjectUtils.equals(mtcBillType, rhs.mtcBillType)) return false;
		if(!ObjectUtils.equals(udHi, rhs.udHi)) return false;
		if(!ObjectUtils.equals(udHid, rhs.udHid)) return false;
		if(!ObjectUtils.equals(udPnum, rhs.udPnum)) return false;
		if(!ObjectUtils.equals(udCurPno, rhs.udCurPno)) return false;
		if(!ObjectUtils.equals(smsType, rhs.smsType)) return false;
		if(!ObjectUtils.equals(oppUserType, rhs.oppUserType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(smsId)
		.append(sccpAddress)
		.append(gateway)
		.append(cfwGateway)
		.append(oppGateway)
		.append(ssr)
		.append(pid)
		.append(dcs)
		.append(retryTimes)
		.append(priorLevel)
		.append(ismgId)
		.append(mocBillType)
		.append(mtcBillType)
		.append(udHi)
		.append(udHid)
		.append(udPnum)
		.append(udCurPno)
		.append(smsType)
		.append(oppUserType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(19);
public static final long BITS_ALL_MARKER = 0x40000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SSmsSpecInfo";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "SMS_ID", 0, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "SCCP_ADDRESS", 1, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "GATEWAY", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "CFW_GATEWAY", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "OPP_GATEWAY", 4, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "SSR", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "PID", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "DCS", 7, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "RETRY_TIMES", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "PRIOR_LEVEL", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "ISMG_ID", 10, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "MOC_BILL_TYPE", 11, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "MTC_BILL_TYPE", 12, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "UD_HI", 13, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "UD_HID", 14, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "UD_PNUM", 15, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "UD_CUR_PNO", 16, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "SMS_TYPE", 17, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SSmsSpecInfo.class, "OPP_USER_TYPE", 18, int.class));
}

}