package com.ailk.ims.business.crminterface.channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryEchannelBillOrderInfoResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;


/**
 * E渠道详单订购情况查询
 * @Description
 * @author luowq
 * @param
 * @Date 2013-4-7
 */

public class QueryEchannelBillOrderInfoBean extends BaseBusiBean {

	private String phoneId;
	public void init(Object... input) throws BaseException {
	    phoneId = (String)input[0];
	}

	@Override
	public void validate(Object... arg0) throws BaseException {
         if(phoneId == null){
        	 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
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
	        busiParams.put("phoneId",phoneId);
	        Do_queryEchannelBillOrderInfoResponse bean = HttpJsonToCrmService.getEchannelBillOrderInfoForCrm(String.valueOf(doneCode), busiParams);
	        return new Object[] {bean};
	}
  
	
	@Override
	public BaseResponse createResponse(Object[] result) {
	    Do_queryEchannelBillOrderInfoResponse res=(Do_queryEchannelBillOrderInfoResponse)result[0];
	    return res;
	}
	
	@Override
	public void destroy() {
	}
}
