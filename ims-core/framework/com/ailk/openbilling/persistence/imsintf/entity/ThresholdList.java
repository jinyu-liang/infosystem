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
@XmlType(propOrder={"thresholdList_Item"})
public class ThresholdList implements IComplexEntity{


	@XmlElement(name="thresholdList_Item")
	private Threshold[] thresholdList_Item;

	public void setThresholdList_Item(Threshold[] obj){
		this.thresholdList_Item = obj;
	}

	public Threshold[] getThresholdList_Item(){
		return thresholdList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ThresholdList rhs=(ThresholdList)rhs0;
		if(!ObjectUtils.equals(thresholdList_Item, rhs.thresholdList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(thresholdList_Item)
		.toHashCode();
	}


}