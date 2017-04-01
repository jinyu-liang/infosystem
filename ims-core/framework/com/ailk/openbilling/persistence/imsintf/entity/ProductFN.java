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
@XmlType(propOrder={"productOrder","fNList","phone_id"})
public class ProductFN implements IComplexEntity{


	@XmlElement(name="productOrder")
	private SProductOrder productOrder;

	@XmlElement(name="fNList")
	private FNList fNList;

	@XmlElement(name="phone_id")
	private String phone_id;

	public void setProductOrder(SProductOrder obj){
		this.productOrder = obj;
	}

	public SProductOrder getProductOrder(){
		return productOrder;
	}

	public void setFNList(FNList obj){
		this.fNList = obj;
	}

	public FNList getFNList(){
		return fNList;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProductFN rhs=(ProductFN)rhs0;
		if(!ObjectUtils.equals(productOrder, rhs.productOrder)) return false;
		if(!ObjectUtils.equals(fNList, rhs.fNList)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productOrder)
		.append(fNList)
		.append(phone_id)
		.toHashCode();
	}


}