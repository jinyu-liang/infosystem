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
@XmlType(propOrder={"blockId","modCode","sendDate","doneDate","sendMod","dataSource","sendNum"})
@Sdl(module="MImsSyncDef")
public class SCheckSms extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BLOCK_ID = "BLOCK_ID";
	public final static String COL_MOD_CODE = "MOD_CODE";
	public final static String COL_SEND_DATE = "SEND_DATE";
	public final static String COL_DONE_DATE = "DONE_DATE";
	public final static String COL_SEND_MOD = "SEND_MOD";
	public final static String COL_DATA_SOURCE = "DATA_SOURCE";
	public final static String COL_SEND_NUM = "SEND_NUM";
	public final static int IDX_BLOCK_ID = 0;
	public final static int IDX_MOD_CODE = 1;
	public final static int IDX_SEND_DATE = 2;
	public final static int IDX_DONE_DATE = 3;
	public final static int IDX_SEND_MOD = 4;
	public final static int IDX_DATA_SOURCE = 5;
	public final static int IDX_SEND_NUM = 6;

	/**
	 * 
	 */
	@XmlElement(name="blockId")
	@Sdl
	private long blockId;

	/**
	 * 
	 */
	@XmlElement(name="modCode")
	@Sdl
	private long modCode;

	/**
	 * 
	 */
	@XmlElement(name="sendDate")
	@Sdl
	private Date sendDate;

	/**
	 * 
	 */
	@XmlElement(name="doneDate")
	@Sdl
	private Date doneDate;

	/**
	 * 
	 */
	@XmlElement(name="sendMod")
	@Sdl
	private short sendMod;

	/**
	 * 
	 */
	@XmlElement(name="dataSource")
	@Sdl
	private String dataSource;

	/**
	 * 
	 */
	@XmlElement(name="sendNum")
	@Sdl
	private long sendNum;

	public void setBlockId(long obj){
		this.blockId = obj;
		onFieldSet(0, obj);
	}

	public long getBlockId(){
		return blockId;
	}

	public void setModCode(long obj){
		this.modCode = obj;
		onFieldSet(1, obj);
	}

	public long getModCode(){
		return modCode;
	}

	public void setSendDate(Date obj){
		this.sendDate = obj;
		onFieldSet(2, obj);
	}

	public Date getSendDate(){
		return sendDate;
	}

	public void setDoneDate(Date obj){
		this.doneDate = obj;
		onFieldSet(3, obj);
	}

	public Date getDoneDate(){
		return doneDate;
	}

	public void setSendMod(short obj){
		this.sendMod = obj;
		onFieldSet(4, obj);
	}

	public short getSendMod(){
		return sendMod;
	}

	public void setDataSource(String obj){
		this.dataSource = obj;
		onFieldSet(5, obj);
	}

	public String getDataSource(){
		return dataSource;
	}

	public void setSendNum(long obj){
		this.sendNum = obj;
		onFieldSet(6, obj);
	}

	public long getSendNum(){
		return sendNum;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SCheckSms(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 7; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SCheckSms(SCheckSms arg0){
		copy(arg0);
	}

	public void copy(final SCheckSms rhs){
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
		modCode = rhs.modCode;
		sendDate = rhs.sendDate;
		doneDate = rhs.doneDate;
		sendMod = rhs.sendMod;
		dataSource = rhs.dataSource;
		sendNum = rhs.sendNum;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCheckSms rhs=(SCheckSms)rhs0;
		if(!ObjectUtils.equals(blockId, rhs.blockId)) return false;
		if(!ObjectUtils.equals(modCode, rhs.modCode)) return false;
		if(!ObjectUtils.equals(sendDate, rhs.sendDate)) return false;
		if(!ObjectUtils.equals(doneDate, rhs.doneDate)) return false;
		if(!ObjectUtils.equals(sendMod, rhs.sendMod)) return false;
		if(!ObjectUtils.equals(dataSource, rhs.dataSource)) return false;
		if(!ObjectUtils.equals(sendNum, rhs.sendNum)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(blockId)
		.append(modCode)
		.append(sendDate)
		.append(doneDate)
		.append(sendMod)
		.append(dataSource)
		.append(sendNum)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(7);
public static final long BITS_ALL_MARKER = 0x40L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SCheckSms";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "BLOCK_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "MOD_CODE", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "SEND_DATE", 2, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "DONE_DATE", 3, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "SEND_MOD", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "DATA_SOURCE", 5, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SCheckSms.class, "SEND_NUM", 6, long.class));
}

}