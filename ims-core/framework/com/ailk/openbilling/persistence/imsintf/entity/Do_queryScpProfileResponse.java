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
@XmlType(propOrder={"phone_id","fph_flag","ussd_callback_flag","icfFlag","icfNumber","icfNumberStatus","sUserAuth","flh_flag","posContinue_flag","brand_id"})
public class Do_queryScpProfileResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sUserAuth")
	private SUserAuth sUserAuth;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="fph_flag")
	private Short fph_flag;

	@XmlElement(name="ussd_callback_flag")
	private Short ussd_callback_flag;

	@XmlElement(name="icfFlag")
	private Short icfFlag;

	@XmlElement(name="icfNumber")
	private String icfNumber;

	@XmlElement(name="icfNumberStatus")
	private String icfNumberStatus;

	@XmlElement(name="flh_flag")
	private Short flh_flag;

	@XmlElement(name="posContinue_flag")
	private Short posContinue_flag;

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

	public void setIcfFlag(Short obj){
		this.icfFlag = obj;
	}

	public Short getIcfFlag(){
		return icfFlag;
	}

	public void setIcfNumber(String obj){
		this.icfNumber = obj;
	}

	public String getIcfNumber(){
		return icfNumber;
	}

	public void setIcfNumberStatus(String obj){
		this.icfNumberStatus = obj;
	}

	public String getIcfNumberStatus(){
		return icfNumberStatus;
	}

	public void setFlh_flag(Short obj){
		this.flh_flag = obj;
	}

	public Short getFlh_flag(){
		return flh_flag;
	}

	public void setPosContinue_flag(Short obj){
		this.posContinue_flag = obj;
	}

	public Short getPosContinue_flag(){
		return posContinue_flag;
	}

	public void setBrand_id(Short obj){
		this.brand_id = obj;
	}

	public Short getBrand_id(){
		return brand_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryScpProfileResponse rhs=(Do_queryScpProfileResponse)rhs0;
		if(!ObjectUtils.equals(sUserAuth, rhs.sUserAuth)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(fph_flag, rhs.fph_flag)) return false;
		if(!ObjectUtils.equals(ussd_callback_flag, rhs.ussd_callback_flag)) return false;
		if(!ObjectUtils.equals(icfFlag, rhs.icfFlag)) return false;
		if(!ObjectUtils.equals(icfNumber, rhs.icfNumber)) return false;
		if(!ObjectUtils.equals(icfNumberStatus, rhs.icfNumberStatus)) return false;
		if(!ObjectUtils.equals(flh_flag, rhs.flh_flag)) return false;
		if(!ObjectUtils.equals(posContinue_flag, rhs.posContinue_flag)) return false;
		if(!ObjectUtils.equals(brand_id, rhs.brand_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUserAuth)
		.append(phone_id)
		.append(fph_flag)
		.append(ussd_callback_flag)
		.append(icfFlag)
		.append(icfNumber)
		.append(icfNumberStatus)
		.append(flh_flag)
		.append(posContinue_flag)
		.append(brand_id)
		.toHashCode();
	}


}