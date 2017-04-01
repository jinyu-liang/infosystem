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
@XmlType(propOrder={"IDType","IDValue","TransactionID","ActionDate","RspCode","RspInfo"})
public class PaymentRsp implements IComplexEntity{


	@XmlElement(name="IDType")
	private String IDType;

	@XmlElement(name="IDValue")
	private String IDValue;

	@XmlElement(name="TransactionID")
	private String TransactionID;

	public String getIDType()
    {
        return IDType;
    }

    public void setIDType(String iDType)
    {
        IDType = iDType;
    }

    public String getIDValue()
    {
        return IDValue;
    }

    public void setIDValue(String iDValue)
    {
        IDValue = iDValue;
    }

    public String getTransactionID()
    {
        return TransactionID;
    }

    public void setTransactionID(String transactionID)
    {
        TransactionID = transactionID;
    }

    public String getActionDate()
    {
        return ActionDate;
    }

    public void setActionDate(String actionDate)
    {
        ActionDate = actionDate;
    }

    public String getRspCode()
    {
        return RspCode;
    }

    public void setRspCode(String rspCode)
    {
        RspCode = rspCode;
    }

    public String getRspInfo()
    {
        return RspInfo;
    }

    public void setRspInfo(String rspInfo)
    {
        RspInfo = rspInfo;
    }

    @XmlElement(name="ActionDate")
	private String ActionDate;

	@XmlElement(name="RspCode")
	private String RspCode;
	
	@XmlElement(name="RspInfo")
    private String RspInfo;

	public boolean equals(final Object rhs0){
        if (rhs0 == null)return false;
        PaymentRsp rhs=(PaymentRsp)rhs0;
        if(!ObjectUtils.equals(IDType, rhs.IDType)) return false;
        if(!ObjectUtils.equals(IDValue, rhs.IDValue)) return false;
        if(!ObjectUtils.equals(TransactionID, rhs.TransactionID)) return false;
        if(!ObjectUtils.equals(ActionDate, rhs.ActionDate)) return false;
        if(!ObjectUtils.equals(RspCode, rhs.RspCode)) return false;
        if(!ObjectUtils.equals(RspInfo, rhs.RspInfo)) return false;
        return true;
    }

    public int hashCode(){
        return new HashCodeBuilder()
        .append(IDType)
        .append(IDValue)
        .append(TransactionID)
        .append(ActionDate)
        .append(RspCode)
        .append(RspInfo)
        .toHashCode();
    }


}