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
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"account"})
public class Do_QueryAcctByPhoneResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="account")
	private CaAccount account;

	public void setAccount(CaAccount obj){
		this.account = obj;
	}

	public CaAccount getAccount(){
		return account;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QueryAcctByPhoneResponse rhs=(Do_QueryAcctByPhoneResponse)rhs0;
		if(!ObjectUtils.equals(account, rhs.account)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(account)
		.toHashCode();
	}


}