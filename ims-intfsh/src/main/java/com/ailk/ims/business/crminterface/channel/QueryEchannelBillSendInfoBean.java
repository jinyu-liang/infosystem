package com.ailk.ims.business.crminterface.channel;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryEchannelBillSendInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryEchannelBillSendInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * 
 * @Description详单发送情况查询
 * @author zhangzhj
 * @Date 2013-4-8
 */

public class QueryEchannelBillSendInfoBean extends BaseBusiBean {
   
	
	private QueryEchannelBillSendInfoReq req;
	@Override
	public void init(Object... input) throws BaseException {
		req = (QueryEchannelBillSendInfoReq)input[0];
	}

	@Override
	public void validate(Object... arg0) throws BaseException {
		if (req==null){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryEchannelBillInfoSendReq");
        }
    	if(!CommonUtil.isValid(req.getPhone_id())){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
    	}
    	if(!CommonUtil.isValid(req.getBill_month())){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bill_month");
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
	        busiParams.put("phoneId",req.getPhone_id());
	        busiParams.put("e_mail",req.getE_mail());
	        busiParams.put("billMonth",req.getBill_month());
	        Do_queryEchannelBillSendInfoResponse bean = HttpJsonToCrmService.getEchannelBillInfoSendForCrm(String.valueOf(doneCode), busiParams);
	        return new Object[] {bean};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
	    Do_queryEchannelBillSendInfoResponse res=(Do_queryEchannelBillSendInfoResponse)result[0];
	    return res;
	}
	
	@Override
	public void destroy() {
	}
}
