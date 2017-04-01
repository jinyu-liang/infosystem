package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"productOrder"})
public class Do_queryPrimaryProductResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="productOrder")
	private SProductOrder productOrder;

	public void setProductOrder(SProductOrder obj){
		this.productOrder = obj;
	}

	public SProductOrder getProductOrder(){
		return productOrder;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryPrimaryProductResponse rhs=(Do_queryPrimaryProductResponse)rhs0;
		if(!ObjectUtils.equals(productOrder, rhs.productOrder)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productOrder)
		.toHashCode();
	}


}