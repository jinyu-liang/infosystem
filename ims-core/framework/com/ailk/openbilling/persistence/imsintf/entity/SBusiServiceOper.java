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
@XmlType(propOrder={"oper_type","busiService"})
public class SBusiServiceOper implements IComplexEntity{


	@XmlElement(name="busiService")
	private SBusiService busiService;

	@XmlElement(name="oper_type")
	private Short oper_type;

	public void setBusiService(SBusiService obj){
		this.busiService = obj;
	}

	public SBusiService getBusiService(){
		return busiService;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBusiServiceOper rhs=(SBusiServiceOper)rhs0;
		if(!ObjectUtils.equals(busiService, rhs.busiService)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busiService)
		.append(oper_type)
		.toHashCode();
	}


}