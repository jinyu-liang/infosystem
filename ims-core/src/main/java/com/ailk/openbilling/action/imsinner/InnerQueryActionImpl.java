package com.ailk.openbilling.action.imsinner;

import org.springframework.beans.factory.annotation.Autowired;
import com.ailk.easyframe.web.action.BaseAction;
import com.ailk.easyframe.web.common.annotation.Param;
import com.ailk.easyframe.web.common.annotation.ParamType;
import com.ailk.openbilling.service.imsinner.InnerQueryService;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryThreadInfoResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryGroupInfo4GUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryGroup4GUIReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryMultiSim4GuiResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryMultiSimReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserByOfferNmaeResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserByOfferNameReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBatchUserResponse;
import com.ailk.openbilling.persistence.imsinner.entity.BatchUserWhere;
import com.ailk.openbilling.persistence.imsinner.entity.BatchUserInfo;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBatchAcctResponse;
import com.ailk.openbilling.persistence.imsinner.entity.BatchAcctWhere;
import com.ailk.openbilling.persistence.imsinner.entity.BatchAccountInfo;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBatchCustResponse;
import com.ailk.openbilling.persistence.imsinner.entity.BatchCustomerInfo;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctProdListReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryCommonUserInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctByPhoneReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryFirstBillDaysResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryFirstBillDaysReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustomerHistoryForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustomerHistoryReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryVPNGroupResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryVPNGroupReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBudgetRuleResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryBudgetRuleReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryParentAcctTreesResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserListForGuiResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserListForGuiReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserTargetStsInfoRespons;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserTargetStsInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdInfoHisResponse;
import com.ailk.openbilling.persistence.imsinner.entity.QueryProdInfoHisReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBalanceResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryBalanceReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_fuzzyCustsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySCustomersReq;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryProductsByUserIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUsersByConditionsResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUsersReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdOfferingInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQryProdOfferInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctsByCustIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOfferingInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryBugetResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryBugetReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_querySplitListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySplitListReq;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySplitByUserReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctsForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctsForGUIReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryCustIdByAcctIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustIdByAcctIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUsersByAcctIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryServiSpecIdsByCustIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctTreesByGroupIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProductsByUserIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustBaseInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustBaseInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryServicesByUserIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryServicesReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryReguideInfoForGUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryReguideInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryAcctByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryUserByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryCmResIdentityByDevResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCmResIdentityByDevReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryAcctByPhoneResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustomerInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustomerInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_isPrimaryProductResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAccountsSuffixResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAccountsSuffixReq;
public class InnerQueryActionImpl extends BaseAction implements InnerQueryAction{


	@Autowired
	protected InnerQueryService imsinner_innerQueryService;

	public void setImsinner_innerQueryService(InnerQueryService obj){
		this.imsinner_innerQueryService = obj;
	}

	public InnerQueryService getImsinner_innerQueryService(){
		return imsinner_innerQueryService;
	}

	protected InnerQueryService getCrudService(){
		return imsinner_innerQueryService;
	}

	public Do_queryThreadInfoResponse do_queryThreadInfo(@Param("sOper") SOperInfo sOper,@Param("threadId") Long threadId){
		return imsinner_innerQueryService.do_queryThreadInfo(sOper,threadId);
	}

	public Do_queryGroupInfo4GUIResponse do_queryGroupInfo4GUI(@Param("sOper") SOperInfo sOper,@Param("sQueryGroup4GUIReq") SQueryGroup4GUIReq sQueryGroup4GUIReq){
		return imsinner_innerQueryService.do_queryGroupInfo4GUI(sOper,sQueryGroup4GUIReq);
	}

	public Do_queryMultiSim4GuiResponse do_queryMultiSim4Gui(@Param("sOper") SOperInfo sOper,@Param("queryMutltSimReq") SQueryMultiSimReq queryMutltSimReq){
		return imsinner_innerQueryService.do_queryMultiSim4Gui(sOper,queryMutltSimReq);
	}

