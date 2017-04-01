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
@XmlType(propOrder={"outer_cust_id","cust_id","outer_acct_id","acct_id","user_id","phone_id","change_type","new_phone_id","old_imsi","new_imsi","notification_flag","new_serial_no","old_serial_no"})
public class ChangeMsisdnAndSimInfo implements IComplexEntity{


	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="change_type")
	private Short change_type;

	@XmlElement(name="new_phone_id")
	private String new_phone_id;

	@XmlElement(name="old_imsi")
	private String old_imsi;

	@XmlElement(name="new_imsi")
	private String new_imsi;

	@XmlElement(name="notification_flag")
	private Short notification_flag;

	@XmlElement(name="new_serial_no")
	private String new_serial_no;

	@XmlElement(name="old_serial_no")
	private String old_serial_no;

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
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

	public void setChange_type(Short obj){
		this.change_type = obj;
	}

	public Short getChange_type(){
		return change_type;
	}

	public void setNew_phone_id(String obj){
		this.new_phone_id = obj;
	}

	public String getNew_phone_id(){
		return new_phone_id;
	}

	public void setOld_imsi(String obj){
		this.old_imsi = obj;
	}

	public String getOld_imsi(){
		return old_imsi;
	}

	public void setNew_imsi(String obj){
		this.new_imsi = obj;
	}

	public String getNew_imsi(){
		return new_imsi;
	}

	public void setNotification_flag(Short obj){
		this.notification_flag = obj;
	}

	public Short getNotification_flag(){
		return notification_flag;
	}

	public void setNew_serial_no(String obj){
		this.new_serial_no = obj;
	}

	public String getNew_serial_no(){
		return new_serial_no;
	}

	public void setOld_serial_no(String obj){
		this.old_serial_no = obj;
	}

	public String getOld_serial_no(){
		return old_serial_no;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ChangeMsisdnAndSimInfo rhs=(ChangeMsisdnAndSimInfo)rhs0;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(change_type, rhs.change_type)) return false;
		if(!ObjectUtils.equals(new_phone_id, rhs.new_phone_id)) return false;
		if(!ObjectUtils.equals(old_imsi, rhs.old_imsi)) return false;
		if(!ObjectUtils.equals(new_imsi, rhs.new_imsi)) return false;
		if(!ObjectUtils.equals(notification_flag, rhs.notification_flag)) return false;
		if(!ObjectUtils.equals(new_serial_no, rhs.new_serial_no)) return false;
		if(!ObjectUtils.equals(old_serial_no, rhs.old_serial_no)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(change_type)
		.append(new_phone_id)
		.append(old_imsi)
		.append(new_imsi)
		.append(notification_flag)
		.append(new_serial_no)
		.append(old_serial_no)
		.toHashCode();
	}


}