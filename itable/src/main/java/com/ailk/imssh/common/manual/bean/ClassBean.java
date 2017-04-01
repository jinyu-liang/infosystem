package com.ailk.imssh.common.manual.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <class name="com.ailk.openbilling.persistence.acct.entity.CaAccount"><BR>
 * <cond field="acctId" value="acctId" /><BR>
 * </class><BR>
 * 
 * @author wangjt
 * @Date 2012-9-26
 */
public class ClassBean
{
    private Class clazz;
    // 下面两个只可能有一个有值
    private List<CondBean> condBeanList;
    private List<CaseBean> caseBeanList;

    public ClassBean(Class clazz)
    {
        this.clazz = clazz;
    }

    public Class getClazz()
    {
        return clazz;
    }

    public List<CondBean> getCondBeanList()
    {
        return condBeanList;
    }

    public void addCondBean(CondBean condBean)
    {
        if (condBeanList == null)
        {
            this.condBeanList = new ArrayList<CondBean>();
        }
        this.condBeanList.add(condBean);
    }

    public List<CaseBean> getCaseBeanList()
    {
        return caseBeanList;
    }

    public void addCaseBean(CaseBean caseBean)
    {
        if (this.caseBeanList == null)
        {
            this.caseBeanList = new ArrayList<CaseBean>();
        }

        this.caseBeanList.add(caseBean);
    }
}