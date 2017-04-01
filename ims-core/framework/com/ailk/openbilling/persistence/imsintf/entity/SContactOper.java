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
@XmlType(propOrder={"oper_type","contact_list"})
public class SContactOper implements IComplexEntity{


	@XmlElement(name="contact_list")
	private SContactList contact_list;

	@XmlElement(name="oper_type")
	private Short oper_type;

	public void setContact_list(SContactList obj){
		this.contact_list = obj;
	}

	public SContactList getContact_list(){
		return contact_list;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SContactOper rhs=(SContactOper)rhs0;
		if(!ObjectUtils.equals(contact_list, rhs.contact_list)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(contact_list)
		.append(oper_type)
		.toHashCode();
	}


}