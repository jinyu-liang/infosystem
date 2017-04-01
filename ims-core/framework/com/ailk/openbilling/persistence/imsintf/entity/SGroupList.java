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
@XmlType(propOrder={"group_items"})
public class SGroupList implements IComplexEntity{


	@XmlElement(name="group_items")
	private SGroup[] group_items;

	public void setGroup_items(SGroup[] obj){
		this.group_items = obj;
	}

	public SGroup[] getGroup_items(){
		return group_items;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SGroupList rhs=(SGroupList)rhs0;
		if(!ObjectUtils.equals(group_items, rhs.group_items)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(group_items)
		.toHashCode();
	}


}