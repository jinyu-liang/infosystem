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
@XmlType(propOrder={"channelId","processFlag","ownerType","billBeginDate","billEndDate","billCycleLength","billCycleType","deductResult","deductTime","preBalance","curBalance"})
@Sdl(module="MXdr")
public class SBillingCommon extends CsdlStructObject implements IComplexEntity{

	public final static String COL_CHANNEL_ID = "CHANNEL_ID";
	public final static String COL_PROCESS_FLAG = "PROCESS_FLAG";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static String COL_BILL_BEGIN_DATE = "BILL_BEGIN_DATE";
	public final static String COL_BILL_END_DATE = "BILL_END_DATE";
	public final static String COL_BILL_CYCLE_LENGTH = "BILL_CYCLE_LENGTH";
	public final static String COL_BILL_CYCLE_TYPE = "BILL_CYCLE_TYPE";
	public final static String COL_DEDUCT_RESULT = "DEDUCT_RESULT";
	public final static String COL_DEDUCT_TIME = "DEDUCT_TIME";
	public final static String COL_PRE_BALANCE = "PRE_BALANCE";
	public final static String COL_CUR_BALANCE = "CUR_BALANCE";
	public final static int IDX_CHANNEL_ID = 0;
	public final static int IDX_PROCESS_FLAG = 1;
	public final static int IDX_OWNER_TYPE = 2;
	public final static int IDX_BILL_BEGIN_DATE = 3;
	public final static int IDX_BILL_END_DATE = 4;
	public final static int IDX_BILL_CYCLE_LENGTH = 5;
	public final static int IDX_BILL_CYCLE_TYPE = 6;
	public final static int IDX_DEDUCT_RESULT = 7;
	public final static int IDX_DEDUCT_TIME = 8;
	public final static int IDX_PRE_BALANCE = 9;
	public final static int IDX_CUR_BALANCE = 10;

	/**
	 * 
	 */
	@XmlElement(name="channelId")
	@Sdl
	private short channelId;

	/**
	 * 
	 */
	@XmlElement(name="processFlag")
	@Sdl
	private short processFlag;

	/**
	 * 
	 */
	@XmlElement(name="ownerType")
	@Sdl
	private short ownerType;

	/**
	 * 
	 */
	@XmlElement(name="billBeginDate")
	@Sdl
	private long billBeginDate;

	/**
	 * 
	 */
	@XmlElement(name="billEndDate")
	@Sdl
	private long billEndDate;

	/**
	 * 
	 */
	@XmlElement(name="billCycleLength")
	@Sdl
	private int billCycleLength;

	/**
	 * 
	 */
	@XmlElement(name="billCycleType")
	@Sdl
	private short billCycleType;

	/**
	 * 
	 */
	@XmlElement(name="deductResult")
	@Sdl
	private short deductResult;

	/**
	 * 
	 */
	@XmlElement(name="deductTime")
	@Sdl
	private long deductTime;

	/**
	 * 
	 */
	@XmlElement(name="preBalance")
	@Sdl
	private long preBalance;

	/**
	 * 
	 */
	@XmlElement(name="curBalance")
	@Sdl
	private long curBalance;

	public void setChannelId(short obj){
		this.channelId = obj;
		onFieldSet(0, obj);
	}

	public short getChannelId(){
		return channelId;
	}

	public void setProcessFlag(short obj){
		this.processFlag = obj;
		onFieldSet(1, obj);
	}

	public short getProcessFlag(){
		return processFlag;
	}

	public void setOwnerType(short obj){
		this.ownerType = obj;
		onFieldSet(2, obj);
	}

	public short getOwnerType(){
		return ownerType;
	}

	public void setBillBeginDate(long obj){
		this.billBeginDate = obj;
		onFieldSet(3, obj);
	}

	public long getBillBeginDate(){
		return billBeginDate;
	}

	public void setBillEndDate(long obj){
		this.billEndDate = obj;
		onFieldSet(4, obj);
	}

	public long getBillEndDate(){
		return billEndDate;
	}

	public void setBillCycleLength(int obj){
		this.billCycleLength = obj;
		onFieldSet(5, obj);
	}

	public int getBillCycleLength(){
		return billCycleLength;
	}

	public void setBillCycleType(short obj){
		this.billCycleType = obj;
		onFieldSet(6, obj);
	}

	public short getBillCycleType(){
		return billCycleType;
	}

	public void setDeductResult(short obj){
		this.deductResult = obj;
		onFieldSet(7, obj);
	}

	public short getDeductResult(){
		return deductResult;
	}

	public void setDeductTime(long obj){
		this.deductTime = obj;
		onFieldSet(8, obj);
	}

	public long getDeductTime(){
		return deductTime;
	}

	public void setPreBalance(long obj){
		this.preBalance = obj;
		onFieldSet(9, obj);
	}

	public long getPreBalance(){
		return preBalance;
	}

	public void setCurBalance(long obj){
		this.curBalance = obj;
		onFieldSet(10, obj);
	}

	public long getCurBalance(){
		return curBalance;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingCommon(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingCommon(SBillingCommon arg0){
		copy(arg0);
	}

	public void copy(final SBillingCommon rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		channelId = rhs.channelId;
		processFlag = rhs.processFlag;
		ownerType = rhs.ownerType;
		billBeginDate = rhs.billBeginDate;
		billEndDate = rhs.billEndDate;
		billCycleLength = rhs.billCycleLength;
		billCycleType = rhs.billCycleType;
		deductResult = rhs.deductResult;
		deductTime = rhs.deductTime;
		preBalance = rhs.preBalance;
		curBalance = rhs.curBalance;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingCommon rhs=(SBillingCommon)rhs0;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(processFlag, rhs.processFlag)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		if(!ObjectUtils.equals(billBeginDate, rhs.billBeginDate)) return false;
		if(!ObjectUtils.equals(billEndDate, rhs.billEndDate)) return false;
		if(!ObjectUtils.equals(billCycleLength, rhs.billCycleLength)) return false;
		if(!ObjectUtils.equals(billCycleType, rhs.billCycleType)) return false;
		if(!ObjectUtils.equals(deductResult, rhs.deductResult)) return false;
		if(!ObjectUtils.equals(deductTime, rhs.deductTime)) return false;
		if(!ObjectUtils.equals(preBalance, rhs.preBalance)) return false;
		if(!ObjectUtils.equals(curBalance, rhs.curBalance)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(channelId)
		.append(processFlag)
		.append(ownerType)
		.append(billBeginDate)
		.append(billEndDate)
		.append(billCycleLength)
		.append(billCycleType)
		.append(deductResult)
		.append(deductTime)
		.append(preBalance)
		.append(curBalance)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingCommon";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "CHANNEL_ID", 0, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "PROCESS_FLAG", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "OWNER_TYPE", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "BILL_BEGIN_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "BILL_END_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "BILL_CYCLE_LENGTH", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "BILL_CYCLE_TYPE", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "DEDUCT_RESULT", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "DEDUCT_TIME", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "PRE_BALANCE", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCommon.class, "CUR_BALANCE", 10, long.class));
}

}