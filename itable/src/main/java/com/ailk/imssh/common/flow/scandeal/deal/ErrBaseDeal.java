package com.ailk.imssh.common.flow.scandeal.deal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jef.database.AbstractDbClient;
import jef.database.Batch;
import jef.database.DataObject;
import jef.database.Field;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.easyframe.sdl.MAbmRdlImsDef.SAbmAssetTransferRet;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncIvrAllInfo;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.easyframe.web.common.session.RequestContext;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.manualmdb.ManualModifyCreator;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.commit.ItableListenerServiceFlow;
import com.ailk.imssh.common.commit.ItableTransListener;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.flow.bean.IndexHandlerObject;
import com.ailk.imssh.common.flow.bean.SyncMdbInfoBean;
import com.ailk.imssh.common.flow.config.ErrDataIndexConfig;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.error.bean.ErrorHolder;
import com.ailk.imssh.common.flow.error.bean.ErrorListQueue;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.init.ITableUpfieldInitBean;
import com.ailk.imssh.common.mdb.ErrorMsgHolder;
import com.ailk.imssh.common.mdb.ItableSalMdbBean;
import com.ailk.imssh.common.util.CommonOperTypeComparator;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserAcctRelCmp;
import com.ailk.imssh.user.cmp.UserStsCmp;
import com.ailk.imssh.user.helper.UserAcctRelHelper;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexErr;
import com.ailk.openbilling.persistence.itable.entity.ISyncMdbParam;
import com.ailk.openbilling.persistence.itable.entity.IUserAcctRel;

/**
 * @Description:索引表处理类
 * @author wangjt
 * @Date 2012-9-13
 */
public class ErrBaseDeal implements IBaseDeal {
	private ImsLogger imsLogger = new ImsLogger(ErrBaseDeal.class);

