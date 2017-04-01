package com.ailk.ims.interceptor;

import java.util.Date;
import jef.common.log.Logger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import com.ailk.ims.common.BusiConfigInfo;
import com.ailk.ims.common.BusiRequestInfo;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.proxy.ex.IExBusiProxy;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.xml.BaseNode;

/**
 * 服 务拦截器，所有服务都会拦截下来，公用信息在 这里进行统一的包装构建
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 * @Date 2011-8-16 tangjl5 response添加sdl接口的判断，response构造为Do_sdlResponse()对象
 * @Date 2012-3-29 wangjt:修改isEmpty方法用来判断对象的问题
 * @Date 2012-3-29 wangjt:上海项目，不需要内外部ID映射
 */
public class ImsInterceptor implements MethodInterceptor
{

    public Object invoke(MethodInvocation invocation) throws Throwable
    {
        BusiConfigInfo configInfo = getConfigInfo(invocation);//获取对应的busi.xml配置节点信息
        
        IExBusiProxy busiProxy = instanceBusiProxy(configInfo);//实例化业务代理类
        Date requestDate = busiProxy.createRequestDate();//当前请求时间
        Logger.debug("----requestDate:"+IMSUtil.formatDate(requestDate));
        long doneCode = busiProxy.createDoneCode(requestDate);//生成流水号
        
        IMSContext context = createContext(configInfo,requestDate,doneCode);//创建上下文对象
        
        
        ImsLogger logger = new ImsLogger(ImsInterceptor.class);//这个时候才创建logger主要是可以从context获取到业务前缀标识了 
        logger.buildPrefix(context);
        
        Object[] args = invocation.getArguments();
        logger.dump("input params", args);//打印入参
        
        
        BusiRequestInfo requestInfo = busiProxy.createBusiRequest(context,args);//创建请求对象
        context.setOper(requestInfo.getOper());
        //context.setBusiRequest(requestInfo);
        
        Object resp = null;
        try{
            resp = busiProxy.doProxyService(context,requestInfo.getOtherParams());
        }
        catch(Exception t){
            logger.error(t, t);
            resp = busiProxy.doProxyException(context,invocation.getMethod().getReturnType(),t);//如果抛抛异常了，不同的proxy也需要有不同的逻辑。
        }
        finally
        {
            if (context.getParent() == null)
            {
                // 如果是顶层请求，退出时需要清空
                SpringUtil.getDbOperatorListener().clearServiceFlow();
            }
        }
        logger.dump("output params", resp);//打印出参
        
        return resp;
        
    }
    
    private IExBusiProxy instanceBusiProxy(BusiConfigInfo configInfo){
        BaseNode serviceNode = configInfo.getMethodNode().getParent();
        String proxyId = serviceNode.getAttribute(ConstantDefine.SERVICE_PROXY_BEAN_ID);
        IExBusiProxy busiProxy = null;
        if (CommonUtil.isEmpty(proxyId))
        {
            IMSUtil.throwBusiException("----proxyBeanId must config in busi.xml for service["+serviceNode.getAttribute("bean")+"]");
        }
        else
        {
            busiProxy = (IExBusiProxy)SpringUtil.getBean(proxyId);
        }
        
        return busiProxy;
    }
    
    
    
    public IMSContext createContext(BusiConfigInfo configInfo,Date requestDate,long doneCode){
        IMSContext context = new IMSContext();
        
        /// 如果是跨模块之间的交叉调用，那么需要把前一个业务流程context关联起来，作为parentContext;
        IMSContext parentContext = (IMSContext) IMSUtil.getRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT);
        if (parentContext != null)
        {
            context.setParent(parentContext);
        }
        // 同时把线程变量里的context设置成当前的context
        IMSUtil.setRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT, context);
        
        
        context.setRequestDate(requestDate);
        context.setDoneCode(doneCode);
        context.setServiceConfig(configInfo.getServiceNode());
        context.setMethodConfig(configInfo.getMethodNode());
        
        return context;
    }
    
    public BusiConfigInfo getConfigInfo(MethodInvocation invocation)throws Exception
    {
        String methodName = invocation.getMethod().getName();
        String serviceStr = (((ReflectiveMethodInvocation) invocation).getThis()).toString();
        String serviceName = serviceStr.substring(0, serviceStr.indexOf("@"));
        
        //这里需要做个特殊判断，如果是deal_xxx方法，则需要调整下，去找对应的read_xxx方法节点
        if (methodName.startsWith("deal_")){
            methodName = methodName.replaceFirst("deal_", "read_");
        }
        
        BaseNode methodNode = BusiUtil.getMethodNode(serviceName, methodName);
        
        if(methodNode == null)
            IMSUtil.throwBusiException("method is not configured in busi.xml : "+CommonUtil.concat(serviceName,"@",methodName));
        
        BusiConfigInfo busiConfig = new BusiConfigInfo(methodNode);
        
        return busiConfig;
    }

   

}
