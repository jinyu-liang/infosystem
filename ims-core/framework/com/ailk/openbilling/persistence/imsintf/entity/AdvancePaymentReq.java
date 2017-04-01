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
@XmlType(propOrder={"orgSoNbr","actionType","paymentDate","createDate","paymentChannel","paymentType","paymentInfo","bankCode"})
public class AdvancePaymentReq implements IComplexEntity{


	@XmlElement(name="paymentInfo")
	private PaymentInfo paymentInfo;

	@XmlElement(name="orgSoNbr")
	private String orgSoNbr;

	@XmlElement(name="actionType")
	private Short actionType;

	@XmlElement(name="paymentDate")
	private String paymentDate;

	@XmlElement(name="createDate")
	private String createDate;

	@XmlElement(name="paymentChannel")
	private Short paymentChannel;

	@XmlElement(name="paymentType")
	private Short paymentType;

	@XmlElement(name="bankCode")
	private String bankCode;

	public void setPaymentInfo(PaymentInfo obj){
		this.paymentInfo = obj;
	}

	public PaymentInfo getPaymentInfo(){
		return paymentInfo;
	}

	public void setOrgSoNbr(String obj){
		this.orgSoNbr = obj;
	}

	public String getOrgSoNbr(){
		return orgSoNbr;
	}

	public void setActionType(Short obj){
		this.actionType = obj;
	}

	public Short getActionType(){
		return actionType;
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

	public void setBankCode(String obj){
		this.bankCode = obj;
	}

	public String getBankCode(){
		return bankCode;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AdvancePaymentReq rhs=(AdvancePaymentReq)rhs0;
		if(!ObjectUtils.equals(paymentInfo, rhs.paymentInfo)) return false;
		if(!ObjectUtils.equals(orgSoNbr, rhs.orgSoNbr)) return false;
		if(!ObjectUtils.equals(actionType, rhs.actionType)) return false;
		if(!ObjectUtils.equals(paymentDate, rhs.paymentDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(paymentChannel, rhs.paymentChannel)) return false;
		if(!ObjectUtils.equals(paymentType, rhs.paymentType)) return false;
		if(!ObjectUtils.equals(bankCode, rhs.bankCode)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentInfo)
		.append(orgSoNbr)
		.append(actionType)
		.append(paymentDate)
		.append(createDate)
		.append(paymentChannel)
		.append(paymentType)
		.append(bankCode)
		.toHashCode();
	}


}