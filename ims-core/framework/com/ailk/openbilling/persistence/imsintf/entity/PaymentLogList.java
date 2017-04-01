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
@XmlType(propOrder={"paymentLogList_Item"})
public class PaymentLogList implements IComplexEntity{


	@XmlElement(name="paymentLogList_Item")
	private PaymentLog[] paymentLogList_Item;

	public void setPaymentLogList_Item(PaymentLog[] obj){
		this.paymentLogList_Item = obj;
	}

	public PaymentLog[] getPaymentLogList_Item(){
		return paymentLogList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentLogList rhs=(PaymentLogList)rhs0;
		if(!ObjectUtils.equals(paymentLogList_Item, rhs.paymentLogList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentLogList_Item)
		.toHashCode();
	}


}