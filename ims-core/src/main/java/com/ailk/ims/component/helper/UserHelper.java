package com.ailk.ims.component.helper;

import java.util.List;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;

/**
 * @Description:用户操作相关静态方法类
 * @author wangjt
 * @Date 2011-12-23
 */
public class UserHelper
{
    private UserHelper()
    {
    }

    /**
     * 判断归属用户是否在支付帐户下的用户里
     * 
     * @author yanchuan 2011-11-29
     */
    public static boolean isExistUser(List<CmResource> resList, CmResource resource)
    {
        if (CommonUtil.isEmpty(resList))
            return false;
        if (resource == null || !CommonUtil.isValid(resource.getResourceId()))
            return true;
        for (CmResource res : resList)
        {
            if (res == null || !CommonUtil.isValid(res.getResourceId()))
                continue;
            if (res.getResourceId().longValue() == resource.getResourceId())
                return true;
        }
        return false;
    }
}
