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
import java.util.List;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"accounts"})
public class Do_QueryAcctInfoResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="accounts")
	private List<CaAccount> accounts;

	public void setAccounts(List<CaAccount> obj){
		this.accounts = obj;
	}

	public List<CaAccount> getAccounts(){
		return accounts;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QueryAcctInfoResponse rhs=(Do_QueryAcctInfoResponse)rhs0;
		if(!ObjectUtils.equals(accounts, rhs.accounts)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(accounts)
		.toHashCode();
	}


}