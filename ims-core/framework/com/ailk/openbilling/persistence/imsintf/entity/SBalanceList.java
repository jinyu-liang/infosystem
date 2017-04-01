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
@XmlType(propOrder={"sBalanceList_Item"})
public class SBalanceList implements IComplexEntity{


	@XmlElement(name="sBalanceList_Item")
	private SBalance[] sBalanceList_Item;

	public void setSBalanceList_Item(SBalance[] obj){
		this.sBalanceList_Item = obj;
	}

	public SBalance[] getSBalanceList_Item(){
		return sBalanceList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBalanceList rhs=(SBalanceList)rhs0;
		if(!ObjectUtils.equals(sBalanceList_Item, rhs.sBalanceList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBalanceList_Item)
		.toHashCode();
	}


}