	public String getErrorMsg(String errorMsg) {
		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				// @2012-10-22 wukl 排除信息中包含中文，字段过长问题
				errorMsg = errorMsg.length() < EnumCodeExDefine.ERROR_MSG_LENGTH ? errorMsg.substring(0,
						errorMsg.length()) : errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
				if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
					errorMsg = errorMsg
							.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH * 2 - errorMsg.getBytes().length);
				}
			}
		}
		if (CommonUtil.isEmpty(errorMsg)) {
			return " ";
		}
		return errorMsg;
	}

	@Override
	@Transactional
	public void moveToErrTable(ErrorHolder errorHolder, Date dealDate, Long errorCode, String errorMsg, IConfig config)
			throws Exception {
		long t1 = System.currentTimeMillis();
		imsLogger.debug(" start to move ", errorHolder.getSubTableName(), " to ERR table", "busi_value = ",
				errorHolder.getBusiValue());
		try {
			AbstractDbClient dbClient = DBUtil.getDBClient();
			DataObject dataObject = errorHolder.getDataObject();

			// 增加到错误表
			dbClient.insert(config.convertToErr(dataObject, dealDate, errorCode, errorMsg));

			// 删除小索引表数据
			if (!errorHolder.isMdbCommitFail()) {
				imsLogger.debug("***not commit mdb fail ,need delete sub table");
				dbClient.delete(config.getDeleteDataObjectSub(dataObject), errorHolder.getSubTableName());
			}
		} catch (Exception e) {
			if (e instanceof SQLException) {
				imsLogger.error(("SQL error at:" + ((SQLException) e).getSQLState()), e);
			}
			IMSUtil.throwBusiException(e);
		}

		imsLogger.debug(new Object[]{t1, " end to move ", errorHolder.getSubTableName(), " to ERR table "});
	}

	@Transactional
	public void dealWhenFail(DataHolder dataHolder, Date dealDate, Long errorCode, String errorMsg, IConfig config)
			throws Exception {
		DataObject dataObject = dataHolder.getDataObject();

		// 2 把错误信息更新到错误通知表I_DATA_INDEX_Err
		ErrDataIndexConfig errConfig=(ErrDataIndexConfig)config;
		String errMsg = errConfig.getErrMsg(errorMsg);
		IDataIndexErr errDataIndex=(IDataIndexErr) dataObject;
		IDataIndexErr idiErr= new IDataIndexErr();
		idiErr.setErrorCode(errorCode);
		idiErr.setErrorMsg(errMsg);
		idiErr.setDealDate(dealDate);
		try {
			DBUtil.updateByCondition(idiErr, new DBCondition(IDataIndexErr.Field.soNbr, errDataIndex.getSoNbr()));
			imsLogger.error("===============End to deal Result:failed,data remained in err table=============== ");
		} catch (Exception e) {
 			IMSUtil.throwBusiException(e);
		}

	}

	@Transactional
	public void dealProcess(DataHolder dataHolder, ITableContext context, IConfig config) throws Exception {
		long t1 = System.currentTimeMillis();
		imsLogger.debug(" start to deal ", dataHolder.getSubTableName());
		try {
			AbstractDbClient dbClient = DBUtil.getDBClient();

			// 执行一个Err工单
			DataObject dataObject = dataHolder.getDataObject();
//			Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);

			// sts == 0 时，表示需要做数据入库动作
//			if (dbSuccSts == FlowConst.DB_SUCC_STS_FALSE) {
				// 1、处理具体业务
				this.dealDB(context, dataObject, config, dbClient);

//			}
			// 3、上发mdb,成功后移入his表
			this.dealMdb(context, dataHolder, config, dbClient);
			// 4、销户删除路由相关
			context.getCmp(UserStsCmp.class).removeRouter4Termi();
		} catch (Exception e) {
			if (e instanceof SQLException) {
				imsLogger.error(("SQL error at:" + ((SQLException) e).getSQLState()), e);
			}
			IMSUtil.throwBusiException(e);
		}

		imsLogger.debug(new Object[]{t1, " end to deal ", dataHolder.getSubTableName(), "busi_value = ", dataHolder.getBusiValue()});
	}

	private void dealDB(ITableContext context, DataObject dataObject, IConfig config, AbstractDbClient dbClient)
			throws Exception {
		IDataIndex iDataIndex = config.convertToIDataIndex(dataObject);

		Long soNbr = iDataIndex.getSoNbr();
		context.setiDataIndex(iDataIndex);
		context.setSeqId(config.getValueOfSeqId(dataObject));

		context.setRequestDate(iDataIndex.getCommitDate());// 修改requestDate的值，以便在上发mdb时使用
		// 使用doneCode，后续业务都是使用doneCode
		context.setDoneCode(iDataIndex.getDoneCode());
		// 往context里面设置分表字段
		RequestContext requestContext = ContextHolder.getRequestContext();

		requestContext.put(ConstantExDefine.ROUTER_KEY_COMMIT_DATE,
				DateUtil.getFormatDate(iDataIndex.getCommitDate(), DateUtil.DATE_FORMAT_YYYYMMDD));

		Map<IndexHandlerObject, List<? extends DataObject>[]> indexHandlerObjectMap = new LinkedHashMap<IndexHandlerObject, List<? extends DataObject>[]>();
		// 将当前I表数据全部存下。
		context.setIndexHandlerObjectMap(indexHandlerObjectMap);
		// 数据入信管表
		String upFieldNew = "0" + iDataIndex.getUpField();
		for (int index = 1; index < upFieldNew.length(); index++)// 跳过第一位
		{
			if (upFieldNew.charAt(index) != '1') {
				continue;
			}
			IndexHandlerObject indexHandlerObject = ITableUpfieldInitBean.getIndexHandlerObjectByIndex(index);
			if (indexHandlerObject == null) {
				throw IMSUtil.throwBusiException("upfield值错误：第" + index + "位不可以为1");
			}
			// Class<? extends BaseHandler> handlerClass =
			// indexHandlerObject.getHandler();
			List<Class<? extends DataObject>> itableClassList = indexHandlerObject.getItableClassList();

			List<? extends DataObject>[] paramArr = (List<? extends DataObject>[]) new List[itableClassList.size()];

			for (int i = 0; i < itableClassList.size(); i++) {
				Class<? extends DataObject> itableClass = itableClassList.get(i);
				Field jefField = DBUtil.getJefField(itableClass, "soNbr");

				DataObject cond = itableClass.newInstance();
				cond.getQuery().addCondition(jefField, soNbr);
				// 获取具体I表数据表数据
				List<DataObject> dataArr = dbClient.select(cond);
				// List<DataObject> dataArr = DBUtil.query(itableClass, new
				// DBCondition(jefField, soNbr));

				if (dataArr.size() > 1) {
					// @Date 2012-08-11 wukl
					// 将查询的数据根据opertype按照删除>修改>新增（3>2>1）来排序
					// 考虑到下周期生效的数据，先执行修改、后新增下周期生效的数据
					Collections.sort(dataArr, new CommonOperTypeComparator<DataObject>());
				}

				paramArr[i] = dataArr;
			}
			indexHandlerObjectMap.put(indexHandlerObject, paramArr);
		}
		// 设置分表参数
		if (iDataIndex.getAcctId() != null) {
			requestContext.put(ConstantExDefine.ROUTER_KEY_ACCT, iDataIndex.getAcctId());
			requestContext.remove(ConstantExDefine.ROUTER_KEY_CUST);
		} else {
			requestContext.put(ConstantExDefine.ROUTER_KEY_CUST, iDataIndex.getCustId());
			requestContext.remove(ConstantExDefine.ROUTER_KEY_ACCT);
		}

		Iterator<Entry<IndexHandlerObject, List<? extends DataObject>[]>> it = indexHandlerObjectMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<IndexHandlerObject, List<? extends DataObject>[]> next = it.next();
			IndexHandlerObject indexHandlerObject = next.getKey();
			Class<? extends BaseHandler> handlerClass = indexHandlerObject.getHandler();
			List<? extends DataObject>[] paramArr = next.getValue();
			BaseHandler handler = handlerClass.newInstance();
			handler.setContext(context);
			if(CommonUtil.isEmpty(paramArr[0])){
				imsLogger.info(" 没有数据传入，跳过...");
				continue;
			}

			long t1 = System.currentTimeMillis();
			handler.initData(paramArr);
			imsLogger.debug(" start to handle:", indexHandlerObject.getItableName());
			if (iDataIndex.getBusiCode() == EnumCodeExDefine.ALL_DELETE_BUSI_CODE) {
				imsLogger.info(" beforeDeal because busi_code is 11111111111 !!!");
				handler.beforeDeal(paramArr);
			}
			handler.handle(paramArr);
			imsLogger.debug(new Object[]{t1, " end to handle:", indexHandlerObject.getItableName(), " handle count :",
					paramArr[0].size()});
		}

		// 数据处理完后，判断是否过户业务，是的话就要进行数据的迁移，及可用资金的转移
		context.getCmp(UserAcctRelCmp.class).changeOwnerDeal();

		// 销户，将用户相关信息删除；
		context.getCmp(UserStsCmp.class).removeRouter4DeleteUser();

