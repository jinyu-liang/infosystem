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
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryAcctByPhoneResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctByPhoneReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据手机号码查询账户信息
 * @author yangyang
 * @Date 2011-9-27
 * @modify at 2012-02-16 by xieqr add userId
 */
public class QueryAcctInfoByDevBean extends BaseBusiBean
{
    private String devId;
    private Long userId;

    @Override
    public void init(Object... input) throws IMSException
    {

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryAcctByPhoneReq req = (SQueryAcctByPhoneReq) input[0];
        devId = req.getDevId();
        userId = req.getUserId();

        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(devId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.INPUT_ONE_USERID_PHONEID);
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        AccountComponent ac = context.getComponent(AccountComponent.class);

        CaAccount ca = null;

        if (CommonUtil.isValid(devId))
        {
            // caohm5 modify 2012-05-10
        	User3hBean bean = context.get3hTree().loadUser3hBean(devId);
        	Long resourceTemp=bean.getUser().getResourceId();
            try
            {
                ca=context.get3hTree().loadUser3hBean(resourceTemp).getAccount();
            }
            catch(IMS3hNotFoundException e)
            {   
                IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_IS_NOT_EXIST_BY_DEV, devId);
            }
        }
        else
        {
            // caohm5 modify 2012-03-08
            ca = null;
            try
            {
                ca=context.get3hTree().loadUser3hBean(userId).getAccount();
            }
            catch(IMS3hNotFoundException e)
            {   
                IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_IS_NOT_EXIST_BY_USER_ID, userId);
            }
        }

        return new Object[] { ca };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        CaAccount account = (CaAccount) result[0];
        Do_QueryAcctByPhoneResponse resp = new Do_QueryAcctByPhoneResponse();
        resp.setAccount(account);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {

    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId));
        return list;
    }

}
