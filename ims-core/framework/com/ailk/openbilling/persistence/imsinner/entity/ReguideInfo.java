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
@XmlType(propOrder={"acct_id","user_id","busi_service_id","time_segment","reguide_type","pay_flag","pay_acct_id","pay_user_id","pay_phone_id","priority","part_type","part_value","max_value","measure_id","b_number","create_date","valid_date","expire_date","threshold","notify_type","notify_phone_id","notify_addr"})
public class ReguideInfo implements IComplexEntity{


	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="busi_service_id")
	private Long busi_service_id;

	@XmlElement(name="time_segment")
	private String time_segment;

	@XmlElement(name="reguide_type")
	private Short reguide_type;

	@XmlElement(name="pay_flag")
	private Short pay_flag;

	@XmlElement(name="pay_acct_id")
	private Long pay_acct_id;

	@XmlElement(name="pay_user_id")
	private Long pay_user_id;

	@XmlElement(name="pay_phone_id")
	private String pay_phone_id;

	@XmlElement(name="priority")
	private Short priority;

	@XmlElement(name="part_type")
	private Short part_type;

	@XmlElement(name="part_value")
	private Double part_value;

	@XmlElement(name="max_value")
	private Double max_value;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	@XmlElement(name="b_number")
	private String b_number;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="threshold")
	private Double threshold;

	@XmlElement(name="notify_type")
	private Short notify_type;

	@XmlElement(name="notify_phone_id")
	private String notify_phone_id;

	@XmlElement(name="notify_addr")
	private String notify_addr;

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setBusi_service_id(Long obj){
		this.busi_service_id = obj;
	}

	public Long getBusi_service_id(){
		return busi_service_id;
	}

	public void setTime_segment(String obj){
		this.time_segment = obj;
	}

	public String getTime_segment(){
		return time_segment;
	}

	public void setReguide_type(Short obj){
		this.reguide_type = obj;
	}

	public Short getReguide_type(){
		return reguide_type;
	}

	public void setPay_flag(Short obj){
		this.pay_flag = obj;
	}

	public Short getPay_flag(){
		return pay_flag;
	}

	public void setPay_acct_id(Long obj){
		this.pay_acct_id = obj;
	}

	public Long getPay_acct_id(){
		return pay_acct_id;
	}

	public void setPay_user_id(Long obj){
		this.pay_user_id = obj;
	}

	public Long getPay_user_id(){
		return pay_user_id;
	}

	public void setPay_phone_id(String obj){
		this.pay_phone_id = obj;
	}

	public String getPay_phone_id(){
		return pay_phone_id;
	}

	public void setPriority(Short obj){
		this.priority = obj;
	}

	public Short getPriority(){
		return priority;
	}

	public void setPart_type(Short obj){
		this.part_type = obj;
	}

	public Short getPart_type(){
		return part_type;
	}

	public void setPart_value(Double obj){
		this.part_value = obj;
	}

	public Double getPart_value(){
		return part_value;
	}

	public void setMax_value(Double obj){
		this.max_value = obj;
	}

	public Double getMax_value(){
		return max_value;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public void setB_number(String obj){
		this.b_number = obj;
	}

	public String getB_number(){
		return b_number;
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

	public void setThreshold(Double obj){
		this.threshold = obj;
	}

	public Double getThreshold(){
		return threshold;
	}

	public void setNotify_type(Short obj){
		this.notify_type = obj;
	}

	public Short getNotify_type(){
		return notify_type;
	}

	public void setNotify_phone_id(String obj){
		this.notify_phone_id = obj;
	}

	public String getNotify_phone_id(){
		return notify_phone_id;
	}

	public void setNotify_addr(String obj){
		this.notify_addr = obj;
	}

	public String getNotify_addr(){
		return notify_addr;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		ReguideInfo rhs=(ReguideInfo)rhs0;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(busi_service_id, rhs.busi_service_id)) return false;
		if(!ObjectUtils.equals(time_segment, rhs.time_segment)) return false;
		if(!ObjectUtils.equals(reguide_type, rhs.reguide_type)) return false;
		if(!ObjectUtils.equals(pay_flag, rhs.pay_flag)) return false;
		if(!ObjectUtils.equals(pay_acct_id, rhs.pay_acct_id)) return false;
		if(!ObjectUtils.equals(pay_user_id, rhs.pay_user_id)) return false;
		if(!ObjectUtils.equals(pay_phone_id, rhs.pay_phone_id)) return false;
		if(!ObjectUtils.equals(priority, rhs.priority)) return false;
		if(!ObjectUtils.equals(part_type, rhs.part_type)) return false;
		if(!ObjectUtils.equals(part_value, rhs.part_value)) return false;
		if(!ObjectUtils.equals(max_value, rhs.max_value)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		if(!ObjectUtils.equals(b_number, rhs.b_number)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(threshold, rhs.threshold)) return false;
		if(!ObjectUtils.equals(notify_type, rhs.notify_type)) return false;
		if(!ObjectUtils.equals(notify_phone_id, rhs.notify_phone_id)) return false;
		if(!ObjectUtils.equals(notify_addr, rhs.notify_addr)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acct_id)
		.append(user_id)
		.append(busi_service_id)
		.append(time_segment)
		.append(reguide_type)
		.append(pay_flag)
		.append(pay_acct_id)
		.append(pay_user_id)
		.append(pay_phone_id)
		.append(priority)
		.append(part_type)
		.append(part_value)
		.append(max_value)
		.append(measure_id)
		.append(b_number)
		.append(create_date)
		.append(valid_date)
		.append(expire_date)
		.append(threshold)
		.append(notify_type)
		.append(notify_phone_id)
		.append(notify_addr)
		.toHashCode();
	}


}