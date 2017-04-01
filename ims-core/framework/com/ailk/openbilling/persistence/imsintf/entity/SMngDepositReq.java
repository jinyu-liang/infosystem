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
@XmlType(propOrder={"outer_acct_id","acct_id","user_id","phone_id","resource_type","sDepositOper"})
public class SMngDepositReq implements IComplexEntity{


	@XmlElement(name="sDepositOper")
	private SDepositOper sDepositOper;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="resource_type")
	private Integer resource_type;

	public void setSDepositOper(SDepositOper obj){
		this.sDepositOper = obj;
	}

	public SDepositOper getSDepositOper(){
		return sDepositOper;
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

	public void setResource_type(Integer obj){
		this.resource_type = obj;
	}

	public Integer getResource_type(){
		return resource_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMngDepositReq rhs=(SMngDepositReq)rhs0;
		if(!ObjectUtils.equals(sDepositOper, rhs.sDepositOper)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(resource_type, rhs.resource_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sDepositOper)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(resource_type)
		.toHashCode();
	}


}