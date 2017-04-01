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
@XmlType(propOrder={"cust_id","acct_id","user_id","identity","identity_type","main_promotion"})
public class SQueryUsersReq implements IComplexEntity{


	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="identity")
	private String identity;

	@XmlElement(name="identity_type")
	private Integer identity_type;

	@XmlElement(name="main_promotion")
	private Integer main_promotion;

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

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
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

	public void setMain_promotion(Integer obj){
		this.main_promotion = obj;
	}

	public Integer getMain_promotion(){
		return main_promotion;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryUsersReq rhs=(SQueryUsersReq)rhs0;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(identity, rhs.identity)) return false;
		if(!ObjectUtils.equals(identity_type, rhs.identity_type)) return false;
		if(!ObjectUtils.equals(main_promotion, rhs.main_promotion)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cust_id)
		.append(acct_id)
		.append(user_id)
		.append(identity)
		.append(identity_type)
		.append(main_promotion)
		.toHashCode();
	}


}