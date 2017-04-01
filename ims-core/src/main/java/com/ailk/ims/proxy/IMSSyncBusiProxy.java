package com.ailk.ims.proxy;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseSyncBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description: 定时任务的业务代理类,此类是单例的，请不要定义任何与当前请求相关的数据！！！
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-1
 */
@Transactional
public class IMSSyncBusiProxy implements IBusiProxy
{
    private ImsLogger imsLogger=new ImsLogger(this.getClass());

    public BaseResponse doService(IMSContext context, Object... input) throws BaseException
    {
        String serviceName = context.getServiceName();
        String methodName = context.getMethodName();
        // 根据syncBean获取业务处理类
        BaseSyncBean syncBean = BusiUtil.getSyncBean(serviceName, methodName);
        if (syncBean == null)
        {
            throw new IMSException("no sync bean is configured with <service: " + serviceName + "> <method: " + methodName + ">");
        }

        syncBean.setContext(context);

        Object[] busiResult = null;
        // 执行业务操作
        imsLogger.info("****** begin to doBusiness");
        if (CommonUtil.isEmpty(input))
        {
            busiResult = syncBean.doRead(input);
        }
        else
        {
            busiResult = syncBean.doDeal(input);
        }
        imsLogger.info("****** doBusiness successfully");

        // 创建应答对象
        imsLogger.info("****** begin to createResponse");
        BaseResponse response = syncBean.createResponse(busiResult);
        imsLogger.info("****** createResponse successfully");

        return response;
    }
}
