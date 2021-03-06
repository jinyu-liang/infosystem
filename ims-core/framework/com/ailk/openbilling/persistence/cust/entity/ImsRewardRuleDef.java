package com.ailk.openbilling.persistence.cust.entity;

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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="CD",name="IMS_REWARD_RULE_DEF")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"limitId","limitType","value","createDate"})
public class ImsRewardRuleDef extends DataObject{


	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="LIMIT_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="limitId")
	private Long limitId;

	@Column(name="LIMIT_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="limitType")
	private Integer limitType;

	@Column(name="VALUE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="value")
	private Integer value;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	public void setLimitId(Long obj){
		this.limitId = obj;
	}

	public Long getLimitId(){
		return limitId;
	}

	public void setLimitType(Integer obj){
		this.limitType = obj;
	}

	public Integer getLimitType(){
		return limitType;
	}

	public void setValue(Integer obj){
		this.value = obj;
	}

	public Integer getValue(){
		return value;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public ImsRewardRuleDef(){
	}

	public ImsRewardRuleDef(Long limitId){
		this.limitId = limitId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsRewardRuleDef rhs=(ImsRewardRuleDef)rhs0;
		if(!ObjectUtils.equals(limitId, rhs.limitId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(limitId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{limitId,limitType,value,createDate}
}