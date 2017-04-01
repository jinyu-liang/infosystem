package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"customer"})
public class Do_queryCustomerInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="customer")
	private SCustomer customer;

	public void setCustomer(SCustomer obj){
		this.customer = obj;
	}

	public SCustomer getCustomer(){
		return customer;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryCustomerInfoResponse rhs=(Do_queryCustomerInfoResponse)rhs0;
		if(!ObjectUtils.equals(customer, rhs.customer)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(customer)
		.toHashCode();
	}


}