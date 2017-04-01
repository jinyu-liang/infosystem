package com.ailk.openbilling.service.imsinner;

import org.springframework.transaction.annotation.Transactional;
import com.ailk.openbilling.persistence.imsinner.entity.Do_LifeCycleByAcctChgResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;
import com.ailk.openbilling.persistence.imsintf.entity.SBalanceList;
import com.ailk.openbilling.persistence.imsintf.entity.Do_changeProductResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SChangeProduct;
import com.ailk.openbilling.persistence.imsintf.entity.OneTimeFee;
import com.ailk.openbilling.persistence.imsinner.entity.Do_orderProductResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsinner.entity.Do_unDoValidityChgResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SUnDoValidityChgReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteAccountResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteAccountReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_fuzzyCustsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SFuzzyCustsForGUIReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_fuzzyAcctsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SFuzzyAcctsForGUIReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_userReratingResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SUserReratingReg;
import com.ailk.openbilling.persistence.imsinner.entity.Do_acctReratingResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SAcctReratingReg;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteUserByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteUserByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteAcctByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteAcctByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteCustByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteCustByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_syncRewardInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.RewardSyncInfo;
import com.ailk.openbilling.persistence.imsinner.entity.Do_amendProductDateResponse;
import com.ailk.openbilling.persistence.imsinner.entity.AmendProductDateReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_chgUserValidity4ShResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SChgUserValidityReq;
import com.ailk.openbilling.persistence.imsintf.entity.Do_setExemptCreditLimitResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SSetEmptLimitReq;
@Transactional
public class InnerServiceImpl extends InnerServiceSkeleton{

	/**
	 * 
	 * @param sOperInfo  
	 * @param sLifeCycleByAcctChgReq  
	 * @param sBalanceList  
	 * @return 
	 */
	public Do_LifeCycleByAcctChgResponse do_LifeCycleByAcctChg(SOperInfo sOperInfo,SLifeCycleByAcctChgReq sLifeCycleByAcctChgReq,SBalanceList sBalanceList){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sChgProduct  
	 * @param oneTimeFee  
	 * @return 
	 */
	public Do_changeProductResponse do_changeProduct(SOperInfo sOper,SChangeProduct sChgProduct,OneTimeFee oneTimeFee){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sProductOrderList  
	 * @return 
	 */
	public Do_orderProductResponse do_orderProduct(SOperInfo sOper,SProductOrderList sProductOrderList){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param SOperInfo  
	 * @param unDoValidityChgReq  
	 * @return 
	 */
	public Do_unDoValidityChgResponse do_unDoValidityChg(SOperInfo SOperInfo,SUnDoValidityChgReq unDoValidityChgReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param SDeleteAccountReq  
	 * @return 
	 */
	public Do_deleteAccountResponse do_deleteAccount(SOperInfo sOper,SDeleteAccountReq SDeleteAccountReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sFuzzyCustsForGUI  
	 * @return 
	 */
	public Do_fuzzyCustsForGUIResponse do_fuzzyMatchCustsForGUI(SOperInfo sOper,SFuzzyCustsForGUIReq sFuzzyCustsForGUI){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sFuzzyAcctsForGUIReq  
	 * @return 
	 */
	public Do_fuzzyAcctsForGUIResponse do_fuzzyMatchAcctsForGUI(SOperInfo sOper,SFuzzyAcctsForGUIReq sFuzzyAcctsForGUIReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sUserReratingReq  
	 * @return 
	 */
	public Do_userReratingResponse do_userRerating(SOperInfo sOper,SUserReratingReg sUserReratingReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param sAcctReratingReq  
	 * @return 
	 */
	public Do_acctReratingResponse do_acctRerating(SOperInfo sOperInfo,SAcctReratingReg sAcctReratingReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param req  
	 * @return 
	 */
	public Do_deleteUserByIdResponse do_deleteUserById(SOperInfo sOper,SDeleteUserByIdReq req){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param req  
	 * @return 
	 */
	public Do_deleteAcctByIdResponse do_deleteAcctById(SOperInfo sOper,SDeleteAcctByIdReq req){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param req  
	 * @return 
	 */
	public Do_deleteCustByIdResponse do_deleteCustById(SOperInfo sOper,SDeleteCustByIdReq req){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param rewardInfo  
	 * @return 
	 */
	public Do_syncRewardInfoResponse do_syncRewardInfo(SOperInfo sOper,RewardSyncInfo rewardInfo){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param amendProdDateReq  
	 * @return 
	 */
	public Do_amendProductDateResponse do_amendProdDate(SOperInfo sOper,AmendProductDateReq amendProdDateReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sChgUserValidityReq  
	 * @return 
	 */
	public Do_chgUserValidity4ShResponse do_chgUserValidity4Sh(SOperInfo sOper,SChgUserValidityReq sChgUserValidityReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param setExemptLimitReq  
	 * @return 
	 */
	public Do_setExemptCreditLimitResponse do_setExemptCreditLimit(SOperInfo sOper,SSetEmptLimitReq setExemptLimitReq){
		//TODO please implement this method for you business.
		return null;
	}


}