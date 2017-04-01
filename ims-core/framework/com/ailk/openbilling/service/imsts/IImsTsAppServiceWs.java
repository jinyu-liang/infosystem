package com.ailk.openbilling.service.imsts;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebParam;
import java.util.List;
import com.ailk.openbilling.persistence.imsts.entity.STsRecord;
import com.ailk.openbilling.persistence.imsts.entity.SUserConfirm;
import com.ailk.openbilling.persistence.imsts.entity.SImsOrderProduct;
import jef.codegen.support.NotModified;
@NotModified
@WebService
@WebServiceProvider(portName="IImsTsAppService")
public interface IImsTsAppServiceWs{

	@WebMethod
	public Integer read_firstActive();
	@WebMethod
	public Integer deal_firstActive(@WebParam(name = "sCustProdSyncList") List<STsRecord> sCustProdSyncList);
	@WebMethod
	public Integer read_creditStatus();
	@WebMethod
	public Integer deal_creditStatus(@WebParam(name = "sCreditStatusSyncList") List<STsRecord> sCreditStatusSyncList);
	@WebMethod
	public Integer read_productStatus();
	@WebMethod
	public Integer deal_productStatus(@WebParam(name = "sProdStsSyncList") List<STsRecord> sProdStsSyncList);
	@WebMethod
	public Integer read_userStsSync();
	@WebMethod
	public Integer deal_userStsSync(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_barService();
	@WebMethod
	public Integer deal_barService(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_rewardInfo();
	@WebMethod
	public Integer deal_rewardInfo(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_otPromUsedUp();
	@WebMethod
	public Integer deal_otPromUsedUp(@WebParam(name = "sOtpromUsedupSyncList") List<STsRecord> sOtpromUsedupSyncList);
	@WebMethod
	public Integer read_lowBalance();
	@WebMethod
	public Integer deal_lowBalance(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_mdbError();
	@WebMethod
	public Integer deal_mdbError(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_expireProd();
	@WebMethod
	public Integer deal_expireProd(@WebParam(name = "sExpireProdNotifyList") List<STsRecord> sExpireProdNotifyList);
	@WebMethod
	public Integer read_event();
	@WebMethod
	public Integer deal_event(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_eventReward();
	@WebMethod
	public Integer deal_eventReward(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_delayValidProd();
	@WebMethod
	public Integer deal_delayValidProd(@WebParam(name = "sDelayValidProdSyncList") List<STsRecord> sDelayValidProdSyncList);
	@WebMethod
	public Integer read_prodOnceNotify();
	@WebMethod
	public Integer deal_prodOnceNotify(@WebParam(name = "sProdOnceNotifyList") List<STsRecord> sProdOnceNotifyList);
	@WebMethod
	public Integer read_prodCycleNotify();
	@WebMethod
	public Integer deal_prodCycleNotify(@WebParam(name = "sProdCycleNotifyList") List<STsRecord> sProdCycleNotifyList);
	@WebMethod
	public Integer read_lifeCycleOncePreNotify();
	@WebMethod
	public Integer deal_lifeCycleOncePreNotify(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_imsUserOrderConfirm();
	@WebMethod
	public Integer deal_imsUserOrderConfirm(@WebParam(name = "sUserConfirmList") List<SUserConfirm> sUserConfirmList);
	@WebMethod
	public Integer read_imsOrderProduct();
	@WebMethod
	public Integer deal_imsOrderProduct(@WebParam(name = "listImsOrderProduct") List<SImsOrderProduct> listImsOrderProduct);
	@WebMethod
	public Integer read_sharingProd();
	@WebMethod
	public Integer deal_sharingProd(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_imsSyncTs();
	@WebMethod
	public Integer deal_imsSyncTs(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_deductFailureTs();
	@WebMethod
	public Integer deal_deductFailureTs(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_resetSysNegtiveValue();
	@WebMethod
	public Integer deal_resetSysNegtiveValue(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_syncIcs();
	@WebMethod
	public Integer deal_syncIcs(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);
	@WebMethod
	public Integer read_terminateBillAcctTs();
	@WebMethod
	public Integer deal_terminateBillAcctTs(@WebParam(name = "sTsRecordList") List<STsRecord> sTsRecordList);

}