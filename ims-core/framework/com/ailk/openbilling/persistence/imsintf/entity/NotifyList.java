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
@XmlType(propOrder={"notifyList_item"})
public class NotifyList implements IComplexEntity{


	@XmlElement(name="notifyList_item")
	private Notify[] notifyList_item;

	public void setNotifyList_item(Notify[] obj){
		this.notifyList_item = obj;
	}

	public Notify[] getNotifyList_item(){
		return notifyList_item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NotifyList rhs=(NotifyList)rhs0;
		if(!ObjectUtils.equals(notifyList_item, rhs.notifyList_item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyList_item)
		.toHashCode();
	}


}