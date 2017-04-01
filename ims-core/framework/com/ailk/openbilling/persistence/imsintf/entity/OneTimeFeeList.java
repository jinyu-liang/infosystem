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
@XmlType(propOrder={"oneTimeFeeList_Item"})
public class OneTimeFeeList implements IComplexEntity{


	@XmlElement(name="oneTimeFeeList_Item")
	private OneTimeFee[] oneTimeFeeList_Item;

	public void setOneTimeFeeList_Item(OneTimeFee[] obj){
		this.oneTimeFeeList_Item = obj;
	}

	public OneTimeFee[] getOneTimeFeeList_Item(){
		return oneTimeFeeList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		OneTimeFeeList rhs=(OneTimeFeeList)rhs0;
		if(!ObjectUtils.equals(oneTimeFeeList_Item, rhs.oneTimeFeeList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oneTimeFeeList_Item)
		.toHashCode();
	}


}