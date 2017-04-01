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
@XmlType(propOrder={"service_state_list"})
public class Do_queryServiceStateResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="service_state_list")
	private ServiceStateResultList service_state_list;

	public void setService_state_list(ServiceStateResultList obj){
		this.service_state_list = obj;
	}

	public ServiceStateResultList getService_state_list(){
		return service_state_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryServiceStateResponse rhs=(Do_queryServiceStateResponse)rhs0;
		if(!ObjectUtils.equals(service_state_list, rhs.service_state_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(service_state_list)
		.toHashCode();
	}


}