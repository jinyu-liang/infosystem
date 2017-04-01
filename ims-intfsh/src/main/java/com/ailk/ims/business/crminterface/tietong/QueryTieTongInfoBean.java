package com.ailk.ims.business.crminterface.tietong;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryTieTongInfoResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

public class QueryTieTongInfoBean extends BaseBusiBean {

	private String phoneId=null;
	
	@Override
	public void init(Object... input) throws BaseException {
		phoneId = input[0].toString();
	}
	
	@Override
	public void validate(Object... arg0) throws BaseException {
    	if(!CommonUtil.isValid(phoneId)){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bill_id");
    	}
	}
	
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... arg0)
			throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... arg0) throws BaseException {
		long doneCode = this.getContext().getDoneCode();
   	 Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("bill_id",phoneId);
        Do_queryTieTongInfoResponse bean = HttpJsonToCrmService.getTieTongInfoForCrm(String.valueOf(doneCode), busiParams);
        return new Object[] {bean};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {		
		Do_queryTieTongInfoResponse res=(Do_queryTieTongInfoResponse)result[0];
	    return res;
	}

	@Override
	public void destroy() {
	}

	



}
