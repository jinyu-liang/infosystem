package com.ailk.imssh.common.flow.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import jef.database.DataObject;
import jef.database.Field;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;

import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.consts.SeqIdGenerate;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexCreditErr;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexErr;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexHis;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexNotify;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;

/**
 * @Description:扫描流程的配置参数[单例类]
 * @author wangjt
 * @Date 2012-9-13
 */
public final class DataIndexConfig implements IConfig {
	private static final DataIndexConfig DATA_INDEX_CONFIG = new DataIndexConfig();

	public static DataIndexConfig getInstance() {
		return DATA_INDEX_CONFIG;
	}

	private DataIndexConfig() {
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
		return "JD.I_DATA_INDEX";
	}

	/**
	 * 小索引表的前缀
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
		Query<IDataIndex> query = QueryBuilder.create(IDataIndex.class);
		// 按照commit_date、soNbr从小到大排序读取 
		// 20150928 修改为 按照commit_date、doneCode从小到大排序读取 
		query.addOrderBy(true, IDataIndex.Field.commitDate, IDataIndex.Field.doneCode,IDataIndex.Field.soNbr); 
		return query;
	}

	/**
	 * 小索引表查询时的条件
	 */
	@Override
	public Query newSubTableQuery() {
		Query<?> query = new IDataIndexSub().getQuery();
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
	 * 获取对象的seqId值
	 */
	@Override
	public Long getValueOfSeqId(DataObject dataObject) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		return iDataIndexSub.getSeqId();
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
	 * 获取对象的userId值
	 * @param dataObject
	 * @return
	 */
    public String getValueOfUserId(DataObject dataObject){
    	IDataIndex iDataIndex = (IDataIndex) dataObject;
		if(iDataIndex.getUserId()==null){
			return "";
		}else{
			return iDataIndex.getUserId().toString();
		}

    }
	/**
	 * 获取对象的acctId值
	 * @param dataObject
	 * @return
	 */
    public String getValueOfAcctId(DataObject dataObject){
    	IDataIndex iDataIndex = (IDataIndex) dataObject;
		if(iDataIndex.getAcctId()==null){
			return "";
		}else{
			return iDataIndex.getAcctId().toString();
		}

    }
	/**
	 * 获取对象的custId值
	 * @param dataObject
	 * @return
	 */
    public String getValueOfCustId(DataObject dataObject){
    	IDataIndex iDataIndex = (IDataIndex) dataObject;
		if(iDataIndex.getCustId()==null){
			return "";
		}else{
			return iDataIndex.getCustId().toString();
		}

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
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		// 分组参考字段
		Long refId = iDataIndexSub.getCustId();
		if (refId == null) {
			refId = iDataIndexSub.getAcctId();
		}
		if (refId == null) {
			refId = iDataIndexSub.getUserId();
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
	 * 获取小索引表中，要删除的对象
	 */
	@Override
	public DataObject getDeleteDataObjectSub(DataObject dataObject) {
		Long seqId = ((IDataIndexSub) dataObject).getSeqId();
		IDataIndexSub iDataIndexSub = new IDataIndexSub();
		iDataIndexSub.getQuery().addCondition(IDataIndexSub.Field.seqId, seqId);
		return iDataIndexSub;
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
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;

		IDataIndex iDataIndex = new IDataIndex();
		iDataIndex.setCustId(iDataIndexSub.getCustId());
		iDataIndex.setAcctId(iDataIndexSub.getAcctId());
		iDataIndex.setUserId(iDataIndexSub.getUserId());
		iDataIndex.setUpField(iDataIndexSub.getUpField());
		iDataIndex.setRegionCode(iDataIndexSub.getRegionCode());
		iDataIndex.setCountyCode(iDataIndexSub.getCountyCode());
		iDataIndex.setCommitDate(iDataIndexSub.getCommitDate());
		iDataIndex.setDoneCode(iDataIndexSub.getDoneCode());
		iDataIndex.setSoNbr(iDataIndexSub.getSoNbr());
		iDataIndex.setBusiCode(iDataIndexSub.getBusiCode());
		return iDataIndex;
	}

	@Override
	public DataObject convertToHis(DataObject dataObject, Date dealDate) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;

		IDataIndexHis iDataIndexHis = new IDataIndexHis();
		iDataIndexHis.setCustId(iDataIndexSub.getOrgCustId());
		iDataIndexHis.setAcctId(iDataIndexSub.getAcctId());
		iDataIndexHis.setUserId(iDataIndexSub.getUserId());
		iDataIndexHis.setUpField(iDataIndexSub.getUpField());
		iDataIndexHis.setRegionCode(iDataIndexSub.getRegionCode());
		iDataIndexHis.setCountyCode(iDataIndexSub.getCountyCode());
		iDataIndexHis.setCommitDate(iDataIndexSub.getCommitDate());
		iDataIndexHis.setSoNbr(iDataIndexSub.getSoNbr());
		iDataIndexHis.setDoneCode(iDataIndexSub.getDoneCode());
		iDataIndexHis.setDealDate(dealDate); // 当前时间
		iDataIndexHis.setBusiCode(iDataIndexSub.getBusiCode());
		return iDataIndexHis;
	}

	@Override
	public DataObject convertToErr(DataObject dataObject, Date dealDate, Long errorCode, String errorMsg) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		if(iDataIndexSub.getBusiCode()==20000000000l){
			
			IDataIndexCreditErr iDataIndexErr=new IDataIndexCreditErr();
			iDataIndexErr.setCustId(iDataIndexSub.getOrgCustId());
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
			
		}else{
			IDataIndexErr iDataIndexErr = new IDataIndexErr();
			iDataIndexErr.setCustId(iDataIndexSub.getOrgCustId());
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
	
	}

	@Override
	public DataObject convertToNotify(DataObject dataObject, Long errorCode, String errorMsg) {
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;

		IDataIndexNotify iDataIndexNotify = new IDataIndexNotify();
		iDataIndexNotify.setSoNbr(iDataIndexSub.getSoNbr());
		iDataIndexNotify.setCustId(iDataIndexSub.getCustId());
		iDataIndexNotify.setAcctId(iDataIndexSub.getAcctId());
		iDataIndexNotify.setUserId(iDataIndexSub.getUserId());
		iDataIndexNotify.setUpField(iDataIndexSub.getUpField());
		iDataIndexNotify.setRegionCode(iDataIndexSub.getRegionCode());
		iDataIndexNotify.setCountyCode(iDataIndexSub.getCountyCode());
		iDataIndexNotify.setCommitDate(iDataIndexSub.getCommitDate());
		iDataIndexNotify.setDoneCode(iDataIndexSub.getDoneCode());
		iDataIndexNotify.setBusiCode(iDataIndexSub.getBusiCode());

		iDataIndexNotify.setErrorDate(DateUtil.currentDate());// 当前时间
		iDataIndexNotify.setErrorCode(errorCode);

		if (errorMsg != null && !errorMsg.isEmpty()) {
			if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
				// @2012-10-22 wukl 排除信息中包含中文，字段过长问题
				errorMsg = errorMsg.length() < EnumCodeExDefine.ERROR_MSG_LENGTH ? errorMsg.substring(0, errorMsg.length()) : errorMsg
						.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH);
				if (errorMsg.getBytes().length > EnumCodeExDefine.ERROR_MSG_LENGTH) {
					errorMsg = errorMsg.substring(0, EnumCodeExDefine.ERROR_MSG_LENGTH * 2 - errorMsg.getBytes().length);
				}

			}
			iDataIndexNotify.setErrorMsg(errorMsg);
		}

		return iDataIndexNotify;
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
		IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
		return iDataIndexSub.getBusiCode() == null ? 0L : iDataIndexSub.getBusiCode();
	}

	@Override
	public Integer getSleepTime() {
		// TODO Auto-generated method stub
		return FlowConst.DATA_INDEX_DISPATCH_SLEEP_TIME;
	}

	@Override
	public String getValueOfKey(DataObject dataObject,int type) {
		StringBuffer sb=new StringBuffer("");
		if(type==1){
			IDataIndex iDataIndex=new IDataIndex();
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
			IDataIndexSub iDataIndexSub=new IDataIndexSub();
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
	public DataObject convertToSubDataObjectWithOrgCust(DataObject dataObject,Long custId) {
		IDataIndex iDataIndex = (IDataIndex) dataObject;
		IDataIndexSub iDataIndexSub = new IDataIndexSub();
		iDataIndexSub.setSeqId(SeqIdGenerate.getIDataIndexSubSeq());// 生成一个SEQ
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
