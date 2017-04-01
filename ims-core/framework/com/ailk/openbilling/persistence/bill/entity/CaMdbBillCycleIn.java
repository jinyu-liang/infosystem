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
@XmlType(propOrder={"soNbr","soDate"})
public class CaMdbBillCycleIn implements IComplexEntity{


	@XmlElement(name="soNbr")
	private Long soNbr;

	@XmlElement(name="soDate")
	private Long soDate;

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setSoDate(Long obj){
		this.soDate = obj;
	}

	public Long getSoDate(){
		return soDate;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaMdbBillCycleIn rhs=(CaMdbBillCycleIn)rhs0;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(soNbr)
		.append(soDate)
		.toHashCode();
	}


}