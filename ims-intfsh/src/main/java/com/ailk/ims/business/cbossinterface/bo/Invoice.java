package com.ailk.ims.business.cbossinterface.bo;

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
@XmlType(propOrder={"RetCode","RetMsg","ContractNo","MobileNo","AbisDate","TranAmt","QryBankTranTime","QryBankTranDate","QryBankTranLogNo","QryMerchantTranDate","QryMerchantTranTime","QryMerchantTranLogNo"})
public class Invoice implements IComplexEntity{


	@XmlElement(name="RetCode")
	private String RetCode;

	@XmlElement(name="RetMsg")
	private String RetMsg;

	@XmlElement(name="ContractNo")
	private String ContractNo;

	@XmlElement(name="MobileNo")
	private String MobileNo;

	@XmlElement(name="AbisDate")
	private String AbisDate;

	@XmlElement(name="TranAmt")
	private String TranAmt;

	@XmlElement(name="QryBankTranTime")
	private String QryBankTranTime;

	@XmlElement(name="QryBankTranDate")
	private String QryBankTranDate;

	@XmlElement(name="QryBankTranLogNo")
	private String QryBankTranLogNo;

	@XmlElement(name="QryMerchantTranDate")
	private String QryMerchantTranDate;

	@XmlElement(name="QryMerchantTranTime")
	private String QryMerchantTranTime;

	@XmlElement(name="QryMerchantTranLogNo")
	private String QryMerchantTranLogNo;

	public void setRetCode(String obj){
		this.RetCode = obj;
	}

	public String getRetCode(){
		return RetCode;
	}

	public void setRetMsg(String obj){
		this.RetMsg = obj;
	}

	public String getRetMsg(){
		return RetMsg;
	}

	public void setContractNo(String obj){
		this.ContractNo = obj;
	}

	public String getContractNo(){
		return ContractNo;
	}

	public void setMobileNo(String obj){
		this.MobileNo = obj;
	}

	public String getMobileNo(){
		return MobileNo;
	}

	public void setAbisDate(String obj){
		this.AbisDate = obj;
	}

	public String getAbisDate(){
		return AbisDate;
	}

	public void setTranAmt(String obj){
		this.TranAmt = obj;
	}

	public String getTranAmt(){
		return TranAmt;
	}

	public void setQryBankTranTime(String obj){
		this.QryBankTranTime = obj;
	}

	public String getQryBankTranTime(){
		return QryBankTranTime;
	}

	public void setQryBankTranDate(String obj){
		this.QryBankTranDate = obj;
	}

	public String getQryBankTranDate(){
		return QryBankTranDate;
	}

	public void setQryBankTranLogNo(String obj){
		this.QryBankTranLogNo = obj;
	}

	public String getQryBankTranLogNo(){
		return QryBankTranLogNo;
	}

	public void setQryMerchantTranDate(String obj){
		this.QryMerchantTranDate = obj;
	}

	public String getQryMerchantTranDate(){
		return QryMerchantTranDate;
	}

	public void setQryMerchantTranTime(String obj){
		this.QryMerchantTranTime = obj;
	}

	public String getQryMerchantTranTime(){
		return QryMerchantTranTime;
	}

	public void setQryMerchantTranLogNo(String obj){
		this.QryMerchantTranLogNo = obj;
	}

	public String getQryMerchantTranLogNo(){
		return QryMerchantTranLogNo;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		Invoice rhs=(Invoice)rhs0;
		if(!ObjectUtils.equals(RetCode, rhs.RetCode)) return false;
		if(!ObjectUtils.equals(RetMsg, rhs.RetMsg)) return false;
		if(!ObjectUtils.equals(ContractNo, rhs.ContractNo)) return false;
		if(!ObjectUtils.equals(MobileNo, rhs.MobileNo)) return false;
		if(!ObjectUtils.equals(AbisDate, rhs.AbisDate)) return false;
		if(!ObjectUtils.equals(TranAmt, rhs.TranAmt)) return false;
		if(!ObjectUtils.equals(QryBankTranTime, rhs.QryBankTranTime)) return false;
		if(!ObjectUtils.equals(QryBankTranDate, rhs.QryBankTranDate)) return false;
		if(!ObjectUtils.equals(QryBankTranLogNo, rhs.QryBankTranLogNo)) return false;
		if(!ObjectUtils.equals(QryMerchantTranDate, rhs.QryMerchantTranDate)) return false;
		if(!ObjectUtils.equals(QryMerchantTranTime, rhs.QryMerchantTranTime)) return false;
		if(!ObjectUtils.equals(QryMerchantTranLogNo, rhs.QryMerchantTranLogNo)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(RetCode)
		.append(RetMsg)
		.append(ContractNo)
		.append(MobileNo)
		.append(AbisDate)
		.append(TranAmt)
		.append(QryBankTranTime)
		.append(QryBankTranDate)
		.append(QryBankTranLogNo)
		.append(QryMerchantTranDate)
		.append(QryMerchantTranTime)
		.append(QryMerchantTranLogNo)
		.toHashCode();
	}


}