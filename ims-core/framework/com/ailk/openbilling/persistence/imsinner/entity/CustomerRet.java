package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import java.util.List;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"cust_id","cust_type","cust_title","sub_cust_type","family_name","first_name","middle_name","country_id","prov_code","region_code","cust_rank","cust_segment","birthday","gender","reg_name","reg_type","reg_nbr","issue_date","language","create_date","valid_date","tax_exempt_code","vat_name","real_name_date","real_name_sts","list_ext_param","sCustomerConact","customer_name","ext1"})
public class CustomerRet extends BaseQueryRet implements IComplexEntity{


	@XmlElement(name="list_ext_param")
	private ExtendParamList list_ext_param;

	@XmlElement(name="sCustomerConact")
	private List<SContact> sCustomerConact;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="cust_type")
	private Short cust_type;

	@XmlElement(name="cust_title")
	private String cust_title;

	@XmlElement(name="sub_cust_type")
	private Short sub_cust_type;

	@XmlElement(name="family_name")
	private String family_name;

	@XmlElement(name="first_name")
	private String first_name;

	@XmlElement(name="middle_name")
	private String middle_name;

	@XmlElement(name="country_id")
	private Short country_id;

	@XmlElement(name="prov_code")
	private Short prov_code;

	@XmlElement(name="region_code")
	private Short region_code;

	@XmlElement(name="cust_rank")
	private Short cust_rank;

	@XmlElement(name="cust_segment")
	private Short cust_segment;

	@XmlElement(name="birthday")
	private String birthday;

	@XmlElement(name="gender")
	private Short gender;

	@XmlElement(name="reg_name")
	private String reg_name;

	@XmlElement(name="reg_type")
	private Short reg_type;

	@XmlElement(name="reg_nbr")
	private String reg_nbr;

	@XmlElement(name="issue_date")
	private String issue_date;

	@XmlElement(name="language")
	private Short language;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="tax_exempt_code")
	private String tax_exempt_code;

	@XmlElement(name="vat_name")
	private String vat_name;

	@XmlElement(name="real_name_date")
	private String real_name_date;

	@XmlElement(name="real_name_sts")
	private Integer real_name_sts;

	@XmlElement(name="customer_name")
	private String customer_name;

	@XmlElement(name="ext1")
	private String ext1;

	public void setList_ext_param(ExtendParamList obj){
		this.list_ext_param = obj;
	}

	public ExtendParamList getList_ext_param(){
		return list_ext_param;
	}

	public void setSCustomerConact(List<SContact> obj){
		this.sCustomerConact = obj;
	}

	public List<SContact> getSCustomerConact(){
		return sCustomerConact;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setCust_type(Short obj){
		this.cust_type = obj;
	}

	public Short getCust_type(){
		return cust_type;
	}

	public void setCust_title(String obj){
		this.cust_title = obj;
	}

	public String getCust_title(){
		return cust_title;
	}

	public void setSub_cust_type(Short obj){
		this.sub_cust_type = obj;
	}

	public Short getSub_cust_type(){
		return sub_cust_type;
	}

	public void setFamily_name(String obj){
		this.family_name = obj;
	}

	public String getFamily_name(){
		return family_name;
	}

	public void setFirst_name(String obj){
		this.first_name = obj;
	}

	public String getFirst_name(){
		return first_name;
	}

	public void setMiddle_name(String obj){
		this.middle_name = obj;
	}

	public String getMiddle_name(){
		return middle_name;
	}

	public void setCountry_id(Short obj){
		this.country_id = obj;
	}

	public Short getCountry_id(){
		return country_id;
	}

	public void setProv_code(Short obj){
		this.prov_code = obj;
	}

	public Short getProv_code(){
		return prov_code;
	}

	public void setRegion_code(Short obj){
		this.region_code = obj;
	}

	public Short getRegion_code(){
		return region_code;
	}

	public void setCust_rank(Short obj){
		this.cust_rank = obj;
	}

	public Short getCust_rank(){
		return cust_rank;
	}

	public void setCust_segment(Short obj){
		this.cust_segment = obj;
	}

	public Short getCust_segment(){
		return cust_segment;
	}

	public void setBirthday(String obj){
		this.birthday = obj;
	}

	public String getBirthday(){
		return birthday;
	}

	public void setGender(Short obj){
		this.gender = obj;
	}

	public Short getGender(){
		return gender;
	}

	public void setReg_name(String obj){
		this.reg_name = obj;
	}

	public String getReg_name(){
		return reg_name;
	}

	public void setReg_type(Short obj){
		this.reg_type = obj;
	}

	public Short getReg_type(){
		return reg_type;
	}

	public void setReg_nbr(String obj){
		this.reg_nbr = obj;
	}

	public String getReg_nbr(){
		return reg_nbr;
	}

	public void setIssue_date(String obj){
		this.issue_date = obj;
	}

	public String getIssue_date(){
		return issue_date;
	}

	public void setLanguage(Short obj){
		this.language = obj;
	}

	public Short getLanguage(){
		return language;
	}

	public void setCreate_date(String obj){
		this.create_date = obj;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setValid_date(String obj){
		this.valid_date = obj;
	}

	public String getValid_date(){
		return valid_date;
	}

	public void setTax_exempt_code(String obj){
		this.tax_exempt_code = obj;
	}

	public String getTax_exempt_code(){
		return tax_exempt_code;
	}

	public void setVat_name(String obj){
		this.vat_name = obj;
	}

	public String getVat_name(){
		return vat_name;
	}

	public void setReal_name_date(String obj){
		this.real_name_date = obj;
	}

	public String getReal_name_date(){
		return real_name_date;
	}

	public void setReal_name_sts(Integer obj){
		this.real_name_sts = obj;
	}

	public Integer getReal_name_sts(){
		return real_name_sts;
	}

	public void setCustomer_name(String obj){
		this.customer_name = obj;
	}

	public String getCustomer_name(){
		return customer_name;
	}

	public void setExt1(String obj){
		this.ext1 = obj;
	}

	public String getExt1(){
		return ext1;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CustomerRet rhs=(CustomerRet)rhs0;
		if(!ObjectUtils.equals(list_ext_param, rhs.list_ext_param)) return false;
		if(!ObjectUtils.equals(sCustomerConact, rhs.sCustomerConact)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(cust_type, rhs.cust_type)) return false;
		if(!ObjectUtils.equals(cust_title, rhs.cust_title)) return false;
		if(!ObjectUtils.equals(sub_cust_type, rhs.sub_cust_type)) return false;
		if(!ObjectUtils.equals(family_name, rhs.family_name)) return false;
		if(!ObjectUtils.equals(first_name, rhs.first_name)) return false;
		if(!ObjectUtils.equals(middle_name, rhs.middle_name)) return false;
		if(!ObjectUtils.equals(country_id, rhs.country_id)) return false;
		if(!ObjectUtils.equals(prov_code, rhs.prov_code)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(cust_rank, rhs.cust_rank)) return false;
		if(!ObjectUtils.equals(cust_segment, rhs.cust_segment)) return false;
		if(!ObjectUtils.equals(birthday, rhs.birthday)) return false;
		if(!ObjectUtils.equals(gender, rhs.gender)) return false;
		if(!ObjectUtils.equals(reg_name, rhs.reg_name)) return false;
		if(!ObjectUtils.equals(reg_type, rhs.reg_type)) return false;
		if(!ObjectUtils.equals(reg_nbr, rhs.reg_nbr)) return false;
		if(!ObjectUtils.equals(issue_date, rhs.issue_date)) return false;
		if(!ObjectUtils.equals(language, rhs.language)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(tax_exempt_code, rhs.tax_exempt_code)) return false;
		if(!ObjectUtils.equals(vat_name, rhs.vat_name)) return false;
		if(!ObjectUtils.equals(real_name_date, rhs.real_name_date)) return false;
		if(!ObjectUtils.equals(real_name_sts, rhs.real_name_sts)) return false;
		if(!ObjectUtils.equals(customer_name, rhs.customer_name)) return false;
		if(!ObjectUtils.equals(ext1, rhs.ext1)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(list_ext_param)
		.append(sCustomerConact)
		.append(cust_id)
		.append(cust_type)
		.append(cust_title)
		.append(sub_cust_type)
		.append(family_name)
		.append(first_name)
		.append(middle_name)
		.append(country_id)
		.append(prov_code)
		.append(region_code)
		.append(cust_rank)
		.append(cust_segment)
		.append(birthday)
		.append(gender)
		.append(reg_name)
		.append(reg_type)
		.append(reg_nbr)
		.append(issue_date)
		.append(language)
		.append(create_date)
		.append(valid_date)
		.append(tax_exempt_code)
		.append(vat_name)
		.append(real_name_date)
		.append(real_name_sts)
		.append(customer_name)
		.append(ext1)
		.toHashCode();
	}


}