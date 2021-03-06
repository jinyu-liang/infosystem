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
 */
@NotModified
@Entity
@Table(schema="cd",name="IMS_LOW_BALANCE_SYNC")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","custId","acctId","phoneId","autoType","payChannel","lowBalanceAmount","currentBalanceAmount","sts","createDate","soNbr","errorMsg","errorCode","dealCount","dealDate","topupAmount"})
public class ImsLowBalanceSync extends DataObject{


	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	/**
	 * ????id
	 */
	@Column(name="CUST_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="custId")
	private Long custId;

	/**
	 * ????id
	 */
	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="acctId")
	private Long acctId;

	/**
	 * ????
	 */
	@Column(name="PHONE_ID",columnDefinition="Varchar",length=64,nullable=false)
	@XmlElement(name="phoneId")
	private String phoneId;

	/**
	 * ??????: 1:Low balance auto topup
	 */
	@Column(name="AUTO_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="autoType")
	private Integer autoType;

	/**
	 * ????: 1:From credit 2:From bank account
	 */
	@Column(name="PAY_CHANNEL",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="payChannel")
	private Integer payChannel;

	/**
	 * ???
	 */
	@Column(name="LOW_BALANCE_AMOUNT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="lowBalanceAmount")
	private Long lowBalanceAmount;

	/**
	 * ????
	 */
	@Column(name="CURRENT_BALANCE_AMOUNT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="currentBalanceAmount")
	private Long currentBalanceAmount;

	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

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

	@Column(name="TOPUP_AMOUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="topupAmount")
	private Long topupAmount;

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

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setAutoType(Integer obj){
		this.autoType = obj;
	}

	public Integer getAutoType(){
		return autoType;
	}

	public void setPayChannel(Integer obj){
		this.payChannel = obj;
	}

	public Integer getPayChannel(){
		return payChannel;
	}

	public void setLowBalanceAmount(Long obj){
		this.lowBalanceAmount = obj;
	}

	public Long getLowBalanceAmount(){
		return lowBalanceAmount;
	}

	public void setCurrentBalanceAmount(Long obj){
		this.currentBalanceAmount = obj;
	}

	public Long getCurrentBalanceAmount(){
		return currentBalanceAmount;
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

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
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

	public void setTopupAmount(Long obj){
		this.topupAmount = obj;
	}

	public Long getTopupAmount(){
		return topupAmount;
	}

	public ImsLowBalanceSync(){
	}

	public ImsLowBalanceSync(Long id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsLowBalanceSync rhs=(ImsLowBalanceSync)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,custId,acctId,phoneId,autoType,payChannel,lowBalanceAmount,currentBalanceAmount,sts,createDate,soNbr,errorMsg,errorCode,dealCount,dealDate,topupAmount}
}