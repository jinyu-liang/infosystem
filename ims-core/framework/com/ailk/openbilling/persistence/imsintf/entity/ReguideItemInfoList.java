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
@XmlType(propOrder={"reguideItemInfoList_Item"})
public class ReguideItemInfoList implements IComplexEntity{


	@XmlElement(name="reguideItemInfoList_Item")
	private ReguideItemInfo[] reguideItemInfoList_Item;

	public void setReguideItemInfoList_Item(ReguideItemInfo[] obj){
		this.reguideItemInfoList_Item = obj;
	}

	public ReguideItemInfo[] getReguideItemInfoList_Item(){
		return reguideItemInfoList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideItemInfoList rhs=(ReguideItemInfoList)rhs0;
		if(!ObjectUtils.equals(reguideItemInfoList_Item, rhs.reguideItemInfoList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideItemInfoList_Item)
		.toHashCode();
	}


}