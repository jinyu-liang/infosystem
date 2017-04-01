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
@XmlType(propOrder={"notificationHistory"})
public class NotificationHistoryList implements IComplexEntity{


	@XmlElement(name="notificationHistory")
	private NotificationHistory[] notificationHistory;

	public void setNotificationHistory(NotificationHistory[] obj){
		this.notificationHistory = obj;
	}

	public NotificationHistory[] getNotificationHistory(){
		return notificationHistory;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NotificationHistoryList rhs=(NotificationHistoryList)rhs0;
		if(!ObjectUtils.equals(notificationHistory, rhs.notificationHistory)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notificationHistory)
		.toHashCode();
	}


}