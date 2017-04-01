package com.ailk.imssh.common.mdb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jef.database.DataObject;

import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupExterior;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturnEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncIvrAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCustInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserEnterprise;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroup;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserHome;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserService;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserShareProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserUserRel;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlHashMap;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.MdbSalBuildComponent;
import com.ailk.ims.component.MdbSalComponent;
import com.ailk.ims.component.ProdMdbSalComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.mapreduce.ImsSyncMapReduce;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.MdbUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.bean.ContextExBean;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.cust.entity.CmCustVip;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareRel;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;

/**
 * @Description:上发mdb类
 * @author wangjt
 * @Date 2012-5-11
 * @Date 2012-09-24 lijc3 修改了继承的基本类
 * @Date 修改账户账期，不会修改产品账期，不需要单独上发
 * @Date 2012-11-09 wukl 群成员上发，网内、网外号码上发到不同的表中
 */
public class ItableSalMdbBean extends ContextExBean {
	private MdbSalComponent salCmp = null;
	private int flag = 0;

	// 覆盖产品标志
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public ItableSalMdbBean(IMSContext context) {
		super.setContext(context);
		salCmp = this.getContext().getComponent(MdbSalComponent.class);
	}

	/**
	 * lijc3 2013-7-15 供第一次MDB上发失败的时候获取上发信息
	 * 
	 * @return
	 */
	public SSyncAllInfo getSyncAllInfo() {
		List<MdbRdl> rdls = createMdbRdl();
		SSyncAllInfo sSyncAllInfo = salCmp.createSyncAllInfo(rdls);
		return sSyncAllInfo;
	}
	
	
	/**
	 * 上发ivr_mdb
	 * @param sSyncAllInfo
	 * @return
	 */
	public ErrorMsgHolder syncIvrMdb(SSyncIvrAllInfo sSyncIvrAllInfo){
		if (sSyncIvrAllInfo == null)// 说明是第一次上发mdb ，则从内存中获取上发数据
		{
			try {
				if (context.getRequestDate() == null) {
					context.setRequestDate(context.getCommitDate());
				}
			} catch (Exception e) {
				context.setRequestDate(DateUtil.currentDate());
			}
			List<MdbRdl> rdls = createIvrRdl();
			sSyncIvrAllInfo = salCmp.createSyncIvrAllInfo(rdls);
		}
		imsLogger.dump("******* sync ivr_mdb content : ", sSyncIvrAllInfo);
		SalClient client = SpringUtil.getSalClient();
		CBSErrorMsg cbsErrorMsg = null;
		try {
			SReturnEx sReturnEx = new SReturnEx();
			// 如果成功则返回0，如果失败则返回1或者其它非0值
			Object ret = client.post(MdbKVDefine.IVR_SYNC_ALL, sSyncIvrAllInfo, sReturnEx);
			
			imsLogger.dump("******* sync IvrMdbRdl result : ", ret);

			cbsErrorMsg = return2CbsMsg(ret);

		} catch (Exception e) {
			imsLogger.error(e, e);
			cbsErrorMsg = new CBSErrorMsg();
			// 返回错误对象
			if (e instanceof BusinessException) {
				cbsErrorMsg.set_errorCode(((BusinessException) e).getCodeAsInteger());
			} else {
				cbsErrorMsg.set_errorCode((int) ErrorCodeDefine.UNKNOWN_ERROR);
			}
			cbsErrorMsg.set_errorMsg(e.getClass().getName() + ":" + e.getLocalizedMessage());
		}

		return new ErrorMsgHolder(cbsErrorMsg, sSyncIvrAllInfo);
	}
	
	/**
	 * lijc3 2013-7-16
	 * 
	 * @param sSyncAllInfo
	 * @param oldAcctId
	 *            不为null的时候表示上发失效的数据到老的MDB
	 * @return
	 */
	public ErrorMsgHolder syncMdb(SSyncAllInfo sSyncAllInfo, Long oldAcctId) {
		// 设置requestDate
		if (sSyncAllInfo == null)// 说明是第一次上发mdb ，则从内存中获取上发数据
		{
			try {
				if (context.getRequestDate() == null) {
					context.setRequestDate(context.getCommitDate());
				}
			} catch (Exception e) {
				context.setRequestDate(DateUtil.currentDate());
			}
			List<MdbRdl> rdls =createMdbRdl();

			 
			sSyncAllInfo = salCmp.createSyncAllInfo(rdls);
			// 异步上发的时候根据flag调整用户级产品或者账户级产品
			resetSSyncAllInfo(sSyncAllInfo);
		}

		imsLogger.dump("******* sync mdb content : ", sSyncAllInfo);

		SalClient client = SpringUtil.getSalClient();
		CBSErrorMsg cbsErrorMsg = null;

		try {
			SReturnEx sReturnEx = new SReturnEx();
			// 如果成功则返回0，如果失败则返回1或者其它非0值
			Object ret = null;
			if (ITableUtil.isMdbCloud()) {
				// map 上发信息
				ImsSyncMapReduce mapReduce = new ImsSyncMapReduce();

				if (oldAcctId != null) {
					mapReduce.setOldAcctId(oldAcctId);
				}
				ret = client.post(MdbKVDefine.SYNC_ALLINFO, sSyncAllInfo, sReturnEx, mapReduce);
			} else {
				ret = client.post(MdbKVDefine.SYNC_ALLINFO, sSyncAllInfo, sReturnEx);
			}
			imsLogger.dump("******* sync MdbRdl result : ", ret);

			cbsErrorMsg = return2CbsMsg(ret);

		} catch (Exception e) {
			//imsLogger.error(e, e);
			e.printStackTrace();
			cbsErrorMsg = new CBSErrorMsg();
			// 返回错误对象
			if (e instanceof BusinessException) {
				cbsErrorMsg.set_errorCode(((BusinessException) e).getCodeAsInteger());
			} else {
				cbsErrorMsg.set_errorCode((int) ErrorCodeDefine.UNKNOWN_ERROR);
			}
			cbsErrorMsg.set_errorMsg(e.getClass().getName() + ":" + e.getLocalizedMessage());
		}

		return new ErrorMsgHolder(cbsErrorMsg, sSyncAllInfo);
	}

