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
@XmlType(propOrder={"OriReqSys","OriActionDate","OriTransactionID","RevokeReason","TransactionID","SettleDate"})
public class RefundsReq implements IComplexEntity{


	@XmlElement(name="OriReqSys")
	private String OriReqSys;

	@XmlElement(name="OriActionDate")
	private String OriActionDate;

	@XmlElement(name="OriTransactionID")
	private String OriTransactionID;

	@XmlElement(name="RevokeReason")
	private String RevokeReason;
	
	@XmlElement(name="TransactionID")
    private String TransactionID;
	
    @XmlElement(name="SettleDate")
    private String SettleDate;
	

	public void setOriReqSys(String obj){
		this.OriReqSys = obj;
	}

	public String getOriReqSys(){
		return OriReqSys;
	}

	public void setOriActionDate(String obj){
		this.OriActionDate = obj;
	}

	public String getOriActionDate(){
		return OriActionDate;
	}

	public void setOriTransactionID(String obj){
		this.OriTransactionID = obj;
	}

	public String getOriTransactionID(){
		return OriTransactionID;
	}

	public void setRevokeReason(String obj){
		this.RevokeReason = obj;
	}

	public String getRevokeReason(){
		return RevokeReason;
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
		RefundsReq rhs=(RefundsReq)rhs0;
		if(!ObjectUtils.equals(OriReqSys, rhs.OriReqSys)) return false;
		if(!ObjectUtils.equals(OriActionDate, rhs.OriActionDate)) return false;
		if(!ObjectUtils.equals(OriTransactionID, rhs.OriTransactionID)) return false;
		if(!ObjectUtils.equals(RevokeReason, rhs.RevokeReason)) return false;
		if(!ObjectUtils.equals(TransactionID, rhs.TransactionID)) return false;
		if(!ObjectUtils.equals(SettleDate, rhs.SettleDate)) return false;
		return true;
	}

	public int hashCode(){
		return new HashCodeBuilder()
		.append(OriReqSys)
		.append(OriActionDate)
		.append(OriTransactionID)
		.append(RevokeReason)
		.append(TransactionID)
		.append(SettleDate)
		.toHashCode();
	}


}