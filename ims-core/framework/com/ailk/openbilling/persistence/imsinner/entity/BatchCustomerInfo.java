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
@XmlType(propOrder={"old_family_name","old_first_name","old_country_code","old_region_code","old_cust_type","old_cust_class","old_cust_segment","old_taxexempt","old_address1","old_address2","old_address3","old_address4","old_address5","new_cust_type","new_cust_class","new_cust_segment","new_taxexempt","new_address1","new_address2","new_address3","new_address4","new_address5"})
public class BatchCustomerInfo implements IComplexEntity{


	@XmlElement(name="old_family_name")
	private String old_family_name;

	@XmlElement(name="old_first_name")
	private String old_first_name;

	@XmlElement(name="old_country_code")
	private Integer old_country_code;

	@XmlElement(name="old_region_code")
	private Integer old_region_code;

	@XmlElement(name="old_cust_type")
	private Short old_cust_type;

	@XmlElement(name="old_cust_class")
	private Long old_cust_class;

	@XmlElement(name="old_cust_segment")
	private Short old_cust_segment;

	@XmlElement(name="old_taxexempt")
	private String old_taxexempt;

	@XmlElement(name="old_address1")
	private String old_address1;

	@XmlElement(name="old_address2")
	private String old_address2;

	@XmlElement(name="old_address3")
	private String old_address3;

	@XmlElement(name="old_address4")
	private String old_address4;

	@XmlElement(name="old_address5")
	private String old_address5;

	@XmlElement(name="new_cust_type")
	private Short new_cust_type;

	@XmlElement(name="new_cust_class")
	private Short new_cust_class;

	@XmlElement(name="new_cust_segment")
	private Short new_cust_segment;

	@XmlElement(name="new_taxexempt")
	private String new_taxexempt;

	@XmlElement(name="new_address1")
	private String new_address1;

	@XmlElement(name="new_address2")
	private String new_address2;

	@XmlElement(name="new_address3")
	private String new_address3;

	@XmlElement(name="new_address4")
	private String new_address4;

	@XmlElement(name="new_address5")
	private String new_address5;

	public void setOld_family_name(String obj){
		this.old_family_name = obj;
	}

	public String getOld_family_name(){
		return old_family_name;
	}

	public void setOld_first_name(String obj){
		this.old_first_name = obj;
	}

	public String getOld_first_name(){
		return old_first_name;
	}

	public void setOld_country_code(Integer obj){
		this.old_country_code = obj;
	}

	public Integer getOld_country_code(){
		return old_country_code;
	}

	public void setOld_region_code(Integer obj){
		this.old_region_code = obj;
	}

	public Integer getOld_region_code(){
		return old_region_code;
	}

	public void setOld_cust_type(Short obj){
		this.old_cust_type = obj;
	}

	public Short getOld_cust_type(){
		return old_cust_type;
	}

	public void setOld_cust_class(Long obj){
		this.old_cust_class = obj;
	}

	public Long getOld_cust_class(){
		return old_cust_class;
	}

	public void setOld_cust_segment(Short obj){
		this.old_cust_segment = obj;
	}

	public Short getOld_cust_segment(){
		return old_cust_segment;
	}

	public void setOld_taxexempt(String obj){
		this.old_taxexempt = obj;
	}

	public String getOld_taxexempt(){
		return old_taxexempt;
	}

	public void setOld_address1(String obj){
		this.old_address1 = obj;
	}

	public String getOld_address1(){
		return old_address1;
	}

	public void setOld_address2(String obj){
		this.old_address2 = obj;
	}

	public String getOld_address2(){
		return old_address2;
	}

	public void setOld_address3(String obj){
		this.old_address3 = obj;
	}

	public String getOld_address3(){
		return old_address3;
	}

	public void setOld_address4(String obj){
		this.old_address4 = obj;
	}

	public String getOld_address4(){
		return old_address4;
	}

	public void setOld_address5(String obj){
		this.old_address5 = obj;
	}

	public String getOld_address5(){
		return old_address5;
	}

