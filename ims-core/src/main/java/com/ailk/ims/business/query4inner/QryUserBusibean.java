package com.ailk.ims.business.query4inner;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;

/**
 * @Description
 * @author hunan
 * @Date 2011-12-26
 * @Date 2012-5-2 tangjl5 若没有用户信息时直接返回，不抛出异常
 * @Date 2012-07-30 wukl 查询三户信息时，有些时候是不需要知道主产品信息，但是每次都做了查询，产品的数据相当庞大，这样相当的耗时，上海这边去除这一步的查询
 */
public class QryUserBusibean extends BaseBusiBean
{
    @Override
    public void validate(Object... input) throws BaseException
    {
        SQueryUserReq req = (SQueryUserReq) input[0];
        String phoneId = req.getPhone_id();
        Long userId = req.getUser_id();
        if (!CommonUtil.isValid(phoneId) && !CommonUtil.isValid(userId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "phone_id and user_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        SQueryUserReq req = (SQueryUserReq) input[0];
        String phoneId = req.getPhone_id();
        Long userId = req.getUser_id();
        // @Date 2012-5-2 tangjl5 若没有用户信息时直接返回，不抛出异常
        CmResource res=null;
        if(!CommonUtil.isValid(phoneId)&&!CommonUtil.isValid(userId))
        {
            throw new IMSException("both userId and phoneId is null");
        }
      //2012-08-27 linzt 整理组件方法  采用load3hBean
        User3hBean userBean=null;
        try
        {
            userBean=context.get3hTree().loadUser3hBean(userId, phoneId); 
            res =userBean.getUser();        
        }
        catch(IMS3hNotFoundException e)
        {
            return null;
        }
        userId = res.getResourceId();
        //往session里面设置分表的字段userId lijc3
       // ContextHolder.getRequestContext().put("RESOURCE_ID", userId);
        IMSUtil.setUserRouterParam(userId);
        ProductQuery prodQry = context.getComponent(ProductQuery.class);
        CaAccountRes acctRes = null;
        Long billAcctId = null;
        Long belongAcctId = null;
        CmResIdentity resIdentity = new CmResIdentity();
        resIdentity.setIdentity(userBean.getPhoneId());
        CoProd coProd = null;
        CmResLifecycle lifeCycle = null;
        if (res != null)
        {
            // taoyf 12-2-15加
            if(ProjectUtil.is_CN_SH())
            {
                lifeCycle = context.get3hTree().loadUser3hBean(userId).getUserLifeCycle();
                acctRes = userBean.getCaAccountRes();
            }
            else
            {
                try
                {
                    lifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
                    if(lifeCycle.getRecSts()!=EnumCodeDefine.IS_VALID_DATA)
                    {
                     // 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
                        lifeCycle=null;
                    }
                    else if (lifeCycle.getExpireDate().before(context.getRequestDate()))
                    {
                        lifeCycle.setOsSts(CommonUtil.string2Integer(String.valueOf(lifeCycle.getNextSts()).substring(0, 2)));
                        //@Date 2012-07-30 wukl 查询三户信息时，有些时候是不需要知道主产品信息，但是每次都做了查询，产品的数据相当庞大，这样相当的耗时，上海这边去除这一步的查询
                        // @Date 2012-5-24 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
                        lifeCycle.setSts(CommonUtil.string2Integer("10" + String.valueOf(lifeCycle.getNextSts()).substring(6, 8)));
                    }
                    //@Date 2012-10-26 yugb :User Story #62519 如果用户是TERMINAL或者pool,则获取历史数据
                    if(lifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_TERMINAL || lifeCycle.getSts()  == EnumCodeDefine.LIFECYCLE_POOL){
                    	acctRes = context.getComponent(UserComponent.class).queryHistoryCaAcctRes(userId);
                    }else{
                    	acctRes = userBean.getCaAccountRes();
                    }
                    
                 // ljc 修改查询主产品逻辑
                    coProd = prodQry.queryPrimaryProductByUserId(userId);
                 }
                catch(IMS3hNotFoundException e)
                {
                	imsLogger.error(e,e);
                }
            }
        }
        if(acctRes != null)
        {
           billAcctId = acctRes.getResAcctId();
           belongAcctId = acctRes.getAcctId();
        }
        SUser user = context.getComponent(UserQuery.class).sUserTransform4Billing(res, resIdentity, coProd, lifeCycle,
                belongAcctId, billAcctId);
        return new Object[] { user };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryUserResponse resp = new Do_queryUserResponse();
        if (result != null && result.length > 0)
        {
            resp.setSUser((SUser) result[0]);
        }
        
        return resp;
    }

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void destroy()
    {
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	return null;
    }
}
