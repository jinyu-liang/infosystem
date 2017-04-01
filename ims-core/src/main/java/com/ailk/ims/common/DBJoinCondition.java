package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import jef.database.QB;
import jef.database.meta.JoinKey;
import jef.database.query.Join;
import jef.database.query.Query;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;

/**
 * @Description: 多表关联查询条件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-4
 */
public class DBJoinCondition
{
    // 关联部件
    private List<JoinPart> joinParts;

    public DBJoinCondition(Class<? extends DataObject> table)
    {
        // 首表的查询条件目前框架不支持on，所以都只能写在where里，即使用DBCondition
        joinParts = new ArrayList<JoinPart>();

        joinParts.add(new JoinPart(table));
    }

    public DBJoinCondition innerJoin(Class<? extends DataObject> table, DBRelation... relations)
    {
        // 这里的DBRelation既可以是某两张表之间的关联，即table1.field1 = table2.field1
        // 也可以是当前表的条件，即table.field = value
        joinParts.add(new JoinPart(table, JoinPart.TYPE_INNER, relations));

        /*
         * JoinKey[] joinKeys = createJoinKeys(table,relations); if(join == null){ join = QB.innerJoin(
         * getOrCreateQuery(this.table), getOrCreateQuery(table), joinKeys ); }else{ join.innerJoin(getOrCreateQuery(table),
         * joinKeys); }
         */
        return this;
        // return join(table,JOINTYPE_INNER,relations);
    }

    public DBJoinCondition leftJoin(Class<? extends DataObject> table, DBRelation... relations)
    {
        joinParts.add(new JoinPart(table, JoinPart.TYPE_LEFT, relations));

        /*
         * JoinKey[] joinKeys = createJoinKeys(table,relations); if(join == null){ join = QB.leftJoin(
         * getOrCreateQuery(this.table), getOrCreateQuery(table), joinKeys ); }else{ join.leftJoin(getOrCreateQuery(table),
         * joinKeys); }
         */
        return this;
    }

    public DBJoinCondition rightJoin(Class<? extends DataObject> table, DBRelation... relations)
    {
        joinParts.add(new JoinPart(table, JoinPart.TYPE_RIGHT, relations));

        /*
         * JoinKey[] joinKeys = createJoinKeys(table,relations); if(join == null){ join = QB.rightJoin(
         * getOrCreateQuery(this.table), getOrCreateQuery(table), joinKeys ); }else{ join.rightJoin(getOrCreateQuery(table),
         * joinKeys); }
         */
        return this;
    }

    /**
     * @Description: 把之前设置的joinParts创建成真正的关联查询条件
     * @author : wuyj
     * @date : 2011-10-21
     */
    public Join build()
    {
        if (CommonUtil.isEmpty(joinParts) || joinParts.size() < 2)
        {
            throw IMSUtil.throwBusiException("invalid join query");
        }
        JoinPart part0 = joinParts.get(0);// 首表，没有连接条件
        JoinPart part1 = joinParts.get(1);// 第二张表，有连接条件

        Join join = createJoin(part0, part1);// 创建第一个连接，即第一张表和第二张表的连接

        // 创建从第三张表开始的连接
        for (int i = 2; i < joinParts.size(); i++)
        {
            addJoin(join, joinParts.get(i));
        }

        return join;
    }

    /**
     * @Description: 添加关联对象中的连接，一般是第三张表开始
     * @author : wuyj
     * @date : 2011-10-21
     * @param join
     * @param part
     */
    private void addJoin(Join join, JoinPart part)
    {
        Query query = part.getQuery();
        JoinKey[] joinKeys = createJoinKeys(part);
        String joinType = part.getType();

        if (JoinPart.TYPE_INNER.equals(joinType))
        {
            join.innerJoin(query, joinKeys);
        }
        else if (JoinPart.TYPE_LEFT.equals(joinType))
        {
            join.leftJoin(query, joinKeys);
        }
        else if (JoinPart.TYPE_RIGHT.equals(joinType))
        {
            join.rightJoin(query, joinKeys);
        }
    }

    /**
     * @Description:创建关联对象，一般是第一张表和第二张表
     * @author : wuyj
     * @date : 2011-10-21
     * @param part0
     * @param part1
     * @return
     */
    private Join createJoin(JoinPart part0, JoinPart part1)
    {
        Join join = null;
        Query query0 = part0.getQuery();
        Query query1 = part1.getQuery();

        List<JoinKey> joinKeyList = new ArrayList<JoinKey>();
        /*
         * JoinKey[] joinKeys0 = createJoinKeys(part0.getRelations()); 框架对左边的条件字段还不支持 if(!CommonUtil.isEmpty(joinKeys0)){
         * for(JoinKey joinKey : joinKeys0){ joinKeyList.add(joinKey); } }
         */

        JoinKey[] joinKeys1 = createJoinKeys(part1);
        if (!CommonUtil.isEmpty(joinKeys1))
        {
            for (JoinKey joinKey : joinKeys1)
            {
                joinKeyList.add(joinKey);
            }
        }
        String joinType = part1.getType();
        JoinKey[] joinKeys = joinKeyList.toArray(new JoinKey[joinKeyList.size()]);

        if (JoinPart.TYPE_INNER.equals(joinType))
        {
            join = QB.innerJoin(query0, query1, joinKeys);
        }
        else if (JoinPart.TYPE_LEFT.equals(joinType))
        {
            join = QB.leftJoin(query0, query1, joinKeys);
        }
        else if (JoinPart.TYPE_RIGHT.equals(joinType))
        {
            join = QB.rightJoin(query0, query1, joinKeys);
        }

        return join;
    }

    /*
     * private Query addQuery(Class<? extends DataObject> tableClass){ if(queryMap == null) queryMap = new
     * LinkedHashMap<Class,Query>(); Query query = QB.create(tableClass); queryMap.put(tableClass, query); return query; }
     */
    private JoinKey[] createJoinKeys(JoinPart part)
    {
        List<DBRelation> relations = part.getRelations();
        if (CommonUtil.isEmpty(relations))
            return null;
        List<JoinKey> joinKeys = new ArrayList<JoinKey>();
        for (int i = 0; i < relations.size(); i++)
        {
            DBRelation item = relations.get(i);
            if (item.getField2() != null)
            {
                // 是连接字段,这里有个重要点需要注意，连接字段必须要按照顺序来，即left必须是上一张表，right必须是当前表
                if (IMSUtil.getClassByField(item.getField1()) == part.getTable())
                {
                    joinKeys.add(QB.on(item.getField2(), item.getField1()));
                }
                else
                {
                    joinKeys.add(QB.on(item.getField1(), item.getField2()));
                }
            }
            else
            {
                // 是条件字段
                joinKeys.add(QB.on(item.getField1(), item.getOperator(), item.getValue()));
            }
        }
        return joinKeys.toArray(new JoinKey[joinKeys.size()]);
    }

    /*
     * private Query getOrCreateQuery(Class<? extends DataObject> tableClass){ Query query = getQuery(tableClass); if(query !=
     * null) return query; return addQuery(tableClass); }
     */
    /**
     * @Description: 根据一个表名从获取对应的Query对象
     * @author : wuyj
     * @date : 2011-10-21
     * @param tableClass
     * @return
     */
    public Query getQuery(Class<? extends DataObject> tableClass)
    {
        for (JoinPart part : joinParts)
        {
            if (tableClass == part.getTable())
            {
                return part.getQuery();
            }
        }
        return null;
    }

    public List<JoinPart> getJoinParts()
    {
        return joinParts;
    }

}
