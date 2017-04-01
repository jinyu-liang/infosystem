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
@XmlType(propOrder={"oper_type","operator_code","operator_type","operator_id","mcc_code","mnc_code","english_name","invoicingCoID","country_code","country_name","tap_version","tap_release","time_zone","currency_code","network_statement","serviceRequestOper"})
public class SManagerIRPartyReq implements IComplexEntity{


	@XmlElement(name="serviceRequestOper")
	private ServiceRequestOper serviceRequestOper;

	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="operator_code")
	private String operator_code;

	@XmlElement(name="operator_type")
	private Short operator_type;

	@XmlElement(name="operator_id")
	private Integer operator_id;

	@XmlElement(name="mcc_code")
	private String mcc_code;

	@XmlElement(name="mnc_code")
	private String mnc_code;

	@XmlElement(name="english_name")
	private String english_name;

	@XmlElement(name="invoicingCoID")
	private String invoicingCoID;

	@XmlElement(name="country_code")
	private Short country_code;

	@XmlElement(name="country_name")
	private String country_name;

	@XmlElement(name="tap_version")
	private Short tap_version;

	@XmlElement(name="tap_release")
	private String tap_release;

	@XmlElement(name="time_zone")
	private Integer time_zone;

	@XmlElement(name="currency_code")
	private Integer currency_code;

	@XmlElement(name="network_statement")
	private String network_statement;

	public void setServiceRequestOper(ServiceRequestOper obj){
		this.serviceRequestOper = obj;
	}

	public ServiceRequestOper getServiceRequestOper(){
		return serviceRequestOper;
	}

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setOperator_code(String obj){
		this.operator_code = obj;
	}

	public String getOperator_code(){
		return operator_code;
	}

	public void setOperator_type(Short obj){
		this.operator_type = obj;
	}

	public Short getOperator_type(){
		return operator_type;
	}

	public void setOperator_id(Integer obj){
		this.operator_id = obj;
	}

	public Integer getOperator_id(){
		return operator_id;
	}

	public void setMcc_code(String obj){
		this.mcc_code = obj;
	}

	public String getMcc_code(){
		return mcc_code;
	}

	public void setMnc_code(String obj){
		this.mnc_code = obj;
	}

	public String getMnc_code(){
		return mnc_code;
	}

	public void setEnglish_name(String obj){
		this.english_name = obj;
	}

	public String getEnglish_name(){
		return english_name;
	}

	public void setInvoicingCoID(String obj){
		this.invoicingCoID = obj;
	}

	public String getInvoicingCoID(){
		return invoicingCoID;
	}

	public void setCountry_code(Short obj){
		this.country_code = obj;
	}

	public Short getCountry_code(){
		return country_code;
	}

	public void setCountry_name(String obj){
		this.country_name = obj;
	}

	public String getCountry_name(){
		return country_name;
	}

	public void setTap_version(Short obj){
		this.tap_version = obj;
	}

	public Short getTap_version(){
		return tap_version;
	}

	public void setTap_release(String obj){
		this.tap_release = obj;
	}

	public String getTap_release(){
		return tap_release;
	}

	public void setTime_zone(Integer obj){
		this.time_zone = obj;
	}

	public Integer getTime_zone(){
		return time_zone;
	}

	public void setCurrency_code(Integer obj){
		this.currency_code = obj;
	}

	public Integer getCurrency_code(){
		return currency_code;
	}

	public void setNetwork_statement(String obj){
		this.network_statement = obj;
	}

	public String getNetwork_statement(){
		return network_statement;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SManagerIRPartyReq rhs=(SManagerIRPartyReq)rhs0;
		if(!ObjectUtils.equals(serviceRequestOper, rhs.serviceRequestOper)) return false;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(operator_code, rhs.operator_code)) return false;
		if(!ObjectUtils.equals(operator_type, rhs.operator_type)) return false;
		if(!ObjectUtils.equals(operator_id, rhs.operator_id)) return false;
		if(!ObjectUtils.equals(mcc_code, rhs.mcc_code)) return false;
		if(!ObjectUtils.equals(mnc_code, rhs.mnc_code)) return false;
		if(!ObjectUtils.equals(english_name, rhs.english_name)) return false;
		if(!ObjectUtils.equals(invoicingCoID, rhs.invoicingCoID)) return false;
		if(!ObjectUtils.equals(country_code, rhs.country_code)) return false;
		if(!ObjectUtils.equals(country_name, rhs.country_name)) return false;
		if(!ObjectUtils.equals(tap_version, rhs.tap_version)) return false;
		if(!ObjectUtils.equals(tap_release, rhs.tap_release)) return false;
		if(!ObjectUtils.equals(time_zone, rhs.time_zone)) return false;
		if(!ObjectUtils.equals(currency_code, rhs.currency_code)) return false;
		if(!ObjectUtils.equals(network_statement, rhs.network_statement)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(serviceRequestOper)
		.append(oper_type)
		.append(operator_code)
		.append(operator_type)
		.append(operator_id)
		.append(mcc_code)
		.append(mnc_code)
		.append(english_name)
		.append(invoicingCoID)
		.append(country_code)
		.append(country_name)
		.append(tap_version)
		.append(tap_release)
		.append(time_zone)
		.append(currency_code)
		.append(network_statement)
		.toHashCode();
	}


}