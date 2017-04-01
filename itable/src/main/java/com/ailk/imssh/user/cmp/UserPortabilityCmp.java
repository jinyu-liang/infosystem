package com.ailk.imssh.user.cmp;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.cmp.SalInterfaceCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.handler.UserPortabilityHandler;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.acct.entity.CaDepositProduct;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmAcctPortability;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserMarket;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrderConfirm;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.cust.entity.CmUserPbx;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoProdShareAlloc;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.itable.entity.IUserPortability;

/**
 * 携号跨区资料新增、删除、修改,包括CmUserPortability,CmAcctPortability两个实体 .
 * 
 * @author chenxd
 * @date 2013-12-05
 */
public class UserPortabilityCmp extends BaseCmp {
	/**
	 * 新增携号跨区资料 .
	 * 
	 * @param iUserPortability
	 *            表映射对象实体 .
	 */
	public final void createUserPortability(final IUserPortability iUserPortability) {
		final CmUserPortability cmUserPortability = new CmUserPortability();
		cmUserPortability.setCreateDate(iUserPortability.getCommitDate());
		cmUserPortability.setExpireDate(iUserPortability.getExpireDate());
		cmUserPortability.setOutRegion(iUserPortability.getOutRegion());
		cmUserPortability.setInRegion(iUserPortability.getInRegion());
		cmUserPortability.setInCountyCode(iUserPortability.getInCountyCode());
		cmUserPortability.setRegionCode(iUserPortability.getRegionCode());
		cmUserPortability.setResourceId(iUserPortability.getUserId());
		cmUserPortability.setSoDate(iUserPortability.getCommitDate());
		cmUserPortability.setSoNbr(context.getSoNbr());
		cmUserPortability.setValidDate(iUserPortability.getValidDate());
		RouterCmp router = context.getCmp(RouterCmp.class);

		final Long acctId = router.getAcctIdByUserIdRout(iUserPortability.getUserId());
		final CmAcctPortability cmAcctPortability = new CmAcctPortability();
		cmAcctPortability.setCreateDate(iUserPortability.getCommitDate());
		cmAcctPortability.setExpireDate(iUserPortability.getExpireDate());
		cmAcctPortability.setOutRegion(iUserPortability.getOutRegion());
		cmAcctPortability.setInRegion(iUserPortability.getInRegion());
		cmAcctPortability.setRegionCode(iUserPortability.getRegionCode());
		cmAcctPortability.setAcctId(acctId);
		cmAcctPortability.setSoDate(iUserPortability.getCommitDate());
		cmAcctPortability.setSoNbr(context.getSoNbr());
		cmAcctPortability.setValidDate(iUserPortability.getValidDate());

		super.insert(cmUserPortability);
		super.insert(cmAcctPortability);

	}

	/**
	 * 修改携号跨区资料 .
	 * 
	 * @param iUserPortability
	 *            表映射对象实体 .
	 */
	public final void modifyUserPortability(final IUserPortability iUserPortability) {

		final CmUserPortability cmUserPortability = new CmUserPortability();

		cmUserPortability.setExpireDate(iUserPortability.getExpireDate());
		cmUserPortability.setOutRegion(iUserPortability.getOutRegion());
		cmUserPortability.setInRegion(iUserPortability.getInRegion());
		cmUserPortability.setResourceId(iUserPortability.getUserId());
		cmUserPortability.setSoDate(iUserPortability.getCommitDate());
		cmUserPortability.setSoNbr(context.getSoNbr());
		cmUserPortability.setRegionCode(iUserPortability.getRegionCode());

		RouterCmp router = context.getCmp(RouterCmp.class);
		final Long acctId = router.getAcctIdByUserIdRout(iUserPortability.getUserId());
		final CmAcctPortability cmAcctPortability = new CmAcctPortability();

		cmAcctPortability.setExpireDate(iUserPortability.getExpireDate());
		cmAcctPortability.setOutRegion(iUserPortability.getOutRegion());
		cmAcctPortability.setInRegion(iUserPortability.getInRegion());
		cmAcctPortability.setAcctId(acctId);
		cmAcctPortability.setSoDate(iUserPortability.getCommitDate());
		cmAcctPortability.setSoNbr(context.getSoNbr());
		cmAcctPortability.setRegionCode(iUserPortability.getRegionCode());

		super.updateByCondition(cmUserPortability, new DBCondition(CmUserPortability.Field.resourceId, iUserPortability.getUserId()));

		super.updateByCondition(cmAcctPortability, new DBCondition(CmAcctPortability.Field.acctId, cmAcctPortability.getAcctId()));

	}

