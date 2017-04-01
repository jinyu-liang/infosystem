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
@XmlType(propOrder={"sProductOrderResultList_Item"})
public class SProductOrderResultList implements IComplexEntity{


	@XmlElement(name="sProductOrderResultList_Item")
	private SProductOrderResult[] sProductOrderResultList_Item;

	public void setSProductOrderResultList_Item(SProductOrderResult[] obj){
		this.sProductOrderResultList_Item = obj;
	}

	public SProductOrderResult[] getSProductOrderResultList_Item(){
		return sProductOrderResultList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SProductOrderResultList rhs=(SProductOrderResultList)rhs0;
		if(!ObjectUtils.equals(sProductOrderResultList_Item, rhs.sProductOrderResultList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sProductOrderResultList_Item)
		.toHashCode();
	}


}