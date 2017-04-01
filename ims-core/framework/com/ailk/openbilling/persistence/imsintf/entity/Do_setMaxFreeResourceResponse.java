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
@XmlType(propOrder={"maxFreeResourceResponseList"})
public class Do_setMaxFreeResourceResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="maxFreeResourceResponseList")
	private MaxFreeResourceResponse maxFreeResourceResponseList;

	public void setMaxFreeResourceResponseList(MaxFreeResourceResponse obj){
		this.maxFreeResourceResponseList = obj;
	}

	public MaxFreeResourceResponse getMaxFreeResourceResponseList(){
		return maxFreeResourceResponseList;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_setMaxFreeResourceResponse rhs=(Do_setMaxFreeResourceResponse)rhs0;
		if(!ObjectUtils.equals(maxFreeResourceResponseList, rhs.maxFreeResourceResponseList)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(maxFreeResourceResponseList)
		.toHashCode();
	}


}