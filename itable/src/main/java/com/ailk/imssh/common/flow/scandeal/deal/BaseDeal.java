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
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.manualmdb.ManualModifyCreator;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.cmp.SalInterfaceCmp;
import com.ailk.imssh.common.commit.ItableListenerServiceFlow;
import com.ailk.imssh.common.commit.ItableTransListener;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.flow.bean.IndexHandlerObject;
import com.ailk.imssh.common.flow.bean.SyncMdbInfoBean;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.error.bean.ErrorHolder;
import com.ailk.imssh.common.flow.error.bean.ErrorListQueue;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.init.ITableUpfieldInitBean;
import com.ailk.imssh.common.mdb.ErrorMsgHolder;
import com.ailk.imssh.common.mdb.ItableSalMdbBean;
import com.ailk.imssh.common.mdb.UserInfoComplex;
import com.ailk.imssh.common.util.CommonOperTypeComparator;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.diff.handler.DiffHandler;
import com.ailk.imssh.user.cmp.UserAcctRelCmp;
import com.ailk.imssh.user.cmp.UserPortabilityCmp;
import com.ailk.imssh.user.cmp.UserStsCmp;
import com.ailk.imssh.user.helper.UserAcctRelHelper;
import com.ailk.openbilling.persistence.itable.entity.IAcctchgDtrade;
import com.ailk.openbilling.persistence.itable.entity.IAcctchgTrade;
import com.ailk.openbilling.persistence.itable.entity.ICustomer;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;
import com.ailk.openbilling.persistence.itable.entity.ISyncMdbParam;
import com.ailk.openbilling.persistence.itable.entity.IUserAcctRel;

/**
 * @Description:索引表处理类
 * @author wangjt
 * @Date 2012-9-13
 */
public class BaseDeal implements IBaseDeal {
	private ImsLogger imsLogger = new ImsLogger(BaseDeal.class);

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
			try{
				dbClient.insert(config.convertToErr(dataObject, dealDate, errorCode, errorMsg));
			}catch(SQLException e){//防止时间不对，错误分表不存在，进入死循环   liucc
					IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
					imsLogger.debug("========err table not exists it's time:"+iDataIndexSub.getCommitDate());
					String timeErr=iDataIndexSub.getCommitDate().toString();
					iDataIndexSub.setCommitDate(dealDate);
					dbClient.insert(config.convertToErr(iDataIndexSub, dealDate, errorCode, errorMsg+" ErrCommitTime: "+timeErr));
			}

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
		AbstractDbClient dbClient = DBUtil.getDBClient();
		DataObject dataObject = dataHolder.getDataObject();
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		if (dataHolder.isError()) {
			imsLogger.debug("***error again");
			// 说明已经在队列中，
			ErrorHolder errorHolder = (ErrorHolder) dataHolder;
			// 则更新值
			errorHolder.failAgain();

			// mdb提交再次失败，需要重新添加到队列中，但是次数
			if (errorHolder.isMdbCommitFail()) {
				// 添加到队列前面！准备再次处理
				// imsLogger.debug("***commit failed ,move to first or last");
				// ErrorListQueue.addErrorHolderToQueueFirst(errorHolder);
				// TODO记录MDB上发失败，是否需要记录ABM失败重新做？
				imsLogger
						.debug("***error code is 1048593,commit db sucess,commit mdb error again,need save mdb param to db");
				Long seqId = config.getValueOfSeqId(dataObject);
				Long soNbr = config.getValueOfSoNbr(dataObject);
				try {
					this.insertMdbParam(seqId, soNbr, infoBean.getsSyncAllInfo(), infoBean.getChangeOwnSyncInfo(),infoBean.getsSyncIvrAllInfo(),
							dealDate, dbClient);
				} catch (Exception e) {
					IMSUtil.throwBusiException(e);
				}

			} else {
				imsLogger.debug("***faied ,move to last");
				ErrorListQueue.moveFirstListToLast();
			}
		} else {
			imsLogger.debug("***first commit mdb error");
			// 如果是mdb提交异常，db提交成功了，小索引表数据已经删除了。需要放入到错误队列中，设置标志位
			ErrorHolder errorHolder = new ErrorHolder(dataHolder);
			if (errorCode == EnumCodeExDefine.MDB_COMMIT_FAIL) {
				SSyncAllInfo allInfo = infoBean.getsSyncAllInfo();
				if (allInfo != null) {
					imsLogger
							.debug("***error code is 1048593,commit db sucess,commit mdb error,need save mdb param to db");
					Long seqId = config.getValueOfSeqId(dataObject);
					Long soNbr = config.getValueOfSoNbr(dataObject);
					imsLogger.debug(
							"***first sync mdb error, need insert i_sync_mdb_param,and update index_sub , seq_id =  ",
							seqId);
					try {
						this.insertMdbParam(seqId, soNbr, allInfo, infoBean.getChangeOwnSyncInfo(),infoBean.getsSyncIvrAllInfo(), dealDate, dbClient);
					} catch (Exception e) {
						IMSUtil.throwBusiException(e);
					}

					// 4 同时把小索引表中的状态改为1 不能更新了，数据库已经提交完毕，删除了
					// DataObject updateDataObjectSub =
					// config.getUpdateDataObjectSub(dataObject);
					// dbClient.update(updateDataObjectSub,
					// dataHolder.getSubTableName());

					// 5 内存中的对象修改状态
					imsLogger.debug("***set dbsuccsts = 1");
					config.setDbSuccStsTrue(dataObject);
					// 设置为mdb提交失败
					errorHolder.setMdbCommitFail(true);
				}
			}
			// 1 该工单放入到错误队列中

			//ErrorListQueue.addErrorHolderToQueue(errorHolder);
		}

