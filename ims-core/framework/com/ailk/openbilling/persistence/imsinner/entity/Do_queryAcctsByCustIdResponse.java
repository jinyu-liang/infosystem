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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"account_list"})
public class Do_queryAcctsByCustIdResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="account_list")
	private List<SQueryAcctsByCustIdReturn> account_list;

	public void setAccount_list(List<SQueryAcctsByCustIdReturn> obj){
		this.account_list = obj;
	}

	public List<SQueryAcctsByCustIdReturn> getAccount_list(){
		return account_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAcctsByCustIdResponse rhs=(Do_queryAcctsByCustIdResponse)rhs0;
		if(!ObjectUtils.equals(account_list, rhs.account_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(account_list)
		.toHashCode();
	}


}