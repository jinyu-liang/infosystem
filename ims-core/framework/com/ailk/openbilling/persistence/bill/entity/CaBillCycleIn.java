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
@XmlType(propOrder={"opType","acctId","resourceId","cycleType","cycleUnit","startBillDay","cycleSpecId","outSoDate","outSoNbr","firstBillType","isDelay","opId","custId","countyCode","regionCode","measureId","channelId","isBillRun","customerType","isNotify","isRepeat"})
public class CaBillCycleIn implements IComplexEntity{


	@XmlElement(name="opType")
	private Short opType;

	@XmlElement(name="acctId")
	private Long acctId;

	@XmlElement(name="resourceId")
	private Long resourceId;

	@XmlElement(name="cycleType")
	private Short cycleType;

	@XmlElement(name="cycleUnit")
	private Integer cycleUnit;

	@XmlElement(name="startBillDay")
	private Integer startBillDay;

	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	@XmlElement(name="outSoDate")
	private Date outSoDate;

	@XmlElement(name="outSoNbr")
	private Long outSoNbr;

	@XmlElement(name="firstBillType")
	private Short firstBillType;

	@XmlElement(name="isDelay")
	private Short isDelay;

	@XmlElement(name="opId")
	private Long opId;

	@XmlElement(name="custId")
	private Long custId;

	@XmlElement(name="countyCode")
	private Integer countyCode;

	@XmlElement(name="regionCode")
	private Integer regionCode;

	@XmlElement(name="measureId")
	private Integer measureId;

	@XmlElement(name="channelId")
	private Integer channelId;

	@XmlElement(name="isBillRun")
	private Short isBillRun;

	@XmlElement(name="customerType")
	private Integer customerType;

	@XmlElement(name="isNotify")
	private Short isNotify;

	@XmlElement(name="isRepeat")
	private Short isRepeat;

	public void setOpType(Short obj){
		this.opType = obj;
	}

	public Short getOpType(){
		return opType;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setResourceId(Long obj){
		this.resourceId = obj;
	}

	public Long getResourceId(){
		return resourceId;
	}

	public void setCycleType(Short obj){
		this.cycleType = obj;
	}

	public Short getCycleType(){
		return cycleType;
	}

	public void setCycleUnit(Integer obj){
		this.cycleUnit = obj;
	}

	public Integer getCycleUnit(){
		return cycleUnit;
	}

	public void setStartBillDay(Integer obj){
		this.startBillDay = obj;
	}

	public Integer getStartBillDay(){
		return startBillDay;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
	}

	public void setOutSoDate(Date obj){
		this.outSoDate = obj;
	}

	public Date getOutSoDate(){
		return outSoDate;
	}

	public void setOutSoNbr(Long obj){
		this.outSoNbr = obj;
	}

	public Long getOutSoNbr(){
		return outSoNbr;
	}

	public void setFirstBillType(Short obj){
		this.firstBillType = obj;
	}

	public Short getFirstBillType(){
		return firstBillType;
	}

	public void setIsDelay(Short obj){
		this.isDelay = obj;
	}

	public Short getIsDelay(){
		return isDelay;
	}

	public void setOpId(Long obj){
		this.opId = obj;
	}

	public Long getOpId(){
		return opId;
	}

	public void setCustId(Long obj){
		this.custId = obj;
	}

	public Long getCustId(){
		return custId;
	}

	public void setCountyCode(Integer obj){
		this.countyCode = obj;
	}

	public Integer getCountyCode(){
		return countyCode;
	}

	public void setRegionCode(Integer obj){
		this.regionCode = obj;
	}

	public Integer getRegionCode(){
		return regionCode;
	}

	public void setMeasureId(Integer obj){
		this.measureId = obj;
	}

	public Integer getMeasureId(){
		return measureId;
	}

	public void setChannelId(Integer obj){
		this.channelId = obj;
	}

	public Integer getChannelId(){
		return channelId;
	}

	public void setIsBillRun(Short obj){
		this.isBillRun = obj;
	}

	public Short getIsBillRun(){
		return isBillRun;
	}

	public void setCustomerType(Integer obj){
		this.customerType = obj;
	}

	public Integer getCustomerType(){
		return customerType;
	}

	public void setIsNotify(Short obj){
		this.isNotify = obj;
	}

	public Short getIsNotify(){
		return isNotify;
	}

	public void setIsRepeat(Short obj){
		this.isRepeat = obj;
	}

	public Short getIsRepeat(){
		return isRepeat;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaBillCycleIn rhs=(CaBillCycleIn)rhs0;
		if(!ObjectUtils.equals(opType, rhs.opType)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(resourceId, rhs.resourceId)) return false;
		if(!ObjectUtils.equals(cycleType, rhs.cycleType)) return false;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		if(!ObjectUtils.equals(startBillDay, rhs.startBillDay)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(outSoDate, rhs.outSoDate)) return false;
		if(!ObjectUtils.equals(outSoNbr, rhs.outSoNbr)) return false;
		if(!ObjectUtils.equals(firstBillType, rhs.firstBillType)) return false;
		if(!ObjectUtils.equals(isDelay, rhs.isDelay)) return false;
		if(!ObjectUtils.equals(opId, rhs.opId)) return false;
		if(!ObjectUtils.equals(custId, rhs.custId)) return false;
		if(!ObjectUtils.equals(countyCode, rhs.countyCode)) return false;
		if(!ObjectUtils.equals(regionCode, rhs.regionCode)) return false;
		if(!ObjectUtils.equals(measureId, rhs.measureId)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(isBillRun, rhs.isBillRun)) return false;
		if(!ObjectUtils.equals(customerType, rhs.customerType)) return false;
		if(!ObjectUtils.equals(isNotify, rhs.isNotify)) return false;
		if(!ObjectUtils.equals(isRepeat, rhs.isRepeat)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(opType)
		.append(acctId)
		.append(resourceId)
		.append(cycleType)
		.append(cycleUnit)
		.append(startBillDay)
		.append(cycleSpecId)
		.append(outSoDate)
		.append(outSoNbr)
		.append(firstBillType)
		.append(isDelay)
		.append(opId)
		.append(custId)
		.append(countyCode)
		.append(regionCode)
		.append(measureId)
		.append(channelId)
		.append(isBillRun)
		.append(customerType)
		.append(isNotify)
		.append(isRepeat)
		.toHashCode();
	}


}