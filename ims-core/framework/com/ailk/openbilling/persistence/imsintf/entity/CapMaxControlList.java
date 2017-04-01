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
@XmlType(propOrder={"capMaxControlList_Item"})
public class CapMaxControlList implements IComplexEntity{


	@XmlElement(name="capMaxControlList_Item")
	private CapMaxControl[] capMaxControlList_Item;

	public void setCapMaxControlList_Item(CapMaxControl[] obj){
		this.capMaxControlList_Item = obj;
	}

	public CapMaxControl[] getCapMaxControlList_Item(){
		return capMaxControlList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CapMaxControlList rhs=(CapMaxControlList)rhs0;
		if(!ObjectUtils.equals(capMaxControlList_Item, rhs.capMaxControlList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(capMaxControlList_Item)
		.toHashCode();
	}


}