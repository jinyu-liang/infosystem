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
@XmlType(propOrder={"mailGroupMember_Item"})
public class MailGroupMemberList implements IComplexEntity{


	@XmlElement(name="mailGroupMember_Item")
	private MailGroupMember[] mailGroupMember_Item;

	public void setMailGroupMember_Item(MailGroupMember[] obj){
		this.mailGroupMember_Item = obj;
	}

	public MailGroupMember[] getMailGroupMember_Item(){
		return mailGroupMember_Item;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MailGroupMemberList rhs=(MailGroupMemberList)rhs0;
		if(!ObjectUtils.equals(mailGroupMember_Item, rhs.mailGroupMember_Item)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(mailGroupMember_Item)
		.toHashCode();
	}


}