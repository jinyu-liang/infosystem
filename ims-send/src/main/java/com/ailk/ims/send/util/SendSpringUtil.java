package com.ailk.ims.send.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.ailk.easyframe.web.common.dal.CommonDao;
import com.ailk.ims.send.auth.TemplateAuthService;
import com.ailk.ims.send.service.ICheckSmsSendService;
import com.ailk.ims.send.service.IImmeSmsSendService;

/**
 * @Description:context类
 * @author wangjt
 * @Date 2012-10-30
 */
public class SendSpringUtil implements ApplicationContextAware
{
    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext()
    {
        return context;
    }

    public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        context = applicationContext;
    }

    public static CommonDao getCommonDao()
    {
        return (CommonDao) context.getBean("commonDao");
    }
    
    public static CommonDao getCommonCrmDao()
    {
        return (CommonDao) context.getBean("commonCrmDao");
    }

    public static IImmeSmsSendService getSingleTableService()
    {
        return (IImmeSmsSendService) context.getBean("singleTableService");
    }

    public static ICheckSmsSendService getCheckSmsSendService()
    {
        return (ICheckSmsSendService) context.getBean("checkSmsSendService");
    }
    public static TemplateAuthService getTemplateAuthService(){
        return (TemplateAuthService) context.getBean("templateAuthService");
    }
    
    public static IImmeSmsSendService getBatchTableService()
    {
        return (IImmeSmsSendService) context.getBean("batchTableService");
    }
    
}
