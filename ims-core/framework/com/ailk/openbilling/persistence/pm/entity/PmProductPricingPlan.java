package com.ailk.openbilling.persistence.pm.entity;

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
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 */
@NotModified
@Entity
@Table(schema="pd",name="PM_PRODUCT_PRICING_PLAN")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productOfferingId","policyId","pricingPlanId","priority","mainPromotion","dispFlag"})
public class PmProductPricingPlan extends DataObject{


	/**
	 * a unique identifier for product offering
	 */
	@Id
	@Column(name="PRODUCT_OFFERING_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productOfferingId")
	private Integer productOfferingId;

	/**
	 * pricing policy ID expresses the scenario of effective price.
	 */
	@Id
	@Column(name="POLICY_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="policyId")
	private Integer policyId;

	/**
	 * pricing plan identifier
	 */
	@Column(name="PRICING_PLAN_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="pricingPlanId")
	private Integer pricingPlanId;

	@Column(name="PRIORITY",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="priority")
	private Integer priority;

	/**
	 * ???-1????????
	 */
	@Id
	@Column(name="MAIN_PROMOTION",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="mainPromotion")
	private Integer mainPromotion;

	/**
	 * 0:ignore;1:??????????;??1???,??????????????
	 */
	@Column(name="DISP_FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="dispFlag")
	private Integer dispFlag;

	public void setProductOfferingId(Integer obj){
		this.productOfferingId = obj;
	}

	public Integer getProductOfferingId(){
		return productOfferingId;
	}

	public void setPolicyId(Integer obj){
		this.policyId = obj;
	}

	public Integer getPolicyId(){
		return policyId;
	}

	public void setPricingPlanId(Integer obj){
		this.pricingPlanId = obj;
	}

	public Integer getPricingPlanId(){
		return pricingPlanId;
	}

	public void setPriority(Integer obj){
		this.priority = obj;
	}

	public Integer getPriority(){
		return priority;
	}

	public void setMainPromotion(Integer obj){
		this.mainPromotion = obj;
	}

	public Integer getMainPromotion(){
		return mainPromotion;
	}

	public void setDispFlag(Integer obj){
		this.dispFlag = obj;
	}

	public Integer getDispFlag(){
		return dispFlag;
	}

	public PmProductPricingPlan(){
	}

	public PmProductPricingPlan(Integer productOfferingId,Integer policyId,Integer mainPromotion){
		this.productOfferingId = productOfferingId;
		this.policyId = policyId;
		this.mainPromotion = mainPromotion;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmProductPricingPlan rhs=(PmProductPricingPlan)rhs0;
		if(!ObjectUtils.equals(policyId, rhs.policyId)) return false;
		if(!ObjectUtils.equals(mainPromotion, rhs.mainPromotion)) return false;
		if(!ObjectUtils.equals(productOfferingId, rhs.productOfferingId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(policyId)
		.append(mainPromotion)
		.append(productOfferingId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{productOfferingId,policyId,pricingPlanId,priority,mainPromotion,dispFlag}
}