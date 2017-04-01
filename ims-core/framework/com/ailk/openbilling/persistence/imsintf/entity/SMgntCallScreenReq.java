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
@XmlType(propOrder={"outer_acct_id","acct_id","user_id","phone_id","oper_type","call_screen_no_type","outer_group_id","group_id","no_list"})
public class SMgntCallScreenReq implements IComplexEntity{


	@XmlElement(name="no_list")
	private SCallScreenNoList no_list;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="call_screen_no_type")
	private Short call_screen_no_type;

	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	public void setNo_list(SCallScreenNoList obj){
		this.no_list = obj;
	}

	public SCallScreenNoList getNo_list(){
		return no_list;
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

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setCall_screen_no_type(Short obj){
		this.call_screen_no_type = obj;
	}

	public Short getCall_screen_no_type(){
		return call_screen_no_type;
	}

	public void setOuter_group_id(String obj){
		this.outer_group_id = obj;
	}

	public String getOuter_group_id(){
		return outer_group_id;
	}

	public void setGroup_id(Long obj){
		this.group_id = obj;
	}

	public Long getGroup_id(){
		return group_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMgntCallScreenReq rhs=(SMgntCallScreenReq)rhs0;
		if(!ObjectUtils.equals(no_list, rhs.no_list)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(call_screen_no_type, rhs.call_screen_no_type)) return false;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(no_list)
		.append(outer_acct_id)
		.append(acct_id)
		.append(user_id)
		.append(phone_id)
		.append(oper_type)
		.append(call_screen_no_type)
		.append(outer_group_id)
		.append(group_id)
		.toHashCode();
	}


}