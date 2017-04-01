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
@XmlType(propOrder={"operator_id","organize_id"})
public class SQueryOperReq implements IComplexEntity{


	@XmlElement(name="operator_id")
	private Long operator_id;

	@XmlElement(name="organize_id")
	private Long organize_id;

	public void setOperator_id(Long obj){
		this.operator_id = obj;
	}

	public Long getOperator_id(){
		return operator_id;
	}

	public void setOrganize_id(Long obj){
		this.organize_id = obj;
	}

	public Long getOrganize_id(){
		return organize_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryOperReq rhs=(SQueryOperReq)rhs0;
		if(!ObjectUtils.equals(operator_id, rhs.operator_id)) return false;
		if(!ObjectUtils.equals(organize_id, rhs.organize_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(operator_id)
		.append(organize_id)
		.toHashCode();
	}


}