		// 2 把错误信息放入到错误通知表I_DATA_INDEX_NOTIFY
		ErrorHolder errorHolder = null;
		if(dataHolder.isError()){
			 errorHolder = (ErrorHolder) dataHolder;

		}else{
			 errorHolder = new ErrorHolder(dataHolder);
		}
        moveToErrTable(errorHolder, DateUtil.currentDate(), errorCode, errorMsg, config);

//		DataObject notify = config.convertToNotify(dataObject, errorCode, errorMsg);
//		try {
//			dbClient.insert(notify);
//		} catch (Exception e) {
//			if (e instanceof SQLException) {
//				imsLogger.error(("SQL error at:" + ((SQLException) e).getSQLState()), e);
//			}
//			IMSUtil.throwBusiException(e);
//		}

	}

	@Transactional
	public void dealProcess(DataHolder dataHolder, ITableContext context, IConfig config) throws Exception {
		long t1 = System.currentTimeMillis();
		imsLogger.debug(" start to deal ", dataHolder.getSubTableName());
		DataObject dataObject = null;
		AbstractDbClient dbClient = null;
		try {
			dbClient = DBUtil.getDBClient();

			// 执行一个工单【可能是正常工单，也可能是错单】
			dataObject = dataHolder.getDataObject();
			Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);
			imsLogger.debug("================================================before deal DB,dataSts:"+config.getValueOfDbSuccSts(dataObject));
			// sts == 0 时，表示需要做数据入库动作
			if (dbSuccSts == FlowConst.DB_SUCC_STS_FALSE) {
				// 1、处理具体业务
				this.dealDB(context, dataObject, config, dbClient);

			}
			imsLogger.debug("================================================after deal DB,dataSts:"+config.getValueOfDbSuccSts(dataObject));
			
			// 3、上发mdb
			this.dealMdb(context, dataHolder, config, dbClient);
			imsLogger.debug("================================================after deal MDB,dataSts:"+config.getValueOfDbSuccSts(dataObject));
			// 4、销户删除路由相关
			context.getCmp(UserStsCmp.class).removeRouter4Termi();
			dealAcctChg(context, dataObject, config, dbClient);
		} catch (Exception e) {
			if(dataObject !=null && dbClient!= null){
				
			}
			if (e instanceof SQLException) {
				imsLogger.error(("SQL error at:" + ((SQLException) e).getSQLState()), e);
			}
			IMSUtil.throwBusiException(e);
		}

		// 2、工单移动到历史表
		// this.moveToHis(dataHolder, context.getLocalDate(), config, dbClient);

		imsLogger.debug(new Object[]{t1, " end to deal ", dataHolder.getSubTableName(), "busi_value = ", dataHolder.getBusiValue()});
	}

	@SuppressWarnings("unchecked")
	private void dealAcctChg(ITableContext context, DataObject dataObject, IConfig config, AbstractDbClient dbClient) throws Exception{
		//TODO 过户之后保存数据
		if (!UserAcctRelHelper.isChangeOwner(context)) {
			return;
		}
		
		int dealSts = config.getValueOfDbSuccSts(dataObject);
		int tradeSts = 0;
		int dTreadeSts = 0;
		if(dealSts == FlowConst.MDB_DEAL_SUCC){
			//mdb处理成功，说明物理库也处理成功
			tradeSts = ConstantExDefine.CHANGE_ACCT_TRADE_SUCC;
			dTreadeSts = ConstantExDefine.CHANGE_ACCT_DTRADE_MDB_SUCC;
		}else{
			tradeSts = ConstantExDefine.CHANGE_ACCT_TRADE_FAIL;
			if(dealSts == FlowConst.DB_SUCC_STS_FALSE){
				//物理库处理失败
				dTreadeSts = ConstantExDefine.CHANGE_ACCT_DTRADE_DB_FAIL;
			}else if(dealSts == FlowConst.DB_SUCC_STS_TRUE){
				//物理库处理成功，mdb上发失败
				dTreadeSts = ConstantExDefine.CHANGE_ACCT_DTRADE_MDB_FAIL;
			}else if(dealSts == FlowConst.DB_DEAL_SUCC ){
				//物理库处理成功，mdb未处理，说明mdb上发之前出现异常，物理库会回滚
				//物理库处理失败
				dTreadeSts = ConstantExDefine.CHANGE_ACCT_DTRADE_DB_FAIL;
			}
		}
		
		Map<Long,Map<String,Object>> acctChgData = (Map<Long, Map<String, Object>>) context.getExtendParam(ConstantExDefine.CHANGE_ACCT_MAP);
		if(acctChgData != null){
			
			List<IAcctchgDtrade> dTradeList = new ArrayList<IAcctchgDtrade>();
			Long soNbr = context.getSoNbr();
			for(Entry<Long,Map<String,Object>> entry : acctChgData.entrySet()){
				dTradeList.add(convertAcctChgDtrade(soNbr,entry,dTreadeSts));
			}
			
			if(CommonUtil.isNotEmpty(dTradeList)){
				Batch<IAcctchgDtrade> batch = dbClient.startBatchInsert(dTradeList.get(0));
				batch.add(dTradeList);
				batch.commit();
			}
			dbClient.insert(convertAcctChgTrade(context,tradeSts));
		}
	}
	
	private IAcctchgDtrade convertAcctChgDtrade(Long soNbr,Entry<Long,Map<String,Object>> entry,Integer status){
		Map<String,Object> dataMap = entry.getValue();
		IAcctchgDtrade dTrade = new IAcctchgDtrade();
		dTrade.setSoNbr(soNbr);
		dTrade.setUserId(entry.getKey());
		dTrade.setOldAcctId((Long)dataMap.get(ConstantExDefine.ROUTER_MOVE_OLD_ACCTID));
		dTrade.setNewAcctId((Long)dataMap.get(ConstantExDefine.ROUTER_MOVE_NEW_ACCTID));
		dTrade.setChangeDate((Date)dataMap.get(ConstantExDefine.CHANGE_ACCT_UPDATE_DATE));
		dTrade.setSts(status);
		Date sysDate = new Date();
		dTrade.setInDate(sysDate);
		dTrade.setUpdateDate(sysDate);
		
		return dTrade;
		
	}
	
	private IAcctchgTrade convertAcctChgTrade(ITableContext context,Integer status){
		IAcctchgTrade trade = new IAcctchgTrade();
		trade.setSoNbr(context.getSoNbr());
		trade.setTradeId(context.getDoneCode());
		trade.setSts(status);
		Date sysDate = new Date();
		trade.setInDate(sysDate);
		trade.setUpdateDate(sysDate);
		
		return trade;
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
		context.setSoNbr(iDataIndex.getSoNbr());
		if (iDataIndex.getBusiCode() == EnumCodeExDefine.DELETE_BUSI_CODE_8) {
			context.setDiffException(true);
			if(iDataIndex.getUserId()==null){
				throw IMSUtil.throwBusiException("8的流程主工单的user_id不能为null");
			}else{
				context.setUserId(iDataIndex.getUserId());
				DiffHandler diff=new DiffHandler();
				diff.setContext(context);
				diff.handleDiffException(null);
			}
		}else{
			context.setDiffException(false);

		}

		// 往context里面设置分表字段
		RequestContext requestContext = ContextHolder.getRequestContext();

		requestContext.put(ConstantExDefine.ROUTER_KEY_COMMIT_DATE,
				DateUtil.getFormatDate(iDataIndex.getCommitDate(), DateUtil.DATE_FORMAT_YYYYMMDD));

		Map<IndexHandlerObject, List<? extends DataObject>[]> indexHandlerObjectMap = new LinkedHashMap<IndexHandlerObject, List<? extends DataObject>[]>();
		// 将当前I表数据全部存下。
		context.setIndexHandlerObjectMap(indexHandlerObjectMap);
		//获取I表的字段，这里通用ICUSTOMER的代替
		java.lang.reflect.Field f_operType = ClassUtil.getField(ICustomer.class, "operType");
		java.lang.reflect.Field f_expireDate = ClassUtil.getField(ICustomer.class, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
        java.lang.reflect.Field f_validDate = ClassUtil.getField(ICustomer.class, ConstantDefine.ENTITY_FIELD_VALID_DATE);
		// 数据入信管表
		String upFieldNew = "0" + iDataIndex.getUpField();
		if(!ITableUtil.deleteMap.containsKey(iDataIndex.getSoNbr())){
			ITableUtil.deleteMap.clear();
			ITableUtil.deleteList.clear();
		}
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

				//adjustOperType(dataArr, iDataIndex.getCommitDate(),f_operType,f_validDate,f_expireDate);
				adjustDiffOperType(dataArr, f_operType);
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
//			if (iDataIndex.getBusiCode() == EnumCodeExDefine.DELETE_BUSI_CODE_8) {
//				imsLogger.info("*******Deal busi_code is 999******** ");
//				handler.handleDiffException(paramArr);
//				imsLogger.debug(new Object[] { t1, " end to handleDiffException:",indexHandlerObject.getItableName(), 
//                  " handle count :",paramArr[0].size() });
//			}else{
				handler.handle(paramArr);
				imsLogger.debug(new Object[] { t1, " end to handle:", indexHandlerObject.getItableName(), 
                   " handle count :",paramArr[0].size() });
//			}
		}

		// 数据处理完后，判断是否过户业务，是的话就要进行数据的迁移，及可用资金的转移
		context.getCmp(UserAcctRelCmp.class).changeOwnerDeal();

		//携号跨区立即生效处理
		context.getCmp(UserPortabilityCmp.class).changeRegionDeal();
		
		// 销户，将用户相关信息删除；
		context.getCmp(UserStsCmp.class).removeRouter4DeleteUser();
		config.setDealSts(dataObject, FlowConst.DB_DEAL_SUCC);
//		indexHandlerObjectMap.clear();
	}
	
	private void adjustOperType(List<DataObject> dataArr, Date commitDate,java.lang.reflect.Field f_operType,java.lang.reflect.Field f_validDate,java.lang.reflect.Field f_expireDate) throws Exception {
		if (CommonUtil.isEmpty(dataArr)) {
			return;
		}
		try {
			for (DataObject obj : dataArr) {
				int operType = (Integer) ClassUtil.getFieldValue(obj, f_operType);
				if (operType == EnumCodeExDefine.OPER_TYPE_DELETE) {
					Date validDate = (Date) ClassUtil.getFieldValue(obj,f_validDate);
					Date expireDate = (Date) ClassUtil.getFieldValue(obj,f_expireDate);
					// 生效时间不等于失效时间 失效时间大于提交时间 失效时间大于生效时间
					if (expireDate.after(commitDate) && !expireDate.before(validDate)) {
						// 把oper_type修改成2
						imsLogger.debug("############################调整操作类型为2");
						ClassUtil.setFieldValue(obj, f_operType, 2);
					}
				}
			}
		} catch (Exception e) {
			imsLogger.error("调整操作类型失败");
			// nothing 同一批数据一个报错 都会报错 跳过不处理
		}
	}
	
	private void adjustDiffOperType(List<DataObject> dataArr,java.lang.reflect.Field f_operType) throws Exception {
		if (CommonUtil.isEmpty(dataArr)) {
			return;
		}
		try {
			for (DataObject obj : dataArr) {
				int operType = (Integer) ClassUtil.getFieldValue(obj, f_operType);
                if(operType==8){
                	ClassUtil.setFieldValue(obj, f_operType, 1);
                }
			}
		} catch (Exception e) {
			imsLogger.error("调整操作类型失败");
			// nothing 同一批数据一个报错 都会报错 跳过不处理
		}
	}
	
	private void moveToHis(DataHolder dataHolder, Date dealDate, IConfig config, AbstractDbClient dbClient)
			throws Exception {
		long t1 = System.currentTimeMillis();
		ITableUtil.cleanRequestParamter();
		imsLogger.debug(" start to move ", dataHolder.getSubTableName(), " to HIS table");
		DataObject dataObject = dataHolder.getDataObject();
		try {
			dbClient.insert(config.convertToHis(dataObject, dealDate));
			// 删除小索引表数据
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
            //SAbmAssetTransferRet ret = context.getCmp(UserAcctRelCmp.class).transferMdbAssetByChangeOwner(userId, oldAcctId,newAcctId);
            int result = context.getCmp(SalInterfaceCmp.class).transferMdbAsset(userId, newAcctId, oldAcctId, 0, 0, 0, null, true);
            if (result != EnumCodeExDefine.CALL_INTERFACE_SUCCESS)
            {
                imsLogger.debug("_____transfer mdb asset error,msg_code: ", result);
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
		//Long seqId = config.getValueOfSeqId(dataObject);
		//Short dbSuccSts = config.getValueOfDbSuccSts(dataObject);

		SSyncAllInfo sSyncAllInfo = infoBean.getsSyncAllInfo();// 上发mdb需要的参数
		SSyncAllInfo changeOwnSyncInfo = infoBean.getChangeOwnSyncInfo();// 上发mdb需要的参数

		// 如果是1的状态，表示数据已经入库，只需要上发mdb，此时，上发mdb需要的数据，需要从表中获取
		//天津修改之后上发失败不会再将数据存入物理库了，直接当错单丢错误表
		ErrorMsgHolder errorMsgHolder = mdbBean.syncMdb(sSyncAllInfo, null);// 数据上发mdb
		if(errorMsgHolder !=null && errorMsgHolder.getCbsErrorMsg()!=null){
			imsLogger.debug("==================================================sync usermdb end, code: ",errorMsgHolder.getCbsErrorMsg().get_errorCode());
		}
		
		// dataHolder.setsSyncAllInfo(errorMsgHolder.getsSyncAllInfo());//
		// 保存上发mdb的信息
		infoBean.setsSyncAllInfo(errorMsgHolder.getsSyncAllInfo());

		CBSErrorMsg cbsErrorMsg = errorMsgHolder.getCbsErrorMsg();
		// 20130715如果是过户 还要将原来MDB中的数据置为失效
		if (cbsErrorMsg == null || cbsErrorMsg.get_errorCode() == 0) {
			if (UserAcctRelHelper.isChangeOwner(context)) {
				List<UserInfoComplex> complexList = context.getUserInfoComplexList();
				if(CommonUtil.isNotEmpty(complexList)){
					for(UserInfoComplex complex : complexList){
						Long oldAcctId = complex.getOldAcctId();
						imsLogger.info("____change owner sync mdb,old acctid = ", oldAcctId);
						mdbBean.resetContextByComplex(complex);
						errorMsgHolder = mdbBean.syncMdb(changeOwnSyncInfo, oldAcctId);
						cbsErrorMsg = errorMsgHolder.getCbsErrorMsg();
						if(errorMsgHolder !=null && errorMsgHolder.getCbsErrorMsg()!=null){
							imsLogger.debug("==================================================change acct sync old usermdb end, code: ",errorMsgHolder.getCbsErrorMsg().get_errorCode());
						}
						if(cbsErrorMsg != null && cbsErrorMsg.get_errorCode() != 0){
							return cbsErrorMsg;
						}
					}
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

		//CBSErrorMsg abmMsg = null;
		//TODO abm先注释，后期再修改
		// 是错单并且资产迁移报错或者正常工单，则需要进行资产迁移
//		if (ITableUtil.isMdbCloud() ) {
//			abmMsg = transferMdbAsset(context, dataHolder,config);
//		}
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
		if ((mdbMsg != null && mdbMsg.get_errorCode() != 0) || (ivrMdbMsg != null &&  ivrMdbMsg.get_errorCode() !=0)) {
			imsLogger.debug("_____sync mdb error,begin remove to error table___");
			isMDBFailOrAbmFail(mdbMsg,ivrMdbMsg,dataHolder);
			config.setDbSuccStsTrue(dataObject);
			ErrorHolder errorHolder = new ErrorHolder(dataHolder);
			beginMoveToErrorTable(errorHolder, context.getLocalDate(), mdbMsg,ivrMdbMsg,config);
			//ErrorListQueue.removeFirstErrorHolder();
	
		} else
		// 表示上发mdb成功
		{
			try{
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
					imsLogger.info("*****after commit sal exception ,sync mdb again success,no need to delete sub table and move to his");
				}
				config.setDealSts(dataObject, FlowConst.MDB_DEAL_SUCC);
				imsLogger.debug("================================================deal mdb success,dataSts:"+config.getValueOfDbSuccSts(dataObject));
			}catch(Exception e){
				config.setDealSts(dataObject, FlowConst.DB_SUCC_STS_FALSE);
				imsLogger.debug("================================================catch exception after deal mdb success,db roll back,dataSts:"+config.getValueOfDbSuccSts(dataObject));
				throw e;
			}
		}
	}

	/**
	 * lijc3 2013-8-28 根据不同的msg创建最后的mdb失败错误
	 * 
	 * @param errorHolder
	 */
	private void beginMoveToErrorTable(ErrorHolder errorHolder, Date dealDate, CBSErrorMsg mdbMsg,CBSErrorMsg ivrMsg,
			IConfig config) throws Exception {

		if(mdbMsg !=null && mdbMsg.get_errorCode() !=0 && (ivrMsg == null || ivrMsg.get_errorCode() == 0)){
			moveToErrTable(errorHolder, dealDate, (long) 28888, "deal db success,only sync user_mdb error", config);
		}else if(mdbMsg !=null && mdbMsg.get_errorCode() !=0 && ivrMsg != null && ivrMsg.get_errorCode() !=0){
			moveToErrTable(errorHolder, dealDate, (long) 29999, "deal db success,sync mdb error and ivr error", config);
		}else if(ivrMsg != null && ivrMsg.get_errorCode() !=0 && (mdbMsg == null || mdbMsg.get_errorCode() == 0)){
			moveToErrTable(errorHolder, dealDate, (long) 27777, "deal db success,sync ivr_mdb error", config);
		}
	
	}

	private void isMDBFailOrAbmFail(CBSErrorMsg mdbMsg,CBSErrorMsg ivrMsg ,DataHolder dataHolder) {
		SyncMdbInfoBean infoBean = dataHolder.getInfoBean();
		
		 if (mdbMsg != null && mdbMsg.get_errorCode() != 0) {
			 imsLogger.debug("_____set mdb fail___");
			 infoBean.setUserMdbFail(true); 
			 }
		

		
		if ((ivrMsg != null && ivrMsg.get_errorCode() != 0)) {
			imsLogger.debug("_____set ivr fail___");
			infoBean.setIvrFail(true);
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
