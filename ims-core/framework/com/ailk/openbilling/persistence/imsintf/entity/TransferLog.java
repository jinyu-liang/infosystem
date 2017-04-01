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
@XmlType(propOrder={"org_phone_id","outer_org_acct_id","org_acct_id","target_phone_id","outer_target_acct_id","target_acct_id","flag","op_id","transfer_date","transfer_rule_id","transfer_times","remark","orgOldBalance","orgNewBalance","targetOldBalance","targetNewBalance","orgOldFreeResource","orgNewFreeResource","targetOldFreeResource","targetNewFreeResource","oneTimeFee","so_mode","source_system","transaction_id","operation_date","_fail_reason"})
public class TransferLog implements IComplexEntity{


	@XmlElement(name="orgOldBalance")
	private SBalance orgOldBalance;

	@XmlElement(name="orgNewBalance")
	private SBalance orgNewBalance;

	@XmlElement(name="targetOldBalance")
	private SBalance targetOldBalance;

	@XmlElement(name="targetNewBalance")
	private SBalance targetNewBalance;

	@XmlElement(name="orgOldFreeResource")
	private FreeResource orgOldFreeResource;

	@XmlElement(name="orgNewFreeResource")
	private FreeResource orgNewFreeResource;

	@XmlElement(name="targetOldFreeResource")
	private FreeResource targetOldFreeResource;

	@XmlElement(name="targetNewFreeResource")
	private FreeResource targetNewFreeResource;

	@XmlElement(name="oneTimeFee")
	private FeeItemList oneTimeFee;

	@XmlElement(name="org_phone_id")
	private String org_phone_id;

	@XmlElement(name="outer_org_acct_id")
	private String outer_org_acct_id;

	@XmlElement(name="org_acct_id")
	private Long org_acct_id;

	@XmlElement(name="target_phone_id")
	private String target_phone_id;

	@XmlElement(name="outer_target_acct_id")
	private String outer_target_acct_id;

	@XmlElement(name="target_acct_id")
	private Long target_acct_id;

	@XmlElement(name="flag")
	private short flag;

	@XmlElement(name="op_id")
	private Integer op_id;

	@XmlElement(name="transfer_date")
	private String transfer_date;

	@XmlElement(name="transfer_rule_id")
	private short transfer_rule_id;

