package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"brandId","offerTypeId","regionCode","prodSpecId","sts","start","limit","codeName","isMain","orderType","billType","acct_id"})
public class SQryProdOfferInfoReq implements IComplexEntity{


	@XmlElement(name="brandId")
	private Integer brandId;

	@XmlElement(name="offerTypeId")
	private Integer offerTypeId;

	@XmlElement(name="regionCode")
	private Integer regionCode;

	@XmlElement(name="prodSpecId")
	private Integer prodSpecId;

	@XmlElement(name="sts")
	private String sts;

	@XmlElement(name="start")
	private Integer start;

	@XmlElement(name="limit")
	private Integer limit;

	@XmlElement(name="codeName")
	private String codeName;

	@XmlElement(name="isMain")
	private Integer isMain;

	@XmlElement(name="orderType")
	private Integer orderType;

	@XmlElement(name="billType")
	private Short billType;

	@XmlElement(name="acct_id")
	private Long acct_id;

	public void setBrandId(Integer obj){
		this.brandId = obj;
	}

	public Integer getBrandId(){
		return brandId;
	}

	public void setOfferTypeId(Integer obj){
		this.offerTypeId = obj;
	}

	public Integer getOfferTypeId(){
		return offerTypeId;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setProdSpecId(Integer obj){
		this.prodSpecId = obj;
	}

	public Integer getProdSpecId(){
		return prodSpecId;
	}

	public void setSts(String obj){
		this.sts = obj;
	}

	public String getSts(){
		return sts;
	}

	public void setStart(Integer obj){
		this.start = obj;
	}

	public Integer getStart(){
		return start;
	}

	public void setLimit(Integer obj){
		this.limit = obj;
	}

	public Integer getLimit(){
		return limit;
	}

	public void setCodeName(String obj){
		this.codeName = obj;
	}

	public String getCodeName(){
		return codeName;
	}

	public void setIsMain(Integer obj){
		this.isMain = obj;
	}

	public Integer getIsMain(){
		return isMain;
	}

	public void setOrderType(Integer obj){
		this.orderType = obj;
	}

	public Integer getOrderType(){
		return orderType;
	}

	public void setBillType(Short obj){
		this.billType = obj;
	}

	public Short getBillType(){
		return billType;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQryProdOfferInfoReq rhs=(SQryProdOfferInfoReq)rhs0;
		if(!ObjectUtils.equals(brandId, rhs.brandId)) return false;
		if(!ObjectUtils.equals(offerTypeId, rhs.offerTypeId)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(prodSpecId, rhs.prodSpecId)) return false;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(start, rhs.start)) return false;
		if(!ObjectUtils.equals(limit, rhs.limit)) return false;
		if(!ObjectUtils.equals(codeName, rhs.codeName)) return false;
		if(!ObjectUtils.equals(isMain, rhs.isMain)) return false;
		if(!ObjectUtils.equals(orderType, rhs.orderType)) return false;
		if(!ObjectUtils.equals(billType, rhs.billType)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(brandId)
		.append(offerTypeId)
		.append(regionCode)
		.append(prodSpecId)
		.append(sts)
		.append(start)
		.append(limit)
		.append(codeName)
		.append(isMain)
		.append(orderType)
		.append(billType)
		.append(acct_id)
		.toHashCode();
	}


}