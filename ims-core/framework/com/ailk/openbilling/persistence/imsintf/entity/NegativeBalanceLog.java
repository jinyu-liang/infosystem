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
@XmlType(propOrder={"acct_id","phone_id","negative_balance","valid_date","expire_date","negative_flag","operation_date","so_mode","source_system","transaction_id","status","_fail_reason","negative_balance_flag","old_negative_balance","new_negative_balance"})
public class NegativeBalanceLog implements IComplexEntity{


	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="negative_balance")
	private Long negative_balance;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="negative_flag")
	private Short negative_flag;

	@XmlElement(name="operation_date")
	private String operation_date;

	@XmlElement(name="so_mode")
	private Short so_mode;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="transaction_id")
	private String transaction_id;

	@XmlElement(name="status")
	private Short status;

	@XmlElement(name="_fail_reason")
	private String _fail_reason;

	@XmlElement(name="negative_balance_flag")
	private Short negative_balance_flag;

	@XmlElement(name="old_negative_balance")
	private Long old_negative_balance;

	@XmlElement(name="new_negative_balance")
	private Long new_negative_balance;

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

	public void setNegative_balance(Long obj){
		this.negative_balance = obj;
	}

	public Long getNegative_balance(){
		return negative_balance;
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

	public void setNegative_flag(Short obj){
		this.negative_flag = obj;
	}

	public Short getNegative_flag(){
		return negative_flag;
	}

	public void setOperation_date(String obj){
		this.operation_date = obj;
	}

	public String getOperation_date(){
		return operation_date;
	}

	public void setSo_mode(Short obj){
		this.so_mode = obj;
	}

	public Short getSo_mode(){
		return so_mode;
	}

	public void setSource_system(String obj){
		this.source_system = obj;
	}

	public String getSource_system(){
		return source_system;
	}

	public void setTransaction_id(String obj){
		this.transaction_id = obj;
	}

	public String getTransaction_id(){
		return transaction_id;
	}

	public void setStatus(Short obj){
		this.status = obj;
	}

	public Short getStatus(){
		return status;
	}

	public void setFail_reason(String obj){
		this._fail_reason = obj;
	}

	public String getFail_reason(){
		return _fail_reason;
	}

	public void setNegative_balance_flag(Short obj){
		this.negative_balance_flag = obj;
	}

	public Short getNegative_balance_flag(){
		return negative_balance_flag;
	}

	public void setOld_negative_balance(Long obj){
		this.old_negative_balance = obj;
	}

	public Long getOld_negative_balance(){
		return old_negative_balance;
	}

	public void setNew_negative_balance(Long obj){
		this.new_negative_balance = obj;
	}

	public Long getNew_negative_balance(){
		return new_negative_balance;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NegativeBalanceLog rhs=(NegativeBalanceLog)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(negative_balance, rhs.negative_balance)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(negative_flag, rhs.negative_flag)) return false;
		if(!ObjectUtils.equals(operation_date, rhs.operation_date)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(transaction_id, rhs.transaction_id)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(_fail_reason, rhs._fail_reason)) return false;
		if(!ObjectUtils.equals(negative_balance_flag, rhs.negative_balance_flag)) return false;
		if(!ObjectUtils.equals(old_negative_balance, rhs.old_negative_balance)) return false;
		if(!ObjectUtils.equals(new_negative_balance, rhs.new_negative_balance)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(phone_id)
		.append(negative_balance)
		.append(valid_date)
		.append(expire_date)
		.append(negative_flag)
		.append(operation_date)
		.append(so_mode)
		.append(source_system)
		.append(transaction_id)
		.append(status)
		.append(_fail_reason)
		.append(negative_balance_flag)
		.append(old_negative_balance)
		.append(new_negative_balance)
		.toHashCode();
	}


}