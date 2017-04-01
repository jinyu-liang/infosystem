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
@XmlType(propOrder={"old_ussd_language","old_irv_language","old_sms_language","old_user_segment","old_user_role","old_valid_date","brand","promotion_id","new_ussd_language","new_irv_language","new_sms_language","new_user_segment","new_user_role","new_valid_date"})
public class BatchUserInfo implements IComplexEntity{


	@XmlElement(name="old_ussd_language")
	private Short old_ussd_language;

	@XmlElement(name="old_irv_language")
	private Short old_irv_language;

	@XmlElement(name="old_sms_language")
	private Short old_sms_language;

	@XmlElement(name="old_user_segment")
	private Short old_user_segment;

	@XmlElement(name="old_user_role")
	private Short old_user_role;

	@XmlElement(name="old_valid_date")
	private String old_valid_date;

	@XmlElement(name="brand")
	private Short brand;

	@XmlElement(name="promotion_id")
	private Integer promotion_id;

	@XmlElement(name="new_ussd_language")
	private Short new_ussd_language;

	@XmlElement(name="new_irv_language")
	private Short new_irv_language;

	@XmlElement(name="new_sms_language")
	private Short new_sms_language;

	@XmlElement(name="new_user_segment")
	private Short new_user_segment;

	@XmlElement(name="new_user_role")
	private Short new_user_role;

	@XmlElement(name="new_valid_date")
	private String new_valid_date;

	public void setOld_ussd_language(Short obj){
		this.old_ussd_language = obj;
	}

	public Short getOld_ussd_language(){
		return old_ussd_language;
	}

	public void setOld_irv_language(Short obj){
		this.old_irv_language = obj;
	}

	public Short getOld_irv_language(){
		return old_irv_language;
	}

	public void setOld_sms_language(Short obj){
		this.old_sms_language = obj;
	}

	public Short getOld_sms_language(){
		return old_sms_language;
	}

	public void setOld_user_segment(Short obj){
		this.old_user_segment = obj;
	}

	public Short getOld_user_segment(){
		return old_user_segment;
	}

	public void setOld_user_role(Short obj){
		this.old_user_role = obj;
	}

	public Short getOld_user_role(){
		return old_user_role;
	}

	public void setOld_valid_date(String obj){
		this.old_valid_date = obj;
	}

	public String getOld_valid_date(){
		return old_valid_date;
	}

	public void setBrand(Short obj){
		this.brand = obj;
	}

	public Short getBrand(){
		return brand;
	}

	public void setPromotion_id(Integer obj){
		this.promotion_id = obj;
	}

	public Integer getPromotion_id(){
		return promotion_id;
	}

	public void setNew_ussd_language(Short obj){
		this.new_ussd_language = obj;
	}

	public Short getNew_ussd_language(){
		return new_ussd_language;
	}

	public void setNew_irv_language(Short obj){
		this.new_irv_language = obj;
	}

	public Short getNew_irv_language(){
		return new_irv_language;
	}

	public void setNew_sms_language(Short obj){
		this.new_sms_language = obj;
	}

	public Short getNew_sms_language(){
		return new_sms_language;
	}

	public void setNew_user_segment(Short obj){
		this.new_user_segment = obj;
	}

	public Short getNew_user_segment(){
		return new_user_segment;
	}

	public void setNew_user_role(Short obj){
		this.new_user_role = obj;
	}

	public Short getNew_user_role(){
		return new_user_role;
	}

	public void setNew_valid_date(String obj){
		this.new_valid_date = obj;
	}

	public String getNew_valid_date(){
		return new_valid_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BatchUserInfo rhs=(BatchUserInfo)rhs0;
		if(!ObjectUtils.equals(old_ussd_language, rhs.old_ussd_language)) return false;
		if(!ObjectUtils.equals(old_irv_language, rhs.old_irv_language)) return false;
		if(!ObjectUtils.equals(old_sms_language, rhs.old_sms_language)) return false;
		if(!ObjectUtils.equals(old_user_segment, rhs.old_user_segment)) return false;
		if(!ObjectUtils.equals(old_user_role, rhs.old_user_role)) return false;
		if(!ObjectUtils.equals(old_valid_date, rhs.old_valid_date)) return false;
		if(!ObjectUtils.equals(brand, rhs.brand)) return false;
		if(!ObjectUtils.equals(promotion_id, rhs.promotion_id)) return false;
		if(!ObjectUtils.equals(new_ussd_language, rhs.new_ussd_language)) return false;
		if(!ObjectUtils.equals(new_irv_language, rhs.new_irv_language)) return false;
		if(!ObjectUtils.equals(new_sms_language, rhs.new_sms_language)) return false;
		if(!ObjectUtils.equals(new_user_segment, rhs.new_user_segment)) return false;
		if(!ObjectUtils.equals(new_user_role, rhs.new_user_role)) return false;
		if(!ObjectUtils.equals(new_valid_date, rhs.new_valid_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(old_ussd_language)
		.append(old_irv_language)
		.append(old_sms_language)
		.append(old_user_segment)
		.append(old_user_role)
		.append(old_valid_date)
		.append(brand)
		.append(promotion_id)
		.append(new_ussd_language)
		.append(new_irv_language)
		.append(new_sms_language)
		.append(new_user_segment)
		.append(new_user_role)
		.append(new_valid_date)
		.toHashCode();
	}


}