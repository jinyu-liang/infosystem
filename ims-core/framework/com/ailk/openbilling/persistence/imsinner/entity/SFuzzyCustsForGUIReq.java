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
@XmlType(propOrder={"common_key","gui_cust_id","gui_acct_id","first_name","middle_name","family_name","identity_type","identity","gui_phone_id","gui_user_id","address","offering_id","account_name","customer_type"})
public class SFuzzyCustsForGUIReq implements IComplexEntity{


	@XmlElement(name="common_key")
	private String common_key;

	@XmlElement(name="gui_cust_id")
	private Long gui_cust_id;

	@XmlElement(name="gui_acct_id")
	private Long gui_acct_id;

	@XmlElement(name="first_name")
	private String first_name;

	@XmlElement(name="middle_name")
	private String middle_name;

	@XmlElement(name="family_name")
	private String family_name;

	@XmlElement(name="identity_type")
	private Integer identity_type;

	@XmlElement(name="identity")
	private String identity;

	@XmlElement(name="gui_phone_id")
	private String gui_phone_id;

	@XmlElement(name="gui_user_id")
	private Long gui_user_id;

	@XmlElement(name="address")
	private String address;

	@XmlElement(name="offering_id")
	private Integer offering_id;

	@XmlElement(name="account_name")
	private String account_name;

	@XmlElement(name="customer_type")
	private Integer customer_type;

	public void setCommon_key(String obj){
		this.common_key = obj;
	}

	public String getCommon_key(){
		return common_key;
	}

	public void setGui_cust_id(Long obj){
		this.gui_cust_id = obj;
	}

	public Long getGui_cust_id(){
		return gui_cust_id;
	}

	public void setGui_acct_id(Long obj){
		this.gui_acct_id = obj;
	}

	public Long getGui_acct_id(){
		return gui_acct_id;
	}

	public void setFirst_name(String obj){
		this.first_name = obj;
	}

	public String getFirst_name(){
		return first_name;
	}

	public void setMiddle_name(String obj){
		this.middle_name = obj;
	}

	public String getMiddle_name(){
		return middle_name;
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

	public void setGui_phone_id(String obj){
		this.gui_phone_id = obj;
	}

	public String getGui_phone_id(){
		return gui_phone_id;
	}

	public void setGui_user_id(Long obj){
		this.gui_user_id = obj;
	}

	public Long getGui_user_id(){
		return gui_user_id;
	}

	public void setAddress(String obj){
		this.address = obj;
	}

	public String getAddress(){
		return address;
	}

	public void setOffering_id(Integer obj){
		this.offering_id = obj;
	}

	public Integer getOffering_id(){
		return offering_id;
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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFuzzyCustsForGUIReq rhs=(SFuzzyCustsForGUIReq)rhs0;
		if(!ObjectUtils.equals(common_key, rhs.common_key)) return false;
		if(!ObjectUtils.equals(gui_cust_id, rhs.gui_cust_id)) return false;
		if(!ObjectUtils.equals(gui_acct_id, rhs.gui_acct_id)) return false;
		if(!ObjectUtils.equals(first_name, rhs.first_name)) return false;
		if(!ObjectUtils.equals(middle_name, rhs.middle_name)) return false;
		if(!ObjectUtils.equals(family_name, rhs.family_name)) return false;
		if(!ObjectUtils.equals(identity_type, rhs.identity_type)) return false;
		if(!ObjectUtils.equals(identity, rhs.identity)) return false;
		if(!ObjectUtils.equals(gui_phone_id, rhs.gui_phone_id)) return false;
		if(!ObjectUtils.equals(gui_user_id, rhs.gui_user_id)) return false;
		if(!ObjectUtils.equals(address, rhs.address)) return false;
		if(!ObjectUtils.equals(offering_id, rhs.offering_id)) return false;
		if(!ObjectUtils.equals(account_name, rhs.account_name)) return false;
		if(!ObjectUtils.equals(customer_type, rhs.customer_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(common_key)
		.append(gui_cust_id)
		.append(gui_acct_id)
		.append(first_name)
		.append(middle_name)
		.append(family_name)
		.append(identity_type)
		.append(identity)
		.append(gui_phone_id)
		.append(gui_user_id)
		.append(address)
		.append(offering_id)
		.append(account_name)
		.append(customer_type)
		.toHashCode();
	}


}