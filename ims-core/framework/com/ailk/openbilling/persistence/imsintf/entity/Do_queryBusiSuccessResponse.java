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
@XmlType(propOrder={"sts","done_code","so_date"})
public class Do_queryBusiSuccessResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="sts")
	private Short sts;

	@XmlElement(name="done_code")
	private String done_code;

	@XmlElement(name="so_date")
	private String so_date;

	public void setSts(Short obj){
		this.sts = obj;
	}

	public Short getSts(){
		return sts;
	}

	public void setDone_code(String obj){
		this.done_code = obj;
	}

	public String getDone_code(){
		return done_code;
	}

	public void setSo_date(String obj){
		this.so_date = obj;
	}

	public String getSo_date(){
		return so_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryBusiSuccessResponse rhs=(Do_queryBusiSuccessResponse)rhs0;
		if(!ObjectUtils.equals(sts, rhs.sts)) return false;
		if(!ObjectUtils.equals(done_code, rhs.done_code)) return false;
		if(!ObjectUtils.equals(so_date, rhs.so_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sts)
		.append(done_code)
		.append(so_date)
		.toHashCode();
	}


}