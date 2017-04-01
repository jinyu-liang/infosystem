package com.ailk.openbilling.service.imssdl;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import com.ailk.openbilling.persistence.imssdl.entity.SOperInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SUpSellReq;
import java.util.List;
import com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChg4Abm;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstActiveRes;
import com.ailk.openbilling.persistence.imssdl.entity.SCaRewardInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SProductStatus;
import com.ailk.openbilling.persistence.imssdl.entity.SCreditStatus;
import com.ailk.openbilling.persistence.imssdl.entity.SResLifeCycleSync;
import com.ailk.openbilling.persistence.imssdl.entity.SResSevCycle;
import com.ailk.openbilling.persistence.imssdl.entity.SOneTimeProm;
import com.ailk.openbilling.persistence.imssdl.entity.SNotifySendStandardIntf;
import com.ailk.openbilling.persistence.imssdl.entity.SLowBalance;
import com.ailk.openbilling.persistence.imssdl.entity.SUserValidityChg;
import com.ailk.openbilling.persistence.imssdl.entity.SRewardOrderProduct;
import com.ailk.openbilling.persistence.imssdl.entity.SLifeCycleChgByPayment;
import com.ailk.openbilling.persistence.imssdl.entity.SUpdatePriceParam;
import com.ailk.openbilling.persistence.imssdl.entity.SBarService;
import com.ailk.openbilling.persistence.imssdl.entity.SProdNotify;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryOperReq;
import javax.xml.ws.Holder;
import com.ailk.openbilling.persistence.imssdl.entity.SOperInfoResp;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryOrgReq;
import com.ailk.openbilling.persistence.imssdl.entity.SOrgInfoResp;
import com.ailk.openbilling.persistence.imssdl.entity.SResourceMonitor;
import com.ailk.openbilling.persistence.imssdl.entity.SDeductAlarm;
import com.ailk.openbilling.persistence.imssdl.entity.SChangeProdInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SRewardOtFreeres;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstAct;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryAcctInfoSdlReq;
import com.ailk.openbilling.persistence.imssdl.entity.SAccountInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SImmeSms;
import com.ailk.openbilling.persistence.imssdl.entity.SBatchSmsInfo;
import jef.codegen.support.NotModified;
@NotModified
@WebService
@WebServiceProvider(portName="IImsSyncAppService")
public interface IImsSyncAppServiceWs{

	@WebMethod
	public Integer do_upSelling(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sUpSellReq") SUpSellReq sUpSellReq);
	@WebMethod
	public Integer do_lifeCycleChg(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listLifeCycleChg4Abm") List<SLifeCycleChg4Abm> listLifeCycleChg4Abm);
	@WebMethod
	public Integer ts_firstAct(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sFirstActiveRes") SFirstActiveRes sFirstActiveRes,@WebParam(name = "listCaRewardInfo") List<SCaRewardInfo> listCaRewardInfo);
	@WebMethod
	public Integer sync_productStatus(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listProductStatus") List<SProductStatus> listProductStatus);
	@WebMethod
	public Integer sync_creditStatus(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sCreditStatus") SCreditStatus sCreditStatus);
	@WebMethod
	public Integer read_lifeCycleStsCheck();
	@WebMethod
	public Integer deal_lifeCycleStsCheck(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sResLifeCycleSync") SResLifeCycleSync sResLifeCycleSync);
	@WebMethod
	public Integer add_expireProd();
	@WebMethod
	public Integer add_validProd();
	@WebMethod
	public Integer add_prodCycleNotify();
	@WebMethod
	public Integer do_resSevCycleChg(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listResSevCycle") List<SResSevCycle> listResSevCycle);
	@WebMethod
	public Integer sync_oneTimePromUsedUp(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sOneTimeProm") SOneTimeProm sOneTimeProm);
	@WebMethod
	public Integer do_sendNotification(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listNotifySendStandardIntf") List<SNotifySendStandardIntf> listNotifySendStandardIntf);
	@WebMethod
	public Integer do_syncLowBalanceEtopup(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listLowBalance") List<SLowBalance> listLowBalance);
	@WebMethod
	public Integer do_userValidityChg(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sUserValidityChg") SUserValidityChg sUserValidityChg);
	@WebMethod
	public Integer do_orderRewardProduct(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sRewardOrderProduct") SRewardOrderProduct sRewardOrderProduct);
	@WebMethod
	public Integer do_lifeCycleChgByPayment(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sLifeCycleChgByPayment") SLifeCycleChgByPayment sLifeCycleChgByPayment);
	@WebMethod
	public Integer do_updateProdPriceParam(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listUpdatePriceParam") List<SUpdatePriceParam> listUpdatePriceParam);
	@WebMethod
	public Integer do_barService(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listBarService") List<SBarService> listBarService);
	@WebMethod
	public Integer create_prodNotifyInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listProdNotify") List<SProdNotify> listProdNotify);
	@WebMethod
	public Integer do_queryOperatorInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sQueryOperReq") SQueryOperReq sQueryOperReq,@WebParam(name = "listOperInfoResp",mode = Mode.INOUT) Holder<List<SOperInfoResp>> listOperInfoResp);
	@WebMethod
	public Integer do_queryOrganizeInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sQueryOrgReq") SQueryOrgReq sQueryOrgReq,@WebParam(name = "listOrgInfoResp",mode = Mode.INOUT) Holder<List<SOrgInfoResp>> listOrgInfoResp);
	@WebMethod
	public Integer do_insertImsResourceMonitor(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listResourceMonitor") List<SResourceMonitor> listResourceMonitor);
	@WebMethod
	public Integer do_insertImsUserOrderConfirm(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listDeductAlarm") List<SDeductAlarm> listDeductAlarm);
	@WebMethod
	public Integer do_modifyProduct(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listProdInfo") List<SChangeProdInfo> listProdInfo);
	@WebMethod
	public Integer do_rewardOtFreeres(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sRewardOtFreeres") SRewardOtFreeres sRewardOtFreeres);
	@WebMethod
	public Integer read_userValidity();
	@WebMethod
	public Integer do_firstActive(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sFirstAct") SFirstAct sFirstAct);
	@WebMethod
	public Integer do_queryAcctInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sQueryAcctInfoSdlReq") SQueryAcctInfoSdlReq sQueryAcctInfoSdlReq,@WebParam(name = "sAccountInfo",mode = Mode.INOUT) Holder<SAccountInfo> sAccountInfo);
	@WebMethod
	public Integer do_deductFailureTs();
	@WebMethod
	public Integer do_insertImmeSmsBySdl(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "listImmeSms") List<SImmeSms> listImmeSms);
	@WebMethod
	public Integer do_insertBatchSmsBySdl(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sBatchSmsInfo") SBatchSmsInfo sBatchSmsInfo);

}