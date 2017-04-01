package com.ailk.ims.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.proxy.IBusiProxy;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * @Description 上海受理业务拦截器
 * @author lijc3
 * @Date 2012-10-12
 */
public class IntfInterceptor implements MethodInterceptor {
    protected ImsLogger imsLogger = new ImsLogger(this.getClass());

    public Object invoke(MethodInvocation invocation) throws Throwable {
        CBSErrorMsg errorMsg = null;
        IMSContext context = null;
        Object result = null;
        long start = System.currentTimeMillis();
        imsLogger.debug("intercepted by " + this.getClass().getName());

        // 打印请求参数
        imsLogger.debug("################ dump inputs");
        if (LogUtil.isInfoEnabled()) {
            printArguments("input", invocation.getArguments());
        }
        imsLogger.debug("################ finish dump inputs");
        imsLogger.debug("################ copy inputs");
        Object[] input = new Object[invocation.getArguments().length - 1];
        System.arraycopy(invocation.getArguments(), 1, input, 0, input.length);
        imsLogger.debug("################ finish copy inputs");
        
        imsLogger.debug("################ get context");
        context = getContext(invocation);
        imsLogger.debug("################ finish get context");

        // 是否需要记录业务记录
        imsLogger.debug("################ isBusiRecord");
        boolean isBusiRecord = IMSUtil.isBusiRecord(context);
        if (isBusiRecord) {
            context.setDoneCode(BusiRecordUtil.getReceiveDoneCode(context
                    .getRequestDate()));
        } else {
            context.setDoneCode(System.currentTimeMillis());
        }
        imsLogger.debug("################ finish isBusiRecord");
        try {
            imsLogger.debug("################ getProxy");
            IBusiProxy proxy =(IBusiProxy)SpringUtil.getBean("intf_business_proxy");
            imsLogger.debug("################ proxy.doService");
            result = proxy.doService(context, input);
            imsLogger.debug("################ finish proxy.doService");
        } catch (Exception t) {
            imsLogger.error("ims error catched:", context, t);
            imsLogger.info("##############IntfInterceptor####################");
            imsLogger.error(t, t);
            imsLogger.info("##############IntfInterceptor####################");
            result = doProxyException(context, invocation.getMethod().getReturnType(), t);
        }


        if (result == null) {
            result = invocation.getMethod().getReturnType().newInstance();
            if (errorMsg == null) {
                errorMsg = IMSUtil.createCBSErrorMsg(context, null);
            }
            ((BaseResponse) result).setErrorMsg(errorMsg);

        }

        IMSUtil.removeRouterParam();

        if (result instanceof BaseResponse
                && ((BaseResponse) result).getErrorMsg() == null) {
            errorMsg = IMSUtil.createCBSErrorMsg(context, null);
            ((BaseResponse) result).setErrorMsg(errorMsg);
        }
        // 打印返回对象
        if (LogUtil.isInfoEnabled()) {
            printArguments("output", result);
        }

        long end = System.currentTimeMillis();
        imsLogger.debug("exit interceptor ", this.getClass().getName(),
                " cost ", end - start, " ms.");
        return result;
    }
    
    public Object doProxyException(IMSContext context,Class<?> retrunType,Throwable t) throws Exception
    {
        BaseResponse result = context.getBusiFailedResponse();
        if(result == null)
            result = (BaseResponse)retrunType.newInstance();
        CBSErrorMsg errorMsg = IMSUtil.createCBSErrorMsg(context, t);;
        result.setErrorMsg(errorMsg);
        return result;
    }
    
    protected IMSContext getContext(MethodInvocation invocation) {
        IMSContext context = new IMSContext();
        context.setRequestDate(DateUtil.currentDate());

        // SOper比较特殊重要，所以这里先提取出来
        if (CommonUtil.isNotEmpty(invocation.getArguments())
                && invocation.getArguments()[0] instanceof SOperInfo)
            context.setOper((SOperInfo) invocation.getArguments()[0]);

        String methodName = invocation.getMethod().getName();
        String serviceStr = (((ReflectiveMethodInvocation) invocation)
                .getThis()).toString();
        String serviceName = serviceStr.substring(0, serviceStr.indexOf("@"));

        // 2012-02-16 wuyujie : 请求最开始把日志前缀初始化后，不用后续每次打印日志的去拼接，提高效率
        StringBuffer prefix_sb = new StringBuffer();

        prefix_sb
                .append("[")
                .append(serviceName.substring(serviceName.lastIndexOf(".") + 1))
                .append(".").append(methodName).append("] ");
        context.setServicePrefix(prefix_sb.toString());

        BaseNode serviceNode = BusiUtil.getServiceNode(serviceName);
        context.setServiceConfig(serviceNode);

        BaseNode methodNode = BusiUtil.getMethodNode(serviceName, methodName);
        // 请求的方法没有配置

        if (methodNode != null) {
            // 把methodNode放入上下文中
            context.setMethodConfig(methodNode);
        } else if (!methodName.startsWith("deal_")) {
            // 如果methodNode == null 并且方法不是以deal_开头的，需要报错
            IMSUtil.throwBusiException("method \"" + methodName
                    + "\" in service \"" + serviceName + "\" not found!");
        }

        try {
            // 如果是跨模块之间的交叉调用，那么需要把前一个业务流程context关联起来，作为parentContext;
            IMSContext parentContext = (IMSContext) IMSUtil
                    .getRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT);
            if (parentContext != null) {
                context.setParent(parentContext);
            }
            // 同时把线程变量里的context设置成当前的context
            IMSUtil.setRequestContextParam(
                    ConstantDefine.REQCONTEXT_KEY_CONTEXT, context);
        } catch (Exception e) {
            imsLogger.error(e, e);
        }
        return context;
    }
    private void printArguments(String type, Object... args) {
        // 打印参数如果跑异常，不能影响业务执行，所以catch
        if (!SysUtil.getBoolean(SysCodeDefine.busi.IS_INTF_DUMP, false)) {
            return;// NO
        }
        try {
            if (CommonUtil.isEmpty(args)) {
                return;
            }
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                imsLogger.intfDump(type + " argument[" + i + "] ", arg);
            }
        } catch (Exception e) {
            imsLogger.error(e, e);
        }
    }
}
