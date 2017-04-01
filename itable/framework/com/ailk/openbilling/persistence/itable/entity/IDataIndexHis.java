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
 */
@NotModified
@Entity
@Table(schema="JD",name="I_DATA_INDEX_HIS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"custId","acctId","userId","upField","regionCode","countyCode","commitDate","soNbr","doneCode","dealDate","remark","busiCode"})
public class IDataIndexHis extends DataObject{


	@Column(name="CUST_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="custId")
	private Long custId;

	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="acctId")
	private Long acctId;

	@Column(name="USER_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="userId")
	private Long userId;

	@Column(name="UP_FIELD",columnDefinition="Varchar",length=64)
	@XmlElement(name="upField")
	private String upField;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	@Column(name="COUNTY_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="countyCode")
	private Integer countyCode;

	@Column(name="COMMIT_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="commitDate")
	private Date commitDate;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="DONE_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="doneCode")
	private Long doneCode;

	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	@Column(name="REMARK",columnDefinition="Varchar",length=255)
	@XmlElement(name="remark")
	private String remark;

	@Column(name="BUSI_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiCode")
	private Long busiCode;

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

	public void setUpField(String obj){
		this.upField = obj;
	}

	public String getUpField(){
		return upField;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setCountyCode(Integer obj){
		this.countyCode = obj;
	}

	public Integer getCountyCode(){
		return countyCode;
	}

	public void setCommitDate(Date obj){
		this.commitDate = obj;
	}

	public Date getCommitDate(){
		return commitDate;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setDoneCode(Long obj){
		this.doneCode = obj;
	}

	public Long getDoneCode(){
		return doneCode;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setBusiCode(Long obj){
		this.busiCode = obj;
	}

	public Long getBusiCode(){
		return busiCode;
	}

	public IDataIndexHis(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IDataIndexHis rhs=(IDataIndexHis)rhs0;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(upField, rhs.upField)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(countyCode, rhs.countyCode)) return false;
		if(!ObjectUtils.equals(commitDate, rhs.commitDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(dealDate, rhs.dealDate)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custId)
		.append(acctId)
		.append(userId)
		.append(upField)
		.append(regionCode)
		.append(countyCode)
		.append(commitDate)
		.append(soNbr)
		.append(doneCode)
		.append(dealDate)
		.append(remark)
		.append(busiCode)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{custId,acctId,userId,upField,regionCode,countyCode,commitDate,soNbr,doneCode,dealDate,remark,busiCode}
}