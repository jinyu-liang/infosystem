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
@XmlType(propOrder={"sAccountList_Item"})
public class SAccountList implements IComplexEntity{


	@XmlElement(name="sAccountList_Item")
	private SAccount[] sAccountList_Item;

	public void setSAccountList_Item(SAccount[] obj){
		this.sAccountList_Item = obj;
	}

	public SAccount[] getSAccountList_Item(){
		return sAccountList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAccountList rhs=(SAccountList)rhs0;
		if(!ObjectUtils.equals(sAccountList_Item, rhs.sAccountList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sAccountList_Item)
		.toHashCode();
	}


}