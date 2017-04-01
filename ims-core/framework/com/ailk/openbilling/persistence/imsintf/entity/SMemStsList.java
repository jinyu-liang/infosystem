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
@XmlType(propOrder={"sMemStsItem"})
public class SMemStsList implements IComplexEntity{


	@XmlElement(name="sMemStsItem")
	private SMemSts[] sMemStsItem;

	public void setSMemStsItem(SMemSts[] obj){
		this.sMemStsItem = obj;
	}

	public SMemSts[] getSMemStsItem(){
		return sMemStsItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMemStsList rhs=(SMemStsList)rhs0;
		if(!ObjectUtils.equals(sMemStsItem, rhs.sMemStsItem)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sMemStsItem)
		.toHashCode();
	}


}