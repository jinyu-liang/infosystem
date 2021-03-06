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
@Table(schema="cd",name="IMS_SONBR_MAPPING")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"doneCode","doneDate","outSoNbr","outSoDate","bosSoDate","bosSoNbr","busiCode","busiSpecId","busiType"})
public class ImsSonbrMapping extends DataObject{


	/**
	 * 信息管理流水号
	 */
	@Column(name="DONE_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="doneCode")
	private Long doneCode;

	/**
	 * 信息管理流水号产生时间
	 */
	@Column(name="DONE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="doneDate")
	private Date doneDate;

	/**
	 * 外围流水号
	 */
	@Column(name="OUT_SO_NBR",columnDefinition="Varchar",length=128,nullable=false)
	@XmlElement(name="outSoNbr")
	private String outSoNbr;

	/**
	 * 外围流水号产生时间
	 */
	@Column(name="OUT_SO_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="outSoDate")
	private Date outSoDate;

	/**
	 * BOS内部产品流水号，比如帐管
	 */
	@Column(name="BOS_SO_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="bosSoDate")
	private Date bosSoDate;

	/**
	 * BOS内部产品流水号产生时间
	 */
	@Column(name="BOS_SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="bosSoNbr")
	private Long bosSoNbr;

	/**
	 * 业务编码
	 */
	@Column(name="BUSI_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiCode")
	private Integer busiCode;

	@Column(name="BUSI_SPEC_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiSpecId")
	private Integer busiSpecId;

	/**
	 * 1 oneTimeFee
2 reward
3 鉴权
4 payment
	 */
	@Column(name="BUSI_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiType")
	private Integer busiType;

	public void setDoneCode(Long obj){
		this.doneCode = obj;
	}

	public Long getDoneCode(){
		return doneCode;
	}

	public void setDoneDate(Date obj){
		this.doneDate = obj;
	}

	public Date getDoneDate(){
		return doneDate;
	}

	public void setOutSoNbr(String obj){
		this.outSoNbr = obj;
	}

	public String getOutSoNbr(){
		return outSoNbr;
	}

	public void setOutSoDate(Date obj){
		this.outSoDate = obj;
	}

	public Date getOutSoDate(){
		return outSoDate;
	}

	public void setBosSoDate(Date obj){
		this.bosSoDate = obj;
	}

	public Date getBosSoDate(){
		return bosSoDate;
	}

	public void setBosSoNbr(Long obj){
		this.bosSoNbr = obj;
	}

	public Long getBosSoNbr(){
		return bosSoNbr;
	}

	public void setBusiCode(Integer obj){
		this.busiCode = obj;
	}

	public Integer getBusiCode(){
		return busiCode;
	}

	public void setBusiSpecId(Integer obj){
		this.busiSpecId = obj;
	}

	public Integer getBusiSpecId(){
		return busiSpecId;
	}

	public void setBusiType(Integer obj){
		this.busiType = obj;
	}

	public Integer getBusiType(){
		return busiType;
	}

	public ImsSonbrMapping(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsSonbrMapping rhs=(ImsSonbrMapping)rhs0;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(doneDate, rhs.doneDate)) return false;
		if(!ObjectUtils.equals(outSoNbr, rhs.outSoNbr)) return false;
		if(!ObjectUtils.equals(outSoDate, rhs.outSoDate)) return false;
		if(!ObjectUtils.equals(bosSoDate, rhs.bosSoDate)) return false;
		if(!ObjectUtils.equals(bosSoNbr, rhs.bosSoNbr)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		if(!ObjectUtils.equals(busiSpecId, rhs.busiSpecId)) return false;
		if(!ObjectUtils.equals(busiType, rhs.busiType)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(doneCode)
		.append(doneDate)
		.append(outSoNbr)
		.append(outSoDate)
		.append(bosSoDate)
		.append(bosSoNbr)
		.append(busiCode)
		.append(busiSpecId)
		.append(busiType)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{doneCode,doneDate,outSoNbr,outSoDate,bosSoDate,bosSoNbr,busiCode,busiSpecId,busiType}
}