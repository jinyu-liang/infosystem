package com.ailk.imssh.common.flow.scandeal.dealthread;

import org.apache.log4j.Logger;

import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.error.bean.ErrorHolder;
import com.ailk.imssh.common.flow.error.bean.ErrorListQueue;
import com.ailk.imssh.common.flow.scandeal.deal.IBaseDeal;
import com.ailk.imssh.common.flow.scandeal.dealprocess.DealProcess;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.util.ITableUtil;

/**
 * @Description:处理数据线程，对应一个队列
 * @author wangjt
 * @Date 2012-9-13
 */
public class DealThread extends Thread
{
    private Logger imsLogger=Logger.getLogger(getClass());
    private IBaseDeal baseDeal;
    private IConfig config;
    private Queue queue;// 需要处理的一个队列

    public DealThread(Queue queue, IBaseDeal realDeal, IConfig config)
    {
        this.queue = queue;
        this.baseDeal = realDeal;
        this.config = config;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                // 从队列中取出第一个对象（可能为空）
                DataHolder dataHolder = queue.getRemoveFirst();
                if (dataHolder != null)
                {
                    Long busiValue = dataHolder.getBusiValue();
                    if (ErrorListQueue.isPreviousErrorExist(busiValue))
                    {
                        // 该用户有错单，则把该工单放到错误队列中
                        imsLogger.debug("######error data has accour , add data to error list . busi_value = " + busiValue);
                        //ErrorListQueue.addErrorHolderToQueue(new ErrorHolder(dataHolder));
                    }
                    else
                    {
                        imsLogger.debug("######begin to deal data, busi_value = " + busiValue);
                        // 该用户无错单，执行业务操作
                        DealProcess.dealProcess(dataHolder, baseDeal, config);
                    }
                }
                else
                {
                    // 队列为空，则停一秒之后执行
                    ITableUtil.sleep(1000);
                }
            }
            catch (Exception e)
            {
                imsLogger.error(e,e);
            }
        }
    }
}
