package com.ailk.ims.httpjson.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import jef.tools.Json;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.BankPayRsp;
import com.ailk.ims.business.cbossinterface.bo.CcbQryBindRsp;
import com.ailk.ims.business.cbossinterface.bo.CcbUnBindRsp;
import com.ailk.ims.business.cbossinterface.bo.ChgTranAmtRsp;
import com.ailk.ims.business.cbossinterface.bo.SignInfoRsp;
import com.ailk.ims.business.cbossinterface.bo.TerminationRsp;
import com.ailk.ims.business.cbossinterface.bo.TtradeDetailRsp;
import com.ailk.ims.business.cbossinterface.bo.UmpayQryBindRsp;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.httpjson.handler.CbossJsonSendHandler;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.google.gson.JsonObject;

/**
 * @Description:封装CBOSS提供的方法
 * @author zhangzj3
 * @Date 2013-7-19
 */
public final class HttpJsonToCbossService{
    private HttpJsonToCbossService(){
        
    }
    /**
     * 调用CBOSS的充值接口(JSON) wangyh3 2012-7-31
     */
    public static BankPayRsp doBankPay(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20001", "BankPayRsp", BankPayRsp.class);
    }

    /**
     * （农行）调用CBOSS的查询绑定关系(JSON) wangyh3 2012-7-31
     * 
     * @param json
     * @return
     */
    public static SignInfoRsp queryBingRelation(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20002", "SignInfoRsp", SignInfoRsp.class);
    }

    /**
     * （农行）调用CBOSS的解除绑定关系(JSON) wangyh3 2012-7-31
     */
    public static TerminationRsp doUnbingRelation(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20003", "TerminationRsp", TerminationRsp.class);
    }

    /**
     * （农行）调用CBOSS的查询交易明细(JSON) wangyh3 2012-7-31
     */
    public static TtradeDetailRsp queryTtradeDetail(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20004", "TtradeDetailRsp", TtradeDetailRsp.class);
    }

    /**
     * （农行）调用CBOSS的修改充值金额(JSON) wangyh3 2012-7-31
     */
    public static ChgTranAmtRsp doChgTranAmt(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20005", "ChgTranAmtRsp", ChgTranAmtRsp.class);
    }

    /**
     * 调用CBOSS的（建行）查询授权结果(JSON) wangyh3 2012-7-31
     */
    public static CcbQryBindRsp doCcbQryBind(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20006", "QryBindRsp", CcbQryBindRsp.class);
    }

    /**
     * 调用CBOSS的（建行）发起解除绑定(JSON) wangyh3 2012-7-31
     */
    public static CcbUnBindRsp doCcbUnBind(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20007", "UnBindRsp", CcbUnBindRsp.class);
    }

    /**
     * 调用CBOSS的（联动优势）查询绑定关系(JSON) wangyh3 2012-7-31
     */
    public static UmpayQryBindRsp doUmpayQryBind(String json)
    {
        return invokeCbossInterface(json, "BIZ1BANK", "ACT20008", "QryBindRsp", UmpayQryBindRsp.class);
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T invokeCbossInterface(String json, String bipCode, String activityCode, String pnodeName, Class<T> clazz){
        String resp = CbossJsonSendHandler.sendRequest(json, bipCode, activityCode);
        Map<String, String> rtnmap = parseResponse(resp);
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
