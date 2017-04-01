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
@Table(schema="JD",name="I_USER_PBX")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"userId","regionCode","mscId","areaCode","inTrunk","outTrunk","accessNumber","matchingAccessNumber","validDate","expireDate","operType","soNbr","commitDate"})
public class IUserPbx extends DataObject{


	@Column(name="USER_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="userId")
	private Long userId;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	@Column(name="MSC_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="mscId")
	private Long mscId;

	@Column(name="AREA_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="areaCode")
	private Integer areaCode;

	@Column(name="IN_TRUNK",columnDefinition="Varchar",length=16)
	@XmlElement(name="inTrunk")
	private String inTrunk;

	@Column(name="OUT_TRUNK",columnDefinition="Varchar",length=16)
	@XmlElement(name="outTrunk")
	private String outTrunk;

	@Column(name="ACCESS_NUMBER",columnDefinition="Varchar",length=32)
	@XmlElement(name="accessNumber")
	private String accessNumber;

	@Column(name="MATCHING_ACCESS_NUMBER",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="matchingAccessNumber")
	private Integer matchingAccessNumber;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="OPER_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="operType")
	private Integer operType;

	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	@Column(name="COMMIT_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="commitDate")
	private Date commitDate;

	public void setUserId(Long obj){
		this.userId = obj;
	}

	public Long getUserId(){
		return userId;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setMscId(Long obj){
		this.mscId = obj;
	}

	public Long getMscId(){
		return mscId;
	}

	public void setAreaCode(Integer obj){
		this.areaCode = obj;
	}

	public Integer getAreaCode(){
		return areaCode;
	}

	public void setInTrunk(String obj){
		this.inTrunk = obj;
	}

	public String getInTrunk(){
		return inTrunk;
	}

	public void setOutTrunk(String obj){
		this.outTrunk = obj;
	}

	public String getOutTrunk(){
		return outTrunk;
	}

	public void setAccessNumber(String obj){
		this.accessNumber = obj;
	}

	public String getAccessNumber(){
		return accessNumber;
	}

	public void setMatchingAccessNumber(Integer obj){
		this.matchingAccessNumber = obj;
	}

	public Integer getMatchingAccessNumber(){
		return matchingAccessNumber;
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

	public IUserPbx(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IUserPbx rhs=(IUserPbx)rhs0;
		if(!ObjectUtils.equals(userId, rhs.userId)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(mscId, rhs.mscId)) return false;
		if(!ObjectUtils.equals(areaCode, rhs.areaCode)) return false;
		if(!ObjectUtils.equals(inTrunk, rhs.inTrunk)) return false;
		if(!ObjectUtils.equals(outTrunk, rhs.outTrunk)) return false;
		if(!ObjectUtils.equals(accessNumber, rhs.accessNumber)) return false;
		if(!ObjectUtils.equals(matchingAccessNumber, rhs.matchingAccessNumber)) return false;
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
		.append(regionCode)
		.append(mscId)
		.append(areaCode)
		.append(inTrunk)
		.append(outTrunk)
		.append(accessNumber)
		.append(matchingAccessNumber)
		.append(validDate)
		.append(expireDate)
		.append(operType)
		.append(soNbr)
		.append(commitDate)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{userId,regionCode,mscId,areaCode,inTrunk,outTrunk,accessNumber,matchingAccessNumber,validDate,expireDate,operType,soNbr,commitDate}
}