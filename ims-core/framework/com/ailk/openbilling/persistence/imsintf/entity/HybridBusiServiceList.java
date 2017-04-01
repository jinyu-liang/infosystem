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
@XmlType(propOrder={"hybridBusiService_Item"})
public class HybridBusiServiceList implements IComplexEntity{


	@XmlElement(name="hybridBusiService_Item")
	private HybridBusiService[] hybridBusiService_Item;

	public void setHybridBusiService_Item(HybridBusiService[] obj){
		this.hybridBusiService_Item = obj;
	}

	public HybridBusiService[] getHybridBusiService_Item(){
		return hybridBusiService_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		HybridBusiServiceList rhs=(HybridBusiServiceList)rhs0;
		if(!ObjectUtils.equals(hybridBusiService_Item, rhs.hybridBusiService_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(hybridBusiService_Item)
		.toHashCode();
	}


}