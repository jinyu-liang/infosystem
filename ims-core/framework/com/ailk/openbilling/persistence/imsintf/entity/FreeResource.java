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
@XmlType(propOrder={"product_id","offer_id","phone_id","resource_id","resource_name","group_name","resource_amount","remain_resource","vaild_date","expire_date","resource_after","resource_before","resource_request","resource_adjust","capital_flag","group_id","payment_mode","remain_value","resource_unit"})
public class FreeResource implements IComplexEntity{


	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="offer_id")
	private Long offer_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="resource_id")
	private Long resource_id;

	@XmlElement(name="resource_name")
	private String resource_name;

	@XmlElement(name="group_name")
	private String group_name;

	@XmlElement(name="resource_amount")
	private Long resource_amount;

	@XmlElement(name="remain_resource")
	private Double remain_resource;

	@XmlElement(name="vaild_date")
	private String vaild_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="resource_after")
	private Long resource_after;

	@XmlElement(name="resource_before")
	private Long resource_before;

	@XmlElement(name="resource_request")
	private Long resource_request;

	@XmlElement(name="resource_adjust")
	private Long resource_adjust;

	@XmlElement(name="capital_flag")
	private Long capital_flag;

	@XmlElement(name="group_id")
	private Long group_id;

	@XmlElement(name="payment_mode")
	private Short payment_mode;

	@XmlElement(name="remain_value")
	private Long remain_value;

	@XmlElement(name="resource_unit")
	private String resource_unit;

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setOffer_id(Long obj){
		this.offer_id = obj;
	}

	public Long getOffer_id(){
		return offer_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setResource_id(Long obj){
		this.resource_id = obj;
	}

	public Long getResource_id(){
		return resource_id;
	}

	public void setResource_name(String obj){
		this.resource_name = obj;
	}

	public String getResource_name(){
		return resource_name;
	}

	public void setGroup_name(String obj){
		this.group_name = obj;
	}

	public String getGroup_name(){
		return group_name;
	}

	public void setResource_amount(Long obj){
		this.resource_amount = obj;
	}

	public Long getResource_amount(){
		return resource_amount;
	}

	public void setRemain_resource(Double obj){
		this.remain_resource = obj;
	}

	public Double getRemain_resource(){
		return remain_resource;
	}

	public void setVaild_date(String obj){
		this.vaild_date = obj;
	}

	public String getVaild_date(){
		return vaild_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setResource_after(Long obj){
		this.resource_after = obj;
	}

	public Long getResource_after(){
		return resource_after;
	}

	public void setResource_before(Long obj){
		this.resource_before = obj;
	}

	public Long getResource_before(){
		return resource_before;
	}

	public void setResource_request(Long obj){
		this.resource_request = obj;
	}

	public Long getResource_request(){
		return resource_request;
	}

	public void setResource_adjust(Long obj){
		this.resource_adjust = obj;
	}

	public Long getResource_adjust(){
		return resource_adjust;
	}

	public void setCapital_flag(Long obj){
		this.capital_flag = obj;
	}

	public Long getCapital_flag(){
		return capital_flag;
	}

	public void setGroup_id(Long obj){
		this.group_id = obj;
	}

	public Long getGroup_id(){
		return group_id;
	}

	public void setPayment_mode(Short obj){
		this.payment_mode = obj;
	}

	public Short getPayment_mode(){
		return payment_mode;
	}

	public void setRemain_value(Long obj){
		this.remain_value = obj;
	}

	public Long getRemain_value(){
		return remain_value;
	}

	public void setResource_unit(String obj){
		this.resource_unit = obj;
	}

	public String getResource_unit(){
		return resource_unit;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FreeResource rhs=(FreeResource)rhs0;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(offer_id, rhs.offer_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(resource_name, rhs.resource_name)) return false;
		if(!ObjectUtils.equals(group_name, rhs.group_name)) return false;
		if(!ObjectUtils.equals(resource_amount, rhs.resource_amount)) return false;
		if(!ObjectUtils.equals(remain_resource, rhs.remain_resource)) return false;
		if(!ObjectUtils.equals(vaild_date, rhs.vaild_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(resource_after, rhs.resource_after)) return false;
		if(!ObjectUtils.equals(resource_before, rhs.resource_before)) return false;
		if(!ObjectUtils.equals(resource_request, rhs.resource_request)) return false;
		if(!ObjectUtils.equals(resource_adjust, rhs.resource_adjust)) return false;
		if(!ObjectUtils.equals(capital_flag, rhs.capital_flag)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(remain_value, rhs.remain_value)) return false;
		if(!ObjectUtils.equals(resource_unit, rhs.resource_unit)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_id)
		.append(offer_id)
		.append(phone_id)
		.append(resource_id)
		.append(resource_name)
		.append(group_name)
		.append(resource_amount)
		.append(remain_resource)
		.append(vaild_date)
		.append(expire_date)
		.append(resource_after)
		.append(resource_before)
		.append(resource_request)
		.append(resource_adjust)
		.append(capital_flag)
		.append(group_id)
		.append(payment_mode)
		.append(remain_value)
		.append(resource_unit)
		.toHashCode();
	}


}