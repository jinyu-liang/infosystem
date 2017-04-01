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
@XmlType(propOrder={"cycle_type","cycle_unit","first_bill_day"})
public class SBillCycle implements IComplexEntity{


	@XmlElement(name="cycle_type")
	private Integer cycle_type;

	@XmlElement(name="cycle_unit")
	private Integer cycle_unit;

	@XmlElement(name="first_bill_day")
	private Integer first_bill_day;

	public void setCycle_type(Integer obj){
		this.cycle_type = obj;
	}

	public Integer getCycle_type(){
		return cycle_type;
	}

	public void setCycle_unit(Integer obj){
		this.cycle_unit = obj;
	}

	public Integer getCycle_unit(){
		return cycle_unit;
	}

	public void setFirst_bill_day(Integer obj){
		this.first_bill_day = obj;
	}

	public Integer getFirst_bill_day(){
		return first_bill_day;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBillCycle rhs=(SBillCycle)rhs0;
		if(!ObjectUtils.equals(cycle_type, rhs.cycle_type)) return false;
		if(!ObjectUtils.equals(cycle_unit, rhs.cycle_unit)) return false;
		if(!ObjectUtils.equals(first_bill_day, rhs.first_bill_day)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cycle_type)
		.append(cycle_unit)
		.append(first_bill_day)
		.toHashCode();
	}


}