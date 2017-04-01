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
@XmlType(propOrder={"reguideAcctInfoList_Item"})
public class ReguideAcctInfoList implements IComplexEntity{


	@XmlElement(name="reguideAcctInfoList_Item")
	private ReguideAcctInfo[] reguideAcctInfoList_Item;

	public void setReguideAcctInfoList_Item(ReguideAcctInfo[] obj){
		this.reguideAcctInfoList_Item = obj;
	}

	public ReguideAcctInfo[] getReguideAcctInfoList_Item(){
		return reguideAcctInfoList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideAcctInfoList rhs=(ReguideAcctInfoList)rhs0;
		if(!ObjectUtils.equals(reguideAcctInfoList_Item, rhs.reguideAcctInfoList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideAcctInfoList_Item)
		.toHashCode();
	}


}