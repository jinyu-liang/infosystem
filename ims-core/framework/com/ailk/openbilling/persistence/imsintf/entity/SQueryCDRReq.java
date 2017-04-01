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
@XmlType(propOrder={"phone_id","acct_id","payment_mode","start_date","end_date","domestic_international","queryCDRTypeList","callUsageCondition","sort_by","heading_style","additional_info","invoice_no","offLineFlag"})
public class SQueryCDRReq implements IComplexEntity{


	@XmlElement(name="queryCDRTypeList")
	private QueryCDRTypeList queryCDRTypeList;

	@XmlElement(name="callUsageCondition")
	private CallUsageCondition callUsageCondition;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="payment_mode")
	private Short payment_mode;

	@XmlElement(name="start_date")
	private String start_date;

	@XmlElement(name="end_date")
	private String end_date;

	@XmlElement(name="domestic_international")
	private Short domestic_international;

	@XmlElement(name="sort_by")
	private Short sort_by;

	@XmlElement(name="heading_style")
	private Short heading_style;

	@XmlElement(name="additional_info")
	private String additional_info;

	@XmlElement(name="invoice_no")
	private String invoice_no;

	@XmlElement(name="offLineFlag")
	private Short offLineFlag;

	public void setQueryCDRTypeList(QueryCDRTypeList obj){
		this.queryCDRTypeList = obj;
	}

	public QueryCDRTypeList getQueryCDRTypeList(){
		return queryCDRTypeList;
	}

	public void setCallUsageCondition(CallUsageCondition obj){
		this.callUsageCondition = obj;
	}

	public CallUsageCondition getCallUsageCondition(){
		return callUsageCondition;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setPayment_mode(Short obj){
		this.payment_mode = obj;
	}

	public Short getPayment_mode(){
		return payment_mode;
	}

	public void setStart_date(String obj){
		this.start_date = obj;
	}

	public String getStart_date(){
		return start_date;
	}

	public void setEnd_date(String obj){
		this.end_date = obj;
	}

	public String getEnd_date(){
		return end_date;
	}

	public void setDomestic_international(Short obj){
		this.domestic_international = obj;
	}

	public Short getDomestic_international(){
		return domestic_international;
	}

	public void setSort_by(Short obj){
		this.sort_by = obj;
	}

	public Short getSort_by(){
		return sort_by;
	}

	public void setHeading_style(Short obj){
		this.heading_style = obj;
	}

	public Short getHeading_style(){
		return heading_style;
	}

	public void setAdditional_info(String obj){
		this.additional_info = obj;
	}

	public String getAdditional_info(){
		return additional_info;
	}

	public void setInvoice_no(String obj){
		this.invoice_no = obj;
	}

	public String getInvoice_no(){
		return invoice_no;
	}

	public void setOffLineFlag(Short obj){
		this.offLineFlag = obj;
	}

	public Short getOffLineFlag(){
		return offLineFlag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SQueryCDRReq rhs=(SQueryCDRReq)rhs0;
		if(!ObjectUtils.equals(queryCDRTypeList, rhs.queryCDRTypeList)) return false;
		if(!ObjectUtils.equals(callUsageCondition, rhs.callUsageCondition)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(start_date, rhs.start_date)) return false;
		if(!ObjectUtils.equals(end_date, rhs.end_date)) return false;
		if(!ObjectUtils.equals(domestic_international, rhs.domestic_international)) return false;
		if(!ObjectUtils.equals(sort_by, rhs.sort_by)) return false;
		if(!ObjectUtils.equals(heading_style, rhs.heading_style)) return false;
		if(!ObjectUtils.equals(additional_info, rhs.additional_info)) return false;
		if(!ObjectUtils.equals(invoice_no, rhs.invoice_no)) return false;
		if(!ObjectUtils.equals(offLineFlag, rhs.offLineFlag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(queryCDRTypeList)
		.append(callUsageCondition)
		.append(phone_id)
		.append(acct_id)
		.append(payment_mode)
		.append(start_date)
		.append(end_date)
		.append(domestic_international)
		.append(sort_by)
		.append(heading_style)
		.append(additional_info)
		.append(invoice_no)
		.append(offLineFlag)
		.toHashCode();
	}


}