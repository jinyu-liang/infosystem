package com.ailk.ims.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.IMSUtil;

/**
 * webservice的消息接收器（CXF）
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author ljc
 */
public class SoapMessageReceiverIntercept implements SOAPHandler<SOAPMessageContext>, ApplicationContextAware, InitializingBean
{
    ApplicationContext context;

    public Set<QName> getHeaders()
    {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context)
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        SOAPMessage message = context.getMessage();
        try
        {
            message.writeTo(bs);
        }
        catch (SOAPException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        IMSUtil.setRequestContextParam(ConstantDefine.REQCONTEXT_KEY_SOAP, bs.toString());

        return true;
    }

    public boolean handleFault(SOAPMessageContext arg0)
    {
        // do some thing...
        return true;
    }

    public void close(MessageContext arg0)
    {
    }

    // 通过实现ApplicationContextAware，初始化时可以得到Spring ApplicationContext，供逻辑使用
    public void setApplicationContext(ApplicationContext arg0) throws BeansException
    {
        this.context = arg0;
    }

    public void afterPropertiesSet() throws Exception
    {
    }

}
