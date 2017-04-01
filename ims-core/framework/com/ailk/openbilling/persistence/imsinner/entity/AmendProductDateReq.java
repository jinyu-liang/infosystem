package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"amendProductDate_Item","user_id","acct_id","phone_id"})
public class AmendProductDateReq implements IComplexEntity{


	@XmlElement(name="amendProductDate_Item")
	private AmendProductDate[] amendProductDate_Item;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	public void setAmendProductDate_Item(AmendProductDate[] obj){
		this.amendProductDate_Item = obj;
	}

	public AmendProductDate[] getAmendProductDate_Item(){
		return amendProductDate_Item;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AmendProductDateReq rhs=(AmendProductDateReq)rhs0;
		if(!ObjectUtils.equals(amendProductDate_Item, rhs.amendProductDate_Item)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(amendProductDate_Item)
		.append(user_id)
		.append(acct_id)
		.append(phone_id)
		.toHashCode();
	}


}