	public Do_queryUserByOfferNmaeResponse do_queryUserByOfferName(@Param("sOper") SOperInfo sOper,@Param("req") SQueryUserByOfferNameReq req){
		return imsinner_innerQueryService.do_queryUserByOfferName(sOper,req);
	}

	public Do_queryBatchUserResponse do_queryBatchUserList(@Param("sOperInfo") SOperInfo sOperInfo,@Param("batchUserWhere") BatchUserWhere batchUserWhere,@Param("batchUserInfo") BatchUserInfo batchUserInfo){
		return imsinner_innerQueryService.do_queryBatchUserList(sOperInfo,batchUserWhere,batchUserInfo);
	}

	public Do_queryBatchAcctResponse do_queryBatchAcctList(@Param("sOper") SOperInfo sOper,@Param("batchAcctWhere") BatchAcctWhere batchAcctWhere,@Param("batchAcctInfo") BatchAccountInfo batchAcctInfo){
		return imsinner_innerQueryService.do_queryBatchAcctList(sOper,batchAcctWhere,batchAcctInfo);
	}

	public Do_queryBatchCustResponse do_queryBatchCustList(@Param("sOper") SOperInfo sOper,@Param("batchCustomerInfo") BatchCustomerInfo batchCustomerInfo){
		return imsinner_innerQueryService.do_queryBatchCustList(sOper,batchCustomerInfo);
	}

	public Do_queryProdListResponse do_queryAcctProdList(@Param("sOper") SOperInfo sOper,@Param("sQueryAcctProdListReq") SQueryAcctProdListReq sQueryAcctProdListReq){
		return imsinner_innerQueryService.do_queryAcctProdList(sOper,sQueryAcctProdListReq);
	}

	public Do_QueryCommonUserInfoResponse do_queryCommonInfoByUserId(@Param("SOper") SOperInfo SOper,@Param("sQueryUserByIdReq") SQueryUserByIdReq sQueryUserByIdReq){
		return imsinner_innerQueryService.do_queryCommonInfoByUserId(SOper,sQueryUserByIdReq);
	}

	public Do_QueryCommonUserInfoResponse do_queryCommonInfoByPhoneId(@Param("SOper") SOperInfo SOper,@Param("sQueryAcctByPhoneReq") SQueryAcctByPhoneReq sQueryAcctByPhoneReq){
		return imsinner_innerQueryService.do_queryCommonInfoByPhoneId(SOper,sQueryAcctByPhoneReq);
	}

	public Do_queryFirstBillDaysResponse do_queryFirstBillDays(@Param("sOper") SOperInfo sOper,@Param("squeryFirstBillDaysReq") SQueryFirstBillDaysReq squeryFirstBillDaysReq){
		return imsinner_innerQueryService.do_queryFirstBillDays(sOper,squeryFirstBillDaysReq);
	}

	public Do_queryCustomerHistoryForGUIResponse do_queryCustomerHistory(@Param("sOper") SOperInfo sOper,@Param("sQueryCustomerHistoryReq") SQueryCustomerHistoryReq sQueryCustomerHistoryReq){
		return imsinner_innerQueryService.do_queryCustomerHistory(sOper,sQueryCustomerHistoryReq);
	}

	public Do_queryVPNGroupResponse do_queryVPNGroup(@Param("sOper") SOperInfo sOper,@Param("groupReq") SQueryVPNGroupReq groupReq){
		return imsinner_innerQueryService.do_queryVPNGroup(sOper,groupReq);
	}

	public Do_queryBudgetRuleResponse do_queryBudgetRule(@Param("sOper") SOperInfo sOper,@Param("budgetRuleReq") SQueryBudgetRuleReq budgetRuleReq){
		return imsinner_innerQueryService.do_queryBudgetRule(sOper,budgetRuleReq);
	}

	public Do_queryParentAcctTreesResponse do_queryParentAcctTrees(@Param("sOper") SOperInfo sOper,@Param("acct_id") Long acct_id){
		return imsinner_innerQueryService.do_queryParentAcctTrees(sOper,acct_id);
	}

	public Do_queryUserListForGuiResponse do_queryUserListForGui(@Param("sOper") SOperInfo sOper,@Param("queryUserListReq") SQueryUserListForGuiReq queryUserListReq){
		return imsinner_innerQueryService.do_queryUserListForGui(sOper,queryUserListReq);
	}

