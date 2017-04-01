package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsinner.entity.SPage;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"errorMsg","sPage"})
public class BaseResponse implements IComplexEntity{


	@XmlElement(name="errorMsg")
	private CBSErrorMsg errorMsg;

	@XmlElement(name="sPage")
	private SPage sPage;

	public void setErrorMsg(CBSErrorMsg obj){
		this.errorMsg = obj;
	}

	public CBSErrorMsg getErrorMsg(){
		return errorMsg;
	}

	public void setSPage(SPage obj){
		this.sPage = obj;
	}

	public SPage getSPage(){
		return sPage;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BaseResponse rhs=(BaseResponse)rhs0;
		if(!ObjectUtils.equals(errorMsg, rhs.errorMsg)) return false;
		if(!ObjectUtils.equals(sPage, rhs.sPage)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(errorMsg)
		.append(sPage)
		.toHashCode();
	}


}