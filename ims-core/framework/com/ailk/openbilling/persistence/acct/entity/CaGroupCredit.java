package com.ailk.openbilling.persistence.acct.entity;

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
@Table(schema="AD",name="CA_GROUP_CREDIT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"groupId","creditClass","creditValue"})
public class CaGroupCredit extends DataObject{


	/**
	 * group ID
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="GROUP_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="groupId")
	private Integer groupId;

	/**
	 * used for mapping foreign data
	 */
	@Column(name="CREDIT_CLASS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="creditClass")
	private Integer creditClass;

	/**
	 * amount of credit limit
	 */
	@Column(name="CREDIT_VALUE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="creditValue")
	private Long creditValue;

	public void setGroupId(Integer obj){
		this.groupId = obj;
	}

	public Integer getGroupId(){
		return groupId;
	}

	public void setCreditClass(Integer obj){
		this.creditClass = obj;
	}

	public Integer getCreditClass(){
		return creditClass;
	}

	public void setCreditValue(Long obj){
		this.creditValue = obj;
	}

	public Long getCreditValue(){
		return creditValue;
	}

	public CaGroupCredit(){
	}

	public CaGroupCredit(Integer groupId){
		this.groupId = groupId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaGroupCredit rhs=(CaGroupCredit)rhs0;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(groupId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{groupId,creditClass,creditValue}
}