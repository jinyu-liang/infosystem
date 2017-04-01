package com.ailk.ims.component.helper;

import com.ailk.ims.define.EnumCodeDefine;

/**
 * @Description:生命周期操作相关静态方法类
 * @author wangjt
 * @Date 2012-4-30
 */
public class LifeCycleHelper
{
    private LifeCycleHelper()
    {
    }

    /**
     * 由生命周期状态变更出发的事件获取同步给CRM对应的reasonCode
     */
    public static Integer getImsResStsSyncReasonCode(Integer triggerEventType, Integer billType)
    {
        Integer reasonCode = null;
        if (null != billType && triggerEventType == EnumCodeDefine.LIFECYCLE_EVENT_BALANCE_CHANGE)
        {
            if (billType == EnumCodeDefine.PROD_BILLTYPE_PREPAID)
            {
                reasonCode = EnumCodeDefine.LIFECYCLE_SYNCTO_CRM_NO_BALANCE;
            }
            else if (billType == EnumCodeDefine.PROD_BILLTYPE_POSTPAID)
            {
                reasonCode = EnumCodeDefine.LIFECYCLE_SYNCTO_CRM_CREDIT_LIMIT;
            }
        }
        else if (triggerEventType == EnumCodeDefine.LIFECYCLE_EVENT_VALIDITYCHANGE)
        {
            reasonCode = EnumCodeDefine.LIFECYCLE_SYNCTO_CRM_VALIDITY_EXPIRE;
        }
        else
        {
            reasonCode = EnumCodeDefine.LIFECYCLE_SYNCTO_CRM_EXPIRE_FROM_SUSPEND_ONEWAY;
        }

        return reasonCode;
    }

}
