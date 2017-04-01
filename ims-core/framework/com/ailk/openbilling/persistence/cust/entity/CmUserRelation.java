package com.ailk.openbilling.persistence.cust.entity;

import javax.persistence.Table;
import jef.database.DataObject;
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
@Table(schema="CD",name="CM_USER_RELATION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resourceId","regionCode","busiType","rResourceId","rRegionCode","validDate","expireDate","createDate","soDate","remark","property","productId","soNbr"})
public class CmUserRelation extends DataObject{


	/**
	 * RESOURCE_ID
	 */
	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="regionCode")
	private Integer regionCode;

	/**
	 * 1:ADC
2:MAS
3:????
	 */
	@Column(name="BUSI_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="busiType")
	private Integer busiType;

	/**
	 * RESOURCE_ID
	 */
	@Column(name="RRESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="rResourceId")
	private Long rResourceId;

	@Column(name="RREGION_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="rRegionCode")
	private Integer rRegionCode;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="REMARK",columnDefinition="Varchar",length=256,nullable=false)
	@XmlElement(name="remark")
	private String remark;

	@Column(name="PROPERTY",columnDefinition="Varchar",length=32,nullable=false)
	@XmlElement(name="property")
	private String property;

	/**
	 * RESOURCE_ID
	 */
	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	@Column(name="SO_NBR",columnDefinition="Varchar",length=40)
	@XmlElement(name="soNbr")
	private String soNbr;

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setBusiType(Integer obj){
		this.busiType = obj;
	}

	public Integer getBusiType(){
		return busiType;
	}

	public void setRResourceId(Long obj){
		this.rResourceId = obj;
	}

	public Long getRResourceId(){
		return rResourceId;
	}

	public void setRRegionCode(Integer obj){
		this.rRegionCode = obj;
	}

	public Integer getRRegionCode(){
		return rRegionCode;
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

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setProperty(String obj){
		this.property = obj;
	}

	public String getProperty(){
		return property;
	}

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setSoNbr(String obj){
		this.soNbr = obj;
	}

	public String getSoNbr(){
		return soNbr;
	}

	public CmUserRelation(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CmUserRelation rhs=(CmUserRelation)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(busiType, rhs.busiType)) return false;
		if(!ObjectUtils.equals(rResourceId, rhs.rResourceId)) return false;
		if(!ObjectUtils.equals(rRegionCode, rhs.rRegionCode)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(property, rhs.property)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(regionCode)
		.append(busiType)
		.append(rResourceId)
		.append(rRegionCode)
		.append(validDate)
		.append(expireDate)
		.append(createDate)
		.append(soDate)
		.append(remark)
		.append(property)
		.append(productId)
		.append(soNbr)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{resourceId,regionCode,busiType,rResourceId,rRegionCode,validDate,expireDate,createDate,soDate,remark,property,productId,soNbr}
}