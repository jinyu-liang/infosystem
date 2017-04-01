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
@XmlType(propOrder={"splitRelList_Item"})
public class SUserPaySplitRelList implements IComplexEntity{


	@XmlElement(name="splitRelList_Item")
	private SUserPaySplitRel[] splitRelList_Item;

	public void setSplitRelList_Item(SUserPaySplitRel[] obj){
		this.splitRelList_Item = obj;
	}

	public SUserPaySplitRel[] getSplitRelList_Item(){
		return splitRelList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserPaySplitRelList rhs=(SUserPaySplitRelList)rhs0;
		if(!ObjectUtils.equals(splitRelList_Item, rhs.splitRelList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(splitRelList_Item)
		.toHashCode();
	}


}