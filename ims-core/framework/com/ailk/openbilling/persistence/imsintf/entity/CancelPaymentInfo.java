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
@XmlType(propOrder={"orign_so_nbr","cancel_date","remark","notification"})
public class CancelPaymentInfo implements IComplexEntity{


	@XmlElement(name="orign_so_nbr")
	private String orign_so_nbr;

	@XmlElement(name="cancel_date")
	private String cancel_date;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="notification")
	private Short notification;

	public void setOrign_so_nbr(String obj){
		this.orign_so_nbr = obj;
	}

	public String getOrign_so_nbr(){
		return orign_so_nbr;
	}

	public void setCancel_date(String obj){
		this.cancel_date = obj;
	}

	public String getCancel_date(){
		return cancel_date;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setNotification(Short obj){
		this.notification = obj;
	}

	public Short getNotification(){
		return notification;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CancelPaymentInfo rhs=(CancelPaymentInfo)rhs0;
		if(!ObjectUtils.equals(orign_so_nbr, rhs.orign_so_nbr)) return false;
		if(!ObjectUtils.equals(cancel_date, rhs.cancel_date)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(notification, rhs.notification)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(orign_so_nbr)
		.append(cancel_date)
		.append(remark)
		.append(notification)
		.toHashCode();
	}


}