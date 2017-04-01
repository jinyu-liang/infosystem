package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.innerqry.entity.Do_QryAcctInfoInRouteByUserIdResponse;
import com.ailk.openbilling.persistence.innerqry.entity.QryAcctInRouteReq;

/**
 * 根据用户编号,手机好,查询账户信息
 * 
 * @Description
 * @author xieqr
 * @Date 2012-10-25
 */
public class QryAcctInfoInRouteByUserIdBean extends BaseBusiBean
{

    private QryAcctInRouteReq req;

    private Long acctId;
    private Long userId;
    private String phoneId;
    private SAccount acct;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QryAcctInRouteReq) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (!CommonUtil.isValid(req.getUser_id()) && !CommonUtil.isValid(req.getPhone_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "userId or phoneId");
        }

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {

        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        try
        {
            if (CommonUtil.isValid(req.getUser_id()))
            {
                userId = req.getUser_id();
                acctId = context.getComponent(RouterComponent.class).getAcctIdByUserIdRout(req.getUser_id());
            }
            else if (CommonUtil.isValid(req.getPhone_id()))
            {
                phoneId = req.getPhone_id();
                acctId = context.getComponent(RouterComponent.class).getAcctIdByPhoneIdRout(req.getPhone_id());
            }
            IMS3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
            CaAccount dmAcct = bean.getAccount();
            acct = context.getComponent(AccountQuery.class).sAccountTransform4Billing(dmAcct);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_QryAcctInfoInRouteByUserIdResponse resp = new Do_QryAcctInfoInRouteByUserIdResponse();
        resp.setAcct(acct);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
