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
@Table(schema="cd",name="IMS_RES_STS_SYNC_RST")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","nbalance","pbalance","capMaxFlag","reasonCode","nsts","psts","psubType","nsubType","pfrauldFlag","nfrauldFlag","puserrequestFlag","nuserrequestFlag","nexpireDate","pexpireDate","phoneId","acctId","resourceId","failReason","soNbr","dealDate","sts","dealDesc","dealAgainDate","createDate","errorMsg","errorCode","dealCount","notifyFlag","changeTime"})
public class ImsResStsSyncRst extends DataObject{


	/**
	 * Business data table ID
	 */
	@Column(name="ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	/**
	 * The flag of last try, if not try, last_try_flag=1,otherwise flag=0 (default)
	 */
	@Column(name="NBALANCE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="nbalance")
	private Long nbalance;

	@Column(name="PBALANCE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="pbalance")
	private Long pbalance;

	/**
	 * If the IR Data usage reach the CAP, Cap_max_flag=1,default 0
	 */
	@Column(name="CAP_MAX_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="capMaxFlag")
	private Long capMaxFlag;

	/**
	 * Reason for status change
0-  no balance
1-  validity expired
2-  hit credit limit
3-  expired from suspend one way
	 */
	@Column(name="REASON_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="reasonCode")
	private Long reasonCode;

	@Column(name="NSTS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="nsts")
	private Integer nsts;

	@Column(name="PSTS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="psts")
	private Integer psts;

	@Column(name="PSUB_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="psubType")
	private Integer psubType;

	@Column(name="NSUB_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="nsubType")
	private Integer nsubType;

	@Column(name="PFRAULD_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="pfrauldFlag")
	private Integer pfrauldFlag;

	@Column(name="NFRAULD_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="nfrauldFlag")
	private Integer nfrauldFlag;

	@Column(name="PUSERREQUEST_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="puserrequestFlag")
	private Integer puserrequestFlag;

	@Column(name="NUSERREQUEST_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="nuserrequestFlag")
	private Integer nuserrequestFlag;

	@Column(name="NEXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="nexpireDate")
	private Date nexpireDate;

	@Column(name="PEXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="pexpireDate")
	private Date pexpireDate;

	@Column(name="PHONE_ID",columnDefinition="Varchar",length=30)
	@XmlElement(name="phoneId")
	private String phoneId;

	/**
	 * ????
	 */
	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="acctId")
	private Long acctId;

	/**
	 * ????
	 */
	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="FAIL_REASON",columnDefinition="Varchar",length=255)
	@XmlElement(name="failReason")
	private String failReason;

	/**
	 * Serial number
	 */
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * Deal date
	 */
	@Column(name="DEAL_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="dealDate")
	private Date dealDate;

	/**
	 * 1:succeeded 2:failed 3:has been processed again
	 */
	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	/**
	 * Save failure information when failed
	 */
	@Column(name="DEAL_DESC",columnDefinition="Varchar",length=255)
	@XmlElement(name="dealDesc")
	private String dealDesc;

	/**
	 * Re-processing date of failure record
	 */
	@Column(name="DEAL_AGAIN_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealAgainDate")
	private Date dealAgainDate;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="ERROR_MSG",columnDefinition="Varchar",length=256)
	@XmlElement(name="errorMsg")
	private String errorMsg;

	@Column(name="ERROR_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="errorCode")
	private Integer errorCode;

	@Column(name="DEAL_COUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="dealCount")
	private Integer dealCount;

	@Column(name="NOTIFY_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="notifyFlag")
	private Integer notifyFlag;

	@Column(name="CHANGE_TIME",columnDefinition="TimeStamp")
	@XmlElement(name="changeTime")
	private Date changeTime;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setNbalance(Long obj){
		this.nbalance = obj;
	}

	public Long getNbalance(){
		return nbalance;
	}

	public void setPbalance(Long obj){
		this.pbalance = obj;
	}

	public Long getPbalance(){
		return pbalance;
	}

	public void setCapMaxFlag(Long obj){
		this.capMaxFlag = obj;
	}

	public Long getCapMaxFlag(){
		return capMaxFlag;
	}

	public void setReasonCode(Long obj){
		this.reasonCode = obj;
	}

	public Long getReasonCode(){
		return reasonCode;
	}

	public void setNsts(Integer obj){
		this.nsts = obj;
	}

	public Integer getNsts(){
		return nsts;
	}

	public void setPsts(Integer obj){
		this.psts = obj;
	}

	public Integer getPsts(){
		return psts;
	}

	public void setPsubType(Integer obj){
		this.psubType = obj;
	}

	public Integer getPsubType(){
		return psubType;
	}

	public void setNsubType(Integer obj){
		this.nsubType = obj;
	}

	public Integer getNsubType(){
		return nsubType;
	}

	public void setPfrauldFlag(Integer obj){
		this.pfrauldFlag = obj;
	}

	public Integer getPfrauldFlag(){
		return pfrauldFlag;
	}

	public void setNfrauldFlag(Integer obj){
		this.nfrauldFlag = obj;
	}

	public Integer getNfrauldFlag(){
		return nfrauldFlag;
	}

	public void setPuserrequestFlag(Integer obj){
		this.puserrequestFlag = obj;
	}

	public Integer getPuserrequestFlag(){
		return puserrequestFlag;
	}

	public void setNuserrequestFlag(Integer obj){
		this.nuserrequestFlag = obj;
	}

	public Integer getNuserrequestFlag(){
		return nuserrequestFlag;
	}

	public void setNexpireDate(Date obj){
		this.nexpireDate = obj;
	}

	public Date getNexpireDate(){
		return nexpireDate;
	}

	public void setPexpireDate(Date obj){
		this.pexpireDate = obj;
	}

	public Date getPexpireDate(){
		return pexpireDate;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setFailReason(String obj){
		this.failReason = obj;
	}

	public String getFailReason(){
		return failReason;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setDealDesc(String obj){
		this.dealDesc = obj;
	}

	public String getDealDesc(){
		return dealDesc;
	}

	public void setDealAgainDate(Date obj){
		this.dealAgainDate = obj;
	}

	public Date getDealAgainDate(){
		return dealAgainDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
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

	public void setNotifyFlag(Integer obj){
		this.notifyFlag = obj;
	}

	public Integer getNotifyFlag(){
		return notifyFlag;
	}

	public void setChangeTime(Date obj){
		this.changeTime = obj;
	}

	public Date getChangeTime(){
		return changeTime;
	}

	public ImsResStsSyncRst(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsResStsSyncRst rhs=(ImsResStsSyncRst)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(nbalance, rhs.nbalance)) return false;
		if(!ObjectUtils.equals(pbalance, rhs.pbalance)) return false;
		if(!ObjectUtils.equals(capMaxFlag, rhs.capMaxFlag)) return false;
		if(!ObjectUtils.equals(reasonCode, rhs.reasonCode)) return false;
		if(!ObjectUtils.equals(nsts, rhs.nsts)) return false;
		if(!ObjectUtils.equals(psts, rhs.psts)) return false;
		if(!ObjectUtils.equals(psubType, rhs.psubType)) return false;
		if(!ObjectUtils.equals(nsubType, rhs.nsubType)) return false;
		if(!ObjectUtils.equals(pfrauldFlag, rhs.pfrauldFlag)) return false;
		if(!ObjectUtils.equals(nfrauldFlag, rhs.nfrauldFlag)) return false;
		if(!ObjectUtils.equals(puserrequestFlag, rhs.puserrequestFlag)) return false;
		if(!ObjectUtils.equals(nuserrequestFlag, rhs.nuserrequestFlag)) return false;
		if(!ObjectUtils.equals(nexpireDate, rhs.nexpireDate)) return false;
		if(!ObjectUtils.equals(pexpireDate, rhs.pexpireDate)) return false;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(failReason, rhs.failReason)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(dealDate, rhs.dealDate)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(dealDesc, rhs.dealDesc)) return false;
		if(!ObjectUtils.equals(dealAgainDate, rhs.dealAgainDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(errorMsg, rhs.errorMsg)) return false;
		if(!ObjectUtils.equals(errorCode, rhs.errorCode)) return false;
		if(!ObjectUtils.equals(dealCount, rhs.dealCount)) return false;
		if(!ObjectUtils.equals(notifyFlag, rhs.notifyFlag)) return false;
		if(!ObjectUtils.equals(changeTime, rhs.changeTime)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(nbalance)
		.append(pbalance)
		.append(capMaxFlag)
		.append(reasonCode)
		.append(nsts)
		.append(psts)
		.append(psubType)
		.append(nsubType)
		.append(pfrauldFlag)
		.append(nfrauldFlag)
		.append(puserrequestFlag)
		.append(nuserrequestFlag)
		.append(nexpireDate)
		.append(pexpireDate)
		.append(phoneId)
		.append(acctId)
		.append(resourceId)
		.append(failReason)
		.append(soNbr)
		.append(dealDate)
		.append(sts)
		.append(dealDesc)
		.append(dealAgainDate)
		.append(createDate)
		.append(errorMsg)
		.append(errorCode)
		.append(dealCount)
		.append(notifyFlag)
		.append(changeTime)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,nbalance,pbalance,capMaxFlag,reasonCode,nsts,psts,psubType,nsubType,pfrauldFlag,nfrauldFlag,puserrequestFlag,nuserrequestFlag,nexpireDate,pexpireDate,phoneId,acctId,resourceId,failReason,soNbr,dealDate,sts,dealDesc,dealAgainDate,createDate,errorMsg,errorCode,dealCount,notifyFlag,changeTime}
}