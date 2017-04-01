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
@XmlType(propOrder={"autoTopUpRelationList_Item"})
public class AutoTopUpRelationList implements IComplexEntity{


	@XmlElement(name="autoTopUpRelationList_Item")
	private AutoTopUpRelation[] autoTopUpRelationList_Item;

	public void setAutoTopUpRelationList_Item(AutoTopUpRelation[] obj){
		this.autoTopUpRelationList_Item = obj;
	}

	public AutoTopUpRelation[] getAutoTopUpRelationList_Item(){
		return autoTopUpRelationList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AutoTopUpRelationList rhs=(AutoTopUpRelationList)rhs0;
		if(!ObjectUtils.equals(autoTopUpRelationList_Item, rhs.autoTopUpRelationList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(autoTopUpRelationList_Item)
		.toHashCode();
	}


}