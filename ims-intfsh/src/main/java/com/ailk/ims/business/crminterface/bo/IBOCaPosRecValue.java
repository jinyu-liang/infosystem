package com.ailk.ims.business.crminterface.bo;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import jef.codegen.support.NotModified;

import com.ailk.easyframe.web.common.dal.IComplexEntity;
/**
 * This class is generated automatically by Asiainfo-Linkage EasyFrame.
 */
@NotModified
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"OldReference","AcctId","FileFlag","CardFlag","ResF012","Ext1","ResF011","SoOrgId","RecDate","BusiSpecId","SpecName","TradeTime","Issbank","TraType","Balance","ResF013","OldTrace","RecSts","SoCountyCode","ReprtCount","SettelDate","OldBatch","Amount","Trk2Len","ExpireDate","IsCancelled","Ext2","RejCode","Trk3","Trk2","Tobossstate","ResF042","RspCode","Orgno","ResF037","ProgressivePayment","ResF041","CheckFlag","CheckFileId","Stans","Bankname","PosType","Pin","Cardno","HostTraceId","Casher","SoNbr","OldDate","Trk3Len","InfoChannel","RolData","Counter","SoRegionCode","Authorright","AuthNo","ResF060","Remark"})
public class IBOCaPosRecValue implements IComplexEntity{


	@XmlElement(name="OldReference")
	private String OldReference;

	@XmlElement(name="AcctId")
	private String AcctId;

	@XmlElement(name="FileFlag")
	private String FileFlag;

	@XmlElement(name="CardFlag")
	private String CardFlag;

	@XmlElement(name="ResF012")
	private String ResF012;

	@XmlElement(name="Ext1")
	private String Ext1;

	@XmlElement(name="ResF011")
	private String ResF011;

	@XmlElement(name="SoOrgId")
	private String SoOrgId;

	@XmlElement(name="RecDate")
	private String RecDate;

	@XmlElement(name="BusiSpecId")
	private String BusiSpecId;

	@XmlElement(name="SpecName")
	private String SpecName;

	@XmlElement(name="TradeTime")
	private String TradeTime;

	@XmlElement(name="Issbank")
	private String Issbank;

	@XmlElement(name="TraType")
	private String TraType;

	@XmlElement(name="Balance")
	private String Balance;

	@XmlElement(name="ResF013")
	private String ResF013;

	@XmlElement(name="OldTrace")
	private String OldTrace;

	@XmlElement(name="RecSts")
	private String RecSts;

	@XmlElement(name="SoCountyCode")
	private String SoCountyCode;

	@XmlElement(name="ReprtCount")
	private String ReprtCount;

	@XmlElement(name="SettelDate")
	private String SettelDate;

	@XmlElement(name="OldBatch")
	private String OldBatch;

	@XmlElement(name="Amount")
	private String Amount;

	@XmlElement(name="Trk2Len")
	private String Trk2Len;

	@XmlElement(name="ExpireDate")
	private String ExpireDate;

	@XmlElement(name="IsCancelled")
	private String IsCancelled;

	@XmlElement(name="Ext2")
	private String Ext2;

	@XmlElement(name="RejCode")
	private String RejCode;

	@XmlElement(name="Trk3")
	private String Trk3;

	@XmlElement(name="Trk2")
	private String Trk2;

	@XmlElement(name="Tobossstate")
	private String Tobossstate;

	@XmlElement(name="ResF042")
	private String ResF042;

	@XmlElement(name="RspCode")
	private String RspCode;

	@XmlElement(name="Orgno")
	private String Orgno;

	@XmlElement(name="ResF037")
	private String ResF037;

	@XmlElement(name="ProgressivePayment")
	private String ProgressivePayment;

	@XmlElement(name="ResF041")
	private String ResF041;

	@XmlElement(name="CheckFlag")
	private String CheckFlag;

	@XmlElement(name="CheckFileId")
	private String CheckFileId;

	@XmlElement(name="Stans")
	private String Stans;

	@XmlElement(name="Bankname")
	private String Bankname;

	@XmlElement(name="PosType")
	private String PosType;

	@XmlElement(name="Pin")
	private String Pin;

	@XmlElement(name="Cardno")
	private String Cardno;

