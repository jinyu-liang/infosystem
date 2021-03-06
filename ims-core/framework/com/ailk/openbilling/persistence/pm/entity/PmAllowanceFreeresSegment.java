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
 * 
 */
@NotModified
@Entity
@Table(schema="pd",name="PM_ALLOWANCE_FREERES_SEGMENT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"priceId","startValue","endValue","amount"})
public class PmAllowanceFreeresSegment extends DataObject{


	/**
	 * pricing identifier
	 */
	@Id
	@Column(name="PRICE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="priceId")
	private Integer priceId;

	/**
	 * Cycles of starting rating
开始周期，必须从1开始。约束：前闭后开。
	 */
	@Id
	@Column(name="START_VALUE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="startValue")
	private Integer startValue;

	/**
	 * 结束周期，必须以-1结束。约束：前闭后开。
	 */
	@Column(name="END_VALUE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="endValue")
	private Integer endValue;

	@Column(name="AMOUNT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="amount")
	private Long amount;

	public void setPriceId(Integer obj){
		this.priceId = obj;
	}

	public Integer getPriceId(){
		return priceId;
	}

	public void setStartValue(Integer obj){
		this.startValue = obj;
	}

	public Integer getStartValue(){
		return startValue;
	}

	public void setEndValue(Integer obj){
		this.endValue = obj;
	}

	public Integer getEndValue(){
		return endValue;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public PmAllowanceFreeresSegment(){
	}

	public PmAllowanceFreeresSegment(Integer priceId,Integer startValue){
		this.priceId = priceId;
		this.startValue = startValue;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmAllowanceFreeresSegment rhs=(PmAllowanceFreeresSegment)rhs0;
		if(!ObjectUtils.equals(startValue, rhs.startValue)) return false;
		if(!ObjectUtils.equals(priceId, rhs.priceId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(startValue)
		.append(priceId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{priceId,startValue,endValue,amount}
}