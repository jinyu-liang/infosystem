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
@XmlType(propOrder={"outer_cust_id","cust_id","outer_acct_id","acct_id","user_id","phone_id","outer_son_cust_id","son_cust_id","outer_son_acct_id","son_acct_id","son_user_id","son_phone_id"})
public class SAuthParentReq implements IComplexEntity{


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

	@XmlElement(name="outer_son_cust_id")
	private String outer_son_cust_id;

	@XmlElement(name="son_cust_id")
	private Long son_cust_id;

	@XmlElement(name="outer_son_acct_id")
	private String outer_son_acct_id;

	@XmlElement(name="son_acct_id")
	private Long son_acct_id;

	@XmlElement(name="son_user_id")
	private Long son_user_id;

	@XmlElement(name="son_phone_id")
	private String son_phone_id;

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

	public void setOuter_son_cust_id(String obj){
		this.outer_son_cust_id = obj;
	}

	public String getOuter_son_cust_id(){
		return outer_son_cust_id;
	}

	public void setSon_cust_id(Long obj){
		this.son_cust_id = obj;
	}

	public Long getSon_cust_id(){
		return son_cust_id;
	}

	public void setOuter_son_acct_id(String obj){
		this.outer_son_acct_id = obj;
	}

	public String getOuter_son_acct_id(){
		return outer_son_acct_id;
	}

	public void setSon_acct_id(Long obj){
		this.son_acct_id = obj;
	}

	public Long getSon_acct_id(){
		return son_acct_id;
	}

	public void setSon_user_id(Long obj){
		this.son_user_id = obj;
	}

	public Long getSon_user_id(){
		return son_user_id;
	}

	public void setSon_phone_id(String obj){
		this.son_phone_id = obj;
	}

	public String getSon_phone_id(){
		return son_phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAuthParentReq rhs=(SAuthParentReq)rhs0;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_son_cust_id, rhs.outer_son_cust_id)) return false;
		if(!ObjectUtils.equals(son_cust_id, rhs.son_cust_id)) return false;
		if(!ObjectUtils.equals(outer_son_acct_id, rhs.outer_son_acct_id)) return false;
		if(!ObjectUtils.equals(son_acct_id, rhs.son_acct_id)) return false;
		if(!ObjectUtils.equals(son_user_id, rhs.son_user_id)) return false;
		if(!ObjectUtils.equals(son_phone_id, rhs.son_phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(outer_son_cust_id)
		.append(son_cust_id)
		.append(outer_son_acct_id)
		.append(son_acct_id)
		.append(son_user_id)
		.append(son_phone_id)
		.toHashCode();
	}


}