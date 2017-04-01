package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
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
@XmlType(propOrder={"amount"})
public class Do_queryBalanceResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="amount")
	private Long amount;

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBalanceResponse rhs=(Do_queryBalanceResponse)rhs0;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(amount)
		.toHashCode();
	}


}