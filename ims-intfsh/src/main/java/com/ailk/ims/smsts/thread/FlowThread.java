package com.ailk.ims.smsts.thread;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.flowbase.IBaseFlow;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;

/**
 * @Description:一个分表扫描的线程类
 * @author wangjt
 * @Date 2012-8-7
 * @Date 2013-1-15 增加线程个数参数
 */
public class FlowThread extends Thread
{
    private ImsLogger imsLogger = new ImsLogger(FlowThread.class);
    private SmsParam param;

    public FlowThread(SmsParam smsParam)
    {
        this.param = smsParam;
    }

    @Override
    public void run()
    {
        try
        {
            if (ContextHolder.getRequestContext() == null)
            {
                ContextHolder.requestInit();
            }
            long t1 = System.currentTimeMillis();
            String logInfo = CommonUtil.concat("###[flag", t1, "]methodName:", param.getMethodName(), ",subTableName:",
                    param.getSubTableName());
            imsLogger.info(logInfo, " start...");
            IBaseFlow flowInstance = (IBaseFlow) param.getFlowInstanceClass().newInstance();
            flowInstance.dealProcess(param);

            imsLogger.info(t1, logInfo, " finish!!! ");
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
    }

}
