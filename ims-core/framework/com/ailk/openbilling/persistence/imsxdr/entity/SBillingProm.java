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
@XmlType(propOrder={"prodId","priceId","adjustItem","cycleBeginDate","cycleEndDate","baseItem","baseFee","refProductId","refItemCode","productFee","discountFee","promFlag","includeTaxFlag","payType","measureId","deductSts"})
@Sdl(module="MXdr")
public class SBillingProm extends CsdlStructObject implements IComplexEntity{

	public final static String COL_PROD_ID = "PROD_ID";
	public final static String COL_PRICE_ID = "PRICE_ID";
	public final static String COL_ADJUST_ITEM = "ADJUST_ITEM";
	public final static String COL_CYCLE_BEGIN_DATE = "CYCLE_BEGIN_DATE";
	public final static String COL_CYCLE_END_DATE = "CYCLE_END_DATE";
	public final static String COL_BASE_ITEM = "BASE_ITEM";
	public final static String COL_BASE_FEE = "BASE_FEE";
	public final static String COL_REF_PRODUCT_ID = "REF_PRODUCT_ID";
	public final static String COL_REF_ITEM_CODE = "REF_ITEM_CODE";
	public final static String COL_PRODUCT_FEE = "PRODUCT_FEE";
	public final static String COL_DISCOUNT_FEE = "DISCOUNT_FEE";
	public final static String COL_PROM_FLAG = "PROM_FLAG";
	public final static String COL_INCLUDE_TAX_FLAG = "INCLUDE_TAX_FLAG";
	public final static String COL_PAY_TYPE = "PAY_TYPE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_DEDUCT_STS = "DEDUCT_STS";
	public final static int IDX_PROD_ID = 0;
	public final static int IDX_PRICE_ID = 1;
	public final static int IDX_ADJUST_ITEM = 2;
	public final static int IDX_CYCLE_BEGIN_DATE = 3;
	public final static int IDX_CYCLE_END_DATE = 4;
	public final static int IDX_BASE_ITEM = 5;
	public final static int IDX_BASE_FEE = 6;
	public final static int IDX_REF_PRODUCT_ID = 7;
	public final static int IDX_REF_ITEM_CODE = 8;
	public final static int IDX_PRODUCT_FEE = 9;
	public final static int IDX_DISCOUNT_FEE = 10;
	public final static int IDX_PROM_FLAG = 11;
	public final static int IDX_INCLUDE_TAX_FLAG = 12;
	public final static int IDX_PAY_TYPE = 13;
	public final static int IDX_MEASURE_ID = 14;
	public final static int IDX_DEDUCT_STS = 15;

	/**
	 * 
	 */
	@XmlElement(name="prodId")
	@Sdl
	private long prodId;

	/**
	 * 
	 */
	@XmlElement(name="priceId")
	@Sdl
	private int priceId;

	/**
	 * 
	 */
	@XmlElement(name="adjustItem")
	@Sdl
	private int adjustItem;

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
	@XmlElement(name="baseItem")
	@Sdl
	private int baseItem;

	/**
	 * 
	 */
	@XmlElement(name="baseFee")
	@Sdl
	private long baseFee;

	/**
	 * 
	 */
	@XmlElement(name="refProductId")
	@Sdl
	private long refProductId;

	/**
	 * 
	 */
	@XmlElement(name="refItemCode")
	@Sdl
	private int refItemCode;

	/**
	 * 
	 */
	@XmlElement(name="productFee")
	@Sdl
	private long productFee;

	/**
	 * 
	 */
	@XmlElement(name="discountFee")
	@Sdl
	private long discountFee;

	/**
	 * 
	 */
	@XmlElement(name="promFlag")
	@Sdl
	private short promFlag;

	/**
	 * 
	 */
	@XmlElement(name="includeTaxFlag")
	@Sdl
	private short includeTaxFlag;

