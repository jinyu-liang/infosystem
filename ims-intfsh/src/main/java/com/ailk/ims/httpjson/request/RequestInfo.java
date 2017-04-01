package com.ailk.ims.httpjson.request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.ailk.ims.util.DateUtil;

/**
 * @Description:请求参数类，包含公共信息和具体业务信息
 * @author wangjt
 * @Date 2012-3-28
 */
public class RequestInfo
{
    /**
     * @param transactionId:统一业务接口的交易流水必须唯一，由最多64为数字和字母组成，必填
     * @param interfaceId：接口标识，每种业务一个，crm提供，必填
     * @param busiCode：：业务编码，每种业务一个，crm提供，必填
     * @param busiParams:参数键值对，不能为空
     */
    public RequestInfo(String transactionId, String interfaceId,String interfaceType, String busiCode, Map<String, String> busiParams)
    {
        PubInfo pubInfo = new PubInfo();

        pubInfo.setTransactionId(transactionId);
        pubInfo.setInterfaceId(interfaceId);
        pubInfo.setInterfaceType(interfaceType);
        pubInfo.setOpId("999990086");
        pubInfo.setCountyCode("571001");
        pubInfo.setOrgId("1");
        pubInfo.setClientIP("10.70.128.132");
        pubInfo.setTransactionTime(DateUtil.formatDate(new Date(), "yyyyMMdd HHmmss"));
        pubInfo.setRegionCode("571");

        Request request = new Request();

        request.setBusiCode(busiCode);
        request.setBusiParams(new HashMap<String, String>(busiParams));

        this.setPubInfo(pubInfo);
        this.setRequest(request);
    }

    private PubInfo PubInfo;
    private Request Request;

    public PubInfo getPubInfo()
    {
        return PubInfo;
    }

    public void setPubInfo(PubInfo pubInfo)
    {
        PubInfo = pubInfo;
    }

    public Request getRequest()
    {
        return Request;
    }

    public void setRequest(Request request)
    {
        Request = request;
    }
}
