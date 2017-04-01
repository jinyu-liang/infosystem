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
@XmlType(propOrder={"user_id","phone_id","hPLMN","nationalRoaming","internationalRoaming","roamingAuthority","iRSMSAuthority","interIntraAuthority","interIntraflag","iRPromptflag","fraudState","fraudTimes"})
public class SUserAuth implements IComplexEntity{


	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="hPLMN")
	private Short hPLMN;

	@XmlElement(name="nationalRoaming")
	private Short nationalRoaming;

	@XmlElement(name="internationalRoaming")
	private Short internationalRoaming;

	@XmlElement(name="roamingAuthority")
	private Short roamingAuthority;

	@XmlElement(name="iRSMSAuthority")
	private Short iRSMSAuthority;

	@XmlElement(name="interIntraAuthority")
	private Short interIntraAuthority;

	@XmlElement(name="interIntraflag")
	private Short interIntraflag;

	@XmlElement(name="iRPromptflag")
	private Short iRPromptflag;

	@XmlElement(name="fraudState")
	private Short fraudState;

	@XmlElement(name="fraudTimes")
	private Short fraudTimes;

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setHPLMN(Short obj){
		this.hPLMN = obj;
	}

	public Short getHPLMN(){
		return hPLMN;
	}

	public void setNationalRoaming(Short obj){
		this.nationalRoaming = obj;
	}

	public Short getNationalRoaming(){
		return nationalRoaming;
	}

	public void setInternationalRoaming(Short obj){
		this.internationalRoaming = obj;
	}

	public Short getInternationalRoaming(){
		return internationalRoaming;
	}

	public void setRoamingAuthority(Short obj){
		this.roamingAuthority = obj;
	}

	public Short getRoamingAuthority(){
		return roamingAuthority;
	}

	public void setIRSMSAuthority(Short obj){
		this.iRSMSAuthority = obj;
	}

	public Short getIRSMSAuthority(){
		return iRSMSAuthority;
	}

	public void setInterIntraAuthority(Short obj){
		this.interIntraAuthority = obj;
	}

	public Short getInterIntraAuthority(){
		return interIntraAuthority;
	}

	public void setInterIntraflag(Short obj){
		this.interIntraflag = obj;
	}

	public Short getInterIntraflag(){
		return interIntraflag;
	}

	public void setIRPromptflag(Short obj){
		this.iRPromptflag = obj;
	}

	public Short getIRPromptflag(){
		return iRPromptflag;
	}

	public void setFraudState(Short obj){
		this.fraudState = obj;
	}

	public Short getFraudState(){
		return fraudState;
	}

	public void setFraudTimes(Short obj){
		this.fraudTimes = obj;
	}

	public Short getFraudTimes(){
		return fraudTimes;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SUserAuth rhs=(SUserAuth)rhs0;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(hPLMN, rhs.hPLMN)) return false;
		if(!ObjectUtils.equals(nationalRoaming, rhs.nationalRoaming)) return false;
		if(!ObjectUtils.equals(internationalRoaming, rhs.internationalRoaming)) return false;
		if(!ObjectUtils.equals(roamingAuthority, rhs.roamingAuthority)) return false;
		if(!ObjectUtils.equals(iRSMSAuthority, rhs.iRSMSAuthority)) return false;
		if(!ObjectUtils.equals(interIntraAuthority, rhs.interIntraAuthority)) return false;
		if(!ObjectUtils.equals(interIntraflag, rhs.interIntraflag)) return false;
		if(!ObjectUtils.equals(iRPromptflag, rhs.iRPromptflag)) return false;
		if(!ObjectUtils.equals(fraudState, rhs.fraudState)) return false;
		if(!ObjectUtils.equals(fraudTimes, rhs.fraudTimes)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(user_id)
		.append(phone_id)
		.append(hPLMN)
		.append(nationalRoaming)
		.append(internationalRoaming)
		.append(roamingAuthority)
		.append(iRSMSAuthority)
		.append(interIntraAuthority)
		.append(interIntraflag)
		.append(iRPromptflag)
		.append(fraudState)
		.append(fraudTimes)
		.toHashCode();
	}


}