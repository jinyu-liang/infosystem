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
@XmlType(propOrder={"billDetailList_Item"})
public class BillDetailList implements IComplexEntity{


	@XmlElement(name="billDetailList_Item")
	private BillDetail[] billDetailList_Item;

	public void setBillDetailList_Item(BillDetail[] obj){
		this.billDetailList_Item = obj;
	}

	public BillDetail[] getBillDetailList_Item(){
		return billDetailList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BillDetailList rhs=(BillDetailList)rhs0;
		if(!ObjectUtils.equals(billDetailList_Item, rhs.billDetailList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billDetailList_Item)
		.toHashCode();
	}


}