	public void setNew_cust_type(Short obj){
		this.new_cust_type = obj;
	}

	public Short getNew_cust_type(){
		return new_cust_type;
	}

	public void setNew_cust_class(Short obj){
		this.new_cust_class = obj;
	}

	public Short getNew_cust_class(){
		return new_cust_class;
	}

	public void setNew_cust_segment(Short obj){
		this.new_cust_segment = obj;
	}

	public Short getNew_cust_segment(){
		return new_cust_segment;
	}

	public void setNew_taxexempt(String obj){
		this.new_taxexempt = obj;
	}

	public String getNew_taxexempt(){
		return new_taxexempt;
	}

	public void setNew_address1(String obj){
		this.new_address1 = obj;
	}

	public String getNew_address1(){
		return new_address1;
	}

	public void setNew_address2(String obj){
		this.new_address2 = obj;
	}

	public String getNew_address2(){
		return new_address2;
	}

	public void setNew_address3(String obj){
		this.new_address3 = obj;
	}

	public String getNew_address3(){
		return new_address3;
	}

	public void setNew_address4(String obj){
		this.new_address4 = obj;
	}

	public String getNew_address4(){
		return new_address4;
	}

	public void setNew_address5(String obj){
		this.new_address5 = obj;
	}

	public String getNew_address5(){
		return new_address5;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BatchCustomerInfo rhs=(BatchCustomerInfo)rhs0;
		if(!ObjectUtils.equals(old_family_name, rhs.old_family_name)) return false;
		if(!ObjectUtils.equals(old_first_name, rhs.old_first_name)) return false;
		if(!ObjectUtils.equals(old_country_code, rhs.old_country_code)) return false;
		if(!ObjectUtils.equals(old_region_code, rhs.old_region_code)) return false;
		if(!ObjectUtils.equals(old_cust_type, rhs.old_cust_type)) return false;
		if(!ObjectUtils.equals(old_cust_class, rhs.old_cust_class)) return false;
		if(!ObjectUtils.equals(old_cust_segment, rhs.old_cust_segment)) return false;
		if(!ObjectUtils.equals(old_taxexempt, rhs.old_taxexempt)) return false;
		if(!ObjectUtils.equals(old_address1, rhs.old_address1)) return false;
		if(!ObjectUtils.equals(old_address2, rhs.old_address2)) return false;
		if(!ObjectUtils.equals(old_address3, rhs.old_address3)) return false;
		if(!ObjectUtils.equals(old_address4, rhs.old_address4)) return false;
		if(!ObjectUtils.equals(old_address5, rhs.old_address5)) return false;
		if(!ObjectUtils.equals(new_cust_type, rhs.new_cust_type)) return false;
		if(!ObjectUtils.equals(new_cust_class, rhs.new_cust_class)) return false;
		if(!ObjectUtils.equals(new_cust_segment, rhs.new_cust_segment)) return false;
		if(!ObjectUtils.equals(new_taxexempt, rhs.new_taxexempt)) return false;
		if(!ObjectUtils.equals(new_address1, rhs.new_address1)) return false;
		if(!ObjectUtils.equals(new_address2, rhs.new_address2)) return false;
		if(!ObjectUtils.equals(new_address3, rhs.new_address3)) return false;
		if(!ObjectUtils.equals(new_address4, rhs.new_address4)) return false;
		if(!ObjectUtils.equals(new_address5, rhs.new_address5)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(old_family_name)
		.append(old_first_name)
		.append(old_country_code)
		.append(old_region_code)
		.append(old_cust_type)
		.append(old_cust_class)
		.append(old_cust_segment)
		.append(old_taxexempt)
		.append(old_address1)
		.append(old_address2)
		.append(old_address3)
		.append(old_address4)
		.append(old_address5)
		.append(new_cust_type)
		.append(new_cust_class)
		.append(new_cust_segment)
		.append(new_taxexempt)
		.append(new_address1)
		.append(new_address2)
		.append(new_address3)
		.append(new_address4)
		.append(new_address5)
		.toHashCode();
	}


}