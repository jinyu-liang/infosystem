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
@XmlType(propOrder={"oper_type","outer_mail_group_id","mail_group_id","name","description","bill_format_id","file_type","summaryBillHandlingCode","summaryBillLanguage","summaryCurrency","summaryStyleId","valid_date","expire_date"})
public class MailGroup implements IComplexEntity{


	@XmlElement(name="oper_type")
	private Short oper_type;

	@XmlElement(name="outer_mail_group_id")
	private String outer_mail_group_id;

	@XmlElement(name="mail_group_id")
	private Long mail_group_id;

	@XmlElement(name="name")
	private String name;

	@XmlElement(name="description")
	private String description;

	@XmlElement(name="bill_format_id")
	private Short bill_format_id;

	@XmlElement(name="file_type")
	private Short file_type;

	@XmlElement(name="summaryBillHandlingCode")
	private Long summaryBillHandlingCode;

	@XmlElement(name="summaryBillLanguage")
	private Integer summaryBillLanguage;

	@XmlElement(name="summaryCurrency")
	private Integer summaryCurrency;

	@XmlElement(name="summaryStyleId")
	private Integer summaryStyleId;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	public void setOper_type(Short obj){
		this.oper_type = obj;
	}

	public Short getOper_type(){
		return oper_type;
	}

	public void setOuter_mail_group_id(String obj){
		this.outer_mail_group_id = obj;
	}

	public String getOuter_mail_group_id(){
		return outer_mail_group_id;
	}

	public void setMail_group_id(Long obj){
		this.mail_group_id = obj;
	}

	public Long getMail_group_id(){
		return mail_group_id;
	}

	public void setName(String obj){
		this.name = obj;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String obj){
		this.description = obj;
	}

	public String getDescription(){
		return description;
	}

	public void setBill_format_id(Short obj){
		this.bill_format_id = obj;
	}

	public Short getBill_format_id(){
		return bill_format_id;
	}

	public void setFile_type(Short obj){
		this.file_type = obj;
	}

	public Short getFile_type(){
		return file_type;
	}

	public void setSummaryBillHandlingCode(Long obj){
		this.summaryBillHandlingCode = obj;
	}

	public Long getSummaryBillHandlingCode(){
		return summaryBillHandlingCode;
	}

	public void setSummaryBillLanguage(Integer obj){
		this.summaryBillLanguage = obj;
	}

	public Integer getSummaryBillLanguage(){
		return summaryBillLanguage;
	}

	public void setSummaryCurrency(Integer obj){
		this.summaryCurrency = obj;
	}

	public Integer getSummaryCurrency(){
		return summaryCurrency;
	}

	public void setSummaryStyleId(Integer obj){
		this.summaryStyleId = obj;
	}

	public Integer getSummaryStyleId(){
		return summaryStyleId;
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

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		MailGroup rhs=(MailGroup)rhs0;
		if(!ObjectUtils.equals(oper_type, rhs.oper_type)) return false;
		if(!ObjectUtils.equals(outer_mail_group_id, rhs.outer_mail_group_id)) return false;
		if(!ObjectUtils.equals(mail_group_id, rhs.mail_group_id)) return false;
		if(!ObjectUtils.equals(name, rhs.name)) return false;
		if(!ObjectUtils.equals(description, rhs.description)) return false;
		if(!ObjectUtils.equals(bill_format_id, rhs.bill_format_id)) return false;
		if(!ObjectUtils.equals(file_type, rhs.file_type)) return false;
		if(!ObjectUtils.equals(summaryBillHandlingCode, rhs.summaryBillHandlingCode)) return false;
		if(!ObjectUtils.equals(summaryBillLanguage, rhs.summaryBillLanguage)) return false;
		if(!ObjectUtils.equals(summaryCurrency, rhs.summaryCurrency)) return false;
		if(!ObjectUtils.equals(summaryStyleId, rhs.summaryStyleId)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oper_type)
		.append(outer_mail_group_id)
		.append(mail_group_id)
		.append(name)
		.append(description)
		.append(bill_format_id)
		.append(file_type)
		.append(summaryBillHandlingCode)
		.append(summaryBillLanguage)
		.append(summaryCurrency)
		.append(summaryStyleId)
		.append(valid_date)
		.append(expire_date)
		.toHashCode();
	}


}