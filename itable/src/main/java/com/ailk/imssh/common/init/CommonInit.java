package com.ailk.imssh.common.init;

import java.io.File;
import java.net.URL;
import jef.codegen.EntityEnhancer;
import jef.tools.ArrayUtils;
import jef.tools.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ailk.ims.util.ImsLogger;

/**
 * @Description:启动时加载公共信息
 * @author wangjt
 * @Date 2012-6-28
 */
public class CommonInit
{
    private static ImsLogger imsLogger = new ImsLogger(CommonInit.class);

    /**
     * 数据分发进程没有使用tsconfig.xml，这就需要我们加载spring配置
     * wukl 2012-11-14
     */
    public static void commonInitWithoutTs()
    {
        try
        {
            EntityEnhancer en = new EntityEnhancer();
            for (URL url : ArrayUtils.toIterable(CommonInit.class.getClassLoader().getResources("")))
            {
                File file = IOUtils.urlToFile(url);
                if (file.isDirectory())
                {
                    en.setRoot(file);
                    en.enhance("com.ailk.openbilling.persistence");
                }
            }

            new ClassPathXmlApplicationContext("classpath*:META-INF/itable_applicationContext.xml");
            //初始化产品定价计划policy
            LuaConfig.init();
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * 数据处理进程启动的时候  会用到tsconfig.xml加载一次spring配置，那这里就不需要再加载spring的配置
     * wukl 2012-11-14
     */
    public static void commonInitWithTs()
    {
        try
        {
            EntityEnhancer en = new EntityEnhancer();
            for (URL url : ArrayUtils.toIterable(CommonInit.class.getClassLoader().getResources("")))
            {
                File file = IOUtils.urlToFile(url);
                if (file.isDirectory())
                {
                    en.setRoot(file);
                    en.enhance("com.ailk.openbilling.persistence");
                }
            }
            //数据处理进程启动的时候  会用到tsconfig.xml加载一次spring配置文件，那这里就不需要再加载spring的配置文件
//            new ClassPathXmlApplicationContext("classpath*:META-INF/itable_applicationContext.xml");
            //初始化产品定价计划policy
            LuaConfig.init();
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
