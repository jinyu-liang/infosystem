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
@Table(schema="CD",name="IMS_SEND_RECORD")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"soNbr","soDate","target","countyCode","isnormal","busiCode","rsoNbr","phoneNo","processContent","doneCode","provCode","destination","soMode","opId","doneTime","extendparam1","remark","orgId","extendparam2","regionCode"})
public class ImsSendRecord extends DataObject{


	@Column(name="SO_NBR",columnDefinition="Varchar",length=255)
	@XmlElement(name="soNbr")
	private String soNbr;

	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="TARGET",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="target")
	private Long target;

	@Column(name="COUNTY_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="countyCode")
	private Integer countyCode;

	@Column(name="ISNORMAL",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="isnormal")
	private Integer isnormal;

	@Column(name="BUSI_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="busiCode")
	private Integer busiCode;

	@Column(name="RSO_NBR",columnDefinition="Varchar",length=255)
	@XmlElement(name="rsoNbr")
	private String rsoNbr;

	@Column(name="PHONE_NO",columnDefinition="Varchar",length=255)
	@XmlElement(name="phoneNo")
	private String phoneNo;

	@Column(name="PROCESS_CONTENT",columnDefinition="Varchar",length=255)
	@XmlElement(name="processContent")
	private String processContent;

	@Column(name="DONE_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="doneCode")
	private Long doneCode;

	@Column(name="PROV_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="provCode")
	private Integer provCode;

	@Column(name="DESTINATION",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="destination")
	private Long destination;

	@Column(name="SO_MODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soMode")
	private Integer soMode;

	@Column(name="OP_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="opId")
	private Long opId;

	@Column(name="DONE_TIME",columnDefinition="TimeStamp")
	@XmlElement(name="doneTime")
	private Date doneTime;

	@Column(name="EXTENDPARAM1",columnDefinition="Varchar",length=255)
	@XmlElement(name="extendparam1")
	private String extendparam1;

	@Column(name="REMARK",columnDefinition="Varchar",length=255)
	@XmlElement(name="remark")
	private String remark;

	@Column(name="ORG_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="orgId")
	private Long orgId;

	@Column(name="EXTENDPARAM2",columnDefinition="Varchar",length=255)
	@XmlElement(name="extendparam2")
	private String extendparam2;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	public void setSoNbr(String obj){
		this.soNbr = obj;
	}

	public String getSoNbr(){
		return soNbr;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setTarget(Long obj){
		this.target = obj;
	}

	public Long getTarget(){
		return target;
	}

	public void setCountyCode(Integer obj){
		this.countyCode = obj;
	}

	public Integer getCountyCode(){
		return countyCode;
	}

	public void setIsnormal(Integer obj){
		this.isnormal = obj;
	}

	public Integer getIsnormal(){
		return isnormal;
	}

	public void setBusiCode(Integer obj){
		this.busiCode = obj;
	}

	public Integer getBusiCode(){
		return busiCode;
	}

	public void setRsoNbr(String obj){
		this.rsoNbr = obj;
	}

	public String getRsoNbr(){
		return rsoNbr;
	}

	public void setPhoneNo(String obj){
		this.phoneNo = obj;
	}

	public String getPhoneNo(){
		return phoneNo;
	}

	public void setProcessContent(String obj){
		this.processContent = obj;
	}

	public String getProcessContent(){
		return processContent;
	}

	public void setDoneCode(Long obj){
		this.doneCode = obj;
	}

	public Long getDoneCode(){
		return doneCode;
	}

	public void setProvCode(Integer obj){
		this.provCode = obj;
	}

	public Integer getProvCode(){
		return provCode;
	}

	public void setDestination(Long obj){
		this.destination = obj;
	}

	public Long getDestination(){
		return destination;
	}

	public void setSoMode(Integer obj){
		this.soMode = obj;
	}

	public Integer getSoMode(){
		return soMode;
	}

	public void setOpId(Long obj){
		this.opId = obj;
	}

	public Long getOpId(){
		return opId;
	}

	public void setDoneTime(Date obj){
		this.doneTime = obj;
	}

	public Date getDoneTime(){
		return doneTime;
	}

	public void setExtendparam1(String obj){
		this.extendparam1 = obj;
	}

	public String getExtendparam1(){
		return extendparam1;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setOrgId(Long obj){
		this.orgId = obj;
	}

	public Long getOrgId(){
		return orgId;
	}

	public void setExtendparam2(String obj){
		this.extendparam2 = obj;
	}

	public String getExtendparam2(){
		return extendparam2;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public ImsSendRecord(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsSendRecord rhs=(ImsSendRecord)rhs0;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(target, rhs.target)) return false;
		if(!ObjectUtils.equals(countyCode, rhs.countyCode)) return false;
		if(!ObjectUtils.equals(isnormal, rhs.isnormal)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		if(!ObjectUtils.equals(rsoNbr, rhs.rsoNbr)) return false;
		if(!ObjectUtils.equals(phoneNo, rhs.phoneNo)) return false;
		if(!ObjectUtils.equals(processContent, rhs.processContent)) return false;
		if(!ObjectUtils.equals(doneCode, rhs.doneCode)) return false;
		if(!ObjectUtils.equals(provCode, rhs.provCode)) return false;
		if(!ObjectUtils.equals(destination, rhs.destination)) return false;
		if(!ObjectUtils.equals(soMode, rhs.soMode)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(doneTime, rhs.doneTime)) return false;
		if(!ObjectUtils.equals(extendparam1, rhs.extendparam1)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(orgId, rhs.orgId)) return false;
		if(!ObjectUtils.equals(extendparam2, rhs.extendparam2)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(soNbr)
		.append(soDate)
		.append(target)
		.append(countyCode)
		.append(isnormal)
		.append(busiCode)
		.append(rsoNbr)
		.append(phoneNo)
		.append(processContent)
		.append(doneCode)
		.append(provCode)
		.append(destination)
		.append(soMode)
		.append(opId)
		.append(doneTime)
		.append(extendparam1)
		.append(remark)
		.append(orgId)
		.append(extendparam2)
		.append(regionCode)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{soNbr,soDate,target,countyCode,isnormal,busiCode,rsoNbr,phoneNo,processContent,doneCode,provCode,destination,soMode,opId,doneTime,extendparam1,remark,orgId,extendparam2,regionCode}
}