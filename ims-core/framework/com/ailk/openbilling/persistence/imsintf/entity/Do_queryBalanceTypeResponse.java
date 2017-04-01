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
@XmlType(propOrder={"balanceInfo"})
public class Do_queryBalanceTypeResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="balanceInfo")
	private BalanceInfo balanceInfo;

	public void setBalanceInfo(BalanceInfo obj){
		this.balanceInfo = obj;
	}

	public BalanceInfo getBalanceInfo(){
		return balanceInfo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBalanceTypeResponse rhs=(Do_queryBalanceTypeResponse)rhs0;
		if(!ObjectUtils.equals(balanceInfo, rhs.balanceInfo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(balanceInfo)
		.toHashCode();
	}


}