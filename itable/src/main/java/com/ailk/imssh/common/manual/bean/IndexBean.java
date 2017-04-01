package com.ailk.imssh.common.manual.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * manual_modify的upfield的配置结构对象
 * 
 * @Description
 * @author wangjt
 * @Date 2012-9-26
 */
public class IndexBean
{
    private List<ClassBean> classBeanList = new ArrayList<ClassBean>();

    public List<ClassBean> getClassBeanList()
    {
        return classBeanList;
    }

    public void addClassBean(ClassBean classBean)
    {
        this.classBeanList.add(classBean);
    }
}
