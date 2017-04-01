package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.acct.entity.CaPaymentPlan;
import java.util.List;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"caPaymentPlan","payementChannelIn"})
public class PaymentPlanOut implements IComplexEntity{


	@XmlElement(name="caPaymentPlan")
	private CaPaymentPlan caPaymentPlan;

	@XmlElement(name="payementChannelIn")
	private List<PaymentChannelIn> payementChannelIn;

	public void setCaPaymentPlan(CaPaymentPlan obj){
		this.caPaymentPlan = obj;
	}

	public CaPaymentPlan getCaPaymentPlan(){
		return caPaymentPlan;
	}

	public void setPayementChannelIn(List<PaymentChannelIn> obj){
		this.payementChannelIn = obj;
	}

	public List<PaymentChannelIn> getPayementChannelIn(){
		return payementChannelIn;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentPlanOut rhs=(PaymentPlanOut)rhs0;
		if(!ObjectUtils.equals(caPaymentPlan, rhs.caPaymentPlan)) return false;
		if(!ObjectUtils.equals(payementChannelIn, rhs.payementChannelIn)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(caPaymentPlan)
		.append(payementChannelIn)
		.toHashCode();
	}


}