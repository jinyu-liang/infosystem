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
@XmlType(propOrder={"user_id","phone_id","change_type","outer_old_acct_id","old_acct_id","outer_billable_acct_id","billable_acct_id","convert_flag","last_term_flag","outer_new_acct_id","new_acct_id","reset_flag","change_promotion_flag","prod_list","days","user_segment","user_valid_date","accumulation_reset_flag"})
public class SChangeOwnerReq implements IComplexEntity{


	@XmlElement(name="prod_list")
	private SProductOrderOperList prod_list;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="change_type")
	private Short change_type;

	@XmlElement(name="outer_old_acct_id")
	private String outer_old_acct_id;

	@XmlElement(name="old_acct_id")
	private Long old_acct_id;

	@XmlElement(name="outer_billable_acct_id")
	private String outer_billable_acct_id;

	@XmlElement(name="billable_acct_id")
	private Long billable_acct_id;

	@XmlElement(name="convert_flag")
	private Short convert_flag;

	@XmlElement(name="last_term_flag")
	private Short last_term_flag;

	@XmlElement(name="outer_new_acct_id")
	private String outer_new_acct_id;

	@XmlElement(name="new_acct_id")
	private Long new_acct_id;

	@XmlElement(name="reset_flag")
	private Short reset_flag;

	@XmlElement(name="change_promotion_flag")
	private Short change_promotion_flag;

	@XmlElement(name="days")
	private Long days;

	@XmlElement(name="user_segment")
	private Short user_segment;

	@XmlElement(name="user_valid_date")
	private String user_valid_date;

	@XmlElement(name="accumulation_reset_flag")
	private Short accumulation_reset_flag;

	public void setProd_list(SProductOrderOperList obj){
		this.prod_list = obj;
	}

	public SProductOrderOperList getProd_list(){
		return prod_list;
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

	public void setChange_type(Short obj){
		this.change_type = obj;
	}

	public Short getChange_type(){
		return change_type;
	}

	public void setOuter_old_acct_id(String obj){
		this.outer_old_acct_id = obj;
	}

	public String getOuter_old_acct_id(){
		return outer_old_acct_id;
	}

	public void setOld_acct_id(Long obj){
		this.old_acct_id = obj;
	}

	public Long getOld_acct_id(){
		return old_acct_id;
	}

	public void setOuter_billable_acct_id(String obj){
		this.outer_billable_acct_id = obj;
	}

	public String getOuter_billable_acct_id(){
		return outer_billable_acct_id;
	}

	public void setBillable_acct_id(Long obj){
		this.billable_acct_id = obj;
	}

	public Long getBillable_acct_id(){
		return billable_acct_id;
	}

	public void setConvert_flag(Short obj){
		this.convert_flag = obj;
	}

	public Short getConvert_flag(){
		return convert_flag;
	}

	public void setLast_term_flag(Short obj){
		this.last_term_flag = obj;
	}

	public Short getLast_term_flag(){
		return last_term_flag;
	}

	public void setOuter_new_acct_id(String obj){
		this.outer_new_acct_id = obj;
	}

	public String getOuter_new_acct_id(){
		return outer_new_acct_id;
	}

	public void setNew_acct_id(Long obj){
		this.new_acct_id = obj;
	}

	public Long getNew_acct_id(){
		return new_acct_id;
	}

	public void setReset_flag(Short obj){
		this.reset_flag = obj;
	}

	public Short getReset_flag(){
		return reset_flag;
	}

	public void setChange_promotion_flag(Short obj){
		this.change_promotion_flag = obj;
	}

	public Short getChange_promotion_flag(){
		return change_promotion_flag;
	}

	public void setDays(Long obj){
		this.days = obj;
	}

	public Long getDays(){
		return days;
	}

	public void setUser_segment(Short obj){
		this.user_segment = obj;
	}

	public Short getUser_segment(){
		return user_segment;
	}

	public void setUser_valid_date(String obj){
		this.user_valid_date = obj;
	}

	public String getUser_valid_date(){
		return user_valid_date;
	}

	public void setAccumulation_reset_flag(Short obj){
		this.accumulation_reset_flag = obj;
	}

	public Short getAccumulation_reset_flag(){
		return accumulation_reset_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SChangeOwnerReq rhs=(SChangeOwnerReq)rhs0;
		if(!ObjectUtils.equals(prod_list, rhs.prod_list)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(change_type, rhs.change_type)) return false;
		if(!ObjectUtils.equals(outer_old_acct_id, rhs.outer_old_acct_id)) return false;
		if(!ObjectUtils.equals(old_acct_id, rhs.old_acct_id)) return false;
		if(!ObjectUtils.equals(outer_billable_acct_id, rhs.outer_billable_acct_id)) return false;
		if(!ObjectUtils.equals(billable_acct_id, rhs.billable_acct_id)) return false;
		if(!ObjectUtils.equals(convert_flag, rhs.convert_flag)) return false;
		if(!ObjectUtils.equals(last_term_flag, rhs.last_term_flag)) return false;
		if(!ObjectUtils.equals(outer_new_acct_id, rhs.outer_new_acct_id)) return false;
		if(!ObjectUtils.equals(new_acct_id, rhs.new_acct_id)) return false;
		if(!ObjectUtils.equals(reset_flag, rhs.reset_flag)) return false;
		if(!ObjectUtils.equals(change_promotion_flag, rhs.change_promotion_flag)) return false;
		if(!ObjectUtils.equals(days, rhs.days)) return false;
		if(!ObjectUtils.equals(user_segment, rhs.user_segment)) return false;
		if(!ObjectUtils.equals(user_valid_date, rhs.user_valid_date)) return false;
		if(!ObjectUtils.equals(accumulation_reset_flag, rhs.accumulation_reset_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_list)
		.append(user_id)
		.append(phone_id)
		.append(change_type)
		.append(outer_old_acct_id)
		.append(old_acct_id)
		.append(outer_billable_acct_id)
		.append(billable_acct_id)
		.append(convert_flag)
		.append(last_term_flag)
		.append(outer_new_acct_id)
		.append(new_acct_id)
		.append(reset_flag)
		.append(change_promotion_flag)
		.append(days)
		.append(user_segment)
		.append(user_valid_date)
		.append(accumulation_reset_flag)
		.toHashCode();
	}


}