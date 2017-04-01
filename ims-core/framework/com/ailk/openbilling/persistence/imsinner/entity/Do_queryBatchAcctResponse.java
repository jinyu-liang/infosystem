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
@XmlType(propOrder={"caAccount"})
public class Do_queryBatchAcctResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="caAccount")
	private List<CaAccount> caAccount;

	public void setCaAccount(List<CaAccount> obj){
		this.caAccount = obj;
	}

	public List<CaAccount> getCaAccount(){
		return caAccount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBatchAcctResponse rhs=(Do_queryBatchAcctResponse)rhs0;
		if(!ObjectUtils.equals(caAccount, rhs.caAccount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(caAccount)
		.toHashCode();
	}


}