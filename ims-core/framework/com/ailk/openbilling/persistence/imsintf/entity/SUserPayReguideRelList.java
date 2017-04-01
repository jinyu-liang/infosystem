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
@XmlType(propOrder={"payRelList_Item"})
public class SUserPayReguideRelList implements IComplexEntity{


	@XmlElement(name="payRelList_Item")
	private SUserPayReguideRel[] payRelList_Item;

	public void setPayRelList_Item(SUserPayReguideRel[] obj){
		this.payRelList_Item = obj;
	}

	public SUserPayReguideRel[] getPayRelList_Item(){
		return payRelList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserPayReguideRelList rhs=(SUserPayReguideRelList)rhs0;
		if(!ObjectUtils.equals(payRelList_Item, rhs.payRelList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(payRelList_Item)
		.toHashCode();
	}


}