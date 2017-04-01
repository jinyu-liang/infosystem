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
@XmlType(propOrder={"phone_id","outer_acct_id","acct_id","flag","op_id","recharge_time","amount","remark","card_serial_no","recharge_method","recharge_partner_id","recharge_service_id","payment_mode","card_batch_no","oldBalance","newBalanceList","so_mode","source_system","transaction_id","validity_period","extra_validity","freeResrouceGroupList","productOrdResultList","_etopup_session_id","_operation_date"})
public class RechargeLog implements IComplexEntity{


	@XmlElement(name="oldBalance")
	private SBalance oldBalance;

	@XmlElement(name="newBalanceList")
	private SBalanceList newBalanceList;

	@XmlElement(name="freeResrouceGroupList")
	private FreeResourceGroupList freeResrouceGroupList;

	@XmlElement(name="productOrdResultList")
	private SProductOrderList productOrdResultList;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private long acct_id;

	@XmlElement(name="flag")
	private short flag;

	@XmlElement(name="op_id")
	private Long op_id;

	@XmlElement(name="recharge_time")
	private String recharge_time;

	@XmlElement(name="amount")
	private long amount;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="card_serial_no")
	private String card_serial_no;

	@XmlElement(name="recharge_method")
	private Short recharge_method;

	@XmlElement(name="recharge_partner_id")
	private String recharge_partner_id;

	@XmlElement(name="recharge_service_id")
	private Integer recharge_service_id;

	@XmlElement(name="payment_mode")
	private String payment_mode;

	@XmlElement(name="card_batch_no")
	private String card_batch_no;

	@XmlElement(name="so_mode")
	private Short so_mode;

	@XmlElement(name="source_system")
	private String source_system;

	@XmlElement(name="transaction_id")
	private String transaction_id;

	@XmlElement(name="validity_period")
	private Integer validity_period;

	@XmlElement(name="extra_validity")
	private Integer extra_validity;

	@XmlElement(name="_etopup_session_id")
	private String _etopup_session_id;

	@XmlElement(name="_operation_date")
	private Date _operation_date;

	public void setOldBalance(SBalance obj){
		this.oldBalance = obj;
	}

	public SBalance getOldBalance(){
		return oldBalance;
	}

	public void setNewBalanceList(SBalanceList obj){
		this.newBalanceList = obj;
	}

	public SBalanceList getNewBalanceList(){
		return newBalanceList;
	}

	public void setFreeResrouceGroupList(FreeResourceGroupList obj){
		this.freeResrouceGroupList = obj;
	}

	public FreeResourceGroupList getFreeResrouceGroupList(){
		return freeResrouceGroupList;
	}

	public void setProductOrdResultList(SProductOrderList obj){
		this.productOrdResultList = obj;
	}

	public SProductOrderList getProductOrdResultList(){
		return productOrdResultList;
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

	public void setFlag(short obj){
		this.flag = obj;
	}

	public short getFlag(){
		return flag;
	}

	public void setOp_id(Long obj){
		this.op_id = obj;
	}

	public Long getOp_id(){
		return op_id;
	}

	public void setRecharge_time(String obj){
		this.recharge_time = obj;
	}

	public String getRecharge_time(){
		return recharge_time;
	}

	public void setAmount(long obj){
		this.amount = obj;
	}

	public long getAmount(){
		return amount;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setCard_serial_no(String obj){
		this.card_serial_no = obj;
	}

	public String getCard_serial_no(){
		return card_serial_no;
	}

	public void setRecharge_method(Short obj){
		this.recharge_method = obj;
	}

	public Short getRecharge_method(){
		return recharge_method;
	}

	public void setRecharge_partner_id(String obj){
		this.recharge_partner_id = obj;
	}

	public String getRecharge_partner_id(){
		return recharge_partner_id;
	}

	public void setRecharge_service_id(Integer obj){
		this.recharge_service_id = obj;
	}

	public Integer getRecharge_service_id(){
		return recharge_service_id;
	}

	public void setPayment_mode(String obj){
		this.payment_mode = obj;
	}

	public String getPayment_mode(){
		return payment_mode;
	}

	public void setCard_batch_no(String obj){
		this.card_batch_no = obj;
	}

	public String getCard_batch_no(){
		return card_batch_no;
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

	public void setValidity_period(Integer obj){
		this.validity_period = obj;
	}

	public Integer getValidity_period(){
		return validity_period;
	}

	public void setExtra_validity(Integer obj){
		this.extra_validity = obj;
	}

	public Integer getExtra_validity(){
		return extra_validity;
	}

	public void setEtopup_session_id(String obj){
		this._etopup_session_id = obj;
	}

	public String getEtopup_session_id(){
		return _etopup_session_id;
	}

	public void setOperation_date(Date obj){
		this._operation_date = obj;
	}

	public Date getOperation_date(){
		return _operation_date;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RechargeLog rhs=(RechargeLog)rhs0;
		if(!ObjectUtils.equals(oldBalance, rhs.oldBalance)) return false;
		if(!ObjectUtils.equals(newBalanceList, rhs.newBalanceList)) return false;
		if(!ObjectUtils.equals(freeResrouceGroupList, rhs.freeResrouceGroupList)) return false;
		if(!ObjectUtils.equals(productOrdResultList, rhs.productOrdResultList)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(op_id, rhs.op_id)) return false;
		if(!ObjectUtils.equals(recharge_time, rhs.recharge_time)) return false;
		if(!ObjectUtils.equals(amount, rhs.amount)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(card_serial_no, rhs.card_serial_no)) return false;
		if(!ObjectUtils.equals(recharge_method, rhs.recharge_method)) return false;
		if(!ObjectUtils.equals(recharge_partner_id, rhs.recharge_partner_id)) return false;
		if(!ObjectUtils.equals(recharge_service_id, rhs.recharge_service_id)) return false;
		if(!ObjectUtils.equals(payment_mode, rhs.payment_mode)) return false;
		if(!ObjectUtils.equals(card_batch_no, rhs.card_batch_no)) return false;
		if(!ObjectUtils.equals(so_mode, rhs.so_mode)) return false;
		if(!ObjectUtils.equals(source_system, rhs.source_system)) return false;
		if(!ObjectUtils.equals(transaction_id, rhs.transaction_id)) return false;
		if(!ObjectUtils.equals(validity_period, rhs.validity_period)) return false;
		if(!ObjectUtils.equals(extra_validity, rhs.extra_validity)) return false;
		if(!ObjectUtils.equals(_etopup_session_id, rhs._etopup_session_id)) return false;
		if(!ObjectUtils.equals(_operation_date, rhs._operation_date)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(oldBalance)
		.append(newBalanceList)
		.append(freeResrouceGroupList)
		.append(productOrdResultList)
		.append(phone_id)
		.append(outer_acct_id)
		.append(acct_id)
		.append(flag)
		.append(op_id)
		.append(recharge_time)
		.append(amount)
		.append(remark)
		.append(card_serial_no)
		.append(recharge_method)
		.append(recharge_partner_id)
		.append(recharge_service_id)
		.append(payment_mode)
		.append(card_batch_no)
		.append(so_mode)
		.append(source_system)
		.append(transaction_id)
		.append(validity_period)
		.append(extra_validity)
		.append(_etopup_session_id)
		.append(_operation_date)
		.toHashCode();
	}


}