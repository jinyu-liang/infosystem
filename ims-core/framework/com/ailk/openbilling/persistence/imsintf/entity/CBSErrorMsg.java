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
@XmlType(propOrder={"orig_so_nbr","so_nbr","finish_date","result_code","error_msg","hint"})
public class CBSErrorMsg implements IComplexEntity{


	@XmlElement(name="orig_so_nbr")
	private String orig_so_nbr;

	@XmlElement(name="so_nbr")
	private Long so_nbr;

	@XmlElement(name="finish_date")
	private String finish_date;

	@XmlElement(name="result_code")
	private Long result_code;

	@XmlElement(name="error_msg")
	private String error_msg;

	@XmlElement(name="hint")
	private String hint;

	public void setOrig_so_nbr(String obj){
		this.orig_so_nbr = obj;
	}

	public String getOrig_so_nbr(){
		return orig_so_nbr;
	}

	public void setSo_nbr(Long obj){
		this.so_nbr = obj;
	}

	public Long getSo_nbr(){
		return so_nbr;
	}

	public void setFinish_date(String obj){
		this.finish_date = obj;
	}

	public String getFinish_date(){
		return finish_date;
	}

	public void setResult_code(Long obj){
		this.result_code = obj;
	}

	public Long getResult_code(){
		return result_code;
	}

	public void setError_msg(String obj){
		this.error_msg = obj;
	}

	public String getError_msg(){
		return error_msg;
	}

	public void setHint(String obj){
		this.hint = obj;
	}

	public String getHint(){
		return hint;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CBSErrorMsg rhs=(CBSErrorMsg)rhs0;
		if(!ObjectUtils.equals(orig_so_nbr, rhs.orig_so_nbr)) return false;
		if(!ObjectUtils.equals(so_nbr, rhs.so_nbr)) return false;
		if(!ObjectUtils.equals(finish_date, rhs.finish_date)) return false;
		if(!ObjectUtils.equals(result_code, rhs.result_code)) return false;
		if(!ObjectUtils.equals(error_msg, rhs.error_msg)) return false;
		if(!ObjectUtils.equals(hint, rhs.hint)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(orig_so_nbr)
		.append(so_nbr)
		.append(finish_date)
		.append(result_code)
		.append(error_msg)
		.append(hint)
		.toHashCode();
	}


}