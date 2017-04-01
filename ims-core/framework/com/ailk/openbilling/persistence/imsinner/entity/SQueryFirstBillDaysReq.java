package com.ailk.openbilling.persistence.imsinner.entity;

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
@XmlType(propOrder={"cycleInfo"})
public class SQueryFirstBillDaysReq implements IComplexEntity{


	@XmlElement(name="cycleInfo")
	private CycleInfo cycleInfo;

	public void setCycleInfo(CycleInfo obj){
		this.cycleInfo = obj;
	}

	public CycleInfo getCycleInfo(){
		return cycleInfo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryFirstBillDaysReq rhs=(SQueryFirstBillDaysReq)rhs0;
		if(!ObjectUtils.equals(cycleInfo, rhs.cycleInfo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(cycleInfo)
		.toHashCode();
	}


}