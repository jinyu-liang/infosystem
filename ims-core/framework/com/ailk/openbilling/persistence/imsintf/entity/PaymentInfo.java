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
@XmlType(propOrder={"outer_cust_id","cust_id","outer_acct_id","acct_id","phone_id","pay_type","currency","reference_num","measure_id","paymentList"})
public class PaymentInfo implements IComplexEntity{


	@XmlElement(name="paymentList")
	private PaymentItemList paymentList;

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

	@XmlElement(name="pay_type")
	private Short pay_type;

	@XmlElement(name="currency")
	private Short currency;

	@XmlElement(name="reference_num")
	private String reference_num;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setPaymentList(PaymentItemList obj){
		this.paymentList = obj;
	}

	public PaymentItemList getPaymentList(){
		return paymentList;
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

	public void setPay_type(Short obj){
		this.pay_type = obj;
	}

	public Short getPay_type(){
		return pay_type;
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

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentInfo rhs=(PaymentInfo)rhs0;
		if(!ObjectUtils.equals(paymentList, rhs.paymentList)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(pay_type, rhs.pay_type)) return false;
		if(!ObjectUtils.equals(currency, rhs.currency)) return false;
		if(!ObjectUtils.equals(reference_num, rhs.reference_num)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentList)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(phone_id)
		.append(pay_type)
		.append(currency)
		.append(reference_num)
		.append(measure_id)
		.toHashCode();
	}


}