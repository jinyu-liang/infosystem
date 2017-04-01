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
@XmlType(propOrder={"user_id","phone_id","amount","recharge_method","recharge_partner_id","recharge_service_id","serial_no","batch_no","remark","owner_type","outer_acct_id","acct_id","sBalance","_etopup_session_id","card_type"})
public class STopUpInfo implements IComplexEntity{


	@XmlElement(name="sBalance")
	private SBalance sBalance;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="recharge_method")
	private Short recharge_method;

	@XmlElement(name="recharge_partner_id")
	private Integer recharge_partner_id;

	@XmlElement(name="recharge_service_id")
	private Integer recharge_service_id;

	@XmlElement(name="serial_no")
	private String serial_no;

	@XmlElement(name="batch_no")
	private String batch_no;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="owner_type")
	private Integer owner_type;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="_etopup_session_id")
	private String _etopup_session_id;

	@XmlElement(name="card_type")
	private Short card_type;

	public void setSBalance(SBalance obj){
		this.sBalance = obj;
	}

	public SBalance getSBalance(){
		return sBalance;
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

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setRecharge_method(Short obj){
		this.recharge_method = obj;
	}

	public Short getRecharge_method(){
		return recharge_method;
	}

	public void setRecharge_partner_id(Integer obj){
		this.recharge_partner_id = obj;
	}

	public Integer getRecharge_partner_id(){
		return recharge_partner_id;
	}

	public void setRecharge_service_id(Integer obj){
		this.recharge_service_id = obj;
	}

	public Integer getRecharge_service_id(){
		return recharge_service_id;
	}

	public void setSerial_no(String obj){
		this.serial_no = obj;
	}

	public String getSerial_no(){
		return serial_no;
	}

	public void setBatch_no(String obj){
		this.batch_no = obj;
	}

	public String getBatch_no(){
		return batch_no;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setOwner_type(Integer obj){
		this.owner_type = obj;
	}

	public Integer getOwner_type(){
		return owner_type;
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

	public void setEtopup_session_id(String obj){
		this._etopup_session_id = obj;
	}

	public String getEtopup_session_id(){
		return _etopup_session_id;
	}

	public void setCard_type(Short obj){
		this.card_type = obj;
	}

	public Short getCard_type(){
		return card_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STopUpInfo rhs=(STopUpInfo)rhs0;
		if(!ObjectUtils.equals(sBalance, rhs.sBalance)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(recharge_method, rhs.recharge_method)) return false;
		if(!ObjectUtils.equals(recharge_partner_id, rhs.recharge_partner_id)) return false;
		if(!ObjectUtils.equals(recharge_service_id, rhs.recharge_service_id)) return false;
		if(!ObjectUtils.equals(serial_no, rhs.serial_no)) return false;
		if(!ObjectUtils.equals(batch_no, rhs.batch_no)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(owner_type, rhs.owner_type)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(_etopup_session_id, rhs._etopup_session_id)) return false;
		if(!ObjectUtils.equals(card_type, rhs.card_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBalance)
		.append(user_id)
		.append(phone_id)
		.append(amount)
		.append(recharge_method)
		.append(recharge_partner_id)
		.append(recharge_service_id)
		.append(serial_no)
		.append(batch_no)
		.append(remark)
		.append(owner_type)
		.append(outer_acct_id)
		.append(acct_id)
		.append(_etopup_session_id)
		.append(card_type)
		.toHashCode();
	}


}