//		indexHandlerObjectMap.clear();
	}

	private void moveToHis(DataHolder dataHolder, Date dealDate, IConfig config, AbstractDbClient dbClient)
			throws Exception {
		long t1 = System.currentTimeMillis();
		ITableUtil.cleanRequestParamter();
		imsLogger.debug(" start to move ", dataHolder.getSubTableName(), " to HIS table");
		DataObject dataObject = dataHolder.getDataObject();
		try {
			dbClient.insert(config.convertToHis(dataObject, dealDate));
			// 删除Err表数据
			dbClient.delete(config.getDeleteDataObjectSub(dataObject), dataHolder.getSubTableName());
		} catch (Exception e) {
			if (e instanceof SQLException) {
				imsLogger.error(("SQL error at:" + ((SQLException) e).getSQLState()), e);
			}
			IMSUtil.throwBusiException(e);
		}

		imsLogger.debug(new Object[]{t1, " end to move ", dataHolder.getSubTableName(), " to HIS table "});
	}

	/**
	 * lijc3 2013-8-28 资产迁移调用
	 * 
	 * @param context
	 * @param dataHolder
	 * @return
	 */
	private CBSErrorMsg transferMdbAsset(ITableContext context, DataHolder dataHolder,IConfig config) {

		CBSErrorMsg msg = new CBSErrorMsg();
		msg.set_errorCode(0);
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		Long oldAcctId = null;
		Long newAcctId = null;
		Long userId = null;
		// abm操作失败，直接从缓存信息中获取
		if (infoBean.isAbmFail()) {
			oldAcctId = infoBean.getOldAcctId();
			newAcctId = infoBean.getNewAcctId();
			userId = infoBean.getUserId();
		}
		// 从缓存中获取的oldAcctId每值，但是是过户，则从context中获取
		if (oldAcctId == null && UserAcctRelHelper.isChangeOwner(context)) {
			userId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_USERID);
			oldAcctId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_OLD_ACCTID);
			newAcctId = (Long) context.getExtendParam(ConstantExDefine.ROUTER_MOVE_NEW_ACCTID);
			infoBean.setUserId(userId);
			infoBean.setOldAcctId(oldAcctId);
			infoBean.setNewAcctId(newAcctId);
		}
		// 都没有获取到值，则不是过户，不用资产迁移
		if (oldAcctId == null) {
        	IUserAcctRel rel=getOldAcctIdFromDb(context, dataHolder, config);
        	if(rel!=null){
        		oldAcctId = rel.getOldAcctId();
                newAcctId = rel.getAcctId();
                userId = rel.getUserId();
        	}
        }
        // 都没有获取到值，则不是过户，不用资产迁移
        if (oldAcctId == null)
        {
            imsLogger.debug("_____oldAcctId is null___");
            return msg;
        }
        long t1 = System.currentTimeMillis();
        try
        {
            imsLogger.debug("_____begin transfer abm asset...", userId, ";", oldAcctId, ";", newAcctId);
            SAbmAssetTransferRet ret = context.getCmp(UserAcctRelCmp.class).transferMdbAssetByChangeOwner(userId, oldAcctId,
                    newAcctId);
            if (ret != null && ret.get_succFlag() != EnumCodeExDefine.CALL_INTERFACE_SUCCESS)
            {
                imsLogger.debug("_____transfer mdb asset error,msg_code: ", ret.get_succFlag());
                msg.set_errorCode((int) ErrorCodeExDefine.ACCESS_INTERFACE_FAILED);
                msg.set_errorMsg("call interface transferMdbAsset error");
            }
        }
        catch (Exception e)
        {
        	imsLogger.error("调用资产迁移失败：", e);
            // 返回错误对象
            if (e instanceof BusinessException)
            {
                msg.set_errorCode(((BusinessException) e).getCodeAsInteger());
            }
            else
            {
                msg.set_errorCode((int) ErrorCodeDefine.UNKNOWN_ERROR);
            }
            msg.set_errorMsg(e.getClass().getName() + ":" + e.getLocalizedMessage());
        }
        imsLogger.debug(new Object[]{t1, "____end to transfer abm asset, msg_code : ", msg.get_errorCode()});
        return msg;
    }

	private CBSErrorMsg syncCloudUserMdb(ITableContext context, DataHolder dataHolder, IConfig config,
			AbstractDbClient dbClient) throws Exception {
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		ItableSalMdbBean mdbBean = new ItableSalMdbBean(context);
		DataObject dataObject = dataHolder.getDataObject();
		Long seqId = config.getValueOfSeqId(dataObject);
		Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);

		SSyncAllInfo sSyncAllInfo = infoBean.getsSyncAllInfo();// 上发mdb需要的参数
		SSyncAllInfo changeOwnSyncInfo = infoBean.getChangeOwnSyncInfo();// 上发mdb需要的参数

		// 如果是1的状态，表示数据已经入库，只需要上发mdb，此时，上发mdb需要的数据，需要从表中获取
		if (dbSuccSts == FlowConst.DB_SUCC_STS_TRUE) {
			if (sSyncAllInfo == null) {
				sSyncAllInfo = getISyncMdbParamFromDB(seqId, dbClient,ConstantExDefine.MDB_SYNC_INFO_ALL);
			}
			if (changeOwnSyncInfo == null) {
				changeOwnSyncInfo = getISyncMdbParamFromDB(seqId, dbClient,ConstantExDefine.MDB_SYNC_INFO_OWN);
			}
		}
		ErrorMsgHolder errorMsgHolder = mdbBean.syncMdb(sSyncAllInfo, null);// 数据上发mdb
		// dataHolder.setsSyncAllInfo(errorMsgHolder.getsSyncAllInfo());//
		// 保存上发mdb的信息
		infoBean.setsSyncAllInfo(errorMsgHolder.getsSyncAllInfo());

		CBSErrorMsg cbsErrorMsg = errorMsgHolder.getCbsErrorMsg();
		// 20130715如果是过户 还要将原来MDB中的数据置为失效
		if ((cbsErrorMsg == null || cbsErrorMsg.get_errorCode() == 0)) {
			if (UserAcctRelHelper.isChangeOwner(context) || changeOwnSyncInfo != null) {
				// 上发数据未空，表示第一次上发
				Long oldAcctId = changeOwnSyncInfo == null ? (context.getUserInfoComplex() == null ? null : context
						.getUserInfoComplex().getOldAcctId()) : infoBean.getOldAcctId();
				try {
                	if(oldAcctId==null&&changeOwnSyncInfo!=null){
                		IUserAcctRel rel=getOldAcctIdFromDb(context, dataHolder, config);
                		if(rel!=null){
                			oldAcctId=rel.getOldAcctId();
                		}
                	}
				} catch (Exception e) {
					imsLogger.error(e, e);
				}
				// 将原来数据置为失效
				imsLogger.info("____change owner sync mdb,old acctid = ", oldAcctId);
				if (oldAcctId != null) {
					mdbBean.resetContextByChangeOwner();
					ErrorMsgHolder errorMsgHolder2 = mdbBean.syncMdb(changeOwnSyncInfo, oldAcctId);// 过户老数据上发mdb
					if (errorMsgHolder2 != null) {
						infoBean.setChangeOwnSyncInfo(errorMsgHolder2.getsSyncAllInfo());
						infoBean.setOldAcctId(oldAcctId);
						cbsErrorMsg = errorMsgHolder2.getCbsErrorMsg();
					}
				}
			}
		} else {
			// 第一个就上发出错了，过户的数据要备份起来，供后续上发
			if (changeOwnSyncInfo == null && UserAcctRelHelper.isChangeOwner(context)) {
				// 将原来数据置为失效
				if (context.getUserInfoComplex() != null) {
					imsLogger.debug("_____when change owner, but return error when sync first new mdb");
					mdbBean.resetContextByChangeOwner();
					infoBean.setChangeOwnSyncInfo(mdbBean.getSyncAllInfo());
					infoBean.setOldAcctId(context.getUserInfoComplex().getOldAcctId());
				}
			}
		}
		return cbsErrorMsg;
	}
	
    private IUserAcctRel getOldAcctIdFromDb(ITableContext context, DataHolder dataHolder, IConfig config){
    	IDataIndex iDataIndex = config.convertToIDataIndex(dataHolder.getDataObject());
    	Long soNbr=config.getValueOfSoNbr(dataHolder.getDataObject());
    	ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_COMMIT_DATE,
                DateUtil.getFormatDate(iDataIndex.getCommitDate(), DateUtil.DATE_FORMAT_YYYYMMDD));
    	List<IUserAcctRel> relList=DBUtil.query(IUserAcctRel.class,new DBCondition(IUserAcctRel.Field.soNbr, soNbr));
    	if(CommonUtil.isEmpty(relList)||relList.size()<2){
    		return null;
    	}
		for(IUserAcctRel rel:relList){
			if (rel.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT)
            {
                if (rel.getOldAcctId().intValue() != rel.getAcctId().intValue())
                {
                	return rel;
                }
            }
		}
    	return null;
    	
    }

    private CBSErrorMsg syncIvrMdb(ITableContext context, DataHolder dataHolder, IConfig config, AbstractDbClient dbClient) throws Exception{
    	SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		ItableSalMdbBean mdbBean = new ItableSalMdbBean(context);
		DataObject dataObject = dataHolder.getDataObject();
		Long seqId = config.getValueOfSeqId(dataObject);
		Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);
		
		SSyncIvrAllInfo sSyncIvrAllInfo = infoBean.getsSyncIvrAllInfo();
		// 如果是1的状态，表示数据已经入库，只需要上发mdb，此时，上发mdb需要的数据，需要从表中获取
		if (dbSuccSts == FlowConst.DB_SUCC_STS_TRUE) {
			if (sSyncIvrAllInfo == null) {
				sSyncIvrAllInfo = getIvrSyncMdbParamFromDB(seqId, dbClient);
			}
		}
		ErrorMsgHolder errorMsgHolder = mdbBean.syncIvrMdb(sSyncIvrAllInfo);
		infoBean.setsSyncIvrAllInfo(errorMsgHolder.getsSyncIvrAllInfo());
		
		CBSErrorMsg cbsErrorMsg = errorMsgHolder.getCbsErrorMsg();

		return cbsErrorMsg;
    }
    
	private CBSErrorMsg syncUserMdb(ITableContext context, DataHolder dataHolder, IConfig config,
			AbstractDbClient dbClient) throws Exception {
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		ItableSalMdbBean mdbBean = new ItableSalMdbBean(context);
		DataObject dataObject = dataHolder.getDataObject();
		Long seqId = config.getValueOfSeqId(dataObject);
		Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);

		SSyncAllInfo sSyncAllInfo = infoBean.getsSyncAllInfo();// 上发mdb需要的参数

		// 如果是1的状态，表示数据已经入库，只需要上发mdb，此时，上发mdb需要的数据，需要从表中获取
		if (dbSuccSts == FlowConst.DB_SUCC_STS_TRUE) {
			if (sSyncAllInfo == null) {
				sSyncAllInfo = getISyncMdbParamFromDB(seqId, dbClient,ConstantExDefine.MDB_SYNC_INFO_ALL);
			}
		}
		ErrorMsgHolder errorMsgHolder = mdbBean.syncMdb(sSyncAllInfo, null);// 数据上发mdb
		infoBean.setsSyncAllInfo(errorMsgHolder.getsSyncAllInfo());

		CBSErrorMsg cbsErrorMsg = errorMsgHolder.getCbsErrorMsg();

		return cbsErrorMsg;
	}

	private void dealMdb(ITableContext context, DataHolder dataHolder, IConfig config, AbstractDbClient dbClient)
			throws Exception {

		imsLogger.debug("_____start to sync mdb and transfer abm asset___ ");
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		if (infoBean == null) {
			imsLogger.debug("_____first init sync_mdb_info_bean___");
			infoBean = new SyncMdbInfoBean();
			dataHolder.setInfoBean(infoBean);
		}

		DataObject dataObject = dataHolder.getDataObject();
		Long soNbr = config.getValueOfSoNbr(dataObject);
		Long seqId = config.getValueOfSeqId(dataObject);
		Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);

		CBSErrorMsg abmMsg = null;
		// 是错单并且资产迁移报错或者正常工单，则需要进行资产迁移
		if (ITableUtil.isMdbCloud() ) {
			abmMsg = transferMdbAsset(context, dataHolder,config);
		}
		// 正常工单或者错误工单并且是userMdb处理失败，则进行上发mdb动作
		long t1 = System.currentTimeMillis();

		imsLogger.debug("_____start to sync mdb___");
		CBSErrorMsg mdbMsg = null;
		if (ITableUtil.isManualSyncMdb()) {
			// 全部异步上发
			ManualModifyCreator creator = new ManualModifyCreator();
			creator.parseCache(context);
			DBUtil.insertBatch(creator.getImsManualModifyList());
		} else {
			if (ITableUtil.isMdbCloud()) {
				mdbMsg = syncCloudUserMdb(context, dataHolder, config, dbClient);
			} else {
				mdbMsg = syncUserMdb(context, dataHolder, config, dbClient);
			}
		}
		
		CBSErrorMsg ivrMdbMsg = syncIvrMdb(context, dataHolder, config, dbClient);
		imsLogger.debug(new Object[]{t1, "_____end to up mdb___"});

		// 如果上发出错，保存错误信息
		if ((mdbMsg != null && mdbMsg.get_errorCode() != 0) || (abmMsg != null && abmMsg.get_errorCode() != 0) || (ivrMdbMsg != null &&  ivrMdbMsg.get_errorCode() !=0)) {
			imsLogger.debug("_____sync mdb error,set user_mdb or abm fail flag___");
			isMDBFailOrAbmFail(mdbMsg, abmMsg, dataHolder);
			if (dataHolder.isError())// 如果是执行错单再次出错
			{
				// 说明已经在队列中，
				ErrorHolder errorHolder = (ErrorHolder) dataHolder;
				// 超过次数，移到错误表，并且删除
				if (errorHolder.isExceedMaxRetryTime()) {
					// 如果是超过次数，移到err表
					imsLogger.debug(
							"***sync mdb or transfer abm asset error over times, remove to err table , busi_value  = ",
							errorHolder.getBusiValue());

					beginMoveToErrorTable(errorHolder, context.getLocalDate(), mdbMsg, abmMsg, config);

					if (dbSuccSts == FlowConst.DB_SUCC_STS_TRUE) {
						// 需要把之前保存的参数记录删除
						removeISyncMdbParam(seqId, dbClient);
					}

					// 从队列中移除该错单[该错单肯定是第一个错单]
					ErrorListQueue.removeFirstErrorHolder();
					return;
				} else {
					// 则更新 处理次数 和 下次处理时间
					imsLogger.debug("**** sync mdb error again");
					errorHolder.failAgain();

					// 该用户的错单列表移到队列末尾[该工单一定是整个队列中的第一个用户的第一个错单]
					ErrorListQueue.moveFirstListToLast();
					errorHolder.setRemove(true);
				}
			} else {
				// 1 表示该工单并非错单，则把该工单放入到错误队列中
				//ErrorListQueue.addErrorHolderToQueue(new ErrorHolder(dataHolder));
			}

			// 2 同时增加上发mdb参数对象到i_sync_mdb_param表
			if (dbSuccSts == FlowConst.DB_SUCC_STS_FALSE) {
				// 3 上发mdb参数对象保存到表中
				imsLogger.debug(
						"***first sync mdb error, need insert i_sync_mdb_param,and update index_sub , seq_id =  ",
						seqId);

				this.insertMdbParam(seqId, soNbr, infoBean.getsSyncAllInfo(), infoBean.getChangeOwnSyncInfo(),
						infoBean.getsSyncIvrAllInfo(),context.getLocalDate(), dbClient);

				// 4 同时把小索引表中的状态改为1
				DataObject updateDataObjectSub = config.getUpdateDataObjectSub(dataObject);
				dbClient.update(updateDataObjectSub, dataHolder.getSubTableName());

				// 5 内存中的对象修改状态
				config.setDbSuccStsTrue(dataObject);

				// 6为解决后续db提交失败增加注册流程
				imsLogger.debug("******first call mdb fail ,region flow mdb fail");
				ItableListenerServiceFlow flow = new ItableListenerServiceFlow();
				flow.setConfig(config);
				flow.setDataOjbect(dataObject);
				ItableTransListener listener = new ItableTransListener();
				listener.addActionContainer(flow);
			}
		} else
		// 表示上发mdb成功
		{
			if (dbSuccSts == FlowConst.DB_SUCC_STS_TRUE) {
				// 需要把之前保存的参数记录删除
				imsLogger.debug("****second sync mdb success, delete i_sync_mdb_param, seq_id = ", seqId);
				removeISyncMdbParam(seqId, dbClient);
			}

			if (dataHolder.isError()) {
				imsLogger.debug("******error data deal sucess, region threadlocal  ", dataHolder.getBusiValue());
				regionRemoveErrorList();
			}
			// 移到到历史表 有可能没有数据
			if (!dataHolder.isMdbCommitFail()) {
				imsLogger.debug("******begin to remove into data index his, busi_value =  ", dataHolder.getBusiValue());
				moveToHis(dataHolder, context.getLocalDate(), config, dbClient);
			} else {
				imsLogger
						.info("*****after commit sal exception ,sync mdb again success,no need to delete sub table and move to his");
			}
		}
	}

	/**
	 * lijc3 2013-8-28 根据不同的msg创建最后的mdb失败错误
	 * 
	 * @param errorHolder
	 */
	private void beginMoveToErrorTable(ErrorHolder errorHolder, Date dealDate, CBSErrorMsg mdbMsg, CBSErrorMsg abmMsg,
			IConfig config) throws Exception {
		if (mdbMsg != null && abmMsg == null) {
			moveToErrTable(errorHolder, dealDate, (long) 28888, "deal db success,only sync user_mdb error", config);
		} else if (mdbMsg == null && abmMsg != null) {
			moveToErrTable(errorHolder, dealDate, (long) 27777, "deal db success,only transfer abm asset error", config);
		} else if (mdbMsg != null && abmMsg != null) {
			moveToErrTable(errorHolder, dealDate, (long) 29999,
					"deal db success,sync mdb error and transfer abm asset error", config);
		}
	}

	private void isMDBFailOrAbmFail(CBSErrorMsg mdbMsg, CBSErrorMsg abmMsg, DataHolder dataHolder) {
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		/*
		 * if (mdbMsg != null && mdbMsg.get_errorCode() != 0) {
		 * imsLogger.debug("_____set mdb fail___");
		 * infoBean.setUserMdbFail(true); }
		 */

		if ((abmMsg != null && abmMsg.get_errorCode() != 0)) {
			imsLogger.debug("_____set abm fail___");
			infoBean.setAbmFail(true);
		}
	}

	private void regionRemoveErrorList() {
		ItableListenerServiceFlow flow = new ItableListenerServiceFlow();
		flow.setRemoveFlag(true);
		ItableTransListener listener = new ItableTransListener();
		listener.addActionContainer(flow);
	}

	private void removeISyncMdbParam(Long seqId, AbstractDbClient dbClient) throws Exception {
		ISyncMdbParam cond = new ISyncMdbParam();
		cond.getQuery().addCondition(ISyncMdbParam.Field.seqId, seqId);
		dbClient.delete(cond);
//
//		cond = new ISyncMdbParam();
//		cond.getQuery().addCondition(ISyncMdbParam.Field.seqId, -seqId);
//		dbClient.delete(cond);
	}

	private SSyncIvrAllInfo getIvrSyncMdbParamFromDB(Long seqId, AbstractDbClient dbClient) throws Exception {
		ISyncMdbParam cond = new ISyncMdbParam();
		cond.getQuery().addCondition(ISyncMdbParam.Field.seqId, seqId);
		cond.getQuery().addCondition(ISyncMdbParam.Field.seqId,ConstantExDefine.MDB_SYNC_INFO_IVR);
		List<ISyncMdbParam> list = dbClient.select(cond);
		// lijc3 增加容错处理
		if (CommonUtil.isNotEmpty(list)) {
			ISyncMdbParam iSyncMdbParam = list.get(0);
			byte[] paramObj = iSyncMdbParam.getParamObj();
			return (SSyncIvrAllInfo) ITableUtil.serialize(paramObj);
		}
		return null;
	}
	
	private SSyncAllInfo getISyncMdbParamFromDB(Long seqId, AbstractDbClient dbClient,Integer dataType) throws Exception {
		ISyncMdbParam cond = new ISyncMdbParam();
		cond.getQuery().addCondition(ISyncMdbParam.Field.seqId, seqId);
		cond.getQuery().addCondition(ISyncMdbParam.Field.dataType,dataType);
		List<ISyncMdbParam> list = dbClient.select(cond);
		// lijc3 增加容错处理
		if (CommonUtil.isNotEmpty(list)) {
			ISyncMdbParam iSyncMdbParam = list.get(0);
			byte[] paramObj = iSyncMdbParam.getParamObj();
			return (SSyncAllInfo) ITableUtil.serialize(paramObj);
		}
		return null;
	}

	/**
	 * 增加上发mdb参数对象到i_sync_mdb_param表【调用方需要保证该seqId对应的上发信息是第一次insert】
	 */
	private void insertMdbParam(Long seqId, Long soNbr, SSyncAllInfo sSyncAllInfo, SSyncAllInfo changeOwnSyncInfo,SSyncIvrAllInfo sSyncIvrAllInfo,
			Date syncDate, AbstractDbClient dbClient) throws SQLException {
		List<ISyncMdbParam> paramList = new ArrayList<ISyncMdbParam>();
		ISyncMdbParam iSyncMdbParam = getISyncMdbParam(seqId, soNbr, sSyncAllInfo, syncDate,ConstantExDefine.MDB_SYNC_INFO_ALL);
		paramList.add(iSyncMdbParam);

		if (changeOwnSyncInfo != null) {
			ISyncMdbParam iSyncMdbParam2 = getISyncMdbParam(seqId, soNbr, changeOwnSyncInfo, syncDate,ConstantExDefine.MDB_SYNC_INFO_OWN);
			paramList.add(iSyncMdbParam2);
		}
		
		if(sSyncIvrAllInfo != null){
			ISyncMdbParam iSyncMdbParam3 = getISyncMdbParam(seqId, soNbr, sSyncIvrAllInfo, syncDate,ConstantExDefine.MDB_SYNC_INFO_IVR);
			paramList.add(iSyncMdbParam3);
		}
		
		Batch<ISyncMdbParam> batch = dbClient.startBatchInsert(iSyncMdbParam);
		batch.add(paramList);
		batch.commit();
	}

	private ISyncMdbParam  getISyncMdbParam(Long seqId, Long soNbr,SSyncIvrAllInfo sSyncIvrAllInfo,Date syncDate,Integer dataType){

			ISyncMdbParam iSyncMdbParam = new ISyncMdbParam();
			iSyncMdbParam.setSeqId(seqId);
			iSyncMdbParam.setSoNbr(soNbr);
			iSyncMdbParam.setSyncDate(syncDate);
			iSyncMdbParam.setDataType(dataType);
			byte[] bs = ITableUtil.serialize(sSyncIvrAllInfo);
			iSyncMdbParam.setParamObj(bs);
			return iSyncMdbParam;
		
		
	}
	
	private ISyncMdbParam getISyncMdbParam(Long seqId, Long soNbr, SSyncAllInfo sSyncAllInfo, Date syncDate,Integer dataType) {
		ISyncMdbParam iSyncMdbParam = new ISyncMdbParam();
		iSyncMdbParam.setSeqId(seqId);
		iSyncMdbParam.setSoNbr(soNbr);
		iSyncMdbParam.setSyncDate(syncDate);
		iSyncMdbParam.setDataType(dataType);
		byte[] bs = ITableUtil.serialize(sSyncAllInfo);// 实现序列化sSyncAllInfo
		iSyncMdbParam.setParamObj(bs);
		return iSyncMdbParam;
	}
}
