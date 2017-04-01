package com.ailk.ims.ts;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.xml.ws.Endpoint;
import org.apache.log4j.Logger;
import com.ailk.ims.util.CommonUtil;

/**
 * 同步crm模拟服务,运行此服务相当于模拟在本地开启了一个crm接收BOS同步接口的webservice接口。<BR>
 * 但每个同步接口对应不同的webservice的url，<BR>
 * 所以本服务会读取 com.asiainfo.aiws.service .infosyncservice下的所有类，生成所有的接口服务。
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-3-2
 */
public class RunWSServer
{
    public static void main(String[] args)
    {
        try
        {
            String service_url_prefix = "http://localhost/";
            String packageName = "com.asiainfo.aiws.service.infosyncservice";
            Enumeration<URL> dirs = RunWSServer.class.getClassLoader().getResources(packageName.replace(".", "/"));
            List<Class> serviceClasses = new ArrayList<Class>();
            while (dirs.hasMoreElements())
            {
                URL url = dirs.nextElement();
                String dir = url.getFile();
                List<Class> subList = getAllClass(packageName, dir);
                if (CommonUtil.isNotEmpty(subList))
                {
                    serviceClasses.addAll(subList);
                }
            }

            StringBuffer sb_msg = new StringBuffer();
            if (CommonUtil.isNotEmpty(serviceClasses))
            {
                for (Class clz : serviceClasses)
                {
                    String clzImplName = clz.getName() + "Impl";
                    Class clzImpl = Class.forName(clzImplName);
                    Object serviceObj = clzImpl.newInstance();
                    String serviceUrl = service_url_prefix + clz.getSimpleName();
                    sb_msg.append("         " + serviceUrl + "\n");
                    Endpoint.publish(serviceUrl, serviceObj);
                }
            }

            Logger.getLogger(RunWSServer.class).info("****** success to publish service at : \n" + sb_msg.toString());
        }
        catch (Exception e)
        {
            Logger.getLogger(RunWSServer.class).error(e, e);
        }
    }

    public static List<Class> getAllClass(String packageName, String dir)
    {
        File file = new File(dir);
        File[] subFiles = file.listFiles();
        if (CommonUtil.isEmpty(subFiles))
            return null;
        List<Class> classes = new ArrayList<Class>();
        for (File subFile : subFiles)
        {
            String fileName = subFile.getName();
            if (!fileName.toLowerCase().endsWith(".class"))
            {
                continue;
            }
            String fullClass = packageName + "." + fileName.substring(0, fileName.toLowerCase().lastIndexOf(".class"));
            try
            {
                Class clz = Class.forName(fullClass);
                if (clz.isInterface())
                    classes.add(clz);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return classes;
    }
}
