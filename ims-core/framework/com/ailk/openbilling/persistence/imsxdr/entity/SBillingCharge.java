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
@XmlType(propOrder={"notificationId","deductSts","prodId","cycleBeginDate","cycleEndDate","payType","priceId","itemCode","measureId","includeTaxFlag","billFee","originalFee","discountFee","baseFee","productFee"})
@Sdl(module="MXdr")
public class SBillingCharge extends CsdlStructObject implements IComplexEntity{

	public final static String COL_NOTIFICATION_ID = "NOTIFICATION_ID";
	public final static String COL_DEDUCT_STS = "DEDUCT_STS";
	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_CYCLE_BEGIN_DATE = "CYCLE_BEGIN_DATE";
	public final static String COL_CYCLE_END_DATE = "CYCLE_END_DATE";
	public final static String COL_PAY_TYPE = "PAY_TYPE";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_ITEM_CODE = "ITEM_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_INCLUDE_TAX_FLAG = "INCLUDE_TAX_FLAG";
	public final static String COL_BILL_FEE = "BILL_FEE";
	public final static String COL_ORIGINAL_FEE = "ORIGINAL_FEE";
	public final static String COL_DISCOUNT_FEE = "DISCOUNT_FEE";
	public final static String COL_BASE_FEE = "BASE_FEE";
	public final static String COL_PRODUCT_FEE = "PRODUCT_FEE";
	public final static int IDX_NOTIFICATION_ID = 0;
	public final static int IDX_DEDUCT_STS = 1;
	public final static int IDX_PROD_ID = 2;
	public final static int IDX_CYCLE_BEGIN_DATE = 3;
	public final static int IDX_CYCLE_END_DATE = 4;
	public final static int IDX_PAY_TYPE = 5;
	public final static int IDX_PRICE_ID = 6;
	public final static int IDX_ITEM_CODE = 7;
	public final static int IDX_MEASURE_ID = 8;
	public final static int IDX_INCLUDE_TAX_FLAG = 9;
	public final static int IDX_BILL_FEE = 10;
	public final static int IDX_ORIGINAL_FEE = 11;
	public final static int IDX_DISCOUNT_FEE = 12;
	public final static int IDX_BASE_FEE = 13;
	public final static int IDX_PRODUCT_FEE = 14;

	/**
	 * 
	 */
	@XmlElement(name="notificationId")
	@Sdl
	private int notificationId;