	@XmlElement(name="HostTraceId")
	private String HostTraceId;

	@XmlElement(name="Casher")
	private String Casher;

	@XmlElement(name="SoNbr")
	private String SoNbr;

	@XmlElement(name="OldDate")
	private String OldDate;

	@XmlElement(name="Trk3Len")
	private String Trk3Len;

	@XmlElement(name="InfoChannel")
	private String InfoChannel;

	@XmlElement(name="RolData")
	private String RolData;

	@XmlElement(name="Counter")
	private String Counter;

	@XmlElement(name="SoRegionCode")
	private String SoRegionCode;

	@XmlElement(name="Authorright")
	private String Authorright;

	@XmlElement(name="AuthNo")
	private String AuthNo;

	@XmlElement(name="ResF060")
	private String ResF060;

	@XmlElement(name="Remark")
	private String Remark;

	public void setOldReference(String obj){
		this.OldReference = obj;
	}

	public String getOldReference(){
		return OldReference;
	}

	public void setAcctId(String obj){
		this.AcctId = obj;
	}

	public String getAcctId(){
		return AcctId;
	}

	public void setFileFlag(String obj){
		this.FileFlag = obj;
	}

	public String getFileFlag(){
		return FileFlag;
	}

	public void setCardFlag(String obj){
		this.CardFlag = obj;
	}

	public String getCardFlag(){
		return CardFlag;
	}

	public void setResF012(String obj){
		this.ResF012 = obj;
	}

	public String getResF012(){
		return ResF012;
	}

	public void setExt1(String obj){
		this.Ext1 = obj;
	}

	public String getExt1(){
		return Ext1;
	}

	public void setResF011(String obj){
		this.ResF011 = obj;
	}

	public String getResF011(){
		return ResF011;
	}

	public void setSoOrgId(String obj){
		this.SoOrgId = obj;
	}

	public String getSoOrgId(){
		return SoOrgId;
	}

	public void setRecDate(String obj){
		this.RecDate = obj;
	}

	public String getRecDate(){
		return RecDate;
	}

	public void setBusiSpecId(String obj){
		this.BusiSpecId = obj;
	}

	public String getBusiSpecId(){
		return BusiSpecId;
	}

	public void setSpecName(String obj){
		this.SpecName = obj;
	}

	public String getSpecName(){
		return SpecName;
	}

	public void setTradeTime(String obj){
		this.TradeTime = obj;
	}

	public String getTradeTime(){
		return TradeTime;
	}

	public void setIssbank(String obj){
		this.Issbank = obj;
	}

	public String getIssbank(){
		return Issbank;
	}

	public void setTraType(String obj){
		this.TraType = obj;
	}

	public String getTraType(){
		return TraType;
	}

	public void setBalance(String obj){
		this.Balance = obj;
	}

	public String getBalance(){
		return Balance;
	}

	public void setResF013(String obj){
		this.ResF013 = obj;
	}

	public String getResF013(){
		return ResF013;
	}

	public void setOldTrace(String obj){
		this.OldTrace = obj;
	}

	public String getOldTrace(){
		return OldTrace;
	}

	public void setRecSts(String obj){
		this.RecSts = obj;
	}

	public String getRecSts(){
		return RecSts;
	}

	public void setSoCountyCode(String obj){
		this.SoCountyCode = obj;
	}

	public String getSoCountyCode(){
		return SoCountyCode;
	}

	public void setReprtCount(String obj){
		this.ReprtCount = obj;
	}

	public String getReprtCount(){
		return ReprtCount;
	}

	public void setSettelDate(String obj){
		this.SettelDate = obj;
	}

	public String getSettelDate(){
		return SettelDate;
	}

	public void setOldBatch(String obj){
		this.OldBatch = obj;
	}

	public String getOldBatch(){
		return OldBatch;
	}

	public void setAmount(String obj){
		this.Amount = obj;
	}

	public String getAmount(){
		return Amount;
	}

	public void setTrk2Len(String obj){
		this.Trk2Len = obj;
	}

	public String getTrk2Len(){
		return Trk2Len;
	}

	public void setExpireDate(String obj){
		this.ExpireDate = obj;
	}

	public String getExpireDate(){
		return ExpireDate;
	}

