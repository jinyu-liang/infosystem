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
@XmlType(propOrder={"sUserAuthList_Item"})
public class SUserAuthList implements IComplexEntity{


	@XmlElement(name="sUserAuthList_Item")
	private SUserAuth[] sUserAuthList_Item;

	public void setSUserAuthList_Item(SUserAuth[] obj){
		this.sUserAuthList_Item = obj;
	}

	public SUserAuth[] getSUserAuthList_Item(){
		return sUserAuthList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserAuthList rhs=(SUserAuthList)rhs0;
		if(!ObjectUtils.equals(sUserAuthList_Item, rhs.sUserAuthList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sUserAuthList_Item)
		.toHashCode();
	}


}