package com.ailk.ims.httpjson.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;

/**
 * @Description:发送json数据到服务端类
 * @author wangjt
 * @Date 2012-3-28
 */
public final class JsonSendHandler
{
    private static ImsLogger imsLogger = new ImsLogger(JsonSendHandler.class);
    /**
     * 后续会配置到数据库
     */
    // private static final String SERVER_URL = "http://10.10.142.244:8000/CRMService";
    //private static final String SERVER_URL = "http://10.10.142.140:41001/com.cmcc.sh.crm.interfaces.forother";
     private static final String SERVER_URL = getCrmAddr();
    private static final String CLIENTID = "com.cmcc.sh.boss.kernel";
    private static final String CRM_PRE = "com.cmcc.sh.crm.interfaces.forboss.";
    private static final String encoding = "UTF-8";

    /**
     * 发送一段json格式的字符串给配置的server，返回server端响应的json格式字符串
     * 
     * @author wangjt 2012-3-28
     */
    public static String sendRequest(String json, String busiCode, String transactionId)
    {
        if (CommonUtil.isEmpty(SERVER_URL))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_CRM_HTTP_JSON_ADDR);
        }

        return sendRequest(json, SERVER_URL, busiCode, transactionId);
    }

    public static String sendRequest(String json, String serverUrl, String busiCode, String transactionId)
    {
        // HttpHost proxy = new HttpHost("hzproxy.asiainfo-linkage.com", 8080);
        // httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(serverUrl);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        // httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        String preBusiCode = CRM_PRE + busiCode;
        httpPost.addHeader("OperationCode", preBusiCode);
        httpPost.addHeader("ClientId", CLIENTID);
        httpPost.addHeader("TransactionId", transactionId);

        BufferedReader reader = null;
        try
        {
//            imsLogger.debug("Request json Info  :", json);
            long start = System.currentTimeMillis();
            // StringEntity reqEntity = new StringEntity(URLEncoder.encode(json, encoding));
            StringEntity reqEntity = new StringEntity(json, encoding);
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
            imsLogger.debug(start,"****************end call CRM service****************");
//            imsLogger.debug("Response json Info  :", sb.toString());
            return sb.toString();
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
            throw new IMSException("invoke crm method error!!!");
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
    /**
     * 
     * 获取CRM 调用地址，默认从BES虚拟机参数中获取，否则从DB中获取
     * @return
     */
   public static String getCrmAddr(){
		String crmAddr = System.getProperty("crm.http.json.addr");
       if (CommonUtil.isNotEmpty(crmAddr))
       {	
    	   imsLogger.debug("get CRM_HTTP_JSON_ADDR from BES vmParam: "+crmAddr);
           return crmAddr;
       }else{
    	   crmAddr = SysUtil.getString(SysCodeDefine.busi.SH_CRM_HTTP_JSON_SERVER_ADDR);
    	   imsLogger.debug("get sh_crm_http_json_server_addr from DB Paramter: "+crmAddr);
    	   return crmAddr;
       }
   }
}
