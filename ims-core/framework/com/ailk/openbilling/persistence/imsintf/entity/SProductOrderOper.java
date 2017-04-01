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
@XmlType(propOrder={"oper_type","prod_list"})
public class SProductOrderOper implements IComplexEntity{


	@XmlElement(name="prod_list")
	private SProductOrderList prod_list;

	@XmlElement(name="oper_type")
	private Short oper_type;

	public void setProd_list(SProductOrderList obj){
		this.prod_list = obj;
	}

	public SProductOrderList getProd_list(){
		return prod_list;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductOrderOper rhs=(SProductOrderOper)rhs0;
		if(!ObjectUtils.equals(prod_list, rhs.prod_list)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_list)
		.append(oper_type)
		.toHashCode();
	}


}