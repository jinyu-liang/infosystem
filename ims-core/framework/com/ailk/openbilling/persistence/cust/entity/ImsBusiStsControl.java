package com.ailk.openbilling.persistence.cust.entity;

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
@Table(schema="CD",name="IMS_BUSI_STS_CONTROL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"busiCode","sts","billingType","objIndex","frauldFlag","userrequestFlag","osSts","objLevel"})
public class ImsBusiStsControl extends DataObject{


	/**
	 * BUSI_CODE
	 */
	@Id
	@Column(name="BUSI_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiCode")
	private Integer busiCode;

	/**
	 * users, accounts, customer status
	 */
	@Id
	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	/**
	 * 0:prepaid 1:postpaid 2:hybird -1:all
	 */
	@Id
	@Column(name="BILLING_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="billingType")
	private Integer billingType;

	/**
	 * OBJ_INDEX
	 */
	@Id
	@Column(name="OBJ_INDEX",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objIndex")
	private Integer objIndex;

	/**
	 * used for prepaid,hybird 1:yes 0:no
	 */
	@Id
	@Column(name="FRAULD_FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="frauldFlag")
	private Integer frauldFlag;

	/**
	 * just for prepaid 1:yes 0:no
	 */
	@Id
	@Column(name="USERREQUEST_FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="userrequestFlag")
	private Integer userrequestFlag;

	/**
	 * 10: nomal 11: out-going call barring 12: call barring -1:all
	 */
	@Id
	@Column(name="OS_STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="osSts")
	private Integer osSts;

	/**
	 * 0:USER 1:ACCOUNT 2:CUSTOMER
	 */
	@Id
	@Column(name="OBj_LEVEL",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objLevel")
	private Integer objLevel;

	public void setBusiCode(Integer obj){
		this.busiCode = obj;
	}

	public Integer getBusiCode(){
		return busiCode;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setBillingType(Integer obj){
		this.billingType = obj;
	}

	public Integer getBillingType(){
		return billingType;
	}

	public void setObjIndex(Integer obj){
		this.objIndex = obj;
	}

	public Integer getObjIndex(){
		return objIndex;
	}

	public void setFrauldFlag(Integer obj){
		this.frauldFlag = obj;
	}

	public Integer getFrauldFlag(){
		return frauldFlag;
	}

	public void setUserrequestFlag(Integer obj){
		this.userrequestFlag = obj;
	}

	public Integer getUserrequestFlag(){
		return userrequestFlag;
	}

	public void setOsSts(Integer obj){
		this.osSts = obj;
	}

	public Integer getOsSts(){
		return osSts;
	}

	public void setObjLevel(Integer obj){
		this.objLevel = obj;
	}

	public Integer getObjLevel(){
		return objLevel;
	}

	public ImsBusiStsControl(){
	}

	public ImsBusiStsControl(Integer busiCode,Integer sts,Integer billingType,Integer objIndex,Integer frauldFlag,Integer userrequestFlag,Integer osSts,Integer objLevel){
		this.busiCode = busiCode;
		this.sts = sts;
		this.billingType = billingType;
		this.objIndex = objIndex;
		this.frauldFlag = frauldFlag;
		this.userrequestFlag = userrequestFlag;
		this.osSts = osSts;
		this.objLevel = objLevel;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsBusiStsControl rhs=(ImsBusiStsControl)rhs0;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(userrequestFlag, rhs.userrequestFlag)) return false;
		if(!ObjectUtils.equals(objLevel, rhs.objLevel)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(osSts, rhs.osSts)) return false;
		if(!ObjectUtils.equals(frauldFlag, rhs.frauldFlag)) return false;
		if(!ObjectUtils.equals(objIndex, rhs.objIndex)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sts)
		.append(userrequestFlag)
		.append(objLevel)
		.append(billingType)
		.append(osSts)
		.append(frauldFlag)
		.append(objIndex)
		.append(busiCode)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{busiCode,sts,billingType,objIndex,frauldFlag,userrequestFlag,osSts,objLevel}
}