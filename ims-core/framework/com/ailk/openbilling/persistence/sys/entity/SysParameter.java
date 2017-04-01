package com.ailk.openbilling.persistence.sys.entity;

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
@Table(schema="sd",name="SYS_PARAMETER")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"paramCode","paramName","paramClass","subClass","paramDataType","paramValue","paramDesc","extClass"})
public class SysParameter extends DataObject{


	/**
	 * Parameter code, configured with characters and by users for certain meaning. Some universal parameters are configured by unified configuration.
	 */
	@Id
	@Column(name="PARAM_CODE",columnDefinition="Varchar",length=32,nullable=false)
	@XmlElement(name="paramCode")
	private String paramCode;

	/**
	 * Parameter name
	 */
	@Column(name="PARAM_NAME",columnDefinition="Varchar",length=256,nullable=false)
	@XmlElement(name="paramName")
	private String paramName;

	/**
	 * Parameter class

Sub systems to which basic system parameters belong:1 －－ Universal system parameter  2 －－ Rating  3 －－ Product Management  4 －－Accounting  5 －－ Accounting Management   6 －－Information Management
	 */
	@Id
	@Column(name="PARAM_CLASS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="paramClass")
	private Integer paramClass;

	/**
	 * Sub modules defined in each sub system, configured by each module.
	 */
	@Id
	@Column(name="SUB_CLASS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="subClass")
	private Integer subClass;

	/**
	 * Parameter data types:
0--Default
1--Char
2--Number
3--Boolean
4--Date
5--Long
6--String
7--Double

Remark: Default 0, analysed and configured by each module.
	 */
	@Column(name="PARAM_DATA_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="paramDataType")
	private Integer paramDataType;

	/**
	 * Parameter value
	 */
	@Column(name="PARAM_VALUE",columnDefinition="Varchar",length=1024,nullable=false)
	@XmlElement(name="paramValue")
	private String paramValue;

	/**
	 * Parameter descriptive information, parameter value range or parameter configuration reason is mainly filled in the field
	 */
	@Column(name="PARAM_DESC",columnDefinition="Varchar",length=1024)
	@XmlElement(name="paramDesc")
	private String paramDesc;

	@Id
	@Column(name="EXT_CLASS",columnDefinition="Varchar",length=256,nullable=false)
	@XmlElement(name="extClass")
	private String extClass;

	public void setParamCode(String obj){
		this.paramCode = obj;
	}

	public String getParamCode(){
		return paramCode;
	}

	public void setParamName(String obj){
		this.paramName = obj;
	}

	public String getParamName(){
		return paramName;
	}

	public void setParamClass(Integer obj){
		this.paramClass = obj;
	}

	public Integer getParamClass(){
		return paramClass;
	}

	public void setSubClass(Integer obj){
		this.subClass = obj;
	}

	public Integer getSubClass(){
		return subClass;
	}

	public void setParamDataType(Integer obj){
		this.paramDataType = obj;
	}

	public Integer getParamDataType(){
		return paramDataType;
	}

	public void setParamValue(String obj){
		this.paramValue = obj;
	}

	public String getParamValue(){
		return paramValue;
	}

	public void setParamDesc(String obj){
		this.paramDesc = obj;
	}

	public String getParamDesc(){
		return paramDesc;
	}

	public void setExtClass(String obj){
		this.extClass = obj;
	}

	public String getExtClass(){
		return extClass;
	}

	public SysParameter(){
	}

	public SysParameter(String paramCode,Integer paramClass,Integer subClass,String extClass){
		this.paramCode = paramCode;
		this.paramClass = paramClass;
		this.subClass = subClass;
		this.extClass = extClass;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SysParameter rhs=(SysParameter)rhs0;
		if(!ObjectUtils.equals(subClass, rhs.subClass)) return false;
		if(!ObjectUtils.equals(extClass, rhs.extClass)) return false;
		if(!ObjectUtils.equals(paramClass, rhs.paramClass)) return false;
		if(!ObjectUtils.equals(paramCode, rhs.paramCode)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(subClass)
		.append(extClass)
		.append(paramClass)
		.append(paramCode)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{paramCode,paramName,paramClass,subClass,paramDataType,paramValue,paramDesc,extClass}
}