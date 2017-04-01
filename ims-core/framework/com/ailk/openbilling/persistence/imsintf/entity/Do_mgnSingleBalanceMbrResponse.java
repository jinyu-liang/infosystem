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
@XmlType(propOrder={"sMemberBalanceResponse","sMasterBalanceResponse","onetimefee_amount"})
public class Do_mgnSingleBalanceMbrResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sMemberBalanceResponse")
	private SBalanceAdjustResponse sMemberBalanceResponse;

	@XmlElement(name="sMasterBalanceResponse")
	private SBalanceAdjustResponse sMasterBalanceResponse;

	@XmlElement(name="onetimefee_amount")
	private Long onetimefee_amount;

	public void setSMemberBalanceResponse(SBalanceAdjustResponse obj){
		this.sMemberBalanceResponse = obj;
	}

	public SBalanceAdjustResponse getSMemberBalanceResponse(){
		return sMemberBalanceResponse;
	}

	public void setSMasterBalanceResponse(SBalanceAdjustResponse obj){
		this.sMasterBalanceResponse = obj;
	}

	public SBalanceAdjustResponse getSMasterBalanceResponse(){
		return sMasterBalanceResponse;
	}

	public void setOnetimefee_amount(Long obj){
		this.onetimefee_amount = obj;
	}

	public Long getOnetimefee_amount(){
		return onetimefee_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_mgnSingleBalanceMbrResponse rhs=(Do_mgnSingleBalanceMbrResponse)rhs0;
		if(!ObjectUtils.equals(sMemberBalanceResponse, rhs.sMemberBalanceResponse)) return false;
		if(!ObjectUtils.equals(sMasterBalanceResponse, rhs.sMasterBalanceResponse)) return false;
		if(!ObjectUtils.equals(onetimefee_amount, rhs.onetimefee_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sMemberBalanceResponse)
		.append(sMasterBalanceResponse)
		.append(onetimefee_amount)
		.toHashCode();
	}


}