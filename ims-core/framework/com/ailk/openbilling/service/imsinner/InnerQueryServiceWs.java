package com.ailk.openbilling.service.imsinner;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebParam;
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
@WebService
@WebServiceProvider(portName="InnerQueryService")
public interface InnerQueryServiceWs{

	@WebMethod
	public Do_queryOfferingInfoByBusiFlagResponse do_queryProductOfferingInfoByBusiFlag(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryParamExReq") SQueryParamExReq sQueryParamExReq);
	@WebMethod
	public Do_querySplitListResponse do_querySplitRelByObjectId(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQuerySplitRelListReq") SQuerySplitRelListReq sQuerySplitRelListReq);
	@WebMethod
	public Do_checkViceNumResponse do_checkViceNum(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "req") SCheckViceNumReq req);
	@WebMethod
	public Do_queryThreadInfoResponse do_queryThreadInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "threadId") Long threadId);
	@WebMethod
	public Do_queryGroupInfo4GUIResponse do_queryGroupInfo4GUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryGroup4GUIReq") SQueryGroup4GUIReq sQueryGroup4GUIReq);
	@WebMethod
	public Do_queryMultiSim4GuiResponse do_queryMultiSim4Gui(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "queryMutltSimReq") SQueryMultiSimReq queryMutltSimReq);
	@WebMethod
	public Do_queryAcctExemptionFlagResponse do_queryAcctExemptionFlag(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "acct_id") Long acct_id);
	@WebMethod
	public Do_queryUserByOfferNmaeResponse do_queryUserByOfferName(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "req") SQueryUserByOfferNameReq req);
	@WebMethod
	public Do_queryBatchUserResponse do_queryBatchUserList(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "batchUserWhere") BatchUserWhere batchUserWhere,@WebParam(name = "batchUserInfo") BatchUserInfo batchUserInfo);
	@WebMethod
	public Do_queryBatchAcctResponse do_queryBatchAcctList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "batchAcctWhere") BatchAcctWhere batchAcctWhere,@WebParam(name = "batchAcctInfo") BatchAccountInfo batchAcctInfo);
	@WebMethod
	public Do_queryBatchCustResponse do_queryBatchCustList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "batchCustomerInfo") BatchCustomerInfo batchCustomerInfo);
	@WebMethod
	public Do_queryProdListResponse do_queryAcctProdList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryAcctProdListReq") SQueryAcctProdListReq sQueryAcctProdListReq);
	@WebMethod
	public Do_QueryCommonUserInfoResponse do_queryCommonInfoByUserId(@WebParam(name = "SOper") SOperInfo SOper,@WebParam(name = "sQueryUserByIdReq") SQueryUserByIdReq sQueryUserByIdReq);
	@WebMethod
	public Do_QueryCommonUserInfoResponse do_queryCommonInfoByPhoneId(@WebParam(name = "SOper") SOperInfo SOper,@WebParam(name = "sQueryAcctByPhoneReq") SQueryAcctByPhoneReq sQueryAcctByPhoneReq);
	@WebMethod
	public Do_queryFirstBillDaysResponse do_queryFirstBillDays(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "squeryFirstBillDaysReq") SQueryFirstBillDaysReq squeryFirstBillDaysReq);
	@WebMethod
	public Do_queryCustomerHistoryForGUIResponse do_queryCustomerHistory(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryCustomerHistoryReq") SQueryCustomerHistoryReq sQueryCustomerHistoryReq);
	@WebMethod
	public Do_queryVPNGroupResponse do_queryVPNGroup(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "groupReq") SQueryVPNGroupReq groupReq);
	@WebMethod
	public Do_queryBudgetRuleResponse do_queryBudgetRule(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "budgetRuleReq") SQueryBudgetRuleReq budgetRuleReq);
	@WebMethod
	public Do_queryUserPaymemtModeResponse do_queryUserPaymentMode(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryUserPaymentModeReq") SQueryUserPaymentModeReq sQueryUserPaymentModeReq);
	@WebMethod
	public Do_queryParentAcctTreesResponse do_queryParentAcctTrees(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "acct_id") Long acct_id);
	@WebMethod
	public Do_queryUserListForGuiResponse do_queryUserListForGui(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "queryUserListReq") SQueryUserListForGuiReq queryUserListReq);
	@WebMethod
	public Do_queryUserTargetStsInfoRespons do_queryUserTargetStsInfoForGUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryUserTargetStsInfoReq") SQueryUserTargetStsInfoReq sQueryUserTargetStsInfoReq);
	@WebMethod
	public Do_queryProdInfoHisResponse do_queryProdHisInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "queryProdInfoHisReq") QueryProdInfoHisReq queryProdInfoHisReq);
	@WebMethod
	public Do_queryBalanceResponse do_queryBalance(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "queryBalanceReq") SQueryBalanceReq queryBalanceReq);
	@WebMethod
	public Do_fuzzyCustsForGUIResponse do_queryCustomers(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryCustomersReq") SQuerySCustomersReq sQueryCustomersReq);
	@WebMethod
	public Do_queryProdListResponse do_queryUserProdList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryProductsByUserIdReq") SQueryProductsByUserIdReq sQueryProductsByUserIdReq);
	@WebMethod
	public Do_queryUsersByConditionsResponse do_queryUsersByConditions(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "SQueryUsersReq") SQueryUsersReq SQueryUsersReq);
	@WebMethod
	public Do_queryProdOfferingInfoResponse do_queryProdOfferInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQryProdOfferInfoReq") SQryProdOfferInfoReq sQryProdOfferInfoReq);
	@WebMethod
	public Do_queryAcctsByCustIdResponse do_queryAcctsByCustId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryAcctInfoReq req);
	@WebMethod
	public Do_queryOfferingInfoResponse do_queryOfferingInfoByOfferId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "offering_id") Integer offering_id);
	@WebMethod
	public Do_queryBugetResponse do_queryBuget(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sQueryBugetReq") SQueryBugetReq sQueryBugetReq);
	@WebMethod
	public Do_querySplitListResponse do_querySplitInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "splitListReq") SQuerySplitListReq splitListReq);
	@WebMethod
	public Do_querySplitListResponse do_querySplitByAcctId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "splitByAcctIdReq") SQuerySplitListReq splitByAcctIdReq);
	@WebMethod
	public Do_querySplitListResponse do_querySplitByUserId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "splitByUserIdReq") SQuerySplitByUserReq splitByUserIdReq);
	@WebMethod
	public Do_queryAcctsForGUIResponse do_queryAcctsForGUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryAcctsForGUIReq") SQueryAcctsForGUIReq sQueryAcctsForGUIReq);
	@WebMethod
	public Do_QueryCustIdByAcctIdResponse do_queryCustIdByAcctId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryCustIdByAcctIdReq req);
	@WebMethod
	public Do_queryUsersByAcctIdResponse do_queryUsersByAcctId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "acct_id") Long acct_id,@WebParam(name = "relation_type") Integer relation_type);
	@WebMethod
	public Do_queryServiSpecIdsByCustIdResponse do_queryServiceSpecIdsByCustId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "cust_id") Long cust_id);
	@WebMethod
	public Do_queryAcctTreesByGroupIdResponse do_queryAcctTreesByGroupId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "acct_id") Long acct_id);
	@WebMethod
	public Do_queryProductsByUserIdResponse do_queryProductsByUserId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryProductsByUserIdReq req);
	@WebMethod
	public Do_queryCustBaseInfoResponse do_queryCustBasicInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "sQueryCustBasicInfoReq") SQueryCustBaseInfoReq sQueryCustBasicInfoReq);
	@WebMethod
	public Do_queryServicesByUserIdResponse do_queryServicesByUserId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "seviceReq") SQueryServicesReq seviceReq);
	@WebMethod
	public Do_QueryReguideInfoForGUIResponse do_queryReguideInfoForGUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryReguideInfoReq") SQueryReguideInfoReq sQueryReguideInfoReq);
	@WebMethod
	public Do_QueryAcctByIdResponse do_queryAcctById(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryAcctByIdReq req);
	@WebMethod
	public Do_QueryUserByIdResponse do_queryUserById(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryUserByIdReq req);
	@WebMethod
	public Do_QueryCmResIdentityByDevResponse do_queryCmResIdentityByDev(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryCmResIdentityByDevReq req);
	@WebMethod
	public Do_QueryAcctByPhoneResponse do_queryAcctInfoByDev(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryAcctByPhoneReq req);
	@WebMethod
	public Do_queryCustomerInfoResponse do_queryCustormInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "reqParam") SQueryCustomerInfoReq reqParam);
	@WebMethod
	public Do_isPrimaryProductResponse do_isPrimaryProduct(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "prod_id") Long prod_id);
	@WebMethod
	public Do_queryAccountsSuffixResponse do_queryAccountsSuffix(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryAccountsSuffixReq") SQueryAccountsSuffixReq sQueryAccountsSuffixReq);

}