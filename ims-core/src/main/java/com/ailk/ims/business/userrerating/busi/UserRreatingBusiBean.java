package com.ailk.ims.business.userrerating.busi;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.BaseLifeCycleComponent;
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
import com.ailk.openbilling.persistence.imsinner.entity.Do_userReratingResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SUserReratingReg;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * @Description: 设置用户的rerating_flag
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2011-11-17
 * @Date 2012-4-16 tangjl5 若rerating_flag已经为1，则只输出错误提示不抛出异常
 */
public class UserRreatingBusiBean extends BaseCancelableBusiBean
{

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(Object... input) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SUserReratingReg req = (SUserReratingReg)input[0];
        if (!CommonUtil.isValid(req.getPhone_id()) && !CommonUtil.isValid(req.getResource_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "phone_id, resource_id");
        }
        
        if (null == req.getRerating_flag())
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "rerating_flag");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SUserReratingReg req = (SUserReratingReg)input[0];
        User3hBean bean = context.get3hTree().loadUser3hBean(req.getResource_id(),req.getPhone_id());
        Long userId = bean.getUserId();
        
        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
        CmResLifecycle lifecycle=null;
        if(ProjectUtil.is_CN_SH())
        {
            try
            {
                lifecycle = context.get3hTree().loadUser3hBean(userId).getUserLifeCycle();
            }
            catch(IMS3hNotFoundException e)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, userId);
            }
        }
        else
        {
            try
            {
                lifecycle = context.get3hTree().loadUser3hBean(userId).getUserLifeCycle();
                if(lifecycle.getRecSts()!=EnumCodeDefine.IS_VALID_DATA)
                {
                 // 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
                    lifecycle=null;
                }
                else if (lifecycle.getExpireDate().before(context.getRequestDate()))
                {
                    lifecycle.setOsSts(CommonUtil.string2Integer(String.valueOf(lifecycle.getNextSts()).substring(0, 2)));

                    // @Date 2012-5-24 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
                    lifecycle.setSts(CommonUtil.string2Integer("10" + String.valueOf(lifecycle.getNextSts()).substring(6, 8)));
                }
             }
            catch(IMS3hNotFoundException e)
            {
            	imsLogger.error(e,e);
            }
        }
        if (lifecycle.getReratingFlag() != EnumCodeDefine.LIFECYCLE_RERATING_FLAG_NO && lifecycle.getReratingFlag() == req.getRerating_flag())
        {
            // @Date 2012-4-16 tangjl5 若rerating_flag已经为1，则只输出错误提示不抛出异常
            imsLogger.error("user<"+ lifecycle.getResourceId() +">'s flag<rerating_flag> type <1> is exist", context);
//          IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_FLAG_IS_ALREADY_EXIST, userId, "rerating_flag", req.getRerating_flag());
        }
        
        lifeCycleCmp.setReratingFlag(userId, req.getRerating_flag());
        
        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_userReratingResponse();
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }


	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SUserReratingReg req = (SUserReratingReg)input[0];
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadUser3hBean(req.getResource_id(),req.getPhone_id()));
		return list;
	}

}
