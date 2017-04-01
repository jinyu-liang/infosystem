package com.ailk.ims.send.init;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import jef.codegen.EntityEnhancer;
import jef.tools.ArrayUtils;
import jef.tools.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ailk.easyframe.web.common.session.ContextHolder;

/**
 * @Description:启动时加载公共信息
 * @author wangjt
 * @Date 2012-6-28
 */
public class CommonInit
{
    public static void main(String[] args) throws IOException
    {
        commonInit();
    }

    public static void commonInit()
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

            new ClassPathXmlApplicationContext("classpath*:META-INF/send_applicationContext.xml");
        }
        catch (Exception e)
        {
            Logger.getLogger(CommonInit.class).error(e, e);
            throw new RuntimeException(e.getMessage());
        }

        if (ContextHolder.getRequestContext() == null)
        {
            ContextHolder.requestInit();
        }

        // 初始化信息
        InitInfo.init();
    }
}
