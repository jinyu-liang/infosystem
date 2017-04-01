package com.ailk.openbilling.action.imsinner;

import com.ailk.easyframe.web.common.annotation.Param;
import com.ailk.easyframe.web.common.annotation.ParamType;
import com.ailk.openbilling.persistence.imsinner.entity.Do_LifeCycleByAcctChgResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imsinner.entity.SLifeCycleByAcctChgReq;
import com.ailk.openbilling.persistence.imsintf.entity.SBalanceList;
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
import jef.codegen.support.NotModified;
@NotModified
public interface InnerAction{

	public Do_LifeCycleByAcctChgResponse do_LifeCycleByAcctChg(@Param("sOperInfo") SOperInfo sOperInfo,@Param("sLifeCycleByAcctChgReq") SLifeCycleByAcctChgReq sLifeCycleByAcctChgReq,@Param("sBalanceList") SBalanceList sBalanceList);
	public Do_orderProductResponse do_orderProduct(@Param("sOper") SOperInfo sOper,@Param("sProductOrderList") SProductOrderList sProductOrderList);
	public Do_unDoValidityChgResponse do_unDoValidityChg(@Param("SOperInfo") SOperInfo SOperInfo,@Param("unDoValidityChgReq") SUnDoValidityChgReq unDoValidityChgReq);
	public Do_deleteAccountResponse do_deleteAccount(@Param("sOper") SOperInfo sOper,@Param("SDeleteAccountReq") SDeleteAccountReq SDeleteAccountReq);
	public Do_fuzzyCustsForGUIResponse do_fuzzyMatchCustsForGUI(@Param("sOper") SOperInfo sOper,@Param("sFuzzyCustsForGUI") SFuzzyCustsForGUIReq sFuzzyCustsForGUI);
	public Do_fuzzyAcctsForGUIResponse do_fuzzyMatchAcctsForGUI(@Param("sOper") SOperInfo sOper,@Param("sFuzzyAcctsForGUIReq") SFuzzyAcctsForGUIReq sFuzzyAcctsForGUIReq);
	public Do_userReratingResponse do_userRerating(@Param("sOper") SOperInfo sOper,@Param("sUserReratingReq") SUserReratingReg sUserReratingReq);
	public Do_acctReratingResponse do_acctRerating(@Param("sOperInfo") SOperInfo sOperInfo,@Param("sAcctReratingReq") SAcctReratingReg sAcctReratingReq);
	public Do_deleteUserByIdResponse do_deleteUserById(@Param("sOper") SOperInfo sOper,@Param("req") SDeleteUserByIdReq req);
	public Do_deleteAcctByIdResponse do_deleteAcctById(@Param("sOper") SOperInfo sOper,@Param("req") SDeleteAcctByIdReq req);
	public Do_deleteCustByIdResponse do_deleteCustById(@Param("sOper") SOperInfo sOper,@Param("req") SDeleteCustByIdReq req);
	public Do_syncRewardInfoResponse do_syncRewardInfo(@Param("sOper") SOperInfo sOper,@Param("rewardInfo") RewardSyncInfo rewardInfo);
	public Do_amendProductDateResponse do_amendProdDate(@Param("sOper") SOperInfo sOper,@Param("amendProdDateReq") AmendProductDateReq amendProdDateReq);

}