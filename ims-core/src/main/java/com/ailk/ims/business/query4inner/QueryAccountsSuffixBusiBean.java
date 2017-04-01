package com.ailk.ims.business.query4inner;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAccountsSuffixResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAccountsSuffixReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SAccountList;

/**
 * 根据确定的分表，批量查询账户
 * @Description
 * @author luojb
 * @Date 2012-11-27
 */
public class QueryAccountsSuffixBusiBean extends BaseBusiBean
{
    private List<Long> acctIds = null;
    private String tableSuffix = null;
    
    @Override
    public void init(Object... input) throws BaseException
    {
        SQueryAccountsSuffixReq req = (SQueryAccountsSuffixReq)input[0];
        acctIds = req.getAcctIdList();
        tableSuffix = req.getTable_suffix();
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if(CommonUtil.isEmpty(acctIds))
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"acctIdList");
        if(!CommonUtil.isValid(tableSuffix))
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"table_suffix");
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        String tableName = "AD.CA_ACCOUNT_" + tableSuffix;
        List<CaAccount> accounts = null;
        try
        {
            accounts = context.getComponent(AccountQuery.class).queryAccountsByTableName(acctIds, tableName);
        }
        catch (Exception e)
        {
            IMSUtil.throwBusiException(e);
        }
        if(CommonUtil.isEmpty(accounts))
            return null;
        SAccount[] sAccounts = new SAccount[accounts.size()];
        for(int i=0;i<accounts.size();i++)
        {
            sAccounts[i] = context.getComponent(AccountQuery.class).sAccountTransform4Billing(accounts.get(i));
            
        }
        SAccountList sAccountList = new SAccountList();
        sAccountList.setSAccountList_Item(sAccounts);
        
        return new Object[]{sAccountList};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryAccountsSuffixResponse resp = new Do_queryAccountsSuffixResponse();
        if(result != null)
            resp.setSAccountList((SAccountList)result[0]);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
