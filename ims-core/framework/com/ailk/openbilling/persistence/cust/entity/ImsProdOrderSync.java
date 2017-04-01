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
@Table(schema="cd",name="IMS_PROD_ORDER_SYNC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","custId","acctId","userId","phoneId","reasonCode","soNbr","sts","createDate","associateId","errorMsg","errorCode","dealCount","dealDate","srcAction"})
public class ImsProdOrderSync extends DataObject{


	/**
	 * ?bos??sequence?????
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	@Column(name="CUST_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="custId")
	private Long custId;

	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="acctId")
	private Long acctId;

	@Column(name="USER_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="userId")
	private Long userId;

	@Column(name="PHONE_ID",columnDefinition="Varchar",length=64)
	@XmlElement(name="phoneId")
	private String phoneId;

	@Column(name="REASON_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="reasonCode")
	private Integer reasonCode;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * 1:???? 2: ???
	 */
	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	/**
	 * create date
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="ASSOCIATE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="associateId")
	private Long associateId;

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

	/**
	 * 0-??1-????IMS????Reward??
2-BOS????????
3-????Reward product
4-????Upselling
5-????Reward freeresource
	 */
	@Column(name="SRC_ACTION",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="srcAction")
	private Integer srcAction;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setCustId(Long obj){
		this.custId = obj;
	}

	public Long getCustId(){
		return custId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setReasonCode(Integer obj){
		this.reasonCode = obj;
	}

	public Integer getReasonCode(){
		return reasonCode;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
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

	public void setAssociateId(Long obj){
		this.associateId = obj;
	}

	public Long getAssociateId(){
		return associateId;
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

	public void setSrcAction(Integer obj){
		this.srcAction = obj;
	}

	public Integer getSrcAction(){
		return srcAction;
	}

	public ImsProdOrderSync(){
	}

	public ImsProdOrderSync(Long id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsProdOrderSync rhs=(ImsProdOrderSync)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,custId,acctId,userId,phoneId,reasonCode,soNbr,sts,createDate,associateId,errorMsg,errorCode,dealCount,dealDate,srcAction}
}