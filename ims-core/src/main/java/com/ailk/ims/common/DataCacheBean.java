package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.DataObject;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;

/**
 * @Description: 缓存对象， 供后续上发mdb使用
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27 2012-02-07 wuyujie : getAllCacheList方法增加日志输出
 * @Date 2012-09-26 zengxr remove log
 * @Date 2012-09-26 wangjt remove log
 */
public class DataCacheBean
{
    // 所有插入数据后的DM都在此对象中缓存，以供后续上发mdb调用
    protected Map<Class<? extends DataObject>, List<DataObject>> cachedData = new HashMap<Class<? extends DataObject>, List<DataObject>>();
    private ImsLogger logger = new ImsLogger(DataCacheBean.class);

    public void cacheSingle(DataObject dm)
    {
        Class clz = dm.getClass();
        List<DataObject> list = cachedData.get(clz);
        if (list == null)
        {
            list = new ArrayList<DataObject>();
            cachedData.put(clz, list);
        }
        list.add(dm);
    }

    /**
     * 
     * 获取所有缓存的列表 2011-08-18 wuyujie : 支持条件
     */
    public <T extends DataObject> List<T> getAllCacheList(Class clz, CacheCondition... conditions)
    {
        try
        {
            List<T> all = (List<T>) cachedData.get(clz);
            // 2012-07-06 yangjh 删除|| CommonUtil.isEmpty(conditions)
            if (CommonUtil.isEmpty(all))
            {
                // logger.debug("****** data [ table : ", clz.getSimpleName(), " ] in cache is null");
                return null;
            }

            List<T> result = new ArrayList<T>();

            // 查处符合条件的
            for (DataObject dm : all)
            {
                boolean valid = true;
                for (CacheCondition param : conditions)
                {
                    String fieldName = param.getField().name();
                    Object cacheValue = ClassUtil.getPropertyValue(dm, fieldName);

                    if (!param.isMatch(cacheValue))
                    {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    result.add((T) dm);
            }

            if (result.size() == 0)
            {
                StringBuffer sb = new StringBuffer("");
                sb.append("{ ");
                for (CacheCondition param : conditions)
                {
                    String fieldName = param.getField().name();
                    String fieldValue = param.getValue().toString();
                    sb.append("[ " + fieldName + " : " + fieldValue + " ]");
                }
                sb.append(" }");
                // logger.debug("No cache for ", clz.getSimpleName(), sb.toString());
            }
            else
            {
                Integer flag = SysUtil.getInt(SysCodeDefine.busi.IS_PRINT_CACHE, 1);
                if (flag == 1)
                {
                    logger.dump("end to get data from the cache", result);
                }
            }
            return result;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public <T extends DataObject> T getSingleCache(Class clz, CacheCondition... conditions)
    {
        List<T> all = getAllCacheList(clz, conditions);
        return (CommonUtil.isEmpty(all) ? null : all.get(0));
    }

    public boolean containCache(Class clz)
    {
        return getSingleCache(clz) != null;
    }

    public void cacheList(List<? extends DataObject> dmList)
    {
        if (CommonUtil.isEmpty(dmList))
        {
            return;
        }
        for (DataObject dm : dmList)
        {
            cacheSingle(dm);
        }
    }

    public Map<Class<? extends DataObject>, List<DataObject>> getCachedData()
    {
        return cachedData;
    }

    public void removeCacheData(DataObject data)
    {
        if (CommonUtil.isEmpty(cachedData) || data == null || CommonUtil.isEmpty(cachedData.get(data.getClass())))
        {
            return;
        }
        cachedData.get(data.getClass()).remove(data);
    }

    public void removeCacheData(List<DataObject> data)
    {
        if (CommonUtil.isEmpty(cachedData) || CommonUtil.isEmpty(data))
        {
            return;
        }
        for (DataObject item : data)
        {
            removeCacheData(item);
        }
    }
    
    /**
     * 移除缓存中所有对象
     * lijc3 2013-6-17
     */
    public void removeAllCacheData()
    {
        cachedData=new HashMap<Class<? extends DataObject>, List<DataObject>>();
    }
}
