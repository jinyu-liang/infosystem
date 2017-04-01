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
@XmlType(propOrder={"reguideInfoList_Item"})
public class ReguideInfoList implements IComplexEntity{


	@XmlElement(name="reguideInfoList_Item")
	private ReguideInfo[] reguideInfoList_Item;

	public void setReguideInfoList_Item(ReguideInfo[] obj){
		this.reguideInfoList_Item = obj;
	}

	public ReguideInfo[] getReguideInfoList_Item(){
		return reguideInfoList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideInfoList rhs=(ReguideInfoList)rhs0;
		if(!ObjectUtils.equals(reguideInfoList_Item, rhs.reguideInfoList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideInfoList_Item)
		.toHashCode();
	}


}