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
@XmlType(propOrder={"reguideAcctId","reguideCharge"})
public class ReguideAcctInfo implements IComplexEntity{


	@XmlElement(name="reguideAcctId")
	private Long reguideAcctId;

	@XmlElement(name="reguideCharge")
	private Long reguideCharge;

	public void setReguideAcctId(Long obj){
		this.reguideAcctId = obj;
	}

	public Long getReguideAcctId(){
		return reguideAcctId;
	}

	public void setReguideCharge(Long obj){
		this.reguideCharge = obj;
	}

	public Long getReguideCharge(){
		return reguideCharge;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideAcctInfo rhs=(ReguideAcctInfo)rhs0;
		if(!ObjectUtils.equals(reguideAcctId, rhs.reguideAcctId)) return false;
		if(!ObjectUtils.equals(reguideCharge, rhs.reguideCharge)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideAcctId)
		.append(reguideCharge)
		.toHashCode();
	}


}