package com.ailk.ims.complex;

import com.ailk.openbilling.persistence.acct.entity.CaAccount;

/**
 * Acct3hBean使用的账户封装类
 * @Description
 * @author luojb
 * @Date 2012-8-30
 */
public class AccountComplex extends BaseComplex
{
    private CaAccount account;

    public CaAccount getAccount()
    {
        return account;
    }

    public void setAccount(CaAccount account)
    {
        this.account = account;
    }
    
    

}
