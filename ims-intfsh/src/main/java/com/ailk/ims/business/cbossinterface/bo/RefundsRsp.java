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
@XmlType(propOrder={"OriReqSys","OriActionDate","OriTransactionID","RspCode","RspInfo","TransactionID","SettleDate"})

public class RefundsRsp implements IComplexEntity {

	@XmlElement(name="OriReqSys")
	private String OriReqSys;

    @XmlElement(name="OriActionDate")
	private String OriActionDate;

	@XmlElement(name="OriTransactionID")
	private String OriTransactionID;

	@XmlElement(name="RspCode")
	private String RspCode;

	@XmlElement(name="RspInfo")
	private String RspInfo;
	
	@XmlElement(name="TransactionID")
    private String TransactionID;

	@XmlElement(name="SettleDate")
	private String SettleDate;

	public String getOriReqSys()
    {
        return OriReqSys;
    }

    public void setOriReqSys(String oriReqSys)
    {
        OriReqSys = oriReqSys;
    }

    public String getOriActionDate()
    {
        return OriActionDate;
    }

    public void setOriActionDate(String oriActionDate)
    {
        OriActionDate = oriActionDate;
    }

    public String getOriTransactionID()
    {
        return OriTransactionID;
    }

    public void setOriTransactionID(String oriTransactionID)
    {
        OriTransactionID = oriTransactionID;
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

    public String getTransactionID()
    {
        return TransactionID;
    }

    public void setTransactionID(String transactionID)
    {
        TransactionID = transactionID;
    }

    public String getSettleDate()
    {
        return SettleDate;
    }

    public void setSettleDate(String settleDate)
    {
        SettleDate = settleDate;
    }
	public boolean equals(final Object rhs0){
		if (rhs0 == null)return false;
		RefundsRsp rhs=(RefundsRsp)rhs0;
		if(!ObjectUtils.equals(OriReqSys, rhs.OriReqSys)) return false;
		if(!ObjectUtils.equals(OriActionDate, rhs.OriActionDate)) return false;
		if(!ObjectUtils.equals(TransactionID, rhs.TransactionID)) return false;
		if(!ObjectUtils.equals(RspCode, rhs.RspCode)) return false;
		if(!ObjectUtils.equals(RspInfo, rhs.RspInfo)) return false;
		if(!ObjectUtils.equals(SettleDate, rhs.SettleDate)) return false;
        if(!ObjectUtils.equals(OriTransactionID, rhs.OriTransactionID)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(OriReqSys)
		.append(OriActionDate)
		.append(TransactionID)
		.append(RspCode)
		.append(RspInfo)
		.append(SettleDate)
        .append(OriTransactionID)
		.toHashCode();
	}
	


}
