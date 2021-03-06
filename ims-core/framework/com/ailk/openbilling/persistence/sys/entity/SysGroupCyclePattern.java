package com.ailk.openbilling.persistence.sys.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="SD",name="SYS_GROUP_CYCLE_PATTERN")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","patternId","brandId","mainPromotion","regionCode","customerClass","customerType","customerSegment","accountType","billingType","resType","resSegment","policyId","validDate","expireDate"})
public class SysGroupCyclePattern extends DataObject{


	/**
	 * SEQ流水
	 */
	@Column(name="ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="id")
	private Integer id;

	/**
	 * Pattern ID
	 */
	@Id
	@Column(name="PATTERN_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="patternId")
	private Integer patternId;

	/**
	 * Brand ID
	 */
	@Id
	@Column(name="BRAND_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="brandId")
	private Integer brandId;

	/**
	 * Main product for reference, default value -1
	 */
	@Id
	@Column(name="MAIN_PROMOTION",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="mainPromotion")
	private Integer mainPromotion;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	/**
	 * Customer Class
0:Person
1:Family
2:Group
-1: ignore
	 */
	@Column(name="CUSTOMER_CLASS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="customerClass")
	private Integer customerClass;

	/**
	 * customer type
-1---ignore
	 */
	@Column(name="CUSTOMER_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="customerType")
	private Integer customerType;

	/**
	 * customer level
-1---ignore
	 */
	@Column(name="CUSTOMER_SEGMENT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="customerSegment")
	private Integer customerSegment;

	/**
	 * account type
-1---ignore
	 */
	@Column(name="ACCOUNT_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="accountType")
	private Integer accountType;

	/**
	 * billing type 
-1-ignore
	 */
	@Column(name="BILLING_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="billingType")
	private Integer billingType;

	/**
	 * Resource type
-1-ignore
	 */
	@Column(name="RES_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="resType")
	private Integer resType;

	/**
	 * Resource segment
1.Classic
2.Gold
3.Platinum
4.Platinum Plus
5.Serenade CEO
6.Serenade Prestige
7.Standard
	 */
	@Column(name="RES_SEGMENT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="resSegment")
	private Integer resSegment;

	/**
	 * Policy ID, 0 for always true.
	 */
	@Id
	@Column(name="POLICY_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="policyId")
	private Integer policyId;

	/**
	 * YYYY-MM-DD HH:MM:SS
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * YYYY-MM-DD HH:MM:SS
	 */
	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	public void setId(Integer obj){
		this.id = obj;
	}

	public Integer getId(){
		return id;
	}

	public void setPatternId(Integer obj){
		this.patternId = obj;
	}

	public Integer getPatternId(){
		return patternId;
	}

	public void setBrandId(Integer obj){
		this.brandId = obj;
	}

	public Integer getBrandId(){
		return brandId;
	}

	public void setMainPromotion(Integer obj){
		this.mainPromotion = obj;
	}

	public Integer getMainPromotion(){
		return mainPromotion;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setCustomerClass(Integer obj){
		this.customerClass = obj;
	}

	public Integer getCustomerClass(){
		return customerClass;
	}

	public void setCustomerType(Integer obj){
		this.customerType = obj;
	}

	public Integer getCustomerType(){
		return customerType;
	}

	public void setCustomerSegment(Integer obj){
		this.customerSegment = obj;
	}

	public Integer getCustomerSegment(){
		return customerSegment;
	}

	public void setAccountType(Integer obj){
		this.accountType = obj;
	}

	public Integer getAccountType(){
		return accountType;
	}

	public void setBillingType(Integer obj){
		this.billingType = obj;
	}

	public Integer getBillingType(){
		return billingType;
	}

	public void setResType(Integer obj){
		this.resType = obj;
	}

	public Integer getResType(){
		return resType;
	}

	public void setResSegment(Integer obj){
		this.resSegment = obj;
	}

	public Integer getResSegment(){
		return resSegment;
	}

	public void setPolicyId(Integer obj){
		this.policyId = obj;
	}

	public Integer getPolicyId(){
		return policyId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public SysGroupCyclePattern(){
	}

	public SysGroupCyclePattern(Integer patternId,Integer brandId,Integer mainPromotion,Integer policyId){
		this.patternId = patternId;
		this.brandId = brandId;
		this.mainPromotion = mainPromotion;
		this.policyId = policyId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SysGroupCyclePattern rhs=(SysGroupCyclePattern)rhs0;
		if(!ObjectUtils.equals(patternId, rhs.patternId)) return false;
		if(!ObjectUtils.equals(policyId, rhs.policyId)) return false;
		if(!ObjectUtils.equals(mainPromotion, rhs.mainPromotion)) return false;
		if(!ObjectUtils.equals(brandId, rhs.brandId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(patternId)
		.append(policyId)
		.append(mainPromotion)
		.append(brandId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,patternId,brandId,mainPromotion,regionCode,customerClass,customerType,customerSegment,accountType,billingType,resType,resSegment,policyId,validDate,expireDate}
}