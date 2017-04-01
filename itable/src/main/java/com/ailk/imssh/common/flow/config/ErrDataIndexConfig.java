package com.ailk.imssh.common.flow.config;

import java.util.Date;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;

import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.consts.SeqIdGenerate;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexErr;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexHis;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexNotify;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;

/**
 * @Description:扫描流程的配置参数[单例类]
 * @author wangjt
 * @Date 2012-9-13
 */
public final class ErrDataIndexConfig implements IConfig {
	private static final ErrDataIndexConfig DATA_INDEX_CONFIG = new ErrDataIndexConfig();

	private static Long seqId=(long) 0;
	public static ErrDataIndexConfig getInstance() {
		return DATA_INDEX_CONFIG;
	}

	private ErrDataIndexConfig() {
	}

	/**
	 * 扫描数据一次扫描的条数<BR>
	 */
	@Override
	public int getScanOneTimeCount() {
		return FlowConst.DATA_INDEX_SCAN_ONE_TIME_COUNT;
	}

	/**
	 * 增加数据到队列中时 ，队列不能超过的数据总数
	 */
	@Override
	public int getQueueMaxCount() {
		return FlowConst.DATA_INDEX_QUEUE_MAX_COUNT;
	}

	/**
	 * 处理的线程数，等于队列数   ERR设死为1个线程
	 */
	@Override
	public int getDealThreadCount() {
//		return FlowConst.DATA_INDEX_DEAL_THREAD_COUNT;
		 return 1;
	}

	/**
	 * 需要扫描的分表总数    ERR写为1
	 */
	@Override
	public int getSubTableCount() {
		return 1;
	}

	/**
	 * 大索引表名称        ERR未用到
	 */
	@Override
	public String getTableName() {
		return "JD.I_DATA_INDEX";
	}

	/**
	 * 小索引表的前缀    ERR未用到
	 */
	@Override
	public String getSubTableNamePrefix() {
		return "JD.I_DATA_INDEX_SUB_";// CD.I_DATA_INDEX_SUB_[0-7]_[1-7]
	}

	
	/**
	 * 扫描大索引的查询条件，包括order by条件
	 */
	@Override
	public Query newTableQuery() {
		Query<IDataIndexErr> query = QueryBuilder.create(IDataIndexErr.class);
		// 按照commit_date、soNbr从小到大排序读取 
		// 20150928 修改为 按照commit_date、doneCode从小到大排序读取 
		query.addCondition(IDataIndexErr.Field.errorCode, Operator.NOT_EQUALS,EnumCodeExDefine.SYNC_MDB_ERR);
		query.addOrderBy(true, IDataIndexErr.Field.commitDate, IDataIndexErr.Field.doneCode,IDataIndexErr.Field.soNbr); 
		return query;
	}

	/**
	 * ERR表查询时的条件   
	 */
	@Override
	public Query newSubTableQuery() {
		Query<?> query = new IDataIndexErr().getQuery();
		query.addOrderBy(true, IDataIndexSub.Field.seqId);// 按照seqId的值，从小到大读取
		return query;
	}

	/**
	 * 小索引表中的seqId
	 */
	@Override
	public Field getSubTableSeqIdField() {
		return IDataIndexSub.Field.seqId;
	}

	/**
	 * 获取对象的seqId值      Err改造，没有seqId，不参考改字段
	 */
	@Override
	public Long getValueOfSeqId(DataObject dataObject) {
		Long seqId=(long) 0;
		return seqId++;
	}

