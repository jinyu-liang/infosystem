package com.ailk.ims.business.delete;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteAcctByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteAcctByIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据用户ID删除帐户
 * 
 * @Description
 * @author yangyang
 * @Date 2011-11-21
 */
public class DeleteAcctByIdBean extends BaseBusiBean
{
    private Long acctId;

    @Override
    public void init(Object... input) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SDeleteAcctByIdReq req = (SDeleteAcctByIdReq) input[0];
        acctId = req.getAcct_id();
        if (!CommonUtil.isValid(acctId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        context.getComponent(AccountComponent.class).delAcct(acctId);
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_deleteAcctByIdResponse();
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        SDeleteAcctByIdReq req = (SDeleteAcctByIdReq) input[0];
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId)); 
        return list;
    }

}
