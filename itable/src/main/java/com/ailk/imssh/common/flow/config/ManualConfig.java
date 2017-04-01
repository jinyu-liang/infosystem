package com.ailk.imssh.common.flow.config;

import java.util.Date;

import jef.database.DataObject;
import jef.database.Field;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;

import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

public class ManualConfig implements IConfig {
	private static final ManualConfig DATA_INDEX_CONFIG = new ManualConfig();
	
	
	public static ManualConfig getInstance() {
		return DATA_INDEX_CONFIG;
	}

	private ManualConfig() {
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
	 * 处理的线程数，等于队列数
	 */
	@Override
	public int getDealThreadCount() {
		return FlowConst.MANUAL_DEAL_THREAD_COUNT;
		// return 1;
	}

	/**
	 * 需要扫描的分表总数
	 */
	@Override
	public int getSubTableCount() {
		return FlowConst.DATA_INDEX_SUB_TABLE_COUNT;
	}

	/**
	 * 大索引表名称
	 */
	@Override
	public String getTableName() {
		return "JD.IMS_MANUAL_MODIFY";
	}

	/**
	 * 小索引表的前缀
	 */
	@Override
	public String getSubTableNamePrefix() {
		return "JD.IMS_MANUAL_MODIFY";// CD.I_DATA_INDEX_SUB_[0-7]_[1-7]
	}

	/**
	 * 扫描大索引的查询条件，包括order by条件
	 */
	@Override
	public Query newTableQuery() {
		Query<ImsManualModify> query = QueryBuilder.create(ImsManualModify.class);

		query.addOrderBy(true, ImsManualModify.Field.seqId); 
		return query;
	}

	/**
	 * 小索引表查询时的条件
	 */
	@Override
	public Query newSubTableQuery() {
		Query<?> query = new ImsManualModify().getQuery();
		query.addOrderBy(true, ImsManualModify.Field.seqId);// 按照seqId的值，从小到大读取
		return query;
	}

	/**
	 * 小索引表中的seqId
	 */
	@Override
	public Field getSubTableSeqIdField() {
		return ImsManualModify.Field.seqId;
	}

	/**
	 * 获取对象的seqId值
	 */
	@Override
	public Long getValueOfSeqId(DataObject dataObject) {
		ImsManualModify iDataIndexSub = (ImsManualModify) dataObject;
		// 分组参考字段
		return  iDataIndexSub.getSeqId();
	}

	/**
	 * 获取对象的soNbr值
	 */
	@Override
	public Long getValueOfSoNbr(DataObject dataObject) {
		return null;
	}

	/**
	 * 获取对象的sts值
	 */
	@Override
	public Short getValueOfDbSuccSts(DataObject dataObject) {
		
		return 0;
	}

	/**
	 * 获取业务参考字段的值
	 * 
	 * @param dataObject
	 *            :大索引表的对象：IDataIndex
	 */
	@Override
	public Long getValueOfBusiField(DataObject dataObject) {
		ImsManualModify iDataIndexSub = (ImsManualModify) dataObject;
		// 分组参考字段
		Long refId = iDataIndexSub.getSeqId();
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
		ImsManualModify iDataIndexSub = (ImsManualModify) dataObject;
		// 分组参考字段
		Long refId = iDataIndexSub.getSeqId();
		return refId;
	}

	/**
	 * 获取大索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObject(DataObject dataObject) {
		return null;
	}

	/**
	 * 获取小索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObjectSub(DataObject dataObject) {
		Long seqId = ((ImsManualModify) dataObject).getSeqId();
		ImsManualModify iDataIndexSub = new ImsManualModify();
		iDataIndexSub.getQuery().addCondition(ImsManualModify.Field.seqId, seqId);
		return iDataIndexSub;
	}

	/**
	 * 获取小索引表中，需要修改状态为 1 的对象
	 */
	@Override
	public DataObject getUpdateDataObjectSub(DataObject dataObject) {
		
		return null;
	}

	/**
	 * 从大索引表对象，转换为小索引表对象
	 */
	@Override
	public DataObject convertToSubDataObject(DataObject dataObject) {
		return null;
	}

	/**
	 * sub表，统一转为IDataIndex ，在context中使用
	 */
	@Override
	public IDataIndex convertToIDataIndex(DataObject dataObject) {
		
		return null;
	}

	@Override
	public DataObject convertToHis(DataObject dataObject, Date dealDate) {
		
		return null;
	}

	@Override
	public DataObject convertToErr(DataObject dataObject, Date dealDate, Long errorCode, String errorMsg) {
		

		return null;
	}

	@Override
	public DataObject convertToNotify(DataObject dataObject, Long errorCode, String errorMsg) {
		

		return null;
	}

	/**
	 * 设置为入库成功，上发mdb失败状态：1:DB_SUCC_STS_TRUE
	 */
	@Override
	public void setDbSuccStsTrue(DataObject dataObject) {
		
	}

	@Override
	public void setDBSuccStsFalse(DataObject dataObject) {
		
	}

	@Override
	public Long getValueOfBusiCode(DataObject dataObject) {
		
		return 0L;
	}

	@Override
	public Integer getSleepTime() {
		// TODO Auto-generated method stub
		return FlowConst.DATA_INDEX_DISPATCH_SLEEP_TIME;
	}

	
	public ImsManualModify convertToIUserDeposti(DataObject dataObject) {
		
		ImsManualModify iDataIndexSub = (ImsManualModify) dataObject;
		
		return iDataIndexSub;
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
	public String getValueOfKey(DataObject dataObject, int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataObject convertToSubDataObjectWithOrgCust(DataObject dataObject, Long custId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDealSts(DataObject dataObject, short value) {
		// TODO Auto-generated method stub
		
	}

}
