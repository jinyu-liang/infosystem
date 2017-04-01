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
@XmlType(propOrder={"spec_char_id","product_id"})
public class SQueryMultiSimReq implements IComplexEntity{


	@XmlElement(name="spec_char_id")
	private Integer spec_char_id;

	@XmlElement(name="product_id")
	private Long product_id;

	public void setSpec_char_id(Integer obj){
		this.spec_char_id = obj;
	}

	public Integer getSpec_char_id(){
		return spec_char_id;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryMultiSimReq rhs=(SQueryMultiSimReq)rhs0;
		if(!ObjectUtils.equals(spec_char_id, rhs.spec_char_id)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(spec_char_id)
		.append(product_id)
		.toHashCode();
	}


}