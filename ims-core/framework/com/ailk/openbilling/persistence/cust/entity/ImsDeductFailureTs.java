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
@Table(schema="cd",name="IMS_DEDUCT_FAILURE_TS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","failureRuleId","resourceId","validDate","sts","createDate","dealDate","dealCount","errorMsg","errorCode"})
public class ImsDeductFailureTs extends DataObject{


	/**
	 * ?????????
	 */
	@Id
	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	/**
	 * ????ID,??PM_RENT_DEDUCT_ACTION
	 */
	@Id
	@Column(name="FAILURE_RULE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="failureRuleId")
	private Integer failureRuleId;

	/**
	 * ??ID
	 */
	@Id
	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	/**
	 * ????
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	@Column(name="DEAL_COUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="dealCount")
	private Integer dealCount;

	@Column(name="ERROR_MSG",columnDefinition="Varchar",length=256)
	@XmlElement(name="errorMsg")
	private String errorMsg;

	@Column(name="ERROR_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="errorCode")
	private Integer errorCode;

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setFailureRuleId(Integer obj){
		this.failureRuleId = obj;
	}

	public Integer getFailureRuleId(){
		return failureRuleId;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
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

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setDealCount(Integer obj){
		this.dealCount = obj;
	}

	public Integer getDealCount(){
		return dealCount;
	}

	public void setErrorMsg(String obj){
		this.errorMsg = obj;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public void setErrorCode(Integer obj){
		this.errorCode = obj;
	}

	public Integer getErrorCode(){
		return errorCode;
	}

	public ImsDeductFailureTs(){
	}

	public ImsDeductFailureTs(Long productId,Integer failureRuleId,Long resourceId){
		this.productId = productId;
		this.failureRuleId = failureRuleId;
		this.resourceId = resourceId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsDeductFailureTs rhs=(ImsDeductFailureTs)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(failureRuleId, rhs.failureRuleId)) return false;
		if(!ObjectUtils.equals(productId, rhs.productId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(failureRuleId)
		.append(productId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{productId,failureRuleId,resourceId,validDate,sts,createDate,dealDate,dealCount,errorMsg,errorCode}
}