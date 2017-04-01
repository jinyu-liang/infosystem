package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"billingPlanId","acctId","cycleSpecId","validDate","expireDate","opId","soDate","soNbr","paymentChannelInList"})
public class BillingPlanChgIn implements IComplexEntity{


	@XmlElement(name="paymentChannelInList")
	private List<PaymentChannelIn> paymentChannelInList;

	@XmlElement(name="billingPlanId")
	private Long billingPlanId;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	@XmlElement(name="validDate")
	private Date validDate;

	@XmlElement(name="expireDate")
	private Date expireDate;

	@XmlElement(name="opId")
	private Integer opId;

	@XmlElement(name="soDate")
	private Date soDate;

	@XmlElement(name="soNbr")
	private Long soNbr;

	public void setPaymentChannelInList(List<PaymentChannelIn> obj){
		this.paymentChannelInList = obj;
	}

	public List<PaymentChannelIn> getPaymentChannelInList(){
		return paymentChannelInList;
	}

	public void setBillingPlanId(Long obj){
		this.billingPlanId = obj;
	}

	public Long getBillingPlanId(){
		return billingPlanId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
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

	public void setOpId(Integer obj){
		this.opId = obj;
	}

	public Integer getOpId(){
		return opId;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillingPlanChgIn rhs=(BillingPlanChgIn)rhs0;
		if(!ObjectUtils.equals(paymentChannelInList, rhs.paymentChannelInList)) return false;
		if(!ObjectUtils.equals(billingPlanId, rhs.billingPlanId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentChannelInList)
		.append(billingPlanId)
		.append(acctId)
		.append(cycleSpecId)
		.append(validDate)
		.append(expireDate)
		.append(opId)
		.append(soDate)
		.append(soNbr)
		.toHashCode();
	}


}