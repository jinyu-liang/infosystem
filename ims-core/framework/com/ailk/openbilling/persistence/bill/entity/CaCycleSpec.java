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
@XmlType(propOrder={"expriedate","validate","firstBillDate","cycleType","cycleUnit"})
public class CaCycleSpec implements IComplexEntity{


	@XmlElement(name="expriedate")
	private Date expriedate;

	@XmlElement(name="validate")
	private Date validate;

	@XmlElement(name="firstBillDate")
	private Integer firstBillDate;

	@XmlElement(name="cycleType")
	private Integer cycleType;

	@XmlElement(name="cycleUnit")
	private Integer cycleUnit;

	public void setExpriedate(Date obj){
		this.expriedate = obj;
	}

	public Date getExpriedate(){
		return expriedate;
	}

	public void setValidate(Date obj){
		this.validate = obj;
	}

	public Date getValidate(){
		return validate;
	}

	public void setFirstBillDate(Integer obj){
		this.firstBillDate = obj;
	}

	public Integer getFirstBillDate(){
		return firstBillDate;
	}

	public void setCycleType(Integer obj){
		this.cycleType = obj;
	}

	public Integer getCycleType(){
		return cycleType;
	}

	public void setCycleUnit(Integer obj){
		this.cycleUnit = obj;
	}

	public Integer getCycleUnit(){
		return cycleUnit;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaCycleSpec rhs=(CaCycleSpec)rhs0;
		if(!ObjectUtils.equals(expriedate, rhs.expriedate)) return false;
		if(!ObjectUtils.equals(validate, rhs.validate)) return false;
		if(!ObjectUtils.equals(firstBillDate, rhs.firstBillDate)) return false;
		if(!ObjectUtils.equals(cycleType, rhs.cycleType)) return false;
		if(!ObjectUtils.equals(cycleUnit, rhs.cycleUnit)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(expriedate)
		.append(validate)
		.append(firstBillDate)
		.append(cycleType)
		.append(cycleUnit)
		.toHashCode();
	}


}