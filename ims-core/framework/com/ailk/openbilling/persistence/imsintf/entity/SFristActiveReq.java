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
@XmlType(propOrder={"user_id","phone_id","activation_time","device_type","sms_lang","ivr_lang","ussd_lang","location","balance"})
public class SFristActiveReq implements IComplexEntity{


	@XmlElement(name="balance")
	private SBalance balance;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="activation_time")
	private String activation_time;

	@XmlElement(name="device_type")
	private String device_type;

	@XmlElement(name="sms_lang")
	private Short sms_lang;

	@XmlElement(name="ivr_lang")
	private Short ivr_lang;

	@XmlElement(name="ussd_lang")
	private Short ussd_lang;

	@XmlElement(name="location")
	private String location;

	public void setBalance(SBalance obj){
		this.balance = obj;
	}

	public SBalance getBalance(){
		return balance;
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

	public void setActivation_time(String obj){
		this.activation_time = obj;
	}

	public String getActivation_time(){
		return activation_time;
	}

	public void setDevice_type(String obj){
		this.device_type = obj;
	}

	public String getDevice_type(){
		return device_type;
	}

	public void setSms_lang(Short obj){
		this.sms_lang = obj;
	}

	public Short getSms_lang(){
		return sms_lang;
	}

	public void setIvr_lang(Short obj){
		this.ivr_lang = obj;
	}

	public Short getIvr_lang(){
		return ivr_lang;
	}

	public void setUssd_lang(Short obj){
		this.ussd_lang = obj;
	}

	public Short getUssd_lang(){
		return ussd_lang;
	}

	public void setLocation(String obj){
		this.location = obj;
	}

	public String getLocation(){
		return location;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SFristActiveReq rhs=(SFristActiveReq)rhs0;
		if(!ObjectUtils.equals(balance, rhs.balance)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(activation_time, rhs.activation_time)) return false;
		if(!ObjectUtils.equals(device_type, rhs.device_type)) return false;
		if(!ObjectUtils.equals(sms_lang, rhs.sms_lang)) return false;
		if(!ObjectUtils.equals(ivr_lang, rhs.ivr_lang)) return false;
		if(!ObjectUtils.equals(ussd_lang, rhs.ussd_lang)) return false;
		if(!ObjectUtils.equals(location, rhs.location)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(balance)
		.append(user_id)
		.append(phone_id)
		.append(activation_time)
		.append(device_type)
		.append(sms_lang)
		.append(ivr_lang)
		.append(ussd_lang)
		.append(location)
		.toHashCode();
	}


}