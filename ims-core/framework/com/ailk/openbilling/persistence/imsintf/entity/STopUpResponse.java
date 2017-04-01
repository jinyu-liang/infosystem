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
@XmlType(propOrder={"user_id","phone_id","account_balance","new_active_stop","validity_period","extra_validity","prod_order_result_list","recharge_time","date_time","freeResourceGroupList","reward_balance","topup_amount"})
public class STopUpResponse implements IComplexEntity{


	@XmlElement(name="prod_order_result_list")
	private SProductOrderResultList prod_order_result_list;

	@XmlElement(name="freeResourceGroupList")
	private FreeResourceGroupList freeResourceGroupList;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="account_balance")
	private Long account_balance;

	@XmlElement(name="new_active_stop")
	private String new_active_stop;

	@XmlElement(name="validity_period")
	private Integer validity_period;

	@XmlElement(name="extra_validity")
	private Integer extra_validity;

	@XmlElement(name="recharge_time")
	private Date recharge_time;

	@XmlElement(name="date_time")
	private Date date_time;

	@XmlElement(name="reward_balance")
	private Long reward_balance;

	@XmlElement(name="topup_amount")
	private Long topup_amount;

	public void setProd_order_result_list(SProductOrderResultList obj){
		this.prod_order_result_list = obj;
	}

	public SProductOrderResultList getProd_order_result_list(){
		return prod_order_result_list;
	}

	public void setFreeResourceGroupList(FreeResourceGroupList obj){
		this.freeResourceGroupList = obj;
	}

	public FreeResourceGroupList getFreeResourceGroupList(){
		return freeResourceGroupList;
	}

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

	public void setAccount_balance(Long obj){
		this.account_balance = obj;
	}

	public Long getAccount_balance(){
		return account_balance;
	}

	public void setNew_active_stop(String obj){
		this.new_active_stop = obj;
	}

	public String getNew_active_stop(){
		return new_active_stop;
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

	public void setRecharge_time(Date obj){
		this.recharge_time = obj;
	}

	public Date getRecharge_time(){
		return recharge_time;
	}

	public void setDate_time(Date obj){
		this.date_time = obj;
	}

	public Date getDate_time(){
		return date_time;
	}

	public void setReward_balance(Long obj){
		this.reward_balance = obj;
	}

	public Long getReward_balance(){
		return reward_balance;
	}

	public void setTopup_amount(Long obj){
		this.topup_amount = obj;
	}

	public Long getTopup_amount(){
		return topup_amount;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		STopUpResponse rhs=(STopUpResponse)rhs0;
		if(!ObjectUtils.equals(prod_order_result_list, rhs.prod_order_result_list)) return false;
		if(!ObjectUtils.equals(freeResourceGroupList, rhs.freeResourceGroupList)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(account_balance, rhs.account_balance)) return false;
		if(!ObjectUtils.equals(new_active_stop, rhs.new_active_stop)) return false;
		if(!ObjectUtils.equals(validity_period, rhs.validity_period)) return false;
		if(!ObjectUtils.equals(extra_validity, rhs.extra_validity)) return false;
		if(!ObjectUtils.equals(recharge_time, rhs.recharge_time)) return false;
		if(!ObjectUtils.equals(date_time, rhs.date_time)) return false;
		if(!ObjectUtils.equals(reward_balance, rhs.reward_balance)) return false;
		if(!ObjectUtils.equals(topup_amount, rhs.topup_amount)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(prod_order_result_list)
		.append(freeResourceGroupList)
		.append(user_id)
		.append(phone_id)
		.append(account_balance)
		.append(new_active_stop)
		.append(validity_period)
		.append(extra_validity)
		.append(recharge_time)
		.append(date_time)
		.append(reward_balance)
		.append(topup_amount)
		.toHashCode();
	}


}