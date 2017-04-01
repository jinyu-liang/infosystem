package com.ailk.ims.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * 公用服 务拦截器，所有服务都会拦截下来，主要是创建上下文，记录请求时间，调用的服务以及方法等
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-2 2012-02-16 wuyujie : 请求最开始把日志前缀初始化后，不用后续每次打印日志的去拼接，提高效率
 * @Date 2012-3-20 luojb 删除ImsReceiveRecord、ImsReceiveRsp的引用
 * @Date 2012-3-29 wukl SDL结构不打印
 * @Date 2012-5-18 wuyujie 打印输出对象，恢复并优化SDL结构打印
 * @Date 2012-09-13 wukl 内部查询类接口改为不从数据库中取doneCode，直接使用默认值
 * @Date 2012-09-26 zengxr if no OBJECT_DUMP parameter, not print all data object
 * @Date 2012-09-26 zengxr retrieve logic for done code
 */
public class CommonServiceInterceptor extends BaseMethodInterceptor
{
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public Object invokeMethod(MethodInvocation invocation, IMSContext context) throws Throwable
    {
        long doneCode = 999999999;
        boolean isInner = IMSUtil.isInnerSystem(context);
        boolean isNotQuery = context.getMethodConfig().getBooleanAttribute(BusiUtil.ATTR_NOT_QUERY, false);
        if (isInner && !isNotQuery)
        {//内部非查询类接口
            context.setDoneCode(doneCode);
        }
        else
        {
            // 生成流水号
            doneCode = BusiRecordUtil.getReceiveDoneCode(context.getRequestDate());
            context.setDoneCode(doneCode);
        }

        imsLogger.info("doneCode : " + doneCode, context);
        imsLogger.info("isInner : " + isInner, context);
        // 打印请求参数
        if (LogUtil.isDebugEnabled())
        {
            printArguments("input", invocation.getArguments());
        }

        Object response = invocation.proceed();

        // 打印返回对象
        if (LogUtil.isDebugEnabled())
        {
            printArguments("output", response);
        }
        return response;
    }

    private void printArguments(String type, Object... args)
    {
        // 打印参数如果跑异常，不能影响业务执行，所以catch
    	if(! SysUtil.getBoolean("OBJECT_DUMP", false)){
			return;//NO
		}
        try
        {
            if (CommonUtil.isEmpty(args))
            {
                return;
            }
            for (int i = 0; i < args.length; i++)
            {
                Object arg = args[i];
                imsLogger.dump(type + " argument[" + i + "] ", arg);
            }
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
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
