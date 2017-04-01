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
@XmlType(propOrder={"product_id","offer_id","accumulate_item","accumulate_name","accumulate_amount","accumulate_pay_mode"})
public class Accumulate implements IComplexEntity{


	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="offer_id")
	private Long offer_id;

	@XmlElement(name="accumulate_item")
	private int accumulate_item;

	@XmlElement(name="accumulate_name")
	private String accumulate_name;

	@XmlElement(name="accumulate_amount")
	private double accumulate_amount;

	@XmlElement(name="accumulate_pay_mode")
	private Short accumulate_pay_mode;

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

	public void setAccumulate_item(int obj){
		this.accumulate_item = obj;
	}

	public int getAccumulate_item(){
		return accumulate_item;
	}

	public void setAccumulate_name(String obj){
		this.accumulate_name = obj;
	}

	public String getAccumulate_name(){
		return accumulate_name;
	}

	public void setAccumulate_amount(double obj){
		this.accumulate_amount = obj;
	}

	public double getAccumulate_amount(){
		return accumulate_amount;
	}

	public void setAccumulate_pay_mode(Short obj){
		this.accumulate_pay_mode = obj;
	}

	public Short getAccumulate_pay_mode(){
		return accumulate_pay_mode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Accumulate rhs=(Accumulate)rhs0;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(offer_id, rhs.offer_id)) return false;
		if(!ObjectUtils.equals(accumulate_item, rhs.accumulate_item)) return false;
		if(!ObjectUtils.equals(accumulate_name, rhs.accumulate_name)) return false;
		if(!ObjectUtils.equals(accumulate_amount, rhs.accumulate_amount)) return false;
		if(!ObjectUtils.equals(accumulate_pay_mode, rhs.accumulate_pay_mode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_id)
		.append(offer_id)
		.append(accumulate_item)
		.append(accumulate_name)
		.append(accumulate_amount)
		.append(accumulate_pay_mode)
		.toHashCode();
	}


}