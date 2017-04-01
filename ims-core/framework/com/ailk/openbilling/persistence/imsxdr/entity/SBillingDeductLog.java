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
@XmlType(propOrder={"acctId","servId","assetId","productId","woffFee","woffTaxFee","prodBeginDate","assetItemCode","measureId","billItemCode","billingType","exchangeRate"})
@Sdl(module="MXdr")
public class SBillingDeductLog extends CsdlStructObject implements IComplexEntity{

	public final static String COL_ACCT_ID = "ACCT_ID";
	public final static String COL_SERV_ID = "SERV_ID";
	public final static String COL_ASSET_ID = "ASSET_ID";
	public final static String COL_PRODUCT_ID = "PRODUCT_ID";
	public final static String COL_WOFF_FEE = "WOFF_FEE";
	public final static String COL_WOFF_TAX_FEE = "WOFF_TAX_FEE";
	public final static String COL_PROD_BEGIN_DATE = "PROD_BEGIN_DATE";
	public final static String COL_ASSET_ITEM_CODE = "ASSET_ITEM_CODE";
	public final static String COL_MEASURE_ID = "MEASURE_ID";
	public final static String COL_BILL_ITEM_CODE = "BILL_ITEM_CODE";
	public final static String COL_BILLING_TYPE = "BILLING_TYPE";
	public final static String COL_EXCHANGE_RATE = "EXCHANGE_RATE";
	public final static int IDX_ACCT_ID = 0;
	public final static int IDX_SERV_ID = 1;
	public final static int IDX_ASSET_ID = 2;
	public final static int IDX_PRODUCT_ID = 3;
	public final static int IDX_WOFF_FEE = 4;
	public final static int IDX_WOFF_TAX_FEE = 5;
	public final static int IDX_PROD_BEGIN_DATE = 6;
	public final static int IDX_ASSET_ITEM_CODE = 7;
	public final static int IDX_MEASURE_ID = 8;
	public final static int IDX_BILL_ITEM_CODE = 9;
	public final static int IDX_BILLING_TYPE = 10;
	public final static int IDX_EXCHANGE_RATE = 11;

	/**
	 * 
	 */
	@XmlElement(name="acctId")
	@Sdl
	private long acctId;

	/**
	 * 
	 */
	@XmlElement(name="servId")
	@Sdl
	private long servId;

	/**
	 * 
	 */
	@XmlElement(name="assetId")
	@Sdl
	private long assetId;

	/**
	 * 
	 */
	@XmlElement(name="productId")
	@Sdl
	private long productId;

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
	@XmlElement(name="prodBeginDate")
	@Sdl
	private long prodBeginDate;

	/**
	 * 
	 */
	@XmlElement(name="assetItemCode")
	@Sdl
	private int assetItemCode;

	/**
	 * 
	 */
	@XmlElement(name="measureId")
	@Sdl
	private int measureId;

	/**
	 * 
	 */
	@XmlElement(name="billItemCode")
	@Sdl
	private int billItemCode;

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

	public void setServId(long obj){
		this.servId = obj;
		onFieldSet(1, obj);
	}

	public long getServId(){
		return servId;
	}

	public void setAssetId(long obj){
		this.assetId = obj;
		onFieldSet(2, obj);
	}

	public long getAssetId(){
		return assetId;
	}

	public void setProductId(long obj){
		this.productId = obj;
		onFieldSet(3, obj);
	}

	public long getProductId(){
		return productId;
	}

	public void setWoffFee(long obj){
		this.woffFee = obj;
		onFieldSet(4, obj);
	}

	public long getWoffFee(){
		return woffFee;
	}

	public void setWoffTaxFee(long obj){
		this.woffTaxFee = obj;
		onFieldSet(5, obj);
	}

	public long getWoffTaxFee(){
		return woffTaxFee;
	}

	public void setProdBeginDate(long obj){
		this.prodBeginDate = obj;
		onFieldSet(6, obj);
	}

	public long getProdBeginDate(){
		return prodBeginDate;
	}

	public void setAssetItemCode(int obj){
		this.assetItemCode = obj;
		onFieldSet(7, obj);
	}

	public int getAssetItemCode(){
		return assetItemCode;
	}

	public void setMeasureId(int obj){
		this.measureId = obj;
		onFieldSet(8, obj);
	}

	public int getMeasureId(){
		return measureId;
	}

	public void setBillItemCode(int obj){
		this.billItemCode = obj;
		onFieldSet(9, obj);
	}

	public int getBillItemCode(){
		return billItemCode;
	}

	public void setBillingType(short obj){
		this.billingType = obj;
		onFieldSet(10, obj);
	}

	public short getBillingType(){
		return billingType;
	}

	public void setExchangeRate(float obj){
		this.exchangeRate = obj;
		onFieldSet(11, obj);
	}

	public float getExchangeRate(){
		return exchangeRate;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SBillingDeductLog(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 12; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SBillingDeductLog(SBillingDeductLog arg0){
		copy(arg0);
	}

	public void copy(final SBillingDeductLog rhs){
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
		servId = rhs.servId;
		assetId = rhs.assetId;
		productId = rhs.productId;
		woffFee = rhs.woffFee;
		woffTaxFee = rhs.woffTaxFee;
		prodBeginDate = rhs.prodBeginDate;
		assetItemCode = rhs.assetItemCode;
		measureId = rhs.measureId;
		billItemCode = rhs.billItemCode;
		billingType = rhs.billingType;
		exchangeRate = rhs.exchangeRate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillingDeductLog rhs=(SBillingDeductLog)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(woffFee, rhs.woffFee)) return false;
		if(!ObjectUtils.equals(woffTaxFee, rhs.woffTaxFee)) return false;
		if(!ObjectUtils.equals(prodBeginDate, rhs.prodBeginDate)) return false;
		if(!ObjectUtils.equals(assetItemCode, rhs.assetItemCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(billItemCode, rhs.billItemCode)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(exchangeRate, rhs.exchangeRate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(servId)
		.append(assetId)
		.append(productId)
		.append(woffFee)
		.append(woffTaxFee)
		.append(prodBeginDate)
		.append(assetItemCode)
		.append(measureId)
		.append(billItemCode)
		.append(billingType)
		.append(exchangeRate)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(12);
public static final long BITS_ALL_MARKER = 0x800L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SBillingDeductLog";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "ACCT_ID", 0, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "SERV_ID", 1, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "ASSET_ID", 2, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "PRODUCT_ID", 3, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "WOFF_FEE", 4, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "WOFF_TAX_FEE", 5, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "PROD_BEGIN_DATE", 6, long.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "ASSET_ITEM_CODE", 7, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "MEASURE_ID", 8, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "BILL_ITEM_CODE", 9, int.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "BILLING_TYPE", 10, short.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SBillingDeductLog.class, "EXCHANGE_RATE", 11, float.class));
}

}