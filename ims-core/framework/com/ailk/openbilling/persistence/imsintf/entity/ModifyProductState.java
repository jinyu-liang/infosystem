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
@XmlType(propOrder={"phone_id","product_id","offer_id","new_state","action_date","user_id"})
public class ModifyProductState implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="offer_id")
	private Long offer_id;

	@XmlElement(name="new_state")
	private Short new_state;

	@XmlElement(name="action_date")
	private String action_date;

	@XmlElement(name="user_id")
	private Long user_id;

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

	public void setOffer_id(Long obj){
		this.offer_id = obj;
	}

	public Long getOffer_id(){
		return offer_id;
	}

	public void setNew_state(Short obj){
		this.new_state = obj;
	}

	public Short getNew_state(){
		return new_state;
	}

	public void setAction_date(String obj){
		this.action_date = obj;
	}

	public String getAction_date(){
		return action_date;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ModifyProductState rhs=(ModifyProductState)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(offer_id, rhs.offer_id)) return false;
		if(!ObjectUtils.equals(new_state, rhs.new_state)) return false;
		if(!ObjectUtils.equals(action_date, rhs.action_date)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(product_id)
		.append(offer_id)
		.append(new_state)
		.append(action_date)
		.append(user_id)
		.toHashCode();
	}


}