package com.ailk.ims.business.imsInterface.userreopen;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertUserReopenNotifyResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.InsertUserReopenNotifyReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.CaUserReopenNotify;
/**
 * @description:用户复机后立即加收套餐费的通知
 * @author zhangzj3
 * @date:2013-6-5
 */
public class InsertUserReopenNotifyBean extends BaseBusiBean {
	
	private InsertUserReopenNotifyReq req;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(InsertUserReopenNotifyReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if (null == req)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "req");
        }
        if (!CommonUtil.isValid(req.getAcct_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
        if (!CommonUtil.isValid(req.getResource_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id");
        }
        Integer flag = req.getFlag();
        if (flag == null){
            flag = 0;
        }
        if(flag < 0 || flag > 1){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "flag", "0,1");
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
    	context.getComponent(CheckComponentSH.class).check3HParam(null, req.getAcct_id(), req.getResource_id(), req.getBill_id());
    	CaUserReopenNotify caUser = new CaUserReopenNotify();
    	caUser.setAcctId(req.getAcct_id());
    	caUser.setBillId(req.getBill_id());
    	caUser.setResourceId(req.getResource_id());
    	caUser.setFlag(req.getFlag());
    	caUser.setExt1(req.getExt1());
    	caUser.setCreateDate(context.getRequestDate());
    	this.context.getComponent(BaseComponent.class).insert(caUser);
    	return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_insertUserReopenNotifyResponse respn=new Do_insertUserReopenNotifyResponse();
    	return respn;
    }

    @Override
    public void destroy()
    {
    }
}
