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
@XmlType(propOrder={"phoneId","resource_id"})
public class SQueryUserStsInfoReq implements IComplexEntity{


	@XmlElement(name="phoneId")
	private String phoneId;

	@XmlElement(name="resource_id")
	private Long resource_id;

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public void setResource_id(Long obj){
		this.resource_id = obj;
	}

	public Long getResource_id(){
		return resource_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryUserStsInfoReq rhs=(SQueryUserStsInfoReq)rhs0;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneId)
		.append(resource_id)
		.toHashCode();
	}


}