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
@XmlType(propOrder={"remark","oneTimeFeeList"})
public class Do_cancelOneTimeFeeResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="oneTimeFeeList")
	private OneTimeFeeList oneTimeFeeList;

	@XmlElement(name="remark")
	private String remark;

	public void setOneTimeFeeList(OneTimeFeeList obj){
		this.oneTimeFeeList = obj;
	}

	public OneTimeFeeList getOneTimeFeeList(){
		return oneTimeFeeList;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_cancelOneTimeFeeResponse rhs=(Do_cancelOneTimeFeeResponse)rhs0;
		if(!ObjectUtils.equals(oneTimeFeeList, rhs.oneTimeFeeList)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oneTimeFeeList)
		.append(remark)
		.toHashCode();
	}


}