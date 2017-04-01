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
@Table(schema="CD",name="CM_RES_SERVICE_STATUS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"serviceInstId","resourceId","serviceId","osSts","osStsDtl","validDate","expireDate","soNbr","createDate","soDate","remark"})
public class CmResServiceStatus extends DataObject{


	@Id
	@Column(name="SERVICE_INST_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="serviceInstId")
	private Long serviceInstId;

	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="SERVICE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="serviceId")
	private Integer serviceId;

	@Column(name="OS_STS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="osSts")
	private Integer osSts;

	@Column(name="OS_STS_DTL",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="osStsDtl")
	private Long osStsDtl;

	@Id
	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Id
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="REMARK",columnDefinition="Varchar",length=255)
	@XmlElement(name="remark")
	private String remark;

	public void setServiceInstId(Long obj){
		this.serviceInstId = obj;
	}

	public Long getServiceInstId(){
		return serviceInstId;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setServiceId(Integer obj){
		this.serviceId = obj;
	}

	public Integer getServiceId(){
		return serviceId;
	}

	public void setOsSts(Integer obj){
		this.osSts = obj;
	}

	public Integer getOsSts(){
		return osSts;
	}

	public void setOsStsDtl(Long obj){
		this.osStsDtl = obj;
	}

	public Long getOsStsDtl(){
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

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public CmResServiceStatus(){
	}

	public CmResServiceStatus(Long serviceInstId,Date validDate,Long soNbr){
		this.serviceInstId = serviceInstId;
		this.validDate = validDate;
		this.soNbr = soNbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CmResServiceStatus rhs=(CmResServiceStatus)rhs0;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(serviceInstId, rhs.serviceInstId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(validDate)
		.append(soNbr)
		.append(serviceInstId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{serviceInstId,resourceId,serviceId,osSts,osStsDtl,validDate,expireDate,soNbr,createDate,soDate,remark}
}