package com.ailk.openbilling.service.imsts;

import com.ailk.easyframe.web.common.dal.IService;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.annotation.Sdl;
import java.util.List;
import com.ailk.openbilling.persistence.imsts.entity.STsRecord;
import com.ailk.openbilling.persistence.imsts.entity.SUserConfirm;
import com.ailk.openbilling.persistence.imsts.entity.SImsOrderProduct;
import jef.codegen.support.NotModified;
@NotModified
@Transactional
@Sdl(module="MImsTsApp")
public interface IImsTsAppService extends IService{

	/**
	 * 
	 * @return 
	 */
	public Integer read_firstActive();
	/**
	 * 
	 * @param sCustProdSyncList  
	 * @return 
	 */
	public Integer deal_firstActive(List<STsRecord> sCustProdSyncList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_creditStatus();
	/**
	 * 
	 * @param sCreditStatusSyncList  
	 * @return 
	 */
	public Integer deal_creditStatus(List<STsRecord> sCreditStatusSyncList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_productStatus();
	/**
	 * 
	 * @param sProdStsSyncList  
	 * @return 
	 */
	public Integer deal_productStatus(List<STsRecord> sProdStsSyncList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_userStsSync();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_userStsSync(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_barService();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_barService(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_rewardInfo();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_rewardInfo(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_otPromUsedUp();
	/**
	 * 
	 * @param sOtpromUsedupSyncList  
	 * @return 
	 */
	public Integer deal_otPromUsedUp(List<STsRecord> sOtpromUsedupSyncList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_lowBalance();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_lowBalance(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_mdbError();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_mdbError(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_expireProd();
	/**
	 * 
	 * @param sExpireProdNotifyList  
	 * @return 
	 */
	public Integer deal_expireProd(List<STsRecord> sExpireProdNotifyList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_event();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_event(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_eventReward();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_eventReward(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_delayValidProd();
	/**
	 * 
	 * @param sDelayValidProdSyncList  
	 * @return 
	 */
	public Integer deal_delayValidProd(List<STsRecord> sDelayValidProdSyncList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_prodOnceNotify();
	/**
	 * 
	 * @param sProdOnceNotifyList  
	 * @return 
	 */
	public Integer deal_prodOnceNotify(List<STsRecord> sProdOnceNotifyList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_prodCycleNotify();
	/**
	 * 
	 * @param sProdCycleNotifyList  
	 * @return 
	 */
	public Integer deal_prodCycleNotify(List<STsRecord> sProdCycleNotifyList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_lifeCycleOncePreNotify();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_lifeCycleOncePreNotify(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_imsUserOrderConfirm();
	/**
	 * 
	 * @param sUserConfirmList  
	 * @return 
	 */
	public Integer deal_imsUserOrderConfirm(List<SUserConfirm> sUserConfirmList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_imsOrderProduct();
	/**
	 * 
	 * @param listImsOrderProduct  
	 * @return 
	 */
	public Integer deal_imsOrderProduct(List<SImsOrderProduct> listImsOrderProduct);
	/**
	 * 
	 * @return 
	 */
	public Integer read_sharingProd();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_sharingProd(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_imsSyncTs();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_imsSyncTs(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_deductFailureTs();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_deductFailureTs(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_resetSysNegtiveValue();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_resetSysNegtiveValue(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_syncIcs();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_syncIcs(List<STsRecord> sTsRecordList);
	/**
	 * 
	 * @return 
	 */
	public Integer read_terminateBillAcctTs();
	/**
	 * 
	 * @param sTsRecordList  
	 * @return 
	 */
	public Integer deal_terminateBillAcctTs(List<STsRecord> sTsRecordList);

}