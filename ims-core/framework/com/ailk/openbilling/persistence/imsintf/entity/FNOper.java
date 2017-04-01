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
@XmlType(propOrder={"oper_type","product_id","fN_list"})
public class FNOper implements IComplexEntity{


	@XmlElement(name="fN_list")
	private FNList fN_list;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="product_id")
	private Long product_id;

	public void setFN_list(FNList obj){
		this.fN_list = obj;
	}

	public FNList getFN_list(){
		return fN_list;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setProduct_id(Long obj){
		this.product_id = obj;
	}

	public Long getProduct_id(){
		return product_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FNOper rhs=(FNOper)rhs0;
		if(!ObjectUtils.equals(fN_list, rhs.fN_list)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(product_id, rhs.product_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(fN_list)
		.append(oper_type)
		.append(product_id)
		.toHashCode();
	}


}