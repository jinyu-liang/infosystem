package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SContactList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"account","productOrderList","contactList"})
public class Do_queryAcctInvolveInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="account")
	private SAccount account;

	@XmlElement(name="productOrderList")
	private SProductOrderList productOrderList;

	@XmlElement(name="contactList")
	private SContactList contactList;

	public void setAccount(SAccount obj){
		this.account = obj;
	}

	public SAccount getAccount(){
		return account;
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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAcctInvolveInfoResponse rhs=(Do_queryAcctInvolveInfoResponse)rhs0;
		if(!ObjectUtils.equals(account, rhs.account)) return false;
		if(!ObjectUtils.equals(productOrderList, rhs.productOrderList)) return false;
		if(!ObjectUtils.equals(contactList, rhs.contactList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(account)
		.append(productOrderList)
		.append(contactList)
		.toHashCode();
	}


}