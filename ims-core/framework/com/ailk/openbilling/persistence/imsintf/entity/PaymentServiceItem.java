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
@XmlType(propOrder={"item_code","amount","sequence"})
public class PaymentServiceItem implements IComplexEntity{


	@XmlElement(name="item_code")
	private Short item_code;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="sequence")
	private Short sequence;

	public void setItem_code(Short obj){
		this.item_code = obj;
	}

	public Short getItem_code(){
		return item_code;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setSequence(Short obj){
		this.sequence = obj;
	}

	public Short getSequence(){
		return sequence;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentServiceItem rhs=(PaymentServiceItem)rhs0;
		if(!ObjectUtils.equals(item_code, rhs.item_code)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(sequence, rhs.sequence)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(item_code)
		.append(amount)
		.append(sequence)
		.toHashCode();
	}


}