package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"product_id","cmResIdentity","cmProdOffering"})
public class SQueryUsersReturn implements IComplexEntity{


	@XmlElement(name="cmResIdentity")
	private CmResIdentity cmResIdentity;

	@XmlElement(name="cmProdOffering")
	private PmProductOffering cmProdOffering;

	@XmlElement(name="product_id")
	private Long product_id;

	public void setCmResIdentity(CmResIdentity obj){
		this.cmResIdentity = obj;
	}

	public CmResIdentity getCmResIdentity(){
		return cmResIdentity;
	}

	public void setCmProdOffering(PmProductOffering obj){
		this.cmProdOffering = obj;
	}

	public PmProductOffering getCmProdOffering(){
		return cmProdOffering;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryUsersReturn rhs=(SQueryUsersReturn)rhs0;
		if(!ObjectUtils.equals(cmResIdentity, rhs.cmResIdentity)) return false;
		if(!ObjectUtils.equals(cmProdOffering, rhs.cmProdOffering)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cmResIdentity)
		.append(cmProdOffering)
		.append(product_id)
		.toHashCode();
	}


}