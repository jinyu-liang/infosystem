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
@XmlType(propOrder={"billUsageItemList_Item"})
public class BillUsageItemList implements IComplexEntity{


	@XmlElement(name="billUsageItemList_Item")
	private BillUsageItem[] billUsageItemList_Item;

	public void setBillUsageItemList_Item(BillUsageItem[] obj){
		this.billUsageItemList_Item = obj;
	}

	public BillUsageItem[] getBillUsageItemList_Item(){
		return billUsageItemList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillUsageItemList rhs=(BillUsageItemList)rhs0;
		if(!ObjectUtils.equals(billUsageItemList_Item, rhs.billUsageItemList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billUsageItemList_Item)
		.toHashCode();
	}


}