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
@XmlType(propOrder={"contact_id","contact_type","outer_cust_id","cust_id","outer_acct_id","acct_id","mobile_phone","home_phone","country_id","province_code","office_phone","fax","email","post_code","address","address1","address2","address3","address4","contact_name","bill_send_type","list_ext_param","postsect","region_code","county_code"})
public class SContact implements IComplexEntity{


	@XmlElement(name="list_ext_param")
	private ExtendParamList list_ext_param;

	@XmlElement(name="contact_id")
	private Long contact_id;

	@XmlElement(name="contact_type")
	private Short contact_type;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="mobile_phone")
	private String mobile_phone;

	@XmlElement(name="home_phone")
	private String home_phone;

	@XmlElement(name="country_id")
	private Long country_id;

	@XmlElement(name="province_code")
	private String province_code;

	@XmlElement(name="office_phone")
	private String office_phone;

	@XmlElement(name="fax")
	private String fax;

	@XmlElement(name="email")
	private String email;

	@XmlElement(name="post_code")
	private String post_code;

	@XmlElement(name="address")
	private String address;

	@XmlElement(name="address1")
	private String address1;

	@XmlElement(name="address2")
	private String address2;

	@XmlElement(name="address3")
	private String address3;

	@XmlElement(name="address4")
	private String address4;

	@XmlElement(name="contact_name")
	private String contact_name;

	@XmlElement(name="bill_send_type")
	private String bill_send_type;

	@XmlElement(name="postsect")
	private String postsect;

	@XmlElement(name="region_code")
	private String region_code;

	@XmlElement(name="county_code")
	private String county_code;

	public void setList_ext_param(ExtendParamList obj){
		this.list_ext_param = obj;
	}

	public ExtendParamList getList_ext_param(){
		return list_ext_param;
	}

	public void setContact_id(Long obj){
		this.contact_id = obj;
	}

	public Long getContact_id(){
		return contact_id;
	}

	public void setContact_type(Short obj){
		this.contact_type = obj;
	}

	public Short getContact_type(){
		return contact_type;
	}

	public void setOuter_cust_id(String obj){
		this.outer_cust_id = obj;
	}

	public String getOuter_cust_id(){
		return outer_cust_id;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setMobile_phone(String obj){
		this.mobile_phone = obj;
	}

	public String getMobile_phone(){
		return mobile_phone;
	}

	public void setHome_phone(String obj){
		this.home_phone = obj;
	}

	public String getHome_phone(){
		return home_phone;
	}

	public void setCountry_id(Long obj){
		this.country_id = obj;
	}

	public Long getCountry_id(){
		return country_id;
	}

	public void setProvince_code(String obj){
		this.province_code = obj;
	}

	public String getProvince_code(){
		return province_code;
	}

	public void setOffice_phone(String obj){
		this.office_phone = obj;
	}

	public String getOffice_phone(){
		return office_phone;
	}

	public void setFax(String obj){
		this.fax = obj;
	}

	public String getFax(){
		return fax;
	}

	public void setEmail(String obj){
		this.email = obj;
	}

	public String getEmail(){
		return email;
	}

	public void setPost_code(String obj){
		this.post_code = obj;
	}

	public String getPost_code(){
		return post_code;
	}

	public void setAddress(String obj){
		this.address = obj;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress1(String obj){
		this.address1 = obj;
	}

	public String getAddress1(){
		return address1;
	}

	public void setAddress2(String obj){
		this.address2 = obj;
	}

	public String getAddress2(){
		return address2;
	}

	public void setAddress3(String obj){
		this.address3 = obj;
	}

	public String getAddress3(){
		return address3;
	}

	public void setAddress4(String obj){
		this.address4 = obj;
	}

	public String getAddress4(){
		return address4;
	}

	public void setContact_name(String obj){
		this.contact_name = obj;
	}

	public String getContact_name(){
		return contact_name;
	}

	public void setBill_send_type(String obj){
		this.bill_send_type = obj;
	}

	public String getBill_send_type(){
		return bill_send_type;
	}

	public void setPostsect(String obj){
		this.postsect = obj;
	}

	public String getPostsect(){
		return postsect;
	}

	public void setRegion_code(String obj){
		this.region_code = obj;
	}

	public String getRegion_code(){
		return region_code;
	}

	public void setCounty_code(String obj){
		this.county_code = obj;
	}

	public String getCounty_code(){
		return county_code;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SContact rhs=(SContact)rhs0;
		if(!ObjectUtils.equals(list_ext_param, rhs.list_ext_param)) return false;
		if(!ObjectUtils.equals(contact_id, rhs.contact_id)) return false;
		if(!ObjectUtils.equals(contact_type, rhs.contact_type)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(mobile_phone, rhs.mobile_phone)) return false;
		if(!ObjectUtils.equals(home_phone, rhs.home_phone)) return false;
		if(!ObjectUtils.equals(country_id, rhs.country_id)) return false;
		if(!ObjectUtils.equals(province_code, rhs.province_code)) return false;
		if(!ObjectUtils.equals(office_phone, rhs.office_phone)) return false;
		if(!ObjectUtils.equals(fax, rhs.fax)) return false;
		if(!ObjectUtils.equals(email, rhs.email)) return false;
		if(!ObjectUtils.equals(post_code, rhs.post_code)) return false;
		if(!ObjectUtils.equals(address, rhs.address)) return false;
		if(!ObjectUtils.equals(address1, rhs.address1)) return false;
		if(!ObjectUtils.equals(address2, rhs.address2)) return false;
		if(!ObjectUtils.equals(address3, rhs.address3)) return false;
		if(!ObjectUtils.equals(address4, rhs.address4)) return false;
		if(!ObjectUtils.equals(contact_name, rhs.contact_name)) return false;
		if(!ObjectUtils.equals(bill_send_type, rhs.bill_send_type)) return false;
		if(!ObjectUtils.equals(postsect, rhs.postsect)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(county_code, rhs.county_code)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(list_ext_param)
		.append(contact_id)
		.append(contact_type)
		.append(outer_cust_id)
		.append(cust_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(mobile_phone)
		.append(home_phone)
		.append(country_id)
		.append(province_code)
		.append(office_phone)
		.append(fax)
		.append(email)
		.append(post_code)
		.append(address)
		.append(address1)
		.append(address2)
		.append(address3)
		.append(address4)
		.append(contact_name)
		.append(bill_send_type)
		.append(postsect)
		.append(region_code)
		.append(county_code)
		.toHashCode();
	}


}