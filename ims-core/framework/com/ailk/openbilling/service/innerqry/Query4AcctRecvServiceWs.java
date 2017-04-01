package com.ailk.openbilling.service.innerqry;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.WebServiceProvider;
import javax.jws.WebParam;
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
import com.ailk.openbilling.persistence.imsintf.entity.Do_queryResponse;
import com.ailk.openbilling.persistence.imsintf.entity.QueryTypeList;
import com.ailk.openbilling.persistence.imsintf.entity.SQueryParam;
import com.ailk.openbilling.persistence.imsinner.entity.Do_query3BeanHisRespose;
import com.ailk.openbilling.persistence.imsinner.entity.SQuery3BeanHisReq;
import com.ailk.openbilling.persistence.innerqry.entity.Do_queryAcctInRouteResponse;
import com.ailk.openbilling.persistence.innerqry.entity.QryAcctInRouteReq;
import com.ailk.openbilling.persistence.innerqry.entity.Do_QryAcctInfoInRouteByUserIdResponse;
import com.ailk.openbilling.persistence.innerqry.entity.Do_queryPhoneWithTimeResponse;
import com.ailk.openbilling.persistence.innerqry.entity.SQueryPhoneWithTimeReq;
import jef.codegen.support.NotModified;
@NotModified
@WebService
@WebServiceProvider(portName="Query4AcctRecvService")
public interface Query4AcctRecvServiceWs{

	@WebMethod
	public Do_custQueryResponse do_queryCustList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "custQueryHolder") CustQueryReqHolder custQueryHolder);
	@WebMethod
	public Do_acctQueryResponse do_queryAcctList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "acctQueryHolder") AcctQueryReqHolder acctQueryHolder);
	@WebMethod
	public Do_userQueryResponse do_queryUserList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "userQueryHolder") UserQueryReqHolder userQueryHolder);
	@WebMethod
	public Do_queryPrimaryProductResponse do_queryPrimaryProductInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "reqParam") SQueryPrimaryProductReq reqParam);
	@WebMethod
	public Do_queryProductInfoResponse do_queryProductInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "req") SQueryProductInfoReq req);
	@WebMethod
	public Do_queryAcctValidityResponse do_queryAcctValidityForGUI(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "queryAcctValidityReq") SQueryAcctValidityReq queryAcctValidityReq);
	@WebMethod
	public Do_qryUserAcctCustResponse do_queryUserAcctCust(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQryUserAcctCustReq") SQryUserAcctCustReq sQryUserAcctCustReq);
	@WebMethod
	public Do_queryCustomerInfoResponse do_queryCustomer(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "SQueryCustomerReq") SQueryCustomerReq SQueryCustomerReq);
	@WebMethod
	public Do_queryAccountResponse do_queryAccount(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryAccountReq") SQueryAccountReq sQueryAccountReq);
	@WebMethod
	public Do_queryUserResponse do_queryUser(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryUserReq") SQueryUserReq sQueryUserReq);
	@WebMethod
	public Do_queryMainUserResponse do_queryMainUser(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "squeryMainUser") SQueryMainUserReq squeryMainUser);
	@WebMethod
	public Do_queryUserListResponse do_queryUserListOld(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "acct_id") Long acct_id);
	@WebMethod
	public Do_QueryAcctInfoResponse do_queryAcctInfoByCustId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "req") SQueryAcctInfoReq req);
	@WebMethod
	public Do_queryOperInfoResponse do_queryOperatorInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryOperReq") SQueryOperReq sQueryOperReq);
	@WebMethod
	public Do_queryOrgInfoResponse do_queryOrganizeInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryOrgReq") SQueryOrgReq sQueryOrgReq);
	@WebMethod
	public Do_queryStaffListResponse do_queryStaffList(@WebParam(name = "oper") SOperInfo oper,@WebParam(name = "req") SQueryStaffListReq req);
	@WebMethod
	public Do_queryCustProfileResponse do_queryCustProfileByCustId(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "cust_id") Long cust_id);
	@WebMethod
	public Do_queryAcctInvolveInfoResponse do_queryAcctInvolveInfo(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQeryAccctInfoReq") SQeryAccctInfoReq sQeryAccctInfoReq);
	@WebMethod
	public Do_queryUserStsInfoResponse do_queryUserStsInfo(@WebParam(name = "sOperInfo") SOperInfo sOperInfo,@WebParam(name = "SQueryUserStsInfoReq") SQueryUserStsInfoReq SQueryUserStsInfoReq);
	@WebMethod
	public Do_queryResponse do_query4Acct(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "queryTypeList") QueryTypeList queryTypeList,@WebParam(name = "queryParam") SQueryParam queryParam);
	@WebMethod
	public Do_query3BeanHisRespose do_query3BeanHisList(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQuery3BeanHisReq") SQuery3BeanHisReq sQuery3BeanHisReq);
	@WebMethod
	public Do_queryAcctInRouteResponse do_queryAcctInRoute(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "qryAcctInRouteReq") QryAcctInRouteReq qryAcctInRouteReq);
	@WebMethod
	public Do_QryAcctInfoInRouteByUserIdResponse do_queryAcctInfoInRoute(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "qryAcctInRouteReq") QryAcctInRouteReq qryAcctInRouteReq);
	@WebMethod
	public Do_queryPhoneWithTimeResponse do_queryPhoneWithTime(@WebParam(name = "sOper") SOperInfo sOper,@WebParam(name = "sQueryPhoneWithTimeReq") SQueryPhoneWithTimeReq sQueryPhoneWithTimeReq);

}