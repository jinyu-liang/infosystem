package com.ailk.openbilling.persistence.imsintf.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"user_id","county_code","region_code","phone_id","company","password","imsi","outer_acct_id","acct_id","outer_billable_acct_id","billable_acct_id","outer_cust_id","cust_id","user_type","user_segment","payment_mode","brand","main_promotion","credit","measure_id","budget","status","os_status","create_date","valid_date","expire_date","device_type","iMEI","mNP_home_area","sms_language","ivr_language","ussd_language","active_date","title_role_id","is_test_number","attrUserEmail","list_ext_param","sUserLifeCycle","serial_no","flh_flag","continue_flag","suspend_stop_date","disable_stop_date","active_stop_date"})
public class SUser implements IComplexEntity{


	@XmlElement(name="list_ext_param")
	private ExtendParamList list_ext_param;

	@XmlElement(name="sUserLifeCycle")
	private SUserLifecycle sUserLifeCycle;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="county_code")
	private Short county_code;

	@XmlElement(name="region_code")
	private Short region_code;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="company")
	private String company;

	@XmlElement(name="password")
	private String password;

	@XmlElement(name="imsi")
	private String imsi;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="outer_billable_acct_id")
	private String outer_billable_acct_id;

	@XmlElement(name="billable_acct_id")
	private Long billable_acct_id;

	@XmlElement(name="outer_cust_id")
	private String outer_cust_id;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="user_type")
	private Short user_type;

	@XmlElement(name="user_segment")
	private Short user_segment;

	@XmlElement(name="payment_mode")
	private Short payment_mode;

	@XmlElement(name="brand")
	private Short brand;

	@XmlElement(name="main_promotion")
	private Integer main_promotion;

	@XmlElement(name="credit")
	private Double credit;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	@XmlElement(name="budget")
	private Double budget;

	@XmlElement(name="status")
	private Short status;

	@XmlElement(name="os_status")
	private String os_status;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="device_type")
	private String device_type;

	@XmlElement(name="iMEI")
	private String iMEI;

	@XmlElement(name="mNP_home_area")
	private String mNP_home_area;

	@XmlElement(name="sms_language")
	private Short sms_language;

	@XmlElement(name="ivr_language")
	private Short ivr_language;

	@XmlElement(name="ussd_language")
	private Short ussd_language;

	@XmlElement(name="active_date")
	private String active_date;

	@XmlElement(name="title_role_id")
	private Short title_role_id;

	@XmlElement(name="is_test_number")
	private Short is_test_number;

	@XmlElement(name="attrUserEmail")
	private String attrUserEmail;

	@XmlElement(name="serial_no")
	private String serial_no;

	@XmlElement(name="flh_flag")
	private Short flh_flag;

	@XmlElement(name="continue_flag")
	private Short continue_flag;

	@XmlElement(name="suspend_stop_date")
	private Date suspend_stop_date;

	@XmlElement(name="disable_stop_date")
	private Date disable_stop_date;

	@XmlElement(name="active_stop_date")
	private Date active_stop_date;

	public void setList_ext_param(ExtendParamList obj){
		this.list_ext_param = obj;
	}

	public ExtendParamList getList_ext_param(){
		return list_ext_param;
	}

	public void setSUserLifeCycle(SUserLifecycle obj){
		this.sUserLifeCycle = obj;
	}

	public SUserLifecycle getSUserLifeCycle(){
		return sUserLifeCycle;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setCounty_code(Short obj){
		this.county_code = obj;
	}

	public Short getCounty_code(){
		return county_code;
	}

	public void setRegion_code(Short obj){
		this.region_code = obj;
	}

	public Short getRegion_code(){
		return region_code;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setCompany(String obj){
		this.company = obj;
	}

	public String getCompany(){
		return company;
	}

	public void setPassword(String obj){
		this.password = obj;
	}

	public String getPassword(){
		return password;
	}

	public void setImsi(String obj){
		this.imsi = obj;
	}

	public String getImsi(){
		return imsi;
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

	public void setOuter_billable_acct_id(String obj){
		this.outer_billable_acct_id = obj;
	}

	public String getOuter_billable_acct_id(){
		return outer_billable_acct_id;
	}

	public void setBillable_acct_id(Long obj){
		this.billable_acct_id = obj;
	}

	public Long getBillable_acct_id(){
		return billable_acct_id;
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

	public void setUser_type(Short obj){
		this.user_type = obj;
	}

	public Short getUser_type(){
		return user_type;
	}

	public void setUser_segment(Short obj){
		this.user_segment = obj;
	}

	public Short getUser_segment(){
		return user_segment;
	}

	public void setPayment_mode(Short obj){
		this.payment_mode = obj;
	}

	public Short getPayment_mode(){
		return payment_mode;
	}

	public void setBrand(Short obj){
		this.brand = obj;
	}

	public Short getBrand(){
		return brand;
	}

	public void setMain_promotion(Integer obj){
		this.main_promotion = obj;
	}

	public Integer getMain_promotion(){
		return main_promotion;
	}

	public void setCredit(Double obj){
		this.credit = obj;
	}

	public Double getCredit(){
		return credit;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public void setBudget(Double obj){
		this.budget = obj;
	}

	public Double getBudget(){
		return budget;
	}

	public void setStatus(Short obj){
		this.status = obj;
	}

	public Short getStatus(){
		return status;
	}

	public void setOs_status(String obj){
		this.os_status = obj;
	}

	public String getOs_status(){
		return os_status;
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

	public void setExpire_date(String obj){
		this.expire_date = obj;
	}

	public String getExpire_date(){
		return expire_date;
	}

	public void setDevice_type(String obj){
		this.device_type = obj;
	}

	public String getDevice_type(){
		return device_type;
	}

	public void setIMEI(String obj){
		this.iMEI = obj;
	}

	public String getIMEI(){
		return iMEI;
	}

	public void setMNP_home_area(String obj){
		this.mNP_home_area = obj;
	}

	public String getMNP_home_area(){
		return mNP_home_area;
	}

	public void setSms_language(Short obj){
		this.sms_language = obj;
	}

	public Short getSms_language(){
		return sms_language;
	}

	public void setIvr_language(Short obj){
		this.ivr_language = obj;
	}

	public Short getIvr_language(){
		return ivr_language;
	}

	public void setUssd_language(Short obj){
		this.ussd_language = obj;
	}

	public Short getUssd_language(){
		return ussd_language;
	}

	public void setActive_date(String obj){
		this.active_date = obj;
	}

	public String getActive_date(){
		return active_date;
	}

	public void setTitle_role_id(Short obj){
		this.title_role_id = obj;
	}

	public Short getTitle_role_id(){
		return title_role_id;
	}

	public void setIs_test_number(Short obj){
		this.is_test_number = obj;
	}

	public Short getIs_test_number(){
		return is_test_number;
	}

	public void setAttrUserEmail(String obj){
		this.attrUserEmail = obj;
	}

	public String getAttrUserEmail(){
		return attrUserEmail;
	}

	public void setSerial_no(String obj){
		this.serial_no = obj;
	}

	public String getSerial_no(){
		return serial_no;
	}

	public void setFlh_flag(Short obj){
		this.flh_flag = obj;
	}

	public Short getFlh_flag(){
		return flh_flag;
	}

	public void setContinue_flag(Short obj){
		this.continue_flag = obj;
	}

	public Short getContinue_flag(){
		return continue_flag;
	}

	public void setSuspend_stop_date(Date obj){
		this.suspend_stop_date = obj;
	}

	public Date getSuspend_stop_date(){
		return suspend_stop_date;
	}

	public void setDisable_stop_date(Date obj){
		this.disable_stop_date = obj;
	}

	public Date getDisable_stop_date(){
		return disable_stop_date;
	}

	public void setActive_stop_date(Date obj){
		this.active_stop_date = obj;
	}

	public Date getActive_stop_date(){
		return active_stop_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUser rhs=(SUser)rhs0;
		if(!ObjectUtils.equals(list_ext_param, rhs.list_ext_param)) return false;
		if(!ObjectUtils.equals(sUserLifeCycle, rhs.sUserLifeCycle)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(county_code, rhs.county_code)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(company, rhs.company)) return false;
		if(!ObjectUtils.equals(password, rhs.password)) return false;
		if(!ObjectUtils.equals(imsi, rhs.imsi)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(outer_billable_acct_id, rhs.outer_billable_acct_id)) return false;
		if(!ObjectUtils.equals(billable_acct_id, rhs.billable_acct_id)) return false;
		if(!ObjectUtils.equals(outer_cust_id, rhs.outer_cust_id)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(user_type, rhs.user_type)) return false;
		if(!ObjectUtils.equals(user_segment, rhs.user_segment)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(brand, rhs.brand)) return false;
		if(!ObjectUtils.equals(main_promotion, rhs.main_promotion)) return false;
		if(!ObjectUtils.equals(credit, rhs.credit)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		if(!ObjectUtils.equals(budget, rhs.budget)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(os_status, rhs.os_status)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(device_type, rhs.device_type)) return false;
		if(!ObjectUtils.equals(iMEI, rhs.iMEI)) return false;
		if(!ObjectUtils.equals(mNP_home_area, rhs.mNP_home_area)) return false;
		if(!ObjectUtils.equals(sms_language, rhs.sms_language)) return false;
		if(!ObjectUtils.equals(ivr_language, rhs.ivr_language)) return false;
		if(!ObjectUtils.equals(ussd_language, rhs.ussd_language)) return false;
		if(!ObjectUtils.equals(active_date, rhs.active_date)) return false;
		if(!ObjectUtils.equals(title_role_id, rhs.title_role_id)) return false;
		if(!ObjectUtils.equals(is_test_number, rhs.is_test_number)) return false;
		if(!ObjectUtils.equals(attrUserEmail, rhs.attrUserEmail)) return false;
		if(!ObjectUtils.equals(serial_no, rhs.serial_no)) return false;
		if(!ObjectUtils.equals(flh_flag, rhs.flh_flag)) return false;
		if(!ObjectUtils.equals(continue_flag, rhs.continue_flag)) return false;
		if(!ObjectUtils.equals(suspend_stop_date, rhs.suspend_stop_date)) return false;
		if(!ObjectUtils.equals(disable_stop_date, rhs.disable_stop_date)) return false;
		if(!ObjectUtils.equals(active_stop_date, rhs.active_stop_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(list_ext_param)
		.append(sUserLifeCycle)
		.append(user_id)
		.append(county_code)
		.append(region_code)
		.append(phone_id)
		.append(company)
		.append(password)
		.append(imsi)
		.append(outer_acct_id)
		.append(acct_id)
		.append(outer_billable_acct_id)
		.append(billable_acct_id)
		.append(outer_cust_id)
		.append(cust_id)
		.append(user_type)
		.append(user_segment)
		.append(payment_mode)
		.append(brand)
		.append(main_promotion)
		.append(credit)
		.append(measure_id)
		.append(budget)
		.append(status)
		.append(os_status)
		.append(create_date)
		.append(valid_date)
		.append(expire_date)
		.append(device_type)
		.append(iMEI)
		.append(mNP_home_area)
		.append(sms_language)
		.append(ivr_language)
		.append(ussd_language)
		.append(active_date)
		.append(title_role_id)
		.append(is_test_number)
		.append(attrUserEmail)
		.append(serial_no)
		.append(flh_flag)
		.append(continue_flag)
		.append(suspend_stop_date)
		.append(disable_stop_date)
		.append(active_stop_date)
		.toHashCode();
	}


}