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
@XmlType(propOrder={"user_id","phone_id","product_id","offering_id"})
public class SQueryProdState implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="offering_id")
	private Integer offering_id;

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setOffering_id(Integer obj){
		this.offering_id = obj;
	}

	public Integer getOffering_id(){
		return offering_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryProdState rhs=(SQueryProdState)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(offering_id, rhs.offering_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(phone_id)
		.append(product_id)
		.append(offering_id)
		.toHashCode();
	}


}