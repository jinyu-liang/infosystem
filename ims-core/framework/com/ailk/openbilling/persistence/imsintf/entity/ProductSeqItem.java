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
@XmlType(propOrder={"product_id","resource_id","resource_value","oper_type","resource_unit","effective_type"})
public class ProductSeqItem implements IComplexEntity{


	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="resource_id")
	private Integer resource_id;

	@XmlElement(name="resource_value")
	private Long resource_value;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="resource_unit")
	private String resource_unit;

	@XmlElement(name="effective_type")
	private Short effective_type;

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setResource_id(Integer obj){
		this.resource_id = obj;
	}

	public Integer getResource_id(){
		return resource_id;
	}

	public void setResource_value(Long obj){
		this.resource_value = obj;
	}

	public Long getResource_value(){
		return resource_value;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setResource_unit(String obj){
		this.resource_unit = obj;
	}

	public String getResource_unit(){
		return resource_unit;
	}

	public void setEffective_type(Short obj){
		this.effective_type = obj;
	}

	public Short getEffective_type(){
		return effective_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProductSeqItem rhs=(ProductSeqItem)rhs0;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(resource_value, rhs.resource_value)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(resource_unit, rhs.resource_unit)) return false;
		if(!ObjectUtils.equals(effective_type, rhs.effective_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_id)
		.append(resource_id)
		.append(resource_value)
		.append(oper_type)
		.append(resource_unit)
		.append(effective_type)
		.toHashCode();
	}


}