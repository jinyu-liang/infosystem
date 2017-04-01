package com.ailk.ims.httpjson.request;

/**
 * @Description:请求的公共信息
 * @author wangjt
 * @Date 2012-3-28
 */
public class PubInfo
{
    private String TransactionId;
    private String InterfaceId;
    private String InterfaceType;
    private String OpId;
    private String CountyCode;
    private String OrgId;
    private String ClientIP;
    private String TransactionTime;
    private String RegionCode;

    public String getTransactionId()
    {
        return TransactionId;
    }

    public void setTransactionId(String transactionId)
    {
        TransactionId = transactionId;
    }

    public String getInterfaceId()
    {
        return InterfaceId;
    }

    public void setInterfaceId(String interfaceId)
    {
        InterfaceId = interfaceId;
    }

    public String getInterfaceType()
    {
        return InterfaceType;
    }

    public void setInterfaceType(String interfaceType)
    {
        InterfaceType = interfaceType;
    }

    public String getOpId()
    {
        return OpId;
    }

    public void setOpId(String opId)
    {
        OpId = opId;
    }

    public String getCountyCode()
    {
        return CountyCode;
    }

    public void setCountyCode(String countyCode)
    {
        CountyCode = countyCode;
    }

    public String getOrgId()
    {
        return OrgId;
    }

    public void setOrgId(String orgId)
    {
        OrgId = orgId;
    }

    public String getClientIP()
    {
        return ClientIP;
    }

    public void setClientIP(String clientIP)
    {
        ClientIP = clientIP;
    }

    public String getTransactionTime()
    {
        return TransactionTime;
    }

    public void setTransactionTime(String transactionTime)
    {
        TransactionTime = transactionTime;
    }

    public String getRegionCode()
    {
        return RegionCode;
    }

    public void setRegionCode(String regionCode)
    {
        RegionCode = regionCode;
    }

}
