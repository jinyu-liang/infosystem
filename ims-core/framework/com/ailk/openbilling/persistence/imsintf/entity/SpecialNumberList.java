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
@XmlType(propOrder={"specialNumberList_Item"})
public class SpecialNumberList implements IComplexEntity{


	@XmlElement(name="specialNumberList_Item")
	private SpecialNumber[] specialNumberList_Item;

	public void setSpecialNumberList_Item(SpecialNumber[] obj){
		this.specialNumberList_Item = obj;
	}

	public SpecialNumber[] getSpecialNumberList_Item(){
		return specialNumberList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SpecialNumberList rhs=(SpecialNumberList)rhs0;
		if(!ObjectUtils.equals(specialNumberList_Item, rhs.specialNumberList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(specialNumberList_Item)
		.toHashCode();
	}


}