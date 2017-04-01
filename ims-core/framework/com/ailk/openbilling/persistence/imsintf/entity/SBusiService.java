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
@XmlType(propOrder={"service_id","time_segment","payment_mode","service_status","threshold","valid_date","create_date","expire_date","remaining_amount"})
public class SBusiService implements IComplexEntity{


	@XmlElement(name="service_id")
	private Integer service_id;

	@XmlElement(name="time_segment")
	private Short time_segment;

	@XmlElement(name="payment_mode")
	private Short payment_mode;

	@XmlElement(name="service_status")
	private Short service_status;

	@XmlElement(name="threshold")
	private Double threshold;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="remaining_amount")
	private Double remaining_amount;

	public void setService_id(Integer obj){
		this.service_id = obj;
	}

	public Integer getService_id(){
		return service_id;
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

	public void setThreshold(Double obj){
		this.threshold = obj;
	}

	public Double getThreshold(){
		return threshold;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setCreate_date(String obj){
		this.create_date = obj;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setRemaining_amount(Double obj){
		this.remaining_amount = obj;
	}

	public Double getRemaining_amount(){
		return remaining_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBusiService rhs=(SBusiService)rhs0;
		if(!ObjectUtils.equals(service_id, rhs.service_id)) return false;
		if(!ObjectUtils.equals(time_segment, rhs.time_segment)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(service_status, rhs.service_status)) return false;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(remaining_amount, rhs.remaining_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(service_id)
		.append(time_segment)
		.append(payment_mode)
		.append(service_status)
		.append(threshold)
		.append(valid_date)
		.append(create_date)
		.append(expire_date)
		.append(remaining_amount)
		.toHashCode();
	}


}