package com.ailk.imssh.common.flow.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import jef.database.DataObject;
import jef.database.Field;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.consts.SeqIdGenerate;
import com.ailk.openbilling.persistence.itable.entity.IBatchDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IBatchDataIndexErr;
import com.ailk.openbilling.persistence.itable.entity.IBatchDataIndexHis;
import com.ailk.openbilling.persistence.itable.entity.IBatchDataIndexNotify;
import com.ailk.openbilling.persistence.itable.entity.IBatchDataIndexSub;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;

/**
 * @Description:扫描流程的配置参数[单例类]
 * @author wangjt
 * @Date 2012-9-13
 */
public final class BatchDataIndexConfig implements IConfig {
	private static final BatchDataIndexConfig BATCH_DATA_INDEX_CONFIG = new BatchDataIndexConfig();

	public static BatchDataIndexConfig getInstance() {
		return BATCH_DATA_INDEX_CONFIG;
	}

	private BatchDataIndexConfig() {
	}

	/**
	 * 扫描数据一次扫描的条数<BR>
	 */
	@Override
	public int getScanOneTimeCount() {
		return FlowConst.BATCH_INDEX_SCAN_ONE_TIME_COUNT;
	}

	/**
	 * 增加数据到队列中时 ，队列不能超过的数据总数
	 */
	@Override
	public int getQueueMaxCount() {
		return FlowConst.BATCH_INDEX_QUEUE_MAX_COUNT;
	}

	/**
	 * 处理的线程数，等于队列数
	 */
	@Override
	public int getDealThreadCount() {
		return FlowConst.BATCH_INDEX_DEAL_THREAD_COUNT;
	}

	/**
	 * 需要扫描的分表总数
	 */
	@Override
	public int getSubTableCount() {
		return FlowConst.BATCH_INDEX_SUB_TABLE_COUNT;
	}

	/**
	 * 大索引表名称
	 */
	@Override
	public String getTableName() {
		return "JD.I_BATCH_DATA_INDEX";
	}

	/**
	 * 小索引表的前缀
	 */
	@Override
	public String getSubTableNamePrefix() {
		return "JD.I_BATCH_DATA_INDEX_SUB_";// CD.I_BATCH_DATA_INDEX_SUB_[0-7]
	}

	/**
	 * 扫描大索引的查询条件，包括order by条件
	 */
	@Override
	public Query newTableQuery() {
		Query<IBatchDataIndex> query = QueryBuilder.create(IBatchDataIndex.class);
		// 按照commit_date、soNbr从小到大排序读取
		query.addOrderBy(true, IBatchDataIndex.Field.commitDate, IBatchDataIndex.Field.soNbr);
		return query;
	}

	/**
	 * 小索引表查询时的条件，包括order by条件
	 */
	@Override
	public Query newSubTableQuery() {
		Query<?> query = new IBatchDataIndexSub().getQuery();
		query.addOrderBy(true, IBatchDataIndexSub.Field.seqId);// 按照seqId的值，从小到大读取
		return query;
	}

	/**
	 * 小索引表中的seqId
	 */
	@Override
	public Field getSubTableSeqIdField() {
		return IBatchDataIndexSub.Field.seqId;
	}

	/**
	 * 获取对象的seqId值
	 */
	@Override
	public Long getValueOfSeqId(DataObject dataObject) {
		IBatchDataIndexSub iDataIndexSub = (IBatchDataIndexSub) dataObject;
		return iDataIndexSub.getSeqId();
	}

	/**
	 * 获取对象的soNbr值
	 */
	@Override
	public Long getValueOfSoNbr(DataObject dataObject) {
		IBatchDataIndexSub iDataIndexSub = (IBatchDataIndexSub) dataObject;
		return iDataIndexSub.getSoNbr();
	}

	/**
	 * 获取对象的sts值
	 */
	@Override
	public Short getValueOfDbSuccSts(DataObject dataObject) {
		IBatchDataIndexSub iDataIndexSub = (IBatchDataIndexSub) dataObject;
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
		IBatchDataIndex iBatchDataIndex = (IBatchDataIndex) dataObject;
		// 分组参考字段
		Long refId = iBatchDataIndex.getCustId();
		if (refId == null) {
			refId = iBatchDataIndex.getAcctId();
		}
		if (refId == null) {
			refId = iBatchDataIndex.getUserId();
		}
		return refId;
	}