	@XmlElement(name="transfer_times")
	private Long transfer_times;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="so_mode")
	private Short so_mode;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="transaction_id")
	private String transaction_id;

	@XmlElement(name="operation_date")
	private Date operation_date;

	@XmlElement(name="_fail_reason")
	private String _fail_reason;

	public void setOrgOldBalance(SBalance obj){
		this.orgOldBalance = obj;
	}

	public SBalance getOrgOldBalance(){
		return orgOldBalance;
	}

	public void setOrgNewBalance(SBalance obj){
		this.orgNewBalance = obj;
	}

	public SBalance getOrgNewBalance(){
		return orgNewBalance;
	}

	public void setTargetOldBalance(SBalance obj){
		this.targetOldBalance = obj;
	}

	public SBalance getTargetOldBalance(){
		return targetOldBalance;
	}

	public void setTargetNewBalance(SBalance obj){
		this.targetNewBalance = obj;
	}

	public SBalance getTargetNewBalance(){
		return targetNewBalance;
	}

	public void setOrgOldFreeResource(FreeResource obj){
		this.orgOldFreeResource = obj;
	}

	public FreeResource getOrgOldFreeResource(){
		return orgOldFreeResource;
	}

	public void setOrgNewFreeResource(FreeResource obj){
		this.orgNewFreeResource = obj;
	}

	public FreeResource getOrgNewFreeResource(){
		return orgNewFreeResource;
	}

	public void setTargetOldFreeResource(FreeResource obj){
		this.targetOldFreeResource = obj;
	}

	public FreeResource getTargetOldFreeResource(){
		return targetOldFreeResource;
	}

	public void setTargetNewFreeResource(FreeResource obj){
		this.targetNewFreeResource = obj;
	}

	public FreeResource getTargetNewFreeResource(){
		return targetNewFreeResource;
	}

	public void setOneTimeFee(FeeItemList obj){
		this.oneTimeFee = obj;
	}

	public FeeItemList getOneTimeFee(){
		return oneTimeFee;
	}

	public void setOrg_phone_id(String obj){
		this.org_phone_id = obj;
	}

	public String getOrg_phone_id(){
		return org_phone_id;
	}

	public void setOuter_org_acct_id(String obj){
		this.outer_org_acct_id = obj;
	}

	public String getOuter_org_acct_id(){
		return outer_org_acct_id;
	}

	public void setOrg_acct_id(Long obj){
		this.org_acct_id = obj;
	}

	public Long getOrg_acct_id(){
		return org_acct_id;
	}

	public void setTarget_phone_id(String obj){
		this.target_phone_id = obj;
	}

	public String getTarget_phone_id(){
		return target_phone_id;
	}

	public void setOuter_target_acct_id(String obj){
		this.outer_target_acct_id = obj;
	}

	public String getOuter_target_acct_id(){
		return outer_target_acct_id;
	}

	public void setTarget_acct_id(Long obj){
		this.target_acct_id = obj;
	}

	public Long getTarget_acct_id(){
		return target_acct_id;
	}

	public void setFlag(short obj){
		this.flag = obj;
	}

	public short getFlag(){
		return flag;
	}

	public void setOp_id(Integer obj){
		this.op_id = obj;
	}

	public Integer getOp_id(){
		return op_id;
	}

	public void setTransfer_date(String obj){
		this.transfer_date = obj;
	}

	public String getTransfer_date(){
		return transfer_date;
	}

	public void setTransfer_rule_id(short obj){
		this.transfer_rule_id = obj;
	}

	public short getTransfer_rule_id(){
		return transfer_rule_id;
	}

	public void setTransfer_times(Long obj){
		this.transfer_times = obj;
	}

	public Long getTransfer_times(){
		return transfer_times;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setSo_mode(Short obj){
		this.so_mode = obj;
	}

	public Short getSo_mode(){
		return so_mode;
	}

	public void setSource_system(String obj){
		this.source_system = obj;
	}

	public String getSource_system(){
		return source_system;
	}

	public void setTransaction_id(String obj){
		this.transaction_id = obj;
	}

	public String getTransaction_id(){
		return transaction_id;
	}

	public void setOperation_date(Date obj){
		this.operation_date = obj;
	}

	public Date getOperation_date(){
		return operation_date;
	}

	public void setFail_reason(String obj){
		this._fail_reason = obj;
	}

	public String getFail_reason(){
		return _fail_reason;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		TransferLog rhs=(TransferLog)rhs0;
		if(!ObjectUtils.equals(orgOldBalance, rhs.orgOldBalance)) return false;
		if(!ObjectUtils.equals(orgNewBalance, rhs.orgNewBalance)) return false;
		if(!ObjectUtils.equals(targetOldBalance, rhs.targetOldBalance)) return false;
		if(!ObjectUtils.equals(targetNewBalance, rhs.targetNewBalance)) return false;
		if(!ObjectUtils.equals(orgOldFreeResource, rhs.orgOldFreeResource)) return false;
		if(!ObjectUtils.equals(orgNewFreeResource, rhs.orgNewFreeResource)) return false;
		if(!ObjectUtils.equals(targetOldFreeResource, rhs.targetOldFreeResource)) return false;
		if(!ObjectUtils.equals(targetNewFreeResource, rhs.targetNewFreeResource)) return false;
		if(!ObjectUtils.equals(oneTimeFee, rhs.oneTimeFee)) return false;
		if(!ObjectUtils.equals(org_phone_id, rhs.org_phone_id)) return false;
		if(!ObjectUtils.equals(outer_org_acct_id, rhs.outer_org_acct_id)) return false;
		if(!ObjectUtils.equals(org_acct_id, rhs.org_acct_id)) return false;
		if(!ObjectUtils.equals(target_phone_id, rhs.target_phone_id)) return false;
		if(!ObjectUtils.equals(outer_target_acct_id, rhs.outer_target_acct_id)) return false;
		if(!ObjectUtils.equals(target_acct_id, rhs.target_acct_id)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(op_id, rhs.op_id)) return false;
		if(!ObjectUtils.equals(transfer_date, rhs.transfer_date)) return false;
		if(!ObjectUtils.equals(transfer_rule_id, rhs.transfer_rule_id)) return false;
		if(!ObjectUtils.equals(transfer_times, rhs.transfer_times)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(transaction_id, rhs.transaction_id)) return false;
		if(!ObjectUtils.equals(operation_date, rhs.operation_date)) return false;
		if(!ObjectUtils.equals(_fail_reason, rhs._fail_reason)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(orgOldBalance)
		.append(orgNewBalance)
		.append(targetOldBalance)
		.append(targetNewBalance)
		.append(orgOldFreeResource)
		.append(orgNewFreeResource)
		.append(targetOldFreeResource)
		.append(targetNewFreeResource)
		.append(oneTimeFee)
		.append(org_phone_id)
		.append(outer_org_acct_id)
		.append(org_acct_id)
		.append(target_phone_id)
		.append(outer_target_acct_id)
		.append(target_acct_id)
		.append(flag)
		.append(op_id)
		.append(transfer_date)
		.append(transfer_rule_id)
		.append(transfer_times)
		.append(remark)
		.append(so_mode)
		.append(source_system)
		.append(transaction_id)
		.append(operation_date)
		.append(_fail_reason)
		.toHashCode();
	}


}