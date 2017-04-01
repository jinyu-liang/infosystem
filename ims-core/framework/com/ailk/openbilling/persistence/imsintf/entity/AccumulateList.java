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
@XmlType(propOrder={"accumulateList_Item"})
public class AccumulateList implements IComplexEntity{


	@XmlElement(name="accumulateList_Item")
	private Accumulate[] accumulateList_Item;

	public void setAccumulateList_Item(Accumulate[] obj){
		this.accumulateList_Item = obj;
	}

	public Accumulate[] getAccumulateList_Item(){
		return accumulateList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AccumulateList rhs=(AccumulateList)rhs0;
		if(!ObjectUtils.equals(accumulateList_Item, rhs.accumulateList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accumulateList_Item)
		.toHashCode();
	}


}