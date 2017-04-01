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
@XmlType(propOrder={"resource_id","phone_id","rerating_flag"})
public class SUserReratingReg implements IComplexEntity{


	@XmlElement(name="resource_id")
	private Long resource_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="rerating_flag")
	private Integer rerating_flag;

	public void setResource_id(Long obj){
		this.resource_id = obj;
	}

	public Long getResource_id(){
		return resource_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setRerating_flag(Integer obj){
		this.rerating_flag = obj;
	}

	public Integer getRerating_flag(){
		return rerating_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserReratingReg rhs=(SUserReratingReg)rhs0;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(rerating_flag, rhs.rerating_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(resource_id)
		.append(phone_id)
		.append(rerating_flag)
		.toHashCode();
	}


}