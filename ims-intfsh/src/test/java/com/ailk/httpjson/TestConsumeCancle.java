package com.ailk.httpjson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import jef.tools.Json;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.ailk.openbilling.persistence.imscnsh.entity.ConsumeDealReq;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class TestConsumeCancle {
    private static String getInputParamList()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7053);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short)1);
        sOper.setRegion_code((short)1);
        sOper.setCounty_code((short)1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short)1);
        sOper.setIsnormal((short)1);
        sOper.setStep_id((short)1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short)1);
        ConsumeDealReq req = new ConsumeDealReq();
        req.setGoods_name("中国");
        req.setPay_amount(100l);
        req.setOut_so_date("2012-10-10");
        req.setOut_so_nbr("2222288737737");
        req.setShop_id("1222");
        req.setShop_name("中国人民");
        req.setPhone_id("18701766389");
        
        Map map =new HashMap();
        map.put("sOper", sOper);
        map.put("consumeReq", req);
//        String sOperStr = JsonUtil.serialize(sOper);
//        System.out.println("sOper paramter is: " + sOperStr);
//        params.add(new BasicNameValuePair("sOper", sOperStr));
//        params.add(new BasicNameValuePair("resource_id", "10957"));
        
        String ret = Json.serialize(map);
        return ret;
    }
    /**
     * xieqr 2012-4-19
     * @param args
     */
    public static void main(String[] args)
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://10.10.142.235:18083/com.cmcc.sh.boss.interfaces.bossforcrm");
         httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
//        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");

        try
        {
//            HttpEntity reqEntity = new UrlEncodedFormEntity(getInputParamList(), "UTF-8");
            HttpEntity reqEntity =  new StringEntity(URLEncoder.encode(getInputParamList(), "UTF-8"));
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
