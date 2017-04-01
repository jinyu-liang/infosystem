package com.ailk.ims.business.crminterface.billinfo;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPhoneIdByImsiResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryPhoneIdByImsiReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

public class QueryPhoneIdByImsiBean extends BaseBusiBean {
	QueryPhoneIdByImsiReq req;
	@Override
	public void init(Object... input) throws BaseException {
		req = (QueryPhoneIdByImsiReq)input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		if(req == null){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "req"); 
        }
        if(!CommonUtil.isValid(req.getImei())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "imei");
        }
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("imei", req.getImei());
        long doneCode = this.getContext().getDoneCode();
        Do_queryPhoneIdByImsiResponse respn= HttpJsonToCrmService.getPhoneIdByImsi(String.valueOf(doneCode), busiParams);
        return new Object[]{respn};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_queryPhoneIdByImsiResponse respn = (Do_queryPhoneIdByImsiResponse)result[0];
        return respn;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
