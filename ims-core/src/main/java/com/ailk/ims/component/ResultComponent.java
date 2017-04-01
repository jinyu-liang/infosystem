package com.ailk.ims.component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import com.ailk.ims.common.CondTable;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.LogUtil;

/**
 * @Description:多表关联查询组件
 * @author wangjt
 * @Date 2012-1-10
 */
public class ResultComponent<R extends DataObject> extends BaseComponent
{
    /**
     * 返回一条记录，可能为空
     * 
     * @author wangjt 2012-1-10
     */
    public R getResult(ResultTable<R> resultTable)
    {
        List<R> resultList = this.getResultList(resultTable);
        if (resultList == null)
        {
            return null;
        }
        return resultList.get(0);
    }

    /**
     * 返回记录列表，可能为空
     * 
     * @author wangjt 2012-1-10
     */
    public List<R> getResultList(ResultTable<R> resultTable)
    {
        List<CondTable> condTableList = resultTable.getCondTableList();
        if (resultTable.getCondTableList().isEmpty())
        {
            throw new RuntimeException("please invoke ResultTable.addCondTable() method first");
        }
        Set keyFieldValueSet = null;
        for (int i = 0; i < condTableList.size(); i++)
        {
            CondTable condTable = condTableList.get(i);
            if (keyFieldValueSet != null)
            {
                condTable.getConds().add(new DBCondition(condTable.getKeyField(), keyFieldValueSet, Operator.IN));
            }

            List list = super.query(condTable.getTableClass(),
                    condTable.getConds().toArray(new DBCondition[condTable.getConds().size()]));
            if (list == null || list.isEmpty())
            {
                return null;
            }
            keyFieldValueSet = new HashSet();
            for (int j = 0; j < list.size(); j++)
            {
                Object object = list.get(j);
                Object fieldValue = getFieldValue(object, condTable.getKeyField().name());
                keyFieldValueSet.add(fieldValue);
            }
        }

        resultTable.getConds().add(new DBCondition(resultTable.getIdInField(), keyFieldValueSet, Operator.IN));
        List<R> list = super.query(IMSUtil.getClassByField(resultTable.getIdInField()),
                resultTable.getConds().toArray(new DBCondition[resultTable.getConds().size()]));
        if (list == null || list.isEmpty())
        {
            return null;
        }
        return list;
    }

    /**
     * 自定义方法，不抛异常
     */
    private Object getFieldValue(Object obj, String fieldName)
    {
        try
        {
            return ClassUtil.getPropertyValue(obj, fieldName);
        }
        catch (Exception e)
        {
            imsLogger.error(e.getMessage());
            return null;
        }
    }
}
