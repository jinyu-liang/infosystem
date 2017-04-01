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
@XmlType(propOrder={"sContactList_Item"})
public class SContactList implements IComplexEntity{


	@XmlElement(name="sContactList_Item")
	private SContact[] sContactList_Item;

	public void setSContactList_Item(SContact[] obj){
		this.sContactList_Item = obj;
	}

	public SContact[] getSContactList_Item(){
		return sContactList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SContactList rhs=(SContactList)rhs0;
		if(!ObjectUtils.equals(sContactList_Item, rhs.sContactList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sContactList_Item)
		.toHashCode();
	}


}