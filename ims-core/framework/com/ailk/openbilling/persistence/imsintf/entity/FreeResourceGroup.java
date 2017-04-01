package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"resource_group_id","name","amount","remaining","unit","mode","valid_date","expired_date","freeResourceList_Item"})
public class FreeResourceGroup implements IComplexEntity{


	@XmlElement(name="freeResourceList_Item")
	private FreeResourceList freeResourceList_Item;

	@XmlElement(name="resource_group_id")
	private Integer resource_group_id;

	@XmlElement(name="name")
	private String name;

	@XmlElement(name="amount")
	private Integer amount;

	@XmlElement(name="remaining")
	private Integer remaining;

	@XmlElement(name="unit")
	private Integer unit;

	@XmlElement(name="mode")
	private Integer mode;

	@XmlElement(name="valid_date")
	private Date valid_date;

	@XmlElement(name="expired_date")
	private Date expired_date;

	public void setFreeResourceList_Item(FreeResourceList obj){
		this.freeResourceList_Item = obj;
	}

	public FreeResourceList getFreeResourceList_Item(){
		return freeResourceList_Item;
	}

	public void setResource_group_id(Integer obj){
		this.resource_group_id = obj;
	}

	public Integer getResource_group_id(){
		return resource_group_id;
	}

	public void setName(String obj){
		this.name = obj;
	}

	public String getName(){
		return name;
	}

	public void setAmount(Integer obj){
		this.amount = obj;
	}

	public Integer getAmount(){
		return amount;
	}

	public void setRemaining(Integer obj){
		this.remaining = obj;
	}

	public Integer getRemaining(){
		return remaining;
	}

	public void setUnit(Integer obj){
		this.unit = obj;
	}

	public Integer getUnit(){
		return unit;
	}

	public void setMode(Integer obj){
		this.mode = obj;
	}

	public Integer getMode(){
		return mode;
	}

	public void setValid_date(Date obj){
		this.valid_date = obj;
	}

	public Date getValid_date(){
		return valid_date;
	}

	public void setExpired_date(Date obj){
		this.expired_date = obj;
	}

	public Date getExpired_date(){
		return expired_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FreeResourceGroup rhs=(FreeResourceGroup)rhs0;
		if(!ObjectUtils.equals(freeResourceList_Item, rhs.freeResourceList_Item)) return false;
		if(!ObjectUtils.equals(resource_group_id, rhs.resource_group_id)) return false;
		if(!ObjectUtils.equals(name, rhs.name)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(remaining, rhs.remaining)) return false;
		if(!ObjectUtils.equals(unit, rhs.unit)) return false;
		if(!ObjectUtils.equals(mode, rhs.mode)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expired_date, rhs.expired_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeResourceList_Item)
		.append(resource_group_id)
		.append(name)
		.append(amount)
		.append(remaining)
		.append(unit)
		.append(mode)
		.append(valid_date)
		.append(expired_date)
		.toHashCode();
	}


}