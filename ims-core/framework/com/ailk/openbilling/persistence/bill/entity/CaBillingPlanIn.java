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
@XmlType(propOrder={"acct_id","cycle_spec_id","start_date","end_date","bill_format_id","rang_spec_id","paymentplanlist","op_id","so_nbr","payment_channel_in_list"})
public class CaBillingPlanIn implements IComplexEntity{


	@XmlElement(name="payment_channel_in_list")
	private List<PaymentChannelIn> payment_channel_in_list;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="cycle_spec_id")
	private Long cycle_spec_id;

	@XmlElement(name="start_date")
	private Date start_date;

	@XmlElement(name="end_date")
	private Date end_date;

	@XmlElement(name="bill_format_id")
	private Integer bill_format_id;

	@XmlElement(name="rang_spec_id")
	private Long rang_spec_id;

	@XmlElement(name="paymentplanlist")
	private String paymentplanlist;

	@XmlElement(name="op_id")
	private Long op_id;

	@XmlElement(name="so_nbr")
	private Long so_nbr;

	public void setPayment_channel_in_list(List<PaymentChannelIn> obj){
		this.payment_channel_in_list = obj;
	}

	public List<PaymentChannelIn> getPayment_channel_in_list(){
		return payment_channel_in_list;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setCycle_spec_id(Long obj){
		this.cycle_spec_id = obj;
	}

	public Long getCycle_spec_id(){
		return cycle_spec_id;
	}

	public void setStart_date(Date obj){
		this.start_date = obj;
	}

	public Date getStart_date(){
		return start_date;
	}

	public void setEnd_date(Date obj){
		this.end_date = obj;
	}

	public Date getEnd_date(){
		return end_date;
	}

	public void setBill_format_id(Integer obj){
		this.bill_format_id = obj;
	}

	public Integer getBill_format_id(){
		return bill_format_id;
	}

	public void setRang_spec_id(Long obj){
		this.rang_spec_id = obj;
	}

	public Long getRang_spec_id(){
		return rang_spec_id;
	}

	public void setPaymentplanlist(String obj){
		this.paymentplanlist = obj;
	}

	public String getPaymentplanlist(){
		return paymentplanlist;
	}

	public void setOp_id(Long obj){
		this.op_id = obj;
	}

	public Long getOp_id(){
		return op_id;
	}

	public void setSo_nbr(Long obj){
		this.so_nbr = obj;
	}

	public Long getSo_nbr(){
		return so_nbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaBillingPlanIn rhs=(CaBillingPlanIn)rhs0;
		if(!ObjectUtils.equals(payment_channel_in_list, rhs.payment_channel_in_list)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(cycle_spec_id, rhs.cycle_spec_id)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(bill_format_id, rhs.bill_format_id)) return false;
		if(!ObjectUtils.equals(rang_spec_id, rhs.rang_spec_id)) return false;
		if(!ObjectUtils.equals(paymentplanlist, rhs.paymentplanlist)) return false;
		if(!ObjectUtils.equals(op_id, rhs.op_id)) return false;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(payment_channel_in_list)
		.append(acct_id)
		.append(cycle_spec_id)
		.append(start_date)
		.append(end_date)
		.append(bill_format_id)
		.append(rang_spec_id)
		.append(paymentplanlist)
		.append(op_id)
		.append(so_nbr)
		.toHashCode();
	}


}