	private void resetSSyncAllInfo(SSyncAllInfo sSyncAllInfo) {
		if (flag == 0) {
			imsLogger.debug("no reset");
			return;
		} else if (flag == 1) {// 调整用户级产品
			imsLogger.debug("reset user prom");
			CsdlHashMap<Long, CsdlArrayList<SUserProm>> userProm = sSyncAllInfo.get_userProm();
			// CsdlArrayList<SUserProm> userPromList=new
			// CsdlArrayList<SUserProm>(SUserProm.class);
			if (CommonUtil.isNotEmpty(userProm)) {
				// serv_id ,list
				CsdlHashMap<Long, CsdlArrayList<SUserProm>> tarUserProm = new CsdlHashMap<Long, CsdlArrayList<SUserProm>>(Long.class,
						CsdlArrayList.class);
				for (Entry<Long, CsdlArrayList<SUserProm>> entry : userProm.entrySet()) {
					CsdlArrayList<SUserProm> sObjectList = entry.getValue();
					if (CommonUtil.isNotEmpty(sObjectList)) {
						SUserProm prod = sObjectList.get(0);
						CsdlArrayList<SUserProm> userPromList = tarUserProm.get(prod.get_servId());
						if (userPromList == null) {
							userPromList = new CsdlArrayList<SUserProm>(SUserProm.class);
							tarUserProm.put(prod.get_servId(), userPromList);
						}
						userPromList.addAll(sObjectList);
					}
				}
				sSyncAllInfo.set_userProm(null);
				sSyncAllInfo.set_userPromAll(tarUserProm);
			}
		} else if (flag == 2) {// 调整账户级产品
			imsLogger.debug("reset acct prom");
			CsdlHashMap<Long, CsdlArrayList<SAcctProm>> acctProm = sSyncAllInfo.get_acctProm();
			if (CommonUtil.isNotEmpty(acctProm)) {
				// acct_id list
				CsdlHashMap<Long, CsdlArrayList<SAcctProm>> tarAcctProm = new CsdlHashMap<Long, CsdlArrayList<SAcctProm>>(Long.class,
						CsdlArrayList.class);
				for (Entry<Long, CsdlArrayList<SAcctProm>> entry : acctProm.entrySet()) {
					CsdlArrayList<SAcctProm> sObjectList = entry.getValue();
					if (CommonUtil.isNotEmpty(sObjectList)) {
						SAcctProm prod = sObjectList.get(0);
						CsdlArrayList<SAcctProm> acctPromList = tarAcctProm.get(prod.get_acctId());
						if (acctPromList == null) {
							acctPromList = new CsdlArrayList<SAcctProm>(SAcctProm.class);
							tarAcctProm.put(prod.get_acctId(), acctPromList);
						}
						acctPromList.addAll(sObjectList);
					}
				}
				sSyncAllInfo.set_acctProm(null);
				sSyncAllInfo.set_acctPromAll(tarAcctProm);
			}
		}
	}

	public void resetContextByChangeOwner() {
		UserInfoComplex complex = this.context.getUserInfoComplex();
		Date expireDate = this.context.getRequestDate();
		if (complex != null) {
			this.imsLogger.debug(new Object[] { "______begin to clean all cache____" });
			this.context.removeAllCacheData();
			this.imsLogger.debug(new Object[] { "______finish clean all cache,begin to set expire_date____" });
			cacheAllInfoByChangeOwn(complex, expireDate);
		}
	}
	
	public void resetContextByComplex(UserInfoComplex complex){
		Date expireDate = this.context.getRequestDate();
		if (complex != null) {
			this.imsLogger.debug(new Object[] { "______begin to clean all cache____" });
			this.context.removeAllCacheData();
			this.imsLogger.debug(new Object[] { "______finish clean all cache,begin to set expire_date____" });
			cacheAllInfoByChangeOwn(complex, expireDate);
		}
	}

