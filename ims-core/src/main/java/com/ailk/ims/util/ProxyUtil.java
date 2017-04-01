package com.ailk.ims.util;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;

import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ConstantDefine;
/**
 * @Description: 代理工具类，主要对组件方法进行方法，可以跟踪组件方法的耗时时间。                
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2012-3-2
 */
public class ProxyUtil {
	public static <T extends BaseComponent> T createProxyComponent(T component){
		try{
			ProxyFactoryBean fac = new ProxyFactoryBean();
			fac.setTarget(component);
			fac.setProxyTargetClass(true);
			fac.addAdvice((MethodInterceptor)SpringUtil.getBean(ConstantDefine.SPRING_BEAN_IMS_CMPLOG_INTERCEPTOR));
	        return (T)fac.getObject();
		}catch(Exception e){
			return component;
		}
	}
}
