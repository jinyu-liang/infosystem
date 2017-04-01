package com.ailk.openbilling.persistence.jd.entity;

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
@Table(schema="jd",name="I_USER_DEPOSIT_NOTIFY")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"servId","promoId","condId","busiType","soNbr","changeFlag","srcType","regionCode","createDate","validDate"})
public class IUserDepositNotify extends DataObject{


	@Column(name="SERV_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="servId")
	private Long servId;

	@Column(name="PROMO_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="promoId")
	private Integer promoId;

	@Column(name="COND_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="condId")
	private Integer condId;

	@Column(name="BUSI_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiType")
	private Integer busiType;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="CHANGE_FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="changeFlag")
	private Integer changeFlag;

	@Column(name="SRC_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="srcType")
	private Integer srcType;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="regionCode")
	private Integer regionCode;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	public void setServId(Long obj){
		this.servId = obj;
	}

	public Long getServId(){
		return servId;
	}

	public void setPromoId(Integer obj){
		this.promoId = obj;
	}

	public Integer getPromoId(){
		return promoId;
	}

	public void setCondId(Integer obj){
		this.condId = obj;
	}

	public Integer getCondId(){
		return condId;
	}

	public void setBusiType(Integer obj){
		this.busiType = obj;
	}

	public Integer getBusiType(){
		return busiType;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setChangeFlag(Integer obj){
		this.changeFlag = obj;
	}

	public Integer getChangeFlag(){
		return changeFlag;
	}

	public void setSrcType(Integer obj){
		this.srcType = obj;
	}

	public Integer getSrcType(){
		return srcType;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
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

	public IUserDepositNotify(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IUserDepositNotify rhs=(IUserDepositNotify)rhs0;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(promoId, rhs.promoId)) return false;
		if(!ObjectUtils.equals(condId, rhs.condId)) return false;
		if(!ObjectUtils.equals(busiType, rhs.busiType)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(changeFlag, rhs.changeFlag)) return false;
		if(!ObjectUtils.equals(srcType, rhs.srcType)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(servId)
		.append(promoId)
		.append(condId)
		.append(busiType)
		.append(soNbr)
		.append(changeFlag)
		.append(srcType)
		.append(regionCode)
		.append(createDate)
		.append(validDate)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{servId,promoId,condId,busiType,soNbr,changeFlag,srcType,regionCode,createDate,validDate}
}