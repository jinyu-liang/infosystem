package com.ailk.openbilling.service.imssdl;

import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.annotation.Sdl;
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
import jef.common.wrapper.Holder;
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
@Transactional
@Sdl(module="MImsSyncApp")
public class IImsSyncAppServiceImpl extends IImsSyncAppServiceSkeleton{

	/**
	 * 
	 * @param sOperInfo  
	 * @param sUpSellReq  
	 * @return 
	 */
	public Integer do_upSelling(SOperInfo sOperInfo,SUpSellReq sUpSellReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listLifeCycleChg4Abm  
	 * @return 
	 */
	public Integer do_lifeCycleChg(SOperInfo sOperInfo,List<SLifeCycleChg4Abm> listLifeCycleChg4Abm){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sFirstActiveRes  
	 * @param listCaRewardInfo  
	 * @return 
	 */
	public Integer ts_firstAct(SOperInfo sOperInfo,SFirstActiveRes sFirstActiveRes,List<SCaRewardInfo> listCaRewardInfo){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listProductStatus  
	 * @return 
	 */
	public Integer sync_productStatus(SOperInfo sOperInfo,List<SProductStatus> listProductStatus){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sCreditStatus  
	 * @return 
	 */
	public Integer sync_creditStatus(SOperInfo sOperInfo,SCreditStatus sCreditStatus){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer read_lifeCycleStsCheck(){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sResLifeCycleSync  
	 * @return 
	 */
	public Integer deal_lifeCycleStsCheck(SOperInfo sOperInfo,SResLifeCycleSync sResLifeCycleSync){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer add_expireProd(){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer add_validProd(){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer add_prodCycleNotify(){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listResSevCycle  
	 * @return 
	 */
	public Integer do_resSevCycleChg(SOperInfo sOperInfo,List<SResSevCycle> listResSevCycle){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sOneTimeProm  
	 * @return 
	 */
	public Integer sync_oneTimePromUsedUp(SOperInfo sOperInfo,SOneTimeProm sOneTimeProm){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listNotifySendStandardIntf  
	 * @return 
	 */
	public Integer do_sendNotification(SOperInfo sOperInfo,List<SNotifySendStandardIntf> listNotifySendStandardIntf){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listLowBalance  
	 * @return 
	 */
	public Integer do_syncLowBalanceEtopup(SOperInfo sOperInfo,List<SLowBalance> listLowBalance){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sUserValidityChg  
	 * @return 
	 */
	public Integer do_userValidityChg(SOperInfo sOperInfo,SUserValidityChg sUserValidityChg){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sRewardOrderProduct  
	 * @return 
	 */
	public Integer do_orderRewardProduct(SOperInfo sOperInfo,SRewardOrderProduct sRewardOrderProduct){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sLifeCycleChgByPayment  
	 * @return 
	 */
	public Integer do_lifeCycleChgByPayment(SOperInfo sOperInfo,SLifeCycleChgByPayment sLifeCycleChgByPayment){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listUpdatePriceParam  
	 * @return 
	 */
	public Integer do_updateProdPriceParam(SOperInfo sOperInfo,List<SUpdatePriceParam> listUpdatePriceParam){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listBarService  
	 * @return 
	 */
	public Integer do_barService(SOperInfo sOperInfo,List<SBarService> listBarService){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listProdNotify  
	 * @return 
	 */
	public Integer create_prodNotifyInfo(SOperInfo sOperInfo,List<SProdNotify> listProdNotify){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sQueryOperReq  
	 * @param listOperInfoResp  
	 * @return 
	 */
	public Integer do_queryOperatorInfo(SOperInfo sOperInfo,SQueryOperReq sQueryOperReq,Holder<List<SOperInfoResp>> listOperInfoResp){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sQueryOrgReq  
	 * @param listOrgInfoResp  
	 * @return 
	 */
	public Integer do_queryOrganizeInfo(SOperInfo sOperInfo,SQueryOrgReq sQueryOrgReq,Holder<List<SOrgInfoResp>> listOrgInfoResp){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listResourceMonitor  
	 * @return 
	 */
	public Integer do_insertImsResourceMonitor(SOperInfo sOperInfo,List<SResourceMonitor> listResourceMonitor){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listDeductAlarm  
	 * @return 
	 */
	public Integer do_insertImsUserOrderConfirm(SOperInfo sOperInfo,List<SDeductAlarm> listDeductAlarm){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listProdInfo  
	 * @return 
	 */
	public Integer do_modifyProduct(SOperInfo sOperInfo,List<SChangeProdInfo> listProdInfo){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sRewardOtFreeres  
	 * @return 
	 */
	public Integer do_rewardOtFreeres(SOperInfo sOperInfo,SRewardOtFreeres sRewardOtFreeres){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer read_userValidity(){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sFirstAct  
	 * @return 
	 */
	public Integer do_firstActive(SOperInfo sOperInfo,SFirstAct sFirstAct){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sQueryAcctInfoSdlReq  
	 * @param sAccountInfo  
	 * @return 
	 */
	public Integer do_queryAcctInfo(SOperInfo sOperInfo,SQueryAcctInfoSdlReq sQueryAcctInfoSdlReq,Holder<SAccountInfo> sAccountInfo){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer do_deductFailureTs(){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param listImmeSms  
	 * @return 
	 */
	public Integer do_insertImmeSmsBySdl(SOperInfo sOperInfo,List<SImmeSms> listImmeSms){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sBatchSmsInfo  
	 * @return 
	 */
	public Integer do_insertBatchSmsBySdl(SOperInfo sOperInfo,SBatchSmsInfo sBatchSmsInfo){
		//TODO please implement this method for you business.
		return null;
	}


}