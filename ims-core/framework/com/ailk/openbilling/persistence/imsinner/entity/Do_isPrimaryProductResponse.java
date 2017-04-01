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
@XmlType(propOrder={"res"})
public class Do_isPrimaryProductResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="res")
	private Boolean res;

	public void setRes(Boolean obj){
		this.res = obj;
	}

	public Boolean isRes(){
		return res;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_isPrimaryProductResponse rhs=(Do_isPrimaryProductResponse)rhs0;
		if(!ObjectUtils.equals(res, rhs.res)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(res)
		.toHashCode();
	}


}