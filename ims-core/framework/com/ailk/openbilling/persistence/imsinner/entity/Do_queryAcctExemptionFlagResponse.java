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
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"exemption_flag"})
public class Do_queryAcctExemptionFlagResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="exemption_flag")
	private Integer exemption_flag;

	public void setExemption_flag(Integer obj){
		this.exemption_flag = obj;
	}

	public Integer getExemption_flag(){
		return exemption_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryAcctExemptionFlagResponse rhs=(Do_queryAcctExemptionFlagResponse)rhs0;
		if(!ObjectUtils.equals(exemption_flag, rhs.exemption_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(exemption_flag)
		.toHashCode();
	}


}