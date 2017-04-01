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
@XmlType(propOrder={"notifyOperList_item"})
public class NotifyOperList implements IComplexEntity{


	@XmlElement(name="notifyOperList_item")
	private NotifyOper[] notifyOperList_item;

	public void setNotifyOperList_item(NotifyOper[] obj){
		this.notifyOperList_item = obj;
	}

	public NotifyOper[] getNotifyOperList_item(){
		return notifyOperList_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NotifyOperList rhs=(NotifyOperList)rhs0;
		if(!ObjectUtils.equals(notifyOperList_item, rhs.notifyOperList_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyOperList_item)
		.toHashCode();
	}


}