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
@XmlType(propOrder={"sUserList_Item"})
public class SUserList implements IComplexEntity{


	@XmlElement(name="sUserList_Item")
	private SUser[] sUserList_Item;

	public void setSUserList_Item(SUser[] obj){
		this.sUserList_Item = obj;
	}

	public SUser[] getSUserList_Item(){
		return sUserList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserList rhs=(SUserList)rhs0;
		if(!ObjectUtils.equals(sUserList_Item, rhs.sUserList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUserList_Item)
		.toHashCode();
	}


}