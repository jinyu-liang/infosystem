package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.List;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"paymentPlanId","acctId","opId","outSoDate","outSoNbr","chgFlag","expireDate","paymentChannelInList"})
public class PaymentPlanChgIn implements IComplexEntity{


	@XmlElement(name="paymentChannelInList")
	private List<PaymentChannelIn> paymentChannelInList;

	@XmlElement(name="paymentPlanId")
	private Long paymentPlanId;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="opId")
	private Integer opId;

	@XmlElement(name="outSoDate")
	private Date outSoDate;

	@XmlElement(name="outSoNbr")
	private String outSoNbr;

	@XmlElement(name="chgFlag")
	private Short chgFlag;

	@XmlElement(name="expireDate")
	private Date expireDate;

	public void setPaymentChannelInList(List<PaymentChannelIn> obj){
		this.paymentChannelInList = obj;
	}

	public List<PaymentChannelIn> getPaymentChannelInList(){
		return paymentChannelInList;
	}

	public void setPaymentPlanId(Long obj){
		this.paymentPlanId = obj;
	}

	public Long getPaymentPlanId(){
		return paymentPlanId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setOpId(Integer obj){
		this.opId = obj;
	}

	public Integer getOpId(){
		return opId;
	}

	public void setOutSoDate(Date obj){
		this.outSoDate = obj;
	}

	public Date getOutSoDate(){
		return outSoDate;
	}

	public void setOutSoNbr(String obj){
		this.outSoNbr = obj;
	}

	public String getOutSoNbr(){
		return outSoNbr;
	}

	public void setChgFlag(Short obj){
		this.chgFlag = obj;
	}

	public Short getChgFlag(){
		return chgFlag;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentPlanChgIn rhs=(PaymentPlanChgIn)rhs0;
		if(!ObjectUtils.equals(paymentChannelInList, rhs.paymentChannelInList)) return false;
		if(!ObjectUtils.equals(paymentPlanId, rhs.paymentPlanId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(outSoDate, rhs.outSoDate)) return false;
		if(!ObjectUtils.equals(outSoNbr, rhs.outSoNbr)) return false;
		if(!ObjectUtils.equals(chgFlag, rhs.chgFlag)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(paymentChannelInList)
		.append(paymentPlanId)
		.append(acctId)
		.append(opId)
		.append(outSoDate)
		.append(outSoNbr)
		.append(chgFlag)
		.append(expireDate)
		.toHashCode();
	}


}