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
@XmlType(propOrder={"recharge_log_list","adjust_log_list","transfer_log_list","payment_log_list","negativebal_log_list","max_freesource_log_list"})
public class Do_queryWorkLogResponse extends BaseResponse implements IComplexEntity{


	@XmlElement(name="recharge_log_list")
	private RechargeLogList recharge_log_list;

	@XmlElement(name="adjust_log_list")
	private AdjustLogList adjust_log_list;

	@XmlElement(name="transfer_log_list")
	private TransferLogList transfer_log_list;

	@XmlElement(name="payment_log_list")
	private PaymentLogList payment_log_list;

	@XmlElement(name="negativebal_log_list")
	private NegativeBalLogList negativebal_log_list;

	@XmlElement(name="max_freesource_log_list")
	private MaxFreeResourceLogList max_freesource_log_list;

	public void setRecharge_log_list(RechargeLogList obj){
		this.recharge_log_list = obj;
	}

	public RechargeLogList getRecharge_log_list(){
		return recharge_log_list;
	}

	public void setAdjust_log_list(AdjustLogList obj){
		this.adjust_log_list = obj;
	}

	public AdjustLogList getAdjust_log_list(){
		return adjust_log_list;
	}

	public void setTransfer_log_list(TransferLogList obj){
		this.transfer_log_list = obj;
	}

	public TransferLogList getTransfer_log_list(){
		return transfer_log_list;
	}

	public void setPayment_log_list(PaymentLogList obj){
		this.payment_log_list = obj;
	}

	public PaymentLogList getPayment_log_list(){
		return payment_log_list;
	}

	public void setNegativebal_log_list(NegativeBalLogList obj){
		this.negativebal_log_list = obj;
	}

	public NegativeBalLogList getNegativebal_log_list(){
		return negativebal_log_list;
	}

	public void setMax_freesource_log_list(MaxFreeResourceLogList obj){
		this.max_freesource_log_list = obj;
	}

	public MaxFreeResourceLogList getMax_freesource_log_list(){
		return max_freesource_log_list;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Do_queryWorkLogResponse rhs=(Do_queryWorkLogResponse)rhs0;
		if(!ObjectUtils.equals(recharge_log_list, rhs.recharge_log_list)) return false;
		if(!ObjectUtils.equals(adjust_log_list, rhs.adjust_log_list)) return false;
		if(!ObjectUtils.equals(transfer_log_list, rhs.transfer_log_list)) return false;
		if(!ObjectUtils.equals(payment_log_list, rhs.payment_log_list)) return false;
		if(!ObjectUtils.equals(negativebal_log_list, rhs.negativebal_log_list)) return false;
		if(!ObjectUtils.equals(max_freesource_log_list, rhs.max_freesource_log_list)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(recharge_log_list)
		.append(adjust_log_list)
		.append(transfer_log_list)
		.append(payment_log_list)
		.append(negativebal_log_list)
		.append(max_freesource_log_list)
		.toHashCode();
	}


}