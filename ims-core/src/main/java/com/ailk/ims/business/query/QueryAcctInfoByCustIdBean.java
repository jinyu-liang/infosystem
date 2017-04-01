package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryAcctInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据客户编号查询账户信息
 * @author yangyang
 * @Date 2011-9-27
 */
public class QueryAcctInfoByCustIdBean extends BaseBusiBean
{
    private Long custId;

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryAcctInfoReq req = (SQueryAcctInfoReq) input[0];
        custId = req.getCust_id();

        if (custId == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "custId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        AccountComponent ac = context.getComponent(AccountComponent.class);
        List<Long> acctIds = ac.queryAcctIdsByCustId(custId);
        if (CommonUtil.isEmpty(acctIds))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_ACCTS_OF_CUST_NOTEXIST, custId);
        }
        List<CaAccount> accts = new ArrayList<CaAccount>();
        for (Long acctId : acctIds)
        {
            try
            {
                CaAccount ca = context.get3hTree().loadAcct3hBean(acctId).getAccount();
                accts.add(ca);
            }
            catch(IMS3hNotFoundException e)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTEXIST, acctId);
            }
        }
        return new Object[] { accts };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<CaAccount> accounts = (List<CaAccount>) result[0];
        Do_QueryAcctInfoResponse resp = new Do_QueryAcctInfoResponse();
        resp.setAccounts(accounts);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
      //增加获取3HBean方法 yanchuan 2012-05-15
        List<IMS3hBean> beans = new ArrayList<IMS3hBean>();
        beans.add(context.get3hTree().loadCust3hBean(custId));
        return beans;
    }

}
