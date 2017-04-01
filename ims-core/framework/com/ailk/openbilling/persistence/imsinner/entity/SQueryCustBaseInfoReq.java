package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"cust_id","acct_id","first_name","customer_name","family_name","identity_type","identity","phone_id","user_id","address","main_offering_id","account_name","customer_type","start","limit","offer_name","object_type"})
public class SQueryCustBaseInfoReq implements IComplexEntity{


	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="first_name")
	private String first_name;

	@XmlElement(name="customer_name")
	private String customer_name;

	@XmlElement(name="family_name")
	private String family_name;

	@XmlElement(name="identity_type")
	private Integer identity_type;

	@XmlElement(name="identity")
	private String identity;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="address")
	private String address;

	@XmlElement(name="main_offering_id")
	private Integer main_offering_id;

	@XmlElement(name="account_name")
	private String account_name;

	@XmlElement(name="customer_type")
	private Integer customer_type;

	@XmlElement(name="start")
	private Integer start;

	@XmlElement(name="limit")
	private Integer limit;

	@XmlElement(name="offer_name")
	private String offer_name;

	@XmlElement(name="object_type")
	private String object_type;

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setFirst_name(String obj){
		this.first_name = obj;
	}

	public String getFirst_name(){
		return first_name;
	}

	public void setCustomer_name(String obj){
		this.customer_name = obj;
	}

	public String getCustomer_name(){
		return customer_name;
	}

	public void setFamily_name(String obj){
		this.family_name = obj;
	}

	public String getFamily_name(){
		return family_name;
	}

	public void setIdentity_type(Integer obj){
		this.identity_type = obj;
	}

	public Integer getIdentity_type(){
		return identity_type;
	}

	public void setIdentity(String obj){
		this.identity = obj;
	}

	public String getIdentity(){
		return identity;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setAddress(String obj){
		this.address = obj;
	}

	public String getAddress(){
		return address;
	}

	public void setMain_offering_id(Integer obj){
		this.main_offering_id = obj;
	}

	public Integer getMain_offering_id(){
		return main_offering_id;
	}

	public void setAccount_name(String obj){
		this.account_name = obj;
	}

	public String getAccount_name(){
		return account_name;
	}

	public void setCustomer_type(Integer obj){
		this.customer_type = obj;
	}

	public Integer getCustomer_type(){
		return customer_type;
	}

	public void setStart(Integer obj){
		this.start = obj;
	}

	public Integer getStart(){
		return start;
	}

	public void setLimit(Integer obj){
		this.limit = obj;
	}

	public Integer getLimit(){
		return limit;
	}

	public void setOffer_name(String obj){
		this.offer_name = obj;
	}

	public String getOffer_name(){
		return offer_name;
	}

	public void setObject_type(String obj){
		this.object_type = obj;
	}

	public String getObject_type(){
		return object_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryCustBaseInfoReq rhs=(SQueryCustBaseInfoReq)rhs0;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(first_name, rhs.first_name)) return false;
		if(!ObjectUtils.equals(customer_name, rhs.customer_name)) return false;
		if(!ObjectUtils.equals(family_name, rhs.family_name)) return false;
		if(!ObjectUtils.equals(identity_type, rhs.identity_type)) return false;
		if(!ObjectUtils.equals(identity, rhs.identity)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(address, rhs.address)) return false;
		if(!ObjectUtils.equals(main_offering_id, rhs.main_offering_id)) return false;
		if(!ObjectUtils.equals(account_name, rhs.account_name)) return false;
		if(!ObjectUtils.equals(customer_type, rhs.customer_type)) return false;
		if(!ObjectUtils.equals(start, rhs.start)) return false;
		if(!ObjectUtils.equals(limit, rhs.limit)) return false;
		if(!ObjectUtils.equals(offer_name, rhs.offer_name)) return false;
		if(!ObjectUtils.equals(object_type, rhs.object_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cust_id)
		.append(acct_id)
		.append(first_name)
		.append(customer_name)
		.append(family_name)
		.append(identity_type)
		.append(identity)
		.append(phone_id)
		.append(user_id)
		.append(address)
		.append(main_offering_id)
		.append(account_name)
		.append(customer_type)
		.append(start)
		.append(limit)
		.append(offer_name)
		.append(object_type)
		.toHashCode();
	}


}