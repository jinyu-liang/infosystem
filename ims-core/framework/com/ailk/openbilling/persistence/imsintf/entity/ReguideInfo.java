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
@XmlType(propOrder={"reguidedMobileNo","reguideItemInfoList","reguideMobileNo","reguideAcct"})
public class ReguideInfo implements IComplexEntity{


	@XmlElement(name="reguideItemInfoList")
	private ReguideItemInfoList reguideItemInfoList;

	@XmlElement(name="reguidedMobileNo")
	private String reguidedMobileNo;

	@XmlElement(name="reguideMobileNo")
	private String reguideMobileNo;

	@XmlElement(name="reguideAcct")
	private Long reguideAcct;

	public void setReguideItemInfoList(ReguideItemInfoList obj){
		this.reguideItemInfoList = obj;
	}

	public ReguideItemInfoList getReguideItemInfoList(){
		return reguideItemInfoList;
	}

	public void setReguidedMobileNo(String obj){
		this.reguidedMobileNo = obj;
	}

	public String getReguidedMobileNo(){
		return reguidedMobileNo;
	}

	public void setReguideMobileNo(String obj){
		this.reguideMobileNo = obj;
	}

	public String getReguideMobileNo(){
		return reguideMobileNo;
	}

	public void setReguideAcct(Long obj){
		this.reguideAcct = obj;
	}

	public Long getReguideAcct(){
		return reguideAcct;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideInfo rhs=(ReguideInfo)rhs0;
		if(!ObjectUtils.equals(reguideItemInfoList, rhs.reguideItemInfoList)) return false;
		if(!ObjectUtils.equals(reguidedMobileNo, rhs.reguidedMobileNo)) return false;
		if(!ObjectUtils.equals(reguideMobileNo, rhs.reguideMobileNo)) return false;
		if(!ObjectUtils.equals(reguideAcct, rhs.reguideAcct)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(reguideItemInfoList)
		.append(reguidedMobileNo)
		.append(reguideMobileNo)
		.append(reguideAcct)
		.toHashCode();
	}


}