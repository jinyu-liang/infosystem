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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryBillInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryBillInfoForCrmReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 优化CRM系统的账单查询功能接口
 * @Description
 * @author zhangzhj
 * @Date 2013-4-1
 */

public class QueryBillInfoForCrmBean extends BaseBusiBean {
  
	private QueryBillInfoForCrmReq req;
	public void init(Object... input)throws BaseException {
		req = (QueryBillInfoForCrmReq)input[0];
	}
	
	@Override
	public void validate(Object... arg0) throws BaseException {
		if (req==null){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryBillInfoForCrmReq");
        }
    	if(!CommonUtil.isValid(req.getUser_id())){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "user_id");
    	}
    	if(!CommonUtil.isValid(req.getSp_code())){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sp_code");
    	}
    	if(!CommonUtil.isValid(req.getSp_serv_code())){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sp_serv_code");
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
        busiParams.put("user_id",req.getUser_id().toString());
        busiParams.put("sp_code",req.getSp_code());
        busiParams.put("sp_serv_code",req.getSp_serv_code());
        Do_queryBillInfoResponse bean = HttpJsonToCrmService.getBillInfoForCrm(String.valueOf(doneCode), busiParams);
        return new Object[] {bean};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {		
		Do_queryBillInfoResponse res=(Do_queryBillInfoResponse)result[0];
	    return res;
	}

	@Override
    public void destroy() {

    }
}
