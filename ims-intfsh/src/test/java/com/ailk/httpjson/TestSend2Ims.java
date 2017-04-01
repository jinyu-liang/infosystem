package com.ailk.httpjson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jef.tools.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * @Description:测试ims的http+json发布情况
 * @author wangjt
 * @Date 2012-4-17
 */
public class TestSend2Ims
{
    private static List<NameValuePair> getInputParamList()
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        SOperInfo sOper = new SOperInfo();
        sOper.setAcct_id(132L);
        sOper.setBusi_code(7005);
        sOper.setSo_mode((short) 99);
        sOper.setSo_nbr("112321312213");

//        SPaUndoIn sPaUndoIn = new SPaUndoIn();
//        // sPaUndoIn.setOp_id(2343);
//        // sPaUndoIn.setSo_nbr(12345L);
//        // sPaUndoIn.setDone_date("20010101 12:12:12");
//        // sPaUndoIn.setRemark("中文中文中文中文中文");
//
//        String sOperStr = JsonUtil.serialize(sOper);
//        String sPaUndoInStr = JsonUtil.serialize(sPaUndoIn);
//        params.add(new BasicNameValuePair("sOper", sOperStr));
//        params.add(new BasicNameValuePair("sPaUndoIn", sPaUndoInStr));
        return params;
    }

    public static void main(String[] args)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://10.8.16.51/interface/imscnsh/CN_SHMgntAction/commonHttpJson.go");
        // httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");

        try
        {
            HttpEntity reqEntity = new UrlEncodedFormEntity(getInputParamList(), "UTF-8");
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(httpPost);// 发送数据
            HttpEntity entity = response.getEntity();// 获取服务器返回对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            String retJson = sb.toString();// 获取服务器返回的json字符串
            System.out.println("receive string is: " + retJson);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }
    }
}
