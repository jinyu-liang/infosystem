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
@Table(schema="PD",name="PM_ENUM_PROD_OFFER_TYPE_DEF")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"offerTypeId","name","description"})
public class PmEnumProdOfferTypeDef extends DataObject{


	/**
	 * Offering Types id

Value:
0: Simple Product Offering
1: Package Product Offering
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="OFFER_TYPE_ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="offerTypeId")
	private Integer offerTypeId;

	/**
	 * Types of Product Offering
	 */
	@Column(name="NAME",columnDefinition="Varchar",length=256,nullable=false)
	@XmlElement(name="name")
	private String name;

	/**
	 * Types Description
	 */
	@Column(name="DESCRIPTION",columnDefinition="Varchar",length=1024)
	@XmlElement(name="description")
	private String description;

	public void setOfferTypeId(Integer obj){
		this.offerTypeId = obj;
	}

	public Integer getOfferTypeId(){
		return offerTypeId;
	}

	public void setName(String obj){
		this.name = obj;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public PmEnumProdOfferTypeDef(){
	}

	public PmEnumProdOfferTypeDef(Integer offerTypeId){
		this.offerTypeId = offerTypeId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmEnumProdOfferTypeDef rhs=(PmEnumProdOfferTypeDef)rhs0;
		if(!ObjectUtils.equals(offerTypeId, rhs.offerTypeId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(offerTypeId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{offerTypeId,name,description}
}