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
@XmlType(propOrder={"prodId","productOfferId","deductFlag","validPeriod","maxRetryTime","firstDeductTime","failureRuleId","deductHis","retryCycle","deductCount","feeDate","originalFee","billFee","measureId","payType"})
@Sdl(module="MXdr")
public class SBillingDeductDtl extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_PRODUCT_OFFER_ID = "PRODUCT_OFFER_ID";
	public final static String COL_DEDUCT_FLAG = "DEDUCT_FLAG";
	public final static String COL_VALID_PERIOD = "VALID_PERIOD";
	public final static String COL_MAX_RETRY_TIME = "MAX_RETRY_TIME";
	public final static String COL_FIRST_DEDUCT_TIME = "FIRST_DEDUCT_TIME";
	public final static String COL_FAILURE_RULE_ID = "FAILURE_RULE_ID";
	public final static String COL_DEDUCT_HIS = "DEDUCT_HIS";
	public final static String COL_RETRY_CYCLE = "RETRY_CYCLE";
	public final static String COL_DEDUCT_COUNT = "DEDUCT_COUNT";
	public final static String COL_FEE_DATE = "FEE_DATE";
	public final static String COL_ORIGINAL_FEE = "ORIGINAL_FEE";
	public final static String COL_BILL_FEE = "BILL_FEE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_PAY_TYPE = "PAY_TYPE";
	public final static int IDX_PROD_ID = 0;
	public final static int IDX_PRODUCT_OFFER_ID = 1;
	public final static int IDX_DEDUCT_FLAG = 2;
	public final static int IDX_VALID_PERIOD = 3;
	public final static int IDX_MAX_RETRY_TIME = 4;
	public final static int IDX_FIRST_DEDUCT_TIME = 5;
	public final static int IDX_FAILURE_RULE_ID = 6;
	public final static int IDX_DEDUCT_HIS = 7;
	public final static int IDX_RETRY_CYCLE = 8;
	public final static int IDX_DEDUCT_COUNT = 9;
	public final static int IDX_FEE_DATE = 10;
	public final static int IDX_ORIGINAL_FEE = 11;
	public final static int IDX_BILL_FEE = 12;
	public final static int IDX_MEASURE_ID = 13;
	public final static int IDX_PAY_TYPE = 14;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="productOfferId")
	@Sdl
	private int productOfferId;

	/**
	 * 
	 */
	@XmlElement(name="deductFlag")
	@Sdl
	private short deductFlag;

	/**
	 * 
	 */
	@XmlElement(name="validPeriod")
	@Sdl
	private int validPeriod;

	/**
	 * 
	 */
	@XmlElement(name="maxRetryTime")
	@Sdl
	private short maxRetryTime;

	/**
	 * 
	 */
	@XmlElement(name="firstDeductTime")
	@Sdl
	private long firstDeductTime;

	/**
	 * 
	 */
	@XmlElement(name="failureRuleId")
	@Sdl
	private short failureRuleId;

	/**
	 * 
	 */
	@XmlElement(name="deductHis")
	@Sdl
	private short deductHis;

	/**
	 * 
	 */
	@XmlElement(name="retryCycle")
	@Sdl
	private short retryCycle;

	/**
	 * 
	 */
	@XmlElement(name="deductCount")
	@Sdl
	private short deductCount;

	/**
	 * 
	 */
	@XmlElement(name="feeDate")
	@Sdl
	private long feeDate;

	/**
	 * 
	 */
	@XmlElement(name="originalFee")
	@Sdl
	private long originalFee;

	/**
	 * 
	 */
	@XmlElement(name="billFee")
	@Sdl
	private long billFee;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="payType")
	@Sdl
	private short payType;

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(0, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setProductOfferId(int obj){
		this.productOfferId = obj;
		onFieldSet(1, obj);
	}

	public int getProductOfferId(){
		return productOfferId;
	}

	public void setDeductFlag(short obj){
		this.deductFlag = obj;
		onFieldSet(2, obj);
	}

	public short getDeductFlag(){
		return deductFlag;
	}

	public void setValidPeriod(int obj){
		this.validPeriod = obj;
		onFieldSet(3, obj);
	}

	public int getValidPeriod(){
		return validPeriod;
	}

	public void setMaxRetryTime(short obj){
		this.maxRetryTime = obj;
		onFieldSet(4, obj);
	}

	public short getMaxRetryTime(){
		return maxRetryTime;
	}

	public void setFirstDeductTime(long obj){
		this.firstDeductTime = obj;
		onFieldSet(5, obj);
	}

	public long getFirstDeductTime(){
		return firstDeductTime;
	}

	public void setFailureRuleId(short obj){
		this.failureRuleId = obj;
		onFieldSet(6, obj);
	}

	public short getFailureRuleId(){
		return failureRuleId;
	}

	public void setDeductHis(short obj){
		this.deductHis = obj;
		onFieldSet(7, obj);
	}

	public short getDeductHis(){
		return deductHis;
	}

	public void setRetryCycle(short obj){
		this.retryCycle = obj;
		onFieldSet(8, obj);
	}

	public short getRetryCycle(){
		return retryCycle;
	}

	public void setDeductCount(short obj){
		this.deductCount = obj;
		onFieldSet(9, obj);
	}

	public short getDeductCount(){
		return deductCount;
	}

	public void setFeeDate(long obj){
		this.feeDate = obj;
		onFieldSet(10, obj);
	}

	public long getFeeDate(){
		return feeDate;
	}

	public void setOriginalFee(long obj){
		this.originalFee = obj;
		onFieldSet(11, obj);
	}

	public long getOriginalFee(){
		return originalFee;
	}

	public void setBillFee(long obj){
		this.billFee = obj;
		onFieldSet(12, obj);
	}

	public long getBillFee(){
		return billFee;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(13, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setPayType(short obj){
		this.payType = obj;
		onFieldSet(14, obj);
	}

	public short getPayType(){
		return payType;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingDeductDtl(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 15; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingDeductDtl(SBillingDeductDtl arg0){
		copy(arg0);
	}

	public void copy(final SBillingDeductDtl rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		prodId = rhs.prodId;
		productOfferId = rhs.productOfferId;
		deductFlag = rhs.deductFlag;
		validPeriod = rhs.validPeriod;
		maxRetryTime = rhs.maxRetryTime;
		firstDeductTime = rhs.firstDeductTime;
		failureRuleId = rhs.failureRuleId;
		deductHis = rhs.deductHis;
		retryCycle = rhs.retryCycle;
		deductCount = rhs.deductCount;
		feeDate = rhs.feeDate;
		originalFee = rhs.originalFee;
		billFee = rhs.billFee;
		measureId = rhs.measureId;
		payType = rhs.payType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingDeductDtl rhs=(SBillingDeductDtl)rhs0;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(productOfferId, rhs.productOfferId)) return false;
		if(!ObjectUtils.equals(deductFlag, rhs.deductFlag)) return false;
		if(!ObjectUtils.equals(validPeriod, rhs.validPeriod)) return false;
		if(!ObjectUtils.equals(maxRetryTime, rhs.maxRetryTime)) return false;
		if(!ObjectUtils.equals(firstDeductTime, rhs.firstDeductTime)) return false;
		if(!ObjectUtils.equals(failureRuleId, rhs.failureRuleId)) return false;
		if(!ObjectUtils.equals(deductHis, rhs.deductHis)) return false;
		if(!ObjectUtils.equals(retryCycle, rhs.retryCycle)) return false;
		if(!ObjectUtils.equals(deductCount, rhs.deductCount)) return false;
		if(!ObjectUtils.equals(feeDate, rhs.feeDate)) return false;
		if(!ObjectUtils.equals(originalFee, rhs.originalFee)) return false;
		if(!ObjectUtils.equals(billFee, rhs.billFee)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(payType, rhs.payType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodId)
		.append(productOfferId)
		.append(deductFlag)
		.append(validPeriod)
		.append(maxRetryTime)
		.append(firstDeductTime)
		.append(failureRuleId)
		.append(deductHis)
		.append(retryCycle)
		.append(deductCount)
		.append(feeDate)
		.append(originalFee)
		.append(billFee)
		.append(measureId)
		.append(payType)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(15);
public static final long BITS_ALL_MARKER = 0x4000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingDeductDtl";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "PROD_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "PRODUCT_OFFER_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "DEDUCT_FLAG", 2, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "VALID_PERIOD", 3, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "MAX_RETRY_TIME", 4, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "FIRST_DEDUCT_TIME", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "FAILURE_RULE_ID", 6, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "DEDUCT_HIS", 7, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "RETRY_CYCLE", 8, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "DEDUCT_COUNT", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "FEE_DATE", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "ORIGINAL_FEE", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "BILL_FEE", 12, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "MEASURE_ID", 13, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductDtl.class, "PAY_TYPE", 14, short.class));
}

}