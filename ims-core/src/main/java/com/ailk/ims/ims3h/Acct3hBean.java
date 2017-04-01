package com.ailk.ims.ims3h;

import com.ailk.ims.complex.AccountComplex;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
/**
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 */
public class Acct3hBean extends IMS3hBean
{

    public Acct3hBean(AccountComplex object)
    {
        super(object);
    }

    @Override
    public boolean isMatch(IMS3hBean another)
    {
        if(another instanceof Acct3hBean){
        	AccountComplex complex = (AccountComplex)another.getObject();
            CaAccount account = complex.getAccount();
            if(this.getAcctId().equals(account.getAcctId())){
                return true;
            }
        }
        return false;
    }

    public boolean isMatch(Long acctId){
        return this.getAcctId().equals(acctId);
    }
    
    @Override
    public Cust3hBean getParent()
    {
        if(this.parent != null){
            return (Cust3hBean)this.parent;
        }
        Cust3hBean custBean = tree.loadCust3hBean(getParentId());
        setParent(custBean);
        return custBean;
    }

    public CaAccount getAccount(){
        return this.getObject().getAccount();
    }
    
    public Long getAcctId(){
        return this.getAccount().getAcctId();
    }

    public Long getCustId(){
        return getParentId();
    }
    
    public CmCustomer getCustomer()throws IMSException
    {
        Cust3hBean custBean = this.getParent();
        return custBean.getObject();
    }
    
    /**
     * caohm5 modify 2012-04-27
     * 账户找客户不走CaCustomerRel这层关系
     */
    public Long getParentId()throws IMSException
    {
        if(parentId == null){
        	if(this.getAccount().getCustId()==null){
        		IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.COMMON_CUST_FOR_ACCT_NOTEXIST, this.getAcctId());
        	}
            parentId = this.getAccount().getCustId();
        }
        return parentId;
    }
    
    public boolean isSingleBalance()
    {
        return this.getAccount().getBalanceType().intValue() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE;
    }
    
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
    
    @Override
    protected AccountComplex getObject()
    {
        return (AccountComplex)this.object;
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

}
