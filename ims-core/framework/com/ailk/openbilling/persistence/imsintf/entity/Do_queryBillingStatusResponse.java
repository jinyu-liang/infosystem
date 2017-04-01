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
@XmlType(propOrder={"billing_status","status_start_date"})
public class Do_queryBillingStatusResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="billing_status")
	private Short billing_status;

	@XmlElement(name="status_start_date")
	private String status_start_date;

	public void setBilling_status(Short obj){
		this.billing_status = obj;
	}

	public Short getBilling_status(){
		return billing_status;
	}

	public void setStatus_start_date(String obj){
		this.status_start_date = obj;
	}

	public String getStatus_start_date(){
		return status_start_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBillingStatusResponse rhs=(Do_queryBillingStatusResponse)rhs0;
		if(!ObjectUtils.equals(billing_status, rhs.billing_status)) return false;
		if(!ObjectUtils.equals(status_start_date, rhs.status_start_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billing_status)
		.append(status_start_date)
		.toHashCode();
	}


}