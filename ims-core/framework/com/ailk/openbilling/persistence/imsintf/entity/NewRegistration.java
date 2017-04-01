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
@XmlType(propOrder={"customer","accountList","userList","productOrderList","contactList","userAuthList","balanceList"})
public class NewRegistration implements IComplexEntity{


	@XmlElement(name="customer")
	private SCustomer customer;

	@XmlElement(name="accountList")
	private SAccountList accountList;

	@XmlElement(name="userList")
	private SUserList userList;

	@XmlElement(name="productOrderList")
	private SProductOrderList productOrderList;

	@XmlElement(name="contactList")
	private SContactList contactList;

	@XmlElement(name="userAuthList")
	private SUserAuthList userAuthList;

	@XmlElement(name="balanceList")
	private SBalanceList balanceList;

	public void setCustomer(SCustomer obj){
		this.customer = obj;
	}

	public SCustomer getCustomer(){
		return customer;
	}

	public void setAccountList(SAccountList obj){
		this.accountList = obj;
	}

	public SAccountList getAccountList(){
		return accountList;
	}

	public void setUserList(SUserList obj){
		this.userList = obj;
	}

	public SUserList getUserList(){
		return userList;
	}

	public void setProductOrderList(SProductOrderList obj){
		this.productOrderList = obj;
	}

	public SProductOrderList getProductOrderList(){
		return productOrderList;
	}

	public void setContactList(SContactList obj){
		this.contactList = obj;
	}

	public SContactList getContactList(){
		return contactList;
	}

	public void setUserAuthList(SUserAuthList obj){
		this.userAuthList = obj;
	}

	public SUserAuthList getUserAuthList(){
		return userAuthList;
	}

	public void setBalanceList(SBalanceList obj){
		this.balanceList = obj;
	}

	public SBalanceList getBalanceList(){
		return balanceList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		NewRegistration rhs=(NewRegistration)rhs0;
		if(!ObjectUtils.equals(customer, rhs.customer)) return false;
		if(!ObjectUtils.equals(accountList, rhs.accountList)) return false;
		if(!ObjectUtils.equals(userList, rhs.userList)) return false;
		if(!ObjectUtils.equals(productOrderList, rhs.productOrderList)) return false;
		if(!ObjectUtils.equals(contactList, rhs.contactList)) return false;
		if(!ObjectUtils.equals(userAuthList, rhs.userAuthList)) return false;
		if(!ObjectUtils.equals(balanceList, rhs.balanceList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(customer)
		.append(accountList)
		.append(userList)
		.append(productOrderList)
		.append(contactList)
		.append(userAuthList)
		.append(balanceList)
		.toHashCode();
	}


}