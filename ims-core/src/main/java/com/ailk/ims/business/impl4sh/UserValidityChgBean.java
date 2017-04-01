package com.ailk.ims.business.impl4sh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.component.LifeCycleByAcctChgComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_chgUserValidity4ShResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SChgUserValidityReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @上海变更用户有效期---帐管使用
 * @Description
 * @author xieqr
 * @Date 2012-5-29
 */
public class UserValidityChgBean extends BaseBusiBean
{
    private SChgUserValidityReq req = null;
    private LifeCycleByAcctChgComponent lifeChgCmp = null;
    BaseLifeCycleComponent lifeCycleCmp = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (SChgUserValidityReq) input[0];
        lifeChgCmp = context.getComponent(LifeCycleByAcctChgComponent.class);
        lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (null == req)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SChgUserValidityReq");
        }
        if (req.getUser_id() == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "user_id ");
        }
        if (CommonUtil.isEmpty(req.getExpire_date()) && req.getIs_valid() == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "is_valid or expire_date");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {

        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        if (CommonUtil.isValid(req.getUser_id()))
        {
            list.add(context.get3hTree().loadUser3hBean(req.getUser_id()));
        }
        return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        User3hBean bean = context.get3hTree().loadUser3hBean(req.getUser_id());
        Long acctId = bean.getAcctId();

        Date expireDt = DateUtil.getFormattedDate(req.getExpire_date());
        Integer effectFlag = req.getIs_valid();
        
        lifeChgCmp.updateUserValidity4SH(bean.getUserId(), acctId, expireDt, effectFlag);
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_chgUserValidity4ShResponse resp = new Do_chgUserValidity4ShResponse();
        return resp;
    }

    @Override
    public void destroy()
    {

    }
}
