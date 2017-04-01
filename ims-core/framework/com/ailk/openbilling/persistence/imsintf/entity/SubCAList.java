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
@XmlType(propOrder={"subCAList_Item"})
public class SubCAList implements IComplexEntity{


	@XmlElement(name="subCAList_Item")
	private SubCA[] subCAList_Item;

	public void setSubCAList_Item(SubCA[] obj){
		this.subCAList_Item = obj;
	}

	public SubCA[] getSubCAList_Item(){
		return subCAList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SubCAList rhs=(SubCAList)rhs0;
		if(!ObjectUtils.equals(subCAList_Item, rhs.subCAList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(subCAList_Item)
		.toHashCode();
	}


}