package com.ailk.ims.httpjson.response;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description:CRM返回的json字符串对应的对象
 * @author wangjt
 * @Date 2012-3-28
 */
public class ResponseInfo
{
    private ErrorInfo ErrorInfo;
    /**
     * 具体返回的信息，以map形式存储
     */
    private Map<String, String> RetInfo;

    public void display()
    {
        System.out.println("ErrorInfo.Code:" + ErrorInfo.getCode());
        System.out.println("ErrorInfo.Message:" + ErrorInfo.getMessage());
        Iterator<Entry<String, String>> it = this.getRetInfo().entrySet().iterator();
        while (it.hasNext())
        {
            Entry<String, String> next = it.next();
            System.out.println("RetInfo." + next.getKey() + ":" + next.getValue());
        }
    }

    public ErrorInfo getErrorInfo()
    {
        return ErrorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo)
    {
        ErrorInfo = errorInfo;
    }

    /**
     * 获取具体业务返回的数据
     */
    public Map<String, String> getRetInfo()
    {
        return RetInfo;
    }

    public void setRetInfo(Map<String, String> retInfo)
    {
        RetInfo = retInfo;
    }

}
