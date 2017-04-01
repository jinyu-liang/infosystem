package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"phone_id","product_sequence_id","resource_id","resource_value","effective_date"})
public class MaxFreeResourceResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="product_sequence_id")
	private Long product_sequence_id;

	@XmlElement(name="resource_id")
	private Integer resource_id;

	@XmlElement(name="resource_value")
	private Long resource_value;

	@XmlElement(name="effective_date")
	private Date effective_date;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setProduct_sequence_id(Long obj){
		this.product_sequence_id = obj;
	}

	public Long getProduct_sequence_id(){
		return product_sequence_id;
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

	public void setEffective_date(Date obj){
		this.effective_date = obj;
	}

	public Date getEffective_date(){
		return effective_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MaxFreeResourceResponse rhs=(MaxFreeResourceResponse)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		if(!ObjectUtils.equals(resource_id, rhs.resource_id)) return false;
		if(!ObjectUtils.equals(resource_value, rhs.resource_value)) return false;
		if(!ObjectUtils.equals(effective_date, rhs.effective_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(product_sequence_id)
		.append(resource_id)
		.append(resource_value)
		.append(effective_date)
		.toHashCode();
	}


}