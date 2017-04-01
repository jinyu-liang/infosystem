package com.ailk.imssh.common.flow.config;

import java.util.Calendar;
import java.util.Date;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;
import jef.tools.DateUtils;

import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.consts.SeqIdGenerate;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.jd.entity.IUserDepositNotify;
import com.ailk.openbilling.persistence.jd.entity.IUserDepositNtSub;

/**
 * @Description:扫描流程的配置参数[单例类]
 * @author wangjt
 * @Date 2012-9-13
 */
public final class UserDepositConfig implements IConfig {
	private static final UserDepositConfig DATA_INDEX_CONFIG = new UserDepositConfig();

	public static UserDepositConfig getInstance() {
		return DATA_INDEX_CONFIG;
	}

	private UserDepositConfig() {
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
		return FlowConst.DATA_INDEX_DEAL_THREAD_COUNT;
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
		return "JD.I_USER_DEPOSIT_NOTIFY";
	}

	/**
	 * 小索引表的前缀
	 */
	@Override
	public String getSubTableNamePrefix() {
		return "JD.I_USER_DEPOSIT_NT_SUB_";// CD.I_DATA_INDEX_SUB_[0-7]_[1-7]
	}

	/**
	 * 扫描大索引的查询条件，包括order by条件
	 */
	@Override
	public Query newTableQuery() {
		Query<IUserDepositNotify> query = QueryBuilder.create(IUserDepositNotify.class);
		Date currentDate = DateUtil.currentDate();
		Date delayBeginDate = DateUtils.monthBegin(currentDate);
		DateUtils.addHour(delayBeginDate, FlowConst.IMS_DEPOSIT_CANCEL_DELAY_TIME);
		//每月1号0-8点 不延迟10分钟分发处理
		if (currentDate.after(delayBeginDate)) {
			query.addCondition(IUserDepositNotify.Field.createDate,Operator.LESS, DateUtil.offsetDate(currentDate, Calendar.MINUTE, FlowConst.IMS_DEPOSIT_DELAY_MINUTES));
		}
		query.addOrderBy(true, IUserDepositNotify.Field.createDate, IUserDepositNotify.Field.validDate); 
		return query;
	}

	/**
	 * 小索引表查询时的条件
	 */
	@Override
	public Query newSubTableQuery() {
		Query<?> query = new IUserDepositNtSub().getQuery();
		query.addOrderBy(true, IUserDepositNtSub.Field.seqId);// 按照seqId的值，从小到大读取
		return query;
	}

	/**
	 * 小索引表中的seqId
	 */
	@Override
	public Field getSubTableSeqIdField() {
		return IUserDepositNtSub.Field.seqId;
	}

	/**
	 * 获取对象的seqId值
	 */
	@Override
	public Long getValueOfSeqId(DataObject dataObject) {
		IUserDepositNtSub iUserDepositNtSub = (IUserDepositNtSub) dataObject;
		return iUserDepositNtSub.getSeqId();
	}

	/**
	 * 获取对象的soNbr值
	 */
	@Override
	public Long getValueOfSoNbr(DataObject dataObject) {
		IUserDepositNtSub iDataIndexSub = (IUserDepositNtSub) dataObject;
		return iDataIndexSub.getSoNbr();
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
		IUserDepositNotify iDataIndex = (IUserDepositNotify) dataObject;
		// 分组参考字段
		Long refId = iDataIndex.getServId();
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
		IUserDepositNtSub iDataIndexSub = (IUserDepositNtSub) dataObject;
		// 分组参考字段
		Long refId = iDataIndexSub.getServId();
		return refId;
	}

	/**
	 * 获取大索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObject(DataObject dataObject) {
		IUserDepositNotify notify=(IUserDepositNotify) dataObject;
		IUserDepositNotify iDataIndex = new IUserDepositNotify();
		iDataIndex.getQuery().addCondition(IUserDepositNotify.Field.servId, notify.getServId());
		iDataIndex.getQuery().addCondition(IUserDepositNotify.Field.promoId, notify.getPromoId());
		iDataIndex.getQuery().addCondition(IUserDepositNotify.Field.condId, notify.getCondId());
		iDataIndex.getQuery().addCondition(IUserDepositNotify.Field.validDate, notify.getValidDate());
		iDataIndex.getQuery().addCondition(IUserDepositNotify.Field.changeFlag, notify.getChangeFlag());
		iDataIndex.getQuery().addCondition(IUserDepositNotify.Field.createDate, notify.getCreateDate());
		return iDataIndex;
	}

	/**
	 * 获取小索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObjectSub(DataObject dataObject) {
		Long seqId = ((IUserDepositNtSub) dataObject).getSeqId();
		IUserDepositNtSub iDataIndexSub = new IUserDepositNtSub();
		iDataIndexSub.getQuery().addCondition(IUserDepositNtSub.Field.seqId, seqId);
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
		IUserDepositNotify iDataIndex = (IUserDepositNotify) dataObject;
		IUserDepositNtSub iDataIndexSub = new IUserDepositNtSub();
		iDataIndexSub.setBusiType(iDataIndex.getBusiType());
		iDataIndexSub.setChangeFlag(iDataIndex.getChangeFlag());
		iDataIndexSub.setCondId(iDataIndex.getCondId());
		iDataIndexSub.setCreateDate(iDataIndex.getCreateDate());
		iDataIndexSub.setPromoId(iDataIndex.getPromoId());
		iDataIndexSub.setRegionCode(iDataIndex.getRegionCode());
		iDataIndexSub.setServId(iDataIndex.getServId());
		iDataIndexSub.setSoNbr(iDataIndex.getSoNbr());
		iDataIndexSub.setSrcType(iDataIndex.getSrcType());
		iDataIndexSub.setValidDate(iDataIndex.getValidDate());
		iDataIndexSub.setSeqId(SeqIdGenerate.getIDataIndexSubSeq());
		return iDataIndexSub;
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
	public void setDealSts(DataObject dataObject, short value) {
		// TODO Auto-generated method stub
		
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

	
	public IUserDepositNotify convertToIUserDeposti(DataObject dataObject) {
		
		IUserDepositNtSub iDataIndexSub = (IUserDepositNtSub) dataObject;
		IUserDepositNotify iDataIndex = new IUserDepositNotify();
		iDataIndex.setBusiType(iDataIndexSub.getBusiType());
		iDataIndex.setChangeFlag(iDataIndexSub.getChangeFlag());
		iDataIndex.setCondId(iDataIndexSub.getCondId());
		iDataIndex.setCreateDate(iDataIndexSub.getCreateDate());
		iDataIndex.setPromoId(iDataIndexSub.getPromoId());
		iDataIndex.setRegionCode(iDataIndexSub.getRegionCode());
		iDataIndex.setServId(iDataIndexSub.getServId());
		iDataIndex.setSoNbr(iDataIndexSub.getSoNbr());
		iDataIndex.setSrcType(iDataIndexSub.getSrcType());
		iDataIndex.setValidDate(iDataIndexSub.getValidDate());
		return iDataIndex;
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
