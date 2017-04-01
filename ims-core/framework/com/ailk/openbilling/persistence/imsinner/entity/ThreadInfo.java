package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"success_amount","fail_amount"})
public class ThreadInfo implements IComplexEntity{


	@XmlElement(name="success_amount")
	private Integer success_amount;

	@XmlElement(name="fail_amount")
	private Integer fail_amount;

	public void setSuccess_amount(Integer obj){
		this.success_amount = obj;
	}

	public Integer getSuccess_amount(){
		return success_amount;
	}

	public void setFail_amount(Integer obj){
		this.fail_amount = obj;
	}

	public Integer getFail_amount(){
		return fail_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ThreadInfo rhs=(ThreadInfo)rhs0;
		if(!ObjectUtils.equals(success_amount, rhs.success_amount)) return false;
		if(!ObjectUtils.equals(fail_amount, rhs.fail_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(success_amount)
		.append(fail_amount)
		.toHashCode();
	}


}