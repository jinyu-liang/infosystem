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
@XmlType(propOrder={"RetCode","RetMsg","BankLogNo","BankDate","CutoffDay"})
public class BankPayRsp implements IComplexEntity{


	@XmlElement(name="RetCode")
	private String RetCode;

	@XmlElement(name="RetMsg")
	private String RetMsg;

	@XmlElement(name="BankLogNo")
	private String BankLogNo;

	@XmlElement(name="BankDate")
	private String BankDate;

	@XmlElement(name="CutoffDay")
	private String CutoffDay;

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

	public void setBankLogNo(String obj){
		this.BankLogNo = obj;
	}

	public String getBankLogNo(){
		return BankLogNo;
	}

	public void setBankDate(String obj){
		this.BankDate = obj;
	}

	public String getBankDate(){
		return BankDate;
	}

	public void setCutoffDay(String obj){
		this.CutoffDay = obj;
	}

	public String getCutoffDay(){
		return CutoffDay;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		BankPayRsp rhs=(BankPayRsp)rhs0;
		if(!ObjectUtils.equals(RetCode, rhs.RetCode)) return false;
		if(!ObjectUtils.equals(RetMsg, rhs.RetMsg)) return false;
		if(!ObjectUtils.equals(BankLogNo, rhs.BankLogNo)) return false;
		if(!ObjectUtils.equals(BankDate, rhs.BankDate)) return false;
		if(!ObjectUtils.equals(CutoffDay, rhs.CutoffDay)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(RetCode)
		.append(RetMsg)
		.append(BankLogNo)
		.append(BankDate)
		.append(CutoffDay)
		.toHashCode();
	}


}