package com.ailk.ims.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sal.client.SalClientFactory;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.proxy.IBusiProxy;

/**
 * @Description:上海专用的帐管服务
 * @author wangjt
 * @Date 2012-7-5
 */
public class SpringShUtil implements ApplicationContextAware
{

    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext()
    {
        return context;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        SpringShUtil.context = context;
    }

    public static Object getBean(String beanName)
    {
        return context.getBean(beanName);
    }

    public static String getBeanNameByClass(Class clazz)
    {
        String[] names = context.getBeanNamesForType(clazz);
        if (CommonUtil.isEmpty(names))
            return null;
        return names[0];
    }

    public static SalClient getSalClient()
    {
        SalClientFactory fac = (SalClientFactory) SpringShUtil.getBean(ConstantDefine.SPRING_BEAN_EF_SALCLIENTFACTORY);
        return fac.getSalClient();
    }

    public static IBusiProxy getServiceProxy()
    {
        return (IBusiProxy) SpringShUtil.getBean(ConstantDefine.SPRING_BEAN_IMS_BUSINESS_PROXY);
    }

}
