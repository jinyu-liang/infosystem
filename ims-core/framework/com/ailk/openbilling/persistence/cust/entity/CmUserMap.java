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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 */
@NotModified
@Entity
@Table(schema="CD",name="CM_USER_MAP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resourceId","imsi","msisdn","imsi2","msisdn2","busiType","regionCode","createDate","soDate","soNbr","validDate","expireDate"})
public class CmUserMap extends DataObject{


	@Id
	@Column(name="RESOURCE_ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="IMSI",columnDefinition="Varchar",length=64)
	@XmlElement(name="imsi")
	private String imsi;

	@Column(name="MSISDN",columnDefinition="Varchar",length=64)
	@XmlElement(name="msisdn")
	private String msisdn;

	@Column(name="IMSI2",columnDefinition="Varchar",length=64)
	@XmlElement(name="imsi2")
	private String imsi2;

	@Column(name="MSISDN2",columnDefinition="Varchar",length=64)
	@XmlElement(name="msisdn2")
	private String msisdn2;

	@Column(name="BUSI_TYPE",precision=4,columnDefinition="NUMBER")
	@XmlElement(name="busiType")
	private Integer busiType;

	@Column(name="REGION_CODE",precision=4,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Id
	@Column(name="SO_NBR",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Id
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setImsi(String obj){
		this.imsi = obj;
	}

	public String getImsi(){
		return imsi;
	}

	public void setMsisdn(String obj){
		this.msisdn = obj;
	}

	public String getMsisdn(){
		return msisdn;
	}

	public void setImsi2(String obj){
		this.imsi2 = obj;
	}

	public String getImsi2(){
		return imsi2;
	}

	public void setMsisdn2(String obj){
		this.msisdn2 = obj;
	}

	public String getMsisdn2(){
		return msisdn2;
	}

	public void setBusiType(Integer obj){
		this.busiType = obj;
	}

	public Integer getBusiType(){
		return busiType;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public CmUserMap(){
	}

	public CmUserMap(Long resourceId,Long soNbr,Date validDate){
		this.resourceId = resourceId;
		this.soNbr = soNbr;
		this.validDate = validDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CmUserMap rhs=(CmUserMap)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(validDate)
		.append(soNbr)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{resourceId,imsi,msisdn,imsi2,msisdn2,busiType,regionCode,createDate,soDate,soNbr,validDate,expireDate}
}