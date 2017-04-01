package com.ailk.ims.httpjson.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;

/**
 * @Description:发送json数据到服务端类
 * @author wangyh3
 * @Date 2012-08-01
 */
public final class CbossJsonSendHandler
{
    private static ImsLogger imsLogger = new ImsLogger(CbossJsonSendHandler.class);
    /**
     * 后续会配置到数据库
     */
//    private static final String SERVER_URL = "http://10.10.141.217:8079/realtime.up"; // 测试环境
    // private static final String SERVER_URL="http://10.10.106.120:8888/localService/realtime.up"; // 生产环境
     private static final String SERVER_URL = SysUtil.getString(EnumCodeShDefine.busi.IM_SH_BOSS_HTTP_JSON_SERVER_ADDR);
    private static final String encoding = HTTP.UTF_8;

    /**
     * 发送一段json格式的字符串给配置的server，返回server端响应的json格式字符串
     * 
     * @author wangyh3 2012-08-01
     */
    public static String sendRequest(String json, String bipCode, String activityCode)
    {
        if (CommonUtil.isEmpty(SERVER_URL))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_CONFIG_IN_SYS_PARAMETER, "IM_SH_BOSS_HTTP_JSON_SERVER_ADDR");
        }
        imsLogger.debug("Request json Info  :" , json);
        String jsonStr;
        try
        {
            jsonStr = URLEncoder.encode(json, encoding);

        }
        catch (UnsupportedEncodingException e1)
        {
            throw new IMSException(e1);
        }

        return sendRequest(jsonStr, SERVER_URL, bipCode, activityCode);
    }

    public static String sendRequest(String json, String serverUrl, String bipCode, String activityCode)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(serverUrl);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");

        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("bipCode", bipCode));
        param.add(new BasicNameValuePair("activityCode", activityCode));
        param.add(new BasicNameValuePair("busiData", json));
        
        BufferedReader reader = null;
        try
        {
            
            UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(param, encoding);
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            reader = new BufferedReader(new InputStreamReader(entity.getContent(), encoding));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            
            String retStr;
            try
            {
                retStr = URLDecoder.decode(sb.toString(), encoding);
            }
            catch (UnsupportedEncodingException e1)
            {
                throw new IMSException(e1);
            }
            
            imsLogger.debug("Response json Info  :" , retStr);
            return retStr;
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            throw new IMSException(ErrorCodeDefine.INVOKE_CBOSS_METHOD_ERROR,e.getMessage());
        }
        finally
        {
        	if (reader != null){
        		try {
        			reader.close();
        		} catch (IOException e) {
        			imsLogger.error(e, e);
        		}
        	}
            httpClient.getConnectionManager().shutdown();
        }
    }
}
