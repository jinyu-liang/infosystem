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
 */
@NotModified
@Entity
@Table(schema="cd",name="CM_RESOURCE_MONITOR")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resourceId","regionCode","serviceId","policyId","monitorType","validDate","expireDate","remarks","soNbr","soDate"})
public class CmResourceMonitor extends DataObject{


	/**
	 * 资源标识
	 */
	@Id
	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	/**
	 * 资源归属地市代码
	 */
	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="regionCode")
	private Integer regionCode;

	/**
	 * 服务ID， 当不区分服务时，service_id =0
	 */
	@Column(name="SERVICE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="serviceId")
	private Integer serviceId;

	/**
	 * 策略ID
	 */
	@Column(name="POLICY_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="policyId")
	private Integer policyId;

	/**
	 * 0:离线 1：欠控 2：在线
	 */
	@Column(name="MONITOR_TYPE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="monitorType")
	private Integer monitorType;

	/**
	 * 生效日期
	 */
	@Id
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * 失效日期
	 */
	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	/**
	 * 注释
	 */
	@Column(name="REMARKS",columnDefinition="Varchar",length=256)
	@XmlElement(name="remarks")
	private String remarks;

	/**
	 * 受理流水
	 */
	@Id
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * 受理日期
	 */
	@Column(name="SO_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="soDate")
	private Date soDate;

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setServiceId(Integer obj){
		this.serviceId = obj;
	}

	public Integer getServiceId(){
		return serviceId;
	}

	public void setPolicyId(Integer obj){
		this.policyId = obj;
	}

	public Integer getPolicyId(){
		return policyId;
	}

	public void setMonitorType(Integer obj){
		this.monitorType = obj;
	}

	public Integer getMonitorType(){
		return monitorType;
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

	public void setRemarks(String obj){
		this.remarks = obj;
	}

	public String getRemarks(){
		return remarks;
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

	public CmResourceMonitor(){
	}

	public CmResourceMonitor(Long resourceId,Date validDate,Long soNbr){
		this.resourceId = resourceId;
		this.validDate = validDate;
		this.soNbr = soNbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CmResourceMonitor rhs=(CmResourceMonitor)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(validDate)
		.append(soNbr)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{resourceId,regionCode,serviceId,policyId,monitorType,validDate,expireDate,remarks,soNbr,soDate}
}