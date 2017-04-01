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
@XmlType(propOrder={"reguideDtlInfoList_Item"})
public class ReguideDtlInfoList implements IComplexEntity{


	@XmlElement(name="reguideDtlInfoList_Item")
	private ReguideDtlInfo[] reguideDtlInfoList_Item;

	public void setReguideDtlInfoList_Item(ReguideDtlInfo[] obj){
		this.reguideDtlInfoList_Item = obj;
	}

	public ReguideDtlInfo[] getReguideDtlInfoList_Item(){
		return reguideDtlInfoList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideDtlInfoList rhs=(ReguideDtlInfoList)rhs0;
		if(!ObjectUtils.equals(reguideDtlInfoList_Item, rhs.reguideDtlInfoList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideDtlInfoList_Item)
		.toHashCode();
	}


}