	public Do_queryUserTargetStsInfoRespons do_queryUserTargetStsInfoForGUI(@Param("sOper") SOperInfo sOper,@Param("sQueryUserTargetStsInfoReq") SQueryUserTargetStsInfoReq sQueryUserTargetStsInfoReq){
		return imsinner_innerQueryService.do_queryUserTargetStsInfoForGUI(sOper,sQueryUserTargetStsInfoReq);
	}

	public Do_queryProdInfoHisResponse do_queryProdHisInfo(@Param("sOper") SOperInfo sOper,@Param("queryProdInfoHisReq") QueryProdInfoHisReq queryProdInfoHisReq){
		return imsinner_innerQueryService.do_queryProdHisInfo(sOper,queryProdInfoHisReq);
	}

	public Do_queryBalanceResponse do_queryBalance(@Param("sOper") SOperInfo sOper,@Param("queryBalanceReq") SQueryBalanceReq queryBalanceReq){
		return imsinner_innerQueryService.do_queryBalance(sOper,queryBalanceReq);
	}

	public Do_fuzzyCustsForGUIResponse do_queryCustomers(@Param("sOper") SOperInfo sOper,@Param("sQueryCustomersReq") SQuerySCustomersReq sQueryCustomersReq){
		return imsinner_innerQueryService.do_queryCustomers(sOper,sQueryCustomersReq);
	}

	public Do_queryProdListResponse do_queryUserProdList(@Param("sOper") SOperInfo sOper,@Param("sQueryProductsByUserIdReq") SQueryProductsByUserIdReq sQueryProductsByUserIdReq){
		return imsinner_innerQueryService.do_queryUserProdList(sOper,sQueryProductsByUserIdReq);
	}

	public Do_queryUsersByConditionsResponse do_queryUsersByConditions(@Param("sOperInfo") SOperInfo sOperInfo,@Param("SQueryUsersReq") SQueryUsersReq SQueryUsersReq){
		return imsinner_innerQueryService.do_queryUsersByConditions(sOperInfo,SQueryUsersReq);
	}

	public Do_queryProdOfferingInfoResponse do_queryProdOfferInfo(@Param("sOper") SOperInfo sOper,@Param("sQryProdOfferInfoReq") SQryProdOfferInfoReq sQryProdOfferInfoReq){
		return imsinner_innerQueryService.do_queryProdOfferInfo(sOper,sQryProdOfferInfoReq);
	}

