package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acctId","paymentPlanId"})
public class QryPaymentPlanIn implements IComplexEntity{


	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="paymentPlanId")
	private Long paymentPlanId;

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setPaymentPlanId(Long obj){
		this.paymentPlanId = obj;
	}

	public Long getPaymentPlanId(){
		return paymentPlanId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QryPaymentPlanIn rhs=(QryPaymentPlanIn)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(paymentPlanId, rhs.paymentPlanId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(paymentPlanId)
		.toHashCode();
	}


}