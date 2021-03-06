package com.ailk.openbilling.persistence.cust.entity;

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
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="cd",name="IMS_PROD_CYCLE_NOTIFY")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productId","firstNotifyDate","notifyCount","nextNotifyDate","modifyDate","createDate","offerId","objectId","objectType","sts","validDate","expireDate","freeResFlag","dealDate","dealCount","errorCode","errorMsg","id"})
public class ImsProdCycleNotify extends DataObject{


	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="productId")
	private Long productId;

	@Column(name="FIRST_NOTIFY_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="firstNotifyDate")
	private Date firstNotifyDate;

	@Column(name="NOTIFY_COUNT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="notifyCount")
	private Integer notifyCount;

	@Column(name="NEXT_NOTIFY_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="nextNotifyDate")
	private Date nextNotifyDate;

	@Column(name="MODIFY_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="modifyDate")
	private Date modifyDate;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="OFFER_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="offerId")
	private Long offerId;

	@Column(name="OBJECT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectId")
	private Long objectId;

	@Column(name="OBJECT_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="objectType")
	private Integer objectType;

	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="FREE_RES_FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="freeResFlag")
	private Integer freeResFlag;

	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	@Column(name="DEAL_COUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="dealCount")
	private Integer dealCount;

	@Column(name="ERROR_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="errorCode")
	private Integer errorCode;

	@Column(name="ERROR_MSG",columnDefinition="Varchar",length=256)
	@XmlElement(name="errorMsg")
	private String errorMsg;

	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setFirstNotifyDate(Date obj){
		this.firstNotifyDate = obj;
	}

	public Date getFirstNotifyDate(){
		return firstNotifyDate;
	}

	public void setNotifyCount(Integer obj){
		this.notifyCount = obj;
	}

	public Integer getNotifyCount(){
		return notifyCount;
	}

	public void setNextNotifyDate(Date obj){
		this.nextNotifyDate = obj;
	}

	public Date getNextNotifyDate(){
		return nextNotifyDate;
	}

	public void setModifyDate(Date obj){
		this.modifyDate = obj;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setOfferId(Long obj){
		this.offerId = obj;
	}

	public Long getOfferId(){
		return offerId;
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

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
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

	public void setFreeResFlag(Integer obj){
		this.freeResFlag = obj;
	}

	public Integer getFreeResFlag(){
		return freeResFlag;
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

	public void setErrorCode(Integer obj){
		this.errorCode = obj;
	}

	public Integer getErrorCode(){
		return errorCode;
	}

	public void setErrorMsg(String obj){
		this.errorMsg = obj;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public ImsProdCycleNotify(){
	}

	public ImsProdCycleNotify(Long id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsProdCycleNotify rhs=(ImsProdCycleNotify)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{productId,firstNotifyDate,notifyCount,nextNotifyDate,modifyDate,createDate,offerId,objectId,objectType,sts,validDate,expireDate,freeResFlag,dealDate,dealCount,errorCode,errorMsg,id}
}