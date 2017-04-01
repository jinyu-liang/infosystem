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
@XmlType(propOrder={"outer_cust_id","cust_id","outer_acct_id","acct_id","phone_id","so_nbr","valid_date","expire_date","oper_type","user_id","exemption_type","exemp_channel_id","billing_type"})
public class SSetEmptLimitReq implements IComplexEntity{


	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="so_nbr")
	private String so_nbr;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="exemption_type")
	private Integer exemption_type;

	@XmlElement(name="exemp_channel_id")
	private Integer exemp_channel_id;

	@XmlElement(name="billing_type")
	private Integer billing_type;

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
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

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setSo_nbr(String obj){
		this.so_nbr = obj;
	}

	public String getSo_nbr(){
		return so_nbr;
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

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setExemption_type(Integer obj){
		this.exemption_type = obj;
	}

	public Integer getExemption_type(){
		return exemption_type;
	}

	public void setExemp_channel_id(Integer obj){
		this.exemp_channel_id = obj;
	}

	public Integer getExemp_channel_id(){
		return exemp_channel_id;
	}

	public void setBilling_type(Integer obj){
		this.billing_type = obj;
	}

	public Integer getBilling_type(){
		return billing_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSetEmptLimitReq rhs=(SSetEmptLimitReq)rhs0;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(exemption_type, rhs.exemption_type)) return false;
		if(!ObjectUtils.equals(exemp_channel_id, rhs.exemp_channel_id)) return false;
		if(!ObjectUtils.equals(billing_type, rhs.billing_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(phone_id)
		.append(so_nbr)
		.append(valid_date)
		.append(expire_date)
		.append(oper_type)
		.append(user_id)
		.append(exemption_type)
		.append(exemp_channel_id)
		.append(billing_type)
		.toHashCode();
	}


}