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
@XmlType(propOrder={"phone_id","outer_acct_id","acct_id","flag","op_id","adjust_type","adjust_time","amount","days","remark","start_flag","freeResourceLogList","oldBalance","newBalance","so_mode","source_system","transaction_id","maximum_flag","rtner","failed_Reson","operation_date"})
public class AdjustLog implements IComplexEntity{


	@XmlElement(name="freeResourceLogList")
	private FreeResourceLog freeResourceLogList;

	@XmlElement(name="oldBalance")
	private SBalance oldBalance;

	@XmlElement(name="newBalance")
	private SBalance newBalance;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private long acct_id;

	@XmlElement(name="flag")
	private Short flag;

	@XmlElement(name="op_id")
	private Long op_id;

	@XmlElement(name="adjust_type")
	private Short adjust_type;

	@XmlElement(name="adjust_time")
	private String adjust_time;

	@XmlElement(name="amount")
	private Long amount;

	@XmlElement(name="days")
	private Long days;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="start_flag")
	private Short start_flag;

	@XmlElement(name="so_mode")
	private Short so_mode;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="transaction_id")
	private String transaction_id;

	@XmlElement(name="maximum_flag")
	private Short maximum_flag;

	@XmlElement(name="rtner")
	private Short rtner;

	@XmlElement(name="failed_Reson")
	private String failed_Reson;

	@XmlElement(name="operation_date")
	private Date operation_date;

	public void setFreeResourceLogList(FreeResourceLog obj){
		this.freeResourceLogList = obj;
	}

	public FreeResourceLog getFreeResourceLogList(){
		return freeResourceLogList;
	}

	public void setOldBalance(SBalance obj){
		this.oldBalance = obj;
	}

	public SBalance getOldBalance(){
		return oldBalance;
	}

	public void setNewBalance(SBalance obj){
		this.newBalance = obj;
	}

	public SBalance getNewBalance(){
		return newBalance;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(long obj){
		this.acct_id = obj;
	}

	public long getAcct_id(){
		return acct_id;
	}

	public void setFlag(Short obj){
		this.flag = obj;
	}

	public Short getFlag(){
		return flag;
	}

	public void setOp_id(Long obj){
		this.op_id = obj;
	}

	public Long getOp_id(){
		return op_id;
	}

	public void setAdjust_type(Short obj){
		this.adjust_type = obj;
	}

	public Short getAdjust_type(){
		return adjust_type;
	}

	public void setAdjust_time(String obj){
		this.adjust_time = obj;
	}

	public String getAdjust_time(){
		return adjust_time;
	}

	public void setAmount(Long obj){
		this.amount = obj;
	}

	public Long getAmount(){
		return amount;
	}

	public void setDays(Long obj){
		this.days = obj;
	}

	public Long getDays(){
		return days;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setStart_flag(Short obj){
		this.start_flag = obj;
	}

	public Short getStart_flag(){
		return start_flag;
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

	public void setMaximum_flag(Short obj){
		this.maximum_flag = obj;
	}

	public Short getMaximum_flag(){
		return maximum_flag;
	}

	public void setRtner(Short obj){
		this.rtner = obj;
	}

	public Short getRtner(){
		return rtner;
	}

	public void setFailed_Reson(String obj){
		this.failed_Reson = obj;
	}

	public String getFailed_Reson(){
		return failed_Reson;
	}

	public void setOperation_date(Date obj){
		this.operation_date = obj;
	}

	public Date getOperation_date(){
		return operation_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AdjustLog rhs=(AdjustLog)rhs0;
		if(!ObjectUtils.equals(freeResourceLogList, rhs.freeResourceLogList)) return false;
		if(!ObjectUtils.equals(oldBalance, rhs.oldBalance)) return false;
		if(!ObjectUtils.equals(newBalance, rhs.newBalance)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(op_id, rhs.op_id)) return false;
		if(!ObjectUtils.equals(adjust_type, rhs.adjust_type)) return false;
		if(!ObjectUtils.equals(adjust_time, rhs.adjust_time)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(days, rhs.days)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(start_flag, rhs.start_flag)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(transaction_id, rhs.transaction_id)) return false;
		if(!ObjectUtils.equals(maximum_flag, rhs.maximum_flag)) return false;
		if(!ObjectUtils.equals(rtner, rhs.rtner)) return false;
		if(!ObjectUtils.equals(failed_Reson, rhs.failed_Reson)) return false;
		if(!ObjectUtils.equals(operation_date, rhs.operation_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(freeResourceLogList)
		.append(oldBalance)
		.append(newBalance)
		.append(phone_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(flag)
		.append(op_id)
		.append(adjust_type)
		.append(adjust_time)
		.append(amount)
		.append(days)
		.append(remark)
		.append(start_flag)
		.append(so_mode)
		.append(source_system)
		.append(transaction_id)
		.append(maximum_flag)
		.append(rtner)
		.append(failed_Reson)
		.append(operation_date)
		.toHashCode();
	}


}