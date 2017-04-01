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
@XmlType(propOrder={"subGroup_Item"})
public class SubGroupList implements IComplexEntity{


	@XmlElement(name="subGroup_Item")
	private SubGroup[] subGroup_Item;

	public void setSubGroup_Item(SubGroup[] obj){
		this.subGroup_Item = obj;
	}

	public SubGroup[] getSubGroup_Item(){
		return subGroup_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SubGroupList rhs=(SubGroupList)rhs0;
		if(!ObjectUtils.equals(subGroup_Item, rhs.subGroup_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(subGroup_Item)
		.toHashCode();
	}


}