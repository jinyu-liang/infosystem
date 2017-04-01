package com.ailk.imssh.common.flow.dispatch.dispatch;

import com.ailk.imssh.common.flow.config.DataIndexConfig;
import com.ailk.imssh.common.flow.config.IConfig;

/**
 * @Description:i_data_index_yyyyMMdd 数据分发
 * @author wangjt
 * @Date 2012-9-13
 */
public class DataIndexDispatch extends Dispatch
{
    /**
     * 启动分发数据线程
     */
    public static void main(String[] args)
    {
        new DataIndexDispatch().doDispatch();
    }

    @Override
    protected IConfig getConfig()
    {
        return DataIndexConfig.getInstance();
    }
}