	public void setIsCancelled(String obj){
		this.IsCancelled = obj;
	}

	public String getIsCancelled(){
		return IsCancelled;
	}

	public void setExt2(String obj){
		this.Ext2 = obj;
	}

	public String getExt2(){
		return Ext2;
	}

	public void setRejCode(String obj){
		this.RejCode = obj;
	}

	public String getRejCode(){
		return RejCode;
	}

	public void setTrk3(String obj){
		this.Trk3 = obj;
	}

	public String getTrk3(){
		return Trk3;
	}

	public void setTrk2(String obj){
		this.Trk2 = obj;
	}

	public String getTrk2(){
		return Trk2;
	}

	public void setTobossstate(String obj){
		this.Tobossstate = obj;
	}

	public String getTobossstate(){
		return Tobossstate;
	}

	public void setResF042(String obj){
		this.ResF042 = obj;
	}

	public String getResF042(){
		return ResF042;
	}

	public void setRspCode(String obj){
		this.RspCode = obj;
	}

	public String getRspCode(){
		return RspCode;
	}

	public void setOrgno(String obj){
		this.Orgno = obj;
	}

	public String getOrgno(){
		return Orgno;
	}

	public void setResF037(String obj){
		this.ResF037 = obj;
	}

	public String getResF037(){
		return ResF037;
	}

	public void setProgressivePayment(String obj){
		this.ProgressivePayment = obj;
	}

	public String getProgressivePayment(){
		return ProgressivePayment;
	}

	public void setResF041(String obj){
		this.ResF041 = obj;
	}

	public String getResF041(){
		return ResF041;
	}

	public void setCheckFlag(String obj){
		this.CheckFlag = obj;
	}

	public String getCheckFlag(){
		return CheckFlag;
	}

	public void setCheckFileId(String obj){
		this.CheckFileId = obj;
	}

	public String getCheckFileId(){
		return CheckFileId;
	}

	public void setStans(String obj){
		this.Stans = obj;
	}

	public String getStans(){
		return Stans;
	}

	public void setBankname(String obj){
		this.Bankname = obj;
	}

	public String getBankname(){
		return Bankname;
	}

	public void setPosType(String obj){
		this.PosType = obj;
	}

	public String getPosType(){
		return PosType;
	}

	public void setPin(String obj){
		this.Pin = obj;
	}

	public String getPin(){
		return Pin;
	}

	public void setCardno(String obj){
		this.Cardno = obj;
	}

	public String getCardno(){
		return Cardno;
	}

	public void setHostTraceId(String obj){
		this.HostTraceId = obj;
	}

	public String getHostTraceId(){
		return HostTraceId;
	}

	public void setCasher(String obj){
		this.Casher = obj;
	}

	public String getCasher(){
		return Casher;
	}

	public void setSoNbr(String obj){
		this.SoNbr = obj;
	}

	public String getSoNbr(){
		return SoNbr;
	}

	public void setOldDate(String obj){
		this.OldDate = obj;
	}

	public String getOldDate(){
		return OldDate;
	}

	public void setTrk3Len(String obj){
		this.Trk3Len = obj;
	}

	public String getTrk3Len(){
		return Trk3Len;
	}

	public void setInfoChannel(String obj){
		this.InfoChannel = obj;
	}

	public String getInfoChannel(){
		return InfoChannel;
	}

	public void setRolData(String obj){
		this.RolData = obj;
	}

	public String getRolData(){
		return RolData;
	}

	public void setCounter(String obj){
		this.Counter = obj;
	}

	public String getCounter(){
		return Counter;
	}

	public void setSoRegionCode(String obj){
		this.SoRegionCode = obj;
	}

	public String getSoRegionCode(){
		return SoRegionCode;
	}

	public void setAuthorright(String obj){
		this.Authorright = obj;
	}

	public String getAuthorright(){
		return Authorright;
	}

	public void setAuthNo(String obj){
		this.AuthNo = obj;
	}

	public String getAuthNo(){
		return AuthNo;
	}

	public void setResF060(String obj){
		this.ResF060 = obj;
	}

	public String getResF060(){
		return ResF060;
	}

	public void setRemark(String obj){
		this.Remark = obj;
	}

	public String getRemark(){
		return Remark;
	}
}