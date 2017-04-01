package com.ailk.openbilling.persistence.innerqry.entity;

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
@XmlType(propOrder={"acct"})
public class Do_QryAcctInfoInRouteByUserIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="acct")
	private SAccount acct;

	public void setAcct(SAccount obj){
		this.acct = obj;
	}

	public SAccount getAcct(){
		return acct;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_QryAcctInfoInRouteByUserIdResponse rhs=(Do_QryAcctInfoInRouteByUserIdResponse)rhs0;
		if(!ObjectUtils.equals(acct, rhs.acct)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct)
		.toHashCode();
	}


}