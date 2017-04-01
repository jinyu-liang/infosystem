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
@XmlType(propOrder={"sMemberList_Item"})
public class SMemberList implements IComplexEntity{


	@XmlElement(name="sMemberList_Item")
	private SMember[] sMemberList_Item;

	public void setSMemberList_Item(SMember[] obj){
		this.sMemberList_Item = obj;
	}

	public SMember[] getSMemberList_Item(){
		return sMemberList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMemberList rhs=(SMemberList)rhs0;
		if(!ObjectUtils.equals(sMemberList_Item, rhs.sMemberList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sMemberList_Item)
		.toHashCode();
	}


}