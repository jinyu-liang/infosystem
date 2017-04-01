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
@XmlType(propOrder={"cap_max_service","amount","remain_value","action","thresholdList","item_name","item_code"})
public class CapMaxControl implements IComplexEntity{


	@XmlElement(name="thresholdList")
	private ThresholdList thresholdList;

	@XmlElement(name="cap_max_service")
	private String cap_max_service;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="remain_value")
	private Long remain_value;

	@XmlElement(name="action")
	private String action;

	@XmlElement(name="item_name")
	private String item_name;

	@XmlElement(name="item_code")
	private Integer item_code;

	public void setThresholdList(ThresholdList obj){
		this.thresholdList = obj;
	}

	public ThresholdList getThresholdList(){
		return thresholdList;
	}

	public void setCap_max_service(String obj){
		this.cap_max_service = obj;
	}

	public String getCap_max_service(){
		return cap_max_service;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setRemain_value(Long obj){
		this.remain_value = obj;
	}

	public Long getRemain_value(){
		return remain_value;
	}

	public void setAction(String obj){
		this.action = obj;
	}

	public String getAction(){
		return action;
	}

	public void setItem_name(String obj){
		this.item_name = obj;
	}

	public String getItem_name(){
		return item_name;
	}

	public void setItem_code(Integer obj){
		this.item_code = obj;
	}

	public Integer getItem_code(){
		return item_code;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CapMaxControl rhs=(CapMaxControl)rhs0;
		if(!ObjectUtils.equals(thresholdList, rhs.thresholdList)) return false;
		if(!ObjectUtils.equals(cap_max_service, rhs.cap_max_service)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(remain_value, rhs.remain_value)) return false;
		if(!ObjectUtils.equals(action, rhs.action)) return false;
		if(!ObjectUtils.equals(item_name, rhs.item_name)) return false;
		if(!ObjectUtils.equals(item_code, rhs.item_code)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(thresholdList)
		.append(cap_max_service)
		.append(amount)
		.append(remain_value)
		.append(action)
		.append(item_name)
		.append(item_code)
		.toHashCode();
	}


}