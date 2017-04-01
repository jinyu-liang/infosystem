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
@Table(schema="CD",name="IMS_MDB_ERROR")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id","busiCode","createDate","custId","acctId","resourceId","productId","upField","dealDate","sts","dealCount","regionCode","countyCode","errorCode","errorMsg"})
public class ImsMdbError extends DataObject{


	/**
	 * bosฤฺฒฟผวยผม๗หฎบล
	 */
	@Id
	@Column(name="ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="id")
	private Long id;

	/**
	 * าตฮ๑ด๚ย๋
	 */
	@Id
	@Column(name="BUSI_CODE",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiCode")
	private Integer busiCode;

	/**
	 * ผวยผดดฝจสฑผไ
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="CUST_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="custId")
	private Long custId;

	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="acctId")
	private Long acctId;

	@Column(name="RESOURCE_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="resourceId")
	private Long resourceId;

	@Column(name="PRODUCT_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="productId")
	private Long productId;

	/**
	 * ษฯทขฑ๊ึพฮปฃฌรฟาปฮปด๚ฑําปีลฑํฃฌสึตฮช1ฑํสพถิำฆตฤฑํะ่าชษฯทข
	 */
	@Column(name="UP_FIELD",columnDefinition="Varchar",length=64)
	@XmlElement(name="upField")
	private String upField;

	/**
	 * ษฯทขสฑผไ
	 */
	@Column(name="DEAL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="dealDate")
	private Date dealDate;

	/**
	 * ษฯทขืดฬฌกฃ
0 - ฮดษฯทขณษนฆ
1 - ษฯทขณษนฆ
	 */
	@Column(name="STS",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="sts")
	private Integer sts;

	/**
	 * ษฯทขดฮส
	 */
	@Column(name="DEAL_COUNT",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="dealCount")
	private Integer dealCount;

	@Column(name="REGION_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="regionCode")
	private Integer regionCode;

	@Column(name="COUNTY_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="countyCode")
	private Integer countyCode;

	@Column(name="ERROR_CODE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="errorCode")
	private Integer errorCode;

	@Column(name="ERROR_MSG",columnDefinition="Varchar",length=256)
	@XmlElement(name="errorMsg")
	private String errorMsg;

	public void setId(Long obj){
		this.id = obj;
	}

	public Long getId(){
		return id;
	}

	public void setBusiCode(Integer obj){
		this.busiCode = obj;
	}

	public Integer getBusiCode(){
		return busiCode;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCustId(Long obj){
		this.custId = obj;
	}

	public Long getCustId(){
		return custId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setProductId(Long obj){
		this.productId = obj;
	}

	public Long getProductId(){
		return productId;
	}

	public void setUpField(String obj){
		this.upField = obj;
	}

	public String getUpField(){
		return upField;
	}

	public void setDealDate(Date obj){
		this.dealDate = obj;
	}

	public Date getDealDate(){
		return dealDate;
	}

	public void setSts(Integer obj){
		this.sts = obj;
	}

	public Integer getSts(){
		return sts;
	}

	public void setDealCount(Integer obj){
		this.dealCount = obj;
	}

	public Integer getDealCount(){
		return dealCount;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setCountyCode(Integer obj){
		this.countyCode = obj;
	}

	public Integer getCountyCode(){
		return countyCode;
	}

	public void setErrorCode(Integer obj){
		this.errorCode = obj;
	}

	public Integer getErrorCode(){
		return errorCode;
	}

	public void setErrorMsg(String obj){
		this.errorMsg = obj;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public ImsMdbError(){
	}

	public ImsMdbError(Long id,Integer busiCode){
		this.id = id;
		this.busiCode = busiCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ImsMdbError rhs=(ImsMdbError)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		if(!ObjectUtils.equals(busiCode, rhs.busiCode)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.append(busiCode)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{id,busiCode,createDate,custId,acctId,resourceId,productId,upField,dealDate,sts,dealCount,regionCode,countyCode,errorCode,errorMsg}
}