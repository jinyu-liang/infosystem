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
import com.ailk.openbilling.persistence.imscnsh.entity.SDoDepositReq;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class TestDeoposit
{
    public static String paramJsonStr()
    {
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(7036);
        sOper.setSo_mode((short) 99);
        sOper.setSo_date("2012-02-02 12:12:12");
        sOper.setCharge_flag((short) 1);
        sOper.setCust_id(1L);
        sOper.setAcct_id(1L);
        sOper.setUser_id(1L);
        sOper.setPhone_id("1");
//        sOper.setOp_id(1);
        sOper.setProv_code((short) 1);
        sOper.setRegion_code((short) 1);
        sOper.setCounty_code((short) 1);
        sOper.setOrg_id(1);
        sOper.setRemark("1");
        sOper.setRso_nbr("1");
        sOper.setIs_monitor((short) 1);
        sOper.setIsnormal((short) 1);
        sOper.setStep_id((short) 1);
        sOper.setSource_system("1");
        sOper.setNotify_flag((short) 1);
        
        SDoDepositReq req = new SDoDepositReq();
        
        req.setAcct_id(99186840L);
        req.setPay_amount(1L);
        req.setPhone_id("15088888888");
        req.setOut_so_nbr("223456824");
        req.setOut_so_date("2012-05-28 17:27:27");
        
        Map map = new HashMap();
        map.put("sOper", sOper);
        map.put("req", req);
        String ret = Json.serialize(map);
//        System.out.println(ret);
        return ret;
    }

    /**
     * xieqr 2012-4-20
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        HttpClient httpClient = new DefaultHttpClient();
//        String postUrl= "http://10.8.16.62/interface/imscnsh/CN_SHMgntAction/commonHttpJson.go";
        String postUrl="http://10.10.140.75:18084/interface/imscnsh/CN_SHMgntAction/commonHttpJson.go";
        HttpPost httpPost = new HttpPost(postUrl);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        httpPost.addHeader("OperationCode", "com.cmcc.sh.alplatform.ims.PT-SH-FS-7036");
        httpPost.addHeader("ClientId", null);
        httpPost.addHeader("TransactionId", null);
        httpPost.addHeader("ClientToken", null);
//         httpPost.addHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");

        try
        {
            // HttpEntity reqEntity = new UrlEncodedFormEntity(getInputParamList(), "UTF-8");
            StringEntity reqEntity = new StringEntity(URLEncoder.encode(paramJsonStr(), "UTF-8"));
            
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

            // JsonObject obj = Json.toJsonObject(retJson);
            // JsonObject obj1= obj.getAsJsonObject("data");
            // JsonElement element = obj1.get("billExtOut");
            // // BillExtOut out = Json.to(BillExtOut.class, element);
            // Do_queryBillExtResponse resp = Json.to(Do_queryBillExtResponse.class, obj1);
            // PrintUtil.print("resp", resp);
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
