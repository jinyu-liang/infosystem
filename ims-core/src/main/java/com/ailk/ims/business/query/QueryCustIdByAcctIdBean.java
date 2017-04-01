package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryCustIdByAcctIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCustIdByAcctIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据帐户编号查询客户编号
 * @author yangyang
 * @Date 2011-10-30
 */
public class QueryCustIdByAcctIdBean extends BaseBusiBean
{
    private Long acctId = null;

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryCustIdByAcctIdReq req = (SQueryCustIdByAcctIdReq) input[0];
        acctId = req.getAcct_id();

        if (CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acctId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        CmCustomer cust = null;
        try
        {
            cust = context.get3hTree().loadAcct3hBean(acctId).getCustomer();
        }
        catch(IMS3hNotFoundException e)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_CUST_BY_ACCT, acctId);
        }
        return new Object[] { cust.getCustId() };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_QueryCustIdByAcctIdResponse resp = new Do_QueryCustIdByAcctIdResponse();
        resp.setCust_id((Long) result[0]);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId));
        return list;
    }

}
