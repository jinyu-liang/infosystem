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
@XmlType(propOrder={"serviceState"})
public class ServiceStateResultList implements IComplexEntity{


	@XmlElement(name="serviceState")
	private ServiceStateResult[] serviceState;

	public void setServiceState(ServiceStateResult[] obj){
		this.serviceState = obj;
	}

	public ServiceStateResult[] getServiceState(){
		return serviceState;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ServiceStateResultList rhs=(ServiceStateResultList)rhs0;
		if(!ObjectUtils.equals(serviceState, rhs.serviceState)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(serviceState)
		.toHashCode();
	}


}