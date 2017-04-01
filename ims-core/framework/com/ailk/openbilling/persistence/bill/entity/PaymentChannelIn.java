package com.ailk.openbilling.persistence.bill.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"caPayChannel","bankFund"})
public class PaymentChannelIn implements IComplexEntity{


	@XmlElement(name="caPayChannel")
	private CaPayChannel caPayChannel;

	@XmlElement(name="bankFund")
	private CaBankFund bankFund;

	public void setCaPayChannel(CaPayChannel obj){
		this.caPayChannel = obj;
	}

	public CaPayChannel getCaPayChannel(){
		return caPayChannel;
	}

	public void setBankFund(CaBankFund obj){
		this.bankFund = obj;
	}

	public CaBankFund getBankFund(){
		return bankFund;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		PaymentChannelIn rhs=(PaymentChannelIn)rhs0;
		if(!ObjectUtils.equals(caPayChannel, rhs.caPayChannel)) return false;
		if(!ObjectUtils.equals(bankFund, rhs.bankFund)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(caPayChannel)
		.append(bankFund)
		.toHashCode();
	}


}