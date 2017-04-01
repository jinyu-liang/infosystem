package com.ailk.openbilling.persistence.imsinner.entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import java.util.List;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannelList;
import jef.codegen.support.NotModified;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acct_id","prov_code","region_code","parent_acct_id","acct_name","company","acct_type","acct_class","acct_segment","status","create_date","valid_date","expire_date","credit","budget","measure_id","due_day","bill_dispatching","bill_language_id","force_cycle","balance_type","bill_cycle","summary_deliver","cust_id","list_ext_param","sAccountContact","sPayChannelList","credit_sign_control"})
public class AccountRet extends CustomerRet implements IComplexEntity{


	@XmlElement(name="bill_cycle")
	private SBillCycle bill_cycle;

	@XmlElement(name="list_ext_param")
	private ExtendParamList list_ext_param;

	@XmlElement(name="sAccountContact")
	private List<SContact> sAccountContact;

	@XmlElement(name="sPayChannelList")
	private SPayChannelList sPayChannelList;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="prov_code")
	private Short prov_code;

	@XmlElement(name="region_code")
	private Short region_code;

	@XmlElement(name="parent_acct_id")
	private Long parent_acct_id;

	@XmlElement(name="acct_name")
	private String acct_name;

	@XmlElement(name="company")
	private Short company;

	@XmlElement(name="acct_type")
	private Short acct_type;

	@XmlElement(name="acct_class")
	private Short acct_class;

	@XmlElement(name="acct_segment")
	private Short acct_segment;

	@XmlElement(name="status")
	private Short status;

	@XmlElement(name="create_date")
	private String create_date;

	@XmlElement(name="valid_date")
	private String valid_date;

	@XmlElement(name="expire_date")
	private String expire_date;

	@XmlElement(name="credit")
	private Double credit;

	@XmlElement(name="budget")
	private Double budget;

	@XmlElement(name="measure_id")
	private Integer measure_id;

	@XmlElement(name="due_day")
	private Short due_day;

	@XmlElement(name="bill_dispatching")
	private Short bill_dispatching;

	@XmlElement(name="bill_language_id")
	private Short bill_language_id;

	@XmlElement(name="force_cycle")
	private Short force_cycle;

	@XmlElement(name="balance_type")
	private Integer balance_type;

	@XmlElement(name="summary_deliver")
	private String summary_deliver;

	@XmlElement(name="cust_id")
	private Long cust_id;

	@XmlElement(name="credit_sign_control")
	private Integer credit_sign_control;

	public void setBill_cycle(SBillCycle obj){
		this.bill_cycle = obj;
	}

	public SBillCycle getBill_cycle(){
		return bill_cycle;
	}

	public void setList_ext_param(ExtendParamList obj){
		this.list_ext_param = obj;
	}

	public ExtendParamList getList_ext_param(){
		return list_ext_param;
	}

	public void setSAccountContact(List<SContact> obj){
		this.sAccountContact = obj;
	}

	public List<SContact> getSAccountContact(){
		return sAccountContact;
	}

	public void setSPayChannelList(SPayChannelList obj){
		this.sPayChannelList = obj;
	}

	public SPayChannelList getSPayChannelList(){
		return sPayChannelList;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setProv_code(Short obj){
		this.prov_code = obj;
	}

	public Short getProv_code(){
		return prov_code;
	}

	public void setRegion_code(Short obj){
		this.region_code = obj;
	}

	public Short getRegion_code(){
		return region_code;
	}

	public void setParent_acct_id(Long obj){
		this.parent_acct_id = obj;
	}

	public Long getParent_acct_id(){
		return parent_acct_id;
	}

	public void setAcct_name(String obj){
		this.acct_name = obj;
	}

	public String getAcct_name(){
		return acct_name;
	}

	public void setCompany(Short obj){
		this.company = obj;
	}

	public Short getCompany(){
		return company;
	}

	public void setAcct_type(Short obj){
		this.acct_type = obj;
	}

	public Short getAcct_type(){
		return acct_type;
	}

	public void setAcct_class(Short obj){
		this.acct_class = obj;
	}

	public Short getAcct_class(){
		return acct_class;
	}

	public void setAcct_segment(Short obj){
		this.acct_segment = obj;
	}

	public Short getAcct_segment(){
		return acct_segment;
	}

	public void setStatus(Short obj){
		this.status = obj;
	}

	public Short getStatus(){
		return status;
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

	public void setCredit(Double obj){
		this.credit = obj;
	}

	public Double getCredit(){
		return credit;
	}

	public void setBudget(Double obj){
		this.budget = obj;
	}

	public Double getBudget(){
		return budget;
	}

	public void setMeasure_id(Integer obj){
		this.measure_id = obj;
	}

	public Integer getMeasure_id(){
		return measure_id;
	}

	public void setDue_day(Short obj){
		this.due_day = obj;
	}

	public Short getDue_day(){
		return due_day;
	}

	public void setBill_dispatching(Short obj){
		this.bill_dispatching = obj;
	}

	public Short getBill_dispatching(){
		return bill_dispatching;
	}

	public void setBill_language_id(Short obj){
		this.bill_language_id = obj;
	}

	public Short getBill_language_id(){
		return bill_language_id;
	}

	public void setForce_cycle(Short obj){
		this.force_cycle = obj;
	}

	public Short getForce_cycle(){
		return force_cycle;
	}

	public void setBalance_type(Integer obj){
		this.balance_type = obj;
	}

	public Integer getBalance_type(){
		return balance_type;
	}

	public void setSummary_deliver(String obj){
		this.summary_deliver = obj;
	}

	public String getSummary_deliver(){
		return summary_deliver;
	}

	public void setCust_id(Long obj){
		this.cust_id = obj;
	}

	public Long getCust_id(){
		return cust_id;
	}

	public void setCredit_sign_control(Integer obj){
		this.credit_sign_control = obj;
	}

	public Integer getCredit_sign_control(){
		return credit_sign_control;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AccountRet rhs=(AccountRet)rhs0;
		if(!ObjectUtils.equals(bill_cycle, rhs.bill_cycle)) return false;
		if(!ObjectUtils.equals(list_ext_param, rhs.list_ext_param)) return false;
		if(!ObjectUtils.equals(sAccountContact, rhs.sAccountContact)) return false;
		if(!ObjectUtils.equals(sPayChannelList, rhs.sPayChannelList)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(prov_code, rhs.prov_code)) return false;
		if(!ObjectUtils.equals(region_code, rhs.region_code)) return false;
		if(!ObjectUtils.equals(parent_acct_id, rhs.parent_acct_id)) return false;
		if(!ObjectUtils.equals(acct_name, rhs.acct_name)) return false;
		if(!ObjectUtils.equals(company, rhs.company)) return false;
		if(!ObjectUtils.equals(acct_type, rhs.acct_type)) return false;
		if(!ObjectUtils.equals(acct_class, rhs.acct_class)) return false;
		if(!ObjectUtils.equals(acct_segment, rhs.acct_segment)) return false;
		if(!ObjectUtils.equals(status, rhs.status)) return false;
		if(!ObjectUtils.equals(create_date, rhs.create_date)) return false;
		if(!ObjectUtils.equals(valid_date, rhs.valid_date)) return false;
		if(!ObjectUtils.equals(expire_date, rhs.expire_date)) return false;
		if(!ObjectUtils.equals(credit, rhs.credit)) return false;
		if(!ObjectUtils.equals(budget, rhs.budget)) return false;
		if(!ObjectUtils.equals(measure_id, rhs.measure_id)) return false;
		if(!ObjectUtils.equals(due_day, rhs.due_day)) return false;
		if(!ObjectUtils.equals(bill_dispatching, rhs.bill_dispatching)) return false;
		if(!ObjectUtils.equals(bill_language_id, rhs.bill_language_id)) return false;
		if(!ObjectUtils.equals(force_cycle, rhs.force_cycle)) return false;
		if(!ObjectUtils.equals(balance_type, rhs.balance_type)) return false;
		if(!ObjectUtils.equals(summary_deliver, rhs.summary_deliver)) return false;
		if(!ObjectUtils.equals(cust_id, rhs.cust_id)) return false;
		if(!ObjectUtils.equals(credit_sign_control, rhs.credit_sign_control)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(bill_cycle)
		.append(list_ext_param)
		.append(sAccountContact)
		.append(sPayChannelList)
		.append(acct_id)
		.append(prov_code)
		.append(region_code)
		.append(parent_acct_id)
		.append(acct_name)
		.append(company)
		.append(acct_type)
		.append(acct_class)
		.append(acct_segment)
		.append(status)
		.append(create_date)
		.append(valid_date)
		.append(expire_date)
		.append(credit)
		.append(budget)
		.append(measure_id)
		.append(due_day)
		.append(bill_dispatching)
		.append(bill_language_id)
		.append(force_cycle)
		.append(balance_type)
		.append(summary_deliver)
		.append(cust_id)
		.append(credit_sign_control)
		.toHashCode();
	}


}