	/**
	 * 获取业务参考字段的值
	 * 
	 * @param dataObject
	 *            :小索引表的对象：IDataIndex
	 */
	@Override
	public Long getValueOfBusiFieldFromSub(DataObject dataObject) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;
		// 分组参考字段
		Long refId = iBatchDataIndexSub.getCustId();
		if (refId == null) {
			refId = iBatchDataIndexSub.getAcctId();
		}
		if (refId == null) {
			refId = iBatchDataIndexSub.getUserId();
		}
		return refId;
	}

	/**
	 * 获取大索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObject(DataObject dataObject) {
		Long soNbr = ((IBatchDataIndex) dataObject).getSoNbr();
		IBatchDataIndex iBatchDataIndex = new IBatchDataIndex();
		iBatchDataIndex.getQuery().addCondition(IBatchDataIndex.Field.soNbr, soNbr);
		return iBatchDataIndex;
	}

	/**
	 * 获取小索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObjectSub(DataObject dataObject) {
		Long seqId = ((IBatchDataIndexSub) dataObject).getSeqId();
		IBatchDataIndexSub iBatchDataIndexSub = new IBatchDataIndexSub();
		iBatchDataIndexSub.getQuery().addCondition(IBatchDataIndexSub.Field.seqId, seqId);
		return iBatchDataIndexSub;
	}

	/**
	 * 获取小索引表中，需要修改状态为 1 的对象
	 */
	@Override
	public DataObject getUpdateDataObjectSub(DataObject dataObject) {
		Long seqId = ((IBatchDataIndexSub) dataObject).getSeqId();
		IBatchDataIndexSub iDataIndexSub = new IBatchDataIndexSub();
		iDataIndexSub.getQuery().addCondition(IDataIndexSub.Field.seqId, seqId);

		iDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_TRUE);

		return iDataIndexSub;
	}

	/**
	 * 从大索引表对象，转换为小索引表对象
	 */
	@Override
	public DataObject convertToSubDataObject(DataObject dataObject) {
		IBatchDataIndex iBatchDataIndex = (IBatchDataIndex) dataObject;
		IBatchDataIndexSub iBatchDataIndexSub = new IBatchDataIndexSub();
		iBatchDataIndexSub.setSeqId(SeqIdGenerate.getIDataIndexSubSeq());// 生成一个SEQ
		iBatchDataIndexSub.setCustId(iBatchDataIndex.getCustId());
		iBatchDataIndexSub.setAcctId(iBatchDataIndex.getAcctId());
		iBatchDataIndexSub.setUserId(iBatchDataIndex.getUserId());
		iBatchDataIndexSub.setUpField(iBatchDataIndex.getUpField());
		iBatchDataIndexSub.setRegionCode(iBatchDataIndex.getRegionCode());
		iBatchDataIndexSub.setCountyCode(iBatchDataIndex.getCountyCode());
		iBatchDataIndexSub.setCommitDate(iBatchDataIndex.getCommitDate());
		iBatchDataIndexSub.setDoneCode(iBatchDataIndex.getDoneCode());
		iBatchDataIndexSub.setSoNbr(iBatchDataIndex.getSoNbr());
		iBatchDataIndexSub.setBusiCode(iBatchDataIndex.getBusiCode());
		iBatchDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_FALSE);// 设置为默认值0
		return iBatchDataIndexSub;
	}

	/**
	 * 统一转为IDataIndex ，在context中使用
	 */
	@Override
	public IDataIndex convertToIDataIndex(DataObject dataObject) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;

		IDataIndex iDataIndex = new IDataIndex();
		iDataIndex.setCustId(iBatchDataIndexSub.getCustId());
		iDataIndex.setAcctId(iBatchDataIndexSub.getAcctId());
		iDataIndex.setUserId(iBatchDataIndexSub.getUserId());
		iDataIndex.setUpField(iBatchDataIndexSub.getUpField());
		iDataIndex.setRegionCode(iBatchDataIndexSub.getRegionCode());
		iDataIndex.setCountyCode(iBatchDataIndexSub.getCountyCode());
		iDataIndex.setCommitDate(iBatchDataIndexSub.getCommitDate());
		iDataIndex.setDoneCode(iBatchDataIndexSub.getDoneCode());
		iDataIndex.setSoNbr(iBatchDataIndexSub.getSoNbr());
		iDataIndex.setBusiCode(iBatchDataIndexSub.getBusiCode());
		return iDataIndex;
	}

	@Override
	public DataObject convertToHis(DataObject dataObject, Date dealDate) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;

		IBatchDataIndexHis iBatchDataIndexHis = new IBatchDataIndexHis();
		iBatchDataIndexHis.setCustId(iBatchDataIndexSub.getCustId());
		iBatchDataIndexHis.setAcctId(iBatchDataIndexSub.getAcctId());
		iBatchDataIndexHis.setUserId(iBatchDataIndexSub.getUserId());
		iBatchDataIndexHis.setUpField(iBatchDataIndexSub.getUpField());
		iBatchDataIndexHis.setRegionCode(iBatchDataIndexSub.getRegionCode());
		iBatchDataIndexHis.setCountyCode(iBatchDataIndexSub.getCountyCode());
		iBatchDataIndexHis.setCommitDate(iBatchDataIndexSub.getCommitDate());
		iBatchDataIndexHis.setDoneCode(iBatchDataIndexSub.getDoneCode());
		iBatchDataIndexHis.setSoNbr(iBatchDataIndexSub.getSoNbr());
		iBatchDataIndexHis.setBusiCode(iBatchDataIndexSub.getBusiCode());
		iBatchDataIndexHis.setDealDate(dealDate); // 当前时间
		return iBatchDataIndexHis;
	}

	@Override
	public DataObject convertToErr(DataObject dataObject, Date dealDate, Long errorCode, String errorMsg) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;

		IBatchDataIndexErr iBatchDataIndexErr = new IBatchDataIndexErr();
		iBatchDataIndexErr.setCustId(iBatchDataIndexSub.getOrgCustId());
		iBatchDataIndexErr.setAcctId(iBatchDataIndexSub.getAcctId());
		iBatchDataIndexErr.setUserId(iBatchDataIndexSub.getUserId());
		iBatchDataIndexErr.setUpField(iBatchDataIndexSub.getUpField());
		iBatchDataIndexErr.setRegionCode(iBatchDataIndexSub.getRegionCode());
		iBatchDataIndexErr.setCountyCode(iBatchDataIndexSub.getCountyCode());
		iBatchDataIndexErr.setCommitDate(iBatchDataIndexSub.getCommitDate());
		iBatchDataIndexErr.setDoneCode(iBatchDataIndexSub.getDoneCode());
		iBatchDataIndexErr.setSoNbr(iBatchDataIndexSub.getSoNbr());
		iBatchDataIndexErr.setBusiCode(iBatchDataIndexSub.getBusiCode());
		iBatchDataIndexErr.setDealDate(dealDate);// 当前时间
		iBatchDataIndexErr.setErrorCode(errorCode);

		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.length() > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
			}
			iBatchDataIndexErr.setErrorMsg(errorMsg);
		}

		return iBatchDataIndexErr;
	}

	@Override
	public DataObject convertToNotify(DataObject dataObject, Long errorCode, String errorMsg) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;

		IBatchDataIndexNotify iBatchDataIndexNotify = new IBatchDataIndexNotify();
		iBatchDataIndexNotify.setSoNbr(iBatchDataIndexSub.getSoNbr());
		iBatchDataIndexNotify.setCustId(iBatchDataIndexSub.getCustId());
		iBatchDataIndexNotify.setAcctId(iBatchDataIndexSub.getAcctId());
		iBatchDataIndexNotify.setUserId(iBatchDataIndexSub.getUserId());
		iBatchDataIndexNotify.setUpField(iBatchDataIndexSub.getUpField());
		iBatchDataIndexNotify.setRegionCode(iBatchDataIndexSub.getRegionCode());
		iBatchDataIndexNotify.setCountyCode(iBatchDataIndexSub.getCountyCode());
		iBatchDataIndexNotify.setCommitDate(iBatchDataIndexSub.getCommitDate());
		iBatchDataIndexNotify.setDoneCode(iBatchDataIndexSub.getDoneCode());
		iBatchDataIndexNotify.setBusiCode(iBatchDataIndexSub.getBusiCode());

		iBatchDataIndexNotify.setErrorDate(DateUtil.currentDate());// 当前时间
		iBatchDataIndexNotify.setErrorCode(errorCode);

		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.length() > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
			}
			iBatchDataIndexNotify.setErrorMsg(errorMsg);
		}

		return iBatchDataIndexNotify;
	}

	/**
	 * 设置为入库成功，上发mdb失败状态：1:DB_SUCC_STS_TRUE
	 */
	@Override
	public void setDbSuccStsTrue(DataObject dataObject) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;
		iBatchDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_TRUE);
	}

	@Override
	public void setDBSuccStsFalse(DataObject dataObject) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;
		iBatchDataIndexSub.setDbSuccSts(FlowConst.DB_SUCC_STS_FALSE);

	}
	
	@Override
	public void setDealSts(DataObject dataObject, short value) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;
		iBatchDataIndexSub.setDbSuccSts(value);
	}

	@Override
	public Long getValueOfBusiCode(DataObject dataObject) {
		IBatchDataIndexSub iBatchDataIndexSub = (IBatchDataIndexSub) dataObject;
		return iBatchDataIndexSub.getBusiCode() == null ? 0L : iBatchDataIndexSub.getBusiCode();
	}

	@Override
	public Integer getSleepTime() {
		// TODO Auto-generated method stub
		return  FlowConst.DATA_INDEX_DISPATCH_SLEEP_TIME;
	}

	@Override
	public String getValueOfKey(DataObject dataObject, int type) {
		StringBuffer sb=new StringBuffer("");
		if(type==1){
			IBatchDataIndex iDataIndex=new IBatchDataIndex();
	        BeanUtils.copyProperties(dataObject, iDataIndex);
	        
	        
	        if(Arrays.asList(EnumCodeExDefine.USER_ID_BUSI_CODE).contains(iDataIndex.getBusiCode().toString())){
	        	sb.append(iDataIndex.getUserId());
	        }else{
	        if(iDataIndex.getCustId()==null){
				sb.append(",");
			}else{
				sb.append(iDataIndex.getCustId().toString());
				sb.append(",");
			}
			if(iDataIndex.getUserId()==null){
				sb.append(",");
			}else{
				sb.append(iDataIndex.getUserId().toString());
				sb.append(",");
			}
			if(iDataIndex.getAcctId()==null){
			}else{
				sb.append(iDataIndex.getAcctId());
			}
	        }
		}else{
			IBatchDataIndexSub iDataIndexSub=new IBatchDataIndexSub();
	        BeanUtils.copyProperties(dataObject, iDataIndexSub);
	        
	        
	        if(Arrays.asList(EnumCodeExDefine.USER_ID_BUSI_CODE).contains(iDataIndexSub.getBusiCode().toString())){
	        	sb.append(iDataIndexSub.getUserId());
	        }else{
			if(iDataIndexSub.getOrgCustId()==null){
				sb.append(",");
			}else{
				sb.append(iDataIndexSub.getOrgCustId().toString());
				sb.append(",");
			}
			if(iDataIndexSub.getUserId()==null){
				sb.append(",");
			}else{
				sb.append(iDataIndexSub.getUserId().toString());
				sb.append(",");
			}
			if(iDataIndexSub.getAcctId()==null){
			}else{
				sb.append(iDataIndexSub.getAcctId());
			}
	        }
		}

		return sb.toString();
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
		IBatchDataIndex iDataIndex = (IBatchDataIndex) dataObject;
		IBatchDataIndexSub iDataIndexSub = new IBatchDataIndexSub();
		iDataIndexSub.setSeqId(SeqIdGenerate.getIBatchDataIndexSubSeq());// 生成一个SEQ
		iDataIndexSub.setCustId(custId);
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
		iDataIndexSub.setOrgCustId(iDataIndex.getCustId());		
		return iDataIndexSub;
	}



	
}
