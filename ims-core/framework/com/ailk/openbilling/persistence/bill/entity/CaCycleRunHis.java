package com.ailk.openbilling.persistence.bill.entity;

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
 */
@NotModified
@Entity
@Table(schema="ad",name="CA_CYCLE_RUN_HIS")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"acctId","cycleSpecId","realCycleSpecId","lastCycleBegin","lastCycleEnd","currCycleBegin","currCycleEnd","modDate","createDate","billDate","cycleSts","billRunSts","billDataSts","confirmBillSts"})
public class CaCycleRunHis extends DataObject{


	/**
	 * A unique identifier of account.
	 */
	@Column(name="ACCT_ID",precision=15,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="acctId")
	private long acctId;

	/**
	 * Bill cycle specification presented externally. If the current bill cycle type is special cycle such as 99, then the last normal bill cycle shall be recorded or it shall be modified the same as REAL_CYCLE_SPEC_ID
	 */
	@Column(name="CYCLE_SPEC_ID",precision=9,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="cycleSpecId")
	private int cycleSpecId;

	/**
	 * Real bill cycle information
	 */
	@Column(name="REAL_CYCLE_SPEC_ID",precision=9,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="realCycleSpecId")
	private int realCycleSpecId;

	/**
	 * Start date of last  bill cycle
	 */
	@Column(name="LAST_CYCLE_BEGIN",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="lastCycleBegin")
	private int lastCycleBegin;

	/**
	 * End date of last  bill cycle
	 */
	@Column(name="LAST_CYCLE_END",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="lastCycleEnd")
	private int lastCycleEnd;

	/**
	 * Start date of the current  bill cycle
	 */
	@Column(name="CURR_CYCLE_BEGIN",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="currCycleBegin")
	private int currCycleBegin;

	/**
	 * End date of the current  bill cycle
	 */
	@Column(name="CURR_CYCLE_END",precision=8,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="currCycleEnd")
	private int currCycleEnd;

