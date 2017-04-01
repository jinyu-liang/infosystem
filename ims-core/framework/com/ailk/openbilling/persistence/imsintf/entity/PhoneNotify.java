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
@XmlType(propOrder={"notifyList_item","phone_id"})
public class PhoneNotify implements IComplexEntity{


	@XmlElement(name="notifyList_item")
	private Notify[] notifyList_item;

	@XmlElement(name="phone_id")
	private String phone_id;

	public void setNotifyList_item(Notify[] obj){
		this.notifyList_item = obj;
	}

	public Notify[] getNotifyList_item(){
		return notifyList_item;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PhoneNotify rhs=(PhoneNotify)rhs0;
		if(!ObjectUtils.equals(notifyList_item, rhs.notifyList_item)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyList_item)
		.append(phone_id)
		.toHashCode();
	}


}