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
@XmlType(propOrder={"modCAList_Item"})
public class ModCAList implements IComplexEntity{


	@XmlElement(name="modCAList_Item")
	private ModCA[] modCAList_Item;

	public void setModCAList_Item(ModCA[] obj){
		this.modCAList_Item = obj;
	}

	public ModCA[] getModCAList_Item(){
		return modCAList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ModCAList rhs=(ModCAList)rhs0;
		if(!ObjectUtils.equals(modCAList_Item, rhs.modCAList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(modCAList_Item)
		.toHashCode();
	}


}