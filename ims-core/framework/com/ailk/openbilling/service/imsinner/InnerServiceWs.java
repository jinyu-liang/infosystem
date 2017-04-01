package com.ailk.openbilling.service.imsinner;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebParam;
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
import jef.codegen.support.NotModified;
@NotModified
@WebService
@WebServiceProvider(portName="InnerService")
public interface InnerServiceWs{

	@WebMethod
	public Do_LifeCycleByAcctChgResponse do_LifeCycleByAcctChg(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sLifeCycleByAcctChgReq") SLifeCycleByAcctChgReq sLifeCycleByAcctChgReq,@WebParam(name = "sBalanceList") SBalanceList sBalanceList);
	@WebMethod
	public Do_changeProductResponse do_changeProduct(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sChgProduct") SChangeProduct sChgProduct,@WebParam(name = "oneTimeFee") OneTimeFee oneTimeFee);
	@WebMethod
	public Do_orderProductResponse do_orderProduct(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sProductOrderList") SProductOrderList sProductOrderList);
	@WebMethod
	public Do_unDoValidityChgResponse do_unDoValidityChg(@WebParam(name = "SOperInfo") SOperInfo SOperInfo,@WebParam(name = "unDoValidityChgReq") SUnDoValidityChgReq unDoValidityChgReq);
	@WebMethod
	public Do_deleteAccountResponse do_deleteAccount(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "SDeleteAccountReq") SDeleteAccountReq SDeleteAccountReq);
	@WebMethod
	public Do_fuzzyCustsForGUIResponse do_fuzzyMatchCustsForGUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sFuzzyCustsForGUI") SFuzzyCustsForGUIReq sFuzzyCustsForGUI);
	@WebMethod
	public Do_fuzzyAcctsForGUIResponse do_fuzzyMatchAcctsForGUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sFuzzyAcctsForGUIReq") SFuzzyAcctsForGUIReq sFuzzyAcctsForGUIReq);
	@WebMethod
	public Do_userReratingResponse do_userRerating(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sUserReratingReq") SUserReratingReg sUserReratingReq);
	@WebMethod
	public Do_acctReratingResponse do_acctRerating(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sAcctReratingReq") SAcctReratingReg sAcctReratingReq);
	@WebMethod
	public Do_deleteUserByIdResponse do_deleteUserById(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "req") SDeleteUserByIdReq req);
	@WebMethod
	public Do_deleteAcctByIdResponse do_deleteAcctById(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "req") SDeleteAcctByIdReq req);
	@WebMethod
	public Do_deleteCustByIdResponse do_deleteCustById(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "req") SDeleteCustByIdReq req);
	@WebMethod
	public Do_syncRewardInfoResponse do_syncRewardInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "rewardInfo") RewardSyncInfo rewardInfo);
	@WebMethod
	public Do_amendProductDateResponse do_amendProdDate(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "amendProdDateReq") AmendProductDateReq amendProdDateReq);
	@WebMethod
	public Do_chgUserValidity4ShResponse do_chgUserValidity4Sh(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sChgUserValidityReq") SChgUserValidityReq sChgUserValidityReq);
	@WebMethod
	public Do_setExemptCreditLimitResponse do_setExemptCreditLimit(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "setExemptLimitReq") SSetEmptLimitReq setExemptLimitReq);

}