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
@XmlType(propOrder={"adjust_type","outer_acct_id","acct_id","phone_id","flag","rtner","remark","notify_flag","sBalance","freeResourceList","user_id","maximum_flag"})
public class AdjustBalanceReq implements IComplexEntity{


	@XmlElement(name="sBalance")
	private SBalance sBalance;

	@XmlElement(name="freeResourceList")
	private FreeResourceList freeResourceList;

	@XmlElement(name="adjust_type")
	private Short adjust_type;

	@XmlElement(name="outer_acct_id")
	private String outer_acct_id;

	@XmlElement(name="acct_id")
	private Long acct_id;

	@XmlElement(name="phone_id")
	private String phone_id;

	@XmlElement(name="flag")
	private Short flag;

	@XmlElement(name="rtner")
	private Short rtner;

	@XmlElement(name="remark")
	private String remark;

	@XmlElement(name="notify_flag")
	private Short notify_flag;

	@XmlElement(name="user_id")
	private Long user_id;

	@XmlElement(name="maximum_flag")
	private Short maximum_flag;

	public void setSBalance(SBalance obj){
		this.sBalance = obj;
	}

	public SBalance getSBalance(){
		return sBalance;
	}

	public void setFreeResourceList(FreeResourceList obj){
		this.freeResourceList = obj;
	}

	public FreeResourceList getFreeResourceList(){
		return freeResourceList;
	}

	public void setAdjust_type(Short obj){
		this.adjust_type = obj;
	}

	public Short getAdjust_type(){
		return adjust_type;
	}

	public void setOuter_acct_id(String obj){
		this.outer_acct_id = obj;
	}

	public String getOuter_acct_id(){
		return outer_acct_id;
	}

	public void setAcct_id(Long obj){
		this.acct_id = obj;
	}

	public Long getAcct_id(){
		return acct_id;
	}

	public void setPhone_id(String obj){
		this.phone_id = obj;
	}

	public String getPhone_id(){
		return phone_id;
	}

	public void setFlag(Short obj){
		this.flag = obj;
	}

	public Short getFlag(){
		return flag;
	}

	public void setRtner(Short obj){
		this.rtner = obj;
	}

	public Short getRtner(){
		return rtner;
	}

	public void setRemark(String obj){
		this.remark = obj;
	}

	public String getRemark(){
		return remark;
	}

	public void setNotify_flag(Short obj){
		this.notify_flag = obj;
	}

	public Short getNotify_flag(){
		return notify_flag;
	}

	public void setUser_id(Long obj){
		this.user_id = obj;
	}

	public Long getUser_id(){
		return user_id;
	}

	public void setMaximum_flag(Short obj){
		this.maximum_flag = obj;
	}

	public Short getMaximum_flag(){
		return maximum_flag;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		AdjustBalanceReq rhs=(AdjustBalanceReq)rhs0;
		if(!ObjectUtils.equals(sBalance, rhs.sBalance)) return false;
		if(!ObjectUtils.equals(freeResourceList, rhs.freeResourceList)) return false;
		if(!ObjectUtils.equals(adjust_type, rhs.adjust_type)) return false;
		if(!ObjectUtils.equals(outer_acct_id, rhs.outer_acct_id)) return false;
		if(!ObjectUtils.equals(acct_id, rhs.acct_id)) return false;
		if(!ObjectUtils.equals(phone_id, rhs.phone_id)) return false;
		if(!ObjectUtils.equals(flag, rhs.flag)) return false;
		if(!ObjectUtils.equals(rtner, rhs.rtner)) return false;
		if(!ObjectUtils.equals(remark, rhs.remark)) return false;
		if(!ObjectUtils.equals(notify_flag, rhs.notify_flag)) return false;
		if(!ObjectUtils.equals(user_id, rhs.user_id)) return false;
		if(!ObjectUtils.equals(maximum_flag, rhs.maximum_flag)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(sBalance)
		.append(freeResourceList)
		.append(adjust_type)
		.append(outer_acct_id)
		.append(acct_id)
		.append(phone_id)
		.append(flag)
		.append(rtner)
		.append(remark)
		.append(notify_flag)
		.append(user_id)
		.append(maximum_flag)
		.toHashCode();
	}


}