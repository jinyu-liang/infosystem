package com.ailk.imssh.common.manual.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jef.tools.reflect.BeanUtils;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmAssetTransferRet;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserVersion;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlHashMap;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.scandeal.deal.BaseDeal;
import com.ailk.imssh.common.manual.bean.CaseBean;
import com.ailk.imssh.common.manual.bean.ClassBean;
import com.ailk.imssh.common.manual.bean.CondBean;
import com.ailk.imssh.common.manual.bean.IndexBean;
import com.ailk.imssh.common.manual.init.ManualModifyUpfieldInitBean;
import com.ailk.imssh.common.mdb.ErrorMsgHolder;
import com.ailk.imssh.common.mdb.ItableSalMdbBean;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserAcctRelCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModifyRst;

/**
 * @Description:手工数据处理
 * @author wangjt
 * @Date 2012-9-25
 * @Date 2012-12-12 wukl 支持只传入订购对象，上发其所有订购；若产品编号没传则product_id不作为查询条件
 * @Date 2012-12-12 wukl 出错后直接移入历史表，不作回滚处理
 */
@Transactional
public class ManualHandler implements IHandler {
	private static final ImsLogger imsLogger = new ImsLogger(ManualHandler.class);
	@Override
	public void hander(ImsManualModify imsManualModify, ITableContext context, String tableName) throws Exception {
		long errorCode = 0;
		String errorMsg = null;
		SSyncAllInfo info = new SSyncAllInfo();
		String upField = imsManualModify.getUpField();
		try {
			context.setManualFlag(true);
			ItableSalMdbBean itableSalMdbBean = new ItableSalMdbBean(context);
			imsLogger.info("*************begin get date from database....");
			// 1、从数据库加载修改的数据到内存中
			int flag = this.loadDbToCache(imsManualModify, context);
			itableSalMdbBean.setFlag(flag);
			dealCaAccountRv(context);

			dealManualRoutes(context);
			if (upField.charAt(EnumCodeExDefine.C_USER_VERSION) == '1') {
				dealCuserVersion(info, imsManualModify, context);
			}

			imsLogger.info("*************end get date from datebase....");
			ErrorMsgHolder syncMdb = null;
			// 2、上发数据到mdb
			imsLogger.info("*************begin Syn data to mdb....");
			if (flag == EnumCodeExDefine.SYNC_MDB_BY_ACCT) {
				syncMdb = itableSalMdbBean.syncMdb(null, imsManualModify.getAcctId());
			} else {
				if (upField.charAt(EnumCodeExDefine.C_USER_VERSION) == '1') {
					syncMdb = itableSalMdbBean.syncMdb(info, null);
				} else {
					syncMdb = itableSalMdbBean.syncMdb(null, null);
				}
			}

			errorCode = syncMdb.getCbsErrorMsg().get_errorCode();
			errorMsg = syncMdb.getCbsErrorMsg().get_errorMsg();
			imsLogger.info("*************end Syn data to mdb....");
		} catch (Exception e) {
			imsLogger.error(e, e);
			errorCode = ErrorCodeDefine.UNKNOWN_ERROR;
			errorMsg = e.getMessage();
		} finally {
			// 3、处理手工索引表中的数据，将原表中的数据删除，移入到结果表中
			romoveModifyData(tableName, imsManualModify, errorCode, errorMsg);
			context = null;
		}
	}

	/**
	 * lijc3 2013-7-17 手工上发的时候处理路由数据
	 * 
	 * @param context
	 */
	private void dealManualRoutes(ITableContext context) {
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		List<CoSplitCharValue> charValueList = context.getAllCacheList(CoSplitCharValue.class);
		if (CommonUtil.isNotEmpty(charValueList)) {
			routCmp.dealManualSplitRouter(charValueList);
		}
		List<CmUserEnterprise> priseList = context.getAllCacheList(CmUserEnterprise.class);
		if (CommonUtil.isNotEmpty(priseList)) {
			routCmp.dealManualEnterpriseRouter(priseList);
		}
		
		List<CaAccountRel> acctRelList = context.getAllCacheList(CaAccountRel.class);
		if(CommonUtil.isNotEmpty(acctRelList)){
			routCmp.dealGroupRela(acctRelList);
		}
		
		/*
		 * List<CmResIdentity> identityList =
		 * context.getAllCacheList(CmResIdentity.class);
		 * 
		 * if (CommonUtil.isNotEmpty(identityList))
		 * {
		 * routCmp.dealManualImsiRouter(identityList);
		 * }
		 */
	}

