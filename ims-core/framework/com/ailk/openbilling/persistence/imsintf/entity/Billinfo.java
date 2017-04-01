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
@XmlType(propOrder={"invoice_no","outer_cust_id","cust_id","outer_acct_id","acct_id","outer_pay_acct_id","pay_acct_id","user_id","phone_id","bill_cycle_no","cycle_begin_date","cycle_end_date","unpay_fee","discount_fee","adjust_fee","prim_fee","measure_id","billItemList"})
public class Billinfo implements IComplexEntity{


	@XmlElement(name="billItemList")
	private BillItemList billItemList;

	@XmlElement(name="invoice_no")
	private String invoice_no;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="outer_pay_acct_id")
	private String outer_pay_acct_id;

	@XmlElement(name="pay_acct_id")
	private Long pay_acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="bill_cycle_no")
	private String bill_cycle_no;

	@XmlElement(name="cycle_begin_date")
	private String cycle_begin_date;

	@XmlElement(name="cycle_end_date")
	private String cycle_end_date;

	@XmlElement(name="unpay_fee")
	private Double unpay_fee;

	@XmlElement(name="discount_fee")
	private Double discount_fee;

	@XmlElement(name="adjust_fee")
	private Double adjust_fee;

	@XmlElement(name="prim_fee")
	private Double prim_fee;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setBillItemList(BillItemList obj){
		this.billItemList = obj;
	}

	public BillItemList getBillItemList(){
		return billItemList;
	}

	public void setInvoice_no(String obj){
		this.invoice_no = obj;
	}

	public String getInvoice_no(){
		return invoice_no;
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

	public void setOuter_pay_acct_id(String obj){
		this.outer_pay_acct_id = obj;
	}

	public String getOuter_pay_acct_id(){
		return outer_pay_acct_id;
	}

	public void setPay_acct_id(Long obj){
		this.pay_acct_id = obj;
	}

	public Long getPay_acct_id(){
		return pay_acct_id;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setBill_cycle_no(String obj){
		this.bill_cycle_no = obj;
	}

	public String getBill_cycle_no(){
		return bill_cycle_no;
	}

	public void setCycle_begin_date(String obj){
		this.cycle_begin_date = obj;
	}

	public String getCycle_begin_date(){
		return cycle_begin_date;
	}

	public void setCycle_end_date(String obj){
		this.cycle_end_date = obj;
	}

	public String getCycle_end_date(){
		return cycle_end_date;
	}

	public void setUnpay_fee(Double obj){
		this.unpay_fee = obj;
	}

	public Double getUnpay_fee(){
		return unpay_fee;
	}

	public void setDiscount_fee(Double obj){
		this.discount_fee = obj;
	}

	public Double getDiscount_fee(){
		return discount_fee;
	}

	public void setAdjust_fee(Double obj){
		this.adjust_fee = obj;
	}

	public Double getAdjust_fee(){
		return adjust_fee;
	}

	public void setPrim_fee(Double obj){
		this.prim_fee = obj;
	}

	public Double getPrim_fee(){
		return prim_fee;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Billinfo rhs=(Billinfo)rhs0;
		if(!ObjectUtils.equals(billItemList, rhs.billItemList)) return false;
		if(!ObjectUtils.equals(invoice_no, rhs.invoice_no)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(outer_pay_acct_id, rhs.outer_pay_acct_id)) return false;
		if(!ObjectUtils.equals(pay_acct_id, rhs.pay_acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(bill_cycle_no, rhs.bill_cycle_no)) return false;
		if(!ObjectUtils.equals(cycle_begin_date, rhs.cycle_begin_date)) return false;
		if(!ObjectUtils.equals(cycle_end_date, rhs.cycle_end_date)) return false;
		if(!ObjectUtils.equals(unpay_fee, rhs.unpay_fee)) return false;
		if(!ObjectUtils.equals(discount_fee, rhs.discount_fee)) return false;
		if(!ObjectUtils.equals(adjust_fee, rhs.adjust_fee)) return false;
		if(!ObjectUtils.equals(prim_fee, rhs.prim_fee)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billItemList)
		.append(invoice_no)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(outer_pay_acct_id)
		.append(pay_acct_id)
		.append(user_id)
		.append(phone_id)
		.append(bill_cycle_no)
		.append(cycle_begin_date)
		.append(cycle_end_date)
		.append(unpay_fee)
		.append(discount_fee)
		.append(adjust_fee)
		.append(prim_fee)
		.append(measure_id)
		.toHashCode();
	}


}