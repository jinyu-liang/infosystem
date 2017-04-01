package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
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
@XmlType(propOrder={"acctList"})
public class Do_acctQueryResponse extends Do_custQueryResponse implements IComplexEntity{


	@XmlElement(name="acctList")
	private List<AccountRet> acctList;

	public void setAcctList(List<AccountRet> obj){
		this.acctList = obj;
	}

	public List<AccountRet> getAcctList(){
		return acctList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_acctQueryResponse rhs=(Do_acctQueryResponse)rhs0;
		if(!ObjectUtils.equals(acctList, rhs.acctList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctList)
		.toHashCode();
	}


}