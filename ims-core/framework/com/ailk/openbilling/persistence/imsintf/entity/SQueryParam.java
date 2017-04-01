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
@XmlType(propOrder={"outer_acct_id","acct_id","user_id","phone_id","product_id","invoice_no","start_date","end_date","owner_type","query_level","new_main_promotion","busi_spec_id","channel","cycle_flag"})
public class SQueryParam implements IComplexEntity{


	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="invoice_no")
	private String invoice_no;

	@XmlElement(name="start_date")
	private String start_date;

	@XmlElement(name="end_date")
	private String end_date;

	@XmlElement(name="owner_type")
	private Integer owner_type;

	@XmlElement(name="query_level")
	private Long query_level;

	@XmlElement(name="new_main_promotion")
	private Long new_main_promotion;

	@XmlElement(name="busi_spec_id")
	private Long busi_spec_id;

	@XmlElement(name="channel")
	private String channel;

	@XmlElement(name="cycle_flag")
	private Integer cycle_flag;

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

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setInvoice_no(String obj){
		this.invoice_no = obj;
	}

	public String getInvoice_no(){
		return invoice_no;
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

	public void setOwner_type(Integer obj){
		this.owner_type = obj;
	}

	public Integer getOwner_type(){
		return owner_type;
	}

	public void setQuery_level(Long obj){
		this.query_level = obj;
	}

	public Long getQuery_level(){
		return query_level;
	}

	public void setNew_main_promotion(Long obj){
		this.new_main_promotion = obj;
	}

	public Long getNew_main_promotion(){
		return new_main_promotion;
	}

	public void setBusi_spec_id(Long obj){
		this.busi_spec_id = obj;
	}

	public Long getBusi_spec_id(){
		return busi_spec_id;
	}

	public void setChannel(String obj){
		this.channel = obj;
	}

	public String getChannel(){
		return channel;
	}

	public void setCycle_flag(Integer obj){
		this.cycle_flag = obj;
	}

	public Integer getCycle_flag(){
		return cycle_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryParam rhs=(SQueryParam)rhs0;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(invoice_no, rhs.invoice_no)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(owner_type, rhs.owner_type)) return false;
		if(!ObjectUtils.equals(query_level, rhs.query_level)) return false;
		if(!ObjectUtils.equals(new_main_promotion, rhs.new_main_promotion)) return false;
		if(!ObjectUtils.equals(busi_spec_id, rhs.busi_spec_id)) return false;
		if(!ObjectUtils.equals(channel, rhs.channel)) return false;
		if(!ObjectUtils.equals(cycle_flag, rhs.cycle_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(product_id)
		.append(invoice_no)
		.append(start_date)
		.append(end_date)
		.append(owner_type)
		.append(query_level)
		.append(new_main_promotion)
		.append(busi_spec_id)
		.append(channel)
		.append(cycle_flag)
		.toHashCode();
	}


}