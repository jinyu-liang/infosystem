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
@XmlType(propOrder={"startTime","oppNumber","totalUsage","unit","totalActualCharge","totalUsageCharge","chargeUnit","totalOwnerPaid","totalPayerPaid","reguideAcctInfoList"})
public class ReguideDtlInfo implements IComplexEntity{


	@XmlElement(name="reguideAcctInfoList")
	private ReguideAcctInfoList reguideAcctInfoList;

	@XmlElement(name="startTime")
	private String startTime;

	@XmlElement(name="oppNumber")
	private String oppNumber;

	@XmlElement(name="totalUsage")
	private Long totalUsage;

	@XmlElement(name="unit")
	private Integer unit;

	@XmlElement(name="totalActualCharge")
	private Long totalActualCharge;

	@XmlElement(name="totalUsageCharge")
	private Long totalUsageCharge;

	@XmlElement(name="chargeUnit")
	private Integer chargeUnit;

	@XmlElement(name="totalOwnerPaid")
	private Long totalOwnerPaid;

	@XmlElement(name="totalPayerPaid")
	private Long totalPayerPaid;

	public void setReguideAcctInfoList(ReguideAcctInfoList obj){
		this.reguideAcctInfoList = obj;
	}

	public ReguideAcctInfoList getReguideAcctInfoList(){
		return reguideAcctInfoList;
	}

	public void setStartTime(String obj){
		this.startTime = obj;
	}

	public String getStartTime(){
		return startTime;
	}

	public void setOppNumber(String obj){
		this.oppNumber = obj;
	}

	public String getOppNumber(){
		return oppNumber;
	}

	public void setTotalUsage(Long obj){
		this.totalUsage = obj;
	}

	public Long getTotalUsage(){
		return totalUsage;
	}

	public void setUnit(Integer obj){
		this.unit = obj;
	}

	public Integer getUnit(){
		return unit;
	}

	public void setTotalActualCharge(Long obj){
		this.totalActualCharge = obj;
	}

	public Long getTotalActualCharge(){
		return totalActualCharge;
	}

	public void setTotalUsageCharge(Long obj){
		this.totalUsageCharge = obj;
	}

	public Long getTotalUsageCharge(){
		return totalUsageCharge;
	}

	public void setChargeUnit(Integer obj){
		this.chargeUnit = obj;
	}

	public Integer getChargeUnit(){
		return chargeUnit;
	}

	public void setTotalOwnerPaid(Long obj){
		this.totalOwnerPaid = obj;
	}

	public Long getTotalOwnerPaid(){
		return totalOwnerPaid;
	}

	public void setTotalPayerPaid(Long obj){
		this.totalPayerPaid = obj;
	}

	public Long getTotalPayerPaid(){
		return totalPayerPaid;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideDtlInfo rhs=(ReguideDtlInfo)rhs0;
		if(!ObjectUtils.equals(reguideAcctInfoList, rhs.reguideAcctInfoList)) return false;
		if(!ObjectUtils.equals(startTime, rhs.startTime)) return false;
		if(!ObjectUtils.equals(oppNumber, rhs.oppNumber)) return false;
		if(!ObjectUtils.equals(totalUsage, rhs.totalUsage)) return false;
		if(!ObjectUtils.equals(unit, rhs.unit)) return false;
		if(!ObjectUtils.equals(totalActualCharge, rhs.totalActualCharge)) return false;
		if(!ObjectUtils.equals(totalUsageCharge, rhs.totalUsageCharge)) return false;
		if(!ObjectUtils.equals(chargeUnit, rhs.chargeUnit)) return false;
		if(!ObjectUtils.equals(totalOwnerPaid, rhs.totalOwnerPaid)) return false;
		if(!ObjectUtils.equals(totalPayerPaid, rhs.totalPayerPaid)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideAcctInfoList)
		.append(startTime)
		.append(oppNumber)
		.append(totalUsage)
		.append(unit)
		.append(totalActualCharge)
		.append(totalUsageCharge)
		.append(chargeUnit)
		.append(totalOwnerPaid)
		.append(totalPayerPaid)
		.toHashCode();
	}


}