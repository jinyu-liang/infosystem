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
@XmlType(propOrder={"sNegativeBalance_Item"})
public class SNegativeBalanceList implements IComplexEntity{


	@XmlElement(name="sNegativeBalance_Item")
	private SNegativeBalance[] sNegativeBalance_Item;

	public void setSNegativeBalance_Item(SNegativeBalance[] obj){
		this.sNegativeBalance_Item = obj;
	}

	public SNegativeBalance[] getSNegativeBalance_Item(){
		return sNegativeBalance_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SNegativeBalanceList rhs=(SNegativeBalanceList)rhs0;
		if(!ObjectUtils.equals(sNegativeBalance_Item, rhs.sNegativeBalance_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sNegativeBalance_Item)
		.toHashCode();
	}


}