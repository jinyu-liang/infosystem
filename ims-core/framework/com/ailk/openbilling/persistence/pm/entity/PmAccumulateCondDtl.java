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
@Table(schema="PD",name="PM_ACCUMULATE_COND_DTL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"accumulateCondId","accumulateItem","threshold","alarmThreshold"})
public class PmAccumulateCondDtl extends DataObject{


	/**
	 * Identifier of the rule  ,that the rules was referenced by  accumulating.
	 */
	@Id
	@Column(name="ACCUMULATE_COND_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="accumulateCondId")
	private Integer accumulateCondId;

	/**
	 * Accumulating references charge item ,it can reference many items.
	 */
	@Id
	@Column(name="ACCUMULATE_ITEM",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="accumulateItem")
	private Integer accumulateItem;

	/**
	 * Threshold of accumulating references charge-item
	 */
	@Column(name="THRESHOLD",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="threshold")
	private Long threshold;

	/**
	 * Alarm threshold of accumulating references, when the accumulated referenced item has arrive at threshold, it will trigger PM_ACCUMULATE_PRICE.alarm
	 */
	@Column(name="ALARM_THRESHOLD",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="alarmThreshold")
	private Long alarmThreshold;

	public void setAccumulateCondId(Integer obj){
		this.accumulateCondId = obj;
	}

	public Integer getAccumulateCondId(){
		return accumulateCondId;
	}

	public void setAccumulateItem(Integer obj){
		this.accumulateItem = obj;
	}

	public Integer getAccumulateItem(){
		return accumulateItem;
	}

	public void setThreshold(Long obj){
		this.threshold = obj;
	}

	public Long getThreshold(){
		return threshold;
	}

	public void setAlarmThreshold(Long obj){
		this.alarmThreshold = obj;
	}

	public Long getAlarmThreshold(){
		return alarmThreshold;
	}

	public PmAccumulateCondDtl(){
	}

	public PmAccumulateCondDtl(Integer accumulateCondId,Integer accumulateItem){
		this.accumulateCondId = accumulateCondId;
		this.accumulateItem = accumulateItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmAccumulateCondDtl rhs=(PmAccumulateCondDtl)rhs0;
		if(!ObjectUtils.equals(accumulateCondId, rhs.accumulateCondId)) return false;
		if(!ObjectUtils.equals(accumulateItem, rhs.accumulateItem)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accumulateCondId)
		.append(accumulateItem)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{accumulateCondId,accumulateItem,threshold,alarmThreshold}
}