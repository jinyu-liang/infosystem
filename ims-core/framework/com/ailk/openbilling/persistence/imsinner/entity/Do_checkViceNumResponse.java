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
@XmlType(propOrder={"check_flag"})
public class Do_checkViceNumResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="check_flag")
	private String check_flag;

	public void setCheck_flag(String obj){
		this.check_flag = obj;
	}

	public String getCheck_flag(){
		return check_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_checkViceNumResponse rhs=(Do_checkViceNumResponse)rhs0;
		if(!ObjectUtils.equals(check_flag, rhs.check_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(check_flag)
		.toHashCode();
	}


}