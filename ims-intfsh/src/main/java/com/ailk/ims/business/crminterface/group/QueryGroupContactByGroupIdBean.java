package com.ailk.ims.business.crminterface.group;

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
import com.ailk.openbilling.persistence.imscnsh.entity.CustInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryGroupContactByGroupIdResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryGroupContactByGroupIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

public class QueryGroupContactByGroupIdBean extends BaseBusiBean {
	QueryGroupContactByGroupIdReq req;
	
	@Override
	public void init(Object... input) throws BaseException {
		req = (QueryGroupContactByGroupIdReq)input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		if(req == null){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "req"); 
        }
        if(!CommonUtil.isValid(req.getGroup_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "group_id");
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
        busiParams.put("GroupId", req.getGroup_id());
        long doneCode = this.getContext().getDoneCode();
        List<CustInfo> contactList = HttpJsonToCrmService.queryGroupContactInfo(String.valueOf(doneCode), busiParams);
        return new Object[]{contactList};
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse createResponse(Object[] result) {
		List<CustInfo> contactInfoList = (List<CustInfo>)result[0];
		Do_queryGroupContactByGroupIdResponse respn = new Do_queryGroupContactByGroupIdResponse();
        respn.setCustInfo(contactInfoList);
        return respn;
	}

	@Override
	public void destroy() {

	}

}
