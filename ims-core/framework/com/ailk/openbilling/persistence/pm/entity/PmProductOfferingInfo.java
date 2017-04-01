package com.ailk.openbilling.persistence.pm.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"product_offering_id","offer_type_id","name","is_main","lifecycle_status_id","prod_spec_id","priority","is_global","valid_date","expire_date","description","opertor_id","billing_priority","offer_class"})
public class PmProductOfferingInfo implements IComplexEntity{


	@XmlElement(name="product_offering_id")
	private Integer product_offering_id;

	@XmlElement(name="offer_type_id")
	private Integer offer_type_id;

	@XmlElement(name="name")
	private String name;

	@XmlElement(name="is_main")
	private Integer is_main;

	@XmlElement(name="lifecycle_status_id")
	private Integer lifecycle_status_id;

	@XmlElement(name="prod_spec_id")
	private Integer prod_spec_id;

	@XmlElement(name="priority")
	private Integer priority;

	@XmlElement(name="is_global")
	private Integer is_global;

	@XmlElement(name="valid_date")
	private Date valid_date;

	@XmlElement(name="expire_date")
	private Date expire_date;

	@XmlElement(name="description")
	private String description;

	@XmlElement(name="opertor_id")
	private Integer opertor_id;

	@XmlElement(name="billing_priority")
	private Integer billing_priority;

	@XmlElement(name="offer_class")
	private String offer_class;

	public void setProduct_offering_id(Integer obj){
		this.product_offering_id = obj;
	}

	public Integer getProduct_offering_id(){
		return product_offering_id;
	}

	public void setOffer_type_id(Integer obj){
		this.offer_type_id = obj;
	}

	public Integer getOffer_type_id(){
		return offer_type_id;
	}

	public void setName(String obj){
		this.name = obj;
	}

	public String getName(){
		return name;
	}

	public void setIs_main(Integer obj){
		this.is_main = obj;
	}

	public Integer getIs_main(){
		return is_main;
	}

	public void setLifecycle_status_id(Integer obj){
		this.lifecycle_status_id = obj;
	}

	public Integer getLifecycle_status_id(){
		return lifecycle_status_id;
	}

	public void setProd_spec_id(Integer obj){
		this.prod_spec_id = obj;
	}

	public Integer getProd_spec_id(){
		return prod_spec_id;
	}

	public void setPriority(Integer obj){
		this.priority = obj;
	}

	public Integer getPriority(){
		return priority;
	}

	public void setIs_global(Integer obj){
		this.is_global = obj;
	}

	public Integer getIs_global(){
		return is_global;
	}

	public void setValid_date(Date obj){
		this.valid_date = obj;
	}

	public Date getValid_date(){
		return valid_date;
	}

	public void setExpire_date(Date obj){
		this.expire_date = obj;
	}

	public Date getExpire_date(){
		return expire_date;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public void setOpertor_id(Integer obj){
		this.opertor_id = obj;
	}

	public Integer getOpertor_id(){
		return opertor_id;
	}

	public void setBilling_priority(Integer obj){
		this.billing_priority = obj;
	}

	public Integer getBilling_priority(){
		return billing_priority;
	}

	public void setOffer_class(String obj){
		this.offer_class = obj;
	}

	public String getOffer_class(){
		return offer_class;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmProductOfferingInfo rhs=(PmProductOfferingInfo)rhs0;
		if(!ObjectUtils.equals(product_offering_id, rhs.product_offering_id)) return false;
		if(!ObjectUtils.equals(offer_type_id, rhs.offer_type_id)) return false;
		if(!ObjectUtils.equals(name, rhs.name)) return false;
		if(!ObjectUtils.equals(is_main, rhs.is_main)) return false;
		if(!ObjectUtils.equals(lifecycle_status_id, rhs.lifecycle_status_id)) return false;
		if(!ObjectUtils.equals(prod_spec_id, rhs.prod_spec_id)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(is_global, rhs.is_global)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(description, rhs.description)) return false;
		if(!ObjectUtils.equals(opertor_id, rhs.opertor_id)) return false;
		if(!ObjectUtils.equals(billing_priority, rhs.billing_priority)) return false;
		if(!ObjectUtils.equals(offer_class, rhs.offer_class)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_offering_id)
		.append(offer_type_id)
		.append(name)
		.append(is_main)
		.append(lifecycle_status_id)
		.append(prod_spec_id)
		.append(priority)
		.append(is_global)
		.append(valid_date)
		.append(expire_date)
		.append(description)
		.append(opertor_id)
		.append(billing_priority)
		.append(offer_class)
		.toHashCode();
	}


}