package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"parent_id","account"})
public class SQueryAcctsByCustIdReturn implements IComplexEntity{


	@XmlElement(name="account")
	private CaAccount account;

	@XmlElement(name="parent_id")
	private Long parent_id;

	public void setAccount(CaAccount obj){
		this.account = obj;
	}

	public CaAccount getAccount(){
		return account;
	}

	public void setParent_id(Long obj){
		this.parent_id = obj;
	}

	public Long getParent_id(){
		return parent_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryAcctsByCustIdReturn rhs=(SQueryAcctsByCustIdReturn)rhs0;
		if(!ObjectUtils.equals(account, rhs.account)) return false;
		if(!ObjectUtils.equals(parent_id, rhs.parent_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(account)
		.append(parent_id)
		.toHashCode();
	}


}