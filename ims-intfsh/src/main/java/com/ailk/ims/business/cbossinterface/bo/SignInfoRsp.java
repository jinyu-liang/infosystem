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
@XmlType(propOrder={"RetCode","RetMsg","BankTranDate","BankTranTime","BankTranLogNo","TranAmt","AccTyp"})
public class SignInfoRsp implements IComplexEntity{


	@XmlElement(name="RetCode")
	private String RetCode;

	@XmlElement(name="RetMsg")
	private String RetMsg;

	@XmlElement(name="BankTranDate")
	private String BankTranDate;

	@XmlElement(name="BankTranTime")
	private String BankTranTime;

	@XmlElement(name="BankTranLogNo")
	private String BankTranLogNo;

	@XmlElement(name="TranAmt")
	private String TranAmt;

	@XmlElement(name="AccTyp")
	private String AccTyp;

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

	public void setBankTranDate(String obj){
		this.BankTranDate = obj;
	}

	public String getBankTranDate(){
		return BankTranDate;
	}

	public void setBankTranTime(String obj){
		this.BankTranTime = obj;
	}

	public String getBankTranTime(){
		return BankTranTime;
	}

	public void setBankTranLogNo(String obj){
		this.BankTranLogNo = obj;
	}

	public String getBankTranLogNo(){
		return BankTranLogNo;
	}

	public void setTranAmt(String obj){
		this.TranAmt = obj;
	}

	public String getTranAmt(){
		return TranAmt;
	}

	public void setAccTyp(String obj){
		this.AccTyp = obj;
	}

	public String getAccTyp(){
		return AccTyp;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		SignInfoRsp rhs=(SignInfoRsp)rhs0;
		if(!ObjectUtils.equals(RetCode, rhs.RetCode)) return false;
		if(!ObjectUtils.equals(RetMsg, rhs.RetMsg)) return false;
		if(!ObjectUtils.equals(BankTranDate, rhs.BankTranDate)) return false;
		if(!ObjectUtils.equals(BankTranTime, rhs.BankTranTime)) return false;
		if(!ObjectUtils.equals(BankTranLogNo, rhs.BankTranLogNo)) return false;
		if(!ObjectUtils.equals(TranAmt, rhs.TranAmt)) return false;
		if(!ObjectUtils.equals(AccTyp, rhs.AccTyp)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(RetCode)
		.append(RetMsg)
		.append(BankTranDate)
		.append(BankTranTime)
		.append(BankTranLogNo)
		.append(TranAmt)
		.append(AccTyp)
		.toHashCode();
	}


}