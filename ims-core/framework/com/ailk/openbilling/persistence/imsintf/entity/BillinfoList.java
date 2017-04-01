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
@XmlType(propOrder={"billinfoList_Item"})
public class BillinfoList implements IComplexEntity{


	@XmlElement(name="billinfoList_Item")
	private Billinfo[] billinfoList_Item;

	public void setBillinfoList_Item(Billinfo[] obj){
		this.billinfoList_Item = obj;
	}

	public Billinfo[] getBillinfoList_Item(){
		return billinfoList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillinfoList rhs=(BillinfoList)rhs0;
		if(!ObjectUtils.equals(billinfoList_Item, rhs.billinfoList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billinfoList_Item)
		.toHashCode();
	}


}