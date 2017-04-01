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
@XmlType(propOrder={"sProductOrderOperList_Item"})
public class SProductOrderOperList implements IComplexEntity{


	@XmlElement(name="sProductOrderOperList_Item")
	private SProductOrderOper[] sProductOrderOperList_Item;

	public void setSProductOrderOperList_Item(SProductOrderOper[] obj){
		this.sProductOrderOperList_Item = obj;
	}

	public SProductOrderOper[] getSProductOrderOperList_Item(){
		return sProductOrderOperList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductOrderOperList rhs=(SProductOrderOperList)rhs0;
		if(!ObjectUtils.equals(sProductOrderOperList_Item, rhs.sProductOrderOperList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sProductOrderOperList_Item)
		.toHashCode();
	}


}