package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;
import jef.database.Field;

/**
 * select main.* from table_main main where exists [not exists] (select 1 from table_exists_1 e1 where e1.connectField =
 * main.connectToField [and other condition extract from condList])
 * 
 * @author wangjt
 */
public class DBExistsCond
{
    private boolean isExists = true;
    private jef.database.Field connectField;
    private jef.database.Field connectToField;
    private List<DBCondition> condList = new ArrayList<DBCondition>();

    public DBExistsCond(Field connectField, Field connectToField)
    {
        this.connectField = connectField;
        this.connectToField = connectToField;
    }

    public boolean isExists()
    {
        return isExists;
    }

    public void setExists(boolean isExists)
    {
        this.isExists = isExists;
    }

    public jef.database.Field getConnectField()
    {
        return connectField;
    }

    public jef.database.Field getConnectToField()
    {
        return connectToField;
    }

    public List<DBCondition> getCondList()
    {
        return condList;
    }

    public void clearConds()
    {
        condList.clear();
    }

    public void addCondition(DBCondition dbCondition)
    {
        condList.add(dbCondition);
    }

    public void addConditons(DBCondition[] dbConditions)
    {
        for (int i = 0; i < dbConditions.length; i++)
        {
            condList.add(dbConditions[i]);
        }
    }
}
