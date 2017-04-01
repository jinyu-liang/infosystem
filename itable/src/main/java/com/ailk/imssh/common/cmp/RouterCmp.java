package com.ailk.imssh.common.cmp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jef.database.Condition.Operator;
import jef.tools.StringUtils;

import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.route.dao.RouteDbDao;
import com.ailk.easyframe.route.dao.RouteMdbDao;
import com.ailk.easyframe.route.entity.br.RouterIdentity;
import com.ailk.easyframe.route.entity.br.RouterIdentity.IdentityType;
import com.ailk.easyframe.route.entity.br.RouterMain;
import com.ailk.easyframe.route.entity.br.RouterPbx;
import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.easyframe.route.entity.dto.RouteDimension;
import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.route.entity.exr.RtGroup;
import com.ailk.easyframe.route.entity.exr.RtGroupExterior;
import com.ailk.easyframe.route.entity.exr.RtGroupRelation;
import com.ailk.easyframe.route.entity.exr.RtPayInfo;
import com.ailk.easyframe.route.service.RouteNotFoundException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareRel;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.itable.entity.IUserPbx;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

/**
 * @Description 路由信息发布处理
 * @author lijc3
 * @Date 2012-7-11
 */
public class RouterCmp extends BaseCmp {
    private RouteMdbDao routeMdbDao;
    private RouteDbDao routeDbDao;
    
	/**
	 * 如果custId已经发布过路由，再创建新的账户，则调用这个方法 <br>
	 * 发布主维度路由，SYS_RT_ACCOUNT
	 * 
	 * @param acctId
	 * @param custId
	 * @param regionCode
	 */
	public void createAcctsRouter(Long acctId, Long custId, Integer regionCode, Date validDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();

		RouterMain main = new RouterMain();
		main.setAcctId(acctId);
		main.setCustId(custId);
		main.setOperatorId(EnumCodeExDefine.OPERATOR_ID);
		main.setRegionCode(regionCode);
		main.setValidDate(validDate);
		imsLogger.debug("******begin to create main  router***", "acctId: ", acctId, "; custId: ", custId);
		router.publishRouterMain(main);
	}

	/**
	 * lijc3 2012-7-11 发布ca_account表的路由信息 <br>
	 * 发布包括主，客维度路由，SYS_RT_ACCOUNT，SYS_RT_CUSTOMER
	 * 
	 * @param acctId
	 *            必传
	 * @param custId
	 *            如果为null，表示发布虚账户
	 * @param regionCode
	 *            可null
	 */
	public void createAcctRouter(Long acctId, Long custId, Integer regionCode, Date validDate, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();

		RouteDimension mapValue = new RouteDimension();
		mapValue.setAccountId(acctId);
		if (custId != null) {
			mapValue.setCustomerId(custId);
		}
		if (regionCode != null) {
			mapValue.setRegionCode(regionCode);
		} else {
			mapValue.setRegionCode(EnumCodeExDefine.REGION_CODE_DEFAULT);
		}
		mapValue.setOperatorId(EnumCodeExDefine.OPERATOR_ID);

		imsLogger.debug("******begin to create account router***", "acctId: ", acctId, "; custId: ", custId);

		router.publishDimensions(mapValue, validDate, dealRouteExpireDate(expireDate));
	}

	/**
	 * lijc3 2012-7-11 发布user 路由信息
	 * 
	 * @param userId
	 *            必传
	 * @param acctId
	 *            如果为null 取I_DATA_INDEX里的user_id
	 * @param phoneId
	 *            必传
	 */
	public void createUserRouter(Long userId, Long acctId, String phoneId, Date validDate, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension mapValue = new RouteDimension();
		mapValue.setAccountId(acctId);
		mapValue.setResourceId(userId);
		if (CommonUtil.isNotEmpty(phoneId)) {
			mapValue.addIdentity(phoneId, IdentityType.PHONE);
		}
		mapValue.setOperatorId(EnumCodeExDefine.OPERATOR_ID);

		imsLogger.debug("******begin to create account router***", "acctId: ", acctId, "; userId: ", userId, "; phoneId: ", phoneId);

		router.publishDimensions(mapValue, validDate, dealRouteExpireDate(expireDate));
	}

	/**
	 * lijc3 2013-7-30 支持发布imsi
	 * 
	 * @param userId
	 * @param acctId
	 * @param phoneId
	 * @param imsi
	 */
	public void createUserRouter(Long userId, Long acctId, String phoneId, String imsi, Date validDate, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension mapValue = new RouteDimension();
		mapValue.setAccountId(acctId);
		mapValue.setResourceId(userId);
		if (CommonUtil.isNotEmpty(phoneId)) {
			mapValue.addIdentity(phoneId, IdentityType.PHONE);
		}
		if (CommonUtil.isNotEmpty(imsi) && !"0".equals(imsi.trim())) {
			mapValue.addIdentity(imsi, IdentityType.IMSI);
		}
		mapValue.setOperatorId(EnumCodeExDefine.OPERATOR_ID);

		imsLogger.debug("******begin to create account router***", "acctId: ", acctId, "; userId: ", userId, "; phoneId: ", phoneId,
				"; imsi :", imsi);

		router.publishDimensions(mapValue, validDate, dealRouteExpireDate(expireDate));
	}

	/**
	 * lijc3 2012-7-12 发布cust路由
	 * 
	 * @param custId
	 */
	public void createCustRouter(Long custId, Date validDate, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension mapValue = new RouteDimension();
		mapValue.setCustomerId(custId);
		imsLogger.debug("******begin to create cust router***", "custId=", custId);
		router.publishDimensions(mapValue, validDate, dealRouteExpireDate(expireDate));
	}

	/**
	 * 清除ContextHolder里面的账户id和用户id lijc3 2012-8-8
	 */
	public void cleanRequestParamter() {
		ITableUtil.cleanRequestParamter();
	}

	/**
	 * lijc3 2012-8-8 往reqest中设置acctId和acctId
	 * 
	 * @param acctId
	 *            账户id
	 * @param userId
	 *            用户id
	 */
	public void setUserIdOrAcctIdIntoRequest(Long userId, Long acctId) {
		ITableUtil.setValue2ContextHolder(null, acctId, userId);
	}

