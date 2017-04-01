package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import jef.database.DataObject;
import org.apache.log4j.Logger;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.init.DBRelInitBean;
import com.ailk.ims.init.MdbGroupInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.xml.BaseNode;

/**
 * @Description: 关联刷新
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-20
 */
public class DBRelRefreshBean extends ContextBean
{
    private BaseComponent cmp;
    // private List<DataObject> refreshData;//需要放入缓存中
    // private Map<Class<? extends DataObject>, List<DataObject>> refresh_data;
    private DataCacheBean cacheBean;



    public DBRelRefreshBean(IMSContext context)
    {
        super.setContext(context);
    }

    /**
     * @Description: 刷新关联表，为了保持数据一致性
     */
    public void refreshRelations()
    {
        cmp = context.getComponent(BaseComponent.class);
        cacheBean = new DataCacheBean();
        List<Class[]> rels = MdbGroupInitBean.getRelGroups(context.getOper().getBusi_code());
        if (CommonUtil.isEmpty(rels))
            return;
        for (Class[] relClasses : rels)
        {
            refreshGroup(relClasses);
        }

        // 把关联更新的数据放入缓存中，必须在最后放入，否则会造成死循环
        Map<Class<? extends DataObject>, List<DataObject>> refreshData = cacheBean.getCachedData();
        Iterator<List<DataObject>> it = refreshData.values().iterator();
        while (it.hasNext())
        {
            context.cacheList(it.next());
        }
    }

    /**
     * @Description:刷新某一个关联组
     * @param relClasses
     */
    private void refreshGroup(Class[] relClasses)
    {
        List<Class> existList = new ArrayList<Class>();// 缓存中存在
        List<Class> notExistList = new ArrayList<Class>();// 缓存中不存在，即要关联更新的
        StringBuffer sb_class = new StringBuffer();
        for (Class clazz : relClasses)
        {
            sb_class.append("\n").append(clazz.getName());

            if (context.containCache(clazz))
            {
                imsLogger.info("****** exist table : " + clazz.getName());
                existList.add(clazz);
            }
            else
            {
                imsLogger.info("****** not exist table : " + clazz.getName());
                notExistList.add(clazz);
            }
        }
        if (CommonUtil.isEmpty(existList))
        {
            imsLogger.info("****** filter group : \n" + sb_class.substring(1));
            return;// 全部不存在说明这一组的数据都不存在，就不要刷新了
        }

        imsLogger.info("****** deal group : \n" + sb_class.substring(1));

        // 解析需要更新的表，按照路径长短排列
        List queue = new ArrayList();// 存放所有需要更新的路径
        for (Class notExist : notExistList)
        {
            List<BaseNode> path = DBRelInitBean.getOptimizeRelTablePath(existList, notExist);
            if (CommonUtil.isEmpty(path))
                throw new IMSException("no avaible relation to table : " + notExist.getSimpleName());
            // 排序，路径最短的放到队列最前面
            if (CommonUtil.isEmpty(queue))
            {
                queue.add(path);
            }
            else
            {
                int count = queue.size();
                for (int i = 0; i < count; i++)
                {
                    List list = (List) queue.get(i);
                    if (path.size() < list.size())
                    {
                        queue.add(i, path);
                    }
                    else
                    {
                        queue.add(path);
                    }
                }
            }
        }

        // 对未操作过的表进行更新
        for (int i = 0; i < queue.size(); i++)
        {
            List<BaseNode> path = (List<BaseNode>) queue.get(i);
            String strPath = DBRelInitBean.getStringPath(path);
            imsLogger.info("****** begin to refresh : " + strPath);

            refreshTargetTable(path);

            // existList.add(path.get(path.size()-1).getRelClass());

            imsLogger.info("****** end to refresh : " + strPath);

        }

        // 对已操作过的表,是many-to-one的表需要进行自身更新
        for (int i = 0; i < existList.size(); i++)
        {
            Class table = existList.get(i);
            List<BaseNode> relNodes = DBRelInitBean.getRelTables(table);
            for (BaseNode rel : relNodes)
            {
                if (rel.getAttribute(DBRelInitBean.ATTR_TYPE) == DBRelInitBean.TYPE_MANY_2_ONE)
                {
                    List<BaseNode> fList = rel.getChildren();
                    refreshSelfTable(table, fList);
                    continue;
                }
            }
        }
    }

