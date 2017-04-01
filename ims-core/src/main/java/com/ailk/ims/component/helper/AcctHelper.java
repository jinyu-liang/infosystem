package com.ailk.ims.component.helper;

import java.util.HashMap;
import java.util.Map;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;

/**
 * @Description:账户操作相关静态方法类
 * @author wangjt
 * @Date 2011-12-23
 * @Date 2012-3-29 wangjt : billCycleValid方法优化
 */
public class AcctHelper
{
    private AcctHelper()
    {
    }

    public static boolean isAccountContact(int contactType)
    {
        return CommonUtil.isIn(contactType,SysUtil.getIntArray(SysCodeDefine.busi.INNER_ACCT_CONTACT));
    }

    /**
     * sBillCyle为空或者其属性都有值，表示合法，其余非法
     * 
     * @author yanchuan 2011-08-16
     */
    public static boolean billCycleValid(SBillCycle sBillCyle)
    {
        if (sBillCyle == null)
        {
            return true;
        }

        if (sBillCyle.getCycle_type() != null && sBillCyle.getCycle_unit() != null && sBillCyle.getFirst_bill_day() != null)
        {
            return true;
        }

        return false;
    }

    /**
     * @author yanchuan 判断账期接口是否为空 2012-1-13
     * @param billcycle
     * @return
     */
    public static boolean isEmptyBillCycle(SBillCycle billcycle)
    {
        if (billcycle == null)
            return true;
        if (billcycle.getCycle_type() != null && billcycle.getCycle_type() != 0)
            return false;
        if (billcycle.getCycle_unit() != null && billcycle.getCycle_unit() != 0)
            return false;
        if (billcycle.getFirst_bill_day() != null && billcycle.getFirst_bill_day() != 0)
            return false;
        return true;
    }

    /**
     * 获取扩展字段，并将其封装在map里
     * 
     * @author yanchuan 2011-08-18
     */
    public static Map<String, String> getExtendParam(ExtendParamList paramList)
    {
        Map<String, String> map = new HashMap<String, String>();
        if (paramList != null && !CommonUtil.isEmpty(paramList.getExtendParamList_Item()))
        {
            for (ExtendParam param : paramList.getExtendParamList_Item())
            {
                if (CommonUtil.isEmpty(param.getParam_name()) || CommonUtil.isEmpty(param.getParam_value()))
                    continue;
                String name = param.getParam_name();
                String value = param.getParam_value();
                map.put(name, value);
            }
        }

        return map;
    }
}
