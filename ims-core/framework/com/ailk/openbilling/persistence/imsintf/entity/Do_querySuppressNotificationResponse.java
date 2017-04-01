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
@XmlType(propOrder={"acctNotifyList","phoneNotifyList"})
public class Do_querySuppressNotificationResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="acctNotifyList")
	private AcctNotify[] acctNotifyList;

	@XmlElement(name="phoneNotifyList")
	private PhoneNotify[] phoneNotifyList;

	public void setAcctNotifyList(AcctNotify[] obj){
		this.acctNotifyList = obj;
	}

	public AcctNotify[] getAcctNotifyList(){
		return acctNotifyList;
	}

	public void setPhoneNotifyList(PhoneNotify[] obj){
		this.phoneNotifyList = obj;
	}

	public PhoneNotify[] getPhoneNotifyList(){
		return phoneNotifyList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_querySuppressNotificationResponse rhs=(Do_querySuppressNotificationResponse)rhs0;
		if(!ObjectUtils.equals(acctNotifyList, rhs.acctNotifyList)) return false;
		if(!ObjectUtils.equals(phoneNotifyList, rhs.phoneNotifyList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctNotifyList)
		.append(phoneNotifyList)
		.toHashCode();
	}


}