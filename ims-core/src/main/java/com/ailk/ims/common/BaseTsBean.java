package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;

import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;

import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.ts.TSUtil;
import com.ailk.ims.ts.TsReadCondition;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;

/**
 * @Description: 优化版上发流程基础类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-5-18
 * @Date 2012-06-07 yangjh 产品退订上发condition语句增加effectiveTime的读取
 * @Date 2012-06-07 yangjh 上面的新加的东西删除 重载到自己的类中去
 * @Date 2012-07-18 yangjh : bug 52016 处理失败没有处理dealCount问题
 */
public abstract class BaseTsBean extends ContextBean {
	
	/**
	 * 是否批量处理
	 * 如果是批量模式，则一次性把所有查询出来的数据都传给deal方法
	 * 如果是非批量模式，则循环所有查询出来的数据，并单条传给deal方法
	 * @return
	 */
	public abstract boolean isDealBatch();
	
	/**
	 * 对应扫描表的数据库实体对象类
	 * 比如扫描ImsProdOrderSync表，则返回ImsProdOrderSync.class
	 * @return
	 */
	public abstract Class<? extends DataObject> getJavaClass();
	
	/**
	 * 对应扫描表的结果表的数据库实体对象类
	 * 比如结果表ImsProdOrderSyncRst表，则返回ImsProdOrderSyncRst.class
	 * 处理成功或者超过指定失败次数的数据会存入该表。
	 * 如果不需要结果表，则直接返回null
	 * @return
	 */
	public abstract Class<? extends DataObject> getRstJavaClass();
	
