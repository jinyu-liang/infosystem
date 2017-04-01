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
@XmlType(propOrder={"freeResourceList_Item"})
public class FreeResourceList implements IComplexEntity{


	@XmlElement(name="freeResourceList_Item")
	private FreeResource[] freeResourceList_Item;

	public void setFreeResourceList_Item(FreeResource[] obj){
		this.freeResourceList_Item = obj;
	}

	public FreeResource[] getFreeResourceList_Item(){
		return freeResourceList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FreeResourceList rhs=(FreeResourceList)rhs0;
		if(!ObjectUtils.equals(freeResourceList_Item, rhs.freeResourceList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeResourceList_Item)
		.toHashCode();
	}


}