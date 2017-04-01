package com.ailk.imssh.common.cmp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jef.common.wrapper.IntRange;
import jef.database.Condition;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.DataObjectMap;
import jef.database.IConditionField;
import jef.database.IConditionField.And;
import jef.database.IConditionField.Or;
import jef.database.query.Query;
import jef.database.query.RefField;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBExistsCond;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.JoinPart;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.bean.ContextExBean;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;

/**
 * @Description:所有组件类的基类
 * @author wangjt
 * @Date 2012-5-11
 * @Date 2012-09-05 wukl list采用拷贝方式，采用oraginResult.addAll(result)方式，oraginResult的值随result值变化
 */
public class BaseCmp extends ContextExBean
{
	protected String[] dbkey = {"account_db11","account_db21"};
	
	public void insertWithAllCache(DataObject dm,DBCondition... conditions){
		DBUtil.insert(dm);
		if(context.isDiffException()){
			  List<DataObject> list = new ArrayList<DataObject>();
		      list.add(dm);
		      context.cacheList(list);
		}else{
		List<DataObject> list = DBUtil.query(dm.getClass(),conditions);
		context.cacheList(list);
		}
	}
	
    public void insert(DataObject dm) throws IMSException
    {
        if (dm == null)
        {
            return;
        }
        List<DataObject> list = new ArrayList<DataObject>();
        list.add(dm);

        insertBatch(list);
    }

    public void insertBatch(List<? extends DataObject> dmList) throws IMSException
    {
        if (CommonUtil.isEmpty(dmList))
        {
            return;
        }
        for (DataObject dm : dmList)
        {
            wrapInsertDataObject(dm);
        }
        DBUtil.insertBatch(dmList);
        context.cacheList(dmList);
    }

