package com.ailk.openbilling.persistence.acct.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 */
@NotModified
@Entity
@Table(schema="AD",name="BI_BUSI_SPEC_EXT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"busiSpecId","rechargeMethod","channelId","busiResult","notificationId","busiPlanId"})
public class BiBusiSpecExt extends DataObject{


	/**
	 * Business interaction specification identifier
	 */
	@Id
	@Column(name="BUSI_SPEC_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiSpecId")
	private Integer busiSpecId;

	/**
	 * Business origin: -1:ALL 1: VC recharge 3: manual recharge 4: ATM recharge 5: STK recharge 6: Tele-banking recharge 7: Spare 8:cash 9: check
	 */
	@Id
	@Column(name="RECHARGE_METHOD",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="rechargeMethod")
	private Integer rechargeMethod;

	/**
	 * ????: 1-CRM 2-?? 3-?? 4-IVR
	 */
	@Id
	@Column(name="CHANNEL_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="channelId")
	private Integer channelId;

	/**
	 * Business result 1: Successful 2: Failed 3: ????? 4: ?????????
	 */
	@Id
	@Column(name="BUSI_RESULT",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiResult")
	private Integer busiResult;

	/**
	 * Business notification
	 */
	@Column(name="NOTIFICATION_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="notificationId")
	private Integer notificationId;

	@Id
	@Column(name="BUSI_PLAN_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="busiPlanId")
	private Integer busiPlanId;

	public void setBusiSpecId(Integer obj){
		this.busiSpecId = obj;
	}

	public Integer getBusiSpecId(){
		return busiSpecId;
	}

	public void setRechargeMethod(Integer obj){
		this.rechargeMethod = obj;
	}

	public Integer getRechargeMethod(){
		return rechargeMethod;
	}

	public void setChannelId(Integer obj){
		this.channelId = obj;
	}

	public Integer getChannelId(){
		return channelId;
	}

	public void setBusiResult(Integer obj){
		this.busiResult = obj;
	}

	public Integer getBusiResult(){
		return busiResult;
	}

	public void setNotificationId(Integer obj){
		this.notificationId = obj;
	}

	public Integer getNotificationId(){
		return notificationId;
	}

	public void setBusiPlanId(Integer obj){
		this.busiPlanId = obj;
	}

	public Integer getBusiPlanId(){
		return busiPlanId;
	}

	public BiBusiSpecExt(){
	}

	public BiBusiSpecExt(Integer busiSpecId,Integer rechargeMethod,Integer channelId,Integer busiResult,Integer busiPlanId){
		this.busiSpecId = busiSpecId;
		this.rechargeMethod = rechargeMethod;
		this.channelId = channelId;
		this.busiResult = busiResult;
		this.busiPlanId = busiPlanId;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BiBusiSpecExt rhs=(BiBusiSpecExt)rhs0;
		if(!ObjectUtils.equals(busiResult, rhs.busiResult)) return false;
		if(!ObjectUtils.equals(channelId, rhs.channelId)) return false;
		if(!ObjectUtils.equals(busiPlanId, rhs.busiPlanId)) return false;
		if(!ObjectUtils.equals(rechargeMethod, rhs.rechargeMethod)) return false;
		if(!ObjectUtils.equals(busiSpecId, rhs.busiSpecId)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(busiResult)
		.append(channelId)
		.append(busiPlanId)
		.append(rechargeMethod)
		.append(busiSpecId)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{busiSpecId,rechargeMethod,channelId,busiResult,notificationId,busiPlanId}
}