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
@XmlType(propOrder={"actualUsageList_Item"})
public class ActualUsageList implements IComplexEntity{


	@XmlElement(name="actualUsageList_Item")
	private ActualUsage[] actualUsageList_Item;

	public void setActualUsageList_Item(ActualUsage[] obj){
		this.actualUsageList_Item = obj;
	}

	public ActualUsage[] getActualUsageList_Item(){
		return actualUsageList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ActualUsageList rhs=(ActualUsageList)rhs0;
		if(!ObjectUtils.equals(actualUsageList_Item, rhs.actualUsageList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(actualUsageList_Item)
		.toHashCode();
	}


}