    /**
     * lijc3 2013-12-18 迁移region_code插入新数据 会将插入新表数据重置生效时间，并缓存新插入的数据以及老的失效数据
     * 
     * @param dmList
     * @param valid_date
     * @throws IMSException
     */
    public void insertBatchByTransferRegionCode(List<? extends DataObject> dmList, Date valid_date, Integer newRegion,Integer countyCode)
            throws IMSException
    {
        if (CommonUtil.isEmpty(dmList))
        {
            return;
        }
        List<DataObject> newList = new ArrayList<DataObject>();
        List<DataObject> notValidNewList=new ArrayList<DataObject>();
        for (DataObject dm : dmList)
        {
            java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_expireDate != null && f_validDate != null)
            {
                try
                {
                    Date validDate = (Date) ClassUtil.getFieldValue(dm, f_validDate);
                    if (validDate != null && validDate.before(valid_date))
                    {
                        // 1 插入新数据，新region_code，valid_date,so_date
                        DataObject newDm = IMSUtil.copyDataObject(dm);
                        java.lang.reflect.Field f_soDate = ClassUtil.getField(newDm.getClass(),
                                ConstantDefine.ENTITY_FIELD_SO_DATE);
                        if (f_soDate != null)
                        {
                            ClassUtil.setFieldValue(newDm, f_soDate, context.getRequestDate());
                        }
                        // 新valid_date
                        ClassUtil.setFieldValue(newDm,
                                ClassUtil.getField(newDm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE), valid_date);
                        // 新region_code
                        java.lang.reflect.Field f_regionCode = ClassUtil.getField(newDm.getClass(),
                                ConstantDefine.ENTITY_FIELD_REGION_CODE);
                        if (f_regionCode != null)
                        {
                            ClassUtil.setFieldValue(newDm, f_regionCode, newRegion);
                        }
                        java.lang.reflect.Field f_countyCode = ClassUtil.getField(dm.getClass(),
                                ConstantExDefine.ENTITY_FIELD_COUNTY_CODE);
                        if (f_countyCode != null)
                        {
                            ClassUtil.setFieldValue(newDm, f_countyCode, countyCode);
                        }
                        
                        // 2 将老数据置为失效
                        ClassUtil.setFieldValue(dm,
                                ClassUtil.getField(newDm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE), valid_date);
                        newList.add(newDm);
                    }
                    else
                    {
                        // 如果是没有生效的数据，则直接在原来的记录上修改region_code和so_date
                        java.lang.reflect.Field f_soDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
                        if (f_soDate != null)
                        {
                            ClassUtil.setFieldValue(dm, f_soDate, context.getRequestDate());
                        }
                        // 设置成新的region_code
                        java.lang.reflect.Field f_regionCode = ClassUtil.getField(dm.getClass(),
                                ConstantDefine.ENTITY_FIELD_REGION_CODE);
                        if (f_regionCode != null)
                        {
                            ClassUtil.setFieldValue(dm, f_regionCode, newRegion);
                        }
                        
                        java.lang.reflect.Field f_countyCode = ClassUtil.getField(dm.getClass(),
                                ConstantExDefine.ENTITY_FIELD_COUNTY_CODE);
                        if (f_countyCode != null)
                        {
                            ClassUtil.setFieldValue(dm, f_countyCode, countyCode);
                        }
                        //未生效的数据也要插入到db里面去。
                        notValidNewList.add(dm);
                    }
                }
                catch (Exception e)
                {
                    throw new IMSException(e);
                }
            }

            /*
             * DataObject newDm=IMSUtil.copyDataObject(dm); wrapChgOwnerInsertDataObjectNoSetSoNbr(newDm,valid_date);
             * //设置成新的region_code try { java.lang.reflect.Field f_regionCode = ClassUtil.getField(newDm.getClass(),
             * ConstantDefine.ENTITY_FIELD_REGION_CODE); if (f_regionCode != null) { ClassUtil.setFieldValue(newDm, f_regionCode,
             * newRegion); } } catch (Exception e) { throw new IMSException(e); } wrapTransferOldDataObjectExpireDate(dm,
             * valid_date);
             */
        }
        //这部分缓存在dmList中
        DBUtil.insertBatch(notValidNewList);
        DBUtil.insertBatch(newList);
        context.cacheList(newList);
        context.cacheList(dmList);
    }

    public <T extends DataObject> List<T> query(Class<? extends DataObject> dmClass, DBCondition... conditions)
            throws IMSException
    {
        return DBUtil.query(dmClass, wrapQueryConditions(conditions));

    }

    /**
     * @Description: 支持分页，排序
     * @param <T>
     * @param dmClass
     * @param range,分页，new IntRange(10,100)表示第10到100条，
     * @param order，排序
     * @param conditions
     * @return
     * @throws IMSException
     */
    public <T extends DataObject> List<T> query(Class<? extends DataObject> dmClass, OrderCondition[] order, IntRange range,
            DBCondition... conditions) throws IMSException
    {
        return DBUtil.query(dmClass, order, range, wrapQueryConditions(conditions));
    }

    public <T extends DataObject> T querySingle(Class<? extends DataObject> dmClass, DBCondition... conditions)
            throws IMSException
    {
        List<? extends DataObject> result = query(dmClass, conditions);
        if (CommonUtil.isEmpty(result))
            return null;
        return (T) result.get(0);
    }

    public <T extends DataObject> List<T> deleteByCondition(Class<T> dmClass, DBCondition... conditions)
    {
        return deleteByCondition(dmClass, null, conditions);
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param <T>
     * @param dmClass
     * @param expireDate
     * @param conditions
     * @return 2012-09-20 zengxr noquery for update and delete
     */
    public <T extends DataObject> List<T> deleteByConditionNoQuery(Class<T> dmClass, Date expireDate, List<T> result,
            DBCondition... conditions)
    {
        DBCondition[] wrapConditions = wrapQueryConditions(conditions);
        if (CommonUtil.isEmpty(result))
            return null;

        try
        {
            DataObject value = dmClass.newInstance();
            wrapDeleteDataObject(value, expireDate);// 设置expire_date为当前时间作为更新
            DBUtil.updateByCondition(value, wrapConditions);// 把符合条件的记录的expire_date先更新掉

            // List<T> newList = new ArrayList<T>();

            for (T dm : result)
            {

                wrapDeleteDataObject(dm, expireDate);// 设置之前查询出来的记录，设置expire_date，然后后续缓存
                // lijc3 20121218 上海删除立即生效的情况下，不用插入第二条数据
                // 2012-02-06 wuyujie : 如果是未生效数据，则不用插入新记录的，防止出现主键冲突
                // Date validDate = (Date) ClassUtil.getFieldValue(dm, ConstantDefine.ENTITY_FIELD_VALID_DATE);
                // if (validDate.after(context.getRequestDate()))
                // continue;
                //
                // T newDm = (T) IMSUtil.copyDataObject(dm);
                //
                // wrapDeleteNewDataObject(newDm, expireDate);// 插入新的数据，其中valid_date和expire_date一致，都是当前时间,so_nbr也是当前
                // newList.add(newDm);
            }
            // DBUtil.insertBatch(newList);
            //
            // result.addAll(newList);

            context.cacheList(result);

            return result;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @param <T>
     * @param dmClass
     * @param expireDate,设置老记录的失效时间，默认是当前时间
     * @param conditions
     * @return
     */
    public <T extends DataObject> List<T> deleteByCondition(Class<T> dmClass, Date expireDate, DBCondition... conditions)
    {
        // 删除操作，都不插入第二条记录 lijc3
        return deleteByCondition_noInsert(dmClass, expireDate, conditions);
        /*
         * DBCondition[] wrapConditions = wrapQueryConditions(conditions); List<T> result = DBUtil.query(dmClass, wrapConditions);
         * if (CommonUtil.isEmpty(result)) return null; try { DataObject value = dmClass.newInstance();
         * wrapDeleteDataObject(value, expireDate);// 设置expire_date为当前时间作为更新 DBUtil.updateByCondition(value, wrapConditions);//
         * 把符合条件的记录的expire_date先更新掉 List<T> newList = new ArrayList<T>(); for (T dm : result) { wrapDeleteDataObject(dm,
         * expireDate);// 设置之前查询出来的记录，设置expire_date，然后后续缓存 // 2012-02-06 wuyujie : 如果是未生效数据，则不用插入新记录的，防止出现主键冲突 Date validDate =
         * (Date) ClassUtil.getFieldValue(dm, ConstantDefine.ENTITY_FIELD_VALID_DATE); if
         * (validDate.after(context.getRequestDate())) continue; T newDm = (T) IMSUtil.copyDataObject(dm);
         * wrapDeleteNewDataObject(newDm, expireDate);// 插入新的数据，其中valid_date和expire_date一致，都是当前时间,so_nbr也是当前 newList.add(newDm); }
         * DBUtil.insertBatch(newList); result.addAll(newList); context.cacheList(result); return result; } catch (Exception e) {
         * throw new IMSException(e); }
         */
    }

    /**
     * @Description: 删除记录时不插入数据
     * @param expireDate,设置老记录的失效时间，默认是当前时间
     * @return 返回更新后的数据
     */
    public <T extends DataObject> List<T> deleteByCondition_noInsert(Class<T> dmClass, Date expireDate, DBCondition... conditions)
    {
        return deleteByCondition_noInsert(dmClass, expireDate, true, conditions);
    }

    //TODO，CD.CM_ROUTE_IDENTITY删除方法，可能会删除
    /**
     * 
     * @param dmClass
     * @param expireDate
     * @param conditions
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public <T extends DataObject> void deleteByName_noInsert(Class<T> dmClass,String dbkey,String tableName,Date expireDate,DBCondition... conditions){
    	DBCondition[] wrapConditions = wrapQueryConditions(conditions);
        List<T> result = DBUtil.query(dmClass, wrapConditions);
        if (CommonUtil.isEmpty(result))
            return;
        try{
        
        	 DataObject value = dmClass.newInstance();
             wrapDeleteDataObject(value, expireDate);// 设置expire_date为当前时间作为更新
             DBUtil.updateWithName(value, dbkey, tableName, wrapConditions);
        	 
        }catch(Exception e){
        	throw new IMSException(e);
        }
    }
    
    public <T extends DataObject> void deleteMode1(Class<T> dmClass, Date expireDate,DBCondition... conditions){
    	
    	try{
    		DBCondition[] wrapConditions = wrapQueryConditions(conditions);
    		DataObject value = dmClass.newInstance();
            wrapDeleteDataObject(value, expireDate);// 设置expire_date为当前时间作为更新
            DBUtil.updateByCondition(value, wrapConditions);
            List<T> result = DBUtil.query(dmClass, conditions);
            context.cacheList(result);
    	}catch(Exception e){
    		throw new IMSException(e);
    	}
    	
    }
    
    /**
     * @Description: 删除记录时不插入数据
     * @param expireDate,设置老记录的失效时间，默认是当前时间
     * @return 返回更新前的数据
     */
    public <T extends DataObject> List<T> deleteByCondition_noInsert(Class<T> dmClass, Date expireDate, boolean isReturnUp,
            DBCondition... conditions)
    {
        DBCondition[] wrapConditions = wrapQueryConditions(conditions);
        List<T> result = DBUtil.query(dmClass, wrapConditions);
        if (CommonUtil.isEmpty(result))
            return null;
        try
        {
            List<T> oraginResult = null;
            if (!isReturnUp)
            {
                oraginResult = new ArrayList<T>();
                // @Date 2012-09-05 wukl list采用拷贝方式，采用oraginResult.addAll(result)方式，oraginResult的值随result值变化
                for (DataObject t : result)
                {
                    DataObject newObj = t.getClass().newInstance();
                    ClassUtil.copy(t, newObj);

                    java.lang.reflect.Field f_validDate = ClassUtil
                            .getField(t.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
                    if (expireDate == null)
                    {
                        expireDate = context.getRequestDate();
                    }
                    if (f_validDate != null)
                    {
                        ClassUtil.setFieldValue(newObj, f_validDate, expireDate);
                    }
                    oraginResult.add((T) newObj);
                }
                // oraginResult.addAll(result);
            }
            DataObject value = dmClass.newInstance();
            wrapDeleteDataObject(value, expireDate);// 设置expire_date为当前时间作为更新
            DBUtil.updateByCondition(value, wrapConditions);
            // 将时间设置为当前时间
            for (DataObject dm : result)
            {
                java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
                if (expireDate == null)
                {
                    expireDate = context.getRequestDate();// 老记录失效时间没有，则默认取当前时间
                }
                if (f_expireDate != null)
                {
                    ClassUtil.setFieldValue(dm, f_expireDate, expireDate);
                }
            }
            context.cacheList(result);
            return isReturnUp ? result : oraginResult;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * 未来生效数据的删除，不保留历史记录，返回删除后的记录 wukl 2012-12-18
     * 
     * @param <T>
     * @param dmClass
     * @param expireDate
     * @param conditions
     * @return
     */
    public <T extends DataObject> List<T> delete_noValid(Class<T> dmClass, DBCondition... conditions)
    {

        try
        {
            List<T> result = (List<T>) DBUtil.deleteByConditionWithReturn(dmClass, conditions);
            if (CommonUtil.isEmpty(result))
                return null;

            // 将时间设置为当前时间
            for (DataObject dm : result)
            {
                java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
                if (f_expireDate != null)
                {
                    ClassUtil.setFieldValue(dm, f_expireDate, context.getRequestDate());
                }
            }
            context.cacheList(result);
            return result;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

	/**
	 * 
	 * @Description
	 * @Date 2016-10-13
	 * 物理删除只删除一次
	 * 仅限于异常接口处理
	 */
	public int deleteByConditionForDiff(Class<? extends DataObject> dmClass, DBCondition... conditions){
		if(isFirstDeleted(dmClass)){
			return DBUtil.deleteByCondition(dmClass, conditions);
		}
		return 0;
	}
	
	/**
	 * @Description
	 * @Date 2016-10-13
	 * 物理删除只删除一次
	 * 仅限于异常接口处理
	 * 并返回删除的实体
	 */
	public List<? extends DataObject> deleteForDiffWithReturn(Class<? extends DataObject> dmClass,
			DBCondition... conditions) throws IMSException {
		if(isFirstDeleted(dmClass)){
		  return DBUtil.deleteByConditionWithReturn(dmClass, conditions);
		}
		return null;
	}
	/***************************************************************************************************/
	/**
	 * 
	 * @Description
	 * 物理删除
	 */
	public int deleteByConditionWithNoUpdate(Class<? extends DataObject> dmClass, DBCondition... conditions){
			return DBUtil.deleteByCondition(dmClass, conditions);
	}

	/**
	 * @Description: 功能同deleteByCondition
	 * 物理删除并返回所有删除的实体
	 */
	public List<? extends DataObject> deleteByConditionWithReturn(Class<? extends DataObject> dmClass,
			DBCondition... conditions) throws IMSException {
		
		return DBUtil.deleteByConditionWithReturn(dmClass, conditions);
	}
    
    /**
     * @Description: 更新
     * @param <T>
     * @param value，需要更新的值，这里面可以设置新纪录的生效时间和失效时间
     * @param oldExpireDate，老记录需要设置的expire_date，默认和新纪录的生效时间保持一致
     * @param conditions
     * @return
     */
    public <T extends DataObject> List<T> updateByCondition(DataObject value, Date oldExpireDate, DBCondition... conditions)
    {
        List<T> result = updateByConditionWithNoCache(null, value, oldExpireDate, oldExpireDate, conditions);
        context.cacheList(result);
        return result;
    }

    /**
     * 更新 wukl 2012-3-15
     * 
     * @param <T>
     * @param value 需要更新的值，这里面可以设置新纪录的生效时间和失效时间
     * @param oldExpireDate 老记录需要设置的expire_date
     * @param newValidDate 新纪录需要设置的valid_date
     * @param conditions
     * @return
     */
    public <T extends DataObject> List<T> updateByCondition(DataObject value, Date oldExpireDate, Date newValidDate,
            DBCondition... conditions)
    {
        List<T> result = updateByConditionWithNoCache(null, value, oldExpireDate, newValidDate, conditions);
        context.cacheList(result);
        return result;
    }
    
    //TODO
    /**
     * CD.CM_ROUTE_IDENTITY修改方法
     * @param result
     * @param value
     * @param dbkey
     * @param tableName
     * @param conditions
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public <T extends DataObject> void updateWithInsertBykey(DataObject value, Date oldExpireDate,Date newValidDate,String dbkey,String tableName,DBCondition... conditions){
         
    	 List<DBCondition> updateCondition = CommonUtil.parseArray2List(conditions);
    	 Date dealTime = context.getRequestDate();
    	 jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
    	 jef.database.Field expire_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
    	 updateCondition.add(new DBCondition(valid_field, dealTime, Operator.LESS_EQUALS));
    	 updateCondition.add(new DBCondition(expire_field, dealTime, Operator.GREAT));
    	 List<T> result = DBUtil.queryByDbKey(value.getClass(),dbkey,conditions);

    	 if(CommonUtil.isEmpty(result)){
    		 //TODO 天津修改方式，有没有记录存在就新增，有就修改
    		 DBUtil.insertWithName(value, dbkey, tableName);
    	 }else{
    		
    		 try{
    			 
    			 List<T> updateList = new ArrayList<T>();
    			 List<T> delList = new ArrayList<T>();
    			 for(T obj : result){
    				 Date objExpireDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
    				 Date objValidDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_VALID_DATE);
    				 
    				 if((objValidDate.getTime() == dealTime.getTime() || objValidDate.before(dealTime)) && objExpireDate.after(dealTime)){
    					 //AND EXPIRE_DATE>:deal_time AND VALID_DATE<:deal_time做UPDATE
    					 //将更新的字段合并进来,并设置失效时间
    					 DBUtil.mergeUpdatedEntityNoSegment(value, obj);
    					 wrapInsertValidate(obj,dealTime);
    					 updateList.add(obj);
    					 
    				 }else if(objValidDate.after(dealTime) && objExpireDate.after(dealTime)){
    					 //AND EXPIRE_DATE>:deal_time AND VALID_DATE>=:deal_time做DELETE
  
    					 delList.add(obj);
    				 }
    			 }
    			 
    			 if(CommonUtil.isNotEmpty(updateList)){
    				 //需要更新,先将旧记录失效，再将新记录插入数据库
    				 DataObject newValue = value.getClass().newInstance();
    				 wrapDeleteDataObject(newValue, context.getRequestDate());
    				 DBUtil.updateWithName(newValue,dbkey, tableName,updateCondition.toArray(new DBCondition[updateCondition.size()]));
    				 DBUtil.insertWithName(updateList.get(0), dbkey,tableName);
    			
    			 }else{
    				 //没有更新的记录，插入新记录
    				 DBUtil.insertWithName(value, dbkey, tableName);
    	    		 
    			 }
    			 
    			 if(CommonUtil.isNotEmpty(delList)){
    				 //需要进行物理删除
    				 List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
    				 delCondition.add(new DBCondition(valid_field, dealTime, Operator.GREAT));
    				 delCondition.add(new DBCondition(expire_field, dealTime, Operator.GREAT));
    				 DBUtil.deleteByDbKey(value.getClass(),dbkey, tableName,delCondition.toArray(new DBCondition[delCondition.size()]));
    				
    			 }
				
    		 }catch (Exception e){
    	        throw new IMSException(e);
    	     }
    	 }
    }
    
    public <T extends DataObject> List<T> updateMode1Segment( DataObject value,DBCondition... conditions){
   	 
   	 List<DBCondition> updateCondition = CommonUtil.parseArray2List(conditions);
   	 Date dealTime = context.getRequestDate();
   	 jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
   	 jef.database.Field expire_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
   	 updateCondition.add(new DBCondition(valid_field, dealTime, Operator.LESS_EQUALS));
   	 updateCondition.add(new DBCondition(expire_field, dealTime, Operator.GREAT));
   	 List<T> result = DBUtil.query(value.getClass(),conditions);
   	 List<T> cacheList = new ArrayList<T>();
   	 if(CommonUtil.isEmpty(result)){
   		 return null;
   	 }else{
   		
   		 try{
   			 List<T> insertList = new ArrayList<T>();
   			 List<T> updateList = new ArrayList<T>();
   			
   			 for(T obj : result){
   				 Date objExpireDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
   				 Date objValidDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_VALID_DATE);
   				 
   				 if((objValidDate.getTime() == dealTime.getTime() || objValidDate.before(dealTime)) && objExpireDate.after(dealTime)){
   					 //AND EXPIRE_DATE>:deal_time AND VALID_DATE<:deal_time做UPDATE
   					 //旧记录要设置失效时间存到updateList，供上发mdb使用
   					 T updateDm =  (T) IMSUtil.copyDataObject(obj);
   					 wrapDeleteDataObject(updateDm,dealTime);
   					 updateList.add(updateDm);
   					//将更新的字段合并进来,设置生效时间为deal_time,存到insertList中
   					 DBUtil.mergeUpdatedEntity(value, obj);
   					 T insertDm = (T) IMSUtil.copyDataObject(obj);
   					 wrapInsertValidate(insertDm, dealTime);
   					 insertList.add(insertDm);
   				 }else{
   					 //既不修改也不删除的数据，也要全量传给mdb
   					 T oriDm = (T) IMSUtil.copyDataObject(obj);
   					 cacheList.add(oriDm);
   				 }
   			 }
   			 
   			 if(CommonUtil.isNotEmpty(updateList)){
   				 //需要更新,先将旧记录失效，再将新记录插入数据库
   				 DataObject newValue = value.getClass().newInstance();
   				 wrapDeleteDataObject(newValue, dealTime);
   				 DBUtil.updateByCondition(newValue, updateCondition.toArray(new DBCondition[updateCondition.size()]));
   				 DBUtil.insertBatch(insertList);
   				 cacheList.addAll(updateList);
   				 cacheList.addAll(insertList);
   			 }else{
   				 //没有更新的记录，直接返回
   				 return null;
   			 }
   	
				
   		 }catch (Exception e){
   	        throw new IMSException(e);
   	     }
   		 
   	 }
   	 if(CommonUtil.isNotEmpty(cacheList)){
   		 context.cacheList(cacheList);
   	 }
   	return cacheList;
   }
    
    public <T extends DataObject> void updateMode1( DataObject value,DBCondition... conditions){
    	 
    	 List<DBCondition> updateCondition = CommonUtil.parseArray2List(conditions);
    	 Date dealTime = context.getRequestDate();
    	 jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
    	 jef.database.Field expire_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
    	 updateCondition.add(new DBCondition(valid_field, dealTime, Operator.LESS_EQUALS));
    	 updateCondition.add(new DBCondition(expire_field, dealTime, Operator.GREAT));
    	 List<T> result = DBUtil.query(value.getClass(),conditions);
    	 List<T> cacheList = new ArrayList<T>();
    	 if(CommonUtil.isEmpty(result)){
    		 DBUtil.insert(value);
    		 T newDm = (T) IMSUtil.copyDataObject(value);
    		 cacheList.add(newDm);
    	 }else{
    		
    		 try{
    			 List<T> insertList = new ArrayList<T>();
    			 List<T> updateList = new ArrayList<T>();
    			 List<T> delList = new ArrayList<T>();
    			 for(T obj : result){
    				 Date objExpireDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
    				 Date objValidDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_VALID_DATE);
    				 
    				 if((objValidDate.getTime() == dealTime.getTime() || objValidDate.before(dealTime)) && objExpireDate.after(dealTime)){
    					 //AND EXPIRE_DATE>:deal_time AND VALID_DATE<:deal_time做UPDATE
    					 //旧记录要设置失效时间存到updateList，供上发mdb使用
    					 T updateDm =  (T) IMSUtil.copyDataObject(obj);
    					 wrapDeleteDataObject(updateDm,dealTime);
    					 updateList.add(updateDm);
    					//将更新的字段合并进来,设置生效时间为deal_time,存到insertList中
    					 DBUtil.mergeUpdatedEntityNoSegment(value, obj);
    					 T insertDm = (T) IMSUtil.copyDataObject(obj);
    					 wrapInsertValidate(insertDm, dealTime);
    					 insertList.add(insertDm);
    				 }else if(objValidDate.after(dealTime) && objExpireDate.after(dealTime)){
    					 //AND EXPIRE_DATE>:deal_time AND VALID_DATE>=:deal_time做DELETE
    					 delList.add(obj);
    				 }else{
    					 //既不修改也不删除的数据，也要全量传给mdb
    					 T oriDm = (T) IMSUtil.copyDataObject(obj);
    					 cacheList.add(oriDm);
    				 }
    			 }
    			 
    			 if(CommonUtil.isNotEmpty(updateList)){
    				 //需要更新,先将旧记录失效，再将新记录插入数据库
    				 DataObject newValue = value.getClass().newInstance();
    				 wrapDeleteDataObject(newValue, dealTime);
    				 DBUtil.updateByCondition(newValue, updateCondition.toArray(new DBCondition[updateCondition.size()]));
    				 DBUtil.insert(insertList.get(0));
    				 cacheList.addAll(updateList);
    				 cacheList.add(insertList.get(0));
    			 }else{
    				 //没有更新的记录，插入新记录
    				 DBUtil.insert(value);
    	    		 T newDm = (T) IMSUtil.copyDataObject(value);
    	    		 cacheList.add(newDm);
    			 }
    			 
    			 if(CommonUtil.isNotEmpty(delList)){
    				 //需要进行物理删除
    				 List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
    				 delCondition.add(new DBCondition(valid_field, dealTime, Operator.GREAT));
    				 delCondition.add(new DBCondition(expire_field, dealTime, Operator.GREAT));
    				 DBUtil.deleteByCondition(value.getClass(), delCondition.toArray(new DBCondition[delCondition.size()]));
    				 //cacheList.addAll(delList);
    			 }
				
    		 }catch (Exception e){
    	        throw new IMSException(e);
    	     }
    	 }
    	 if(CommonUtil.isNotEmpty(cacheList)){
    		 context.cacheList(cacheList);
    	 }
    }
    
    public <T extends DataObject> void updateProductDate(DataObject value,DBCondition... conditions){
    	List<DBCondition> updateCondition = CommonUtil.parseArray2List(conditions);
   	 	List<T> result = DBUtil.query(value.getClass(),updateCondition.toArray(new DBCondition[updateCondition.size()]));
   	 	List<T> cacheList = new ArrayList<T>();
   	 	for (int i = 0; i < result.size(); i++)
        {
         
             DataObject dm = result.get(i);
        	 T newDm = (T) IMSUtil.copyDataObject(dm);
        	 DBUtil.mergeUpdatedEntity(value, newDm);
        	 cacheList.add(newDm);
         	
	 	}
  	   	DBUtil.updateByCondition(value, updateCondition.toArray(new DBCondition[updateCondition.size()]));
  	 	context.cacheList(cacheList);        
    }
    public <T extends DataObject> void updateMode2( DataObject value,Integer operType,Date ValidDate,DBCondition... conditions){
  
    	List<DBCondition> updateCondition = CommonUtil.parseArray2List(conditions);
    	 Date inputExpireDate = (Date) ClassUtil.getFieldValue(value, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);// 传入的失效时间
         Date inputValidDate = ValidDate;// 传入的生效时间
         
    	jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
   	 	jef.database.Field expire_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
		if (value instanceof CoProdCharValue) {
			Integer parmId = (Integer) ClassUtil.getFieldValue(value,CoProdCharValue.Field.specCharId.toString());
			if (null != parmId && parmId.intValue() != 13001) {
				updateCondition.add(new DBCondition(valid_field, inputValidDate));
			}
		} else {
			updateCondition.add(new DBCondition(valid_field, inputValidDate));
		}

//   	updateCondition.add(new DBCondition(valid_field,inputValidDate));
   	 	updateCondition.add(new DBCondition(valid_field,expire_field,Operator.LESS_EQUALS));
   	 	List<T> result = DBUtil.query(value.getClass(),updateCondition.toArray(new DBCondition[updateCondition.size()]));
   	 	List<T> cacheList = new ArrayList<T>();
   	 	if(CommonUtil.isEmpty(result)){
   	 		if(EnumCodeExDefine.OPER_TYPE_UPDATE==operType){
   	 		try {
   	 	  	for(DBCondition db:conditions){
   	    		
					ClassUtil.setFieldValue(value, db.getField().name(), db.getValue());
   	    	}
			ClassUtil.setFieldValue(value,valid_field.name(), inputValidDate);
   	 	} catch (Exception e) {
			e.printStackTrace();
		}
   	 		DBUtil.insert(value);
   	 		T newDm = (T) IMSUtil.copyDataObject(value);
   	 		cacheList.add(newDm);
   	 		}
   	 	
   	 	}else{
   	 		if(EnumCodeExDefine.OPER_TYPE_DELETE==operType){
   	 	      if (expire_field != null)
              {
                  try {
					ClassUtil.setFieldValue(value, expire_field.name(), inputExpireDate != null ? inputExpireDate : context.getRequestDate());
				} catch (Exception e) {
					e.printStackTrace();
				}
              }

   	 		}
   	 	 for (int i = 0; i < result.size(); i++)
         {
          
          DataObject dm = result.get(i);
          Date objValidDate = (Date) ClassUtil.getFieldValue(dm, ConstantDefine.ENTITY_FIELD_VALID_DATE);
          Date objExpireDate = (Date) ClassUtil.getFieldValue(dm, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
          if(inputValidDate.getTime() == objValidDate.getTime() && (!objValidDate.after(objExpireDate))){
         	 T newDm = (T) IMSUtil.copyDataObject(dm);
         	 DBUtil.mergeUpdatedEntity(value, newDm);
         	 cacheList.add(newDm);
          	}
	 	}
   	   	 	DBUtil.updateByCondition(value, updateCondition.toArray(new DBCondition[updateCondition.size()]));
           
   	 		//cacheList.addAll(result);
           
   	 		List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
   	 		delCondition.add(new DBCondition(valid_field,inputValidDate));
   	 		delCondition.add(new DBCondition(valid_field,expire_field,Operator.GREAT));
   	 		DBUtil.deleteByCondition(value.getClass(), delCondition.toArray(new DBCondition[delCondition.size()]));
   	 	}
   	 	context.cacheList(cacheList);
    }
    
    public <T extends DataObject> void updateMode2AndCust( DataObject value,Integer operType,Date validDate,String dbkey,String tableName,DBCondition... conditions){
    	  
    	List<DBCondition> updateCondition = CommonUtil.parseArray2List(conditions);
    	 Date inputExpireDate = (Date) ClassUtil.getFieldValue(value, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);// 传入的失效时间
         Date inputValidDate = validDate;// 传入的生效时间
         
    	jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
   	 	jef.database.Field expire_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
   	 	updateCondition.add(new DBCondition(valid_field,inputValidDate));
   	 	updateCondition.add(new DBCondition(valid_field,expire_field,Operator.LESS_EQUALS));
   	 	List<T> result = DBUtil.query(value.getClass(),updateCondition.toArray(new DBCondition[updateCondition.size()]));
   	 	List<T> cacheList = new ArrayList<T>();
   	 	if(CommonUtil.isEmpty(result)){
   	 		if(EnumCodeExDefine.OPER_TYPE_UPDATE==operType){
   	 	  	for(DBCondition db:conditions){
   	    		try {
					ClassUtil.setFieldValue(value, db.getField().name(), db.getValue());
					ClassUtil.setFieldValue(value, valid_field.name(), inputValidDate);
				} catch (Exception e) {
					e.printStackTrace();
				}
   	    	}
   	 	 List<DataObject> newList = new ArrayList<DataObject>();  
   	     newList.add(value);
   	 	 DBUtil.insertBatchWithDbkey(newList, tableName, dbkey);
   	 		T newDm = (T) IMSUtil.copyDataObject(value);
   	 		cacheList.add(newDm);
   	 		}
   	 	
   	 	}else{
   	 		for (int i = 0; i < result.size(); i++)
   	 		{
   	 			DataObject dm = result.get(i);
   	 			T newDm = (T) IMSUtil.copyDataObject(dm);
   	 			DBUtil.mergeUpdatedEntity(value, newDm);
   	 			cacheList.add(newDm);
   	 		}
            DBUtil.updateWithName(value, dbkey, tableName,updateCondition.toArray(new DBCondition[updateCondition.size()]));
            
			List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
   	 		delCondition.add(new DBCondition(valid_field,inputValidDate));
   	 		delCondition.add(new DBCondition(valid_field,expire_field,Operator.GREAT));
   	 	    DBUtil.deleteByDbKey(value.getClass(), dbkey, tableName, delCondition.toArray(new DBCondition[delCondition.size()]));
   	 		//DBUtil.deleteByCondition(value.getClass(), delCondition.toArray(new DBCondition[delCondition.size()]));
   	 	}
   	 	context.cacheList(cacheList);
    }
    
    
    
    public <T extends DataObject> void updateMode3( DataObject value,DBCondition... conditions){
    	
    	jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
   	 	jef.database.Field expire_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
   	 	//updateCondition.add(new DBCondition(valid_field,expire_field,Operator.LESS_EQUALS));
   	 	
   	 	List<T> result = DBUtil.query(value.getClass(),conditions);
   	 	List<T> cacheList = new ArrayList<T>();
   	 	if(CommonUtil.isEmpty(result)){
   	 		DBUtil.insert(value);
   	 		
   	 	}else{
   	 		
   	 	try{
			
			 List<T> delList = new ArrayList<T>();
			 Date inputExpireDate = (Date) ClassUtil.getFieldValue(value, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
			 for(T obj : result){
				 
				 Date objValidDate = (Date) ClassUtil.getFieldValue(obj, ConstantDefine.ENTITY_FIELD_VALID_DATE);
				 
				 if(objValidDate.after(inputExpireDate)){
					 
					 delList.add(obj);
				 }
			 }
			 
			 
				
			DBUtil.updateByCondition(value, conditions);
			
			 
			 if(CommonUtil.isNotEmpty(delList)){
				 //需要进行物理删除
				 List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
		   	 	 delCondition.add(new DBCondition(valid_field,expire_field,Operator.GREAT));
		   	 	 DBUtil.deleteByCondition(value.getClass(), delCondition.toArray(new DBCondition[delCondition.size()]));
			 }
			
  	 		}catch (Exception e){
  	 			throw new IMSException(e);
  	 		}
   	 		
   	 	}
   	 	T newDm = (T) IMSUtil.copyDataObject(value);
 		cacheList.add(newDm);
   	 	context.cacheList(cacheList);
    }
    public void deleteByDbKey(Class classes,String dbkey,String tableName,DBCondition... conditions){
	 	List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
    	DBUtil.deleteByDbKey(classes, dbkey, tableName, delCondition.toArray(new DBCondition[delCondition.size()]));

    }
    public Date getNextMonthBegin(Date date){
    	
    	Date expireDate = jef.tools.DateUtils.monthBegin(date);
		jef.tools.DateUtils.addMonth(expireDate, 1);
		return expireDate;
    }
    
    
    @Transactional(propagation=Propagation.REQUIRED)
    public <T extends DataObject> List<T> updateNoCacheByKey(List<T> result,DataObject value, Date oldExpireDate,String dbkey,String tableName,DBCondition... conditions){
    	 DBCondition[] queryConditions = wrapQueryConditions(conditions);
         if (CommonUtil.isEmpty(result))
         {
             result = DBUtil.queryByDbKey(value.getClass(),dbkey, queryConditions);
         }
         if (CommonUtil.isEmpty(result))
             return null;
         
         oldExpireDate = oldExpireDate == null ? context.getRequestDate() : oldExpireDate;
         try
         {
             // 2012-05-30 wuyujie : 把未生效的数据直接更新，不需要插入新记录
             jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
             List<DBCondition> notValidCondList = CommonUtil.parseArray2List(queryConditions);
             // 加上未生效的条件
             notValidCondList.add(new DBCondition(valid_field, context.getRequestDate(), Operator.GREAT));
             DataObject newValue = value.getClass().newInstance();
             wrapDeleteDataObject(newValue, oldExpireDate);// expire_date置为当前时间
             List<DBCondition> validCondList = CommonUtil.parseArray2List(queryConditions);
             // 加上已生效的条件
             validCondList.add(new DBCondition(valid_field, context.getRequestDate(), Operator.LESS_EQUALS));
             // 先把老数据置失效
             DBUtil.updateWithName(newValue,dbkey, tableName, validCondList.toArray(new DBCondition[validCondList.size()]));

             List<T> newList = new ArrayList<T>();
             for (int i = 0; i < result.size(); i++)
             {
                 DataObject dm = result.get(i);
                 T newDm = (T) IMSUtil.copyDataObject(dm);
                 DBUtil.mergeUpdatedEntity(value, newDm);// 把更新的值也合并进来
                 wrapInsertValidate(newDm, oldExpireDate);
                 newList.add(newDm);
             }

             // 插入新纪录
             DBUtil.insertBatchWithDbkey(newList, tableName, dbkey);
             result.addAll(newList);
         }
         catch (Exception e)
         {
             throw new IMSException(e);
         }
         return result;
         
    }
    
    public <T extends DataObject> List<T> updateByConditionWithNoWrap(List<T> result, DataObject value, Date oldExpireDate, DBCondition... conditions){
    	
    	  if (CommonUtil.isEmpty(result))
          {
              result = query(value.getClass(), conditions);
          }
          if (CommonUtil.isEmpty(result))
              return null;
          
          oldExpireDate = oldExpireDate == null ? context.getRequestDate() : oldExpireDate;
          
          try
          {
         
              // 已生效的数据先把原数据置失效，再插入新数据
              DataObject newValue = value.getClass().newInstance();
              wrapDeleteDataObject(newValue, oldExpireDate);// expire_date置为当前时间
              List<DBCondition> validCondList = CommonUtil.parseArray2List(conditions);
           
              DBUtil.updateByCondition(newValue, validCondList.toArray(new DBCondition[validCondList.size()]));

              // 因为老的数据也需要缓存起来，所有需要新开设一个List，用以存储新的数据
              List<T> newList = new ArrayList<T>();
              for (int i = 0; i < result.size(); i++)
              {
                  DataObject dm = result.get(i);
                  DBUtil.mergeUpdatedEntity(value, dm);// 把更新的值也合并进来

                  T newDm = (T) IMSUtil.copyDataObject(dm);
                  DBUtil.mergeUpdatedEntity(value, newDm);// 把更新的值也合并进来
                  newList.add(newDm);

              }

              // 插入新纪录
              DBUtil.insertBatch(newList);
              result.addAll(newList);
          }
          catch (Exception e)
          {
              throw new IMSException(e);
          }
          return result;
    	
    }
    
    public <T extends DataObject> List<T> updateByConditionWithNoCache(List<T> result, DataObject value, Date oldExpireDate,
            Date newValidDate, DBCondition... conditions)
    {
        DBCondition[] queryConditions = wrapQueryConditions(conditions);
        if (CommonUtil.isEmpty(result))
        {
            result = query(value.getClass(), queryConditions);
        }
        if (CommonUtil.isEmpty(result))
            return null;
        
        oldExpireDate = oldExpireDate == null ? context.getRequestDate() : oldExpireDate;
        try
        {
            // 2012-05-30 wuyujie : 把未生效的数据直接更新，不需要插入新记录
            jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            List<DBCondition> notValidCondList = CommonUtil.parseArray2List(queryConditions);
            // 加上未生效的条件
            notValidCondList.add(new DBCondition(valid_field, context.getRequestDate(), Operator.GREAT));
            DataObject newValue = value.getClass().newInstance();

            DBUtil.mergeUpdatedEntity(value, newValue);// 把更新的值也合并进来
            //如果没有so_nbr字段跳过set值
            setSoNbrByDataObject(newValue);
            DBUtil.updateByCondition(newValue, notValidCondList.toArray(new DBCondition[notValidCondList.size()]));
            // 已生效的数据先把原数据置失效，再插入新数据
            newValue = value.getClass().newInstance();
            wrapDeleteDataObject(newValue, oldExpireDate);// expire_date置为当前时间
            List<DBCondition> validCondList = CommonUtil.parseArray2List(queryConditions);
            // 加上已生效的条件
            validCondList.add(new DBCondition(valid_field, context.getRequestDate(), Operator.LESS_EQUALS));
            // 先把老数据置失效
            DBUtil.updateByCondition(newValue, validCondList.toArray(new DBCondition[validCondList.size()]));

            // 因为老的数据也需要缓存起来，所有需要新开设一个List，用以存储新的数据
            Date inputExpireDate = (Date) ClassUtil.getFieldValue(value, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);// 传入的失效时间
            Date inputValidDate = (Date) ClassUtil.getFieldValue(value, ConstantDefine.ENTITY_FIELD_VALID_DATE);// 传入的生效时间
            List<T> newList = new ArrayList<T>();
            for (int i = 0; i < result.size(); i++)
            {
                DataObject dm = result.get(i);
                Date validDate = (Date) ClassUtil.getFieldValue(dm, ConstantDefine.ENTITY_FIELD_VALID_DATE);
                if (validDate.after(context.getRequestDate()))
                {
                    DBUtil.mergeUpdatedEntity(value, dm);// 把更新的值也合并进来
                    //如果没有so_nbr字段跳过set值
                    setSoNbrByDataObject(dm);
                    continue;// 如果是未生效数据，则不用插入新记录
                }

                T newDm = (T) IMSUtil.copyDataObject(dm);
                DBUtil.mergeUpdatedEntity(value, newDm);// 把更新的值也合并进来
                newList.add(newDm);
                wrapUpdateNewDataObject(dm, newDm, inputValidDate, inputExpireDate, newValidDate);

                wrapDeleteDataObject(dm, oldExpireDate);// 设置之前查询出来的记录，设置expire_date，然后缓存

            }

            // 插入新纪录
            DBUtil.insertBatch(newList);
            result.addAll(newList);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
        return result;
    }

	/**
	 * 2016-9-8
	 * 判断实体有无so_nbr字段
	 * @param obj
	 * @throws Exception
	 */
	private void setSoNbrByDataObject(DataObject obj) throws Exception {
		java.lang.reflect.Field f_soNbr = ClassUtil.getField(obj.getClass(), ConstantDefine.ENTITY_FIELD_SO_NBR);
		if (f_soNbr != null)
		{
		    ClassUtil.setFieldValue(obj, ConstantDefine.ENTITY_FIELD_SO_NBR, context.getSoNbr());
		}
	}

    public <T extends DataObject> List<T> updateByCondition(DataObject value, DBCondition... conditions)
    {
        return updateByCondition(value, null, conditions);
    }

    /**
     * @Description: 包装关联字段，如果各个表没有expire_date的需要默认加上
     * @author : wuyj
     * @date : 2011-10-21
     */
    public void wrapDBRelations(DBJoinCondition join)
    {
        List<JoinPart> parts = join.getJoinParts();
        for (JoinPart part : parts)
        {
            jef.database.Field field = DBUtil.getJefField(part.getTable(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            if (field == null)
                continue;// 没有失效时间字段则过滤
            List<DBRelation> relations = part.getRelations();
            boolean hasExpire = false;
            if (!CommonUtil.isEmpty(relations))
            {
                for (DBRelation rel : relations)
                {
                    if (rel.getField1().name().equalsIgnoreCase(ConstantDefine.ENTITY_FIELD_EXPIRE_DATE))
                    {
                        hasExpire = true;
                    }
                }
            }
            if (!hasExpire)
            {
                // 如果没有失效时间条件，则自动加上失效时间>当前时间

                if (relations == null)
                    relations = new ArrayList<DBRelation>();
                relations.add(new DBRelation(field, context.getRequestDate(), Operator.GREAT));
            }
        }
    }
    /**
     * @Description: 包装条件，对valid_date,expire_date自动设置
     * @param dmClass
     * @param conditions
     * @return
     */
    private DBCondition[] wrapQueryConditions(DBCondition[] conditions, DBJoinCondition join)
    {

        Map<Class<? extends DataObject>, List<DBCondition>> group = null;
        List<DBCondition> wrapConditions = null;

        if (!CommonUtil.isEmpty(conditions))
        {
            group = DBCondition.parseConditionWithGroup(conditions);
            wrapConditions = CommonUtil.parseArray2List(conditions);
        }
        else
        {
            wrapConditions = new ArrayList<DBCondition>();
            group = new HashMap<Class<? extends DataObject>, List<DBCondition>>();
        }

        Class[] tables = null;

        if (join != null)
        {
            tables = new Class[] { join.getJoinParts().get(0).getTable() };
        }
        else
        {
            Set<Class<? extends DataObject>> keySet = group.keySet();
            tables = keySet.toArray(new Class[keySet.size()]);
        }
        for (Class dmClass : tables)
        {
            // 从已经存在的条件里判断是否有expire_date条件
            List<DBCondition> inputConds = group == null ? null : group.get(dmClass);
            boolean hasExpire = false;
            if (!CommonUtil.isEmpty(inputConds))
            {
                for (DBCondition inputCond : inputConds)
                {

                    String fName = inputCond.getField().name();
                    if (CommonUtil.isEmpty(fName))
                        continue;
                    if (fName.equalsIgnoreCase(ConstantDefine.ENTITY_FIELD_VALID_DATE))
                    {
                        ;// hasValid = true;
                    }
                    else if (fName.equalsIgnoreCase(ConstantDefine.ENTITY_FIELD_EXPIRE_DATE))
                    {
                        hasExpire = true;
                    }
                }
            }
            if (!hasExpire)
            {
                // 如果没有失效时间条件，则自动加上失效时间>当前时间
                jef.database.Field field = DBUtil.getJefField(dmClass, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
                if (field != null)
                    wrapConditions.add(new DBCondition(field, context.getRequestDate(), Operator.GREAT));
            }
        }

        return wrapConditions.toArray(new DBCondition[wrapConditions.size()]);
    }

    private DBCondition[] wrapQueryConditions(DBCondition[] conditions)
    {
        return wrapQueryConditions(conditions, null);
    }

    /**
     * @Description: 对新增对象进行包装，自动设置create_date,so_date,valid_date,expire_date值
     * @param dm
     */
    private void wrapInsertDataObject(DataObject dm) throws IMSException
    {
        try
        {
            java.lang.reflect.Field f_soNbr = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_NBR);
            if (f_soNbr != null)
            {
                ClassUtil.setFieldValue(dm, f_soNbr, context.getSoNbr());
            }

            java.lang.reflect.Field f_createDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_CREATE_DATE);
            if (f_createDate != null && ClassUtil.getFieldValue(dm, f_createDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_createDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_soDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
            if (f_soDate != null && ClassUtil.getFieldValue(dm, f_soDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_soDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_validDate != null && ClassUtil.getFieldValue(dm, f_validDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_validDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            if (f_expireDate != null && ClassUtil.getFieldValue(dm, f_expireDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_expireDate, IMSUtil.getDefaultExpireDate());
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    
    private void wrapInsertValidate(DataObject dm, Date valiDate){
    	try
        {
            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_validDate != null)
            {
                if (valiDate == null)
                {
                	valiDate = context.getRequestDate();// 老记录失效时间没有，则默认取当前时间
                }
                ClassUtil.setFieldValue(dm, f_validDate, valiDate);
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    /**
     * @Description:对删除对象做包装，主要是设置失效时间为当前时间
     * @param dm
     * @param oldExpireDate,老记录的失效时间
     * @throws IMSException
     */
    private void wrapDeleteDataObject(DataObject dm, Date oldExpireDate) throws IMSException
    {
        try
        {
            java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            if (f_expireDate != null)
            {
                if (oldExpireDate == null)
                {
                    oldExpireDate = context.getRequestDate();// 老记录失效时间没有，则默认取当前时间
                }
                ClassUtil.setFieldValue(dm, f_expireDate, oldExpireDate);
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description: 对删除后新增的记录做包装，主要是设置失效时间和失效时间为当前时间
     * @param dm
     * @throws IMSException
     */
    private void wrapDeleteNewDataObject(DataObject dm, Date expireDate) throws IMSException
    {
        try
        {
            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_validDate != null)
            {
                ClassUtil.setFieldValue(dm, f_validDate, expireDate != null ? expireDate : context.getRequestDate());
            }

            java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            if (f_expireDate != null)
            {
                ClassUtil.setFieldValue(dm, f_expireDate, expireDate != null ? expireDate : context.getRequestDate());
            }

            java.lang.reflect.Field f_soNbr = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_NBR);
            if (f_soNbr != null)
            {
                ClassUtil.setFieldValue(dm, f_soNbr, context.getSoNbr());
            }

            java.lang.reflect.Field f_soDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
            if (f_soDate != null)
            {
                ClassUtil.setFieldValue(dm, f_soDate, context.getRequestDate());
            }

        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description: 对更新后新增的记录做包装
     * @param dm
     * @throws IMSException
     */
    private void wrapUpdateNewDataObject(DataObject orig, DataObject newObj, Date inputValidDate, Date inputExpireDate,
            Date oldExpireDate) throws IMSException
    {
        try
        {
            // 新增的生效时间没传等于当前时间
            if (inputValidDate == null)
            {
                if (oldExpireDate != null)
                    inputValidDate = oldExpireDate;
                else
                    inputValidDate = context.getRequestDate();

                Date origValidDate = (Date) ClassUtil.getFieldValue(orig, ConstantDefine.ENTITY_FIELD_VALID_DATE);

                if (origValidDate.after(inputValidDate))
                    inputValidDate = origValidDate;// 老记录生效时间大于新记录的生效时间，说明老记录还没生效，仍然用原纪录的生效时间

            }
            ClassUtil.setFieldValue(newObj, ConstantDefine.ENTITY_FIELD_VALID_DATE, inputValidDate);

            // 新增的失效时间等于原先的失效时间,如果有传入失效时间，则用传入的值
            if (inputExpireDate != null)
            {
                ClassUtil.setFieldValue(newObj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE, inputExpireDate);
            }

            java.lang.reflect.Field f_soDate = ClassUtil.getField(newObj.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
            if (f_soDate != null)
                ClassUtil.setFieldValue(newObj, f_soDate, context.getRequestDate());

            java.lang.reflect.Field f_soNbr = ClassUtil.getField(newObj.getClass(), ConstantDefine.ENTITY_FIELD_SO_NBR);
            if (f_soNbr != null)
            {
                ClassUtil.setFieldValue(newObj, f_soNbr, context.getSoNbr());
            }

        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @param mainConds :the conditions of the main table
     * @return the record list of the main table
     */
    public <T extends DataObject> List<T> queryExists(List<DBCondition> mainConds, DBExistsCond... existsConds)
    {
        if (CommonUtil.isEmpty(existsConds))
        {
            throw new RuntimeException("invalid parameters!!!");
        }
        Class mainClass = null;
        for (int i = 0; i < existsConds.length; i++)
        {
            DBExistsCond existsCond = existsConds[i];
            if (mainClass == null)
            {
                mainClass = IMSUtil.getClassByField(existsCond.getConnectToField());
            }
            List<DBCondition> condList = existsCond.getCondList();
            DBCondition[] dbConditions = wrapQueryConditions(condList.toArray(new DBCondition[condList.size()]));
            existsCond.clearConds();
            existsCond.addConditons(dbConditions);
        }

        DBCondition[] mainCondArr = wrapQueryConditions(mainConds.toArray(new DBCondition[mainConds.size()]));
        List<DBCondition> mainCondsNew = new ArrayList<DBCondition>();
        for (int i = 0; i < mainCondArr.length; i++)
        {
            mainCondsNew.add(mainCondArr[i]);
        }
        List<T> resultList = DBUtil.queryExists(mainCondsNew, existsConds);
        if (CommonUtil.isEmpty(resultList))
        {
            return null;
        }
        return resultList;
    }

    /**
     * @Description: 多表联合查询，详见DBUtil对应方法
     */
    public List<Map> queryJoin(DBJoinCondition joinCondition, OrderCondition[] orders, IntRange range,
            DBCondition... dbConditions)
    {
        // 需要包装各个连接的关联条件(首表除外)，没有expire_date的需要默认加上
        this.wrapDBRelations(joinCondition);

        // 在条件中需要包装首表的的查询条件
        DBCondition[] wrapConditions = this.wrapQueryConditions(dbConditions, joinCondition);

        return queryJoinDb(joinCondition, orders, range, wrapConditions);
    }

    public List<Map> queryJoin(DBJoinCondition joinCondition, DBCondition... dbConditions)
    {
        return queryJoin(joinCondition, null, null, dbConditions);
    }

    public Map<Class, DataObject> queryJoinSingle(DBJoinCondition joinCondition, DBCondition... dbConditions)
    {
        List<Map> result = queryJoin(joinCondition, dbConditions);
        if (CommonUtil.isEmpty(result))
            return null;
        return result.get(0);
    }

    /*
     * @Description:根据表名和条件返回符合记录的条数 caohm5 2012-3-1 add
     */
    public int queryCount(Class<? extends DataObject> dmClass, DBCondition... conditions) throws IMSException
    {

        return DBUtil.queryCount(dmClass, wrapQueryConditions(conditions));
    }

    /**
     * @Description: 构造复杂的嵌套查询
     */
    public static IConditionField buildComplexCondition(DBCondition condition, Query<?> query) throws IMSException
    {
        if (condition instanceof DBOrCondition)
        {
            Or or = new Or();
            List<DBCondition> subConds = condition.getConditions();
            for (DBCondition cond : subConds)
            {
                if (cond.getConditions() == null)
                {
                    Class fClass = IMSUtil.getClassByField(cond.getField());
                    jef.database.Field f = query.getType() == fClass ? cond.getField() : new RefField(cond.getField());
                    if (cond.getOperator() == null)
                    {
                        or.addCondition(f, cond.getValue());
                    }
                    else
                    {
                        or.addCondition(f, cond.getOperator(), cond.getValue());
                    }

                }
                else
                {
                    IConditionField condF = buildComplexCondition(cond, query);
                    or.addCondition(condF);
                }

            }
            return or;
        }
        else
        {
            And and = new And();
            List<DBCondition> subConds = condition.getConditions();
            for (DBCondition cond : subConds)
            {
                if (cond.getConditions() == null)
                {
                    Class fClass = IMSUtil.getClassByField(cond.getField());
                    jef.database.Field f = query.getType() == fClass ? cond.getField() : new RefField(cond.getField());
                    if (cond.getOperator() == null)
                    {
                        and.addCondition(f, cond.getValue());
                    }
                    else
                    {
                        and.addCondition(f, cond.getOperator(), cond.getValue());
                    }
                }
                else
                {
                    IConditionField condF = buildComplexCondition(cond, query);
                    and.addCondition(Condition.get(condF));
                }
            }
            return and;
        }
    }

    /**
     * @Description:多表关联查询
     * @param DBJoinCondition,查询的关联条件，具体参见DBJoinCondition说明
     * @param dbConditions,具体的查询条件
     * @param orders,排序,每一个order里只能设置同一张表的字段
     * @param range,分页，new IntRange(1,100),查询1到100条记录
     * @return,返回的是一个List，每条记录是一个Map， 键名为表名class，健值是表名对应的实例对象，也是按照传入顺序排列的
     */
    public static List<Map> queryJoinDb(DBJoinCondition joinCondition, OrderCondition[] orders, IntRange range,
            DBCondition... dbConditions)
    {
        try
        {
            if (!CommonUtil.isEmpty(dbConditions))
            {
                // 把DBCondition的值设置到对应的Query中去,因为不同的Query对象对应不同的表,条件需要匹配到对应的表
                for (int i = 0; i < dbConditions.length; i++)
                {
                    DBCondition condition = dbConditions[i];
                    if (CommonUtil.isEmpty(condition.getConditions()))
                    {
                        // 普通查询
                        Class fieldClass = IMSUtil.getClassByField(condition.getField());
                        Query query = joinCondition.getQuery(fieldClass);
                        if (query == null)
                            throw new IMSException("invalid database condition");
                        // 2011-10-24 wuyujie : 除了用is null查询，其它表达式情况字段不能为null
                        if (condition.getValue() == null && Operator.IS_NULL != condition.getOperator())
                        {
                            throw new IMSException("query condition value of \"" + condition.getField().name()
                                    + "\" can not be null.");
                        }
                        // @Date 2012-04-07 lijc3 修改bug 不为空才设置操作符
                        if (condition.getOperator() == null)
                        {
                            query.addCondition(condition.getField(), condition.getValue());
                        }
                        else
                        {
                            query.addCondition(condition.getField(), condition.getOperator(), condition.getValue());
                        }
                    }
                    else
                    {
                        // 嵌套的复杂查询
                        DBCondition cond = DBCondition.getFirstIndividualCondition(condition);

                        Class fieldClass = IMSUtil.getClassByField(cond.getField());
                        Query query = joinCondition.getQuery(fieldClass);
                        if (query == null)
                            throw new IMSException("invalid database condition");

                        IConditionField condF = buildComplexCondition(condition, query);
                        query.addCondition(condF);

                    }
                }
            }
            if (!CommonUtil.isEmpty(orders))
            {
                for (OrderCondition order : orders)
                {
                    Class fieldClass = IMSUtil.getClassByField(order.getField());
                    Query query = joinCondition.getQuery(fieldClass);
                    query.addOrderBy(order.isAsc(), order.getField());
                }

            }
            List<DataObjectMap> resultList = DBUtil.getDBClient().select(joinCondition.build(), DataObjectMap.class, range);
            if (CommonUtil.isEmpty(resultList))
                return null;
            List<Map> returnResult = new ArrayList<Map>();

            List<JoinPart> joinParts = joinCondition.getJoinParts();
            for (DataObjectMap resultItem : resultList)
            {
                Map itemMap = new LinkedHashMap();

                for (JoinPart part : joinParts)
                {
                    Query query = part.getQuery();
                    itemMap.put(part.getTable(), resultItem.get(query));
                }
                returnResult.add(itemMap);
            }
            return returnResult;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description: without query
     * @param <T>
     * @param value
     * @param conditions
     * @return 2012-09-20 zengxr noquery for update and delete
     */
    public <T extends DataObject> List<T> updateDirectByConditionNoQuery(DataObject value, List<T> results,
            DBCondition... conditions)
    {
        DBUtil.updateByCondition(value, conditions);
        DBUtil.mergeUpdatedEntities(value, results);

        context.cacheList(results);
        return results;
    }

    /**
     * 在旧记录上直接更新并缓存 copy from BaseComponent
     */
    public <T extends DataObject> List<T> updateDirectByCondition(DataObject value, DBCondition... conditions)
    {
        List<T> results = (List<T>) DBUtil.updateByConditionWithReturn(value, conditions);
        Field[] fields = ClassUtil.getFields(value.getClass());
        Map<Field, Object> map = new HashMap<Field, Object>();
        for (Field f : fields)
        {
            Object fieldValue = ClassUtil.getFieldValue(value, f.getName());
            if (fieldValue != null)
            {
                map.put(f, fieldValue);
            }
        }
        /*
         * for (T obj : results) { for (Field f : map.keySet()) { try { ClassUtil.setFieldValue(obj, f, map.get(f)); } catch
         * (Exception e) { throw new IMSException(e); } } }
         */
        context.cacheList(results);
        return results;
    }

    /**
     * lijc3 2012-9-20 没有查询，需要传入list
     * 
     * @param <T>
     * @param list
     * @param value
     * @param oldExpireDate
     * @param conditions
     * @return
     */
    public <T extends DataObject> List<T> updateByConditionWithNoQuery(List<T> list, DataObject value, Date oldExpireDate,
            DBCondition... conditions)
    {
        List<T> result = updateByConditionWithNoCache(list, value, oldExpireDate, oldExpireDate, conditions);
        context.cacheList(result);
        return result;
    }

    /**
     * lijc3 2013-12-17 封装过户需要插入的新数据 重置生效时间
     * 
     * @param dm
     * @throws IMSException
     */
    private void wrapChgOwnerInsertDataObject(DataObject dm, Date valid_Date) throws IMSException
    {
        try
        {
            java.lang.reflect.Field f_soNbr = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_NBR);
            if (f_soNbr != null)
            {
                ClassUtil.setFieldValue(dm, f_soNbr, context.getSoNbr());
            }

            java.lang.reflect.Field f_soDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
            if (f_soDate != null)
            {
                ClassUtil.setFieldValue(dm, f_soDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_validDate != null)
            {
                Date validDate = (Date) ClassUtil.getFieldValue(dm, f_validDate);
                // 生效时间为空，或者生效时间比当前时间小，就设置生效时间为当前时间
                if (validDate == null || validDate.before(valid_Date))
                {
                    ClassUtil.setFieldValue(dm, f_validDate, valid_Date);
                }
            }

        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * lijc3 2013-12-17 封装携号跨区资料迁移缓存失效的数据 重置生效时间
     * 
     * @param dm
     * @throws IMSException
     */
    private void wrapTransferOldDataObjectExpireDate(DataObject dm, Date expireDate) throws IMSException
    {
        try
        {

            java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_expireDate != null && f_validDate != null)
            {
                Date validDate = (Date) ClassUtil.getFieldValue(dm, f_validDate);
                // 生效时间比携号跨区时间早，才将他置为失效
                if (validDate != null && validDate.before(expireDate))
                {
                    ClassUtil.setFieldValue(dm, f_expireDate, expireDate);
                }
            }

        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public void insertBatchByChangeOwnNoSetSoNbr(List<? extends DataObject> dmList,boolean needCache) throws IMSException
    {
        if (CommonUtil.isEmpty(dmList))
        {
            return;
        }

        DBUtil.insertBatch(dmList);
        if(needCache){
        	 context.cacheList(dmList);
        }
       
    }

    /**
     * lijc3 2013-12-17 封装过户需要插入的新数据
     * 
     * @param dm
     * @throws IMSException
     */
    private void wrapChgOwnerInsertDataObjectNoSetSoNbr(DataObject dm, Date valid_Date) throws IMSException
    {
        try
        {
            java.lang.reflect.Field f_soDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
            if (f_soDate != null)
            {
                ClassUtil.setFieldValue(dm, f_soDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_validDate != null)
            {
                Date validDate = (Date) ClassUtil.getFieldValue(dm, f_validDate);
                // 生效时间为空，或者生效时间比当前时间小，就设置生效时间为当前时间
                if (validDate == null ||  validDate.before(valid_Date))
                {
                    ClassUtil.setFieldValue(dm, f_validDate, valid_Date);
                }
            }

        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    
    
    
    /**
	 * 产品id in size补全，补全-1
	 * 
	 * @param desc
	 * @return
	 */
	public List<Long> completeListSize(List<Long> desc) {
		if (CommonUtil.isEmpty(desc)) {
			return desc;
		}
		int size = desc.size();
		if (size <= 10) {
			for (int i = 0; i < 10 - size; i++) {
				desc.add(-1L);
			}
			return desc;
		} else if (size <= 20) {
			for (int i = 0; i < 20 - size; i++) {
				desc.add(-1L);
			}
			return desc;
		} else if (size <= 30) {
			for (int i = 0; i < 30 - size; i++) {
				desc.add(-1L);
			}
			return desc;
		} else if (size <= 40) {
			for (int i = 0; i < 40 - size; i++) {
				desc.add(-1L);
			}
			return desc;
		}

		return desc;
	}
	/**
	 * 补全set
	 * @param desc
	 * @return
	 */
	public List<Long> completeSetSize(Set<Long> desc) {
		if(CommonUtil.isEmpty(desc)){
			return null;
		}
		List<Long> temp=new ArrayList<Long>(desc);
		return completeListSize(temp);
	}
	
	
	/**
	 * 产品id in size补全，补全-1
	 * 
	 * @param desc
	 * @return
	 */
	public List<Integer> completeListIntSize(List<Integer> desc) {
		if (CommonUtil.isEmpty(desc)) {
			return desc;
		}
		int size = desc.size();
		if (size <= 10) {
			for (int i = 0; i < 10 - size; i++) {
				desc.add(-1);
			}
			return desc;
		} else if (size <= 20) {
			for (int i = 0; i < 20 - size; i++) {
				desc.add(-1);
			}
			return desc;
		} else if (size <= 30) {
			for (int i = 0; i < 30 - size; i++) {
				desc.add(-1);
			}
			return desc;
		} else if (size <= 40) {
			for (int i = 0; i < 40 - size; i++) {
				desc.add(-1);
			}
			return desc;
		}

		return desc;
	}
	/**
	 * 补全set
	 * @param desc
	 * @return
	 */
	public List<Integer> completeSetIntSize(Set<Integer> desc) {
		if(CommonUtil.isEmpty(desc)){
			return null;
		}
		List<Integer> temp=new ArrayList<Integer>(desc);
		return completeListIntSize(temp);
	}
	
	/**
	 * 判断是是否第一次删除
	 * @Date  2016-10-12
	 * @param clazz
	 */
	public boolean isFirstDeleted(Class<? extends DataObject> clazz) {
		String simpleClassName = clazz.getSimpleName();
		if(!ITableUtil.deleteList.contains(simpleClassName)){
			ITableUtil.deleteList.add(simpleClassName);
			ITableUtil.deleteMap.put(context.getSoNbr(), ITableUtil.deleteList);
			imsLogger.info(new Object[]{"**********first delete for entity : *********************"+simpleClassName});
			return true;
		}
		imsLogger.info(new Object[]{"**********delete more than onece :*********************"+simpleClassName});
		return false;
	}
 
}
