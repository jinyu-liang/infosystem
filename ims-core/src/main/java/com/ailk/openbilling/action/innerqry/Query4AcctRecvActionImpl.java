package com.ailk.openbilling.action.innerqry;

import org.springframework.beans.factory.annotation.Autowired;
import com.ailk.easyframe.web.action.BaseAction;
import com.ailk.easyframe.web.common.annotation.Param;
import com.ailk.easyframe.web.common.annotation.ParamType;
import com.ailk.openbilling.service.innerqry.Query4AcctRecvService;
import com.ailk.openbilling.persistence.imsinner.entity.Do_custQueryResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imsinner.entity.CustQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.Do_acctQueryResponse;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.Do_userQueryResponse;
import com.ailk.openbilling.persistence.imsinner.entity.UserQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryPrimaryProductResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryPrimaryProductReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProductInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryProductInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctValidityResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctValidityReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_qryUserAcctCustResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQryUserAcctCustReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustomerInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustomerReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAccountResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAccountReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryMainUserResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryMainUserReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryAcctInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOperInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryOperReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOrgInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryOrgReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryStaffListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryStaffListReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryCustProfileResponse;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctInvolveInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQeryAccctInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserStsInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserStsInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_query3BeanHisRespose;
import com.ailk.openbilling.persistence.imsinner.entity.SQuery3BeanHisReq;
import com.ailk.openbilling.persistence.innerqry.entity.Do_queryAcctInRouteResponse;
import com.ailk.openbilling.persistence.innerqry.entity.QryAcctInRouteReq;
public class Query4AcctRecvActionImpl extends BaseAction implements Query4AcctRecvAction{


	@Autowired
	protected Query4AcctRecvService innerqry_query4AcctRecvService;

	public void setInnerqry_query4AcctRecvService(Query4AcctRecvService obj){
		this.innerqry_query4AcctRecvService = obj;
	}

	public Query4AcctRecvService getInnerqry_query4AcctRecvService(){
		return innerqry_query4AcctRecvService;
	}

	protected Query4AcctRecvService getCrudService(){
		return innerqry_query4AcctRecvService;
	}

	public Do_custQueryResponse do_queryCustList(@Param("sOper") SOperInfo sOper,@Param("custQueryHolder") CustQueryReqHolder custQueryHolder){
		return innerqry_query4AcctRecvService.do_queryCustList(sOper,custQueryHolder);
	}

	public Do_acctQueryResponse do_queryAcctList(@Param("sOper") SOperInfo sOper,@Param("acctQueryHolder") AcctQueryReqHolder acctQueryHolder){
		return innerqry_query4AcctRecvService.do_queryAcctList(sOper,acctQueryHolder);
	}

	public Do_userQueryResponse do_queryUserList(@Param("sOper") SOperInfo sOper,@Param("userQueryHolder") UserQueryReqHolder userQueryHolder){
		return innerqry_query4AcctRecvService.do_queryUserList(sOper,userQueryHolder);
	}

	public Do_queryPrimaryProductResponse do_queryPrimaryProductInfo(@Param("sOper") SOperInfo sOper,@Param("reqParam") SQueryPrimaryProductReq reqParam){
		return innerqry_query4AcctRecvService.do_queryPrimaryProductInfo(sOper,reqParam);
	}

	public Do_queryProductInfoResponse do_queryProductInfo(@Param("sOper") SOperInfo sOper,@Param("req") SQueryProductInfoReq req){
		return innerqry_query4AcctRecvService.do_queryProductInfo(sOper,req);
	}

	public Do_queryAcctValidityResponse do_queryAcctValidityForGUI(@Param("sOper") SOperInfo sOper,@Param("queryAcctValidityReq") SQueryAcctValidityReq queryAcctValidityReq){
		return innerqry_query4AcctRecvService.do_queryAcctValidityForGUI(sOper,queryAcctValidityReq);
	}

	public Do_qryUserAcctCustResponse do_queryUserAcctCust(@Param("sOper") SOperInfo sOper,@Param("sQryUserAcctCustReq") SQryUserAcctCustReq sQryUserAcctCustReq){
		return innerqry_query4AcctRecvService.do_queryUserAcctCust(sOper,sQryUserAcctCustReq);
	}

