package com.ailk.openbilling.persistence.pm.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
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
 */
@NotModified
@Entity
@Table(schema="PD",name="PM_USAGE_ACCUMULATE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"priceRuleId","accumulateCondId"})
public class PmUsageAccumulate extends DataObject{


	/**
	 * rating rule ID
	 */
	@Id
	@Column(name="PRICE_RULE_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="priceRuleId")
	private Integer priceRuleId;

	/**
	 * Identifier of the rule  ,that the rules was referenced by  accumulating.
	 */
	@Column(name="ACCUMULATE_COND_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="accumulateCondId")
	private Integer accumulateCondId;

	public void setPriceRuleId(Integer obj){
		this.priceRuleId = obj;
	}

	public Integer getPriceRuleId(){
		return priceRuleId;
	}

	public void setAccumulateCondId(Integer obj){
		this.accumulateCondId = obj;
	}

	public Integer getAccumulateCondId(){
		return accumulateCondId;
	}

	public PmUsageAccumulate(){
	}

	public PmUsageAccumulate(Integer priceRuleId){
		this.priceRuleId = priceRuleId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmUsageAccumulate rhs=(PmUsageAccumulate)rhs0;
		if(!ObjectUtils.equals(priceRuleId, rhs.priceRuleId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(priceRuleId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{priceRuleId,accumulateCondId}
}