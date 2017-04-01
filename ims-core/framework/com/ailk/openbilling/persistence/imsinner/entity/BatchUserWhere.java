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
@XmlType(propOrder={"brand"})
public class BatchUserWhere implements IComplexEntity{


	@XmlElement(name="brand")
	private Short brand;

	public void setBrand(Short obj){
		this.brand = obj;
	}

	public Short getBrand(){
		return brand;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BatchUserWhere rhs=(BatchUserWhere)rhs0;
		if(!ObjectUtils.equals(brand, rhs.brand)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(brand)
		.toHashCode();
	}


}