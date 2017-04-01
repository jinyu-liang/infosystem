package com.ailk.openbilling.service.innerqry;

import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class Query4AcctRecvServiceImpl extends Query4AcctRecvServiceSkeleton{

	/**
	 * 
	 * @param sOper  
	 * @param custQueryHolder  
	 * @return 
	 */
	public Do_custQueryResponse do_queryCustList(SOperInfo sOper,CustQueryReqHolder custQueryHolder){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param acctQueryHolder  
	 * @return 
	 */
	public Do_acctQueryResponse do_queryAcctList(SOperInfo sOper,AcctQueryReqHolder acctQueryHolder){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param userQueryHolder  
	 * @return 
	 */
	public Do_userQueryResponse do_queryUserList(SOperInfo sOper,UserQueryReqHolder userQueryHolder){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param reqParam  
	 * @return 
	 */
	public Do_queryPrimaryProductResponse do_queryPrimaryProductInfo(SOperInfo sOper,SQueryPrimaryProductReq reqParam){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param req  
	 * @return 
	 */
	public Do_queryProductInfoResponse do_queryProductInfo(SOperInfo sOper,SQueryProductInfoReq req){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param queryAcctValidityReq  
	 * @return 
	 */
	public Do_queryAcctValidityResponse do_queryAcctValidityForGUI(SOperInfo sOper,SQueryAcctValidityReq queryAcctValidityReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQryUserAcctCustReq  
	 * @return 
	 */
	public Do_qryUserAcctCustResponse do_queryUserAcctCust(SOperInfo sOper,SQryUserAcctCustReq sQryUserAcctCustReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param SQueryCustomerReq  
	 * @return 
	 */
	public Do_queryCustomerInfoResponse do_queryCustomer(SOperInfo sOper,SQueryCustomerReq SQueryCustomerReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQueryAccountReq  
	 * @return 
	 */
	public Do_queryAccountResponse do_queryAccount(SOperInfo sOper,SQueryAccountReq sQueryAccountReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQueryUserReq  
	 * @return 
	 */
	public Do_queryUserResponse do_queryUser(SOperInfo sOper,SQueryUserReq sQueryUserReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param squeryMainUser  
	 * @return 
	 */
	public Do_queryMainUserResponse do_queryMainUser(SOperInfo sOper,SQueryMainUserReq squeryMainUser){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param acct_id  
	 * @return 
	 */
	public Do_queryUserListResponse do_queryUserListOld(SOperInfo sOper,Long acct_id){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param req  
	 * @return 
	 */
	public Do_QueryAcctInfoResponse do_queryAcctInfoByCustId(SOperInfo sOperInfo,SQueryAcctInfoReq req){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQueryOperReq  
	 * @return 
	 */
	public Do_queryOperInfoResponse do_queryOperatorInfo(SOperInfo sOper,SQueryOperReq sQueryOperReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQueryOrgReq  
	 * @return 
	 */
	public Do_queryOrgInfoResponse do_queryOrganizeInfo(SOperInfo sOper,SQueryOrgReq sQueryOrgReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param oper  
	 * @param req  
	 * @return 
	 */
	public Do_queryStaffListResponse do_queryStaffList(SOperInfo oper,SQueryStaffListReq req){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param cust_id  
	 * @return 
	 */
	public Do_queryCustProfileResponse do_queryCustProfileByCustId(SOperInfo sOperInfo,Long cust_id){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQeryAccctInfoReq  
	 * @return 
	 */
	public Do_queryAcctInvolveInfoResponse do_queryAcctInvolveInfo(SOperInfo sOper,SQeryAccctInfoReq sQeryAccctInfoReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOperInfo  
	 * @param SQueryUserStsInfoReq  
	 * @return 
	 */
	public Do_queryUserStsInfoResponse do_queryUserStsInfo(SOperInfo sOperInfo,SQueryUserStsInfoReq SQueryUserStsInfoReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param queryTypeList  
	 * @param queryParam  
	 * @return 
	 */
	public Do_queryResponse do_query4Acct(SOperInfo sOper,QueryTypeList queryTypeList,SQueryParam queryParam){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQuery3BeanHisReq  
	 * @return 
	 */
	public Do_query3BeanHisRespose do_query3BeanHisList(SOperInfo sOper,SQuery3BeanHisReq sQuery3BeanHisReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param qryAcctInRouteReq  
	 * @return 
	 */
	public Do_queryAcctInRouteResponse do_queryAcctInRoute(SOperInfo sOper,QryAcctInRouteReq qryAcctInRouteReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param qryAcctInRouteReq  
	 * @return 
	 */
	public Do_QryAcctInfoInRouteByUserIdResponse do_queryAcctInfoInRoute(SOperInfo sOper,QryAcctInRouteReq qryAcctInRouteReq){
		//TODO please implement this method for you business.
		return null;
	}

	/**
	 * 
	 * @param sOper  
	 * @param sQueryPhoneWithTimeReq  
	 * @return 
	 */
	public Do_queryPhoneWithTimeResponse do_queryPhoneWithTime(SOperInfo sOper,SQueryPhoneWithTimeReq sQueryPhoneWithTimeReq){
		//TODO please implement this method for you business.
		return null;
	}


}