	public Do_queryAcctsByCustIdResponse do_queryAcctsByCustId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryAcctInfoReq req){
		return imsinner_innerQueryService.do_queryAcctsByCustId(sOperInfo,req);
	}

	public Do_queryOfferingInfoResponse do_queryOfferingInfoByOfferId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("offering_id") Integer offering_id){
		return imsinner_innerQueryService.do_queryOfferingInfoByOfferId(sOperInfo,offering_id);
	}

	public Do_queryBugetResponse do_queryBuget(@Param("sOperInfo") SOperInfo sOperInfo,@Param("sQueryBugetReq") SQueryBugetReq sQueryBugetReq){
		return imsinner_innerQueryService.do_queryBuget(sOperInfo,sQueryBugetReq);
	}

	public Do_querySplitListResponse do_querySplitInfo(@Param("sOperInfo") SOperInfo sOperInfo,@Param("splitListReq") SQuerySplitListReq splitListReq){
		return imsinner_innerQueryService.do_querySplitInfo(sOperInfo,splitListReq);
	}

	public Do_querySplitListResponse do_querySplitByAcctId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("splitByAcctIdReq") SQuerySplitListReq splitByAcctIdReq){
		return imsinner_innerQueryService.do_querySplitByAcctId(sOperInfo,splitByAcctIdReq);
	}

	public Do_querySplitListResponse do_querySplitByUserId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("splitByUserIdReq") SQuerySplitByUserReq splitByUserIdReq){
		return imsinner_innerQueryService.do_querySplitByUserId(sOperInfo,splitByUserIdReq);
	}

	public Do_queryAcctsForGUIResponse do_queryAcctsForGUI(@Param("sOper") SOperInfo sOper,@Param("sQueryAcctsForGUIReq") SQueryAcctsForGUIReq sQueryAcctsForGUIReq){
		return imsinner_innerQueryService.do_queryAcctsForGUI(sOper,sQueryAcctsForGUIReq);
	}

	public Do_QueryCustIdByAcctIdResponse do_queryCustIdByAcctId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryCustIdByAcctIdReq req){
		return imsinner_innerQueryService.do_queryCustIdByAcctId(sOperInfo,req);
	}

	public Do_queryUsersByAcctIdResponse do_queryUsersByAcctId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("acct_id") Long acct_id,@Param("relation_type") Integer relation_type){
		return imsinner_innerQueryService.do_queryUsersByAcctId(sOperInfo,acct_id,relation_type);
	}

	public Do_queryServiSpecIdsByCustIdResponse do_queryServiceSpecIdsByCustId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("cust_id") Long cust_id){
		return imsinner_innerQueryService.do_queryServiceSpecIdsByCustId(sOperInfo,cust_id);
	}

	public Do_queryAcctTreesByGroupIdResponse do_queryAcctTreesByGroupId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("acct_id") Long acct_id){
		return imsinner_innerQueryService.do_queryAcctTreesByGroupId(sOperInfo,acct_id);
	}

	public Do_queryProductsByUserIdResponse do_queryProductsByUserId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryProductsByUserIdReq req){
		return imsinner_innerQueryService.do_queryProductsByUserId(sOperInfo,req);
	}

	public Do_queryCustBaseInfoResponse do_queryCustBasicInfo(@Param("sOperInfo") SOperInfo sOperInfo,@Param("sQueryCustBasicInfoReq") SQueryCustBaseInfoReq sQueryCustBasicInfoReq){
		return imsinner_innerQueryService.do_queryCustBasicInfo(sOperInfo,sQueryCustBasicInfoReq);
	}

	public Do_queryServicesByUserIdResponse do_queryServicesByUserId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("seviceReq") SQueryServicesReq seviceReq){
		return imsinner_innerQueryService.do_queryServicesByUserId(sOperInfo,seviceReq);
	}

	public Do_QueryReguideInfoForGUIResponse do_queryReguideInfoForGUI(@Param("sOper") SOperInfo sOper,@Param("sQueryReguideInfoReq") SQueryReguideInfoReq sQueryReguideInfoReq){
		return imsinner_innerQueryService.do_queryReguideInfoForGUI(sOper,sQueryReguideInfoReq);
	}

	public Do_QueryAcctByIdResponse do_queryAcctById(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryAcctByIdReq req){
		return imsinner_innerQueryService.do_queryAcctById(sOperInfo,req);
	}

	public Do_QueryUserByIdResponse do_queryUserById(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryUserByIdReq req){
		return imsinner_innerQueryService.do_queryUserById(sOperInfo,req);
	}

	public Do_QueryCmResIdentityByDevResponse do_queryCmResIdentityByDev(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryCmResIdentityByDevReq req){
		return imsinner_innerQueryService.do_queryCmResIdentityByDev(sOperInfo,req);
	}

	public Do_QueryAcctByPhoneResponse do_queryAcctInfoByDev(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryAcctByPhoneReq req){
		return imsinner_innerQueryService.do_queryAcctInfoByDev(sOperInfo,req);
	}

	public Do_queryCustomerInfoResponse do_queryCustormInfo(@Param("sOper") SOperInfo sOper,@Param("reqParam") SQueryCustomerInfoReq reqParam){
		return imsinner_innerQueryService.do_queryCustormInfo(sOper,reqParam);
	}

	public Do_isPrimaryProductResponse do_isPrimaryProduct(@Param("sOper") SOperInfo sOper,@Param("prod_id") Long prod_id){
		return imsinner_innerQueryService.do_isPrimaryProduct(sOper,prod_id);
	}

	public Do_queryAccountsSuffixResponse do_queryAccountsSuffix(@Param("sOper") SOperInfo sOper,@Param("sQueryAccountsSuffixReq") SQueryAccountsSuffixReq sQueryAccountsSuffixReq){
		return imsinner_innerQueryService.do_queryAccountsSuffix(sOper,sQueryAccountsSuffixReq);
	}


}