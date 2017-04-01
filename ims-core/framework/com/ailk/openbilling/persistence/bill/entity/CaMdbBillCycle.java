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
@XmlType(propOrder={"acctId","billPlanId","cycleUnit","cycleType","billDateShift","prodId","servId","itemCode","validDate","expireDate","cycleSpecId","firstBillDate"})
public class CaMdbBillCycle implements IComplexEntity{


	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="billPlanId")
	private Long billPlanId;

	@XmlElement(name="cycleUnit")
	private Integer cycleUnit;

	@XmlElement(name="cycleType")
	private Integer cycleType;

	@XmlElement(name="billDateShift")
	private Integer billDateShift;

	@XmlElement(name="prodId")
	private Long prodId;

	@XmlElement(name="servId")
	private Long servId;

	@XmlElement(name="itemCode")
	private Integer itemCode;

	@XmlElement(name="validDate")
	private Date validDate;

	@XmlElement(name="expireDate")
	private Date expireDate;

	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	@XmlElement(name="firstBillDate")
	private Date firstBillDate;

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setBillPlanId(Long obj){
		this.billPlanId = obj;
	}

	public Long getBillPlanId(){
		return billPlanId;
	}

	public void setCycleUnit(Integer obj){
		this.cycleUnit = obj;
	}

	public Integer getCycleUnit(){
		return cycleUnit;
	}

	public void setCycleType(Integer obj){
		this.cycleType = obj;
	}

	public Integer getCycleType(){
		return cycleType;
	}

	public void setBillDateShift(Integer obj){
		this.billDateShift = obj;
	}

	public Integer getBillDateShift(){
		return billDateShift;
	}

	public void setProdId(Long obj){
		this.prodId = obj;
	}

	public Long getProdId(){
		return prodId;
	}

	public void setServId(Long obj){
		this.servId = obj;
	}

	public Long getServId(){
		return servId;
	}

	public void setItemCode(Integer obj){
		this.itemCode = obj;
	}

	public Integer getItemCode(){
		return itemCode;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
	}

	public void setFirstBillDate(Date obj){
		this.firstBillDate = obj;
	}

	public Date getFirstBillDate(){
		return firstBillDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaMdbBillCycle rhs=(CaMdbBillCycle)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(billPlanId, rhs.billPlanId)) return false;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		if(!ObjectUtils.equals(cycleType, rhs.cycleType)) return false;
		if(!ObjectUtils.equals(billDateShift, rhs.billDateShift)) return false;
		if(!ObjectUtils.equals(prodId, rhs.prodId)) return false;
		if(!ObjectUtils.equals(servId, rhs.servId)) return false;
		if(!ObjectUtils.equals(itemCode, rhs.itemCode)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(firstBillDate, rhs.firstBillDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(billPlanId)
		.append(cycleUnit)
		.append(cycleType)
		.append(billDateShift)
		.append(prodId)
		.append(servId)
		.append(itemCode)
		.append(validDate)
		.append(expireDate)
		.append(cycleSpecId)
		.append(firstBillDate)
		.toHashCode();
	}


}