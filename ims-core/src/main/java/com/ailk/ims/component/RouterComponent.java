package com.ailk.ims.component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.route.entity.br.RouterIdentity;
import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.easyframe.route.entity.dto.RouteDimension;
import com.ailk.easyframe.route.entity.dto.RouteDimension.Key;
import com.ailk.easyframe.route.entity.dto.RouteQryParam;
import com.ailk.easyframe.route.entity.dto.RouteQueryMode;
import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.route.entity.dto.RouteRet;
import com.ailk.easyframe.route.service.RouteNotFoundException;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.ims.abmtransfer.commom.ErrorCode;
import com.ailk.ims.abmtransfer.commom.SdlUtil;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;

/**
 * 路由相关组件
 * 
 * @Description
 * @author xieqr
 * @Date 2012-10-9
 */
public class RouterComponent extends BaseComponent {
	/**
	 * 通过用户编号在路由中找账户编号 xier 2012-10-9
	 * 
	 * @param userId
	 * @return
	 */
	public Long getAcctIdByUserIdRout(Long userId) {
		RouterClient router = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setResourceId(userId);
		RouteResult info = router.searchDbRoutingInfo(rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_USER_ID_ROUTER, userId);
		}
		Long acctId = info.getRouteDimension().getAccountId();
		imsLogger.debug("query account_id by userId in router userId : ", userId, " accountId : ", acctId);
		return acctId;
	}

	/**
	 * 通过手机号码在路由中找账户号 xieqr 2012-10-9
	 * 
	 * @param identity
	 * @return
	 */
	public Long getAcctIdByPhoneIdRout(String identity) {
		RouterClient router = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setIdentity(identity, RouterIdentity.IdentityType.PHONE);
		RouteResult info = router.searchDbRoutingInfo(rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_PHONE_ID_ROUTER, identity);
		}
		Long acctId = info.getRouteDimension().getAccountId();
		imsLogger.debug("query account_id by phoneId in router phoneId : ", identity, " accountId : ", acctId);
		return acctId;
	}

	public Long getAcctIdByImsi(Long imsi) {
		RouterClient router = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setIdentity(String.valueOf(imsi), RouterIdentity.IdentityType.IMSI);
		RouteResult info = router.searchDbRoutingInfo(rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_PHONE_ID_ROUTER, imsi);
		}
		return info.getRouteDimension().getAccountId();
	}

	public Long getAcctIdByCustId(Long custId) {
		RouterClient router = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setCustomerId(custId);
		RouteResult info = router.searchDbRoutingInfo(rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, custId);
		}

		return info.getRouteDimension().getAccountId();
	}

	/**
	 * 
	 * lijc3 2013-8-27 根据用户id查询账户
	 * 
	 * @param userId
	 * @param vertical
	 * @return
	 */
	public RouteResult getAcctIdByUserId(Long userId, String vertical) {
		RouterClient routeService = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setResourceId(userId);
		RouteResult info = null;
		try {
			info = routeService.searchMdbRoutingInfo(vertical, rdm);
		} catch (RouteNotFoundException e) {
			Date currentDate = DateUtil.currentDate();
			Date beginDate = DateUtil.offsetDate(currentDate, Calendar.DAY_OF_MONTH, -1);
			Date endDate = DateUtil.offsetDate(currentDate, Calendar.DAY_OF_MONTH, 1);
			RouteQueryMode mode = new RouteQueryMode();
			int qryBeginTime = (int) (beginDate.getTime() / 1000);// 框架的时间是1970到现在的秒数
			int qryEndTime = (int) (endDate.getTime() / 1000);
			RouteDimension dim = new RouteDimension();
			dim.setResourceId(userId);
			RouteQryParam qryParams = new RouteQryParam();
			qryParams.setQryMode(mode);
			qryParams.setRouteDim(dim);
			RouteRet ret = routeService.searchMdbRoutingInfo(vertical, qryParams, qryBeginTime, qryEndTime);
			if (ret != null && CommonUtil.isNotEmpty(ret.getRouteResults())) {
				info = ret.getRouteResults().get(0);
			} else {
				throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_USER_ID_ROUTER, userId);
			}
		}
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_USER_ID_ROUTER, userId);
		}
		return info;
	}

	/**
	 * 
	 * lijc3 2013-8-27 根据客户id查询账户
	 * 
	 * @param custId
	 * @param vertical
	 * @return
	 */
	public RouteResult getAcctIdByCustId(Long custId, String vertical) {
		RouterClient routeService = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setCustomerId(custId);
		RouteResult info = routeService.searchMdbRoutingInfo(vertical, rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_PHONE_ID_ROUTER, custId);
		}
		return info;
	}

	/**
	 * 
	 * lijc3 2013-8-27 根据客户id查询账户
	 * 
	 * @param custId
	 * @param vertical
	 * @return
	 */
	public RouteResult getAcctIdRouteResult(Long acctId, String vertical) {
		RouterClient routeService = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setAccountId(acctId);
		RouteResult info = routeService.searchMdbRoutingInfo(vertical, rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_PHONE_ID_ROUTER, acctId);
		}
		return info;
	}

	/**
	 * 
	 * lijc3 2013-8-27 根据imsi查询账户
	 * 
	 * @param imsi
	 * @param vertical
	 * @return
	 */
	public RouteResult getAcctIdByImsi(Long imsi, String vertical) {
		RouterClient routeService = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setIdentity(String.valueOf(imsi), RouterIdentity.IdentityType.IMSI);
		RouteResult info = routeService.searchMdbRoutingInfo(vertical, rdm);
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_PHONE_ID_ROUTER, imsi);
		}
		return info;
	}

	/**
	 * 
	 * lijc3 2013-8-27 根据账户id为mdb维度查询路由信息
	 * 
	 * @param acctId
	 * @param vertical
	 * @return
	 */
	public RouteResult queryRouteByAcctId(Long acctId, String vertical) {
		RouterClient routeService = SpringUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setAccountId(acctId);
		RouteResult result = routeService.searchMdbRoutingInfo(vertical, rdm);
		if (result == null || result.getRouteDimension() == null || result.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_FOUND_ACCT_ID_BY_USER_ID_ROUTER, acctId);
		}
		return result;
	}

	public RouteResult queryRoute(String vertical, int ownerType, long ownerId) {
		RouteDimension rdm = new RouteDimension();
		if (ownerType == SdlUtil.OWNER_TEYP_ACCT || ownerType == SdlUtil.OWNER_TEYP_GROUP) {

			rdm.setValue(Key.ACCT_ID, String.valueOf(ownerId));
		} else if (ownerType == SdlUtil.OWNER_TEYP_USER) {
			rdm.setValue(Key.RESOURCE_ID, String.valueOf(ownerId));
		} else {
			throw new BusinessException(ErrorCode.COMMON_OWNER_TYPE_INVALID, String.valueOf(ownerType));
		}
		RouterClient routeService = SpringUtil.getRouteSearchService();
		RouteResult result = routeService.searchMdbRoutingInfo(vertical, rdm);
		return result;
	}
	
	public Long queryAcctIdByUserId(Long userId){
		RouterClient router = SpringUtil.getRouteSearchService();
		List<RouterResource> list=router.getResourceList(userId);
		String debugInfo = "================query all acctid : ";
		if(CommonUtil.isNotEmpty(list)){
			for(RouterResource result : list){
				debugInfo += result.getAcctId()+",";
			}
			
		}
		 imsLogger.debug(debugInfo);
		RouterResource routerResource=null;
	    for(RouterResource rr:list){
	    	
	    	Integer expireDate=rr.getExpireDate();
			int count=0;
	    	for(RouterResource sysRtResourc:list){
	    		if(expireDate>=sysRtResourc.getExpireDate()){
	    			count++;
	    		}
	    	}
	    	if(count==list.size()){
	    		routerResource=rr;
	    		break;
	    	}
	    }
	   
	    if(routerResource == null){
	    	throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
	    }else{
	    	return routerResource.getAcctId();
	    }
    	
    }
}
