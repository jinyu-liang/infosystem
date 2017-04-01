package com.ailk.imssh.common.flow.bean;

import java.util.List;
import jef.database.DataObject;
import com.ailk.imssh.common.handler.BaseHandler;

/**
 * @Description:索引表upfield字段和处理类以及对应的数据表的包装对象
 * @author wangjt
 * @Date 2012-5-17
 */
public class IndexHandlerObject
{
    private Integer index;// 从1开始计数，表示upfield第一位
    private Class<? extends BaseHandler> handler;// 对应的处理类
    private List<Class<? extends DataObject>> itableClassList;// 对应的数据表列表
    private String itableName;// 对应的数据表的表名[取配置的第一张i表]

    public IndexHandlerObject(Integer index, Class<? extends BaseHandler> handler,
            List<Class<? extends DataObject>> itableClassList, String itableName)
    {
        this.index = index;
        this.handler = handler;
        this.itableClassList = itableClassList;
        this.itableName = itableName;
    }

    public Integer getIndex()
    {
        return index;
    }

    public Class<? extends BaseHandler> getHandler()
    {
        return handler;
    }

    public List<Class<? extends DataObject>> getItableClassList()
    {
        return itableClassList;
    }

    public String getItableName()
    {
        return itableName;
    }
}
