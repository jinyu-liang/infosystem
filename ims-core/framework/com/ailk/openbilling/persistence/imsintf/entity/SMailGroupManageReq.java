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
@XmlType(propOrder={"mailGroup","mailGroupMemberList","mailGroupPost"})
public class SMailGroupManageReq implements IComplexEntity{


	@XmlElement(name="mailGroup")
	private MailGroup mailGroup;

	@XmlElement(name="mailGroupMemberList")
	private MailGroupMemberList mailGroupMemberList;

	@XmlElement(name="mailGroupPost")
	private MailGroupPost mailGroupPost;

	public void setMailGroup(MailGroup obj){
		this.mailGroup = obj;
	}

	public MailGroup getMailGroup(){
		return mailGroup;
	}

	public void setMailGroupMemberList(MailGroupMemberList obj){
		this.mailGroupMemberList = obj;
	}

	public MailGroupMemberList getMailGroupMemberList(){
		return mailGroupMemberList;
	}

	public void setMailGroupPost(MailGroupPost obj){
		this.mailGroupPost = obj;
	}

	public MailGroupPost getMailGroupPost(){
		return mailGroupPost;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMailGroupManageReq rhs=(SMailGroupManageReq)rhs0;
		if(!ObjectUtils.equals(mailGroup, rhs.mailGroup)) return false;
		if(!ObjectUtils.equals(mailGroupMemberList, rhs.mailGroupMemberList)) return false;
		if(!ObjectUtils.equals(mailGroupPost, rhs.mailGroupPost)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(mailGroup)
		.append(mailGroupMemberList)
		.append(mailGroupPost)
		.toHashCode();
	}


}