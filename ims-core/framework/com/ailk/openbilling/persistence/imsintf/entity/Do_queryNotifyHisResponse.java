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
@XmlType(propOrder={"notify_his_list"})
public class Do_queryNotifyHisResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="notify_his_list")
	private NotificationHistoryList notify_his_list;

	public void setNotify_his_list(NotificationHistoryList obj){
		this.notify_his_list = obj;
	}

	public NotificationHistoryList getNotify_his_list(){
		return notify_his_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryNotifyHisResponse rhs=(Do_queryNotifyHisResponse)rhs0;
		if(!ObjectUtils.equals(notify_his_list, rhs.notify_his_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(notify_his_list)
		.toHashCode();
	}


}