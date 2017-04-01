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
@XmlType(propOrder={"phone_id","outer_acct_id","acct_id","action_type","notifyOperList","user_id"})
public class SSetNotificationFlagReq implements IComplexEntity{


	@XmlElement(name="notifyOperList")
	private NotifyOperList[] notifyOperList;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="action_type")
	private Short action_type;

	@XmlElement(name="user_id")
	private Long user_id;

	public void setNotifyOperList(NotifyOperList[] obj){
		this.notifyOperList = obj;
	}

	public NotifyOperList[] getNotifyOperList(){
		return notifyOperList;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setAction_type(Short obj){
		this.action_type = obj;
	}

	public Short getAction_type(){
		return action_type;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SSetNotificationFlagReq rhs=(SSetNotificationFlagReq)rhs0;
		if(!ObjectUtils.equals(notifyOperList, rhs.notifyOperList)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(action_type, rhs.action_type)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notifyOperList)
		.append(phone_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(action_type)
		.append(user_id)
		.toHashCode();
	}


}