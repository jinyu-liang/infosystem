package com.ailk.imssh.common.flow.dispatch.dispatch;

import com.ailk.imssh.common.flow.config.BatchDataIndexConfig;
import com.ailk.imssh.common.flow.config.IConfig;

/**
 * @Description:i_data_index_yyyyMMdd 数据分发
 * @author wangjt
 * @Date 2012-9-13
 */
public class BatchIndexDispatch extends Dispatch
{
    /**
     * 启动分发数据线程
     */
    public static void main(String[] args)
    {
        new BatchIndexDispatch().doDispatch();
    }

    @Override
    protected IConfig getConfig()
    {
        return BatchDataIndexConfig.getInstance();
    }
}
