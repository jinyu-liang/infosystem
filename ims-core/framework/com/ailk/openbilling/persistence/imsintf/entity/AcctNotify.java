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
@XmlType(propOrder={"notifyList_item","accnt_id"})
public class AcctNotify implements IComplexEntity{


	@XmlElement(name="notifyList_item")
	private Notify[] notifyList_item;

	@XmlElement(name="accnt_id")
	private String accnt_id;

	public void setNotifyList_item(Notify[] obj){
		this.notifyList_item = obj;
	}

	public Notify[] getNotifyList_item(){
		return notifyList_item;
	}

	public void setAccnt_id(String obj){
		this.accnt_id = obj;
	}

	public String getAccnt_id(){
		return accnt_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AcctNotify rhs=(AcctNotify)rhs0;
		if(!ObjectUtils.equals(notifyList_item, rhs.notifyList_item)) return false;
		if(!ObjectUtils.equals(accnt_id, rhs.accnt_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyList_item)
		.append(accnt_id)
		.toHashCode();
	}


}