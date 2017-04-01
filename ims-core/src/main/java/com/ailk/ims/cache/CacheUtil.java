package com.ailk.ims.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;

public class CacheUtil
{
    
    /**
     * 判断当前对象是否符合传入的缓存条件
     * @author wuyj 2012-9-16
     * @param data
     * @param conditions
     * @return
     * @throws Exception
     */
    public static boolean isMatch(Object data,CacheCondition...conditions){
        try{
            for (CacheCondition param : conditions)
            {
                String fieldName = param.getField().name();
                Object cacheValue = ClassUtil.getPropertyValue(data, fieldName);
    
                if (!param.isMatch(cacheValue))
                {
                    return false;//一个条件不符合当前对象就不符合了
                }
            }
        }catch(Exception e){
            IMSUtil.throwBusiException(e);
        }
        return true;
    }
    
    /**
     * 把CacheCondition[]转换成DBCondition[]
     * @author wuyj 2012-9-17
     * @param condition
     * @return
     */
    public static DBCondition[] buildDBCondtions(CacheCondition... condition){
        if(CommonUtil.isEmpty(condition))
            return null;
        DBCondition[] dbConds = new DBCondition[condition.length];
        for (int i=0;i< dbConds.length;i++)
        {
            CacheCondition cond = condition[i];
            dbConds[i] = new DBCondition(cond.getField(), cond.getValue(), cond.getOperator());
        }
        return dbConds;
    }
    
    
    /**
     * 获取符合条件的所有缓存对象
     * @author wuyj 2012-9-16
     * @param it
     * @param conditions
     * @return
     * @throws Exception
     */
    protected static List<DataObject> getListFromIterator(Iterator<DataObject> it,CacheCondition...conditions){
        if(it == null)
            return null;
        List<DataObject> result = new ArrayList<DataObject>();
        while(it.hasNext()){
            DataObject data = it.next();
            boolean isMatch = CacheUtil.isMatch(data, conditions);
            if (isMatch)
                result.add(data);
        }
        return result;
    }
    
    
    
    
    /**
     * 获取符合条件的第一个缓存对象
     * @author wuyj 2012-9-17
     * @param it
     * @param conditions
     * @return
     */
    protected static DataObject getSingleFromIterator(Iterator<DataObject> it,CacheCondition...conditions){
        if(it == null)
            return null;
        while(it.hasNext()){
            DataObject data = it.next();
            boolean isMatch = CacheUtil.isMatch(data, conditions);
            if (isMatch)
                return data;
        }
        return null;
    }
    
    
    /**
     * 根据cacheLevel判断 缓存中读取不到数据时是否允许查表
     * luojb 2012-10-23
     * @param cacheLevel
     * @return
     */
    public static boolean isAllowedFromDB(int cacheLevel)
    {
        return cacheLevel == 2 || cacheLevel == 3;
    }
    
    /**
     * 根据cacheLevel判断 是否启动时加载数据
     * luojb 2012-10-23
     * @param cacheLevel
     * @return
     */
    public static boolean isInitCache(int cacheLevel)
    {
        return cacheLevel != 3;
    }
    
    
}
