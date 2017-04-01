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
@XmlType(propOrder={"productFN"})
public class ProductFNList implements IComplexEntity{


	@XmlElement(name="productFN")
	private ProductFN[] productFN;

	public void setProductFN(ProductFN[] obj){
		this.productFN = obj;
	}

	public ProductFN[] getProductFN(){
		return productFN;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ProductFNList rhs=(ProductFNList)rhs0;
		if(!ObjectUtils.equals(productFN, rhs.productFN)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productFN)
		.toHashCode();
	}


}