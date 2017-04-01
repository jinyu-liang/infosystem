package com.ailk.ims.init;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.cache.BaseCacheParser;
import com.ailk.ims.cache.CacheHouse;
import com.ailk.ims.cache.CacheUtil;
import com.ailk.ims.cache.DefaultCacheParser;
import com.ailk.ims.init.check.IConfigCheck;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;
/**
 * DB缓存的Spring bean
 * @Description
 * @author luojb
 * @Date 2012-3-1
 * @modify xieqr 2012-3-28  修改对CommonUtil.isNotEmpty(error) 判断
 * @luojb 2012-07-05 缓存无法命中是否从db中读取
 */
public class DBCacheBean
{
    private static String TAG_TABLE = "table";
    private static String ATTR_BEAN = "bean";
    private static String ATTR_CACHELEVEL = "cacheLevel";
    private static String TAG_CONDITION = "condition";
    private static String TAG_ORDER = "order";
    private static String TAG_CHECK = "check";
    private static String ATTR_PARSER = "parser";
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    
    private BaseNode node = null;

    public void setNode(BaseNode node)
    {
        this.node = node;
    }

    public BaseNode getNode()
    {
        return node;
    }
    
    public CacheHouse load(){
        try{
            CacheHouse cacheHouse = new CacheHouse();
            List<BaseNode> tables = node.getChildrenByTagName(TAG_TABLE);
            if (CommonUtil.isEmpty(tables))
            {
                IMSUtil.throwBusiException("no <table> tag under <config " + node.getAttribute(ATTR_BEAN) + ">");
            }
            for (BaseNode table : tables)
            {
                imsLogger.debug("----------load["+table.getAttribute(ATTR_BEAN)+"]");
                String tableEntityName = table.getAttribute(ATTR_BEAN);
                if (!CommonUtil.isValid(tableEntityName))
                {
                    IMSUtil.throwBusiException("bean of <table> is null");
                }
                
                Integer cacheLevel = table.getIntAttribute(ATTR_CACHELEVEL);
                String parserName = table.getAttribute(ATTR_PARSER);
                Class entityClass = Class.forName(tableEntityName);
                if(cacheLevel == null)
                    cacheLevel = 1;
                
                List<DataObject> cacheList = new ArrayList<DataObject>();
                if(CacheUtil.isInitCache(cacheLevel))
                {
                    BaseNode orderNode = table.getChildByTagName(TAG_ORDER);
                    BaseNode conditionNode = table.getChildByTagName(TAG_CONDITION);
                    String condition = conditionNode == null ? null : conditionNode.getText();
                    String order = orderNode == null ? null : orderNode.getText();
                    cacheList = (List<DataObject>)DBUtil.queryByNative(entityClass, condition, order);
                }
                
                BaseCacheParser cacheParser = null;
                if(CommonUtil.isValid(parserName)){
                    Class parserClass = Class.forName(parserName);
                    cacheParser = (BaseCacheParser)ClassUtil.instance(parserClass);
                }else{
                    cacheParser = new DefaultCacheParser();
                }
                cacheParser.setClz(entityClass);
                cacheParser.setCacheLevel(cacheLevel);
                cacheParser.cacheList(cacheList);
                check(cacheParser, table);
                cacheHouse.addCacheParser(entityClass,cacheParser);
                
            }
            imsLogger.debug("----------load finished");
            return cacheHouse;
        }catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }
    
    private void check(BaseCacheParser parser,BaseNode table) throws Exception
    {
        BaseNode checkNode = table.getChildByTagName(TAG_CHECK);
        if(checkNode == null)
            return ;
        String checkBeanName = checkNode.getAttribute(ATTR_BEAN);
        if(checkBeanName == null)
            return;
        Class entityClass = Class.forName(checkBeanName);
        IConfigCheck check = (IConfigCheck)ClassUtil.instance(entityClass);
        check.check(checkNode, parser);
    }
}
