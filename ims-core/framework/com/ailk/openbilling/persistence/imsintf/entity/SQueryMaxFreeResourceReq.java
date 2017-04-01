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
@XmlType(propOrder={"acct_id","product_id","product_sequence_id","product_name"})
public class SQueryMaxFreeResourceReq implements IComplexEntity{


	@XmlElement(name="acct_id")
	private String acct_id;

	@XmlElement(name="product_id")
	private Long product_id;

	@XmlElement(name="product_sequence_id")
	private Long product_sequence_id;

	@XmlElement(name="product_name")
	private String product_name;

	public void setAcct_id(String obj){
		this.acct_id = obj;
	}

	public String getAcct_id(){
		return acct_id;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public void setProduct_sequence_id(Long obj){
		this.product_sequence_id = obj;
	}

	public Long getProduct_sequence_id(){
		return product_sequence_id;
	}

	public void setProduct_name(String obj){
		this.product_name = obj;
	}

	public String getProduct_name(){
		return product_name;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryMaxFreeResourceReq rhs=(SQueryMaxFreeResourceReq)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		if(!ObjectUtils.equals(product_sequence_id, rhs.product_sequence_id)) return false;
		if(!ObjectUtils.equals(product_name, rhs.product_name)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(product_id)
		.append(product_sequence_id)
		.append(product_name)
		.toHashCode();
	}


}