package com.ailk.imssh.common.flow.scandeal.dealprocess;

import java.sql.SQLException;
import java.util.Date;
import com.ailk.easyframe.transaction.support.CpfCommitMixedException;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.error.bean.ErrorHolder;
import com.ailk.imssh.common.flow.error.bean.ErrorListQueue;
import com.ailk.imssh.common.flow.scandeal.deal.IBaseDeal;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;
import com.ailk.imssh.common.util.ITableUtil;

/**
 * @Description:处理流程
 * @author wangjt
 * @Date 2012-11-8
 */
public class ErrDealProcess
{
    private static final ImsLogger imsLogger = new ImsLogger(ErrDealProcess.class);

    /**
     * 执行一个错误工单
     */
    public static void dealProcess(DataHolder dataHolder, IBaseDeal baseDeal, IConfig config)
    {
        ITableContext context = new ITableContext();
        long start = System.currentTimeMillis();
        try
        {
            ITableUtil.requestInit(); // 初始化request

            // 1、数据入库 2、移到历史表 3、上发mdb
            baseDeal.dealProcess(dataHolder, context, config);
            imsLogger.error("====================End to deal Result:Success====================");
        }
        catch (Exception e)
        {
            try
            {
                String errorMsg = "";
                long errorCode = 0;
                //处理SAL提交异常
                if (e instanceof CpfCommitMixedException)
                {
                    imsLogger.error("%%%%%% mdb commit exception catch ");
                    errorCode = EnumCodeExDefine.MDB_COMMIT_FAIL;
                    errorMsg = ((BaseException) e).getLocalizedMessage();
                }
                else if (e instanceof BaseException)
                {
                    imsLogger.error(e, e);
                    errorMsg = ((BaseException) e).getLocalizedMessage();
                    try
                    {
                        errorCode = Long.parseLong(((BaseException) e).getCode());
                    }
                    catch (Exception e2)
                    {

                        errorCode = ErrorCodeDefine.UNKNOWN_ERROR;
                    }
                }
                else
                {
                    if (e instanceof SQLException)
                    {
                        imsLogger.error(("SQL error at:" + ((SQLException) e).getSQLState()), e);
                        errorMsg = e.getClass().getName() + " : " + e.getMessage();
                    }
                    else
                    {
                        imsLogger.error(e, e);
                        errorMsg = e.getClass().getName() + " : " + e.getMessage();
                    }
                    errorCode = ErrorCodeDefine.UNKNOWN_ERROR;
                }

                Date localDate = context.getLocalDate();
//                // 如果是错单
//                if (dataHolder.isError())
//                {
//                    ErrorHolder errorHolder = (ErrorHolder) dataHolder;
//                    // 需要判断是否重试次数超限
//                    if (errorHolder.isExceedMaxRetryTime())
//                    {
//                        // 设置到错误表的数据 报错为1048593的时候 只需要重新上发即可
//                        // 如果是，移到err表
//                        baseDeal.moveToErrTable(errorHolder, localDate, errorCode, errorMsg, config);
//                        // 从队列中移除该错单[该错单肯定是第一个错单] 不是mdb提交失败 需要移除
//                        if (!errorHolder.isMdbCommitFail())
//                        {
//                            imsLogger.debug("***not mdb commit fail,need remove errorHolder from error queue");
//                            if(!errorHolder.isRemove()){
//                                ErrorListQueue.removeFirstErrorHolder();
//                            }
//                        }
//                        return;
//                    }
//                }
                // 
                baseDeal.dealWhenFail(dataHolder, localDate, errorCode, errorMsg, config);

            }
            catch (Exception e2)
            {
                imsLogger.error(e2, e2);
            }
        }
        finally
        {
            context.destroy();
            ContextHolder.requestClear();
            if (SysUtil.getBoolean("OBJECT_DUMP", false))
            {
                imsLogger.error(new Object[]{start, "*******end to do all thing "});
            }
        }
    }

}
