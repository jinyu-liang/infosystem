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
@XmlType(propOrder={"RetCode","RetMsg","MerchantCode","AuthDate","AuthId","CustomerName","SingleLimit","DayLimit"})
public class CcbQryBindRsp implements IComplexEntity{


	@XmlElement(name="RetCode")
	private String RetCode;

	@XmlElement(name="RetMsg")
	private String RetMsg;

	@XmlElement(name="MerchantCode")
	private String MerchantCode;

	@XmlElement(name="AuthDate")
	private String AuthDate;

	@XmlElement(name="AuthId")
	private String AuthId;

	@XmlElement(name="CustomerName")
	private String CustomerName;

	@XmlElement(name="SingleLimit")
	private String SingleLimit;

	@XmlElement(name="DayLimit")
	private String DayLimit;

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

	public void setMerchantCode(String obj){
		this.MerchantCode = obj;
	}

	public String getMerchantCode(){
		return MerchantCode;
	}

	public void setAuthDate(String obj){
		this.AuthDate = obj;
	}

	public String getAuthDate(){
		return AuthDate;
	}

	public void setAuthId(String obj){
		this.AuthId = obj;
	}

	public String getAuthId(){
		return AuthId;
	}

	public void setCustomerName(String obj){
		this.CustomerName = obj;
	}

	public String getCustomerName(){
		return CustomerName;
	}

	public void setSingleLimit(String obj){
		this.SingleLimit = obj;
	}

	public String getSingleLimit(){
		return SingleLimit;
	}

	public void setDayLimit(String obj){
		this.DayLimit = obj;
	}

	public String getDayLimit(){
		return DayLimit;
	}

	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		CcbQryBindRsp rhs=(CcbQryBindRsp)rhs0;
		if(!ObjectUtils.equals(RetCode, rhs.RetCode)) return false;
		if(!ObjectUtils.equals(RetMsg, rhs.RetMsg)) return false;
		if(!ObjectUtils.equals(MerchantCode, rhs.MerchantCode)) return false;
		if(!ObjectUtils.equals(AuthDate, rhs.AuthDate)) return false;
		if(!ObjectUtils.equals(AuthId, rhs.AuthId)) return false;
		if(!ObjectUtils.equals(CustomerName, rhs.CustomerName)) return false;
		if(!ObjectUtils.equals(SingleLimit, rhs.SingleLimit)) return false;
		if(!ObjectUtils.equals(DayLimit, rhs.DayLimit)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(RetCode)
		.append(RetMsg)
		.append(MerchantCode)
		.append(AuthDate)
		.append(AuthId)
		.append(CustomerName)
		.append(SingleLimit)
		.append(DayLimit)
		.toHashCode();
	}


}