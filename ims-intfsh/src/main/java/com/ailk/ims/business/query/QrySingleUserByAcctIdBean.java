package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BalanceShComponent;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.shinner.entity.Do_qrySingleUserByAcctIdResponse;

/**
 * @description:查询账户下的第一个用户信息
 * @author caohm5
 * @date:2012-09-07 16:37:00
 * @date 2012-11-26 luojb 性能优化
 */

public class QrySingleUserByAcctIdBean extends BaseBusiBean
{

    private Long acctId;

    @Override
    public void init(Object... input) throws BaseException
    {
        acctId = (Long) input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (!CommonUtil.isValid(acctId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
//        List<IMS3hBean> bean = new ArrayList<IMS3hBean>();
//        bean.add(context.get3hTree().loadAcct3hBean(acctId));
//        return bean;
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SUser sUser = queryFirstUserByAcctId(acctId);
        return new Object[] { sUser };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        SUser sUser = (SUser) result[0];
        Do_qrySingleUserByAcctIdResponse respn = new Do_qrySingleUserByAcctIdResponse();
        respn.setSUser(sUser);
        return respn;
    }

    @Override
    public void destroy()
    {
    }

    private SUser queryFirstUserByAcctId(Long acctId)
    {
        if (null == acctId)
        {
            return null;
        }
        Long resourceId = context.getComponent(BalanceShComponent.class).queryFirstUserIdByAcctId(acctId);
        if (null == resourceId)
        {
            return null;
        }
        User3hBean bean = context.get3hTree().loadUser3hBean(resourceId);
        if (bean.getUser() == null)
        {
            return null;
        }
        CmResource res = bean.getUser();
        CmResIdentity identity = new CmResIdentity();
        identity.setIdentity(bean.getPhoneId());
        // 查询主产品
        // @date:2012-10-11 zhangzj3 性能优化，不查询用户的主产品
        // CoProd coProd=context.getComponent(ProductQuery.class).queryPrimaryProductByUserId(resourceId);
        CmResLifecycle lifeCycle = bean.getUserLifeCycle();
        SUser sUser = context.getComponent(UserQuery.class).sUserTransform4Billing(res, identity, null, lifeCycle, acctId,
                acctId);
        return sUser;
    }
}
