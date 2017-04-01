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
@XmlType(propOrder={"fNOperList_Item"})
public class FNOperList implements IComplexEntity{


	@XmlElement(name="fNOperList_Item")
	private FNOper[] fNOperList_Item;

	public void setFNOperList_Item(FNOper[] obj){
		this.fNOperList_Item = obj;
	}

	public FNOper[] getFNOperList_Item(){
		return fNOperList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		FNOperList rhs=(FNOperList)rhs0;
		if(!ObjectUtils.equals(fNOperList_Item, rhs.fNOperList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(fNOperList_Item)
		.toHashCode();
	}


}