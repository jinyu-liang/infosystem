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
@XmlType(propOrder={"count","maxNum"})
public class SQueryMaxNumOfGroupMemberResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="count")
	private Integer count;

	@XmlElement(name="maxNum")
	private Integer maxNum;

	public void setCount(Integer obj){
		this.count = obj;
	}

	public Integer getCount(){
		return count;
	}

	public void setMaxNum(Integer obj){
		this.maxNum = obj;
	}

	public Integer getMaxNum(){
		return maxNum;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryMaxNumOfGroupMemberResponse rhs=(SQueryMaxNumOfGroupMemberResponse)rhs0;
		if(!ObjectUtils.equals(count, rhs.count)) return false;
		if(!ObjectUtils.equals(maxNum, rhs.maxNum)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(count)
		.append(maxNum)
		.toHashCode();
	}


}