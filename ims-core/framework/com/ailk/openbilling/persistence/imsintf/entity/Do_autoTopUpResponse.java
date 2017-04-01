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
@XmlType(propOrder={"auto_id"})
public class Do_autoTopUpResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="auto_id")
	private String auto_id;

	public void setAuto_id(String obj){
		this.auto_id = obj;
	}

	public String getAuto_id(){
		return auto_id;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_autoTopUpResponse rhs=(Do_autoTopUpResponse)rhs0;
		if(!ObjectUtils.equals(auto_id, rhs.auto_id)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(auto_id)
		.toHashCode();
	}


}