package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"invoice_no","amount","sequence","paymentServiceItemList"})
public class PaymentItem implements IComplexEntity{


	@XmlElement(name="paymentServiceItemList")
	private PaymentServiceItemList paymentServiceItemList;

	@XmlElement(name="invoice_no")
	private String invoice_no;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="sequence")
	private Short sequence;

	public void setPaymentServiceItemList(PaymentServiceItemList obj){
		this.paymentServiceItemList = obj;
	}

	public PaymentServiceItemList getPaymentServiceItemList(){
		return paymentServiceItemList;
	}

	public void setInvoice_no(String obj){
		this.invoice_no = obj;
	}

	public String getInvoice_no(){
		return invoice_no;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setSequence(Short obj){
		this.sequence = obj;
	}

	public Short getSequence(){
		return sequence;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentItem rhs=(PaymentItem)rhs0;
		if(!ObjectUtils.equals(paymentServiceItemList, rhs.paymentServiceItemList)) return false;
		if(!ObjectUtils.equals(invoice_no, rhs.invoice_no)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(sequence, rhs.sequence)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentServiceItemList)
		.append(invoice_no)
		.append(amount)
		.append(sequence)
		.toHashCode();
	}


}