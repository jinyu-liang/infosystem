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
@XmlType(propOrder={"outer_acct_id","acct_id","phone_id","service_type","amount","prepaid_phone","user_id","owner_type","bill_type","_etopup_session_id"})
public class SGsmTopUpReq implements IComplexEntity{


	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="service_type")
	private Short service_type;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="prepaid_phone")
	private String prepaid_phone;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="owner_type")
	private Integer owner_type;

	@XmlElement(name="bill_type")
	private Integer bill_type;

	@XmlElement(name="_etopup_session_id")
	private String _etopup_session_id;

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

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setService_type(Short obj){
		this.service_type = obj;
	}

	public Short getService_type(){
		return service_type;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setPrepaid_phone(String obj){
		this.prepaid_phone = obj;
	}

	public String getPrepaid_phone(){
		return prepaid_phone;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setOwner_type(Integer obj){
		this.owner_type = obj;
	}

	public Integer getOwner_type(){
		return owner_type;
	}

	public void setBill_type(Integer obj){
		this.bill_type = obj;
	}

	public Integer getBill_type(){
		return bill_type;
	}

	public void setEtopup_session_id(String obj){
		this._etopup_session_id = obj;
	}

	public String getEtopup_session_id(){
		return _etopup_session_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGsmTopUpReq rhs=(SGsmTopUpReq)rhs0;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(service_type, rhs.service_type)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(prepaid_phone, rhs.prepaid_phone)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(owner_type, rhs.owner_type)) return false;
		if(!ObjectUtils.equals(bill_type, rhs.bill_type)) return false;
		if(!ObjectUtils.equals(_etopup_session_id, rhs._etopup_session_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_acct_id)
		.append(acct_id)
		.append(phone_id)
		.append(service_type)
		.append(amount)
		.append(prepaid_phone)
		.append(user_id)
		.append(owner_type)
		.append(bill_type)
		.append(_etopup_session_id)
		.toHashCode();
	}


}