	/**
	 * 删除携号跨区资料 ,根据UserId ,acctId.
	 * 
	 * @param iUserPortability
	 *            表映射对象实体
	 */
	public final void deleteUserPortability(final IUserPortability iUserPortability) {
		RouterCmp router = context.getCmp(RouterCmp.class);
		final Long acctId = router.getAcctIdByUserIdRout(iUserPortability.getUserId());
		this.deleteByCondition(CmUserPortability.class, new DBCondition(CmUserPortability.Field.resourceId, iUserPortability.getUserId()));

		this.deleteByCondition(CmAcctPortability.class, new DBCondition(CmAcctPortability.Field.acctId, acctId));

	}

	public void changeRegionDeal() throws Exception {
		Object changeRegion = context.getExtendParam(ConstantExDefine.CHANGE_REGION_IMMEDIATE);
		boolean flag = changeRegion == null ? false : Boolean.valueOf(changeRegion.toString());

		if (flag) {
			IUserPortability protability = (IUserPortability) context.getSingleIDataObjectList(UserPortabilityHandler.class);
			// 地市相同,则只改county_code
			if (protability.getInRegion().equals(protability.getOutRegion())) {
				transfercountyCode(protability.getUserId(), protability.getOutRegion(), protability.getInRegion(),
						protability.getInCountyCode(), context.getCommitDate(), context);
			} else {
				SalInterfaceCmp cmp = context.getCmp(SalInterfaceCmp.class);
				Long acctId = context.getCmp(RouterCmp.class).getAcctIdByUserIdRout(protability.getUserId());
				if (protability.getOperType() == EnumCodeExDefine.OPER_TYPE_DELETE) {
					transfer(protability.getUserId(), protability.getInRegion(), protability.getOutRegion(), protability.getInCountyCode(),
							context.getCommitDate(), true, context);
					cmp.transferAps(protability.getUserId(), acctId, acctId, protability.getInRegion(), protability.getOutRegion(),
							EnumCodeExDefine.APS_TRANSFER_TYPE_IMME);
				} else {
					transfer(protability.getUserId(), protability.getOutRegion(), protability.getInRegion(), protability.getInCountyCode(),
							context.getCommitDate(), true, context);
					cmp.transferAps(protability.getUserId(), acctId, acctId, protability.getOutRegion(), protability.getInRegion(),
							EnumCodeExDefine.APS_TRANSFER_TYPE_IMME);
				}
			}

		}
	}

	public void transfer(Long userId, Integer oldRegion, Integer newRegion, Integer countyCode, Date expireDate, boolean flag,
			ITableContext context) throws Exception {
		// 单个用户编号不存在两条记录，携号跨区撤单的时候会置为失效
		// 1,先判断要处理的用户编号是否已经处理，查历史表
		RouterCmp routeCmp = context.getCmp(RouterCmp.class);
		// Integer oldRegion = protability.getOutRegion();
		// Integer newRegion = protability.getInRegion();
		// Long userId = protability.getResourceId();
		// Integer countyCode = protability.getInCountyCode();
		RouteResult routeRes = routeCmp.queryUserRouter(userId);
		if (routeRes == null) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
		}
		Long acctId = routeRes.getRouteDimension().getAccountId();

		ITableUtil.setValue2ContextHolder(null, acctId, userId);
		// 获取结束时间
		// Date expireDate = protability.getValidDate();

