package com.ailk.ims.common;

import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.OneTimeFee;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * 业务请求相关的信息，出参和入参,访问时间
 * @Description
 * @author wuyj
 * @Date 2012-9-21
 */
public class BusiRequestInfo
{
    private SOperInfo oper;// 操作员信息
    private Object[] otherParams;//除了SOperInfo以外的其它请求参数
    private OneTimeFee oneTimeFee;// 一次性费用节点
    private CBSErrorMsg cbsErrorMsg;
    
    public BusiRequestInfo(SOperInfo oper,Object[] otherParams){
        this.oper = oper;
        this.otherParams = otherParams;
    }
    
    public SOperInfo getOper()
    {
        return oper;
    }
    public CBSErrorMsg getCbsErrorMsg()
    {
        return cbsErrorMsg;
    }
    public void setCbsErrorMsg(CBSErrorMsg cbsErrorMsg)
    {
        this.cbsErrorMsg = cbsErrorMsg;
    }

    public Object[] getOtherParams()
    {
        return otherParams;
    }

    public void setOtherParams(Object[] otherParams)
    {
        this.otherParams = otherParams;
    }

    public OneTimeFee getOneTimeFee()
    {
        return oneTimeFee;
    }

    public void setOneTimeFee(OneTimeFee oneTimeFee)
    {
        this.oneTimeFee = oneTimeFee;
    }

    public void setOper(SOperInfo oper)
    {
        this.oper = oper;
    }
}
