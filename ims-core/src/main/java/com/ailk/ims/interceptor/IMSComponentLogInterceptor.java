package com.ailk.ims.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
/**
 * @Description: 组件Component方法拦截器，拦截组件中的所有方法，输出首尾日志，主要用于监控每个方法的耗时
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-12-13
 */
public class IMSComponentLogInterceptor implements MethodInterceptor {
	private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public Object invoke(MethodInvocation invocation) throws Throwable {
		IMSContext context = (IMSContext) IMSUtil.getRequestContextParam(ConstantDefine.REQCONTEXT_KEY_CONTEXT);
		long start = System.currentTimeMillis();
		
		String cmpStr = (((ReflectiveMethodInvocation)invocation).getThis()).toString();
		cmpStr = cmpStr.substring(cmpStr.lastIndexOf(".")+1,cmpStr.indexOf("@"));
		
		StringBuffer sb_method = new StringBuffer();
		sb_method.append(cmpStr)
				.append(".")
				.append(invocation.getMethod().getName());
		
		imsLogger.info("enter method : "+sb_method.toString(), context);
		
		Object result = invocation.proceed();
		
		imsLogger.info("exit method : "+sb_method.toString(), start,context);
		
		return result;
	}

}
