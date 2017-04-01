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
@Table(schema="JD",name="I_GROUP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"groupId","groupType","maxMemberNumber","contactPhone","groupName","status","provCode","countyCode","regionCode","validDate","expireDate","freeresShareFlag","operType","soNbr","commitDate","busiTag"})
public class IGroup extends DataObject{


	@Column(name="GROUP_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="groupId")
	private Long groupId;

	@Column(name="GROUP_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="groupType")
	private Integer groupType;

	@Column(name="MAX_MEMBER_NUMBER",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="maxMemberNumber")
	private Integer maxMemberNumber;

	@Column(name="CONTACT_PHONE",columnDefinition="Varchar",length=28)
	@XmlElement(name="contactPhone")
	private String contactPhone;

	@Column(name="GROUP_NAME",columnDefinition="Varchar",length=64)
	@XmlElement(name="groupName")
	private String groupName;

	@Column(name="STATUS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="status")
	private Integer status;

	@Column(name="PROV_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="provCode")
	private Integer provCode;

	@Column(name="COUNTY_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="countyCode")
	private Integer countyCode;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="FREERES_SHARE_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="freeresShareFlag")
	private Integer freeresShareFlag;

	@Column(name="OPER_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="operType")
	private Integer operType;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="COMMIT_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="commitDate")
	private Date commitDate;

	@Column(name="BUSI_TAG",columnDefinition="Varchar",length=1)
	@XmlElement(name="busiTag")
	private String busiTag;

	public void setGroupId(Long obj){
		this.groupId = obj;
	}

	public Long getGroupId(){
		return groupId;
	}

	public void setGroupType(Integer obj){
		this.groupType = obj;
	}

	public Integer getGroupType(){
		return groupType;
	}

	public void setMaxMemberNumber(Integer obj){
		this.maxMemberNumber = obj;
	}

	public Integer getMaxMemberNumber(){
		return maxMemberNumber;
	}

	public void setContactPhone(String obj){
		this.contactPhone = obj;
	}

	public String getContactPhone(){
		return contactPhone;
	}

	public void setGroupName(String obj){
		this.groupName = obj;
	}

	public String getGroupName(){
		return groupName;
	}

	public void setStatus(Integer obj){
		this.status = obj;
	}

	public Integer getStatus(){
		return status;
	}

	public void setProvCode(Integer obj){
		this.provCode = obj;
	}

	public Integer getProvCode(){
		return provCode;
	}

	public void setCountyCode(Integer obj){
		this.countyCode = obj;
	}

	public Integer getCountyCode(){
		return countyCode;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
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

	public void setFreeresShareFlag(Integer obj){
		this.freeresShareFlag = obj;
	}

	public Integer getFreeresShareFlag(){
		return freeresShareFlag;
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

	public void setBusiTag(String obj){
		this.busiTag = obj;
	}

	public String getBusiTag(){
		return busiTag;
	}

	public IGroup(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IGroup rhs=(IGroup)rhs0;
		if(!ObjectUtils.equals(groupId, rhs.groupId)) return false;
		if(!ObjectUtils.equals(groupType, rhs.groupType)) return false;
		if(!ObjectUtils.equals(maxMemberNumber, rhs.maxMemberNumber)) return false;
		if(!ObjectUtils.equals(contactPhone, rhs.contactPhone)) return false;
		if(!ObjectUtils.equals(groupName, rhs.groupName)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(provCode, rhs.provCode)) return false;
		if(!ObjectUtils.equals(countyCode, rhs.countyCode)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(freeresShareFlag, rhs.freeresShareFlag)) return false;
		if(!ObjectUtils.equals(operType, rhs.operType)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(commitDate, rhs.commitDate)) return false;
		if(!ObjectUtils.equals(busiTag, rhs.busiTag)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(groupId)
		.append(groupType)
		.append(maxMemberNumber)
		.append(contactPhone)
		.append(groupName)
		.append(status)
		.append(provCode)
		.append(countyCode)
		.append(regionCode)
		.append(validDate)
		.append(expireDate)
		.append(freeresShareFlag)
		.append(operType)
		.append(soNbr)
		.append(commitDate)
		.append(busiTag)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{groupId,groupType,maxMemberNumber,contactPhone,groupName,status,provCode,countyCode,regionCode,validDate,expireDate,freeresShareFlag,operType,soNbr,commitDate,busiTag}
}