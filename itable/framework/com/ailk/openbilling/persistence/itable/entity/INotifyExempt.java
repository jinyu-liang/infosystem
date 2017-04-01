package com.ailk.openbilling.persistence.itable.entity;

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
@Table(schema="JD",name="I_NOTIFY_EXEMPT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"userId","acctId","urgeStopFlag","validDate","expireDate","operType","soNbr","commitDate","officePhone","recType"})
public class INotifyExempt extends DataObject{


	@Column(name="USER_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="userId")
	private Long userId;

	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="acctId")
	private Long acctId;

	@Column(name="URGE_STOP_FLAG",columnDefinition="Varchar",length=32)
	@XmlElement(name="urgeStopFlag")
	private String urgeStopFlag;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="OPER_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="operType")
	private Integer operType;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="COMMIT_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="commitDate")
	private Date commitDate;

	@Column(name="OFFICE_PHONE",columnDefinition="Varchar",length=28)
	@XmlElement(name="officePhone")
	private String officePhone;

	@Column(name="REC_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="recType")
	private Integer recType;

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setUrgeStopFlag(String obj){
		this.urgeStopFlag = obj;
	}

	public String getUrgeStopFlag(){
		return urgeStopFlag;
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

	public void setOperType(Integer obj){
		this.operType = obj;
	}

	public Integer getOperType(){
		return operType;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setCommitDate(Date obj){
		this.commitDate = obj;
	}

	public Date getCommitDate(){
		return commitDate;
	}

	public void setOfficePhone(String obj){
		this.officePhone = obj;
	}

	public String getOfficePhone(){
		return officePhone;
	}

	public void setRecType(Integer obj){
		this.recType = obj;
	}

	public Integer getRecType(){
		return recType;
	}

	public INotifyExempt(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		INotifyExempt rhs=(INotifyExempt)rhs0;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(urgeStopFlag, rhs.urgeStopFlag)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(operType, rhs.operType)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(commitDate, rhs.commitDate)) return false;
		if(!ObjectUtils.equals(officePhone, rhs.officePhone)) return false;
		if(!ObjectUtils.equals(recType, rhs.recType)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userId)
		.append(acctId)
		.append(urgeStopFlag)
		.append(validDate)
		.append(expireDate)
		.append(operType)
		.append(soNbr)
		.append(commitDate)
		.append(officePhone)
		.append(recType)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{userId,acctId,urgeStopFlag,validDate,expireDate,operType,soNbr,commitDate,officePhone,recType}
}