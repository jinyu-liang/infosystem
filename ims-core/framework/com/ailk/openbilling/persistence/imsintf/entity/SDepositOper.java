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
@XmlType(propOrder={"oper_type","sDeposit"})
public class SDepositOper implements IComplexEntity{


	@XmlElement(name="sDeposit")
	private SDeposit sDeposit;

	@XmlElement(name="oper_type")
	private Short oper_type;

	public void setSDeposit(SDeposit obj){
		this.sDeposit = obj;
	}

	public SDeposit getSDeposit(){
		return sDeposit;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SDepositOper rhs=(SDepositOper)rhs0;
		if(!ObjectUtils.equals(sDeposit, rhs.sDeposit)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sDeposit)
		.append(oper_type)
		.toHashCode();
	}


}