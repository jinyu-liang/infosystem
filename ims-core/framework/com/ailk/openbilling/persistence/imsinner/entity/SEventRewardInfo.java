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
@XmlType(propOrder={"cust_id","acct_id","user_id","billing_type"})
public class SEventRewardInfo implements IComplexEntity{


	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="billing_type")
	private Integer billing_type;

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

	public void setBilling_type(Integer obj){
		this.billing_type = obj;
	}

	public Integer getBilling_type(){
		return billing_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SEventRewardInfo rhs=(SEventRewardInfo)rhs0;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(billing_type, rhs.billing_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cust_id)
		.append(acct_id)
		.append(user_id)
		.append(billing_type)
		.toHashCode();
	}


}