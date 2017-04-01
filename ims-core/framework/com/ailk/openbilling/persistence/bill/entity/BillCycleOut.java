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
@XmlType(propOrder={"endate","billday","accountsts","expiredate","billPlanId","name","createDate","paymentPlanId","startdate","acctId","validate","cycleSpecId","accountClass","so_date","billFormatId","rangSpecId"})
public class BillCycleOut implements IComplexEntity{


	@XmlElement(name="endate")
	private Date endate;

	@XmlElement(name="billday")
	private Integer billday;

	@XmlElement(name="accountsts")
	private Short accountsts;

	@XmlElement(name="expiredate")
	private Date expiredate;

	@XmlElement(name="billPlanId")
	private Long billPlanId;

	@XmlElement(name="name")
	private String name;

	@XmlElement(name="createDate")
	private Date createDate;

	@XmlElement(name="paymentPlanId")
	private Long paymentPlanId;

	@XmlElement(name="startdate")
	private Date startdate;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="validate")
	private Date validate;

	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	@XmlElement(name="accountClass")
	private Integer accountClass;

	@XmlElement(name="so_date")
	private Date so_date;

	@XmlElement(name="billFormatId")
	private Integer billFormatId;

	@XmlElement(name="rangSpecId")
	private Integer rangSpecId;

	public void setEndate(Date obj){
		this.endate = obj;
	}

	public Date getEndate(){
		return endate;
	}

	public void setBillday(Integer obj){
		this.billday = obj;
	}

	public Integer getBillday(){
		return billday;
	}

	public void setAccountsts(Short obj){
		this.accountsts = obj;
	}

	public Short getAccountsts(){
		return accountsts;
	}

	public void setExpiredate(Date obj){
		this.expiredate = obj;
	}

	public Date getExpiredate(){
		return expiredate;
	}

	public void setBillPlanId(Long obj){
		this.billPlanId = obj;
	}

	public Long getBillPlanId(){
		return billPlanId;
	}

	public void setName(String obj){
		this.name = obj;
	}

	public String getName(){
		return name;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setPaymentPlanId(Long obj){
		this.paymentPlanId = obj;
	}

	public Long getPaymentPlanId(){
		return paymentPlanId;
	}

	public void setStartdate(Date obj){
		this.startdate = obj;
	}

	public Date getStartdate(){
		return startdate;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setValidate(Date obj){
		this.validate = obj;
	}

	public Date getValidate(){
		return validate;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
	}

	public void setAccountClass(Integer obj){
		this.accountClass = obj;
	}

	public Integer getAccountClass(){
		return accountClass;
	}

	public void setSo_date(Date obj){
		this.so_date = obj;
	}

	public Date getSo_date(){
		return so_date;
	}

	public void setBillFormatId(Integer obj){
		this.billFormatId = obj;
	}

	public Integer getBillFormatId(){
		return billFormatId;
	}

	public void setRangSpecId(Integer obj){
		this.rangSpecId = obj;
	}

	public Integer getRangSpecId(){
		return rangSpecId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillCycleOut rhs=(BillCycleOut)rhs0;
		if(!ObjectUtils.equals(endate, rhs.endate)) return false;
		if(!ObjectUtils.equals(billday, rhs.billday)) return false;
		if(!ObjectUtils.equals(accountsts, rhs.accountsts)) return false;
		if(!ObjectUtils.equals(expiredate, rhs.expiredate)) return false;
		if(!ObjectUtils.equals(billPlanId, rhs.billPlanId)) return false;
		if(!ObjectUtils.equals(name, rhs.name)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(paymentPlanId, rhs.paymentPlanId)) return false;
		if(!ObjectUtils.equals(startdate, rhs.startdate)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(validate, rhs.validate)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(accountClass, rhs.accountClass)) return false;
		if(!ObjectUtils.equals(so_date, rhs.so_date)) return false;
		if(!ObjectUtils.equals(billFormatId, rhs.billFormatId)) return false;
		if(!ObjectUtils.equals(rangSpecId, rhs.rangSpecId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(endate)
		.append(billday)
		.append(accountsts)
		.append(expiredate)
		.append(billPlanId)
		.append(name)
		.append(createDate)
		.append(paymentPlanId)
		.append(startdate)
		.append(acctId)
		.append(validate)
		.append(cycleSpecId)
		.append(accountClass)
		.append(so_date)
		.append(billFormatId)
		.append(rangSpecId)
		.toHashCode();
	}


}