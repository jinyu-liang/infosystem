package com.ailk.ims.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sal.client.SalClientFactory;
import com.ailk.ims.cache.OfferCacheBean;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.listener.IDBOperatorListener;
import com.ailk.ims.proxy.IBusiProxy;
import com.ailk.ims.proxy.INewBusiProxy;

/**
 * @Description: ApplicationContext的帮助类,自动装载ApplicationContext
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author zengxr
 * @Date 2011-6-29
 * @Date 2012-6-14 2012-06-14 wuyujie ： 新增getBean(Class<?> clz)
 * @Date 2012-06-14 wuyujie ：新增getDbOperatorListener()
 * @Date 2012-08-08 linzt ：NT 调用账管的接口押金服务 getBillDepositPocketService
 * @Date 2012-10-07 路由服务类
 */
public class SpringUtil implements ApplicationContextAware
{

    private static ApplicationContext context;
    private static ImsLogger logger = new ImsLogger(SpringUtil.class);
    public static ApplicationContext getApplicationContext()
    {
        return context;
    }

    /*
     * 注入ApplicationContext
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        // 在加载Spring时自动获得context
        logger.info("****** set spring application context");
        SpringUtil.context = context;
    }

    public static OfferCacheBean getOfferCacheBean(){
        return (OfferCacheBean) SpringUtil.getBean(ConstantDefine.OFFER_CACHE_BEAN);
    }
    public static RouterClient getRouteSearchService()
    {
        return (RouterClient) SpringUtil.getBean(ConstantDefine.ROUTER_SERVICE_BEAN);
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
        SalClientFactory fac = (SalClientFactory) SpringUtil.getBean(ConstantDefine.SPRING_BEAN_EF_SALCLIENTFACTORY);
        return fac.getSalClient();
    }

    /**
     * 获取一个dao的实例
     */
    public static <T> T getDao(Class<T> t)
    {
        return context.getBean(t);
    }

    public static IBusiProxy getServiceProxy()
    {
        return (IBusiProxy) SpringUtil.getBean(ConstantDefine.SPRING_BEAN_IMS_BUSINESS_PROXY);
    }
    
    public static INewBusiProxy getNewServiceProxy()
    {
        return (INewBusiProxy) SpringUtil.getBean(ConstantDefine.SPRING_BEAN_IMS_BUSINESS_PROXY);
    }

    // @Date 2012-09-14 yugb :事务一致性改造
    public static IDBOperatorListener getDbOperatorListener()
    {
        return (IDBOperatorListener) SpringUtil.getBean(ConstantDefine.SPRING_BEAN_IMS_DB_LISTENER);
    }

}
