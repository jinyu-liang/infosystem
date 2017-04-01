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
@XmlType(propOrder={"user_id","phone_id","region_code","new_state","sub_type","reason_days","action_date","valid_date","expire_date","suspend_request_flag","fraud_flag","unbilling_flag"})
public class SUserStatus implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="region_code")
	private Integer region_code;

	@XmlElement(name="new_state")
	private Short new_state;

	@XmlElement(name="sub_type")
	private Short sub_type;

	@XmlElement(name="reason_days")
	private Short reason_days;

	@XmlElement(name="action_date")
	private String action_date;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="suspend_request_flag")
	private Short suspend_request_flag;

	@XmlElement(name="fraud_flag")
	private Short fraud_flag;

	@XmlElement(name="unbilling_flag")
	private Short unbilling_flag;

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

	public void setRegion_code(Integer obj){
		this.region_code = obj;
	}

	public Integer getRegion_code(){
		return region_code;
	}

	public void setNew_state(Short obj){
		this.new_state = obj;
	}

	public Short getNew_state(){
		return new_state;
	}

	public void setSub_type(Short obj){
		this.sub_type = obj;
	}

	public Short getSub_type(){
		return sub_type;
	}

	public void setReason_days(Short obj){
		this.reason_days = obj;
	}

	public Short getReason_days(){
		return reason_days;
	}

	public void setAction_date(String obj){
		this.action_date = obj;
	}

	public String getAction_date(){
		return action_date;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setSuspend_request_flag(Short obj){
		this.suspend_request_flag = obj;
	}

	public Short getSuspend_request_flag(){
		return suspend_request_flag;
	}

	public void setFraud_flag(Short obj){
		this.fraud_flag = obj;
	}

	public Short getFraud_flag(){
		return fraud_flag;
	}

	public void setUnbilling_flag(Short obj){
		this.unbilling_flag = obj;
	}

	public Short getUnbilling_flag(){
		return unbilling_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserStatus rhs=(SUserStatus)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(new_state, rhs.new_state)) return false;
		if(!ObjectUtils.equals(sub_type, rhs.sub_type)) return false;
		if(!ObjectUtils.equals(reason_days, rhs.reason_days)) return false;
		if(!ObjectUtils.equals(action_date, rhs.action_date)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(suspend_request_flag, rhs.suspend_request_flag)) return false;
		if(!ObjectUtils.equals(fraud_flag, rhs.fraud_flag)) return false;
		if(!ObjectUtils.equals(unbilling_flag, rhs.unbilling_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(phone_id)
		.append(region_code)
		.append(new_state)
		.append(sub_type)
		.append(reason_days)
		.append(action_date)
		.append(valid_date)
		.append(expire_date)
		.append(suspend_request_flag)
		.append(fraud_flag)
		.append(unbilling_flag)
		.toHashCode();
	}


}