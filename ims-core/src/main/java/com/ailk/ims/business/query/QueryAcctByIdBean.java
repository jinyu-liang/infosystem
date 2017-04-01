package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryAcctByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctByIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 
 * @Description 根据账户编号查询账户信息
 * @author yangyang
 * @Date 2011-9-27
 */
public class QueryAcctByIdBean extends BaseBusiBean
{
    private Long accountId;

    @Override
    public void init(Object... input) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryAcctByIdReq req = (SQueryAcctByIdReq) input[0];
        accountId=req.getAcct_id();

        if (CommonUtil.isEmpty(accountId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"accountId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        Map dataMap = (Map) IMSUtil.getRequestContextParam(ConstantDefine.THREAD_QUERY_IMS3HBEAN);
        if (CommonUtil.isNotEmpty(dataMap) && dataMap.get(ConstantDefine.ACTIVE_SDL_SACCOUNTEX) != null)
        {
            return new Object[] { (CaAccount) dataMap.get(ConstantDefine.ACTIVE_SDL_SACCOUNTEX) };
        }

      

        CaAccount account = context.get3hTree().loadAcct3hBean(accountId).getAccount();
        return new Object[] { account };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        CaAccount account=(CaAccount)result[0];
        Do_QueryAcctByIdResponse resp = new Do_QueryAcctByIdResponse();
        resp.setAccount(account);
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadAcct3hBean(accountId));
		return list;
	}

}
