package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jef.database.AbstractDbClient;
import jef.database.Condition.Operator;
import jef.database.annotation.PartitionResult;

import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmAssetTransfer;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmAssetTransferRet;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.STransParam;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.mdb.ItableSalMdbBean;
import com.ailk.imssh.common.mdb.UserInfoComplex;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;
import com.ailk.imssh.user.helper.UserAcctRelHelper;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.acct.entity.CaDepositProduct;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.cust.entity.CmCustVip;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserImpu;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserMarket;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrderConfirm;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.cust.entity.CmUserPbx;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareRel;
import com.ailk.openbilling.persistence.cust.entity.CmUserValidchange;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdMapping;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.itable.entity.IUserAcctRel;

/**
 * @Description 用户账户关系表同步
 * @author wukl
 * @Date 2012-5-17
 * @Date 2012-06-29
 *       分、合户，过户时CRM会传两条记录过来，此时同一个so_nbr会找到新增、删除两条记录，真正处理的顺序应该是先执行删除，后执行新增；<BR>
 *       但是对于检索到的数据无法控制先后顺序，故只能在修改、删除时增加老账户作为条件
 * @Date 2012-08-11 wukl 增加路由变更的数据迁移
 */
public class UserAcctRelCmp extends UserQuery {

	/**
	 * 创建用户账户关系<br>
	 * wukl 2012-5-17
	 * 
	 * @param iUserAcctRel
	 */
	public void createUserAcctRel(IUserAcctRel iUserAcctRel) {
		// 增加新的用户账户关系记录
		
		RouterCmp routeCmp = context.getCmp(RouterCmp.class);
		if (UserAcctRelHelper.needCreateRoute(context,iUserAcctRel.getUserId())) {
			//要发布路由
			
			Date validDate = iUserAcctRel.getValidDate();
			if(routeCmp.isUserRouted(iUserAcctRel.getUserId())){
				routeCmp.deleteRouter(iUserAcctRel.getUserId(),null, null, context.getRequestDate());
				validDate = context.getRequestDate();
			}
			
			routeCmp.createUserRouter(iUserAcctRel.getUserId(), iUserAcctRel.getAcctId(), null,null,
					validDate, iUserAcctRel.getExpireDate());
		}
		ITableUtil.setValue2ContextHolder(iUserAcctRel.getAcctId(), null, null);
		insertCaAccountRes(iUserAcctRel);
	}

	/**
	 * 修改用户账户关系 <br>
	 * wukl 2012-5-17
	 * 
	 * @param iUserAcctRel
	 */
	@SuppressWarnings("unchecked")
	public void modifyUserAcctRel(IUserAcctRel iUserAcctRel) {
		// 如果新老账户不一致，就表示用户发生了过户;
		if (iUserAcctRel.getOldAcctId().intValue() != iUserAcctRel.getAcctId().intValue()) {
			// 发生了过户
			String paramKey = iUserAcctRel.getAcctId()+ConstantExDefine.USER_ACCT_SPLIT+iUserAcctRel.getOldAcctId();
			Map<String,Object> paramMap = (Map<String, Object>) context.getExtendParam(paramKey);
			if(paramMap == null){
				paramMap = new HashMap<String,Object>();
				paramMap.put(ConstantExDefine.ROUTER_MOVE_USERID, iUserAcctRel.getUserId());
				paramMap.put("CHANGE_ACCT_DATE", iUserAcctRel.getValidDate());
				paramMap.put("CHANGE_ACCT_EXPIRE_DATE", iUserAcctRel.getExpireDate());
			}
			this.changeRoute(iUserAcctRel,paramMap);
		}else{
			RouterCmp routeCmp = context.getCmp(RouterCmp.class);
			routeCmp.checkUserRouter(iUserAcctRel.getUserId());
		}
		
		// 进行修改操作
		this.updateCaAccountRes(iUserAcctRel);
	}

