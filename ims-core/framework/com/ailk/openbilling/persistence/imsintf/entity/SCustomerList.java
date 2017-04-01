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
@XmlType(propOrder={"sCustomerList_Item"})
public class SCustomerList implements IComplexEntity{


	@XmlElement(name="sCustomerList_Item")
	private SCustomer[] sCustomerList_Item;

	public void setSCustomerList_Item(SCustomer[] obj){
		this.sCustomerList_Item = obj;
	}

	public SCustomer[] getSCustomerList_Item(){
		return sCustomerList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCustomerList rhs=(SCustomerList)rhs0;
		if(!ObjectUtils.equals(sCustomerList_Item, rhs.sCustomerList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sCustomerList_Item)
		.toHashCode();
	}


}