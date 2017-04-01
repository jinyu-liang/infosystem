package com.ailk.ims.util;

import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;

/**
 * @Description: 获取序列操作类，后续获取各个表序列值的方法都可以定义在该类中
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author yanchuan
 * @Date 2011-12-14
 * @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
 */
public class SequenceUtil
{
    /**
     * 获取客户id
     * 
     * @yanchuan 2011-12-14
     * @return
     */
    public static long getCustomerId()
    {
        return DBUtil.getSequence(CmCustomer.class);
    }

    /**
     * 获取帐户id
     * 
     * @yanchuan 2011-12-14
     * @return
     */
    public static long getAccountId()
    {
        return DBUtil.getSequence(CaAccount.class);
    }

    /**
     * 获取用户id
     * 
     * @yanchuan 2011-12-14
     * @return
     */
    public static long getResourceId()
    {
        return DBUtil.getSequence(CmResource.class);
    }

    /**
     * 获取IMS_ORDER_PRODUCT的id
     * 
     * @Description
     * @Author lijingcheng
     * @return
     */
    public static long getImsOrderProductId()
    {
        // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
        return DBUtil.getSequence();
    }
}
