package com.ailk.openbilling.persistence.innerqry.entity;

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
@XmlType(propOrder={"user_id","phone_id","acct_id","is_accountInfo","is_custId"})
public class QryAcctInRouteReq implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="is_accountInfo")
	private Boolean is_accountInfo;

	@XmlElement(name="is_custId")
	private Boolean is_custId;

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

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setIs_accountInfo(Boolean obj){
		this.is_accountInfo = obj;
	}

	public Boolean getIs_accountInfo(){
		return is_accountInfo;
	}

	public void setIs_custId(Boolean obj){
		this.is_custId = obj;
	}

	public Boolean getIs_custId(){
		return is_custId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		QryAcctInRouteReq rhs=(QryAcctInRouteReq)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(is_accountInfo, rhs.is_accountInfo)) return false;
		if(!ObjectUtils.equals(is_custId, rhs.is_custId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(phone_id)
		.append(acct_id)
		.append(is_accountInfo)
		.append(is_custId)
		.toHashCode();
	}


}