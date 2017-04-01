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
@Table(schema="PD",name="PM_BUSI_TYPE_RULE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"busiType","isTruncPre"})
public class PmBusiTypeRule extends DataObject{


	@Id
	@Column(name="BUSI_TYPE",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiType")
	private Integer busiType;

	@Column(name="IS_TRUNC_PRE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="isTruncPre")
	private Integer isTruncPre;

	public void setBusiType(Integer obj){
		this.busiType = obj;
	}

	public Integer getBusiType(){
		return busiType;
	}

	public void setIsTruncPre(Integer obj){
		this.isTruncPre = obj;
	}

	public Integer getIsTruncPre(){
		return isTruncPre;
	}

	public PmBusiTypeRule(){
	}

	public PmBusiTypeRule(Integer busiType){
		this.busiType = busiType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PmBusiTypeRule rhs=(PmBusiTypeRule)rhs0;
		if(!ObjectUtils.equals(busiType, rhs.busiType)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busiType)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{busiType,isTruncPre}
}