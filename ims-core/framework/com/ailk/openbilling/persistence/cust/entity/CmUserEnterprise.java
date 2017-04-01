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
@Table(schema="CD",name="CM_USER_ENTERPRISE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"resourceId","regionCode","spCode","serviceCode","operatorCode","busiType","property","unbalancedFlag","replaceFlag","validDate","expireDate","soDate","remark","soNbr"})
public class CmUserEnterprise extends DataObject{


	/**
	 * ?????????,?0
	 */
	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="regionCode")
	private Integer regionCode;

	/**
	 * ????????????????
1.?????SP_CODE,??0
	 */
	@Column(name="SP_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="spCode")
	private Long spCode;

	@Column(name="SERVICE_CODE",columnDefinition="Varchar",length=32,nullable=false)
	@XmlElement(name="serviceCode")
	private String serviceCode;

	@Column(name="OPERATOR_CODE",columnDefinition="Varchar",length=32,nullable=false)
	@XmlElement(name="operatorCode")
	private String operatorCode;

	/**
	 * 1:ADC
2:MAS
3:????
	 */
	@Column(name="BUSI_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="busiType")
	private Integer busiType;

	/**
	 * 0:??
3;???
4:???
5:???
6:???
7;???
8:???
	 */
	@Column(name="PROPERTY",columnDefinition="Varchar",length=20,nullable=false)
	@XmlElement(name="property")
	private String property;

	/**
	 * ??????????
0:???
1:??
???0,???
	 */
	@Column(name="UNBALANCED_FLAG",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="unbalancedFlag")
	private Integer unbalancedFlag;

	/**
	 * ?8?????,???0~8,???????????000~111,???????:
?? ?? ???????? ?????? ??????
0 ?????????????,??????????? 0 0 0
1 ?????????????,??????????? 0 0 1
2 ?????????????,??????????? 0 1 0
3 ???????????????,??????????? 0 1 1
4 ?????????????,?????????? 1 0 0
5 ?????????????,?????????? 1 0 1
6 ?????????????,?????????? 1 1 0
7 ???????????????,?????????? 1 1 1


???0
	 */
	@Column(name="REPLACE_FLAG",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="replaceFlag")
	private Integer replaceFlag;

	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	@Column(name="SO_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="REMARK",columnDefinition="Varchar",length=255)
	@XmlElement(name="remark")
	private String remark;

	@Column(name="SO_NBR",columnDefinition="Varchar",length=40)
	@XmlElement(name="soNbr")
	private String soNbr;

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

	public void setSpCode(Long obj){
		this.spCode = obj;
	}

	public Long getSpCode(){
		return spCode;
	}

	public void setServiceCode(String obj){
		this.serviceCode = obj;
	}

	public String getServiceCode(){
		return serviceCode;
	}

	public void setOperatorCode(String obj){
		this.operatorCode = obj;
	}

	public String getOperatorCode(){
		return operatorCode;
	}

	public void setBusiType(Integer obj){
		this.busiType = obj;
	}

	public Integer getBusiType(){
		return busiType;
	}

	public void setProperty(String obj){
		this.property = obj;
	}

	public String getProperty(){
		return property;
	}

	public void setUnbalancedFlag(Integer obj){
		this.unbalancedFlag = obj;
	}

	public Integer getUnbalancedFlag(){
		return unbalancedFlag;
	}

	public void setReplaceFlag(Integer obj){
		this.replaceFlag = obj;
	}

	public Integer getReplaceFlag(){
		return replaceFlag;
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

	public void setSoNbr(String obj){
		this.soNbr = obj;
	}

	public String getSoNbr(){
		return soNbr;
	}

	public CmUserEnterprise(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CmUserEnterprise rhs=(CmUserEnterprise)rhs0;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(spCode, rhs.spCode)) return false;
		if(!ObjectUtils.equals(serviceCode, rhs.serviceCode)) return false;
		if(!ObjectUtils.equals(operatorCode, rhs.operatorCode)) return false;
		if(!ObjectUtils.equals(busiType, rhs.busiType)) return false;
		if(!ObjectUtils.equals(property, rhs.property)) return false;
		if(!ObjectUtils.equals(unbalancedFlag, rhs.unbalancedFlag)) return false;
		if(!ObjectUtils.equals(replaceFlag, rhs.replaceFlag)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resourceId)
		.append(regionCode)
		.append(spCode)
		.append(serviceCode)
		.append(operatorCode)
		.append(busiType)
		.append(property)
		.append(unbalancedFlag)
		.append(replaceFlag)
		.append(validDate)
		.append(expireDate)
		.append(soDate)
		.append(remark)
		.append(soNbr)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{resourceId,regionCode,spCode,serviceCode,operatorCode,busiType,property,unbalancedFlag,replaceFlag,validDate,expireDate,soDate,remark,soNbr}
}