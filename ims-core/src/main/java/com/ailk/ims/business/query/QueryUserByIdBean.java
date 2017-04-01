package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryUserByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserByIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 工单告警查询
 * @author yangyang
 * @Date 2011-9-27
 */
public class QueryUserByIdBean extends BaseBusiBean
{
    private Long userId;

    @Override
    public void init(Object... input) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {

        SQueryUserByIdReq req = (SQueryUserByIdReq) input[0];
        userId = req.getUser_id();

        if (!CommonUtil.isValid(userId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "userId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
    	//2012-08-28 linzt 整理组件方法  采用load3hBean
        CmResource cmResource =context.get3hTree().loadUser3hBean(userId).getUser();
        return new Object[] { cmResource };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_QueryUserByIdResponse resp = new Do_QueryUserByIdResponse();
        resp.setUser((CmResource) result[0]);
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
