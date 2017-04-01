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
@XmlType(propOrder={"billingCommon","billingChargeDtl","billingPromDtl","pocketDeductVal","creditDeductVal","budgetVal","accumulateVal","busiInfo","billingProd","freeResCreator","billingDeductDtl"})
@Sdl(module="MXdr")
public class SOutBillingXdr extends CsdlStructObject implements IComplexEntity{

	public final static String COL_BILLING_COMMON = "BILLING_COMMON";
	public final static String COL_BILLING_CHARGE_DTL = "BILLING_CHARGE_DTL";
	public final static String COL_BILLING_PROM_DTL = "BILLING_PROM_DTL";
	public final static String COL_POCKET_DEDUCT_VAL = "POCKET_DEDUCT_VAL";
	public final static String COL_CREDIT_DEDUCT_VAL = "CREDIT_DEDUCT_VAL";
	public final static String COL_BUDGET_VAL = "BUDGET_VAL";
	public final static String COL_ACCUMULATE_VAL = "ACCUMULATE_VAL";
	public final static String COL_BUSI_INFO = "BUSI_INFO";
	public final static String COL_BILLING_PROD = "BILLING_PROD";
	public final static String COL_FREE_RES_CREATOR = "FREE_RES_CREATOR";
	public final static String COL_BILLING_DEDUCT_DTL = "BILLING_DEDUCT_DTL";
	public final static int IDX_BILLING_COMMON = 0;
	public final static int IDX_BILLING_CHARGE_DTL = 1;
	public final static int IDX_BILLING_PROM_DTL = 2;
	public final static int IDX_POCKET_DEDUCT_VAL = 3;
	public final static int IDX_CREDIT_DEDUCT_VAL = 4;
	public final static int IDX_BUDGET_VAL = 5;
	public final static int IDX_ACCUMULATE_VAL = 6;
	public final static int IDX_BUSI_INFO = 7;
	public final static int IDX_BILLING_PROD = 8;
	public final static int IDX_FREE_RES_CREATOR = 9;
	public final static int IDX_BILLING_DEDUCT_DTL = 10;

	/**
	 * 
	 */
	@XmlElement(name="billingCommon")
	@Sdl
	private SBillingCommon billingCommon;

	/**
	 * 
	 */
	@XmlElement(name="billingChargeDtl")
	@Sdl
	private List<SBillingCharge> billingChargeDtl;

	/**
	 * 
	 */
	@XmlElement(name="billingPromDtl")
	@Sdl
	private List<SBillingProm> billingPromDtl;

	/**
	 * 
	 */
	@XmlElement(name="pocketDeductVal")
	@Sdl
	private List<SBillingDeductLog> pocketDeductVal;

	/**
	 * 
	 */
	@XmlElement(name="creditDeductVal")
	@Sdl
	private List<SBillingDeductLog> creditDeductVal;

	/**
	 * 
	 */
	@XmlElement(name="budgetVal")
	@Sdl
	private List<SBillingBudgetLog> budgetVal;

	/**
	 * 
	 */
	@XmlElement(name="accumulateVal")
	@Sdl
	private List<SBillingAccumulate> accumulateVal;

	/**
	 * 
	 */
	@XmlElement(name="busiInfo")
	@Sdl
	private SBillingBusiness busiInfo;

	/**
	 * 
	 */
	@XmlElement(name="billingProd")
	@Sdl
	private List<SBillingProd> billingProd;

	/**
	 * 
	 */
	@XmlElement(name="freeResCreator")
	@Sdl
	private List<SFreeResCreator> freeResCreator;

	/**
	 * 
	 */
	@XmlElement(name="billingDeductDtl")
	@Sdl
	private List<SBillingDeductDtl> billingDeductDtl;

	public void setBillingCommon(SBillingCommon obj){
		this.billingCommon = obj;
		onFieldSet(0, obj);
	}

	public SBillingCommon getBillingCommon(){
		return billingCommon;
	}

	public void setBillingChargeDtl(List<SBillingCharge> obj){
		this.billingChargeDtl = obj;
		onFieldSet(1, obj);
	}

	public List<SBillingCharge> getBillingChargeDtl(){
		return billingChargeDtl;
	}

	public void setBillingPromDtl(List<SBillingProm> obj){
		this.billingPromDtl = obj;
		onFieldSet(2, obj);
	}

	public List<SBillingProm> getBillingPromDtl(){
		return billingPromDtl;
	}

	public void setPocketDeductVal(List<SBillingDeductLog> obj){
		this.pocketDeductVal = obj;
		onFieldSet(3, obj);
	}

	public List<SBillingDeductLog> getPocketDeductVal(){
		return pocketDeductVal;
	}

	public void setCreditDeductVal(List<SBillingDeductLog> obj){
		this.creditDeductVal = obj;
		onFieldSet(4, obj);
	}

	public List<SBillingDeductLog> getCreditDeductVal(){
		return creditDeductVal;
	}

	public void setBudgetVal(List<SBillingBudgetLog> obj){
		this.budgetVal = obj;
		onFieldSet(5, obj);
	}

	public List<SBillingBudgetLog> getBudgetVal(){
		return budgetVal;
	}

	public void setAccumulateVal(List<SBillingAccumulate> obj){
		this.accumulateVal = obj;
		onFieldSet(6, obj);
	}

