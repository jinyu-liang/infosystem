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
@XmlType(propOrder={"notify_type","notify_channel","service_id","notification_id","notification_name","notification_desc"})
public class Notify implements IComplexEntity{


	@XmlElement(name="notify_type")
	private Short notify_type;

	@XmlElement(name="notify_channel")
	private Short notify_channel;

	@XmlElement(name="service_id")
	private Integer service_id;

	@XmlElement(name="notification_id")
	private Long notification_id;

	@XmlElement(name="notification_name")
	private String notification_name;

	@XmlElement(name="notification_desc")
	private String notification_desc;

	public void setNotify_type(Short obj){
		this.notify_type = obj;
	}

	public Short getNotify_type(){
		return notify_type;
	}

	public void setNotify_channel(Short obj){
		this.notify_channel = obj;
	}

	public Short getNotify_channel(){
		return notify_channel;
	}

	public void setService_id(Integer obj){
		this.service_id = obj;
	}

	public Integer getService_id(){
		return service_id;
	}

	public void setNotification_id(Long obj){
		this.notification_id = obj;
	}

	public Long getNotification_id(){
		return notification_id;
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
		Notify rhs=(Notify)rhs0;
		if(!ObjectUtils.equals(notify_type, rhs.notify_type)) return false;
		if(!ObjectUtils.equals(notify_channel, rhs.notify_channel)) return false;
		if(!ObjectUtils.equals(service_id, rhs.service_id)) return false;
		if(!ObjectUtils.equals(notification_id, rhs.notification_id)) return false;
		if(!ObjectUtils.equals(notification_name, rhs.notification_name)) return false;
		if(!ObjectUtils.equals(notification_desc, rhs.notification_desc)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notify_type)
		.append(notify_channel)
		.append(service_id)
		.append(notification_id)
		.append(notification_name)
		.append(notification_desc)
		.toHashCode();
	}


}