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
@XmlType(propOrder={"freeResourceId","amount","remainAmount"})
public class FreeResourceLog implements IComplexEntity{


	@XmlElement(name="freeResourceId")
	private Long freeResourceId;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="remainAmount")
	private Long remainAmount;

	public void setFreeResourceId(Long obj){
		this.freeResourceId = obj;
	}

	public Long getFreeResourceId(){
		return freeResourceId;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setRemainAmount(Long obj){
		this.remainAmount = obj;
	}

	public Long getRemainAmount(){
		return remainAmount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FreeResourceLog rhs=(FreeResourceLog)rhs0;
		if(!ObjectUtils.equals(freeResourceId, rhs.freeResourceId)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(remainAmount, rhs.remainAmount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeResourceId)
		.append(amount)
		.append(remainAmount)
		.toHashCode();
	}


}