		List<CmResource> backUpCmResource = (List<CmResource>) DBUtil.updateByConditionWithNoUpReturn(CmResource.class, expireDate,
				new DBCondition(CmResource.Field.resourceId, userId), new DBCondition(CmResource.Field.expireDate, expireDate,
						Operator.GREAT));
		List<CmUserParam> backUpCmUserParam = (List<CmUserParam>) DBUtil.updateByConditionWithNoUpReturn(CmUserParam.class, expireDate,
				new DBCondition(CmUserParam.Field.resourceId, userId), new DBCondition(CmUserParam.Field.expireDate, expireDate,
						Operator.GREAT));
		List<CmResLifecycle> backUpCmResLifecycle = (List<CmResLifecycle>) DBUtil.updateByConditionWithNoUpReturn(CmResLifecycle.class,
				expireDate, new DBCondition(CmResLifecycle.Field.resourceId, userId), new DBCondition(CmResLifecycle.Field.expireDate,
						expireDate, Operator.GREAT));
		List<CmResIdentity> backUpCmResIdentity = (List<CmResIdentity>) DBUtil.updateByConditionWithNoUpReturn(CmResIdentity.class,
				expireDate, new DBCondition(CmResIdentity.Field.resourceId, userId), new DBCondition(CmResIdentity.Field.expireDate,
						expireDate, Operator.GREAT));
		List<CoProd> backUpCoProd = (List<CoProd>) DBUtil.updateByConditionWithNoUpReturn(CoProd.class, expireDate, new DBCondition(
				CoProd.Field.objectId, userId), new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
				new DBCondition(CoProd.Field.expireDate, expireDate, Operator.GREAT));
		List<CoProd> backUpAcctCoProd = (List<CoProd>) DBUtil.updateByConditionWithNoUpReturn(CoProd.class, expireDate, new DBCondition(
				CoProd.Field.objectId, acctId), new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
				new DBCondition(CoProd.Field.expireDate, expireDate, Operator.GREAT));
		// 合并用户级和账户级产品
		if (CommonUtil.isNotEmpty(backUpCoProd)) {
			if (CommonUtil.isNotEmpty(backUpAcctCoProd)) {
				backUpCoProd.addAll(backUpAcctCoProd);
			}
		} else {
			if (CommonUtil.isNotEmpty(backUpAcctCoProd)) {
				backUpCoProd = backUpAcctCoProd;
			}
		}

