package com.ailk.ims.business.imsInterface.uservalidtity;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.ResValidComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.CanclePrePaidUserValidityIn;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_checkPrepaidUserValidityResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;
/**
 * @description:取消预付费卡余额有效期校验
 * @author caohm5
 * @date:2012-08-20 20:55
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class CheckPrepaidUserValidityBean extends BaseBusiBean {
	private CanclePrePaidUserValidityIn req;
    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(CanclePrePaidUserValidityIn)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if(null==req){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SQueryParam");
    	}
    	if(!CommonUtil.isValid(req.getResource_id())&&CommonUtil.isEmpty(req.getPhone_id())){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id、phone_id");
    	}
    	
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
    	Check3HParamReturn bean = context.getComponent(CheckComponentSH.class).check3HParam(null, null, req.getResource_id(), req.getPhone_id());
    	Integer flag=context.getComponent(ResValidComponent.class).checkIsCancleResVlid(req.getResource_id(),req.getPhone_id(),true,bean);
    	return new Object[]{flag};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Integer flag=(Integer)result[0];
    	Do_checkPrepaidUserValidityResponse respn=new Do_checkPrepaidUserValidityResponse();
    	if(flag==null){
    		//可以取消
    		respn.setNflag(0);
    	}else {
    		//不可以取消的标示
    		respn.setNflag(flag);
    	}
    	return respn;
    }
    @Override
    public void destroy()
    {
    }
}
