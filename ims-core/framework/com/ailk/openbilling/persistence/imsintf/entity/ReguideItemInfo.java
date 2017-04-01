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
@XmlType(propOrder={"itemId","totalActualCharge","totalUsageCharge","reguideDtlInfoList","reguideMobileNo","unit","chargeUnit","totalActualUsage"})
public class ReguideItemInfo implements IComplexEntity{


	@XmlElement(name="reguideDtlInfoList")
	private ReguideDtlInfoList reguideDtlInfoList;

	@XmlElement(name="itemId")
	private Long itemId;

	@XmlElement(name="totalActualCharge")
	private Long totalActualCharge;

	@XmlElement(name="totalUsageCharge")
	private Long totalUsageCharge;

	@XmlElement(name="reguideMobileNo")
	private String reguideMobileNo;

	@XmlElement(name="unit")
	private Integer unit;

	@XmlElement(name="chargeUnit")
	private Integer chargeUnit;

	@XmlElement(name="totalActualUsage")
	private Long totalActualUsage;

	public void setReguideDtlInfoList(ReguideDtlInfoList obj){
		this.reguideDtlInfoList = obj;
	}

	public ReguideDtlInfoList getReguideDtlInfoList(){
		return reguideDtlInfoList;
	}

	public void setItemId(Long obj){
		this.itemId = obj;
	}

	public Long getItemId(){
		return itemId;
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

	public void setReguideMobileNo(String obj){
		this.reguideMobileNo = obj;
	}

	public String getReguideMobileNo(){
		return reguideMobileNo;
	}

	public void setUnit(Integer obj){
		this.unit = obj;
	}

	public Integer getUnit(){
		return unit;
	}

	public void setChargeUnit(Integer obj){
		this.chargeUnit = obj;
	}

	public Integer getChargeUnit(){
		return chargeUnit;
	}

	public void setTotalActualUsage(Long obj){
		this.totalActualUsage = obj;
	}

	public Long getTotalActualUsage(){
		return totalActualUsage;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideItemInfo rhs=(ReguideItemInfo)rhs0;
		if(!ObjectUtils.equals(reguideDtlInfoList, rhs.reguideDtlInfoList)) return false;
		if(!ObjectUtils.equals(itemId, rhs.itemId)) return false;
		if(!ObjectUtils.equals(totalActualCharge, rhs.totalActualCharge)) return false;
		if(!ObjectUtils.equals(totalUsageCharge, rhs.totalUsageCharge)) return false;
		if(!ObjectUtils.equals(reguideMobileNo, rhs.reguideMobileNo)) return false;
		if(!ObjectUtils.equals(unit, rhs.unit)) return false;
		if(!ObjectUtils.equals(chargeUnit, rhs.chargeUnit)) return false;
		if(!ObjectUtils.equals(totalActualUsage, rhs.totalActualUsage)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideDtlInfoList)
		.append(itemId)
		.append(totalActualCharge)
		.append(totalUsageCharge)
		.append(reguideMobileNo)
		.append(unit)
		.append(chargeUnit)
		.append(totalActualUsage)
		.toHashCode();
	}


}