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
@XmlType(propOrder={"acct_id","resource_id","owner_type","billing_type"})
public class StaffInfo implements IComplexEntity{


	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="resource_id")
	private Long resource_id;

	@XmlElement(name="owner_type")
	private Integer owner_type;

	@XmlElement(name="billing_type")
	private Long billing_type;

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setResource_id(Long obj){
		this.resource_id = obj;
	}

	public Long getResource_id(){
		return resource_id;
	}

	public void setOwner_type(Integer obj){
		this.owner_type = obj;
	}

	public Integer getOwner_type(){
		return owner_type;
	}

	public void setBilling_type(Long obj){
		this.billing_type = obj;
	}

	public Long getBilling_type(){
		return billing_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		StaffInfo rhs=(StaffInfo)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(owner_type, rhs.owner_type)) return false;
		if(!ObjectUtils.equals(billing_type, rhs.billing_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(resource_id)
		.append(owner_type)
		.append(billing_type)
		.toHashCode();
	}


}