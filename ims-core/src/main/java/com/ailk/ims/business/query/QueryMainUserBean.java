package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryMainUserResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryMainUserReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;

/**
 * @Description: 根据账户ID查询主号码 （提供给帐管）
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author yangjh
 * @Date 2012-2-10
 */
public class QueryMainUserBean extends BaseBusiBean
{
    private Long acctId;
    private SUser sUser;
    private BaseComponent baseCmp;

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryMainUserReq req = (SQueryMainUserReq) input[0];
        baseCmp = context.getComponent(BaseComponent.class);
        acctId = req.getAcct_id();
        if (!CommonUtil.isValid(acctId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        IMS3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
        CaAccountRes caRes = baseCmp.querySingle(CaAccountRes.class,
                new DBCondition(CaAccountRes.Field.acctId, bean.getAcctId()), new DBCondition(
                        CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING), new DBCondition(
                        CaAccountRes.Field.titleRoleId, EnumCodeDefine.TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER));

        if (caRes == null)
        {
            return null;
        }
        sUser = context.getComponent(UserQuery.class).querySUserByUserId(caRes.getResourceId());
        if (sUser == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_USER_NOTEXIST, caRes.getResourceId());
        }
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryMainUserResponse resp = new Do_queryMainUserResponse();
        resp.setSUser(sUser);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId));
        return list;
    }

}
