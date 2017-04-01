package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acct_id","billing_name","invoice_no","bill_start_date","bill_end_date","payment_due_date","bill_amount"})
public class Do_queryInvoiceInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="billing_name")
	private String billing_name;

	@XmlElement(name="invoice_no")
	private String invoice_no;

	@XmlElement(name="bill_start_date")
	private Date bill_start_date;

	@XmlElement(name="bill_end_date")
	private Date bill_end_date;

	@XmlElement(name="payment_due_date")
	private Date payment_due_date;

	@XmlElement(name="bill_amount")
	private Long bill_amount;

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setBilling_name(String obj){
		this.billing_name = obj;
	}

	public String getBilling_name(){
		return billing_name;
	}

	public void setInvoice_no(String obj){
		this.invoice_no = obj;
	}

	public String getInvoice_no(){
		return invoice_no;
	}

	public void setBill_start_date(Date obj){
		this.bill_start_date = obj;
	}

	public Date getBill_start_date(){
		return bill_start_date;
	}

	public void setBill_end_date(Date obj){
		this.bill_end_date = obj;
	}

	public Date getBill_end_date(){
		return bill_end_date;
	}

	public void setPayment_due_date(Date obj){
		this.payment_due_date = obj;
	}

	public Date getPayment_due_date(){
		return payment_due_date;
	}

	public void setBill_amount(Long obj){
		this.bill_amount = obj;
	}

	public Long getBill_amount(){
		return bill_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryInvoiceInfoResponse rhs=(Do_queryInvoiceInfoResponse)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(billing_name, rhs.billing_name)) return false;
		if(!ObjectUtils.equals(invoice_no, rhs.invoice_no)) return false;
		if(!ObjectUtils.equals(bill_start_date, rhs.bill_start_date)) return false;
		if(!ObjectUtils.equals(bill_end_date, rhs.bill_end_date)) return false;
		if(!ObjectUtils.equals(payment_due_date, rhs.payment_due_date)) return false;
		if(!ObjectUtils.equals(bill_amount, rhs.bill_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(billing_name)
		.append(invoice_no)
		.append(bill_start_date)
		.append(bill_end_date)
		.append(payment_due_date)
		.append(bill_amount)
		.toHashCode();
	}


}