		// 以下三张表没有object_id的索引，所以改为根据prod_id去查
		List<CoProdMapping> backUpCoProdMapping = null;
		List<CoProdCharValue> backUpCoProdCharValue = null;
		List<CoProdPriceParam> backUpCoProdPriceParam = null;
		List<CoFnCharValue> backUpFnValue = null;
		List<CoSplitCharValue> backUpSplitValue = null;
		List<CoBudgetCharValue> backUpBudgetValue = null;
		if (CommonUtil.isNotEmpty(backUpCoProd)) {
			Set<Long> prodIds = new HashSet<Long>();
			for (CoProd prod : backUpCoProd) {
				prodIds.add(prod.getProductId());
			}

			backUpCoProdMapping = (List<CoProdMapping>) DBUtil.updateByConditionWithNoUpReturn(CoProdMapping.class, expireDate,
					new DBCondition(CoProdMapping.Field.objectId, userId), new DBCondition(CoProdMapping.Field.expireDate, expireDate,
							Operator.GREAT));
			backUpCoProdCharValue = (List<CoProdCharValue>) DBUtil.updateByConditionWithNoUpReturn(CoProdCharValue.class, expireDate,
					new DBCondition(CoProdCharValue.Field.productId, prodIds, Operator.IN), new DBCondition(
							CoProdCharValue.Field.expireDate, expireDate, Operator.GREAT));
			backUpCoProdPriceParam = (List<CoProdPriceParam>) DBUtil.updateByConditionWithNoUpReturn(CoProdPriceParam.class, expireDate,
					new DBCondition(CoProdPriceParam.Field.productId, prodIds, Operator.IN), new DBCondition(
							CoProdPriceParam.Field.expireDate, expireDate, Operator.GREAT));
			backUpFnValue = (List<CoFnCharValue>) DBUtil.updateByConditionWithNoUpReturn(CoFnCharValue.class, expireDate, new DBCondition(
					CoFnCharValue.Field.productId, prodIds, Operator.IN), new DBCondition(CoFnCharValue.Field.expireDate, expireDate,
					Operator.GREAT));
			backUpSplitValue = (List<CoSplitCharValue>) DBUtil.updateByConditionWithNoUpReturn(CoSplitCharValue.class, expireDate,
					new DBCondition(CoSplitCharValue.Field.productId, prodIds, Operator.IN), new DBCondition(
							CoSplitCharValue.Field.expireDate, expireDate, Operator.GREAT));
			backUpBudgetValue = (List<CoBudgetCharValue>) DBUtil.updateByConditionWithNoUpReturn(CoBudgetCharValue.class, expireDate,
					new DBCondition(CoBudgetCharValue.Field.productId, prodIds, Operator.IN), new DBCondition(
							CoBudgetCharValue.Field.expireDate, expireDate, Operator.GREAT));

		}
		// 新增的CM表也需要迁移
		List<CmUserMap> backUpCmUserMap = (List<CmUserMap>) DBUtil.updateByConditionWithNoUpReturn(CmUserMap.class, expireDate,
				new DBCondition(CmUserMap.Field.resourceId, userId),
				new DBCondition(CmUserMap.Field.expireDate, expireDate, Operator.GREAT));
		List<CmUserOrder> backUpCmUserOrder = (List<CmUserOrder>) DBUtil.updateByConditionWithNoUpReturn(CmUserOrder.class, expireDate,
				new DBCondition(CmUserOrder.Field.resourceId, userId), new DBCondition(CmUserOrder.Field.expireDate, expireDate,
						Operator.GREAT));
		List<CmUserPbx> backUpCmUserPbx = (List<CmUserPbx>) DBUtil.updateByConditionWithNoUpReturn(CmUserPbx.class, expireDate,
				new DBCondition(CmUserPbx.Field.resourceId, userId),
				new DBCondition(CmUserPbx.Field.expireDate, expireDate, Operator.GREAT));
		List<CmUserMarket> backUpCmUserMarket = (List<CmUserMarket>) DBUtil.updateByConditionWithNoUpReturn(CmUserMarket.class, expireDate,
				new DBCondition(CmUserMarket.Field.resourceId, userId), new DBCondition(CmUserMarket.Field.expireDate, expireDate,
						Operator.GREAT));

		List<CmUserShareProd> backUpCmUserShareProd = (List<CmUserShareProd>) DBUtil.updateByConditionWithNoUpReturn(CmUserShareProd.class,
				expireDate, new DBCondition(CmUserShareProd.Field.resourceId, userId), new DBCondition(CmUserShareProd.Field.expireDate,
						expireDate, Operator.GREAT));

		List<CmResourceMonitor> backUpCmResourceMonitor = (List<CmResourceMonitor>) DBUtil.updateByConditionWithNoUpReturn(
				CmResourceMonitor.class, expireDate, new DBCondition(CmResourceMonitor.Field.resourceId, userId), new DBCondition(
						CmResourceMonitor.Field.expireDate, expireDate, Operator.GREAT));
		List<CmUserEnterprise> backUpCmUserEnterprise = (List<CmUserEnterprise>) DBUtil.updateByConditionWithNoUpReturn(
				CmUserEnterprise.class, expireDate, new DBCondition(CmUserEnterprise.Field.resourceId, userId), new DBCondition(
						CmUserEnterprise.Field.expireDate, expireDate, Operator.GREAT));
		List<CmUserOrderConfirm> backUpCmUserOrderConfirm = DBUtil.query(CmUserOrderConfirm.class, new DBCondition(
				CmUserOrderConfirm.Field.resourceId, userId), new DBCondition(CmUserOrderConfirm.Field.expireDate, expireDate,
				Operator.GREAT));

