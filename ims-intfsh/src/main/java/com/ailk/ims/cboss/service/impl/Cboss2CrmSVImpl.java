package com.ailk.ims.cboss.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import jef.tools.Json;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.PaymentRsp;
import com.ailk.ims.business.cbossinterface.bo.RefundsRsp;
import com.ailk.ims.cboss.client.HttpJsonClient;
import com.ailk.ims.cboss.json2map.Transfer;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.google.gson.JsonObject;

public class Cboss2CrmSVImpl  {

    private static ImsLogger imsLogger = new ImsLogger(Cboss2CrmSVImpl.class);
    private static String serverUrl;
    /**
     * @desc手机支付缴费交易
     * @return Map
     */
    public static Map payAtCurrentTimeByBillId(Map map) throws IMSException {
        serverUrl = SysUtil.getString(EnumCodeShDefine.busi.IM_SH_CBOSS_HTTP_JSON_SERVER_ADDR);
        String json = HttpJsonClient.getJsonAsbytes(map);
        Map outMap = new HashMap();
        String bipCode = "BIP1C005";
        String activityCode = "T1001006";
        imsLogger.debug("入参报文为：" + json);
            String httpRtnInfo = HttpJsonClient.httpPost(serverUrl,json,bipCode,activityCode);
            imsLogger.debug("HTTP返回值为：" + httpRtnInfo);
            Map httpMap = splitHttpRtnInfo(httpRtnInfo);
            String respCode = httpMap.get("respCode") + "";// 0
            String respDesc = httpMap.get("respDesc") + "";// success
            if ("0".equals(respCode)) {
                imsLogger.debug("手机支付上行cboss接口调用成功");
                outMap.put("respCode", respCode);
                outMap.put("respDesc", respDesc);
                Object obj = httpMap.get("respData");
                outMap = Transfer.parseData(obj);
            } else {
                imsLogger.debug("手机支付上行cboss接口调用失败");
                outMap.put("respCode", respCode);
                outMap.put("respDesc", respDesc);
                imsLogger.error("[", respCode, "]", respDesc);
                IMSUtil.throwBusiException(ErrorCodeDefine.CBOSS_SERVICE_ERROR_CODE, respCode, respDesc);
            }
        return outMap;
    }
    
    /**
     * @desc总对总签约用户缴费
     * @return Map
     */
    public static PaymentRsp postPaidPay(String bipCode,String json) throws IMSException {
        serverUrl = SysUtil.getString(EnumCodeShDefine.busi.IM_SH_NTNCBOSS_HTTP_JSON_SERVER_ADDR);
        String activityCode = "T1000157";
        imsLogger.debug("总队总签约用户缴费入参报文为：" + json);
        String httpRtnInfo = HttpJsonClient.httpPost(serverUrl,json,bipCode,activityCode);
        imsLogger.debug("总队总签约用户缴HTTP返回值为：" + httpRtnInfo);
        PaymentRsp rsp = jsonToClass(httpRtnInfo,"PaymentRsp",PaymentRsp.class);
        return rsp;
    }
    
    /**
     * @desc总对总冲正接口
     * @return Map
     */
    public static RefundsRsp refundByNTN(String json) throws IMSException {
        serverUrl = SysUtil.getString(EnumCodeShDefine.busi.IM_SH_NTNCBOSS_HTTP_JSON_SERVER_ADDR);
        String activityCode = "T1000159";
        String bipCode ="BIP1A166" ;
        imsLogger.debug("总对总冲正入参报文为：" + json);
        String httpRtnInfo = HttpJsonClient.httpPost(serverUrl,json,bipCode,activityCode);
        imsLogger.debug("总对总冲正HTTP返回值为：" + httpRtnInfo);
        RefundsRsp rsp = jsonToClass(httpRtnInfo,"RefundsRsp",RefundsRsp.class);
        return rsp;
    }

    /**
     * 规整出参Map key1：respCode String key2: respDesc String key3: respData JSON串
     */
    private  static Map splitHttpRtnInfo(String httpRtnInfo) {
        Map httpMap = new HashMap();
        if (httpRtnInfo != null && httpRtnInfo.contains("&")) {
            int endIndex = -1;
            while ((endIndex = httpRtnInfo.indexOf('&')) != -1) {
                String tempStr = httpRtnInfo.substring(0, endIndex);
                httpRtnInfo = httpRtnInfo.substring(endIndex + 1);
                if (tempStr.contains("=")) {
                    String key = new String();
                    String value = new String();
                    key = tempStr.substring(0, tempStr.indexOf("=")).trim();
                    value = tempStr.substring(tempStr.indexOf("=") + 1).trim();
                    httpMap.put(key, value);
                }
            }
            if (httpRtnInfo.contains("=")) {
                String key = httpRtnInfo.substring(0, httpRtnInfo.indexOf("=")).trim();
                String value = httpRtnInfo.substring(httpRtnInfo.indexOf("=") + 1).trim();
                httpMap.put(key, value);
            }
        }
        return httpMap;
    }
    
    private static <T> T jsonToClass(String res, String pnodeName, Class<T> clazz)
    {
        Map<String, String> rtnmap = parseResponse(res);
        String respCode = rtnmap.get("respCode");
        String respDesc = rtnmap.get("respDesc");
        String respData = rtnmap.get("respData");
        String rspCode = null;
        String rspInfo = null;
        JsonObject jsonobj = null;
        if(CommonUtil.isValid(respData)){
            jsonobj = Json.toJsonObject(respData).getAsJsonObject(pnodeName);
        }
        if (jsonobj != null){
            rspCode = jsonobj.getAsString("rspCode");
            rspInfo = jsonobj.getAsString("rspInfo");
        }
        if (!"0".equals(respCode)){
            if(CommonUtil.isValid(rspCode)){
                respCode = rspCode;
            }
            if(CommonUtil.isValid(rspInfo)){
                respDesc = rspInfo;
            }
            IMSUtil.throwBusiException(ErrorCodeDefine.CBOSS_SERVICE_ERROR_CODE, respCode, respDesc);
        }
        if (jsonobj != null){
            return (T)Json.to(clazz, jsonobj);
        }
        return null;
    }

    private static Map<String, String> parseResponse(String response) throws BaseException
    {
        Map<String, String> rtnMap = new HashMap<String, String>();
        try
        {
            String[] pairs = response.split("&");
            for (String pair : pairs)
            {
                int pt = pair.indexOf("=");
                if (pt == -1)
                {
                    throw new IMSException("cboss response error format!!!");
                }
                String key = pair.substring(0, pt);
                String value = pair.substring(pt + 1);
                value = java.net.URLDecoder.decode(value, "UTF-8");
                rtnMap.put(key, value);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IMSException("unsupported encoding exception!!!");
        }

        return rtnMap;
    }
}
