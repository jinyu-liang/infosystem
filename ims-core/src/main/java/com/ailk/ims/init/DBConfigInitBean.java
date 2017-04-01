package com.ailk.ims.init;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.cache.CacheHouse;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.InitBean;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.xml.BaseNode;

/**
 * 表数据缓存
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 * @Date 2012-06-15 zengxr Query data from DB when no data in cache. getFromDBNoCache
 * @luojb 2012-07-05 缓存无法命中是否从db中读取
 */
public class DBConfigInitBean implements IImsConfigInit
{
    private static String ATTR_BEAN = "bean";

    public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception
    {
        // 合并项
        if (nodeProj != null && CommonUtil.isNotEmpty(nodeProj.getChildren()))
        {
            InitBean.mergeChildrenByAttr(node, nodeProj, ATTR_BEAN);
        }
    }

    public void init(BaseNode node) throws Exception
    {
        DBCacheBean bean = (DBCacheBean) SpringUtil.getBean(ConstantDefine.IMS_DB_CACHE_BEAN);
        bean.setNode(node);
        bean.load();
    }

    public static CacheHouse getCacheHouse()
    {
        DBCacheBean bean = (DBCacheBean) SpringUtil.getBean(ConstantDefine.IMS_DB_CACHE_BEAN);
        CacheHouse cacheHouse = bean.load();// 因为init方法里已经load过一次，所以这次的load其实是从框架的缓存里获取数据
        return cacheHouse;
    }

    /**
     * CacheHouse 已提供相同方法 luojb 2012-10-23
     * 
     * @param <T>
     * @param clz
     * @param conds
     * @return
     * @deprecated
     */
    public static <T extends DataObject> T getSingleCached(Class clz, CacheCondition... conds)
    {
        return (T)getCacheHouse().getSingleCached(clz, conds);
    }

    /**
     * CacheHouse 已提供相同方法 luojb 2012-10-23
     * 
     * @param <T>
     * @param clz
     * @param conditions
     * @return
     * @deprecated
     */
    public static <T extends DataObject> List<T> getCached(Class clz, CacheCondition... conds)
    {
        return getCacheHouse().getCached(clz, conds);
    }

}
