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
@Table(schema="cd",name="IMS_CREDIT_STATUS_SYNC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","phoneId","flag","oldStatus","oldSubType","newStatus","newSubType","creditLimit","tempCreditLimit","overUsage","triggerDate","soNbr","createDate","sts","errorMsg","errorCode","dealCount","dealDate"})
public class ImsCreditStatusSync extends DataObject{


	/**
	 * ??
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	/**
	 * PHONE_ID
	 */
	@Column(name="PHONE_ID",columnDefinition="Varchar",length=64,nullable=false)
	@XmlElement(name="phoneId")
	private String phoneId;

	/**
	 * FLAG
	 */
	@Column(name="FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="flag")
	private Integer flag;

	/**
	 * OLD_STATUS
	 */
	@Column(name="OLD_STATUS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="oldStatus")
	private Integer oldStatus;

	/**
	 * OLD_SUB_TYPE
	 */
	@Column(name="OLD_SUB_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="oldSubType")
	private Integer oldSubType;

	/**
	 * NEW_STATUS
	 */
	@Column(name="NEW_STATUS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="newStatus")
	private Integer newStatus;

	/**
	 * NEW_SUB_TYPE
	 */
	@Column(name="NEW_SUB_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="newSubType")
	private Integer newSubType;

	/**
	 * CREDIT_LIMIT
	 */
	@Column(name="CREDIT_LIMIT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="creditLimit")
	private Long creditLimit;

	/**
	 * TEMP_CREDIT_LIMIT
	 */
	@Column(name="TEMP_CREDIT_LIMIT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="tempCreditLimit")
	private Long tempCreditLimit;

	/**
	 * OVER_USAGE
	 */
	@Column(name="OVER_USAGE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="overUsage")
	private Long overUsage;

	/**
	 * TRIGGER_DATE
	 */
	@Column(name="TRIGGER_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="triggerDate")
	private Date triggerDate;

	/**
	 * SO_NBR
	 */
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * ????
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	/**
	 * ????
	 */
	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="ERROR_MSG",columnDefinition="Varchar",length=256)
	@XmlElement(name="errorMsg")
	private String errorMsg;

	@Column(name="ERROR_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="errorCode")
	private Integer errorCode;

	@Column(name="DEAL_COUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="dealCount")
	private Integer dealCount;

	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setFlag(Integer obj){
		this.flag = obj;
	}

	public Integer getFlag(){
		return flag;
	}

	public void setOldStatus(Integer obj){
		this.oldStatus = obj;
	}

	public Integer getOldStatus(){
		return oldStatus;
	}

	public void setOldSubType(Integer obj){
		this.oldSubType = obj;
	}

	public Integer getOldSubType(){
		return oldSubType;
	}

	public void setNewStatus(Integer obj){
		this.newStatus = obj;
	}

	public Integer getNewStatus(){
		return newStatus;
	}

	public void setNewSubType(Integer obj){
		this.newSubType = obj;
	}

	public Integer getNewSubType(){
		return newSubType;
	}

	public void setCreditLimit(Long obj){
		this.creditLimit = obj;
	}

	public Long getCreditLimit(){
		return creditLimit;
	}

	public void setTempCreditLimit(Long obj){
		this.tempCreditLimit = obj;
	}

	public Long getTempCreditLimit(){
		return tempCreditLimit;
	}

	public void setOverUsage(Long obj){
		this.overUsage = obj;
	}

	public Long getOverUsage(){
		return overUsage;
	}

	public void setTriggerDate(Date obj){
		this.triggerDate = obj;
	}

	public Date getTriggerDate(){
		return triggerDate;
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

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
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

	public void setDealCount(Integer obj){
		this.dealCount = obj;
	}

	public Integer getDealCount(){
		return dealCount;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public ImsCreditStatusSync(){
	}

	public ImsCreditStatusSync(Long id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsCreditStatusSync rhs=(ImsCreditStatusSync)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,phoneId,flag,oldStatus,oldSubType,newStatus,newSubType,creditLimit,tempCreditLimit,overUsage,triggerDate,soNbr,createDate,sts,errorMsg,errorCode,dealCount,dealDate}
}