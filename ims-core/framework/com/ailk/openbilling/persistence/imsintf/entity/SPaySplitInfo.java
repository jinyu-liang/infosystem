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
@XmlType(propOrder={"outer_pay_acct_id","pay_acct_id","outer_pay_cust_id","pay_cust_id","priority","valid_date","expire_date","part_type","part_value","measure_id","split_method"})
public class SPaySplitInfo implements IComplexEntity{


	@XmlElement(name="outer_pay_acct_id")
	private String outer_pay_acct_id;

	@XmlElement(name="pay_acct_id")
	private Long pay_acct_id;

	@XmlElement(name="outer_pay_cust_id")
	private String outer_pay_cust_id;

	@XmlElement(name="pay_cust_id")
	private Long pay_cust_id;

	@XmlElement(name="priority")
	private Short priority;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="part_type")
	private Short part_type;

	@XmlElement(name="part_value")
	private Double part_value;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	@XmlElement(name="split_method")
	private Short split_method;

	public void setOuter_pay_acct_id(String obj){
		this.outer_pay_acct_id = obj;
	}

	public String getOuter_pay_acct_id(){
		return outer_pay_acct_id;
	}

	public void setPay_acct_id(Long obj){
		this.pay_acct_id = obj;
	}

	public Long getPay_acct_id(){
		return pay_acct_id;
	}

	public void setOuter_pay_cust_id(String obj){
		this.outer_pay_cust_id = obj;
	}

	public String getOuter_pay_cust_id(){
		return outer_pay_cust_id;
	}

	public void setPay_cust_id(Long obj){
		this.pay_cust_id = obj;
	}

	public Long getPay_cust_id(){
		return pay_cust_id;
	}

	public void setPriority(Short obj){
		this.priority = obj;
	}

	public Short getPriority(){
		return priority;
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

	public void setPart_type(Short obj){
		this.part_type = obj;
	}

	public Short getPart_type(){
		return part_type;
	}

	public void setPart_value(Double obj){
		this.part_value = obj;
	}

	public Double getPart_value(){
		return part_value;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public void setSplit_method(Short obj){
		this.split_method = obj;
	}

	public Short getSplit_method(){
		return split_method;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPaySplitInfo rhs=(SPaySplitInfo)rhs0;
		if(!ObjectUtils.equals(outer_pay_acct_id, rhs.outer_pay_acct_id)) return false;
		if(!ObjectUtils.equals(pay_acct_id, rhs.pay_acct_id)) return false;
		if(!ObjectUtils.equals(outer_pay_cust_id, rhs.outer_pay_cust_id)) return false;
		if(!ObjectUtils.equals(pay_cust_id, rhs.pay_cust_id)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(part_type, rhs.part_type)) return false;
		if(!ObjectUtils.equals(part_value, rhs.part_value)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		if(!ObjectUtils.equals(split_method, rhs.split_method)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_pay_acct_id)
		.append(pay_acct_id)
		.append(outer_pay_cust_id)
		.append(pay_cust_id)
		.append(priority)
		.append(valid_date)
		.append(expire_date)
		.append(part_type)
		.append(part_value)
		.append(measure_id)
		.append(split_method)
		.toHashCode();
	}


}