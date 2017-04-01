package com.ailk.imssh.common.commit;

import com.ailk.easyframe.transaction.support.CpfTransactionInfo;
import com.ailk.easyframe.transaction.support.CpfTransactionListener;
import com.ailk.easyframe.transaction.support.CpfTransactionResult;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.error.bean.ErrorListQueue;
/**
 * 
 * @Description 事务监听器
 * @author lijc3
 * @Date 2013-1-7
 */
public class ItableTransListener implements CpfTransactionListener
{
    ImsLogger imsLogger = new ImsLogger(getClass());
    private static ThreadLocal<ItableListenerServiceFlow> serviceFlowContainer = new ThreadLocal<ItableListenerServiceFlow>();

    public void addActionContainer(ItableListenerServiceFlow flow)
    {
        serviceFlowContainer.set(flow);
    }

    @Override
    public void beginTransaction(CpfTransactionInfo txInfo)
    {

    }

    @Override
    public void beforeCommit(CpfTransactionInfo txInfo)
    {

    }

    @Override
    public void afterCompletion(CpfTransactionInfo txInfo, CpfTransactionResult result)
    {
        try
        {
            if (!result.isDbSuccess())
            {
                // db提交失败，如果有mdb成功的情况，需要设置内存中为失败状态,其他的由db回滚自动完成
                ItableListenerServiceFlow flow = serviceFlowContainer.get();
                if (flow != null && flow.getConfig() != null && flow.getDataOjbect() != null)
                {
                    imsLogger.debug("*******because of db commit failed....");
                    IConfig config = flow.getConfig();
                    config.setDBSuccStsFalse(flow.getDataOjbect());
                    // 清除线程
                    imsLogger.debug("******clean db commit fail threadlocal");
                }
            }
            else if (result.isSalSuccess())
            {
                // mdb提交成功，需要移除错误队列中相应信息
                ItableListenerServiceFlow flow = serviceFlowContainer.get();
                if (flow != null)
                {
                    boolean removeFlag = flow.getRemoveFlag();
                    if (removeFlag)
                    {
                        imsLogger.info("******error data deal sucess, remove from error list");
                        ErrorListQueue.removeFirstErrorHolder();
                        imsLogger.debug("*******error list size =  ", ErrorListQueue.getSize());
                    }
                    // 清除线程
                    imsLogger.debug("******clean mdb commit success threadlocal");
                }
            }
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
        finally
        {
            clearServiceFlow();
        }
        imsLogger.debug(" ******exit after completion*****");
    }

    public void clearServiceFlow()
    {
        // 因为本类是单例的，所以每次运行完毕需要清空
        serviceFlowContainer.set(null);
    }
}
