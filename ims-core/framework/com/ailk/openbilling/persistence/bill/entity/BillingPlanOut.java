package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaPaymentPlan;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"billingPlan","billingCycleSpecification","paymentPlan","paymentChannelInList"})
public class BillingPlanOut implements IComplexEntity{


	@XmlElement(name="billingPlan")
	private CaBillingPlan billingPlan;

	@XmlElement(name="billingCycleSpecification")
	private CaBillingCycleSpec billingCycleSpecification;

	@XmlElement(name="paymentPlan")
	private CaPaymentPlan paymentPlan;

	@XmlElement(name="paymentChannelInList")
	private List<PaymentChannelIn> paymentChannelInList;

	public void setBillingPlan(CaBillingPlan obj){
		this.billingPlan = obj;
	}

	public CaBillingPlan getBillingPlan(){
		return billingPlan;
	}

	public void setBillingCycleSpecification(CaBillingCycleSpec obj){
		this.billingCycleSpecification = obj;
	}

	public CaBillingCycleSpec getBillingCycleSpecification(){
		return billingCycleSpecification;
	}

	public void setPaymentPlan(CaPaymentPlan obj){
		this.paymentPlan = obj;
	}

	public CaPaymentPlan getPaymentPlan(){
		return paymentPlan;
	}

	public void setPaymentChannelInList(List<PaymentChannelIn> obj){
		this.paymentChannelInList = obj;
	}

	public List<PaymentChannelIn> getPaymentChannelInList(){
		return paymentChannelInList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillingPlanOut rhs=(BillingPlanOut)rhs0;
		if(!ObjectUtils.equals(billingPlan, rhs.billingPlan)) return false;
		if(!ObjectUtils.equals(billingCycleSpecification, rhs.billingCycleSpecification)) return false;
		if(!ObjectUtils.equals(paymentPlan, rhs.paymentPlan)) return false;
		if(!ObjectUtils.equals(paymentChannelInList, rhs.paymentChannelInList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billingPlan)
		.append(billingCycleSpecification)
		.append(paymentPlan)
		.append(paymentChannelInList)
		.toHashCode();
	}


}