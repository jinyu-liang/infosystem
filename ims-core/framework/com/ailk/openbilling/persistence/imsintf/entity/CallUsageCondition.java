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
@XmlType(propOrder={"destination_number","call_duration","network_operator_destination_number"})
public class CallUsageCondition implements IComplexEntity{


	@XmlElement(name="destination_number")
	private String destination_number;

	@XmlElement(name="call_duration")
	private Integer call_duration;

	@XmlElement(name="network_operator_destination_number")
	private Short network_operator_destination_number;

	public void setDestination_number(String obj){
		this.destination_number = obj;
	}

	public String getDestination_number(){
		return destination_number;
	}

	public void setCall_duration(Integer obj){
		this.call_duration = obj;
	}

	public Integer getCall_duration(){
		return call_duration;
	}

	public void setNetwork_operator_destination_number(Short obj){
		this.network_operator_destination_number = obj;
	}

	public Short getNetwork_operator_destination_number(){
		return network_operator_destination_number;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CallUsageCondition rhs=(CallUsageCondition)rhs0;
		if(!ObjectUtils.equals(destination_number, rhs.destination_number)) return false;
		if(!ObjectUtils.equals(call_duration, rhs.call_duration)) return false;
		if(!ObjectUtils.equals(network_operator_destination_number, rhs.network_operator_destination_number)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(destination_number)
		.append(call_duration)
		.append(network_operator_destination_number)
		.toHashCode();
	}


}