	public List<SBillingAccumulate> getAccumulateVal(){
		return accumulateVal;
	}

	public void setBusiInfo(SBillingBusiness obj){
		this.busiInfo = obj;
		onFieldSet(7, obj);
	}

	public SBillingBusiness getBusiInfo(){
		return busiInfo;
	}

	public void setBillingProd(List<SBillingProd> obj){
		this.billingProd = obj;
		onFieldSet(8, obj);
	}

	public List<SBillingProd> getBillingProd(){
		return billingProd;
	}

	public void setFreeResCreator(List<SFreeResCreator> obj){
		this.freeResCreator = obj;
		onFieldSet(9, obj);
	}

	public List<SFreeResCreator> getFreeResCreator(){
		return freeResCreator;
	}

	public void setBillingDeductDtl(List<SBillingDeductDtl> obj){
		this.billingDeductDtl = obj;
		onFieldSet(10, obj);
	}

	public List<SBillingDeductDtl> getBillingDeductDtl(){
		return billingDeductDtl;
	}

	public List<MemberTypeInfo> getMemberInfoList(){
		return memberTypeInfoList;
	}

	public SOutBillingXdr(){
		m_llMarkers = new long[1]; // used marker
		m_llUsedMarkers = new long[1]; // used marker
		fieldNum = 11; 
		markerNum = 1; 
	}

	/**
	 * 创建copy方法
	 */
	public SOutBillingXdr(SOutBillingXdr arg0){
		copy(arg0);
	}

	public void copy(final SOutBillingXdr rhs){
		if (rhs == null)return;
		this.m_llMarker = rhs.m_llMarker;
		this.m_llUsedMarker = rhs.m_llUsedMarker;
		this.fieldNum = rhs.fieldNum;
		this.markerNum = rhs.markerNum;
		for (int i = 0; i < markerNum; i++) {
			m_llMarkers[i] = rhs.m_llMarkers[i];
			m_llUsedMarkers[i] = rhs.m_llUsedMarkers[i];
		}
		billingCommon = rhs.billingCommon;
		billingChargeDtl = rhs.billingChargeDtl;
		billingPromDtl = rhs.billingPromDtl;
		pocketDeductVal = rhs.pocketDeductVal;
		creditDeductVal = rhs.creditDeductVal;
		budgetVal = rhs.budgetVal;
		accumulateVal = rhs.accumulateVal;
		busiInfo = rhs.busiInfo;
		billingProd = rhs.billingProd;
		freeResCreator = rhs.freeResCreator;
		billingDeductDtl = rhs.billingDeductDtl;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOutBillingXdr rhs=(SOutBillingXdr)rhs0;
		if(!ObjectUtils.equals(billingCommon, rhs.billingCommon)) return false;
		if(!ObjectUtils.equals(billingChargeDtl, rhs.billingChargeDtl)) return false;
		if(!ObjectUtils.equals(billingPromDtl, rhs.billingPromDtl)) return false;
		if(!ObjectUtils.equals(pocketDeductVal, rhs.pocketDeductVal)) return false;
		if(!ObjectUtils.equals(creditDeductVal, rhs.creditDeductVal)) return false;
		if(!ObjectUtils.equals(budgetVal, rhs.budgetVal)) return false;
		if(!ObjectUtils.equals(accumulateVal, rhs.accumulateVal)) return false;
		if(!ObjectUtils.equals(busiInfo, rhs.busiInfo)) return false;
		if(!ObjectUtils.equals(billingProd, rhs.billingProd)) return false;
		if(!ObjectUtils.equals(freeResCreator, rhs.freeResCreator)) return false;
		if(!ObjectUtils.equals(billingDeductDtl, rhs.billingDeductDtl)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billingCommon)
		.append(billingChargeDtl)
		.append(billingPromDtl)
		.append(pocketDeductVal)
		.append(creditDeductVal)
		.append(budgetVal)
		.append(accumulateVal)
		.append(busiInfo)
		.append(billingProd)
		.append(freeResCreator)
		.append(billingDeductDtl)
		.toHashCode();
	}


protected static java.util.List<MemberTypeInfo> memberTypeInfoList = new java.util.ArrayList<MemberTypeInfo>(11);
public static final long BITS_ALL_MARKER = 0x400L;
public static final long BITS_NOT_NULL_MARKER = 0x0L;
public static final String SZ_TYPE_NAME = "com.ailk.openbilling.persistence.imsxdr.entity.SOutBillingXdr";
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llMarker = 0l; // null marker
@XmlTransient
@Expose(deserialize = false, serialize = false)
protected long m_llUsedMarker = 0l; // used marker

static{
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BILLING_COMMON", 0, SBillingCommon.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BILLING_CHARGE_DTL", 1, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BILLING_PROM_DTL", 2, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "POCKET_DEDUCT_VAL", 3, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "CREDIT_DEDUCT_VAL", 4, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BUDGET_VAL", 5, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "ACCUMULATE_VAL", 6, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BUSI_INFO", 7, SBillingBusiness.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BILLING_PROD", 8, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "FREE_RES_CREATOR", 9, List.class));
	memberTypeInfoList.add(MemberTypeInfo.get(SOutBillingXdr.class, "BILLING_DEDUCT_DTL", 10, List.class));
}

}