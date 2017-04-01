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
@XmlType(propOrder={"busiServiceOperArr"})
public class SBusiServiceOperList implements IComplexEntity{


	@XmlElement(name="busiServiceOperArr")
	private SBusiServiceOper[] busiServiceOperArr;

	public void setBusiServiceOperArr(SBusiServiceOper[] obj){
		this.busiServiceOperArr = obj;
	}

	public SBusiServiceOper[] getBusiServiceOperArr(){
		return busiServiceOperArr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SBusiServiceOperList rhs=(SBusiServiceOperList)rhs0;
		if(!ObjectUtils.equals(busiServiceOperArr, rhs.busiServiceOperArr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busiServiceOperArr)
		.toHashCode();
	}


}