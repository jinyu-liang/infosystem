package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"billingPlanId","acctId","outDate","num","isExportShortBillCycle","isSupress","isPrepaid"})
public class QryBillingPlanIn implements IComplexEntity{


	@XmlElement(name="billingPlanId")
	private Long billingPlanId;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="outDate")
	private Date outDate;

	@XmlElement(name="num")
	private Integer num;

	@XmlElement(name="isExportShortBillCycle")
	private Short isExportShortBillCycle;

	@XmlElement(name="isSupress")
	private Short isSupress;

	@XmlElement(name="isPrepaid")
	private Short isPrepaid;

	public void setBillingPlanId(Long obj){
		this.billingPlanId = obj;
	}

	public Long getBillingPlanId(){
		return billingPlanId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setOutDate(Date obj){
		this.outDate = obj;
	}

	public Date getOutDate(){
		return outDate;
	}

	public void setNum(Integer obj){
		this.num = obj;
	}

	public Integer getNum(){
		return num;
	}

	public void setIsExportShortBillCycle(Short obj){
		this.isExportShortBillCycle = obj;
	}

	public Short getIsExportShortBillCycle(){
		return isExportShortBillCycle;
	}

	public void setIsSupress(Short obj){
		this.isSupress = obj;
	}

	public Short getIsSupress(){
		return isSupress;
	}

	public void setIsPrepaid(Short obj){
		this.isPrepaid = obj;
	}

	public Short getIsPrepaid(){
		return isPrepaid;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QryBillingPlanIn rhs=(QryBillingPlanIn)rhs0;
		if(!ObjectUtils.equals(billingPlanId, rhs.billingPlanId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(outDate, rhs.outDate)) return false;
		if(!ObjectUtils.equals(num, rhs.num)) return false;
		if(!ObjectUtils.equals(isExportShortBillCycle, rhs.isExportShortBillCycle)) return false;
		if(!ObjectUtils.equals(isSupress, rhs.isSupress)) return false;
		if(!ObjectUtils.equals(isPrepaid, rhs.isPrepaid)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billingPlanId)
		.append(acctId)
		.append(outDate)
		.append(num)
		.append(isExportShortBillCycle)
		.append(isSupress)
		.append(isPrepaid)
		.toHashCode();
	}


}