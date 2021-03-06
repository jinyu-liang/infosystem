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
@XmlType(propOrder={"longList_Item"})
public class LongList implements IComplexEntity{


	@XmlElement(name="longList_Item")
	private Long[] longList_Item;

	public void setLongList_Item(Long[] obj){
		this.longList_Item = obj;
	}

	public Long[] getLongList_Item(){
		return longList_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		LongList rhs=(LongList)rhs0;
		if(!ObjectUtils.equals(longList_Item, rhs.longList_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(longList_Item)
		.toHashCode();
	}


}