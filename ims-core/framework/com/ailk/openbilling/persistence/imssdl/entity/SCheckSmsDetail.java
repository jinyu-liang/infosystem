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
@XmlType(propOrder={"blockId","smsCode","billId","message","priorityLevel","sendDate","srcAddr","requestReport","doneCode"})
@Sdl(module="MImsSyncDef")
public class SCheckSmsDetail extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BLOCK_ID = "BLOCK_ID";
	public final static String COL_SMS_CODE = "SMS_CODE";
	public final static String COL_BILL_ID = "BILL_ID";
	public final static String COL_MESSAGE = "MESSAGE";
	public final static String COL_PRIORITY_LEVEL = "PRIORITY_LEVEL";
	public final static String COL_SEND_DATE = "SEND_DATE";
	public final static String COL_SRC_ADDR = "SRC_ADDR";
	public final static String COL_REQUEST_REPORT = "REQUEST_REPORT";
	public final static String COL_DONE_CODE = "DONE_CODE";
	public final static int IDX_BLOCK_ID = 0;
	public final static int IDX_SMS_CODE = 1;
	public final static int IDX_BILL_ID = 2;
	public final static int IDX_MESSAGE = 3;
	public final static int IDX_PRIORITY_LEVEL = 4;
	public final static int IDX_SEND_DATE = 5;
	public final static int IDX_SRC_ADDR = 6;
	public final static int IDX_REQUEST_REPORT = 7;
	public final static int IDX_DONE_CODE = 8;

	/**
	 * 
	 */
	@XmlElement(name="blockId")
	@Sdl
	private long blockId;

	/**
	 * 
	 */
	@XmlElement(name="smsCode")
	@Sdl
	private long smsCode;

	/**
	 * 
	 */
	@XmlElement(name="billId")
	@Sdl
	private String billId;

	/**
	 * 
	 */
	@XmlElement(name="message")
	@Sdl
	private String message;

	/**
	 * 
	 */
	@XmlElement(name="priorityLevel")
	@Sdl
	private int priorityLevel;

	/**
	 * 
	 */
	@XmlElement(name="sendDate")
	@Sdl
	private Date sendDate;

	/**
	 * 
	 */
	@XmlElement(name="srcAddr")
	@Sdl
	private String srcAddr;

	/**
	 * 
	 */
	@XmlElement(name="requestReport")
	@Sdl
	private short requestReport;

	/**
	 * 
	 */
	@XmlElement(name="doneCode")
	@Sdl
	private long doneCode;

	public void setBlockId(long obj){
		this.blockId = obj;
		onFieldSet(0, obj);
	}

	public long getBlockId(){
		return blockId;
	}

	public void setSmsCode(long obj){
		this.smsCode = obj;
		onFieldSet(1, obj);
	}

	public long getSmsCode(){
		return smsCode;
	}

	public void setBillId(String obj){
		this.billId = obj;
		onFieldSet(2, obj);
	}

	public String getBillId(){
		return billId;
	}

	public void setMessage(String obj){
		this.message = obj;
		onFieldSet(3, obj);
	}

	public String getMessage(){
		return message;
	}

	public void setPriorityLevel(int obj){
		this.priorityLevel = obj;
		onFieldSet(4, obj);
	}

	public int getPriorityLevel(){
		return priorityLevel;
	}

	public void setSendDate(Date obj){
		this.sendDate = obj;
		onFieldSet(5, obj);
	}

	public Date getSendDate(){
		return sendDate;
	}

	public void setSrcAddr(String obj){
		this.srcAddr = obj;
		onFieldSet(6, obj);
	}

	public String getSrcAddr(){
		return srcAddr;
	}

	public void setRequestReport(short obj){
		this.requestReport = obj;
		onFieldSet(7, obj);
	}

	public short getRequestReport(){
		return requestReport;
	}

	public void setDoneCode(long obj){
		this.doneCode = obj;
		onFieldSet(8, obj);
	}

	public long getDoneCode(){
		return doneCode;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCheckSmsDetail(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 9; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCheckSmsDetail(SCheckSmsDetail arg0){
		copy(arg0);
	}

	public void copy(final SCheckSmsDetail rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		blockId = rhs.blockId;
		smsCode = rhs.smsCode;
		billId = rhs.billId;
		message = rhs.message;
		priorityLevel = rhs.priorityLevel;
		sendDate = rhs.sendDate;
		srcAddr = rhs.srcAddr;
		requestReport = rhs.requestReport;
		doneCode = rhs.doneCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCheckSmsDetail rhs=(SCheckSmsDetail)rhs0;
		if(!ObjectUtils.equals(blockId, rhs.blockId)) return false;
		if(!ObjectUtils.equals(smsCode, rhs.smsCode)) return false;
		if(!ObjectUtils.equals(billId, rhs.billId)) return false;
		if(!ObjectUtils.equals(message, rhs.message)) return false;
		if(!ObjectUtils.equals(priorityLevel, rhs.priorityLevel)) return false;
		if(!ObjectUtils.equals(sendDate, rhs.sendDate)) return false;
		if(!ObjectUtils.equals(srcAddr, rhs.srcAddr)) return false;
		if(!ObjectUtils.equals(requestReport, rhs.requestReport)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(blockId)
		.append(smsCode)
		.append(billId)
		.append(message)
		.append(priorityLevel)
		.append(sendDate)
		.append(srcAddr)
		.append(requestReport)
		.append(doneCode)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(9);
public static final long BITS_ALL_MARKER = 0x100L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SCheckSmsDetail";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "BLOCK_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "SMS_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "BILL_ID", 2, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "MESSAGE", 3, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "PRIORITY_LEVEL", 4, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "SEND_DATE", 5, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "SRC_ADDR", 6, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "REQUEST_REPORT", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSmsDetail.class, "DONE_CODE", 8, long.class));
}

}