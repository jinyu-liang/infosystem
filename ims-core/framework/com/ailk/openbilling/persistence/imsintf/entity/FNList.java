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
@XmlType(propOrder={"fNList_Item"})
public class FNList implements IComplexEntity{


	@XmlElement(name="fNList_Item")
	private FN[] fNList_Item;

	public void setFNList_Item(FN[] obj){
		this.fNList_Item = obj;
	}

	public FN[] getFNList_Item(){
		return fNList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FNList rhs=(FNList)rhs0;
		if(!ObjectUtils.equals(fNList_Item, rhs.fNList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(fNList_Item)
		.toHashCode();
	}


}