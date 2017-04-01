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
@XmlType(propOrder={"oper_type","outer_pay_acct_id","pay_acct_id","pay_user_id","pay_phone_id","notification_flag","autoTopUpRelList"})
public class SAutoTopUpReq implements IComplexEntity{


	@XmlElement(name="autoTopUpRelList")
	private AutoTopUpRelationList autoTopUpRelList;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="outer_pay_acct_id")
	private String outer_pay_acct_id;

	@XmlElement(name="pay_acct_id")
	private Long pay_acct_id;

	@XmlElement(name="pay_user_id")
	private Long pay_user_id;

	@XmlElement(name="pay_phone_id")
	private String pay_phone_id;

	@XmlElement(name="notification_flag")
	private Short notification_flag;

	public void setAutoTopUpRelList(AutoTopUpRelationList obj){
		this.autoTopUpRelList = obj;
	}

	public AutoTopUpRelationList getAutoTopUpRelList(){
		return autoTopUpRelList;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setOuter_pay_acct_id(String obj){
		this.outer_pay_acct_id = obj;
	}

	public String getOuter_pay_acct_id(){
		return outer_pay_acct_id;
	}

	public void setPay_acct_id(Long obj){
		this.pay_acct_id = obj;
	}

	public Long getPay_acct_id(){
		return pay_acct_id;
	}

	public void setPay_user_id(Long obj){
		this.pay_user_id = obj;
	}

	public Long getPay_user_id(){
		return pay_user_id;
	}

	public void setPay_phone_id(String obj){
		this.pay_phone_id = obj;
	}

	public String getPay_phone_id(){
		return pay_phone_id;
	}

	public void setNotification_flag(Short obj){
		this.notification_flag = obj;
	}

	public Short getNotification_flag(){
		return notification_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SAutoTopUpReq rhs=(SAutoTopUpReq)rhs0;
		if(!ObjectUtils.equals(autoTopUpRelList, rhs.autoTopUpRelList)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(outer_pay_acct_id, rhs.outer_pay_acct_id)) return false;
		if(!ObjectUtils.equals(pay_acct_id, rhs.pay_acct_id)) return false;
		if(!ObjectUtils.equals(pay_user_id, rhs.pay_user_id)) return false;
		if(!ObjectUtils.equals(pay_phone_id, rhs.pay_phone_id)) return false;
		if(!ObjectUtils.equals(notification_flag, rhs.notification_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(autoTopUpRelList)
		.append(oper_type)
		.append(outer_pay_acct_id)
		.append(pay_acct_id)
		.append(pay_user_id)
		.append(pay_phone_id)
		.append(notification_flag)
		.toHashCode();
	}


}