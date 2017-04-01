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
@XmlType(propOrder={"item_id","item_name","amount","measure_name"})
public class FreeItem4GUI implements IComplexEntity{


	@XmlElement(name="item_id")
	private Integer item_id;

	@XmlElement(name="item_name")
	private String item_name;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="measure_name")
	private String measure_name;

	public void setItem_id(Integer obj){
		this.item_id = obj;
	}

	public Integer getItem_id(){
		return item_id;
	}

	public void setItem_name(String obj){
		this.item_name = obj;
	}

	public String getItem_name(){
		return item_name;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setMeasure_name(String obj){
		this.measure_name = obj;
	}

	public String getMeasure_name(){
		return measure_name;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FreeItem4GUI rhs=(FreeItem4GUI)rhs0;
		if(!ObjectUtils.equals(item_id, rhs.item_id)) return false;
		if(!ObjectUtils.equals(item_name, rhs.item_name)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(measure_name, rhs.measure_name)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(item_id)
		.append(item_name)
		.append(amount)
		.append(measure_name)
		.toHashCode();
	}


}