	/**
	 * lijc3 2012-9-22 判断cust_id是否已经发布路由
	 * 
	 * @param custId
	 * @return
	 */
	public boolean isCustRouted(Long custId) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setCustomerId(custId);
		try {
			RouteResult ret = router.searchDbRoutingInfo("*",rdm);
			return ret != null;
		} catch (Exception e) {
			return false;
		}

	}
	
	public RouteResult queryCustRoute(Long custId){
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension rdm = new RouteDimension();
		rdm.setCustomerId(custId);
	
		RouteResult ret = router.searchDbRoutingInfo("*",rdm);
		if (ret == null || ret.getRouteDimension() == null || ret.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCTS_OF_CUST_NOTEXIST, custId);
		}
		return ret;
	}

	/**
	 * lijc3 2012-10-9 判断userId是否已经发布路由
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isUserRouted(Long userId) {
		try {
			return queryUserRouter(userId) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public RouteResult queryUserRouter(Long userId) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		
		RouteDimension rdm = new RouteDimension();
		rdm.setResourceId(userId);
		RouteResult info = router.searchDbRoutingInfo("*",rdm);
		
		if (info == null || info.getRouteDimension() == null || info.getRouteDimension().getAccountId() == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
		}
		return info;
		
	}
    public Long queryAcctIdByUserId(Long userId){
		RouterClient router = SpringExUtil.getRouteSearchService();
		List<RouterResource> list=router.getResourceList(userId);
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
    
    public RouterResource querySegAcctByUser(long userId){
    	 List<RouterResource> resourceList = DBUtil.query(RouterResource.class,new DBCondition(RouterResource.Field.resourceId,userId));
    	 if(CommonUtil.isEmpty(resourceList)){
 			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
 		}else {
 			if(resourceList.size() > 1){
 				RouterResource resource = resourceList.get(0);
 				for(int i=1; i<resourceList.size(); i++){
 					RouterResource other = resourceList.get(i);
 					if(other.getExpireDate() > resource.getExpireDate()){
 						resource = other;
 					}
 				}
 				return resource;
 			}else{
 				return resourceList.get(0);
 			}
 		}
    }
    
    
    public RouterMain querySegAcctByCust(long custId){
    	List<RouterMain> acctList = DBUtil.query(RouterMain.class,new DBCondition(RouterMain.Field.custId,custId));
    	if(CommonUtil.isEmpty(acctList)){
 			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCTS_OF_CUST_NOTEXIST, custId);
 		}else {
 			if(acctList.size() > 1){
 				RouterMain acct = acctList.get(0);
 				for(int i=1; i<acctList.size(); i++){
 					RouterMain other = acctList.get(i);
 					if(other.getExpireDate() > acct.getExpireDate()){
 						acct = other;
 					}
 				}
 				return acct;
 			}else{
 				return acctList.get(0);
 			}
 		}
    }
    
	/**
	 * lijc3 2012-11-1 判断账户是否发布路由
	 * 
	 * @param acctId
	 * @return
	 */
	public boolean isAcctRouted(Long acctId) {
		try {
			RouteResult ret = getAccountRoute(acctId);
			return ret != null;
		} catch (Exception e) {
			return false;
		}
	}

	public RouteResult getAccountRoute(Long acctId) {
		RouterClient router = SpringExUtil.getRouteSearchService();

		RouteDimension rdm = new RouteDimension();
		rdm.setAccountId(acctId);
		RouteResult ret = router.searchDbRoutingInfo("*",rdm);
		return ret;
	}

	/**
	 * lijc3 2012-9-22 查询phone_id的历史路由信息
	 * 
	 * @param phoneId
	 * @param date
	 * @return 账户信息
	 */
	@SuppressWarnings("unused")
	private List<Long> queryIdentityHisRout(String phoneId, Date date) {
		// RouterClient router = SpringExUtil.getRouteSearchService();
		// List<HistoryRouterIdentity> hisIdentityList = router.get(phoneId,
		// null, date);
		// if (CommonUtil.isNotEmpty(hisIdentityList))
		// {
		// List<Long> acctList = new ArrayList<Long>();
		// for (HistoryRouterIdentity iden : hisIdentityList)
		// {
		// acctList.add(iden.getAcctId());
		// }
		// return acctList;
		// }
		return null;
		// TODO
	}

	/**
	 * lijc3 2012-9-22 查询phone_id的历史路由信息
	 * 
	 * @param phoneId
	 * @param date
	 * @return 账户信息
	 */
	@SuppressWarnings("unused")
	private List<Long> queryUserIdHisRout(Long userId, Date date) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		List<RouterResource> hisResList = router.getHistoryRouterResources(userId, null, date);
		if (CommonUtil.isNotEmpty(hisResList)) {
			List<Long> acctList = new ArrayList<Long>();
			for (RouterResource iden : hisResList) {
				acctList.add(iden.getAcctId());
			}
			return acctList;
		}
		return null;
	}

	/**
	 * lijc3 2012-10-8 通过路由获取acct_id
	 * 
	 * @param userId
	 * @return
	 */
	public Long getAcctIdByUserIdRout(Long userId) {
		RouteResult info = queryUserRouter(userId);
		return Long.valueOf(info.getRouteDimension().getAccountId());
	}

	/**
	 * 删除路由信息，将信息移入到历史表中 目前只支持删除账户与用户、账户与手机号码之间的关系 wukl 2012-12-7
	 */
	public void deleteRouter(String identity, Long resourceId, String imsi, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension value = new RouteDimension();
		if (CommonUtil.isNotEmpty(identity)) {
			value.addIdentity(identity, RouterIdentity.IdentityType.PHONE);
		}
		if (CommonUtil.isNotEmpty(imsi) && !"0".equals(imsi.trim())) {
			value.addIdentity(imsi, RouterIdentity.IdentityType.IMSI);
		}
		if (resourceId != null) {
			value.setResourceId(resourceId);
		}

		imsLogger.debug("****** remove router when delete user, user_id = [ " + resourceId + " ], phone_id = [ " + identity + " ]");
		router.removeDimensions(value, dealRouteExpireDate(expireDate));

	}

	/**
	 * 删除路由信息，将信息移入到历史表中 目前只支持删除账户与用户、账户与手机号码之间的关系 wukl 2012-12-7
	 */
	public void deleteAcctRouter(Long acctId, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension value = new RouteDimension();

		value.setAccountId(acctId);
		imsLogger.debug("****** remove router when delete account, acct_id = [ " + acctId + " ]");
		router.removeDimensions(value, dealRouteExpireDate(expireDate));

	}

	/**
	 * 删除路由信息，将信息移入到历史表中 目前只支持删除账户与用户、账户与手机号码之间的关系 wukl 2012-12-7
	 */
	public void deleteRouter(Long userId,String identity, String imsi, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension value = new RouteDimension();
		if (CommonUtil.isNotEmpty(identity)) {
			value.addIdentity(identity, RouterIdentity.IdentityType.PHONE);
		}
		if (CommonUtil.isNotEmpty(imsi) && !"0".equals(imsi.trim())) {
			value.addIdentity(imsi, IdentityType.IMSI);
		}
		if (userId != null) {
			value.setResourceId(userId);
		}


		router.removeDimensions(value, dealRouteExpireDate(expireDate));

	}

	public void deleteRouterWithExpire(String identity, String imsi, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension value = new RouteDimension();
		if (CommonUtil.isNotEmpty(identity)) {
			value.addIdentity(identity, RouterIdentity.IdentityType.PHONE);
		}
		if (CommonUtil.isNotEmpty(imsi) && !"0".equals(imsi.trim())) {
			value.addIdentity(imsi, IdentityType.IMSI);
		}

		router.removeDimensions(value, dealRouteExpireDate(expireDate));

	}

	/**
	 * lijc3 2013-7-24 发布行业网关路由信息
	 * 
	 * @param userId
	 * @param servCode
	 * @param opCode
	 */
	public void createEnterpriseRoute(Long userId, String servCode, String opCode, Long spCode, Date date, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteService();
		RouteDimension rdm = new RouteDimension();
		if (spCode != null) {
			rdm.setIndustry(servCode, opCode, spCode);
		} else {
			rdm.setIndustry(servCode, opCode);
		}
		rdm.setResourceId(userId);
		router.publishDimensions(rdm, date, dealRouteExpireDate(expireDate));
	}

	public void deleteEnterpriseRoute(Long userId, String servCode, String opCode, Long spCode, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteService();
		RouteDimension rdm = new RouteDimension();
		if (spCode != null) {
			rdm.setIndustry(servCode, opCode, spCode);
		} else {
			rdm.setIndustry(servCode, opCode);
		}
		router.removeDimensions(rdm, dealRouteExpireDate(expireDate));
	}

	/**
	 * lijc3 2013-7-24 发布代付路由信息
	 * 
	 * @param payList
	 */
	public void createSplitRouter(List<RtPayInfo> payList) {
		RouterClient router = SpringExUtil.getRouteService();
		router.publishPayInfos(payList);
	}

	/**
	 * lijc3 2013-7-24 发布群组关系路由信息
	 * 
	 * @param rtGroupList
	 */
	public void createGroupMemberRoute(List<RtGroup> rtGroupList) {
		RouterClient router = SpringExUtil.getRouteService();
		router.publishGroupInfos(rtGroupList);
	}

	/**
	 * lijc3 2013-7-24 发布群组关系路由信息
	 * 
	 * @param rtGroupList
	 */
	public void createGroupExteriorsRoute(List<RtGroupExterior> groupExteriors) {
		RouterClient router = SpringExUtil.getRouteService();
		router.publishGroupExteriors(groupExteriors);
	}

	/**
	 * lijc3 2013-7-24 发布群组之间关系路由
	 * 
	 * @param rtGroupRelaList
	 */
	public void dealGroupRela(List<CaAccountRel> acctRelList){
		List<RtGroupRelation> rtGroupRelaList = new ArrayList<RtGroupRelation>();
		for(CaAccountRel rel : acctRelList){
			RtGroupRelation rtGroupRel = createRtGroupRelation(rel.getRelGroupId(),rel.getGroupId(),rel.getValidDate(),rel.getExpireDate());
			rtGroupRelaList.add(rtGroupRel);
		}
		createGroupRela(rtGroupRelaList);
	}
	
	public void createGroupRela(List<RtGroupRelation> rtGroupRelaList) {
		RouterClient router = SpringExUtil.getRouteService();
		router.publishGroupRelations(rtGroupRelaList);
	}

	/**
	 * lijc3 2013-7-24 mdb路由信息是否存在同一个mdb中
	 * 
	 * @param routeInfos
	 * @return
	 */
	public boolean isRouteToSameMdb(Long oldAcctId, Long newAcctId) {
		try {
			List<RouteResult> rtList = new ArrayList<RouteResult>();
			rtList.add(queryAcctMdbRout(ConstantExDefine.USER_MDB_VERTIVAL_VALUE, oldAcctId));
			rtList.add(queryAcctMdbRout(ConstantExDefine.USER_MDB_VERTIVAL_VALUE, newAcctId));
			RouterClient router = SpringExUtil.getRouteService();
			return router.isSameSharding(rtList);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * lijc3 2013-7-24 根据账户查询MDB中的路由信息
	 * 
	 * @param verticalValue
	 * @param acctId
	 * @return
	 */
	public RouteResult queryAcctMdbRout(String verticalValue, Long acctId) {
		RouterClient router = SpringExUtil.getRouteService();
		RouteDimension rdm = new RouteDimension();
		rdm.setAccountId(acctId);
		RouteResult result = router.searchMdbRoutingInfo(verticalValue, rdm);
		return result;
	}

	private RtPayInfo createRtPayRelation(List<CoSplitCharValue> charValueList) {
		if (CommonUtil.isEmpty(charValueList)) {
			return null;
		}
		Long userId = charValueList.get(0).getObjectId();
		Long payAcctId = null;
		for (CoSplitCharValue value : charValueList) {
			if (value.getSpecCharId() == SpecCodeDefine.PAY_ACCT_ID) {
				payAcctId = Long.valueOf(value.getValue());
				break;
			}
		}
		if (payAcctId == null) {
			return null;
		}
		Date validDate = null;
		Date expireDate = null;
		CoSplitCharValue rv = ITableUtil.getFirstDataObjectByValidDate(charValueList);
		if (rv == null) {
			return null;
		}
		validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(charValueList);
		expireDate = rv.getExpireDate();

		RtPayInfo info = createRtPayRelation(payAcctId, userId, validDate, expireDate, EnumCodeExDefine.PAY_RELATION_ROUTE_ACCT_USER);
		return info;
	}

	/*
	 * private RtPayInfo createRtPayRelationByPayRel(List<CoSplitPayRel>
	 * payRelList) { if (CommonUtil.isEmpty(payRelList)) {
	 * return null; } Long payAcctId = payRelList.get(0).getReguidObjectId();
	 * Long userId = payRelList.get(0).getObjectId(); Date
	 * validDate = null; Date expireDate = null; CoSplitPayRel rv =
	 * ITableUtil.getFirstDataObjectByValidDate(payRelList); if (rv
	 * == null) { return null; } validDate = rv.getValidDate(); rv =
	 * ITableUtil.getLastDataObjectByExpireDate(payRelList);
	 * expireDate = rv.getExpireDate(); RtPayInfo info =
	 * createRtPayRelation(payAcctId, userId, validDate,
	 * expireDate,EnumCodeExDefine.PAY_RELATION_ROUTE_ACCT_USER); return info; }
	 */
	// 根据co_split_char_value创建
	private RtPayInfo createRtPayRelationByCharValue(List<CoSplitCharValue> charValueList) {
		if (CommonUtil.isEmpty(charValueList)) {
			return null;
		}
		Long payAcctId = CommonUtil.string2Long(charValueList.get(0).getValue());
		Long userId = charValueList.get(0).getObjectId();

		Date validDate = null;
		Date expireDate = null;
		CoSplitCharValue rv = ITableUtil.getFirstDataObjectByValidDate(charValueList);
		if (rv == null) {
			return null;
		}
		validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(charValueList);
		expireDate = rv.getExpireDate();

		RtPayInfo info = createRtPayRelation(payAcctId, userId, validDate, expireDate, EnumCodeExDefine.PAY_RELATION_ROUTE_ACCT_USER);
		return info;
	}

	private RtPayInfo createRtPayRelByUserShareProd(List<CmUserShareRel> payRelList) {
		if (CommonUtil.isEmpty(payRelList)) {
			return null;
		}
		Long userId = payRelList.get(0).getResourceId();
		Long viceUserId = payRelList.get(0).getViceResourceId();

		Date validDate = null;
		Date expireDate = null;
		CmUserShareRel rv = ITableUtil.getFirstDataObjectByValidDate(payRelList);
		if (rv == null) {
			return null;
		}
		validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(payRelList);
		expireDate = rv.getExpireDate();

		RtPayInfo info = createRtPayRelation(userId, viceUserId, validDate, expireDate, EnumCodeExDefine.PAY_RELATION_ROUTE_USER_USER);
		return info;
	}

	public void publicSplitRoute(Map<Long, Set<Long>> splitMap) {
		if (CommonUtil.isEmpty(splitMap)) {
			return;
		}
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		List<RtPayInfo> rtPayInfoList = new ArrayList<RtPayInfo>();
		for (Entry<Long, Set<Long>> entry : splitMap.entrySet()) {
			Long payAcctId = entry.getKey();
			for (Long userId : entry.getValue()) {
				/*
				 * ITableUtil.setValue2ContextHolder(null, null, userId);
				 * List<CoSplitPayRel> rvList =
				 * DBUtil.query(CoSplitPayRel.class, new
				 * DBCondition(CoSplitPayRel.Field.objectId, userId), new
				 * DBCondition(CoSplitPayRel.Field.reguidObjectId, payAcctId));
				 * if (CommonUtil.isNotEmpty(rvList)) {
				 * rtPayInfoList.add(createRtPayRelationByPayRel(rvList)); }
				 */
				long routeAcctId = routCmp.queryAcctIdByUserId(userId);
				ITableUtil.setValue2ContextHolder(null, routeAcctId, null);
				List<CoSplitCharValue> rvList = DBUtil.query(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.objectId,
						userId), new DBCondition(CoSplitCharValue.Field.value, String.valueOf(payAcctId)), new DBCondition(
						CoSplitCharValue.Field.specCharId, SpecCodeDefine.PAY_ACCT_ID));
				if (CommonUtil.isNotEmpty(rvList)) {
					rtPayInfoList.add(createRtPayRelationByCharValue(rvList));
				}
			}
		}
		if (CommonUtil.isNotEmpty(rtPayInfoList)) {
			imsLogger.debug("^^^split router msg ", rtPayInfoList);
			routCmp.createSplitRouter(rtPayInfoList);
		}

	}

	public void publicUserShareProdRoute(Map<Long, Set<Long>> splitMap) {
		if (CommonUtil.isEmpty(splitMap)) {
			return;
		}
		List<RtPayInfo> rtPayInfoList = new ArrayList<RtPayInfo>();
		for (Entry<Long, Set<Long>> entry : splitMap.entrySet()) {
			Long userId = entry.getKey();
			for (Long viceUserId : entry.getValue()) {
				ITableUtil.setValue2ContextHolder(null, null, userId);
				List<CmUserShareRel> rvList = DBUtil.query(CmUserShareRel.class,
						new DBCondition(CmUserShareRel.Field.resourceId, userId), new DBCondition(CmUserShareRel.Field.viceResourceId,
								viceUserId));
				if (CommonUtil.isNotEmpty(rvList)) {
					rtPayInfoList.add(createRtPayRelByUserShareProd(rvList));
				}
			}
		}
		if (CommonUtil.isNotEmpty(rtPayInfoList)) {
			imsLogger.dump("^^^split router msg ", rtPayInfoList);
			createSplitRouter(rtPayInfoList);
		}

	}

	private RtPayInfo createRtPayRelation(Long originalId, Long oppAttr, Date validDate, Date expireDate, short payType) {
		RtPayInfo info = new RtPayInfo();
		// info.setExpireDate(expireDate);
		// info.setValidDate(validDate);
		info.setExpireDt(this.toMdbExpireDate(expireDate));
		info.setValidDt(this.toMdbValidDate(validDate));
		info.setOriginalId(oppAttr);
		info.setPayId(originalId);
		// 账户给用户代付
		info.setPayType(payType);
		return info;
	}

	/**
	 * lijc3 2013-7-30 处理群组路由
	 * 
	 * @param memberMap
	 *            群组以及群组对应的用户idMAP
	 */
	// 增加了in 数据最大限制
	public void publicGroupMemberRoute(Map<Long, Set<Long>> memberMap, Map<Long, Set<String>> exteriorMap,Map<String, List<Date>> userIdValidDateMap,Map<String, List<Date>> phoneIdValidDateMap) {
		if (CommonUtil.isNotEmpty(memberMap)) {
			for (Entry<Long, Set<Long>> entry : memberMap.entrySet()) {
				Long groupId = entry.getKey();
				Long acctId=queryAcctIdByUserId(groupId);
                ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
				List<CaAccountRv> rvList = getCaAccountRvList(groupId, new ArrayList<Long>(entry.getValue()), null,userIdValidDateMap,phoneIdValidDateMap);
				if (CommonUtil.isNotEmpty(rvList)) {
					Map<Long, List<CaAccountRv>> rvMap = new HashMap<Long, List<CaAccountRv>>();
					for (CaAccountRv rv : rvList) {
						List<CaAccountRv> list = rvMap.get(rv.getResourceId());
						if (list == null) {
							list = new ArrayList<CaAccountRv>();
							rvMap.put(rv.getResourceId(), list);
						}
						list.add(rv);
					}
					List<RtGroup> pubGroupList = new ArrayList<RtGroup>();
					for (Entry<Long, List<CaAccountRv>> entry2 : rvMap.entrySet()) {
						RtGroup rtGroup = createRtGroup(entry2.getValue());
						pubGroupList.add(rtGroup);
					}
					imsLogger.debug("^^^group member router msg ", pubGroupList);
					createGroupMemberRoute(pubGroupList);
				}
			}
		}

		if (CommonUtil.isNotEmpty(exteriorMap)) {
			for (Entry<Long, Set<String>> entry : exteriorMap.entrySet()) {
				Long groupId = entry.getKey();
				
				Long acctId=queryAcctIdByUserId(groupId);
                ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
				List<CaAccountRv> rvList = getCaAccountRvList(groupId, null, new ArrayList<String>(entry.getValue()),userIdValidDateMap,phoneIdValidDateMap);
				if (CommonUtil.isNotEmpty(rvList)) {
					Map<String, List<CaAccountRv>> rvMap = new HashMap<String, List<CaAccountRv>>();
					for (CaAccountRv rv : rvList) {
						List<CaAccountRv> list = rvMap.get(rv.getResourceId().toString());
						if (list == null) {
							list = new ArrayList<CaAccountRv>();
							rvMap.put(rv.getPhoneId(), list);
						}
						list.add(rv);
					}
					List<RtGroupExterior> pubGroupList = new ArrayList<RtGroupExterior>();
					for (Entry<String, List<CaAccountRv>> entry2 : rvMap.entrySet()) {
						RtGroupExterior RtGroupExterior = createRtGroupExterior(entry2.getValue());
						pubGroupList.add(RtGroupExterior);
					}
					imsLogger.debug("^^^group member router msg ", pubGroupList);
					createGroupExteriorsRoute(pubGroupList);
				}
			}
		}

	}

	private RtGroupExterior createRtGroupExterior(List<CaAccountRv> rvList) {
		CaAccountRv rv = ITableUtil.getFirstDataObjectByValidDate(rvList);
		if (rv == null) {
			return null;
		}
		RtGroupExterior rtExterior = new RtGroupExterior();
		rtExterior.setGroupId(rv.getAcctId());
	      //手机号码左边去0
        if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1)
        {
            String str = rv.getPhoneId();
            String newStr = str.replaceAll("^(0+)", "");
            rtExterior.setPhoneId(newStr);
        }
        else
        {
        	rtExterior.setPhoneId(rv.getPhoneId());
        }
		Date validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(rvList);
		Date expireDate = rv.getExpireDate();
		// 失效时间比当前时间大的维护关系，失效时间比当前时间小的，删除路由关系
		rtExterior.setExpireDt(this.toMdbExpireDate(expireDate));
		rtExterior.setValidDt(this.toMdbValidDate(validDate));
		return rtExterior;
	}

	private RtGroup createRtGroup(List<CaAccountRv> rvList) {
		CaAccountRv rv = ITableUtil.getFirstDataObjectByValidDate(rvList);
		if (rv == null) {
			return null;
		}
		RtGroup rtGroup = new RtGroup();
		rtGroup.setGroupId(rv.getAcctId());
		rtGroup.setOriginalId(rv.getResourceId());
		Date validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(rvList);
		Date expireDate = rv.getExpireDate();
		// 失效时间比当前时间大的维护关系，失效时间比当前时间小的，删除路由关系
		rtGroup.setExpireDt(this.toMdbExpireDate(expireDate));
		rtGroup.setValidDt(this.toMdbValidDate(validDate));
		return rtGroup;
	}

	/**
	 * lijc3 2013-8-7 手工上发处理代付路由信息
	 * 
	 * @param valueList
	 */
	public void dealManualSplitRouter(List<CoSplitCharValue> valueList) {
		RtPayInfo rtInfo = createRtPayRelation(valueList);
		List<RtPayInfo> rtPayInfoList = new ArrayList<RtPayInfo>();
		rtPayInfoList.add(rtInfo);
		createSplitRouter(rtPayInfoList);
	}

	/**
	 * lijc3 2013-8-7 手工上发处理群组成员路由信息
	 * 
	 * @param rvList
	 */
	public void dealManualGroupMemberRouter(List<CaAccountRv> rvList) {
		RtGroup rtGroup = createRtGroup(rvList);
		List<RtGroup> pubGroupList = new ArrayList<RtGroup>();
		pubGroupList.add(rtGroup);
		createGroupMemberRoute(pubGroupList);
	}

	/**
	 * lijc3 2013-8-7 手工上发处理行业网关路由信息
	 * 
	 * @param priseList
	 */
	public void dealManualEnterpriseRouter(List<CmUserEnterprise> priseList) {
		CmUserEnterprise prise = ITableUtil.getLastDataObjectByExpireDate(priseList);
		if (prise == null) {
			return;
		}
		// createEnterpriseRoute(prise.getResourceId(), prise.getServiceCode(),
		// prise.getOperatorCode(),prise.getValidDate(), prise.getExpireDate());
	}

	/**
	 * lijc3 2013-8-7 手工上发处理imsi路由
	 * 
	 * @param identityList
	 */
	public void dealManualImsiRouter(List<CmResIdentity> identityList) {
		/*
		 * if (CommonUtil.isNotEmpty(identityList)) { Date currDate =
		 * context.getCommitDate() == null ? DateUtil.currentDate() :
		 * context.getCommitDate(); CmResIdentity identity =
		 * ITableUtil.getLastDataObjectByExpireDate(identityList); if
		 * (identity.getExpireDate().after(identity.getValidDate()) &&
		 * identity.getExpireDate().after(currDate)) {
		 * createUserRouter(identity.getResourceId(),
		 * getAcctIdByUserIdRout(identity.getResourceId()),
		 * identity.getIdentity(),
		 * identity.getRelIdentity()); } }
		 */
	}

	/**
	 * 同步路由db数据到路由mdb
	 * 
	 * @param index
	 * @param imsManualModify
	 * @param context
	 */
	public void syncRouteByManual(int index, ImsManualModify imsManualModify) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		// 先同步三户路由数据
		RouteDimension dim = new RouteDimension();
		if (index == EnumCodeExDefine.SYS_RT_ACCOUNT) {
			Long acctId = imsManualModify.getAcctId();
			if (acctId == null) {
				throw new IMSException("acctId  can't be null when sync zd.sys_rt_account !!");
			}
			dim.setAccountId(acctId);
		} else if (index == EnumCodeExDefine.SYS_RT_RESOURCE) {
			Long userId = imsManualModify.getUserId();
			if (userId == null) {
				throw new IMSException("userId  can't be null when sync zd.sys_rt_resource !!");
			}
			dim.setResourceId(userId);
		} else if (index == EnumCodeExDefine.SYS_RT_CUSTOMER) {
			Long custId = imsManualModify.getCustId();
			if (custId == null) {
				throw new IMSException("custId  can't be null when sync zd.sys_rt_customer !!");
			}
			dim.setCustomerId(custId);
		}
		router.syncMdb(dim);
		if (index == EnumCodeExDefine.SYS_RT_IDENTITY) {
			dim = new RouteDimension();
			Long userId = imsManualModify.getUserId();
			if (userId == null) {
				throw new IMSException("userId  can't be null when sync zd.sys_rt_identity !!");
			}
			List<CmResIdentity> idenList = DBUtil.query(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, userId));
			if (CommonUtil.isNotEmpty(idenList)) {
				for (CmResIdentity iden : idenList) {
					dim.addIdentity(iden.getIdentity(), IdentityType.PHONE);
					dim.addIdentity(iden.getRelIdentity(), IdentityType.IMSI);
				}
			}
			router.syncMdb(dim);
		}
		if (index == EnumCodeExDefine.SYS_RT_INDUSTRY) {
			Long userId = imsManualModify.getUserId();
			if (userId == null) {
				throw new IMSException("userId  can't be null when sync zd.sys_rt_industry !!");
			}
			List<CmUserEnterprise> enpList = DBUtil.query(CmUserEnterprise.class,
					new DBCondition(CmUserEnterprise.Field.resourceId, userId));
			if (CommonUtil.isNotEmpty(enpList)) {
				for (CmUserEnterprise iden : enpList) {
					dim = new RouteDimension();
					dim.setIndustry(iden.getServiceCode(), iden.getOperatorCode());
					router.syncMdb(dim);
				}
			}
		}
	}

	/**
	 * 左去0
	 * 
	 * @param phone
	 * @return
	 */
	public String removeLeftZero(String phone) {

		return StringUtils.ltrim(phone, new char[] { '0' });
	}

	/**
	 * 处理路由的最大失效时间
	 * 
	 * @param expireDate
	 * @return
	 */
	private Date dealRouteExpireDate(Date expireDate) {
		Date defaultExpireDate = new Date(Integer.MAX_VALUE * 1000L);
		if (expireDate == null || expireDate.after(defaultExpireDate)) {
			return defaultExpireDate;
		}
		return expireDate;
	}

	private List<CaAccountRv> getCaAccountRvList(Long groupId, List<Long> userIds, List<String> phoneList,Map<String, List<Date>> userIdValidDateMap,Map<String, List<Date>> phoneIdValidDateMap) {
		List<CaAccountRv> CaAccountRvList=new ArrayList<CaAccountRv>();
		List<CaAccountRv> list = null;
		if (CommonUtil.isNotEmpty(userIds)) {
			if (userIds.size() <= 1000) {
				for(Long userId:userIds){
					String groupIdAndUserId=groupId.toString()+userId.toString();
					List<Date> validDateList=userIdValidDateMap.get(groupIdAndUserId);
						list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
								CaAccountRv.Field.resourceId, userId),
								new DBCondition(CaAccountRv.Field.validDate, validDateList,Operator.IN),
								new DBCondition(CaAccountRv.Field.validDate, CaAccountRv.Field.expireDate,Operator.LESS));

						if(list!=null){
							CaAccountRvList.addAll(list);
						}
					

				}

				
			} else {
				int size = userIds.size();
				int tem = size / ConstantExDefine.SQL_IN_MAX_NUMBER;
				for (int i = 0; i < tem + 1; i++) {
					int start = ConstantExDefine.SQL_IN_MAX_NUMBER * i;
					int end = ConstantExDefine.SQL_IN_MAX_NUMBER * (i + 1);
					if (end > size) {
						end = size;
					}
					List<Long> idList = userIds.subList(start, end);
//					if (list == null) {
//						list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
//								CaAccountRv.Field.resourceId, idList, Operator.IN),
//								//new DBCondition(CaAccountRv.Field.validDate,validDate),
//								new DBCondition(CaAccountRv.Field.validDate, CaAccountRv.Field.expireDate,Operator.LESS));
//					} else {
					for(Long userId:idList){
						String groupIdAndUserId=groupId.toString()+userId.toString();
						List<Date> validDateList=userIdValidDateMap.get(groupIdAndUserId);
							list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId),
									new DBCondition(CaAccountRv.Field.resourceId, userId),
									new DBCondition(CaAccountRv.Field.validDate, validDateList,Operator.IN),
									new DBCondition(CaAccountRv.Field.validDate, CaAccountRv.Field.expireDate,Operator.LESS));

							if(list!=null){
								CaAccountRvList.addAll(list);
							}
							
						

					}
						//list.addAll(temList);
				
						
						
					//}
				}
			}
		}

		if (CommonUtil.isNotEmpty(phoneList)) {
			if (phoneList.size() <= ConstantExDefine.SQL_IN_MAX_NUMBER) {
				for(String phoneId:phoneList){
					String groupIdAndphoneId=groupId.toString()+phoneId;
					List<Date> validDateList=phoneIdValidDateMap.get(groupIdAndphoneId);
						list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
								CaAccountRv.Field.phoneId, phoneId),
								new DBCondition(CaAccountRv.Field.validDate, validDateList,Operator.IN),
								new DBCondition(CaAccountRv.Field.validDate, CaAccountRv.Field.expireDate,Operator.LESS));
					    if(list!=null){
					    	CaAccountRvList.addAll(list);
					    }
					

				}
				
			} else {
				int size = phoneList.size();
				int tem = size / ConstantExDefine.SQL_IN_MAX_NUMBER;
				for (int i = 0; i < tem + 1; i++) {
					int start = ConstantExDefine.SQL_IN_MAX_NUMBER * i;
					int end = ConstantExDefine.SQL_IN_MAX_NUMBER * (i + 1);
					if (end > size) {
						end = size;
					}
					List<String> idList = phoneList.subList(start, end);
//					if (list == null) {
//						for(String phoneId:idList){
//							String groupIdAndphoneId=groupId.toString()+phoneId;
//							Date validDate=phoneIdValidDateMap.get(groupIdAndphoneId);
//							list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
//									CaAccountRv.Field.phoneId, phoneId),
//									new DBCondition(CaAccountRv.Field.validDate, validDate),
//									new DBCondition(CaAccountRv.Field.validDate, CaAccountRv.Field.expireDate,Operator.LESS));
//
//						}
//						
//					} else {
					for(String phoneId:idList){
						String groupIdAndphoneId=groupId.toString()+phoneId;
						List<Date> validDateList=phoneIdValidDateMap.get(groupIdAndphoneId);

							List<CaAccountRv> temList = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId),
									new DBCondition(CaAccountRv.Field.phoneId, idList, Operator.IN),
									new DBCondition(CaAccountRv.Field.validDate, validDateList,Operator.IN),
									new DBCondition(CaAccountRv.Field.validDate, CaAccountRv.Field.expireDate,Operator.LESS));
							;
							CaAccountRvList.addAll(temList);
						

					}
					//}
				}
			}
		}
		return CaAccountRvList;
	}

	public void publicUserUserRelRoute(Map<Long, List<Long>> userRelMap) {
		for (Entry<Long, List<Long>> entry : userRelMap.entrySet()) {
			List<RtPayInfo> rtPayInfoList = new ArrayList<RtPayInfo>();
			ITableUtil.setValue2ContextHolder(null, null, entry.getKey());
			List<CmUserRelation> relaList = DBUtil.query(CmUserRelation.class,
					new DBCondition(CmUserRelation.Field.resourceId, entry.getKey()), new DBCondition(CmUserRelation.Field.rResourceId,
							new ArrayList<Long>(entry.getValue()), Operator.IN));
			if (CommonUtil.isNotEmpty(relaList)) {
				
				List<RtPayInfo> infoList = createRtPayRelByUserRelation(relaList);
				if (CommonUtil.isNotEmpty(infoList)) {
					rtPayInfoList.addAll(infoList);
				}
			}

			if (CommonUtil.isNotEmpty(rtPayInfoList)) {
				imsLogger.dump("^^^split router msg ", rtPayInfoList);
				createSplitRouter(rtPayInfoList);
			}
		}
	}

	private List<RtPayInfo> createRtPayRelByUserRelation(List<CmUserRelation> payRelList) {
		if (CommonUtil.isEmpty(payRelList)) {
			return null;
		}
		Date validDate = null;
		Date expireDate = null;
		CmUserRelation rv = ITableUtil.getFirstDataObjectByValidDate(payRelList);
		if (rv == null) {
			return null;
		}
		validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(payRelList);
		expireDate = rv.getExpireDate();
		Long originalId = payRelList.get(0).getResourceId();
		List<RtPayInfo> payInfoList = new ArrayList<RtPayInfo>();
		for(CmUserRelation payRel : payRelList){
			
			Long oppAttr = payRel.getRResourceId();
			RtPayInfo info = createRtPayRelation(originalId, oppAttr, validDate, expireDate, EnumCodeExDefine.PAY_RELATION_ROUTE_USER_USER);
			payInfoList.add(info);
		}
		
		return payInfoList;
	}

	public void publicPbxRoute(IUserPbx dmPbx) {
		RouterClient router = SpringExUtil.getRouteService();
		RouterPbx pbx = new RouterPbx();
		pbx.setAccessNum(dmPbx.getAccessNumber());
		pbx.setEffectiveDate(this.toMdbValidDate(dmPbx.getValidDate()));
		pbx.setExpireDate(this.toMdbExpireDate(dmPbx.getExpireDate()));
		pbx.setMatchAccessNum(dmPbx.getMatchingAccessNumber().shortValue());
		pbx.setMscId(dmPbx.getMscId());
		pbx.setResourceId(dmPbx.getUserId());
		pbx.setValidDate(dmPbx.getValidDate());

		String inTrunk = dmPbx.getInTrunk();
		if (inTrunk.equals("0000"))
			inTrunk = Integer.toString(Integer.parseInt(inTrunk));
		pbx.setInTrunk(inTrunk);
		String outTrunk = dmPbx.getInTrunk();
		if (outTrunk.equals("0000"))
			outTrunk = Integer.toString(Integer.parseInt(outTrunk));
		pbx.setOutTrunk(outTrunk);

		router.publishPbx(pbx);

	}

	/**
	 * 发布群层级关系路由信息
	 * 
	 * @param acctRelMap
	 */
	public void publishGroupRelations(Map<Long, Set<Long>> acctRelMap) {
		RouterClient router = SpringExUtil.getRouteService();
		List<RtGroupRelation> groupRelationList = new ArrayList<RtGroupRelation>();
		RouterCmp routeCmp = this.getContext().getCmp(RouterCmp.class);
		for (Entry<Long, Set<Long>> entry : acctRelMap.entrySet()) {
			long routeId = routeCmp.queryAcctIdByUserId(entry.getKey());
			ITableUtil.setValue2ContextHolder(null, routeId, null);
			for (Long relGroupId : entry.getValue()) {
				List<CaAccountRel> relList = DBUtil.query(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId, entry.getKey()),
						new DBCondition(CaAccountRel.Field.relGroupId, relGroupId),
						new DBCondition(CaAccountRel.Field.validDate,context.getValidDate()),
						new DBCondition(CaAccountRel.Field.validDate,CaAccountRel.Field.expireDate,Operator.LESS));
				if (CommonUtil.isNotEmpty(relList)) {
					RtGroupRelation relation = createRtGroupRelationRoute(relList);
					if (relation != null) {
						groupRelationList.add(relation);
					}
				}
			}
		}
		router.publishGroupRelations(groupRelationList);
	}

	private RtGroupRelation createRtGroupRelationRoute(List<CaAccountRel> payRelList) {
		if (CommonUtil.isEmpty(payRelList)) {
			return null;
		}
		Long groupId = payRelList.get(0).getRelGroupId(); //子群
		Long parentGroupId = payRelList.get(0).getGroupId();//母群

		Date validDate = null;
		Date expireDate = null;
		CaAccountRel rv = ITableUtil.getFirstDataObjectByValidDate(payRelList);
		if (rv == null) {
			return null;
		}
		validDate = rv.getValidDate();
		rv = ITableUtil.getLastDataObjectByExpireDate(payRelList);
		expireDate = rv.getExpireDate();

		RtGroupRelation info = createRtGroupRelation(groupId, parentGroupId, validDate, expireDate);
		return info;
	}

	private RtGroupRelation createRtGroupRelation(Long groupId, Long parentGroupId, Date validDate, Date expireDate) {
		RtGroupRelation info = new RtGroupRelation();
		info.setExpireDate(expireDate);
		info.setValidDate(validDate);
		info.setExpireDt(this.toMdbExpireDate(expireDate));
		info.setValidDt(this.toMdbValidDate(validDate));
		info.setGroupId(groupId);
		info.setParentGroupId(parentGroupId);
		return info;
	}

	/**
	 * 创建副卡路由
	 * 
	 * @param userId
	 * @param acctId
	 * @param phoneId
	 * @param imsi
	 * @param validDate
	 * @param expireDate
	 */
	public void createUserVCardRouter(Long userId, String phoneId, String imsi, Date validDate, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension mapValue = new RouteDimension();

		//mapValue.setResourceId(userId);
		if (CommonUtil.isNotEmpty(phoneId)) {
			mapValue.addIdentity(phoneId, IdentityType.VCARD);
		}
		if (CommonUtil.isNotEmpty(imsi) && !"0".equals(imsi.trim())) {
			mapValue.addIdentity(imsi, IdentityType.IMSI);
		}
		mapValue.setOperatorId(EnumCodeExDefine.OPERATOR_ID);

		imsLogger.debug("******begin to create account router***", "; userId: ", userId, "; phoneId: ", phoneId, "; imsi :", imsi);

		router.publishDimensions(mapValue, validDate, dealRouteExpireDate(expireDate));
	}

	/**
	 * 删除路由信息，将信息移入到历史表中 目前只支持删除账户与用户、账户与手机号码之间的关系 wukl 2012-12-7
	 */
	public void deleteUserVCardRouter(Long userId, String identity, String imsi, Date expireDate) {
		RouterClient router = SpringExUtil.getRouteSearchService();
		RouteDimension value = new RouteDimension();
		if (CommonUtil.isNotEmpty(identity)) {
			value.addIdentity(identity, RouterIdentity.IdentityType.VCARD);
		}
		if (CommonUtil.isNotEmpty(imsi) && !"0".equals(imsi.trim())) {
			value.addIdentity(imsi, IdentityType.IMSI);
		}
//		if (userId != null) {
//			value.setResourceId(userId);
//		}

		router.removeDimensions(value, dealRouteExpireDate(expireDate));

	}
	
	
	/**
	 * 检查用户路由，如果没有抛出异常
	 * @Date 20161009
	 * @param userId 
	 */
	 public void checkUserRouter(Long userId) {
		if(null == userId){
		    return;
		 } 
		if (!isUserRouted(userId)) {
			throw new RouteNotFoundException(RouteNotFoundException.NOT_EXIST_RESOURCE_ROUTE_DIM,userId);
		  }
	 }
	/**
	 * 检查账户路由，如果没有抛出异常
	 * @Date 20161009
	 * @param userId 
	 */
	 public void checkAcctRouter(Long acctId) {
	    if(null == acctId){
	    	return;
	    } 
		if (!isAcctRouted(acctId)) {
			throw new RouteNotFoundException(RouteNotFoundException.NOT_EXIST_MAIN_ROUTE_DIM,acctId);
		  }
	 }
	 
	/**
	 * 检查客户路由，如果没有抛出异常
	 * @Date 20161009
	 * @param userId 
	 */
	 public void checkCustRouter(Long custId) {
		  if(null == custId){
		    	return;
		   } 
		  if (!isCustRouted(custId)) {
		     	 throw new RouteNotFoundException(RouteNotFoundException.NOT_EXIST_CUSTOMER_ROUTE_DIM,custId);
		  }
	 }
    public void deleteIdentityRouter(Long userId, String identity, String imsi, Date expireDate,Date validDate){
    	List<RouterIdentity> routerIdentityList=DBUtil.query(RouterIdentity.class, new DBCondition(RouterIdentity.Field.identity,identity),
    			new DBCondition(RouterIdentity.Field.resourceId, userId),
    			new DBCondition(RouterIdentity.Field.identityType, 4),
    			new DBCondition(RouterIdentity.Field.effectiveDate, validDate.getTime()/1000),
    			new DBCondition(RouterIdentity.Field.effectiveDate, RouterIdentity.Field.expireDate,Operator.LESS));
    	Date expireDt= dealRouteExpireDate(expireDate);
    	int expireTime = getCurrentTime(expireDt==null?new Date():expireDt);
    	if(routerIdentityList.isEmpty()){
    		for(RouterIdentity ri:routerIdentityList){
    			ri.prepareUpdate(RouterIdentity.Field.expireDate,expireTime);
    			try {
					DBUtil.getDBClient().update(ri);
				} catch (IMSException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
    private int getCurrentTime(Date expireDt)
    {
        return (int) (expireDt.getTime() / 1000);
    }
	public void setRouteMdbDao(RouteMdbDao routeMdbDao) {
		this.routeMdbDao = routeMdbDao;
	}

	public void setRouteDbDao(RouteDbDao routeDbDao) {
		this.routeDbDao = routeDbDao;
	}

	public int queryVersionByUserId(Long userId){
		RouterClient router = SpringExUtil.getRouteSearchService();
		List<RouterResource> list=router.getResourceList(userId);
		RouterResource routerResource=null;
	    for(RouterResource rr:list){
	    	
	    	int version=rr.getVersion();
			int count=0;
	    	for(RouterResource sysRtResourc:list){
	    		if(version>=sysRtResourc.getVersion()){
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
	    	return routerResource.getVersion();
	    }
    	
    }


}