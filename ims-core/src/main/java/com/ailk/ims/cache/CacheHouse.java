package com.ailk.ims.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.DataObject;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.util.ImsLogger;
/**
 * 缓存数据存放仓库，所有的缓存数据都可以被这个对象持有。
 * @Description
 * @author wuyj
 * @Date 2012-9-16
 */
public class CacheHouse
{
    private Map<Class<?>,BaseCacheParser> data;//主要存储字段，key是表名，value是BaseCacheParser对象
    private ImsLogger imslogger = new ImsLogger(getClass());
    public CacheHouse(){
        data = new HashMap<Class<?>,BaseCacheParser>();
    }
    
    public BaseCacheParser getCacheParser(Class<? extends DataObject> clz){
        BaseCacheParser parser = data.get(clz);
        if(parser == null)
            imslogger.debug("----not config table["+clz.getSimpleName()+"] for cache in file[boot.xml]");
        return parser;
    }
    
    public void addCacheParser(Class<? extends DataObject> clz,BaseCacheParser cacheParser){
        data.put(clz, cacheParser);
    }
    
    public DataObject getSingleCached(Class clz,CacheCondition...conds)
    {
        BaseCacheParser cacheParser = getCacheParser(clz);
        if(cacheParser == null)
            return null;
        return cacheParser.getSingle(conds); 
    }
    public <T extends DataObject>List<T> getCached(Class clz,CacheCondition... conds){
        BaseCacheParser cacheParser = getCacheParser(clz);
        if(cacheParser == null)
            return null;
        return (List<T>)cacheParser.getList(conds);
    }
    
}
