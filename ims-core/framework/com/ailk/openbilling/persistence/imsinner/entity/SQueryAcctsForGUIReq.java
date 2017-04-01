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
@XmlType(propOrder={"acct_id","cust_id","identity","identity_type","acct_name","first_name","middle_name","family_name","offer_id","phone_id"})
public class SQueryAcctsForGUIReq implements IComplexEntity{


	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="identity")
	private String identity;

	@XmlElement(name="identity_type")
	private Integer identity_type;

	@XmlElement(name="acct_name")
	private String acct_name;

	@XmlElement(name="first_name")
	private String first_name;

	@XmlElement(name="middle_name")
	private String middle_name;

	@XmlElement(name="family_name")
	private String family_name;

	@XmlElement(name="offer_id")
	private Integer offer_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setIdentity(String obj){
		this.identity = obj;
	}

	public String getIdentity(){
		return identity;
	}

	public void setIdentity_type(Integer obj){
		this.identity_type = obj;
	}

	public Integer getIdentity_type(){
		return identity_type;
	}

	public void setAcct_name(String obj){
		this.acct_name = obj;
	}

	public String getAcct_name(){
		return acct_name;
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

	public void setOffer_id(Integer obj){
		this.offer_id = obj;
	}

	public Integer getOffer_id(){
		return offer_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryAcctsForGUIReq rhs=(SQueryAcctsForGUIReq)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(identity, rhs.identity)) return false;
		if(!ObjectUtils.equals(identity_type, rhs.identity_type)) return false;
		if(!ObjectUtils.equals(acct_name, rhs.acct_name)) return false;
		if(!ObjectUtils.equals(first_name, rhs.first_name)) return false;
		if(!ObjectUtils.equals(middle_name, rhs.middle_name)) return false;
		if(!ObjectUtils.equals(family_name, rhs.family_name)) return false;
		if(!ObjectUtils.equals(offer_id, rhs.offer_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(cust_id)
		.append(identity)
		.append(identity_type)
		.append(acct_name)
		.append(first_name)
		.append(middle_name)
		.append(family_name)
		.append(offer_id)
		.append(phone_id)
		.toHashCode();
	}


}