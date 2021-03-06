package com.ailk.openbilling.persistence.busi.entity;

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
@Table(schema="bd",name="RS_SYS_INTL_OPER_INFO")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","operatorCode","imsiHead","englishName","countryId","provCode","tapVersion","tapRelease","tapDecimalPlaces","taxTreatment","taxRate","measureTypeId","reportErrorType","timeZoneId","networkStatement","validDate","expireDate","modifyDate","description"})
public class RsSysIntlOperInfo extends DataObject{


	/**
	 * Record sequence number
	 */
	@Id
	@Column(name="ID",precision=10,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Integer id;

	/**
	 * Operator code
	 */
	@Column(name="OPERATOR_CODE",columnDefinition="Varchar",length=32)
	@XmlElement(name="operatorCode")
	private String operatorCode;

	/**
	 * IMSI code: First characteristic 5-digit of IMSI
	 */
	@Column(name="IMSI_HEAD",columnDefinition="Varchar",length=15)
	@XmlElement(name="imsiHead")
	private String imsiHead;

	/**
	 * English name of operator
	 */
	@Column(name="ENGLISH_NAME",columnDefinition="Varchar",length=256,nullable=false)
	@XmlElement(name="englishName")
	private String englishName;

	/**
	 * Country ID: from table sys_country.country_id
	 */
	@Column(name="COUNTRY_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="countryId")
	private Integer countryId;

	/**
	 * Province code
	 */
	@Column(name="PROV_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="provCode")
	private Integer provCode;

	/**
	 * TAP VERSION
	 */
	@Column(name="TAP_VERSION",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="tapVersion")
	private Integer tapVersion;

	/**
	 * TAP Release Version
	 */
	@Column(name="TAP_RELEASE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="tapRelease")
	private Integer tapRelease;

	/**
	 * Decimal places of charges in tap file
	 */
	@Column(name="TAP_DECIMAL_PLACES",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="tapDecimalPlaces")
	private Integer tapDecimalPlaces;

	/**
	 * Agreement tax rate of operator This column is used to check whether the tax information in TAP file header is right, not refer to the follow-up tax definition and calculation. 6-digit decimal
	 */
	@Column(name="TAX_TREATMENT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="taxTreatment")
	private Integer taxTreatment;

	/**
	 * The main tax rate
	 */
	@Column(name="TAX_RATE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="taxRate")
	private Long taxRate;

	/**
	 * Refer to table SYS_MEASURE_TYPE  If subscriber not offer the value, it is default as USD
	 */
	@Column(name="MEASURE_TYPE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="measureTypeId")
	private Integer measureTypeId;

	/**
	 * Report type of error file 0: file check error or CDR check error, only report log and statistical information;  1: file check error or CDR check error, require reporting log and statistical information, meanwhile generate RAP file to send back to the opposite operator.
	 */
	@Column(name="REPORT_ERROR_TYPE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="reportErrorType")
	private Integer reportErrorType;

	/**
	 * Time zone ID from SYS_TIME_ZONE_DEF
	 */
	@Column(name="TIME_ZONE_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="timeZoneId")
	private Integer timeZoneId;

	/**
	 * Operator name for bill statement mapping to CRM, with no need to show in GUI.
	 */
	@Column(name="NETWORK_STATEMENT",columnDefinition="Varchar",length=512,nullable=false)
	@XmlElement(name="networkStatement")
	private String networkStatement;

	/**
	 * Effective date
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * Expired date
	 */
	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="expireDate")
	private Date expireDate;

	/**
	 * Modified date
	 */
	@Column(name="MODIFY_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="modifyDate")
	private Date modifyDate;

	/**
	 * Description
	 */
	@Column(name="DESCRIPTION",columnDefinition="Varchar",length=1024)
	@XmlElement(name="description")
	private String description;

	public void setId(Integer obj){
		this.id = obj;
	}

	public Integer getId(){
		return id;
	}

	public void setOperatorCode(String obj){
		this.operatorCode = obj;
	}

	public String getOperatorCode(){
		return operatorCode;
	}

	public void setImsiHead(String obj){
		this.imsiHead = obj;
	}

	public String getImsiHead(){
		return imsiHead;
	}

	public void setEnglishName(String obj){
		this.englishName = obj;
	}

	public String getEnglishName(){
		return englishName;
	}

	public void setCountryId(Integer obj){
		this.countryId = obj;
	}

	public Integer getCountryId(){
		return countryId;
	}

	public void setProvCode(Integer obj){
		this.provCode = obj;
	}

	public Integer getProvCode(){
		return provCode;
	}

	public void setTapVersion(Integer obj){
		this.tapVersion = obj;
	}

	public Integer getTapVersion(){
		return tapVersion;
	}

	public void setTapRelease(Integer obj){
		this.tapRelease = obj;
	}

	public Integer getTapRelease(){
		return tapRelease;
	}

	public void setTapDecimalPlaces(Integer obj){
		this.tapDecimalPlaces = obj;
	}

	public Integer getTapDecimalPlaces(){
		return tapDecimalPlaces;
	}

	public void setTaxTreatment(Integer obj){
		this.taxTreatment = obj;
	}

	public Integer getTaxTreatment(){
		return taxTreatment;
	}

	public void setTaxRate(Long obj){
		this.taxRate = obj;
	}

	public Long getTaxRate(){
		return taxRate;
	}

	public void setMeasureTypeId(Integer obj){
		this.measureTypeId = obj;
	}

	public Integer getMeasureTypeId(){
		return measureTypeId;
	}

	public void setReportErrorType(Integer obj){
		this.reportErrorType = obj;
	}

	public Integer getReportErrorType(){
		return reportErrorType;
	}

	public void setTimeZoneId(Integer obj){
		this.timeZoneId = obj;
	}

	public Integer getTimeZoneId(){
		return timeZoneId;
	}

	public void setNetworkStatement(String obj){
		this.networkStatement = obj;
	}

	public String getNetworkStatement(){
		return networkStatement;
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

	public void setModifyDate(Date obj){
		this.modifyDate = obj;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public RsSysIntlOperInfo(){
	}

	public RsSysIntlOperInfo(Integer id){
		this.id = id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RsSysIntlOperInfo rhs=(RsSysIntlOperInfo)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,operatorCode,imsiHead,englishName,countryId,provCode,tapVersion,tapRelease,tapDecimalPlaces,taxTreatment,taxRate,measureTypeId,reportErrorType,timeZoneId,networkStatement,validDate,expireDate,modifyDate,description}
}