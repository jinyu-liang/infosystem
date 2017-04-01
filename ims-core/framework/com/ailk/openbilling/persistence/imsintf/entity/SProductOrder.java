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
@XmlType(propOrder={"product_id","offer_id","so_id","product_type","parent_serv_product","outer_cust_id","cust_id","outer_acct_id","acct_id","user_id","phone_id","outer_billable_acct_id","billable_acct_id","billing_type","prod_state","create_date","valid_type","valid_date","expire_date","nextcycle_start_date","bill_cycle","reguid_price_param","param_list","price_description","prepaid_recurring_fee","last_bill_date"})
public class SProductOrder implements IComplexEntity{


	@XmlElement(name="bill_cycle")
	private SBillCycle bill_cycle;

	@XmlElement(name="reguid_price_param")
	private ExtendParamList reguid_price_param;

	@XmlElement(name="param_list")
	private SProductParamList param_list;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="offer_id")
	private Long offer_id;

	@XmlElement(name="so_id")
	private String so_id;

	@XmlElement(name="product_type")
	private Integer product_type;

	@XmlElement(name="parent_serv_product")
	private Long parent_serv_product;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="outer_billable_acct_id")
	private String outer_billable_acct_id;

	@XmlElement(name="billable_acct_id")
	private Long billable_acct_id;

	@XmlElement(name="billing_type")
	private Short billing_type;

	@XmlElement(name="prod_state")
	private Short prod_state;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="valid_type")
	private Short valid_type;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="nextcycle_start_date")
	private String nextcycle_start_date;

	@XmlElement(name="price_description")
	private String price_description;

	@XmlElement(name="prepaid_recurring_fee")
	private Long prepaid_recurring_fee;

	@XmlElement(name="last_bill_date")
	private String last_bill_date;

	public void setBill_cycle(SBillCycle obj){
		this.bill_cycle = obj;
	}

	public SBillCycle getBill_cycle(){
		return bill_cycle;
	}

	public void setReguid_price_param(ExtendParamList obj){
		this.reguid_price_param = obj;
	}

	public ExtendParamList getReguid_price_param(){
		return reguid_price_param;
	}

	public void setParam_list(SProductParamList obj){
		this.param_list = obj;
	}

	public SProductParamList getParam_list(){
		return param_list;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setOffer_id(Long obj){
		this.offer_id = obj;
	}

	public Long getOffer_id(){
		return offer_id;
	}

	public void setSo_id(String obj){
		this.so_id = obj;
	}

	public String getSo_id(){
		return so_id;
	}

	public void setProduct_type(Integer obj){
		this.product_type = obj;
	}

	public Integer getProduct_type(){
		return product_type;
	}

	public void setParent_serv_product(Long obj){
		this.parent_serv_product = obj;
	}

	public Long getParent_serv_product(){
		return parent_serv_product;
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

	public void setOuter_billable_acct_id(String obj){
		this.outer_billable_acct_id = obj;
	}

	public String getOuter_billable_acct_id(){
		return outer_billable_acct_id;
	}

	public void setBillable_acct_id(Long obj){
		this.billable_acct_id = obj;
	}

	public Long getBillable_acct_id(){
		return billable_acct_id;
	}

	public void setBilling_type(Short obj){
		this.billing_type = obj;
	}

	public Short getBilling_type(){
		return billing_type;
	}

	public void setProd_state(Short obj){
		this.prod_state = obj;
	}

	public Short getProd_state(){
		return prod_state;
	}

	public void setCreate_date(String obj){
		this.create_date = obj;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setValid_type(Short obj){
		this.valid_type = obj;
	}

	public Short getValid_type(){
		return valid_type;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setNextcycle_start_date(String obj){
		this.nextcycle_start_date = obj;
	}

	public String getNextcycle_start_date(){
		return nextcycle_start_date;
	}

	public void setPrice_description(String obj){
		this.price_description = obj;
	}

	public String getPrice_description(){
		return price_description;
	}

	public void setPrepaid_recurring_fee(Long obj){
		this.prepaid_recurring_fee = obj;
	}

	public Long getPrepaid_recurring_fee(){
		return prepaid_recurring_fee;
	}

	public void setLast_bill_date(String obj){
		this.last_bill_date = obj;
	}

	public String getLast_bill_date(){
		return last_bill_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductOrder rhs=(SProductOrder)rhs0;
		if(!ObjectUtils.equals(bill_cycle, rhs.bill_cycle)) return false;
		if(!ObjectUtils.equals(reguid_price_param, rhs.reguid_price_param)) return false;
		if(!ObjectUtils.equals(param_list, rhs.param_list)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(offer_id, rhs.offer_id)) return false;
		if(!ObjectUtils.equals(so_id, rhs.so_id)) return false;
		if(!ObjectUtils.equals(product_type, rhs.product_type)) return false;
		if(!ObjectUtils.equals(parent_serv_product, rhs.parent_serv_product)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_billable_acct_id, rhs.outer_billable_acct_id)) return false;
		if(!ObjectUtils.equals(billable_acct_id, rhs.billable_acct_id)) return false;
		if(!ObjectUtils.equals(billing_type, rhs.billing_type)) return false;
		if(!ObjectUtils.equals(prod_state, rhs.prod_state)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(valid_type, rhs.valid_type)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(nextcycle_start_date, rhs.nextcycle_start_date)) return false;
		if(!ObjectUtils.equals(price_description, rhs.price_description)) return false;
		if(!ObjectUtils.equals(prepaid_recurring_fee, rhs.prepaid_recurring_fee)) return false;
		if(!ObjectUtils.equals(last_bill_date, rhs.last_bill_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bill_cycle)
		.append(reguid_price_param)
		.append(param_list)
		.append(product_id)
		.append(offer_id)
		.append(so_id)
		.append(product_type)
		.append(parent_serv_product)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(outer_billable_acct_id)
		.append(billable_acct_id)
		.append(billing_type)
		.append(prod_state)
		.append(create_date)
		.append(valid_type)
		.append(valid_date)
		.append(expire_date)
		.append(nextcycle_start_date)
		.append(price_description)
		.append(prepaid_recurring_fee)
		.append(last_bill_date)
		.toHashCode();
	}


}