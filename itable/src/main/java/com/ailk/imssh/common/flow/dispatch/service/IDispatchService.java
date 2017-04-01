package com.ailk.imssh.common.flow.dispatch.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import jef.database.AbstractDbClient;
import jef.database.DataObject;
import com.ailk.imssh.common.flow.config.IConfig;

/**
 * @Description:分发数据接口类
 * @author wangjt
 * @Date 2012-9-13
 */
@Transactional
public interface IDispatchService
{

    /**
     * @param dataObjectList:大索引表的数据List
     */
    public void dispatchToSubTables(List<DataObject> dataObjectList, AbstractDbClient dbClient, IConfig config) throws Exception;

}