	public void cacheAllInfoByChangeOwn(UserInfoComplex complex, Date expireDate) {
		ITableUtil.setExpireDate(complex.getBackUpBudgetValue(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmIdentityVsImei(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResIdentity(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResLifecycle(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResource(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResourceMonitor(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResServCycle(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserEnterprise(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserOrderConfirm(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCoProd(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCoProdBillingCycle(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCoProdCharValue(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCoProdPriceParam(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpFnValue(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpSplitValue(), expireDate);

		ITableUtil.setExpireDate(complex.getBackUpCmUserOrder(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserMap(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserPbx(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserPortability(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserShareProd(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserMarket(), expireDate);
		ITableUtil.setExpireDate(complex.getCellInfoValue(), expireDate);

		ITableUtil.setExpireDate(complex.getBackUpCaAccountGroup(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCaAccountRv(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserRelation(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserParam(), expireDate);
		
		ITableUtil.setExpireDate(complex.getBackUpCmCustGroup(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmCustVip(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCaCredit(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResService(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmResServiceStatus(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserImpu(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserShareRel(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmContactMedium(), expireDate);
		ITableUtil.setExpireDate(complex.getBackUpCmUserValidchange(), expireDate);
		
		this.context.cacheList(complex.getBackUpBudgetValue());
		this.context.cacheList(complex.getBackUpCmIdentityVsImei());
		this.context.cacheList(complex.getBackUpCmResIdentity());
		this.context.cacheList(complex.getBackUpCmResLifecycle());
		this.context.cacheList(complex.getBackUpCmResource());
		this.context.cacheList(complex.getBackUpCmResourceMonitor());
		this.context.cacheList(complex.getBackUpCmResServCycle());
		this.context.cacheList(complex.getBackUpCmUserEnterprise());
		this.context.cacheList(complex.getBackUpCmUserOrderConfirm());
		this.context.cacheList(complex.getBackUpCoProd());
		this.context.cacheList(complex.getBackUpCoProdBillingCycle());
		this.context.cacheList(complex.getBackUpCoProdCharValue());
		this.context.cacheList(complex.getBackUpCoProdPriceParam());
		this.context.cacheList(complex.getBackUpFnValue());
		this.context.cacheList(complex.getBackUpSplitValue());
		this.context.cacheList(complex.getBackUpAcctRes());

		this.context.cacheList(complex.getBackUpCmUserPbx());
		this.context.cacheList(complex.getBackUpCmUserOrder());
		this.context.cacheList(complex.getBackUpCmUserMap());
		this.context.cacheList(complex.getBackUpCmUserPortability());
		this.context.cacheList(complex.getBackUpCmUserShareProd());
		this.context.cacheList(complex.getBackUpCmUserMarket());
		this.context.cacheList(complex.getCellInfoValue());

		this.context.cacheList(complex.getBackUpCaAccountGroup());
		this.context.cacheList(complex.getBackUpCaAccountRv());
		this.context.cacheList(complex.getBackUpCmUserRelation());
		this.context.cacheList(complex.getBackUpCmUserParam());
		
		this.context.cacheList(complex.getBackUpCmCustGroup());
		this.context.cacheList(complex.getBackUpCmCustVip());
		this.context.cacheList(complex.getBackUpCaCredit());
		this.context.cacheList(complex.getBackUpCmResService());
		this.context.cacheList(complex.getBackUpCmResServiceStatus());
		this.context.cacheList(complex.getBackUpCmUserImpu());
		this.context.cacheList(complex.getBackUpCmUserShareRel());
		this.context.cacheList(complex.getBackUpCmContactMedium());
		this.context.cacheList(complex.getBackUpCmUserValidchange());
	}

	private CBSErrorMsg return2CbsMsg(Object ret) {
		if (ret == null) {
			return null;
		}
		CBSErrorMsg errorMsg = new CBSErrorMsg();
		errorMsg.set_errorMsg("sync mdb error");

		int errorCode = -1;
		if (ret instanceof SReturn) {
			SReturn sRet = (SReturn) ret;
			errorCode = sRet.get_ret();
		} else if (ret instanceof SReturnEx) {
			SReturnEx sRetEx = (SReturnEx) ret;
			errorCode = sRetEx.get_ret();
		}
		errorMsg.set_errorCode(errorCode);

		return errorMsg;
	}
	
	private List<MdbRdl> createIvrRdl(){
		List<MdbRdl> result = new ArrayList<MdbRdl>();
		result.add(salCmp.buildSuserInt());
		result.add(salCmp.buildSuserSvc());
		
		return result;
	}
	
	private List<MdbRdl> createMdbRdl() {
		List<MdbRdl> result = new ArrayList<MdbRdl>();

		

//		result.add(salCmp.buildSCustomerRdl());
		result.add(salCmp.buildSIdentityList());
		result.add(salCmp.buildSIdentityBoundList());
		result.add(salCmp.buildSUserLIst());
		result.add(salCmp.buildUserAcctRelListNoCondition());
		result.add(salCmp.buildSUserCycleList());
		result.add(salCmp.buildAccountRdl());
		result.add(salCmp.buildExemptionList());
		result.add(salCmp.buildImAcctContactList());
		result.add(salCmp.buildImAcctBillCycleList());
		
		result.add(salCmp.buildSUserServiceStatusList());
		// 上发账户生命周期
		result.add(salCmp.buildSAcctCycleList());

		buildUserGroupAndGroupExterior(result);// this.upCaAccountRv();

		result.add(salCmp.buildSUserGroup4VPN());
		result.add(salCmp.buildSGroupList());
		result.add(salCmp.buildUserMonitorList());
		result.add(salCmp.buildUserOrderConfirmList());
		result.add(salCmp.buildSpecShareDtlList());
		result.add(salCmp.buildSpecShareInfoList());
		result.add(salCmp.buildUserPbx());
		result.add(salCmp.buildUserPort());
		result.add(salCmp.buildResourceMap());
		result.add(salCmp.buildUserMarket());
		
		//群组层级关系
		result.add(salCmp.buildUserGroupTierList());
		
		result.add(buildSUserShareProdList());
		result.add(buildUserUserRelList());
		result.add(buildSUserParamList());
		result.add(buildSUserCustInfoList());
		result.add(buildSUserServiceInfoList());
		result.add(salCmp.buildVaildChange());
		//prodSalCmp.buildSalProductOrder(result);
		buildSalProductOrderTj(result);
		buildUserHomeRdl(result);// 动态小区
		result.add(this.buildUserEnterpriseList());

		List<MdbRdl> list = new ArrayList<MdbRdl>();
		for (MdbRdl rdl : result) {
			if (null != rdl) {
				list.add(rdl);
			}
		}
		return list;
	}

	/**
	 * 群成员上发，网内、网外号码上发到不同的表中 如果是网外号码，则relationshiptype=3，上发到表SGroupExterior中
	 * 如果是网内号码，则relationshiptype=2，上发到表SUserGroup中
	 * 
	 * @Author: wukl
	 * @Date: 2012-11-9
	 * @param result
	 */
	private void buildUserGroupAndGroupExterior(List<MdbRdl> result) {
		ListMapMdbRdl groupExteriorList = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUP_EXTERIOR, SReturn.class);
		ListMapMdbRdl userGroupList = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUP, SReturn.class);
		List<CaAccountRv> rvList = context.getAllCacheList(CaAccountRv.class);
		if (!CommonUtil.isEmpty(rvList)) {
			MdbSalBuildComponent component = context.getComponent(MdbSalBuildComponent.class);
			for (CaAccountRv rv : rvList) {
				if (rv.getRelationshipType().intValue() == EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR) {
					SGroupExterior sGroupExterior = component.buildGroupExterior(rv,context.isDiffException());
					groupExteriorList.addRecord(sGroupExterior.get_serviceAcctId(), sGroupExterior);
				} else {
					SUserGroup group = component.buildCorpUserGroup(rv,context.isDiffException());
					userGroupList.addRecord(group.get_servId(), group);
				}
			}

			result.add(groupExteriorList);
			result.add(userGroupList);
		}
	}

	// 亲情号码
	public void buildUserRela(List<MdbRdl> result) {
		List<CoFnCharValue> fnList = context.getAllCacheList(CoFnCharValue.class);
		if (CommonUtil.isNotEmpty(fnList)) {
			ListMapMdbRdl userRelaRdlMap = new ListMapMdbRdl(MdbKVDefine.SYNC_USERRELA, SReturn.class);
			for (CoFnCharValue value : fnList) {
				userRelaRdlMap.addRecord(value.getObjectId(), buildSUserRelaRdl(value));
			}
			result.add(userRelaRdlMap);
		}
	}

	// 动态小区
	private void buildUserHomeRdl(List<MdbRdl> result) {
		List<CoProdCellInfo> cellInfoList = context.getAllCacheList(CoProdCellInfo.class);
		if (CommonUtil.isNotEmpty(cellInfoList)) {
			ListMapMdbRdl homeZoneRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_HOME_ZONE, SReturn.class);

			MdbSalBuildComponent component = context.getComponent(MdbSalBuildComponent.class);
			for (CoProdCellInfo info : cellInfoList) {
				SUserHome home = component.buildSUserHome(info,context.isDiffException());
				homeZoneRdl.addRecord(home.get_promNo(), home);
			}
			result.add(homeZoneRdl);
		}
	}

	// 亲情号码
	private SUserRela buildSUserRelaRdl(CoFnCharValue value) {
		SUserRela userRela = new SUserRela();
		userRela.set_promNo(value.getProductId());
		userRela.set_validDate(this.toMdbValidDate(value.getValidDate()));
		userRela.set_expireDate(this.toMdbExpireDate(value.getExpireDate()));
		userRela.set_servId(value.getObjectId());
		if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1){
			String str = value.getValue();
            String newStr = str.replaceAll("^(0+)", "");
            userRela.set_oppNumber(newStr);
		}else{
			userRela.set_oppNumber(value.getValue());
		}
		userRela.set_syncFlag(context.getSyncFlag(value.getValidDate()));
		return userRela;
	}

	/**
	 * wangyh3 2012-6-25
	 */
	private ListMapMdbRdl buildUserEnterpriseList() {
		ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERENTERPRISE, SReturn.class);
		List<CmUserEnterprise> priseList = context.getAllCacheList(CmUserEnterprise.class);
		if (CommonUtil.isEmpty(priseList))
			return null;
		for (CmUserEnterprise prise : priseList) {
			SUserEnterprise userEnterprise = this.buildUserEnterprise(prise,context.isDiffException());
			rdl.addRecord(userEnterprise.get_servId(), userEnterprise);
		}
		return rdl;
	}
	
	/**
	 * wangyh3 2012-6-25
	 */
	private ListMapMdbRdl buildUserUserRelList() {
		ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERUSERREL, SReturn.class);
//		List<CmUserRelation> relationList=null;
//		if(context.isDiffException()){
//			 RouterCmp baseCmp = context.getCmp(RouterCmp.class);
//	     	 Long acctId=baseCmp.queryAcctIdByUserId(context.getUserId());
//	    	 ITableUtil.setValue2ContextHolder(null, acctId, null);
//			relationList = DBUtil.query(CmUserRelation.class,new DBCondition(CmUserRelation.Field.rResourceId, context.getUserId()));
//		}else{
//			relationList = context.getAllCacheList(CmUserRelation.class);
//		}
		//relationList = context.getAllCacheList(CmUserRelation.class);
		List<CmUserRelation> relationList = context.getAllCacheList(CmUserRelation.class);

//		if(context.isDiffException()||context.isManualFlag()){
//			if (CommonUtil.isNotEmpty(relationList)){
//				for (CmUserRelation prise : relationList) {
//					SUserUserRel rel = this.buildUserUserRel(prise,context.isDiffException());
//					rdl.addRecord(rel.get_relServId(), rel);
//				}
//			}
//		}else{
//			if (CommonUtil.isNotEmpty(relationList)){
//				for (CmUserRelation prise : relationList) {
//					SUserUserRel rel = this.buildUserUserRel(prise,context.isDiffException());
//					rdl.addRecord(rel.get_servId(), rel);
//				}
//			}	
//		}
		if (CommonUtil.isNotEmpty(relationList)){
			for (CmUserRelation prise : relationList) {
				SUserUserRel rel = this.buildUserUserRel(prise,context.isDiffException());
				rdl.addRecord(rel.get_servId(), rel);
			}
		}	
		//多终端共享
		List<CmUserShareRel> shareRelList = context.getAllCacheList(CmUserShareRel.class);
		if(context.isDiffException()||context.isManualFlag()){
			if (CommonUtil.isNotEmpty(shareRelList)){
				for (CmUserShareRel prise : shareRelList) {
					SUserUserRel rel = this.buildUserUserRel(prise,context.isDiffException());
					rdl.addRecord(rel.get_relServId(), rel);
				}
			}
		}else{
			if (CommonUtil.isNotEmpty(shareRelList)){
				for (CmUserShareRel prise : shareRelList) {
					SUserUserRel rel = this.buildUserUserRel(prise,context.isDiffException());
					rdl.addRecord(rel.get_servId(), rel);
				}
			}
		}

		return rdl;
	}

	private ListMapMdbRdl buildSUserShareProdList() {
		ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CMUSERSHAREPROD, SReturn.class);
		List<CmUserShareProd> priseList = context.getAllCacheList(CmUserShareProd.class);
		if (CommonUtil.isEmpty(priseList))
			return null;
		for (CmUserShareProd prise : priseList) {
			SUserShareProm prom = this.buildSUserShareProd(prise,context.isDiffException());
			rdl.addRecord(prom.get_servId(), prom);
		}
		return rdl;
	}
	
	private ListMapMdbRdl buildSUserParamList() {
		ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_SUSERPARAM, SReturn.class);
		List<CmUserParam> relationList = context.getAllCacheList(CmUserParam.class);
		if (CommonUtil.isEmpty(relationList))
			return null;
		for (CmUserParam prise : relationList) {
			SUserParam rel = this.buildUserParam(prise,context.isDiffException());
			rdl.addRecord(rel.get_servId(), rel);
		}
		return rdl;
	}
	
	private ListMapMdbRdl buildSUserCustInfoList() {
		ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_SUSERCUSTINFO, SReturn.class);
		List<CmCustVip> relationList = context.getAllCacheList(CmCustVip.class);
		if (CommonUtil.isEmpty(relationList))
			return null;
		for (CmCustVip vip : relationList) {
			SUserCustInfo rel = this.buildUserCustInfo(vip,context.isDiffException());
			rdl.addRecord(rel.get_servId(), rel);
		}
		return rdl;
	}
	
	private ListMapMdbRdl buildSUserServiceInfoList() {
		ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_SUSERSERVICEINFO, SReturn.class);
		List<CmResService> relationList = context.getAllCacheList(CmResService.class);
		if (CommonUtil.isEmpty(relationList))
			return null;
		for (CmResService prise : relationList) {
			SUserService rel = this.buildUserServiceInfo(prise,context.isDiffException());
			rdl.addRecord(rel.get_servId(), rel);
		}
		return rdl;
	}
	

	private SUserShareProm buildSUserShareProd(CmUserShareProd shareProd,boolean diffException) {
		SUserShareProm prom = new SUserShareProm();
		prom.set_expireDate(this.toMdbExpireDate(shareProd.getExpireDate()));
		prom.set_servId(shareProd.getResourceId());
		prom.set_promId(shareProd.getProductId());
		prom.set_serviceId(shareProd.getServiceId());
		if(diffException||context.isManualFlag()){
			prom.set_syncFlag(0);	
		}else{
			prom.set_syncFlag(2);
		}
		
		prom.set_validDate(this.toMdbValidDate(shareProd.getValidDate()));
		prom.set_groupId(0L);//默认设置为0 
		prom.set_regionCode(shareProd.getRegionCode());
		return prom;
	}

	private SUserEnterprise buildUserEnterprise(CmUserEnterprise prise,boolean diffException) {
		SUserEnterprise sp = new SUserEnterprise();

		if (prise.getBusiType() != null) {
			sp.set_busiType(prise.getBusiType());
		}
		sp.set_expireDate(this.toMdbExpireDate(prise.getExpireDate()));
		if (prise.getOperatorCode() != null) {
			sp.set_operatorCode(prise.getOperatorCode());
		}
		if (CommonUtil.isNotEmpty(prise.getProperty())) {
			sp.set_property(CommonUtil.StringToLong(prise.getProperty()));
		}
		if (prise.getReplaceFlag() != null) {
			sp.set_replaceFlag(prise.getReplaceFlag());
		}
		sp.set_serviceCode(prise.getServiceCode());
		if (prise.getResourceId() != null) {
			sp.set_servId(prise.getResourceId());
		}
		if (prise.getSpCode() != null) {
			sp.set_spCode(prise.getSpCode());
		}
		sp.set_validDate(this.toMdbValidDate(prise.getValidDate()));
		if (prise.getUnbalancedFlag() != null) {
			sp.set_unbalanceFlag(prise.getUnbalancedFlag());
		}
		if(diffException||context.isManualFlag()){
			sp.set_syncFlag(0);

		}else{
		sp.set_syncFlag(2);
		}
		return sp;
	}

	private SUserUserRel buildUserUserRel(CmUserRelation dmRela,boolean diffException) {
		SUserUserRel rel = new SUserUserRel();
		rel.set_expireDate(this.toMdbExpireDate(dmRela.getExpireDate()));
		rel.set_relaType(dmRela.getBusiType());       // 江西一卡付费约定传为13 ,移动OA为14
		rel.set_relServId(dmRela.getRResourceId());
		rel.set_servId(dmRela.getResourceId());
		if(diffException||context.isManualFlag()){
			rel.set_syncFlag(0);
		}else{
		rel.set_syncFlag(2); 
		}
		rel.set_validDate(this.toMdbValidDate(dmRela.getValidDate()));

		return rel;
	}
	
	private SUserUserRel buildUserUserRel(CmUserShareRel dmRel,boolean diffException) {
		SUserUserRel rel = new SUserUserRel();
		rel.set_expireDate(this.toMdbExpireDate(dmRel.getExpireDate()));
		rel.set_relaType(15);       // 湖南多终端共享定义为15
		rel.set_relServId(dmRel.getViceResourceId());
		rel.set_servId(dmRel.getResourceId());
		if(diffException||context.isManualFlag()){
			rel.set_syncFlag(0);
		}else{
			rel.set_syncFlag(2);
		}
		rel.set_validDate(this.toMdbValidDate(dmRel.getValidDate()));

		return rel;
	}
	
	private SUserParam buildUserParam(CmUserParam dmParam,boolean diffException) {
		SUserParam sParam = new SUserParam();
		sParam.set_expireDate(this.toMdbExpireDate(dmParam.getExpireDate()));
		sParam.set_keyId(dmParam.getParamId());
		sParam.set_userParam(dmParam.getParamValue());
		sParam.set_servId(dmParam.getResourceId());
		if(diffException||context.isManualFlag()){
			sParam.set_syncFlag(0);
		}else{
			sParam.set_syncFlag(2);
		}
		
		sParam.set_validDate(this.toMdbValidDate(dmParam.getValidDate()));

		return sParam;
	}
	
	private SUserCustInfo buildUserCustInfo(CmCustVip dmVip,boolean diffException) {
		SUserCustInfo sParam = new SUserCustInfo();
		sParam.set_expireDate(this.toMdbExpireDate(dmVip.getExpireDate()));
		if(diffException||context.isManualFlag()){
			sParam.set_syncFlag(0);
		}else{
		sParam.set_syncFlag(2);
		}
		sParam.set_validDate(this.toMdbValidDate(dmVip.getValidDate()));
		
		sParam.set_servId(dmVip.getResourceId());
		sParam.set_custId(dmVip.getCustId());
		sParam.set_custStatus(dmVip.getVipState().shortValue());
		sParam.set_custClass(dmVip.getVipClass().shortValue());
		
		return sParam;
	}
	
	private SUserService buildUserServiceInfo(CmResService dmService,boolean diffException) {
		SUserService sParam = new SUserService();
		sParam.set_expireDate(this.toMdbExpireDate(dmService.getExpireDate()));
		if(diffException||context.isManualFlag()){
			sParam.set_syncFlag(0);
		}else{
		sParam.set_syncFlag(2);
		}
		sParam.set_validDate(this.toMdbValidDate(dmService.getValidDate()));
		sParam.set_instId(dmService.getServiceInstId());
		sParam.set_servId(dmService.getResourceId());
		sParam.set_mainFlag(dmService.getMainTag().shortValue());
		sParam.set_serviceId(dmService.getServiceId());
		
		return sParam;
	}
	
	 /**
     * 天津CoProd入mdb规则
     * 按CoProd.ObjectType
     * 0-用户订购普通优惠，分发到内存库CUserProm中。
	 * 1-用户订购群组优惠，分发到内存库CGroupProm中。
     * 2-群组订购群组优惠，分发到内存库CGroupProm中
     * 3-账户订购账户优惠，分发到内存库CAcctProm中。
     * @param result
     */
    public void buildSalProductOrderTj(List<MdbRdl> result){
    	 Map<String, ListMapMdbRdl> tempMap = new HashMap<String, ListMapMdbRdl>();
    	 ProdMdbSalComponent prodSalCmp = this.getContext().getComponent(ProdMdbSalComponent.class);
         //产品表
    	 List<CoProd> coProds=null;
    	 if(context.isDiffException()){
    		 RouterCmp routeCmp = context.getCmp(RouterCmp.class);
         	 Long acctId=routeCmp.queryAcctIdByUserId(context.getUserId());
        	 ITableUtil.setValue2ContextHolder(null, acctId, null);
    		 coProds=DBUtil.query(CoProd.class,new DBCondition(CoProd.Field.objectId, context.getUserId()));
    	 }else{
             coProds = context.getAllCacheList(CoProd.class);
    	 }
         if (CommonUtil.isNotEmpty(coProds)){
        	 for (CoProd coProd:coProds){
        		 if (coProd == null)
                 {
                     continue;
                 }
                
                 if(coProd.getProdTypeId() == EnumCodeDefine.PROD_TYPE_USER){
                	
                	 prodSalCmp.buildSUserPromRdl(coProd,tempMap);
                 }else if(coProd.getProdTypeId() == EnumCodeDefine.PROD_TYPE_GROUP_1){
                	 Long productId = coProd.getProductId();
                	 CoProdCharValue charValue = null;
                     List<CoProdCharValue> charValueList = context.getAllCacheList(CoProdCharValue.class,  new CacheCondition(CoProdCharValue.Field.productId,productId));
                     if(CommonUtil.isNotEmpty(charValueList)){
                    	 for(CoProdCharValue value : charValueList){
                        	 if(value.getSpecCharId().intValue() == SpecCodeDefine.GROUP_IN_PERSONAL_ID || value.getSpecCharId().intValue()==SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID){
                        		 charValue = value;
                        		 break;
                        	 }
                         }
                     }
                     
                     if(charValue == null)
                     {
                    	 RouterCmp routeCmp = context.getCmp(RouterCmp.class);
                     	 Long acctId=routeCmp.queryAcctIdByUserId(coProd.getObjectId());
                    	 ITableUtil.setValue2ContextHolder(null, acctId, null);
                    	 charValue = DBUtil.querySingle(CoProdCharValue.class,new DBCondition(CoProdCharValue.Field.productId,productId),
                    			 new DBOrCondition(new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeDefine.GROUP_IN_PERSONAL_ID),
                    					 		   new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeDefine.GROUP_NO_PORT_PERSONAL_ID)));
                     }
                     if(charValue == null)
                     { 
                         continue;
                     }
                     prodSalCmp.buildGroupPromRdl(coProd,tempMap,charValue);
                	 
                 }else if( coProd.getProdTypeId() == EnumCodeDefine.PROD_TYPE_GROUP_2){
                	 
                	 prodSalCmp.buildGroupPromRdl(coProd,tempMap,null);
                 
                 }else if(coProd.getProdTypeId() == EnumCodeDefine.PROD_TYPE_ACCT){
                	 prodSalCmp.buildSAcctPromRdl(coProd,tempMap);
                 } 
        	 }
         }
       //产品周期表
         List<CoProdBillingCycle> coProdBillCycles = context.getAllCacheList(CoProdBillingCycle.class);
         if (CommonUtil.isNotEmpty(coProdBillCycles))
         {
        	 prodSalCmp.buidlSPromBillCycleRdl(coProdBillCycles, tempMap);
         }
         
         
         //用户服务
         List<CmResServCycle> resServCycleList = context.getAllCacheList(CmResServCycle.class);
         if (CommonUtil.isNotEmpty(resServCycleList))
         {
        	 prodSalCmp.buildSUserServiceStatusRdl(resServCycleList, tempMap);
         }
         
         
         //gaoqc5 2013-2-21 亲情号码上发不需关联产品
         List<CoFnCharValue> coFnCharValues = context.getAllCacheList(CoFnCharValue.class);
         if (CommonUtil.isNotEmpty(coFnCharValues))
         {
        	 prodSalCmp.buildSUserRelaRdl(coFnCharValues, tempMap);
         }
         
         // 二次议价参数
         List<CoProdPriceParam> coProdPriceParams=null;
         if(context.isDiffException()){
        	 RouterCmp baseCmp = context.getCmp(RouterCmp.class);
    		 Long acctId=baseCmp.queryAcctIdByUserId(context.getUserId());
        	 ITableUtil.setValue2ContextHolder(null, acctId, null);
        	 coProdPriceParams= DBUtil.query(CoProdPriceParam.class,new DBCondition(CoProdPriceParam.Field.objectId, context.getUserId()));
         }else{
        	 coProdPriceParams= context.getAllCacheList(CoProdPriceParam.class);
         }
         
         if (CommonUtil.isNotEmpty(coProdPriceParams))
         {
        	 prodSalCmp.buildSPromPriceParamRdl(coProdPriceParams, tempMap);
         }
         
         // 账户分账信息表
         List<CoSplitCharValue> coSplitCharValues = context.getAllCacheList(CoSplitCharValue.class);
         prodSalCmp.buildSAcctSplitPayRdl(coSplitCharValues, tempMap);
         
         //预算
         List<CoBudgetCharValue> coBudgetCharValue = context.getAllCacheList(CoBudgetCharValue.class);
         prodSalCmp.buildBudgetInfoRdl(coBudgetCharValue, tempMap);

         
         List<CoProdCellInfo> cellInfoList = context.getAllCacheList(CoProdCellInfo.class);
         if (CommonUtil.isNotEmpty(cellInfoList))
         {
        	 prodSalCmp.buildSUserHomeRdl(cellInfoList, tempMap);
         }
         
         // 特征值对象
         List<CoProdCharValue> coProdCharValues=null;
         if(context.isDiffException()){
    		 RouterCmp baseCmp = context.getCmp(RouterCmp.class);
    		 Long acctId=baseCmp.queryAcctIdByUserId(context.getUserId());
        	 ITableUtil.setValue2ContextHolder(null, acctId, null);
        	 coProdCharValues=DBUtil.query(CoProdCharValue.class,new DBCondition(CoProdCharValue.Field.objectId, context.getUserId()));
         }else{
             coProdCharValues = context.getAllCacheList(CoProdCharValue.class);
         }
         if (CommonUtil.isNotEmpty(coProdCharValues))
         {
             //先按产品id分组
             Map<Long,List<? extends DataObject>> map = MdbUtil.getMapByProductIds(coProdCharValues);
             
             for(Entry<Long,List<? extends DataObject>> en:map.entrySet())
             {
                 Long productId = en.getKey();
                 CoProd coProd = context.getSingleCache(CoProd.class, 
                         new CacheCondition(CoProd.Field.productId,productId));
                 if(coProd == null)
                 {
                     coProd = DBUtil.querySingle(CoProd.class,new DBCondition(CoProd.Field.productId,productId));
                 }
                 if(coProd == null)
                 { 
                     continue;
                 }
                 //按group_id分组
                 //@Date 2013-09-10 yugb :wukl手工上发不填产品编号时，导致特征值重复
                 Map<Long,List<? extends DataObject>> groupMap = MdbUtil.getMapByGroupIds(en.getValue());
                 for(List<? extends DataObject> list:groupMap.values())
                 {
                     // 按so_nbr分组
                     Map<Long,List<? extends DataObject>> soNbrMap = MdbUtil.getMapBySoNbr(list);
                     for(List l:soNbrMap.values())
                     {
                         List<CoProdCharValue> charValues = (List<CoProdCharValue>)l;
                         if (coProd.getBusiFlag() == SpecCodeDefine.INTER_GROUP_PROMOTION)
                         {
                             // 群间关系CGroupRela
                        	 prodSalCmp.buildSGroupRelaRdl(charValues, coProd.getBusiFlag(), tempMap);
                         }
                         else if (coProd.getBusiFlag() == SpecCodeDefine.OUTNET_PHONE)
                         {
                             // 群特殊号码产品关系
                        	 prodSalCmp.buildSGroupRelaRdl(charValues, coProd.getBusiFlag(), tempMap);
                         }
                         else
                         {
                        	 prodSalCmp.buildSPromCharValueRdl(charValues, coProd, tempMap);
                         }
                     }
                 }
                 }
             }
             //全部放入返回的结果中
             if(CommonUtil.isNotEmpty(tempMap)){
                 for(Entry<String, ListMapMdbRdl> entry:tempMap.entrySet()){
                     result.add(entry.getValue());
             }
         }
    }


}
