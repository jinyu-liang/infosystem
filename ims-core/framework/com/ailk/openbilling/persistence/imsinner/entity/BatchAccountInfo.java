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
@XmlType(propOrder={"acct_name","country_code","old_region_code","old_operator_id","old_measure_id","old_bill_language","old_invoice_type","old_bill_style_id","old_charge_type","new_region_code","new_operator_id","new_measuer_id","new_bill_language","new_invoice_type","new_bill_style_id","new_charge_type"})
public class BatchAccountInfo implements IComplexEntity{


	@XmlElement(name="acct_name")
	private String acct_name;

	@XmlElement(name="country_code")
	private Integer country_code;

	@XmlElement(name="old_region_code")
	private Integer old_region_code;

	@XmlElement(name="old_operator_id")
	private Integer old_operator_id;

	@XmlElement(name="old_measure_id")
	private Integer old_measure_id;

	@XmlElement(name="old_bill_language")
	private Integer old_bill_language;

	@XmlElement(name="old_invoice_type")
	private Integer old_invoice_type;

	@XmlElement(name="old_bill_style_id")
	private Integer old_bill_style_id;

	@XmlElement(name="old_charge_type")
	private String old_charge_type;

	@XmlElement(name="new_region_code")
	private Integer new_region_code;

	@XmlElement(name="new_operator_id")
	private Integer new_operator_id;

	@XmlElement(name="new_measuer_id")
	private Integer new_measuer_id;

	@XmlElement(name="new_bill_language")
	private Integer new_bill_language;

	@XmlElement(name="new_invoice_type")
	private Integer new_invoice_type;

	@XmlElement(name="new_bill_style_id")
	private Integer new_bill_style_id;

	@XmlElement(name="new_charge_type")
	private String new_charge_type;

	public void setAcct_name(String obj){
		this.acct_name = obj;
	}

	public String getAcct_name(){
		return acct_name;
	}

	public void setCountry_code(Integer obj){
		this.country_code = obj;
	}

	public Integer getCountry_code(){
		return country_code;
	}

	public void setOld_region_code(Integer obj){
		this.old_region_code = obj;
	}

	public Integer getOld_region_code(){
		return old_region_code;
	}

	public void setOld_operator_id(Integer obj){
		this.old_operator_id = obj;
	}

	public Integer getOld_operator_id(){
		return old_operator_id;
	}

	public void setOld_measure_id(Integer obj){
		this.old_measure_id = obj;
	}

	public Integer getOld_measure_id(){
		return old_measure_id;
	}

	public void setOld_bill_language(Integer obj){
		this.old_bill_language = obj;
	}

	public Integer getOld_bill_language(){
		return old_bill_language;
	}

	public void setOld_invoice_type(Integer obj){
		this.old_invoice_type = obj;
	}

	public Integer getOld_invoice_type(){
		return old_invoice_type;
	}

	public void setOld_bill_style_id(Integer obj){
		this.old_bill_style_id = obj;
	}

	public Integer getOld_bill_style_id(){
		return old_bill_style_id;
	}

	public void setOld_charge_type(String obj){
		this.old_charge_type = obj;
	}

	public String getOld_charge_type(){
		return old_charge_type;
	}

	public void setNew_region_code(Integer obj){
		this.new_region_code = obj;
	}

	public Integer getNew_region_code(){
		return new_region_code;
	}

	public void setNew_operator_id(Integer obj){
		this.new_operator_id = obj;
	}

	public Integer getNew_operator_id(){
		return new_operator_id;
	}

	public void setNew_measuer_id(Integer obj){
		this.new_measuer_id = obj;
	}

	public Integer getNew_measuer_id(){
		return new_measuer_id;
	}

	public void setNew_bill_language(Integer obj){
		this.new_bill_language = obj;
	}

	public Integer getNew_bill_language(){
		return new_bill_language;
	}

	public void setNew_invoice_type(Integer obj){
		this.new_invoice_type = obj;
	}

	public Integer getNew_invoice_type(){
		return new_invoice_type;
	}

	public void setNew_bill_style_id(Integer obj){
		this.new_bill_style_id = obj;
	}

	public Integer getNew_bill_style_id(){
		return new_bill_style_id;
	}

	public void setNew_charge_type(String obj){
		this.new_charge_type = obj;
	}

	public String getNew_charge_type(){
		return new_charge_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BatchAccountInfo rhs=(BatchAccountInfo)rhs0;
		if(!ObjectUtils.equals(acct_name, rhs.acct_name)) return false;
		if(!ObjectUtils.equals(country_code, rhs.country_code)) return false;
		if(!ObjectUtils.equals(old_region_code, rhs.old_region_code)) return false;
		if(!ObjectUtils.equals(old_operator_id, rhs.old_operator_id)) return false;
		if(!ObjectUtils.equals(old_measure_id, rhs.old_measure_id)) return false;
		if(!ObjectUtils.equals(old_bill_language, rhs.old_bill_language)) return false;
		if(!ObjectUtils.equals(old_invoice_type, rhs.old_invoice_type)) return false;
		if(!ObjectUtils.equals(old_bill_style_id, rhs.old_bill_style_id)) return false;
		if(!ObjectUtils.equals(old_charge_type, rhs.old_charge_type)) return false;
		if(!ObjectUtils.equals(new_region_code, rhs.new_region_code)) return false;
		if(!ObjectUtils.equals(new_operator_id, rhs.new_operator_id)) return false;
		if(!ObjectUtils.equals(new_measuer_id, rhs.new_measuer_id)) return false;
		if(!ObjectUtils.equals(new_bill_language, rhs.new_bill_language)) return false;
		if(!ObjectUtils.equals(new_invoice_type, rhs.new_invoice_type)) return false;
		if(!ObjectUtils.equals(new_bill_style_id, rhs.new_bill_style_id)) return false;
		if(!ObjectUtils.equals(new_charge_type, rhs.new_charge_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_name)
		.append(country_code)
		.append(old_region_code)
		.append(old_operator_id)
		.append(old_measure_id)
		.append(old_bill_language)
		.append(old_invoice_type)
		.append(old_bill_style_id)
		.append(old_charge_type)
		.append(new_region_code)
		.append(new_operator_id)
		.append(new_measuer_id)
		.append(new_bill_language)
		.append(new_invoice_type)
		.append(new_bill_style_id)
		.append(new_charge_type)
		.toHashCode();
	}


}