		// 群反向表
		List<CaResGrpRevs> backUpGrpRes = (List<CaResGrpRevs>) DBUtil.updateByConditionWithNoUpReturn(CaResGrpRevs.class, expireDate,
				new DBCondition(CaResGrpRevs.Field.resourceId, userId), new DBCondition(CaResGrpRevs.Field.expireDate, expireDate,
						Operator.GREAT));
		List<CoSplitPayRel> backUpSplitRel = (List<CoSplitPayRel>) DBUtil.updateByConditionWithNoUpReturn(CoSplitPayRel.class, expireDate,
				new DBCondition(CoSplitPayRel.Field.objectId, userId), new DBCondition(CoSplitPayRel.Field.expireDate, expireDate,
						Operator.GREAT));
		// ------------上面是用户相关表---------------------------------------------------------------//
		List<CaAccount> backUpCaAccount = (List<CaAccount>) DBUtil.updateByConditionWithNoUpReturn(CaAccount.class, expireDate,
				new DBCondition(CaAccount.Field.acctId, acctId), new DBCondition(CaAccount.Field.expireDate, expireDate, Operator.GREAT));
		List<CaBillingPlan> backUpCaBillingPlan = (List<CaBillingPlan>) DBUtil.updateByConditionWithNoUpReturn(CaBillingPlan.class,
				expireDate, new DBCondition(CaBillingPlan.Field.acctId, acctId), new DBCondition(CaBillingPlan.Field.expireDate,
						expireDate, Operator.GREAT));
		List<CaNotifyExempt> backUpAcctCaNotifyExempt = (List<CaNotifyExempt>) DBUtil.updateByConditionWithNoUpReturn(CaNotifyExempt.class,
				expireDate, new DBCondition(CaNotifyExempt.Field.objectId, acctId), new DBCondition(CoProd.Field.objectType,
						EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new DBCondition(CaNotifyExempt.Field.expireDate, expireDate,
						Operator.GREAT));
		List<CaNotifyExempt> backUpUserCaNotifyExempt = (List<CaNotifyExempt>) DBUtil.updateByConditionWithNoUpReturn(CaNotifyExempt.class,
				expireDate, new DBCondition(CaNotifyExempt.Field.objectId, userId), new DBCondition(CoProd.Field.objectType,
						EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CaNotifyExempt.Field.expireDate, expireDate, Operator.GREAT));
		List<CaBankFund> backUpCaBankFund = (List<CaBankFund>) DBUtil.updateByConditionWithNoUpReturn(CaBankFund.class, expireDate,
				new DBCondition(CaBankFund.Field.acctId, acctId), new DBCondition(CaBankFund.Field.expireDate, expireDate, Operator.GREAT));
		List<CaPayChannel> backUpCaPayChannel = (List<CaPayChannel>) DBUtil.updateByConditionWithNoUpReturn(CaPayChannel.class, expireDate,
				new DBCondition(CaPayChannel.Field.acctId, acctId), new DBCondition(CaPayChannel.Field.expireDate, expireDate,
						Operator.GREAT));
		List<CaAccountRes> backUpCaAccountRes = (List<CaAccountRes>) DBUtil.updateByConditionWithNoUpReturn(CaAccountRes.class, expireDate,
				new DBCondition(CaAccountRes.Field.acctId, acctId), new DBCondition(CaAccountRes.Field.expireDate, expireDate,
						Operator.GREAT));
		// 20151002 信用度直接全部迁移，不截断 不留记录
		List<CaCredit> backUpCaCredit = (List<CaCredit>) DBUtil.deleteByConditionWithReturn(CaCredit.class, new DBCondition(
				CaCredit.Field.acctId, acctId), new DBCondition(CaCredit.Field.expireDate, expireDate, Operator.GREAT));
		List<CaCycleRun> backUpCycleRun = null;
		// 携号跨区立即生效，要迁移CA_CYCLE_RUN
		if (flag) {
			backUpCycleRun = (List<CaCycleRun>) DBUtil.deleteByConditionWithReturn(CaCycleRun.class, new DBCondition(
					CaCycleRun.Field.acctId, acctId));
		}
		List<CmContactMedium> backUpCmContactMedium = (List<CmContactMedium>) DBUtil.updateByConditionWithNoUpReturn(CmContactMedium.class,
				expireDate, new DBCondition(CmContactMedium.Field.objectId, acctId), new DBCondition(CmContactMedium.Field.expireDate,
						expireDate, Operator.GREAT));
		// 账户级共享免费资源限额
		List<CoProdShareAlloc> backUpCoProdShareAlloc = (List<CoProdShareAlloc>) DBUtil.updateByConditionWithNoUpReturn(
				CoProdShareAlloc.class, expireDate, new DBCondition(CoProdShareAlloc.Field.objectId, acctId), new DBCondition(
						CoProdShareAlloc.Field.expireDate, expireDate, Operator.GREAT));

		List<CoProdShareAlloc> backUpGroupCoProdShareAlloc = (List<CoProdShareAlloc>) DBUtil.updateByConditionWithNoUpReturn(
				CoProdShareAlloc.class, expireDate, new DBCondition(CoProdShareAlloc.Field.objectId, userId), new DBCondition(
						CoProdShareAlloc.Field.expireDate, expireDate, Operator.GREAT));

		List<CaAccountGroup> backUpCaAccountGroup = (List<CaAccountGroup>) DBUtil.updateByConditionWithNoUpReturn(CaAccountGroup.class,
				expireDate, new DBCondition(CaAccountGroup.Field.acctId, userId), new DBCondition(CaAccountGroup.Field.expireDate,
						expireDate, Operator.GREAT));

		List<CaAccountRv> backUpCaAccountRv = (List<CaAccountRv>) DBUtil.updateByConditionWithNoUpReturn(CaAccountRv.class, expireDate,
				new DBCondition(CaAccountRv.Field.acctId, userId),
				new DBCondition(CaAccountRv.Field.expireDate, expireDate, Operator.GREAT));

		List<CaDepositProduct> backUpCaDepositProduct = (List<CaDepositProduct>) DBUtil.updateByConditionWithNoUpReturn(
				CaDepositProduct.class, expireDate, new DBCondition(CaDepositProduct.Field.servId, userId), new DBCondition(
						CaDepositProduct.Field.expireDate, expireDate, Operator.GREAT));

		Long custId = null;
		if (CommonUtil.isNotEmpty(backUpCaAccount)) {
			custId = backUpCaAccount.get(0).getCustId();
		}

		imsLogger.debug("******* 重新发布账户路由，更新region_code ,new_region_code = ", newRegion);
		routeCmp.createAcctRouter(acctId, custId, newRegion, expireDate, null);
		imsLogger.debug("******* 将备份的数据插入到新的region_code对应的路由分表中");
		ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_REGION_CODE, newRegion);
		try {
			routeCmp.insertBatchByTransferRegionCode(backUpCmResIdentity, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmResLifecycle, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCoProd, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCoProdPriceParam, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCoProdCharValue, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpFnValue, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpSplitValue, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpBudgetValue, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpGrpRes, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpSplitRel, expireDate, newRegion, countyCode);

			routeCmp.insertBatchByTransferRegionCode(backUpCaDepositProduct, expireDate, newRegion, countyCode);
			// routeCmp.insertBatchByTransferRegionCode(backUpCmUserOrderConfirm,expireDate,
			// newRegion);
			routeCmp.insertBatchByTransferRegionCode(backUpCoProdMapping, expireDate, newRegion, countyCode);

			// 账户相关表
			routeCmp.insertBatchByTransferRegionCode(backUpCaAccount, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCaBillingPlan, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpAcctCaNotifyExempt, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpUserCaNotifyExempt, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCaBankFund, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCaPayChannel, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCaAccountRes, expireDate, newRegion, countyCode);
			// 20151002 信用度特殊处理 不修改生效时间
			DBUtil.insertBatch(backUpCaCredit);
			DBUtil.insertBatch(backUpCycleRun);
			routeCmp.insertBatchByTransferRegionCode(backUpCmContactMedium, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCoProdShareAlloc, expireDate, newRegion, countyCode);

			routeCmp.insertBatchByTransferRegionCode(backUpCaAccountGroup, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCaAccountRv, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpGroupCoProdShareAlloc, expireDate, newRegion, countyCode);

			// 20151002 分表规则中是从GROUP_ID获取region_code
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, newRegion);
			routeCmp.insertBatchByTransferRegionCode(backUpCmResource, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmResourceMonitor, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserEnterprise, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserMap, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserOrder, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserPbx, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserMarket, expireDate, newRegion, countyCode);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserParam, expireDate, newRegion, countyCode);

			// 二次扣费确认特殊处理
			if (CommonUtil.isNotEmpty(backUpCmUserOrderConfirm)) {
				for (CmUserOrderConfirm confirm : backUpCmUserOrderConfirm) {
					confirm.setRegionCode(newRegion);
				}
				routeCmp.insertBatch(backUpCmUserOrderConfirm);
			}
			ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_GROUP_ID);
			// 20151002 分表规则中是从USER_ID获取region_code
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_USER_ID, newRegion);
			routeCmp.insertBatchByTransferRegionCode(backUpCmUserShareProd, expireDate, newRegion, countyCode);
			ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_USER_ID);
		} catch (Exception e) {
			imsLogger.error("插入新表出现异常：", e);
			imsLogger.debug("*******插入新表出现异常， 重新发布账户路由，更新region_code ,old_region_code = ", oldRegion);
			routeCmp.createAcctRouter(acctId, custId, oldRegion, expireDate, null);
			IMSUtil.throwBusiException(e);
		}
		// 携号跨区调用abm资产迁移接口
		context.getCmp(UserAcctRelCmp.class).transferMdbAssetByChangeRegionCode(userId, acctId, newRegion, oldRegion);
	}

	public void transfercountyCode(Long userId, Integer oldRegion, Integer newRegion, Integer countyCode, Date expireDate,
			ITableContext context) throws Exception {
		RouterCmp routeCmp = context.getCmp(RouterCmp.class);
		RouteResult routeRes = routeCmp.queryUserRouter(userId);
		if (routeRes == null) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, userId);
		}
		Long acctId = routeRes.getRouteDimension().getAccountId();
		ITableUtil.setValue2ContextHolder(null, acctId, userId);
		try {

			List<CmResource> backUpCmResource = (List<CmResource>) DBUtil.updateByConditionWithNoUpReturn(CmResource.class, expireDate,
					new DBCondition(CmResource.Field.resourceId, userId), new DBCondition(CmResource.Field.expireDate, expireDate,
							Operator.GREAT));

			List<CaAccount> backUpCaAccount = (List<CaAccount>) DBUtil.updateByConditionWithNoUpReturn(CaAccount.class, expireDate,
					new DBCondition(CaAccount.Field.acctId, acctId),
					new DBCondition(CaAccount.Field.expireDate, expireDate, Operator.GREAT));
			if (CommonUtil.isNotEmpty(backUpCmResource)) {
				routeCmp.insertBatchByTransferRegionCode(backUpCmResource, expireDate, newRegion, countyCode);
			}
			if (CommonUtil.isNotEmpty(backUpCaAccount)) {
				routeCmp.insertBatchByTransferRegionCode(backUpCaAccount, expireDate, newRegion, countyCode);
			}

		} catch (Exception e) {
			imsLogger.error("出现异常：", e);
			IMSUtil.throwBusiException(e);
		}

	}
}
