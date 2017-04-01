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
@XmlType(propOrder={"fee_item","unpay_fee","discount_fee","adjust_fee","prim_fee","phone_id","measure_id"})
public class BillDetail implements IComplexEntity{


	@XmlElement(name="fee_item")
	private Integer fee_item;

	@XmlElement(name="unpay_fee")
	private double unpay_fee;

	@XmlElement(name="discount_fee")
	private Double discount_fee;

	@XmlElement(name="adjust_fee")
	private Double adjust_fee;

	@XmlElement(name="prim_fee")
	private Double prim_fee;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setFee_item(Integer obj){
		this.fee_item = obj;
	}

	public Integer getFee_item(){
		return fee_item;
	}

	public void setUnpay_fee(double obj){
		this.unpay_fee = obj;
	}

	public double getUnpay_fee(){
		return unpay_fee;
	}

	public void setDiscount_fee(Double obj){
		this.discount_fee = obj;
	}

	public Double getDiscount_fee(){
		return discount_fee;
	}

	public void setAdjust_fee(Double obj){
		this.adjust_fee = obj;
	}

	public Double getAdjust_fee(){
		return adjust_fee;
	}

	public void setPrim_fee(Double obj){
		this.prim_fee = obj;
	}

	public Double getPrim_fee(){
		return prim_fee;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillDetail rhs=(BillDetail)rhs0;
		if(!ObjectUtils.equals(fee_item, rhs.fee_item)) return false;
		if(!ObjectUtils.equals(unpay_fee, rhs.unpay_fee)) return false;
		if(!ObjectUtils.equals(discount_fee, rhs.discount_fee)) return false;
		if(!ObjectUtils.equals(adjust_fee, rhs.adjust_fee)) return false;
		if(!ObjectUtils.equals(prim_fee, rhs.prim_fee)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(fee_item)
		.append(unpay_fee)
		.append(discount_fee)
		.append(adjust_fee)
		.append(prim_fee)
		.append(phone_id)
		.append(measure_id)
		.toHashCode();
	}


}