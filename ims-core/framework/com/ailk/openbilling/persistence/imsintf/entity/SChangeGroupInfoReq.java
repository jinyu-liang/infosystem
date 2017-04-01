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
@XmlType(propOrder={"outer_group_id","group_id","max_group_number","main_phone_id","valid_date","expire_date","outer_billable_acct_id","billable_acct_id","contact_phone","clip_flag","outgoing_cs_flag","ocs_routing_flag","espace_flag","inGroupOutgoingCall","inGroupIncomingCall","crossGroupOutgoingCall","crossGroupIncomingCall","specOutgoingCall","specIncomingCall","outGroupOutgingCall","outGroupIncomingCall","group_type"})
public class SChangeGroupInfoReq implements IComplexEntity{


	@XmlElement(name="outer_group_id")
	private String outer_group_id;

	@XmlElement(name="group_id")
	private Long group_id;

	@XmlElement(name="max_group_number")
	private Integer max_group_number;

	@XmlElement(name="main_phone_id")
	private String main_phone_id;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="outer_billable_acct_id")
	private String outer_billable_acct_id;

	@XmlElement(name="billable_acct_id")
	private Long billable_acct_id;

	@XmlElement(name="contact_phone")
	private String contact_phone;

	@XmlElement(name="clip_flag")
	private Integer clip_flag;

	@XmlElement(name="outgoing_cs_flag")
	private Integer outgoing_cs_flag;

	@XmlElement(name="ocs_routing_flag")
	private Integer ocs_routing_flag;

	@XmlElement(name="espace_flag")
	private Integer espace_flag;

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

	@XmlElement(name="group_type")
	private Short group_type;

	public void setOuter_group_id(String obj){
		this.outer_group_id = obj;
	}

	public String getOuter_group_id(){
		return outer_group_id;
	}

	public void setGroup_id(Long obj){
		this.group_id = obj;
	}

	public Long getGroup_id(){
		return group_id;
	}

	public void setMax_group_number(Integer obj){
		this.max_group_number = obj;
	}

	public Integer getMax_group_number(){
		return max_group_number;
	}

	public void setMain_phone_id(String obj){
		this.main_phone_id = obj;
	}

	public String getMain_phone_id(){
		return main_phone_id;
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

	public void setContact_phone(String obj){
		this.contact_phone = obj;
	}

	public String getContact_phone(){
		return contact_phone;
	}

	public void setClip_flag(Integer obj){
		this.clip_flag = obj;
	}

	public Integer getClip_flag(){
		return clip_flag;
	}

	public void setOutgoing_cs_flag(Integer obj){
		this.outgoing_cs_flag = obj;
	}

	public Integer getOutgoing_cs_flag(){
		return outgoing_cs_flag;
	}

	public void setOcs_routing_flag(Integer obj){
		this.ocs_routing_flag = obj;
	}

	public Integer getOcs_routing_flag(){
		return ocs_routing_flag;
	}

	public void setEspace_flag(Integer obj){
		this.espace_flag = obj;
	}

	public Integer getEspace_flag(){
		return espace_flag;
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

	public void setGroup_type(Short obj){
		this.group_type = obj;
	}

	public Short getGroup_type(){
		return group_type;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SChangeGroupInfoReq rhs=(SChangeGroupInfoReq)rhs0;
		if(!ObjectUtils.equals(outer_group_id, rhs.outer_group_id)) return false;
		if(!ObjectUtils.equals(group_id, rhs.group_id)) return false;
		if(!ObjectUtils.equals(max_group_number, rhs.max_group_number)) return false;
		if(!ObjectUtils.equals(main_phone_id, rhs.main_phone_id)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(outer_billable_acct_id, rhs.outer_billable_acct_id)) return false;
		if(!ObjectUtils.equals(billable_acct_id, rhs.billable_acct_id)) return false;
		if(!ObjectUtils.equals(contact_phone, rhs.contact_phone)) return false;
		if(!ObjectUtils.equals(clip_flag, rhs.clip_flag)) return false;
		if(!ObjectUtils.equals(outgoing_cs_flag, rhs.outgoing_cs_flag)) return false;
		if(!ObjectUtils.equals(ocs_routing_flag, rhs.ocs_routing_flag)) return false;
		if(!ObjectUtils.equals(espace_flag, rhs.espace_flag)) return false;
		if(!ObjectUtils.equals(inGroupOutgoingCall, rhs.inGroupOutgoingCall)) return false;
		if(!ObjectUtils.equals(inGroupIncomingCall, rhs.inGroupIncomingCall)) return false;
		if(!ObjectUtils.equals(crossGroupOutgoingCall, rhs.crossGroupOutgoingCall)) return false;
		if(!ObjectUtils.equals(crossGroupIncomingCall, rhs.crossGroupIncomingCall)) return false;
		if(!ObjectUtils.equals(specOutgoingCall, rhs.specOutgoingCall)) return false;
		if(!ObjectUtils.equals(specIncomingCall, rhs.specIncomingCall)) return false;
		if(!ObjectUtils.equals(outGroupOutgingCall, rhs.outGroupOutgingCall)) return false;
		if(!ObjectUtils.equals(outGroupIncomingCall, rhs.outGroupIncomingCall)) return false;
		if(!ObjectUtils.equals(group_type, rhs.group_type)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(outer_group_id)
		.append(group_id)
		.append(max_group_number)
		.append(main_phone_id)
		.append(valid_date)
		.append(expire_date)
		.append(outer_billable_acct_id)
		.append(billable_acct_id)
		.append(contact_phone)
		.append(clip_flag)
		.append(outgoing_cs_flag)
		.append(ocs_routing_flag)
		.append(espace_flag)
		.append(inGroupOutgoingCall)
		.append(inGroupIncomingCall)
		.append(crossGroupOutgoingCall)
		.append(crossGroupIncomingCall)
		.append(specOutgoingCall)
		.append(specIncomingCall)
		.append(outGroupOutgingCall)
		.append(outGroupIncomingCall)
		.append(group_type)
		.toHashCode();
	}


}