package com.ailk.ims.business.delete;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteCustByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteAcctByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteCustByIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据客户ID删除客户
 * 
 * @Description
 * @author yangyang
 * @Date 2011-11-21
 */
public class DeleteCustByIdBean extends BaseBusiBean
{
    private Long custId;

    @Override
    public void init(Object... input) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SDeleteCustByIdReq req = (SDeleteCustByIdReq) input[0];
        custId = req.getCust_id();
        if (!CommonUtil.isValid(custId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "cust_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        CustomerComponent cc = context.getComponent(CustomerComponent.class);
        AccountComponent ac = context.getComponent(AccountComponent.class);
        UserComponent uc = context.getComponent(UserComponent.class);

        List<CaAccount> accts = ac.queryAccountsByCustId(custId);

        cc.deleteCustById(custId);

        if (!CommonUtil.isEmpty(accts))
        {
            for (CaAccount acct : accts)
            {
                Long acctId = acct.getAcctId();
                ac.delAcct(acctId);
                List<CmResource> crs = uc.queryUsersByAcctID(acctId);

                if (!CommonUtil.isEmpty(crs))
                {
                    for (CmResource cr : crs)
                    {
                        uc.deleteUserByUserId(cr.getResourceId());
                    }
                }
            }
        }

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_deleteCustByIdResponse();
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        SDeleteCustByIdReq req = (SDeleteCustByIdReq) input[0];
        Long custId = req.getCust_id()  ;
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadCust3hBean(custId));
        return list;
    }

}
