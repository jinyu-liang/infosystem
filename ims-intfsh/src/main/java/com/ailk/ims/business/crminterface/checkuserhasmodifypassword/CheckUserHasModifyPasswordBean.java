package com.ailk.ims.business.crminterface.checkuserhasmodifypassword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_checkUserHaveModifyPasswordResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.ModifyPwdInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

public class CheckUserHasModifyPasswordBean extends BaseBusiBean {
	
	private String phoneId;
    @Override
    public void init(Object... input) throws BaseException
    {
    	phoneId=(String)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if(CommonUtil.isEmpty(phoneId)){
    		 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
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
    	Map<String, String> busiParams = new HashMap<String, String>();
    	busiParams.put("billId", phoneId);
    	long doneCode = this.getContext().getDoneCode();
    	ModifyPwdInfo crmFlag=HttpJsonToCrmService.checkUserHasModifyPassword(String.valueOf(doneCode), busiParams);
    	return new Object[]{crmFlag};
    	
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	ModifyPwdInfo crmFlag=(ModifyPwdInfo)result[0];
    	
    	Do_checkUserHaveModifyPasswordResponse respn=new Do_checkUserHaveModifyPasswordResponse();
    	respn.setModifyPwdInfo(crmFlag);
    	return respn;
    }

    @Override
    public void destroy()
    {
    }
}
