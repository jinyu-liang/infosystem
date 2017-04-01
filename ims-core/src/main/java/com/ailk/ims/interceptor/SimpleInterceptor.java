/**
 * 
 */
package com.ailk.ims.interceptor;

import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.proxy.INewBusiProxy;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * @Description 
 * @author wangdw5
 * @Date 2012-9-27
 */
public class SimpleInterceptor implements MethodInterceptor
{
    
    protected ImsLogger imsLogger = new ImsLogger(this.getClass());

    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        /**
         * 1、创建上下文对象
         * 2、实例化业务代理(proxy)
         * 3、创建流水号(需要由proxy抽象实现，因为query服务不会从获取sequence，直接返回time，提高效率)
         * 4、执行业务代理逻辑。
         */
        Object result = null;
        IMSContext context = null;
        CBSErrorMsg errorMsg = null;
        try
        {
            context = getContext(invocation);
            
            INewBusiProxy busiProxy = instanceBusiProxy(context);
            
            Object[] input = invocation.getArguments();
            
            busiProxy.createDoneCode(context, input);
            
            List<Object> args = busiProxy.invokeArguments(context, input);
            
            busiProxy.doService(context, args.toArray(new Object[args.size()]));
        }
        catch (Exception t)
        {
            if (context == null)
            {
                imsLogger.error("throw out exception because of inner service", context);
                throw t;
            }
            
            if (IMSUtil.isInnerSystem(context))
            {
                if (!(t instanceof IMS3hNotFoundException) || context.isNotQueryBusi())
                {
                    imsLogger.info("throw out exception because of inner service", context);
                    throw t;// 内部接口需要再往外抛
                    
                }
                result = null;
            }else
                errorMsg = IMSUtil.createCBSErrorMsg(context, t);
        }
        if (result == null)
        {
            result = invocation.getMethod().getReturnType().newInstance();
            if(errorMsg == null){
                errorMsg = IMSUtil.createCBSErrorMsg(context, null);
            }
            ((BaseResponse) result).setErrorMsg(errorMsg);
        }
        imsLogger.info("exit interceptor " + this.getClass().getName(), context);
        return result;
    }
    
    private INewBusiProxy instanceBusiProxy(IMSContext context)
    {
        INewBusiProxy busiProxy = null;
        String proxyBeanId = context.getMethodConfig().getParent().getAttribute(ConstantDefine.SERVICE_PROXY_BEAN_ID);
        if (CommonUtil.isEmpty(proxyBeanId))
        {
            // 默认获取webserce业务代理类
            busiProxy = SpringUtil.getNewServiceProxy();
        }
        else
        {
            busiProxy = (INewBusiProxy) SpringUtil.getBean(proxyBeanId);
        }

        return busiProxy;

    }
    
    protected IMSContext getContext(MethodInvocation invocation)
    {
        IMSContext context = new IMSContext();
        context.setRequestDate(DateUtil.currentDate());

        // SOper比较特殊重要，所以这里先提取出来
        if (CommonUtil.isNotEmpty(invocation.getArguments()) && invocation.getArguments()[0] instanceof SOperInfo)
            context.setOper((SOperInfo) invocation.getArguments()[0]);

        String methodName = invocation.getMethod().getName();
        String serviceStr = (((ReflectiveMethodInvocation) invocation).getThis()).toString();
        String serviceName = serviceStr.substring(0, serviceStr.indexOf("@"));

        // 2012-02-16 wuyujie : 请求最开始把日志前缀初始化后，不用后续每次打印日志的去拼接，提高效率
        StringBuffer prefix_sb = new StringBuffer();

        prefix_sb.append("[").append(serviceName.substring(serviceName.lastIndexOf(".") + 1)).append(".").append(methodName)
                .append("] ");
        context.setServicePrefix(prefix_sb.toString());

        BaseNode serviceNode = BusiUtil.getServiceNode(serviceName);
        context.setServiceConfig(serviceNode);

        BaseNode methodNode = BusiUtil.getMethodNode(serviceName, methodName);
        // 请求的方法没有配置

        if (methodNode != null)
        {
            // 把methodNode放入上下文中
            context.setMethodConfig(methodNode);
        }
        else if (!methodName.startsWith("deal_"))
        {
            // 如果methodNode == null 并且方法不是以deal_开头的，需要报错
            IMSUtil.throwBusiException("method \"" + methodName + "\" in service \"" + serviceName + "\" not found!");
        }

        try
        {
            // 如果是跨模块之间的交叉调用，那么需要把前一个业务流程context关联起来，作为parentContext;
            IMSContext parentContext = (IMSContext) IMSUtil.getRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT);
            if (parentContext != null)
            {
                context.setParent(parentContext);
            }
            // 同时把线程变量里的context设置成当前的context
            IMSUtil.setRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT, context);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
        return context;
    }

}