	/**
	 * 获取对象的soNbr值
	 */
	@Override
	public Long getValueOfSoNbr(DataObject dataObject) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		return iDataIndexSub.getSoNbr();
	}

	/**
	 * 获取对象的sts值
	 */
	@Override
	public Short getValueOfDbSuccSts(DataObject dataObject) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		return iDataIndexSub.getDbSuccSts();
	}

	/**
	 * 获取业务参考字段的值
	 * 
	 * @param dataObject
	 *            :大索引表的对象：IDataIndex
	 */
	@Override
	public Long getValueOfBusiField(DataObject dataObject) {
		IDataIndex iDataIndex = (IDataIndex) dataObject;
		// 分组参考字段
		Long refId = iDataIndex.getCustId();
		if (refId == null) {
			refId = iDataIndex.getAcctId();
		}
		if (refId == null) {
			refId = iDataIndex.getUserId();
		}
		return refId;
	}

	/**
	 * 获取业务参考字段的值
	 * 
	 * @param dataObject
	 *            :小索引表的对象：IDataIndexSub
	 */
	@Override
	public Long getValueOfBusiFieldFromSub(DataObject dataObject) {
		IDataIndexErr iDataIndexErr = (IDataIndexErr) dataObject;
		// 分组参考字段
		Long refId = iDataIndexErr.getCustId();
		if (refId == null) {
			refId = iDataIndexErr.getAcctId();
		}
		if (refId == null) {
			refId = iDataIndexErr.getUserId();
		}
		return refId;
	}

	/**
	 * 获取大索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObject(DataObject dataObject) {
		Long soNbr = ((IDataIndex) dataObject).getSoNbr();
		IDataIndex iDataIndex = new IDataIndex();
		iDataIndex.getQuery().addCondition(IDataIndex.Field.soNbr, soNbr);
		return iDataIndex;
	}

	/**
	 * 获取Err表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObjectSub(DataObject dataObject) {
		
		IDataIndexErr errData=(IDataIndexErr) dataObject;
		IDataIndexErr iDataIndexErr = new IDataIndexErr();
		iDataIndexErr.getQuery().addCondition(IDataIndexErr.Field.soNbr, errData.getSoNbr());
		return iDataIndexErr;
	}

	/**
	 * 获取小索引表中，需要修改状态为 1 的对象
	 */
	@Override
	public DataObject getUpdateDataObjectSub(DataObject dataObject) {
		Long seqId = ((IDataIndexSub) dataObject).getSeqId();
		IDataIndexSub iDataIndexSub = new IDataIndexSub();
		iDataIndexSub.getQuery().addCondition(IDataIndexSub.Field.seqId, seqId);

		iDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_TRUE);

		return iDataIndexSub;
	}

	/**
	 * 从大索引表对象，转换为小索引表对象
	 */
	@Override
	public DataObject convertToSubDataObject(DataObject dataObject) {
		IDataIndex iDataIndex = (IDataIndex) dataObject;
		IDataIndexSub iDataIndexSub = new IDataIndexSub();
		iDataIndexSub.setSeqId(SeqIdGenerate.getIDataIndexSubSeq());// 生成一个SEQ
		iDataIndexSub.setCustId(iDataIndex.getCustId());
		iDataIndexSub.setAcctId(iDataIndex.getAcctId());
		iDataIndexSub.setUserId(iDataIndex.getUserId());
		iDataIndexSub.setUpField(iDataIndex.getUpField());
		iDataIndexSub.setRegionCode(iDataIndex.getRegionCode());
		iDataIndexSub.setCountyCode(iDataIndex.getCountyCode());
		iDataIndexSub.setCommitDate(iDataIndex.getCommitDate());
		iDataIndexSub.setDoneCode(iDataIndex.getDoneCode());
		iDataIndexSub.setSoNbr(iDataIndex.getSoNbr());
		iDataIndexSub.setBusiCode(iDataIndex.getBusiCode());
		iDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_FALSE);// 设置为默认值0
		return iDataIndexSub;
	}

	/**
	 * sub表，统一转为IDataIndex ，在context中使用
	 */
	@Override
	public IDataIndex convertToIDataIndex(DataObject dataObject) {
		IDataIndexErr iDataIndexErr = (IDataIndexErr) dataObject;

		IDataIndex iDataIndex = new IDataIndex();
		iDataIndex.setCustId(iDataIndexErr.getCustId());
		iDataIndex.setAcctId(iDataIndexErr.getAcctId());
		iDataIndex.setUserId(iDataIndexErr.getUserId());
		iDataIndex.setUpField(iDataIndexErr.getUpField());
		iDataIndex.setRegionCode(iDataIndexErr.getRegionCode());
		iDataIndex.setCountyCode(iDataIndexErr.getCountyCode());
		iDataIndex.setCommitDate(iDataIndexErr.getCommitDate());
		iDataIndex.setDoneCode(iDataIndexErr.getDoneCode());
		iDataIndex.setSoNbr(iDataIndexErr.getSoNbr());
		iDataIndex.setBusiCode(iDataIndexErr.getBusiCode());
		return iDataIndex;
	}

	@Override
	public DataObject convertToHis(DataObject dataObject, Date dealDate) {
		IDataIndexErr iDataIndexErr = (IDataIndexErr) dataObject;

		IDataIndexHis iDataIndexHis = new IDataIndexHis();
		iDataIndexHis.setCustId(iDataIndexErr.getCustId());
		iDataIndexHis.setAcctId(iDataIndexErr.getAcctId());
		iDataIndexHis.setUserId(iDataIndexErr.getUserId());
		iDataIndexHis.setUpField(iDataIndexErr.getUpField());
		iDataIndexHis.setRegionCode(iDataIndexErr.getRegionCode());
		iDataIndexHis.setCountyCode(iDataIndexErr.getCountyCode());
		iDataIndexHis.setCommitDate(iDataIndexErr.getCommitDate());
		iDataIndexHis.setSoNbr(iDataIndexErr.getSoNbr());
		iDataIndexHis.setDoneCode(iDataIndexErr.getDoneCode());
		iDataIndexHis.setDealDate(dealDate); // 当前时间
		iDataIndexHis.setBusiCode(iDataIndexErr.getBusiCode());
		iDataIndexHis.setRemark("err deal");
		return iDataIndexHis;
	}

	@Override
	public DataObject convertToErr(DataObject dataObject, Date dealDate, Long errorCode, String errorMsg) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		IDataIndexErr iDataIndexErr = new IDataIndexErr();
		iDataIndexErr.setCustId(iDataIndexSub.getCustId());
		iDataIndexErr.setAcctId(iDataIndexSub.getAcctId());
		iDataIndexErr.setUserId(iDataIndexSub.getUserId());
		iDataIndexErr.setUpField(iDataIndexSub.getUpField());
		iDataIndexErr.setRegionCode(iDataIndexSub.getRegionCode());
		iDataIndexErr.setCountyCode(iDataIndexSub.getCountyCode());
		iDataIndexErr.setCommitDate(iDataIndexSub.getCommitDate());
		iDataIndexErr.setSoNbr(iDataIndexSub.getSoNbr());
		iDataIndexErr.setDoneCode(iDataIndexSub.getDoneCode());
		iDataIndexErr.setBusiCode(iDataIndexSub.getBusiCode());
		iDataIndexErr.setDealDate(dealDate);// 当前时间
		iDataIndexErr.setErrorCode(errorCode);

		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				// @2012-10-22 wukl 排除信息中包含中文，字段过长问题
				errorMsg = errorMsg.length() < EnumCodeExDefine.ERROR_MSG_LENGTH ? errorMsg.substring(0, errorMsg.length()) : errorMsg
						.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
				if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
					errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH * 2 - errorMsg.getBytes().length);
				}

			}
			iDataIndexErr.setErrorMsg(errorMsg);
		}

		return iDataIndexErr;
	}
	
	public String getErrMsg(String errorMsg){
		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				// @2012-10-22 wukl 排除信息中包含中文，字段过长问题
				errorMsg = errorMsg.length() < EnumCodeExDefine.ERROR_MSG_LENGTH ? errorMsg.substring(0, errorMsg.length()) : errorMsg
						.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
				if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
					errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH * 2 - errorMsg.getBytes().length);
				}

			}
			return errorMsg;
		}
		return errorMsg;
	}
	
	public DataObject updateErr(DataObject dataObject, Long errorCode, String errorMsg) {
		IDataIndexErr iDataIndexErr = (IDataIndexErr) dataObject;
		iDataIndexErr.setDealDate(DateUtil.currentDate());
		iDataIndexErr.setErrorCode(errorCode);
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				// @2012-10-22 wukl 排除信息中包含中文，字段过长问题
				errorMsg = errorMsg.length() < EnumCodeExDefine.ERROR_MSG_LENGTH ? errorMsg.substring(0, errorMsg.length()) : errorMsg
						.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
				if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
					errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH * 2 - errorMsg.getBytes().length);
				}

			}
			iDataIndexErr.setErrorMsg(errorMsg);
		}
		
		return iDataIndexErr;
	}

	@Override
	public DataObject convertToNotify(DataObject dataObject, Long errorCode, String errorMsg) {
		IDataIndexErr IDataIndexErr = (IDataIndexErr) dataObject;

		IDataIndexErr iDataIndexErr = new IDataIndexErr();
		iDataIndexErr.setSoNbr(IDataIndexErr.getSoNbr());
		iDataIndexErr.setCustId(IDataIndexErr.getCustId());
		iDataIndexErr.setAcctId(IDataIndexErr.getAcctId());
		iDataIndexErr.setUserId(IDataIndexErr.getUserId());
		iDataIndexErr.setUpField(IDataIndexErr.getUpField());
		iDataIndexErr.setRegionCode(IDataIndexErr.getRegionCode());
		iDataIndexErr.setCountyCode(IDataIndexErr.getCountyCode());
		iDataIndexErr.setCommitDate(IDataIndexErr.getCommitDate());
		iDataIndexErr.setDoneCode(IDataIndexErr.getDoneCode());
		iDataIndexErr.setBusiCode(IDataIndexErr.getBusiCode());

		iDataIndexErr.setDealDate(DateUtil.currentDate());// 当前时间
		iDataIndexErr.setErrorCode(errorCode);

		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				// @2012-10-22 wukl 排除信息中包含中文，字段过长问题
				errorMsg = errorMsg.length() < EnumCodeExDefine.ERROR_MSG_LENGTH ? errorMsg.substring(0, errorMsg.length()) : errorMsg
						.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
				if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
					errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH * 2 - errorMsg.getBytes().length);
				}

			}
			iDataIndexErr.setErrorMsg(errorMsg);
		}

		return iDataIndexErr;
	}

	/**
	 * 设置为入库成功，上发mdb失败状态：1:DB_SUCC_STS_TRUE
	 */
	@Override
	public void setDbSuccStsTrue(DataObject dataObject) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		iDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_TRUE);
	}

	@Override
	public void setDBSuccStsFalse(DataObject dataObject) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		iDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_FALSE);

	}
	
	@Override
	public void setDealSts(DataObject dataObject, short value) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		iDataIndexSub.setDbSuccSts(value);
	}

	@Override
	public Long getValueOfBusiCode(DataObject dataObject) {
		IDataIndexErr iDataIndexErr = (IDataIndexErr) dataObject;
		return iDataIndexErr.getBusiCode() == null ? 0L : iDataIndexErr.getBusiCode();
	}

	@Override
	public Integer getSleepTime() {
		// TODO Auto-generated method stub
		return FlowConst.DATA_INDEX_DISPATCH_SLEEP_TIME;
	}

	@Override
	public String getValueOfKey(DataObject dataObject, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueOfUserId(DataObject dataObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueOfCustId(DataObject dataObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueOfAcctId(DataObject dataObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataObject convertToSubDataObjectWithOrgCust(DataObject dataObject, Long custId) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
