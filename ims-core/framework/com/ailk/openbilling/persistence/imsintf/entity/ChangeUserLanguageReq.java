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
@XmlType(propOrder={"phone_id","user_id","ussd_language","ivr_language","sms_language"})
public class ChangeUserLanguageReq implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="ussd_language")
	private Short ussd_language;

	@XmlElement(name="ivr_language")
	private Short ivr_language;

	@XmlElement(name="sms_language")
	private Short sms_language;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setUssd_language(Short obj){
		this.ussd_language = obj;
	}

	public Short getUssd_language(){
		return ussd_language;
	}

	public void setIvr_language(Short obj){
		this.ivr_language = obj;
	}

	public Short getIvr_language(){
		return ivr_language;
	}

	public void setSms_language(Short obj){
		this.sms_language = obj;
	}

	public Short getSms_language(){
		return sms_language;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ChangeUserLanguageReq rhs=(ChangeUserLanguageReq)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(ussd_language, rhs.ussd_language)) return false;
		if(!ObjectUtils.equals(ivr_language, rhs.ivr_language)) return false;
		if(!ObjectUtils.equals(sms_language, rhs.sms_language)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(user_id)
		.append(ussd_language)
		.append(ivr_language)
		.append(sms_language)
		.toHashCode();
	}


}