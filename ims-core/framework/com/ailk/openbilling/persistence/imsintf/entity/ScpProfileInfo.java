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
@XmlType(propOrder={"phone_id","fph_flag","ussd_callback_flag","icf_flag","icf_number","flh_flag","continue_flag","sUserAuth","brand_id"})
public class ScpProfileInfo implements IComplexEntity{


	@XmlElement(name="sUserAuth")
	private SUserAuth sUserAuth;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="fph_flag")
	private Short fph_flag;

	@XmlElement(name="ussd_callback_flag")
	private Short ussd_callback_flag;

	@XmlElement(name="icf_flag")
	private Short icf_flag;

	@XmlElement(name="icf_number")
	private String icf_number;

	@XmlElement(name="flh_flag")
	private Short flh_flag;

	@XmlElement(name="continue_flag")
	private Short continue_flag;

	@XmlElement(name="brand_id")
	private Short brand_id;

	public void setSUserAuth(SUserAuth obj){
		this.sUserAuth = obj;
	}

	public SUserAuth getSUserAuth(){
		return sUserAuth;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setFph_flag(Short obj){
		this.fph_flag = obj;
	}

	public Short getFph_flag(){
		return fph_flag;
	}

	public void setUssd_callback_flag(Short obj){
		this.ussd_callback_flag = obj;
	}

	public Short getUssd_callback_flag(){
		return ussd_callback_flag;
	}

	public void setIcf_flag(Short obj){
		this.icf_flag = obj;
	}

	public Short getIcf_flag(){
		return icf_flag;
	}

	public void setIcf_number(String obj){
		this.icf_number = obj;
	}

	public String getIcf_number(){
		return icf_number;
	}

	public void setFlh_flag(Short obj){
		this.flh_flag = obj;
	}

	public Short getFlh_flag(){
		return flh_flag;
	}

	public void setContinue_flag(Short obj){
		this.continue_flag = obj;
	}

	public Short getContinue_flag(){
		return continue_flag;
	}

	public void setBrand_id(Short obj){
		this.brand_id = obj;
	}

	public Short getBrand_id(){
		return brand_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ScpProfileInfo rhs=(ScpProfileInfo)rhs0;
		if(!ObjectUtils.equals(sUserAuth, rhs.sUserAuth)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(fph_flag, rhs.fph_flag)) return false;
		if(!ObjectUtils.equals(ussd_callback_flag, rhs.ussd_callback_flag)) return false;
		if(!ObjectUtils.equals(icf_flag, rhs.icf_flag)) return false;
		if(!ObjectUtils.equals(icf_number, rhs.icf_number)) return false;
		if(!ObjectUtils.equals(flh_flag, rhs.flh_flag)) return false;
		if(!ObjectUtils.equals(continue_flag, rhs.continue_flag)) return false;
		if(!ObjectUtils.equals(brand_id, rhs.brand_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUserAuth)
		.append(phone_id)
		.append(fph_flag)
		.append(ussd_callback_flag)
		.append(icf_flag)
		.append(icf_number)
		.append(flh_flag)
		.append(continue_flag)
		.append(brand_id)
		.toHashCode();
	}


}