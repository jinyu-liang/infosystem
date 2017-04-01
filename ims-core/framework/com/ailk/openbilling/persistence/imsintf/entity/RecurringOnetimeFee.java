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
@XmlType(propOrder={"product_sequence_id","product_offer_id","product_name","charge_date","recurring_fee","fee_item_description","amount"})
public class RecurringOnetimeFee implements IComplexEntity{


	@XmlElement(name="product_sequence_id")
	private Long product_sequence_id;

	@XmlElement(name="product_offer_id")
	private Long product_offer_id;

	@XmlElement(name="product_name")
	private String product_name;

	@XmlElement(name="charge_date")
	private Date charge_date;

	@XmlElement(name="recurring_fee")
	private Long recurring_fee;

	@XmlElement(name="fee_item_description")
	private String fee_item_description;

	@XmlElement(name="amount")
	private Long amount;

	public void setProduct_sequence_id(Long obj){
		this.product_sequence_id = obj;
	}

	public Long getProduct_sequence_id(){
		return product_sequence_id;
	}

	public void setProduct_offer_id(Long obj){
		this.product_offer_id = obj;
	}

	public Long getProduct_offer_id(){
		return product_offer_id;
	}

	public void setProduct_name(String obj){
		this.product_name = obj;
	}

	public String getProduct_name(){
		return product_name;
	}

	public void setCharge_date(Date obj){
		this.charge_date = obj;
	}

	public Date getCharge_date(){
		return charge_date;
	}

	public void setRecurring_fee(Long obj){
		this.recurring_fee = obj;
	}

	public Long getRecurring_fee(){
		return recurring_fee;
	}

	public void setFee_item_description(String obj){
		this.fee_item_description = obj;
	}

	public String getFee_item_description(){
		return fee_item_description;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RecurringOnetimeFee rhs=(RecurringOnetimeFee)rhs0;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		if(!ObjectUtils.equals(product_offer_id, rhs.product_offer_id)) return false;
		if(!ObjectUtils.equals(product_name, rhs.product_name)) return false;
		if(!ObjectUtils.equals(charge_date, rhs.charge_date)) return false;
		if(!ObjectUtils.equals(recurring_fee, rhs.recurring_fee)) return false;
		if(!ObjectUtils.equals(fee_item_description, rhs.fee_item_description)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_sequence_id)
		.append(product_offer_id)
		.append(product_name)
		.append(charge_date)
		.append(recurring_fee)
		.append(fee_item_description)
		.append(amount)
		.toHashCode();
	}


}