package com.ailk.openbilling.service.imsinner;

import com.ailk.easyframe.web.common.dal.IService;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOfferingInfoByBusiFlagResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryParamExReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_querySplitListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQuerySplitRelListReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_checkViceNumResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SCheckViceNumReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryThreadInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryGroupInfo4GUIResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryGroup4GUIReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryMultiSim4GuiResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryMultiSimReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctExemptionFlagResponse;
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
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserPaymemtModeResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserPaymentModeReq;
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
import jef.codegen.support.NotModified;
@NotModified
@Transactional
public interface InnerQueryService extends IService{

	/**
	 * 
	 * @param sOper  
	 * @param sQueryParamExReq  
	 * @return 
	 */
	public Do_queryOfferingInfoByBusiFlagResponse do_queryProductOfferingInfoByBusiFlag(SOperInfo sOper,SQueryParamExReq sQueryParamExReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQuerySplitRelListReq  
	 * @return 
	 */
	public Do_querySplitListResponse do_querySplitRelByObjectId(SOperInfo sOper,SQuerySplitRelListReq sQuerySplitRelListReq);
	/**
	 * 
	 * @param sOper  
	 * @param req  
	 * @return 
	 */
	public Do_checkViceNumResponse do_checkViceNum(SOperInfo sOper,SCheckViceNumReq req);
	/**
	 * 
	 * @param sOper  
	 * @param threadId  
	 * @return 
	 */
	public Do_queryThreadInfoResponse do_queryThreadInfo(SOperInfo sOper,Long threadId);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryGroup4GUIReq  
	 * @return 
	 */
	public Do_queryGroupInfo4GUIResponse do_queryGroupInfo4GUI(SOperInfo sOper,SQueryGroup4GUIReq sQueryGroup4GUIReq);
	/**
	 * 
	 * @param sOper  
	 * @param queryMutltSimReq  
	 * @return 
	 */
	public Do_queryMultiSim4GuiResponse do_queryMultiSim4Gui(SOperInfo sOper,SQueryMultiSimReq queryMutltSimReq);
	/**
	 * 
	 * @param sOper  
	 * @param acct_id  
	 * @return 
	 */
	public Do_queryAcctExemptionFlagResponse do_queryAcctExemptionFlag(SOperInfo sOper,Long acct_id);
	/**
	 * 
	 * @param sOper  
	 * @param req  
	 * @return 
	 */
	public Do_queryUserByOfferNmaeResponse do_queryUserByOfferName(SOperInfo sOper,SQueryUserByOfferNameReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param batchUserWhere  
	 * @param batchUserInfo  
	 * @return 
	 */
	public Do_queryBatchUserResponse do_queryBatchUserList(SOperInfo sOperInfo,BatchUserWhere batchUserWhere,BatchUserInfo batchUserInfo);
	/**
	 * 
	 * @param sOper  
	 * @param batchAcctWhere  
	 * @param batchAcctInfo  
	 * @return 
	 */
	public Do_queryBatchAcctResponse do_queryBatchAcctList(SOperInfo sOper,BatchAcctWhere batchAcctWhere,BatchAccountInfo batchAcctInfo);
	/**
	 * 
	 * @param sOper  
	 * @param batchCustomerInfo  
	 * @return 
	 */
	public Do_queryBatchCustResponse do_queryBatchCustList(SOperInfo sOper,BatchCustomerInfo batchCustomerInfo);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryAcctProdListReq  
	 * @return 
	 */
	public Do_queryProdListResponse do_queryAcctProdList(SOperInfo sOper,SQueryAcctProdListReq sQueryAcctProdListReq);
	/**
	 * 
	 * @param SOper  
	 * @param sQueryUserByIdReq  
	 * @return 
	 */
	public Do_QueryCommonUserInfoResponse do_queryCommonInfoByUserId(SOperInfo SOper,SQueryUserByIdReq sQueryUserByIdReq);
	/**
	 * 
	 * @param SOper  
	 * @param sQueryAcctByPhoneReq  
	 * @return 
	 */
	public Do_QueryCommonUserInfoResponse do_queryCommonInfoByPhoneId(SOperInfo SOper,SQueryAcctByPhoneReq sQueryAcctByPhoneReq);
	/**
	 * 
	 * @param sOper  
	 * @param squeryFirstBillDaysReq  
	 * @return 
	 */
	public Do_queryFirstBillDaysResponse do_queryFirstBillDays(SOperInfo sOper,SQueryFirstBillDaysReq squeryFirstBillDaysReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryCustomerHistoryReq  
	 * @return 
	 */
	public Do_queryCustomerHistoryForGUIResponse do_queryCustomerHistory(SOperInfo sOper,SQueryCustomerHistoryReq sQueryCustomerHistoryReq);
	/**
	 * 
	 * @param sOper  
	 * @param groupReq  
	 * @return 
	 */
	public Do_queryVPNGroupResponse do_queryVPNGroup(SOperInfo sOper,SQueryVPNGroupReq groupReq);
	/**
	 * 
	 * @param sOper  
	 * @param budgetRuleReq  
	 * @return 
	 */
	public Do_queryBudgetRuleResponse do_queryBudgetRule(SOperInfo sOper,SQueryBudgetRuleReq budgetRuleReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryUserPaymentModeReq  
	 * @return 
	 */
	public Do_queryUserPaymemtModeResponse do_queryUserPaymentMode(SOperInfo sOper,SQueryUserPaymentModeReq sQueryUserPaymentModeReq);
	/**
	 * 
	 * @param sOper  
	 * @param acct_id  
	 * @return 
	 */
	public Do_queryParentAcctTreesResponse do_queryParentAcctTrees(SOperInfo sOper,Long acct_id);
	/**
	 * 
	 * @param sOper  
	 * @param queryUserListReq  
	 * @return 
	 */
	public Do_queryUserListForGuiResponse do_queryUserListForGui(SOperInfo sOper,SQueryUserListForGuiReq queryUserListReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryUserTargetStsInfoReq  
	 * @return 
	 */
	public Do_queryUserTargetStsInfoRespons do_queryUserTargetStsInfoForGUI(SOperInfo sOper,SQueryUserTargetStsInfoReq sQueryUserTargetStsInfoReq);
	/**
	 * 
	 * @param sOper  
	 * @param queryProdInfoHisReq  
	 * @return 
	 */
	public Do_queryProdInfoHisResponse do_queryProdHisInfo(SOperInfo sOper,QueryProdInfoHisReq queryProdInfoHisReq);
	/**
	 * 
	 * @param sOper  
	 * @param queryBalanceReq  
	 * @return 
	 */
	public Do_queryBalanceResponse do_queryBalance(SOperInfo sOper,SQueryBalanceReq queryBalanceReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryCustomersReq  
	 * @return 
	 */
	public Do_fuzzyCustsForGUIResponse do_queryCustomers(SOperInfo sOper,SQuerySCustomersReq sQueryCustomersReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryProductsByUserIdReq  
	 * @return 
	 */
	public Do_queryProdListResponse do_queryUserProdList(SOperInfo sOper,SQueryProductsByUserIdReq sQueryProductsByUserIdReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param SQueryUsersReq  
	 * @return 
	 */
	public Do_queryUsersByConditionsResponse do_queryUsersByConditions(SOperInfo sOperInfo,SQueryUsersReq SQueryUsersReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQryProdOfferInfoReq  
	 * @return 
	 */
	public Do_queryProdOfferingInfoResponse do_queryProdOfferInfo(SOperInfo sOper,SQryProdOfferInfoReq sQryProdOfferInfoReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_queryAcctsByCustIdResponse do_queryAcctsByCustId(SOperInfo sOperInfo,SQueryAcctInfoReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param offering_id  
	 * @return 
	 */
	public Do_queryOfferingInfoResponse do_queryOfferingInfoByOfferId(SOperInfo sOperInfo,Integer offering_id);
	/**
	 * 
	 * @param sOperInfo  
	 * @param sQueryBugetReq  
	 * @return 
	 */
	public Do_queryBugetResponse do_queryBuget(SOperInfo sOperInfo,SQueryBugetReq sQueryBugetReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param splitListReq  
	 * @return 
	 */
	public Do_querySplitListResponse do_querySplitInfo(SOperInfo sOperInfo,SQuerySplitListReq splitListReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param splitByAcctIdReq  
	 * @return 
	 */
	public Do_querySplitListResponse do_querySplitByAcctId(SOperInfo sOperInfo,SQuerySplitListReq splitByAcctIdReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param splitByUserIdReq  
	 * @return 
	 */
	public Do_querySplitListResponse do_querySplitByUserId(SOperInfo sOperInfo,SQuerySplitByUserReq splitByUserIdReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryAcctsForGUIReq  
	 * @return 
	 */
	public Do_queryAcctsForGUIResponse do_queryAcctsForGUI(SOperInfo sOper,SQueryAcctsForGUIReq sQueryAcctsForGUIReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_QueryCustIdByAcctIdResponse do_queryCustIdByAcctId(SOperInfo sOperInfo,SQueryCustIdByAcctIdReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param acct_id  
	 * @param relation_type  
	 * @return 
	 */
	public Do_queryUsersByAcctIdResponse do_queryUsersByAcctId(SOperInfo sOperInfo,Long acct_id,Integer relation_type);
	/**
	 * 
	 * @param sOperInfo  
	 * @param cust_id  
	 * @return 
	 */
	public Do_queryServiSpecIdsByCustIdResponse do_queryServiceSpecIdsByCustId(SOperInfo sOperInfo,Long cust_id);
	/**
	 * 
	 * @param sOperInfo  
	 * @param acct_id  
	 * @return 
	 */
	public Do_queryAcctTreesByGroupIdResponse do_queryAcctTreesByGroupId(SOperInfo sOperInfo,Long acct_id);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_queryProductsByUserIdResponse do_queryProductsByUserId(SOperInfo sOperInfo,SQueryProductsByUserIdReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param sQueryCustBasicInfoReq  
	 * @return 
	 */
	public Do_queryCustBaseInfoResponse do_queryCustBasicInfo(SOperInfo sOperInfo,SQueryCustBaseInfoReq sQueryCustBasicInfoReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param seviceReq  
	 * @return 
	 */
	public Do_queryServicesByUserIdResponse do_queryServicesByUserId(SOperInfo sOperInfo,SQueryServicesReq seviceReq);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryReguideInfoReq  
	 * @return 
	 */
	public Do_QueryReguideInfoForGUIResponse do_queryReguideInfoForGUI(SOperInfo sOper,SQueryReguideInfoReq sQueryReguideInfoReq);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_QueryAcctByIdResponse do_queryAcctById(SOperInfo sOperInfo,SQueryAcctByIdReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_QueryUserByIdResponse do_queryUserById(SOperInfo sOperInfo,SQueryUserByIdReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_QueryCmResIdentityByDevResponse do_queryCmResIdentityByDev(SOperInfo sOperInfo,SQueryCmResIdentityByDevReq req);
	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_QueryAcctByPhoneResponse do_queryAcctInfoByDev(SOperInfo sOperInfo,SQueryAcctByPhoneReq req);
	/**
	 * 
	 * @param sOper  
	 * @param reqParam  
	 * @return 
	 */
	public Do_queryCustomerInfoResponse do_queryCustormInfo(SOperInfo sOper,SQueryCustomerInfoReq reqParam);
	/**
	 * 
	 * @param sOper  
	 * @param prod_id  
	 * @return 
	 */
	public Do_isPrimaryProductResponse do_isPrimaryProduct(SOperInfo sOper,Long prod_id);
	/**
	 * 
	 * @param sOper  
	 * @param sQueryAccountsSuffixReq  
	 * @return 
	 */
	public Do_queryAccountsSuffixResponse do_queryAccountsSuffix(SOperInfo sOper,SQueryAccountsSuffixReq sQueryAccountsSuffixReq);

}