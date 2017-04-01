package com.ailk.ims.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import jef.codegen.EntityEnhancer;
import jef.tools.ArrayUtils;
import jef.tools.IOUtils;
import jef.tools.reflect.BeanUtils;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.easyframe.sdl.sdlbuffer.COBB_Stream;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.FileUtil;

/**
 * @Description: 本地运行的引擎类，比如可以再单元测试中调用此类进行spring上下文的初始化等等
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-9-27
 */
public class LocalRunner
{
    public static void main(String[] args) throws Exception
    {
    }

    /**
     * @Description:webserice的本地运行
     * @param is
     * @param serviceName
     * @return
     * @throws Exception
     */
    /*
     * public static BaseResponse runWebservice(InputStream is,String serviceName) throws Exception{ Object servObj =
     * SpringUtil.getBean(ConstantDefine.SPRING_BEAN_IMS_INFOMGNTSERVICE); // OMElement element = WSUtil.createOMElement(is); //
     * Method method = WSUtil.getServiceMethod(element.getLocalName(),servObj.getClass()); // Object[] params =
     * RPCUtil.processRequest(element,method, new DefaultObjectSupplier(), null); // Object[] input = null; // if (params.length >
     * 1) { // input = new Object[params.length - 1]; // for (int i = 1; i < params.length; i++) { // input[i - 1] = params[i]; //
     * } // } Method method=null; Object[] params=null; BaseResponse response = (BaseResponse)method.invoke(servObj, params);
     * return response; }
     */
    /**
     * @Description:webserice的本地运行
     * @param is
     * @param serviceName
     * @return
     * @throws Exception
     */
    /*public static int runSdlServer(String path, Object service, String method) throws Exception
    {
        String msg = FileUtil.getClassRes(path);

        return runSdlServerEx(msg, service, method);
    }

    *//**
     * @Description:webserice的本地运行
     * @param is
     * @param serviceName
     * @return
     * @throws Exception
     *//*
    public static int runSdlServerEx(String msg, Object service, String method) throws Exception
    {

        COBB_Stream in = TempObbStream.getInstance(method);
        in.open();
        in.import_data(msg.getBytes());
        COBB_Stream out = TempObbStream.getInstance(method);
        Integer ret = BeanUtils.invokeStatic(service.getClass(), method, new Object[] { in, out });

        return ret.intValue();
    }*/

    public static void initContext() throws Exception
    {
        enhance();

        initSpringContext();
        
 //       initSpringContext()中已经做了bean初始化工作luojb 2011-11-02
//        initIMSContext();

    }

    /**
     * @Description: 实体增强
     * @throws IOException
     */
    public static void enhance() throws IOException
    {
        EntityEnhancer en = new EntityEnhancer();
        for (URL url : ArrayUtils.toIterable(LocalRunner.class.getClassLoader().getResources("")))
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
    public static void initSpringContext()
    {
        OLTPServiceContext.getInstance().init("classpath*:META-INF/" + ConstantDefine.SPRING_APP_CONTEXTFILE);
    }

    /**
     * @throws ServletException
     * @Description: 初始化IMS应用系统上下文
     */
    public static void initIMSContext() throws ServletException
    {
        InitBean.init();
    }
}
