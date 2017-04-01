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
@XmlType(propOrder={"onetimefee_amount"})
public class Do_ChgMasterNumResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="onetimefee_amount")
	private Long onetimefee_amount;

	public void setOnetimefee_amount(Long obj){
		this.onetimefee_amount = obj;
	}

	public Long getOnetimefee_amount(){
		return onetimefee_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_ChgMasterNumResponse rhs=(Do_ChgMasterNumResponse)rhs0;
		if(!ObjectUtils.equals(onetimefee_amount, rhs.onetimefee_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(onetimefee_amount)
		.toHashCode();
	}


}