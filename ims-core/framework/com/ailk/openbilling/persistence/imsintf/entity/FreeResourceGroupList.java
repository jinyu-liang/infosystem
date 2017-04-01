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
@XmlType(propOrder={"freeResourceGroup_Item"})
public class FreeResourceGroupList implements IComplexEntity{


	@XmlElement(name="freeResourceGroup_Item")
	private FreeResourceGroup[] freeResourceGroup_Item;

	public void setFreeResourceGroup_Item(FreeResourceGroup[] obj){
		this.freeResourceGroup_Item = obj;
	}

	public FreeResourceGroup[] getFreeResourceGroup_Item(){
		return freeResourceGroup_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FreeResourceGroupList rhs=(FreeResourceGroupList)rhs0;
		if(!ObjectUtils.equals(freeResourceGroup_Item, rhs.freeResourceGroup_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeResourceGroup_Item)
		.toHashCode();
	}


}