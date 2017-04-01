package com.ailk.ims.business.crminterface.acctInfo;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryUserStopInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryUserStopInfoReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SIdMap;
import com.ailk.openbilling.persistence.imscnsh.entity.SPrivData;
import com.ailk.openbilling.persistence.imscnsh.entity.SUserStopInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Company: Asiainfo-Linkage Technologies （China）,Inc. Hangzhou
 * @author Asiainfo-Linkage R&D deparment(HangZhou)/liukun6
 * @version 1.0 Copyright (c) 2013
 * @date 2013-8-29 下午03:22:40
 * @Description 查询用户停机原因、激活状态等
 * 
 */
public class QueryUserStopInfoBean extends BaseBusiBean {
	
	private QueryUserStopInfoReq req = null;

	@Override
	public void init(Object... input) throws BaseException {
		 req = (QueryUserStopInfoReq) input[0];

	}

	@Override
	public void validate(Object... input) throws BaseException {
		if (null==req)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryUserStopInfoReq");
        }
        if (!CommonUtil.isValid(req.getResource_id()) && !CommonUtil.isValid(req.getPhone_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id or phone_id");
        }
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		
		Map<String, Object> busiParams = new HashMap<String, Object>();
		
		SIdMap sIdMap = new SIdMap();
		if(CommonUtil.isValid(req.getResource_id())){
			sIdMap.setM_llSubId(req.getResource_id().toString());
		}else{
			sIdMap.setM_llSubId("");
		}
		if(CommonUtil.isValid(req.getPhone_id())){
			sIdMap.setM_strBillId(req.getPhone_id());
		}else{
			sIdMap.setM_strBillId("");
		}
		if(CommonUtil.isValid(req.getAcct_id())){
			sIdMap.setM_llAccId(req.getAcct_id().toString());
		}else{
			sIdMap.setM_llAccId("");
		}
		sIdMap.setM_iServiceId("");
		sIdMap.setM_llCustomerId("");
		
		SPrivData sData = new SPrivData();
		sData.setM_iOpEntityId("");
		sData.setM_iOpId("");
		sData.setM_iOrgId("");
		sData.setM_iVestOrgId("");
		busiParams.put("sPrivData", sData);
		busiParams.put("strCcsOpId", "");
		busiParams.put("sIdMap", sIdMap);
		
        long doneCode = this.getContext().getDoneCode();
        /**
         * nLanType ： 是否激活:0:未激活 1:已激活 -99:已激活 
         * strStopTime：停机时间 
         * listStopReason[n] 停机原因   23:未开通停机 ,29:未实名停机 
         */
        SUserStopInfo sUserStopInfo = HttpJsonToCrmService.getUserStopInfo(String.valueOf(doneCode), busiParams);
        
        return new Object[] { sUserStopInfo };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		SUserStopInfo sUserStopInfo = (SUserStopInfo) result[0];
		Do_queryUserStopInfoResponse resp = new Do_queryUserStopInfoResponse();
        resp.setSUserStopInfo(sUserStopInfo);
        return resp;
	}

	@Override
	public void destroy() {

	}

}
