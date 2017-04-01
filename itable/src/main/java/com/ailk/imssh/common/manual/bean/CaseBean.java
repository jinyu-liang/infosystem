package com.ailk.imssh.common.manual.bean;

import java.util.ArrayList;
import java.util.List;
import jef.database.Field;

/**
 * 对应配置文件中： <BR>
 * <case field="objectId" value="userId"> <BR>
 * <cond field="objectType" value="0" /> <BR>
 * </case>
 * 
 * @author wangjt
 * @Date 2012-9-26
 */
public class CaseBean
{
    private Field field;// 直接保存jef class的Field
    private String value;
    private List<CondBean> condBeanList = new ArrayList<CondBean>();

    public CaseBean(Field field, String value)
    {
        this.field = field;
        this.value = value;
    }

    public Field getField()
    {
        return field;
    }

    public String getValue()
    {
        return value;
    }

    public List<CondBean> getCondBeanList()
    {
        return condBeanList;
    }

    public void addCondBean(CondBean condBean)
    {
        this.condBeanList.add(condBean);
    }
}
