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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"phoneId"})
public class Do_queryPhoneWithTimeResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="phoneId")
	private String phoneId;

	public void setPhoneId(String obj){
		this.phoneId = obj;
	}

	public String getPhoneId(){
		return phoneId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryPhoneWithTimeResponse rhs=(Do_queryPhoneWithTimeResponse)rhs0;
		if(!ObjectUtils.equals(phoneId, rhs.phoneId)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phoneId)
		.toHashCode();
	}


}