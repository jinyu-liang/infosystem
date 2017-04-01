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
@XmlType(propOrder={"outer_acct_id","acct_id","user_id","phone_id","start_date","end_date","related_phone_id","email","notification_type","notification_id"})
public class SQueryNotificationHisReq implements IComplexEntity{


	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="start_date")
	private String start_date;

	@XmlElement(name="end_date")
	private String end_date;

	@XmlElement(name="related_phone_id")
	private String related_phone_id;

	@XmlElement(name="email")
	private String email;

	@XmlElement(name="notification_type")
	private String notification_type;

	@XmlElement(name="notification_id")
	private Long notification_id;

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

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setStart_date(String obj){
		this.start_date = obj;
	}

	public String getStart_date(){
		return start_date;
	}

	public void setEnd_date(String obj){
		this.end_date = obj;
	}

	public String getEnd_date(){
		return end_date;
	}

	public void setRelated_phone_id(String obj){
		this.related_phone_id = obj;
	}

	public String getRelated_phone_id(){
		return related_phone_id;
	}

	public void setEmail(String obj){
		this.email = obj;
	}

	public String getEmail(){
		return email;
	}

	public void setNotification_type(String obj){
		this.notification_type = obj;
	}

	public String getNotification_type(){
		return notification_type;
	}

	public void setNotification_id(Long obj){
		this.notification_id = obj;
	}

	public Long getNotification_id(){
		return notification_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryNotificationHisReq rhs=(SQueryNotificationHisReq)rhs0;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(related_phone_id, rhs.related_phone_id)) return false;
		if(!ObjectUtils.equals(email, rhs.email)) return false;
		if(!ObjectUtils.equals(notification_type, rhs.notification_type)) return false;
		if(!ObjectUtils.equals(notification_id, rhs.notification_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(start_date)
		.append(end_date)
		.append(related_phone_id)
		.append(email)
		.append(notification_type)
		.append(notification_id)
		.toHashCode();
	}


}