    // 刷新已操作过数据的表，因为有可能当前表的其它记录也需要关联刷新
    private void refreshSelfTable(Class table, List<BaseNode> fields)
    {
        try
        {
            List<DataObject> cacheList = context.getAllCacheList(table);

            Vector<String> hasRefreshed = new Vector<String>();// 标志相关记录已更新，防止老新数据重复更新
            for (DataObject cache : cacheList)
            {
                String field_key = new String();
                List<DBCondition> fValues = DBRelInitBean.getRootDBConditions(fields, cache);
                for (DBCondition value : fValues)
                {
                    field_key += "@" + value.getValue().toString();
                }
                field_key = field_key.substring(1);
                // 更新过的就不用再更新了,比如update会有两条记录，其中一条对其它表做了关联更新，另一条就不需要再去刷其它表了。
                if (hasRefreshed.contains(field_key))
                    continue;

                refreshSelfRecords(cache, fields);

                hasRefreshed.add(field_key);
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    // 刷新未操作过数据的表
    private void refreshTargetTable(List<BaseNode> path)
    {
        try
        {
            BaseNode startNode = path.get(0);// 第一个肯定在缓存中存在，作为数据基点
            List<DataObject> cacheList = context.getAllCacheList(DBRelInitBean.getRootClass(startNode));
            List<BaseNode> fields = startNode.getChildren();

            Vector<String> hasRefreshed = new Vector<String>();// 标志相关记录已更新，防止老新数据重复更新
            for (DataObject cache : cacheList)
            {
                String field_key = new String();
                List<DBCondition> fValues = DBRelInitBean.getRootDBConditions(fields, cache);
                for (DBCondition value : fValues)
                {
                    field_key += "@" + value.getValue().toString();
                }
                field_key = field_key.substring(1);
                // 更新过的就不用再更新了,比如update会有两条记录，其中一条对其它表做了关联更新，另一条就不需要再去刷其它表了。
                if (hasRefreshed.contains(field_key))
                    continue;

                refreshTargetRecords(cache, path);// 更新目标关联表

                hasRefreshed.add(field_key);
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    private void refreshSelfRecords(DataObject base, List<BaseNode> fields) throws Exception
    {
        // 如果是多对一，则更新自身表中的其它记录
        DataObject newBaseObj = base.getClass().newInstance();
        List<DBCondition> fValues = DBRelInitBean.getRootDBConditions(fields, base);

        // 更新的只是其它so_nbr不等于当前的记录，所以需要加上so_nbr条件
        jef.database.Field field_sbNbr = DBUtil.getJefField(base.getClass(), ConstantDefine.ENTITY_FIELD_SO_NBR);
        fValues.add(new DBCondition(field_sbNbr, context.getSoNbr(), jef.database.Condition.Operator.NOT_EQUALS));

        imsLogger.debug("****** refresh self table[" + base.getClass().getSimpleName() + "] other records : "
                + DBRelInitBean.getStringCondition(fValues));
        List<DataObject> refrehList = cmp.updateByConditionWithNoCache(newBaseObj, null, null,
                fValues.toArray(new DBCondition[fValues.size()]));
        if (!CommonUtil.isEmpty(refrehList))
            cacheBean.cacheList(refrehList);
    }

    private void refreshTargetRecords(DataObject base, List<BaseNode> path) throws Exception
    {
        List<DataObject> baseList = new ArrayList<DataObject>();
        baseList.add(base);

        for (int i = 0; i < path.size(); i++)
        {
            BaseNode node = path.get(i);
            Class<? extends DataObject> relClass = DBRelInitBean.getRelClass(node);

            List<DBCondition> conds = DBRelInitBean.buildRelConditions(node, baseList);

            jef.database.Field field_sbNbr = DBUtil.getJefField(relClass, ConstantDefine.ENTITY_FIELD_SO_NBR);
            conds.add(new DBCondition(field_sbNbr, context.getSoNbr(), jef.database.Condition.Operator.NOT_EQUALS));
            DBCondition[] arrConds = conds.toArray(new DBCondition[conds.size()]);

            String rel = node.getAttribute(DBRelInitBean.ATTR_REL);
            if (i == path.size() - 1)
            {
                imsLogger.info("****** refresh table[" + rel + "]");
                // 说明是最后一个关联，即relClass就是最终需要更新的目标
                List<DataObject> refrehList = cmp.updateByConditionWithNoCache(relClass.newInstance(), null, null, arrConds);
                if (!CommonUtil.isEmpty(refrehList))
                {
                    cacheBean.cacheList(refrehList);
                }
            }
            else
            {
                imsLogger.info("****** relate table[" + rel + "]");
                // 查询关联对象值
                if (cacheBean.containCache(relClass))
                {
                    // 缓存中存在，说明已经被更新过
                    baseList = cacheBean.getAllCacheList(relClass);
                }
                else
                {
                    // 从数据库中查找
                    baseList = cmp.query(relClass, arrConds);
                }
                if (CommonUtil.isEmpty(baseList))
                {
                    imsLogger.info("****** relate table[" + rel + "] : no record.");
                    return;// 中间关联对象查不到数据则直接返回
                }
            }
        }
    }

}
