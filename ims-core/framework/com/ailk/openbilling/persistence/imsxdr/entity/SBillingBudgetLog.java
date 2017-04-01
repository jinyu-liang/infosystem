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
@XmlType(propOrder={"acctId","ownerId","primaryKey","productId","budgetProductId","woffFee","woffTaxFee","budgetValidDate","budgetExpireDate","prodBeginDate","billItemCode","budgetItemCode","measureId","ownerType","billingType","exchangeRate"})
@Sdl(module="MXdr")
public class SBillingBudgetLog extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_OWNER_ID = "OWNER_ID";
	public final static String COL_PRIMARY_KEY = "PRIMARY_KEY";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_BUDGET_PRODUCT_ID = "BUDGET_PRODUCT_ID";
	public final static String COL_WOFF_FEE = "WOFF_FEE";
	public final static String COL_WOFF_TAX_FEE = "WOFF_TAX_FEE";
	public final static String COL_BUDGET_VALID_DATE = "BUDGET_VALID_DATE";
	public final static String COL_BUDGET_EXPIRE_DATE = "BUDGET_EXPIRE_DATE";
	public final static String COL_PROD_BEGIN_DATE = "PROD_BEGIN_DATE";
	public final static String COL_BILL_ITEM_CODE = "BILL_ITEM_CODE";
	public final static String COL_BUDGET_ITEM_CODE = "BUDGET_ITEM_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_OWNER_TYPE = "OWNER_TYPE";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_EXCHANGE_RATE = "EXCHANGE_RATE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_OWNER_ID = 1;
	public final static int IDX_PRIMARY_KEY = 2;
	public final static int IDX_PRODUCT_ID = 3;
	public final static int IDX_BUDGET_PRODUCT_ID = 4;
	public final static int IDX_WOFF_FEE = 5;
	public final static int IDX_WOFF_TAX_FEE = 6;
	public final static int IDX_BUDGET_VALID_DATE = 7;
	public final static int IDX_BUDGET_EXPIRE_DATE = 8;
	public final static int IDX_PROD_BEGIN_DATE = 9;
	public final static int IDX_BILL_ITEM_CODE = 10;
	public final static int IDX_BUDGET_ITEM_CODE = 11;
	public final static int IDX_MEASURE_ID = 12;
	public final static int IDX_OWNER_TYPE = 13;
	public final static int IDX_BILLING_TYPE = 14;
	public final static int IDX_EXCHANGE_RATE = 15;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="ownerId")
	@Sdl
	private long ownerId;

	/**
	 * 
	 */
	@XmlElement(name="primaryKey")
	@Sdl
	private long primaryKey;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

	/**
	 * 
	 */
	@XmlElement(name="budgetProductId")
	@Sdl
	private long budgetProductId;

	/**
	 * 
	 */
	@XmlElement(name="woffFee")
	@Sdl
	private long woffFee;

	/**
	 * 
	 */
	@XmlElement(name="woffTaxFee")
	@Sdl
	private long woffTaxFee;

	/**
	 * 
	 */
	@XmlElement(name="budgetValidDate")
	@Sdl
	private long budgetValidDate;

	/**
	 * 
	 */
	@XmlElement(name="budgetExpireDate")
	@Sdl
	private long budgetExpireDate;

	/**
	 * 
	 */
	@XmlElement(name="prodBeginDate")
	@Sdl
	private long prodBeginDate;

	/**
	 * 
	 */
	@XmlElement(name="billItemCode")
	@Sdl
	private int billItemCode;

	/**
	 * 
	 */
	@XmlElement(name="budgetItemCode")
	@Sdl
	private int budgetItemCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="ownerType")
	@Sdl
	private short ownerType;

	/**
	 * 
	 */
	@XmlElement(name="billingType")
	@Sdl
	private short billingType;

	/**
	 * 
	 */
	@XmlElement(name="exchangeRate")
	@Sdl
	private float exchangeRate;

	public void setAcctId(long obj){
		this.acctId = obj;
		onFieldSet(0, obj);
	}

	public long getAcctId(){
		return acctId;
	}

	public void setOwnerId(long obj){
		this.ownerId = obj;
		onFieldSet(1, obj);
	}

	public long getOwnerId(){
		return ownerId;
	}

	public void setPrimaryKey(long obj){
		this.primaryKey = obj;
		onFieldSet(2, obj);
	}

	public long getPrimaryKey(){
		return primaryKey;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(3, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setBudgetProductId(long obj){
		this.budgetProductId = obj;
		onFieldSet(4, obj);
	}

	public long getBudgetProductId(){
		return budgetProductId;
	}

	public void setWoffFee(long obj){
		this.woffFee = obj;
		onFieldSet(5, obj);
	}

	public long getWoffFee(){
		return woffFee;
	}

	public void setWoffTaxFee(long obj){
		this.woffTaxFee = obj;
		onFieldSet(6, obj);
	}

	public long getWoffTaxFee(){
		return woffTaxFee;
	}

	public void setBudgetValidDate(long obj){
		this.budgetValidDate = obj;
		onFieldSet(7, obj);
	}

	public long getBudgetValidDate(){
		return budgetValidDate;
	}

	public void setBudgetExpireDate(long obj){
		this.budgetExpireDate = obj;
		onFieldSet(8, obj);
	}

	public long getBudgetExpireDate(){
		return budgetExpireDate;
	}

	public void setProdBeginDate(long obj){
		this.prodBeginDate = obj;
		onFieldSet(9, obj);
	}

	public long getProdBeginDate(){
		return prodBeginDate;
	}

	public void setBillItemCode(int obj){
		this.billItemCode = obj;
		onFieldSet(10, obj);
	}

	public int getBillItemCode(){
		return billItemCode;
	}

	public void setBudgetItemCode(int obj){
		this.budgetItemCode = obj;
		onFieldSet(11, obj);
	}

	public int getBudgetItemCode(){
		return budgetItemCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(12, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setOwnerType(short obj){
		this.ownerType = obj;
		onFieldSet(13, obj);
	}

	public short getOwnerType(){
		return ownerType;
	}

	public void setBillingType(short obj){
		this.billingType = obj;
		onFieldSet(14, obj);
	}

	public short getBillingType(){
		return billingType;
	}

	public void setExchangeRate(float obj){
		this.exchangeRate = obj;
		onFieldSet(15, obj);
	}

	public float getExchangeRate(){
		return exchangeRate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingBudgetLog(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 16; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingBudgetLog(SBillingBudgetLog arg0){
		copy(arg0);
	}

	public void copy(final SBillingBudgetLog rhs){
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
		ownerId = rhs.ownerId;
		primaryKey = rhs.primaryKey;
		productId = rhs.productId;
		budgetProductId = rhs.budgetProductId;
		woffFee = rhs.woffFee;
		woffTaxFee = rhs.woffTaxFee;
		budgetValidDate = rhs.budgetValidDate;
		budgetExpireDate = rhs.budgetExpireDate;
		prodBeginDate = rhs.prodBeginDate;
		billItemCode = rhs.billItemCode;
		budgetItemCode = rhs.budgetItemCode;
		measureId = rhs.measureId;
		ownerType = rhs.ownerType;
		billingType = rhs.billingType;
		exchangeRate = rhs.exchangeRate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingBudgetLog rhs=(SBillingBudgetLog)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(ownerId, rhs.ownerId)) return false;
		if(!ObjectUtils.equals(primaryKey, rhs.primaryKey)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(budgetProductId, rhs.budgetProductId)) return false;
		if(!ObjectUtils.equals(woffFee, rhs.woffFee)) return false;
		if(!ObjectUtils.equals(woffTaxFee, rhs.woffTaxFee)) return false;
		if(!ObjectUtils.equals(budgetValidDate, rhs.budgetValidDate)) return false;
		if(!ObjectUtils.equals(budgetExpireDate, rhs.budgetExpireDate)) return false;
		if(!ObjectUtils.equals(prodBeginDate, rhs.prodBeginDate)) return false;
		if(!ObjectUtils.equals(billItemCode, rhs.billItemCode)) return false;
		if(!ObjectUtils.equals(budgetItemCode, rhs.budgetItemCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(ownerType, rhs.ownerType)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(exchangeRate, rhs.exchangeRate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(ownerId)
		.append(primaryKey)
		.append(productId)
		.append(budgetProductId)
		.append(woffFee)
		.append(woffTaxFee)
		.append(budgetValidDate)
		.append(budgetExpireDate)
		.append(prodBeginDate)
		.append(billItemCode)
		.append(budgetItemCode)
		.append(measureId)
		.append(ownerType)
		.append(billingType)
		.append(exchangeRate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(16);
public static final long BITS_ALL_MARKER = 0x8000L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingBudgetLog";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "OWNER_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "PRIMARY_KEY", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "PRODUCT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "BUDGET_PRODUCT_ID", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "WOFF_FEE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "WOFF_TAX_FEE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "BUDGET_VALID_DATE", 7, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "BUDGET_EXPIRE_DATE", 8, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "PROD_BEGIN_DATE", 9, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "BILL_ITEM_CODE", 10, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "BUDGET_ITEM_CODE", 11, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "MEASURE_ID", 12, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "OWNER_TYPE", 13, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "BILLING_TYPE", 14, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingBudgetLog.class, "EXCHANGE_RATE", 15, float.class));
}

}