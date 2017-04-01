package com.ailk.ims.cache;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.xml.BaseNode;
/**
 * 基础的缓存封装类
 * @Description
 * @author luojb
 * @Date 2012-10-23
 */
public abstract class BaseCacheParser
{
    private Class<? extends DataObject> clz;
    private List<DataObject> list = new ArrayList<DataObject>();
    private int cacheLevel;
    
    abstract protected void cacheSingleByCustom(DataObject obj);
    
    abstract protected void cacheListByCustom(List<DataObject> list);
    
    public Class<? extends DataObject> getClz()
    {
        return clz;
    }

    public void setClz(Class<? extends DataObject> clz)
    {
        this.clz = clz;
    }

    public void cacheSingle(DataObject obj){
        if(obj == null)
            return;
        list.add(obj);
        cacheSingleByCustom(obj);
    }
    public void cacheList(List<DataObject> obj){
        if(obj == null || obj.size()<=0)
            return;
        list.addAll(obj);
        cacheListByCustom(obj);
    }
    
    public  List<? extends DataObject> getList(CacheCondition...conds){
        List result = null;
        if(list != null)
            result = CacheUtil.getListFromIterator(list.iterator(), conds);
        if(result == null && CacheUtil.isAllowedFromDB(cacheLevel)){
            //如果缓存中为空，且允许从数据库中二次加载
            result = DBUtil.query(clz, CacheUtil.buildDBCondtions(conds));
            if(result != null)
                cacheList(result);
        }
        return result;
    }
    
    public  DataObject getSingle(CacheCondition...conds){
        DataObject result = null;
        if(list != null)
            result = CacheUtil.getSingleFromIterator(list.iterator(), conds);
        if(result == null && CacheUtil.isAllowedFromDB(cacheLevel)){
            //如果缓存中为空，且允许从数据库中二次加载
              result = DBUtil.querySingle(clz, CacheUtil.buildDBCondtions(conds));
              if(result != null)
                  cacheSingle(result);
          }
        return result;
    }

    public int getCacheLevel()
    {
        return cacheLevel;
    }

    public void setCacheLevel(int cacheLevel)
    {
        this.cacheLevel = cacheLevel;
    }
}
