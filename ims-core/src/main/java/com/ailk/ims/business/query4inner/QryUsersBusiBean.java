package com.ailk.ims.business.query4inner;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserListResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;

/**
 * @Description
 * @author hunan
 * @Date 2011-12-26
 */
public class QryUsersBusiBean extends BaseBusiBean
{
    private List<SUser> users = null;

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        Long acctId = (Long) input[0];
        AccountComponent acctCmp = context.getComponent(AccountComponent.class);
        ProductQuery prodQry = context.getComponent(ProductQuery.class);
        // LifeCycleComponent lifeCycleCmp = context.getComponent(LifeCycleComponent.class);
        List<CaAccountRes> acctResList = acctCmp.queryAcctResByAcctIDAndType(acctId,
                EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);
        users = new ArrayList<SUser>();
        if (CommonUtil.isNotEmpty(acctResList))
        {
            for (CaAccountRes acctRes : acctResList)
            {
                Long userId = acctRes.getResourceId();
                User3hBean bean = context.get3hTree().loadUser3hBean(userId);
                if (bean.getUser() == null)
                {
                    continue;
                }
                CmResource res = bean.getUser();
                CmResIdentity identity = new CmResIdentity();
                identity.setIdentity(bean.getPhoneId());
//                List<CoProd> coProdList = prodQry.queryProdListByUserId(userId, SpecCodeDefine.MAIN_PRODUCT);
                //ljc 修改主产品查询逻辑 2012-03-06
                CoProd coProd=prodQry.queryPrimaryProductByUserId(userId);
//                if (CommonUtil.isNotEmpty(coProdList))
//                {
//                    coProd = coProdList.get(0);
//                }
                // CmResLifecycle lifeCycle = lifeCycleCmp.queryResLifeCyc(userId);
                // taoyf加于12-2-15；
                CmResLifecycle lifeCycle = bean.getUserLifeCycle();
                Long billAcctId = bean.getBillAcctId();
                SUser user = context.getComponent(UserQuery.class).sUserTransform4Billing(res, identity, coProd, lifeCycle,
                        acctRes.getAcctId(), billAcctId);
                users.add(user);
            }
        }

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryUserListResponse resp = new Do_queryUserListResponse();
        resp.setSUsers(users);
        return resp;
    }

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	Long acctId = (Long) input[0];
    	List<com.ailk.ims.ims3h.IMS3hBean> list = new ArrayList<com.ailk.ims.ims3h.IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId));
        return list;
    }

}
