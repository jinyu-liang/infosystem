package com.ailk.ims.business.query4inner;

import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAccountResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAccountReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;

/**
 * 查询账户信息并返回给账管
 * @Description
 * @author wukl
 * @Date 2012-3-1
 */
public class QryAccountBusiBean extends BaseBusiBean
{

    private SAccount acct;

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryAccountReq req = (SQueryAccountReq) input[0];
        Long acctId = req.getAcct_id();
        Long userId = req.getUser_id();
        String phoneId = req.getPhone_id();
        if (!CommonUtil.isValid(acctId) && !CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "acct_id , user_id ,phone_id");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        CaAccount account = null;
        Map dataMap = (Map) IMSUtil.getRequestContextParam(ConstantDefine.THREAD_QUERY_IMS3HBEAN);
        if (CommonUtil.isNotEmpty(dataMap) && dataMap.get(ConstantDefine.ACTIVE_SDL_SACCOUNTEX) != null)
        {
            account = (CaAccount) dataMap.get(ConstantDefine.ACTIVE_SDL_SACCOUNTEX);
        }
        if (account != null)
        {
            acct = context.getComponent(AccountQuery.class).sAccountTransform4Billing(account);
            return null;
        }

        SQueryAccountReq req = (SQueryAccountReq) input[0];
        Long acctId = req.getAcct_id();
        Long userId = req.getUser_id();
        String phoneId = req.getPhone_id();
        try
        {
            IMS3hBean bean = context.get3hTree().load3hBean(null, acctId, userId, phoneId);
            CaAccount dmAcct = bean.getAccount();
            acct = context.getComponent(AccountQuery.class).sAccountTransform4Billing(dmAcct);
        }
        catch (IMSException e)
        {
            // 查询不到不抛错 luojb
            imsLogger.info(e);
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryAccountResponse resp = new Do_queryAccountResponse();
        resp.setSAccount(acct);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}

}
