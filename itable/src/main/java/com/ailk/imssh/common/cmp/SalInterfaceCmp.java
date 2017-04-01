package com.ailk.imssh.common.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.tools.Json;

import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sal.exceptions.SALException;
import com.ailk.easyframe.sal.exchange.SalResponse;
import com.ailk.easyframe.sal.route.bean.MdbRoute;
import com.ailk.easyframe.sal.util.SalUtil;
import com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SRatingAdjust;
import com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SRatingAdjustRet;
import com.ailk.easyframe.sdl.MAbmRdlCommonDef.SAbmBalanceQueryRet;
import com.ailk.easyframe.sdl.MAbmRdlCommonDef.SAbmBalanceQueryUp;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmAssetTransfer;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmAssetTransferRet;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmFreeRes;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmOtFreeRes;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmTransCleanRet;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmTransCleanUp;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmTransQueryRet;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmTransQueryUp;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmTransUpdateRet;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmTransUpdateUp;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAlarmInfo;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SBudget;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SBudgetNotifyStatus;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SCapMax;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SFreeresLimit;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SFreeresSharelog;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SNonAssetFreeDtl;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SNonAssetRiskDtl;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SRatingAccumulate;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SSlaAccumulate;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.STransParam;
import com.ailk.easyframe.sdl.MAbmRdlImsDef.SVersion;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.ims.abmtransfer.commom.ErrorCode;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;

public class SalInterfaceCmp extends BaseCmp {
	private SalInterfaceCmp() {

	}

	/**
	 * 携号跨区资产迁移
	 * 
	 * @param userId
	 * @param acctId
	 * @param regionCode
	 * @param orgRegionCode
	 * @return
	 */
	public SAbmAssetTransferRet transferMdbAssetByChangeRegionCode(Long userId, Long acctId, int regionCode, int orgRegionCode) {
		return transferMdbAsset(userId, acctId, acctId, regionCode, orgRegionCode, EnumCodeExDefine.ABM_TRANSFER_TYPE_ACCT);
	}

	/**
	 * 过户资产迁移 lijc3 2013-8-26
	 */
	public SAbmAssetTransferRet transferMdbAssetByChangeOwner(Long userId, Long oldAcctId, Long newAcctId) {
		RouteResult rst = context.getCmp(RouterCmp.class).queryUserRouter(userId);
		int regionCode = rst.getRouteDimension().getRegionCode();
		return transferMdbAsset(userId, newAcctId, oldAcctId, regionCode, regionCode, EnumCodeExDefine.ABM_TRANSFER_TYPE_USER);
	}

	/**
	 * 资产迁移接口
	 * 
	 * @param servId
	 * @param acctId
	 * @param orgAcctId
	 * @param regionCode
	 * @param orgRegionCode
	 * @param transferType
	 * @return
	 */
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

