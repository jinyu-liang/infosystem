package com.ailk.openbilling.persistence.acct.entity;

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
 */
@NotModified
@Entity
@Table(schema="ad",name="CA_POCKET")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"assetId","pocketItem","validDate","sts","acctId","expireDate","amount","measureId","billingType","soNbr","createDate","soDate","resourceId","negativeFlag"})
public class CaPocket extends DataObject{


	@Column(name="ASSET_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="assetId")
	private Long assetId;

	@Column(name="POCKET_ITEM",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="pocketItem")
	private Integer pocketItem;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="STS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="acctId")
	private Long acctId;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="AMOUNT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="amount")
	private Long amount;

	@Column(name="MEASURE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="measureId")
	private Integer measureId;

	@Column(name="BILLING_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="billingType")
	private Integer billingType;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="NEGATIVE_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="negativeFlag")
	private Integer negativeFlag;

	public void setAssetId(Long obj){
		this.assetId = obj;
	}

	public Long getAssetId(){
		return assetId;
	}

	public void setPocketItem(Integer obj){
		this.pocketItem = obj;
	}

	public Integer getPocketItem(){
		return pocketItem;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public void setBillingType(Integer obj){
		this.billingType = obj;
	}

	public Integer getBillingType(){
		return billingType;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
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

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setNegativeFlag(Integer obj){
		this.negativeFlag = obj;
	}

	public Integer getNegativeFlag(){
		return negativeFlag;
	}

	public CaPocket(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaPocket rhs=(CaPocket)rhs0;
		if(!ObjectUtils.equals(assetId, rhs.assetId)) return false;
		if(!ObjectUtils.equals(pocketItem, rhs.pocketItem)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(billingType, rhs.billingType)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(negativeFlag, rhs.negativeFlag)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(assetId)
		.append(pocketItem)
		.append(validDate)
		.append(sts)
		.append(acctId)
		.append(expireDate)
		.append(amount)
		.append(measureId)
		.append(billingType)
		.append(soNbr)
		.append(createDate)
		.append(soDate)
		.append(resourceId)
		.append(negativeFlag)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{assetId,pocketItem,validDate,sts,acctId,expireDate,amount,measureId,billingType,soNbr,createDate,soDate,resourceId,negativeFlag}
}