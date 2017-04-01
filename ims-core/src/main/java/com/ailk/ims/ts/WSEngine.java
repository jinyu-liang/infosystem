package com.ailk.ims.ts;
/**
 * @Description: webservice服务bean
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-5-23
 */
public class WSEngine {
	private String serviceUrl;
    private Class serviceClass;
    private Object serviceObj;

    public WSEngine(String serviceUrl, Class<?> serviceClass, Object serviceObj)
    {
        this.serviceUrl = serviceUrl;
        this.serviceClass = serviceClass;
        this.serviceObj = serviceObj;
    }

    public String getServiceUrl()
    {
        return serviceUrl;
    }

    public Class getServiceClass()
    {
        return serviceClass;
    }

    public Object getServiceObj()
    {
        return serviceObj;
    }
}
