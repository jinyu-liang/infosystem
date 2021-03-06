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
@Table(schema="CD",name="IMS_REWARD_FREERESOURCE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","productOfferingId","objectId","objectType","itmeId","measureId","amount","validDate","expireDate","soNbr"})
public class ImsRewardFreeresource extends DataObject{


	@Id
	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	@Column(name="PRODUCT_OFFERING_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="productOfferingId")
	private Integer productOfferingId;

	@Id
	@Column(name="OBJECT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectId")
	private Long objectId;

	@Id
	@Column(name="OBJECT_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectType")
	private Integer objectType;

	@Id
	@Column(name="ITME_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="itmeId")
	private Integer itmeId;

	@Column(name="MEASURE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="measureId")
	private Integer measureId;

	@Column(name="AMOUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="amount")
	private Long amount;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setProductOfferingId(Integer obj){
		this.productOfferingId = obj;
	}

	public Integer getProductOfferingId(){
		return productOfferingId;
	}

	public void setObjectId(Long obj){
		this.objectId = obj;
	}

	public Long getObjectId(){
		return objectId;
	}

	public void setObjectType(Integer obj){
		this.objectType = obj;
	}

	public Integer getObjectType(){
		return objectType;
	}

	public void setItmeId(Integer obj){
		this.itmeId = obj;
	}

	public Integer getItmeId(){
		return itmeId;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
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

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public ImsRewardFreeresource(){
	}

	public ImsRewardFreeresource(Long productId,Long objectId,Integer objectType,Integer itmeId){
		this.productId = productId;
		this.objectId = objectId;
		this.objectType = objectType;
		this.itmeId = itmeId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsRewardFreeresource rhs=(ImsRewardFreeresource)rhs0;
		if(!ObjectUtils.equals(objectId, rhs.objectId)) return false;
		if(!ObjectUtils.equals(itmeId, rhs.itmeId)) return false;
		if(!ObjectUtils.equals(objectType, rhs.objectType)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(objectId)
		.append(itmeId)
		.append(objectType)
		.append(productId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{productId,productOfferingId,objectId,objectType,itmeId,measureId,amount,validDate,expireDate,soNbr}
}