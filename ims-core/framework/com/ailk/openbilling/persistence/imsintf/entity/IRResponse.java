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
@XmlType(propOrder={"operator_id","operator_code","busi_service_code","service_sequence_id"})
public class IRResponse implements IComplexEntity{


	@XmlElement(name="operator_id")
	private Integer operator_id;

	@XmlElement(name="operator_code")
	private String operator_code;

	@XmlElement(name="busi_service_code")
	private String busi_service_code;

	@XmlElement(name="service_sequence_id")
	private Integer service_sequence_id;

	public void setOperator_id(Integer obj){
		this.operator_id = obj;
	}

	public Integer getOperator_id(){
		return operator_id;
	}

	public void setOperator_code(String obj){
		this.operator_code = obj;
	}

	public String getOperator_code(){
		return operator_code;
	}

	public void setBusi_service_code(String obj){
		this.busi_service_code = obj;
	}

	public String getBusi_service_code(){
		return busi_service_code;
	}

	public void setService_sequence_id(Integer obj){
		this.service_sequence_id = obj;
	}

	public Integer getService_sequence_id(){
		return service_sequence_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		IRResponse rhs=(IRResponse)rhs0;
		if(!ObjectUtils.equals(operator_id, rhs.operator_id)) return false;
		if(!ObjectUtils.equals(operator_code, rhs.operator_code)) return false;
		if(!ObjectUtils.equals(busi_service_code, rhs.busi_service_code)) return false;
		if(!ObjectUtils.equals(service_sequence_id, rhs.service_sequence_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(operator_id)
		.append(operator_code)
		.append(busi_service_code)
		.append(service_sequence_id)
		.toHashCode();
	}


}