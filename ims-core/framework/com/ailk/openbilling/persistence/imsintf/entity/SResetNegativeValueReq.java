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
@XmlType(propOrder={"id"})
public class SResetNegativeValueReq implements IComplexEntity{


	@XmlElement(name="id")
	private Integer id;

	public void setId(Integer obj){
		this.id = obj;
	}

	public Integer getId(){
		return id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SResetNegativeValueReq rhs=(SResetNegativeValueReq)rhs0;
		if(!ObjectUtils.equals(id, rhs.id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(id)
		.toHashCode();
	}


}