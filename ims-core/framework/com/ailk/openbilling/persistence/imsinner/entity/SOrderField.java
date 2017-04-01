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
@XmlType(propOrder={"field","type"})
public class SOrderField implements IComplexEntity{


	@XmlElement(name="field")
	private String field;

	@XmlElement(name="type")
	private Short type;

	public void setField(String obj){
		this.field = obj;
	}

	public String getField(){
		return field;
	}

	public void setType(Short obj){
		this.type = obj;
	}

	public Short getType(){
		return type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SOrderField rhs=(SOrderField)rhs0;
		if(!ObjectUtils.equals(field, rhs.field)) return false;
		if(!ObjectUtils.equals(type, rhs.type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(field)
		.append(type)
		.toHashCode();
	}


}