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
@XmlType(propOrder={"phone_id","service_id","service_status"})
public class ServiceStateResult implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="service_id")
	private int service_id;

	@XmlElement(name="service_status")
	private short service_status;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setService_id(int obj){
		this.service_id = obj;
	}

	public int getService_id(){
		return service_id;
	}

	public void setService_status(short obj){
		this.service_status = obj;
	}

	public short getService_status(){
		return service_status;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ServiceStateResult rhs=(ServiceStateResult)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(service_id, rhs.service_id)) return false;
		if(!ObjectUtils.equals(service_status, rhs.service_status)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(service_id)
		.append(service_status)
		.toHashCode();
	}


}