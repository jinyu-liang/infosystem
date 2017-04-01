package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.ContextBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBExistsCond;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.JoinPart;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;

/**
 * @Author wuyj
 * @Date 2011-7-28 2011-07-28 wuyujie : 增加生效失效时间的自动设置
 * @Date 2011-7-29 wangjt：增加queryExists方法
 * @Date 2011-08-05 wuyujie : 增加queryJoin方法
 * @Date 2011-08-08 wuyujie : 修改updateByCondition方法，如果失效时间传入了，则新增的那条失效时间就取传入的。
 * @Date 2011-08-12 wuyujie : updateByCondition如果传入的生效时间小于当前时间则用当前时间，防止主键冲突问题
 * @Date 2011-08-15 wuyujie : 查询条件去掉了生效时间小于当前时间的限制，只查询失效时间大于当前时间
 * @Date 2012-02-06 wuyujie : 如果是未生效数据，则不用插入新记录的，防止出现主键冲突
 * @Date 2012-3-29 wangjt: 修改isEmpty方法用来判断对象的问题
 * @Date 2012-04-28 wuyujie ：因为直接使用query(DataObject)存在查找条件顺序无法控制的缺陷，所以废除
 * @Date 2012-05-30 wuyujie : 把未生效的数据直接更新，不需要插入新记录
 * @date 2012-07-16 luojb:修改updateDirectByCondition方法，返回值已经是更新过了的
 */
public class BaseComponent extends ContextBean
{
    public BaseComponent()
    {
    }