	/***
	 * 查询资产通用接口,账户编号不能为0
	 * 
	 * @param acctId
	 * @param onwerId
	 * @param ownerType
	 * @return
	 */
	public SAbmBalanceQueryRet commonQueryAsset(long acctId, long onwerId, int ownerType) {
		SalClient client = SpringUtil.getSalClient();
		SAbmBalanceQueryUp queryUp = new SAbmBalanceQueryUp();
		SAbmBalanceQueryRet queryRst = new SAbmBalanceQueryRet();
		queryRst.set_succFlag(-1);
		queryUp.set_ownerId(onwerId);
		queryUp.set_acctId(acctId);
		queryUp.set_ownerType((short) ownerType);
		try {
			if (ITableUtil.isMdbCloud()) {
				MdbRoute route = context.getCmp(RouterCmp.class).getAccountRoute(acctId);
				com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmBalanceQueryUp> entry2 = new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmBalanceQueryUp>(
						route, queryUp);
				List<com.ailk.easyframe.sal.mapreduce.Entry> list2 = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry>();
				list2.add(entry2);
				queryRst = client.get(MdbKVDefine.ABM_COMMON_QUERY, list2, queryRst);
			} else {
				queryRst = client.get(MdbKVDefine.ABM_COMMON_QUERY, queryUp, queryRst);
			}
		} catch (SALException e) {
			IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED, "查询资产接口出错,SAL接口异常");
		}
		if (queryRst.get_succFlag() == -1) {
			IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED, "查询资产接口出错,返回-1");
		}
		return queryRst;
	}

	public void adjustOtFreeRes(CsdlArrayList<com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes> adjustList) {
		SalClient client = SpringUtil.getSalClient();
		SRatingAdjust adjust = new SRatingAdjust();
		adjust.set_otFreeresList(adjustList);
		SRatingAdjustRet ret = new SRatingAdjustRet();

		if (ITableUtil.isMdbCloud()) {
			MdbRoute route = context.getCmp(RouterCmp.class).getAccountRoute(adjustList.get(0).get_acctId());
			com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SRatingAdjust> entry2 = new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SRatingAdjust>(
					route, adjust);
			List<com.ailk.easyframe.sal.mapreduce.Entry> list2 = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry>();
			list2.add(entry2);
			client.post(MdbKVDefine.ABM_ADJUST, list2, ret);
		} else {
			client.post(MdbKVDefine.ABM_ADJUST, adjust, ret);
		}
	}

	public void transferAps(long servId, long acctId, long orgAcctId, int regionCode, int orgRegionCode,int transFlag) {
		/*
		SalClient client = SpringUtil.getSalClient();
		SRasBillTransUp transUp = new SRasBillTransUp();
		SRasBillTransRet ret = new SRasBillTransRet();
		com.ailk.easyframe.sdl.MRasImsDef.STransParam sTransParam = new com.ailk.easyframe.sdl.MRasImsDef.STransParam();
		sTransParam.set_acctId(acctId);
		sTransParam.set_orgAcctId(acctId);
		sTransParam.set_orgRegionCode(orgRegionCode);
		sTransParam.set_regionCode(regionCode);
		sTransParam.set_servId(servId);
		
		sTransParam.set_transFlag(transFlag);

		transUp.set_transParam(sTransParam);

		if (ITableUtil.isMdbCloud()) {
			MdbRoute route = context.getCmp(RouterCmp.class).getAccountRoute(acctId);
			com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SRasBillTransUp> entry2 = new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SRasBillTransUp>(
					route, transUp);
			List<com.ailk.easyframe.sal.mapreduce.Entry> list2 = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry>();
			list2.add(entry2);
			ret = client.get(MdbKVDefine.APS_TRANSFER, list2, ret);
		} else {
			ret = client.get(MdbKVDefine.APS_TRANSFER, transUp, ret);
		}
		*/

	}
	
	private static int CLEAN_ABM_ASSET_FAIL = 320005;// 该错误编码在sal服务端有定义
	
	public int transferMdbAsset(Long userId, Long acctId, Long orgAcctId, int regionCode, int orgRegionCode, int transType, Date validDate,
			boolean isImmediate) {
		int retVal = 0;
		RouterCmp routerCmp = context.getCmp(RouterCmp.class);
		SalClient client = SpringUtil.getSalClient();
		SAbmAssetTransfer updateUp = new SAbmAssetTransfer();
		SAbmAssetTransferRet updateRet = new SAbmAssetTransferRet();

		STransParam sTransParam = new STransParam();
		sTransParam.set_acctId(acctId);
		sTransParam.set_orgAcctId(orgAcctId);
		sTransParam.set_orgRegionCode(orgRegionCode);
		sTransParam.set_regionCode(regionCode);
		sTransParam.set_servId(userId);
		sTransParam.set_transType(transType);
		updateUp.set_transParam(sTransParam);
		updateRet.set_succFlag(-1);
		try {
			long startTime = System.currentTimeMillis();
			RouteResult m1 = null;// 老的路由
			RouteResult m2 = null;// 按用户查询最新路由的帐户

			String verticalValue = SalUtil.getDomainName(MdbKVDefine.TRANSFERASSETTABLES);

			// 过户的时候迁移资产路由是查询的老账户路由和新账户路由
			if (transType == EnumCodeExDefine.ABM_TRANSFER_TYPE_USER) {
				m1 = routerCmp.queryAcctMdbRout(verticalValue, orgAcctId);// 老账户的路由
				m2 = routerCmp.queryUserRouter(userId);// 用户对应当前的路由
				// 校验路由的帐户和新帐户是否一致
				if (acctId.longValue() != m2.getRouteDimension().getAccountId()) {
					imsLogger.error("the route acctId of servId is not equal input acctId, transferMdbAsset return fail.");
					throw new BusinessException(ErrorCode.ROUTE_ACCTID_WRONG);
				}
				if (m2.getRouteDimension().getResourceVersion() == 0) {
					imsLogger.error("the route version of servId is equal zero, so transferMdbAsset can't continue.");
					throw new BusinessException(ErrorCode.ROUTE_VERSION_EMPTY);
				}
			}

			SVersion updateVersion = new SVersion();
			updateVersion.set_versionType(transType);
			if (transType == EnumCodeExDefine.ABM_TRANSFER_TYPE_USER) {
				updateVersion.set_versionKey(userId);
				updateVersion.set_versionSeq(m2.getRouteDimension().getResourceVersion());
			} else {
				updateVersion.set_versionKey(acctId);
				updateVersion.set_versionSeq(m2.getVersion());
			}
			CsdlArrayList<SVersion> updateVersionList = new CsdlArrayList<SVersion>(SVersion.class);
			updateVersionList.add(updateVersion);

			updateUp.set_versionList(updateVersionList);

			if (routerCmp.isRouteToSameMdb(m1.getRouteDimension().getAccountId(), m2.getRouteDimension().getAccountId())) {
				imsLogger.debug("the orgAcctId and acctId are same mdb, so call TransferSingleMdbAsset interface.");
				updateRet = this.transferSingleMdbAsset(updateUp, m2);

			} else {
				imsLogger.debug("the orgAcctId and acctId are not different mdb, so need delete one,insert another MDB.");
				SAbmTransQueryRet sAbmTransQueryRet = new SAbmTransQueryRet();
				SAbmTransQueryUp sAbmTransQueryUp = new SAbmTransQueryUp();
				
				SVersion queryVersion = new SVersion();
				queryVersion.set_versionType(transType);
				if (transType == EnumCodeExDefine.ABM_TRANSFER_TYPE_USER) {
					queryVersion.set_versionKey(userId);
					queryVersion.set_versionSeq(m1.getRouteDimension().getResourceVersion());
				} else {
					queryVersion.set_versionKey(acctId);
					queryVersion.set_versionSeq(m1.getVersion());
				}
				CsdlArrayList<SVersion> queryVersionList = new CsdlArrayList<SVersion>(SVersion.class);
				queryVersionList.add(queryVersion);				
				sAbmTransQueryUp.set_transParam(sTransParam);
				sAbmTransQueryUp.set_versionList(queryVersionList);
				sAbmTransQueryRet = this.queryTransferAsset(sAbmTransQueryUp, m1);
				if (sAbmTransQueryRet.get_succFlag() == 0) {
					// 查询成功时，调用资产更新接口新增一份资产数据
					SAbmTransUpdateRet sAbmTransUpdateRet = new SAbmTransUpdateRet();
					SAbmTransUpdateUp sAbmTransUpdateUp = new SAbmTransUpdateUp();
					// 把查询返回结果的账户设置为目标帐户号，组织为更新入参
					convertToSAbmTransUpdateUp(sAbmTransQueryRet, acctId, sAbmTransUpdateUp, transType);
					sAbmTransUpdateUp.set_versionList(updateVersionList);
					sAbmTransUpdateUp.set_transParam(sTransParam);
					sAbmTransUpdateRet = this.transferAssetTables(sAbmTransUpdateUp, m2);

					//清理返回
					SAbmTransCleanRet sAbmTransCleanRet = new SAbmTransCleanRet();
					SAbmTransCleanUp sAbmTransCleanUp = new SAbmTransCleanUp();
					sAbmTransCleanUp.set_transParam(sTransParam);

					if (sAbmTransUpdateRet.get_succFlag() == 0) {
						// 更新成功时，调用资产清理接口
						try {
							sAbmTransCleanUp.set_unlockFlag((short) 0);// 0：正常清理，
																		// 1：仅解锁接口
							sAbmTransCleanRet = this.cleanTransferAsset(sAbmTransCleanUp, m1);
						} catch (SALException e) {
							sAbmTransCleanRet.set_succFlag(-1);
							imsLogger.error("更新成功时，调用资产清理接口失败：", e);
						}

						if (sAbmTransCleanRet.get_succFlag() == 0) {
							// 正常清理成功
							imsLogger.debug("call normal CleanTransferAsset success, SAbmAssetTransfer complete.");
							updateRet.set_succFlag(sAbmTransCleanRet.get_succFlag());
						} else {
							// 正常清理失败，则返回特殊错误码
							imsLogger.info("call normal CleanTransferAsset fail, SAbmAssetTransfer complete, return a special error code.");
							updateRet.set_succFlag(CLEAN_ABM_ASSET_FAIL);
						}
					} else {
						// 更新失败时，调用资产清理接口，仅解锁原资产
						try {
							sAbmTransCleanUp.set_unlockFlag((short) 1);// 0：正常清理，
																		// 1：仅解锁接口
							sAbmTransCleanRet = this.cleanTransferAsset(sAbmTransCleanUp, m1);
						} catch (SALException e) {
							imsLogger.error("更新失败时，调用资产清理接口，解锁原资产失败：", e);
						}
						updateRet.set_succFlag(sAbmTransUpdateRet.get_succFlag());// 设置为最后一次失败的错误码
					}

				} else {
					// 查询失败时，返回资产迁移失败
					updateRet.set_succFlag(sAbmTransQueryRet.get_succFlag());
				}

			}
			client.commit();
			long endTime = System.currentTimeMillis();
			imsLogger.debug("[SalInterface.transferMdbAsset] cost:" + (endTime - startTime));
		} catch (SALException e) {
			imsLogger.error("[SalInterface.transferMdbAsset] Passing SAL. Error", e);
			// throw new
			// BusinessException(ErrorCode.TRANSFER_MDB_ASSET_ERR,e.getMessage());XXX
			updateRet.set_succFlag(-1);
			throw new IMSException(e.getLocalizedMessage());
		}
		retVal = updateRet.get_succFlag();
		if (retVal == 0) {
			imsLogger.debug("transferMdbAsset success");
		}

		imsLogger.debug(Json.serialize(updateRet));

		return retVal;
	}
	
	/**
	 * 单MDB资产迁移接口
	 * 
	 * @param singleMdbAsset
	 * @return
	 */
	private SAbmAssetTransferRet transferSingleMdbAsset(SAbmAssetTransfer singleMdbAsset, MdbRoute mdbRoute) {
		imsLogger.debug("transferSingleMdbAsset params:");
		imsLogger.debug(Json.serialize(singleMdbAsset));
		SalClient client = SpringUtil.getSalClient();

		SAbmAssetTransferRet retUpdate = new SAbmAssetTransferRet();
		// try {
		List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmAssetTransfer>> inList = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmAssetTransfer>>();
		inList.add(new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmAssetTransfer>(mdbRoute, singleMdbAsset));
		List<SalResponse<SAbmAssetTransfer, SAbmAssetTransferRet>> list = client
				.post(MdbKVDefine.TRANSFERSINGLEMDBASSET, inList, retUpdate);
		if (null != list && !list.isEmpty() && list.get(0).getOut() != null) {
			retUpdate = list.get(0).getOut();
		} else {
			retUpdate.set_succFlag(-1);
		}
		// } catch (SALException e) {
		// log.error("[SalInterface.transferSingleMdbAsset] Passing SAL. Error",
		// e);
		// throw new
		// BusinessException(ErrorCode.COMMON_FREEZE_ABM_TABLES_ERROR,e.getMessage());
		// }
		if (retUpdate.get_succFlag() == 0) {
			imsLogger.debug("transferSingleMdbAsset success");
		}
		return retUpdate;
	}
	
	/**
	 * 资产迁移查询接口
	 * 
	 * @param queryUp
	 * @return
	 */
	private SAbmTransQueryRet queryTransferAsset(SAbmTransQueryUp queryUp, MdbRoute mdbRoute) {

		imsLogger.debug("queryTransferAsset params:");
		imsLogger.debug(Json.serialize(queryUp));

		SalClient client = SpringUtil.getSalClient();

		SAbmTransQueryRet queryRet = new SAbmTransQueryRet();
		// try {
		long startTime = System.currentTimeMillis();

		List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransQueryUp>> inList = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransQueryUp>>();
		inList.add(new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransQueryUp>(mdbRoute, queryUp));
		List<SalResponse<SAbmTransQueryUp, SAbmTransQueryRet>> salResponse = client.post(MdbKVDefine.QUERYTRANSFERASSET, inList, queryRet);

		long endTime = System.currentTimeMillis();
		imsLogger.debug("[SalInterface.queryTransferAsset] cost:" + (endTime - startTime));
		// } catch (SALException e) {
		// log.error("[SalInterface.queryTransferAsset] Passing SAL. Error", e);
		// throw new
		// BusinessException(ErrorCode.COMMON_QUERY_ABM_TABLES_ERROR,e.getMessage());
		// }
		if (CommonUtil.isNotEmpty(salResponse) && salResponse.get(0) != null) {
			queryRet = salResponse.get(0).getOut();
		} else {
			queryRet.set_succFlag(-1);
		}
		if (queryRet.get_succFlag() == 0) {
			imsLogger.debug("queryTransferAsset success");
		}

		imsLogger.debug(Json.serialize(queryRet));

		return queryRet;
	}
	
	/**
	 * 把查询返回结果的账户设置为目标帐户号
	 * 
	 * @param sAbmTransQueryRet
	 * @param get_acctId
	 * @param sAbmTransUpdateUp
	 */
	private void convertToSAbmTransUpdateUp(SAbmTransQueryRet b, long acctId, SAbmTransUpdateUp a, int transType) {

		for (SAlarmInfo src : b.get_transAsset().get_alarmInfoList()) {
			SAlarmInfo dest = new SAlarmInfo();
			dest.copy(src);
			a.get_transAsset().get_alarmInfoList().add(dest);
		}
		for (SBudget src : b.get_transAsset().get_budgetList()) {
			SBudget dest = new SBudget();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_budgetList().add(dest);
		}
		for (SBudgetNotifyStatus src : b.get_transAsset().get_budgetStatusList()) {
			SBudgetNotifyStatus dest = new SBudgetNotifyStatus();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_budgetStatusList().add(dest);
		}
		for (SAbmFreeRes src : b.get_transAsset().get_freeresList()) {
			SAbmFreeRes dest = new SAbmFreeRes();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_freeresList().add(dest);
		}
		for (SAbmOtFreeRes src : b.get_transAsset().get_otfreeresList()) {
			SAbmOtFreeRes dest = new SAbmOtFreeRes();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_otfreeresList().add(dest);
		}
		for (SRatingAccumulate src : b.get_transAsset().get_accumulateList()) {
			SRatingAccumulate dest = new SRatingAccumulate();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_accumulateList().add(dest);
		}
		for (SSlaAccumulate src : b.get_transAsset().get_slaAccumulateList()) {
			SSlaAccumulate dest = new SSlaAccumulate();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_slaAccumulateList().add(dest);
		}
		for (SCapMax src : b.get_transAsset().get_capmaxList()) {
			SCapMax dest = new SCapMax();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_capmaxList().add(dest);
		}
		for (SFreeresLimit src : b.get_transAsset().get_freeresLimitList()) {
			SFreeresLimit dest = new SFreeresLimit();
			dest.copy(src);
			dest.set_acctId(acctId);
			a.get_transAsset().get_freeresLimitList().add(dest);
		}
		for (SNonAssetFreeDtl src : b.get_transAsset().get_nonAssetFreeDtlList()) {
			SNonAssetFreeDtl dest = new SNonAssetFreeDtl();
			dest.copy(src);
			a.get_transAsset().get_nonAssetFreeDtlList().add(dest);
		}
		for (SNonAssetRiskDtl src : b.get_transAsset().get_nonAssetRiskDtlList()) {
			SNonAssetRiskDtl dest = new SNonAssetRiskDtl();
			dest.copy(src);
			a.get_transAsset().get_nonAssetRiskDtlList().add(dest);
		}
		for (SFreeresSharelog src : b.get_transAsset().get_freeresLogList()) {
			SFreeresSharelog dest = new SFreeresSharelog();
			dest.copy(src);
			a.get_transAsset().get_freeresLogList().add(dest);
		}
		for (SAbmFreeRes src : b.get_transAsset().get_freeresList()) {
			SAbmFreeRes dest = new SAbmFreeRes();
			dest.copy(src);
			//a.get_transAsset().get_freeresInfoList().add(dest);
			a.get_transAsset().get_freeresList().add(dest);
		}
		// 携号跨区还需要迁移剩下的表
		if (transType == 1) {
			a.get_transAsset().set_creditList(b.get_transAsset().get_creditList());
			a.get_transAsset().set_confirmRecordList(b.get_transAsset().get_confirmRecordList());
			a.get_transAsset().set_notificationStatiList(b.get_transAsset().get_notificationStatiList());
			a.get_transAsset().set_notificationStatusList(b.get_transAsset().get_notificationStatusList());
			a.get_transAsset().set_notificationRecordList(b.get_transAsset().get_notificationRecordList());
			a.get_transAsset().set_splitDeductList(b.get_transAsset().get_splitDeductList());
		}
		//调用set方法
		a.set_transAsset(a.get_transAsset());
	}
	
	/**
	 * 资产迁移更新接口
	 * 
	 * @param transferAsset
	 * @return
	 */
	private SAbmTransUpdateRet transferAssetTables(SAbmTransUpdateUp transferAsset, MdbRoute mdbRoute) {
		imsLogger.debug("transferAssetTables params:");
		imsLogger.debug(Json.serialize(transferAsset));
		int retVal = 0;
		SalClient client = SpringUtil.getSalClient();

		SAbmTransUpdateRet retUpdate = new SAbmTransUpdateRet();
		// try {
		List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransUpdateUp>> inList = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransUpdateUp>>();
		//List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransUpdateUp>> inList = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransUpdateUp>>();
		inList.add(new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransUpdateUp>(mdbRoute, transferAsset));
		imsLogger.debug("ABM资产迁移入新mdb参数:inList", Json.serialize(inList));
		List<SalResponse<SAbmTransUpdateUp, SAbmTransUpdateRet>> list = client.post(MdbKVDefine.TRANSFERASSETTABLES, inList, retUpdate);
		if (null != list && !list.isEmpty() && list.get(0).getOut() != null) {
			retUpdate = list.get(0).getOut();
		} else {
			retUpdate.set_succFlag(-1);
		}
		// } catch (SALException e) {
		// log.error("[SalInterface.transferAssetTables] Passing SAL. Error",
		// e);
		// throw new
		// BusinessException(ErrorCode.COMMON_FREEZE_ABM_TABLES_ERROR,e.getMessage());
		// // e.printStackTrace();
		// }
		retVal = retUpdate.get_succFlag();
		if (retVal == 0) {
			imsLogger.debug("transferAssetTables success");
		}
		return retUpdate;
	}
	
	/**
	 * 资产迁移清理接口
	 * 
	 * @param sCleanRet
	 * @return
	 */
	private SAbmTransCleanRet cleanTransferAsset(SAbmTransCleanUp sCleanRet, MdbRoute mdbRoute) {
		imsLogger.debug("cleanTransferAsset params:");
		imsLogger.debug(Json.serialize(sCleanRet));
		int retVal = 0;
		SalClient client = SpringUtil.getSalClient();

		SAbmTransCleanRet retUpdate = new SAbmTransCleanRet();
		// try {
		List<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransCleanUp>> inList = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransCleanUp>>();
		inList.add(new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmTransCleanUp>(mdbRoute, sCleanRet));
		List<SalResponse<SAbmTransCleanUp, SAbmTransCleanRet>> list = client.post(MdbKVDefine.CLEANTRANSFERASSET, inList, retUpdate);
		if (null != list && !list.isEmpty() && list.get(0).getOut() != null) {
			retUpdate = list.get(0).getOut();
		} else {
			retUpdate.set_succFlag(-1);
		}
		// } catch (SALException e) {
		// log.error("[SalInterface.cleanTransferAsset] Passing SAL. Error", e);
		// throw new
		// BusinessException(ErrorCode.COMMON_FREEZE_ABM_TABLES_ERROR,e.getMessage());
		// }
		retVal = retUpdate.get_succFlag();
		if (retVal == 0) {
			imsLogger.debug("cleanTransferAsset success");
		}
		return retUpdate;
	}
}
