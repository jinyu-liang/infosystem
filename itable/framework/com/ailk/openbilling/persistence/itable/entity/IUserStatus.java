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
@Table(schema="JD",name="I_USER_STATUS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"userId","sts","osSts","osStsDtl","validDate","expireDate","operType","soNbr","commitDate"})
public class IUserStatus extends DataObject{


	@Column(name="USER_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="userId")
	private Long userId;

	@Column(name="STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="sts")
	private Integer sts;

	@Column(name="OS_STS",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="osSts")
	private Integer osSts;

	/**
	 * ?????:
???,??????,?????,???,????,????,???????????????
	 */
	@Column(name="OS_STS_DTL",columnDefinition="Varchar",length=64,nullable=false)
	@XmlElement(name="osStsDtl")
	private String osStsDtl;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	/**
	 * 1:??
2:??
3:??
	 */
	@Column(name="OPER_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="operType")
	private Integer operType;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="COMMIT_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="commitDate")
	private Date commitDate;

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setOsSts(Integer obj){
		this.osSts = obj;
	}

	public Integer getOsSts(){
		return osSts;
	}

	public void setOsStsDtl(String obj){
		this.osStsDtl = obj;
	}

	public String getOsStsDtl(){
		return osStsDtl;
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

	public IUserStatus(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IUserStatus rhs=(IUserStatus)rhs0;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(osSts, rhs.osSts)) return false;
		if(!ObjectUtils.equals(osStsDtl, rhs.osStsDtl)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(operType, rhs.operType)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(commitDate, rhs.commitDate)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(userId)
		.append(sts)
		.append(osSts)
		.append(osStsDtl)
		.append(validDate)
		.append(expireDate)
		.append(operType)
		.append(soNbr)
		.append(commitDate)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{userId,sts,osSts,osStsDtl,validDate,expireDate,operType,soNbr,commitDate}
}