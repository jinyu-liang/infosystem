package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.imsintf.entity.SUserLifecycle;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"user_id","county_code","region_code","phone_id","password","imsi","billable_acct_id","user_type","user_segment","payment_mode","brand","main_promotion","credit","measure_id","budget","status","os_status","create_date","device_type","iMEI","mNP_home_area","sms_language","ivr_language","ussd_language","active_date","title_role_id","main_prod","main_prod_state","in_net_days","os_sts_dtl","out_service_date","user_validity","sUserLifecycle"})
public class UserRet extends AccountRet implements IComplexEntity{


	@XmlElement(name="user_validity")
	private CmResValidity user_validity;

	@XmlElement(name="sUserLifecycle")
	private SUserLifecycle sUserLifecycle;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="county_code")
	private Short county_code;

	@XmlElement(name="region_code")
	private Short region_code;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="password")
	private String password;

	@XmlElement(name="imsi")
	private String imsi;

	@XmlElement(name="billable_acct_id")
	private Long billable_acct_id;

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
	private Integer os_status;

	@XmlElement(name="create_date")
	private String create_date;

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

	@XmlElement(name="main_prod")
	private Long main_prod;

	@XmlElement(name="main_prod_state")
	private Short main_prod_state;

	@XmlElement(name="in_net_days")
	private Long in_net_days;

	@XmlElement(name="os_sts_dtl")
	private Integer os_sts_dtl;

	@XmlElement(name="out_service_date")
	private String out_service_date;

	public void setUser_validity(CmResValidity obj){
		this.user_validity = obj;
	}

	public CmResValidity getUser_validity(){
		return user_validity;
	}

	public void setSUserLifecycle(SUserLifecycle obj){
		this.sUserLifecycle = obj;
	}

	public SUserLifecycle getSUserLifecycle(){
		return sUserLifecycle;
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

	public void setBillable_acct_id(Long obj){
		this.billable_acct_id = obj;
	}

	public Long getBillable_acct_id(){
		return billable_acct_id;
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

	public void setOs_status(Integer obj){
		this.os_status = obj;
	}

	public Integer getOs_status(){
		return os_status;
	}

	public void setCreate_date(String obj){
		this.create_date = obj;
	}

	public String getCreate_date(){
		return create_date;
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

	public void setMain_prod(Long obj){
		this.main_prod = obj;
	}

	public Long getMain_prod(){
		return main_prod;
	}

	public void setMain_prod_state(Short obj){
		this.main_prod_state = obj;
	}

	public Short getMain_prod_state(){
		return main_prod_state;
	}

	public void setIn_net_days(Long obj){
		this.in_net_days = obj;
	}

	public Long getIn_net_days(){
		return in_net_days;
	}

	public void setOs_sts_dtl(Integer obj){
		this.os_sts_dtl = obj;
	}

	public Integer getOs_sts_dtl(){
		return os_sts_dtl;
	}

	public void setOut_service_date(String obj){
		this.out_service_date = obj;
	}

	public String getOut_service_date(){
		return out_service_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		UserRet rhs=(UserRet)rhs0;
		if(!ObjectUtils.equals(user_validity, rhs.user_validity)) return false;
		if(!ObjectUtils.equals(sUserLifecycle, rhs.sUserLifecycle)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(county_code, rhs.county_code)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(password, rhs.password)) return false;
		if(!ObjectUtils.equals(imsi, rhs.imsi)) return false;
		if(!ObjectUtils.equals(billable_acct_id, rhs.billable_acct_id)) return false;
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
		if(!ObjectUtils.equals(device_type, rhs.device_type)) return false;
		if(!ObjectUtils.equals(iMEI, rhs.iMEI)) return false;
		if(!ObjectUtils.equals(mNP_home_area, rhs.mNP_home_area)) return false;
		if(!ObjectUtils.equals(sms_language, rhs.sms_language)) return false;
		if(!ObjectUtils.equals(ivr_language, rhs.ivr_language)) return false;
		if(!ObjectUtils.equals(ussd_language, rhs.ussd_language)) return false;
		if(!ObjectUtils.equals(active_date, rhs.active_date)) return false;
		if(!ObjectUtils.equals(title_role_id, rhs.title_role_id)) return false;
		if(!ObjectUtils.equals(main_prod, rhs.main_prod)) return false;
		if(!ObjectUtils.equals(main_prod_state, rhs.main_prod_state)) return false;
		if(!ObjectUtils.equals(in_net_days, rhs.in_net_days)) return false;
		if(!ObjectUtils.equals(os_sts_dtl, rhs.os_sts_dtl)) return false;
		if(!ObjectUtils.equals(out_service_date, rhs.out_service_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_validity)
		.append(sUserLifecycle)
		.append(user_id)
		.append(county_code)
		.append(region_code)
		.append(phone_id)
		.append(password)
		.append(imsi)
		.append(billable_acct_id)
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
		.append(device_type)
		.append(iMEI)
		.append(mNP_home_area)
		.append(sms_language)
		.append(ivr_language)
		.append(ussd_language)
		.append(active_date)
		.append(title_role_id)
		.append(main_prod)
		.append(main_prod_state)
		.append(in_net_days)
		.append(os_sts_dtl)
		.append(out_service_date)
		.toHashCode();
	}


}