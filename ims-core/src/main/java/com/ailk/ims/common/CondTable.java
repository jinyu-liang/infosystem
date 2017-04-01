package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.util.IMSUtil;

/**
 * @Description:条件表
 * @author wangjt
 * @Date 2012-1-9
 */
public class CondTable
{
    /**
     * cond表的关键字段，不可为null
     */
    private jef.database.Field keyField;
    /**
     * cond表查询的条件，可以为空
     */
    private List<DBCondition> conds = new ArrayList<DBCondition>();

    /**
     * 条件表对应的类
     */
    private Class<? extends DataObject> tableClass;

    /**
     * @param keyField:不可为空
     * @param conds：可以为空
     */
    public CondTable(Field keyField, DBCondition... conds)
    {
        super();
        if (keyField == null)
        {
            throw new RuntimeException("invalid invoke: keyField must input");
        }
        this.keyField = keyField;

        if (conds != null)
        {
            for (int i = 0; i < conds.length; i++)
            {
                this.conds.add(conds[i]);
            }
        }

        tableClass = IMSUtil.getClassByField(keyField);
    }

    public jef.database.Field getKeyField()
    {
        return keyField;
    }

    public List<DBCondition> getConds()
    {
        return conds;
    }

    public Class<? extends DataObject> getTableClass()
    {
        return tableClass;
    }

}
