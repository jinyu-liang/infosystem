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
@XmlType(propOrder={"paymentServiceItem"})
public class PaymentServiceItemList implements IComplexEntity{


	@XmlElement(name="paymentServiceItem")
	private PaymentServiceItem[] paymentServiceItem;

	public void setPaymentServiceItem(PaymentServiceItem[] obj){
		this.paymentServiceItem = obj;
	}

	public PaymentServiceItem[] getPaymentServiceItem(){
		return paymentServiceItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentServiceItemList rhs=(PaymentServiceItemList)rhs0;
		if(!ObjectUtils.equals(paymentServiceItem, rhs.paymentServiceItem)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentServiceItem)
		.toHashCode();
	}


}