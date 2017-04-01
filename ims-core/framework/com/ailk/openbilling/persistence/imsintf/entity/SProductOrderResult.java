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
@XmlType(propOrder={"product_id","offer_id","so_id","valid_date","expire_date","product_param_list"})
public class SProductOrderResult implements IComplexEntity{


	@XmlElement(name="product_param_list")
	private SProductParamList product_param_list;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="offer_id")
	private Long offer_id;

	@XmlElement(name="so_id")
	private String so_id;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	public void setProduct_param_list(SProductParamList obj){
		this.product_param_list = obj;
	}

	public SProductParamList getProduct_param_list(){
		return product_param_list;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setOffer_id(Long obj){
		this.offer_id = obj;
	}

	public Long getOffer_id(){
		return offer_id;
	}

	public void setSo_id(String obj){
		this.so_id = obj;
	}

	public String getSo_id(){
		return so_id;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductOrderResult rhs=(SProductOrderResult)rhs0;
		if(!ObjectUtils.equals(product_param_list, rhs.product_param_list)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(offer_id, rhs.offer_id)) return false;
		if(!ObjectUtils.equals(so_id, rhs.so_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(product_param_list)
		.append(product_id)
		.append(offer_id)
		.append(so_id)
		.append(valid_date)
		.append(expire_date)
		.toHashCode();
	}


}