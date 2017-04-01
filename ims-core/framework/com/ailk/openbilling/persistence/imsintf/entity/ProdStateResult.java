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
@XmlType(propOrder={"phone_id","prodcut_id","offering_id","offering_type","valid_date","expire_date","prod_state"})
public class ProdStateResult implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="prodcut_id")
	private Long prodcut_id;

	@XmlElement(name="offering_id")
	private int offering_id;

	@XmlElement(name="offering_type")
	private Short offering_type;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="prod_state")
	private Short prod_state;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setProdcut_id(Long obj){
		this.prodcut_id = obj;
	}

	public Long getProdcut_id(){
		return prodcut_id;
	}

	public void setOffering_id(int obj){
		this.offering_id = obj;
	}

	public int getOffering_id(){
		return offering_id;
	}

	public void setOffering_type(Short obj){
		this.offering_type = obj;
	}

	public Short getOffering_type(){
		return offering_type;
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

	public void setProd_state(Short obj){
		this.prod_state = obj;
	}

	public Short getProd_state(){
		return prod_state;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProdStateResult rhs=(ProdStateResult)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(prodcut_id, rhs.prodcut_id)) return false;
		if(!ObjectUtils.equals(offering_id, rhs.offering_id)) return false;
		if(!ObjectUtils.equals(offering_type, rhs.offering_type)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(prod_state, rhs.prod_state)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(prodcut_id)
		.append(offering_id)
		.append(offering_type)
		.append(valid_date)
		.append(expire_date)
		.append(prod_state)
		.toHashCode();
	}


}