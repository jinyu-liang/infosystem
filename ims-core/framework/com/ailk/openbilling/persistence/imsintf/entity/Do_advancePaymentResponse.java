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
@XmlType(propOrder={"so_nbr","payment_so_nbr","advancepay_so_nbr"})
public class Do_advancePaymentResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="so_nbr")
	private Long so_nbr;

	@XmlElement(name="payment_so_nbr")
	private Long payment_so_nbr;

	@XmlElement(name="advancepay_so_nbr")
	private Long advancepay_so_nbr;

	public void setSo_nbr(Long obj){
		this.so_nbr = obj;
	}

	public Long getSo_nbr(){
		return so_nbr;
	}

	public void setPayment_so_nbr(Long obj){
		this.payment_so_nbr = obj;
	}

	public Long getPayment_so_nbr(){
		return payment_so_nbr;
	}

	public void setAdvancepay_so_nbr(Long obj){
		this.advancepay_so_nbr = obj;
	}

	public Long getAdvancepay_so_nbr(){
		return advancepay_so_nbr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_advancePaymentResponse rhs=(Do_advancePaymentResponse)rhs0;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		if(!ObjectUtils.equals(payment_so_nbr, rhs.payment_so_nbr)) return false;
		if(!ObjectUtils.equals(advancepay_so_nbr, rhs.advancepay_so_nbr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(so_nbr)
		.append(payment_so_nbr)
		.append(advancepay_so_nbr)
		.toHashCode();
	}


}