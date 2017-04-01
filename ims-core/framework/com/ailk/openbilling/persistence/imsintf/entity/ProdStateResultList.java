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
@XmlType(propOrder={"productState"})
public class ProdStateResultList implements IComplexEntity{


	@XmlElement(name="productState")
	private ProdStateResult[] productState;

	public void setProductState(ProdStateResult[] obj){
		this.productState = obj;
	}

	public ProdStateResult[] getProductState(){
		return productState;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProdStateResultList rhs=(ProdStateResultList)rhs0;
		if(!ObjectUtils.equals(productState, rhs.productState)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productState)
		.toHashCode();
	}


}