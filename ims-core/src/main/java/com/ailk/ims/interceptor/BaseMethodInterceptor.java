package com.ailk.ims.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;

/**
 * @Description: 基础拦截器。所有拦截器都继承这个类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-12-13
 * @Date 2012-05-18 wangdw5:[45310]3hbean中查询不到信息需要抛特定异常
 * @Date 2012-05-19 createCBSErrorMsg转移到IMSUtil中
 */
public abstract class BaseMethodInterceptor implements MethodInterceptor
{
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        Object result = null;
        CBSErrorMsg errorMsg = null;
        IMSContext context = null;
        long start = 0;
        try
        {
            context = getContext(invocation);
            start = System.currentTimeMillis();
            imsLogger.info("intercepted by " + this.getClass().getName(), context);

            result = invokeMethod(invocation, context);

            if (result instanceof BaseResponse && ((BaseResponse) result).getErrorMsg() == null)
            {
                errorMsg = IMSUtil.createCBSErrorMsg(context, null);
                ((BaseResponse) result).setErrorMsg(errorMsg);
            }
        }
        catch (Exception t)
        {
            imsLogger.error("ims error catched:",t);
            if (context == null)
            {
                imsLogger.error("throw out exception because of inner service", context);
                throw t;
            }
            
            if (IMSUtil.isInnerSystem(context))
            {
                /**
                 * @Date 2012-05-18 wangdw5:[45310]3hbean中查询不到信息需要抛特定异常 如果是内部接口,在特定情况下(IMS3hNotFoundException异常并且是查询接口)不抛异常而是返回空
                 */
                if (!(t instanceof IMS3hNotFoundException) || context.isNotQueryBusi())
                {
                	imsLogger.error("throw out exception because of inner service", context);
                    throw t;// 内部接口需要再往外抛
                    
                }
                result = null;
            }else
            	errorMsg = IMSUtil.createCBSErrorMsg(context, t);
        }
        if (result == null)
        {
            if(context.getMethodConfig().getBooleanAttribute("isSdl", false)){
                //sdl接口的话直接返回1
                result = Integer.valueOf(1);
            }else{
                result = invocation.getMethod().getReturnType().newInstance();
                if(errorMsg == null){
                    errorMsg = IMSUtil.createCBSErrorMsg(context, null);
                }
                ((BaseResponse) result).setErrorMsg(errorMsg);
            }
        }
        imsLogger.debug("exit interceptor ", this.getClass().getName(),
                " cost ", System.currentTimeMillis() - start, " ms.");
        return result;
    }

    /**
     * @Description: 执行后续具体的方法
     * @author : wuyj
     * @date : 2011-12-13
     */
    public abstract Object invokeMethod(MethodInvocation invocation, IMSContext context) throws Throwable;

    protected IMSContext getContext(MethodInvocation invocation)
    {
        IMSContext context = (IMSContext) IMSUtil.getRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT);
        return context;
    }

}
