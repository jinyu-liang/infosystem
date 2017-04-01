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
@XmlType(propOrder={"sub_group_name","after_discount_charge","unit","usage","peak_time","before_discount_charge"})
public class SubActualUsage implements IComplexEntity{


	@XmlElement(name="sub_group_name")
	private String sub_group_name;

	@XmlElement(name="after_discount_charge")
	private Long after_discount_charge;

	@XmlElement(name="unit")
	private Short unit;

	@XmlElement(name="usage")
	private Long usage;

	@XmlElement(name="peak_time")
	private String peak_time;

	@XmlElement(name="before_discount_charge")
	private Long before_discount_charge;

	public void setSub_group_name(String obj){
		this.sub_group_name = obj;
	}

	public String getSub_group_name(){
		return sub_group_name;
	}

	public void setAfter_discount_charge(Long obj){
		this.after_discount_charge = obj;
	}

	public Long getAfter_discount_charge(){
		return after_discount_charge;
	}

	public void setUnit(Short obj){
		this.unit = obj;
	}

	public Short getUnit(){
		return unit;
	}

	public void setUsage(Long obj){
		this.usage = obj;
	}

	public Long getUsage(){
		return usage;
	}

	public void setPeak_time(String obj){
		this.peak_time = obj;
	}

	public String getPeak_time(){
		return peak_time;
	}

	public void setBefore_discount_charge(Long obj){
		this.before_discount_charge = obj;
	}

	public Long getBefore_discount_charge(){
		return before_discount_charge;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SubActualUsage rhs=(SubActualUsage)rhs0;
		if(!ObjectUtils.equals(sub_group_name, rhs.sub_group_name)) return false;
		if(!ObjectUtils.equals(after_discount_charge, rhs.after_discount_charge)) return false;
		if(!ObjectUtils.equals(unit, rhs.unit)) return false;
		if(!ObjectUtils.equals(usage, rhs.usage)) return false;
		if(!ObjectUtils.equals(peak_time, rhs.peak_time)) return false;
		if(!ObjectUtils.equals(before_discount_charge, rhs.before_discount_charge)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sub_group_name)
		.append(after_discount_charge)
		.append(unit)
		.append(usage)
		.append(peak_time)
		.append(before_discount_charge)
		.toHashCode();
	}


}