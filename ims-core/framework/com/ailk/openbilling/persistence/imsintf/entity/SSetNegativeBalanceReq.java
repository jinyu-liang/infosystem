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
@XmlType(propOrder={"phone_id","negative_balance","notify_flag","valid_date","expire_date","user_id","outer_acct_id","acct_id","owner_type","modify_type"})
public class SSetNegativeBalanceReq implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="negative_balance")
	private Long negative_balance;

	@XmlElement(name="notify_flag")
	private Short notify_flag;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="owner_type")
	private Integer owner_type;

	@XmlElement(name="modify_type")
	private Short modify_type;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setNegative_balance(Long obj){
		this.negative_balance = obj;
	}

	public Long getNegative_balance(){
		return negative_balance;
	}

	public void setNotify_flag(Short obj){
		this.notify_flag = obj;
	}

	public Short getNotify_flag(){
		return notify_flag;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
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

	public void setOwner_type(Integer obj){
		this.owner_type = obj;
	}

	public Integer getOwner_type(){
		return owner_type;
	}

	public void setModify_type(Short obj){
		this.modify_type = obj;
	}

	public Short getModify_type(){
		return modify_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSetNegativeBalanceReq rhs=(SSetNegativeBalanceReq)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(negative_balance, rhs.negative_balance)) return false;
		if(!ObjectUtils.equals(notify_flag, rhs.notify_flag)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(owner_type, rhs.owner_type)) return false;
		if(!ObjectUtils.equals(modify_type, rhs.modify_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(negative_balance)
		.append(notify_flag)
		.append(valid_date)
		.append(expire_date)
		.append(user_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(owner_type)
		.append(modify_type)
		.toHashCode();
	}


}