	public Do_queryCustomerInfoResponse do_queryCustomer(@Param("sOper") SOperInfo sOper,@Param("SQueryCustomerReq") SQueryCustomerReq SQueryCustomerReq){
		return innerqry_query4AcctRecvService.do_queryCustomer(sOper,SQueryCustomerReq);
	}

	public Do_queryAccountResponse do_queryAccount(@Param("sOper") SOperInfo sOper,@Param("sQueryAccountReq") SQueryAccountReq sQueryAccountReq){
		return innerqry_query4AcctRecvService.do_queryAccount(sOper,sQueryAccountReq);
	}

	public Do_queryUserResponse do_queryUser(@Param("sOper") SOperInfo sOper,@Param("sQueryUserReq") SQueryUserReq sQueryUserReq){
		return innerqry_query4AcctRecvService.do_queryUser(sOper,sQueryUserReq);
	}

	public Do_queryMainUserResponse do_queryMainUser(@Param("sOper") SOperInfo sOper,@Param("squeryMainUser") SQueryMainUserReq squeryMainUser){
		return innerqry_query4AcctRecvService.do_queryMainUser(sOper,squeryMainUser);
	}

	public Do_queryUserListResponse do_queryUserListOld(@Param("sOper") SOperInfo sOper,@Param("acct_id") Long acct_id){
		return innerqry_query4AcctRecvService.do_queryUserListOld(sOper,acct_id);
	}

	public Do_QueryAcctInfoResponse do_queryAcctInfoByCustId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("req") SQueryAcctInfoReq req){
		return innerqry_query4AcctRecvService.do_queryAcctInfoByCustId(sOperInfo,req);
	}

	public Do_queryOperInfoResponse do_queryOperatorInfo(@Param("sOper") SOperInfo sOper,@Param("sQueryOperReq") SQueryOperReq sQueryOperReq){
		return innerqry_query4AcctRecvService.do_queryOperatorInfo(sOper,sQueryOperReq);
	}

	public Do_queryOrgInfoResponse do_queryOrganizeInfo(@Param("sOper") SOperInfo sOper,@Param("sQueryOrgReq") SQueryOrgReq sQueryOrgReq){
		return innerqry_query4AcctRecvService.do_queryOrganizeInfo(sOper,sQueryOrgReq);
	}

	public Do_queryStaffListResponse do_queryStaffList(@Param("oper") SOperInfo oper,@Param("req") SQueryStaffListReq req){
		return innerqry_query4AcctRecvService.do_queryStaffList(oper,req);
	}

	public Do_queryCustProfileResponse do_queryCustProfileByCustId(@Param("sOperInfo") SOperInfo sOperInfo,@Param("cust_id") Long cust_id){
		return innerqry_query4AcctRecvService.do_queryCustProfileByCustId(sOperInfo,cust_id);
	}

	public Do_queryAcctInvolveInfoResponse do_queryAcctInvolveInfo(@Param("sOper") SOperInfo sOper,@Param("sQeryAccctInfoReq") SQeryAccctInfoReq sQeryAccctInfoReq){
		return innerqry_query4AcctRecvService.do_queryAcctInvolveInfo(sOper,sQeryAccctInfoReq);
	}

	public Do_queryUserStsInfoResponse do_queryUserStsInfo(@Param("sOperInfo") SOperInfo sOperInfo,@Param("SQueryUserStsInfoReq") SQueryUserStsInfoReq SQueryUserStsInfoReq){
		return innerqry_query4AcctRecvService.do_queryUserStsInfo(sOperInfo,SQueryUserStsInfoReq);
	}

	public Do_query3BeanHisRespose do_query3BeanHisList(@Param("sOper") SOperInfo sOper,@Param("sQuery3BeanHisReq") SQuery3BeanHisReq sQuery3BeanHisReq){
		return innerqry_query4AcctRecvService.do_query3BeanHisList(sOper,sQuery3BeanHisReq);
	}

	public Do_queryAcctInRouteResponse do_queryAcctInRoute(@Param("sOper") SOperInfo sOper,@Param("qryAcctInRouteReq") QryAcctInRouteReq qryAcctInRouteReq){
		return innerqry_query4AcctRecvService.do_queryAcctInRoute(sOper,qryAcctInRouteReq);
	}


}