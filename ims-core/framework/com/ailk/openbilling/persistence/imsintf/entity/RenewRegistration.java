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
@XmlType(propOrder={"user_id","phone_id","change_account","change_promotion_flag","productList","account","contactList","balanceList"})
public class RenewRegistration implements IComplexEntity{


	@XmlElement(name="productList")
	private SProductOrderList productList;

	@XmlElement(name="account")
	private SAccount account;

	@XmlElement(name="contactList")
	private SContactList contactList;

	@XmlElement(name="balanceList")
	private SBalanceList balanceList;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="change_account")
	private Short change_account;

	@XmlElement(name="change_promotion_flag")
	private Short change_promotion_flag;

	public void setProductList(SProductOrderList obj){
		this.productList = obj;
	}

	public SProductOrderList getProductList(){
		return productList;
	}

	public void setAccount(SAccount obj){
		this.account = obj;
	}

	public SAccount getAccount(){
		return account;
	}

	public void setContactList(SContactList obj){
		this.contactList = obj;
	}

	public SContactList getContactList(){
		return contactList;
	}

	public void setBalanceList(SBalanceList obj){
		this.balanceList = obj;
	}

	public SBalanceList getBalanceList(){
		return balanceList;
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

	public void setChange_account(Short obj){
		this.change_account = obj;
	}

	public Short getChange_account(){
		return change_account;
	}

	public void setChange_promotion_flag(Short obj){
		this.change_promotion_flag = obj;
	}

	public Short getChange_promotion_flag(){
		return change_promotion_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RenewRegistration rhs=(RenewRegistration)rhs0;
		if(!ObjectUtils.equals(productList, rhs.productList)) return false;
		if(!ObjectUtils.equals(account, rhs.account)) return false;
		if(!ObjectUtils.equals(contactList, rhs.contactList)) return false;
		if(!ObjectUtils.equals(balanceList, rhs.balanceList)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(change_account, rhs.change_account)) return false;
		if(!ObjectUtils.equals(change_promotion_flag, rhs.change_promotion_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(productList)
		.append(account)
		.append(contactList)
		.append(balanceList)
		.append(user_id)
		.append(phone_id)
		.append(change_account)
		.append(change_promotion_flag)
		.toHashCode();
	}


}