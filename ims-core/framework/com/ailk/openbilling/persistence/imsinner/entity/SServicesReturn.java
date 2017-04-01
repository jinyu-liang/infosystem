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
@XmlType(propOrder={"service_id","service_name","time_period","time_segment","payment_mode","service_status","status_name","threshold","valid_date","expire_date"})
public class SServicesReturn implements IComplexEntity{


	@XmlElement(name="service_id")
	private Integer service_id;

	@XmlElement(name="service_name")
	private String service_name;

	@XmlElement(name="time_period")
	private Short time_period;

	@XmlElement(name="time_segment")
	private Short time_segment;

	@XmlElement(name="payment_mode")
	private Short payment_mode;

	@XmlElement(name="service_status")
	private Short service_status;

	@XmlElement(name="status_name")
	private String status_name;

	@XmlElement(name="threshold")
	private Long threshold;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	public void setService_id(Integer obj){
		this.service_id = obj;
	}

	public Integer getService_id(){
		return service_id;
	}

	public void setService_name(String obj){
		this.service_name = obj;
	}

	public String getService_name(){
		return service_name;
	}

	public void setTime_period(Short obj){
		this.time_period = obj;
	}

	public Short getTime_period(){
		return time_period;
	}

	public void setTime_segment(Short obj){
		this.time_segment = obj;
	}

	public Short getTime_segment(){
		return time_segment;
	}

	public void setPayment_mode(Short obj){
		this.payment_mode = obj;
	}

	public Short getPayment_mode(){
		return payment_mode;
	}

	public void setService_status(Short obj){
		this.service_status = obj;
	}

	public Short getService_status(){
		return service_status;
	}

	public void setStatus_name(String obj){
		this.status_name = obj;
	}

	public String getStatus_name(){
		return status_name;
	}

	public void setThreshold(Long obj){
		this.threshold = obj;
	}

	public Long getThreshold(){
		return threshold;
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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SServicesReturn rhs=(SServicesReturn)rhs0;
		if(!ObjectUtils.equals(service_id, rhs.service_id)) return false;
		if(!ObjectUtils.equals(service_name, rhs.service_name)) return false;
		if(!ObjectUtils.equals(time_period, rhs.time_period)) return false;
		if(!ObjectUtils.equals(time_segment, rhs.time_segment)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(service_status, rhs.service_status)) return false;
		if(!ObjectUtils.equals(status_name, rhs.status_name)) return false;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(service_id)
		.append(service_name)
		.append(time_period)
		.append(time_segment)
		.append(payment_mode)
		.append(service_status)
		.append(status_name)
		.append(threshold)
		.append(valid_date)
		.append(expire_date)
		.toHashCode();
	}


}