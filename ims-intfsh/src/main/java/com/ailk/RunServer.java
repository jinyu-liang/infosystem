package com.ailk;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import jef.codegen.EntityEnhancer;
import jef.tools.ArrayUtils;
import jef.tools.IOUtils;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.service.TesterSvr;
import com.ailk.ims.util.ImsLogger;

/**
 * sdl java server端启动类
 * 
 * @author wangjt
 */
public class RunServer
{
    public static void main(String[] args)
    {
        try
        {
            initContext();
        }
        catch (Exception e)
        {
            ImsLogger imsLogger =new ImsLogger(RunServer.class);
            imsLogger.error("--init context for java sdl server fail:--");
            imsLogger.error(e, e);
            imsLogger.error("please fix the error");
            return;
        }

        TesterSvr.main(null);
    }

    private static void initContext() throws Exception
    {
        enhance();

        initSpringContext();

    }

    private static void enhance() throws IOException
    {
        EntityEnhancer en = new EntityEnhancer();
        for (URL url : ArrayUtils.toIterable(RunServer.class.getClassLoader().getResources("")))
        {
            File file = IOUtils.urlToFile(url);
            if (file.isDirectory())
            {
                en.setRoot(file);
                en.enhance("com.ailk.openbilling.persistence");
            }
        }
    }

    /**
     * @Description:初始化spring下上文
     */
    private static void initSpringContext()
    {
         OLTPServiceContext.getInstance().init("classpath*:META-INF/intfsh_applicationContext.xml");
//        new ClassPathXmlApplicationContext("classpath*:META-INF/intfsh_applicationContext.xml");
    }
}
