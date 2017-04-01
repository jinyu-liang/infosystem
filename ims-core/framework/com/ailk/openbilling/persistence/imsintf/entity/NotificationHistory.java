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
@XmlType(propOrder={"phone_id","notification_type","channel","related_phone_id","notification_id","template_id","module","service","sender","email","email_address","notify_lanauge","notify_content","notify_time","create_date","notify_lang","notify_channel","notification_name","notification_desc"})
public class NotificationHistory implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="notification_type")
	private short notification_type;

	@XmlElement(name="channel")
	private Short channel;

	@XmlElement(name="related_phone_id")
	private String related_phone_id;

	@XmlElement(name="notification_id")
	private Long notification_id;

	@XmlElement(name="template_id")
	private Long template_id;

	@XmlElement(name="module")
	private String module;

	@XmlElement(name="service")
	private String service;

	@XmlElement(name="sender")
	private String sender;

	@XmlElement(name="email")
	private String email;

	@XmlElement(name="email_address")
	private String email_address;

	@XmlElement(name="notify_lanauge")
	private Short notify_lanauge;

	@XmlElement(name="notify_content")
	private String notify_content;

	@XmlElement(name="notify_time")
	private String notify_time;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="notify_lang")
	private Short notify_lang;

	@XmlElement(name="notify_channel")
	private Short notify_channel;

	@XmlElement(name="notification_name")
	private String notification_name;

	@XmlElement(name="notification_desc")
	private String notification_desc;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setNotification_type(short obj){
		this.notification_type = obj;
	}

	public short getNotification_type(){
		return notification_type;
	}

	public void setChannel(Short obj){
		this.channel = obj;
	}

	public Short getChannel(){
		return channel;
	}

	public void setRelated_phone_id(String obj){
		this.related_phone_id = obj;
	}

	public String getRelated_phone_id(){
		return related_phone_id;
	}

	public void setNotification_id(Long obj){
		this.notification_id = obj;
	}

	public Long getNotification_id(){
		return notification_id;
	}

	public void setTemplate_id(Long obj){
		this.template_id = obj;
	}

	public Long getTemplate_id(){
		return template_id;
	}

	public void setModule(String obj){
		this.module = obj;
	}

	public String getModule(){
		return module;
	}

	public void setService(String obj){
		this.service = obj;
	}

	public String getService(){
		return service;
	}

	public void setSender(String obj){
		this.sender = obj;
	}

	public String getSender(){
		return sender;
	}

	public void setEmail(String obj){
		this.email = obj;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail_address(String obj){
		this.email_address = obj;
	}

	public String getEmail_address(){
		return email_address;
	}

	public void setNotify_lanauge(Short obj){
		this.notify_lanauge = obj;
	}

	public Short getNotify_lanauge(){
		return notify_lanauge;
	}

	public void setNotify_content(String obj){
		this.notify_content = obj;
	}

	public String getNotify_content(){
		return notify_content;
	}

	public void setNotify_time(String obj){
		this.notify_time = obj;
	}

	public String getNotify_time(){
		return notify_time;
	}

	public void setCreate_date(String obj){
		this.create_date = obj;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setNotify_lang(Short obj){
		this.notify_lang = obj;
	}

	public Short getNotify_lang(){
		return notify_lang;
	}

	public void setNotify_channel(Short obj){
		this.notify_channel = obj;
	}

	public Short getNotify_channel(){
		return notify_channel;
	}

	public void setNotification_name(String obj){
		this.notification_name = obj;
	}

	public String getNotification_name(){
		return notification_name;
	}

	public void setNotification_desc(String obj){
		this.notification_desc = obj;
	}

	public String getNotification_desc(){
		return notification_desc;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NotificationHistory rhs=(NotificationHistory)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(notification_type, rhs.notification_type)) return false;
		if(!ObjectUtils.equals(channel, rhs.channel)) return false;
		if(!ObjectUtils.equals(related_phone_id, rhs.related_phone_id)) return false;
		if(!ObjectUtils.equals(notification_id, rhs.notification_id)) return false;
		if(!ObjectUtils.equals(template_id, rhs.template_id)) return false;
		if(!ObjectUtils.equals(module, rhs.module)) return false;
		if(!ObjectUtils.equals(service, rhs.service)) return false;
		if(!ObjectUtils.equals(sender, rhs.sender)) return false;
		if(!ObjectUtils.equals(email, rhs.email)) return false;
		if(!ObjectUtils.equals(email_address, rhs.email_address)) return false;
		if(!ObjectUtils.equals(notify_lanauge, rhs.notify_lanauge)) return false;
		if(!ObjectUtils.equals(notify_content, rhs.notify_content)) return false;
		if(!ObjectUtils.equals(notify_time, rhs.notify_time)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(notify_lang, rhs.notify_lang)) return false;
		if(!ObjectUtils.equals(notify_channel, rhs.notify_channel)) return false;
		if(!ObjectUtils.equals(notification_name, rhs.notification_name)) return false;
		if(!ObjectUtils.equals(notification_desc, rhs.notification_desc)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(notification_type)
		.append(channel)
		.append(related_phone_id)
		.append(notification_id)
		.append(template_id)
		.append(module)
		.append(service)
		.append(sender)
		.append(email)
		.append(email_address)
		.append(notify_lanauge)
		.append(notify_content)
		.append(notify_time)
		.append(create_date)
		.append(notify_lang)
		.append(notify_channel)
		.append(notification_name)
		.append(notification_desc)
		.toHashCode();
	}


}