	/**
	 * modify date
	 */
	@Column(name="MOD_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="modDate")
	private Date modDate;

	/**
	 * Generating date of the current record
	 */
	@Column(name="CREATE_DATE",columnDefinition="TimeStamp",nullable=false)
	@XmlElement(name="createDate")
	private Date createDate;

	@Column(name="BILL_DATE",precision=8,columnDefinition="NUMBER")
	@XmlElement(name="billDate")
	private Integer billDate;

	/**
	 * 0-Remain 1-Normal, bill run in regular billing cycle 2-Suppressฃจno bill runฃฉ 3-Un-suppress 4-Hot bill statusฃจno bill runฃฉฃจthe following is related to bill run processฃฉ 11-Bill importing and exporting finished, bill has been loaded into database bill table 12-Error occurs in bill run
	 */
	@Column(name="CYCLE_STS",precision=2,columnDefinition="NUMBER",nullable=false)
	@XmlElement(name="cycleSts")
	private int cycleSts;

	/**
	 * 0-init  1- finished export_billฃจตผีสฝแส๘ฃฌี๛ธ๖bill runิหะะฝแส๘ฃฉ  2- runningฃจฟชสผิหะะbill runฃฌฃจภีสละถฯัำณูปฐตฅฒปิสะํดฆภํฃฉ  3- finished billrun(ำลปผฦหใอ๊ณษฃฌฟษาิตผีส)
	 */
	@Column(name="BILL_RUN_STS",precision=2,columnDefinition="NUMBER")
	@XmlElement(name="billRunSts")
	private Integer billRunSts;

	/**
	 * 0-init 1- finished 2- running 3- waiting for receipt
	 */
	@Column(name="BILL_DATA_STS",precision=2,columnDefinition="NUMBER")
	@XmlElement(name="billDataSts")
	private Integer billDataSts;

	@Column(name="CONFIRM_BILL_STS",precision=2,columnDefinition="NUMBER")
	@XmlElement(name="confirmBillSts")
	private Integer confirmBillSts;

	public void setAcctId(long obj){
		this.acctId = obj;
	}

	public long getAcctId(){
		return acctId;
	}

	public void setCycleSpecId(int obj){
		this.cycleSpecId = obj;
	}

	public int getCycleSpecId(){
		return cycleSpecId;
	}

	public void setRealCycleSpecId(int obj){
		this.realCycleSpecId = obj;
	}

	public int getRealCycleSpecId(){
		return realCycleSpecId;
	}

	public void setLastCycleBegin(int obj){
		this.lastCycleBegin = obj;
	}

	public int getLastCycleBegin(){
		return lastCycleBegin;
	}

	public void setLastCycleEnd(int obj){
		this.lastCycleEnd = obj;
	}

	public int getLastCycleEnd(){
		return lastCycleEnd;
	}

	public void setCurrCycleBegin(int obj){
		this.currCycleBegin = obj;
	}

	public int getCurrCycleBegin(){
		return currCycleBegin;
	}

	public void setCurrCycleEnd(int obj){
		this.currCycleEnd = obj;
	}

	public int getCurrCycleEnd(){
		return currCycleEnd;
	}

	public void setModDate(Date obj){
		this.modDate = obj;
	}

	public Date getModDate(){
		return modDate;
	}

	public void setCreateDate(Date obj){
		this.createDate = obj;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setBillDate(Integer obj){
		this.billDate = obj;
	}

	public Integer getBillDate(){
		return billDate;
	}

	public void setCycleSts(int obj){
		this.cycleSts = obj;
	}

	public int getCycleSts(){
		return cycleSts;
	}

	public void setBillRunSts(Integer obj){
		this.billRunSts = obj;
	}

	public Integer getBillRunSts(){
		return billRunSts;
	}

	public void setBillDataSts(Integer obj){
		this.billDataSts = obj;
	}

	public Integer getBillDataSts(){
		return billDataSts;
	}

	public void setConfirmBillSts(Integer obj){
		this.confirmBillSts = obj;
	}

	public Integer getConfirmBillSts(){
		return confirmBillSts;
	}

	public CaCycleRunHis(){
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CaCycleRunHis rhs=(CaCycleRunHis)rhs0;
		if(!ObjectUtils.equals(acctId, rhs.acctId)) return false;
		if(!ObjectUtils.equals(cycleSpecId, rhs.cycleSpecId)) return false;
		if(!ObjectUtils.equals(realCycleSpecId, rhs.realCycleSpecId)) return false;
		if(!ObjectUtils.equals(lastCycleBegin, rhs.lastCycleBegin)) return false;
		if(!ObjectUtils.equals(lastCycleEnd, rhs.lastCycleEnd)) return false;
		if(!ObjectUtils.equals(currCycleBegin, rhs.currCycleBegin)) return false;
		if(!ObjectUtils.equals(currCycleEnd, rhs.currCycleEnd)) return false;
		if(!ObjectUtils.equals(modDate, rhs.modDate)) return false;
		if(!ObjectUtils.equals(createDate, rhs.createDate)) return false;
		if(!ObjectUtils.equals(billDate, rhs.billDate)) return false;
		if(!ObjectUtils.equals(cycleSts, rhs.cycleSts)) return false;
		if(!ObjectUtils.equals(billRunSts, rhs.billRunSts)) return false;
		if(!ObjectUtils.equals(billDataSts, rhs.billDataSts)) return false;
		if(!ObjectUtils.equals(confirmBillSts, rhs.confirmBillSts)) return false;
		return super.isEquals(rhs);
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(acctId)
		.append(cycleSpecId)
		.append(realCycleSpecId)
		.append(lastCycleBegin)
		.append(lastCycleEnd)
		.append(currCycleBegin)
		.append(currCycleEnd)
		.append(modDate)
		.append(createDate)
		.append(billDate)
		.append(cycleSts)
		.append(billRunSts)
		.append(billDataSts)
		.append(confirmBillSts)
		.append(super.getHashCode())
		.toHashCode();
	}


public enum Field implements jef.database.Field{acctId,cycleSpecId,realCycleSpecId,lastCycleBegin,lastCycleEnd,currCycleBegin,currCycleEnd,modDate,createDate,billDate,cycleSts,billRunSts,billDataSts,confirmBillSts}
}