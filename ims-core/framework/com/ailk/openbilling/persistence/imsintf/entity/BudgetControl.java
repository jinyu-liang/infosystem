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
@XmlType(propOrder={"busi_service_id","payment_mode","budget_type","budget","remain_budget","temp_budget","action","measure_id","thresholdList"})
public class BudgetControl implements IComplexEntity{


	@XmlElement(name="thresholdList")
	private ThresholdList thresholdList;

	@XmlElement(name="busi_service_id")
	private Long busi_service_id;

	@XmlElement(name="payment_mode")
	private Short payment_mode;

	@XmlElement(name="budget_type")
	private Short budget_type;

	@XmlElement(name="budget")
	private Double budget;

	@XmlElement(name="remain_budget")
	private Double remain_budget;

	@XmlElement(name="temp_budget")
	private Double temp_budget;

	@XmlElement(name="action")
	private Short action;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	public void setThresholdList(ThresholdList obj){
		this.thresholdList = obj;
	}

	public ThresholdList getThresholdList(){
		return thresholdList;
	}

	public void setBusi_service_id(Long obj){
		this.busi_service_id = obj;
	}

	public Long getBusi_service_id(){
		return busi_service_id;
	}

	public void setPayment_mode(Short obj){
		this.payment_mode = obj;
	}

	public Short getPayment_mode(){
		return payment_mode;
	}

	public void setBudget_type(Short obj){
		this.budget_type = obj;
	}

	public Short getBudget_type(){
		return budget_type;
	}

	public void setBudget(Double obj){
		this.budget = obj;
	}

	public Double getBudget(){
		return budget;
	}

	public void setRemain_budget(Double obj){
		this.remain_budget = obj;
	}

	public Double getRemain_budget(){
		return remain_budget;
	}

	public void setTemp_budget(Double obj){
		this.temp_budget = obj;
	}

	public Double getTemp_budget(){
		return temp_budget;
	}

	public void setAction(Short obj){
		this.action = obj;
	}

	public Short getAction(){
		return action;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BudgetControl rhs=(BudgetControl)rhs0;
		if(!ObjectUtils.equals(thresholdList, rhs.thresholdList)) return false;
		if(!ObjectUtils.equals(busi_service_id, rhs.busi_service_id)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(budget_type, rhs.budget_type)) return false;
		if(!ObjectUtils.equals(budget, rhs.budget)) return false;
		if(!ObjectUtils.equals(remain_budget, rhs.remain_budget)) return false;
		if(!ObjectUtils.equals(temp_budget, rhs.temp_budget)) return false;
		if(!ObjectUtils.equals(action, rhs.action)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(thresholdList)
		.append(busi_service_id)
		.append(payment_mode)
		.append(budget_type)
		.append(budget)
		.append(remain_budget)
		.append(temp_budget)
		.append(action)
		.append(measure_id)
		.toHashCode();
	}


}