package com.ailk.imssh.common.manual.handler;

import java.util.Date;

import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.user.cmp.UserPortabilityCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;

public class CustTransferCountyHandler implements ITransferHandler {


	public void transfer(CmUserPortability protability, ITableContext context) throws Exception {
		Integer countyCode = protability.getInCountyCode();
		Integer newRegion = protability.getInRegion();
		Long userId = protability.getResourceId();
		Date expireDate = protability.getValidDate();
		context.getCmp(UserPortabilityCmp.class).transfercountyCode(userId, protability.getOutRegion(), newRegion, countyCode, expireDate, context);
		
		/*
		RouterCmp routeCmp = context.getCmp(RouterCmp.class);
		RouteResult routeRes = routeCmp.queryUserRouter(userId);
		if (routeRes == null) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
		}
		Long acctId = routeRes.getRouteDimension().getAccountId();
		ITableUtil.setValue2ContextHolder(null, acctId, userId);
		// 获取结束时间
		try {

			List<CmResource> backUpCmResource = (List<CmResource>) DBUtil.updateByConditionWithNoUpReturn(CmResource.class, expireDate,
					new DBCondition(CmResource.Field.resourceId, userId), new DBCondition(CmResource.Field.expireDate, expireDate,
							Operator.GREAT));

			List<CaAccount> backUpCaAccount = (List<CaAccount>) DBUtil.updateByConditionWithNoUpReturn(CaAccount.class, expireDate,
					new DBCondition(CaAccount.Field.acctId, acctId),
					new DBCondition(CaAccount.Field.expireDate, expireDate, Operator.GREAT));
			if(CommonUtil.isNotEmpty(backUpCmResource)){
				routeCmp.insertBatchByTransferRegionCode(backUpCmResource, expireDate, newRegion,countyCode);
			}
			if(CommonUtil.isNotEmpty(backUpCaAccount)){
				routeCmp.insertBatchByTransferRegionCode(backUpCaAccount, expireDate, newRegion,countyCode);
			}


		} catch (Exception e) {
			imsLogger.error("出现异常：", e);
			IMSUtil.throwBusiException(e);
		}
		*/
	}

}
