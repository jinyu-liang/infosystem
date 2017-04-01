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
@XmlType(propOrder={"productOfferingId","productOfferingName","offerTypeId","offerTypeName","lifecycleStatusId","lifecycleStatusName","brandId","brandName","priceNum","regionCode","offerDup","isMain","description"})
public class ProdOfferInfo implements IComplexEntity{


	@XmlElement(name="productOfferingId")
	private Long productOfferingId;

	@XmlElement(name="productOfferingName")
	private String productOfferingName;

	@XmlElement(name="offerTypeId")
	private Integer offerTypeId;

	@XmlElement(name="offerTypeName")
	private String offerTypeName;

	@XmlElement(name="lifecycleStatusId")
	private Integer lifecycleStatusId;

	@XmlElement(name="lifecycleStatusName")
	private String lifecycleStatusName;

	@XmlElement(name="brandId")
	private Integer brandId;

	@XmlElement(name="brandName")
	private String brandName;

	@XmlElement(name="priceNum")
	private Integer priceNum;

	@XmlElement(name="regionCode")
	private Integer regionCode;

	@XmlElement(name="offerDup")
	private Boolean offerDup;

	@XmlElement(name="isMain")
	private Integer isMain;

	@XmlElement(name="description")
	private String description;

	public void setProductOfferingId(Long obj){
		this.productOfferingId = obj;
	}

	public Long getProductOfferingId(){
		return productOfferingId;
	}

	public void setProductOfferingName(String obj){
		this.productOfferingName = obj;
	}

	public String getProductOfferingName(){
		return productOfferingName;
	}

	public void setOfferTypeId(Integer obj){
		this.offerTypeId = obj;
	}

	public Integer getOfferTypeId(){
		return offerTypeId;
	}

	public void setOfferTypeName(String obj){
		this.offerTypeName = obj;
	}

	public String getOfferTypeName(){
		return offerTypeName;
	}

	public void setLifecycleStatusId(Integer obj){
		this.lifecycleStatusId = obj;
	}

	public Integer getLifecycleStatusId(){
		return lifecycleStatusId;
	}

	public void setLifecycleStatusName(String obj){
		this.lifecycleStatusName = obj;
	}

	public String getLifecycleStatusName(){
		return lifecycleStatusName;
	}

	public void setBrandId(Integer obj){
		this.brandId = obj;
	}

	public Integer getBrandId(){
		return brandId;
	}

	public void setBrandName(String obj){
		this.brandName = obj;
	}

	public String getBrandName(){
		return brandName;
	}

	public void setPriceNum(Integer obj){
		this.priceNum = obj;
	}

	public Integer getPriceNum(){
		return priceNum;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setOfferDup(Boolean obj){
		this.offerDup = obj;
	}

	public Boolean isOfferDup(){
		return offerDup;
	}

	public void setIsMain(Integer obj){
		this.isMain = obj;
	}

	public Integer getIsMain(){
		return isMain;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProdOfferInfo rhs=(ProdOfferInfo)rhs0;
		if(!ObjectUtils.equals(productOfferingId, rhs.productOfferingId)) return false;
		if(!ObjectUtils.equals(productOfferingName, rhs.productOfferingName)) return false;
		if(!ObjectUtils.equals(offerTypeId, rhs.offerTypeId)) return false;
		if(!ObjectUtils.equals(offerTypeName, rhs.offerTypeName)) return false;
		if(!ObjectUtils.equals(lifecycleStatusId, rhs.lifecycleStatusId)) return false;
		if(!ObjectUtils.equals(lifecycleStatusName, rhs.lifecycleStatusName)) return false;
		if(!ObjectUtils.equals(brandId, rhs.brandId)) return false;
		if(!ObjectUtils.equals(brandName, rhs.brandName)) return false;
		if(!ObjectUtils.equals(priceNum, rhs.priceNum)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(offerDup, rhs.offerDup)) return false;
		if(!ObjectUtils.equals(isMain, rhs.isMain)) return false;
		if(!ObjectUtils.equals(description, rhs.description)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productOfferingId)
		.append(productOfferingName)
		.append(offerTypeId)
		.append(offerTypeName)
		.append(lifecycleStatusId)
		.append(lifecycleStatusName)
		.append(brandId)
		.append(brandName)
		.append(priceNum)
		.append(regionCode)
		.append(offerDup)
		.append(isMain)
		.append(description)
		.toHashCode();
	}


}