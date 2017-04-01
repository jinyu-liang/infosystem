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
@XmlType(propOrder={"outer_acct_id","acct_id","user_id","phone_id","target_phone_id","query_type","recharge_channel","start_date","end_date","product_sequence_id"})
public class QueryWorkLogReq implements IComplexEntity{


	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="target_phone_id")
	private String target_phone_id;

	@XmlElement(name="query_type")
	private Short query_type;

	@XmlElement(name="recharge_channel")
	private Short recharge_channel;

	@XmlElement(name="start_date")
	private String start_date;

	@XmlElement(name="end_date")
	private String end_date;

	@XmlElement(name="product_sequence_id")
	private String product_sequence_id;

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

	public void setTarget_phone_id(String obj){
		this.target_phone_id = obj;
	}

	public String getTarget_phone_id(){
		return target_phone_id;
	}

	public void setQuery_type(Short obj){
		this.query_type = obj;
	}

	public Short getQuery_type(){
		return query_type;
	}

	public void setRecharge_channel(Short obj){
		this.recharge_channel = obj;
	}

	public Short getRecharge_channel(){
		return recharge_channel;
	}

	public void setStart_date(String obj){
		this.start_date = obj;
	}

	public String getStart_date(){
		return start_date;
	}

	public void setEnd_date(String obj){
		this.end_date = obj;
	}

	public String getEnd_date(){
		return end_date;
	}

	public void setProduct_sequence_id(String obj){
		this.product_sequence_id = obj;
	}

	public String getProduct_sequence_id(){
		return product_sequence_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QueryWorkLogReq rhs=(QueryWorkLogReq)rhs0;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(target_phone_id, rhs.target_phone_id)) return false;
		if(!ObjectUtils.equals(query_type, rhs.query_type)) return false;
		if(!ObjectUtils.equals(recharge_channel, rhs.recharge_channel)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(target_phone_id)
		.append(query_type)
		.append(recharge_channel)
		.append(start_date)
		.append(end_date)
		.append(product_sequence_id)
		.toHashCode();
	}


}