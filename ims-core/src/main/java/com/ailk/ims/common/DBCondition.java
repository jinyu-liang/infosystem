package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;

/**
 * @Description: 从数据库查询DataObject查询条件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-1
 */
public class DBCondition extends CacheCondition
{
    private List<DBCondition> conditions;// 嵌套条件，和上面三个是互斥，只能用一种

    public DBCondition(DBCondition... conds)
    {
        super();
        if (CommonUtil.isEmpty(conds) || conds.length < 2)
        {
            throw IMSUtil.throwBusiException("At least 2 conditions should be passed in.");
        }
        conditions = new ArrayList<DBCondition>();
        for (DBCondition cond : conds)
        {
            conditions.add(cond);
        }

    }

    public DBCondition(jef.database.Field field, Object value)
    {
        super(field, value);
    }

    public DBCondition(jef.database.Field field, Object value, Operator operator)
    {
        super(field, value);
        super.setOperator(operator);
    }

    public List<DBCondition> getConditions()
    {
        return conditions;
    }

    /**
     * @Description: 获取条件中真正起作用的单个条件
     * @return
     */
    public static List<DBCondition> getIndividualConditions(DBCondition[] conditions)
    {
        List<DBCondition> result = new ArrayList<DBCondition>();

        for (DBCondition cond : conditions)
        {
            List<DBCondition> subConds = cond.getConditions();
            if (subConds == null)
            {
                result.add(cond);
            }
            else
            {
                List<DBCondition> subList = getIndividualConditions(subConds.toArray(new DBCondition[subConds.size()]));
                result.addAll(subList);
            }
        }
        return result;
    }

    /**
     * @Description: 按照表来分组条件
     * @param conditions
     * @return
     */
    public static Map<Class<? extends DataObject>, List<DBCondition>> parseConditionWithGroup(DBCondition[] conditions)
    {
        if (CommonUtil.isEmpty(conditions))
            return null;

        Map<Class<? extends DataObject>, List<DBCondition>> conditionGroup = new HashMap<Class<? extends DataObject>, List<DBCondition>>();

        List<DBCondition> individualConds = DBCondition.getIndividualConditions(conditions);
        for (DBCondition indivCond : individualConds)
        {
            Class dmClass = IMSUtil.getClassByField(indivCond.getField());
            List condList = conditionGroup.get(dmClass);
            if (condList == null)
            {
                condList = new ArrayList<DBCondition>();
                conditionGroup.put(dmClass, condList);
            }
            condList.add(indivCond);
        }
        return conditionGroup;
    }

    public static DBCondition getFirstIndividualCondition(DBCondition condition)
    {
        List<DBCondition> subList = condition.getConditions();
        if (subList == null)
            return condition;
        for (DBCondition subCond : subList)
        {
            DBCondition sub = getFirstIndividualCondition(subCond);
            if (sub != null)
                return sub;
        }
        return null;
    }

    public String toString()
    {
        if (conditions == null)
        {
            Class clazz = IMSUtil.getClassByField(this.getField());
            String strField = clazz.getSimpleName() + "." + this.getField().name();
            String strOper = this.getOperator() == null ? "=" : getOperator().getKey();
            String strValue = this.getValue() == null ? "null" : this.getValue().toString();
            return strField + " " + strOper + " " + strValue;
        }
        else
        {
            return super.toString();
        }

    }
}
