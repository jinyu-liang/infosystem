package com.ailk.ims.business.sdlinterface.query;

import java.util.List;

import jef.common.wrapper.Holder;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SAccountInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SQueryAcctInfoSdlReq;

/**
 * 上海 根据用户编号查询账户 编号 SDL 接口
 * 
 * @Description
 * @author xieqr
 * @Date 2012-6-8
 */
public class QueryAcctByUserId4SdlBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws BaseException
    {

    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {

        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQueryAcctInfoSdlReq req = (SQueryAcctInfoSdlReq) input[0];
        Holder holder = (Holder) input[1];
        SAccountInfo acctInfo = (SAccountInfo) holder.get();
        User3hBean bean = context.get3hTree().loadUser3hBean(req.getResourceId(), req.getPhoneId());
        acctInfo.setAcctId(bean.getAcctId());

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {

        return new Do_sdlResponse();
    }

    @Override
    public void destroy()
    {

    }

}
