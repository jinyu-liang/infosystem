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
@XmlType(propOrder={"phone_id","short_phone_id","role","valid_date","expire_date","number_type","clip_flag","display_number","espace_flag","status","inGroupOutgoingCall","inGroupIncomingCall","crossGroupOutgoingCall","crossGroupIncomingCall","specOutgoingCall","specIncomingCall","outGroupOutgingCall","outGroupIncomingCall"})
public class SMember implements IComplexEntity{


	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="short_phone_id")
	private String short_phone_id;

	@XmlElement(name="role")
	private Integer role;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="number_type")
	private Integer number_type;

	@XmlElement(name="clip_flag")
	private Integer clip_flag;

	@XmlElement(name="display_number")
	private String display_number;

	@XmlElement(name="espace_flag")
	private Integer espace_flag;

	@XmlElement(name="status")
	private Integer status;

	@XmlElement(name="inGroupOutgoingCall")
	private Integer inGroupOutgoingCall;

	@XmlElement(name="inGroupIncomingCall")
	private Integer inGroupIncomingCall;

	@XmlElement(name="crossGroupOutgoingCall")
	private Integer crossGroupOutgoingCall;

	@XmlElement(name="crossGroupIncomingCall")
	private Integer crossGroupIncomingCall;

	@XmlElement(name="specOutgoingCall")
	private Integer specOutgoingCall;

	@XmlElement(name="specIncomingCall")
	private Integer specIncomingCall;

	@XmlElement(name="outGroupOutgingCall")
	private Integer outGroupOutgingCall;

	@XmlElement(name="outGroupIncomingCall")
	private Integer outGroupIncomingCall;

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setShort_phone_id(String obj){
		this.short_phone_id = obj;
	}

	public String getShort_phone_id(){
		return short_phone_id;
	}

	public void setRole(Integer obj){
		this.role = obj;
	}

	public Integer getRole(){
		return role;
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

	public void setNumber_type(Integer obj){
		this.number_type = obj;
	}

	public Integer getNumber_type(){
		return number_type;
	}

	public void setClip_flag(Integer obj){
		this.clip_flag = obj;
	}

	public Integer getClip_flag(){
		return clip_flag;
	}

	public void setDisplay_number(String obj){
		this.display_number = obj;
	}

	public String getDisplay_number(){
		return display_number;
	}

	public void setEspace_flag(Integer obj){
		this.espace_flag = obj;
	}

	public Integer getEspace_flag(){
		return espace_flag;
	}

	public void setStatus(Integer obj){
		this.status = obj;
	}

	public Integer getStatus(){
		return status;
	}

	public void setInGroupOutgoingCall(Integer obj){
		this.inGroupOutgoingCall = obj;
	}

	public Integer getInGroupOutgoingCall(){
		return inGroupOutgoingCall;
	}

	public void setInGroupIncomingCall(Integer obj){
		this.inGroupIncomingCall = obj;
	}

	public Integer getInGroupIncomingCall(){
		return inGroupIncomingCall;
	}

	public void setCrossGroupOutgoingCall(Integer obj){
		this.crossGroupOutgoingCall = obj;
	}

	public Integer getCrossGroupOutgoingCall(){
		return crossGroupOutgoingCall;
	}

	public void setCrossGroupIncomingCall(Integer obj){
		this.crossGroupIncomingCall = obj;
	}

	public Integer getCrossGroupIncomingCall(){
		return crossGroupIncomingCall;
	}

	public void setSpecOutgoingCall(Integer obj){
		this.specOutgoingCall = obj;
	}

	public Integer getSpecOutgoingCall(){
		return specOutgoingCall;
	}

	public void setSpecIncomingCall(Integer obj){
		this.specIncomingCall = obj;
	}

	public Integer getSpecIncomingCall(){
		return specIncomingCall;
	}

	public void setOutGroupOutgingCall(Integer obj){
		this.outGroupOutgingCall = obj;
	}

	public Integer getOutGroupOutgingCall(){
		return outGroupOutgingCall;
	}

	public void setOutGroupIncomingCall(Integer obj){
		this.outGroupIncomingCall = obj;
	}

	public Integer getOutGroupIncomingCall(){
		return outGroupIncomingCall;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SMember rhs=(SMember)rhs0;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(short_phone_id, rhs.short_phone_id)) return false;
		if(!ObjectUtils.equals(role, rhs.role)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(number_type, rhs.number_type)) return false;
		if(!ObjectUtils.equals(clip_flag, rhs.clip_flag)) return false;
		if(!ObjectUtils.equals(display_number, rhs.display_number)) return false;
		if(!ObjectUtils.equals(espace_flag, rhs.espace_flag)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(inGroupOutgoingCall, rhs.inGroupOutgoingCall)) return false;
		if(!ObjectUtils.equals(inGroupIncomingCall, rhs.inGroupIncomingCall)) return false;
		if(!ObjectUtils.equals(crossGroupOutgoingCall, rhs.crossGroupOutgoingCall)) return false;
		if(!ObjectUtils.equals(crossGroupIncomingCall, rhs.crossGroupIncomingCall)) return false;
		if(!ObjectUtils.equals(specOutgoingCall, rhs.specOutgoingCall)) return false;
		if(!ObjectUtils.equals(specIncomingCall, rhs.specIncomingCall)) return false;
		if(!ObjectUtils.equals(outGroupOutgingCall, rhs.outGroupOutgingCall)) return false;
		if(!ObjectUtils.equals(outGroupIncomingCall, rhs.outGroupIncomingCall)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(phone_id)
		.append(short_phone_id)
		.append(role)
		.append(valid_date)
		.append(expire_date)
		.append(number_type)
		.append(clip_flag)
		.append(display_number)
		.append(espace_flag)
		.append(status)
		.append(inGroupOutgoingCall)
		.append(inGroupIncomingCall)
		.append(crossGroupOutgoingCall)
		.append(crossGroupIncomingCall)
		.append(specOutgoingCall)
		.append(specIncomingCall)
		.append(outGroupOutgingCall)
		.append(outGroupIncomingCall)
		.toHashCode();
	}


}