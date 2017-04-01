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
@XmlType(propOrder={"outer_cust_id","cust_id","outer_acct_id","acct_id","phone_id","flag","op_id","payment_date","pay_type","invoice_no","amount","sequence","currency","reference_num","remark","paymentServiceItemList","so_mode","source_system","transaction_id","operation_date","_fail_reason"})
public class PaymentLog implements IComplexEntity{


	@XmlElement(name="paymentServiceItemList")
	private PaymentServiceItemList paymentServiceItemList;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="flag")
	private Short flag;

	@XmlElement(name="op_id")
	private Integer op_id;

	@XmlElement(name="payment_date")
	private String payment_date;

	@XmlElement(name="pay_type")
	private Short pay_type;

	@XmlElement(name="invoice_no")
	private String invoice_no;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="sequence")
	private Short sequence;

	@XmlElement(name="currency")
	private Short currency;

	@XmlElement(name="reference_num")
	private String reference_num;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="so_mode")
	private Short so_mode;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="transaction_id")
	private String transaction_id;

	@XmlElement(name="operation_date")
	private String operation_date;

	@XmlElement(name="_fail_reason")
	private String _fail_reason;

	public void setPaymentServiceItemList(PaymentServiceItemList obj){
		this.paymentServiceItemList = obj;
	}

	public PaymentServiceItemList getPaymentServiceItemList(){
		return paymentServiceItemList;
	}

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setFlag(Short obj){
		this.flag = obj;
	}

	public Short getFlag(){
		return flag;
	}

	public void setOp_id(Integer obj){
		this.op_id = obj;
	}

	public Integer getOp_id(){
		return op_id;
	}

	public void setPayment_date(String obj){
		this.payment_date = obj;
	}

	public String getPayment_date(){
		return payment_date;
	}

	public void setPay_type(Short obj){
		this.pay_type = obj;
	}

	public Short getPay_type(){
		return pay_type;
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

	public void setCurrency(Short obj){
		this.currency = obj;
	}

	public Short getCurrency(){
		return currency;
	}

	public void setReference_num(String obj){
		this.reference_num = obj;
	}

	public String getReference_num(){
		return reference_num;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setSo_mode(Short obj){
		this.so_mode = obj;
	}

	public Short getSo_mode(){
		return so_mode;
	}

	public void setSource_system(String obj){
		this.source_system = obj;
	}

	public String getSource_system(){
		return source_system;
	}

	public void setTransaction_id(String obj){
		this.transaction_id = obj;
	}

	public String getTransaction_id(){
		return transaction_id;
	}

	public void setOperation_date(String obj){
		this.operation_date = obj;
	}

	public String getOperation_date(){
		return operation_date;
	}

	public void setFail_reason(String obj){
		this._fail_reason = obj;
	}

	public String getFail_reason(){
		return _fail_reason;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentLog rhs=(PaymentLog)rhs0;
		if(!ObjectUtils.equals(paymentServiceItemList, rhs.paymentServiceItemList)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(op_id, rhs.op_id)) return false;
		if(!ObjectUtils.equals(payment_date, rhs.payment_date)) return false;
		if(!ObjectUtils.equals(pay_type, rhs.pay_type)) return false;
		if(!ObjectUtils.equals(invoice_no, rhs.invoice_no)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(sequence, rhs.sequence)) return false;
		if(!ObjectUtils.equals(currency, rhs.currency)) return false;
		if(!ObjectUtils.equals(reference_num, rhs.reference_num)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(transaction_id, rhs.transaction_id)) return false;
		if(!ObjectUtils.equals(operation_date, rhs.operation_date)) return false;
		if(!ObjectUtils.equals(_fail_reason, rhs._fail_reason)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentServiceItemList)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(phone_id)
		.append(flag)
		.append(op_id)
		.append(payment_date)
		.append(pay_type)
		.append(invoice_no)
		.append(amount)
		.append(sequence)
		.append(currency)
		.append(reference_num)
		.append(remark)
		.append(so_mode)
		.append(source_system)
		.append(transaction_id)
		.append(operation_date)
		.append(_fail_reason)
		.toHashCode();
	}


}