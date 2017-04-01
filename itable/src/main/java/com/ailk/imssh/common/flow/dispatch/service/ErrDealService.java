package com.ailk.imssh.common.flow.dispatch.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jef.common.wrapper.IntRange;
import jef.database.AbstractDbClient;
import jef.database.Batch;
import jef.database.Condition.Operator;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.easyframe.web.common.session.RequestContext;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexErr;

public class ErrDealService implements IErrDealService {

	private ImsLogger imsLogger = new ImsLogger(ErrDealService.class);
	
	@Override
	public void errDeal(String YYYYMM) throws Exception{
    	CommonInit.commonInitWithTs();
    	ITableUtil.requestInit();
    	imsLogger.error("============start deal err data from i_data_index_err every 3 minutes,subtable param :"+YYYYMM+"=============");
    	Query<IDataIndexErr> query = QueryBuilder.create(IDataIndexErr.class);
    	query.addCondition(IDataIndexErr.Field.errorCode, Operator.NOT_EQUALS,EnumCodeExDefine.SYNC_MDB_ERR);
		query.addOrderBy(true, IDataIndexErr.Field.commitDate, IDataIndexErr.Field.doneCode,IDataIndexErr.Field.soNbr); 
		
		String errTableName = "JD.I_DATA_INDEX_ERR_" + YYYYMM;
		MyTableName myTableName = new MyTableName(errTableName);
		
		// 往context里面设置分表字段
		RequestContext requestContext = ContextHolder.getRequestContext();

		requestContext.put(ConstantExDefine.ROUTER_KEY_COMMIT_DATE,
				DateUtil.getFormatDate(new Date(Integer.parseInt(YYYYMM.substring(0,4))-1900, Integer.parseInt(YYYYMM.substring(4,6))-1,1), DateUtil.DATE_FORMAT_YYYYMMDD));
		
		List<IDataIndexErr> delIDataIndexErrList = new ArrayList<IDataIndexErr>();
		List<IDataIndex> insertIDataIndexList = new ArrayList<IDataIndex>();
		
		AbstractDbClient dbClient=DBUtil.getCommonDao().getClient();
		
		while(true){
				Long timeBegin=System.currentTimeMillis();
				
				List<IDataIndexErr> allList = dbClient.select(query, new IntRange(0, 3000),myTableName);
				imsLogger.error("============ i_data_index_err_"+YYYYMM+" records size:"+allList.size()+"============");
				if (CommonUtil.isNotEmpty(allList)) {
			        for (IDataIndexErr iDataIndexErr : allList){
			        	delIDataIndexErrList.add(getDeleteDataObject(iDataIndexErr));
			        	IDataIndex iDataIndex=convertToIDataIndexObject(iDataIndexErr);
			        	insertIDataIndexList.add(iDataIndex);
			        }
					
			        //删除i_data_index_err表数据
			        Batch<IDataIndexErr> tableBatch = dbClient.startBatchDelete(delIDataIndexErrList.get(0));
			        tableBatch.add(delIDataIndexErrList);
			        tableBatch.commit();
			        delIDataIndexErrList.clear();
			        
			        // 增加数据到索引表i_data_index
			        Batch<IDataIndex> subTableBatch = null;
		            String TableName = "JD.I_DATA_INDEX";
		            imsLogger.error("======== "+allList.size()+" i_data_index_err records move to table: JD.I_DATA_INDEX===========");
		            subTableBatch = dbClient.startBatchInsert(insertIDataIndexList.get(0), TableName);
		            subTableBatch.add(insertIDataIndexList);
		            subTableBatch.commit();
		            insertIDataIndexList.clear();
					
				}
				
				Long timeEnd=System.currentTimeMillis();
				Long subTime=timeEnd-timeBegin;
				if(subTime<180000){
					imsLogger.error("================err data progress sleep "+(180000-subTime)+" ms .........===========");
					ITableUtil.sleep(180000-subTime);
				}
		}
    }

	
	public IDataIndexErr getDeleteDataObject(IDataIndexErr iDataIndexErr) {
		Long soNbr = iDataIndexErr.getSoNbr();
		IDataIndexErr iDIndexErr = new IDataIndexErr();
		iDIndexErr.getQuery().addCondition(IDataIndexErr.Field.soNbr, soNbr);
		return iDIndexErr;
	}
    
    public IDataIndex convertToIDataIndexObject(IDataIndexErr iDataIndexErr){
    	IDataIndex iDataIndex=new IDataIndex();
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
}
