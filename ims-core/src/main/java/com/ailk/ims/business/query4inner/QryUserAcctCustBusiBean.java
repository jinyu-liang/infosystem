package com.ailk.ims.business.query4inner;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_qryUserAcctCustResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQryUserAcctCustReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;

/**
 * @Description
 * @author hunan
 * @Date 2011-12-26
 */
public class QryUserAcctCustBusiBean extends BaseBusiBean
{

    private SUser user;
    private SAccount acct;
    private SCustomer cust;

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {

        SQryUserAcctCustReq req = (SQryUserAcctCustReq) input[0];
        if (!CommonUtil.isValid(req.getPhone_id()) && !CommonUtil.isValid(req.getUser_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "phoneId and userId ");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        long start = System.currentTimeMillis();
        imsLogger.debug("begin to do something", context);
        SQryUserAcctCustReq req = (SQryUserAcctCustReq) input[0];
        this.queryUserAcctCustInfo(req);
        imsLogger.debug("finish to do something", start, context);
        return null;
    }

    private void queryUserAcctCustInfo(SQryUserAcctCustReq req)
    {
        String phoneId = req.getPhone_id();
        Long userId = req.getUser_id();
        User3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);
        if (bean.getUser() == null)
        {
            return;
        }
        Long belongAcctId = bean.getAcctId();
        Long billAcctId = bean.getBillAcctId();
        CmCustomer dmCust = bean.getCustomer();
        CaAccount dmAcct = bean.getAccount();
        CmResource resource = bean.getUser();
        CmResIdentity resIdentity = new CmResIdentity();
        resIdentity.setIdentity(bean.getPhoneId());

        UserQuery userCmp = context.getComponent(UserQuery.class);
        ProductQuery prodCmp = context.getComponent(ProductQuery.class);
        PartyComponent paryCmp = context.getComponent(PartyComponent.class);
        
        if (resource == null)
            return;
        CoProd coProd = prodCmp.queryPrimaryProductByUserId(resource.getResourceId());

        //2012-08-27 linzt 整理组件方法  采用load3hBean 
        CmResLifecycle resLifeCycle = null;
        try
        {
            context.get3hTree().loadUser3hBean(resource.getResourceId()).getUserLifeCycle();
        }
        catch(IMS3hNotFoundException e)
        {
        	imsLogger.error(e,e);
        }
        user = userCmp.sUserTransform4Billing(resource, resIdentity, coProd, resLifeCycle, belongAcctId, billAcctId);
        if(dmAcct == null)
            return;
        acct = context.getComponent(AccountComponent.class).sAccountTransform4Billing(dmAcct);
        // 查询个人客户信息
        if (dmCust == null)
            return;
        if (IMSUtil.isPersonCust(dmCust.getCustomerClass()))
        {
            CmIndividual individual = paryCmp.queryInvidualByCustId(dmCust.getCustId());
            CmIndividualName individualName = paryCmp.queryInvidualNameByCustId(dmCust.getCustId());
            cust = context.getComponent(CustomerComponent.class).sCustTransform4Billing(dmCust, individual, individualName, null);
        }
        else
        {
            if (CommonUtil.isValid(bean.getCustId()))
            {
                CmOrgName orgName = paryCmp.queryCmOrgNameByPartyId(bean.getCustId());
                cust = context.getComponent(CustomerComponent.class).sCustTransform4Billing(dmCust, null, null, orgName);
            }
        }

    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_qryUserAcctCustResponse resp = new Do_qryUserAcctCustResponse();
        resp.setSAccount(acct);
        resp.setSCustomer(cust);
        resp.setSUser(user);
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	return null;
    }

}