	/**
	 * 删除用户账户关系<br>
	 * wukl 2012-5-17
	 * 
	 * @param iUserAcctRel
	 */
	public void deleteUserAcctRel(IUserAcctRel iUserAcctRel) {
		
		if(UserAcctRelHelper.needCreateRoute(context, iUserAcctRel.getUserId())){
			RouterCmp routeCmp = context.getCmp(RouterCmp.class);
			Date expireDate = getNextMonthBegin(iUserAcctRel.getExpireDate());
			ITableUtil.setValue2ContextHolder(iUserAcctRel.getAcctId(), null, null);
			this.deleteCaAccountRes(iUserAcctRel,expireDate);
			//删除路由
			routeCmp.deleteRouter(iUserAcctRel.getUserId(),null,null,expireDate);
		}else{
			ITableUtil.setValue2ContextHolder(iUserAcctRel.getAcctId(), null, null);
			CaAccountRes value = new CaAccountRes();
//		value.setChgFlag(EnumCodeExDefine.CHG_FLAG_OLD);
			// value.setExpireDate(context.getCommitDate());
//			DBUtil.updateByCondition(value, new DBCondition(CaAccountRes.Field.resourceId, iUserAcctRel.getUserId()), new DBCondition(
//					CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()),
//					new DBCondition(CaAccountRes.Field.expireDate, context.getCommitDate(), Operator.GREAT),
//					new DBCondition(CaAccountRes.Field.validDate, iUserAcctRel.getValidDate()),
//					new DBCondition(CaAccountRes.Field.validDate, CaAccountRes.Field.expireDate,Operator.LESS_EQUALS));

			value.setExpireDate(context.getCommitDate());
			
//			this.updateMode2(value, EnumCodeExDefine.OPER_TYPE_DELETE,iUserAcctRel.getValidDate(),
//					new DBCondition(CaAccountRes.Field.resourceId, iUserAcctRel.getUserId()),
//					new DBCondition(CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()));
			this.deleteByCondition(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, iUserAcctRel.getUserId()),
					new DBCondition(CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()));
		}
		

	}

	/**
	 * 删除用户账户关系表数据<br>
	 * wukl 2012-5-17
	 * 
	 * @param iUserAcctRel
	 */
	private void deleteCaAccountRes(IUserAcctRel iUserAcctRel,Date expireDate) {
		// 使用noInsert进行操作
		// Date date =
		// iUserAcctRel.getExpireDate().before(context.getCommitDate()) ?
		// iUserAcctRel.getExpireDate() : context
		// .getCommitDate();
		this.deleteByCondition_noInsert(CaAccountRes.class, expireDate, new DBCondition(CaAccountRes.Field.resourceId,
				iUserAcctRel.getUserId()), new DBCondition(CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()),
				new DBCondition(CaAccountRes.Field.validDate, CaAccountRes.Field.expireDate,Operator.LESS_EQUALS));
		
		DBUtil.deleteByCondition(CaAccountRes.class,new DBCondition(CaAccountRes.Field.resourceId, iUserAcctRel.getUserId()),
				new DBCondition(CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()),
				new DBCondition(CaAccountRes.Field.validDate, CaAccountRes.Field.expireDate,Operator.GREAT));
	}

	/**
	 * 修改前先调用DBUtil的方法,改掉记录的chg_flag
	 * 
	 * @param iUserAcctRel
	 */
	// private void updateCaAccountResNoInsert(IUserAcctRel iUserAcctRel)
	// {
	// CaAccountRes value = new CaAccountRes();
	// value.setChgFlag(EnumCodeExDefine.CHG_FLAG_OLD);
	//
	// DBUtil.updateByCondition(value, new
	// DBCondition(CaAccountRes.Field.resourceId, iUserAcctRel.getUserId()),
	// new DBCondition(CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()));
	// }

	/**
	 * 修改用户账户关系表数据<br>
	 * wukl 2012-5-17
	 * 
	 * @param iUserAcctRel
	 */
	private void updateCaAccountRes(IUserAcctRel iUserAcctRel) {
		CaAccountRes caAccountRes = new CaAccountRes();
		caAccountRes.setCreateDate(context.getCommitDate());
		caAccountRes.setSoDate(context.getCommitDate());
		caAccountRes.setResAcctId(iUserAcctRel.getAcctId());
		caAccountRes.setRelationshipType(iUserAcctRel.getRelationshipType());
		caAccountRes.setTitleRoleId(iUserAcctRel.getTitleRoleId());
		caAccountRes.setSoNbr(context.getSoNbr());
		caAccountRes.setExpireDate(iUserAcctRel.getExpireDate());
		if (!UserAcctRelHelper.isChangeOwner(context)) {
			caAccountRes.setChgFlag(EnumCodeExDefine.CHG_FLAG_NORMAL);
		} else {
			// 过户后，新账户的chg_flag置为10
			caAccountRes.setChgFlag(EnumCodeExDefine.CHG_FLAG_NEW);
		}
		this.updateMode2(caAccountRes,EnumCodeExDefine.OPER_TYPE_UPDATE,iUserAcctRel.getValidDate(),
				new DBCondition(CaAccountRes.Field.resourceId, iUserAcctRel.getUserId()),
				new DBCondition(CaAccountRes.Field.acctId, iUserAcctRel.getOldAcctId()));
		
	}

	/**
	 * 增加用户账户关系表数据<br>
	 * wukl 2012-5-17
	 * 
	 * @param iUserAcctRel
	 */
	private void insertCaAccountRes(IUserAcctRel iUserAcctRel) {
		CaAccountRes caAccountRes = new CaAccountRes();
		caAccountRes.setCreateDate(context.getCommitDate());
		caAccountRes.setSoDate(context.getCommitDate());
		caAccountRes.setAcctId(iUserAcctRel.getAcctId());
		caAccountRes.setResAcctId(iUserAcctRel.getAcctId());
		caAccountRes.setResourceId(iUserAcctRel.getUserId());
		caAccountRes.setRelationshipType(iUserAcctRel.getRelationshipType());
		caAccountRes.setTitleRoleId(iUserAcctRel.getTitleRoleId());
		caAccountRes.setSoNbr(context.getSoNbr());
		if (UserAcctRelHelper.isChangeOwner(context)) {
			caAccountRes.setValidDate(context.getCommitDate());
		} else {
			caAccountRes.setValidDate(iUserAcctRel.getValidDate());
		}
		caAccountRes.setExpireDate(iUserAcctRel.getExpireDate());
		if (!UserAcctRelHelper.isChangeOwner(context)) {
			caAccountRes.setChgFlag(EnumCodeExDefine.CHG_FLAG_NORMAL);
		} else {
			// 过户后，新账户的chg_flag置为10
			caAccountRes.setChgFlag(EnumCodeExDefine.CHG_FLAG_NEW);
		}
		super.insert(caAccountRes);
	}

	/**
	 * 更换路由，分库分表变化时迁移数据 wukl 2012-8-12
	 * 
	 * @param iUserAcctRel
	 */
	private void changeRoute(IUserAcctRel iUserAcctRel,Map<String,Object> paramMap) {
		boolean chgFlag = isRouteChange(iUserAcctRel);
		if (chgFlag) {// 分库、分表发生变化，需要迁移数据
			imsLogger.debug("****** 路由分库分表发生了变化");
			paramMap.put(ConstantExDefine.ROUTER_MOVE_DATA_FLAG, true);
		}
	}

	/**
	 * 判断路由分库、分表是否有变更 wukl 2012-8-12
	 * 
	 * @param iUserAcctRel
	 * @return 需要更新路由返回true,否返回false
	 */
	private boolean isRouteChange(IUserAcctRel iUserAcctRel) {
		CaAccount oldAcct = context.get3hTree().loadAcct3hBean(iUserAcctRel.getOldAcctId()).getAccount();
		CaAccount newAcct = context.get3hTree().loadAcct3hBean(iUserAcctRel.getAcctId()).getAccount();

		AbstractDbClient dbClient = DBUtil.getDBClient();
		// wangjt: 改为： getPartitionResults
		PartitionResult[] oldPartResults = dbClient.getPartitionResults(oldAcct);
		PartitionResult[] newPartResults = dbClient.getPartitionResults(newAcct);

		if (CommonUtil.isNotEmpty(oldPartResults) && CommonUtil.isNotEmpty(newPartResults)) {
			String oldAcctDb = oldPartResults[0].getDatabase();
			String oldAcctTb = oldPartResults[0].getAsOneTable();
			String newAcctDb = newPartResults[0].getDatabase();
			String newAcctTb = newPartResults[0].getAsOneTable();
			if (oldAcctDb == null && newAcctDb == null || oldAcctDb != null && newAcctDb != null && oldAcctDb.equals(newAcctDb)) {// 同库
				if (oldAcctTb != null && oldAcctTb.equals(newAcctTb)) {// 同表
					imsLogger.debug("############# 新老账户属于同一分库、分表，无需更新路由");
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * lijc3 2012-9-19 处理前，先判断是否需要进行过户处理
	 * 
	 * @param dataList
	 */
	public void beforeHandler(List<IUserAcctRel> dataList) {
		if (dataList.size() > 1) {
			Map<Long,Map<String,Object>> changeAcctMap = null;
			for (int i=0;i<dataList.size();i++) {
				IUserAcctRel rel = dataList.get(i);
				if (rel.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
					if (rel.getOldAcctId().intValue() != rel.getAcctId().intValue()) {
						if(changeAcctMap == null){
							context.addExtendParam(ConstantExDefine.CHANGE_ACCT_FLAG, true);
							changeAcctMap = new HashMap<Long,Map<String,Object>>();
						}

						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put(ConstantExDefine.ROUTER_MOVE_OLD_ACCTID, rel.getOldAcctId());
						paramMap.put(ConstantExDefine.ROUTER_MOVE_NEW_ACCTID, rel.getAcctId());
						paramMap.put(ConstantExDefine.CHANGE_ACCT_DATE, rel.getValidDate());
						paramMap.put(ConstantExDefine.CHANGE_ACCT_EXPIRE_DATE, rel.getExpireDate());
						paramMap.put(ConstantExDefine.CHANGE_ACCT_UPDATE_DATE, rel.getCommitDate());
						this.changeRoute(rel,paramMap);
						changeAcctMap.put(rel.getUserId(), paramMap);
					}
				}
			}
			if(changeAcctMap!=null){
				context.addExtendParam(ConstantExDefine.CHANGE_ACCT_MAP, changeAcctMap);
			}
		}
	}

	/**
	 * 过户时的特殊处理 一、数据迁移 二、预付费过户则需要转可用资金 wukl 2012-11-15
	 */
	public void changeOwnerDeal() {
		if (!UserAcctRelHelper.isChangeOwner(context)) {
			return;
		}

		// 1、数据迁移
		changeData4Route();

		// 2、预付费过户转移可用资金
		// GX不进行资金转移，由CRM调用帐管实时接口处理
		// doTransfer4Prepaid();

	}

	public SAbmAssetTransferRet transferMdbAssetByChangeRegionCode(Long userId, Long acctId, int regionCode, int orgRegionCode) {
		return transferMdbAsset(userId, acctId, acctId, regionCode, orgRegionCode, EnumCodeExDefine.ABM_TRANSFER_TYPE_ACCT);
	}

	/**
	 * 过户资产迁移 lijc3 2013-8-26
	 */

	public SAbmAssetTransferRet transferMdbAssetByChangeOwner(Long userId, Long oldAcctId, Long newAcctId) {
		RouteResult rst = context.getCmp(RouterCmp.class).queryUserRouter(userId);
		//int regionCode = rst.getRouteDimension().getRegionCode();
		int regionCode=22;
		if(rst.getRouteDimension()!=null){
			return transferMdbAsset(userId, newAcctId, oldAcctId, regionCode, regionCode, EnumCodeExDefine.ABM_TRANSFER_TYPE_USER);
		}else{
			return null;
		}
		
		
	}

	public SAbmAssetTransferRet transferMdbAsset(long servId, long acctId, long orgAcctId, int regionCode, int orgRegionCode,
			int transferType) {
		SAbmAssetTransferRet ret = new SAbmAssetTransferRet();
		STransParam param = new STransParam();
		SAbmAssetTransfer transfer = new SAbmAssetTransfer();
		param.set_acctId(acctId);
		param.set_orgAcctId(orgAcctId);
		param.set_orgRegionCode(orgRegionCode);
		param.set_regionCode(regionCode);
		param.set_servId(servId);
		param.set_transType(transferType);
		transfer.set_transParam(param);
		ret = SpringUtil.getSalClient().get(MdbKVDefine.TRANSFERSINGLEMDBASSET, transfer, ret);
		return ret;
	}

	/**
	 * 预付费过户则需要转可用资金 wukl 2012-11-15
	 */
	@SuppressWarnings("unused")
	private void doTransfer4Prepaid() {
		// 获取过户标识
		if (!UserAcctRelHelper.isChangeOwner(context)) {
			return;
		}

		if (context.getBusiCode() == 191000000105L || context.getBusiCode() == 191000000107L) {
			imsLogger.info("****分户不资金转移");
			return;
		}
		// 判断过户上家 是否是预付费
		Long oldAcctId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_OLD_ACCTID);
		Acct3hBean bean = context.get3hTree().loadAcct3hBean(oldAcctId);
		if (bean.getAccount().getCreditSignControl().shortValue() != EnumCodeExDefine.PREPAID) {
			return;
		}

	}

	/**
	 * 进行数据迁移 wukl 2012-8-12
	 * 
	 * @param iUserAcctRel
	 */
	@SuppressWarnings("unchecked")
	private void changeData4Route() {
		RouterCmp routerCmp = context.getCmp(RouterCmp.class);
		Map<Long,Map<String,Object>> changeAcctMap = (Map<Long, Map<String, Object>>) context.getExtendParam(ConstantExDefine.CHANGE_ACCT_MAP);
		if(changeAcctMap != null){
			List<UserInfoComplex> complexList = new ArrayList<UserInfoComplex>();
			for(Entry<Long,Map<String,Object>> entry : changeAcctMap.entrySet()){
				ITableUtil.cleanRequestParamter();
				Long  userId = entry.getKey();
				Map<String,Object> paramMap = entry.getValue();
				Long oldAcctId = (Long) paramMap.get(ConstantExDefine.ROUTER_MOVE_OLD_ACCTID);
				Long newAcctId = (Long) paramMap.get(ConstantExDefine.ROUTER_MOVE_NEW_ACCTID);

				Date changeDate = context.getCommitDate();
				Date expireDate = (Date) paramMap.get(ConstantExDefine.CHANGE_ACCT_EXPIRE_DATE);
				//TODO
				boolean isSameMdb = routerCmp.isRouteToSameMdb(oldAcctId, newAcctId);
				// 非云化，返回true
				if (!ITableUtil.isMdbCloud()) {
					isSameMdb = true;
				}
				// 数据库和MDB都在同一个库中 南基不移动数据库数据
				if (!UserAcctRelHelper.isRouterMoveData(paramMap) && isSameMdb) {// 过户分库、分表一致时
																											// 不需要移数据，但是还需要发布路由
					routerCmp.createUserRouter(userId, newAcctId, null, null, changeDate, expireDate);
					continue;
				}
				
				// 先将RequestContext中的数据清空，并设置成旧的账户，以获取历史数据
				imsLogger.debug("=========================================================== set ContextHolder router acctid:",oldAcctId);
				ITableUtil.setValue2ContextHolder(oldAcctId, oldAcctId, null);

				// 旧库数据置为失效,并备份
				imsLogger.debug("=============================================================================== query old data from old database");
				List<CmResIdentity> backUpCmResIdentity = DBUtil.query(CmResIdentity.class,new DBCondition(CmResIdentity.Field.resourceId, userId));
				List<CmResource> backUpCmResource = DBUtil.query(CmResource.class, new DBCondition(CmResource.Field.resourceId, userId));
				List<CaAccountRes> backUpCaAccountRes = DBUtil.query(CaAccountRes.class,new DBCondition(CaAccountRes.Field.resourceId, userId));
				List<CmResLifecycle> backUpCmResLifecycle = DBUtil.query(CmResLifecycle.class,new DBCondition(CmResLifecycle.Field.resourceId, userId));
				List<CaNotifyExempt> backUpcaNotifyExempt =  DBUtil.query(CaNotifyExempt.class,
								new DBCondition(CaNotifyExempt.Field.objectId, userId), new DBOrCondition(
								new DBCondition(CaNotifyExempt.Field.objectType, EnumCodeExDefine.PROD_OBJECTTYPE_DEV),
								new DBCondition(CaNotifyExempt.Field.objectType,EnumCodeExDefine.PROD_OBJECTTYPE_GCA)));
				List<CmResServiceStatus> backUpCmResServiceStatus = DBUtil.query(CmResServiceStatus.class,new DBCondition(CmResServiceStatus.Field.resourceId, userId));
				List<CmContactMedium> backUpCmContactMedium = DBUtil.query(CmContactMedium.class, new DBCondition(CmContactMedium.Field.objectId, userId),
								new DBOrCondition(new DBCondition(CmContactMedium.Field.objectType, EnumCodeDefine.DEV_CONTACT_TYPE),
												  new DBCondition(CmContactMedium.Field.objectType,EnumCodeDefine.GROUP_CONTACT_TYPE)));
				List<CaAccountGroup> backUpCaAccountGroup = DBUtil.query(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId, userId));
				List<CaAccountRv> backUpCaAccountRv = DBUtil.query(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId, userId));
				List<CaAccountRel> backUpCaAccountRel = DBUtil.query(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,userId));
				List<CmResourceMonitor> backUpCmResourceMonitor = DBUtil.query(CmResourceMonitor.class, new DBCondition(CmResourceMonitor.Field.resourceId, userId));
				List<CoProd> backUpCoProd = DBUtil.query(CoProd.class,new DBCondition(CoProd.Field.objectId, userId));

				// 以下三张表没有object_id的索引，所以改为根据prod_id去查
				List<CoProdMapping> backUpCoProdMapping = DBUtil.query(CoProdMapping.class, new DBCondition(CoProdMapping.Field.objectId, userId));
				List<CoProdBillingCycle> backUpCoProdBillingCycle = null;
				List<CoProdCharValue> backUpCoProdCharValue = null;
				List<CoProdPriceParam> backUpCoProdPriceParam = null;
				List<CoFnCharValue> backUpFnValue = null;
				List<CoSplitCharValue> backUpSplitValue = null;
				// List<CoProdCellInfo> cellInfoValue = null;
				List<CoBudgetCharValue> backUpBudgetValue = null;
				Set<Long> prodIds = new HashSet<Long>();
				if (CommonUtil.isNotEmpty(backUpCoProd)) {
					for (CoProd prod : backUpCoProd) {
						if (prodIds.contains(prod.getProductId())) {
							continue;
						} else {
							prodIds.add(prod.getProductId());
						}
					}

					backUpCoProdBillingCycle = DBUtil.query(CoProdBillingCycle.class,new DBCondition(CoProdBillingCycle.Field.productId, prodIds, Operator.IN));
					backUpCoProdCharValue = DBUtil.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodIds, Operator.IN));
					backUpCoProdPriceParam = DBUtil.query(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodIds, Operator.IN));
					backUpFnValue = DBUtil.query(CoFnCharValue.class,new DBCondition(CoFnCharValue.Field.productId, prodIds, Operator.IN));
					backUpSplitValue = DBUtil.query(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId, prodIds, Operator.IN));
					backUpBudgetValue = DBUtil.query(CoBudgetCharValue.class, new DBCondition(CoBudgetCharValue.Field.productId, prodIds, Operator.IN));

				}
				
				List<CmUserOrderConfirm> backUpCmUserOrderConfirm = DBUtil.query(CmUserOrderConfirm.class, new DBCondition(CmUserOrderConfirm.Field.resourceId, userId));
				List<CmUserEnterprise> backUpCmUserEnterprise = DBUtil.query(CmUserEnterprise.class, new DBCondition(CmUserEnterprise.Field.resourceId, userId));
				List<CmUserMap> backUpCmUserMap = DBUtil.query(CmUserMap.class, new DBCondition(CmUserMap.Field.resourceId, userId));
				List<CmUserPortability> backUpCmUserPortability = DBUtil.query(CmUserPortability.class, new DBCondition(CmUserPortability.Field.resourceId, userId));
				List<CmUserPbx> backUpCmUserPbx = DBUtil.query(CmUserPbx.class, new DBCondition(CmUserPbx.Field.resourceId, userId));
				List<CmUserShareProd> backUpCmUserShareProd = DBUtil.query(CmUserShareProd.class,new DBCondition(CmUserShareProd.Field.resourceId, userId));
				List<CmUserMarket> backUpCmUserMarket = DBUtil.query(CmUserMarket.class,new DBCondition(CmUserMarket.Field.resourceId, userId));
				List<CmUserRelation> backUpCmUserRelation = DBUtil.query(CmUserRelation.class, new DBCondition(CmUserRelation.Field.resourceId, userId));
				List<CmUserParam> backUpCmUserParam = DBUtil.query(CmUserParam.class,new DBCondition(CmUserParam.Field.resourceId, userId));
				List<CmCustVip> backUpCmCustVip = DBUtil.query(CmCustVip.class, new DBCondition(CmCustVip.Field.resourceId, userId));
				List<CmResService> backUpCmResService = DBUtil.query(CmResService.class,new DBCondition(CmCustVip.Field.resourceId, userId));
				List<CmUserShareRel> backUpCmUserShareRel = DBUtil.query(CmUserShareRel.class,new DBCondition(CmUserShareRel.Field.resourceId, userId));
				List<CmUserValidchange> backUpCmUserValidchange = DBUtil.query(CmUserValidchange.class,new DBCondition(CmUserValidchange.Field.userId,userId));
				// 新增的CM表也需要迁移		
				List<CmUserOrder> backUpCmUserOrder = DBUtil.query(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId, userId));
				// 群反向表
				List<CaResGrpRevs> backUpGrpRes = DBUtil.query(CaResGrpRevs.class, new DBCondition(CaResGrpRevs.Field.resourceId, userId));
				// 湖南版本新增表
				List<CmCustGroup> backUpCmCustGroup = DBUtil.query(CmCustGroup.class,new DBCondition(CmCustGroup.Field.resourceId, userId));
				List<CmUserImpu> backUpCmUserImpu = DBUtil.query(CmUserImpu.class,new DBCondition(CmUserShareRel.Field.resourceId, userId));
				List<CaCredit> backUpCaCredit = DBUtil.query(CaCredit.class,new DBCondition(CaCredit.Field.resourceId, userId));
				
				//TODO 删除旧库数据
				imsLogger.debug("*******clear old data with old router ");
				this.deleteByConditionWithNoUpdate(CmResIdentity.class,new DBCondition(CmResIdentity.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmResource.class, new DBCondition(CmResource.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CaAccountRes.class,new DBCondition(CaAccountRes.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmResLifecycle.class,new DBCondition(CmResLifecycle.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CaNotifyExempt.class,
											new DBCondition(CaNotifyExempt.Field.objectId, userId), new DBOrCondition(
											new DBCondition(CaNotifyExempt.Field.objectType, EnumCodeExDefine.PROD_OBJECTTYPE_DEV),
											new DBCondition(CaNotifyExempt.Field.objectType,EnumCodeExDefine.PROD_OBJECTTYPE_GCA)));
				this.deleteByConditionWithNoUpdate(CmResServiceStatus.class,new DBCondition(CmResServiceStatus.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmContactMedium.class, new DBCondition(CmContactMedium.Field.objectId, userId),
											new DBOrCondition(new DBCondition(CmContactMedium.Field.objectType, EnumCodeDefine.DEV_CONTACT_TYPE),
											new DBCondition(CmContactMedium.Field.objectType,EnumCodeDefine.GROUP_CONTACT_TYPE)));
				this.deleteByConditionWithNoUpdate(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId, userId));
				this.deleteByConditionWithNoUpdate(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId, userId));
				this.deleteByConditionWithNoUpdate(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,userId));
				this.deleteByConditionWithNoUpdate(CmResourceMonitor.class, new DBCondition(CmResourceMonitor.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CoProd.class,new DBCondition(CoProd.Field.objectId, userId));
				this.deleteByConditionWithNoUpdate(CoProdMapping.class, new DBCondition(CoProdMapping.Field.objectId, userId));
				if(CommonUtil.isNotEmpty(prodIds)){
					this.deleteByConditionWithNoUpdate(CoProdBillingCycle.class,new DBCondition(CoProdBillingCycle.Field.productId, prodIds, Operator.IN));
					this.deleteByConditionWithNoUpdate(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodIds, Operator.IN));
					this.deleteByConditionWithNoUpdate(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodIds, Operator.IN));
					this.deleteByConditionWithNoUpdate(CoFnCharValue.class,new DBCondition(CoFnCharValue.Field.productId, prodIds, Operator.IN));
					this.deleteByConditionWithNoUpdate(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId, prodIds, Operator.IN));
					this.deleteByConditionWithNoUpdate(CoBudgetCharValue.class, new DBCondition(CoBudgetCharValue.Field.productId, prodIds, Operator.IN));
				}
				
				this.deleteByConditionWithNoUpdate(CmUserOrderConfirm.class, new DBCondition(CmUserOrderConfirm.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserEnterprise.class, new DBCondition(CmUserEnterprise.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserMap.class, new DBCondition(CmUserMap.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserPortability.class, new DBCondition(CmUserPortability.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserPbx.class, new DBCondition(CmUserPbx.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserShareProd.class,new DBCondition(CmUserShareProd.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserMarket.class,new DBCondition(CmUserMarket.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserRelation.class, new DBCondition(CmUserRelation.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserParam.class,new DBCondition(CmUserParam.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmCustVip.class, new DBCondition(CmCustVip.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmResService.class,new DBCondition(CmCustVip.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserShareRel.class,new DBCondition(CmUserShareRel.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserValidchange.class,new DBCondition(CmUserValidchange.Field.userId,userId));
				this.deleteByConditionWithNoUpdate(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CaResGrpRevs.class, new DBCondition(CaResGrpRevs.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmCustGroup.class,new DBCondition(CmCustGroup.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CmUserImpu.class,new DBCondition(CmUserShareRel.Field.resourceId, userId));
				this.deleteByConditionWithNoUpdate(CaCredit.class,new DBCondition(CaCredit.Field.resourceId, userId));
				
				routerCmp.createUserRouter(userId, newAcctId, null, null, changeDate, expireDate);
				// 数据处理完后，发布新路由
				imsLogger.debug("******* 发布用户与账户的新关系到路由中");
				imsLogger.debug("=========================================================== set ContextHolder router acctid:",newAcctId);
				ITableUtil.setValue2ContextHolder(newAcctId, newAcctId, null);

				imsLogger.debug("******* start move data to new database");
				// 向新库插入数据
				boolean needMoveMdb = false;
				if(!isSameMdb && ITableUtil.isMdbCloud()){
					needMoveMdb = true;
				}
				//如果mdb需要搬迁，应当将搬迁后的数据全部放到context中，供mdb上发到新库，如果不需要搬迁，则不放到context中
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmResource,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmResIdentity,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmResLifecycle,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCoProd,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCoProdBillingCycle,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCoProdPriceParam,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCoProdCharValue,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpFnValue,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpSplitValue,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpBudgetValue,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpGrpRes,needMoveMdb);
				
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCaAccountRes,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCaAccountRel,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserValidchange,needMoveMdb);
	
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmResourceMonitor,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserEnterprise,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserOrderConfirm,needMoveMdb);

				this.insertBatchByChangeOwnNoSetSoNbr(backUpCoProdMapping,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCaAccountGroup,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCaAccountRv,needMoveMdb);

				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserMap,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserOrder,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserPbx,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserPortability,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserShareProd,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserMarket,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserRelation,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpcaNotifyExempt,needMoveMdb);

				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserParam,needMoveMdb);
				
				
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmCustGroup,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmCustVip,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmResServiceStatus,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmResService,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserShareRel,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmUserImpu,needMoveMdb);
				this.insertBatchByChangeOwnNoSetSoNbr(backUpCmContactMedium,needMoveMdb);
				if(CommonUtil.isNotEmpty(backUpCaCredit)){
					for(CaCredit dmCredit : backUpCaCredit){
						dmCredit.setAcctId(newAcctId);
					}
					this.insertBatchByChangeOwnNoSetSoNbr(backUpCaCredit,needMoveMdb);
				}
				
				// 过户旧库信息备份
				// 不在同一个MDB才进行MDB数据备份
				if(needMoveMdb){
					UserInfoComplex complex = new UserInfoComplex();

					complex.setCommitDate(context.getCommitDate());
					complex.setSameMdb(isSameMdb);
					complex.setBackUpCmUserMap(backUpCmUserMap);
					complex.setBackUpCmUserOrder(backUpCmUserOrder);
					complex.setBackUpCmUserPbx(backUpCmUserPbx);
					complex.setBackUpCmUserPortability(backUpCmUserPortability);
					complex.setBackUpCmUserShareProd(backUpCmUserShareProd);
					complex.setBackUpCmUserMarket(backUpCmUserMarket);

					complex.setBackUpCmResource(backUpCmResource);
					complex.setBackUpCmResIdentity(backUpCmResIdentity);
					complex.setBackUpCmResLifecycle(backUpCmResLifecycle);
					complex.setBackUpCoProd(backUpCoProd);
					complex.setBackUpCoProdBillingCycle(backUpCoProdBillingCycle);
					complex.setBackUpCoProdPriceParam(backUpCoProdPriceParam);
					complex.setBackUpCoProdCharValue(backUpCoProdCharValue);
					complex.setBackUpFnValue(backUpFnValue);
			
					complex.setBackUpGrpRes(backUpGrpRes);
					complex.setBackUpBudgetValue(backUpBudgetValue);
					complex.setBackUpCmResourceMonitor(backUpCmResourceMonitor);
					complex.setBackUpCmUserEnterprise(backUpCmUserEnterprise);
					complex.setBackUpCmUserOrderConfirm(backUpCmUserOrderConfirm);
					complex.setBackUpCaNotifyExempt(backUpcaNotifyExempt);
					complex.setBackUpCoProdMapping(backUpCoProdMapping);
					complex.setBackUpCaAccountGroup(backUpCaAccountGroup);
					complex.setBackUpCaAccountRv(backUpCaAccountRv);
					complex.setBackUpCmUserRelation(backUpCmUserRelation);

					complex.setBackUpCmUserParam(backUpCmUserParam);
					
					complex.setBackUpCmCustGroup(backUpCmCustGroup);
					complex.setBackUpCmCustVip(backUpCmCustVip);
					complex.setBackUpCaCredit(backUpCaCredit);
					complex.setBackUpCmResService(backUpCmResService);
					complex.setBackUpCmResServiceStatus(backUpCmResServiceStatus);
					complex.setBackUpCmUserImpu(backUpCmUserImpu);
					complex.setBackUpCmUserShareRel(backUpCmUserShareRel);
					complex.setBackUpCmContactMedium(backUpCmContactMedium);
					complex.setBackUpCmUserValidchange(backUpCmUserValidchange);
					complex.setOldAcctId(oldAcctId);
					complex.setNewAcctId(newAcctId);
					
					if (CommonUtil.isNotEmpty(backUpCaAccountRes)) {
						ArrayList<CaAccountRes> oldResList = new ArrayList<CaAccountRes>();
						for (CaAccountRes res : backUpCaAccountRes) {
							if(res.getAcctId().longValue() != newAcctId.longValue()){
								oldResList.add(res);
							}
						}
						if(CommonUtil.isNotEmpty(oldResList)){
							complex.setBackUpAcctRes(oldResList);
						}
					}
					complexList.add(complex);
				}else {
					imsLogger.debug("***change owner,but is the same mdb...");

				}
				
				imsLogger.debug("******* 路由变更的数据迁移结束 : from ",oldAcctId," to ",newAcctId);
			}
			if(CommonUtil.isNotEmpty(complexList)){
				context.setUserInfoComplexList(complexList);
			}
			
		}
		
//		Long userId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_USERID);
//		Long oldAcctId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_OLD_ACCTID);
//		Long newAcctId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_NEW_ACCTID);
//
//		Date changeDate = context.getCommitDate();
//		Date expireDate = (Date) context.getExtendParam("CHANGE_ACCT_EXPIRE_DATE");
		
	}

}