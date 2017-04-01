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
@XmlType(propOrder={"acctId","productSequenceId","deductFlag","previousBalance","currentBalance","retryTime","currentRetryCycle","currentRetryTime","prorateDays","oldCycleDate","chargeAmount","splitChargeAmount","deductTime","failReason","ruleId","cycleIndex"})
@Sdl(module="MImsSyncDef")
public class SProductStatus extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_PRODUCT_SEQUENCE_ID = "PRODUCT_SEQUENCE_ID";
	public final static String COL_DEDUCT_FLAG = "DEDUCT_FLAG";
	public final static String COL_PREVIOUS_BALANCE = "PREVIOUS_BALANCE";
	public final static String COL_CURRENT_BALANCE = "CURRENT_BALANCE";
	public final static String COL_RETRY_TIME = "RETRY_TIME";
	public final static String COL_CURRENT_RETRY_CYCLE = "CURRENT_RETRY_CYCLE";
	public final static String COL_CURRENT_RETRY_TIME = "CURRENT_RETRY_TIME";
	public final static String COL_PRORATE_DAYS = "PRORATE_DAYS";
	public final static String COL_OLD_CYCLE_DATE = "OLD_CYCLE_DATE";
	public final static String COL_CHARGE_AMOUNT = "CHARGE_AMOUNT";
	public final static String COL_SPLIT_CHARGE_AMOUNT = "SPLIT_CHARGE_AMOUNT";
	public final static String COL_DEDUCT_TIME = "DEDUCT_TIME";
	public final static String COL_FAIL_REASON = "FAIL_REASON";
	public final static String COL_RULE_ID = "RULE_ID";
	public final static String COL_CYCLE_INDEX = "CYCLE_INDEX";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_PRODUCT_SEQUENCE_ID = 1;
	public final static int IDX_DEDUCT_FLAG = 2;
	public final static int IDX_PREVIOUS_BALANCE = 3;
	public final static int IDX_CURRENT_BALANCE = 4;
	public final static int IDX_RETRY_TIME = 5;
	public final static int IDX_CURRENT_RETRY_CYCLE = 6;
	public final static int IDX_CURRENT_RETRY_TIME = 7;
	public final static int IDX_PRORATE_DAYS = 8;
	public final static int IDX_OLD_CYCLE_DATE = 9;
	public final static int IDX_CHARGE_AMOUNT = 10;
	public final static int IDX_SPLIT_CHARGE_AMOUNT = 11;
	public final static int IDX_DEDUCT_TIME = 12;
	public final static int IDX_FAIL_REASON = 13;
	public final static int IDX_RULE_ID = 14;
	public final static int IDX_CYCLE_INDEX = 15;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="productSequenceId")
	@Sdl
	private long productSequenceId;

	/**
	 * 
	 */
	@XmlElement(name="deductFlag")
	@Sdl
	private short deductFlag;

	/**
	 * 
	 */
	@XmlElement(name="previousBalance")
	@Sdl
	private long previousBalance;

	/**
	 * 
	 */
	@XmlElement(name="currentBalance")
	@Sdl
	private long currentBalance;

	/**
	 * 
	 */
	@XmlElement(name="retryTime")
	@Sdl
	private short retryTime;

	/**
	 * 
	 */
	@XmlElement(name="currentRetryCycle")
	@Sdl
	private short currentRetryCycle;

	/**
	 * 
	 */
	@XmlElement(name="currentRetryTime")
	@Sdl
	private short currentRetryTime;

	/**
	 * 
	 */
	@XmlElement(name="prorateDays")
	@Sdl
	private short prorateDays;

	/**
	 * 
	 */
	@XmlElement(name="oldCycleDate")
	@Sdl
	private Date oldCycleDate;

	/**
	 * 
	 */
	@XmlElement(name="chargeAmount")
	@Sdl
	private long chargeAmount;

	/**
	 * 
	 */
	@XmlElement(name="splitChargeAmount")
	@Sdl
	private long splitChargeAmount;

	/**
	 * 
	 */
	@XmlElement(name="deductTime")
	@Sdl
	private Date deductTime;

	/**
	 * 
	 */
	@XmlElement(name="failReason")
	@Sdl
	private String failReason;

	/**
	 * 
	 */
	@XmlElement(name="ruleId")
	@Sdl
	private short ruleId;

	/**
	 * 
	 */
	@XmlElement(name="cycleIndex")
	@Sdl
	private int cycleIndex;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setProductSequenceId(long obj){
		this.productSequenceId = obj;
		onFieldSet(1, obj);
	}

	public long getProductSequenceId(){
		return productSequenceId;
	}

	public void setDeductFlag(short obj){
		this.deductFlag = obj;
		onFieldSet(2, obj);
	}

	public short getDeductFlag(){
		return deductFlag;
	}

	public void setPreviousBalance(long obj){
		this.previousBalance = obj;
		onFieldSet(3, obj);
	}

	public long getPreviousBalance(){
		return previousBalance;
	}

	public void setCurrentBalance(long obj){
		this.currentBalance = obj;
		onFieldSet(4, obj);
	}

	public long getCurrentBalance(){
		return currentBalance;
	}

	public void setRetryTime(short obj){
		this.retryTime = obj;
		onFieldSet(5, obj);
	}

	public short getRetryTime(){
		return retryTime;
	}

	public void setCurrentRetryCycle(short obj){
		this.currentRetryCycle = obj;
		onFieldSet(6, obj);
	}

	public short getCurrentRetryCycle(){
		return currentRetryCycle;
	}

	public void setCurrentRetryTime(short obj){
		this.currentRetryTime = obj;
		onFieldSet(7, obj);
	}

	public short getCurrentRetryTime(){
		return currentRetryTime;
	}

	public void setProrateDays(short obj){
		this.prorateDays = obj;
		onFieldSet(8, obj);
	}

	public short getProrateDays(){
		return prorateDays;
	}

	public void setOldCycleDate(Date obj){
		this.oldCycleDate = obj;
		onFieldSet(9, obj);
	}

	public Date getOldCycleDate(){
		return oldCycleDate;
	}

	public void setChargeAmount(long obj){
		this.chargeAmount = obj;
		onFieldSet(10, obj);
	}

	public long getChargeAmount(){
		return chargeAmount;
	}

	public void setSplitChargeAmount(long obj){
		this.splitChargeAmount = obj;
		onFieldSet(11, obj);
	}

	public long getSplitChargeAmount(){
		return splitChargeAmount;
	}

	public void setDeductTime(Date obj){
		this.deductTime = obj;
		onFieldSet(12, obj);
	}

	public Date getDeductTime(){
		return deductTime;
	}

	public void setFailReason(String obj){
		this.failReason = obj;
		onFieldSet(13, obj);
	}

	public String getFailReason(){
		return failReason;
	}

	public void setRuleId(short obj){
		this.ruleId = obj;
		onFieldSet(14, obj);
	}

	public short getRuleId(){
		return ruleId;
	}

	public void setCycleIndex(int obj){
		this.cycleIndex = obj;
		onFieldSet(15, obj);
	}

	public int getCycleIndex(){
		return cycleIndex;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SProductStatus(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 16; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SProductStatus(SProductStatus arg0){
		copy(arg0);
	}

	public void copy(final SProductStatus rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		acctId = rhs.acctId;
		productSequenceId = rhs.productSequenceId;
		deductFlag = rhs.deductFlag;
		previousBalance = rhs.previousBalance;
		currentBalance = rhs.currentBalance;
		retryTime = rhs.retryTime;
		currentRetryCycle = rhs.currentRetryCycle;
		currentRetryTime = rhs.currentRetryTime;
		prorateDays = rhs.prorateDays;
		oldCycleDate = rhs.oldCycleDate;
		chargeAmount = rhs.chargeAmount;
		splitChargeAmount = rhs.splitChargeAmount;
		deductTime = rhs.deductTime;
		failReason = rhs.failReason;
		ruleId = rhs.ruleId;
		cycleIndex = rhs.cycleIndex;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductStatus rhs=(SProductStatus)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(productSequenceId, rhs.productSequenceId)) return false;
		if(!ObjectUtils.equals(deductFlag, rhs.deductFlag)) return false;
		if(!ObjectUtils.equals(previousBalance, rhs.previousBalance)) return false;
		if(!ObjectUtils.equals(currentBalance, rhs.currentBalance)) return false;
		if(!ObjectUtils.equals(retryTime, rhs.retryTime)) return false;
		if(!ObjectUtils.equals(currentRetryCycle, rhs.currentRetryCycle)) return false;
		if(!ObjectUtils.equals(currentRetryTime, rhs.currentRetryTime)) return false;
		if(!ObjectUtils.equals(prorateDays, rhs.prorateDays)) return false;
		if(!ObjectUtils.equals(oldCycleDate, rhs.oldCycleDate)) return false;
		if(!ObjectUtils.equals(chargeAmount, rhs.chargeAmount)) return false;
		if(!ObjectUtils.equals(splitChargeAmount, rhs.splitChargeAmount)) return false;
		if(!ObjectUtils.equals(deductTime, rhs.deductTime)) return false;
		if(!ObjectUtils.equals(failReason, rhs.failReason)) return false;
		if(!ObjectUtils.equals(ruleId, rhs.ruleId)) return false;
		if(!ObjectUtils.equals(cycleIndex, rhs.cycleIndex)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(productSequenceId)
		.append(deductFlag)
		.append(previousBalance)
		.append(currentBalance)
		.append(retryTime)
		.append(currentRetryCycle)
		.append(currentRetryTime)
		.append(prorateDays)
		.append(oldCycleDate)
		.append(chargeAmount)
		.append(splitChargeAmount)
		.append(deductTime)
		.append(failReason)
		.append(ruleId)
		.append(cycleIndex)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(16);
public static final long BITS_ALL_MARKER = 0x8000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imssdl.entity.SProductStatus";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "PRODUCT_SEQUENCE_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "DEDUCT_FLAG", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "PREVIOUS_BALANCE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "CURRENT_BALANCE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "RETRY_TIME", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "CURRENT_RETRY_CYCLE", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "CURRENT_RETRY_TIME", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "PRORATE_DAYS", 8, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "OLD_CYCLE_DATE", 9, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "CHARGE_AMOUNT", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "SPLIT_CHARGE_AMOUNT", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "DEDUCT_TIME", 12, Date.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "FAIL_REASON", 13, String.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "RULE_ID", 14, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SProductStatus.class, "CYCLE_INDEX", 15, int.class));
}

}