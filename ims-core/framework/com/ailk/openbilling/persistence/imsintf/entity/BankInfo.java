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
@XmlType(propOrder={"bank_code","bank_abbreviate","bank_desc_thai","bank_desc_eng"})
public class BankInfo implements IComplexEntity{


	@XmlElement(name="bank_code")
	private Integer bank_code;

	@XmlElement(name="bank_abbreviate")
	private String bank_abbreviate;

	@XmlElement(name="bank_desc_thai")
	private String bank_desc_thai;

	@XmlElement(name="bank_desc_eng")
	private String bank_desc_eng;

	public void setBank_code(Integer obj){
		this.bank_code = obj;
	}

	public Integer getBank_code(){
		return bank_code;
	}

	public void setBank_abbreviate(String obj){
		this.bank_abbreviate = obj;
	}

	public String getBank_abbreviate(){
		return bank_abbreviate;
	}

	public void setBank_desc_thai(String obj){
		this.bank_desc_thai = obj;
	}

	public String getBank_desc_thai(){
		return bank_desc_thai;
	}

	public void setBank_desc_eng(String obj){
		this.bank_desc_eng = obj;
	}

	public String getBank_desc_eng(){
		return bank_desc_eng;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BankInfo rhs=(BankInfo)rhs0;
		if(!ObjectUtils.equals(bank_code, rhs.bank_code)) return false;
		if(!ObjectUtils.equals(bank_abbreviate, rhs.bank_abbreviate)) return false;
		if(!ObjectUtils.equals(bank_desc_thai, rhs.bank_desc_thai)) return false;
		if(!ObjectUtils.equals(bank_desc_eng, rhs.bank_desc_eng)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bank_code)
		.append(bank_abbreviate)
		.append(bank_desc_thai)
		.append(bank_desc_eng)
		.toHashCode();
	}


}