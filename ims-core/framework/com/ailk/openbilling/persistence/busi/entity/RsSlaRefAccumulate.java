package com.ailk.openbilling.persistence.busi.entity;

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
@Table(schema="bd",name="RS_SLA_REF_ACCUMULATE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"accumulateItem","slaRefId","cycleType","cycleUnit"})
public class RsSlaRefAccumulate extends DataObject{


	/**
	 * accumulated charging item
	 */
	@Column(name="ACCUMULATE_ITEM",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="accumulateItem")
	private Integer accumulateItem;

	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="SLA_REF_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="slaRefId")
	private Integer slaRefId;

	/**
	 * Cycle types: 1:By calendar day  2:By calendar week  3:By calendar month
 4:By calendar year 5:By non-calendar month, similar to the function of
 add_months() in Oracle  6:By offset by hour  7:By offset by calendar month
 At present accumulated amount is stored by calendar day, minimum unit of a
 cycle
	 */
	@Column(name="CYCLE_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="cycleType")
	private Integer cycleType;

	/**
	 * Cycle unit, reference unit for accumulation
	 */
	@Column(name="CYCLE_UNIT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="cycleUnit")
	private Integer cycleUnit;

	public void setAccumulateItem(Integer obj){
		this.accumulateItem = obj;
	}

	public Integer getAccumulateItem(){
		return accumulateItem;
	}

	public void setSlaRefId(Integer obj){
		this.slaRefId = obj;
	}

	public Integer getSlaRefId(){
		return slaRefId;
	}

	public void setCycleType(Integer obj){
		this.cycleType = obj;
	}

	public Integer getCycleType(){
		return cycleType;
	}

	public void setCycleUnit(Integer obj){
		this.cycleUnit = obj;
	}

	public Integer getCycleUnit(){
		return cycleUnit;
	}

	public RsSlaRefAccumulate(){
	}

	public RsSlaRefAccumulate(Integer slaRefId){
		this.slaRefId = slaRefId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RsSlaRefAccumulate rhs=(RsSlaRefAccumulate)rhs0;
		if(!ObjectUtils.equals(slaRefId, rhs.slaRefId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(slaRefId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{accumulateItem,slaRefId,cycleType,cycleUnit}
}