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
@XmlType(propOrder={"recurringOnetimeFee_Item"})
public class RecurringOnetimeFeeList implements IComplexEntity{


	@XmlElement(name="recurringOnetimeFee_Item")
	private RecurringOnetimeFee[] recurringOnetimeFee_Item;

	public void setRecurringOnetimeFee_Item(RecurringOnetimeFee[] obj){
		this.recurringOnetimeFee_Item = obj;
	}

	public RecurringOnetimeFee[] getRecurringOnetimeFee_Item(){
		return recurringOnetimeFee_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RecurringOnetimeFeeList rhs=(RecurringOnetimeFeeList)rhs0;
		if(!ObjectUtils.equals(recurringOnetimeFee_Item, rhs.recurringOnetimeFee_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(recurringOnetimeFee_Item)
		.toHashCode();
	}


}