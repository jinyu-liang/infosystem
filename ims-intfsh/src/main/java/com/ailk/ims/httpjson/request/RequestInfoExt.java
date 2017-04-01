package com.ailk.ims.httpjson.request;

import java.util.Date;
import java.util.Map;
import com.ailk.ims.util.DateUtil;

public class RequestInfoExt {

    /**
     * @param transactionId:统一业务接口的交易流水必须唯一，由最多64为数字和字母组成，必填
     * @param interfaceId：接口标识，每种业务一个，crm提供，必填
     * @param busiCode：：业务编码，每种业务一个，crm提供，必填
     * @param busiParams:参数键值对，不能为空
     */
    public RequestInfoExt(String transactionId, String interfaceId,String interfaceType, String busiCode, Map<String, String[]> busiParams)
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
        RequestExt request = new RequestExt();
        request.setBusiCode(busiCode);
        request.setBusiParams(busiParams);

        this.setPubInfo(pubInfo);
        this.setRequest(request);
    }

    private PubInfo PubInfo;
    private RequestExt Request;

    public PubInfo getPubInfo()
    {
        return PubInfo;
    }

    public void setPubInfo(PubInfo pubInfo)
    {
        PubInfo = pubInfo;
    }

    public RequestExt getRequest()
    {
        return Request;
    }

    public void setRequest(RequestExt request)
    {
        Request = request;
    }


}
