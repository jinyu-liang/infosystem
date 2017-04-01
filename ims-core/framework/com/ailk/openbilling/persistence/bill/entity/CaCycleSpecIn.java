package com.ailk.openbilling.persistence.bill.entity;

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
@XmlType(propOrder={"cycleType","cycleUnit","startBillDay","acctId"})
public class CaCycleSpecIn implements IComplexEntity{


	@XmlElement(name="cycleType")
	private Short cycleType;

	@XmlElement(name="cycleUnit")
	private Integer cycleUnit;

	@XmlElement(name="startBillDay")
	private Integer startBillDay;

	@XmlElement(name="acctId")
	private Long acctId;

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

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaCycleSpecIn rhs=(CaCycleSpecIn)rhs0;
		if(!ObjectUtils.equals(cycleType, rhs.cycleType)) return false;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		if(!ObjectUtils.equals(startBillDay, rhs.startBillDay)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cycleType)
		.append(cycleUnit)
		.append(startBillDay)
		.append(acctId)
		.toHashCode();
	}


}