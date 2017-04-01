package com.ailk.imssh.common.flow.scandeal.deal;

import java.util.Date;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.error.bean.ErrorHolder;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;

/**
 * @Description:接口表处理接口
 * @author wangjt
 * @Date 2012-6-18
 */
public interface IBaseDeal
{
    /**
     * 业务处理失败时的操作(子类可以自行决定如何控制事务)
     */
    public void dealWhenFail(DataHolder dataHolder, Date dealDate, Long errorCode, String errorMsg, IConfig config)
            throws Exception;

    /**
     * 业务处理
     */
    public void dealProcess(DataHolder dataHolder, ITableContext context, IConfig config) throws Exception;

    /**
     * 重试多次失败的数据，移到错误表
     */
    public void moveToErrTable(ErrorHolder errorHolder, Date dealDate, Long errorCode, String errorMsg, IConfig config)
            throws Exception;
}