	/**
	 * 
	 */
	@XmlElement(name="payType")
	@Sdl
	private short payType;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="deductSts")
	@Sdl
	private short deductSts;

	public void setProdId(long obj){
		this.prodId = obj;
		onFieldSet(0, obj);
	}

	public long getProdId(){
		return prodId;
	}

	public void setPriceId(int obj){
		this.priceId = obj;
		onFieldSet(1, obj);
	}

	public int getPriceId(){
		return priceId;
	}

	public void setAdjustItem(int obj){
		this.adjustItem = obj;
		onFieldSet(2, obj);
	}

	public int getAdjustItem(){
		return adjustItem;
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

	public void setBaseItem(int obj){
		this.baseItem = obj;
		onFieldSet(5, obj);
	}

	public int getBaseItem(){
		return baseItem;
	}

	public void setBaseFee(long obj){
		this.baseFee = obj;
		onFieldSet(6, obj);
	}

	public long getBaseFee(){
		return baseFee;
	}

	public void setRefProductId(long obj){
		this.refProductId = obj;
		onFieldSet(7, obj);
	}

	public long getRefProductId(){
		return refProductId;
	}

	public void setRefItemCode(int obj){
		this.refItemCode = obj;
		onFieldSet(8, obj);
	}

	public int getRefItemCode(){
		return refItemCode;
	}

	public void setProductFee(long obj){
		this.productFee = obj;
		onFieldSet(9, obj);
	}

	public long getProductFee(){
		return productFee;
	}

	public void setDiscountFee(long obj){
		this.discountFee = obj;
		onFieldSet(10, obj);
	}

	public long getDiscountFee(){
		return discountFee;
	}

	public void setPromFlag(short obj){
		this.promFlag = obj;
		onFieldSet(11, obj);
	}

	public short getPromFlag(){
		return promFlag;
	}

	public void setIncludeTaxFlag(short obj){
		this.includeTaxFlag = obj;
		onFieldSet(12, obj);
	}

	public short getIncludeTaxFlag(){
		return includeTaxFlag;
	}

	public void setPayType(short obj){
		this.payType = obj;
		onFieldSet(13, obj);
	}

	public short getPayType(){
		return payType;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(14, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setDeductSts(short obj){
		this.deductSts = obj;
		onFieldSet(15, obj);
	}

	public short getDeductSts(){
		return deductSts;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingProm(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 16; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingProm(SBillingProm arg0){
		copy(arg0);
	}

	public void copy(final SBillingProm rhs){
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
		priceId = rhs.priceId;
		adjustItem = rhs.adjustItem;
		cycleBeginDate = rhs.cycleBeginDate;
		cycleEndDate = rhs.cycleEndDate;
		baseItem = rhs.baseItem;
		baseFee = rhs.baseFee;
		refProductId = rhs.refProductId;
		refItemCode = rhs.refItemCode;
		productFee = rhs.productFee;
		discountFee = rhs.discountFee;
		promFlag = rhs.promFlag;
		includeTaxFlag = rhs.includeTaxFlag;
		payType = rhs.payType;
		measureId = rhs.measureId;
		deductSts = rhs.deductSts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingProm rhs=(SBillingProm)rhs0;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		if(!ObjectUtils.equals(adjustItem, rhs.adjustItem)) return false;
		if(!ObjectUtils.equals(cycleBeginDate, rhs.cycleBeginDate)) return false;
		if(!ObjectUtils.equals(cycleEndDate, rhs.cycleEndDate)) return false;
		if(!ObjectUtils.equals(baseItem, rhs.baseItem)) return false;
		if(!ObjectUtils.equals(baseFee, rhs.baseFee)) return false;
		if(!ObjectUtils.equals(refProductId, rhs.refProductId)) return false;
		if(!ObjectUtils.equals(refItemCode, rhs.refItemCode)) return false;
		if(!ObjectUtils.equals(productFee, rhs.productFee)) return false;
		if(!ObjectUtils.equals(discountFee, rhs.discountFee)) return false;
		if(!ObjectUtils.equals(promFlag, rhs.promFlag)) return false;
		if(!ObjectUtils.equals(includeTaxFlag, rhs.includeTaxFlag)) return false;
		if(!ObjectUtils.equals(payType, rhs.payType)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(deductSts, rhs.deductSts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prodId)
		.append(priceId)
		.append(adjustItem)
		.append(cycleBeginDate)
		.append(cycleEndDate)
		.append(baseItem)
		.append(baseFee)
		.append(refProductId)
		.append(refItemCode)
		.append(productFee)
		.append(discountFee)
		.append(promFlag)
		.append(includeTaxFlag)
		.append(payType)
		.append(measureId)
		.append(deductSts)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(16);
public static final long BITS_ALL_MARKER = 0x8000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingProm";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "PROD_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "PRICE_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "ADJUST_ITEM", 2, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "CYCLE_BEGIN_DATE", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "CYCLE_END_DATE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "BASE_ITEM", 5, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "BASE_FEE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "REF_PRODUCT_ID", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "REF_ITEM_CODE", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "PRODUCT_FEE", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "DISCOUNT_FEE", 10, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "PROM_FLAG", 11, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "INCLUDE_TAX_FLAG", 12, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "PAY_TYPE", 13, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "MEASURE_ID", 14, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingProm.class, "DEDUCT_STS", 15, short.class));
}

}