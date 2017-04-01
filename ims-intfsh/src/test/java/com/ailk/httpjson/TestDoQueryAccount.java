package com.ailk.httpjson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jef.tools.Json;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReq;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.CustQueryReq;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

public class TestDoQueryAccount
{
    private static List<NameValuePair> getInputParamList()
    {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        SOperInfo sOper = new SOperInfo();
        sOper.setSo_nbr("112321312213");
        sOper.setBusi_code(9101);
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
        
        AcctQueryReqHolder acctQueryReqHolder = new AcctQueryReqHolder();
        CustQueryReq cust=new CustQueryReq();
        cust.setIsCmCustomer(true);
        AcctQueryReq acctQueryReq = new AcctQueryReq();
        List<Long> acctIds = new ArrayList<Long>();
        acctIds.add(3000072L);
        acctQueryReq.setAcctIds(acctIds);
        acctQueryReqHolder.setCustReq(cust);
        acctQueryReqHolder.setAcctReq(acctQueryReq);
        
        String sOperStr = Json.serialize(sOper);
        String acctQueryReqHolderStr = Json.serialize(acctQueryReqHolder);

        System.out.println("sOper paramter is: " + sOperStr);
        System.out.println("acctQueryHolder paramter is: " + acctQueryReqHolderStr);
        params.add(new BasicNameValuePair("sOper", sOperStr));
        params.add(new BasicNameValuePair("acctQueryHolder", acctQueryReqHolderStr));
        return params;
    }
    /**
     * jkedy 2012-4-23
     * @param args
     */
    public static void main(String[] args)
    {

        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost("http://10.8.16.51/interface/imscnsh/CN_SHMgntAction/do_queryBillExt.go");
        HttpPost httpPost = new HttpPost("http://10.8.16.62/interface/imsinner/InnerQueryAction/commonHttpJson.go");
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
