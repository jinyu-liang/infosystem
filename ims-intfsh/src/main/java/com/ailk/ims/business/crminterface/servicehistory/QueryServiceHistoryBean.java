package com.ailk.ims.business.crminterface.servicehistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryServiceHistoryResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryServiceHistoryReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * 
 * @Description写一条副号码订购历史到CRM业务历史记录表
 * @author zhangzhj
 * @Date 2013-6-6
 */
public class QueryServiceHistoryBean extends BaseBusiBean {

	private QueryServiceHistoryReq req=null;
	
	@Override
	public void init(Object... input) throws BaseException {
		req = (QueryServiceHistoryReq)input[0];
	}
	
	@Override
	public void validate(Object... arg0) throws BaseException {
		  if(req == null){
	            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryServiceHistoryReq");
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
        busiParams.put("billId",req.getPhone_id());
        if(req.getBusi_spec_id() != null){
            busiParams.put("businessId",req.getBusi_spec_id().toString());
        }else{
            busiParams.put("businessId","");
        }
        busiParams.put("remarks",req.getRemark());
        Do_queryServiceHistoryResponse bean = HttpJsonToCrmService.getServiceHistoryForCrm(String.valueOf(doneCode), busiParams);
        return new Object[] {bean};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {		
		Do_queryServiceHistoryResponse res=(Do_queryServiceHistoryResponse)result[0];
	    return res;
	}

	@Override
	public void destroy() {
	}

}
