package com.ailk.imssh.common.manual.bean;

import jef.database.Field;

/**
 * 对应配置文件中： <BR>
 * <cond field="acctId" value="acctId" />
 * 
 * @author wangjt
 * @Date 2012-9-26
 */
public class CondBean
{
    private Field field;// 直接保存jef class的Field
    private String value;

    public Field getField()
    {
        return field;
    }

    public String getValue()
    {
        return value;
    }

    public CondBean(Field field, String value)
    {
        super();
        this.field = field;
        this.value = value;
    }
}
