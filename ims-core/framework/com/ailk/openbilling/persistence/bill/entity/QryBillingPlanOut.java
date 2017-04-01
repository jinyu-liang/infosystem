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
@XmlType(propOrder={"endDate","startDate","cycleSpecId","billingPlanOut","lastEndDate","lastStartDate"})
public class QryBillingPlanOut implements IComplexEntity{


	@XmlElement(name="billingPlanOut")
	private List<BillingPlanOut> billingPlanOut;

	@XmlElement(name="endDate")
	private Date endDate;

	@XmlElement(name="startDate")
	private Date startDate;

	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	@XmlElement(name="lastEndDate")
	private Date lastEndDate;

	@XmlElement(name="lastStartDate")
	private Date lastStartDate;

	public void setBillingPlanOut(List<BillingPlanOut> obj){
		this.billingPlanOut = obj;
	}

	public List<BillingPlanOut> getBillingPlanOut(){
		return billingPlanOut;
	}

	public void setEndDate(Date obj){
		this.endDate = obj;
	}

	public Date getEndDate(){
		return endDate;
	}

	public void setStartDate(Date obj){
		this.startDate = obj;
	}

	public Date getStartDate(){
		return startDate;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
	}

	public void setLastEndDate(Date obj){
		this.lastEndDate = obj;
	}

	public Date getLastEndDate(){
		return lastEndDate;
	}

	public void setLastStartDate(Date obj){
		this.lastStartDate = obj;
	}

	public Date getLastStartDate(){
		return lastStartDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QryBillingPlanOut rhs=(QryBillingPlanOut)rhs0;
		if(!ObjectUtils.equals(billingPlanOut, rhs.billingPlanOut)) return false;
		if(!ObjectUtils.equals(endDate, rhs.endDate)) return false;
		if(!ObjectUtils.equals(startDate, rhs.startDate)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(lastEndDate, rhs.lastEndDate)) return false;
		if(!ObjectUtils.equals(lastStartDate, rhs.lastStartDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billingPlanOut)
		.append(endDate)
		.append(startDate)
		.append(cycleSpecId)
		.append(lastEndDate)
		.append(lastStartDate)
		.toHashCode();
	}


}