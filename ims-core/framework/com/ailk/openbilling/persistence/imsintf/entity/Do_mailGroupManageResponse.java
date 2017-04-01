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
@XmlType(propOrder={"accountMail"})
public class Do_mailGroupManageResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="accountMail")
	private AccountMail[] accountMail;

	public void setAccountMail(AccountMail[] obj){
		this.accountMail = obj;
	}

	public AccountMail[] getAccountMail(){
		return accountMail;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_mailGroupManageResponse rhs=(Do_mailGroupManageResponse)rhs0;
		if(!ObjectUtils.equals(accountMail, rhs.accountMail)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accountMail)
		.toHashCode();
	}


}