package com.ailk.openbilling.persistence.acct.entity;

import javax.persistence.Table;
import jef.database.DataObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Date;
import jef.codegen.support.NotModified;
/**
 * This class was generated by EasyFrame according to the table in database.
 * You need to modify the type of primary key field, to the strategy your own.
 * 
 */
@NotModified
@Entity
@Table(schema="AD",name="CA_BILLING_PLAN")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"billingPlanId","acctId","cycleSpecId","billFormatId","rangeSpecId","paymentPlanId","validDate","expireDate","soNbr","createDate","soDate","firstBillDate","cycleNum"})
public class CaBillingPlan extends DataObject{


	/**
	 * ณ๖ีสผฦปฎฑเบล
	 */
	@Column(name="BILLING_PLAN_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="billingPlanId")
	private Long billingPlanId;

	/**
	 * ีสปงฑเบล
	 */
	@Column(name="ACCT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="acctId")
	private Long acctId;

	/**
	 * ีสฦฺนๆธ๑ฑเบล
	 */
	@Column(name="CYCLE_SPEC_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="cycleSpecId")
	private Long cycleSpecId;

	/**
	 * ีสตฅธ๑สฝด๚ย๋
	 */
	@Column(name="BILL_FORMAT_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="billFormatId")
	private Integer billFormatId;

	/**
	 * ทถฮงนๆธ๑ฑเบล
	 */
	@Column(name="RANGE_SPEC_ID",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="rangeSpecId")
	private Integer rangeSpecId;

	/**
	 * ึงธถผฦปฎฑเบล
	 */
	@Column(name="PAYMENT_PLAN_ID",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="paymentPlanId")
	private Long paymentPlanId;

	/**
	 * ษ๚ะงฦฺ
	 */
	@Column(name="VALID_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="validDate")
	private Date validDate;

	/**
	 * สงะงฦฺ
	 */
	@Column(name="EXPIRE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="expireDate")
	private Date expireDate;

	/**
	 * สภํนคตฅ
	 */
	@Column(name="SO_NBR",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="soNbr")
	private Long soNbr;

	/**
	 * ตฑวฐผวยผตฤฒ๚ษ๚สฑผไ
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	/**
	 * ตฑวฐผวยผะธฤสฑผไ
	 */
	@Column(name="SO_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="soDate")
	private Date soDate;

	@Column(name="FIRST_BILL_DATE",columnDefinition="TimeStamp")
	@XmlElement(name="firstBillDate")
	private Date firstBillDate;

	@Column(name="CYCLE_NUM",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="cycleNum")
	private Integer cycleNum;

	public void setBillingPlanId(Long obj){
		this.billingPlanId = obj;
	}

	public Long getBillingPlanId(){
		return billingPlanId;
	}

	public void setAcctId(Long obj){
		this.acctId = obj;
	}

	public Long getAcctId(){
		return acctId;
	}

	public void setCycleSpecId(Long obj){
		this.cycleSpecId = obj;
	}

	public Long getCycleSpecId(){
		return cycleSpecId;
	}

	public void setBillFormatId(Integer obj){
		this.billFormatId = obj;
	}

	public Integer getBillFormatId(){
		return billFormatId;
	}

	public void setRangeSpecId(Integer obj){
		this.rangeSpecId = obj;
	}

	public Integer getRangeSpecId(){
		return rangeSpecId;
	}

	public void setPaymentPlanId(Long obj){
		this.paymentPlanId = obj;
	}

	public Long getPaymentPlanId(){
		return paymentPlanId;
	}

	public void setValidDate(Date obj){
		this.validDate = obj;
	}

	public Date getValidDate(){
		return validDate;
	}

	public void setExpireDate(Date obj){
		this.expireDate = obj;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setSoNbr(Long obj){
		this.soNbr = obj;
	}

	public Long getSoNbr(){
		return soNbr;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setSoDate(Date obj){
		this.soDate = obj;
	}

	public Date getSoDate(){
		return soDate;
	}

	public void setFirstBillDate(Date obj){
		this.firstBillDate = obj;
	}

	public Date getFirstBillDate(){
		return firstBillDate;
	}

	public void setCycleNum(Integer obj){
		this.cycleNum = obj;
	}

	public Integer getCycleNum(){
		return cycleNum;
	}

	public CaBillingPlan(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaBillingPlan rhs=(CaBillingPlan)rhs0;
		if(!ObjectUtils.equals(billingPlanId, rhs.billingPlanId)) return false;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(billFormatId, rhs.billFormatId)) return false;
		if(!ObjectUtils.equals(rangeSpecId, rhs.rangeSpecId)) return false;
		if(!ObjectUtils.equals(paymentPlanId, rhs.paymentPlanId)) return false;
		if(!ObjectUtils.equals(validDate, rhs.validDate)) return false;
		if(!ObjectUtils.equals(expireDate, rhs.expireDate)) return false;
		if(!ObjectUtils.equals(soNbr, rhs.soNbr)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(soDate, rhs.soDate)) return false;
		if(!ObjectUtils.equals(firstBillDate, rhs.firstBillDate)) return false;
		if(!ObjectUtils.equals(cycleNum, rhs.cycleNum)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(billingPlanId)
		.append(acctId)
		.append(cycleSpecId)
		.append(billFormatId)
		.append(rangeSpecId)
		.append(paymentPlanId)
		.append(validDate)
		.append(expireDate)
		.append(soNbr)
		.append(createDate)
		.append(soDate)
		.append(firstBillDate)
		.append(cycleNum)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{billingPlanId,acctId,cycleSpecId,billFormatId,rangeSpecId,paymentPlanId,validDate,expireDate,soNbr,createDate,soDate,firstBillDate,cycleNum}
}