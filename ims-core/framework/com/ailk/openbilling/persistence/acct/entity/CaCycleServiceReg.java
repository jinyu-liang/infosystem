package com.ailk.openbilling.persistence.acct.entity;

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
@Table(schema="AD",name="CA_CYCLE_SERVICE_REG")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"soNbr","pvalidDate","pexpireDate","nvalidDate","nexpireDate","resourceId","serviceSpecId","psts","nsts","dealDate","unSoOpId","unSoNbr","unDealDate","soDate","creataDate"})
public class CaCycleServiceReg extends DataObject{


	/**
	 * handle the number of work order
	 */
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Id
	@Column(name="SO_NBR",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * Previous validation date
	 */
	@Column(name="PVALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="pvalidDate")
	private Date pvalidDate;

	/**
	 * Previous invalidation date
	 */
	@Column(name="PEXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="pexpireDate")
	private Date pexpireDate;

	/**
	 * New validation date
	 */
	@Column(name="NVALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="nvalidDate")
	private Date nvalidDate;

	/**
	 * just impact on month
	 */
	@Column(name="NEXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="nexpireDate")
	private Date nexpireDate;

	/**
	 * Equipment ID
	 */
	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	/**
	 * Service ID
	 */
	@Column(name="SERVICE_SPEC_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="serviceSpecId")
	private Integer serviceSpecId;

	/**
	 * Previous status

0 --- Activation Period
1 --- Promotional Period
2 --- Validity Period
3 --- Grace Period
4 --- Recharge Period
5 --- Grace before Recycle
	 */
	@Column(name="PSTS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="psts")
	private Integer psts;

	/**
	 * New status

0 --- Activation Period
1 --- Promotional Period
2 --- Validity Period
3 --- Grace Period
4 --- Recharge Period
5 --- Grace before Recycle
	 */
	@Column(name="NSTS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="nsts")
	private Integer nsts;

	/**
	 * time of handling
	 */
	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	/**
	 * cancel the transact work order
	 */
	@Column(name="UN_SO_OP_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="unSoOpId")
	private Long unSoOpId;

	/**
	 * cancel the number of transacting work order
	 */
	@Column(name="UN_SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="unSoNbr")
	private Long unSoNbr;

	/**
	 * transaction date and time of order cancelling
	 */
	@Column(name="UN_DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="unDealDate")
	private Date unDealDate;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="CREATA_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="creataDate")
	private Date creataDate;

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setPvalidDate(Date obj){
		this.pvalidDate = obj;
	}

	public Date getPvalidDate(){
		return pvalidDate;
	}

	public void setPexpireDate(Date obj){
		this.pexpireDate = obj;
	}

	public Date getPexpireDate(){
		return pexpireDate;
	}

	public void setNvalidDate(Date obj){
		this.nvalidDate = obj;
	}

	public Date getNvalidDate(){
		return nvalidDate;
	}

	public void setNexpireDate(Date obj){
		this.nexpireDate = obj;
	}

	public Date getNexpireDate(){
		return nexpireDate;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setServiceSpecId(Integer obj){
		this.serviceSpecId = obj;
	}

	public Integer getServiceSpecId(){
		return serviceSpecId;
	}

	public void setPsts(Integer obj){
		this.psts = obj;
	}

	public Integer getPsts(){
		return psts;
	}

	public void setNsts(Integer obj){
		this.nsts = obj;
	}

	public Integer getNsts(){
		return nsts;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setUnSoOpId(Long obj){
		this.unSoOpId = obj;
	}

	public Long getUnSoOpId(){
		return unSoOpId;
	}

	public void setUnSoNbr(Long obj){
		this.unSoNbr = obj;
	}

	public Long getUnSoNbr(){
		return unSoNbr;
	}

	public void setUnDealDate(Date obj){
		this.unDealDate = obj;
	}

	public Date getUnDealDate(){
		return unDealDate;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setCreataDate(Date obj){
		this.creataDate = obj;
	}

	public Date getCreataDate(){
		return creataDate;
	}

	public CaCycleServiceReg(){
	}

	public CaCycleServiceReg(Long soNbr){
		this.soNbr = soNbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaCycleServiceReg rhs=(CaCycleServiceReg)rhs0;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(soNbr)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{soNbr,pvalidDate,pexpireDate,nvalidDate,nexpireDate,resourceId,serviceSpecId,psts,nsts,dealDate,unSoOpId,unSoNbr,unDealDate,soDate,creataDate}
}