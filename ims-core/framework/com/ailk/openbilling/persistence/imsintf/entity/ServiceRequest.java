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
@XmlType(propOrder={"busi_service_code","service_sequence_id","camel_support","valid_date","expire_date","service_status","description"})
public class ServiceRequest implements IComplexEntity{


	@XmlElement(name="busi_service_code")
	private String busi_service_code;

	@XmlElement(name="service_sequence_id")
	private Integer service_sequence_id;

	@XmlElement(name="camel_support")
	private Short camel_support;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="service_status")
	private Short service_status;

	@XmlElement(name="description")
	private String description;

	public void setBusi_service_code(String obj){
		this.busi_service_code = obj;
	}

	public String getBusi_service_code(){
		return busi_service_code;
	}

	public void setService_sequence_id(Integer obj){
		this.service_sequence_id = obj;
	}

	public Integer getService_sequence_id(){
		return service_sequence_id;
	}

	public void setCamel_support(Short obj){
		this.camel_support = obj;
	}

	public Short getCamel_support(){
		return camel_support;
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

	public void setService_status(Short obj){
		this.service_status = obj;
	}

	public Short getService_status(){
		return service_status;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ServiceRequest rhs=(ServiceRequest)rhs0;
		if(!ObjectUtils.equals(busi_service_code, rhs.busi_service_code)) return false;
		if(!ObjectUtils.equals(service_sequence_id, rhs.service_sequence_id)) return false;
		if(!ObjectUtils.equals(camel_support, rhs.camel_support)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(service_status, rhs.service_status)) return false;
		if(!ObjectUtils.equals(description, rhs.description)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busi_service_code)
		.append(service_sequence_id)
		.append(camel_support)
		.append(valid_date)
		.append(expire_date)
		.append(service_status)
		.append(description)
		.toHashCode();
	}


}