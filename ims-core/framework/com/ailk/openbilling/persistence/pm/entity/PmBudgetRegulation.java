package com.ailk.openbilling.persistence.pm.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="PD",name="PM_BUDGET_REGULATION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"priceRuleId","measureId","precisionRound","budgetType"})
public class PmBudgetRegulation extends DataObject{


	/**
	 * Budget identifier
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="PRICE_RULE_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="priceRuleId")
	private Integer priceRuleId;

	/**
	 * Budget limit measurement unit
	 */
	@Column(name="MEASURE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="measureId")
	private Integer measureId;

	/**
	 * 1: Rounding up
2; Rounding down
3: Rounding to the nearest
	 */
	@Column(name="PRECISION_ROUND",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="precisionRound")
	private Integer precisionRound;

	/**
	 * 0:计费预算
1：账务全业务预算 
2：账务分业务预算
	 */
	@Column(name="BUDGET_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="budgetType")
	private Integer budgetType;

	public void setPriceRuleId(Integer obj){
		this.priceRuleId = obj;
	}

	public Integer getPriceRuleId(){
		return priceRuleId;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public void setPrecisionRound(Integer obj){
		this.precisionRound = obj;
	}

	public Integer getPrecisionRound(){
		return precisionRound;
	}

	public void setBudgetType(Integer obj){
		this.budgetType = obj;
	}

	public Integer getBudgetType(){
		return budgetType;
	}

	public PmBudgetRegulation(){
	}

	public PmBudgetRegulation(Integer priceRuleId){
		this.priceRuleId = priceRuleId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmBudgetRegulation rhs=(PmBudgetRegulation)rhs0;
		if(!ObjectUtils.equals(priceRuleId, rhs.priceRuleId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(priceRuleId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{priceRuleId,measureId,precisionRound,budgetType}
}