	/**
	 * 进行具体的逻辑处理.比如上发到CRM，上发到MDB等。
	 * @param dbObj,如果是批量模式，传入的是List<DataObject>，单条模式传入的是DataObject
	 * @return 需要返回处理结果
	 */
	public abstract CBSErrorMsg deal(Object dbObj);
	
	
	/**
	 * 对处理成功的接口表数据的操作，默认逻辑是：1、如果有结果表则插入结果表  2、删除原记录
	 * @param dbObjList
	 * @return
	 */
	public void dealSuccess(DataObject dbObj) throws Exception{
		if(this.getRstJavaClass() != null){
			//插入结果表
			DataObject hisObj = trans2RstJavaObject(dbObj);
			//插入结果表后需要把error信息清除掉
			ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_ERROR_MSG, "");
	    	ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_ERROR_CODE, 0);
			DBUtil.insert(hisObj);
		}
		
		//把接口表数据删除
		Field keyField = DBUtil.getJefField(this.getJavaClass(), getKeyField());
		DBUtil.deleteByCondition(this.getJavaClass(),TSUtil.getIdCondition(dbObj,keyField));
	}
	
	/**
	 * 对处理失败的接口表数据的操作，默认逻辑是：
	 * 1、如果未超过最大允许失败次数，则修改当前记录的deal_count.
	 * 2、如果超过最大允许失败次数，则转移到结果表中，即删除原 表记录，插入结果表(前提是有结果表)
	 * @param dbObj
	 * @param error
	 * @throws Exception
	 */
	public void dealFail(DataObject dbObj,CBSErrorMsg error) throws Exception{
		int maxCount = SysUtil.getInt(SysCodeDefine.busi.RECORD_DEAL_COUNT_VALUE, 3);// 重试次数
		Integer dealCount = (Integer)ClassUtil.getPropertyValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_DEAL_COUNT);//当前处理次数
		if(dealCount == null)
			dealCount = 0;
		ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_ERROR_MSG, error.getError_msg());
    	ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_ERROR_CODE, error.getResult_code());
    	ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_DEAL_DATE, context.getRequestDate());
    	Field keyField = DBUtil.getJefField(this.getJavaClass(), getKeyField());
    	DBCondition idCondition = TSUtil.getIdCondition(dbObj,keyField);
    	
    	if (dealCount >= maxCount)
        {
			//插入历史表
			if(this.getRstJavaClass() != null){
				DataObject hisObj = trans2RstJavaObject(dbObj);
				DBUtil.insert(hisObj);
			}
			
			//把接口表数据删除
			DBUtil.deleteByCondition(this.getJavaClass(),idCondition);
        }
        else
        { 	// 如果未超限，则接口表中对应的记录deal_sts改为3，deal_count+1，记录error_msg，error_code。
//        	DataObject updateValue = (DataObject)ClassUtil.instance(this.getJavaClass());
        	//2012-07-18 yangjh bug：52016 处理失败没有处理dealCount问题
        	ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_STS, EnumCodeDefine.SYNC_DEAL_STS_FAIL);
        	ClassUtil.setFieldValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_DEAL_COUNT, dealCount + 1);
        	
        	DBUtil.updateByCondition(dbObj, idCondition);
        }
	}
	
	
	/**
	 * 创建扫描接口表的读取条件。
	 * 每个SyncReadCondition代表一组条件，多个SyncReadCondition之间是“Or”的查询关系，SyncReadCondition里可以进行每组条件以及排序设置
	 * 比如要扫描2批数据：未处理数据、之前处理失败数据，那么SyncReadCondition[0]是sts=未处理，SyncReadCondition[1]是sts=失败，
	 * 那么程序会自动先读取出sts=未处理的数据，再读取出sts=失败的数据，如果有最大条数限制，则先读的优先.
	 * 默认创建的是
	 * 1、先读取之前处理失败的数据 
	 * 2、再读取之前处理超时的数据
	 * 3、再读取尚未处理的数据
	 * 如果不符合业务，则需要重载。
	 * @return
	 */
	public List<TsReadCondition> createReadConditions(){
		Class<? extends DataObject> dbTable = getJavaClass();
		
		List<TsReadCondition> result = new ArrayList<TsReadCondition>();
		
		//创建排序条件
		Field createDateField = DBUtil.getJefField(dbTable, ConstantDefine.ENTITY_FIELD_CREATE_DATE);
        OrderCondition[] orders = new OrderCondition[]{new OrderCondition(createDateField)};// 根据createDate排序
        
        
        TsReadCondition readCondition = new TsReadCondition();
        Field stsField = DBUtil.getJefField(dbTable, ConstantDefine.ENTITY_FIELD_SYNC_STS);
        Field dealDateField = DBUtil.getJefField(dbTable, ConstantDefine.ENTITY_FIELD_SYNC_DEAL_DATE);
        
        //先读取之前处理失败的数据
        readCondition.addDBConditions(new DBCondition(stsField, EnumCodeDefine.SYNC_DEAL_STS_FAIL));
        readCondition.addOrderConditions(orders);
        result.add(readCondition);
        
        //再读取之前处理超时的数据
        readCondition = new TsReadCondition();
        readCondition.addDBConditions(
        	new DBCondition(stsField, EnumCodeDefine.SYNC_DEAL_STS_HANDLING),
        	new DBCondition(dealDateField, TSUtil.getTimeOutDate(context.getRequestDate()),Operator.LESS)//deal_time小于算出来最早时间
        );
        readCondition.addOrderConditions(orders);
        result.add(readCondition);
        
        
        //再读取尚未处理的数据
        readCondition = new TsReadCondition();
        readCondition.addDBConditions(
        	        new DBCondition(stsField, EnumCodeDefine.SYNC_DEAL_STS_INITIAL)
        );
        readCondition.addOrderConditions(orders);
        result.add(readCondition);
		
		
		return result;
	}
	
	
	/**
	 * 根据之前创建的createReadConditions读取数据。有最大条数限制。
	 * @param conditions
	 * @return
	 * @throws Exception 
	 */
	public List<DataObject> read(List<TsReadCondition> conditions) throws Exception{
		Class<? extends DataObject> dbClass = this.getJavaClass();
		 // 同步,只能由一个线程来读取一张表中的数据
		synchronized (dbClass){
			int maxCount = SysUtil.getInt(SysCodeDefine.busi.ONE_TIME_DEAL_RECORD_COUNT, 1000);// 一次处理记录条数
			List<DataObject> result = new ArrayList<DataObject>();
			
			for(TsReadCondition cond : conditions){
				imsLogger.info("remaining count to read : "+maxCount, context);
				if(maxCount <= 0)
					continue;//如果已经达到最大条数了，则直接返回了
				DBCondition[] dbConditions = cond.getDbConditions();
				OrderCondition[] orderConditions = cond.getOrderConditions();
				List<DataObject> list = DBUtil.query(
						dbClass, 
						orderConditions, 
						new IntRange(0, maxCount), 
						dbConditions
				);
				
				if(CommonUtil.isNotEmpty(list)){
					result.addAll(list);
					maxCount -= list.size();//剩余最大条数需要更新
				}
			}
			if(CommonUtil.isNotEmpty(result))
    		{
    			//立即把读取出来的数据deal_sts=2(表示在处理中),deal_date=当前时间,以防后续重复读取
    			DataObject updateValue = (DataObject)ClassUtil.instance(dbClass);
    			ClassUtil.setFieldValue(updateValue, ConstantDefine.ENTITY_FIELD_SYNC_STS, EnumCodeDefine.SYNC_DEAL_STS_HANDLING);
    			ClassUtil.setFieldValue(updateValue, ConstantDefine.ENTITY_FIELD_SYNC_DEAL_DATE, context.getRequestDate());
    			
    			Long[] ids = new Long[result.size()];
    			Field keyField = DBUtil.getJefField(this.getJavaClass(), getKeyField());
    			for(int i=0;i< result.size();i++){
    				ids[i] = TSUtil.getId(result.get(i),keyField);
    			}
    			
    			DBUtil.updateByCondition(
    					updateValue, 
    					new DBCondition(
    							DBUtil.getJefField(dbClass, getKeyField()),
    							ids,
    							Operator.IN
    					)
    			);
    		}
			
			return result;
		}
		
	}
	/**
	 * 把数据库实体对象转换成对应的结果表实体对象。
	 * 默认两者的结构尽量都是定义成一致。
	 * 如果有特殊，则需要重载。
	 * @param dbObj
	 * @return
	 */
	public DataObject trans2RstJavaObject(DataObject dbObj){
		
		Class<? extends DataObject> dbHisClass = this.getRstJavaClass();
		
		DataObject rstObj = (DataObject)ClassUtil.instance(dbHisClass);
		
		TSUtil.copyJava2His(dbObj,rstObj);//把sdl对象中的数据拷贝到其对应的ims对象中
		
	 
			imsLogger.dump("****** [db->rst]data object", dbObj);
			imsLogger.dump("****** [db->rst]rst object", rstObj);
		 
		
		return rstObj;
	}
	
	/**
	 * 返回对应表里的主键字段，默认是ID作为主键
	 * @return
	 */
	public String getKeyField(){
		return ConstantDefine.ENTITY_FIELD_SYNC_ID;
	}
	
	
	/**
	 * deal后的结果处理
	 * @author wuyj 2012-8-29
	 * @param dbObjList,deal传入的原数据
	 * @param error,deal返回的错误对象
	 * @throws Exception
	 * @Date 2012-09-04 yangjh : 改用泛型public <T extends DataObject> void  dealResult(List<T> dbObjList,CBSErrorMsg error) throws Exception{
	 */
	public <T extends DataObject> void  dealResult(List<T> dbObjList,CBSErrorMsg error) throws Exception{
	    IMSContext context = this.getContext();
	    
	    String error_msg = (error == null) ? null : error.getError_msg();
        String error_hint = (error == null) ? null : error.getHint();
        Long error_code = (error == null) ? null : error.getResult_code();
        
        boolean isSuccess = error == null || error.getResult_code() == null || 0L == error.getResult_code();
        
        long start = 0;
        for (int i=0;i< dbObjList.size();i++){
            start = System.currentTimeMillis();
            DataObject dbObj = dbObjList.get(i);
            Long recordId = null;
            try {
                recordId = (Long)ClassUtil.getPropertyValue(dbObj, ConstantDefine.ENTITY_FIELD_SYNC_ID);
            } catch (Exception e2) {
            	imsLogger.error(e2,e2);
            }
            imsLogger.info("result of record["+recordId+"] : ["+error_code+"]"+error_msg+":"+error_hint,context);
            
            if(isSuccess){
                //处理成功
                imsLogger.info("begin to deal success",context);
                this.dealSuccess(dbObj);
                imsLogger.info("finish to deal success",start,context);
            }else{
                //处理失败
                imsLogger.info("begin to deal success",context);
                this.dealFail(dbObj, error);
                imsLogger.info("finish to deal success",start,context);
            }
            imsLogger.info("finish to deal record : "+recordId,context);
        }
            
	}
}
