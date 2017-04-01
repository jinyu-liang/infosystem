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
@XmlType(propOrder={"busi_service_id","amount","remain_value","action","status","status_date","thresholdList"})
public class CapMaxAccumulate implements IComplexEntity{


	@XmlElement(name="thresholdList")
	private ThresholdList thresholdList;

	@XmlElement(name="busi_service_id")
	private Integer busi_service_id;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="remain_value")
	private Long remain_value;

	@XmlElement(name="action")
	private String action;

	@XmlElement(name="status")
	private String status;

	@XmlElement(name="status_date")
	private String status_date;

	public void setThresholdList(ThresholdList obj){
		this.thresholdList = obj;
	}

	public ThresholdList getThresholdList(){
		return thresholdList;
	}

	public void setBusi_service_id(Integer obj){
		this.busi_service_id = obj;
	}

	public Integer getBusi_service_id(){
		return busi_service_id;
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

	public void setStatus(String obj){
		this.status = obj;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus_date(String obj){
		this.status_date = obj;
	}

	public String getStatus_date(){
		return status_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CapMaxAccumulate rhs=(CapMaxAccumulate)rhs0;
		if(!ObjectUtils.equals(thresholdList, rhs.thresholdList)) return false;
		if(!ObjectUtils.equals(busi_service_id, rhs.busi_service_id)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(remain_value, rhs.remain_value)) return false;
		if(!ObjectUtils.equals(action, rhs.action)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(status_date, rhs.status_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(thresholdList)
		.append(busi_service_id)
		.append(amount)
		.append(remain_value)
		.append(action)
		.append(status)
		.append(status_date)
		.toHashCode();
	}


}