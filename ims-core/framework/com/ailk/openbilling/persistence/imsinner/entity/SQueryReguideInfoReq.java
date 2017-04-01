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
@XmlType(propOrder={"cust_id","acct_id","user_id","phone_id","pay_flag","reguide_type"})
public class SQueryReguideInfoReq implements IComplexEntity{


	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="pay_flag")
	private Integer pay_flag;

	@XmlElement(name="reguide_type")
	private Integer reguide_type;

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

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setPay_flag(Integer obj){
		this.pay_flag = obj;
	}

	public Integer getPay_flag(){
		return pay_flag;
	}

	public void setReguide_type(Integer obj){
		this.reguide_type = obj;
	}

	public Integer getReguide_type(){
		return reguide_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryReguideInfoReq rhs=(SQueryReguideInfoReq)rhs0;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(pay_flag, rhs.pay_flag)) return false;
		if(!ObjectUtils.equals(reguide_type, rhs.reguide_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cust_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(pay_flag)
		.append(reguide_type)
		.toHashCode();
	}


}