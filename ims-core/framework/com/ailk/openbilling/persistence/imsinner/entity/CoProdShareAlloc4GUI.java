package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"phoneId","resourceId","billFlag","itemId","itemName","amount","expireDate"})
public class CoProdShareAlloc4GUI implements IComplexEntity{


	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="resourceId")
	private Long resourceId;

	@XmlElement(name="billFlag")
	private Integer billFlag;

	@XmlElement(name="itemId")
	private Integer itemId;

	@XmlElement(name="itemName")
	private String itemName;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="expireDate")
	private Date expireDate;

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setBillFlag(Integer obj){
		this.billFlag = obj;
	}

	public Integer getBillFlag(){
		return billFlag;
	}

	public void setItemId(Integer obj){
		this.itemId = obj;
	}

	public Integer getItemId(){
		return itemId;
	}

	public void setItemName(String obj){
		this.itemName = obj;
	}

	public String getItemName(){
		return itemName;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CoProdShareAlloc4GUI rhs=(CoProdShareAlloc4GUI)rhs0;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(billFlag, rhs.billFlag)) return false;
		if(!ObjectUtils.equals(itemId, rhs.itemId)) return false;
		if(!ObjectUtils.equals(itemName, rhs.itemName)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneId)
		.append(resourceId)
		.append(billFlag)
		.append(itemId)
		.append(itemName)
		.append(amount)
		.append(expireDate)
		.toHashCode();
	}


}