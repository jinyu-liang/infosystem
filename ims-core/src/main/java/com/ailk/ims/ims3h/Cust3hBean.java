package com.ailk.ims.ims3h;

import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
/**
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 */
public class Cust3hBean extends IMS3hBean
{

    public Cust3hBean(Object object)
    {
        super(object);
    }

    @Override
    public boolean isMatch(IMS3hBean another)
    {
        if (another instanceof Cust3hBean)
        {
            CmCustomer cust = (CmCustomer) another.getObject();
            if (this.getObject().getCustId().equals(cust.getCustId()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isMatch(Long custId)
    {
        return this.getObject().getCustId().equals(custId);
    }

    @Override
    public IMS3hBean getParent()
    {
        return null;
    }

    public CmCustomer getCustomer()
    {
        return this.getObject();
    }

    @Override
    public CmCustomer getObject()
    {
        return (CmCustomer) this.object;
    }

    @Override
    public Long getCustId()
    {
        return getCustomer().getCustId();
    }

    @Override
    public Long getAcctId()
    {
        return null;
    }

    @Override
    public Long getUserId()
    {
        return null;
    }

    @Override
    public String getPhoneId()
    {
        return null;
    }

    @Override
    public CaAccount getAccount()
    {
        return null;
    }

    @Override
    public Long getParentId()
    {
        return null;
    }

}
