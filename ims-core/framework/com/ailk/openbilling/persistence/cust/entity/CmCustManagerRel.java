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
 * 
 */
@NotModified
@Entity
@Table(schema="CD",name="CM_CUST_MANAGER_REL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"custId","groupId","custManagerId","soNbr","soDate","createDate","validDate","expireDate"})
public class CmCustManagerRel extends DataObject{


	/**
	 * ????
	 */
	@Id
	@Column(name="CUST_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="custId")
	private Long custId;

	/**
	 * ????
	 */
	@Id
	@Column(name="GROUP_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="groupId")
	private Long groupId;

	/**
	 * ??????
	 */
	@Id
	@Column(name="CUST_MANAGER_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="custManagerId")
	private Long custManagerId;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	@Id
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	public void setCustId(Long obj){
		this.custId = obj;
	}

	public Long getCustId(){
		return custId;
	}

	public void setGroupId(Long obj){
		this.groupId = obj;
	}

	public Long getGroupId(){
		return groupId;
	}

	public void setCustManagerId(Long obj){
		this.custManagerId = obj;
	}

	public Long getCustManagerId(){
		return custManagerId;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
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

	public CmCustManagerRel(){
	}

	public CmCustManagerRel(Long custId,Long groupId,Long custManagerId,Date validDate){
		this.custId = custId;
		this.groupId = groupId;
		this.custManagerId = custManagerId;
		this.validDate = validDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CmCustManagerRel rhs=(CmCustManagerRel)rhs0;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(custManagerId, rhs.custManagerId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(custId)
		.append(groupId)
		.append(validDate)
		.append(custManagerId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{custId,groupId,custManagerId,soNbr,soDate,createDate,validDate,expireDate}
}