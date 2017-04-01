package com.ailk.ims.proxy.ex;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;

public class InnerQueryWSBusiProxy extends InnerWSBusiProxy
{
    public BaseResponse doProxyService(IMSContext context, Object[] otherParams) throws Exception
    {
        BaseResponse resp = doService(context,otherParams);
        
        return resp;
    }
    
    
    protected BaseResponse doService(IMSContext context, Object... input) throws BaseException
    {
        ImsLogger logger = new ImsLogger(getClass());
        logger.buildPrefix(context);
        BaseResponse response = null;
        try{
            BaseBusiBean busiBean = null;
    
            String serviceName = context.getServiceName();
            String methodName = context.getMethodName();
    
            // 根据busicode获取业务处理类
            busiBean = BusiUtil.getBusiBean(serviceName, methodName);
    
            if (busiBean == null)
            {
                throw new IMSException("no busi bean is configured with <service: " + serviceName + "> <method: " + methodName
                        + ">");
            }
            busiBean.setContext(context);
    
            // 初始化工作
            busiBean.init(input);
    
            // 数据合法性校验
            busiBean.validate(input);
    
            // 创建main3hbean
            List<IMS3hBean> beans = busiBean.createMain3hBeans(input);
            if (CommonUtil.isNotEmpty(beans))
            {
                for (IMS3hBean bean : beans)
                {
                    if (bean instanceof User3hBean || bean instanceof Acct3hBean)
                    {
                        // @Date 2012-07-12 wukl 针对OBJECT_ID查询时设置分表参数
                        ContextHolder.getRequestContext().put("ACCT_ID", bean.getAcctId());
                    }
                    else
                    {
                        // @Date 2012-07-12 wukl 针对OBJECT_ID查询时设置分表参数
                        ContextHolder.getRequestContext().put("CUST_ID", bean.getCustId());
                    }
                }
            }
            context.setMain3hBean(beans);
    
            // 执行业务操作
            Object[] busiResult = busiBean.doBusiness(input);
    
            // 创建应答对象
            response = busiBean.createResponse(busiResult);
    
            // 数据清理工作
            busiBean.destroy();
            
            //@Date 2012-08-28 wukl 处理账管查询用户相关表时分表出错的问题，TODO 待分表参数的新方案改动后，可删除这段代码
            //账管自己查询用户有效期表时，由于先调用了查询用户信息的接口，此时session中设置了分表参数ACCT_ID，这就导致账管查另外用户的有效期出现分表错误；
            IMSUtil.removeRouterParam();
        }
        catch(IMS3hNotFoundException e)
        {
            logger.error(e);
        }
        return response;
    }
    
    public Object doProxyException(IMSContext context,Class<?> retrunType,Throwable t) throws Exception
    {
        BaseResponse result = (BaseResponse)retrunType.newInstance();
        CBSErrorMsg errorMsg = IMSUtil.createCBSErrorMsg(context, t);;
        result.setErrorMsg(errorMsg);
        return result;
    }

}
