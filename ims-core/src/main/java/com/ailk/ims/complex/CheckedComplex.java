package com.ailk.ims.complex;

import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;

/**
 * @Description: 账户用户相关的复合类
 * @Author wangjt
 * @Date 2011-10-7
 */
public class CheckedComplex extends BaseComplex
{
    private CaAccount account;

    private CmResource user;
    private CmResLifecycle userLifecycle;
    private CmResIdentity userResIdentity;

    public Long getAcctId()
    {
        return account == null ? null : account.getAcctId();
    }

    public Long getUserId()
    {
        return user == null ? null : user.getResourceId();
    }

    public String getPhoneId()
    {
        return userResIdentity == null ? null : userResIdentity.getIdentity();
    }

    // ---自动生成方法---
    public CaAccount getAccount()
    {
        return account;
    }

    public void setAccount(CaAccount account)
    {
        this.account = account;
    }

    public CmResource getUser()
    {
        return user;
    }

    public void setUser(CmResource user)
    {
        this.user = user;
    }

    public CmResLifecycle getUserLifecycle()
    {
        return userLifecycle;
    }

    public void setUserLifecycle(CmResLifecycle userLifecycle)
    {
        this.userLifecycle = userLifecycle;
    }

    public CmResIdentity getUserResIdentity()
    {
        return userResIdentity;
    }

    public void setUserResIdentity(CmResIdentity userResIdentity)
    {
        this.userResIdentity = userResIdentity;
    }

}
