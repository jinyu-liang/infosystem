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
@XmlType(propOrder={"billItemList_Item"})
public class BillItemList implements IComplexEntity{


	@XmlElement(name="billItemList_Item")
	private BillItem[] billItemList_Item;

	public void setBillItemList_Item(BillItem[] obj){
		this.billItemList_Item = obj;
	}

	public BillItem[] getBillItemList_Item(){
		return billItemList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillItemList rhs=(BillItemList)rhs0;
		if(!ObjectUtils.equals(billItemList_Item, rhs.billItemList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billItemList_Item)
		.toHashCode();
	}


}