package com.ailk.openbilling.persistence.pm.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
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
@Table(schema="PD",name="PM_PRICING_PLAN")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"pricingPlanId","pricingPlanName","pricingPlanDesc","remarks"})
public class PmPricingPlan extends DataObject{


	/**
	 * identifier of pricing plan
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="PRICING_PLAN_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="pricingPlanId")
	private Integer pricingPlanId;

	/**
	 * names of pricing plan for your choice
	 */
	@Column(name="PRICING_PLAN_NAME",columnDefinition="Varchar",length=256)
	@XmlElement(name="pricingPlanName")
	private String pricingPlanName;

	/**
	 * description about pricing tariff
	 */
	@Column(name="PRICING_PLAN_DESC",columnDefinition="Varchar",length=1024,nullable=false)
	@XmlElement(name="pricingPlanDesc")
	private String pricingPlanDesc;

	/**
	 * details
	 */
	@Column(name="REMARKS",columnDefinition="Varchar",length=1024,nullable=false)
	@XmlElement(name="remarks")
	private String remarks;

	public void setPricingPlanId(Integer obj){
		this.pricingPlanId = obj;
	}

	public Integer getPricingPlanId(){
		return pricingPlanId;
	}

	public void setPricingPlanName(String obj){
		this.pricingPlanName = obj;
	}

	public String getPricingPlanName(){
		return pricingPlanName;
	}

	public void setPricingPlanDesc(String obj){
		this.pricingPlanDesc = obj;
	}

	public String getPricingPlanDesc(){
		return pricingPlanDesc;
	}

	public void setRemarks(String obj){
		this.remarks = obj;
	}

	public String getRemarks(){
		return remarks;
	}

	public PmPricingPlan(){
	}

	public PmPricingPlan(Integer pricingPlanId){
		this.pricingPlanId = pricingPlanId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmPricingPlan rhs=(PmPricingPlan)rhs0;
		if(!ObjectUtils.equals(pricingPlanId, rhs.pricingPlanId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(pricingPlanId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{pricingPlanId,pricingPlanName,pricingPlanDesc,remarks}
}