	/**
	 * 
	 */
	@XmlElement(name="deductSts")
	@Sdl
	private short deductSts;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="cycleBeginDate")
	@Sdl
	private long cycleBeginDate;

	/**
	 * 
	 */
	@XmlElement(name="cycleEndDate")
	@Sdl
	private long cycleEndDate;

	/**
	 * 
	 */
	@XmlElement(name="payType")
	@Sdl
	private short payType;

	/**
	 * 
	 */
	@XmlElement(name="priceId")
	@Sdl
	private int priceId;

	/**
	 * 
	 */
	@XmlElement(name="itemCode")
	@Sdl
	private int itemCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="includeTaxFlag")
	@Sdl
	private short includeTaxFlag;

	/**
	 * 
	 */
	@XmlElement(name="billFee")
	@Sdl
	private long billFee;

	/**
	 * 
	 */
	@XmlElement(name="originalFee")
	@Sdl
	private long originalFee;

	/**
	 * 
	 */
	@XmlElement(name="discountFee")
	@Sdl
	private long discountFee;

	/**
	 * 
	 */
	@XmlElement(name="baseFee")
	@Sdl
	private long baseFee;

	/**
	 * 
	 */
	@XmlElement(name="productFee")
	@Sdl
	private long productFee;

	public void setNotificationId(int obj){
		this.notificationId = obj;
		onFieldSet(0, obj);
	}

	public int getNotificationId(){
		return notificationId;
	}

	public void setDeductSts(short obj){
		this.deductSts = obj;
		onFieldSet(1, obj);
	}

	public short getDeductSts(){
		return deductSts;
	}

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(2, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setCycleBeginDate(long obj){
		this.cycleBeginDate = obj;
		onFieldSet(3, obj);
	}

	public long getCycleBeginDate(){
		return cycleBeginDate;
	}

	public void setCycleEndDate(long obj){
		this.cycleEndDate = obj;
		onFieldSet(4, obj);
	}

	public long getCycleEndDate(){
		return cycleEndDate;
	}

	public void setPayType(short obj){
		this.payType = obj;
		onFieldSet(5, obj);
	}

	public short getPayType(){
		return payType;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(6, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setItemCode(int obj){
		this.itemCode = obj;
		onFieldSet(7, obj);
	}

	public int getItemCode(){
		return itemCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(8, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setIncludeTaxFlag(short obj){
		this.includeTaxFlag = obj;
		onFieldSet(9, obj);
	}

	public short getIncludeTaxFlag(){
		return includeTaxFlag;
	}

	public void setBillFee(long obj){
		this.billFee = obj;
		onFieldSet(10, obj);
	}

	public long getBillFee(){
		return billFee;
	}

	public void setOriginalFee(long obj){
		this.originalFee = obj;
		onFieldSet(11, obj);
	}

	public long getOriginalFee(){
		return originalFee;
	}

	public void setDiscountFee(long obj){
		this.discountFee = obj;
		onFieldSet(12, obj);
	}

	public long getDiscountFee(){
		return discountFee;
	}

	public void setBaseFee(long obj){
		this.baseFee = obj;
		onFieldSet(13, obj);
	}

	public long getBaseFee(){
		return baseFee;
	}

	public void setProductFee(long obj){
		this.productFee = obj;
		onFieldSet(14, obj);
	}

	public long getProductFee(){
		return productFee;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingCharge(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 15; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingCharge(SBillingCharge arg0){
		copy(arg0);
	}

	public void copy(final SBillingCharge rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		notificationId = rhs.notificationId;
		deductSts = rhs.deductSts;
		prodId = rhs.prodId;
		cycleBeginDate = rhs.cycleBeginDate;
		cycleEndDate = rhs.cycleEndDate;
		payType = rhs.payType;
		priceId = rhs.priceId;
		itemCode = rhs.itemCode;
		measureId = rhs.measureId;
		includeTaxFlag = rhs.includeTaxFlag;
		billFee = rhs.billFee;
		originalFee = rhs.originalFee;
		discountFee = rhs.discountFee;
		baseFee = rhs.baseFee;
		productFee = rhs.productFee;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingCharge rhs=(SBillingCharge)rhs0;
		if(!ObjectUtils.equals(notificationId, rhs.notificationId)) return false;
		if(!ObjectUtils.equals(deductSts, rhs.deductSts)) return false;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(cycleBeginDate, rhs.cycleBeginDate)) return false;
		if(!ObjectUtils.equals(cycleEndDate, rhs.cycleEndDate)) return false;
		if(!ObjectUtils.equals(payType, rhs.payType)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(includeTaxFlag, rhs.includeTaxFlag)) return false;
		if(!ObjectUtils.equals(billFee, rhs.billFee)) return false;
		if(!ObjectUtils.equals(originalFee, rhs.originalFee)) return false;
		if(!ObjectUtils.equals(discountFee, rhs.discountFee)) return false;
		if(!ObjectUtils.equals(baseFee, rhs.baseFee)) return false;
		if(!ObjectUtils.equals(productFee, rhs.productFee)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notificationId)
		.append(deductSts)
		.append(prodId)
		.append(cycleBeginDate)
		.append(cycleEndDate)
		.append(payType)
		.append(priceId)
		.append(itemCode)
		.append(measureId)
		.append(includeTaxFlag)
		.append(billFee)
		.append(originalFee)
		.append(discountFee)
		.append(baseFee)
		.append(productFee)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(15);
public static final long BITS_ALL_MARKER = 0x4000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingCharge";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "NOTIFICATION_ID", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "DEDUCT_STS", 1, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "PROD_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "CYCLE_BEGIN_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "CYCLE_END_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "PAY_TYPE", 5, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "PRICE_ID", 6, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "ITEM_CODE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "MEASURE_ID", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "INCLUDE_TAX_FLAG", 9, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "BILL_FEE", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "ORIGINAL_FEE", 11, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "DISCOUNT_FEE", 12, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "BASE_FEE", 13, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingCharge.class, "PRODUCT_FEE", 14, long.class));
}

}