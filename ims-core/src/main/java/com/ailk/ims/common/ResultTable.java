package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import jef.database.Field;

/**
 * @Description:结果表
 * @author wangjt
 * @Date 2012-1-9
 */
public class ResultTable<R extends DataObject>
{
    /**
     * 返回结果表的关键字段，不可为空
     */
    private jef.database.Field idInField;

    /**
     * 结果表查询的条件，可以为空
     */
    private List<DBCondition> conds = new ArrayList<DBCondition>();

    /**
     * cond的相关表
     */
    private List<CondTable> condTableList = new ArrayList();

    /**
     * @param idInField:不能为null
     * @param conds：可以为null
     */
    public ResultTable(Field idInField, DBCondition... conds)
    {
        super();
        if (idInField == null)
        {
            throw new RuntimeException("invalid invoke: idInField must input");
        }
        this.idInField = idInField;
        if (conds != null)
        {
            for (int i = 0; i < conds.length; i++)
            {
                this.conds.add(conds[i]);
            }
        }
    }

    /**
     * 增加一个cond条件(该方法至少被调用一次)
     * 
     * @param keyField:不可为空
     * @param conds：可以为空
     */
    public void addCondTable(jef.database.Field keyField, DBCondition... conds)
    {
        condTableList.add(new CondTable(keyField, conds));
    }

    public jef.database.Field getIdInField()
    {
        return idInField;
    }

    public List<DBCondition> getConds()
    {
        return conds;
    }

    public List<CondTable> getCondTableList()
    {
        return condTableList;
    }
}
