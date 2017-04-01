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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"sAccount"})
public class Do_queryAccountResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sAccount")
	private SAccount sAccount;

	public void setSAccount(SAccount obj){
		this.sAccount = obj;
	}

	public SAccount getSAccount(){
		return sAccount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAccountResponse rhs=(Do_queryAccountResponse)rhs0;
		if(!ObjectUtils.equals(sAccount, rhs.sAccount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sAccount)
		.toHashCode();
	}


}