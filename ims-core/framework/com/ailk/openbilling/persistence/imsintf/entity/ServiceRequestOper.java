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
@XmlType(propOrder={"service_oper_type","serviceRequestList"})
public class ServiceRequestOper implements IComplexEntity{


	@XmlElement(name="serviceRequestList")
	private ServiceRequestList serviceRequestList;

	@XmlElement(name="service_oper_type")
	private Short service_oper_type;

	public void setServiceRequestList(ServiceRequestList obj){
		this.serviceRequestList = obj;
	}

	public ServiceRequestList getServiceRequestList(){
		return serviceRequestList;
	}

	public void setService_oper_type(Short obj){
		this.service_oper_type = obj;
	}

	public Short getService_oper_type(){
		return service_oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ServiceRequestOper rhs=(ServiceRequestOper)rhs0;
		if(!ObjectUtils.equals(serviceRequestList, rhs.serviceRequestList)) return false;
		if(!ObjectUtils.equals(service_oper_type, rhs.service_oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(serviceRequestList)
		.append(service_oper_type)
		.toHashCode();
	}


}