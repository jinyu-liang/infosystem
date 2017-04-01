package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProductsByUserIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryProductsByUserIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据用户id查询产品列表
 * @author 严川
 * @Date 2011-10-20
 */
public class QueryProductsByUserIdBean extends BaseBusiBean
{
    private Long userId = null;
    private String phoneId = null;

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryProductsByUserIdReq req = (SQueryProductsByUserIdReq) input[0];
        userId = req.getUserId();
        phoneId = (String) req.getPhoneId();

        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.INPUT_ONE_USERID_PHONEID);
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        BaseProductComponent pc = context.getComponent(BaseProductComponent.class);
        UserComponent userCmp = context.getComponent(UserComponent.class);
        // 如果用户存在，则根据用户id查询电话电话
        if (userId == null && !CommonUtil.isEmpty(phoneId))
        {
            CmResource res = userCmp.queryUserByPhoneId(phoneId);
            if (res != null)
                userId = res.getResourceId();
        }
        List<CoProd> coProds = pc.queryProdListByUserId(userId);
        return new Object[] { coProds };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryProductsByUserIdResponse resp = new Do_queryProductsByUserIdResponse();
        List<CoProd> coProds = (List<CoProd>) result[0];
        resp.setProd_list(coProds);
        return resp;
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId, phoneId));
        return list;
    }

}
