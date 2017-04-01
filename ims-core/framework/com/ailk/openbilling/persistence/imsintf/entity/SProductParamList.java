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
@XmlType(propOrder={"sProductParamList_Item"})
public class SProductParamList implements IComplexEntity{


	@XmlElement(name="sProductParamList_Item")
	private SProductParam[] sProductParamList_Item;

	public void setSProductParamList_Item(SProductParam[] obj){
		this.sProductParamList_Item = obj;
	}

	public SProductParam[] getSProductParamList_Item(){
		return sProductParamList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductParamList rhs=(SProductParamList)rhs0;
		if(!ObjectUtils.equals(sProductParamList_Item, rhs.sProductParamList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sProductParamList_Item)
		.toHashCode();
	}


}