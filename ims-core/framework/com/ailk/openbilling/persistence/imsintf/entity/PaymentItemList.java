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
@XmlType(propOrder={"paymentItem_Item"})
public class PaymentItemList implements IComplexEntity{


	@XmlElement(name="paymentItem_Item")
	private PaymentItem[] paymentItem_Item;

	public void setPaymentItem_Item(PaymentItem[] obj){
		this.paymentItem_Item = obj;
	}

	public PaymentItem[] getPaymentItem_Item(){
		return paymentItem_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentItemList rhs=(PaymentItemList)rhs0;
		if(!ObjectUtils.equals(paymentItem_Item, rhs.paymentItem_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentItem_Item)
		.toHashCode();
	}


}