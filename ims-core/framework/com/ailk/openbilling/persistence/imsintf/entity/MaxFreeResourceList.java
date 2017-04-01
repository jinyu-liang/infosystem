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
@XmlType(propOrder={"maxFreeResourceList_Item"})
public class MaxFreeResourceList implements IComplexEntity{


	@XmlElement(name="maxFreeResourceList_Item")
	private MaxFreeResource[] maxFreeResourceList_Item;

	public void setMaxFreeResourceList_Item(MaxFreeResource[] obj){
		this.maxFreeResourceList_Item = obj;
	}

	public MaxFreeResource[] getMaxFreeResourceList_Item(){
		return maxFreeResourceList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MaxFreeResourceList rhs=(MaxFreeResourceList)rhs0;
		if(!ObjectUtils.equals(maxFreeResourceList_Item, rhs.maxFreeResourceList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(maxFreeResourceList_Item)
		.toHashCode();
	}


}