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
import java.util.Map;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 * 
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"appType","appQuotaId","processTime","charge","discountFee","taxCharge","freeResDiscountFee","freeResChargeVal","freeResCreator","addupResQueryVal","addupResUsingVal","addupResQueryValPricerule","addupResUsingValPricerule","freeresQueryVal","creditQueryVal","bookQueryVal","bookChargeVal","creditChargeVal","freeresLimitQueryVal","capMaxVal","budgetVal","payforVal","validRateProdId","priceTrace","prePayBillDtl","stdUnit","tsFlag","irIddCharge","iddOperatorId","markupCharge","rateProdPrice","hybridRn","isBarservice","settleCharge","undeductFee","undeductFlag","rating2billitemmap","chargeConverted"})
@Sdl(module="MXdr")
public class SOutRatingXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_APP_TYPE = "APP_TYPE";
	public final static String COL_APP_QUOTA_ID = "APP_QUOTA_ID";
	public final static String COL_PROCESS_TIME = "PROCESS_TIME";
	public final static String COL_CHARGE = "CHARGE";
	public final static String COL_DISCOUNT_FEE = "DISCOUNT_FEE";
	public final static String COL_TAX_CHARGE = "TAX_CHARGE";
	public final static String COL_FREE_RES_DISCOUNT_FEE = "FREE_RES_DISCOUNT_FEE";
	public final static String COL_FREE_RES_CHARGE_VAL = "FREE_RES_CHARGE_VAL";
	public final static String COL_FREE_RES_CREATOR = "FREE_RES_CREATOR";
	public final static String COL_ADDUP_RES_QUERY_VAL = "ADDUP_RES_QUERY_VAL";
	public final static String COL_ADDUP_RES_USING_VAL = "ADDUP_RES_USING_VAL";
	public final static String COL_ADDUP_RES_QUERY_VAL_PRICERULE = "ADDUP_RES_QUERY_VAL_PRICERULE";
	public final static String COL_ADDUP_RES_USING_VAL_PRICERULE = "ADDUP_RES_USING_VAL_PRICERULE";
	public final static String COL_FREERES_QUERY_VAL = "FREERES_QUERY_VAL";
	public final static String COL_CREDIT_QUERY_VAL = "CREDIT_QUERY_VAL";
	public final static String COL_BOOK_QUERY_VAL = "BOOK_QUERY_VAL";
	public final static String COL_BOOK_CHARGE_VAL = "BOOK_CHARGE_VAL";
	public final static String COL_CREDIT_CHARGE_VAL = "CREDIT_CHARGE_VAL";
	public final static String COL_FREERES_LIMIT_QUERY_VAL = "FREERES_LIMIT_QUERY_VAL";
	public final static String COL_CAP_MAX_VAL = "CAP_MAX_VAL";
	public final static String COL_BUDGET_VAL = "BUDGET_VAL";
	public final static String COL_PAYFOR_VAL = "PAYFOR_VAL";
	public final static String COL_VALID_RATE_PROD_ID = "VALID_RATE_PROD_ID";
	public final static String COL_PRICE_TRACE = "PRICE_TRACE";
	public final static String COL_PRE_PAY_BILL_DTL = "PRE_PAY_BILL_DTL";
	public final static String COL_STD_UNIT = "STD_UNIT";
	public final static String COL_TS_FLAG = "TS_FLAG";
	public final static String COL_IR_IDD_CHARGE = "IR_IDD_CHARGE";
	public final static String COL_IDD_OPERATOR_ID = "IDD_OPERATOR_ID";
	public final static String COL_MARKUP_CHARGE = "MARKUP_CHARGE";
	public final static String COL_RATE_PROD_PRICE = "RATE_PROD_PRICE";
	public final static String COL_HYBRID_RN = "HYBRID_RN";
	public final static String COL_IS_BARSERVICE = "IS_BARSERVICE";
	public final static String COL_SETTLE_CHARGE = "SETTLE_CHARGE";
	public final static String COL_UNDEDUCT_FEE = "UNDEDUCT_FEE";
	public final static String COL_UNDEDUCT_FLAG = "UNDEDUCT_FLAG";
	public final static String COL_RATING2BILLITEMMAP = "RATING2BILLITEMMAP";
	public final static String COL_CHARGE_CONVERTED = "CHARGE_CONVERTED";
	public final static int IDX_APP_TYPE = 0;
	public final static int IDX_APP_QUOTA_ID = 1;
	public final static int IDX_PROCESS_TIME = 2;
	public final static int IDX_CHARGE = 3;
	public final static int IDX_DISCOUNT_FEE = 4;
	public final static int IDX_TAX_CHARGE = 5;
	public final static int IDX_FREE_RES_DISCOUNT_FEE = 6;
	public final static int IDX_FREE_RES_CHARGE_VAL = 7;
	public final static int IDX_FREE_RES_CREATOR = 8;
	public final static int IDX_ADDUP_RES_QUERY_VAL = 9;
	public final static int IDX_ADDUP_RES_USING_VAL = 10;
	public final static int IDX_ADDUP_RES_QUERY_VAL_PRICERULE = 11;
	public final static int IDX_ADDUP_RES_USING_VAL_PRICERULE = 12;
	public final static int IDX_FREERES_QUERY_VAL = 13;
	public final static int IDX_CREDIT_QUERY_VAL = 14;
	public final static int IDX_BOOK_QUERY_VAL = 15;
	public final static int IDX_BOOK_CHARGE_VAL = 16;
	public final static int IDX_CREDIT_CHARGE_VAL = 17;
	public final static int IDX_FREERES_LIMIT_QUERY_VAL = 18;
	public final static int IDX_CAP_MAX_VAL = 19;
	public final static int IDX_BUDGET_VAL = 20;
	public final static int IDX_PAYFOR_VAL = 21;
	public final static int IDX_VALID_RATE_PROD_ID = 22;
	public final static int IDX_PRICE_TRACE = 23;
	public final static int IDX_PRE_PAY_BILL_DTL = 24;
	public final static int IDX_STD_UNIT = 25;
	public final static int IDX_TS_FLAG = 26;
	public final static int IDX_IR_IDD_CHARGE = 27;
	public final static int IDX_IDD_OPERATOR_ID = 28;
	public final static int IDX_MARKUP_CHARGE = 29;
	public final static int IDX_RATE_PROD_PRICE = 30;
	public final static int IDX_HYBRID_RN = 31;
	public final static int IDX_IS_BARSERVICE = 32;
	public final static int IDX_SETTLE_CHARGE = 33;
	public final static int IDX_UNDEDUCT_FEE = 34;
	public final static int IDX_UNDEDUCT_FLAG = 35;
	public final static int IDX_RATING2BILLITEMMAP = 36;
	public final static int IDX_CHARGE_CONVERTED = 37;

	/**
	 * 
	 */
	@XmlElement(name="charge")
	@Sdl
	private List<SCharge> charge;

	/**
	 * 
	 */
	@XmlElement(name="discountFee")
	@Sdl
	private List<SDiscountFee> discountFee;

	/**
	 * 
	 */
	@XmlElement(name="taxCharge")
	@Sdl
	private List<STaxCharge> taxCharge;

	/**
	 * 
	 */
	@XmlElement(name="freeResDiscountFee")
	@Sdl
	private List<SFreeResDiscountFee> freeResDiscountFee;

	/**
	 * 
	 */
	@XmlElement(name="freeResChargeVal")
	@Sdl
	private List<SFreeResChargeVal> freeResChargeVal;

	/**
	 * 
	 */
	@XmlElement(name="freeResCreator")
	@Sdl
	private List<SFreeResCreator> freeResCreator;

	/**
	 * 
	 */
	@XmlElement(name="addupResQueryVal")
	@Sdl
	private List<SAddupResQueryVal> addupResQueryVal;

	/**
	 * 
	 */
	@XmlElement(name="addupResUsingVal")
	@Sdl
	private List<SAddupResUsingVal> addupResUsingVal;

	/**
	 * 
	 */
	@XmlElement(name="addupResQueryValPricerule")
	@Sdl
	private List<SAddupResQueryVal> addupResQueryValPricerule;

	/**
	 * 
	 */
	@XmlElement(name="addupResUsingValPricerule")
	@Sdl
	private List<SAddupResUsingVal> addupResUsingValPricerule;

	/**
	 * 
	 */
	@XmlElement(name="freeresQueryVal")
	@Sdl
	private List<SFreeResQueryVal> freeresQueryVal;

	/**
	 * 
	 */
	@XmlElement(name="creditQueryVal")
	@Sdl
	private List<SAssetQueryVal> creditQueryVal;

	/**
	 * 
	 */
	@XmlElement(name="bookQueryVal")
	@Sdl
	private List<SAssetQueryVal> bookQueryVal;

	/**
	 * 
	 */
	@XmlElement(name="bookChargeVal")
	@Sdl
	private List<SAssetChargeVal> bookChargeVal;

	/**
	 * 
	 */
	@XmlElement(name="creditChargeVal")
	@Sdl
	private List<SAssetChargeVal> creditChargeVal;

	/**
	 * 
	 */
	@XmlElement(name="freeresLimitQueryVal")
	@Sdl
	private List<SFreeResLimitQueryVal> freeresLimitQueryVal;

	/**
	 * 
	 */
	@XmlElement(name="capMaxVal")
	@Sdl
	private List<SCapMaxVal> capMaxVal;

	/**
	 * 
	 */
	@XmlElement(name="budgetVal")
	@Sdl
	private List<SCapMaxVal> budgetVal;

	/**
	 * 
	 */
	@XmlElement(name="payforVal")
	@Sdl
	private List<SPayforVal> payforVal;

	/**
	 * 
	 */
	@XmlElement(name="validRateProdId")
	@Sdl
	private List<SValidRateProdId> validRateProdId;

	/**
	 * 
	 */
	@XmlElement(name="priceTrace")
	@Sdl
	private List<SPriceTrace> priceTrace;

	/**
	 * 
	 */
	@XmlElement(name="prePayBillDtl")
	@Sdl
	private List<SPrePayBillDtl> prePayBillDtl;

	/**
	 * 
	 */
	@XmlElement(name="stdUnit")
	@Sdl
	private List<SStdRatingUnit> stdUnit;

	/**
	 * 
	 */
	@XmlElement(name="irIddCharge")
	@Sdl
	private List<SCharge> irIddCharge;

	/**
	 * 
	 */
	@XmlElement(name="markupCharge")
	@Sdl
	private List<SMarkupCharge> markupCharge;

	/**
	 * 
	 */
	@XmlElement(name="rateProdPrice")
	@Sdl
	private List<SRateProdPrice> rateProdPrice;

	/**
	 * 
	 */
	@XmlElement(name="hybridRn")
	@Sdl
	private SPayOffMode hybridRn;

	/**
	 * 
	 */
	@XmlElement(name="settleCharge")
	@Sdl
	private List<SSettleCharge> settleCharge;

	/**
	 * 
	 */
	@XmlElement(name="undeductFee")
	@Sdl
	private List<SUndeductFee> undeductFee;

	/**
	 * 
	 */
	@XmlElement(name="rating2billitemmap")
	@Sdl
	private Map<Integer,SRating2BillItem> rating2billitemmap;

	/**
	 * 
	 */
	@XmlElement(name="chargeConverted")
	@Sdl
	private List<SChargeConverted> chargeConverted;

	/**
	 * 
	 */
	@XmlElement(name="appType")
	@Sdl
	private int appType;

	/**
	 * 
	 */
	@XmlElement(name="appQuotaId")
	@Sdl
	private int appQuotaId;

	/**
	 * 
	 */
	@XmlElement(name="processTime")
	@Sdl
	private long processTime;

	/**
	 * 
	 */
	@XmlElement(name="tsFlag")
	@Sdl
	private int tsFlag;

	/**
	 * 
	 */
	@XmlElement(name="iddOperatorId")
	@Sdl
	private short iddOperatorId;

	/**
	 * 
	 */
	@XmlElement(name="isBarservice")
	@Sdl
	private int isBarservice;

	/**
	 * 
	 */
	@XmlElement(name="undeductFlag")
	@Sdl
	private short undeductFlag;

	public void setCharge(List<SCharge> obj){
		this.charge = obj;
		onFieldSet(3, obj);
	}

	public List<SCharge> getCharge(){
		return charge;
	}

	public void setDiscountFee(List<SDiscountFee> obj){
		this.discountFee = obj;
		onFieldSet(4, obj);
	}

	public List<SDiscountFee> getDiscountFee(){
		return discountFee;
	}

	public void setTaxCharge(List<STaxCharge> obj){
		this.taxCharge = obj;
		onFieldSet(5, obj);
	}

	public List<STaxCharge> getTaxCharge(){
		return taxCharge;
	}

	public void setFreeResDiscountFee(List<SFreeResDiscountFee> obj){
		this.freeResDiscountFee = obj;
		onFieldSet(6, obj);
	}

	public List<SFreeResDiscountFee> getFreeResDiscountFee(){
		return freeResDiscountFee;
	}

	public void setFreeResChargeVal(List<SFreeResChargeVal> obj){
		this.freeResChargeVal = obj;
		onFieldSet(7, obj);
	}

	public List<SFreeResChargeVal> getFreeResChargeVal(){
		return freeResChargeVal;
	}

	public void setFreeResCreator(List<SFreeResCreator> obj){
		this.freeResCreator = obj;
		onFieldSet(8, obj);
	}

	public List<SFreeResCreator> getFreeResCreator(){
		return freeResCreator;
	}

	public void setAddupResQueryVal(List<SAddupResQueryVal> obj){
		this.addupResQueryVal = obj;
		onFieldSet(9, obj);
	}

	public List<SAddupResQueryVal> getAddupResQueryVal(){
		return addupResQueryVal;
	}

	public void setAddupResUsingVal(List<SAddupResUsingVal> obj){
		this.addupResUsingVal = obj;
		onFieldSet(10, obj);
	}

	public List<SAddupResUsingVal> getAddupResUsingVal(){
		return addupResUsingVal;
	}

	public void setAddupResQueryValPricerule(List<SAddupResQueryVal> obj){
		this.addupResQueryValPricerule = obj;
		onFieldSet(11, obj);
	}

	public List<SAddupResQueryVal> getAddupResQueryValPricerule(){
		return addupResQueryValPricerule;
	}

	public void setAddupResUsingValPricerule(List<SAddupResUsingVal> obj){
		this.addupResUsingValPricerule = obj;
		onFieldSet(12, obj);
	}

	public List<SAddupResUsingVal> getAddupResUsingValPricerule(){
		return addupResUsingValPricerule;
	}

	public void setFreeresQueryVal(List<SFreeResQueryVal> obj){
		this.freeresQueryVal = obj;
		onFieldSet(13, obj);
	}

	public List<SFreeResQueryVal> getFreeresQueryVal(){
		return freeresQueryVal;
	}

	public void setCreditQueryVal(List<SAssetQueryVal> obj){
		this.creditQueryVal = obj;
		onFieldSet(14, obj);
	}

	public List<SAssetQueryVal> getCreditQueryVal(){
		return creditQueryVal;
	}

	public void setBookQueryVal(List<SAssetQueryVal> obj){
		this.bookQueryVal = obj;
		onFieldSet(15, obj);
	}

	public List<SAssetQueryVal> getBookQueryVal(){
		return bookQueryVal;
	}

	public void setBookChargeVal(List<SAssetChargeVal> obj){
		this.bookChargeVal = obj;
		onFieldSet(16, obj);
	}

	public List<SAssetChargeVal> getBookChargeVal(){
		return bookChargeVal;
	}

	public void setCreditChargeVal(List<SAssetChargeVal> obj){
		this.creditChargeVal = obj;
		onFieldSet(17, obj);
	}

	public List<SAssetChargeVal> getCreditChargeVal(){
		return creditChargeVal;
	}

	public void setFreeresLimitQueryVal(List<SFreeResLimitQueryVal> obj){
		this.freeresLimitQueryVal = obj;
		onFieldSet(18, obj);
	}

	public List<SFreeResLimitQueryVal> getFreeresLimitQueryVal(){
		return freeresLimitQueryVal;
	}

	public void setCapMaxVal(List<SCapMaxVal> obj){
		this.capMaxVal = obj;
		onFieldSet(19, obj);
	}

	public List<SCapMaxVal> getCapMaxVal(){
		return capMaxVal;
	}

	public void setBudgetVal(List<SCapMaxVal> obj){
		this.budgetVal = obj;
		onFieldSet(20, obj);
	}

	public List<SCapMaxVal> getBudgetVal(){
		return budgetVal;
	}

	public void setPayforVal(List<SPayforVal> obj){
		this.payforVal = obj;
		onFieldSet(21, obj);
	}

	public List<SPayforVal> getPayforVal(){
		return payforVal;
	}

	public void setValidRateProdId(List<SValidRateProdId> obj){
		this.validRateProdId = obj;
		onFieldSet(22, obj);
	}

	public List<SValidRateProdId> getValidRateProdId(){
		return validRateProdId;
	}

	public void setPriceTrace(List<SPriceTrace> obj){
		this.priceTrace = obj;
		onFieldSet(23, obj);
	}

	public List<SPriceTrace> getPriceTrace(){
		return priceTrace;
	}

	public void setPrePayBillDtl(List<SPrePayBillDtl> obj){
		this.prePayBillDtl = obj;
		onFieldSet(24, obj);
	}

	public List<SPrePayBillDtl> getPrePayBillDtl(){
		return prePayBillDtl;
	}

	public void setStdUnit(List<SStdRatingUnit> obj){
		this.stdUnit = obj;
		onFieldSet(25, obj);
	}

	public List<SStdRatingUnit> getStdUnit(){
		return stdUnit;
	}

	public void setIrIddCharge(List<SCharge> obj){
		this.irIddCharge = obj;
		onFieldSet(27, obj);
	}

	public List<SCharge> getIrIddCharge(){
		return irIddCharge;
	}

	public void setMarkupCharge(List<SMarkupCharge> obj){
		this.markupCharge = obj;
		onFieldSet(29, obj);
	}

	public List<SMarkupCharge> getMarkupCharge(){
		return markupCharge;
	}

	public void setRateProdPrice(List<SRateProdPrice> obj){
		this.rateProdPrice = obj;
		onFieldSet(30, obj);
	}

	public List<SRateProdPrice> getRateProdPrice(){
		return rateProdPrice;
	}

	public void setHybridRn(SPayOffMode obj){
		this.hybridRn = obj;
		onFieldSet(31, obj);
	}

	public SPayOffMode getHybridRn(){
		return hybridRn;
	}

	public void setSettleCharge(List<SSettleCharge> obj){
		this.settleCharge = obj;
		onFieldSet(33, obj);
	}

	public List<SSettleCharge> getSettleCharge(){
		return settleCharge;
	}

	public void setUndeductFee(List<SUndeductFee> obj){
		this.undeductFee = obj;
		onFieldSet(34, obj);
	}

	public List<SUndeductFee> getUndeductFee(){
		return undeductFee;
	}

	public void setRating2billitemmap(Map<Integer,SRating2BillItem> obj){
		this.rating2billitemmap = obj;
		onFieldSet(36, obj);
	}

	public Map<Integer,SRating2BillItem> getRating2billitemmap(){
		return rating2billitemmap;
	}

	public void setChargeConverted(List<SChargeConverted> obj){
		this.chargeConverted = obj;
		onFieldSet(37, obj);
	}

	public List<SChargeConverted> getChargeConverted(){
		return chargeConverted;
	}

	public void setAppType(int obj){
		this.appType = obj;
		onFieldSet(0, obj);
	}

	public int getAppType(){
		return appType;
	}

	public void setAppQuotaId(int obj){
		this.appQuotaId = obj;
		onFieldSet(1, obj);
	}

	public int getAppQuotaId(){
		return appQuotaId;
	}

	public void setProcessTime(long obj){
		this.processTime = obj;
		onFieldSet(2, obj);
	}

	public long getProcessTime(){
		return processTime;
	}

	public void setTsFlag(int obj){
		this.tsFlag = obj;
		onFieldSet(26, obj);
	}

	public int getTsFlag(){
		return tsFlag;
	}

	public void setIddOperatorId(short obj){
		this.iddOperatorId = obj;
		onFieldSet(28, obj);
	}

	public short getIddOperatorId(){
		return iddOperatorId;
	}

	public void setIsBarservice(int obj){
		this.isBarservice = obj;
		onFieldSet(32, obj);
	}

	public int getIsBarservice(){
		return isBarservice;
	}

	public void setUndeductFlag(short obj){
		this.undeductFlag = obj;
		onFieldSet(35, obj);
	}

	public short getUndeductFlag(){
		return undeductFlag;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOutRatingXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 38; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOutRatingXdr(SOutRatingXdr arg0){
		copy(arg0);
	}

	public void copy(final SOutRatingXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		appType = rhs.appType;
		appQuotaId = rhs.appQuotaId;
		processTime = rhs.processTime;
		charge = rhs.charge;
		discountFee = rhs.discountFee;
		taxCharge = rhs.taxCharge;
		freeResDiscountFee = rhs.freeResDiscountFee;
		freeResChargeVal = rhs.freeResChargeVal;
		freeResCreator = rhs.freeResCreator;
		addupResQueryVal = rhs.addupResQueryVal;
		addupResUsingVal = rhs.addupResUsingVal;
		addupResQueryValPricerule = rhs.addupResQueryValPricerule;
		addupResUsingValPricerule = rhs.addupResUsingValPricerule;
		freeresQueryVal = rhs.freeresQueryVal;
		creditQueryVal = rhs.creditQueryVal;
		bookQueryVal = rhs.bookQueryVal;
		bookChargeVal = rhs.bookChargeVal;
		creditChargeVal = rhs.creditChargeVal;
		freeresLimitQueryVal = rhs.freeresLimitQueryVal;
		capMaxVal = rhs.capMaxVal;
		budgetVal = rhs.budgetVal;
		payforVal = rhs.payforVal;
		validRateProdId = rhs.validRateProdId;
		priceTrace = rhs.priceTrace;
		prePayBillDtl = rhs.prePayBillDtl;
		stdUnit = rhs.stdUnit;
		tsFlag = rhs.tsFlag;
		irIddCharge = rhs.irIddCharge;
		iddOperatorId = rhs.iddOperatorId;
		markupCharge = rhs.markupCharge;
		rateProdPrice = rhs.rateProdPrice;
		hybridRn = rhs.hybridRn;
		isBarservice = rhs.isBarservice;
		settleCharge = rhs.settleCharge;
		undeductFee = rhs.undeductFee;
		undeductFlag = rhs.undeductFlag;
		rating2billitemmap = rhs.rating2billitemmap;
		chargeConverted = rhs.chargeConverted;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOutRatingXdr rhs=(SOutRatingXdr)rhs0;
		if(!ObjectUtils.equals(appType, rhs.appType)) return false;
		if(!ObjectUtils.equals(appQuotaId, rhs.appQuotaId)) return false;
		if(!ObjectUtils.equals(processTime, rhs.processTime)) return false;
		if(!ObjectUtils.equals(charge, rhs.charge)) return false;
		if(!ObjectUtils.equals(discountFee, rhs.discountFee)) return false;
		if(!ObjectUtils.equals(taxCharge, rhs.taxCharge)) return false;
		if(!ObjectUtils.equals(freeResDiscountFee, rhs.freeResDiscountFee)) return false;
		if(!ObjectUtils.equals(freeResChargeVal, rhs.freeResChargeVal)) return false;
		if(!ObjectUtils.equals(freeResCreator, rhs.freeResCreator)) return false;
		if(!ObjectUtils.equals(addupResQueryVal, rhs.addupResQueryVal)) return false;
		if(!ObjectUtils.equals(addupResUsingVal, rhs.addupResUsingVal)) return false;
		if(!ObjectUtils.equals(addupResQueryValPricerule, rhs.addupResQueryValPricerule)) return false;
		if(!ObjectUtils.equals(addupResUsingValPricerule, rhs.addupResUsingValPricerule)) return false;
		if(!ObjectUtils.equals(freeresQueryVal, rhs.freeresQueryVal)) return false;
		if(!ObjectUtils.equals(creditQueryVal, rhs.creditQueryVal)) return false;
		if(!ObjectUtils.equals(bookQueryVal, rhs.bookQueryVal)) return false;
		if(!ObjectUtils.equals(bookChargeVal, rhs.bookChargeVal)) return false;
		if(!ObjectUtils.equals(creditChargeVal, rhs.creditChargeVal)) return false;
		if(!ObjectUtils.equals(freeresLimitQueryVal, rhs.freeresLimitQueryVal)) return false;
		if(!ObjectUtils.equals(capMaxVal, rhs.capMaxVal)) return false;
		if(!ObjectUtils.equals(budgetVal, rhs.budgetVal)) return false;
		if(!ObjectUtils.equals(payforVal, rhs.payforVal)) return false;
		if(!ObjectUtils.equals(validRateProdId, rhs.validRateProdId)) return false;
		if(!ObjectUtils.equals(priceTrace, rhs.priceTrace)) return false;
		if(!ObjectUtils.equals(prePayBillDtl, rhs.prePayBillDtl)) return false;
		if(!ObjectUtils.equals(stdUnit, rhs.stdUnit)) return false;
		if(!ObjectUtils.equals(tsFlag, rhs.tsFlag)) return false;
		if(!ObjectUtils.equals(irIddCharge, rhs.irIddCharge)) return false;
		if(!ObjectUtils.equals(iddOperatorId, rhs.iddOperatorId)) return false;
		if(!ObjectUtils.equals(markupCharge, rhs.markupCharge)) return false;
		if(!ObjectUtils.equals(rateProdPrice, rhs.rateProdPrice)) return false;
		if(!ObjectUtils.equals(hybridRn, rhs.hybridRn)) return false;
		if(!ObjectUtils.equals(isBarservice, rhs.isBarservice)) return false;
		if(!ObjectUtils.equals(settleCharge, rhs.settleCharge)) return false;
		if(!ObjectUtils.equals(undeductFee, rhs.undeductFee)) return false;
		if(!ObjectUtils.equals(undeductFlag, rhs.undeductFlag)) return false;
		if(!ObjectUtils.equals(rating2billitemmap, rhs.rating2billitemmap)) return false;
		if(!ObjectUtils.equals(chargeConverted, rhs.chargeConverted)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(appType)
		.append(appQuotaId)
		.append(processTime)
		.append(charge)
		.append(discountFee)
		.append(taxCharge)
		.append(freeResDiscountFee)
		.append(freeResChargeVal)
		.append(freeResCreator)
		.append(addupResQueryVal)
		.append(addupResUsingVal)
		.append(addupResQueryValPricerule)
		.append(addupResUsingValPricerule)
		.append(freeresQueryVal)
		.append(creditQueryVal)
		.append(bookQueryVal)
		.append(bookChargeVal)
		.append(creditChargeVal)
		.append(freeresLimitQueryVal)
		.append(capMaxVal)
		.append(budgetVal)
		.append(payforVal)
		.append(validRateProdId)
		.append(priceTrace)
		.append(prePayBillDtl)
		.append(stdUnit)
		.append(tsFlag)
		.append(irIddCharge)
		.append(iddOperatorId)
		.append(markupCharge)
		.append(rateProdPrice)
		.append(hybridRn)
		.append(isBarservice)
		.append(settleCharge)
		.append(undeductFee)
		.append(undeductFlag)
		.append(rating2billitemmap)
		.append(chargeConverted)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(38);
public static final long BITS_ALL_MARKER = 0x2000000000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOutRatingXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "APP_TYPE", 0, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "APP_QUOTA_ID", 1, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "PROCESS_TIME", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "CHARGE", 3, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "DISCOUNT_FEE", 4, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "TAX_CHARGE", 5, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "FREE_RES_DISCOUNT_FEE", 6, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "FREE_RES_CHARGE_VAL", 7, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "FREE_RES_CREATOR", 8, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "ADDUP_RES_QUERY_VAL", 9, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "ADDUP_RES_USING_VAL", 10, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "ADDUP_RES_QUERY_VAL_PRICERULE", 11, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "ADDUP_RES_USING_VAL_PRICERULE", 12, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "FREERES_QUERY_VAL", 13, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "CREDIT_QUERY_VAL", 14, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "BOOK_QUERY_VAL", 15, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "BOOK_CHARGE_VAL", 16, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "CREDIT_CHARGE_VAL", 17, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "FREERES_LIMIT_QUERY_VAL", 18, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "CAP_MAX_VAL", 19, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "BUDGET_VAL", 20, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "PAYFOR_VAL", 21, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "VALID_RATE_PROD_ID", 22, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "PRICE_TRACE", 23, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "PRE_PAY_BILL_DTL", 24, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "STD_UNIT", 25, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "TS_FLAG", 26, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "IR_IDD_CHARGE", 27, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "IDD_OPERATOR_ID", 28, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "MARKUP_CHARGE", 29, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "RATE_PROD_PRICE", 30, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "HYBRID_RN", 31, SPayOffMode.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "IS_BARSERVICE", 32, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "SETTLE_CHARGE", 33, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "UNDEDUCT_FEE", 34, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "UNDEDUCT_FLAG", 35, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "RATING2BILLITEMMAP", 36, Map.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutRatingXdr.class, "CHARGE_CONVERTED", 37, List.class));
}

}