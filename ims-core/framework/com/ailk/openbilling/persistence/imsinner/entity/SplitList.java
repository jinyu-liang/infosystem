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
@XmlType(propOrder={"product_id","pay_acct_id","priority","valid_date","expire_date","part_value","part_type","acct_id","user_id"})
public class SplitList implements IComplexEntity{


	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="pay_acct_id")
	private Long pay_acct_id;

	@XmlElement(name="priority")
	private Short priority;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="part_value")
	private Long part_value;

	@XmlElement(name="part_type")
	private Long part_type;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setPay_acct_id(Long obj){
		this.pay_acct_id = obj;
	}

	public Long getPay_acct_id(){
		return pay_acct_id;
	}

	public void setPriority(Short obj){
		this.priority = obj;
	}

	public Short getPriority(){
		return priority;
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

	public void setPart_value(Long obj){
		this.part_value = obj;
	}

	public Long getPart_value(){
		return part_value;
	}

	public void setPart_type(Long obj){
		this.part_type = obj;
	}

	public Long getPart_type(){
		return part_type;
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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SplitList rhs=(SplitList)rhs0;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(pay_acct_id, rhs.pay_acct_id)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(part_value, rhs.part_value)) return false;
		if(!ObjectUtils.equals(part_type, rhs.part_type)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_id)
		.append(pay_acct_id)
		.append(priority)
		.append(valid_date)
		.append(expire_date)
		.append(part_value)
		.append(part_type)
		.append(acct_id)
		.append(user_id)
		.toHashCode();
	}


}