	private void dealCaAccountRv(ITableContext context) {
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		List<CaAccountRv> rvList = context.getAllCacheList(CaAccountRv.class);
		if (CommonUtil.isEmpty(rvList)) {
			return;
		}
		routCmp.dealManualGroupMemberRouter(rvList);
		Set<Long> userIdSet = new HashSet<Long>();
		for (CaAccountRv rv : rvList) {
			Long userId = rv.getResourceId();
			if (userIdSet.contains(userId)) {
				continue;
			} else {
				userIdSet.add(userId);
			}
			Long routeAcctId = routCmp.queryAcctIdByUserId(userId);
			ITableUtil.setValue2ContextHolder(null, routeAcctId, null);
			List<CaResGrpRevs> revsList = DBUtil.query(CaResGrpRevs.class, new DBCondition(CaResGrpRevs.Field.resourceId, userId));
			if (CommonUtil.isEmpty(revsList)) {
				continue;
			}
			Set<Long> acctSet = new HashSet<Long>();
			for (CaResGrpRevs revs : revsList) {
				if (revs.getAcctId().equals(rv.getAcctId())) {
					continue;
				}
				if (acctSet.contains(revs.getAcctId())) {
					continue;
				} else {
					acctSet.add(revs.getAcctId());
				}
				Long rvRouteAcctId = routCmp.queryAcctIdByUserId( revs.getAcctId());
				ITableUtil.setValue2ContextHolder(rvRouteAcctId,null, null);
				List<CaAccountRv> list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, revs.getAcctId()),
						new DBCondition(CaAccountRv.Field.resourceId, userId));
				context.cacheList(list);
			}
		}
	}

	/**
	 * 处理手工索引表中的数据，将原表中的数据删除，移入到结果表中 wukl 2012-12-12
	 * 
	 * @param imsManualModifyRst
	 * @param imsManualModify
	 * @param errorMsg
	 */
	public void romoveModifyData(String tableName, ImsManualModify imsManualModify, Long errorCode, String errorMsg) {
		errorMsg = new BaseDeal().getErrorMsg(errorMsg);
		try {
			// 1、将手工索引表的结构 转成历史表的结构
			ImsManualModifyRst imsManualModifyRst = new ImsManualModifyRst();
			ITableUtil.copyFieldValue(imsManualModify, imsManualModifyRst);
			imsManualModifyRst.setDealDate(DateUtil.currentDate());
			if (errorCode == 0) {
				imsManualModifyRst.setDealSts(EnumCodeExDefine.DEAL_STS_SUCC);
				imsManualModifyRst.setErrorMsg("");
			} else {
				imsManualModifyRst.setDealSts(EnumCodeExDefine.DEAL_STS_FAIL);
				imsManualModifyRst.setErrorCode(errorCode);
				imsManualModifyRst.setErrorMsg(errorMsg);
			}

			// 2、删除手工索引表中的数据
			if (CommonUtil.isEmpty(tableName)) {
				DBUtil.deleteByCondition(ImsManualModify.class, new DBCondition(ImsManualModify.Field.seqId, imsManualModify.getSeqId()));
			} else {
				ImsManualModify cond = new ImsManualModify();
				cond.setSeqId(imsManualModify.getSeqId());
				DBUtil.getDBClient().delete(cond, tableName);
			}

			// 3、增加结果记录
			DBUtil.insert(imsManualModifyRst);
		} catch (SQLException e) {
			imsLogger.error("SQL error at: " + e.getSQLState(), e);
			throw new BaseException(e.getErrorCode(), e, e.getMessage());
		}
	}

	/**
	 * 根据upfield，从数据库中查询出有变更的表的对应数据，缓存
	 */
	private int loadDbToCache(ImsManualModify imsManualModify, IMSContext context) {
		String upField = imsManualModify.getUpField();
		Long custId = imsManualModify.getCustId();
		Long acctId = imsManualModify.getAcctId();
		Long userId = imsManualModify.getUserId();
		Long prodId = imsManualModify.getProdId();
		// 是否产品全量覆盖上发 为0表示单个产品上发，为1表示整个用户产品上发 为2表是整个用户或者账户产品上发。
		// 都不包含群产品及群个性化产品
		int flag = 0;
		RouterCmp routerCmp = new RouterCmp();
		for (int i = 0; i < upField.length(); i++) {
			if (upField.charAt(i) != '1') {
				continue;
			}
			if (i == EnumCodeExDefine.SYNC_MDB_BY_ACCT) {
				continue;
			}
			if(i==EnumCodeExDefine.C_USER_VERSION){
				continue;
			}
			// 上发用户账户关系，全表扫描
			if (i == 7){
				if (!ITableUtil.isMdbCloud()) {
					ITableUtil.setValue2ContextHolder(null, null, null);
				} else {
					Long routeAcctId = routerCmp.queryAcctIdByUserId(acctId);
					ITableUtil.setValue2ContextHolder(routeAcctId, null, null);
				}
			}else if(i == 9) {
				if (!ITableUtil.isMdbCloud()) {
					ITableUtil.setValue2ContextHolder(null, null, null);
				} else {
					Long routeAcctId = routerCmp.queryAcctIdByUserId(userId);
					ITableUtil.setValue2ContextHolder(routeAcctId, null, null);
				}
			} else if (upField.charAt(EnumCodeExDefine.SYNC_MDB_BY_ACCT) == '1') {
				ITableUtil.setValue2ContextHolder(null, acctId, null);
				flag = EnumCodeExDefine.SYNC_MDB_BY_ACCT;
			} else {
				if(!(i == EnumCodeExDefine.SYS_RT_RESOURCE || i == EnumCodeExDefine.SYS_RT_ACCOUNT || i == EnumCodeExDefine.SYS_RT_IDENTITY
						|| i == EnumCodeExDefine.SYS_RT_CUSTOMER || i == EnumCodeExDefine.SYS_RT_INDUSTRY)){
					if(userId!=null){
						Long routeAcctId = routerCmp.queryAcctIdByUserId(userId);
						ITableUtil.setValue2ContextHolder(null, routeAcctId, null);
					}else if(userId==null&&acctId!=null){
						ITableUtil.setValue2ContextHolder(null, acctId, null);
					}else if(userId==null&&acctId==null&&custId!=null){
			            ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_CUST, custId);

					}
				}
			}

			if (i == EnumCodeExDefine.DEAL_CHANGE_OWN_ASSET) {
				if (custId == null || acctId == null | userId == null) {
					throw new IMSException(
							"cust_id(new_acct_id),old_acct_id(acct_id),user_id cannot null when change own transfer assert !!");
				}
				SAbmAssetTransferRet ret = new UserAcctRelCmp().transferMdbAssetByChangeOwner(userId, acctId, custId);
				if (ret != null && ret.get_succFlag() != EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
					throw new IMSException("transfer assert when change own error");
				}
				continue;
			}
			if (i == EnumCodeExDefine.SYS_RT_RESOURCE || i == EnumCodeExDefine.SYS_RT_ACCOUNT || i == EnumCodeExDefine.SYS_RT_IDENTITY
					|| i == EnumCodeExDefine.SYS_RT_CUSTOMER || i == EnumCodeExDefine.SYS_RT_INDUSTRY) {
				// 处理路由
				new RouterCmp().syncRouteByManual(i, imsManualModify);
				continue;
			}

			IndexBean indexBean = ManualModifyUpfieldInitBean.getManualUpfieldIndex(i);
			List<ClassBean> classBeanList = indexBean.getClassBeanList();

			int firstSize = -1;
			String firstClassName = null;
			for (ClassBean classBean : classBeanList) {
				Class clazz = classBean.getClazz();
				List<CondBean> condBeanList = classBean.getCondBeanList();

				// class对应的是condBeanList的情况
				if (condBeanList != null && condBeanList.isEmpty() == false) {
					List<DBCondition> dbCondList = this.convertCondListToDbCondList(custId, acctId, userId, prodId, condBeanList);

					// 查询出数据
					List query = DBUtil.query(clazz, dbCondList.toArray(new DBCondition[dbCondList.size()]));
					int currentSize = query == null ? 0 : query.size();

					// 对应多个class类的时候，需要验证数据库的记录数相同
					if (firstSize == -1) {
						firstSize = currentSize;
						firstClassName = clazz.getName();
					} else if (firstSize != currentSize) {
						// 不相同抛出异常
						throw new IMSException(CommonUtil.concat("record size of table ", clazz.getName(), " not match ", firstClassName,
								"'s record size!!!"));
					}

					// 缓存起来
					context.cacheList(query);
				} else {// class对应的是caseBeanList的情况
					List<CaseBean> caseBeanList = classBean.getCaseBeanList();

					DBCondition dbCond = null;
					List<CondBean> condBeanList2 = new ArrayList<CondBean>();

					for (CaseBean caseBean : caseBeanList) {
						String value = caseBean.getValue();
						if (value.equals("userId")) {
							if (CommonUtil.isValid(userId)) {
								dbCond = new DBCondition(caseBean.getField(), userId);
								// 支持只传入订购对象，上发其所有订购；若产品编号没传则product_id不作为查询条件
								List<CondBean> tempList = caseBean.getCondBeanList();
								for (CondBean cb : tempList) {
									if (cb.getValue().equals("prodId") && !CommonUtil.isValid(prodId)) {
										if (i == 17) {
											flag = 1;
										}
										continue;
									}
									condBeanList2.add(cb);
								}
								// case分支找到，则不再执行下面配置的case
								break;
							}
						} else if (value.equals("acctId")) {
							if (CommonUtil.isValid(acctId)) {
								dbCond = new DBCondition(caseBean.getField(), acctId);
								// 支持只传入订购对象，上发其所有订购；若产品编号没传则product_id不作为查询条件
								List<CondBean> tempList = caseBean.getCondBeanList();
								for (CondBean cb : tempList) {
									if (cb.getValue().equals("prodId") && !CommonUtil.isValid(prodId)) {
										if (i == 17) {
											flag = 2;
										}
										continue;
									}
									condBeanList2.add(cb);
								}
								// case分支找到，则不再执行下面配置的case
								break;
							}
						}
					}
					if (dbCond == null) {
						throw new IMSException("acctId and userId can't be null at the same time in table: ims_manual_modify !!");
					}
					List<DBCondition> dbCondList = convertCondListToDbCondList(custId, acctId, userId, prodId, condBeanList2);
					dbCondList.add(dbCond);

					List query = DBUtil.query(clazz, dbCondList.toArray(new DBCondition[dbCondList.size()]));
					context.cacheList(query);
				}
			}
		}
		return flag;
	}

	private List<DBCondition> convertCondListToDbCondList(Long custId, Long acctId, Long userId, Long prodId, List<CondBean> condBeanList) {
		List<DBCondition> dbConditionList = new ArrayList<DBCondition>();
		for (CondBean condBean : condBeanList) {

			DBCondition condition = this.convertCondToDbCond(custId, acctId, userId, prodId, condBean);
			dbConditionList.add(condition);
		}
		return dbConditionList;
	}

	private DBCondition convertCondToDbCond(Long custId, Long acctId, Long userId, Long prodId, CondBean condBean) {
		String value = condBean.getValue();

		if (value.equals("custId")) {
			if (CommonUtil.isValid(custId) == false) {
				throw new IMSException("when modify customer info,cust_id must input in ims_manual_modify!!!");
			}
			return new DBCondition(condBean.getField(), custId);
		} else if (value.equals("acctId")) {
			if (CommonUtil.isValid(acctId) == false) {
				throw new IMSException("when modify account info,acct_id must input in ims_manual_modify!!!");
			}
			return new DBCondition(condBean.getField(), acctId);
		} else if (value.equals("userId")) {
			// if (CommonUtil.isValid(userId) == false)
			if (userId == null) {
				throw new IMSException("when modify user info,user_id must input in ims_manual_modify!!!");
			}
			return new DBCondition(condBean.getField(), userId);
		} else if (value.equals("prodId")) {
			if (CommonUtil.isValid(userId) == false && CommonUtil.isValid(acctId) == false) {
				throw new IMSException("when modify product info,acct_id or user_id must input in ims_manual_modify!!!");
			}
			return new DBCondition(condBean.getField(), prodId);
		}

		return new DBCondition(condBean.getField(), value);
	}
	
	private void dealCuserVersion(SSyncAllInfo info,ImsManualModify imsManualModify, ITableContext context) {
		// TODO Auto-generated method stub
		//  SSyncAllInfo info = new SSyncAllInfo();
		Long userId = imsManualModify.getUserId();
		if (userId == null) {
			throw new IMSException("userId  can't be null when sync CUserVersion !!");
		}
		 RouterCmp routerCmp = new RouterCmp();
		 int vesion=routerCmp.queryVersionByUserId(userId);
		 SUserVersion version = new SUserVersion();
	        if(userId != null){
	        	version.set_resourceId(userId);
	        	version.set_resourceVersion(vesion);
	        	version.set_versionType(EnumCodeDefine.MDB_ROUTE_VERSION_TYPE_USER);
	        }
	        CsdlHashMap<Long, CsdlArrayList<SUserVersion>> versionMap = new CsdlHashMap<Long, CsdlArrayList<SUserVersion>>(
	                Long.class, CsdlArrayList.class);
	        // 不是全量的情况下全部设置为2
	        version.set_syncFlag(0);
	        CsdlArrayList<SUserVersion> list = new CsdlArrayList<SUserVersion>(SUserVersion.class);
	        list.add(version);
	        if(userId != null){
	        	versionMap.put(userId, list);
	        }
	        info.set_userVersion(versionMap);

	}

}
