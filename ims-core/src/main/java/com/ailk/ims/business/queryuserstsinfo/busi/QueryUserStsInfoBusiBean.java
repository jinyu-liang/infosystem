package com.ailk.ims.business.queryuserstsinfo.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUserStsInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUserStsInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;

/**
 * @Description: 查询用户生命周期状态
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author tangjl5
 * @Date 2011-9-27
 */
public class QueryUserStsInfoBusiBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryUserStsInfoReq req = (SQueryUserStsInfoReq) input[0];
        Long userId = req.getResource_id();
        if ((userId == null || userId <= 0) && !CommonUtil.isValid(req.getPhoneId()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "userId or phoneId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryUserStsInfoReq req = (SQueryUserStsInfoReq) input[0];
        Long userId = req.getResource_id();
        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(userId, req.getPhoneId());
        CmResource res = user3hBean.getUser();
        CmResLifecycle resLifeCycle=null;
        if(ProjectUtil.is_CN_SH())
        {
            try
            {
                resLifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
            }
            catch(IMS3hNotFoundException e)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, res.getResourceId());
            }
        }
        else
        {
            try
            {
                resLifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
                if(resLifeCycle.getRecSts()!=EnumCodeDefine.IS_VALID_DATA)
                {
                 // 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
                    resLifeCycle=null;
                }
                else if (resLifeCycle.getExpireDate().before(context.getRequestDate()))
                {
                    resLifeCycle.setOsSts(CommonUtil.string2Integer(String.valueOf(resLifeCycle.getNextSts()).substring(0, 2)));

                    // @Date 2012-5-24 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
                    resLifeCycle.setSts(CommonUtil.string2Integer("10" + String.valueOf(resLifeCycle.getNextSts()).substring(6, 8)));
                }
             }
            catch(IMS3hNotFoundException e)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, res.getResourceId());
            }
        }

        Map<String, Date> periodDate = null;
        if (res.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            SysGroupCyclePattern groupCyclePatten = lifeCycleCmp.queryCaGroupCyclePattern(res, user3hBean.getCustomer(), user3hBean.getAccount());

            if (null == groupCyclePatten)
            {
                // 用户的brand_id、res_type、billing_type，客户的cust_type、cust_class，帐户的account_type未匹配到组
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_NOT_MATCHING_GROUP, res.getResourceId());
            }
            
            periodDate = lifeCycleCmp.calculateActiveSuspendDisabelStopTime(resLifeCycle.getSts(),
                    resLifeCycle.getValidDate(), resLifeCycle.getExpireDate(), groupCyclePatten.getPatternId());
        }

        Object[] object = new Object[] { res, resLifeCycle, periodDate };
        return object;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        CmResource res = (CmResource) result[0];
        CmResLifecycle resLifeCycle = (CmResLifecycle) result[1];
        Do_queryUserStsInfoResponse response = new Do_queryUserStsInfoResponse();
        if (res != null)
        {
            response.setBrand_id(res.getBrandId());
        }

        if (resLifeCycle != null)
        {
            if (null != resLifeCycle.getSts())
                response.setSts(resLifeCycle.getSts());
            if (null != resLifeCycle.getFrauldFlag())
                response.setFrauld_flag(resLifeCycle.getFrauldFlag());
            if (null != resLifeCycle.getReratingFlag())
                response.setRerating_flag(resLifeCycle.getReratingFlag());
            if (null != resLifeCycle.getUserrequestFlag())
                response.setUser_request_flag(resLifeCycle.getUserrequestFlag());
            if (null != resLifeCycle.getUnbillingFlag())
                response.setUnbilling_flag(resLifeCycle.getUnbillingFlag());
            if (null != resLifeCycle.getOsSts())
                response.setOs_sts(resLifeCycle.getOsSts());
            //2012-05-12 zhangzj3 返回8位的用户状态。
            String fullSts = context.getComponent(BaseLifeCycleComponent.class).getUserFullSts(resLifeCycle);
            response.setFull_sts(fullSts);
        }

        Map<String, Date> periodDate = (Map<String, Date>) result[2];
        if (CommonUtil.isNotEmpty(periodDate))
        {
        	response.setAvtive_valid_date(periodDate.get(ConstantDefine.TOP_UP_ACTIVE_VALIDDATE));
            response.setActive_period(periodDate.get(ConstantDefine.TOP_UP_ACTIVE_EXPIREDATE));
            response.setSuspend_period(periodDate.get(ConstantDefine.TOP_UP_SUSPEND_EXPIREDATE));
            response.setDisable_period(periodDate.get(ConstantDefine.TOP_UP_DISABLE_EXPIREDATE));
        }

        return response;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryUserStsInfoReq req = (SQueryUserStsInfoReq) input[0];
        Long userId = req.getResource_id();
        String phoneId = req.getPhoneId();
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId, phoneId));
        return list;
	}

}
