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
@XmlType(propOrder={"adjustLogList_Item"})
public class AdjustLogList implements IComplexEntity{


	@XmlElement(name="adjustLogList_Item")
	private AdjustLog[] adjustLogList_Item;

	public void setAdjustLogList_Item(AdjustLog[] obj){
		this.adjustLogList_Item = obj;
	}

	public AdjustLog[] getAdjustLogList_Item(){
		return adjustLogList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AdjustLogList rhs=(AdjustLogList)rhs0;
		if(!ObjectUtils.equals(adjustLogList_Item, rhs.adjustLogList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(adjustLogList_Item)
		.toHashCode();
	}


}