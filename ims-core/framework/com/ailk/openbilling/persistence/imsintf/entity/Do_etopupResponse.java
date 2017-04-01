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
@XmlType(propOrder={"balance","activestop","value","valueadd","validity","validityadd"})
public class Do_etopupResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="balance")
	private String balance;

	@XmlElement(name="activestop")
	private String activestop;

	@XmlElement(name="value")
	private Long value;

	@XmlElement(name="valueadd")
	private Long valueadd;

	@XmlElement(name="validity")
	private String validity;

	@XmlElement(name="validityadd")
	private Long validityadd;

	public void setBalance(String obj){
		this.balance = obj;
	}

	public String getBalance(){
		return balance;
	}

	public void setActivestop(String obj){
		this.activestop = obj;
	}

	public String getActivestop(){
		return activestop;
	}

	public void setValue(Long obj){
		this.value = obj;
	}

	public Long getValue(){
		return value;
	}

	public void setValueadd(Long obj){
		this.valueadd = obj;
	}

	public Long getValueadd(){
		return valueadd;
	}

	public void setValidity(String obj){
		this.validity = obj;
	}

	public String getValidity(){
		return validity;
	}

	public void setValidityadd(Long obj){
		this.validityadd = obj;
	}

	public Long getValidityadd(){
		return validityadd;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_etopupResponse rhs=(Do_etopupResponse)rhs0;
		if(!ObjectUtils.equals(balance, rhs.balance)) return false;
		if(!ObjectUtils.equals(activestop, rhs.activestop)) return false;
		if(!ObjectUtils.equals(value, rhs.value)) return false;
		if(!ObjectUtils.equals(valueadd, rhs.valueadd)) return false;
		if(!ObjectUtils.equals(validity, rhs.validity)) return false;
		if(!ObjectUtils.equals(validityadd, rhs.validityadd)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(balance)
		.append(activestop)
		.append(value)
		.append(valueadd)
		.append(validity)
		.append(validityadd)
		.toHashCode();
	}


}