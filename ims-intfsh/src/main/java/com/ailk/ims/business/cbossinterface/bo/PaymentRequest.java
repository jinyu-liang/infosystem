package com.ailk.ims.business.cbossinterface.bo;

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
@XmlType(propOrder={"iDType","iDValue","transactionID","actionDate","payed","cnlTyp","subID","payedType"})
public class PaymentRequest implements IComplexEntity{


	@XmlElement(name="iDType")
	private String iDType;

	@XmlElement(name="iDValue")
	private String iDValue;

	@XmlElement(name="transactionID")
	private String transactionID;

	@XmlElement(name="actionDate")
	private String actionDate;

	@XmlElement(name="payed")
	private Long payed;

	@XmlElement(name="cnlTyp")
	private String cnlTyp;

	@XmlElement(name="subID")
	private String subID;

	@XmlElement(name="payedType")
	private String payedType;

	public void setIDType(String obj){
		this.iDType = obj;
	}

	public String getIDType(){
		return iDType;
	}

	public void setIDValue(String obj){
		this.iDValue = obj;
	}

	public String getIDValue(){
		return iDValue;
	}

	public void setTransactionID(String obj){
		this.transactionID = obj;
	}

	public String getTransactionID(){
		return transactionID;
	}

	public void setActionDate(String obj){
		this.actionDate = obj;
	}

	public String getActionDate(){
		return actionDate;
	}

	public void setPayed(Long obj){
		this.payed = obj;
	}

	public Long getPayed(){
		return payed;
	}

	public void setCnlTyp(String obj){
		this.cnlTyp = obj;
	}

	public String getCnlTyp(){
		return cnlTyp;
	}

	public void setSubID(String obj){
		this.subID = obj;
	}

	public String getSubID(){
		return subID;
	}

	public void setPayedType(String obj){
		this.payedType = obj;
	}

	public String getPayedType(){
		return payedType;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentRequest rhs=(PaymentRequest)rhs0;
		if(!ObjectUtils.equals(iDType, rhs.iDType)) return false;
		if(!ObjectUtils.equals(iDValue, rhs.iDValue)) return false;
		if(!ObjectUtils.equals(transactionID, rhs.transactionID)) return false;
		if(!ObjectUtils.equals(actionDate, rhs.actionDate)) return false;
		if(!ObjectUtils.equals(payed, rhs.payed)) return false;
		if(!ObjectUtils.equals(cnlTyp, rhs.cnlTyp)) return false;
		if(!ObjectUtils.equals(subID, rhs.subID)) return false;
		if(!ObjectUtils.equals(payedType, rhs.payedType)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(iDType)
		.append(iDValue)
		.append(transactionID)
		.append(actionDate)
		.append(payed)
		.append(cnlTyp)
		.append(subID)
		.append(payedType)
		.toHashCode();
	}


}