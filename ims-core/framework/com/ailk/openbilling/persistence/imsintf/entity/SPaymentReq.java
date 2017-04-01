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
@XmlType(propOrder={"paymentDate","createDate","paymentChannel","paymentType","orderType","paymentInfo","bankCode"})
public class SPaymentReq implements IComplexEntity{


	@XmlElement(name="paymentInfo")
	private PaymentInfo paymentInfo;

	@XmlElement(name="paymentDate")
	private String paymentDate;

	@XmlElement(name="createDate")
	private String createDate;

	@XmlElement(name="paymentChannel")
	private Short paymentChannel;

	@XmlElement(name="paymentType")
	private Short paymentType;

	@XmlElement(name="orderType")
	private Short orderType;

	@XmlElement(name="bankCode")
	private String bankCode;

	public void setPaymentInfo(PaymentInfo obj){
		this.paymentInfo = obj;
	}

	public PaymentInfo getPaymentInfo(){
		return paymentInfo;
	}

	public void setPaymentDate(String obj){
		this.paymentDate = obj;
	}

	public String getPaymentDate(){
		return paymentDate;
	}

	public void setCreateDate(String obj){
		this.createDate = obj;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setPaymentChannel(Short obj){
		this.paymentChannel = obj;
	}

	public Short getPaymentChannel(){
		return paymentChannel;
	}

	public void setPaymentType(Short obj){
		this.paymentType = obj;
	}

	public Short getPaymentType(){
		return paymentType;
	}

	public void setOrderType(Short obj){
		this.orderType = obj;
	}

	public Short getOrderType(){
		return orderType;
	}

	public void setBankCode(String obj){
		this.bankCode = obj;
	}

	public String getBankCode(){
		return bankCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SPaymentReq rhs=(SPaymentReq)rhs0;
		if(!ObjectUtils.equals(paymentInfo, rhs.paymentInfo)) return false;
		if(!ObjectUtils.equals(paymentDate, rhs.paymentDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(paymentChannel, rhs.paymentChannel)) return false;
		if(!ObjectUtils.equals(paymentType, rhs.paymentType)) return false;
		if(!ObjectUtils.equals(orderType, rhs.orderType)) return false;
		if(!ObjectUtils.equals(bankCode, rhs.bankCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentInfo)
		.append(paymentDate)
		.append(createDate)
		.append(paymentChannel)
		.append(paymentType)
		.append(orderType)
		.append(bankCode)
		.toHashCode();
	}


}