package com.ailk.openbilling.persistence.imsintf.entity;

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
@XmlType(propOrder={"bookItem","amount","validDate","expireDate","measureId"})
public class SDeposit implements IComplexEntity{


	@XmlElement(name="bookItem")
	private Integer bookItem;

	@XmlElement(name="amount")
	private Double amount;

	@XmlElement(name="validDate")
	private String validDate;

	@XmlElement(name="expireDate")
	private String expireDate;

	@XmlElement(name="measureId")
	private Integer measureId;

	public void setBookItem(Integer obj){
		this.bookItem = obj;
	}

	public Integer getBookItem(){
		return bookItem;
	}

	public void setAmount(Double obj){
		this.amount = obj;
	}

	public Double getAmount(){
		return amount;
	}

	public void setValidDate(String obj){
		this.validDate = obj;
	}

	public String getValidDate(){
		return validDate;
	}

	public void setExpireDate(String obj){
		this.expireDate = obj;
	}

	public String getExpireDate(){
		return expireDate;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDeposit rhs=(SDeposit)rhs0;
		if(!ObjectUtils.equals(bookItem, rhs.bookItem)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bookItem)
		.append(amount)
		.append(validDate)
		.append(expireDate)
		.append(measureId)
		.toHashCode();
	}


}