    public static <T extends BaseComponent> T getInstance(Class<T> clazz)
    {
        try
        {
            IMSContext context = (IMSContext) IMSUtil.getRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT);
            T newObj = context.getComponent(clazz);
            return newObj;
        }
        catch (Exception e)
        {
        	ImsLogger imsLogger = new ImsLogger(BaseComponent.class);
        	imsLogger.error(e,e);
        }
        return null;
    }
    
    public void insertWithAllCache(DataObject dm,DBCondition... conditions){
		DBUtil.insert(dm);
		List<DataObject> list = DBUtil.query(dm.getClass(),conditions);
		context.cacheList(list);;
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

    public <T extends DataObject> List<T> query(Class<? extends DataObject> dmClass, DBCondition... conditions)
            throws IMSException
    {
        return DBUtil.query(dmClass, wrapQueryConditions(conditions));

    }
    
    public List<Object> querySingleField(Class<? extends DataObject> dmClass, jef.database.Field field, DBCondition... conditions)
            throws IMSException
    {
        List<DataObject> objectList = DBUtil.query(dmClass, wrapQueryConditions(conditions));
        List<Object> fieldList = null;
        if (CommonUtil.isNotEmpty(objectList))
        {
            fieldList = new ArrayList<Object>();
            for (DataObject object : objectList)
            {
                fieldList.add(ClassUtil.getPropertyValue(object, field.toString()));
            }
        }
        return fieldList;
    }

    /**
     * @Description: 支持分页，排序
     * @param range,分页，new IntRange(10,100)表示第10到100条，
     * @param order，排序
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
     * @param expireDate,设置老记录的失效时间，默认是当前时间
     */
    public <T extends DataObject> List<T> deleteByCondition(Class<T> dmClass, Date expireDate, DBCondition... conditions)
    {
        //上海删除数据不插入生效失效时间一样的记录
        if(ProjectUtil.is_CN_SH()){
            return deleteByCondition_noInsert(dmClass, expireDate, conditions);
        }else{
            DBCondition[] wrapConditions = wrapQueryConditions(conditions);
            List<T> result = DBUtil.query(dmClass, wrapConditions);
            if (CommonUtil.isEmpty(result))
                return null;
            
            try
            {
                DataObject value = dmClass.newInstance();
                wrapDeleteDataObject(value, expireDate);// 设置expire_date为当前时间作为更新
                DBUtil.updateByCondition(value, wrapConditions);// 把符合条件的记录的expire_date先更新掉
                
                List<T> newList = new ArrayList<T>();
                
                for (T dm : result)
                {
                    
                    wrapDeleteDataObject(dm, expireDate);// 设置之前查询出来的记录，设置expire_date，然后后续缓存
                    
                    // 2012-02-06 wuyujie : 如果是未生效数据，则不用插入新记录的，防止出现主键冲突
                    Date validDate = (Date) ClassUtil.getPropertyValue(dm, ConstantDefine.ENTITY_FIELD_VALID_DATE);
                    if (validDate.after(context.getRequestDate()))
                        continue;
                    
                    T newDm = (T) IMSUtil.copyDataObject(dm);
                    
                    wrapDeleteNewDataObject(newDm, expireDate);// 插入新的数据，其中valid_date和expire_date一致，都是当前时间,so_nbr也是当前
                    newList.add(newDm);
                }
                DBUtil.insertBatch(newList);
                
                result.addAll(newList);
                
                context.cacheList(result);
                
                return result;
            }
            catch (Exception e)
            {
                throw new IMSException(e);
            }
        }
    }

    /**
     * @Description: 删除记录时不插入数据
     * @param expireDate,设置老记录的失效时间，默认是当前时间
     * @return 返回更新后的数据
     */
    public <T extends DataObject> List<T> deleteByCondition_noInsert(Class<T> dmClass, Date expireDate, DBCondition... conditions)
    {
        return deleteByCondition_noInsert(dmClass,expireDate,true,conditions);
    }
    
    /**
     * @Description: 删除记录时不插入数据
     * @param expireDate,设置老记录的失效时间，默认是当前时间
     * @return 返回更新前的数据
     */
    public <T extends DataObject> List<T> deleteByCondition_noInsert(Class<T> dmClass, Date expireDate,boolean isReturnUp, DBCondition... conditions)
    {
        DBCondition[] wrapConditions = wrapQueryConditions(conditions);
        List<T> result = DBUtil.query(dmClass, wrapConditions);
        if (CommonUtil.isEmpty(result))
            return null;
        try
        {
            List<T> oraginResult = null;
            if(!isReturnUp){
                oraginResult = new ArrayList<T>();
                oraginResult.addAll(result);
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
            return isReturnUp?result:oraginResult;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
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
   					 DBUtil.mergeUpdatedEntity(value, obj);
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
   				 DBUtil.insertBatch(insertList);
   				 cacheList.addAll(updateList);
   				 cacheList.addAll(insertList);
   			 }else{
   				 //没有更新的记录，插入新记录
   				 DBUtil.insert(value);
   	    		 T newDm = (T) IMSUtil.copyDataObject(value);
   	    		 cacheList.add(newDm);
   			 }
   			 
   			 if(CommonUtil.isNotEmpty(delList)){
   				 //需要进行物理删除
   				 List<DBCondition> delCondition = CommonUtil.parseArray2List(conditions);
   				 delCondition.add(new DBCondition(valid_field, dealTime, Operator.GREAT_EQUALS));
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
     * @Description: 更新
     * @param value，需要更新的值，这里面可以设置新纪录的生效时间和失效时间
     * @param oldExpireDate，老记录需要设置的expire_date，默认和新纪录的生效时间保持一致
     */
    public <T extends DataObject> List<T> updateByCondition(DataObject value, Date oldExpireDate, DBCondition... conditions)
    {
        List<T> result = updateByConditionWithNoCache(value, oldExpireDate, oldExpireDate, conditions);
        context.cacheList(result);
        return result;
    }

    /**
     * 更新 wukl 2012-3-15
     * 
     * @param value 需要更新的值，这里面可以设置新纪录的生效时间和失效时间
     * @param oldExpireDate 老记录需要设置的expire_date
     * @param newValidDate 新纪录需要设置的valid_date
     */
    public <T extends DataObject> List<T> updateByCondition(DataObject value, Date oldExpireDate, Date newValidDate,
            DBCondition... conditions)
    {
        List<T> result = updateByConditionWithNoCache(value, oldExpireDate, newValidDate, conditions);
        context.cacheList(result);
        return result;
    }

    public <T extends DataObject> List<T> updateByConditionWithNoCache(DataObject value, Date oldExpireDate, Date newValidDate,
            DBCondition... conditions)
    {
        DBCondition[] queryConditions = wrapQueryConditions(conditions);
        List<T> result = query(value.getClass(), queryConditions);
        if (CommonUtil.isEmpty(result))
            return null;
        try
        {
            DataObject newValue = value.getClass().newInstance();
            jef.database.Field valid_field = DBUtil.getJefField(value.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            // 已生效的数据先把原数据置失效，再插入新数据
            wrapDeleteDataObject(newValue, oldExpireDate);// expire_date置为当前时间
            List<DBCondition> validCondList = CommonUtil.parseArray2List(queryConditions);
            // 加上已生效的条件
            validCondList.add(new DBCondition(valid_field, context.getRequestDate(), Operator.LESS_EQUALS));
            // 先把老数据置失效
            DBUtil.updateByCondition(newValue, validCondList.toArray(new DBCondition[validCondList.size()]));

            // 2012-05-30 wuyujie : 把未生效的数据直接更新，不需要插入新记录
            newValue = value.getClass().newInstance();
            List<DBCondition> notValidCondList = CommonUtil.parseArray2List(queryConditions);
            // 加上未生效的条件
            notValidCondList.add(new DBCondition(valid_field, context.getRequestDate(), Operator.GREAT));

            DBUtil.mergeUpdatedEntity(value, newValue);// 把更新的值也合并进来

            ClassUtil.setFieldValue(newValue, ConstantDefine.ENTITY_FIELD_SO_NBR, context.getSoNbr());
            DBUtil.updateByCondition(newValue, notValidCondList.toArray(new DBCondition[notValidCondList.size()]));

            // 因为老的数据也需要缓存起来，所有需要新开设一个List，用以存储新的数据
            Date inputExpireDate = (Date) ClassUtil.getPropertyValue(value, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);// 传入的失效时间
            Date inputValidDate = (Date) ClassUtil.getPropertyValue(value, ConstantDefine.ENTITY_FIELD_VALID_DATE);// 传入的生效时间
            List<T> newList = new ArrayList<T>();
            for (int i = 0; i < result.size(); i++)
            {
                DataObject dm = result.get(i);
                Date validDate = (Date) ClassUtil.getPropertyValue(dm, ConstantDefine.ENTITY_FIELD_VALID_DATE);
                if (validDate.after(context.getRequestDate()))
                {
                    DBUtil.mergeUpdatedEntity(value, dm);// 把更新的值也合并进来
                    ClassUtil.setFieldValue(dm, ConstantDefine.ENTITY_FIELD_SO_NBR, context.getSoNbr());
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
     * 对新增对象进行包装，自动设置so_nbr,create_date,so_date,valid_date,expire_date值
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
            if (f_createDate != null && ClassUtil.getPropertyValue(dm, f_createDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_createDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_soDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_SO_DATE);
            if (f_soDate != null && ClassUtil.getPropertyValue(dm, f_soDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_soDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_validDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE);
            if (f_validDate != null && ClassUtil.getPropertyValue(dm, f_validDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_validDate, context.getRequestDate());
            }

            java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(), ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            if (f_expireDate != null && ClassUtil.getPropertyValue(dm, f_expireDate) == null)
            {
                ClassUtil.setFieldValue(dm, f_expireDate, IMSUtil.getDefaultExpireDate());
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description:对删除对象做包装，主要是设置失效时间为当前时间
     * @param oldExpireDate,老记录的失效时间(老记录失效时间没有，则默认取当前时间)
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
     * 对更新后新记录的对象的时间进行包装
     * 
     * @author wuyj 2012-6-29
     * @param orig,原记录
     * @param newObj，新插入的记录
     * @param inputValidDate，新纪录的生效时间，如果没传，则取oldExpireDate，如果oldExpireDate为空，则取当前时间
     * @param inputExpireDate，新纪录的失效时间
     * @param oldExpireDate，老记录的失效时间
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

                Date origValidDate = (Date) ClassUtil.getPropertyValue(orig, ConstantDefine.ENTITY_FIELD_VALID_DATE);

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

        return DBUtil.queryJoin(joinCondition, orders, range, wrapConditions);
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

    /**
     * 根据表名和条件返回符合记录的条数 caohm5 2012-3-1 add
     */
    public int queryCount(Class<? extends DataObject> dmClass, DBCondition... conditions) throws IMSException
    {
        return DBUtil.queryCount(dmClass, wrapQueryConditions(conditions));
    }

    /**
     * 在旧记录上直接更新并缓存 luojb 2012-7-10
     */
    public <T extends DataObject> List<T> updateDirectByCondition(DataObject value, DBCondition... conditions)
    {
        List<T> results = (List<T>) DBUtil.updateByConditionWithReturn(value, conditions);
        context.cacheList(results);
        return results;
    }
    
    public <T extends DataObject> List<T> queryWithIn(Class<? extends DataObject> dmClass,jef.database.Field field,List objectId,DBCondition... conditions)
           throws IMSException
   {
     return DBUtil.query(dmClass, wrapQueryConditions(conditions));

    }
}
