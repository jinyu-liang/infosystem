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
@XmlType(propOrder={"phoneItem"})
public class PhoneItemList implements IComplexEntity{


	@XmlElement(name="phoneItem")
	private PhoneItem[] phoneItem;

	public void setPhoneItem(PhoneItem[] obj){
		this.phoneItem = obj;
	}

	public PhoneItem[] getPhoneItem(){
		return phoneItem;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PhoneItemList rhs=(PhoneItemList)rhs0;
		if(!ObjectUtils.equals(phoneItem, rhs.phoneItem)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneItem)
		.toHashCode();
	}


}