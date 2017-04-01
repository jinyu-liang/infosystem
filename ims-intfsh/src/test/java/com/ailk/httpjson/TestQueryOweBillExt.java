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
import com.ailk.openbilling.persistence.imscnsh.entity.OweFeeBillIn;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class TestQueryOweBillExt
{
    private static List<NameValuePair> getInputParamList()
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7022);
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
        
        OweFeeBillIn qweFeeBillIn = new OweFeeBillIn();
        qweFeeBillIn.setAcct_id(186840L);
        qweFeeBillIn.setRet_type((short)1);
        qweFeeBillIn.setReal_type((short)1);
        
        String sOperStr = JsonUtil.serialize(sOper);
        String qweFeeBillInStr = JsonUtil.serialize(qweFeeBillIn);
        System.out.println("sOper paramter is: " + sOperStr);
        params.add(new BasicNameValuePair("sOper", sOperStr));
        params.add(new BasicNameValuePair("qweFeeBillIn", qweFeeBillInStr));
        return params;
    }
    /**
     * xieqr 2012-4-20
     * @param args
     */
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
