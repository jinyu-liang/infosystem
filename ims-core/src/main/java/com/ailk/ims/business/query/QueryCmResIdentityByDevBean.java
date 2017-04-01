package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_QueryCmResIdentityByDevResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryCmResIdentityByDevReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据手机号码查询CM_RES_IDENTITY列表
 * @author yangyang
 * @Date 2011-10-25
 * @modify by xieqr 2012-02-16 add userId
 */
public class QueryCmResIdentityByDevBean extends BaseBusiBean
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
        SQueryCmResIdentityByDevReq req = (SQueryCmResIdentityByDevReq) input[0];
        devId = req.getPhone_id();
        userId = req.getUser_id();

        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(devId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.INPUT_ONE_USERID_PHONEID);
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        UserComponent uc = context.getComponent(UserComponent.class);
        List<CmResIdentity> list = new ArrayList<CmResIdentity>();
        if (CommonUtil.isValid(devId))
        {
            list = uc.queryResIdentityByIdentity(devId);
            if (CommonUtil.isEmpty(list))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_LIST_OF_CM_RES_IDENTITY_BY_DEV, devId);
            }
        }
        else
        {
            CmResIdentity res = queryResIdentityByUserId(userId);
            if (res == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CAN_NOT_FIND_LIST_OF_CM_RES_IDENTITY_BY_USER_ID, userId);
            }
            list.add(res);
        }
        return new Object[] { list };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<CmResIdentity> list = (List<CmResIdentity>) result[0];
        Do_QueryCmResIdentityByDevResponse resp = new Do_QueryCmResIdentityByDevResponse();
        resp.setList(list);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadUser3hBean(userId,devId));
		return list;
    }
    
    /**
     * @Description: 根据resourceId查询CmResIdentity
     */
    private CmResIdentity queryResIdentityByUserId(Long resourceId) throws IMSException
    {
        return queryResIdentityByUserId(resourceId, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
    }
    private CmResIdentity queryResIdentityByUserId(Long resourceId, Integer idenType) throws IMSException
    {
        return queryResIdentity(resourceId, null, idenType);
    }
    private CmResIdentity queryResIdentity(Long userId, String iden, Integer idenType) throws IMSException
    {
        if (userId == null && CommonUtil.isEmpty(iden))
        {
            return null;
        }

        List<DBCondition> conList = new ArrayList<DBCondition>();
        if (CommonUtil.isValid(userId))
        {
            conList.add(new DBCondition(CmResIdentity.Field.resourceId, userId));
        }

        if (CommonUtil.isValid(iden))
        {
            conList.add(new DBCondition(CmResIdentity.Field.identity, iden));
        }

        if (idenType != null)
        {
            conList.add(new DBCondition(CmResIdentity.Field.identityType, idenType));
        }

        return context.getComponent(UserQuery.class).querySingle(CmResIdentity.class, conList.toArray(new DBCondition[conList.size()]));
    }

}
