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
@XmlType(propOrder={"white_count","white_maxNum","black_count","black_maxNum"})
public class SQueryMaxNumOfCallScreenResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="white_count")
	private Integer white_count;

	@XmlElement(name="white_maxNum")
	private Integer white_maxNum;

	@XmlElement(name="black_count")
	private Integer black_count;

	@XmlElement(name="black_maxNum")
	private Integer black_maxNum;

	public void setWhite_count(Integer obj){
		this.white_count = obj;
	}

	public Integer getWhite_count(){
		return white_count;
	}

	public void setWhite_maxNum(Integer obj){
		this.white_maxNum = obj;
	}

	public Integer getWhite_maxNum(){
		return white_maxNum;
	}

	public void setBlack_count(Integer obj){
		this.black_count = obj;
	}

	public Integer getBlack_count(){
		return black_count;
	}

	public void setBlack_maxNum(Integer obj){
		this.black_maxNum = obj;
	}

	public Integer getBlack_maxNum(){
		return black_maxNum;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryMaxNumOfCallScreenResponse rhs=(SQueryMaxNumOfCallScreenResponse)rhs0;
		if(!ObjectUtils.equals(white_count, rhs.white_count)) return false;
		if(!ObjectUtils.equals(white_maxNum, rhs.white_maxNum)) return false;
		if(!ObjectUtils.equals(black_count, rhs.black_count)) return false;
		if(!ObjectUtils.equals(black_maxNum, rhs.black_maxNum)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(white_count)
		.append(white_maxNum)
		.append(black_count)
		.append(black_maxNum)
		.toHashCode();
	}


}