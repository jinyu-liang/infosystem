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
@XmlType(propOrder={"original_so_nbr","original_so_date","onetime_seq","remark"})
public class SCancelOneTimeFeeReq implements IComplexEntity{


	@XmlElement(name="original_so_nbr")
	private String original_so_nbr;

	@XmlElement(name="original_so_date")
	private String original_so_date;

	@XmlElement(name="onetime_seq")
	private Long onetime_seq;

	@XmlElement(name="remark")
	private String remark;

	public void setOriginal_so_nbr(String obj){
		this.original_so_nbr = obj;
	}

	public String getOriginal_so_nbr(){
		return original_so_nbr;
	}

	public void setOriginal_so_date(String obj){
		this.original_so_date = obj;
	}

	public String getOriginal_so_date(){
		return original_so_date;
	}

	public void setOnetime_seq(Long obj){
		this.onetime_seq = obj;
	}

	public Long getOnetime_seq(){
		return onetime_seq;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SCancelOneTimeFeeReq rhs=(SCancelOneTimeFeeReq)rhs0;
		if(!ObjectUtils.equals(original_so_nbr, rhs.original_so_nbr)) return false;
		if(!ObjectUtils.equals(original_so_date, rhs.original_so_date)) return false;
		if(!ObjectUtils.equals(onetime_seq, rhs.onetime_seq)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(original_so_nbr)
		.append(original_so_date)
		.append(onetime_seq)
		.append(remark)
		.toHashCode();
	}


}