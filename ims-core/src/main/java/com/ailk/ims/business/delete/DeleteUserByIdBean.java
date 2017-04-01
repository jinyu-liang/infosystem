package com.ailk.ims.business.delete;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_deleteUserByIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteCustByIdReq;
import com.ailk.openbilling.persistence.imsinner.entity.SDeleteUserByIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据用户ID删除用户
 * 
 * @Description
 * @author yangyang
 * @Date 2011-11-21
 * @Date 2012-03-29
 */
public class DeleteUserByIdBean extends BaseBusiBean
{
    private Long userId;
    private String phoneId;

    @Override
    public void init(Object... input) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SDeleteUserByIdReq req = (SDeleteUserByIdReq) input[0];
        userId = req.getUser_id();
        phoneId = req.getPhone_id();
        if (!CommonUtil.isValid(userId) && CommonUtil.isEmpty(phoneId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "user_id and phone_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        UserComponent uc = context.getComponent(UserComponent.class);
//        if (!CommonUtil.isValid(userId) && !CommonUtil.isEmpty(phoneId))
//        {
//            userId = uc.queryUserIdByPhone(phoneId);
//            if(!CommonUtil.isValid(userId))
//                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_USER_FOR_PHONE_NOTEXIST,phoneId);
//        }
        //lijc3 修改userId和phoneId同时校验
        User3hBean userBean=context.get3hTree().loadUser3hBean(userId,phoneId);
        uc.deleteUserByUserId(userBean.getUserId());
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_deleteUserByIdResponse();
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        SDeleteUserByIdReq req = (SDeleteUserByIdReq) input[0];
        Long userId= req.getUser_id();
        String phoneId = req.getPhone_id();
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId,phoneId));
        return list;
    }

}
